package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingGem implements IOreRecipeRegistrator {
	
	public ProcessingGem() {
		OrePrefixes.gem.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				if ((aMaterial == Materials.Charcoal || aMaterial == Materials.Coal) && GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
					RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, GT_OreDictUnificator.get(aPrefix, aMaterial), null, null, new ItemStack(Items.stick, 1, 0)));
				}
				
				if (aMaterial.mFuelPower > 0) {
					RecipeFactory<?> factory;
					switch(aMaterial.mFuelType) {
					case 0:
						factory = RecipeMaps.DIESEL_FUELS.factory();
						break;
					case 1:
						factory = RecipeMaps.TURBINE_FUELS.factory();
						break;
					case 2:
						factory = RecipeMaps.HOT_FUELS.factory();
						break;
					case 4:
						factory = RecipeMaps.PLASMA_FUELS.factory();
						break;
					case 5:
						factory = RecipeMaps.MAGIC_FUELS.factory();
						break;
					default:
						factory = RecipeMaps.DENSE_FUELS.factory();
						break;
					}
					
					factory.EUt(1)
						.duration(aMaterial.mFuelPower * 2 * 1000)
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.buildAndRegister();
				}
				
				if ((aMaterial.mTypes & 4) != 0 && aMaterial.mTransparent && GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1) == null) {
					RecipeMaps.LATHE.factory().EUt(16)
						.duration(Math.max(aMaterial.getMass(), 1))
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.output(GT_OreDictUnificator.get(OrePrefixes.lense, aMaterial, 1L))
						.output(GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 1L))
						.buildAndRegister();
				}
				
				if (aMaterial == Materials.Mercury) {
					RecipeMaps.CANNING.factory().EUt(1).duration(100)
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.input(GT_Items.Cell_Empty.get(1))
						.output(GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1L))
						.buildAndRegister();
				}
				
				if (aMaterial == Materials.NetherQuartz) {
					System.err.print("");
				}
				
				if (!OrePrefixes.block.isIgnored(aMaterial)) {
					GT_RecipeRegistrator.registerBlockForcibly(aPrefix, aMaterial, entry.ores);
					RecipeHandler.scheduleIC2RecipeToRemove(GT_ModHandler.getCompressorRecipeList(), (in, out) -> in.matches(entry.ores.get(0)));
					ItemStack block = GT_OreDictUnificator.get(OrePrefixes.block, aMaterial);
					
					if (block != null) {
						if (aMaterial.mSmallBlock) {
							RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCompressionRecipe(entry, 4, block));
						} else {
							RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCompressionRecipe(entry, 9, block));
						}
						
						if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.storageblockcrafting, OrePrefixes.block.get(aMaterial), false)) {
							for (ItemStack b : entry.ores) {
								if (aMaterial.mSmallBlock) {
									RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, b, b, null, b, b, null));
								} else {
									RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, b, b, b, b, b, b, b, b, b));
								}
							}
						}
					}
				}
				
				for (ItemStack stack : entry.ores) {
					if (!aMaterial.contains(SubTag.NO_SMELTING)) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1, stack), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L)));
					}
				}
			}
		}
	}
}
