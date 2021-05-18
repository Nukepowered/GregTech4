package gregtechmod.loaders.oreprocessing;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Shapeless_Recipe;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import net.minecraft.item.ItemStack;
import gregtechmod.common.recipe.RecipeEntry.Match;

public class ProcessingNugget implements IOreRecipeRegistrator {

	public ProcessingNugget() {
		OrePrefixes.nugget.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial) && (aMaterial.mTypes & 2) != 0) {
				ItemStack round = GT_OreDictUnificator.get(OrePrefixes.round, aMaterial, 1L);
				
				if (round != null) RecipeMaps.LATHE.factory().EUt(8)
					.duration(Math.max(aMaterial.getMass() / 4, 1))
					.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
					.output(round)
					.buildAndRegister();
				RecipeMaps.ALLOY_SMELTING.factory().EUt(2).duration(200)
					.input(RecipeEntry.fromStacks(9, entry.ores, Match.STRICT))
					.output(GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L))
					.buildAndRegister();
				
				RecipeHandler.executeOnFinish(() -> {
					GameRegistry.addRecipe(new GT_Shapeless_Recipe(GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L), new Object[] { entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName }));
					if (round != null)
						GameRegistry.addRecipe(new GT_Shapeless_Recipe(GT_OreDictUnificator.get(OrePrefixes.round, aMaterial, 1L), new Object[] { GT_ToolDictNames.craftingToolFile.toString(), entry.oreDictName, entry.oreDictName }));
				});
			}
		}
	}
}
