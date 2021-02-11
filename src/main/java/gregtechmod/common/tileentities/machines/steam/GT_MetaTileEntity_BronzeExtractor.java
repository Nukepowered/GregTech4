package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.RecipeMap;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeExtractor extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeExtractor(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_BronzeExtractor(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 169);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeExtractor(recipeLogic.recipeMap);
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 342;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 343;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeExtractor.tooltip";
	}
}