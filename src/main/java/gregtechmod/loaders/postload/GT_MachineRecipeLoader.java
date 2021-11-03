package gregtechmod.loaders.postload;

import ic2.api.recipe.*;
import net.minecraft.item.*;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import gregtechmod.api.util.*;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.UnifierRecipeEntry;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import cpw.mods.fml.common.Loader;
import gregtechmod.api.*;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import gregtechmod.api.enums.*;

public class GT_MachineRecipeLoader implements Runnable
{
    private final MaterialStack[][] mAlloySmelterList;
    
    public GT_MachineRecipeLoader() {
        this.mAlloySmelterList = new MaterialStack[][] { { new MaterialStack(Materials.Tetrahedrite, 3L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.Bronze, 3L) }, { new MaterialStack(Materials.Tetrahedrite, 3L), new MaterialStack(Materials.Zinc, 1L), new MaterialStack(Materials.Brass, 3L) }, { new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.Bronze, 4L) }, { new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Zinc, 1L), new MaterialStack(Materials.Brass, 4L) }, { new MaterialStack(Materials.Copper, 1L), new MaterialStack(Materials.Nickel, 1L), new MaterialStack(Materials.Cupronickel, 2L) }, { new MaterialStack(Materials.Iron, 2L), new MaterialStack(Materials.Nickel, 1L), new MaterialStack(Materials.Invar, 3L) }, { new MaterialStack(Materials.Chrome, 1L), new MaterialStack(Materials.Nickel, 4L), new MaterialStack(Materials.Nichrome, 5L) }, { new MaterialStack(Materials.Tin, 9L), new MaterialStack(Materials.Antimony, 1L), new MaterialStack(Materials.SolderingAlloy, 10L) }, { new MaterialStack(Materials.Lead, 4L), new MaterialStack(Materials.Antimony, 1L), new MaterialStack(Materials.BatteryAlloy, 5L) }, { new MaterialStack(Materials.Gold, 1L), new MaterialStack(Materials.Silver, 1L), new MaterialStack(Materials.Electrum, 2L) }, { new MaterialStack(Materials.Magnesium, 1L), new MaterialStack(Materials.Aluminium, 2L), new MaterialStack(Materials.Magnalium, 3L) }, { new MaterialStack(Materials.Copper, 1L), new MaterialStack(Materials.Redstone, 4L), new MaterialStack(Materials.RedAlloy, 1L) }, { new MaterialStack(Materials.Silver, 1L), new MaterialStack(Materials.Nikolite, 4L), new MaterialStack(Materials.BlueAlloy, 1L) }, { new MaterialStack(Materials.Iron, 1L), new MaterialStack(Materials.Electrotine, 8L), new MaterialStack(Materials.ElectrotineAlloy, 1L) } };
    }
    
	@Override
    public void run() {
    	
        GT_Log.log.info("Adding non-OreDict Machine Recipes.");
        
        try {
            GT_Utility.removeSimpleIC2MachineRecipe(null, Recipes.metalformerExtruding.getRecipes(), GT_Items.Cell_Empty.get(3L));
            GT_Utility.removeSimpleIC2MachineRecipe(new ItemStack(Items.gunpowder), Recipes.extractor.getRecipes(), null);
        }
        catch (Throwable t) {}
        
        GT_ModHandler.addSmeltingRecipe(GT_Items.Food_Potato_On_Stick.get(1L), GT_Items.Food_Potato_On_Stick_Roasted.get(1L));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(Items.slime_ball, 1), GT_Items.IC2_Resin.get(1L));
        GT_ModHandler.addSmeltingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Items.slime_ball, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rubber, 2L));
        GT_ModHandler.addExtractionRecipe(GT_Items.IC2_Resin.get(1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rubber, 3L));
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("rubberSapling", 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rubber, 1L));
        GT_ModHandler.addExtractionRecipe(GT_ModHandler.getIC2Item("rubberLeaves", 16L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rubber, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Cell_Air.get(1L), GT_Items.Cell_Empty.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Battery_SU_LV_SulfuricAcid.get(1L), GT_Items.Battery_Hull_LV.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Battery_SU_LV_Mercury.get(1L), GT_Items.Battery_Hull_LV.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Battery_SU_MV_SulfuricAcid.get(1L), GT_Items.Battery_Hull_MV.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Battery_SU_MV_Mercury.get(1L), GT_Items.Battery_Hull_MV.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Battery_RE_LV_Lithium.get(1L), GT_Items.Battery_Hull_LV.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Battery_RE_LV_Sodium.get(1L), GT_Items.Battery_Hull_LV.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Battery_RE_MV_Lithium.get(1L), GT_Items.Battery_Hull_MV.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Battery_RE_MV_Sodium.get(1L), GT_Items.Battery_Hull_MV.get(1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Plumbilia.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Lead, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Argentia.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Silver, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Indigo.get(1L), GregTech_API.getGregTechMaterial(9, 1));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Ferru.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Aurelia.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Gold, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_BobsYerUncleRanks.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Emerald, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_MilkWart.get(1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Milk, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Coppon.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Copper, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Tine.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tin, 1L));
       
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Plumbilia.get(8L), GT_ModHandler.getIC2Item("plantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Argentia.get(8L), GT_ModHandler.getIC2Item("plantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Indigo.get(8L), GT_ModHandler.getIC2Item("plantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Ferru.get(8L), GT_ModHandler.getIC2Item("plantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Aurelia.get(8L), GT_ModHandler.getIC2Item("plantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_OilBerry.get(8L), GT_ModHandler.getIC2Item("plantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_BobsYerUncleRanks.get(8L), GT_ModHandler.getIC2Item("plantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Coppon.get(4L), new ItemStack(Blocks.wool, 1, 1));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Tine.get(4L), GT_ModHandler.getIC2Item("plantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.IC2_Compressed_Coal_Chunk.get(1L), GT_Items.IC2_Industrial_Diamond.get(1L));
        
        GT_ModHandler.addCompressionRecipe(new UnifierRecipeEntry(OrePrefixes.dust, Materials.Wood, 8),  GT_OreDictUnificator.get(OrePrefixes.plank, Materials.Wood, 8L));
        GT_ModHandler.addCompressionRecipe(new UnifierRecipeEntry(OrePrefixes.dust, Materials.Uranium, 1), GT_ModHandler.getIC2Item("Uran238", 1L));
        GT_ModHandler.addCompressionRecipe(new UnifierRecipeEntry(OrePrefixes.dust, Materials.Uranium235, 1), GT_ModHandler.getIC2Item("Uran235", 1L));
        GT_ModHandler.addCompressionRecipe(new UnifierRecipeEntry(OrePrefixes.dust, Materials.Plutonium, 1), GT_ModHandler.getIC2Item("Plutonium", 1L));
        GT_ModHandler.addCompressionRecipe(new UnifierRecipeEntry(OrePrefixes.dustTiny, Materials.Uranium235, 1), GT_ModHandler.getIC2Item("smallUran235", 1L));
        GT_ModHandler.addCompressionRecipe(new UnifierRecipeEntry(OrePrefixes.dustTiny, Materials.Plutonium, 1), GT_ModHandler.getIC2Item("smallPlutonium", 1L));
        
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.dye, 1, 3), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chocolate, 1L));
        GT_ModHandler.addPulverisationRecipe(GT_Items.Crop_Drop_Tine.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2L));
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.blaze_rod, 1), new ItemStack(Items.blaze_powder, 3), new ItemStack(Items.blaze_powder, 1), 50, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.ice, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ice, 1L), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.glowstone, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 4L), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.redstone_lamp, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 4L), 90, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.lit_redstone_lamp, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 4L), 90, false);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getRCItem("cube.crushed.obsidian", 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 1L), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.reeds, 1), new ItemStack(Items.sugar, 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.clay, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Clay, 2L), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.hardened_clay, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Clay, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Clay, 1L), 50, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.stained_hardened_clay, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Clay, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Clay, 1L), 50, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.snow, 1), new ItemStack(Items.snowball, 4), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.stick, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2L), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.melon_block, 1, 0), new ItemStack(Items.melon, 8, 0), new ItemStack(Items.melon_seeds, 1), 80, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.pumpkin, 1, 0), new ItemStack(Items.pumpkin_seeds, 4, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.melon, 1, 0), new ItemStack(Items.melon_seeds, 16, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.wheat, 1, 0), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wheat, 1L), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("plantBall", 1L), new ItemStack(Blocks.dirt, 1, 0), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("crop", 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Items.flint, 1), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Flint, 4L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Flint, 1L), 40, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Blocks.wool, 1, 32767), new ItemStack(Items.string, 2), new ItemStack(Items.string, 1), 50, false);
        
        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1000).EUt(120).duration(500).input(OrePrefixes.ingot, Materials.Iron).input(OrePrefixes.gem, Materials.Coal, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L)).buildAndRegister();
        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1100).EUt(120).duration(500).input(OrePrefixes.ingot, Materials.ShadowIron, 1).input(OrePrefixes.gem, Materials.Coal, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.ShadowSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L)).buildAndRegister();
        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1200).EUt(120).duration(500).input(OrePrefixes.ingot, Materials.MeteoricIron, 1).input(OrePrefixes.gem, Materials.Coal, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.MeteoricSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L)).buildAndRegister();
        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(3000).EUt(500).duration(500).input(OrePrefixes.ingot, Materials.Tungsten).input(OrePrefixes.ingot, Materials.Steel).outputs(GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.TungstenSteel, 2L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4L)).buildAndRegister();
        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1700).EUt(120).duration(800).input(OrePrefixes.dust, Materials.Ilmenite, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Titanium, 1L)).buildAndRegister();
