package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public abstract class MTEWorkableMultiblock extends MetaTileEntityMultiblock implements IRecipeWorkable {
	
	protected RecipeLogic recipeLogic;
	
	public MTEWorkableMultiblock(int aID, String aName, RecipeMap<?> map) {
		super(aID, aName);
		initRecipeLogic(map);
	}
	
	public MTEWorkableMultiblock(RecipeMap<?> map) {
		initRecipeLogic(map);
	}
	
	protected void initRecipeLogic(RecipeMap<?> map) {
		recipeLogic = new MultiblockRecipeLogic(map, this);
	}
	
	@Override public boolean allowToCheckRecipe() 					{return true;}
	@Override public boolean isGivingInformation() 					{return true;}
	@Override public void startProcess() 							{}
	@Override public void stutterProcess() 							{}
	@Override public RecipeLogic getRecipeLogic() 					{return recipeLogic;}
    @Override public int increaseProgress(int aProgress)			{recipeLogic.increaseProgressTime(aProgress);return recipeLogic.getMaxProgressTime()-recipeLogic.getProgressTime();}
	
    @Override
    public ItemStack getStackIn(int idx) {
    	return this.getStackInSlot(idx);
    }
    
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
		recipeLogic.saveToNBT(aNBT);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		recipeLogic.loadFromNBT(aNBT);
	}
    
	@Override
	public void onPostTick() {
		super.onPostTick();
		if (getBaseMetaTileEntity().isServerSide()) {
    		if (structComplete && getRepairStatus() > 0) {
    			this.updateLogic();
    		}
		}
	}
	
	protected void updateLogic() {
		recipeLogic.update();
		if (recipeLogic.isActive()) {
			if (!doRandomMaintenanceDamage() || !polluteEnvironment(getPollutionPerTick(mInventory[1]))) {
				stopMachine();
			}
		}
	}
	
	@Override
	public void stopMachine() {
		super.stopMachine();
		recipeLogic.stop();
		getBaseMetaTileEntity().disableWorking();
	}
	
	@Override
	public void endProcess() {
		mEfficiency = (int) (this.getMaxEfficiency(mInventory[1]) * (this.getRepairStatus() * 1.0D / this.getIdealStatus()));
	}
	
	@Override
	public boolean spaceForOutput(Recipe recipe) {
		List<ItemStack> outputSlots = this.getOutputItems();
		List<ItemStack> allOutputs = recipe.getAllOutputs();
		
		for (ItemStack current : allOutputs) {
			int amount = current.stackSize;
			for (int i = 0; current != null && amount > 0 && i < outputSlots.size(); i++) {
				ItemStack slot = outputSlots.get(i);
				if (slot == null) {
					amount = 0;
					break;
				} else if (GT_Utility.areStacksEqual(slot, current)) {
					int newSize = Math.min(slot.getMaxStackSize(), amount + slot.stackSize);
					amount -= newSize - slot.stackSize;
				}
			}
			
			if (amount > 0) {
				return false;
			}
		}
		
		for (FluidStack fluid : recipe.getFluidOutputs()) {
			int amount = fluid.amount;
			for (int i = 0; amount > 0 && i < fluidOutputs.size(); i++) {
				FluidStack stackInSlot = fluidOutputs.get(i);
				if (GT_Utility.isFluidStackValid(stackInSlot) && stackInSlot.isFluidEqual(fluid)) {
					int tmp = Math.min(MAX_FLUID_STACK, stackInSlot.amount + fluid.amount);
					amount -= tmp - stackInSlot.amount;
				} else if (stackInSlot == null) amount = 0;
			}
			
			if (amount > 0)
				return false;
		}
		
		return true;
	}
	
	@Override
	public Map<String, List<Object>> getInfoData() {
		return InfoBuilder.create()
				.newKey("sensor.progress.percentage", recipeLogic.getDisplayProgress() * 100.0D / recipeLogic.getDisplayMaxProgress())
				.newKey("sensor.progress.secs", recipeLogic.getDisplayProgress() / 20)
				.newKey("sensor.progress.secs.1", recipeLogic.getDisplayMaxProgress() / 20)
				.newKey("metatileentity.multiblock.malfunction_amount", getRepairStatus())
				.build();
	}
	
	public static class MultiblockRecipeLogic extends RecipeLogic {

		public MultiblockRecipeLogic(RecipeMap<?> recipeMap, IRecipeWorkable machine) {
			super(recipeMap, machine);
		}
		
		@Override
		public boolean update() {
			boolean success = false;
			IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
			MTEWorkableMultiblock machine = getMachine1();
			overclockersCount = base.getOverclockerUpgradeCount();
			
			if (base.isAllowedToWork()) {
				if (progressTime > 0) {
					int tmp = progressTime;
					success = updateRecipeProgress();
					if (tmp == 0 && !success) {
						throw new IllegalStateException();
					}
				}
				
				if (progressTime == 0) {
					if (machine.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0 || wasNoEnergy) {
						if (machine.isUniversalEnergyStored(machine.getMinimumStoredEU() - 100)) {
							trySerachRecipe();
							wasNoEnergy = false;
						} else {
							previousRecipe = null;
							wasNoEnergy = true;
							triggerMachine(false);
						} 
					}
				}
			} 
			
			return success;
		}
		
		@Override
		protected boolean updateRecipeProgress() {
			if (!getMachine1().drainEnergyInput((int) (EUt * (2.0D - (getMachine1().mEfficiency / 100.0D))))) {
				if ((progressTime += progressTimeManipulator.applyAsInt((int)Math.pow(2, overclockersCount))) >= maxProgressTime) {
					progressTime = 0;
					maxProgressTime = 0;
					EUt = 0;
					
					endRecipe(previousRecipe);
					getMachine().endProcess();
					return true;
				}
			} else {
				getMachine1().stopMachine();
			}
			
			return false;
		}
		
		private MTEWorkableMultiblock getMachine1() {
			return (MTEWorkableMultiblock) getMachine();
		}
	}
}
