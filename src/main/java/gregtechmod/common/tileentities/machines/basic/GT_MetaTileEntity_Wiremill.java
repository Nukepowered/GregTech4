package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Wiremill extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Wiremill(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Wiremill(List<Recipe> recipeMap) {
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
