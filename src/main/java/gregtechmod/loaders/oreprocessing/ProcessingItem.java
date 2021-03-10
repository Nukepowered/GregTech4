package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import net.minecraft.item.ItemStack;

public class ProcessingItem implements IOreRecipeRegistrator {

	public ProcessingItem() {
		OrePrefixes.item.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry))) {
				for (ItemStack aStack : entry.ores) {
					if (entry.oreDictName.equals("itemManganese")) {
						GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Manganese, GregTech_API.MATERIAL_UNIT);
					} else if (entry.oreDictName.equals("itemSalt")) {
						GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Salt, GregTech_API.MATERIAL_UNIT);
					} else if (entry.oreDictName.equals("itemMagnesium")) {
						GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Magnesium, GregTech_API.MATERIAL_UNIT);
					} else if (!entry.oreDictName.equals("itemPhosphorite") && !entry.oreDictName.equals("itemPhosphorus")) {
						if (entry.oreDictName.equals("itemSulfur")) {
							GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Sulfur, GregTech_API.MATERIAL_UNIT);
						} else if (!entry.oreDictName.equals("itemAluminum") && !entry.oreDictName.equals("itemAluminium")) {
							if (entry.oreDictName.equals("itemSaltpeter")) {
								GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Saltpeter, GregTech_API.MATERIAL_UNIT);
							} else if (entry.oreDictName.equals("itemUranium")) {
								GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Uranium, GregTech_API.MATERIAL_UNIT);
							} else if (!entry.oreDictName.equals("itemRubber") && !entry.oreDictName.equals("itemRecord")) {
								GT_Log.log.warn("Item Name: " + entry.oreDictName + " !!!Unknown Item detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it\'s just an Information.");
							}
						} else {
							GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Aluminium, GregTech_API.MATERIAL_UNIT);
						}
					} else {
						GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1, aStack), Materials.Phosphorus, GregTech_API.MATERIAL_UNIT);
					}
				}
			}
		}
	}
}
