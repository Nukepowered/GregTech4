package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.recipe.RecipeMaps;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingStoneCobble implements IOreRecipeRegistrator {

	public ProcessingStoneCobble() {
		OrePrefixes.stoneCobble.add(this);
	}

	@Override
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry)))
				for (ItemStack aStack : entry.ores)
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(400).input(OrePrefixes.stick, Materials.Wood).input(GT_Utility.copyAmount(1L, aStack)).output(new ItemStack(Blocks.lever, 1)).buildAndRegister();
		}
	}
}
