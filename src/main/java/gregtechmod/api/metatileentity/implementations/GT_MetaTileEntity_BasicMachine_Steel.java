package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This is the main construct for my Basic Machines such as the Automatic Extractor
 * Extend this class to make a simple Machine
 */
public abstract class GT_MetaTileEntity_BasicMachine_Steel extends GT_MetaTileEntity_BasicMachine_Bronze {
	public GT_MetaTileEntity_BasicMachine_Steel(int aID, String aName, RecipeMap<?> recipeMap) {
		super(aID, aName, recipeMap);
	}
	
	public GT_MetaTileEntity_BasicMachine_Steel(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	protected void initRecipeLogic(RecipeMap<?> map) {
		this.recipeLogic = new RecipeLogic(map, this) {
			@Override
			protected void startRecipe(Recipe recipe) {
				if (spaceForOutput(recipe)) {
					previousRecipe = recipe;
					maxProgressTime = (int) (recipe.getDuration() * 1.175F); // Override max progress time, to increase it for bronze machines
					progressTime = 1;
					EUt = recipe.getEUt();
					if (consumeInputs(recipe)) {
						getBaseMetaTileEntity().setActive(true);
						startProcess();
					} else {
						GT_Log.log.catching(new IllegalStateException("Error state detected! RecipeMap passed recipe, but it's not matching! Report about this!!!"));
						EUt = 0;
						progressTime = 0;
						maxProgressTime = 0;
						previousRecipe = null;
					}
					
				} else {
					getBaseMetaTileEntity().setActive(false);
				}
			}
		};
	}
	
	@Override public int getTopFacingInactive()						{return 354;}
	@Override public int getTopFacingPipe()							{return 357;}
	@Override public int getBottomFacingInactive()					{return 355;}
	@Override public int getBottomFacingPipe()						{return 358;}
	@Override public int getSideFacingInactive()					{return 356;}
	@Override public int getSideFacingPipe()						{return 359;}
	
	@Override
	public float getSteamDamage() {
		return 12.0F;
	}
}