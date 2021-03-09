package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Shaped_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingGear implements IOreRecipeRegistrator {

	public ProcessingGear() {
		OrePrefixes.gearGt.add(this);
	}
	
	@Override
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				for (ItemStack aStack : entry.ores) {
					switch (aMaterial) {
					case Wood:
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Utility.copyAmount(1, aStack), new Object[]{"SPS", "PTP", "SPS", 'P', "plankWood", 'S', OrePrefixes.stick.get(aMaterial), 'T', GT_ToolDictNames.craftingToolSaw.toString()}));
						break;
					case Stone:
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Utility.copyAmount(1, aStack), new Object[]{"SPS", "PTP", "SPS", 'P', "stoneSmooth", 'S', new ItemStack(Blocks.stone_button, 1, 0), 'T', GT_ToolDictNames.craftingToolFile.toString()}));
						break;
					default:
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Utility.copyAmount(1, aStack), new Object[]{"SPS", "PTP", "SPS", 'P', OrePrefixes.plate.get(aMaterial), 'S', OrePrefixes.stick.get(aMaterial), 'T', GT_ToolDictNames.craftingToolWrench.toString()}));
					}
				}
			}
		}
	}
}