//        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1700).EUt(120).duration(640).input(OrePrefixes.gem, Materials.Ilmenite, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Titanium, 1L)).buildAndRegister();
        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1500).EUt(120).duration(400).input(OrePrefixes.dust, Materials.Galena, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Silver, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Lead, 1L)).buildAndRegister();
//        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1500).EUt(120).duration(320).input(OrePrefixes.gem, Materials.Galena, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Silver, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Lead, 1L)).buildAndRegister();
        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1000).EUt(120).duration(400).input(OrePrefixes.dust, Materials.Magnetite, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2)).buildAndRegister();
//        RecipeMaps.BLAST_FURNANCE.factory().minTemperature(1000).EUt(120).duration(320).input(OrePrefixes.gem, Materials.Magnetite, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 2)).buildAndRegister();
        
        RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(7200).input(OrePrefixes.ingot, Materials.Iron).input(OrePrefixes.gem, Materials.Coal, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4)).buildAndRegister();
        RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(7200).input(OrePrefixes.dust, Materials.Iron).input(OrePrefixes.gem, Materials.Coal, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4)).buildAndRegister();
        RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(64800).input(OrePrefixes.ingot, Materials.Iron, 9).input(OrePrefixes.block, Materials.Coal, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 36)).buildAndRegister();
    	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(64800).input(OrePrefixes.dust, Materials.Iron, 9).input(OrePrefixes.block, Materials.Coal, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 36)).buildAndRegister();
    	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(3600).input(OrePrefixes.dust, Materials.Steel).input(OrePrefixes.gem, Materials.Coal, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2)).buildAndRegister();
    	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(32400).input(OrePrefixes.dust, Materials.Steel, 9).input(OrePrefixes.block, Materials.Coal, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 18)).buildAndRegister();
    	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(64800).input(OrePrefixes.block, Materials.Iron).input(OrePrefixes.gem, Materials.Coal, 36).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 36)).buildAndRegister();
    	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(64800).input(OrePrefixes.block, Materials.Iron).input(OrePrefixes.block, Materials.Coal, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 36)).buildAndRegister();
        if (OreDictionary.doesOreNameExist("fuelCoke")) {
        	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(4800).input(OrePrefixes.ingot, Materials.Iron).input("fuelCoke", 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4)).buildAndRegister();
        	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(4800).input(OrePrefixes.dust, Materials.Iron).input("fuelCoke", 2).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4)).buildAndRegister();
        	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(2400).input(OrePrefixes.dust, Materials.Steel).input("fuelCoke").outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2)).buildAndRegister();
        	RecipeMaps.BRONZE_BLAST_FURNANCE.factory().EUt(1).duration(43200).input(OrePrefixes.block, Materials.Iron).input("fuelCoke", 18).outputs(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 9), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 36)).buildAndRegister();
        }
        
        RecipeMaps.CANINNING.factory().EUt(2).duration( 100).input(OrePrefixes.cell, Materials.Mercury		,  1).input(GT_Items.Battery_Hull_LV.get(1)).outputs(GT_Items.Battery_SU_LV_Mercury			.getWithCharge(1, Integer.MAX_VALUE), GT_Items.Cell_Empty.get( 1)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration( 400).input(OrePrefixes.cell, Materials.SulfuricAcid	,  4).inputs(GT_Items.Battery_Hull_MV.get(1)).outputs(GT_Items.Battery_SU_MV_SulfuricAcid	.getWithCharge(1, Integer.MAX_VALUE), GT_Items.Cell_Empty.get( 4)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration( 400).input(OrePrefixes.cell, Materials.Mercury		,  4).inputs(GT_Items.Battery_Hull_MV.get(1)).outputs(GT_Items.Battery_SU_MV_Mercury		.getWithCharge(1, Integer.MAX_VALUE), GT_Items.Cell_Empty.get( 4)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration(1600).input(OrePrefixes.cell, Materials.Nitrogen		, 16).inputs(GT_Items.Spray_Empty.get(1))	   .outputs(GT_Items.Spray_Ice					.get(1)								, GT_Items.Cell_Empty.get(16)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration(1600).inputs(GT_Items.Cell_Water.get(16)				, GT_Items.Spray_Empty.get(1))	   				.outputs(GT_Items.Spray_Hydration			.get(1)								, GT_Items.Cell_Empty.get(16)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration(1600).input(OrePrefixes.cell, Materials.ConstructionFoam, 40).input(GT_Items.Spray_Empty.get(1))	   .outputs(GT_Items.Spray_CFoam				.get(1)								, GT_Items.Cell_Empty.get(40)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration( 100).input(OrePrefixes.dust, Materials.Lithium, 15).input(GT_Items.Battery_Hull_LV.get(1)) .output(GT_Items.Battery_RE_LV_Lithium				.get(1)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration( 100).input(OrePrefixes.dust, Materials.Sodium,  4).input(GT_Items.Battery_Hull_LV.get(1)) .output(GT_Items.Battery_RE_LV_Sodium					.get(1)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration( 400).input(OrePrefixes.dust, Materials.Lithium, 60).input(GT_Items.Battery_Hull_MV.get(1)) .output(GT_Items.Battery_RE_MV_Lithium				.get(1)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration( 400).input(OrePrefixes.dust, Materials.Sodium,  16).input(GT_Items.Battery_Hull_MV.get(1)) .output(GT_Items.Battery_RE_MV_Sodium		.			get(1)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration(1600).inputs(new ItemStack(Blocks.sand, 16, 0)								 			 , GT_Items.Spray_Empty.get(1))	    .output(GT_Items.Spray_Hardener				.get(1)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(2).duration( 100).input(OrePrefixes.dust, Materials.Redstone, 2).input(GT_Items.Battery_Hull_LV.get(1)) .output(GT_ModHandler.getIC2Item("reBattery", 1))	.buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(1).duration( 800).inputs(GT_Items.IC2_Grin_Powder.get(1)									  	 		 , GT_Items.Spray_Empty.get(1)) 	.output(GT_ModHandler.getIC2Item("weedEx", 1L))		.buildAndRegister();
        for (final Dyes tDye : Dyes.values())
            if (tDye != Dyes._NULL) {
                for (final ItemStack tIteratedStack : GT_OreDictUnificator.getOres(tDye.toString())) {
                    if (tIteratedStack.getMaxStackSize() >= 16 && GT_Utility.getContainerItem(tIteratedStack) == null) {
                        RecipeMaps.CANINNING.factory().EUt(1).duration(800).inputs(GT_Utility.copyAmount(16, tIteratedStack), GT_Items.Spray_Empty.get(1L)).output(GT_Items.SPRAY_CAN_DYES[tDye.mColor].get(1)).buildAndRegister();
                    }
                }
            }
        RecipeMaps.CANINNING.factory().EUt(1).duration(100).inputs(GT_Items.Crop_Drop_OilBerry.get(4L), GT_Items.Cell_Empty.get(1L)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil, 1L)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(1).duration(100).inputs(GT_ModHandler.getIC2Item("plantBall", 1L), GT_Items.Cell_Empty.get(1L)).output(GT_ModHandler.getIC2Item("biomassCell", 1L)).buildAndRegister();
//        RecipeMaps.CANINNING.factory().EUt(1).duration(50).inputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.BioFuel, 1L), GT_Items.Fuel_Can_Plastic_Empty.get(1L)).outputs(GT_ModHandler.setFuelValue(GT_Items.Fuel_Can_Plastic_Filled.get(1L), (short)(Materials.BioFuel.mFuelPower * 400)), GT_Items.Cell_Empty.get(1L)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(1).duration(100).inputs(new ItemStack(Items.lava_bucket), GT_Items.Cell_Empty.get(1L)).outputs(GT_Items.Cell_Lava.get(1L), new ItemStack(Items.bucket, 1)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(1).duration(100).inputs(new ItemStack(Items.water_bucket), GT_Items.Cell_Empty.get(1L)).outputs(GT_Items.Cell_Water.get(1L), new ItemStack(Items.bucket, 1)).buildAndRegister();
        
        RecipeMaps.CANINNING.factory().EUt(8).duration(100).inputs(GT_Items.IC2_Fuel_Rod_Empty.get(1), GT_ModHandler.getIC2Item("UranFuel", 1)).output(GT_ModHandler.getIC2Item("reactorUraniumSimple", 1)).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(8).duration(100).inputs(GT_Items.IC2_Fuel_Rod_Empty.get(1), GT_ModHandler.getIC2Item("MOXFuel", 1)).output(GT_ModHandler.getIC2Item("reactorMOXSimple", 1)).buildAndRegister();
        
        ItemStack constCell = GT_Utility.fillFluidContainer(GT_Utility.copy(Materials.ConstructionFoam.mFluid, 1000), GT_Items.Cell_Empty.get(1));
        RecipeEntry entry = RecipeEntry.fromStacks(10, Lists.newArrayList(
        		GT_ModHandler.getIC2Item("ironScaffold", 1),
        		GT_Items.Frame_Aluminium.get(1),
        		GT_Items.Frame_Iron.get(1),
        		GT_Items.Frame_StainlessSteel.get(1),
        		GT_Items.Frame_Steel.get(1),
        		GT_Items.Frame_TungstenSteel.get(1)));
        
        RecipeMaps.CANINNING.factory().EUt(4).duration(200).input(GT_Items.Cell_Water.get(1)).input(GT_ModHandler.getIC2Item("constructionFoamPowder", 1)).output(constCell).buildAndRegister();
        RecipeMaps.CANINNING.factory().EUt(8).duration(100).input(constCell).input(entry).outputs(GT_ModHandler.getIC2Item("reinforcedStone", 10), GT_Items.Cell_Empty.get(1)).buildAndRegister();
        
        RecipeMaps.FUSION_REACTOR.factory().EUt(4096).startEU(40000000).duration(128).inputs(GT_Utility.copy(Materials.Deuterium.mGas, 1000), GT_Utility.copy(Materials.Tritium.mGas, 1000)).output(GT_Utility.copy(Materials.Helium.mPlasma, 1000)).buildAndRegister();
        RecipeMaps.FUSION_REACTOR.factory().EUt(2048).startEU(60000000).duration(128).inputs(GT_Utility.copy(Materials.Deuterium.mGas, 1000), GT_Utility.copy(Materials.Helium_3.mGas, 1000)).output(GT_Utility.copy(Materials.Helium.mPlasma, 1000)).buildAndRegister();
        RecipeMaps.FUSION_REACTOR.factory().EUt(32768).startEU(150000000).duration(512).inputs(GT_Utility.copy(Materials.Lithium.mFluid, 1000), GT_Utility.copy(Materials.Tungsten.mSolid, 1000)).output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iridium, 1L)).buildAndRegister();
        RecipeMaps.FUSION_REACTOR.factory().EUt(32768).startEU(100000000).duration(512).inputs(GT_Utility.copy(Materials.Beryllium.mSolid, 1000), GT_Utility.copy(Materials.Tungsten.mSolid, 1000)).output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Platinum, 1L)).buildAndRegister();
        
        RecipeMaps.IMPLOSION_COMPRESSOR.factory().EUt(30).duration(20).setShaped(true).input(GregTech_API.getGregTechMaterial(4, 1)).input(GT_ModHandler.getIC2Item("industrialTnt", 8)).outputs(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Iridium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4L)).buildAndRegister();
        RecipeMaps.IMPLOSION_COMPRESSOR.factory().EUt(30).duration(20).setShaped(true).input(GT_Items.IC2_Compressed_Coal_Chunk.get(1)).input(GT_ModHandler.getIC2Item("industrialTnt", 8)).outputs(GT_Items.IC2_Industrial_Diamond.get(1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4L)).buildAndRegister();
        
        RecipeMaps.DISTILLATION.factory().EUt(64).duration(4000).setShaped(true).input(OrePrefixes.cell, Materials.Oil, 16).input(GT_Items.Cell_Empty.get(32)).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Fuel, 16L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SulfuricAcid, 16L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glyceryl, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 15L)).buildAndRegister();
        RecipeMaps.DISTILLATION.factory().EUt(64).duration(500).setShaped(true).input(OrePrefixes.cell, Materials.Biomass, 3).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Ethanol, 1L), GT_Items.Cell_Water.get(1L), GT_Items.Cell_Empty.get(1L), GT_Items.IC2_Fertilizer.get(1L)).buildAndRegister();
        
        RecipeMaps.ELECTROLYZER.factory().EUt(120).duration(215).input(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 6L)).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 4L), GT_Items.Cell_Air.get(1L), GT_Items.Cell_Empty.get(1L)).buildAndRegister();
        RecipeMaps.ELECTROLYZER.factory().EUt(120).duration(215).input(GT_Items.Cell_Empty.get(1L)).input(GT_ModHandler.getWater(1000)).output(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1L)).buildAndRegister();
        RecipeMaps.ELECTROLYZER.factory().EUt(120).duration(215).input(GT_Items.Cell_Water.get(1L)).output(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1L)).buildAndRegister();
        RecipeMaps.ELECTROLYZER.factory().EUt(120).duration(215).inputs(new ItemStack(Items.water_bucket, 1), GT_Items.Cell_Empty.get(1)).output(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1L)).output(new ItemStack(Items.bucket)).buildAndRegister();
        RecipeMaps.ELECTROLYZER.factory().EUt(106).duration(24).input(new ItemStack(Items.dye, 3, 15)).output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1)).buildAndRegister();
        RecipeMaps.ELECTROLYZER.factory().EUt(25).duration(500).input("sand", 8).outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.SiliconDioxide, 1L)).buildAndRegister();
        
        if (Loader.isModLoaded("appliedenergistics2")) {
        	Optional<ItemStack> chargedCrystal = appeng.api.AEApi.instance()
        			.definitions()
        			.materials()
        			.certusQuartzCrystalCharged()
        			.maybeStack(1);
        	
        	if (chargedCrystal.isPresent()) {
            	RecipeMaps.ELECTROLYZER.factory()
            		.EUt(30).duration(200)
            		.input(OrePrefixes.gem, Materials.CertusQuartz, 1)
            		.outputs(chargedCrystal.get())
            		.buildAndRegister();        		
        	}
        }
       
        
        GT_ModHandler.removeRecipeByOutput(GT_Items.IC2_Fertilizer.get(1L));
        
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 500).input(OrePrefixes.dust, Materials.NetherQuartz	, 3).input(OrePrefixes.dust, Materials.Sodium		, 1).output(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.NetherQuartz, 1L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 500).input(OrePrefixes.dust, Materials.CertusQuartz	, 3).input(OrePrefixes.dust, Materials.Sodium		, 1).output(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.CertusQuartz, 1L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 500).input(OrePrefixes.dust, Materials.Quartzite	, 3).input(OrePrefixes.dust, Materials.Sodium		, 1).output(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Quartzite, 1L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(1000).input(OrePrefixes.dust, Materials.Uraninite	, 1).input(OrePrefixes.dust, Materials.Aluminium	, 1).output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(1000).input(OrePrefixes.dust, Materials.Uraninite	, 1).input(OrePrefixes.dust, Materials.Magnesium	, 1).output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 250).input(OrePrefixes.dust, Materials.Calcium		, 1).input(OrePrefixes.dust, Materials.Carbon		, 1).output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 200).input(OrePrefixes.dust, Materials.Calcite		, 1).input(OrePrefixes.dust, Materials.Sulfur		, 1).output(GT_Items.IC2_Fertilizer.get(2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 300).input(OrePrefixes.dust, Materials.Calcite		, 1).input(OrePrefixes.dust, Materials.Phosphorus	, 1).output(GT_Items.IC2_Fertilizer.get(3L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 200).input(OrePrefixes.dust, Materials.Calcite		, 1).input(OrePrefixes.dust, Materials.Phosphate	, 1).output(GT_Items.IC2_Fertilizer.get(2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 100).input(OrePrefixes.dust, Materials.Calcite		, 1).input(OrePrefixes.dust, Materials.Ash			, 3).output(GT_Items.IC2_Fertilizer.get(1L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 100).input(OrePrefixes.dust, Materials.Calcite		, 1).input(OrePrefixes.dust, Materials.DarkAsh		, 1).output(GT_Items.IC2_Fertilizer.get(1L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 300).input(OrePrefixes.dust, Materials.Calcium		, 1).input(OrePrefixes.dust, Materials.Sulfur		, 1).output(GT_Items.IC2_Fertilizer.get(3L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 400).input(OrePrefixes.dust, Materials.Calcium		, 1).input(OrePrefixes.dust, Materials.Phosphorus	, 1).output(GT_Items.IC2_Fertilizer.get(4L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 300).input(OrePrefixes.dust, Materials.Calcium		, 1).input(OrePrefixes.dust, Materials.Phosphate	, 1).output(GT_Items.IC2_Fertilizer.get(3L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 200).input(OrePrefixes.dust, Materials.Calcium		, 1).input(OrePrefixes.dust, Materials.Ash			, 3).output(GT_Items.IC2_Fertilizer.get(2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 200).input(OrePrefixes.dust, Materials.Calcium		, 1).input(OrePrefixes.dust, Materials.DarkAsh		, 1).output(GT_Items.IC2_Fertilizer.get(2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 300).input(OrePrefixes.dust, Materials.Apatite		, 1).input(OrePrefixes.dust, Materials.Sulfur		, 1).output(GT_Items.IC2_Fertilizer.get(3L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 400).input(OrePrefixes.dust, Materials.Apatite		, 1).input(OrePrefixes.dust, Materials.Phosphorus	, 1).output(GT_Items.IC2_Fertilizer.get(4L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 300).input(OrePrefixes.dust, Materials.Apatite		, 1).input(OrePrefixes.dust, Materials.Phosphate	, 1).output(GT_Items.IC2_Fertilizer.get(3L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 200).input(OrePrefixes.dust, Materials.Apatite		, 1).input(OrePrefixes.dust, Materials.Ash			, 3).output(GT_Items.IC2_Fertilizer.get(2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 200).input(OrePrefixes.dust, Materials.Apatite		, 1).input(OrePrefixes.dust, Materials.DarkAsh		, 1).output(GT_Items.IC2_Fertilizer.get(2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 300).input(OrePrefixes.dust, Materials.Glauconite	, 1).input(OrePrefixes.dust, Materials.Sulfur		, 1).output(GT_Items.IC2_Fertilizer.get(3L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 400).input(OrePrefixes.dust, Materials.Glauconite	, 1).input(OrePrefixes.dust, Materials.Phosphorus	, 1).output(GT_Items.IC2_Fertilizer.get(4L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 400).input(OrePrefixes.dust, Materials.Glauconite	, 1).input(OrePrefixes.dust, Materials.Phosphate	, 1).output(GT_Items.IC2_Fertilizer.get(3L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 200).input(OrePrefixes.dust, Materials.Glauconite	, 1).input(OrePrefixes.dust, Materials.Ash			, 3).output(GT_Items.IC2_Fertilizer.get(2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 200).input(OrePrefixes.dust, Materials.Glauconite	, 1).input(OrePrefixes.dust, Materials.DarkAsh		, 1).output(GT_Items.IC2_Fertilizer.get(2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(  50).input(OrePrefixes.nugget, Materials.Gold		, 8).input(new ItemStack(Items.melon, 1, 32767)).output(new ItemStack(Items.speckled_melon, 1, 0)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(  50).input(OrePrefixes.nugget, Materials.Gold		, 8).input(new ItemStack(Items.carrot, 1, 32767)).output(new ItemStack(Items.golden_carrot, 1, 0)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(  50).input(OrePrefixes.ingot, Materials.Gold		, 8).input(new ItemStack(Items.apple, 1, 32767)).output(new ItemStack(Items.golden_apple, 1, 0)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(  50).input(OrePrefixes.block, Materials.Gold		, 8).input(new ItemStack(Items.apple, 1, 32767)).output(new ItemStack(Items.golden_apple, 1, 1)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(  50).input(OrePrefixes.dust, Materials.Blaze		, 1).input(OrePrefixes.gem, Materials.EnderPearl, 1).output(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderEye, 1L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(  50).input(OrePrefixes.dust, Materials.Blaze		, 1).input(new ItemStack(Items.slime_ball, 1, 32767)).output(new ItemStack(Items.magma_cream, 1, 0)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(3500).input(OrePrefixes.cell, Materials.Carbon		, 1).input(OrePrefixes.cell, Materials.Hydrogen, 4).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 5L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(1500).input(OrePrefixes.cell, Materials.Nitrogen		, 1).input(OrePrefixes.cell, Materials.Carbon, 1).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitroCarbon, 2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(1000).input(OrePrefixes.cell, Materials.Glyceryl		, 1).input(OrePrefixes.cell, Materials.Fuel, 4).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitroFuel, 5L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(1250).input(OrePrefixes.cell, Materials.Nitrogen		, 1).input(GT_Items.Cell_Air.get(1L)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitrogenDioxide, 2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(4000).input(OrePrefixes.cell, Materials.SodiumSulfide, 2).input(GT_Items.Cell_Air.get(2L)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, 4L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(1750).input(OrePrefixes.cell, Materials.NitroCarbon	, 3).input(GT_Items.Cell_Water.get(3L)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glyceryl, 6L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration( 100).input(OrePrefixes.cell, Materials.Sulfur		, 1).input(OrePrefixes.cell, Materials.Sodium, 1).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumSulfide, 2L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(1150).input(OrePrefixes.cell, Materials.Sulfur		, 1).input(GT_Items.Cell_Water.get(2L)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SulfuricAcid, 3L)).buildAndRegister();
        RecipeMaps.CHEMICAL.factory().EUt(30).duration(  10).input(OrePrefixes.cell, Materials.Hydrogen		, 4).input(GT_Items.Cell_Air.get(1L)).output(GT_Items.Cell_Water.get(5L)).buildAndRegister();
        
        RecipeMaps.BENDING.factory().EUt( 8).duration( 100).setShaped(true).input(GT_Items.IC2_Mixed_Metal_Ingot.get(1)).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 1)).output(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Advanced, 1L)).buildAndRegister();
        if (Loader.isModLoaded("Railcraft")) {
	        RecipeMaps.BENDING.factory().EUt(15).duration( 200).setShaped(true).input(OrePrefixes.stick, Materials.Aluminium, 6).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 6)).output(GT_ModHandler.getRCItem("part.rail.standard", 2L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration( 400).setShaped(true).input(OrePrefixes.stick, Materials.Iron, 6).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 6)).output(GT_ModHandler.getRCItem("part.rail.standard", 4L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration( 300).setShaped(true).input(OrePrefixes.stick, Materials.Bronze, 6).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 6)).output(GT_ModHandler.getRCItem("part.rail.standard", 3L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration( 800).setShaped(true).input(OrePrefixes.stick, Materials.Steel, 6).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 6)).output(GT_ModHandler.getRCItem("part.rail.standard", 8L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration(1200).setShaped(true).input(OrePrefixes.stick, Materials.StainlessSteel, 6).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 6)).output(GT_ModHandler.getRCItem("part.rail.standard", 12L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration(1600).setShaped(true).input(OrePrefixes.stick, Materials.Titanium, 6).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 6)).output(GT_ModHandler.getRCItem("part.rail.standard", 16L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(30).duration(2400).setShaped(true).input(OrePrefixes.stick, Materials.TungstenSteel, 6).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 6)).output(GT_ModHandler.getRCItem("part.rail.reinforced", 24L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration( 200).setShaped(true).input(OrePrefixes.stick, Materials.Aluminium, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output(GT_ModHandler.getRCItem("part.rebar", 4L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration( 400).setShaped(true).input(OrePrefixes.stick, Materials.Iron, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output( GT_ModHandler.getRCItem("part.rebar", 8L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration( 400).setShaped(true).input(OrePrefixes.stick, Materials.Bronze, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output(GT_ModHandler.getRCItem("part.rebar", 8L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration( 800).setShaped(true).input(OrePrefixes.stick, Materials.Steel, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output(GT_ModHandler.getRCItem("part.rebar", 16L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration(1200).setShaped(true).input(OrePrefixes.stick, Materials.StainlessSteel, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output( GT_ModHandler.getRCItem("part.rebar", 24L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration(1600).setShaped(true).input(OrePrefixes.stick, Materials.Titanium, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output(GT_ModHandler.getRCItem("part.rebar", 32L)).buildAndRegister();
	        RecipeMaps.BENDING.factory().EUt(15).duration(2400).setShaped(true).input(OrePrefixes.stick, Materials.TungstenSteel, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output(GT_ModHandler.getRCItem("part.rebar", 48L)).buildAndRegister();
        }
        RecipeMaps.BENDING.factory().EUt( 8).duration(1200).setShaped(true).input(OrePrefixes.plate, Materials.Tin, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output(GT_Items.Cell_Empty.get(6L)).buildAndRegister();
        RecipeMaps.BENDING.factory().EUt( 4).duration( 800).setShaped(true).input(OrePrefixes.plate, Materials.Iron, 12).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 12)).output(new ItemStack(Items.bucket, 4, 0)).buildAndRegister();
        RecipeMaps.BENDING.factory().EUt( 8).duration( 100).setShaped(true).input(GT_ModHandler.getIC2Item("casingiron", 2L)).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 2)).output(GT_ModHandler.getIC2Item("fuelRod", 1L)).buildAndRegister();
        RecipeMaps.BENDING.factory().EUt( 8).duration( 100).setShaped(true).input(GT_ModHandler.getIC2Item("casingtin", 1L)).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 1)).output(GT_Items.IC2_Food_Can_Empty.get(1L)).buildAndRegister();
        
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(100).input(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1, 32767)).output(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(300).input(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1L, 32767)).output(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(600).input(GT_ModHandler.getIC2Item("reactorCoolantSix", 1L, 32767)).output(GT_ModHandler.getIC2Item("reactorCoolantSix", 1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(700).input(GT_Items.Reactor_Coolant_He_1.getWildcard(1L)).output(GT_Items.Reactor_Coolant_He_1.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(2000).input(GT_Items.Reactor_Coolant_He_3.getWildcard(1L)).output(GT_Items.Reactor_Coolant_He_3.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(3900).input(GT_Items.Reactor_Coolant_He_6.getWildcard(1L)).output(GT_Items.Reactor_Coolant_He_6.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(500).input(GT_Items.Reactor_Coolant_NaK_1.getWildcard(1L)).output(GT_Items.Reactor_Coolant_NaK_1.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(1500).input(GT_Items.Reactor_Coolant_NaK_3.getWildcard(1L)).output(GT_Items.Reactor_Coolant_NaK_3.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(3000).input(GT_Items.Reactor_Coolant_NaK_6.getWildcard(1L)).output(GT_Items.Reactor_Coolant_NaK_6.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.VACUUM_FREEZER.factory().EUt(120).duration(50).input(GT_Items.Cell_Water.get(1L)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Ice, 1L)).buildAndRegister();
        
        RecipeMaps.ALLOY_SMELTING.factory().EUt(1).duration(100).inputs(new ItemStack(Items.water_bucket, 1, 0)).output(new ItemStack(Items.bucket, 1, 0)).buildAndRegister();
        RecipeMaps.ALLOY_SMELTING.factory().EUt(1).duration(100).inputs(new ItemStack(Items.milk_bucket, 1, 0)).output(new ItemStack(Items.bucket, 1, 0)).buildAndRegister();
        if (Loader.isModLoaded("ThermalExpansion")) {
	        RecipeMaps.ALLOY_SMELTING.factory().EUt(16).duration(200).input(OrePrefixes.dust , Materials.Lead, 1).input(OrePrefixes.dust, Materials.Obsidian, 2).output(GT_Items.TE_Hardened_Glass.get(2L)).buildAndRegister();
	        RecipeMaps.ALLOY_SMELTING.factory().EUt(16).duration(200).input(OrePrefixes.ingot, Materials.Lead, 1).input(OrePrefixes.dust, Materials.Obsidian, 2).output(GT_Items.TE_Hardened_Glass.get(2L)).buildAndRegister();
        }
        
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input("glowstone").output(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Glowstone, 4L)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input("blockGlass", 3).output(new ItemStack(Blocks.glass_pane, 8, 0)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(new ItemStack(Blocks.wooden_slab, 1, 0)).output(GregTech_API.getGregTechMaterial(60, 2)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(new ItemStack(Blocks.wooden_slab, 1, 1)).output(GregTech_API.getGregTechMaterial(61, 2)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(new ItemStack(Blocks.wooden_slab, 1, 2)).output(GregTech_API.getGregTechMaterial(62, 2)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(new ItemStack(Blocks.wooden_slab, 1, 3)).output(GregTech_API.getGregTechMaterial(63, 2)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(OrePrefixes.plate, Materials.Iron, 1).output(GT_ModHandler.getIC2Item("casingiron", 2L)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(OrePrefixes.plate, Materials.Gold, 1).output(GT_ModHandler.getIC2Item("casinggold", 2L)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(OrePrefixes.plate, Materials.Bronze, 1).output(GT_ModHandler.getIC2Item("casingbronze", 2L)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(OrePrefixes.plate, Materials.Copper, 1).output(GT_ModHandler.getIC2Item("casingcopper", 2L)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(OrePrefixes.plate, Materials.Tin, 1).output(GT_ModHandler.getIC2Item("casingtin", 2L)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(OrePrefixes.plate, Materials.Lead, 1).output(GT_ModHandler.getIC2Item("casinglead", 2L)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(OrePrefixes.plate, Materials.Cupronickel, 1).output(GT_Items.Credit_Greg_Cupronickel.get(4L)).buildAndRegister();
        RecipeMaps.CUTTING.factory().EUt(16).duration(100).input(OrePrefixes.plate, Materials.Brass, 1).output(GT_Items.Coin_Doge.get(4L)).buildAndRegister();
        
        RecipeMaps.WIREMILL.factory().EUt(2).duration(100).input(OrePrefixes.ingot, Materials.Copper).output(GT_ModHandler.getIC2Item("copperCableItem", 3L)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(1).duration(150).input(OrePrefixes.ingot, Materials.Tin).output(GT_ModHandler.getIC2Item("tinCableItem", 4L)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(4).duration(100).input(OrePrefixes.ingot, Materials.SolderingAlloy).output(GT_Items.Tool_SolderingMaterial_Tin.get(1L)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(2).duration(200).input(OrePrefixes.ingot, Materials.Iron).output(GT_ModHandler.getIC2Item("ironCableItem", 6L)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(1).duration(200).input(OrePrefixes.ingot, Materials.Gold).output(GT_ModHandler.getIC2Item("goldCableItem", 6L)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(4).duration(100).input(OrePrefixes.ingot, Materials.Lead).output(GT_Items.Tool_SolderingMaterial_Lead.get(1L)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(12).duration(450).input(OrePrefixes.ingot, Materials.Kanthal).output(GregTech_API.getGregTechComponent(19, 1)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(16).duration(600).input(OrePrefixes.ingot, Materials.Nichrome).output(GregTech_API.getGregTechComponent(20, 1)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(8).duration(300).input(OrePrefixes.ingot, Materials.Cupronickel, 3).output(GregTech_API.getGregTechComponent(21, 1)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(2).duration(400).input(OrePrefixes.dust, Materials.Carbon).output(GT_ModHandler.getIC2Item("carbonFiber", 1L)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(2).duration(400).input(OrePrefixes.dust, Materials.Charcoal).output(GT_ModHandler.getIC2Item("carbonFiber", 1L)).buildAndRegister();
        RecipeMaps.WIREMILL.factory().EUt(2).duration(400).input(OrePrefixes.dust, Materials.Coal).output(GT_ModHandler.getIC2Item("carbonFiber", 1L)).buildAndRegister();
        
        if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
            RecipeMaps.ASSEMBLING.factory().EUt(1).duration(400).input(OrePrefixes.stick, Materials.Wood).input(OrePrefixes.gem, Materials.Coal).output(new ItemStack(Blocks.torch, 4)).buildAndRegister();
        }
        
        RecipeMaps.ASSEMBLING.factory().EUt(2).duration(200).inputs(new ItemStack(Items.string, 4, 32767), new ItemStack(Items.slime_ball, 1, 32767)).output(new ItemStack(Items.lead, 2)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(400).inputs(GT_Items.IC2_Compressed_Coal_Ball.get(8L), new ItemStack(Blocks.brick_block, 1)).output(GT_Items.IC2_Compressed_Coal_Chunk.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(2).duration(1600).inputs(GregTech_API.getGregTechComponent(49, 1), GregTech_API.getGregTechComponent(24, 2)).output(GT_Items.Circuit_Advanced.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(3200).inputs(GregTech_API.getGregTechComponent(50, 1), GregTech_API.getGregTechComponent(3, 1)).output(GregTech_API.getGregTechComponent(1, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(3200).inputs(GregTech_API.getGregTechComponent(50, 1), GT_ModHandler.getIC2Item("lapotronCrystal", 1, 32767)).output(GregTech_API.getGregTechComponent(0, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(12800).inputs(GregTech_API.getGregTechComponent(1, 1), GregTech_API.getGregTechComponent(3, 8)).output(GT_Items.Tool_DataOrb.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(8).duration(6400).inputs(new ItemStack(GregTech_API.sBlockList[1], 1, 6), GregTech_API.getGregTechComponent(3, 4)).output(GT_Items.Tool_Sonictron.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(8).duration(6400).input(GT_ModHandler.getIC2Item("waterMill", 2L)).output(GT_ModHandler.getIC2Item("generator", 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(3200).input(GT_Items.Armor_LithiumPack.getWildcard(1L)).output(GT_Items.Battery_RE_LV_Lithium.get(6L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(2).duration(1600).input(GT_ModHandler.getIC2Item("batPack", 1, 32767)).output(GT_ModHandler.getIC2Item("reBattery", 6L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(3200).inputs(GT_ModHandler.getIC2Item("hvTransformer", 1L), GT_ModHandler.getIC2Item("transformerUpgrade", 1L)).output(GregTech_API.getGregTechComponent(27, 1)).buildAndRegister();
        
        if (Loader.isModLoaded("Railcraft")) {
	        RecipeMaps.ASSEMBLING.factory().EUt(8).duration(128).inputs(new ItemStack(Blocks.stone_slab, 3, 0), GT_ModHandler.getRCItem("part.rebar", 1L)).output(GT_ModHandler.getRCItem("part.tie.stone", 1L)).buildAndRegister();
	        RecipeMaps.ASSEMBLING.factory().EUt(8).duration(128).inputs(new ItemStack(Blocks.stone_slab, 3, 7), GT_ModHandler.getRCItem("part.rebar", 1L)).output(GT_ModHandler.getRCItem("part.tie.stone", 1L)).buildAndRegister();
	        RecipeMaps.ASSEMBLING.factory().EUt(1).duration(800).input(GT_ModHandler.getRCItem("part.tie.wood", 4L)).output(GT_ModHandler.getRCItem("part.railbed.wood", 1L)).buildAndRegister();
	        RecipeMaps.ASSEMBLING.factory().EUt(1).duration(800).input(GT_ModHandler.getRCItem("part.tie.stone", 4L)).output(GT_ModHandler.getRCItem("part.railbed.stone", 1L)).buildAndRegister();
        
	        ItemStack tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1L);
	        ItemStack tStack3;
	        
	        RecipeMaps.ASSEMBLING.factory().EUt(1).duration(1600).inputs(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.wood"		, 6L)).output(GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3)).buildAndRegister();
	        RecipeMaps.ASSEMBLING.factory().EUt(1).duration(1600).inputs(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.standard"	, 6L)).output(GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3)).buildAndRegister();
	        RecipeMaps.ASSEMBLING.factory().EUt(1).duration(1600).inputs(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.advanced"	, 6L)).output(GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, new ItemStack(Items.redstone), tStack3)).buildAndRegister();
	        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1L);
	        RecipeMaps.ASSEMBLING.factory().EUt(1).duration(1600).inputs(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.reinforced"	, 6L)).output(GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3)).buildAndRegister();
	        RecipeMaps.ASSEMBLING.factory().EUt(1).duration(1600).inputs(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.speed"		, 6L)).output(GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3)).buildAndRegister();
        }
        
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration(3200).inputs(GregTech_API.getGregTechComponent(5, 1), GregTech_API.getGregTechComponent(6, 1)).output(GregTech_API.getGregTechComponent(8, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 800).inputs(GT_ModHandler.getIC2Item("carbonFiber", 2L)).output(GT_ModHandler.getIC2Item("carbonMesh", 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration(1600).inputs(GT_ModHandler.getIC2Item("carbonMesh", 16L)).output(GT_Items.Component_LavaFilter.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).inputs(GT_Items.Circuit_Basic.get(1L), GT_ModHandler.getIC2Item("frequencyTransmitter", 1L)).output(GT_Items.NC_SensorKit.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).inputs(GT_Items.NC_SensorCard.getWithDamage(1L, 0L)).output(GT_Items.Circuit_Basic.get(2L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(GT_ModHandler.getIC2Item("pump", 1L)).output(GregTech_API.getGregTechComponent(6, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1, 32767)).output(GregTech_API.getGregTechComponent(11, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(new ItemStack(Blocks.light_weighted_pressure_plate, 1, 32767)).output(GregTech_API.getGregTechComponent(10, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(GT_ModHandler.getIC2Item("ecMeter", 1L)).output(GregTech_API.getGregTechComponent(15, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 2).input(new ItemStack(Blocks.iron_bars, 2)).output(GregTech_API.getGregTechComponent(9, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(new ItemStack(Blocks.lever, 1)).output(GregTech_API.getGregTechComponent(31, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(new ItemStack(Blocks.crafting_table, 1)).output(GregTech_API.getGregTechComponent(64, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(1600).input(OrePrefixes.plate, Materials.Aluminium, 1).input(GT_ModHandler.getIC2Item("energyCrystal", 1L, 32767)).output(GregTech_API.getGregTechComponent(12, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(3200).input(OrePrefixes.plate, Materials.Aluminium, 1).input(GT_ModHandler.getIC2Item("lapotronCrystal", 1L, 32767)).output(GregTech_API.getGregTechComponent(13, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(6400).input(OrePrefixes.plate, Materials.Aluminium, 1).input(GT_Items.Energy_LapotronicOrb.get(1L)).output(GregTech_API.getGregTechComponent(14, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 2).input(GT_Items.Circuit_Basic.get(1L)).output(GregTech_API.getGregTechComponent(22, 3)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration(6400).input(OrePrefixes.plate, Materials.Aluminium, 4).input(GT_ModHandler.getIC2Item("generator", 1L)).output(GT_ModHandler.getIC2Item("waterMill", 2L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 1).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(OrePrefixes.plate, Materials.RedAlloy, 2).output(GT_Items.Circuit_Board_Basic.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 1).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(OrePrefixes.plate, Materials.Electrum, 2).output(GT_Items.Circuit_Board_Basic.get(2L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.plate, Materials.Aluminium, 1).input(OrePrefixes.dust, Materials.Plastic, 2).output(GregTech_API.getGregTechComponent(89, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.plate, Materials.Aluminium, 1).input(OrePrefixes.dust, Materials.Wood, 2).output(GregTech_API.getGregTechComponent(89, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.plate, Materials.Aluminium, 1).input(OrePrefixes.plate, Materials.Iridium, 1).output(GregTech_API.getGregTechComponent(88, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 1).input(OrePrefixes.dust, Materials.Redstone, 1).output(GregTech_API.getGregTechComponent(86, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Aluminium, 2).input(new ItemStack(Items.iron_door, 1)).output(GregTech_API.getGregTechComponent(69, 2)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 800).input(OrePrefixes.plate, Materials.Iron, 5).input(new ItemStack(Blocks.chest, 1, 32767)).output(new ItemStack(Blocks.hopper)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 800).input(OrePrefixes.plate, Materials.Iron, 5).input(new ItemStack(Blocks.trapped_chest, 1, 32767)).output(new ItemStack(Blocks.hopper)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(GT_ModHandler.getIC2Item("pump", 1)).output(GregTech_API.getGregTechComponent(6, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(new ItemStack(Blocks.heavy_weighted_pressure_plate, 1, 32767)).output(GregTech_API.getGregTechComponent(11, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(new ItemStack(Blocks.light_weighted_pressure_plate, 1, 32767)).output(GregTech_API.getGregTechComponent(10, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(GT_ModHandler.getIC2Item("ecMeter", 1L)).output(GregTech_API.getGregTechComponent(15, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 2).input(new ItemStack(Blocks.iron_bars, 2)).output(GregTech_API.getGregTechComponent(9, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(new ItemStack(Items.comparator, 1)).output(GregTech_API.getGregTechComponent(30, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(new ItemStack(Blocks.lever, 1)).output(GregTech_API.getGregTechComponent(31, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(new ItemStack(Blocks.crafting_table, 1)).output(GregTech_API.getGregTechComponent(64, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(1600).input(OrePrefixes.plate, Materials.Iron, 1).input(GT_ModHandler.getIC2Item("energyCrystal", 1L, 32767)).output(GregTech_API.getGregTechComponent(12, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(3200).input(OrePrefixes.plate, Materials.Iron, 1).input(GT_ModHandler.getIC2Item("lapotronCrystal", 1L, 32767)).output(GregTech_API.getGregTechComponent(13, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(6400).input(OrePrefixes.plate, Materials.Iron, 1).input(GT_Items.Energy_LapotronicOrb.get(1)).output(GregTech_API.getGregTechComponent(14, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 2).input(GT_Items.Circuit_Basic.get(1)).output(GregTech_API.getGregTechComponent(22, 4)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 1).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(OrePrefixes.plate, Materials.RedAlloy, 2).output(GT_Items.Circuit_Board_Basic.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 1).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(OrePrefixes.plate, Materials.Electrum, 2).output(GT_Items.Circuit_Board_Basic.get(2L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.plate, Materials.Iron, 1).input(OrePrefixes.dust, Materials.Plastic, 2).output(GregTech_API.getGregTechComponent(89, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.plate, Materials.Iron, 1).input(OrePrefixes.dust, Materials.Wood, 2).output(GregTech_API.getGregTechComponent(89, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.plate, Materials.Iron, 1).input(OrePrefixes.plate, Materials.Iridium, 1).output(GregTech_API.getGregTechComponent(88, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 1).input(OrePrefixes.dust, Materials.Redstone, 1).output(GregTech_API.getGregTechComponent(86, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 800).input(OrePrefixes.plate, Materials.Iron, 2).input(new ItemStack(Items.iron_door, 1)).output(GregTech_API.getGregTechComponent(69, 2)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.plate, Materials.Electrum, 2).input(GT_Items.Circuit_Basic.get(1L)).output(GT_Items.Circuit_Board_Advanced.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.plate, Materials.Electrum, 4).input(OrePrefixes.plate, Materials.Silicon, 1).output(GT_Items.Circuit_Board_Advanced.get(2L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration(3200).input(OrePrefixes.plate, Materials.Platinum, 1).input(GT_Items.Circuit_Advanced.get(1L)).output(GT_Items.Circuit_Board_Elite.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration(6400).input(OrePrefixes.plate, Materials.Magnalium, 2).input(GT_ModHandler.getIC2Item("generator", 1L)).output(GT_ModHandler.getIC2Item("windMill", 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration( 400).input(OrePrefixes.plate, Materials.TungstenSteel, 1).input(GT_ModHandler.getIC2Item("reinforcedStone", 1L)).output(new ItemStack(GregTech_API.sBlockList[4], 1, 8)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration( 400).input(OrePrefixes.plate, Materials.TungstenSteel, 1).input(new ItemStack(GregTech_API.sBlockList[0], 1, 2)).output(new ItemStack(GregTech_API.sBlockList[4], 1, 9)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration( 400).input(OrePrefixes.plate, Materials.Iridium, 1).input(GT_ModHandler.getIC2Item("reinforcedStone", 1L)).output(new ItemStack(GregTech_API.sBlockList[0], 1, 2)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration( 400).input(OrePrefixes.plate, Materials.Iridium, 1).input(new ItemStack(GregTech_API.sBlockList[4], 1, 8)).output(new ItemStack(GregTech_API.sBlockList[4], 1, 9)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(32).duration(1600).input(OrePrefixes.plate, Materials.Steel, 2).input(GT_ModHandler.getRCItem("machine.beta.engine.steam.high", 1L, GregTech_API.getGregTechBlock(1, 1, 34))).output(GregTech_API.getGregTechComponent(80, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(32).duration(1600).input(OrePrefixes.plate, Materials.Steel, 2).input(new ItemStack(Blocks.glass_pane, 1, 32767)).output(GregTech_API.getGregTechComponent(81, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration(6400).input(OrePrefixes.plate, Materials.Emerald, 8).input(GT_Items.Circuit_Advanced.get(1L)).output(GregTech_API.getGregTechComponent(3, 4)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration(6400).input(OrePrefixes.plate, Materials.Olivine, 8).input(GT_Items.Circuit_Advanced.get(1L)).output(GregTech_API.getGregTechComponent(3, 4)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 400).input(OrePrefixes.gem, Materials.EnderPearl, 1).input(new ItemStack(Items.blaze_powder, 1, 0)).output(new ItemStack(Items.ender_eye, 1, 0)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(2500).input(OrePrefixes.gem, Materials.EnderPearl, 6).input(new ItemStack(Items.blaze_rod, 1, 0)).output(new ItemStack(Items.ender_eye, 6, 0)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration( 400).input(OrePrefixes.plate, Materials.Aluminium, 6).input(GregTech_API.getGregTechComponent(22, 1)).output(GregTech_API.getGregTechComponent(32, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration( 400).input(OrePrefixes.plate, Materials.Bronze, 6).input(GregTech_API.getGregTechComponent(22, 1)).output(GregTech_API.getGregTechComponent(33, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration( 400).input(OrePrefixes.plate, Materials.Brass, 6).input(GregTech_API.getGregTechComponent(22, 1)).output(GregTech_API.getGregTechComponent(34, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration( 400).input(OrePrefixes.plate, Materials.Steel, 6).input(GregTech_API.getGregTechComponent(22, 1)).output(GregTech_API.getGregTechComponent(35, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration( 400).input(OrePrefixes.plate, Materials.Titanium, 6).input(GregTech_API.getGregTechComponent(22, 1)).output(GregTech_API.getGregTechComponent(36, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration( 400).input(OrePrefixes.plate, Materials.Iron, 8).input(GregTech_API.getGregTechComponent(22, 1)).output(GT_ModHandler.getIC2Item("machine", 1L, GregTech_API.getGregTechComponent(37, 1))).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration( 400).input(OrePrefixes.plate, Materials.TungstenSteel, 6).input(GregTech_API.getGregTechComponent(22, 1)).output(GregTech_API.getGregTechComponent(38, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 8).duration( 400).input(OrePrefixes.plate, Materials.StainlessSteel, 6).input(GregTech_API.getGregTechComponent(22, 1)).output(GregTech_API.getGregTechComponent(39, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.gear, Materials.Gold, 1).input(OrePrefixes.gem, Materials.Diamond, 4).output(GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Diamond, 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration(1600).input(OrePrefixes.gear, Materials.Gold, 1).input(OrePrefixes.plate, Materials.Diamond, 4).output(GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Diamond, 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 800).input(OrePrefixes.dust, Materials.Flint, 5).input(new ItemStack(Blocks.tnt, 3, 32767)).output(GT_ModHandler.getIC2Item("industrialTnt", 5L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 1).duration( 400).input(OrePrefixes.dust, Materials.Gunpowder, 4).input(new ItemStack(Blocks.sand, 4, 32767)).output(new ItemStack(Blocks.tnt, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 800).input(OrePrefixes.dust, Materials.Glowstone, 1).input(OrePrefixes.dust, Materials.Lapis, 1).output(GregTech_API.getGregTechComponent(24, 2)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 800).input(OrePrefixes.dust, Materials.Glowstone, 1).input(OrePrefixes.dust, Materials.Lazurite, 1).output(GregTech_API.getGregTechComponent(24, 2)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 800).input(OrePrefixes.dust, Materials.Redstone, 1).input(GT_Items.Cell_Empty.get(1L)).output(GregTech_API.getGregTechComponent(56, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 1).duration( 400).input(OrePrefixes.dust, Materials.Redstone, 4).input(OrePrefixes.dust, Materials.Glowstone, 4).output(new ItemStack(Blocks.redstone_lamp, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 1).duration( 400).input(OrePrefixes.dust, Materials.Redstone, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(new ItemStack(Blocks.redstone_torch, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration( 400).input(OrePrefixes.dust, Materials.Redstone, 1).input(OrePrefixes.ingot, Materials.Iron, 4).output(new ItemStack(Items.compass, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration( 400).input(OrePrefixes.dust, Materials.Redstone, 1).input(OrePrefixes.ingot, Materials.Gold, 4).output(new ItemStack(Items.clock, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 1).duration( 400).input(OrePrefixes.stick, Materials.Wood, 1).input(GT_Items.IC2_Resin.get(1L)).output(new ItemStack(Blocks.torch, 6)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 4).duration( 400).input(OrePrefixes.dust, Materials.Coal, 8).input(new ItemStack(Items.flint, 1)).output(GT_Items.IC2_Compressed_Coal_Ball.get(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 100).inputs(GT_ModHandler.getIC2Item("tinCableItem", 1L)).input(OrePrefixes.ingot, Materials.Rubber, 1).output(GT_ModHandler.getIC2Item("insulatedTinCableItem", 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 100).inputs(GT_ModHandler.getIC2Item("copperCableItem", 1L)).input(OrePrefixes.ingot, Materials.Rubber, 1).output(GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 200).inputs(GT_ModHandler.getIC2Item("goldCableItem", 1L)).input(OrePrefixes.ingot, Materials.Rubber, 2).output(GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt( 2).duration( 300).inputs(GT_ModHandler.getIC2Item("ironCableItem", 1L)).input(OrePrefixes.ingot, Materials.Rubber, 3).output(GT_ModHandler.getIC2Item("insulatedIronCableItem", 1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSword, Materials.Wood, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(new ItemStack(Items.wooden_sword, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSword, Materials.Stone, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(new ItemStack(Items.stone_sword, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSword, Materials.Iron, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(new ItemStack(Items.iron_sword, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSword, Materials.Gold, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(new ItemStack(Items.golden_sword, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSword, Materials.Diamond, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(new ItemStack(Items.diamond_sword, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSword, Materials.Bronze, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(GT_Items.Tool_Sword_Bronze.getUndamaged(1L)).buildAndRegister();
//        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(100).input(OrePrefixes.toolHeadSword, Materials.Flint, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(GT_Items.Tool_Sword_Flint.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSword, Materials.Steel, 1).input(OrePrefixes.stick, Materials.Iron, 1).output(GT_Items.Tool_Sword_Steel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSword, Materials.TungstenSteel, 1).input(OrePrefixes.stick, Materials.Steel, 1).output(GT_Items.Tool_Sword_TungstenSteel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadPickaxe, Materials.Wood, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.wooden_pickaxe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadPickaxe, Materials.Stone, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.stone_pickaxe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadPickaxe, Materials.Iron, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.iron_pickaxe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadPickaxe, Materials.Gold, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.golden_pickaxe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadPickaxe, Materials.Diamond, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.diamond_pickaxe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadPickaxe, Materials.Bronze, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_Items.Tool_Pickaxe_Bronze.getUndamaged(1L)).buildAndRegister();
//        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(100).input(OrePrefixes.toolHeadPickaxe, Materials.Flint, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_Items.Tool_Pickaxe_Flint.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadPickaxe, Materials.Steel, 1).input(OrePrefixes.stick, Materials.Iron, 2).output(GT_Items.Tool_Pickaxe_Steel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadPickaxe, Materials.TungstenSteel, 1).input(OrePrefixes.stick, Materials.Steel, 2).output(GT_Items.Tool_Pickaxe_TungstenSteel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadShovel, Materials.Wood, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.wooden_shovel, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadShovel, Materials.Stone, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.stone_shovel, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadShovel, Materials.Iron, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.iron_shovel, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadShovel, Materials.Gold, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.golden_shovel, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadShovel, Materials.Diamond, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.diamond_shovel, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadShovel, Materials.Bronze, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_Items.Tool_Shovel_Bronze.getUndamaged(1L)).buildAndRegister();
//        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(100).input(OrePrefixes.toolHeadShovel, Materials.Flint, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_Items.Tool_Shovel_Flint.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadShovel, Materials.Steel, 1).input(OrePrefixes.stick, Materials.Iron, 2).output(GT_Items.Tool_Shovel_Steel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadShovel, Materials.TungstenSteel, 1).input(OrePrefixes.stick, Materials.Steel, 2).output(GT_Items.Tool_Shovel_TungstenSteel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadAxe, Materials.Wood, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.wooden_axe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadAxe, Materials.Stone, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.stone_axe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadAxe, Materials.Iron, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.iron_axe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadAxe, Materials.Gold, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.golden_axe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadAxe, Materials.Diamond, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.diamond_axe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadAxe, Materials.Bronze, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_Items.Tool_Axe_Bronze.getUndamaged(1L)).buildAndRegister();
//        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(100).input(OrePrefixes.toolHeadAxe, Materials.Flint, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_Items.Tool_Axe_Flint.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadAxe, Materials.Steel, 1).input(OrePrefixes.stick, Materials.Iron, 2).output(GT_Items.Tool_Axe_Steel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadAxe, Materials.TungstenSteel, 1).input(OrePrefixes.stick, Materials.Steel, 2).output(GT_Items.Tool_Axe_TungstenSteel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHoe, Materials.Wood, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.wooden_hoe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHoe, Materials.Stone, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.stone_hoe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHoe, Materials.Iron, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.iron_hoe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHoe, Materials.Gold, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.golden_hoe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHoe, Materials.Diamond, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(new ItemStack(Items.diamond_hoe, 1)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHoe, Materials.Bronze, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_Items.Tool_Hoe_Bronze.getUndamaged(1L)).buildAndRegister();
//        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(100).input(OrePrefixes.toolHeadHoe, Materials.Flint, 1).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_Items.Tool_Hoe_Flint.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHoe, Materials.Steel, 1).input(OrePrefixes.stick, Materials.Iron, 2).output(GT_Items.Tool_Hoe_Steel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHoe, Materials.TungstenSteel, 1).input(OrePrefixes.stick, Materials.Steel, 2).output(GT_Items.Tool_Hoe_TungstenSteel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSaw, Materials.Iron, 1).input(OrePrefixes.stick, Materials.Wood, 4).output(GT_Items.Tool_Saw_Iron.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSaw, Materials.Bronze, 1).input(OrePrefixes.stick, Materials.Wood, 4).output(GT_Items.Tool_Saw_Bronze.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSaw, Materials.Steel, 1).input(OrePrefixes.stick, Materials.Iron, 4).output(GT_Items.Tool_Saw_Steel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadSaw, Materials.TungstenSteel, 1).input(OrePrefixes.stick, Materials.Steel, 4).output(GT_Items.Tool_Saw_TungstenSteel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHammer, Materials.Iron, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(GT_Items.Tool_Hammer_Iron.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHammer, Materials.Bronze, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(GT_Items.Tool_Hammer_Bronze.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHammer, Materials.Rubber, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(GT_Items.Tool_Hammer_Rubber.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHammer, Materials.Plastic, 1).input(OrePrefixes.stick, Materials.Iron, 1).output(GT_Items.Tool_Hammer_Plastic.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHammer, Materials.Steel, 1).input(OrePrefixes.stick, Materials.Iron, 1).output(GT_Items.Tool_Hammer_Steel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadHammer, Materials.TungstenSteel, 1).input(OrePrefixes.stick, Materials.Steel, 1).output(GT_Items.Tool_Hammer_TungstenSteel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadFile, Materials.Iron, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(GT_Items.Tool_File_Iron.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadFile, Materials.Bronze, 1).input(OrePrefixes.stick, Materials.Wood, 1).output(GT_Items.Tool_File_Bronze.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadFile, Materials.Steel, 1).input(OrePrefixes.stick, Materials.Iron, 1).output(GT_Items.Tool_File_Steel.getUndamaged(1L)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(16).duration( 100).input(OrePrefixes.toolHeadFile, Materials.TungstenSteel, 1).input(OrePrefixes.stick, Materials.Steel, 1).output(GT_Items.Tool_File_TungstenSteel.getUndamaged(1L)).buildAndRegister();
//        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(100).inputs(GT_OreDictUnificator.get(OrePrefixes.toolHeadScrewdriver, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L)).output(GT_Items.Tool_Screwdriver_Iron.getUndamaged(1L)).buildAndRegister();
//        RecipeMaps.ASSEMBLING.factory().EUt(16).duration(100).inputs(GT_OreDictUnificator.get(OrePrefixes.toolHeadScrewdriver, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 1L)).output(GT_Items.Tool_Screwdriver_TungstenSteel.getUndamaged(1L)).buildAndRegister();
        
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(300).input(OrePrefixes.stick, Materials.Iron, 3).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 3)).output(new ItemStack(Blocks.iron_bars, 4)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(6).duration(260).input(OrePrefixes.plate, Materials.Iron, 7).nonConsumable(GT_Items.Circuit_Integrated.getWithDamage(0, 7)).output(new ItemStack(Items.cauldron, 1)).buildAndRegister();
        
        GT_ModHandler.removeRecipe(new ItemStack(Items.lava_bucket), GT_Items.Cell_Empty.get(1L));
        GT_ModHandler.removeRecipe(new ItemStack(Items.water_bucket), GT_Items.Cell_Empty.get(1L));
        GT_ModHandler.removeFurnaceSmelting(GT_Items.IC2_Resin.get(1L));
        
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(40).input(OrePrefixes.cell, Materials.Ice, 1).outputs(new ItemStack(Blocks.ice, 1), GT_Items.Cell_Empty.get(1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(500).input(new ItemStack(Items.magma_cream, 1)).outputs(new ItemStack(Items.blaze_powder, 1), new ItemStack(Items.slime_ball, 1)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(3000).input(OrePrefixes.cell, Materials.Hydrogen, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Deuterium, 1L), GT_Items.Cell_Empty.get(3L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(10000).input(OrePrefixes.cell, Materials.Helium, 16).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium_3, 1L), GT_Items.Cell_Empty.get(15L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(3000).input(OrePrefixes.cell, Materials.Deuterium, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Tritium, 1L), GT_Items.Cell_Empty.get(3L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(50000).input(OrePrefixes.dust, Materials.Uranium, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium235, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plutonium, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(100000).input(OrePrefixes.dust, Materials.Plutonium, 4).outputs(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plutonium241, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(1650).input(new ItemStack(Blocks.mycelium, 8)).outputs(new ItemStack((Block)Blocks.brown_mushroom, 2), new ItemStack((Block)Blocks.red_mushroom, 2), new ItemStack(Items.clay_ball, 1), new ItemStack(Blocks.sand, 4)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(10000).setShaped(true).input(new ItemStack(Items.golden_apple, 1, 1)).input(GT_Items.Cell_Empty.get(2)).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 2L), new ItemStack(Items.gold_ingot, 64)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(10000).setShaped(true).input(new ItemStack(Items.golden_apple, 1, 0)).input(GT_Items.Cell_Empty.get(1)).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), new ItemStack(Items.gold_ingot, 7)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(10000).setShaped(true).input(new ItemStack(Items.golden_carrot, 1, 0)).input(GT_Items.Cell_Empty.get(1)).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), new ItemStack(Items.gold_nugget, 6)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(10000).setShaped(true).input(new ItemStack(Items.speckled_melon, 1, 0)).input(GT_Items.Cell_Empty.get(1)).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), new ItemStack(Items.gold_nugget, 6)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.apple, 32, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.mushroom_stew, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.bread, 64, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.porkchop, 12, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.cooked_porkchop, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.beef, 12, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.cooked_beef, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.fish, 12, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.cooked_fished, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.chicken, 12, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.cooked_chicken, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.melon, 64, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Blocks.melon_block, 1)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Blocks.pumpkin, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.rotten_flesh, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.spider_eye, 32, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.carrot, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.potato, 16, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.poisonous_potato, 12, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.baked_potato, 24, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.cookie, 64, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.cake, 8, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Blocks.brown_mushroom_block, 12, 32767)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Blocks.red_mushroom_block, 12, 32767)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Blocks.brown_mushroom, 32, 32767)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Blocks.red_mushroom, 32, 32767)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(new ItemStack(Items.nether_wart, 32, 32767)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).input(GT_ModHandler.getIC2Item("terraWart", 16L)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(1300).input(GT_Items.IC2_Resin.get(4L)).outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rubber, 12L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plastic, 6L), GT_ModHandler.getIC2Item("plantBall", 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(2500).setShaped(true).input(new ItemStack(Blocks.soul_sand, 16)).input(GT_Items.Cell_Empty.get(1)).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L), new ItemStack(Blocks.sand, 10)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).input(new FluidStack(FluidRegistry.LAVA, 8000)).outputs(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(6000).input(OrePrefixes.cell, Materials.Lava, 8).outputs(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 17L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(2400).input(OrePrefixes.dust, Materials.Netherrack, 16).outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(4800).setShaped(true).input(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Endstone, 16L)).input(GT_Items.Cell_Empty.get(2)).outputs(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium_3, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), new ItemStack(Blocks.sand, 12)).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(250).input(OrePrefixes.dust, Materials.DarkAsh, 2).outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1L), GT_Items.TE_Slag.get(1L, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1L))).buildAndRegister();
        RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(25000).setShaped(true).input(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 16L)).input(GT_Items.Cell_Empty.get(1)).outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 8L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold, 8L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium, 1L)).buildAndRegister();
        
        RecipeMaps.PRINTER.factory().EUt(1).duration(200).setShaped(true).input(new ItemStack(Items.reeds)).output(new ItemStack(Items.paper)).buildAndRegister();
        RecipeMaps.PRINTER.factory().EUt(1).duration(200).setShaped(true).input(OrePrefixes.dust, Materials.Wood).output(new ItemStack(Items.paper)).buildAndRegister();
        RecipeMaps.PRINTER.factory().EUt(2).duration(400).setShaped(true).input(new ItemStack(Items.paper, 3)).input(new ItemStack(Items.leather)).output(new ItemStack(Items.book)).buildAndRegister();
        RecipeMaps.PRINTER.factory().EUt(2).duration(400).setShaped(true).input(new ItemStack(Items.paper, 8)).input(new ItemStack(Items.compass)).output(new ItemStack(Items.map)).buildAndRegister();
        if (Loader.isModLoaded("arsmagica2")) {
        	RecipeMaps.PRINTER.factory().EUt(2).duration(400).setShaped(true).input("paperEmpty", 8).input(OrePrefixes.stick, Materials.Wood, 2).output(GT_OreDictUnificator.getFirstOre("paperArsSpellParchment", 1)).buildAndRegister();
        }
        
        RecipeMaps.LATHE.factory().EUt(8).duration(300)
        	.input(OrePrefixes.block, Materials.Iron)
        	.output(GT_ModHandler.getIC2Item("ironshaft", 1))
        	.output(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Iron, 6))
        	.buildAndRegister();
        RecipeMaps.LATHE.factory().EUt(8).duration(300)
	    	.input(OrePrefixes.block, Materials.Steel)
	    	.output(GT_ModHandler.getIC2Item("steelshaft", 1))
	    	.output(GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Steel, 6))
	    	.buildAndRegister();
        
        
        for (final MaterialStack[] tMats : this.mAlloySmelterList) {
            final ItemStack tOutputIngot = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount);
            final ItemStack tDust1 = GT_OreDictUnificator.get(OrePrefixes.dust, tMats[0].mMaterial, tMats[0].mAmount);
            final ItemStack tDust2 = GT_OreDictUnificator.get(OrePrefixes.dust, tMats[1].mMaterial, tMats[1].mAmount);
            final ItemStack tIngot1 = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[0].mMaterial, tMats[0].mAmount);
            final ItemStack tIngot2 = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[1].mMaterial, tMats[1].mAmount);
            
            if (tOutputIngot != null) {
            	int dur = (int)tMats[2].mAmount * 50;
            	GT_ModHandler.addInductionSmelterRecipe(tDust1, tDust2, tOutputIngot, null, dur * 32, 0);
            	RecipeMaps.ALLOY_SMELTING.factory().EUt(16).duration(dur)
            		.input(OrePrefixes.dust, tMats[0].mMaterial, (int)tMats[0].mAmount)
            		.input(OrePrefixes.dust, tMats[1].mMaterial, (int)tMats[1].mAmount)
            		.output(tOutputIngot).buildAndRegister();
            	
            	
            	if (tIngot1 != null && tIngot2 != null) {
            		GT_ModHandler.addInductionSmelterRecipe(tIngot1, tIngot2, tOutputIngot, null, dur * 32, 0);
	            	RecipeMaps.ALLOY_SMELTING.factory().EUt(16).duration(dur)
		        		.input(OrePrefixes.ingot, tMats[0].mMaterial, (int)tMats[0].mAmount)
		        		.input(OrePrefixes.ingot, tMats[1].mMaterial, (int)tMats[1].mAmount)
		        		.output(tOutputIngot).buildAndRegister();
            	}
            	
            	if (tIngot2 != null) {
            		GT_ModHandler.addInductionSmelterRecipe(tDust1, tIngot2, tOutputIngot, null, dur * 32, 0);
	            	RecipeMaps.ALLOY_SMELTING.factory().EUt(16).duration(dur)
		        		.input(OrePrefixes.dust, tMats[0].mMaterial, (int)tMats[0].mAmount)
		        		.input(OrePrefixes.ingot, tMats[1].mMaterial, (int)tMats[1].mAmount)
		        		.output(tOutputIngot).buildAndRegister();
            	}
            	
            	if (tIngot1 != null) {
            		GT_ModHandler.addInductionSmelterRecipe(tIngot1, tDust2, tOutputIngot, null, dur * 32, 0);
	            	RecipeMaps.ALLOY_SMELTING.factory().EUt(16).duration(dur)
		        		.input(OrePrefixes.ingot, tMats[0].mMaterial, (int)tMats[0].mAmount)
		        		.input(OrePrefixes.dust, tMats[1].mMaterial, (int)tMats[1].mAmount)
		        		.output(tOutputIngot).buildAndRegister();
            	}
            	
//            	
//                GT_ModHandler.addAlloySmelterRecipe(tIngot1, tDust2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false);
//                GT_ModHandler.addAlloySmelterRecipe(tIngot1, tIngot2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false);
//                GT_ModHandler.addAlloySmelterRecipe(tDust1, tIngot2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false);
//                GT_ModHandler.addAlloySmelterRecipe(tDust1, tDust2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false);
            }
        }
    }
}
