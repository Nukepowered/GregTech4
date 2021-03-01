package gregtechmod.api.metatileentity.implementations;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;
import gregtechmod.api.util.ListAdapter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public abstract class BasicFluidWorkable extends GT_MetaTileEntity_BasicTank implements IRecipeWorkable {
	protected RecipeLogic recipeLogic;
	
	public BasicFluidWorkable(int aID, String aName, RecipeMap<?> recipeMap) {
		super(aID, aName);
		initRecipeLogic(recipeMap);
	}
	
	public BasicFluidWorkable(RecipeMap<?> recipeMap) {
		initRecipeLogic(recipeMap);
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
    @Override public boolean isValidSlot(int aIndex) 				{return aIndex < 6;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
    @Override public int maxEUInput()								{return 32;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public RecipeLogic getRecipeLogic() 					{return recipeLogic;}
	@Override public int increaseProgress(int aProgress)			{recipeLogic.increaseProgressTime(aProgress); return recipeLogic.getMaxProgressTime()-recipeLogic.getProgressTime();}
	
	@Override public boolean doesFillContainers()	{return false;}
	@Override public boolean doesEmptyContainers()	{return false;}
	@Override public boolean canTankBeFilled()		{return true;}
	@Override public boolean canTankBeEmptied()		{return true;}
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return true;}
	
	@Override public int getInputSlot() 			{return 1;}
	@Override public int getOutputSlot() 			{return 2;}
	@Override public int getStackDisplaySlot() 		{return 6;}
	
	protected void initRecipeLogic(RecipeMap<?> recipeMap) {
		recipeLogic = new RecipeLogic(recipeMap, this);
	}
	
	@Override
	public List<FluidStack> getFluidInputs() {
		return new ListAdapter<FluidStack>(mFluid);
	}
	
	@Override
	public List<FluidStack> getFluidOutputs() {
		return Collections.emptyList();
	}
	
    @Override
    public void onPostTick() {
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	recipeLogic.update();
		}
    }
    
	@Override
	public void startProcess() {}
	
	@Override
	public void endProcess() {}

	@Override
	public void stutterProcess() {
		if (GregTech_API.sConstantEnergy) {
			int val = (int) (recipeLogic.getMaxProgressTime() * 0.1D);
			
			if (recipeLogic.getProgressTime() > val) 
				recipeLogic.increaseProgressTime(-val);
		}
	}
	
	@Override // TODO did not check for fluid output slots, not needed yet
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
					amount -= newSize;
				}
			}
			
			if (amount > 0) {
				return false;
			}
		}
		
		for (FluidStack fluid : recipe.getFluidOutputs()) {
			int amount = this.fill(fluid.copy(), false);
			if (amount < fluid.amount) {
				return false;
			}
		}
		
		return true;
    }
	
	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (GT_Utility.isFluidStackValid(resource)) {
			List<FluidStack> fluidInputs = this.getFluidInputs();
			for (int i = 0; i < fluidInputs.size(); i++) {
				FluidStack stackInSlot = fluidInputs.get(i);
				if (!GT_Utility.isFluidStackValid(stackInSlot) || stackInSlot.isFluidEqual(resource)) {
					int space = getCapacity() - stackInSlot.amount;
					int toFill = resource.amount <= space  ? resource.amount : space;
					if (doFill) {
						stackInSlot.amount += toFill;
						// TODO could be dupe, may change resource
					}
					
					return toFill;
				}
			}
		}
		
		return 0;
	}
	
	@Override
	public FluidStack drain(ForgeDirection aSide, FluidStack aFluid, boolean doDrain) {
		if (GT_Utility.isFluidStackValid(aFluid)) {
			List<FluidStack> fluidOutputs = this.getFluidOutputs();
			for (int i = 0; i < fluidOutputs.size(); i++) {
				FluidStack stackInSlot = fluidOutputs.get(i);
				if (GT_Utility.isFluidStackValid(stackInSlot) && aFluid.isFluidEqual(stackInSlot)) {
					int amount = Math.min(aFluid.amount, stackInSlot.amount);
					FluidStack result = stackInSlot.copy();
					result.amount = amount;
					if (doDrain) {
						if (stackInSlot.amount == amount) {
							fluidOutputs.set(i, null);
						} else stackInSlot.amount -= amount;
					}
					
					return result;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection aSide, int maxDrain, boolean doDrain) {
		List<FluidStack> fluidOutputs = this.getFluidOutputs();
		for (int i = 0; i < fluidOutputs.size(); i++) {
			FluidStack stackInSlot = fluidOutputs.get(i);
			if (GT_Utility.isFluidStackValid(stackInSlot)) {
				int amount = Math.min(maxDrain, stackInSlot.amount);
				FluidStack result = stackInSlot.copy();
				result.amount = amount;
				if (doDrain) {
					if (stackInSlot.amount == amount) {
						fluidOutputs.set(i, null);
					} else stackInSlot.amount -= amount;
				}
				
				return result;
			}
		}
		
		return null;
	}
    
	@Override
	public Map<String, List<Object>> getInfoData() {
		return InfoBuilder.create()
				.newKey("sensor.progress.percentage", recipeLogic.getDisplayProgress() * 100.0D / recipeLogic.getDisplayMaxProgress())
				.newKey("sensor.progress.secs", recipeLogic.getDisplayProgress() / 20)
				.newKey("sensor.progress.secs.1", recipeLogic.getDisplayMaxProgress() / 20)
				.build();
	}
	
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	@Override
	public boolean allowToCheckRecipe() {
		return true;
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
}
