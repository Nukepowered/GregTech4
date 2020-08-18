package gregtechmod.loaders.load;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_FluidRegistry;
import gregtechmod.common.items.GT_MetaItem_Cell;
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
        
        if (null != (tLiquid = FluidRegistry.getFluidStack("oil"			, 1000))) {Materials.Oil.mFluid								= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(17, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("fuel"			, 1000))) {Materials.Fuel.mFluid							= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(18, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("bioethanol"		, 1000))) {Materials.Ethanol.mFluid							= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(19, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("biomass"		, 1000))) {Materials.Biomass.mFluid							= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(20, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("ice"			, 1000))) {Materials.Water.mSolid = Materials.Ice.mSolid	= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(23, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		if (null != (tLiquid = FluidRegistry.getFluidStack("seedoil"		, 1000))) {Materials.SeedOil.mFluid							= tLiquid.copy(); FluidContainerRegistry.registerFluidContainer(new FluidContainerData(tLiquid, tStack = GT_MetaItem_Cell.instance.getUnunifiedStack(24, 1), GT_ModHandler.getEmptyCell(1))); GT_ModHandler.addLiquidTransposerRecipe(GT_ModHandler.getEmptyCell(1), tLiquid, tStack, 160);}
		
		GT_FluidRegistry.addFluid("heliumplasma"		, null					, Materials.Helium			, 3, GT_MetaItem_Cell.instance.getUnunifiedStack(131, 1), GT_ModHandler.getEmptyCell(1));
		
		GT_FluidRegistry.addFluid("hydrogen"			, null					, Materials.Hydrogen		, 2, GT_MetaItem_Cell.instance.getUnunifiedStack( 0, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("deuterium"			, null					, Materials.Deuterium		, 2, GT_MetaItem_Cell.instance.getUnunifiedStack( 1, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("tritium"				, null					, Materials.Tritium			, 2, GT_MetaItem_Cell.instance.getUnunifiedStack( 2, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("helium"				, null					, Materials.Helium			, 2, GT_MetaItem_Cell.instance.getUnunifiedStack( 3, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("helium_3"			, null					, Materials.Helium_3		, 2, GT_MetaItem_Cell.instance.getUnunifiedStack( 6, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("methane"				, null					, Materials.Methane			, 2, GT_MetaItem_Cell.instance.getUnunifiedStack( 9, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("nitrogen"			, null					, Materials.Nitrogen		, 2, GT_MetaItem_Cell.instance.getUnunifiedStack(15, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("nitrogendioxide"		, null					, Materials.NitrogenDioxide	, 2, GT_MetaItem_Cell.instance.getUnunifiedStack(38, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("steam"				, null					, Materials.Water			, 2, null, null);
		
		Materials.Ice.mGas = Materials.Water.mGas;
		
		GT_FluidRegistry.addFluid("wolframium"			, null					, Materials.Tungsten		, 0, GT_MetaItem_Cell.instance.getUnunifiedStack( 4, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("lithium"				, null					, Materials.Lithium			, 1, GT_MetaItem_Cell.instance.getUnunifiedStack( 5, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("silicon"				, null					, Materials.Silicon			, 0, GT_MetaItem_Cell.instance.getUnunifiedStack( 7, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("berylium"			, null					, Materials.Beryllium		, 0, GT_MetaItem_Cell.instance.getUnunifiedStack(10, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("calcium"				, null					, Materials.Calcium			, 0, GT_MetaItem_Cell.instance.getUnunifiedStack(11, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("sodium"				, null					, Materials.Sodium			, 0, GT_MetaItem_Cell.instance.getUnunifiedStack(12, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("chlorine"			, null					, Materials.Chlorine		, 1, GT_MetaItem_Cell.instance.getUnunifiedStack(13, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("potassium"			, null					, Materials.Potassium		, 1, GT_MetaItem_Cell.instance.getUnunifiedStack(14, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("mercury"				, null					, Materials.Mercury			, 1, GT_MetaItem_Cell.instance.getUnunifiedStack(16, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("nitrofuel"			, null					, Materials.NitroFuel		, 1, GT_MetaItem_Cell.instance.getUnunifiedStack(22, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("sodiumpersulfate"	, null					, Materials.SodiumPersulfate, 1, GT_MetaItem_Cell.instance.getUnunifiedStack(32, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("calciumcarbonate"	, null					, Materials.Calcite			, 0, GT_MetaItem_Cell.instance.getUnunifiedStack(33, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("glyceryl"			, null					, Materials.Glyceryl		, 1, GT_MetaItem_Cell.instance.getUnunifiedStack(34, 1), GT_ModHandler.getEmptyCell(1));
		GT_FluidRegistry.addFluid("nitrocoalfuel"		, null					, Materials.NitroCoalFuel	, 1, GT_MetaItem_Cell.instance.getUnunifiedStack(35, 1), GT_ModHandler.getEmptyCell(1));
		
        GT_Log.log.info("GT_Mod: Initializing various Fuels.");
        new GT_Recipe(new ItemStack(Items.lava_bucket), new ItemStack(Blocks.obsidian), GT_OreDictUnificator.get("ingotCopper", 1), GT_OreDictUnificator.get("ingotTin", 1), GT_OreDictUnificator.get("ingotElectrum", 1), 30, 2);
		
        GregTech_API.sRecipeAdder.addFuel(new ItemStack(Items.experience_bottle, 1)					, null,     10, 5);
        GregTech_API.sRecipeAdder.addFuel(new ItemStack(Items.ghast_tear, 1)					, null,    500, 5);
        GregTech_API.sRecipeAdder.addFuel(new ItemStack(Blocks.beacon, 1)					, null, Materials.NetherStar.mFuelPower * 2, Materials.NetherStar.mFuelType);
		
		GT_ModHandler.addBoilerFuel(GT_Utility.getFluidForFilledItem(GT_MetaItem_Cell.instance.getUnunifiedStack(35, 1)), 18000);
		GT_ModHandler.addBoilerFuel(GT_Utility.getFluidForFilledItem(GT_MetaItem_Cell.instance.getUnunifiedStack( 5, 1)), 24000);
	}
}
