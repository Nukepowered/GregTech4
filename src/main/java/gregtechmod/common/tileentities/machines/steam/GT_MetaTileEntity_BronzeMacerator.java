package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.RecipeMap;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeMacerator extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeMacerator(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_BronzeMacerator(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 164);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeMacerator(recipeLogic.recipeMap);
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 326;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 327;
	}
	
	@Override
	public int getTopFacingInactive() {
		return 328;
	}
	
	@Override
	public int getTopFacingActive() {
		return 329;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeMacerator.tooltip";
	}
}
