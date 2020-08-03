package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Bender extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Bender(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Bender() {
		
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 140);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Bender();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null && mInventory[2].stackSize > 0) {
    		GT_Recipe tRecipe = GT_Recipe.findEqualBenderRecipe(mInventory[2], null);
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
	public int getFrontFacingInactive() {
		return 238;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 239;
	}
	@Override
	public String getDescription() {
		return "Boo, he's bad! We want BENDER!!!";
	}
}
