package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.item.ItemStack;

public class ProcessingOreSmelting implements IOreRecipeRegistrator {

	private final static OrePrefixes[] mSmeltingPrefixes = new OrePrefixes[] {
			OrePrefixes.crushed, OrePrefixes.crushedPurified, OrePrefixes.crushedCentrifuged, OrePrefixes.dustImpure, OrePrefixes.dustPure, OrePrefixes.dustRefined
	};

	public ProcessingOreSmelting() {
		for (OrePrefixes tPrefix : mSmeltingPrefixes) {
			tPrefix.add(this);
		}
	}

	@Override
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial) && !aMaterial.contains(SubTag.NO_SMELTING)) {
				if (!aMaterial.mBlastFurnaceRequired && !aMaterial.mDirectSmelting.mBlastFurnaceRequired) {
					switch (aPrefix) {
					case crushed:
					case crushedPurified:
					case crushedCentrifuged:
						ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial.mDirectSmelting, 10L);
						if (tStack == null)
							tStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L);

						for (ItemStack aStack : entry.ores)
							GT_ModHandler.addSmeltingRecipe(aStack, tStack);
						break;
					default:
						for (ItemStack aStack : entry.ores)
							GT_ModHandler.addSmeltingRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L));
					}
				} else if (GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L) != null) {
					for (final ItemStack aStack : entry.ores)
						RecipeHandler.scheduleSmeltingToRemove((in, out) -> in.isItemEqual(aStack));
					
					ItemStack out = aMaterial.mBlastFurnaceTemp > 1750
							? GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial, GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L), 1L)
							: GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L);
					
					if (out == null) {
						System.err.println(aMaterial);
					}
					
					RecipeMaps.BLAST_FURNACE.factory()
						.minTemperature(aMaterial.mBlastFurnaceTemp)
						.EUt(120).duration(Math.max(aMaterial.getMass() / 4, 1) * aMaterial.mBlastFurnaceTemp)
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.output(out)
						.buildAndRegister();
					if (aMaterial.mBlastFurnaceTemp <= 1000) {
						for (ItemStack aStack : entry.ores)
							GT_ModHandler.addRCBlastFurnaceRecipe(GT_Utility.copyAmount(1, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L), aMaterial.mBlastFurnaceTemp * 2);
					}
				}
			}
		}
	}
}
