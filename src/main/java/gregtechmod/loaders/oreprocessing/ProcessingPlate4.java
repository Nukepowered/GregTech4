package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.RecipeHandler;
import net.minecraft.item.ItemStack;

public class ProcessingPlate4 implements IOreRecipeRegistrator {

	public ProcessingPlate4() {
		OrePrefixes.plateQuadruple.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				ItemStack aStack = GT_OreDictUnificator.get(aPrefix, aMaterial);
				if (!aMaterial.contains(SubTag.NO_SMASHING) && GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammerquadrupleplate, OrePrefixes.plate.get(aMaterial), true)) {
					RecipeHandler.executeOnFinish(() -> {
						GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1, aStack),
								new Object[] { "I", "B", "H", 'H', GT_ToolDictNames.craftingToolHardHammer, 'I', OrePrefixes.plateTriple.get(aMaterial), 'B', OrePrefixes.plate.get(aMaterial) });
						GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1, aStack),
								new Object[] { GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial) });
					});
				} else {
					RecipeHandler.executeOnFinish(() -> GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1, aStack),
							new Object[] { OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial) }));
				}
				
				for (final ItemStack tStack : entry.ores) {
					RecipeHandler.scheduleCraftingToRemove(rec -> rec == null || rec.getRecipeOutput() == null || rec.getRecipeOutput().isItemEqual(tStack));
				}
			}
		}
	}
}
