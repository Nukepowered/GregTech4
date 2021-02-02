package gregtechmod.common.tileentities.machines;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.BasicFluidWorkable;
import gregtechmod.api.recipe.RecipeMaps;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Centrifuge extends BasicFluidWorkable {
	
	private int mSpeed = 0;
	
	public GT_MetaTileEntity_Centrifuge(int aID, String mName) {
		super(aID, mName, RecipeMaps.sCentrifugeRecipes);
	}
	
	public GT_MetaTileEntity_Centrifuge() {
		super(RecipeMaps.sCentrifugeRecipes);
	}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Centrifuge();
	}
	
	@Override
	public void onPostTick() {
		super.onPostTick();
		if (getBaseMetaTileEntity().isServerSide()) {
			mSpeed = getBaseMetaTileEntity().getOverclockerUpgradeCount();
		}
	}
	
	@Override public int getInputSlot() 						{return 1;}
	@Override public int getOutputSlot() 						{return 2;}
	@Override public int getStackDisplaySlot() 					{return 6;}
	@Override public int getInvSize()							{return 7;}
	@Override public void onRightclick(EntityPlayer aPlayer)	{getBaseMetaTileEntity().openGUI(aPlayer, 146);}
	@Override public int[] getInputSlots() 						{ return new int[] {0, 1}; }
	@Override public int[] getOutputSlots() 					{ return new int[] {2, 3, 4, 5}; }; 
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 0) return 32;
    	if (aSide == 1) return aActive ? mSpeed >= 2 ? 44 : mSpeed == 1 ? 43 : 42 : 41;
    	return aActive ? mSpeed >= 2 ? 28 : mSpeed == 1 ? 27 : 26 : 25;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Centrifuge.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_Utility.areStacksEqual(aStack, GT_ModHandler.getEmptyCell(1))||GT_Utility.areStacksEqual(aStack, GT_ModHandler.getEmptyFuelCan(1))?aIndex==1:aIndex==0;
	}
	
	@Override
	public void onValueUpdate(byte aValue) {
		mSpeed = aValue;
	}
	
	@Override
	public byte getUpdateData() {
		return (byte) mSpeed;
	}
	
	@Override public int getTankPressure() {return -100;}
	@Override public int getCapacity() {return 64000;}

	@Override
	public void stutterProcess() {
		super.stutterProcess();
		getBaseMetaTileEntity().setErrorDisplayID(1);
	}
	
	@Override
	public void endProcess() {
		getBaseMetaTileEntity().setErrorDisplayID(0);
	}
}
