package gregtechmod.common.recipe.logic;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;

public class GeneratorRecipeLogic extends RecipeLogic {
	
	public final int efficiency; // FIXME change generator logic to TOTAL EU
	
	public GeneratorRecipeLogic(int efficiency, RecipeMap<?> recipeMap, IRecipeWorkable machine) {
		super(recipeMap, machine);
		this.efficiency = efficiency;
	}
	
	/** For internal use only */
	protected GeneratorRecipeLogic(int efficiency, RecipeMap<?> recipeMap) {
		super(recipeMap);
		this.efficiency = efficiency;
	}
	
	@Override 
	public boolean update() {
		boolean success = false;
		IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
		
		if (base.isAllowedToWork()) {
			if (progressTime > 0) {
				int tmp = progressTime;
				success = updateRecipeProgress();
				if (tmp == 0 && !success) {
					throw new IllegalStateException();
				}
			}
			
			if (progressTime == 0) {
				if (base.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0) {// || !getMachine().getFluidInputs().isEmpty()) { // TODO may be generators will not work
					int a = base.getUniversalEnergyStored();
					int b = base.getOutputVoltage() * 10;
					int c = getMachine().getMinimumStoredEU();
					if (a < (b + c)) {
						trySerachRecipe();
					} else {
						previousRecipe = null;
						base.setActive(false);
					} 
				}
			}
		} 
		
		return success;
	}
	
	@Override
	protected boolean updateRecipeProgress() {
			if (getMachine().getBaseMetaTileEntity().increaseStoredEnergyUnits(EUt * efficiency / 100, false)) {
				if ((progressTime += progressTimeManipulator.applyAsInt(1)) >= maxProgressTime) {
					progressTime = 0;
					maxProgressTime = 0;
					EUt = 0;
					
					endRecipe(previousRecipe);
					getMachine().endProcess();
					return true;
				}
			}
			
		return false;
	}
}