package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.common.recipe.logic.GeneratorRecipeLogic;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_PlasmaGenerator extends GT_MetaTileEntity_BasicGenerator {

	public GT_MetaTileEntity_PlasmaGenerator(int aID, String aName, RecipeMap<?> recipeMap, int efficiency) {
		super(aID, aName, recipeMap, efficiency);
	}

	public GT_MetaTileEntity_PlasmaGenerator(RecipeMap<?> recipeMap, int efficiency) {
		super(recipeMap, efficiency);
	}

	@Override public boolean isFacingValid(byte aFacing) {return true;}
	@Override public int maxEUOutput() {return this.getBaseMetaTileEntity().isAllowedToWork() ? 2048 : 0;}
	@Override public void onRightclick(EntityPlayer aPlayer) {this.getBaseMetaTileEntity().openGUI(aPlayer, 121);}
	@Override public boolean isOutputFacing(byte aSide) {return aSide == this.getBaseMetaTileEntity().getFrontFacing();}
	@Override public int maxEUStore() {return 1000000000;}

	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_PlasmaGenerator(recipeLogic.recipeMap, ((GeneratorRecipeLogic)recipeLogic).efficiency);
	}

	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide == aFacing ? 18 : (aActive ? 20 : 19);
	}

	@Override
	public String getDescription() {
		return "metatileentity.GT_PlasmaGenerator.tooltip";
	}
}
