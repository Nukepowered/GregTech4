package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.GT_Mod;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.OreDictEntry;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingLeaves implements IOreRecipeRegistrator {

	public ProcessingLeaves() {
		OrePrefixes.treeLeaves.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry))) {
				for (ItemStack aStack : entry.ores) {
					if (aStack.getItem() instanceof ItemBlock && GT_Mod.sWoodStackSize < aStack.getMaxStackSize()) {
						aStack.getItem().setMaxStackSize(GT_Mod.sWoodStackSize);
					}
				}
			}
		}
	}
}
