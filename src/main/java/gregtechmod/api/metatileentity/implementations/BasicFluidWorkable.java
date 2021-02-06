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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
		recipeLogic = new RecipeLogic(recipeMap, this) {
			@Override
			protected void consumeInputs(Recipe recipe) {
				if (mFluid == null || !BasicFluidWorkable.this.consumeFluids(true, recipe)) {
					super.consumeInputs(recipe);
				}
			}
			
			@Override
			protected boolean isInputNonEmpty() {
				return mFluid != null || super.isInputNonEmpty();
			}
			
			@Override
			protected boolean match(Recipe recipe) {
				return consumeFluids(false, recipe) || super.match(recipe);
			}
		};
		recipeLogic.setRecipeProvider(this::searchForRecipe);
	}
	
    protected Recipe searchForRecipe() {
    	Recipe result = null;
    	
    	if (mFluid != null) {
    		ItemStack filledBucket = GT_Utility.fillFluidContainer(mFluid, new ItemStack(Items.bucket));
    		if (filledBucket != null) {
    			filledBucket.stackSize = mFluid.amount / 1000;
    			result = recipeLogic.recipeMap.findRecipe(Collections.singletonList(filledBucket));
    		}
    	}
    	
    	if (result == null) {
    		result = recipeLogic.recipeMap.findRecipe(getInputItems());
    	}
    	
    	return result;
    }
	
    @Override
    public void onPostTick() {
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	recipeLogic.update();
		}
    }
    
    protected boolean consumeFluids(boolean consume, Recipe recipe) {
    	ItemStack fluidItem = findFluidItem(recipe);
    	FluidStack fluid = null;
    	
    	if (mFluid != null && fluidItem != null && (fluid = GT_Utility.getFluidForFilledItem(fluidItem)) != null) {
    		fluid.amount = 1000 * fluidItem.stackSize;
    		if (fluid.getFluidID() == mFluid.getFluidID() && mFluid.amount >= fluid.amount) {
    			if (consume) mFluid.amount -= fluid.amount;
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    protected ItemStack findFluidItem(Recipe recipe) {
    	ItemStack fluidItem = null;
//    	for (ItemStack[] stacks : recipe.getRepresentativeInputs()) {
//    		for (ItemStack stack : stacks) {
//    			if (GT_Utility.getFluidForFilledItem(stack) != null && GT_Utility.getContainerForFilledItem(stack).getItem() == Items.bucket) {
//    				fluidItem = stack;
//    				break;
//    			}
//    		}
//    	} // FIXME add fluid to recipes!!!
    	
    	return fluidItem;
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
    
	@Override
    public boolean spaceForOutput(Recipe recipe) {
//		ItemStack[] outputs = recipe.getOutputs();
//		if (outputs.length <= getOutputItems().length) {
//			List<ItemStack> slots = new ArrayList<>();
//			for (int i : getOutputItems()) slots.add(mInventory[i]);
//			for (int i = 0; i < outputs.length && i < slots.size(); i++) {
//				if (slots.get(i) != null && outputs[i] != null) {
//					if (!GT_Utility.areStacksEqual(slots.get(i), outputs[i]) || slots.get(i).stackSize + outputs[i].stackSize > slots.get(i).getMaxStackSize()) {
//						return false;
//					}
//				}
//			}
//		} else return false;
		
		return true;
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
