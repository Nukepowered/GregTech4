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
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingPlate1 implements IOreRecipeRegistrator {

	public ProcessingPlate1() {
		OrePrefixes.plate.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(true, entry.ores.get(0)));
				
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
				
				RecipeHandler.scheduleIC2RecipeToRemove(GT_ModHandler.getCompressorRecipeList(), (in, out) -> in.matches(GT_Utility.copyAmount(9, entry.ores.get(0))));
				
				if ((aMaterial.mTypes & 4) != 0 && aMaterial.mTransparent) {
					RecipeMaps.LATHE.factory().EUt(16)
						.duration(Math.max(aMaterial.getMass() / 2, 1))
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.output(GT_OreDictUnificator.get(OrePrefixes.lense, aMaterial, 1L))
						.output(GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 1L))
						.buildAndRegister();
				}

				if (!aMaterial.contains(SubTag.NO_SMASHING)) {
					ItemStack tStack;
					if ((tStack = GT_OreDictUnificator.get(OrePrefixes.plateDouble, aMaterial, 1L)) != null) 
						RecipeMaps.BENDING.factory()
							.setShaped(true).EUt(24)
							.duration(Math.max(aMaterial.getMass() * 2, 1))
							.input(RecipeEntry.fromStacks(2, entry.ores, Match.STRICT))
							.nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 2))
							.output(tStack)
							.buildAndRegister();
					if ((tStack = GT_OreDictUnificator.get(OrePrefixes.plateTriple, aMaterial, 1L)) != null) 
						RecipeMaps.BENDING.factory()
							.setShaped(true).EUt(24)
							.duration(Math.max(aMaterial.getMass() * 3, 1))
							.input(RecipeEntry.fromStacks(3, entry.ores, Match.STRICT))
							.nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 3))
							.output(tStack)
							.buildAndRegister();
					if ((tStack = GT_OreDictUnificator.get(OrePrefixes.plateQuadruple, aMaterial, 1L)) != null) 
						RecipeMaps.BENDING.factory()
							.setShaped(true).EUt(24)
							.duration(Math.max(aMaterial.getMass() * 4, 1))
							.input(RecipeEntry.fromStacks(4, entry.ores, Match.STRICT))
							.nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 4))
							.output(tStack)
							.buildAndRegister();
					if ((tStack = GT_OreDictUnificator.get(OrePrefixes.plateQuintuple, aMaterial, 1L)) != null) 
						RecipeMaps.BENDING.factory()
							.setShaped(true).EUt(24)
							.duration(Math.max(aMaterial.getMass() * 5, 1))
							.input(RecipeEntry.fromStacks(5, entry.ores, Match.STRICT))
							.nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 5))
							.output(tStack)
							.buildAndRegister();
					if ((tStack = GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1L)) != null) 
						RecipeMaps.BENDING.factory()
							.setShaped(true).EUt(24)
							.duration(Math.max(aMaterial.getMass() * 9, 1))
							.input(RecipeEntry.fromStacks(9, entry.ores, Match.STRICT))
							.nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 9))
							.output(tStack)
							.buildAndRegister();
					
					ItemStack aStack = GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial);
					if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammerplating, OrePrefixes.ingot.get(aMaterial), true)) {
						RecipeHandler.executeOnFinish(() -> {
							GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1, aStack),
									new Object[] { "H", "I", "I", 'H', GT_ToolDictNames.craftingToolHardHammer, 'I', OrePrefixes.ingot.get(aMaterial) });
							GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1, aStack),
									new Object[] { GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.ingot.get(aMaterial) });
						});
					}

					if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammerplating, OrePrefixes.ingotDouble.get(aMaterial), true)) {
						RecipeHandler.executeOnFinish(() -> {
							GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1, aStack),
									new Object[] { "H", "I", 'H', GT_ToolDictNames.craftingToolHardHammer, 'I', OrePrefixes.ingotDouble.get(aMaterial) });
							GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(2, aStack), new Object[] {
									GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.ingotDouble.get(aMaterial) });
						});
					}
				}
				
				for (ItemStack aStack : entry.ores) {
					RecipeHandler.scheduleCraftingToRemove(rec -> rec != null && rec.getRecipeOutput() != null && rec.getRecipeOutput().isItemEqual(aStack));
					
					if (!aMaterial.contains(SubTag.NO_SMELTING)) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1, aStack),
								GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L)));
					}
					
					if (aMaterial == Materials.Paper) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, aStack, true) ? 2 : 3, aStack),
								new Object[] { "XXX", 'X', new ItemStack(Items.reeds, 1, 32767) }));
					}
				}
			}
		}
	}
}
