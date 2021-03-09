package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraftforge.fluids.FluidStack;

public class ProcessingCellPlasma implements IOreRecipeRegistrator {

	public ProcessingCellPlasma() {
		OrePrefixes.cellPlasma.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
//		if (aMaterial == Materials.Empty) {
//			GT_ModHandler.removeRecipeByOutput(aStack);
//		}
		for (OreDictEntry entry : dictEntry) {
			Materials material = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, material) && material != Materials.Empty) {
				RecipeFactory<?> factory = RecipeMaps.PLASMA_FUELS.factory().EUt(1);
				if (GT_Utility.isFluidStackValid(material.mPlasma))
					factory.duration(1024 * Math.max(1, material.getMass())).input(new FluidStack(material.mPlasma, 1));
				else
					factory.duration(1000 * 1024 * Math.max(1, material.getMass())).input(RecipeEntry.fromStacks(entry.ores, Match.STRICT));
				factory.buildAndRegister();
				RecipeMaps.VACUUM_FREEZER.factory()
					.EUt(120)
					.duration(Math.max(material.getMass() * 2, 1))
					.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
					.output(GT_OreDictUnificator.get(OrePrefixes.cell, material, 1))
					.buildAndRegister();
			}
		}
	}
}
