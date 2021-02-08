package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public abstract class GT_MetaTileEntity_BasicGenerator extends GT_MetaTileEntity_BasicTank {
	protected RecipeMap<?> recipeMap;
	protected int efficiency;
	// FIXME change basic generator logic
	public GT_MetaTileEntity_BasicGenerator(int aID, String aName, RecipeMap<?> recipeMap, int efficiency) {
		super(aID, aName);
		this.recipeMap = recipeMap;
		this.efficiency = efficiency;
	}
	
	public GT_MetaTileEntity_BasicGenerator(RecipeMap<?> recipeMap, int efficiency) {
		this.recipeMap = recipeMap;
		this.efficiency = efficiency;
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return true;}
	@Override public int maxEUOutput()								{return getBaseMetaTileEntity().isAllowedToWork()?12:0;}
	@Override public int maxEUStore()								{return 1000000;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override public boolean doesFillContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean doesEmptyContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeFilled()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeEmptied()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return false;}
	@Override public boolean isFluidInputAllowed(FluidStack aFluid) {return getFuelValue(aFluid) > 0;}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getTimer()%10==0) {
    		if (mFluid == null) {
    			if (getBaseMetaTileEntity().getUniversalEnergyStored() < getBaseMetaTileEntity().getOutputVoltage() + getMinimumStoredEU()) {
    				mInventory[getStackDisplaySlot()] = null;
    			} else {
    				if (mInventory[getStackDisplaySlot()] == null) mInventory[getStackDisplaySlot()] = new ItemStack(Blocks.fire, 1);
    				mInventory[getStackDisplaySlot()].setStackDisplayName(I18n.format("metatileentity.GT_MetaTileEntity_BasicGenerator.tooltip", getBaseMetaTileEntity().getUniversalEnergyStored() - getMinimumStoredEU()));
    			}
    		} else {
    			int tFuelValue = getFuelValue(mFluid);
    			if (tFuelValue > 0) while (getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU()) && mFluid.amount > 0) {
    				if (getBaseMetaTileEntity().increaseStoredEnergyUnits(tFuelValue, true)) mFluid.amount--;
    			}
    		}
    		if (mInventory[getInputSlot()] != null && getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU()) && GT_Utility.getFluidForFilledItem(mInventory[getInputSlot()]) == null) {
    			int tFuelValue = getFuelValue(mInventory[getInputSlot()]);
    			if (tFuelValue > 0) {
        			ItemStack tEmptyContainer = getEmptyContainer(mInventory[getInputSlot()]);
					if (getBaseMetaTileEntity().addStackToSlot(getOutputSlot(), tEmptyContainer)) {
						getBaseMetaTileEntity().increaseStoredEnergyUnits(tFuelValue, true);
						getBaseMetaTileEntity().decrStackSize(getInputSlot(), 1);
					}
    			}
    		}
		}
    	
    	if (getBaseMetaTileEntity().isServerSide()) getBaseMetaTileEntity().setActive(getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getUniversalEnergyStored() >= getBaseMetaTileEntity().getOutputVoltage() + getMinimumStoredEU());
    }
    
    public int getFuelValue(FluidStack aLiquid) {
    	if (aLiquid == null) return 0;
    	FluidStack tLiquid;
    	for (Recipe tFuel : recipeMap.getRecipes()) if ((tLiquid = GT_Utility.getFluidForFilledItem(tFuel.getInputs().get(0).getVariants().get(0))) != null) if (aLiquid.isFluidEqual(tLiquid)) return (int)(((long)tFuel.getEUtoStart() * efficiency) / 100);
    	return 0;
    }
    
    public int getFuelValue(ItemStack aStack) {
    	if (GT_Utility.isStackInvalid(aStack)) return 0;
    	Recipe tFuel = recipeMap.findRecipe(new Arr);
    	if (tFuel != null) return (int)((tFuel.getEUtoStart() * 1000L * efficiency) / 100);
    	return 0;
    }
    
    public ItemStack getEmptyContainer(ItemStack aStack) {
    	if (GT_Utility.isStackInvalid(aStack)) return null;
    	Recipe tFuel = recipeMap.findRecipe(true, recipeMap, aStack);
    	if (tFuel != null) return GT_Utility.copy(tFuel.getOutputs()[0]);
    	return GT_Utility.getContainerItem(aStack);
    }
    
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return super.allowPutStack(aIndex, aSide, aStack) && (getFuelValue(aStack) > 0 || getFuelValue(GT_Utility.getFluidForFilledItem(aStack)) > 0);
	}
	
	@Override
	public int getCapacity() {
		return 10000;
	}
	
	@Override
	public int getTankPressure() {
		return -100;
	}
}
