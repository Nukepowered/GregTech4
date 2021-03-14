package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.GT_Mod;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.OreDictEntry;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingSaplings implements IOreRecipeRegistrator {

	public ProcessingSaplings() {
		OrePrefixes.treeSapling.add((IOreRecipeRegistrator) this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				GT_ModHandler.addPulverisationRecipe(entry, 1, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2L), null, 0);
				
				for (ItemStack aStack : entry.ores) {
					if (aStack.getItem() instanceof ItemBlock && GT_Mod.sWoodStackSize < aStack.getItem().getItemStackLimit(aStack)) {
						aStack.getItem().setMaxStackSize(GT_Mod.sWoodStackSize);
					}
				}
			}
		}
	}
}
