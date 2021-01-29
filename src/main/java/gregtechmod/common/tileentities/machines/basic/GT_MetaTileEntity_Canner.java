package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
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
    		Recipe tRecipe = Recipe.findEqualRecipe(false, false, Recipe.sCannerRecipes, mInventory[1], mInventory[2]);
    		if (tRecipe != null && spaceForOutput(tRecipe.getOutput(0), tRecipe.getOutput(1)) && tRecipe.isRecipeInputEqual(true, true, mInventory[1], mInventory[2])) {
    			mEUt = tRecipe.mEUt;
    			mMaxProgresstime = tRecipe.mDuration;
    			mOutputItem1 = ItemStack.copyItemStack(tRecipe.getOutput(0));
    			mOutputItem2 = ItemStack.copyItemStack(tRecipe.getOutput(1));
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
		return "metatileentity.GT_Canner.tooltip";
	}
}
