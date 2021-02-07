package gregtechmod.api.interfaces;

import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.recipe.RecipeMap;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * A interface to get GT's RecipeMaps
 */
public interface IGT_RecipeAdder {

	public RecipeMap<? extends RecipeFactory<?>> fusionRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> centrifugeRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> electrolyzerRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> chemicalRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> blastRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> canningRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> alloySmelterRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> circuitAssemblerRecipes();

	public RecipeMap<? extends RecipeFactory<?>> hammerRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> wiremillRecipes();

	public RecipeMap<? extends RecipeFactory<?>> bendingRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> extruderRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> implosionCompressorRecipes();
	
	/**
	 * Use the Grinder Recipe adder below.
	 * Just use GT_ModHandler.getWaterCell(1) as second Parameter.
	 */
	public RecipeMap<? extends RecipeFactory<?>> grinderRecipes();
	
	/**
	 * Distillation Tower Recipe Map
	 */
	public RecipeMap<? extends RecipeFactory<?>> distillationrRecipes();

	public RecipeMap<? extends RecipeFactory<?>> latheRecipes();
	
	/**
	 * Plate Cutter Recipe Map
	 */
	public RecipeMap<? extends RecipeFactory<?>> cutterRecipes();
	
	public RecipeMap<? extends RecipeFactory<?>> freezerRecipes();
	
	/**
	 * Use the Sawmill Recipe adder below.
	 * Just use GT_ModHandler.getWaterCell(1) as second Parameter.
	 */
	public RecipeMap<? extends RecipeFactory<?>> sawmillRecipes();
	
	/**
	 * Adds a Fuel for My Generators
	 * @param aInput1 must be != null
	 * @param aOutput1 can be null
	 * @param aEU EU per MilliBucket. If no Liquid Form of this Container is available, then it will give you EU*1000 per Item.
	 * @param aType 0 = Diesel; 1 = Gas Turbine; 2 = Thermal; 3 = Dense Fluid; 4 = Plasma; 5 = Magic; And if something is unclear or missing, then look at the GT_Recipe-Class
	 */
	public RecipeMap<? extends RecipeFactory<?>> turbineFuels();
	
	public RecipeMap<? extends RecipeFactory<?>> dieselFuels();
	
	public RecipeMap<? extends RecipeFactory<?>> semifluidFuels();
	
	public RecipeMap<? extends RecipeFactory<?>> thermalFuels();
	
	public RecipeMap<? extends RecipeFactory<?>> plasmaFuels();
	
	public RecipeMap<? extends RecipeFactory<?>> magicFuels();
	
	/**
	 * Adds a BlockID to the List of the Minable Blocks by the Jackhammer. Call this in postload!
	 */
	public boolean addJackHammerMinableBlock(Block aBlock, boolean aDiamondOnly);
	
	/**
	 * Adds a Sound to the Sonictron9001
	 * you should NOT call this in the preInit-Phase!
	 * @param aItemStack = The Item you want to display for this Sound
	 * @param aSoundName = The Name of the Sound in the resources/newsound-folder like Vanillasounds
	 * @return true if the Sound got added, otherwise false.
	 */
	public boolean addSonictronSound(ItemStack aItemStack, String aSoundName);
}
