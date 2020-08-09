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
		new GT_MetaTileEntity_ElectricAutoWorkbench		( 16, "GT_E_Craftingtable");
		new GT_MetaTileEntity_Translocator				( 17, "GT_Translocator");
		new GT_MetaTileEntity_ElectricBufferSmall		( 18, "GT_E_Buffer_Small");
		new GT_MetaTileEntity_ElectricBufferLarge		( 19, "GT_E_Buffer_Large");
		new GT_MetaTileEntity_AdvancedTranslocator		( 20, "GT_Adv_Translocator");
		new GT_MetaTileEntity_ElectricBufferAdvanced	( 21, "GT_Adv_Buffer");
		new GT_MetaTileEntity_RockBreaker				( 22, "GT_RockBreaker");
		new GT_MetaTileEntity_ElectricSorter			( 23, "GT_E_Sorter");
		new GT_MetaTileEntity_ElectricItemClearer		( 24, "GT_E_ItemClearer");
		new GT_MetaTileEntity_Electrolyzer				( 25, "GT_Electrolyzer");
		new GT_MetaTileEntity_CropHarvestor				( 26, "GT_Harvestor");
		new GT_MetaTileEntity_Scrapboxinator			( 27, "GT_Scrapboxinator");
		new GT_MetaTileEntity_Grinder					( 28, "GT_Grinder");
		new GT_MetaTileEntity_BlastFurnace				( 29, "GT_BlastFurnace");
		new GT_MetaTileEntity_Quantumtank				( 30, "GT_QuantumTank");
		new GT_MetaTileEntity_ImplosionCompressor		( 31, "GT_ImplosionCompressor");
		new GT_MetaTileEntity_Sawmill					( 32, "GT_Sawmill");
		new GT_MetaTileEntity_DieselGenerator			( 33, "GT_DieselGenerator");
		new GT_MetaTileEntity_GasTurbine				( 34, "GT_GasTurbine");
		new GT_MetaTileEntity_ThermalGenerator			( 35, "GT_ThermalGenerator");
		new GT_MetaTileEntity_SemifluidGenerator		( 36, "GT_SemifluidGenerator");
		new GT_MetaTileEntity_PlasmaGenerator			( 37, "GT_PlasmaGenerator");
		new GT_MetaTileEntity_VacuumFreezer				( 38, "GT_VacuumFreezer");
		new GT_MetaTileEntity_ElectricRegulatorAdvanced	( 39, "GT_RegulatorAdvanced");
		new GT_MetaTileEntity_DragonEggEnergySiphon		( 40, "GT_DragonEggEnergySiphon");
		new GT_MetaTileEntity_ChemicalReactor			( 41, "GT_ChemicalReactor");
		new GT_MetaTileEntity_MagicEnergyConverter		( 42, "GT_MagicConverter");
		new GT_MetaTileEntity_MagicEnergyAbsorber		( 43, "GT_MagicAbsorber");
		new GT_MetaTileEntity_DistillationTower			( 44, "GT_DistillationTower");
		new GT_MetaTileEntity_Safe						( 45, "GT_Safe");
		new GT_MetaTileEntity_ElectricInventoryManager	( 46, "GT_InventoryManager");
		new GT_MetaTileEntity_AdvancedPump				( 47, "GT_Pump");
		new GT_MetaTileEntity_Barrel					( 48, "GT_Barrel");
		new GT_MetaTileEntity_QuantumChest				( 49, "GT_QuantumChest");
		new GT_MetaTileEntity_Macerator					( 50, "GT_Macerator");
		new GT_MetaTileEntity_Extractor					( 51, "GT_Extractor");
		new GT_MetaTileEntity_Compressor				( 52, "GT_Compressor");
		new GT_MetaTileEntity_Recycler					( 53, "GT_Recycler");
		new GT_MetaTileEntity_E_Furnace					( 54, "GT_E_Furnace");
		new GT_MetaTileEntity_Wiremill					( 55, "GT_Wiremill");
		new GT_MetaTileEntity_AlloySmelter				( 56, "GT_AlloySmelter");
		new GT_MetaTileEntity_Canner					( 57, "GT_Canner");
		new GT_MetaTileEntity_ElectricTypeSorter		( 58, "GT_E_T_Sorter");
		new GT_MetaTileEntity_Bender					( 59, "GT_Bender");
		new GT_MetaTileEntity_Assembler					( 60, "GT_Assembler");
		new GT_MetaTileEntity_Printer					( 61, "GT_Printer");
		new GT_MetaTileEntity_Centrifuge				( 62, "GT_Centrifuge");
		new GT_MetaTileEntity_Microwave					( 63, "GT_Microwave");
		new GT_MetaTileEntity_Pulverizer				( 64, "GT_Pulverizer");
		new GT_MetaTileEntity_RedstoneLamp				( 65, "GT_RedstoneLamp");
		new GT_MetaTileEntity_RedstoneNoteBlock			( 66, "GT_RedstoneNoteBlock");
		new GT_MetaTileEntity_RedstoneButtonPanel		( 67, "GT_RedstoneButtonPanel");
		new GT_MetaTileEntity_RedstoneStrengthDisplay	( 68, "GT_RedstoneDisplayBlock");
		new GT_MetaTileEntity_RedstoneCircuitBlock		( 69, "GT_RedstoneCircuitBlock");
		new GT_MetaTileEntity_Shelf						( 70, "GT_Shelf");
		new GT_MetaTileEntity_Shelf_Iron				( 71, "GT_Shelf_Iron");
		new GT_MetaTileEntity_Shelf_FileCabinet			( 72, "GT_Shelf_FileCabinet");
		new GT_MetaTileEntity_Shelf_Desk				( 73, "GT_Shelf_Desk");
		new GT_MetaTileEntity_Shelf_Compartment			( 74, "GT_Shelf_Compartment");
		
		new GT_MetaTileEntity_RedstoneStrengthScale		( 78, "GT_RedstoneScaleBlock");
		new GT_MetaTileEntity_MachineBox				( 79, "GT_MachineBox");
		new GT_MetaTileEntity_FusionComputer			( 80, "GT_Fusion_Computer");
		new GT_MetaTileEntity_FusionEnergyInjector		( 81, "GT_Fusion_Energy");
		new GT_MetaTileEntity_FusionInjector			( 82, "GT_Fusion_Injector");
		new GT_MetaTileEntity_FusionExtractor			( 83, "GT_Fusion_Extractor");
		new GT_MetaTileEntity_TesseractGenerator		( 84, "GT_Tesseract_Generator");
		new GT_MetaTileEntity_TesseractTerminal			( 85, "GT_Tesseract_Terminal");
		new GT_MetaTileEntity_ChargerBox				( 86, "GT_ChargerBox");
		new GT_MetaTileEntity_BatteryBox				( 87, "GT_BatteryBox");
		new GT_MetaTileEntity_BatteryCharger			( 88, "GT_BatteryCharger");
		new GT_MetaTileEntity_LargeCharger				( 89, "GT_LargeCharger");
		new GT_MetaTileEntity_Teleporter				( 90, "GT_Teleporter");
		new GT_MetaTileEntity_Hatch_Input				( 91, "GT_Input_Hatch");
		new GT_MetaTileEntity_Hatch_Output				( 92, "GT_Output_Hatch");
		new GT_MetaTileEntity_Hatch_Maintenance			( 93, "GT_Maintenance_Hatch");
		new GT_MetaTileEntity_Hatch_Dynamo				( 94, "GT_Dynamo_Hatch");
		new GT_MetaTileEntity_Hatch_EnergyInput			( 95, "GT_Energy_Hatch");
		new GT_MetaTileEntity_Hatch_Muffler				( 96, "GT_Muffler_Hatch");
		
		new GT_MetaTileEntity_AESU						(100, "GT_AESU");
		new GT_MetaTileEntity_IDSU						(101, "GT_IDSU");
		new GT_MetaTileEntity_Lightningrod				(102, "GT_Lightningrod");
		new GT_MetaTileEntity_Supercondensator			(103, "GT_Supercondensator");
		
		new GT_MetaTileEntity_Multi_GasTurbine			(108, "GT_Multi_GasTurbine");
		new GT_MetaTileEntity_Multi_SteamTurbine		(109, "GT_Multi_SteamTurbine");
		new GT_MetaTileEntity_Multi_ThermalBoiler		(110, "GT_Multi_ThermalBoiler");
		new GT_MetaTileEntity_Lathe						(111, "GT_Lathe");
		new GT_MetaTileEntity_AdvancedCraftingTable		(112, "GT_ProjectTable");
		new GT_MetaTileEntity_PlateCutter				(113, "GT_Cutter");
		
		new GT_MetaTileEntity_BronzeCraftingTable		(128, "GT_BronzeTable");
		new GT_MetaTileEntity_Boiler_Bronze				(129, "GT_BronzeBoiler");
		new GT_MetaTileEntity_BronzeMacerator			(130, "GT_BronzeMacerator");
		new GT_MetaTileEntity_BronzeFurnace				(131, "GT_BronzeFurnace");
		new GT_MetaTileEntity_BronzeAlloySmelter		(132, "GT_BronzeAlloySmelter");
		new GT_MetaTileEntity_BronzeSteamHammer			(133, "GT_BronzeSteamHammer");
		new GT_MetaTileEntity_BronzeCompressor			(134, "GT_BronzeCompressor");
		new GT_MetaTileEntity_BronzeExtractor			(135, "GT_BronzeExtractor");
		new GT_MetaTileEntity_BronzeBlastFurnace		(136, "GT_BronzeBlastFurnace");
		new GT_MetaTileEntity_Boiler_Steel				(137, "GT_SteelBoiler");
		new GT_MetaTileEntity_SteelFurnace				(138, "GT_SteelFurnace");
		
		//---------------------------------------------------------------------------------------------------------------
		
		new GT_MetaPipeEntity_Bronze					(1800, "GT_Pipe_Bronze");
		new GT_MetaPipeEntity_Steel						(1801, "GT_Pipe_Steel");
		new GT_MetaPipeEntity_StainlessSteel			(1802, "GT_Pipe_StainlessSteel");
		new GT_MetaPipeEntity_TungstenSteel				(1803, "GT_Pipe_TungstenSteel");
		
		new GT_MetaPipeEntity_Bronze_Large				(1850, "GT_Pipe_Bronze_Large");
		new GT_MetaPipeEntity_Steel_Large				(1851, "GT_Pipe_Steel_Large");
		new GT_MetaPipeEntity_StainlessSteel_Large		(1852, "GT_Pipe_StainlessSteel_Large");
		new GT_MetaPipeEntity_TungstenSteel_Large		(1853, "GT_Pipe_TungstenSteel_Large");
		
		new GT_MetaPipeEntity_Brass						(1900, "GT_Pipe_Brass");
		new GT_MetaPipeEntity_Electrum					(1901, "GT_Pipe_Electrum");
		
		new GT_MetaPipeEntity_Brass_Large				(1950, "GT_Pipe_Brass_Large");
		new GT_MetaPipeEntity_Electrum_Large			(1951, "GT_Pipe_Electrum_Large");
	}
}