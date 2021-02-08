package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.RecipeMap;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Wiremill extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Wiremill(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Wiremill(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer) {getBaseMetaTileEntity().openGUI(aPlayer, 136);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Wiremill(recipeLogic.recipeMap);
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
