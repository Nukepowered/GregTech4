package gregtechmod.common.tileentities.energy.production;

import java.util.Collections;
import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicGenerator;
import gregtechmod.api.recipe.RecipeMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_SemifluidGenerator extends GT_MetaTileEntity_BasicGenerator {

	public GT_MetaTileEntity_SemifluidGenerator(int aID, String aName, RecipeMap<?> recipeMap, int efficiency) {
		super(aID, aName, recipeMap, efficiency);
	}

	public GT_MetaTileEntity_SemifluidGenerator(RecipeMap<?> recipeMap, int efficiency) {
		super(recipeMap, efficiency);
	}

	@Override public boolean isFacingValid(byte aFacing) {return false;}
	@Override public int maxEUOutput() {return this.getBaseMetaTileEntity().isAllowedToWork() ? 8 : 0;}
	@Override public void onRightclick(EntityPlayer aPlayer) {this.getBaseMetaTileEntity().openGUI(aPlayer, 120);}
	@Override public List<ItemStack> getInputItems() { return Collections.emptyList(); }
	@Override public List<ItemStack> getOutputItems() { return Collections.emptyList(); }
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_SemifluidGenerator(recipeLogic.recipeMap, efficiency);
	}

	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide == 0 ? 38 : (aSide == 1 ? 29 : 85);
	}

	@Override
	public String getDescription() {
		return "metatileentity.GT_SemifluidGenerator.tooltip";
	}
}
