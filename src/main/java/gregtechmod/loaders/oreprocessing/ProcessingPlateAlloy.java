package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingPlateAlloy implements IOreRecipeRegistrator {

	public ProcessingPlateAlloy() {
		OrePrefixes.plateAlloy.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry))) {
				if (entry.oreDictName.equals("plateAlloyCarbon")) {
					RecipeMaps.ASSEMBLING.factory().EUt(8).duration(6400)
						.input(RecipeEntry.fromStacks(4, entry.ores, Match.DAMAGE))
						.input(GT_ModHandler.getIC2Item("generator", 1L))
						.output(GT_ModHandler.getIC2Item("windMill", 1))
						.buildAndRegister();
				} else if (entry.oreDictName.equals("plateAlloyAdvanced")) {
					RecipeMaps.ASSEMBLING.factory().EUt(4).duration(400)
						.input(RecipeEntry.fromStacks(2, entry.ores, Match.DAMAGE))
						.input(new ItemStack(Blocks.glass, 7, 0))
						.output(GT_ModHandler.getIC2Item("reinforcedGlass", 7L))
						.buildAndRegister();
				}
			}
		}
	}
}
