package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Steel;
import gregtechmod.api.recipe.RecipeMap;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_SteelFurnace extends GT_MetaTileEntity_BasicMachine_Steel {
	
	public GT_MetaTileEntity_SteelFurnace(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_SteelFurnace(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 172);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_SteelFurnace(recipeLogic.recipeMap);
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 360;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 361;
	}
	
	@Override
	public int getSideFacingInactive() {
		return 362;
	}
	
	@Override
	public int getBottomFacingInactive() {
		return 363;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_SteelFurnace.tooltip";
	}
}
