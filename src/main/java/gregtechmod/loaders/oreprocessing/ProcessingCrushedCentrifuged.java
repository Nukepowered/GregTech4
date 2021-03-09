package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import net.minecraft.item.ItemStack;

public class ProcessingCrushedCentrifuged implements IOreRecipeRegistrator {

	public ProcessingCrushedCentrifuged() {
		OrePrefixes.crushedCentrifuged.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				for (ItemStack aStack : entry.ores) {
					GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, GT_Utility.selectItemInList(2, aMaterial, aMaterial.mOreByProducts), 1L), 10, false);
				}
			}
		}
	}
}
