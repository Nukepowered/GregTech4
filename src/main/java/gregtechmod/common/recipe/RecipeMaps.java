package gregtechmod.common.recipe;

import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.common.recipe.factory.SimpleRecipeFactory;
import gregtechmod.common.recipe.maps.MaceratorRecipeMap;

/**
 * 
 * @author TheDarkDnKTv
 *
 */
public class RecipeMaps {
	public static final RecipeMap<SimpleRecipeFactory> FUSION_REACTOR		= new RecipeMap<>(1, 2, 1, 1, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> CENTRIFUGE			= new RecipeMap<>(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> ELECTROLYZER			= new RecipeMap<>(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> GRINDER				= new RecipeMap<>(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> BLAST_FURNANCE		= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> IMPLOSION_COMPRESSOR	= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> SAWMILL				= new RecipeMap<>(1, 2, 1, 3, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> VACUUM_FREEZER		= new RecipeMap<>(1, 1, 1, 1, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> CHEMICAL				= new RecipeMap<>(1, 2, 1, 1, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> DISTILLATION			= new RecipeMap<>(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> WIREMILL				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> BENDING				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> ALLOY_SMELTING		= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> ASSEMBLING			= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> CANINNING			= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
//	public static final RecipeMap<SimpleRecipeFactory> sCNCRecipes			= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> LATHE				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> CUTTING				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> EXTRUDING			= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> HAMMER				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> PRINTER				= new RecipeMap<>(1, 3, 1, 2, new SimpleRecipeFactory());
	
	public static final RecipeMap<SimpleRecipeFactory> DIESEL_FUELS			= new RecipeMap<>(1, 1, 0, 0, new SimpleRecipeFactory()); // TODO generators recipemaps
	public static final RecipeMap<SimpleRecipeFactory> TURBINE_FUELS		= new RecipeMap<>(1, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> HOT_FUELS			= new RecipeMap<>(1, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> DENSE_FUELS			= new RecipeMap<>(1, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> PLASMA_FUELS			= new RecipeMap<>(1, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> MAGIC_FUELS			= new RecipeMap<>(1, 1, 0, 1, new SimpleRecipeFactory());
	
	// Fake RecipeMaps
	public static final MaceratorRecipeMap MACERATION 						= new MaceratorRecipeMap(1, 2, 1, 2);
}
