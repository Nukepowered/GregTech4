package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import net.minecraft.item.ItemStack;

public class ProcessingToolHeadPickaxe implements IOreRecipeRegistrator {

	public ProcessingToolHeadPickaxe() {
		OrePrefixes.toolHeadPickaxe.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial) && !aMaterial.contains(SubTag.NO_SMASHING)) {
				for (ItemStack aStack : entry.ores) {
					GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, aStack), false, true,
							new Object[] { "PII", "F H", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial),
									Character.valueOf('I'), OrePrefixes.ingot.get(aMaterial), Character.valueOf('H'),
									GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('F'),
									GT_ToolDictNames.craftingToolFile });
				}
			}
		}
	}
}
