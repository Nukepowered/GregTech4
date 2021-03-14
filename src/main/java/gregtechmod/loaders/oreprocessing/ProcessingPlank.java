package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.GT_Mod;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingPlank implements IOreRecipeRegistrator {

	public ProcessingPlank() {
		OrePrefixes.plank.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				if (aMaterial == Materials.Wood) {
					RecipeMaps.LATHE.factory().EUt(8).duration(10)
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.output(GT_OreDictUnificator.get(OrePrefixes.stick,  Materials.Wood, 2L))
						.buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(1600)
						.input(RecipeEntry.fromStacks(8, entry.ores, Match.STRICT))
						.input(GT_OreDictUnificator.get(OrePrefixes.dust,  Materials.Redstone, 1L))
						.output(new ItemStack(Blocks.noteblock, 1))
						.buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(1600)
						.input(RecipeEntry.fromStacks(8, entry.ores, Match.STRICT))
						.input(GT_OreDictUnificator.get(OrePrefixes.gem,  Materials.Diamond, 1L))
						.output(new ItemStack(Blocks.jukebox, 1))
						.buildAndRegister();
					
					GT_ModHandler.addPulverisationRecipe(entry, 1, GT_OreDictUnificator.get(OrePrefixes.dust,  Materials.Wood, 1L), null, 0);
					
					for (ItemStack aStack : entry.ores) {
						if (aStack.getItem() instanceof ItemBlock && GT_Mod.sPlankStackSize < aStack.getMaxStackSize()) {
							aStack.getItem().setMaxStackSize(GT_Mod.sPlankStackSize);
						}
					}
				}
			}
		}
	}
}
