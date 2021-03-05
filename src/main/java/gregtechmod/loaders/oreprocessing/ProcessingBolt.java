package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

public class ProcessingBolt implements IOreRecipeRegistrator {

	public ProcessingBolt() {
		OrePrefixes.bolt.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial) && (aMaterial.mTypes & 2) != 0) {
				RecipeMaps.LATHE.factory().EUt(4).duration(Math.max(aMaterial.getMass() / 8, 1))
					.input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE))
					.output(GT_OreDictUnificator.get(OrePrefixes.screw, aMaterial, 1))
					.buildAndRegister();
			}
		}
	}
}
