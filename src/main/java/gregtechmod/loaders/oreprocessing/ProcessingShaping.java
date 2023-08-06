package gregtechmod.loaders.oreprocessing;

import java.util.Arrays;
import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.items.GT_MetaGenerated_Item_02;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import net.minecraft.item.ItemStack;
import gregtechmod.common.recipe.RecipeEntry.Match;

public class ProcessingShaping implements IOreRecipeRegistrator {
	
	public final static List<Materials> PIPES_MED_LARGE = Arrays.asList(
			Materials.Bronze,
			Materials.Steel,
			Materials.StainlessSteel,
			Materials.TungstenSteel,
			Materials.Brass,
			Materials.Electrum,
			Materials.Platinum
	);
	
	public final static List<Materials> PIPES_SMALL = Arrays.asList(
			Materials.Bronze,
			Materials.Steel,
			Materials.StainlessSteel,
			Materials.TungstenSteel
	);
	
	public ProcessingShaping() {
		OrePrefixes.ingot.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry e : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, e);
			if (this.isExecutable(aPrefix, aMaterial) && GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L) != null && !aMaterial.contains(SubTag.NO_SMELTING)) {
				int tAmount = (int)(aPrefix.mMaterialAmount / GregTech_API.MATERIAL_UNIT);
				if(tAmount > 0 && tAmount <= 64 && aPrefix.mMaterialAmount % GregTech_API.MATERIAL_UNIT == 0L) {
					if (PIPES_MED_LARGE.contains(aMaterial)) {
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(24 * tAmount)	.input(RecipeEntry.fromStacks(3, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Pipe_Medium	.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.pipeMedium	, aMaterial, tAmount)).buildAndRegister();
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(48 * tAmount)	.input(RecipeEntry.fromStacks(6, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Pipe_Large	.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.pipeLarge	, aMaterial, tAmount)).buildAndRegister();
					}
					
					if (PIPES_SMALL.contains(aMaterial)) {
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(8 * tAmount)	.input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Pipe_Small	.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.pipeSmall	, aMaterial, tAmount)).buildAndRegister();
					}
					
					if ((aMaterial.mTypes & 64) != 0 && GT_MetaGenerated_Item_02.sTempToolHeadMaterials.contains(aMaterial)) {
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 2 * tAmount, tAmount)).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Sword	.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword	, aMaterial, tAmount)).buildAndRegister();
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 3 * tAmount, tAmount)).input(RecipeEntry.fromStacks(3, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Pickaxe .get(1)).output(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, aMaterial, tAmount)).buildAndRegister();
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 1 * tAmount, tAmount)).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Shovel	.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel	, aMaterial, tAmount)).buildAndRegister();
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 3 * tAmount, tAmount)).input(RecipeEntry.fromStacks(3, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Axe		.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe	, aMaterial, tAmount)).buildAndRegister();
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 2 * tAmount, tAmount)).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Hoe		.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe	, aMaterial, tAmount)).buildAndRegister();
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 6 * tAmount, tAmount)).input(RecipeEntry.fromStacks(6, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Hammer	.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer	, aMaterial, tAmount)).buildAndRegister();
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 2 * tAmount, tAmount)).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_File	.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.toolHeadFile	, aMaterial, tAmount)).buildAndRegister();
						RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 2 * tAmount, tAmount)).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Saw		.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw	, aMaterial, tAmount)).buildAndRegister();
					}

					ItemStack gear = GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial, tAmount);
					if ((aMaterial.mTypes & 128) != 0 && gear != null) {
						RecipeMaps.EXTRUDING.factory()
								.EUt(128)
								.setShaped(true)
								.duration(Math.max(aMaterial.getMass() * 5 * tAmount, tAmount))
								.input(RecipeEntry.fromStacks(4, e.ores, Match.STRICT))
								.nonConsumable(GT_Items.Shape_Extruder_Gear.get(1))
								.output(gear)
								.buildAndRegister();
						RecipeMaps.ALLOY_SMELTING.factory()
								.EUt(32)
								.duration(Math.max(aMaterial.getMass() * 10 * tAmount, tAmount))
								.input(RecipeEntry.fromStacks(8, e.ores, Match.STRICT))
								.nonConsumable(GT_Items.Shape_Mold_Gear.get(1))
								.output(gear)
								.buildAndRegister();
					}
					
					// wtf is this
