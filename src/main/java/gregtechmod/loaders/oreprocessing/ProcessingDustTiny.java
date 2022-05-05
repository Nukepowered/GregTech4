package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Shapeless_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class ProcessingDustTiny implements IOreRecipeRegistrator {

	public ProcessingDustTiny() {
		OrePrefixes.dustTiny.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				ItemStack ingot;
				RecipeHandler.executeOnFinish(() -> GameRegistry.addRecipe(new GT_Shapeless_Recipe(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L),
						new Object[] { entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName, entry.oreDictName })));
				if (!aMaterial.contains(SubTag.NO_SMELTING) && (ingot = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L)) != null) {
					if (aMaterial.mBlastFurnaceRequired) {
						RecipeFactory<?> factory = RecipeMaps.BLAST_FURNACE.factory()
								.minTemperature(aMaterial.mBlastFurnaceTemp)
								.EUt(120).duration(Math.max(aMaterial.getMass() / 40, 1) * aMaterial.mBlastFurnaceTemp)
								.input(RecipeEntry.fromStacks(9, entry.ores, Match.STRICT));
							if (aMaterial.mBlastFurnaceTemp > 1750)
								factory.output(GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial));
							else
								factory.output(ingot);
							factory.buildAndRegister();
					} else {
						RecipeMaps.ALLOY_SMELTING.factory().EUt(3).duration(130)
							.input(RecipeEntry.fromStacks(9, entry.ores, Match.STRICT))
							.output(ingot)
							.buildAndRegister();
						
						GT_ModHandler.addInductionSmelterRecipe(GT_Utility.copyAmount(9L, entry.ores.get(0)), null, ingot, null, 130 * 3 * 2, 0);
						
						for (ItemStack stack : entry.ores) {
							RecipeHandler.executeOnFinish(() -> {
								GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1, stack), GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 1L));
							});
						}
					}
				}
			}
		}
	}
}
