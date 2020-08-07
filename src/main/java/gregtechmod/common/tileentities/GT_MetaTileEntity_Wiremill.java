package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Wiremill extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Wiremill(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Wiremill() {
		
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 136);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Wiremill();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null && mInventory[2].stackSize > 0) {
    		if (mInventory[2].stackSize > 2 && GT_Utility.areStacksEqual(mInventory[2], GT_ModHandler.getIC2Item("copperCableItem", 1)) && GT_ModHandler.mFineCopper != null && spaceForOutput(mOutputItem1 = GT_Utility.copy(1, GT_ModHandler.mFineCopper), null)) {
    			mEUt = 1;
    			mMaxProgresstime = 400;
        		mInventory[2].stackSize -= 3;
        		return;
    		}
    		if (mInventory[2].stackSize > 5 && GT_Utility.areStacksEqual(mInventory[2], GT_ModHandler.getIC2Item("ironCableItem", 1)) && GT_ModHandler.mFineIron != null && spaceForOutput(mOutputItem1 = GT_Utility.copy(1, GT_ModHandler.mFineIron), null)) {
    			mEUt = 2;
    			mMaxProgresstime = 400;
        		mInventory[2].stackSize -= 6;
        		return;
    		}
    		GT_Recipe tRecipe = GT_Recipe.findEqualWiremillRecipe(mInventory[2], null);
    		if (tRecipe != null && spaceForOutput(tRecipe.mOutput1, null) && tRecipe.isRecipeInputEqual(true, true, mInventory[2], null)) {
        		mEUt = tRecipe.mEUt;
    			mMaxProgresstime = tRecipe.mDuration;
    			mOutputItem1 = GT_Utility.copy(tRecipe.mOutput1);
    			return;
    		}
    	}
		mOutputItem1 = null;
    }
	
	@Override
	public int getTopFacingInactive() {
		return 235;
	}
	
	@Override
	public int getTopFacingActive() {
		return 236;
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 224;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 225;
	}
	
	@Override
	public String getDescription() {
		return "Produces Wires more efficiently";
	}
}
