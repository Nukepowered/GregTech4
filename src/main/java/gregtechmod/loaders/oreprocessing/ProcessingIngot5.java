package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;
import net.minecraft.item.ItemStack;

public class ProcessingIngot5 implements IOreRecipeRegistrator {

	public ProcessingIngot5() {
		OrePrefixes.ingotQuintuple.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				ItemStack aStack = GT_OreDictUnificator.get(aPrefix, aMaterial);
				if (aStack != null && !aMaterial.contains(SubTag.NO_SMASHING)) {
					RecipeMaps.BENDING.factory().EUt(24).setShaped(true)
						.duration(Math.max(aMaterial.getMass() * 2, 1))
						.input(OrePrefixes.ingot, aMaterial, 5)
						.nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 5))
						.output(aStack)
						.buildAndRegister();
					if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammerquintupleingot, OrePrefixes.ingot.get(aMaterial), true)) {
						RecipeHandler.executeOnFinish(() -> {
							GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1, aStack),
									new Object[] { "I", "B", "H", 'H', GT_ToolDictNames.craftingToolHardHammer, 'I', OrePrefixes.ingotQuadruple.get(aMaterial), 'B', OrePrefixes.ingot.get(aMaterial) });
							GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1, aStack),
									new Object[] { GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial) });
						});
					} else {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1, aStack),
								new Object[] { OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial) }));
					}
					
					ItemStack plate = GT_OreDictUnificator.get(OrePrefixes.plateQuintuple, aMaterial);
					if (plate != null) {
						RecipeMaps.BENDING.factory().EUt(24).setShaped(true)
							.duration(Math.max(aMaterial.getMass() * 5, 1))
							.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
							.nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 1))
							.output(plate)
							.buildAndRegister();
					}
				}
			}
		}
	}
}
