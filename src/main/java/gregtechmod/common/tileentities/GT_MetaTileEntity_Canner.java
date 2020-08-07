package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Canner extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Canner(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Canner() {
		
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 138);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Canner();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[1] != null || mInventory[2] != null) {
    		GT_Recipe tRecipe = GT_Recipe.findEqualCannerRecipe(mInventory[1], mInventory[2]);
    		if (tRecipe != null && spaceForOutput(tRecipe.mOutput1, tRecipe.mOutput2) && tRecipe.isRecipeInputEqual(true, true, mInventory[1], mInventory[2])) {
    			mEUt = tRecipe.mEUt;
    			mMaxProgresstime = tRecipe.mDuration;
    			mOutputItem1 = ItemStack.copyItemStack(tRecipe.mOutput1);
    			mOutputItem2 = ItemStack.copyItemStack(tRecipe.mOutput2);
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
	public int getFrontFacingInactive() {
		return 254;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 255;
	}
	@Override
	public String getDescription() {
		return "Unmobile Food Canning Machine GTA4";
	}
}
