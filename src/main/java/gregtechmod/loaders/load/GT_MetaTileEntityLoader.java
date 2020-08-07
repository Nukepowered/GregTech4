package gregtechmod.loaders.load;

import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Dynamo;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_EnergyInput;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Muffler;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Output;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.tileentities.*;
import net.minecraft.client.resources.I18n;

public class GT_MetaTileEntityLoader implements Runnable {
	@Override
	public void run() {
		GT_Log.log.info("GT_Mod: Register MetaTileEntities.");
		new GT_MetaTileEntity_ElectricAutoWorkbench		( 16, "GT_E_Craftingtable"		, "metatileentity.GT_E_Craftingtable.name");
		new GT_MetaTileEntity_Translocator				( 17, "GT_Translocator"			, "metatileentity.GT_Translocator.name");
		new GT_MetaTileEntity_ElectricBufferSmall		( 18, "GT_E_Buffer_Small"		, "metatileentity.GT_E_Buffer_Small.name");
		new GT_MetaTileEntity_ElectricBufferLarge		( 19, "GT_E_Buffer_Large"		, "metatileentity.GT_E_Buffer_Large.name");
		new GT_MetaTileEntity_AdvancedTranslocator		( 20, "GT_Adv_Translocator"		, "metatileentity.GT_Adv_Translocator.name");
		new GT_MetaTileEntity_ElectricBufferAdvanced	( 21, "GT_Adv_Buffer"			, "metatileentity.GT_Adv_Buffer.name");
		new GT_MetaTileEntity_RockBreaker				( 22, "GT_RockBreaker"			, "metatileentity.GT_RockBreaker.name");
		new GT_MetaTileEntity_ElectricSorter			( 23, "GT_E_Sorter"				, "metatileentity.GT_E_Sorter.name");
		new GT_MetaTileEntity_ElectricItemClearer		( 24, "GT_E_ItemClearer"		, "metatileentity.GT_E_ItemClearer.name");
		new GT_MetaTileEntity_Electrolyzer				( 25, "GT_Electrolyzer"			, "metatileentity.GT_Electrolyzer.name");
		new GT_MetaTileEntity_CropHarvestor				( 26, "GT_Harvestor"			, "metatileentity.GT_Harvestor.name");
		new GT_MetaTileEntity_Scrapboxinator			( 27, "GT_Scrapboxinator"		, "metatileentity.GT_Scrapboxinator.name");
		new GT_MetaTileEntity_Grinder					( 28, "GT_Grinder"				, "metatileentity.GT_Grinder.name");
		new GT_MetaTileEntity_BlastFurnace				( 29, "GT_BlastFurnace"			, "metatileentity.GT_BlastFurnace.name");
		new GT_MetaTileEntity_Quantumtank				( 30, "GT_QuantumTank"			, "metatileentity.GT_QuantumTank.name");
		new GT_MetaTileEntity_ImplosionCompressor		( 31, "GT_ImplosionCompressor"	, "metatileentity.GT_ImplosionCompressor.name");
		new GT_MetaTileEntity_Sawmill					( 32, "GT_Sawmill"				, "metatileentity.GT_Sawmill.name");
		new GT_MetaTileEntity_DieselGenerator			( 33, "GT_DieselGenerator"		, "metatileentity.GT_DieselGenerator.name");
		new GT_MetaTileEntity_GasTurbine				( 34, "GT_GasTurbine"			, "metatileentity.GT_GasTurbine.name");
		new GT_MetaTileEntity_ThermalGenerator			( 35, "GT_ThermalGenerator"		, "metatileentity.GT_ThermalGenerator.name");
		new GT_MetaTileEntity_SemifluidGenerator		( 36, "GT_SemifluidGenerator"	, "metatileentity.GT_SemifluidGenerator.name");
		new GT_MetaTileEntity_PlasmaGenerator			( 37, "GT_PlasmaGenerator"		, "metatileentity.GT_PlasmaGenerator.name");
		new GT_MetaTileEntity_VacuumFreezer				( 38, "GT_VacuumFreezer"		, "metatileentity.GT_VacuumFreezer.name");
		new GT_MetaTileEntity_ElectricRegulatorAdvanced	( 39, "GT_RegulatorAdvanced"	, "metatileentity.GT_RegulatorAdvanced.name");
//		new GT_MetaTileEntity_DragonEggEnergySiphon		( 40, "GT_DragonEggEnergySiphon", "metatileentity.GT_DragonEggEnergySiphon.name");
		new GT_MetaTileEntity_ChemicalReactor			( 41, "GT_ChemicalReactor"		, "metatileentity.GT_ChemicalReactor.name");
//		new GT_MetaTileEntity_MagicEnergyConverter		( 42, "GT_MagicConverter"		, "metatileentity.GT_MagicConverter.name");
//		new GT_MetaTileEntity_MagicEnergyAbsorber		( 43, "GT_MagicAbsorber"		, "metatileentity.GT_MagicAbsorber.name");
		new GT_MetaTileEntity_DistillationTower			( 44, "GT_DistillationTower"	, "metatileentity.GT_DistillationTower.name");
		new GT_MetaTileEntity_Safe						( 45, "GT_Safe"					, "metatileentity.GT_Safe.name");
		new GT_MetaTileEntity_ElectricInventoryManager	( 46, "GT_InventoryManager"		, "metatileentity.GT_InventoryManager.name");
//		new GT_MetaTileEntity_AdvancedPump				( 47, "GT_Pump"					, "metatileentity.GT_Pump.name");
//		new GT_MetaTileEntity_Barrel					( 48, "GT_Barrel"				, "metatileentity.GT_Barrel.name");
		new GT_MetaTileEntity_QuantumChest				( 49, "GT_QuantumChest"			, "metatileentity.GT_QuantumChest.name");
		new GT_MetaTileEntity_Macerator					( 50, "GT_Macerator"			, "metatileentity.GT_Macerator.name");
		new GT_MetaTileEntity_Extractor					( 51, "GT_Extractor"			, "metatileentity.GT_Extractor.name");
		new GT_MetaTileEntity_Compressor				( 52, "GT_Compressor"			, "metatileentity.GT_Compressor.name");
		new GT_MetaTileEntity_Recycler					( 53, "GT_Recycler"				, "metatileentity.GT_Recycler.name");
		new GT_MetaTileEntity_E_Furnace					( 54, "GT_E_Furnace"			, "metatileentity.GT_E_Furnace.name");
		new GT_MetaTileEntity_Wiremill					( 55, "GT_Wiremill"				, "metatileentity.GT_Wiremill.name");
		new GT_MetaTileEntity_AlloySmelter				( 56, "GT_AlloySmelter"			, "metatileentity.GT_AlloySmelter.name");
		new GT_MetaTileEntity_Canner					( 57, "GT_Canner"				, "metatileentity.GT_Canner.name");
		new GT_MetaTileEntity_ElectricTypeSorter		( 58, "GT_E_T_Sorter"			, "metatileentity.GT_E_T_Sorter.name");
		new GT_MetaTileEntity_Bender					( 59, "GT_Bender"				, "metatileentity.GT_Bender.name");
		new GT_MetaTileEntity_Assembler					( 60, "GT_Assembler"			, "metatileentity.GT_Assembler.name");
		new GT_MetaTileEntity_Printer					( 61, "GT_Printer"				, "metatileentity.GT_Printer.name");
		new GT_MetaTileEntity_Centrifuge				( 62, "GT_Centrifuge"			, "metatileentity.T_Centrifuge.name");
		new GT_MetaTileEntity_Microwave					( 63, "GT_Microwave"			, "metatileentity.GT_Microwave.name");
		new GT_MetaTileEntity_Pulverizer				( 64, "GT_Pulverizer"			, "metatileentity.GT_Pulverizer.name");
		new GT_MetaTileEntity_RedstoneLamp				( 65, "GT_RedstoneLamp"			, "metatileentity.GT_RedstoneLamp.name");
		new GT_MetaTileEntity_RedstoneNoteBlock			( 66, "GT_RedstoneNoteBlock"	, "metatileentity.GT_RedstoneNoteBlock.name");
		new GT_MetaTileEntity_RedstoneButtonPanel		( 67, "GT_RedstoneButtonPanel"	, "metatileentity.GT_RedstoneButtonPanel.name");
		new GT_MetaTileEntity_RedstoneStrengthDisplay	( 68, "GT_RedstoneDisplayBlock"	, "metatileentity.GT_RedstoneDisplayBlock.name");
//		new GT_MetaTileEntity_RedstoneCircuitBlock		( 69, "GT_RedstoneCircuitBlock"	, "metatileentity.GT_RedstoneCircuitBlock.name");
		new GT_MetaTileEntity_Shelf						( 70, "GT_Shelf"				, "metatileentity.GT_Shelf.name");
		new GT_MetaTileEntity_Shelf_Iron				( 71, "GT_Shelf_Iron"			, "metatileentity.GT_Shelf_Iron.name");
		new GT_MetaTileEntity_Shelf_FileCabinet			( 72, "GT_Shelf_FileCabinet"	, "metatileentity.GT_Shelf_FileCabinet.name");
		new GT_MetaTileEntity_Shelf_Desk				( 73, "GT_Shelf_Desk"			, "metatileentity.GT_Shelf_Desk.name");
		new GT_MetaTileEntity_Shelf_Compartment			( 74, "GT_Shelf_Compartment"	, "metatileentity.GT_Shelf_Compartment.name");
//		
		new GT_MetaTileEntity_RedstoneStrengthScale		( 78, "GT_RedstoneScaleBlock"	, "metatileentity.GT_RedstoneScaleBlock.name");
		new GT_MetaTileEntity_MachineBox				( 79, "GT_MachineBox"			, "metatileentity.GT_MachineBox.name");
		new GT_MetaTileEntity_FusionComputer			( 80, "GT_Fusion_Computer"		, "metatileentity.GT_Fusion_Computer.name");
		new GT_MetaTileEntity_FusionEnergyInjector		( 81, "GT_Fusion_Energy"		, "metatileentity.GT_Fusion_Energy.name");
		new GT_MetaTileEntity_FusionInjector			( 82, "GT_Fusion_Injector"		, "metatileentity.GT_Fusion_Injector.name");
		new GT_MetaTileEntity_FusionExtractor			( 83, "GT_Fusion_Extractor"		, "metatileentity.GT_Fusion_Extractor.name");
		new GT_MetaTileEntity_TesseractGenerator		( 84, "GT_Tesseract_Generator"	, "metatileentity.GT_Tesseract_Generator.name");
		new GT_MetaTileEntity_TesseractTerminal			( 85, "GT_Tesseract_Terminal"	, "metatileentity.GT_Tesseract_Terminal.name");
		new GT_MetaTileEntity_ChargerBox				( 86, "GT_ChargerBox"			, "metatileentity.GT_ChargerBox.name");
		new GT_MetaTileEntity_BatteryBox				( 87, "GT_BatteryBox"			, "metatileentity.GT_BatteryBox.name");
		new GT_MetaTileEntity_BatteryCharger			( 88, "GT_BatteryCharger"		, "metatileentity.GT_BatteryCharger.name");
		new GT_MetaTileEntity_LargeCharger				( 89, "GT_LargeCharger"			, "metatileentity.GT_LargeCharger.name");
		new GT_MetaTileEntity_Teleporter				( 90, "GT_Teleporter"			, "metatileentity.GT_Teleporter.name");
		new GT_MetaTileEntity_Hatch_Input				( 91, "GT_Input_Hatch"			, "metatileentity.GT_Input_Hatch.name");
		new GT_MetaTileEntity_Hatch_Output				( 92, "GT_Output_Hatch"			, "metatileentity.GT_Output_Hatch.name");
		new GT_MetaTileEntity_Hatch_Maintenance			( 93, "GT_Maintenance_Hatch"	, "metatileentity.GT_Maintenance_Hatch.name");
		new GT_MetaTileEntity_Hatch_Dynamo				( 94, "GT_Dynamo_Hatch"			, "metatileentity.GT_Dynamo_Hatch.name");
		new GT_MetaTileEntity_Hatch_EnergyInput			( 95, "GT_Energy_Hatch"			, "metatileentity.GT_Energy_Hatch.name");
		new GT_MetaTileEntity_Hatch_Muffler				( 96, "GT_Muffler_Hatch"		, "metatileentity.GT_Muffler_Hatch.name");
//		
		new GT_MetaTileEntity_AESU						(100, "GT_AESU"					, "metatileentity.GT_AESU.name");
		new GT_MetaTileEntity_IDSU						(101, "GT_IDSU"					, "metatileentity.GT_IDSU.name");
		new GT_MetaTileEntity_Lightningrod				(102, "GT_Lightningrod"			, "metatileentity.GT_Lightningrod.name");
		new GT_MetaTileEntity_Supercondensator			(103, "GT_Supercondensator"		, "metatileentity.GT_Supercondensator.name");
//		
		new GT_MetaTileEntity_Multi_GasTurbine			(108, "GT_Multi_GasTurbine"		, "metatileentity.GT_Multi_GasTurbine.name");
		new GT_MetaTileEntity_Multi_SteamTurbine		(109, "GT_Multi_SteamTurbine"	, "metatileentity.GT_Multi_SteamTurbine.name");
		new GT_MetaTileEntity_Multi_ThermalBoiler		(110, "GT_Multi_ThermalBoiler"	, "metatileentity.GT_Multi_ThermalBoiler.name");
		new GT_MetaTileEntity_Lathe						(111, "GT_Lathe"				, "metatileentity.GT_Lathe.name");
//		new GT_MetaTileEntity_AdvancedCraftingTable		(112, "GT_ProjectTable"			, "metatileentity.GT_ProjectTable.name");
		new GT_MetaTileEntity_PlateCutter				(113, "GT_Cutter"				, "metatileentity.GT_Cutter.name");
//		
		new GT_MetaTileEntity_BronzeCraftingTable		(128, "GT_BronzeTable"			, "metatileentity.GT_BronzeTable.name");
//		new GT_MetaTileEntity_Boiler_Bronze				(129, "GT_BronzeBoiler"			, "metatileentity.GT_BronzeBoiler.name");
		new GT_MetaTileEntity_BronzeMacerator			(130, "GT_BronzeMacerator"		, "metatileentity.GT_BronzeMacerator.name");
		new GT_MetaTileEntity_BronzeFurnace				(131, "GT_BronzeFurnace"		, "metatileentity.GT_BronzeFurnace.name");
		new GT_MetaTileEntity_BronzeAlloySmelter		(132, "GT_BronzeAlloySmelter"	, "metatileentity.GT_BronzeAlloySmelter.name");
		new GT_MetaTileEntity_BronzeSteamHammer			(133, "GT_BronzeSteamHammer"	, "metatileentity.GT_BronzeSteamHammer.name");
		new GT_MetaTileEntity_BronzeCompressor			(134, "GT_BronzeCompressor"		, "metatileentity.GT_BronzeCompressor.name");
		new GT_MetaTileEntity_BronzeExtractor			(135, "GT_BronzeExtractor"		, "metatileentity.GT_BronzeExtractor.name");
		new GT_MetaTileEntity_BronzeBlastFurnace		(136, "GT_BronzeBlastFurnace"	, "metatileentity.GT_BronzeBlastFurnace.name");
//		new GT_MetaTileEntity_Boiler_Steel				(137, "GT_SteelBoiler"			, "metatileentity.GT_SteelBoiler.name");
		new GT_MetaTileEntity_SteelFurnace				(138, "GT_SteelFurnace"			, "metatileentity.GT_SteelFurnace.name");
//		
//		//---------------------------------------------------------------------------------------------------------------
//		
//		new GT_MetaPipeEntity_Bronze					(1800, "GT_Pipe_Bronze"					, "metatileentity.GT_Pipe_Bronze.name");
//		new GT_MetaPipeEntity_Steel						(1801, "GT_Pipe_Steel"					, "metatileentity.GT_Pipe_Steel.name");
//		new GT_MetaPipeEntity_StainlessSteel			(1802, "GT_Pipe_StainlessSteel"			, "metatileentity.GT_Pipe_StainlessStee.namel");
//		new GT_MetaPipeEntity_TungstenSteel				(1803, "GT_Pipe_TungstenSteel"			, "metatileentity.GT_Pipe_TungstenSteel.name");
//		
//		new GT_MetaPipeEntity_Bronze_Large				(1850, "GT_Pipe_Bronze_Large"			, "metatileentity.GT_Pipe_Bronze_Large.name");
//		new GT_MetaPipeEntity_Steel_Large				(1851, "GT_Pipe_Steel_Large"			, "metatileentity.GT_Pipe_Steel_Large.name");
//		new GT_MetaPipeEntity_StainlessSteel_Large		(1852, "GT_Pipe_StainlessSteel_Large"	, "metatileentity.GT_Pipe_StainlessSteel_Large.name");
//		new GT_MetaPipeEntity_TungstenSteel_Large		(1853, "GT_Pipe_TungstenSteel_Large"	, "metatileentity.GT_Pipe_TungstenSteel_Large.name");
//		
//		new GT_MetaPipeEntity_Brass						(1900, "GT_Pipe_Brass"					, "metatileentity.GT_Pipe_Brass.name");
//		new GT_MetaPipeEntity_Electrum					(1901, "GT_Pipe_Electrum"				, "metatileentity.GT_Pipe_Electrum.name");
//		
//		new GT_MetaPipeEntity_Brass_Large				(1950, "GT_Pipe_Brass_Large"			, "metatileentity.GT_Pipe_Brass_Large.name");
//		new GT_MetaPipeEntity_Electrum_Large			(1951, "GT_Pipe_Electrum_Large"			, "metatileentity.GT_Pipe_Electrum_Large.name");
	}
}