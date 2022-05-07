package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
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
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.RecipeMaps;

import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingOre implements IOreRecipeRegistrator {

	public ProcessingOre() {
		OrePrefixes.ore.add(this);
		OrePrefixes.oreEnd.add(this);
		OrePrefixes.oreNether.add(this);
		OrePrefixes.oreDense.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				int mult = aPrefix != OrePrefixes.oreNether && aPrefix != OrePrefixes.oreDense ? 1 : 2;
				
				if (aMaterial == Materials.Oilsands) {
					RecipeMaps.CENTRIFUGE.factory().EUt(5).setShaped(true)
						.duration(1000 * mult)
						.input(RecipeEntry.fromStacks(2, entry.ores, Match.STRICT))
						.input(GT_Items.Cell_Empty.get(1))
						.output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil, mult))
						.output(new ItemStack(Blocks.sand, 1))
						.buildAndRegister();
				} else {
					registerStandardOreRecipes(aPrefix, aMaterial, entry, mult);
				}
				
				ItemStack tIngot = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L);
				ItemStack tSmeltInto = tIngot == null ? (aMaterial.contains(SubTag.SMELTING_TO_GEM)
						? GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial.mOreReplacement.mDirectSmelting,
								GT_OreDictUnificator.get(OrePrefixes.crystal, aMaterial.mOreReplacement.mDirectSmelting,
										GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial.mOreReplacement,
												GT_OreDictUnificator.get(OrePrefixes.crystal, aMaterial.mOreReplacement, 1L), 1L),
										1L),
								1L)
						: null) : tIngot;
				
				for (ItemStack aStack : entry.ores) {
					if (aMaterial != Materials.Oilsands) 
						defaultOreRegistration(aPrefix, aMaterial, aStack, mult, tSmeltInto);
					if (aStack.getItem() instanceof ItemBlock && aStack.getMaxStackSize() > GT_Mod.sOreStackSize)
						aStack.getItem().setMaxStackSize(GT_Mod.sOreStackSize);
				}
			}
		}
	}
	
	private static void defaultOreRegistration(OrePrefixes aPrefix, Materials aMaterial, ItemStack aOreStack, int aMultiplier, ItemStack tSmeltInto) {
		GT_ModHandler.addValuableOre(aOreStack.copy(), aMaterial.mOreValue);
		if (aMaterial.mBlastFurnaceRequired || aMaterial.mDirectSmelting.mBlastFurnaceRequired) {
			RecipeHandler.scheduleSmeltingToRemove((in, out) -> in.isItemEqual(aOreStack));
		} else {
			GT_ModHandler.addSmeltingRecipe(aOreStack, GT_Utility.copyAmount(aMultiplier * aMaterial.mSmeltingMultiplier, tSmeltInto));
		}
	}
	
	private static boolean registerStandardOreRecipes(OrePrefixes aPrefix, Materials aMaterial, OreDictEntry entry, int aMultiplier) {
		if (aMaterial != null) {
			Materials tMaterial = aMaterial.mOreReplacement;
			Materials tPrimaryByMaterial = null;
			Materials tSecondaryByMaterial = null;
			aMultiplier = Math.max(1, aMultiplier);
			ItemStack tIngot = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1L);
			ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial, 1L);
			ItemStack tSmeltInto = tIngot == null ? (aMaterial.contains(SubTag.SMELTING_TO_GEM)
					? GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial.mDirectSmelting,
							GT_OreDictUnificator.get(OrePrefixes.crystal, tMaterial.mDirectSmelting,
									GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial,
											GT_OreDictUnificator.get(OrePrefixes.crystal, tMaterial, 1L), 1L),
									1L),
							1L)
					: null) : tIngot;
			ItemStack tSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMaterial, 1L);
			ItemStack tDust = GT_OreDictUnificator.get(OrePrefixes.dust, tMaterial, tGem, 1L);
			ItemStack tCleaned = GT_OreDictUnificator.get(OrePrefixes.crushedPurified, tMaterial, tDust, 1L);
			ItemStack tCrushed = GT_OreDictUnificator.get(OrePrefixes.crushed, tMaterial,
					(aMaterial.mOreMultiplier * aMultiplier));
			ItemStack tPrimaryByProduct = null;
			ItemStack tPrimaryByProductSmall = null;
			ItemStack tSecondaryByProduct = null;
			ItemStack tSecondaryByProductSmall = null;
			if (tCrushed == null) {
				tCrushed = GT_OreDictUnificator.get(OrePrefixes.dustImpure, tMaterial,
						GT_Utility.copyAmount((aMaterial.mOreMultiplier * aMultiplier),
								new Object[] { tCleaned, tDust, tGem }),
						(aMaterial.mOreMultiplier * aMultiplier));
			}

			Iterator<Materials> iterator = aMaterial.mOreByProducts.iterator();

			while (iterator.hasNext()) {
				Materials tMat = (Materials) iterator.next();
				if (tPrimaryByProduct == null) {
					tPrimaryByMaterial = tMat;
					tPrimaryByProduct = GT_OreDictUnificator.get(OrePrefixes.dust, tMat, 1L);
					tPrimaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMat, 1L);
					if (tPrimaryByProductSmall == null) {
						tPrimaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMat,
								GT_OreDictUnificator.get(OrePrefixes.nugget, tMat, 2L), 2L);
					}
				}

				if (tSecondaryByProduct == null || tSecondaryByMaterial == tPrimaryByMaterial) {
					tSecondaryByMaterial = tMat;
					tSecondaryByProduct = GT_OreDictUnificator.get(OrePrefixes.dust, tMat, 1L);
					tSecondaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMat, 1L);
					if (tSecondaryByProductSmall == null) {
						tSecondaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMat,
								GT_OreDictUnificator.get(OrePrefixes.nugget, tMat, 2L), 2L);
					}
				}
			}

			if (tPrimaryByMaterial == null) {
				tPrimaryByMaterial = tMaterial;
			}

			if (tPrimaryByProduct == null) {
				tPrimaryByProduct = tDust;
			}

			if (tPrimaryByProductSmall == null) {
				tPrimaryByProductSmall = tSmall;
			}

			if (tSecondaryByMaterial == null) {
				tSecondaryByMaterial = tPrimaryByMaterial;
			}

			if (tSecondaryByProduct == null) {
				tSecondaryByProduct = tPrimaryByProduct;
			}

			if (tSecondaryByProductSmall == null) {
				tSecondaryByProductSmall = tPrimaryByProductSmall;
			}
			
			RecipeEntry ingr = RecipeEntry.fromStacks(entry.ores, Match.STRICT);
			
			if (tSmeltInto != null) {
				if (!aMaterial.mBlastFurnaceRequired && !aMaterial.mDirectSmelting.mBlastFurnaceRequired) {
					GT_ModHandler.addInductionSmelterRecipe(entry.ores.get(0), new ItemStack(Blocks.sand, 1),
							GT_Utility.mul((aMultiplier * (aMaterial.contains(SubTag.INDUCTIONSMELTING_LOW_OUTPUT) ? 1 : 2) * aMaterial.mSmeltingMultiplier), tSmeltInto),
							GT_Items.TE_Slag_Rich.get(1), 300 * aMultiplier, 10 * aMultiplier);
					GT_ModHandler.addInductionSmelterRecipe(entry.ores.get(0),
							GT_Items.TE_Slag_Rich.get(aMultiplier),
							GT_Utility.mul((aMultiplier * (aMaterial.contains(SubTag.INDUCTIONSMELTING_LOW_OUTPUT) ? 2 : 3) * aMaterial.mSmeltingMultiplier), tSmeltInto),
							GT_Items.TE_Slag.get(aMultiplier), 300 * aMultiplier, 95);
				}
				
				if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_TRIPLE)) {
					RecipeMaps.BLAST_FURNACE.factory()
						.minTemperature(1500)
						.EUt(120).duration(tSmeltInto.stackSize * 500)
						.input(ingr)
						.input(OrePrefixes.dust, Materials.Calcite, aMultiplier)
						.output(GT_Utility.mul(aMultiplier * 3 * aMaterial.mSmeltingMultiplier, tSmeltInto))
						.output(GT_Items.TE_Slag.get(1L, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L)))
						.buildAndRegister();
				} else if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_DOUBLE)) {
					RecipeMaps.BLAST_FURNACE.factory()
						.minTemperature(1500)
						.EUt(120).duration(tSmeltInto.stackSize * 500)
						.input(ingr)
						.input(OrePrefixes.dust, Materials.Calcite, aMultiplier)
						.output(GT_Utility.mul(aMultiplier * 2 * aMaterial.mSmeltingMultiplier, tSmeltInto))
						.output(GT_Items.TE_Slag.get(1L, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L)))
						.buildAndRegister();
				}
			}

			if (tCrushed != null) {
				final ItemStack copy = GT_Utility.copy(tCrushed);
				if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammercrushing, aPrefix.get(aMaterial), true)) {
					RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCraftingRecipe(copy, new Object[] { "T", "O", 'T', GT_ToolDictNames.craftingToolHardHammer, 'O', aPrefix.get(aMaterial) }));
				}

				RecipeMaps.HAMMER.factory().EUt(10) .duration(16).input(ingr).output(tCrushed).buildAndRegister();
				RecipeHandler.scheduleIC2RecipeToRemove(GT_ModHandler.getMaceratorRecipeList(), (in, out) -> in.matches(entry.ores.get(0)));
				final ItemStack out1 =tMaterial.contains(SubTag.PULVERIZING_CINNABAR) ? GT_OreDictUnificator.get(OrePrefixes.crystal, Materials.Cinnabar, GT_Utility.copyAmount(1, tPrimaryByProduct), 1L) : GT_Utility.copyAmount(1, tPrimaryByProduct);
				final int val = tPrimaryByProduct == null ? 0 : tPrimaryByProduct.stackSize * 10 * aMultiplier * aMaterial.mByProductMultiplier;
				RecipeHandler.executeOnFinish(() -> GT_ModHandler.addPulverisationRecipe(entry, 1, GT_Utility.mul(2L, copy), out1, val));
				RecipeFactory<?> factory;
				if (tGem != null) {
					factory = RecipeMaps.GRINDER.factory()
						.EUt(120).duration(100)
						.input(ingr)
						.input(GT_ModHandler.getWater(1000 * aMultiplier))
						.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem))
						.output(tSmall == null
									? GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned)
									: GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 6, tSmall));
					if (tPrimaryByProductSmall != null)
						factory.output(GT_Utility.mul(aMultiplier * 2 * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
					factory.buildAndRegister();
					
					if (tMaterial.contains(SubTag.WASHING_MERCURY)) {
						if (tSmall == null) {
							factory = RecipeMaps.GRINDER.factory()
								.EUt(120).duration(100).setShaped(true)
								.input(ingr)
								.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, aMultiplier))
								.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem));
							if (tCleaned != null)
								factory.output(GT_Utility.mul(aMultiplier * 3 * aMaterial.mOreMultiplier, tCleaned));
							if (tPrimaryByProductSmall != null)
								factory.output(GT_Utility.mul(aMultiplier * 2 * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
								
							factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
						}
						
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem));
						if (tCleaned != null)
							factory.output(GT_Utility.mul(aMultiplier * 3 * aMaterial.mOreMultiplier, tDust));
						if (tPrimaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * 2 * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}


					if (tPrimaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem));
						if (tCleaned != null || tSmall != null)	
							factory.output(tSmall == null
									? GT_Utility.mul((aMultiplier * 2 * aMaterial.mOreMultiplier), tCleaned)
									: GT_Utility.mul((aMultiplier * 6 * aMaterial.mOreMultiplier), tSmall));
						if (tPrimaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * 2 * aMaterial.mByProductMultiplier, tPrimaryByProduct));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}
					
					if (tMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
						if (tSmall == null) {
							factory = RecipeMaps.GRINDER.factory()
								.EUt(120).duration(100).setShaped(true)
								.input(ingr)
								.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, aMultiplier))
								.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem));
							if (tCleaned != null)	
								factory.output(GT_Utility.mul(aMultiplier * 3 * aMaterial.mOreMultiplier, tCleaned));
							if (tPrimaryByProductSmall != null)
								factory.output(GT_Utility.mul(aMultiplier * 2 * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
							factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
						}
						
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem));
						if (tDust != null)	
							factory.output(GT_Utility.mul(aMultiplier * 3 * aMaterial.mOreMultiplier, tDust));
						if (tPrimaryByProductSmall != null)	
							factory.output(GT_Utility.mul(aMultiplier * 2 * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}

					if (tPrimaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem));
							if (tSmall != null || tCleaned != null)
								factory.output(tSmall == null
									? GT_Utility.mul((aMultiplier * 2 * aMaterial.mOreMultiplier), tCleaned)
									: GT_Utility.mul((aMultiplier * 6 * aMaterial.mOreMultiplier), tSmall));
							if (tPrimaryByProduct != null)
								factory.output(GT_Utility.mul(aMultiplier * 2 * aMaterial.mByProductMultiplier, tPrimaryByProduct));
							factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}
				} else if (tCleaned != null) {
					factory = RecipeMaps.GRINDER.factory()
						.EUt(120).duration(100)
						.input(ingr)
						.input(GT_ModHandler.getWater(1000 * aMultiplier))
						.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned));
					if (tPrimaryByProductSmall != null)
						factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
					if (tSecondaryByProductSmall != null)
						factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tSecondaryByProductSmall));
					factory.buildAndRegister();
					
					if (tMaterial.contains(SubTag.WASHING_MERCURY)) {
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 3, tCleaned));
						if (tPrimaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
						if (tSecondaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tSecondaryByProductSmall));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}

					if (tPrimaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned));
						if (tPrimaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
						if (tSecondaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tSecondaryByProductSmall));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}

					if (tSecondaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned));
						if (tPrimaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
						if (tSecondaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tSecondaryByProductSmall));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}

					if (tMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 3, tCleaned));
						if (tPrimaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
						if (tSecondaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tSecondaryByProductSmall));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}

					if (tPrimaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned));
						if (tPrimaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
						if (tSecondaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tSecondaryByProductSmall));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					} else if (tSecondaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
						factory = RecipeMaps.GRINDER.factory()
							.EUt(120).duration(100).setShaped(true)
							.input(ingr)
							.input(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, aMultiplier))
							.output(GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned));
						if (tPrimaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tPrimaryByProductSmall));
						if (tSecondaryByProductSmall != null)
							factory.output(GT_Utility.mul(aMultiplier * aMaterial.mByProductMultiplier, tSecondaryByProductSmall));
						factory.output(GT_Items.Cell_Empty.get(aMultiplier)).buildAndRegister();
					}
				}
			}

			return true;
		} else {
			return false;
		}
	}
}