//					RecipeMaps.EXTRUDING.factory().EUt( 80).setShaped(true).duration(10)			.input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Ingot			.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, tAmount)).buildAndRegister();
					
					if ((aMaterial.mTypes & 2) != 0) {
						ItemStack tItem = null;
						if ((tItem = GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, tAmount)) != null) {
							RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 1 * tAmount, tAmount)).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Plate	.get(1)).output(tItem).buildAndRegister();
							RecipeMaps.ALLOY_SMELTING.factory().EUt(32).duration(Math.max(aMaterial.getMass() *  2 * tAmount, tAmount)).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Mold_Plate.get(1)).output(tItem).buildAndRegister();
						}
						
						if(tAmount * 2 <= 64 && (tItem = GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 2)) != null)
							RecipeMaps.EXTRUDING.factory().EUt( 96).setShaped(true).duration(Math.max(aMaterial.getMass() * 2 * tAmount, tAmount)).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Rod  .get(1)).output(tItem).buildAndRegister();
						if (tAmount * 8 <= 64 && (tItem = GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, 8)) != null)
							RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(Math.max(aMaterial.getMass() * 2 * tAmount, tAmount)).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Bolt .get(1)).output(tItem).buildAndRegister();
						if (tAmount * 4 <= 64 && (tItem = GT_OreDictUnificator.get(OrePrefixes.ring, aMaterial, 4)) != null)
							RecipeMaps.EXTRUDING.factory().EUt( 96).setShaped(true).duration(Math.max(aMaterial.getMass() * 2 * tAmount, tAmount)).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Ring .get(1)).output(tItem).buildAndRegister();
						if (!OrePrefixes.block.isIgnored(aMaterial) && (tItem = GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1)) != null)
							RecipeMaps.EXTRUDING.factory().EUt(128).setShaped(true).duration(10 * tAmount										 ).input(RecipeEntry.fromStacks(9, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Block.get(1)).output(tItem).buildAndRegister();
						
						switch(aMaterial) {
			            case Iron:
			            	if(tAmount * 6 <= 64)
			            		RecipeMaps.EXTRUDING.factory().EUt(64).setShaped(true).duration(tAmount * 8).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Wire.get(1)).output(GT_ModHandler.getIC2Item("ironCableItem", (tAmount * 6))).buildAndRegister();
							if(tAmount * 2 <= 64) {
								RecipeMaps.EXTRUDING.factory().EUt(48).setShaped(true).duration(tAmount * 32).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Casing.get(1)).output(GT_ModHandler.getIC2Item("casingiron", (tAmount * 2))).buildAndRegister();
								RecipeMaps.ALLOY_SMELTING.factory().EUt(12).duration(tAmount * 128).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Mold_Casing.get(1)).output(GT_ModHandler.getIC2Item("casingiron", (tAmount * 3))).buildAndRegister();
							}
							RecipeMaps.EXTRUDING.factory().EUt(64).setShaped(true).duration(tAmount * 32).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Cell.get(1)).output(GT_ModHandler.getIC2Item("fuelRod", tAmount)).buildAndRegister();
							break;
			            case Tin:
			               if(tAmount * 4 <= 64)
			                  RecipeMaps.EXTRUDING.factory().EUt(64).setShaped(true).duration(tAmount * 3).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Wire.get(1)).output(GT_ModHandler.getIC2Item("tinCableItem", (tAmount * 4))).buildAndRegister();
			               if(tAmount * 2 <= 64) {
			                  RecipeMaps.EXTRUDING.factory().EUt(48).setShaped(true).duration(tAmount * 32).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Casing.get(1)).output(GT_ModHandler.getIC2Item("casingtin", (tAmount * 2))).buildAndRegister();
			                  RecipeMaps.ALLOY_SMELTING.factory().EUt(12).duration(tAmount * 128).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Mold_Casing.get(1)).output(GT_ModHandler.getIC2Item("casingtin", (tAmount * 3))).buildAndRegister();
			               }
			               
			               RecipeMaps.EXTRUDING.factory().EUt(64).setShaped(true).duration(tAmount * 32).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Cell.get(1)).output(GT_Items.Cell_Empty.get(tAmount)).buildAndRegister();
			               break;
			            case SolderingAlloy:
			               RecipeMaps.EXTRUDING.factory().EUt(64).setShaped(true).duration(tAmount * 8).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Wire.get(1)).output(GT_Items.Tool_SolderingMaterial_Tin.get(tAmount)).buildAndRegister();
			               break;
			            case Lead:
			            	RecipeMaps.EXTRUDING.factory().EUt(64).setShaped(true).duration(tAmount * 8).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Wire.get(1)).output(GT_Items.Tool_SolderingMaterial_Lead.get(tAmount)).buildAndRegister();
	
			            	if(tAmount * 2 <= 64) {
								RecipeMaps.EXTRUDING.factory().EUt(48).setShaped(true).duration(tAmount * 32).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Casing.get(1)).output(GT_ModHandler.getIC2Item("casinglead", (tAmount * 2))).buildAndRegister();
				                RecipeMaps.ALLOY_SMELTING.factory().EUt(12).duration(tAmount * 128).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Mold_Casing.get(1)).output(GT_ModHandler.getIC2Item("casinglead", (tAmount * 3))).buildAndRegister();
							}
							break;
			            case Copper:
			               if(tAmount * 3 <= 64) {
			                  RecipeMaps.EXTRUDING.factory().EUt(64).setShaped(true).duration(tAmount * 4).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Wire.get(1)).output(GT_ModHandler.getIC2Item("copperCableItem", (tAmount * 3))).buildAndRegister();
			               }
	
			               if(tAmount * 2 <= 64) {
			                  RecipeMaps.EXTRUDING.factory().EUt(48).setShaped(true).duration(tAmount * 32).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Casing.get(1)).output(GT_ModHandler.getIC2Item("casingcopper", (tAmount * 2))).buildAndRegister();
				              RecipeMaps.ALLOY_SMELTING.factory().EUt(12).duration(tAmount * 128).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Mold_Casing.get(1)).output(GT_ModHandler.getIC2Item("casingcopper", (tAmount * 3))).buildAndRegister();
			               }
			               break;
			            case Bronze:
			               if(tAmount * 2 <= 64) {
			                  RecipeMaps.EXTRUDING.factory().EUt(48).setShaped(true).duration(tAmount * 32).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Casing.get(1)).output(GT_ModHandler.getIC2Item("casingbronze", (tAmount * 2))).buildAndRegister();
				              RecipeMaps.ALLOY_SMELTING.factory().EUt(12).duration(tAmount * 128).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Mold_Casing.get(1)).output(GT_ModHandler.getIC2Item("casingbronze", (tAmount * 3))).buildAndRegister();
			               }
			               break;
			            case Gold:
			               if(tAmount * 6 <= 64) {
			                  RecipeMaps.EXTRUDING.factory().EUt(64).setShaped(true).duration(tAmount * 4).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Wire.get(1)).output(GT_ModHandler.getIC2Item("goldCableItem", (tAmount * 6))).buildAndRegister();
			               }
	
			               if(tAmount * 2 <= 64) {
			                  RecipeMaps.EXTRUDING.factory().EUt(48).setShaped(true).duration(tAmount * 32).input(RecipeEntry.fromStacks(1, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Extruder_Casing.get(1)).output(GT_ModHandler.getIC2Item("casinggold", (tAmount * 2))).buildAndRegister();
				              RecipeMaps.ALLOY_SMELTING.factory().EUt(12).duration(tAmount * 128).input(RecipeEntry.fromStacks(2, e.ores, Match.STRICT)).nonConsumable(GT_Items.Shape_Mold_Casing.get(1)).output(GT_ModHandler.getIC2Item("casinggold", (tAmount * 3))).buildAndRegister();
			               }
			            default: break;
			            }
					}
				}
			}
		}
	}
}
