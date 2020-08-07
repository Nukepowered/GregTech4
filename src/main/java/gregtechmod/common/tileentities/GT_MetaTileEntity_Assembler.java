package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Assembler extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Assembler(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Assembler() {
		
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 141);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Assembler();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[1] != null || mInventory[2] != null) {
    		GT_Recipe tRecipe = GT_Recipe.findEqualAssemblerRecipe(mInventory[1], mInventory[2]);
    		if (tRecipe != null && spaceForOutput(tRecipe.mOutput1, null) && tRecipe.isRecipeInputEqual(true, true, mInventory[1], mInventory[2])) {
        		mEUt = tRecipe.mEUt;
    			mMaxProgresstime = tRecipe.mDuration;
    			mOutputItem1 = GT_Utility.copy(tRecipe.mOutput1);
    			return;
    		}
    	}
		mOutputItem1 = null;
    }
	
	@Override
    public boolean hasTwoSeperateInputs() {
    	return true;
    }
	
	@Override
	public int getTopFacingInactive() {
		return 237;
	}
	
	@Override
	public int getTopFacingActive() {
		return 237;
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
		return "Avengers, Assemble!";
	}
}
