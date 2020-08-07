package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_PlateCutter extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_PlateCutter(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_PlateCutter() {
		
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 162);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_PlateCutter();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		if (mInventory[2] != null && mInventory[2].stackSize > 0) {
			GT_Recipe tRecipe = GT_Recipe.findEqualCutterRecipe(mInventory[2], null);
    		if (tRecipe != null && spaceForOutput(tRecipe.mOutput1, null) && tRecipe.isRecipeInputEqual(true, true, mInventory[2], null)) {
        		mEUt = tRecipe.mEUt;
    			mMaxProgresstime = tRecipe.mDuration;
    			if (tRecipe.mOutput1 != null) mOutputItem1 = GT_Utility.copy(tRecipe.mOutput1);
    			return;
    		}
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 306;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 307;
	}
	
	@Override
	public String getDescription() {
		return "Slices Blocks into Plates";
	}
}
