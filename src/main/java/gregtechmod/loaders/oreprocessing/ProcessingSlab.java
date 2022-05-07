package gregtechmod.loaders.oreprocessing;

import java.util.List;

import cpw.mods.fml.common.Loader;
import gregtechmod.GT_Mod;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingSlab implements IOreRecipeRegistrator {
	
	public static final boolean RAILCRAFT = Loader.isModLoaded("Railcraft");
	
	public ProcessingSlab() {
		OrePrefixes.slab.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials mat = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, mat) && mat == Materials.Wood) {
				if (RAILCRAFT) {
					RecipeMaps.CANNING.factory().EUt(4).duration(200).input(GT_ModHandler.getRCItem("fluid.creosote.bucket", 1L)).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).outputs(GT_ModHandler.getRCItem("part.tie.wood", 1L), new ItemStack(Items.bucket, 1)).buildAndRegister();
					RecipeMaps.CANNING.factory().EUt(4).duration(200).input(OrePrefixes.cell, Materials.Creosote).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).outputs(GT_ModHandler.getRCItem("part.tie.wood", 1L), GT_Items.Cell_Empty.get(1)).buildAndRegister();
				}
				
				GT_ModHandler.addPulverisationRecipe(entry, 1, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2), null, 0);
				
				for (ItemStack aStack : entry.ores) {
					if (aStack.getItem() instanceof ItemBlock && GT_Mod.sPlankStackSize < aStack.getItem().getItemStackLimit(aStack)) {
						aStack.getItem().setMaxStackSize(GT_Mod.sPlankStackSize);
					}
				}
			}
		}
	}
}
