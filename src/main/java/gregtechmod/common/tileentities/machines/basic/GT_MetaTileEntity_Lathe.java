package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Lathe extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Lathe(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Lathe(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 159);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Lathe(recipeLogic.recipeMap);
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		if (mInventory[2] != null && mInventory[2].stackSize > 0) {
    		Recipe tRecipe = Recipe.findEqualRecipe(false, false, Recipe.sLatheRecipes, mInventory[2]);
    		if (tRecipe != null && spaceForOutput(tRecipe.getOutput(0), tRecipe.getOutput(1)) && tRecipe.isRecipeInputEqual(true, true, mInventory[2], null)) {
        		mEUt = tRecipe.mEUt;
    			mMaxProgresstime = tRecipe.mDuration;
    			if (tRecipe.getOutput(0) != null) mOutputItem1 = GT_Utility.copy(tRecipe.getOutput(0));
    			if (tRecipe.getOutput(1) != null) mOutputItem2 = GT_Utility.copy(tRecipe.getOutput(1));
    			return;
    		}
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 304;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 305;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Lathe.tooltip";
	}
}
