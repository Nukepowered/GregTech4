package gregtechmod.api.metatileentity.implementations;

import gregtechmod.api.recipe.RecipeMap;

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