package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Wiremill extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Wiremill(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Wiremill() {}
	
	@Override public void onRightclick(EntityPlayer aPlayer) {getBaseMetaTileEntity().openGUI(aPlayer, 136);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Wiremill();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 1, 2, (byte) 64, (byte) 1, (byte) 64, (byte) 1);
		GT_Utility.moveStackFromSlotAToSlotB(this.getBaseMetaTileEntity(), this.getBaseMetaTileEntity(), 3, 4, (byte) 64, (byte) 1, (byte) 64, (byte) 1);
		if (super.mInventory[2] != null && super.mInventory[2].stackSize > 0) {
			GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(true, false, GT_Recipe.sWiremillRecipes, mInventory[2]);
			if (tRecipe != null && this.spaceForOutput(tRecipe.getOutput(0), null) && tRecipe.isRecipeInputEqual(true, true, mInventory[2])) {
				super.mEUt = tRecipe.mEUt;
				super.mMaxProgresstime = tRecipe.mDuration;
				super.mOutputItem1 = tRecipe.getOutput(0);
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
		return "metatileentity.GT_Wiremill.tooltip";
	}
}
