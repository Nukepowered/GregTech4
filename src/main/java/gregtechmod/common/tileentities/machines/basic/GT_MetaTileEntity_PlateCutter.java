package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.RecipeMap;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_PlateCutter extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_PlateCutter(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_PlateCutter(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 162);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_PlateCutter(recipeLogic.recipeMap);
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 306;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 307;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Cutter.tooltip";
	}
}
