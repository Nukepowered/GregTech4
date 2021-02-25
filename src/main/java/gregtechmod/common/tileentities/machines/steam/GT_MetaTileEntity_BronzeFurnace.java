package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeFurnace extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeFurnace(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_BronzeFurnace(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	public void initRecipeLogic(RecipeMap<?> map) {
		recipeLogic = new RecipeLogic(map, this) {
			@Override
			protected void startRecipe(Recipe recipe) {
				if (getMachine().spaceForOutput(recipe) && getMachine().getBaseMetaTileEntity().decreaseStoredEnergyUnits(recipe.getEUtoStart(), false)) {
					previousRecipe = recipe;
					maxProgressTime = GT_Utility.isDebugItem(getMachine().getStackInSlot(batterySlot)) ? 1 : 400;
					progressTime = 1;
					EUt = recipe.getEUt();
					if (consumeInputs(recipe)) {
						getMachine().getBaseMetaTileEntity().setActive(true);
						getMachine().startProcess();
					} else {
						GT_Log.log.catching(new IllegalStateException("Error state detected! RecipeMap passed recipe, but it's not matching! Report about this!!!"));
						EUt = 0;
						progressTime = 0;
						maxProgressTime = 0;
						previousRecipe = null;
					}
					
				} else {
					getMachine().getBaseMetaTileEntity().setActive(false);
				}
			}
		};
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 165);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeFurnace(recipeLogic.recipeMap);
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 330;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 331;
	}
	
	@Override
	public int getSideFacingInactive() {
		return 338;
	}
	
	@Override
	public int getBottomFacingInactive() {
		return 339;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeFurnace.tooltip";
	}
}
