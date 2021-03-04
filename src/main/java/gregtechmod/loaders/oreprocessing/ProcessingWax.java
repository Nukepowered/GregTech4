package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.recipe.Ingredient;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;

public class ProcessingWax implements IOreRecipeRegistrator {

	public ProcessingWax() {
		OrePrefixes.wax.add(this);
	}

	@Override
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			if (entry.oreDictName.equals("waxMagical")) {
				Ingredient recipeEntry = RecipeEntry.fromStacks(1, entry.ores);
				RecipeMaps.MAGIC_FUELS.factory().EUt(6).duration(1).input(recipeEntry).buildAndRegister();
			}
		}
	}
}
