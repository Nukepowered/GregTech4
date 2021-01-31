package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Compressor extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Compressor(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Compressor(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 133);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Compressor(recipeLogic.recipeMap);
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null && null != (mOutputItem1 = GT_ModHandler.getCompressorOutput(mInventory[2], true, mInventory[3]))) {
    		mEUt = 2;
    		mMaxProgresstime = 400;
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 248;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 249;
	}
	
	@Override
	public int getTopFacingInactive() {
		return 230;
	}
	
	@Override
	public int getTopFacingActive() {
		return 234;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Compressor.tooltip";
	}
}
