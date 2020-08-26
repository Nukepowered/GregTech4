package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_FusionInjector extends GT_MetaTileEntity_BasicTank {
	
	public IGregTechTileEntity mFusionComputer;
	
	public GT_MetaTileEntity_FusionInjector(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_FusionInjector() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 144);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_FusionInjector();
	}
	
	@Override public boolean doesFillContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean doesEmptyContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeFilled()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeEmptied()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return true;}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getTimer()%20==0) {
    		getBaseMetaTileEntity().setActive(mFusionComputer!=null&&mFusionComputer.isActive());
    	}
    }
    
    public ItemStack getMaterial() {
    	if (mInventory[getInputSlot()] == null) {
    		ItemStack tStack = GT_Utility.fillFluidContainer(mFluid, GT_Items.Cell_Empty.get(1));
    		if (tStack == null) return null;
    		tStack.stackSize = mFluid.amount / GT_Utility.getFluidForFilledItem(tStack).amount;
    		return tStack;
    	}
    	return mInventory[getInputSlot()];
    }
    
    public boolean consumeMaterial(ItemStack aStack) {
    	if (aStack == null) return false;
    	if (mFluid == null || !GT_Utility.containsFluid(aStack, mFluid) || GT_Utility.getFluidForFilledItem(aStack).amount * aStack.stackSize > mFluid.amount) {
    		if (mInventory[0] != null && GT_Utility.areStacksEqual(mInventory[0], aStack) && mInventory[0].stackSize >= aStack.stackSize) {
    			ItemStack tOutputCells = GT_ModHandler.getEmptyCell(GT_ModHandler.getCapsuleCellContainerCount(aStack));
    			if (tOutputCells != null && tOutputCells.stackSize > 0) {
    				if (mInventory[1] == null) {
    					mInventory[1] = tOutputCells;
    				} else if (GT_Utility.areStacksEqual(mInventory[1], tOutputCells)) {
    					mInventory[1].stackSize = Math.min(mInventory[1].getMaxStackSize(), mInventory[1].stackSize + tOutputCells.stackSize);
    				}
    			}
    			getBaseMetaTileEntity().decrStackSize(0, aStack.stackSize);
    			return true;
    		}
    	} else {
    		mFluid.amount -= (GT_Utility.getFluidForFilledItem(aStack).amount * aStack.stackSize);
    		return true;
    	}
    	return false;
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 0 || aSide == 1) return 16;
    	return aActive?20:19;
	}
	
	@Override
	public int getCapacity() {
		return 10000;
	}
	
	@Override
	public String getDescription() {
		return "metatilentity.GT_Fusion_Injector.tooltip";
	}
	
	@Override
	public int getTankPressure() {
		return -100;
	}
}
