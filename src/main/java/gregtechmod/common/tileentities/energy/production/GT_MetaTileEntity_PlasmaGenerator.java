package gregtechmod.common.tileentities.energy.production;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.util.GT_Recipe;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_PlasmaGenerator extends GT_MetaTileEntity_BasicGenerator {

	public GT_MetaTileEntity_PlasmaGenerator(int aID, String aName) {
		super(aID, aName);
	}

	public GT_MetaTileEntity_PlasmaGenerator() {}

	@Override public boolean isFacingValid(byte aFacing) {return true;}
	@Override public int maxEUOutput() {return this.getBaseMetaTileEntity().isAllowedToWork() ? 2048 : 0;}
	@Override public void onRightclick(EntityPlayer aPlayer) {this.getBaseMetaTileEntity().openGUI(aPlayer, 121);}
	@Override public List<GT_Recipe> getRecipes() {return GT_Recipe.sPlasmaFuels;}
	@Override public int getEfficiency() {return 100;}
	@Override public boolean isOutputFacing(byte aSide) {return aSide == this.getBaseMetaTileEntity().getFrontFacing();}
	@Override public int maxEUStore() {return 1000000000;}

	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_PlasmaGenerator();
	}

	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide == aFacing ? 18 : (aActive ? 20 : 19);
	}

	@Override
	public String getDescription() {
		return "Harness " + this.getEfficiency() + "% of the immense Power of Plasma"; // TODO locale
	}
}
