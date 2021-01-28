package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.recipe.GT_Recipe;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_GasTurbine extends GT_MetaTileEntity_BasicGenerator {

	public GT_MetaTileEntity_GasTurbine(int aID, String aName) {
		super(aID, aName);
	}

	public GT_MetaTileEntity_GasTurbine() {}

	@Override public boolean isFacingValid(byte aFacing) {return true;}
	@Override public int maxEUOutput() {return this.getBaseMetaTileEntity().isAllowedToWork() ? 16 : 0;}
	@Override public void onRightclick(EntityPlayer aPlayer) {this.getBaseMetaTileEntity().openGUI(aPlayer, 118);}
	@Override public List<GT_Recipe> getRecipes() {return GT_Recipe.sTurbineFuels;}
	@Override public int getEfficiency() {return 75;}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_GasTurbine();
	}

	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide == 0 ? 82 : (aSide == 1 ? 81 : 40);
	}

	@Override
	public String getDescription() {
		return "metatileentity.GT_GasTurbine.tooltip";
	}
}
