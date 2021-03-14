package gregtechmod.loaders.oreprocessing;

import java.util.List;
import java.util.stream.Collectors;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingLog implements IOreRecipeRegistrator {

	public ProcessingLog() {
		OrePrefixes.log.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		List<Integer> logs = dictEntry.stream()
			.map(ent -> ent.ores)
			.flatMap(List::stream)
			.map(s -> GT_Utility.stackToInt(s))
			.collect(Collectors.toList());
		
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", false)) {
			RecipeHandler.scheduleSmeltingToRemove((in, out) -> out.isItemEqual(new ItemStack(Items.coal, 1, 1)) && logs.contains(GT_Utility.stackToInt(in)));
		}
		
		for (OreDictEntry entry : dictEntry) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				RecipeEntry recEntry = RecipeEntry.fromStacks(entry.ores, Match.STRICT);
				if (aMaterial == Materials.Rubber) {
					RecipeMaps.CENTRIFUGE.factory().EUt(5)
						.duration(5000).setShaped(true)
						.input(RecipeEntry.fromStacks(16, entry.ores, Match.STRICT))
						.input(GT_Items.Cell_Empty.get(1))
						.output(GT_Items.IC2_Resin.get(8))
						.output(GT_ModHandler.getIC2Item("plantBall", 6L))
						.output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L))
						.output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 4))
						.buildAndRegister();
					GT_ModHandler.addSawmillRecipe(entry, 1, 100, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 16L), GT_Items.IC2_Resin.get(1));
					GT_ModHandler.addPulverisationRecipe(entry, 1, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 6L), GT_Items.IC2_Resin.get(1), 33);
					
					
					RecipeMaps.SAWMILL.factory().EUt(30).duration(200).setShaped(true).input(recEntry).input(GT_ModHandler.getWater(1000)).outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 16L), GT_Items.IC2_Resin.get(1)).buildAndRegister();
					
					// TODO Schedule removal of extractor recipe
					RecipeHandler.executeOnFinish(() -> GT_ModHandler.addExtractionRecipe(recEntry, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rubber, 1L)));
				} else {
					GT_ModHandler.addPulverisationRecipe(entry, 1, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 6L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), 80);
				}
				
				for (ItemStack aStack : entry.ores) {
					if (aStack.getItem() instanceof ItemBlock && GT_Mod.sWoodStackSize < aStack.getMaxStackSize()) {
						aStack.getItem().setMaxStackSize(GT_Mod.sWoodStackSize);
					}
				
					int aMeta = aStack.getItemDamage();
					ItemStack tStack;
					if (aMeta == 32767) {
						for (int tPlanks = 0; tPlanks < 16; ++tPlanks) {
							tStack = GT_ModHandler.getRecipeOutput(new ItemStack(aStack.getItem(), 1, tPlanks));
							if (tStack != null) {
								ItemStack tPlanks1 = GT_Utility.copy(tStack);
								tPlanks1.stackSize = tPlanks1.stackSize * 3 / 2;
								
								final ItemStack planks = new ItemStack(aStack.getItem(), 1, tPlanks);
								
								RecipeMaps.CUTTING.factory()
									.EUt(8).duration(200)
									.input(planks)
									.output(GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank ? tStack.stackSize : (tStack.stackSize * 5 / 4), tStack))
									.buildAndRegister();
								RecipeMaps.SAWMILL.factory()
									.EUt(30).duration(200)
									.input(planks)
									.input(GT_ModHandler.getWater(1000))
									.outputs(tPlanks1, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L))
									.buildAndRegister();
								
								RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, planks, null, null, null));
								GT_ModHandler.addSawmillRecipe(planks, tPlanks1, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L));
								
								final ItemStack tStack1 = tStack.copy();
								
								RecipeHandler.executeOnFinish(() -> {
									GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank ? tStack1.stackSize : (tStack1.stackSize * 5 / 4), tStack1),
											new Object[] { "S", "L", 'S', GT_ToolDictNames.craftingToolSaw, 'L', planks });
									GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount((tStack1.stackSize / (GT_Mod.sNerfedWoodPlank ? 2 : 1)), tStack1),
											new Object[] { planks });
								});
							} else break;
						}
					} else {
						tStack = GT_ModHandler.getRecipeOutput(GT_Utility.copyAmount(1, aStack));
						if (tStack != null) {
							ItemStack var10 = GT_Utility.copy(tStack);
							var10.stackSize = var10.stackSize * 3 / 2;
							RecipeMaps.CUTTING.factory()
								.EUt(8).duration(200)
								.input(aStack)
								.output(GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank ? tStack.stackSize : (tStack.stackSize * 5 / 4), tStack))
								.buildAndRegister();
							RecipeMaps.SAWMILL.factory()
								.EUt(30).duration(200)
								.input(aStack)
								.input(GT_ModHandler.getWater(1000))
								.outputs(var10, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L))
								.buildAndRegister();
							
							GT_ModHandler.addSawmillRecipe(GT_Utility.copyAmount(1, aStack), var10, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L));
							RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, GT_Utility.copyAmount(1, aStack), null, null, null));
							
							final ItemStack tStack1 = tStack.copy();
							
							RecipeHandler.executeOnFinish(() -> {
								GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank ? tStack1.stackSize : (tStack1.stackSize * 5 / 4) , tStack1),
										new Object[] { "S", "L", 'S', GT_ToolDictNames.craftingToolSaw, 'L', GT_Utility.copyAmount(1, aStack) });
								GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount((tStack1.stackSize / (GT_Mod.sNerfedWoodPlank ? 2 : 1)), tStack1),
										new Object[] { GT_Utility.copyAmount(1, aStack) });
							});
						}
					}
				}
			}
		}
	}
}
