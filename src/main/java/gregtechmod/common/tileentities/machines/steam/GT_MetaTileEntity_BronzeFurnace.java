package gregtechmod.common.tileentities.machines.steam;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeFurnace extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeFurnace(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_BronzeFurnace(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 165);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeFurnace(recipeLogic.recipeMap);
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null && null != (mOutputItem1 = GT_ModHandler.getSmeltingOutput(mInventory[2], true, mInventory[3]))) {
    		mEUt = 3;
    		mMaxProgresstime = 400;
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 330;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 331;
	}
	
	@Override
	public int getSideFacingInactive() {
		return 338;
	}
	
	@Override
	public int getBottomFacingInactive() {
		return 339;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeFurnace.tooltip";
	}
}
