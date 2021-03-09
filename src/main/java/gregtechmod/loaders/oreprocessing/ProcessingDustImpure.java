package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.item.ItemStack;

public class ProcessingDustImpure implements IOreRecipeRegistrator {

	public ProcessingDustImpure() {
		OrePrefixes.dustPure.add(this);
		OrePrefixes.dustImpure.add(this);
		OrePrefixes.dustRefined.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				Materials tByProduct = (Materials) GT_Utility.selectItemInList(aPrefix == OrePrefixes.dustPure ? 1 : (aPrefix == OrePrefixes.dustRefined ? 2 : 0), aMaterial, aMaterial.mOreByProducts);
				ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tByProduct, GT_OreDictUnificator.get(OrePrefixes.nugget, tByProduct, 1L), 1L);
				if (tStack == null) {
					tStack = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tByProduct, 1L);
					if (tStack == null) {
						tStack = GT_OreDictUnificator.get(OrePrefixes.dust, tByProduct, GT_OreDictUnificator.get(OrePrefixes.gem, tByProduct, 1L), 1L);
						if (tStack == null) {
							tStack = GT_OreDictUnificator.get(OrePrefixes.cell, tByProduct, 1L);
							if (tStack == null) {
								RecipeMaps.CENTRIFUGE.factory().EUt(5)
									.duration(Math.max(1, aMaterial.getMass()))
									.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
									.output(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1))
									.buildAndRegister();
							} else {
								RecipeMaps.CENTRIFUGE.factory().EUt(5).setShaped(true)
									.duration(Math.max(1, aMaterial.getMass() * 72))
									.input(RecipeEntry.fromStacks(9, entry.ores, Match.STRICT))
									.input(GT_Items.Cell_Empty.get(1))
									.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 9), tStack)
									.buildAndRegister();
							}
						} else {
							RecipeMaps.CENTRIFUGE.factory().EUt(5)
								.duration(Math.max(1, aMaterial.getMass() * 72))
								.input(RecipeEntry.fromStacks(9, entry.ores, Match.STRICT))
								.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 9), tStack)
								.buildAndRegister();
						}
					} else {
						RecipeMaps.CENTRIFUGE.factory().EUt(5)
							.duration(Math.max(1, aMaterial.getMass() * 16))
							.input(RecipeEntry.fromStacks(2, entry.ores, Match.STRICT))
							.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 2), tStack)
							.buildAndRegister();
					}
				} else {
					RecipeMaps.CENTRIFUGE.factory().EUt(5)
						.duration(Math.max(1, aMaterial.getMass() * 8))
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), tStack)
						.buildAndRegister();
				}
			}
		}
	}
}
