package gregtechmod.common.recipe;

import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.common.recipe.factory.BlastFurnaceRecipeFactory;
import gregtechmod.common.recipe.factory.GeneratorRecipeFactory;
import gregtechmod.common.recipe.factory.SimpleRecipeFactory;
import gregtechmod.common.recipe.factory.ThermalBoilerRecipeMap;
import gregtechmod.common.recipe.maps.FurnanceRecipeMap;
import gregtechmod.common.recipe.maps.IC2RecipeMap;
import gregtechmod.common.recipe.maps.PulverizerRecipeMap;
import gregtechmod.common.recipe.maps.RecipeMapPrinter;
import gregtechmod.common.recipe.maps.RecyclerRecipeMap;
import gregtechmod.common.recipe.maps.ScannerRecipeMap;

/**
 * 
 * @author TheDarkDnKTv
 *
 */
public class RecipeMaps {
	public static final RecipeMap<SimpleRecipeFactory> 			FUSION_REACTOR			= new RecipeMap<>(0, 2, 0, 1, 0, 2, 0, 1, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			CENTRIFUGE				= new RecipeMap<>(0, 2, 1, 4, 0, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			ELECTROLYZER			= new RecipeMap<>(0, 2, 1, 4, 0, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			GRINDER					= new RecipeMap<>(1, 2, 1, 4, 0, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap<BlastFurnaceRecipeFactory> 	BLAST_FURNACE 			= new RecipeMap<>(1, 2, 1, 2, new BlastFurnaceRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			BRONZE_BLAST_FURNACE 	= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			IMPLOSION_COMPRESSOR	= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			SAWMILL					= new RecipeMap<>(1, 2, 1, 3, 0, 1, 0, 0, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			VACUUM_FREEZER			= new RecipeMap<>(1, 1, 1, 1, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			CHEMICAL				= new RecipeMap<>(1, 2, 1, 1, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			DISTILLATION			= new RecipeMap<>(1, 2, 1, 4, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			WIREMILL				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			BENDING					= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			ALLOY_SMELTING			= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			ASSEMBLING				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			CANNING 				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			LATHE					= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory>			CUTTING					= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory> 			EXTRUDING				= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMap<SimpleRecipeFactory>			HAMMER					= new RecipeMap<>(1, 2, 1, 2, new SimpleRecipeFactory());
	public static final RecipeMapPrinter			   			PRINTER					= new RecipeMapPrinter(1, 3, 1, 2);
	public static final PulverizerRecipeMap			   			PULVERIZING 			= new PulverizerRecipeMap(1, 2, 1, 4);
	
	public static final RecipeMap<GeneratorRecipeFactory> DIESEL_FUELS		= new RecipeMap<>(0, 1, 0, 1, 0, 1, 0, 0, new GeneratorRecipeFactory());
	public static final RecipeMap<GeneratorRecipeFactory> TURBINE_FUELS		= new RecipeMap<>(0, 1, 0, 1, 0, 1, 0, 0, new GeneratorRecipeFactory());
	public static final RecipeMap<GeneratorRecipeFactory> HOT_FUELS			= new RecipeMap<>(0, 1, 0, 1, 0, 1, 0, 0, new GeneratorRecipeFactory());
	public static final RecipeMap<GeneratorRecipeFactory> DENSE_FUELS		= new RecipeMap<>(0, 1, 0, 1, 0, 1, 0, 0, new GeneratorRecipeFactory());
	public static final RecipeMap<GeneratorRecipeFactory> PLASMA_FUELS		= new RecipeMap<>(0, 1, 0, 1, 0, 1, 0, 0, new GeneratorRecipeFactory());
	public static final RecipeMap<GeneratorRecipeFactory> MAGIC_FUELS		= new RecipeMap<>(0, 1, 0, 1, 0, 1, 0, 0, new GeneratorRecipeFactory());
	
	public static final RecipeMap<GeneratorRecipeFactory> STEAM_FUELS		= new RecipeMap<>(0, 0, 0, 0, 0, 1, 0, 1, new GeneratorRecipeFactory());
	
	// Fake RecipeMaps
	public static final IC2RecipeMap 		MACERATION 		= new IC2RecipeMap(1, 2, 1, 2, GT_ModHandler::getMaceratorResult);
	public static final IC2RecipeMap 		EXTRACTION 		= new IC2RecipeMap(1, 2, 1, 2, GT_ModHandler::getExtractorResult);
	public static final IC2RecipeMap 		COMPRESSION 	= new IC2RecipeMap(1, 2, 1, 2, GT_ModHandler::getCompressorResult);
	public static final RecyclerRecipeMap 	RECYCLING 		= new RecyclerRecipeMap(1, 2, 1, 2);
	public static final ScannerRecipeMap 	SCANNING 		= new ScannerRecipeMap(1, 2, 1, 2);
	public static final FurnanceRecipeMap 	MELTING			= new FurnanceRecipeMap(1, 2, 1, 2);
	public static final ThermalBoilerRecipeMap THERMAL_BOILER = new ThermalBoilerRecipeMap();
}
