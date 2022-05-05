package gregtechmod.loaders.oreprocessing;

import java.util.List;
import java.util.stream.Collectors;

import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.item.ItemStack;

public class ProcessingDye implements IOreRecipeRegistrator {

	public ProcessingDye() {
		OrePrefixes.dye.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				Dyes aDye = Dyes.get(entry.oreDictName);
				if(aDye.mColor >= 0 && aDye.mColor < 16) {
					List<ItemStack> ores = entry.ores.stream()
							.filter(s -> s.getMaxStackSize() >= 16 && GT_Utility.getContainerItem(s) == null)
							.collect(Collectors.toList());
			        RecipeMaps.CANNING.factory()
			        	.EUt(1).duration(800)
			        	.input(RecipeEntry.fromStacks(16, ores, Match.STRICT))
			        	.input(GT_Items.Spray_Empty.get(1))
			        	.output(GT_Items.SPRAY_CAN_DYES[aDye.mColor].get(1))
			        	.buildAndRegister();
			    }
			}
		}
	}
}
