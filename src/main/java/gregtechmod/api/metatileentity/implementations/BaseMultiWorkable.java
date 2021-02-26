package gregtechmod.api.metatileentity.implementations;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;
import gregtechmod.api.util.ListAdapter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

/** Base workable multiblock machines class
 * @author TheDarkDnKTv
 *
 */
public abstract class BaseMultiWorkable extends MetaTileEntity implements IRecipeWorkable {
	
	protected RecipeLogic recipeLogic;
	protected boolean needCheckStruct = true;
	protected boolean structComplete = false;

	public BaseMultiWorkable(int aID, String aBasicName, RecipeMap<?> map) {
		super(aID, aBasicName);
		initRecipeLogic(map);
	}

	public BaseMultiWorkable(RecipeMap<?> map) {
		initRecipeLogic(map);
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean allowToCheckRecipe() 					{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isGivingInformation() 					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
    @Override public int maxEUInput()								{return 128;}
    @Override public int maxEUStore()								{return 10000;}
	@Override public RecipeLogic getRecipeLogic() 					{return recipeLogic;}
    @Override public int increaseProgress(int aProgress)			{recipeLogic.increaseProgressTime(aProgress);return recipeLogic.getMaxProgressTime()-recipeLogic.getProgressTime();}
	
    protected abstract boolean checkMachine();
    
    public boolean isStructComplete() {
    	return structComplete;
    }
    
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide()) {
    		if (needCheckStruct) {
    			structComplete = checkMachine();
        		needCheckStruct = false;
        		getBaseMetaTileEntity().setActive(structComplete);
    		}
    		
    		if (structComplete) {
    			recipeLogic.update();
    		}
    	}
    }
    
	protected void initRecipeLogic(RecipeMap<?> map) {
		recipeLogic = new RecipeLogic(map, this) {
			@Override protected void triggerMachine(boolean value) {}
		};
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		recipeLogic.saveToNBT(aNBT);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		needCheckStruct = true;
		recipeLogic.loadFromNBT(aNBT);
	}

	@Override
	public void startProcess() {}

	@Override
	public void endProcess() {
		getBaseMetaTileEntity().setErrorDisplayID(0);
	}

	@Override
	public void stutterProcess() {
		if (GregTech_API.sConstantEnergy) {
			int val = (int) (recipeLogic.getMaxProgressTime() * 0.1D);
			getBaseMetaTileEntity().setErrorDisplayID(1);
			if (recipeLogic.getProgressTime() > val) 
				recipeLogic.increaseProgressTime(-val);
		}
	}
	
	@Override
	public void onMachineBlockUpdate() {
		needCheckStruct = true;
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
					amount -= newSize;
				}
			}
			
			if (amount > 0) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public List<ItemStack> getInputItems() {
		return new ListAdapter<>(mInventory, 0, 1);
	}

	@Override
	public List<ItemStack> getOutputItems() {
		return new ListAdapter<>(mInventory, 2, 3);
	}

	@Override
	public List<FluidStack> getFluidInputs() {
		return Collections.emptyList();
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		return Collections.emptyList();
	}

	@Override
	public int getInvSize() {
		return 4;
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex == 2 || aIndex == 3;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex == 0 || aIndex == 1;
	}
	
	@Override
	public Map<String, List<Object>> getInfoData() {
		return InfoBuilder.create()
				.newKey("sensor.progress.percentage", recipeLogic.getDisplayProgress() * 100.0D / recipeLogic.getDisplayMaxProgress())
				.newKey("sensor.progress.secs", recipeLogic.getDisplayProgress() / 20)
				.newKey("sensor.progress.secs.1", recipeLogic.getDisplayMaxProgress() / 20)
				.build();
	}
}
