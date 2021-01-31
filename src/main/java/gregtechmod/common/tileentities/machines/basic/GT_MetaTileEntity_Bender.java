package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Bender extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Bender(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Bender(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 140);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Bender(recipeLogic.recipeMap);
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte) 64, (byte) 1, (byte) 64, (byte) 1);
		if (GT_Utility.isStackValid(mInventory[1])) {
			Recipe tRecipe = Recipe.findEqualRecipe(false, false, Recipe.sBenderRecipes, mInventory[1], mInventory[2]);
			if (tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), null) && tRecipe.isRecipeInputEqual(true, true, mInventory[1], mInventory[2])) {
				mEUt = tRecipe.mEUt;
				mMaxProgresstime = tRecipe.mDuration;
				mOutputItem1 = tRecipe.getOutput(0);
				return;
			}

			if (GT_Utility.isStackInvalid(mInventory[2])) {
				for (int i = 64; i > 0; --i) {
					tRecipe = Recipe.findEqualRecipe(false, false, Recipe.sBenderRecipes, mInventory[1], GT_Items.Circuit_Integrated.getWithDamage(0, (long) i));
					if (tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), null) && tRecipe.isRecipeInputEqual(true, true, super.mInventory[1], GT_Items.Circuit_Integrated.getWithDamage(0L, (long) i))) {
						mEUt = tRecipe.mEUt;
						mMaxProgresstime = tRecipe.mDuration;
						mOutputItem1 = tRecipe.getOutput(0);
						return;
					}

					tRecipe = Recipe.findEqualRecipe(false, false, Recipe.sBenderRecipes, GT_Items.Circuit_Integrated.getWithDamage(0L, (long) i), mInventory[1]);
					if (tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), null) && tRecipe.isRecipeInputEqual(true, true, GT_Items.Circuit_Integrated.getWithDamage(0L, (long) i), mInventory[1])) {
						mEUt = tRecipe.mEUt;
						mMaxProgresstime = tRecipe.mDuration;
						mOutputItem1 = tRecipe.getOutput(0);
						return;
					}
				}
			}
		}
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
		return "metatileentity.GT_Bender.tooltip";
	}
}
