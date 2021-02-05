package gregtechmod.common.recipe;

import gregtechmod.api.recipe.RecipeMap;

/**
 * 
 * @author TheDarkDnKTv
 *
 */
public class RecipeMaps {
	public static final RecipeMap FUSION_REACTOR		= new RecipeMap(1, 2, 1, 1, new SimpleRecipeFactory());
	public static final RecipeMap CENTRIFUGE			= new RecipeMap(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap ELECTROLYZER			= new RecipeMap(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap GRINDER				= new RecipeMap(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap BLAST_FURNANCE		= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap IMPLOSION_COMPRESSOR	= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap SAWMILL				= new RecipeMap(1, 2, 1, 3, new SimpleRecipeFactory());
	public static final RecipeMap VACUUM_FREEZER		= new RecipeMap(1, 1, 1, 1, new SimpleRecipeFactory());
	public static final RecipeMap CHEMICAL				= new RecipeMap(1, 2, 1, 1, new SimpleRecipeFactory());
	public static final RecipeMap DISTILLATION			= new RecipeMap(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap WIREMILL				= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap BENDING				= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap ALLOY_SMELTING		= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap ASSEMBLING			= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap CANINNING				= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
//	public static final RecipeMap sCNCRecipes			= new ArrayRecipeMap();
	public static final RecipeMap LATHE					= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap CUTTING				= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap EXTRUDING				= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap HAMMER				= new RecipeMap(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap PRINTER				= new RecipeMap(1, 3, 1, 2, new SimpleRecipeFactory());
	
	public static final RecipeMap DIESEL_FUELS			= new RecipeMap(1, 1, 0, 0, new SimpleRecipeFactory()); // TODO generators recipemaps
	public static final RecipeMap TURBINE_FUELS			= new RecipeMap(1, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap HOT_FUELS				= new RecipeMap(1, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap DENSE_FUELS			= new RecipeMap(1, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap PLASMA_FUELS			= new RecipeMap(1, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap MAGIC_FUELS			= new RecipeMap(1, 1, 0, 0, new SimpleRecipeFactory());
}
