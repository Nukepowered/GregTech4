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
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.item.ItemStack;

public class ProcessingIngot1 implements IOreRecipeRegistrator {

	public ProcessingIngot1() {
		OrePrefixes.ingot.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				RecipeFactory<?> factory;
				if (aMaterial.mFuelPower > 0) {
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
					
					factory.EUt(1).duration(aMaterial.mFuelPower * 1000).input(RecipeEntry.fromStacks(entry.ores, Match.STRICT)).buildAndRegister();
				}
				
				if (!aMaterial.contains(SubTag.NO_SMASHING) && GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial) != null) {
					RecipeMaps.HAMMER.factory().EUt(16).duration(Math.max(aMaterial.getMass(), 1)).input(RecipeEntry.fromStacks(2, entry.ores, Match.STRICT)).output(GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1)).buildAndRegister();
					RecipeMaps.BENDING.factory().EUt(24).duration(Math.max(aMaterial.getMass() *  2, 1)).setShaped(true).input(RecipeEntry.fromStacks(1, entry.ores, Match.STRICT)).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 1)).output(GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1)).buildAndRegister();
					if (GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial) != null) 
						RecipeMaps.BENDING.factory().EUt(24).duration(Math.max(aMaterial.getMass() * 18, 1)).setShaped(true).input(RecipeEntry.fromStacks(9, entry.ores, Match.STRICT)).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 9)).output(GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1)).buildAndRegister();
				}

				if (!OrePrefixes.block.isIgnored(aMaterial)) {
					GT_ModHandler.addCompressionRecipe(entry, 9, GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L));
					
					ItemStack a = entry.ores.get(0);
					if (GT_ModHandler.getRecipeOutput(a, a, a, a, a, a, a, a, a) != null)
						if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.storageblockcrafting, OrePrefixes.block.get(aMaterial), false))
							RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, a, a, a, a, a, a, a, a, a));
				}

				if (GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial) != null) {
					RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1L), new Object[] { "F", "I", 'F', GT_ToolDictNames.craftingToolFile, 'I', entry.oreDictName }));
					RecipeMaps.LATHE.factory().EUt(16)
						.duration(Math.max(aMaterial.getMass() * 5, 1))
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.output(GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1L))
						.output(GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 2L))
						.buildAndRegister();
				}
				
				if (!aMaterial.contains(SubTag.NO_SMELTING) && GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 1L) != null) {
					boolean needRecipe = false;
					
					for (ItemStack aStack : entry.ores) {
						if (GT_ModHandler.getSmeltingOutput(aStack, false, null) == null) {
							if (aStack.getItem().hasContainerItem(aStack) || !GregTech_API.sRecipeFile.get(GT_ConfigCategories.Machines.smelting, entry.oreDictName, true)) {
								needRecipe = true;
								break;
							} else {
								RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 9L)));
							}
						}
					}
					
					if (needRecipe) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 9L), new Object[] { entry.oreDictName }));
					}
				}

				ItemStack tStack;
				if (null != (tStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L))) {
					if (aMaterial.mBlastFurnaceRequired || aMaterial.contains(SubTag.NO_SMELTING)) {
						RecipeHandler.scheduleSmeltingToRemove((in, out) -> in.isItemEqual(tStack));
					}

					GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copyAmount(1, entry.ores.get(0)), // TODO vanilla recipe replcer
							GT_Utility.copyAmount(1, tStack), OrePrefixes.plate.get(aMaterial),
							!aMaterial.contains(SubTag.NO_SMELTING), true, !aMaterial.contains(SubTag.NO_SMASHING));
				} else {
					GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copyAmount(1, entry.ores.get(0)),
							GT_Utility.copyAmount(1, entry.ores.get(0)), OrePrefixes.plate.get(aMaterial),
							!aMaterial.contains(SubTag.NO_SMELTING), false, !aMaterial.contains(SubTag.NO_SMASHING));
				}

				if (aMaterial == Materials.Mercury) {
					GT_Log.log.error("Quicksilver Ingots?, Don\'t tell me there is an Armor made of that highly toxic and very likely to be melting Material!");
				}
			}
		}
	}
}
