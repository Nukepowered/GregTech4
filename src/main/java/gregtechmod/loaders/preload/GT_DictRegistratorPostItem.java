package gregtechmod.loaders.preload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Dust;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_DictRegistratorPostItem implements Runnable {
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		GT_Log.log.info("GT_Mod: Registering other Stuff to the OreDict.");
		GT_OreDictUnificator.set(OrePrefixes.plate, Materials.Iron					, GT_ModHandler.getRCItem("part.plate.iron", 1L));
		GT_OreDictUnificator.set(OrePrefixes.plate, Materials.Steel					, GT_ModHandler.getRCItem("part.plate.steel", 1L));
	    GT_OreDictUnificator.set(OrePrefixes.circuit, Materials.Basic				, GT_ModHandler.getIC2Item("electronicCircuit", 1L));
	    GT_OreDictUnificator.set(OrePrefixes.circuit, Materials.Advanced			, GT_ModHandler.getIC2Item("advancedCircuit", 1L));
	    GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Lithium		, GT_Items.Energy_Lithium.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Lithium		, GT_Items.Energy_Lithium_Empty.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Ultimate	, GT_Items.Tool_DataOrb.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Basic		, GT_Items.Circuit_Integrated.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Basic		, GT_ModHandler.getIC2Item("reBattery", 1L));
	    GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Basic		, GT_ModHandler.getIC2Item("chargedReBattery", 1L, 32767));
	    GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Advanced	, GT_ModHandler.getIC2Item("advBattery", 1L, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting60kEUPack			, GT_ModHandler.getIC2Item("batPack", 1L, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting300kEUPack			, GT_ModHandler.getIC2Item("lapPack", 1L, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting600kEUPack			, GT_Items.Armor_LithiumPack.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Elite		, GT_ModHandler.getIC2Item("energyCrystal", 1L, 32767));
	    GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Master		, GT_ModHandler.getIC2Item("lapotronCrystal", 1L, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting100kkEUPack		, GT_Items.Armor_LapotronicPack.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(OrePrefixes.battery, Materials.Ultimate	, new ItemStack(GregTech_API.sItemList[37], 1, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting10kCoolantStore	, GT_ModHandler.getIC2Item("reactorCoolantSimple", 1L, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting30kCoolantStore	, GT_ModHandler.getIC2Item("reactorCoolantTriple", 1L, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting60kCoolantStore	, GT_ModHandler.getIC2Item("reactorCoolantSix", 1L, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting60kCoolantStore	, GT_Items.Reactor_Coolant_He_1.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting60kCoolantStore	, GT_Items.Reactor_Coolant_NaK_1.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting180kCoolantStore	, GT_Items.Reactor_Coolant_He_3.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting180kCoolantStore	, GT_Items.Reactor_Coolant_NaK_3.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting360kCoolantStore	, GT_Items.Reactor_Coolant_He_6.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.crafting360kCoolantStore	, GT_Items.Reactor_Coolant_NaK_6.getWildcard(1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireCopper			, GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireGold			, GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireIron			, GT_ModHandler.getIC2Item("insulatedIronCableItem", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireTin			, GT_ModHandler.getIC2Item("insulatedTinCableItem", 1L, GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1L)));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier01	, GT_ModHandler.getIC2Item("machine", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier01	, new ItemStack(GregTech_API.sBlockList[1], 1, 79));
	    if(!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes	, "NotUseAdvMachineBlockInGT", true)) {
	       GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier02, GT_ModHandler.getIC2Item("advancedMachine", 1L));
	    }

	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier04	, new ItemStack(GregTech_API.sBlockList[0], 1, 10));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRedstoneTorch		, new ItemStack(Blocks.unlit_redstone_torch, 1, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRedstoneTorch		, new ItemStack(Blocks.redstone_torch, 1, 32767));
	    GT_OreDictUnificator.registerOre(OrePrefixes.computer, Materials.Basic		, new ItemStack(GregTech_API.sBlockList[1], 1, 69));
	    GT_OreDictUnificator.registerOre(OrePrefixes.computer, Materials.Master		, new ItemStack(GregTech_API.sBlockList[1], 1, 4));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWorkBench			, new ItemStack(Blocks.crafting_table, 1));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWorkBench			, new ItemStack(GregTech_API.sBlockList[1], 1, 16));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPiston				, new ItemStack(Blocks.piston, 1, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPiston				, new ItemStack(Blocks.sticky_piston, 1, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingSafe				, new ItemStack(GregTech_API.sBlockList[1], 1, 45));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingSafe				, GT_ModHandler.getIC2Item("personalSafe", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingChest				, new ItemStack(Blocks.chest, 1, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingChest				, new ItemStack(Blocks.trapped_chest, 1, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingFurnace			, new ItemStack(Blocks.furnace, 1, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingFurnace			, new ItemStack(Blocks.lit_furnace, 1, 32767));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPump				, GT_ModHandler.getIC2Item("pump", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingElectromagnet		, GT_ModHandler.getIC2Item("magnetizer", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingTeleporter			, GT_ModHandler.getIC2Item("teleporter", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingMacerator			, GT_ModHandler.getIC2Item("macerator", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingMacerator			, new ItemStack(GregTech_API.sBlockList[1], 1, 50));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingExtractor			, GT_ModHandler.getIC2Item("extractor", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingExtractor			, new ItemStack(GregTech_API.sBlockList[1], 1, 51));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingCompressor			, GT_ModHandler.getIC2Item("compressor", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingCompressor			, new ItemStack(GregTech_API.sBlockList[1], 1, 52));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRecycler			, GT_ModHandler.getIC2Item("recycler", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRecycler			, new ItemStack(GregTech_API.sBlockList[1], 1, 53));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingIronFurnace		, GT_ModHandler.getIC2Item("ironFurnace", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingCentrifuge			, new ItemStack(GregTech_API.sBlockList[1], 1, 62));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingInductionFurnace	, GT_ModHandler.getIC2Item("inductionFurnace", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingElectricFurnace	, GT_ModHandler.getIC2Item("electroFurnace", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingElectricFurnace	, new ItemStack(GregTech_API.sBlockList[1], 1, 54));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingGenerator			, GT_ModHandler.getIC2Item("generator", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingGeothermalGenerator, GT_ModHandler.getIC2Item("geothermalGenerator", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingSolarPanel			, GT_ModHandler.getIC2Item("solarPanel", 1L));
	    GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingMVTUpgrade			, GT_ModHandler.getIC2Item("transformerUpgrade", 1L));
	    
	    GT_OreDictUnificator.registerOre("paperEmpty"					, new ItemStack(Items.paper, 1));
	    GT_OreDictUnificator.registerOre("paperMap"						, new ItemStack(Items.map, 1));
	    GT_OreDictUnificator.registerOre("paperMap"						, new ItemStack(Items.map, 1));
	    GT_OreDictUnificator.registerOre("bookEmpty"					, new ItemStack(Items.book, 1));
	    GT_OreDictUnificator.registerOre("bookWritable"					, new ItemStack(Items.writable_book, 1));
	    GT_OreDictUnificator.registerOre("bookWritten"					, new ItemStack(Items.written_book, 1));
	    GT_OreDictUnificator.registerOre("bookEnchanted"				, new ItemStack(Items.enchanted_book, 1));
		
		
		
		
        GT_Log.log.info("GT_Mod: Registering GT/IC2-Circuitry and similar to the OreDict.");
    	GT_OreDictUnificator.registerOre("craftingLiBattery"			, GregTech_API.getGregTechItem(56, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingLiBattery"			, GregTech_API.getGregTechItem(57, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingCircuitTier08"		, GregTech_API.getGregTechItem(43, 1, 0));
    	
    	GT_OreDictUnificator.registerOre("crafting10kEUStore"			, GT_ModHandler.getIC2Item("reBattery", 1));
    	GT_OreDictUnificator.registerOre("crafting10kEUStore"			, GT_ModHandler.getIC2Item("chargedReBattery", 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("crafting60kEUPack"			, GT_ModHandler.getIC2Item("batPack", 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("crafting100kEUStore"			, GregTech_API.getGregTechItem(56, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("crafting100kEUStore"			, GregTech_API.getGregTechItem(57, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("crafting300kEUPack"			, GT_ModHandler.getIC2Item("lapPack", 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("crafting600kEUPack"			, GregTech_API.getGregTechItem(58, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("crafting1kkEUStore"			, GT_ModHandler.getIC2Item("energyCrystal", 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("crafting10kkEUStore"			, GT_ModHandler.getIC2Item("lapotronCrystal", 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting100kkEUStore"			, GregTech_API.getGregTechItem(37, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting100kkEUPack"			, GregTech_API.getGregTechItem(45, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		
		GT_OreDictUnificator.registerOre("crafting10kCoolantStore"		, GT_ModHandler.getIC2Item("reactorCoolantSimple", 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting30kCoolantStore"		, GT_ModHandler.getIC2Item("reactorCoolantTriple", 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting60kCoolantStore"		, GT_ModHandler.getIC2Item("reactorCoolantSix", 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting60kCoolantStore"		, GregTech_API.getGregTechItem(60, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting60kCoolantStore"		, GregTech_API.getGregTechItem(34, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting180kCoolantStore"		, GregTech_API.getGregTechItem(61, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting180kCoolantStore"		, GregTech_API.getGregTechItem(35, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting360kCoolantStore"		, GregTech_API.getGregTechItem(62, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("crafting360kCoolantStore"		, GregTech_API.getGregTechItem(36, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		
		GT_OreDictUnificator.registerOre("craftingWireCopper"			, GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1));
		GT_OreDictUnificator.registerOre("craftingWireGold"				, GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1));
		GT_OreDictUnificator.registerOre("craftingWireIron"				, GT_ModHandler.getIC2Item("insulatedIronCableItem", 1));
		GT_OreDictUnificator.registerOre("craftingWireTin"				, GT_ModHandler.getIC2Item("insulatedTinCableItem", 1, GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1)));
		
    	GT_OreDictUnificator.registerOre("craftingRawMachineTier01"		, GT_ModHandler.getIC2Item("machine", 1));
    	GT_OreDictUnificator.registerOre("craftingRawMachineTier01"		, new ItemStack(GregTech_API.sBlockList[1], 1, 79));
//		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "NotUseAdvMachineBlockInGT", true))
		GT_OreDictUnificator.registerOre("craftingRawMachineTier02"		, GT_ModHandler.getIC2Item("advancedMachine", 1));
    	GT_OreDictUnificator.registerOre("craftingRawMachineTier04"		, new ItemStack(GregTech_API.sBlockList[0], 1, 10));
    	
    	GT_OreDictUnificator.registerOre("craftingRedstoneTorch"		, new ItemStack(Blocks.unlit_redstone_torch, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingRedstoneTorch"		, new ItemStack(Blocks.redstone_torch, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	
    	GT_OreDictUnificator.registerOre("craftingCircuitTier00"		, new ItemStack(Blocks.unlit_redstone_torch, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingCircuitTier00"		, new ItemStack(Blocks.redstone_torch, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingCircuitTier00"		, new ItemStack(Blocks.lever, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	
    	GT_OreDictUnificator.registerOre("craftingCircuitTier03"		, new ItemStack(GregTech_API.sBlockList[1], 1,69));
    	
    	GT_OreDictUnificator.registerOre("craftingCircuitTier02"		, GT_ModHandler.getIC2Item("electronicCircuit", 1));
    	GT_OreDictUnificator.registerOre("craftingCircuitTier04"		, GT_ModHandler.getIC2Item("advancedCircuit", 1));
    	GT_OreDictUnificator.registerOre("craftingCircuitTier10"		, new ItemStack(GregTech_API.sBlockList[1], 1, 4));
    	
    	GT_OreDictUnificator.registerOre("craftingWorkBench"			, new ItemStack(Blocks.crafting_table, 1));
    	GT_OreDictUnificator.registerOre("craftingWorkBench"			, new ItemStack(GregTech_API.sBlockList[1], 1, 16));

    	GT_OreDictUnificator.registerOre("craftingPiston"				, new ItemStack(Blocks.piston, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingPiston"				, new ItemStack(Blocks.sticky_piston, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	
    	GT_OreDictUnificator.registerOre("craftingChest"				, new ItemStack(GregTech_API.sBlockList[1], 1, 45));
    	GT_OreDictUnificator.registerOre("craftingChest"				, new ItemStack(Blocks.chest, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingChest"				, new ItemStack(Blocks.trapped_chest, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingChest"				, GT_ModHandler.getIC2Item("personalSafe", 1));
    	
    	GT_OreDictUnificator.registerOre("craftingFurnace"				, new ItemStack(Blocks.furnace, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	GT_OreDictUnificator.registerOre("craftingFurnace"				, new ItemStack(Blocks.lit_furnace, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
    	
    	GT_OreDictUnificator.registerOre("craftingPump"					, GT_ModHandler.getIC2Item("pump", 1));
    	GT_OreDictUnificator.registerOre("craftingElectromagnet"		, GT_ModHandler.getIC2Item("magnetizer", 1));
    	GT_OreDictUnificator.registerOre("craftingTeleporter"			, GT_ModHandler.getIC2Item("teleporter", 1));
    	
    	GT_OreDictUnificator.registerOre("craftingMacerator"			, GT_ModHandler.getIC2Item("macerator", 1));
    	GT_OreDictUnificator.registerOre("craftingMacerator"			, new ItemStack(GregTech_API.sBlockList[1], 1,50));
    	GT_OreDictUnificator.registerOre("craftingMacerator"			, GT_ModHandler.getTEItem("pulverizer", 1));
    	
    	GT_OreDictUnificator.registerOre("craftingExtractor"			, GT_ModHandler.getIC2Item("extractor", 1));
    	GT_OreDictUnificator.registerOre("craftingExtractor"			, new ItemStack(GregTech_API.sBlockList[1], 1,51));
    	
    	GT_OreDictUnificator.registerOre("craftingCompressor"			, GT_ModHandler.getIC2Item("compressor", 1));
    	GT_OreDictUnificator.registerOre("craftingCompressor"			, new ItemStack(GregTech_API.sBlockList[1], 1,52));
    	
    	GT_OreDictUnificator.registerOre("craftingRecycler"				, GT_ModHandler.getIC2Item("recycler", 1));
    	GT_OreDictUnificator.registerOre("craftingRecycler"				, new ItemStack(GregTech_API.sBlockList[1], 1,53));
    	
    	GT_OreDictUnificator.registerOre("craftingIronFurnace"			, GT_ModHandler.getIC2Item("ironFurnace", 1));
    	
    	GT_OreDictUnificator.registerOre("craftingCentrifuge"			, new ItemStack(GregTech_API.sBlockList[1], 1,62));
    	
    	GT_OreDictUnificator.registerOre("craftingInductionFurnace"		, GT_ModHandler.getIC2Item("inductionFurnace", 1));
    	GT_OreDictUnificator.registerOre("craftingInductionFurnace"		, GT_ModHandler.getTEItem("smelter", 1));
    	
    	GT_OreDictUnificator.registerOre("craftingElectricFurnace"		, GT_ModHandler.getIC2Item("electroFurnace", 1));
    	GT_OreDictUnificator.registerOre("craftingElectricFurnace"		, new ItemStack(GregTech_API.sBlockList[1], 1,54));

    	GT_OreDictUnificator.registerOre("craftingGenerator"			, GT_ModHandler.getIC2Item("generator", 1));
    	
    	GT_OreDictUnificator.registerOre("craftingGeothermalGenerator"	, GT_ModHandler.getIC2Item("geothermalGenerator", 1));
    	
    	GT_OreDictUnificator.registerOre("craftingSolarPanel"			, GT_ModHandler.getIC2Item("solarPanel", 1));
    	
    	GT_OreDictUnificator.registerOre("craftingMVTUpgrade"			, GT_ModHandler.getIC2Item("transformerUpgrade", 1));
    	
		GT_OreDictUnificator.registerOre("molecule_1n"			, GregTech_API.getGregTechItem(91, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		
    	GT_OreDictUnificator.registerOre("paperEmpty"			, new ItemStack(Items.paper, 1));
    	GT_OreDictUnificator.registerOre("paperMap"				, new ItemStack(Items.map, 1));
    	GT_OreDictUnificator.registerOre("paperMap"				, new ItemStack(Items.map, 1));
    	GT_OreDictUnificator.registerOre("bookEmpty"			, new ItemStack(Items.book, 1));
    	GT_OreDictUnificator.registerOre("bookWritable"			, new ItemStack(Items.writable_book, 1));
    	GT_OreDictUnificator.registerOre("bookWritten"			, new ItemStack(Items.written_book, 1));
    	GT_OreDictUnificator.registerOre("bookEnchanted"		, new ItemStack(Items.enchanted_book, 1));
    	
        GT_Log.log.info("GT_Mod: Register colors to the OreDict.");
    	GT_OreDictUnificator.registerOre("dyeCyan"				, GT_MetaItem_Dust.instance.getUnunifiedStack(2, 1));
    	GT_OreDictUnificator.registerOre("dyeBlue"				, GT_MetaItem_Dust.instance.getUnunifiedStack(5, 1));
	}
}
