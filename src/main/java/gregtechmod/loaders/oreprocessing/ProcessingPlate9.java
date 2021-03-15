package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.RecipeHandler;

import net.minecraft.item.ItemStack;

public class ProcessingPlate9 implements IOreRecipeRegistrator {

	public ProcessingPlate9() {
		OrePrefixes.plateDense.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				for (final ItemStack tStack : entry.ores) {
					RecipeHandler.scheduleCraftingToRemove(rec -> rec == null || rec.getRecipeOutput() == null || rec.getRecipeOutput().isItemEqual(tStack));
				}
			}
		}
	}
}
