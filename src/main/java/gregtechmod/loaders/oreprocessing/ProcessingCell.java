package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.MaterialStack;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class ProcessingCell implements IOreRecipeRegistrator {

	public ProcessingCell() {
		OrePrefixes.cell.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				if (aMaterial != Materials.Empty) {
					RecipeFactory<?> factory;
					if (aMaterial.mMaterialList.size() > 0 && (aMaterial.mExtraData & 3) != 0) {
						int totalMaterialAmount = 0;
						for (MaterialStack tMat : aMaterial.mMaterialList)
							totalMaterialAmount += tMat.mAmount;
						int tItemAmount1 = 0;
						int capsuleCount = -totalMaterialAmount;
						int tDensityMultiplier = (int) (aMaterial.getDensity() > GregTech_API.MATERIAL_UNIT ? aMaterial.getDensity() / GregTech_API.MATERIAL_UNIT : 1L);
						List<ItemStack> tList = new ArrayList<>();
						
						for (MaterialStack tMat1 : aMaterial.mMaterialList) {
							if (tMat1.mAmount > 0L) {
								ItemStack tStack;
								if (tMat1.mMaterial == Materials.Oxygen) {
									tStack = GT_Items.Cell_Air.get(tMat1.mAmount * tDensityMultiplier / 2L);
								} else {
									tStack = GT_OreDictUnificator.get(OrePrefixes.dust, tMat1.mMaterial, tMat1.mAmount);
									if (tStack == null) {
										tStack = GT_OreDictUnificator.get(OrePrefixes.cell, tMat1.mMaterial, tMat1.mAmount);
									}
								}

								if (tItemAmount1 + (tMat1.mAmount * GregTech_API.MATERIAL_UNIT) <= 64 * aMaterial.getDensity()) {
									tItemAmount1 += tMat1.mAmount * GregTech_API.MATERIAL_UNIT;
									if (tStack != null) {
										for (tStack.stackSize *= tDensityMultiplier; tStack.stackSize > 64; tStack.stackSize -= 64) {
											if (capsuleCount + GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64 < 0) {
												if (tList.size() >= 3) {
													break;
												}
											} else if (tList.size() >= 4) {
												break;
											}

											if (capsuleCount + (GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64) > 64) {
												break;
											}

											capsuleCount += GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64;
											tList.add(GT_Utility.copyAmount(64, tStack));
										}

										if (tStack.stackSize > 0 && capsuleCount + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack) <= 64) {
											if (capsuleCount + GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack) < 0) {
												if (tList.size() >= 3) {
													continue;
												}
											} else if (tList.size() >= 4) {
												continue;
											}

											capsuleCount += (long) GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack);
											tList.add(tStack);
										}
									}
								}
							}
						}

						tItemAmount1 = (int) ((tItemAmount1 * tDensityMultiplier % aMaterial.getDensity() > 0L ? 1 : 0) + tItemAmount1 * tDensityMultiplier / aMaterial.getDensity());
						if (tList.size() > 0) {
							if ((aMaterial.mExtraData & 1) != 0) {
								factory = RecipeMaps.ELECTROLYZER.factory()
									.setShaped(true)
									.EUt(Math.min(4, tList.size()) * 30)
									.duration(Math.max(1, Math.abs(aMaterial.getProtons() * 8 * tItemAmount1)))
									.input(RecipeEntry.fromStacks(tItemAmount1, entry.ores, Match.DAMAGE, Match.NBT));
								factory.outputs(tList.toArray(new ItemStack[0]));
								if (capsuleCount > 0)
									factory.input(GT_Items.Cell_Empty.get(capsuleCount));
								else if (capsuleCount != 0)
									factory.output(GT_Items.Cell_Empty.get(-capsuleCount));
								factory.buildAndRegister();
							}

							if ((aMaterial.mExtraData & 2) != 0) {
								factory = RecipeMaps.CENTRIFUGE.factory()
										.setShaped(true)
										.EUt(5)
										.duration(Math.max(1, Math.abs(aMaterial.getMass() * 2 * tItemAmount1)))
										.input(RecipeEntry.fromStacks(tItemAmount1, entry.ores, Match.DAMAGE, Match.NBT));
								factory.outputs(tList.toArray(new ItemStack[0]));
								if (capsuleCount > 0)
									factory.input(GT_Items.Cell_Empty.get(capsuleCount));
								else if (capsuleCount != 0)
									factory.output(GT_Items.Cell_Empty.get(-capsuleCount));
								factory.buildAndRegister();
							}
						}
					}
					
					for (ItemStack aStack : entry.ores) {
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
							
							
							FluidStack fuel = GT_Utility.getFluidForFilledItem(aStack);
							if (fuel != null) {
								factory.input(new FluidStack(fuel, 1)).duration(aMaterial.mFuelPower);
							} else {
								ItemStack container = GT_Utility.getContainerItem(aStack);
								factory.input(aStack).duration(aMaterial.mFuelPower * 1000);
								if (container != null) factory.output(container);
							}
							
							factory.EUt(1).buildAndRegister();
						}
	
						if (aMaterial == Materials.Ice) {
							GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1L));
						}
					}
				}
			}
		}
	}
}
