package gregtechmod.common.tileentities.energy.production;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.util.GT_Recipe;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_ThermalGenerator extends GT_MetaTileEntity_BasicGenerator {

	public GT_MetaTileEntity_ThermalGenerator(int aID, String aName) {
		super(aID, aName);
	}

	public GT_MetaTileEntity_ThermalGenerator() {}

	@Override public boolean isFacingValid(byte aFacing) { return false;}
	@Override public int maxEUOutput() {return this.getBaseMetaTileEntity().isAllowedToWork() ? 24 : 0;}
	@Override public void onRightclick(EntityPlayer aPlayer) {this.getBaseMetaTileEntity().openGUI(aPlayer, 119);}
	@Override public List<GT_Recipe> getRecipes() {return GT_Recipe.sHotFuels;}
	@Override public int getEfficiency() {return 80;}

	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ThermalGenerator();
	}

	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide == 0 ? 38 : (aSide == 1 ? (aActive ? 84 : 83) : 36);
	}

	@Override
	public String getDescription() {
		return "metatileentity.GT_ThermalGenerator.tooltip";
	}
}