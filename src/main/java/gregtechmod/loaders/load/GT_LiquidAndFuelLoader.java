package gregtechmod.loaders.load;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_FluidRegistry;
import gregtechmod.common.items.GT_MetaItem_Cell;
import gregtechmod.common.recipe.RecipeMaps;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GT_LiquidAndFuelLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("GT_Mod: Grabbing Liquids of other Mods to register Liquid Cells, and adding Liquid Transposer Recipes for them");

        ItemStack tStack = GT_ModHandler.getRCItem("fluid.creosote.cell", 1);
        FluidStack tLiquid = GT_Utility.getFluidForFilledItem(tStack);;
        
        if (tLiquid != null) {
        	tLiquid = tLiquid.copy();
        	tLiquid.amount = 1000;
        	Materials.Creosote.mFluid = tLiquid;
        }

        Materials.Water.mFluid = Materials.Ice.mFluid = GT_ModHandler.getWater(1000);
        Materials.Lava.mFluid = GT_ModHandler.getLava(1000);
        Materials.ConstructionFoam.mFluid = GT_Utility.getFluidForFilledItem(GT_ModHandler.getIC2Item("CFCell", 1));
        Materials.UUMatter.mFluid = GT_Utility.getFluidForFilledItem(GT_ModHandler.getIC2Item("uuMatterCell", 1));
        
        if (null != (tLiquid = FluidRegistry.getFluidStack("oil"			, 1000))) {Materials.Oil.mFluid								= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil		, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("fuel"			, 1000))) {Materials.Fuel.mFluid							= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Fuel		, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("bioethanol"		, 1000))) {Materials.Ethanol.mFluid							= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Ethanol	, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("biomass"		, 1000))) {Materials.Biomass.mFluid							= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Biomass	, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("ice"			, 1000))) {Materials.Water.mSolid = Materials.Ice.mSolid	= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Ice		, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("seedoil"		, 1000))) {Materials.SeedOil.mFluid							= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SeedOil	, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("creosote"		, 1000))) {Materials.Creosote.mFluid						= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Creosote	, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		
		GT_FluidRegistry.addFluid("heliumplasma"		, null					, Materials.Helium			, OrePrefixes.cellPlasma, 3, GT_ModHandler.getEmptyCell(1));
		
		GT_FluidRegistry.addFluid("hydrogen"			, null					, Materials.Hydrogen		, OrePrefixes.cell, 2, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("deuterium"			, null					, Materials.Deuterium		, OrePrefixes.cell, 2, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("tritium"				, null					, Materials.Tritium			, OrePrefixes.cell, 2, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("helium"				, null					, Materials.Helium			, OrePrefixes.cell, 2, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("helium-3"			, null					, Materials.Helium_3		, OrePrefixes.cell, 2, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("methane"				, null					, Materials.Methane			, OrePrefixes.cell, 2, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("nitrogen"			, null					, Materials.Nitrogen		, OrePrefixes.cell, 2, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("nitrogendioxide"		, null					, Materials.NitrogenDioxide	, OrePrefixes.cell, 2, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("steam"				, null					, Materials.Water			, 2, null, null);
		
		Materials.Ice.mGas = Materials.Water.mGas;
		
		GT_FluidRegistry.addFluid("wolframium"			, null					, Materials.Tungsten		, OrePrefixes.cell, 0, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("lithium"				, null					, Materials.Lithium			, OrePrefixes.cell, 1, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("silicon"				, null					, Materials.Silicon			, OrePrefixes.cell, 0, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("berylium"			, null					, Materials.Beryllium		, OrePrefixes.cell, 0, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("calcium"				, null					, Materials.Calcium			, OrePrefixes.cell, 0, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("sodium"				, null					, Materials.Sodium			, OrePrefixes.cell, 0, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("chlorine"			, null					, Materials.Chlorine		, OrePrefixes.cell, 1, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("potassium"			, null					, Materials.Potassium		, OrePrefixes.cell, 1, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("mercury"				, null					, Materials.Mercury			, OrePrefixes.cell, 1, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("nitrofuel"			, null					, Materials.NitroFuel		, OrePrefixes.cell, 1, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("sodiumpersulfate"	, null					, Materials.SodiumPersulfate, OrePrefixes.cell, 1, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("calciumcarbonate"	, null					, Materials.Calcite			, OrePrefixes.cell, 0, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("glyceryl"			, null					, Materials.Glyceryl		, OrePrefixes.cell, 1, GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("nitrocoalfuel"		, null					, Materials.NitroCoalFuel	, OrePrefixes.cell, 1, GT_ModHandler.getEmptyCell(1));
		
        GT_Log.log.info("GT_Mod: Initializing various Fuels.");
        RecipeMaps.HOT_FUELS.factory().EUt(15).duration(2).input(new ItemStack(Items.lava_bucket)).buildAndRegister(); // starteu = 30
        RecipeMaps.HOT_FUELS.factory().EUt(20).duration(2).input(GT_ModHandler.getIC2Item("hotcoolantCell", 1)).output(GT_ModHandler.getIC2Item("coolantCell", 1)).buildAndRegister(); // was set startEU = 20
        RecipeMaps.MAGIC_FUELS.factory().EUt(10).duration(1).input(new ItemStack(Items.experience_bottle, 1)).buildAndRegister(); // startEU = 10
        RecipeMaps.MAGIC_FUELS.factory().EUt(20).duration(25).input(new ItemStack(Items.ghast_tear, 1)).buildAndRegister(); // startEU = 500
        RecipeMaps.MAGIC_FUELS.factory().EUt(32).duration(3125).input(new ItemStack(Blocks.beacon, 1)).buildAndRegister(); // startEU = 500 // Materials.NetherStar.mFuelPower * 2 = 50000 * 2
		
		GT_ModHandler.addBoilerFuel(GT_Utility.getFluidForFilledItem(GT_MetaItem_Cell.instance.getUnunifiedStack(35, 1)), 18000);
		GT_ModHandler.addBoilerFuel(GT_Utility.getFluidForFilledItem(GT_MetaItem_Cell.instance.getUnunifiedStack( 5, 1)), 24000);
	}
}
