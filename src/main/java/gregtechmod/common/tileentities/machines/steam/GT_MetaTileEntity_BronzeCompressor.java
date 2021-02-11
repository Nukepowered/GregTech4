package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.RecipeMap;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeCompressor extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeCompressor(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_BronzeCompressor(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 168);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeCompressor(recipeLogic.recipeMap);
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 340;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 341;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeCompressor.name";
	}
}