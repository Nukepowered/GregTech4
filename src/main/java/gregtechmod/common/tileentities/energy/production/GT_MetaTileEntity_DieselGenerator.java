package gregtechmod.common.tileentities.energy.production;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_DieselGenerator extends GT_MetaTileEntity_BasicGenerator {

	public GT_MetaTileEntity_DieselGenerator(int aID, String aName) {
		super(aID, aName);
	}

	public GT_MetaTileEntity_DieselGenerator() {}

	@Override public boolean isFacingValid(byte aFacing) {return aFacing > 1;}
	@Override public int maxEUOutput() {return this.getBaseMetaTileEntity().isAllowedToWork() ? 12 : 0;}
	@Override public void onRightclick(EntityPlayer aPlayer) {this.getBaseMetaTileEntity().openGUI(aPlayer, 117);}
	@Override public List<GT_Recipe> getRecipes() {return GT_Recipe.sDieselFuels;}
	@Override public int getEfficiency() {return 100;}

	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_DieselGenerator();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide == 0 ? 38
				: (aSide != 1 ? 36 : (aFacing != 2 && aFacing != 3 ? (aActive ? 303 : 302) : (aActive ? 281 : 80)));
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_DieselGenerator.tooltip";
	}
	
	@Override
	public int getFuelValue(ItemStack aStack) {
		int rValue = Math.max(GT_ModHandler.getFuelCanValue(aStack) * 6 / 5, super.getFuelValue(aStack));
		if (GT_Items.Fuel_Can_Plastic_Filled.isStackEqual(aStack, false, true)) {
			rValue = Math.max(rValue, GameRegistry.getFuelValue(aStack) * 3);
		}

		return rValue;
	}
}
