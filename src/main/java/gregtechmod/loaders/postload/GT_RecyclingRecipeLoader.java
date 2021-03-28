package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_RecyclingRecipeLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("Adding all the Reverse Recipes for the Furnace/Macerator/Sawmill.");
        
        GT_Log.log.info("Adding all the Reverse Recipes for the Furnace/Macerator/Sawmill.");
        if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.storageblockcrafting, "tile.glowstone", false)) {
           GT_ModHandler.removeRecipe(new ItemStack[]{new ItemStack(Items.glowstone_dust, 1), new ItemStack(Items.glowstone_dust, 1), null, new ItemStack(Items.glowstone_dust, 1), new ItemStack(Items.glowstone_dust, 1)});
        }

        GT_ModHandler.addCompressionRecipe(new ItemStack(Items.glowstone_dust, 4, 0), new ItemStack(Blocks.glowstone, 1));
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.book, 1)								, Materials.Paper			, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.written_book, 1)						, Materials.Paper			, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.writable_book, 1)					, Materials.Paper			, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(GregTech_API.sBlockList[4], 1, 8)			, Materials.TungstenSteel	, GregTech_API.MATERIAL_UNIT	);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(GregTech_API.sBlockList[0], 1, 2)			, Materials.Iridium			, GregTech_API.MATERIAL_UNIT	);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(GregTech_API.sBlockList[4], 1, 9)			, Materials.Iridium			, GregTech_API.MATERIAL_UNIT	);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(GregTech_API.sBlockList[4], 1, 13)			, Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.minecart, 1)							, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 5);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.bucket, 1)							, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.iron_door, 1)						, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.cauldron, 1)							, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 7);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Blocks.iron_bars, 8)						, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Blocks.light_weighted_pressure_plate, 1)	, Materials.Gold			, GregTech_API.MATERIAL_UNIT * 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1)	, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Blocks.anvil, 1, 0)						, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 30);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Blocks.anvil, 1, 1)						, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 20);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Blocks.anvil, 1, 2)						, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 10);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Blocks.hopper, 1)							, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 5);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.chainmail_helmet, 4)					, Materials.Steel			, GregTech_API.MATERIAL_UNIT * 5);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.chainmail_chestplate, 4)				, Materials.Steel			, GregTech_API.MATERIAL_UNIT * 8);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.chainmail_leggings, 4)				, Materials.Steel			, GregTech_API.MATERIAL_UNIT * 7);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(Items.chainmail_boots, 4)					, Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_ModHandler.getIC2Item("ironFurnace", 1)				, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 5);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_ModHandler.getIC2Item("ironFence", 1)					, Materials.Iron			, GregTech_API.MATERIAL_UNIT / 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_ModHandler.getIC2Item("machine", 1)					, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 8);
        
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_ModHandler.getRecipeOutput(new ItemStack[] {
        		GT_OreDictUnificator.get(OrePrefixes.block, Materials.Steel, 1),
        		GT_OreDictUnificator.get(OrePrefixes.block, Materials.Steel, 1),
        		GT_OreDictUnificator.get(OrePrefixes.block, Materials.Steel, 1),
        		null,
        		GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1),
        		null,
        		GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1),
        		GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1),
        		GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1)})												, Materials.Steel			, GregTech_API.MATERIAL_UNIT * 30);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_ModHandler.getRecipeOutput(new ItemStack[] {
        		null,
        		GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1),
        		null,
        		GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1),
        		null,
        		GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1),
        		null,
        		null,
        		null})																											, Materials.Tin				, GregTech_API.MATERIAL_UNIT * 3);

        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(32, 1)					, Materials.Aluminium		, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(33, 1)					, Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(34, 1)					, Materials.Brass			, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(35, 1)					, Materials.Steel			, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(36, 1)					, Materials.Titanium		, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(37, 1)					, Materials.Iron			, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(38, 1)					, Materials.TungstenSteel	, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(39, 1)					, Materials.StainlessSteel	, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(51, 1)					, Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(52, 1)					, Materials.Steel			, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(53, 1)					, Materials.Magnalium		, GregTech_API.MATERIAL_UNIT * 3);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GregTech_API.getGregTechComponent(54, 1)					, Materials.TungstenSteel	, GregTech_API.MATERIAL_UNIT * 3);
        
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Coin_Chocolate				.get(1), Materials.Gold				, GregTech_API.MATERIAL_UNIT / 9);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Coin_Gold_Ancient			.get(1), Materials.Gold				, GregTech_API.MATERIAL_UNIT / 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Coin_Doge					.get(1), Materials.Brass			, GregTech_API.MATERIAL_UNIT / 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Credit_Greg_Cupronickel		.get(1), Materials.Cupronickel		, GregTech_API.MATERIAL_UNIT / 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.IC2_Food_Can_Empty			.get(1), Materials.Tin				, GregTech_API.MATERIAL_UNIT / 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.IC2_Fuel_Rod_Empty			.get(1), Materials.Iron				, GregTech_API.MATERIAL_UNIT	);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Fuel_Can_Plastic_Empty		.get(1), Materials.Plastic			, GregTech_API.MATERIAL_UNIT	);
        
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Battery_Hull_LV				.get(1), Materials.BatteryAlloy		, GregTech_API.MATERIAL_UNIT * 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Battery_Hull_MV				.get(1), Materials.BatteryAlloy		, GregTech_API.MATERIAL_UNIT * 6);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Empty					.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Mold_Gear				.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4, false);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Mold_Plate			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4, false);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Mold_Casing			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4, false);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Mold_Credit			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4, false);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Plate		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Rod			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Bolt			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Ring			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Cell			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Ingot		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Wire			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Casing		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Block		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Pipe_Small	.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Pipe_Medium	.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Pipe_Large	.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Gear			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Sword		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Shovel		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Hoe			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Axe			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Hammer		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Pickaxe		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_File			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Shape_Extruder_Saw			.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_Boiler		.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 5);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_Furnace		.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_AlloySmelter	.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 11);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_Hammer		.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 12);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_CraftingTable.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 7);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_Macerator	.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 12);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_Compressor	.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 8);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_Extractor	.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 5);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Bronze_BlastFurnace	.get(1), Materials.Bronze			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Steel_Boiler		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 5);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Machine_Steel_Furnace		.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 4);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Frame_Iron					.get(1), Materials.Iron				, GregTech_API.MATERIAL_UNIT * 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Frame_Aluminium				.get(1), Materials.Aluminium		, GregTech_API.MATERIAL_UNIT * 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Frame_Steel					.get(1), Materials.Steel			, GregTech_API.MATERIAL_UNIT * 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Frame_StainlessSteel		.get(1), Materials.StainlessSteel	, GregTech_API.MATERIAL_UNIT * 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Frame_TungstenSteel			.get(1), Materials.TungstenSteel	, GregTech_API.MATERIAL_UNIT * 2);
        GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Items.Cell_Empty					.get(1), Materials.Tin				, GregTech_API.MATERIAL_UNIT * 2);
        
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Items.clock, 1)				, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 4));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Items.compass, 1)				, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 4));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Items.iron_horse_armor, 1)	, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 8));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Items.golden_horse_armor, 1)	, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 8));
        
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.iron_horse_armor, 1)		, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 8), new ItemStack(Items.leather, 6), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.golden_horse_armor, 1)		, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold, 8), new ItemStack(Items.leather, 6), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.diamond_horse_armor, 1)	, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Diamond, 8), new ItemStack(Items.leather, 6), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.clock, 1)					, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold, 4), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.compass, 1)				, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 4), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.bookshelf, 1)				, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Paper, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 6), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.enchanting_table, 1)		, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Diamond, 2), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 4), 95, false);

        ItemStack reds = new ItemStack(Items.redstone, 1);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.sign, 1, 0)				, GT_OreDictUnificator.get(OrePrefixes.dust			, Materials.Wood, 2), null,  0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.wooden_door, 1, 0)			, GT_OreDictUnificator.get(OrePrefixes.dust			, Materials.Wood, 6), null,  0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.chest, 1, 0)				, GT_OreDictUnificator.get(OrePrefixes.dust			, Materials.Wood, 8), null,  0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.wooden_button, 1, 0)		, GT_OreDictUnificator.get(OrePrefixes.dust			, Materials.Wood, 1), null,  0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.wooden_pressure_plate, 1)	, GT_OreDictUnificator.get(OrePrefixes.dust			, Materials.Wood, 2), null,  0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.redstone_torch, 1)		, GT_OreDictUnificator.get(OrePrefixes.dustSmall	, Materials.Wood, 2), reds, 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.ladder, 1, 0)				, GT_OreDictUnificator.get(OrePrefixes.dust			, Materials.Wood, 1), null,  0, false);
        
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.stone_pressure_plate, 1)	, new ItemStack(Blocks.sand, 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.furnace, 1)				, new ItemStack(Blocks.sand, 6), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.stone_button, 1)			, new ItemStack(Blocks.sand, 1), null, 0, false);
        
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.lever, 1)					, new ItemStack(Blocks.sand, 1)		, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.item_frame, 1, 0)			, new ItemStack(Items.leather, 1)	, GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Wood, 4), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.bow, 1, 0)					, new ItemStack(Items.string, 3)	, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 3), 95, false);
    }
}