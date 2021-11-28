package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.MaterialStack;
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

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ProcessingDust implements IOreRecipeRegistrator {

	public ProcessingDust() {
		OrePrefixes.dust.add(this);
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

				if (aMaterial.mAmplificationValue > 0) {
					GT_ModHandler.addIC2MatterAmplifier(aMaterial.mAmplificationValue, entry.oreDictName);
				}
				
				RecipeHandler.executeOnFinish(() -> {
					ItemStack tStack;
					
					if ((tStack = GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 4)) != null)
						GameRegistry.addRecipe(new ShapedOreRecipe(tStack, new Object[]{" X", "  ", 'X', entry.oreDictName}).setMirrored(false));
					if ((tStack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, aMaterial, 9)) != null)
						GameRegistry.addRecipe(new ShapedOreRecipe(tStack, new Object[]{"X ", "  ", 'X', entry.oreDictName}).setMirrored(false));
				});
								
				if (GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1) != null) {
					RecipeMaps.CANINNING.factory().EUt(1).duration(100)
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.input(aMaterial == Materials.Milk ? GT_Items.Cell_Water.get(1) : GT_Items.Cell_Empty.get(1))
						.output(GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1))
						.buildAndRegister();
					if (aMaterial != Materials.Milk && aMaterial != Materials.ConstructionFoam) RecipeMaps.CANINNING.factory().EUt(1).duration(100)
						.input(OrePrefixes.cell, aMaterial)
						.output(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1))
						.output(GT_OreDictUnificator.get(GT_Items.Cell_Empty.get(1)))
						.buildAndRegister();
				}
				
				if (aMaterial.mMaterialList.size() > 0 && (aMaterial.mExtraData & 3) != 0) {
					int tItemAmount = 0;
					long tCapsuleCount = 0L;
					long tDensityMultiplier = aMaterial.getDensity() > GregTech_API.MATERIAL_UNIT ? aMaterial.getDensity() / GregTech_API.MATERIAL_UNIT : 1L;
					List<ItemStack> tList = new ArrayList<>();
					for (MaterialStack tMat : aMaterial.mMaterialList) {
						if (tMat.mAmount > 0L) {
							ItemStack tStack;
							if (tMat.mMaterial == Materials.Oxygen) {
								tStack = GT_Items.Cell_Air.get(tMat.mAmount / 2);
							} else {
								tStack = GT_OreDictUnificator.get(OrePrefixes.dust, tMat.mMaterial, tMat.mAmount);
								if (tStack == null) {
									tStack = GT_OreDictUnificator.get(OrePrefixes.cell, tMat.mMaterial, tMat.mAmount);
								}
							}

							if (tItemAmount + tMat.mAmount * GregTech_API.MATERIAL_UNIT <= 64 * aMaterial.getDensity()) {
								tItemAmount += tMat.mAmount * GregTech_API.MATERIAL_UNIT;
								if (tStack != null) {
									for (tStack.stackSize = (int) (tStack.stackSize * tDensityMultiplier); tStack.stackSize > 64 && tList.size() < 4 && tCapsuleCount + (GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64) <= 64L; tStack.stackSize -= 64) {
										tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64;
										tList.add(GT_Utility.copyAmount(64, tStack ));
									}

									if (tStack.stackSize > 0 && tList.size() < 4 && tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack) <= 64L) {
										tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack);
										tList.add(tStack);
									}
								}
							}
						}
					}

					tItemAmount = (int) ((tItemAmount * tDensityMultiplier % aMaterial.getDensity() > 0L ? 1 : 0) + tItemAmount * tDensityMultiplier / aMaterial.getDensity());
					if (tList.size() > 0) {
						if ((aMaterial.mExtraData & 1) != 0) {
							factory = RecipeMaps.ELECTROLYZER.factory()
								.setShaped(true)
								.EUt(Math.min(4, tList.size()) * 30)
								.duration(Math.max(1, Math.abs(aMaterial.getProtons() * 2 * tItemAmount)))
								.input(RecipeEntry.fromStacks(tItemAmount, entry.ores, Match.STRICT));
							if (tCapsuleCount > 0) 
								factory.input(GT_Items.Cell_Empty.get(tCapsuleCount));
							factory.outputs(tList.toArray(new ItemStack[0])).buildAndRegister();
						}

						if ((aMaterial.mExtraData & 2) != 0) {
							factory = RecipeMaps.CENTRIFUGE.factory()
								.setShaped(true).EUt(5)
								.duration(Math.max(1, Math.abs(aMaterial.getMass() * 8 * tItemAmount)))
								.input(RecipeEntry.fromStacks(tItemAmount, entry.ores, Match.STRICT));
							if (tCapsuleCount > 0) 
								factory.input(GT_Items.Cell_Empty.get(tCapsuleCount));
							factory.outputs(tList.toArray(new ItemStack[0])).buildAndRegister();
						}
					}
				}
				
				switch (aMaterial) {
				case _NULL:
				case Empty:
					continue;
				default:
					break;
				case Milk:
					RecipeMaps.CANINNING.factory().EUt(1).duration(100)
						.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT))
						.input(new ItemStack(Items.water_bucket, 1))
						.output(new ItemStack(Items.milk_bucket, 1))
						.buildAndRegister();
					break;
				case Mercury:
					GT_Log.log.error("Quicksilver Dust?, To melt that, you don't even need a Furnace...");
					break;
				case Diamond:
					RecipeMaps.IMPLOSION_COMPRESSOR.factory().EUt(30).duration(20)
						.input(RecipeEntry.fromStacks(4, entry.ores, Match.STRICT))
						.input(GT_ModHandler.getIC2Item("industrialTnt", 32))
						.output(GT_Items.IC2_Industrial_Diamond.get(3))
						.output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 16L))
						.buildAndRegister();
					break;
				case Opal:
				case Olivine:
				case Emerald:
				case Ruby:
				case Sapphire:
				case GreenSapphire:
				case Topaz:
				case BlueTopaz:
				case Tanzanite:
					RecipeMaps.IMPLOSION_COMPRESSOR.factory().EUt(30).duration(20)
						.input(RecipeEntry.fromStacks(4, entry.ores, Match.STRICT))
						.input(GT_ModHandler.getIC2Item("industrialTnt", 24))
						.output(GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 3L))
						.output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 12L))
						.buildAndRegister();
					break;
				case GarnetRed:
				case GarnetYellow:
				case Jasper:
				case Amber:
				case Monazite:
				case Forcicium:
				case Forcillium:
				case Force:
					RecipeMaps.IMPLOSION_COMPRESSOR.factory().EUt(30).duration(20)
						.input(RecipeEntry.fromStacks(4, entry.ores, Match.STRICT))
						.input(GT_ModHandler.getIC2Item("industrialTnt", 16))
						.output(GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 3L))
						.output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 8L))
						.buildAndRegister();
				}
				
				ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L);
				
				if (!aMaterial.contains(SubTag.NO_SMELTING) && tStack != null) {
					if (aMaterial.mBlastFurnaceRequired && null != tStack) {
						factory = RecipeMaps.BLAST_FURNANCE.factory()
							.minTemperature(aMaterial.mBlastFurnaceTemp).EUt(120)
							.duration(Math.max(aMaterial.getMass() / 40, 1) * aMaterial.mBlastFurnaceTemp)
							.input(RecipeEntry.fromStacks(entry.ores, Match.STRICT));
						if (aMaterial.mBlastFurnaceTemp > 1750)
							factory.output(GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial, tStack, 1));
						else
							factory.output(tStack);
						factory.buildAndRegister();
					}
				} else {
					if (!OrePrefixes.block.isIgnored(aMaterial) && null == GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L)) {
						GT_RecipeRegistrator.registerBlockForcibly(aPrefix, aMaterial, entry.ores);
						RecipeHandler.scheduleIC2RecipeToRemove(GT_ModHandler.getCompressorRecipeList(), (in, out) -> in.matches(entry.ores.get(0)));
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCompressionRecipe(entry, 9, GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L)));
						for (ItemStack a : entry.ores)
							RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, a, a, a, a, a, a, a, a, a));
					}
					
					if ((OrePrefixes.block.isIgnored(aMaterial)
							|| null == GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L))
							&& aMaterial != Materials.GraniteRed && aMaterial != Materials.GraniteBlack
							&& aMaterial != Materials.Obsidian && aMaterial != Materials.Glowstone
							&& aMaterial != Materials.Paper) {
						RecipeHandler.scheduleIC2RecipeToRemove(GT_ModHandler.getCompressorRecipeList(), (in, out) -> in.matches(GT_Utility.copyAmount(9, entry.ores.get(0))));
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCompressionRecipe(entry, 1, GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L)));
					}
				}
				
				
				for (ItemStack aStack : entry.ores) {
					if (null != tStack && !aMaterial.contains(SubTag.NO_SMELTING)) {
						if (aMaterial.mBlastFurnaceRequired) {
							RecipeHandler.scheduleSmeltingToRemove((in, out) -> in.isItemEqual(aStack));
							if (aMaterial.mBlastFurnaceTemp <= 1000)
								GT_ModHandler.addRCBlastFurnaceRecipe(GT_Utility.copyAmount(1, aStack), GT_Utility.copyAmount(1, tStack), aMaterial.mBlastFurnaceTemp);
						} else {
							RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(aStack, tStack));
						}
					}

					switch (aMaterial) {
					default:
						break;
					case Wheat:
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1, aStack), new ItemStack(Items.bread, 1, 0)));
						break;
					case Tetrahedrite:
					case Chalcopyrite:
					case Malachite:
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1, aStack),
								GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Copper, 6L)));
						break;
					case Pentlandite:
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1, aStack),
								GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Nickel, 6L)));
						break;
					case Garnierite:
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1, aStack),
								GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Nickel, 1L)));
						break;
					case Cassiterite:
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1, aStack),
								GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L)));
						break;
					case Coal:
						GT_ModHandler.addLiquidTransposerFillRecipe(GT_Utility.copyAmount(1, aStack),
								GT_ModHandler.getWater(125L),
								GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal, 1L), 125);
						break;
					case HydratedCoal:
						GT_ModHandler.addLiquidTransposerEmptyRecipe(GT_Utility.copyAmount(1, aStack),
								GT_ModHandler.getWater(125L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L),
								125);
						break;
					}
				}
			}
		}
	}
}
