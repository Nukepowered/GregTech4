package gregtechmod.common.tileentities.energy.production;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.ListAdapter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_ThermalGenerator extends GT_MetaTileEntity_BasicGenerator {

	public GT_MetaTileEntity_ThermalGenerator(int aID, String aName, RecipeMap<?> recipeMap, int efficiency) {
		super(aID, aName, recipeMap, efficiency);
	}

	public GT_MetaTileEntity_ThermalGenerator(RecipeMap<?> recipeMap, int efficiency) {
		super(recipeMap, efficiency);
	}

	@Override public boolean isFacingValid(byte aFacing) { return false;}
	@Override public int maxEUOutput() {return this.getBaseMetaTileEntity().isAllowedToWork() ? 24 : 0;}
	@Override public void onRightclick(EntityPlayer aPlayer) {this.getBaseMetaTileEntity().openGUI(aPlayer, 119);}
	@Override public List<ItemStack> getInputItems() { return new ListAdapter<>(mInventory, 0, 1); }
	@Override public List<ItemStack> getOutputItems() { return new ListAdapter<>(mInventory, 1, 2); }
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ThermalGenerator(recipeLogic.recipeMap, efficiency);
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