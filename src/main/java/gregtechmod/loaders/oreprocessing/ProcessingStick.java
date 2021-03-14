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

public class ProcessingStick implements IOreRecipeRegistrator {

	public ProcessingStick() {
		OrePrefixes.stick.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial) && (aMaterial.mTypes & 2) != 0) {
				RecipeMaps.CUTTING.factory().EUt(4).duration(aMaterial.getMass() * 2).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).output(GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, 4L)).buildAndRegister();
			}
		}
	}
}
