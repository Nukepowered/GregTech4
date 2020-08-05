package gregtechmod.loaders.load;

import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_EnergyInput;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.tileentities.*;

public class GT_MetaTileEntityLoader implements Runnable {
	@Override
	public void run() {
		GT_Log.log.info("GT_Mod: Register MetaTileEntities.");
		new GT_MetaTileEntity_ElectricAutoWorkbench		( 16, "GT_E_Craftingtable"		, "Electric Craftingtable");
		new GT_MetaTileEntity_Translocator				( 17, "GT_Translocator"			, "Electric Translocator");
		new GT_MetaTileEntity_ElectricBufferSmall		( 18, "GT_E_Buffer_Small"		, "Small Electric Buffer");
		new GT_MetaTileEntity_ElectricBufferLarge		( 19, "GT_E_Buffer_Large"		, "Large Electric Buffer");
		new GT_MetaTileEntity_AdvancedTranslocator		( 20, "GT_Adv_Translocator"		, "Advanced Translocator");
		new GT_MetaTileEntity_ElectricBufferAdvanced	( 21, "GT_Adv_Buffer"			, "Advanced Buffer");
		new GT_MetaTileEntity_RockBreaker				( 22, "GT_RockBreaker"			, "Electric Rock Breaker");
		new GT_MetaTileEntity_ElectricSorter			( 23, "GT_E_Sorter"				, "Electric Sorter");
		new GT_MetaTileEntity_ElectricItemClearer		( 24, "GT_E_ItemClearer"		, "Electric Item Clearer");
		new GT_MetaTileEntity_Electrolyzer				( 25, "GT_Electrolyzer"			, "Industrial Electrolyzer");
		new GT_MetaTileEntity_CropHarvestor				( 26, "GT_Harvestor"			, "Crop Harvestor");
		new GT_MetaTileEntity_Scrapboxinator			( 27, "GT_Scrapboxinator"		, "Scrapboxinator");
		new GT_MetaTileEntity_Grinder					( 28, "GT_Grinder"				, "Industrial Grinder");
		new GT_MetaTileEntity_BlastFurnace				( 29, "GT_BlastFurnace"			, "Industrial Blast Furnace");
		new GT_MetaTileEntity_Quantumtank				( 30, "GT_QuantumTank"			, "Quantum Tank");
		new GT_MetaTileEntity_ImplosionCompressor		( 31, "GT_ImplosionCompressor"	, "Implosion Compressor");
		new GT_MetaTileEntity_Sawmill					( 32, "GT_Sawmill"				, "Industrial Sawmill");
		new GT_MetaTileEntity_DieselGenerator			( 33, "GT_DieselGenerator"		, "Diesel Generator");
		new GT_MetaTileEntity_GasTurbine				( 34, "GT_GasTurbine"			, "Gas Turbine");
		new GT_MetaTileEntity_ThermalGenerator			( 35, "GT_ThermalGenerator"		, "Thermal Generator");
		new GT_MetaTileEntity_SemifluidGenerator		( 36, "GT_SemifluidGenerator"	, "Semifluid Generator");
		new GT_MetaTileEntity_PlasmaGenerator			( 37, "GT_PlasmaGenerator"		, "Plasma Generator");
		new GT_MetaTileEntity_VacuumFreezer				( 38, "GT_VacuumFreezer"		, "Vacuum Freezer");
		new GT_MetaTileEntity_ElectricRegulatorAdvanced	( 39, "GT_RegulatorAdvanced"	, "Advanced Regulator");
		new GT_MetaTileEntity_DragonEggEnergySiphon		( 40, "GT_DragonEggEnergySiphon", "Dragon Egg Energy Siphon");
		new GT_MetaTileEntity_ChemicalReactor			( 41, "GT_ChemicalReactor"		, "Chemical Reactor");
		new GT_MetaTileEntity_MagicEnergyConverter		( 42, "GT_MagicConverter"		, "Magic Energy Converter");
		new GT_MetaTileEntity_MagicEnergyAbsorber		( 43, "GT_MagicAbsorber"		, "Magic Energy Absorber");
		new GT_MetaTileEntity_DistillationTower			( 44, "GT_DistillationTower"	, "Distillation Tower");
		new GT_MetaTileEntity_Safe						( 45, "GT_Safe"					, "Advanced Safe");
		new GT_MetaTileEntity_ElectricInventoryManager	( 46, "GT_InventoryManager"		, "Inventory Manager");
		new GT_MetaTileEntity_AdvancedPump				( 47, "GT_Pump"					, "Advanced Pump");
		new GT_MetaTileEntity_Barrel					( 48, "GT_Barrel"				, "Digital Chest");
		new GT_MetaTileEntity_QuantumChest				( 49, "GT_QuantumChest"			, "Quantum Chest");
		new GT_MetaTileEntity_Macerator					( 50, "GT_Macerator"			, "Automatic Macerator");
		new GT_MetaTileEntity_Extractor					( 51, "GT_Extractor"			, "Automatic Extractor");
		new GT_MetaTileEntity_Compressor				( 52, "GT_Compressor"			, "Automatic Compressor");
		new GT_MetaTileEntity_Recycler					( 53, "GT_Recycler"				, "Automatic Recycler");
		new GT_MetaTileEntity_E_Furnace					( 54, "GT_E_Furnace"			, "Automatic E-Furnace");
		new GT_MetaTileEntity_Wiremill					( 55, "GT_Wiremill"				, "Automatic Wiremill");
		new GT_MetaTileEntity_AlloySmelter				( 56, "GT_AlloySmelter"			, "Alloy Smelter");
		new GT_MetaTileEntity_Canner					( 57, "GT_Canner"				, "Automatic Canning Machine");
		new GT_MetaTileEntity_ElectricTypeSorter		( 58, "GT_E_T_Sorter"			, "Electric Type Sorter");
		new GT_MetaTileEntity_Bender					( 59, "GT_Bender"				, "Plate Bending Machine");
		new GT_MetaTileEntity_Assembler					( 60, "GT_Assembler"			, "Assembling Machine");
		new GT_MetaTileEntity_Printer					( 61, "GT_Printer"				, "Printing Factory");
		new GT_MetaTileEntity_Centrifuge				( 62, "GT_Centrifuge"			, "Industrial Centrifuge");
		new GT_MetaTileEntity_Microwave					( 63, "GT_Microwave"			, "Microwave Oven");
		new GT_MetaTileEntity_Pulverizer				( 64, "GT_Pulverizer"			, "Universal Macerator");
		new GT_MetaTileEntity_RedstoneLamp				( 65, "GT_RedstoneLamp"			, "Redstone Controlled Lamp");
		new GT_MetaTileEntity_RedstoneNoteBlock			( 66, "GT_RedstoneNoteBlock"	, "Redstone Note Block");
		new GT_MetaTileEntity_RedstoneButtonPanel		( 67, "GT_RedstoneButtonPanel"	, "Button Panel");
		new GT_MetaTileEntity_RedstoneStrengthDisplay	( 68, "GT_RedstoneDisplayBlock"	, "Redstone Display");
		new GT_MetaTileEntity_RedstoneCircuitBlock		( 69, "GT_RedstoneCircuitBlock"	, "Redstone Circuit Block");
		new GT_MetaTileEntity_Shelf						( 70, "GT_Shelf"				, "Wood encased Shelf");
		new GT_MetaTileEntity_Shelf_Iron				( 71, "GT_Shelf_Iron"			, "Metal encased Shelf");
		new GT_MetaTileEntity_Shelf_FileCabinet			( 72, "GT_Shelf_FileCabinet"	, "File Cabinet");
		new GT_MetaTileEntity_Shelf_Desk				( 73, "GT_Shelf_Desk"			, "Metal encased Desk");
		new GT_MetaTileEntity_Shelf_Compartment			( 74, "GT_Shelf_Compartment"	, "Compartment");
		
		new GT_MetaTileEntity_RedstoneStrengthScale		( 78, "GT_RedstoneScaleBlock"	, "Redstone Scale");
		new GT_MetaTileEntity_MachineBox				( 79, "GT_MachineBox"			, "Machine Box");
		new GT_MetaTileEntity_FusionComputer			( 80, "GT_Fusion_Computer"		, "Fusion Control Computer");
		new GT_MetaTileEntity_FusionEnergyInjector		( 81, "GT_Fusion_Energy"		, "Fusion Energy Injector");
		new GT_MetaTileEntity_FusionInjector			( 82, "GT_Fusion_Injector"		, "Fusion Material Injector");
		new GT_MetaTileEntity_FusionExtractor			( 83, "GT_Fusion_Extractor"		, "Fusion Material Extractor");
		new GT_MetaTileEntity_TesseractGenerator		( 84, "GT_Tesseract_Generator"	, "Tesseract Generator");
		new GT_MetaTileEntity_TesseractTerminal			( 85, "GT_Tesseract_Terminal"	, "Tesseract Terminal");
		new GT_MetaTileEntity_ChargerBox				( 86, "GT_ChargerBox"			, "Charger Box");
		new GT_MetaTileEntity_BatteryBox				( 87, "GT_BatteryBox"			, "Battery Box");
		new GT_MetaTileEntity_BatteryCharger			( 88, "GT_BatteryCharger"		, "Battery Charger");
		new GT_MetaTileEntity_LargeCharger				( 89, "GT_LargeCharger"			, "Large Charger");
		new GT_MetaTileEntity_Teleporter				( 90, "GT_Teleporter"			, "Teleporter");
		new GT_MetaTileEntity_Hatch_Input				( 91, "GT_Input_Hatch"			, "Input Hatch");
		new GT_MetaTileEntity_Hatch_Output				( 92, "GT_Output_Hatch"			, "Output Hatch");
		new GT_MetaTileEntity_Hatch_Maintenance			( 93, "GT_Maintenance_Hatch"	, "Maintenance Hatch");
		new GT_MetaTileEntity_Hatch_Dynamo				( 94, "GT_Dynamo_Hatch"			, "Dynamo Hatch");
		new GT_MetaTileEntity_Hatch_EnergyInput			( 95, "GT_Energy_Hatch"			, "Energy Hatch");
		new GT_MetaTileEntity_Hatch_Muffler				( 96, "GT_Muffler_Hatch"		, "Muffler Hatch");
		
		new GT_MetaTileEntity_AESU						(100, "GT_AESU"					, "AESU");
		new GT_MetaTileEntity_IDSU						(101, "GT_IDSU"					, "IDSU");
		new GT_MetaTileEntity_Lightningrod				(102, "GT_Lightningrod"			, "Lightning Rod");
		new GT_MetaTileEntity_Supercondensator			(103, "GT_Supercondensator"		, "Supercondensator");
		
		new GT_MetaTileEntity_Multi_GasTurbine			(108, "GT_Multi_GasTurbine"		, "Large Gas Turbine");
		new GT_MetaTileEntity_Multi_SteamTurbine		(109, "GT_Multi_SteamTurbine"	, "Large Steam Turbine");
		new GT_MetaTileEntity_Multi_ThermalBoiler		(110, "GT_Multi_ThermalBoiler"	, "Thermal Boiler");
		new GT_MetaTileEntity_Lathe						(111, "GT_Lathe"				, "Lathe");
		new GT_MetaTileEntity_AdvancedCraftingTable		(112, "GT_ProjectTable"			, "Advanced Crafting Table");
		new GT_MetaTileEntity_PlateCutter				(113, "GT_Cutter"				, "Plate Cutting Machine");
		
		new GT_MetaTileEntity_BronzeCraftingTable		(128, "GT_BronzeTable"			, "Bronze Workbench");
		new GT_MetaTileEntity_Boiler_Bronze				(129, "GT_BronzeBoiler"			, "Small Coal Boiler");
		new GT_MetaTileEntity_BronzeMacerator			(130, "GT_BronzeMacerator"		, "Sturdy Grinder");
		new GT_MetaTileEntity_BronzeFurnace				(131, "GT_BronzeFurnace"		, "Steam Furnace");
		new GT_MetaTileEntity_BronzeAlloySmelter		(132, "GT_BronzeAlloySmelter"	, "Steam Smelter");
		new GT_MetaTileEntity_BronzeSteamHammer			(133, "GT_BronzeSteamHammer"	, "Steam Powered Forge Hammer");
		new GT_MetaTileEntity_BronzeCompressor			(134, "GT_BronzeCompressor"		, "Steam Compressor");
		new GT_MetaTileEntity_BronzeExtractor			(135, "GT_BronzeExtractor"		, "Squeezing Extractor");
		new GT_MetaTileEntity_BronzeBlastFurnace		(136, "GT_BronzeBlastFurnace"	, "Bronze Blast Furnace");
		new GT_MetaTileEntity_Boiler_Steel				(137, "GT_SteelBoiler"			, "High Pressure Coal Boiler");
		new GT_MetaTileEntity_SteelFurnace				(138, "GT_SteelFurnace"			, "High Pressure Steam Furnace");
		
		//---------------------------------------------------------------------------------------------------------------
		
		new GT_MetaPipeEntity_Bronze					(1800, "GT_Pipe_Bronze"					, "Bronze Fluid Pipe");
		new GT_MetaPipeEntity_Steel						(1801, "GT_Pipe_Steel"					, "Steel Fluid Pipe");
		new GT_MetaPipeEntity_StainlessSteel			(1802, "GT_Pipe_StainlessSteel"			, "Stainless Steel Fluid Pipe");
		new GT_MetaPipeEntity_TungstenSteel				(1803, "GT_Pipe_TungstenSteel"			, "Tungstensteel Fluid Pipe");
		
		new GT_MetaPipeEntity_Bronze_Large				(1850, "GT_Pipe_Bronze_Large"			, "Large Bronze Fluid Pipe");
		new GT_MetaPipeEntity_Steel_Large				(1851, "GT_Pipe_Steel_Large"			, "Large Steel Fluid Pipe");
		new GT_MetaPipeEntity_StainlessSteel_Large		(1852, "GT_Pipe_StainlessSteel_Large"	, "Large Stainless Steel Fluid Pipe");
		new GT_MetaPipeEntity_TungstenSteel_Large		(1853, "GT_Pipe_TungstenSteel_Large"	, "Large Tungstensteel Fluid Pipe");
		
		new GT_MetaPipeEntity_Brass						(1900, "GT_Pipe_Brass"					, "Brass Item Pipe");
		new GT_MetaPipeEntity_Electrum					(1901, "GT_Pipe_Electrum"				, "Electrum Item Pipe");
		
		new GT_MetaPipeEntity_Brass_Large				(1950, "GT_Pipe_Brass_Large"			, "Large Brass Item Pipe");
		new GT_MetaPipeEntity_Electrum_Large			(1951, "GT_Pipe_Electrum_Large"			, "Large Electrum Item Pipe");
	}
}