package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

public class ProcessingBattery implements IOreRecipeRegistrator {

	public ProcessingBattery() {
		OrePrefixes.battery.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials material = this.getMaterial(aPrefix, entry);
			if (material == Materials.Lithium && this.isExecutable(aPrefix, material)) {
				RecipeMaps.ASSEMBLING.factory().EUt(16).duration(12800)
					.input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE))
					.input(GT_ModHandler.getIC2Item("cropnalyzer", 1L, 32767))
					.output(GT_Items.Tool_Scanner.getAlmostBroken(1))
					.buildAndRegister();
				RecipeMaps.ASSEMBLING.factory().EUt(4).duration(3200)
					.input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE))
					.input(OrePrefixes.plate, Materials.Aluminium)
					.output(GregTech_API.getGregTechComponent(26, 1))
					.buildAndRegister();
			}
		}
	}
}
