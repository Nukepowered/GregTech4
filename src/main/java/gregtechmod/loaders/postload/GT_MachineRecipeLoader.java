package gregtechmod.loaders.postload;

import ic2.api.recipe.*;
import net.minecraft.item.*;
import gregtechmod.api.util.*;
import gregtechmod.api.*;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import gregtechmod.api.enums.*;
import gregtechmod.api.interfaces.*;

public class GT_MachineRecipeLoader implements Runnable
{
    private final MaterialStack[][] mAlloySmelterList;
    
    public GT_MachineRecipeLoader() {
        this.mAlloySmelterList = new MaterialStack[][] { { new MaterialStack(Materials.Tetrahedrite, 3L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.Bronze, 3L) }, { new MaterialStack(Materials.Tetrahedrite, 3L), new MaterialStack(Materials.Zinc, 1L), new MaterialStack(Materials.Brass, 3L) }, { new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Tin, 1L), new MaterialStack(Materials.Bronze, 4L) }, { new MaterialStack(Materials.Copper, 3L), new MaterialStack(Materials.Zinc, 1L), new MaterialStack(Materials.Brass, 4L) }, { new MaterialStack(Materials.Copper, 1L), new MaterialStack(Materials.Nickel, 1L), new MaterialStack(Materials.Cupronickel, 2L) }, { new MaterialStack(Materials.Iron, 2L), new MaterialStack(Materials.Nickel, 1L), new MaterialStack(Materials.Invar, 3L) }, { new MaterialStack(Materials.Chrome, 1L), new MaterialStack(Materials.Nickel, 4L), new MaterialStack(Materials.Nichrome, 5L) }, { new MaterialStack(Materials.Tin, 9L), new MaterialStack(Materials.Antimony, 1L), new MaterialStack(Materials.SolderingAlloy, 10L) }, { new MaterialStack(Materials.Lead, 4L), new MaterialStack(Materials.Antimony, 1L), new MaterialStack(Materials.BatteryAlloy, 5L) }, { new MaterialStack(Materials.Gold, 1L), new MaterialStack(Materials.Silver, 1L), new MaterialStack(Materials.Electrum, 2L) }, { new MaterialStack(Materials.Magnesium, 1L), new MaterialStack(Materials.Aluminium, 2L), new MaterialStack(Materials.Magnalium, 3L) }, { new MaterialStack(Materials.Copper, 1L), new MaterialStack(Materials.Redstone, 4L), new MaterialStack(Materials.RedAlloy, 1L) }, { new MaterialStack(Materials.Silver, 1L), new MaterialStack(Materials.Nikolite, 4L), new MaterialStack(Materials.BlueAlloy, 1L) } };
    }
    
    @Override
    public void run() {
    	
        GT_Log.log.info("GT_Mod: Adding non-OreDict Machine Recipes.");
        
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
        GT_ModHandler.addExtractionRecipe(GregTech_API.getGregTechMaterial(8, 1), GregTech_API.getGregTechMaterial(9, 1));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Plumbilia.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Lead, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Argentia.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Silver, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Indigo.get(1L), GregTech_API.getGregTechMaterial(9, 1));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Ferru.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Iron, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Aurelia.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Gold, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_BobsYerUncleRanks.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Emerald, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_MilkWart.get(1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Milk, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Coppon.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Copper, 1L));
        GT_ModHandler.addExtractionRecipe(GT_Items.Crop_Drop_Tine.get(1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tin, 1L));
       
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Plumbilia.get(8L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Argentia.get(8L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Indigo.get(8L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Ferru.get(8L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Aurelia.get(8L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_OilBerry.get(8L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_BobsYerUncleRanks.get(8L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Coppon.get(4L), new ItemStack(Blocks.wool, 1, 1));
        GT_ModHandler.addCompressionRecipe(GT_Items.Crop_Drop_Tine.get(4L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GregTech_API.getGregTechMaterial(8, 8), GT_ModHandler.getIC2Item("compressedPlantBall", 1L));
        GT_ModHandler.addCompressionRecipe(GT_Items.IC2_Compressed_Coal_Chunk.get(1L), GT_Items.IC2_Industrial_Diamond.get(1L));
        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 8L), new ItemStack(GregTech_API.sItemList[8], 1, 17809));
        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L), GT_ModHandler.getIC2Item("Uran238", 1L));
        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium235, 1L), GT_ModHandler.getIC2Item("Uran235", 1L));
        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plutonium, 1L), GT_ModHandler.getIC2Item("Plutonium", 1L));
        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium235, 1L), GT_ModHandler.getIC2Item("smallUran235", 1L));
        GT_ModHandler.addCompressionRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plutonium, 1L), GT_ModHandler.getIC2Item("smallPlutonium", 1L));
        
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
        
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 2L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L), 500, 120, 1000);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.ShadowIron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 2L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.ShadowSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L), 500, 120, 1100);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.MeteoricIron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 2L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.MeteoricSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L), 500, 120, 1200);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.ingotHot, Materials.TungstenSteel, 2L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4L), 500, 500, 3000);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ilmenite, 2L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Titanium, 1L), 800, 120, 1700);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ilmenite, 2L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Titanium, 1L), 640, 120, 1700);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Galena, 2L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Silver, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Lead, 1L), 400, 120, 1500);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Galena, 2L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Silver, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Lead, 1L), 320, 120, 1500);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnetite, 2L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L), 400, 120, 1000);
        GregTech_API.sRecipeAdder.addBlastRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Magnetite, 2L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 2L), 320, 120, 1000);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SulfuricAcid, 1L), GT_Items.Battery_Hull_LV.get(1L), GT_Items.Battery_SU_LV_SulfuricAcid.getWithCharge(1L, Integer.MAX_VALUE), GT_Items.Cell_Empty.get(1L), 100, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, 1L), GT_Items.Battery_Hull_LV.get(1L), GT_Items.Battery_SU_LV_Mercury.getWithCharge(1L, Integer.MAX_VALUE), GT_Items.Cell_Empty.get(1L), 100, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SulfuricAcid, 4L), GT_Items.Battery_Hull_MV.get(1L), GT_Items.Battery_SU_MV_SulfuricAcid.getWithCharge(1L, Integer.MAX_VALUE), GT_Items.Cell_Empty.get(4L), 400, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, 4L), GT_Items.Battery_Hull_MV.get(1L), GT_Items.Battery_SU_MV_Mercury.getWithCharge(1L, Integer.MAX_VALUE), GT_Items.Cell_Empty.get(4L), 400, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 2L), GT_Items.Battery_Hull_LV.get(1L), GT_Items.Battery_RE_LV_Lithium.get(1L), null, 100, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 2L), GT_Items.Battery_Hull_LV.get(1L), GT_Items.Battery_RE_LV_Sodium.get(1L), null, 100, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lithium, 8L), GT_Items.Battery_Hull_MV.get(1L), GT_Items.Battery_RE_MV_Lithium.get(1L), null, 400, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 8L), GT_Items.Battery_Hull_MV.get(1L), GT_Items.Battery_RE_MV_Sodium.get(1L), null, 400, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 2L), GT_Items.Battery_Hull_LV.get(1L), GT_ModHandler.getIC2Item("reBattery", 1L), null, 100, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_Items.IC2_Grin_Powder.get(1L), GT_Items.Spray_Empty.get(1L), GT_ModHandler.getIC2Item("weedEx", 1L), null, 800, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Blocks.sand, 16, 0), GT_Items.Spray_Empty.get(1L), GT_Items.Spray_Hardener.get(1L), null, 1600, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen, 16L), GT_Items.Spray_Empty.get(1L), GT_Items.Spray_Ice.get(1L), GT_Items.Cell_Empty.get(16L), 1600, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_Items.Cell_Water.get(16L), GT_Items.Spray_Empty.get(1L), GT_Items.Spray_Hydration.get(1L), GT_Items.Cell_Empty.get(16L), 1600, 2);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.ConstructionFoam, 40L), GT_Items.Spray_Empty.get(1L), GT_Items.Spray_CFoam.get(1L), GT_Items.Cell_Empty.get(40L), 1600, 2);
        for (final Dyes tDye : Dyes.values()) {
            if (tDye != Dyes._NULL) {
                for (final ItemStack tIteratedStack : GT_OreDictUnificator.getOres(tDye.toString())) {
                    if (tIteratedStack.getMaxStackSize() >= 16 && GT_Utility.getContainerItem(tIteratedStack) == null) {
                        GregTech_API.sRecipeAdder.addCannerRecipe(GT_Utility.copyAmount(16L, tIteratedStack), GT_Items.Spray_Empty.get(1L), GT_Items.SPRAY_CAN_DYES[tDye.mColor].get(1L), null, 800, 1);
                    }
                }
            }
        }
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_Items.Crop_Drop_OilBerry.get(4L), GT_Items.Cell_Empty.get(1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil, 1L), null, 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(GregTech_API.getGregTechMaterial(7, 4), GT_Items.Cell_Empty.get(1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil, 1L), null, 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_ModHandler.getIC2Item("compressedPlantBall", 1L), GT_Items.Cell_Empty.get(1L), GT_ModHandler.getIC2Item("bioCell", 1L), null, 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_ModHandler.getIC2Item("hydratedCoalClump", 1L), GT_Items.Cell_Empty.get(1L), GT_ModHandler.getIC2Item("hydratedCoalCell", 1L), null, 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.BioFuel, 6L), GT_Items.IC2_Fuel_Can_Empty.get(1L), GT_ModHandler.getFuelCan(Materials.BioFuel.mFuelPower * 6000), GT_Items.Cell_Empty.get(6L), 600, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.CoalFuel, 6L), GT_Items.IC2_Fuel_Can_Empty.get(1L), GT_ModHandler.getFuelCan(Materials.CoalFuel.mFuelPower * 6000), GT_Items.Cell_Empty.get(6L), 600, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.BioFuel, 1L), GT_Items.Fuel_Can_Plastic_Empty.get(1L), GT_ModHandler.setFuelValue(GT_Items.Fuel_Can_Plastic_Filled.get(1L), (short)(Materials.BioFuel.mFuelPower * 400)), GT_Items.Cell_Empty.get(1L), 50, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.CoalFuel, 1L), GT_Items.Fuel_Can_Plastic_Empty.get(1L), GT_ModHandler.setFuelValue(GT_Items.Fuel_Can_Plastic_Filled.get(1L), (short)(Materials.CoalFuel.mFuelPower * 400)), GT_Items.Cell_Empty.get(1L), 50, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.lava_bucket), GT_Items.Cell_Empty.get(1L), GT_Items.Cell_Lava.get(1L), new ItemStack(Items.bucket, 1), 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.water_bucket), GT_Items.Cell_Empty.get(1L), GT_Items.Cell_Water.get(1L), new ItemStack(Items.bucket, 1), 100, 1);
        GregTech_API.sRecipeAdder.addFusionReactorRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Deuterium, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Tritium, 1L), GT_OreDictUnificator.get(OrePrefixes.cellPlasma, Materials.Helium, 1L), 128, -4096, 40000000);
        GregTech_API.sRecipeAdder.addFusionReactorRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Deuterium, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium_3, 1L), GT_OreDictUnificator.get(OrePrefixes.cellPlasma, Materials.Helium, 1L), 128, -2048, 60000000);
        GregTech_API.sRecipeAdder.addFusionReactorRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Lithium, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iridium, 1L), 512, -32768, 150000000);
        GregTech_API.sRecipeAdder.addFusionReactorRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Beryllium, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Platinum, 1L), 512, -32768, 100000000);
        GregTech_API.sRecipeAdder.addImplosionRecipe(GregTech_API.getGregTechMaterial(4, 1), 8, GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Iridium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4L));
        GregTech_API.sRecipeAdder.addImplosionRecipe(GT_Items.IC2_Compressed_Coal_Chunk.get(1L), 8, GT_Items.IC2_Industrial_Diamond.get(1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 4L));
        GregTech_API.sRecipeAdder.addDistillationRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil, 16L), 32, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Fuel, 16L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SulfuricAcid, 16L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glyceryl, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 15L), 4000, 64);
        GregTech_API.sRecipeAdder.addDistillationRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Biomass, 3L), 0, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Ethanol, 1L), GT_Items.Cell_Water.get(1L), GT_Items.Cell_Empty.get(1L), GT_Items.IC2_Fertilizer.get(1L), 500, 64);
        GregTech_API.sRecipeAdder.addElectrolyzerRecipe(GT_ModHandler.getIC2Item("electrolyzedWaterCell", 6L), 0, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 4L), GT_Items.Cell_Air.get(1L), null, GT_Items.Cell_Empty.get(1L), 100, 30);
        GregTech_API.sRecipeAdder.addElectrolyzerRecipe(GT_Items.Cell_Water.get(1L), 0, GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1L), null, null, null, 215, 120);
        GregTech_API.sRecipeAdder.addElectrolyzerRecipe(new ItemStack(Items.water_bucket, 1), 1, GT_ModHandler.getIC2Item("electrolyzedWaterCell", 1L), null, null, new ItemStack(Items.bucket, 1), 215, 120);
        GregTech_API.sRecipeAdder.addElectrolyzerRecipe(new ItemStack(Items.dye, 3, 15), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), null, null, null, 24, 106);
        GregTech_API.sRecipeAdder.addElectrolyzerRecipe(new ItemStack(Blocks.sand, 8), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.SiliconDioxide, 1L), null, null, null, 500, 25);
        
        GT_ModHandler.removeRecipeByOutput(GT_Items.IC2_Fertilizer.get(1L));
        
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.NetherQuartz, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.NetherQuartz, 1L), 500);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.CertusQuartz, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.CertusQuartz, 1L), 500);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Quartzite, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodium, 1L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Quartzite, 1L), 500);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uraninite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L), 1000);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uraninite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnesium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1L), 1000);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 2L), 250);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_Items.IC2_Fertilizer.get(2L), 200);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), GT_Items.IC2_Fertilizer.get(3L), 300);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), GT_Items.IC2_Fertilizer.get(2L), 200);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), GT_Items.IC2_Fertilizer.get(1L), 100);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), GT_Items.IC2_Fertilizer.get(1L), 100);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_Items.IC2_Fertilizer.get(3L), 300);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), GT_Items.IC2_Fertilizer.get(4L), 400);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), GT_Items.IC2_Fertilizer.get(3L), 300);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), GT_Items.IC2_Fertilizer.get(2L), 200);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), GT_Items.IC2_Fertilizer.get(2L), 200);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_Items.IC2_Fertilizer.get(3L), 300);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), GT_Items.IC2_Fertilizer.get(4L), 400);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), GT_Items.IC2_Fertilizer.get(3L), 300);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), GT_Items.IC2_Fertilizer.get(2L), 200);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Apatite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), GT_Items.IC2_Fertilizer.get(2L), 200);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1L), GT_Items.IC2_Fertilizer.get(3L), 300);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1L), GT_Items.IC2_Fertilizer.get(4L), 400);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphate, 1L), GT_Items.IC2_Fertilizer.get(3L), 300);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 3L), GT_Items.IC2_Fertilizer.get(2L), 200);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glauconite, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 1L), GT_Items.IC2_Fertilizer.get(2L), 200);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 8L), new ItemStack(Items.melon, 1, 32767), new ItemStack(Items.speckled_melon, 1, 0), 50);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 8L), new ItemStack(Items.carrot, 1, 32767), new ItemStack(Items.golden_carrot, 1, 0), 50);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 8L), new ItemStack(Items.apple, 1, 32767), new ItemStack(Items.golden_apple, 1, 0), 50);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.block, Materials.Gold, 8L), new ItemStack(Items.apple, 1, 32767), new ItemStack(Items.golden_apple, 1, 1), 50);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Blaze, 1L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderPearl, 1L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderEye, 1L), 50);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Blaze, 1L), new ItemStack(Items.slime_ball, 1, 32767), new ItemStack(Items.magma_cream, 1, 0), 50);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Carbon, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 4L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 5L), 3500);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Carbon, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitroCarbon, 2L), 1500);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glyceryl, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.CoalFuel, 4L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitroCoalFuel, 5L), 250);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glyceryl, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Fuel, 4L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitroFuel, 5L), 1000);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen, 1L), GT_Items.Cell_Air.get(1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitrogenDioxide, 2L), 1250);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumSulfide, 2L), GT_Items.Cell_Air.get(2L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, 4L), 4000);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.NitroCarbon, 3L), GT_Items.Cell_Water.get(3L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Glyceryl, 6L), 1750);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Sulfur, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Sodium, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumSulfide, 2L), 100);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Sulfur, 1L), GT_Items.Cell_Water.get(2L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SulfuricAcid, 3L), 1150);
        GregTech_API.sRecipeAdder.addChemicalRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 4L), GT_Items.Cell_Air.get(1L), GT_Items.Cell_Water.get(5L), 10);
        
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_Items.IC2_Mixed_Metal_Ingot.get(1L), GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Advanced, 1L), 100, 8);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Aluminium, 6L), GT_ModHandler.getRCItem("part.rail.standard", 2L), 200, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 6L), GT_ModHandler.getRCItem("part.rail.standard", 4L), 400, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Bronze, 6L), GT_ModHandler.getRCItem("part.rail.standard", 3L), 300, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 6L), GT_ModHandler.getRCItem("part.rail.standard", 8L), 800, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.StainlessSteel, 6L), GT_ModHandler.getRCItem("part.rail.standard", 12L), 1200, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Titanium, 6L), GT_ModHandler.getRCItem("part.rail.standard", 16L), 1600, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.TungstenSteel, 6L), GT_ModHandler.getRCItem("part.rail.reinforced", 24L), 2400, 30);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Aluminium, 12L), GT_ModHandler.getRCItem("part.rebar", 4L), 200, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 12L), GT_ModHandler.getRCItem("part.rebar", 8L), 400, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Bronze, 12L), GT_ModHandler.getRCItem("part.rebar", 8L), 400, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 12L), GT_ModHandler.getRCItem("part.rebar", 16L), 800, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.StainlessSteel, 12L), GT_ModHandler.getRCItem("part.rebar", 24L), 1200, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Titanium, 12L), GT_ModHandler.getRCItem("part.rebar", 32L), 1600, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.TungstenSteel, 12L), GT_ModHandler.getRCItem("part.rebar", 48L), 2400, 15);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Tin, 12L), GT_Items.Cell_Empty.get(6L), 1200, 8);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 12L), new ItemStack(Items.bucket, 4, 0), 800, 4);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_ModHandler.getIC2Item("casingiron", 2L), GT_ModHandler.getIC2Item("fuelRod", 1L), 100, 8);
        GregTech_API.sRecipeAdder.addBenderRecipe(GT_ModHandler.getIC2Item("casingtin", 1L), GT_Items.IC2_Food_Can_Empty.get(1L), 100, 8);
        
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantSimple", 1L, 32767), GT_ModHandler.getIC2Item("reactorCoolantSimple", 1L), 100);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantTriple", 1L, 32767), GT_ModHandler.getIC2Item("reactorCoolantTriple", 1L), 300);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_ModHandler.getIC2Item("reactorCoolantSix", 1L, 32767), GT_ModHandler.getIC2Item("reactorCoolantSix", 1L), 600);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Items.Reactor_Coolant_He_1.getWildcard(1L), GT_Items.Reactor_Coolant_He_1.getUndamaged(1L), 700);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Items.Reactor_Coolant_He_3.getWildcard(1L), GT_Items.Reactor_Coolant_He_3.getUndamaged(1L), 2000);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Items.Reactor_Coolant_He_6.getWildcard(1L), GT_Items.Reactor_Coolant_He_6.getUndamaged(1L), 3900);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Items.Reactor_Coolant_NaK_1.getWildcard(1L), GT_Items.Reactor_Coolant_NaK_1.getUndamaged(1L), 500);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Items.Reactor_Coolant_NaK_3.getWildcard(1L), GT_Items.Reactor_Coolant_NaK_3.getUndamaged(1L), 1500);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Items.Reactor_Coolant_NaK_6.getWildcard(1L), GT_Items.Reactor_Coolant_NaK_6.getUndamaged(1L), 3000);
        GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Items.Cell_Water.get(1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Ice, 1L), 50);
        
        GregTech_API.sRecipeAdder.addAlloySmelterRecipe(new ItemStack(Items.water_bucket, 1, 0), null, new ItemStack(Items.bucket, 1, 0), 100, 1);
        GregTech_API.sRecipeAdder.addAlloySmelterRecipe(new ItemStack(Items.milk_bucket, 1, 0), null, new ItemStack(Items.bucket, 1, 0), 100, 1);
        GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lead, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 2L), GT_Items.TE_Hardened_Glass.get(2L), 200, 16);
        GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Lead, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 2L), GT_Items.TE_Hardened_Glass.get(2L), 200, 16);
        
        GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack(Blocks.glowstone, 1, 0), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Glowstone, 4L), 100, 16);
        GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack(Blocks.glass, 3, 0), new ItemStack(Blocks.glass_pane, 8, 0), 50, 8);
        GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack((Block)Blocks.wooden_slab, 1, 0), GregTech_API.getGregTechMaterial(60, 2), 50, 8);
        GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack((Block)Blocks.wooden_slab, 1, 1), GregTech_API.getGregTechMaterial(61, 2), 50, 8);
        GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack((Block)Blocks.wooden_slab, 1, 2), GregTech_API.getGregTechMaterial(62, 2), 50, 8);
        GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack((Block)Blocks.wooden_slab, 1, 3), GregTech_API.getGregTechMaterial(63, 2), 50, 8);
        GregTech_API.sRecipeAdder.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_ModHandler.getIC2Item("casingiron", 2L), 50, 16);
        GregTech_API.sRecipeAdder.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Gold, 1L), GT_ModHandler.getIC2Item("casinggold", 2L), 50, 16);
        GregTech_API.sRecipeAdder.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Bronze, 1L), GT_ModHandler.getIC2Item("casingbronze", 2L), 50, 16);
        GregTech_API.sRecipeAdder.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Copper, 1L), GT_ModHandler.getIC2Item("casingcopper", 2L), 50, 16);
        GregTech_API.sRecipeAdder.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Tin, 1L), GT_ModHandler.getIC2Item("casingtin", 2L), 50, 16);
        GregTech_API.sRecipeAdder.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Lead, 1L), GT_ModHandler.getIC2Item("casinglead", 2L), 50, 16);
        GregTech_API.sRecipeAdder.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Cupronickel, 1L), GT_Items.Credit_Greg_Cupronickel.get(4L), 100, 16);
        GregTech_API.sRecipeAdder.addCutterRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Brass, 1L), GT_Items.Coin_Doge.get(4L), 100, 16);
        
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1L), GT_ModHandler.getIC2Item("copperCableItem", 3L), 100, 2);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L), GT_ModHandler.getIC2Item("tinCableItem", 4L), 150, 1);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.SolderingAlloy, 1L), GT_Items.Tool_SolderingMaterial_Tin.get(1L), 100, 4);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L), GT_ModHandler.getIC2Item("ironCableItem", 6L), 200, 2);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 1L), GT_ModHandler.getIC2Item("goldCableItem", 6L), 200, 1);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Lead, 1L), GT_Items.Tool_SolderingMaterial_Lead.get(1L), 100, 4);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Kanthal, 4L), GregTech_API.getGregTechComponent(19, 1), 450, 12);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Nichrome, 5L), GregTech_API.getGregTechComponent(20, 1), 600, 16);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Cupronickel, 3L), GregTech_API.getGregTechComponent(21, 1), 300, 8);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Carbon, 8L), GT_ModHandler.getIC2Item("carbonFiber", 1L), 400, 2);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Charcoal, 8L), GT_ModHandler.getIC2Item("carbonFiber", 1L), 400, 2);
        GregTech_API.sRecipeAdder.addWiremillRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 4L), GT_ModHandler.getIC2Item("carbonFiber", 1L), 400, 2);
        
        if (!GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
            GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Items.coal, 1, 32767), new ItemStack(Blocks.torch, 4), 400, 1);
        }
        
        GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(Items.string, 4, 32767), new ItemStack(Items.slime_ball, 1, 32767), new ItemStack(Items.lead, 2), 200, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.IC2_Compressed_Coal_Ball.get(8L), new ItemStack(Blocks.brick_block, 1), GT_Items.IC2_Compressed_Coal_Chunk.get(1L), 400, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GregTech_API.getGregTechComponent(49, 1), GregTech_API.getGregTechComponent(24, 2), GT_Items.Circuit_Advanced.get(1L), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GregTech_API.getGregTechComponent(50, 1), GregTech_API.getGregTechComponent(3, 1), GregTech_API.getGregTechComponent(1, 1), 3200, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GregTech_API.getGregTechComponent(50, 1), GT_ModHandler.getIC2Item("lapotronCrystal", 1L, 32767), GregTech_API.getGregTechComponent(0, 1), 3200, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GregTech_API.getGregTechComponent(1, 1), GregTech_API.getGregTechComponent(3, 8), GT_Items.Tool_DataOrb.get(1L), 12800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 6), GregTech_API.getGregTechComponent(3, 4), GT_Items.Tool_Sonictron.get(1L), 6400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("waterMill", 2L), null, GT_ModHandler.getIC2Item("generator", 1L), 6400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(Blocks.stone_slab, 3, 0), GT_ModHandler.getRCItem("part.rebar", 1L), GT_ModHandler.getRCItem("part.tie.stone", 1L), 128, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(Blocks.stone_slab, 3, 7), GT_ModHandler.getRCItem("part.rebar", 1L), GT_ModHandler.getRCItem("part.tie.stone", 1L), 128, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.Armor_LithiumPack.getWildcard(1L), null, GT_Items.Battery_RE_LV_Lithium.get(6L), 3200, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("batPack", 1L, 32767), null, GT_ModHandler.getIC2Item("reBattery", 6L), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("hvTransformer", 1L), GT_ModHandler.getIC2Item("transformerUpgrade", 1L), GregTech_API.getGregTechComponent(27, 1), 3200, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getRCItem("part.tie.wood", 4L), null, GT_ModHandler.getRCItem("part.railbed.wood", 1L), 800, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getRCItem("part.tie.stone", 4L), null, GT_ModHandler.getRCItem("part.railbed.stone", 1L), 800, 1);
        
        final IGT_RecipeAdder sRecipeAdder = GregTech_API.sRecipeAdder;
        ItemStack tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1L);
        ItemStack tStack3;
        
        sRecipeAdder.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.standard", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder2 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1L);
        
        sRecipeAdder2.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.advanced", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder3 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1L);
        
        sRecipeAdder3.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.reinforced", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder4 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1L);
        
        sRecipeAdder4.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.speed", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder5 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.wood", 1L);
        
        sRecipeAdder5.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.wood", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder6 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1L);
        
        sRecipeAdder6.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.standard", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder7 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1L);
        
        sRecipeAdder7.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.advanced", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder8 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1L);
        
        sRecipeAdder8.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.reinforced", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder9 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1L);
        
        sRecipeAdder9.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.speed", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        final IGT_RecipeAdder sRecipeAdder10 = GregTech_API.sRecipeAdder;
        tStack2 = GT_ModHandler.getRCItem("part.railbed.stone", 1L);
        
        sRecipeAdder10.addAssemblerRecipe(tStack2, tStack3 = GT_ModHandler.getRCItem("part.rail.wood", 6L), GT_ModHandler.getRecipeOutput(tStack3, null, tStack3, tStack3, tStack2, tStack3, tStack3, null, tStack3), 1600, 1);
        
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GregTech_API.getGregTechComponent(5, 1), GregTech_API.getGregTechComponent(6, 1), GregTech_API.getGregTechComponent(8, 1), 3200, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("carbonFiber", 2L), null, GT_ModHandler.getIC2Item("carbonMesh", 1L), 800, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("carbonMesh", 16L), null, GT_Items.Component_LavaFilter.get(1L), 1600, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.Circuit_Basic.get(1L), GT_ModHandler.getIC2Item("frequencyTransmitter", 1L), GT_Items.NC_SensorKit.get(1L), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.NC_SensorCard.getWithDamage(1L, 0L), null, GT_Items.Circuit_Basic.get(2L), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_ModHandler.getIC2Item("pump", 1L), GregTech_API.getGregTechComponent(6, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), new ItemStack(Blocks.heavy_weighted_pressure_plate, 1, 32767), GregTech_API.getGregTechComponent(11, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), new ItemStack(Blocks.light_weighted_pressure_plate, 1, 32767), GregTech_API.getGregTechComponent(10, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_ModHandler.getIC2Item("ecMeter", 1L), GregTech_API.getGregTechComponent(15, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 2L), new ItemStack(Blocks.iron_bars, 2), GregTech_API.getGregTechComponent(9, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), new ItemStack(Blocks.lever, 1), GregTech_API.getGregTechComponent(31, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), new ItemStack(Blocks.crafting_table, 1), GregTech_API.getGregTechComponent(64, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_ModHandler.getIC2Item("energyCrystal", 1L, 32767), GregTech_API.getGregTechComponent(12, 1), 1600, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_ModHandler.getIC2Item("lapotronCrystal", 1L, 32767), GregTech_API.getGregTechComponent(13, 1), 3200, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_Items.Energy_LapotronicOrb.get(1L), GregTech_API.getGregTechComponent(14, 1), 6400, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 2L), GT_Items.Circuit_Basic.get(1L), GregTech_API.getGregTechComponent(22, 3), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 4L), GT_ModHandler.getIC2Item("generator", 1L), GT_ModHandler.getIC2Item("waterMill", 2L), 6400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.RedAlloy, 2L), GT_Items.Circuit_Board_Basic.get(1L), 800, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Electrum, 2L), GT_Items.Circuit_Board_Basic.get(2L), 800, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plastic, 2L), GregTech_API.getGregTechComponent(89, 1), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2L), GregTech_API.getGregTechComponent(89, 1), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 1L), GregTech_API.getGregTechComponent(88, 1), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GregTech_API.getGregTechComponent(86, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 2L), new ItemStack(Items.iron_door, 1), GregTech_API.getGregTechComponent(69, 2), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 5L), new ItemStack(Blocks.chest, 1, 32767), new ItemStack(Blocks.hopper), 800, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 5L), new ItemStack(Blocks.trapped_chest, 1, 32767), new ItemStack(Blocks.hopper), 800, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_ModHandler.getIC2Item("pump", 1L), GregTech_API.getGregTechComponent(6, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), new ItemStack(Blocks.heavy_weighted_pressure_plate, 1, 32767), GregTech_API.getGregTechComponent(11, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), new ItemStack(Blocks.light_weighted_pressure_plate, 1, 32767), GregTech_API.getGregTechComponent(10, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_ModHandler.getIC2Item("ecMeter", 1L), GregTech_API.getGregTechComponent(15, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 2L), new ItemStack(Blocks.iron_bars, 2), GregTech_API.getGregTechComponent(9, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), new ItemStack(Items.comparator, 1), GregTech_API.getGregTechComponent(30, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), new ItemStack(Blocks.lever, 1), GregTech_API.getGregTechComponent(31, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), new ItemStack(Blocks.crafting_table, 1), GregTech_API.getGregTechComponent(64, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_ModHandler.getIC2Item("energyCrystal", 1L, 32767), GregTech_API.getGregTechComponent(12, 1), 1600, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_ModHandler.getIC2Item("lapotronCrystal", 1L, 32767), GregTech_API.getGregTechComponent(13, 1), 3200, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_Items.Energy_LapotronicOrb.get(1L), GregTech_API.getGregTechComponent(14, 1), 6400, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 2L), GT_Items.Circuit_Basic.get(1L), GregTech_API.getGregTechComponent(22, 4), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.RedAlloy, 2L), GT_Items.Circuit_Board_Basic.get(1L), 800, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Electrum, 2L), GT_Items.Circuit_Board_Basic.get(2L), 800, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plastic, 2L), GregTech_API.getGregTechComponent(89, 1), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 2L), GregTech_API.getGregTechComponent(89, 1), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 1L), GregTech_API.getGregTechComponent(88, 1), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GregTech_API.getGregTechComponent(86, 1), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 2L), new ItemStack(Items.iron_door, 1), GregTech_API.getGregTechComponent(69, 2), 800, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Electrum, 2L), GT_Items.Circuit_Basic.get(1L), GT_Items.Circuit_Board_Advanced.get(1L), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Silicon, 1L), GT_Items.Circuit_Board_Advanced.get(2L), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Platinum, 1L), GT_Items.Circuit_Advanced.get(1L), GT_Items.Circuit_Board_Elite.get(1L), 3200, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Magnalium, 2L), GT_ModHandler.getIC2Item("generator", 1L), GT_ModHandler.getIC2Item("windMill", 1L), 6400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 1L), GT_ModHandler.getIC2Item("reinforcedStone", 1L), new ItemStack(GregTech_API.sBlockList[4], 1, 8), 400, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 1L), new ItemStack(GregTech_API.sBlockList[0], 1, 2), new ItemStack(GregTech_API.sBlockList[4], 1, 9), 400, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 1L), GT_ModHandler.getIC2Item("reinforcedStone", 1L), new ItemStack(GregTech_API.sBlockList[0], 1, 2), 400, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 1L), new ItemStack(GregTech_API.sBlockList[4], 1, 8), new ItemStack(GregTech_API.sBlockList[4], 1, 9), 400, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2L), GT_ModHandler.getRCItem("machine.beta.engine.steam.high", 1L, GregTech_API.getGregTechBlock(1, 1, 34)), GregTech_API.getGregTechComponent(80, 1), 1600, 32);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2L), new ItemStack(Blocks.glass_pane, 1, 32767), GregTech_API.getGregTechComponent(81, 1), 1600, 32);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Emerald, 8L), GT_Items.Circuit_Advanced.get(1L), GregTech_API.getGregTechComponent(3, 4), 6400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Olivine, 8L), GT_Items.Circuit_Advanced.get(1L), GregTech_API.getGregTechComponent(3, 4), 6400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderPearl, 1L), new ItemStack(Items.blaze_powder, 1, 0), new ItemStack(Items.ender_eye, 1, 0), 400, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.EnderPearl, 6L), new ItemStack(Items.blaze_rod, 1, 0), new ItemStack(Items.ender_eye, 6, 0), 2500, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 6L), GregTech_API.getGregTechComponent(22, 1), GregTech_API.getGregTechComponent(32, 1), 400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Bronze, 6L), GregTech_API.getGregTechComponent(22, 1), GregTech_API.getGregTechComponent(33, 1), 400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Brass, 6L), GregTech_API.getGregTechComponent(22, 1), GregTech_API.getGregTechComponent(34, 1), 400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 6L), GregTech_API.getGregTechComponent(22, 1), GregTech_API.getGregTechComponent(35, 1), 400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Titanium, 6L), GregTech_API.getGregTechComponent(22, 1), GregTech_API.getGregTechComponent(36, 1), 400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 8L), GregTech_API.getGregTechComponent(22, 1), GT_ModHandler.getIC2Item("machine", 1L, GregTech_API.getGregTechComponent(37, 1)), 400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.TungstenSteel, 6L), GregTech_API.getGregTechComponent(22, 1), GregTech_API.getGregTechComponent(38, 1), 400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 6L), GregTech_API.getGregTechComponent(22, 1), GregTech_API.getGregTechComponent(39, 1), 400, 8);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Diamond, 4L), GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Diamond, 1L), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Diamond, 4L), GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Diamond, 1L), 1600, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Flint, 5L), new ItemStack(Blocks.tnt, 3, 32767), GT_ModHandler.getIC2Item("industrialTnt", 5L), 800, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gunpowder, 4L), new ItemStack(Blocks.sand, 4, 32767), new ItemStack(Blocks.tnt, 1), 400, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lapis, 1L), GregTech_API.getGregTechComponent(24, 2), 800, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lazurite, 1L), GregTech_API.getGregTechComponent(24, 2), 800, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_Items.Cell_Empty.get(1L), GregTech_API.getGregTechComponent(56, 1), 800, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 4L), new ItemStack(Blocks.redstone_lamp, 1), 400, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Blocks.redstone_torch, 1), 400, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 4L), new ItemStack(Items.compass, 1), 400, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 4L), new ItemStack(Items.clock, 1), 400, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.IC2_Resin.get(1L), new ItemStack(Blocks.torch, 6), 400, 1);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 8L), new ItemStack(Items.flint, 1), GT_Items.IC2_Compressed_Coal_Ball.get(1L), 400, 4);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("tinCableItem", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 1L), GT_ModHandler.getIC2Item("insulatedTinCableItem", 1L), 100, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("copperCableItem", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 1L), GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1L), 100, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("goldCableItem", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 2L), GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1L), 200, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("ironCableItem", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 3L), GT_ModHandler.getIC2Item("insulatedIronCableItem", 1L), 300, 2);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Wood, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Items.wooden_sword, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Stone, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Items.stone_sword, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Items.iron_sword, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Items.golden_sword, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Diamond, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), new ItemStack(Items.diamond_sword, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.Tool_Sword_Bronze.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Flint, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.Tool_Sword_Flint.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 1L), GT_Items.Tool_Sword_Steel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 1L), GT_Items.Tool_Sword_TungstenSteel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Wood, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.wooden_pickaxe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Stone, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.stone_pickaxe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.iron_pickaxe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.golden_pickaxe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Diamond, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.diamond_pickaxe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), GT_Items.Tool_Pickaxe_Bronze.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Flint, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), GT_Items.Tool_Pickaxe_Flint.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 2L), GT_Items.Tool_Pickaxe_Steel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 2L), GT_Items.Tool_Pickaxe_TungstenSteel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Wood, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.wooden_shovel, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Stone, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.stone_shovel, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.iron_shovel, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.golden_shovel, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Diamond, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.diamond_shovel, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), GT_Items.Tool_Shovel_Bronze.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Flint, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), GT_Items.Tool_Shovel_Flint.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 2L), GT_Items.Tool_Shovel_Steel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 2L), GT_Items.Tool_Shovel_TungstenSteel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Wood, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.wooden_axe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Stone, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.stone_axe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.iron_axe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.golden_axe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Diamond, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.diamond_axe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), GT_Items.Tool_Axe_Bronze.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Flint, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), GT_Items.Tool_Axe_Flint.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 2L), GT_Items.Tool_Axe_Steel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 2L), GT_Items.Tool_Axe_TungstenSteel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Wood, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.wooden_hoe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Stone, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.stone_hoe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.iron_hoe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Gold, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.golden_hoe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Diamond, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), new ItemStack(Items.diamond_hoe, 1), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), GT_Items.Tool_Hoe_Bronze.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Flint, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2L), GT_Items.Tool_Hoe_Flint.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 2L), GT_Items.Tool_Hoe_Steel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 2L), GT_Items.Tool_Hoe_TungstenSteel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 4L), GT_Items.Tool_Saw_Iron.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 4L), GT_Items.Tool_Saw_Bronze.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 4L), GT_Items.Tool_Saw_Steel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 4L), GT_Items.Tool_Saw_TungstenSteel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.Tool_Hammer_Iron.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.Tool_Hammer_Bronze.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, Materials.Rubber, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.Tool_Hammer_Rubber.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, Materials.Plastic, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 1L), GT_Items.Tool_Hammer_Plastic.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 1L), GT_Items.Tool_Hammer_Steel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 1L), GT_Items.Tool_Hammer_TungstenSteel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.Tool_File_Iron.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, Materials.Bronze, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.Tool_File_Bronze.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Iron, 1L), GT_Items.Tool_File_Steel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 1L), GT_Items.Tool_File_TungstenSteel.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadScrewdriver, Materials.Iron, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1L), GT_Items.Tool_Screwdriver_Iron.getUndamaged(1L), 100, 16);
        GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.toolHeadScrewdriver, Materials.TungstenSteel, 1L), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Steel, 1L), GT_Items.Tool_Screwdriver_TungstenSteel.getUndamaged(1L), 100, 16);
        
        GT_ModHandler.removeRecipe(new ItemStack(Items.lava_bucket), GT_Items.Cell_Empty.get(1L));
        GT_ModHandler.removeRecipe(new ItemStack(Items.water_bucket), GT_Items.Cell_Empty.get(1L));
        GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("compressedPlantBall", 1L), GT_Items.Cell_Empty.get(1L));
        GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("hydratedCoalClump", 1L), GT_Items.Cell_Empty.get(1L));
        GT_ModHandler.removeFurnaceSmelting(GT_Items.IC2_Resin.get(1L));
        
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Ice, 1L), 0, new ItemStack(Blocks.ice, 1), null, null, GT_Items.Cell_Empty.get(1L), 40);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.magma_cream, 1), 0, new ItemStack(Items.blaze_powder, 1), new ItemStack(Items.slime_ball, 1), null, null, 500);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Hydrogen, 4L), 0, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Deuterium, 1L), null, null, GT_Items.Cell_Empty.get(3L), 3000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium, 16L), 0, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium_3, 1L), null, null, GT_Items.Cell_Empty.get(15L), 10000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Deuterium, 4L), 0, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Tritium, 1L), null, null, GT_Items.Cell_Empty.get(3L), 3000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 4L), 0, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium235, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plutonium, 1L), null, null, 50000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plutonium, 4L), 0, GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Plutonium241, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium, 1L), null, null, 100000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.dirt, 16), 0, GT_ModHandler.getIC2Item("compressedPlantBall", 1L), GT_ModHandler.getIC2Item("plantBall", 1L), new ItemStack(Items.clay_ball, 1), new ItemStack(Blocks.sand, 8), 2500);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.grass, 16), 0, GT_ModHandler.getIC2Item("compressedPlantBall", 2L), GT_ModHandler.getIC2Item("plantBall", 2L), new ItemStack(Items.clay_ball, 1), new ItemStack(Blocks.sand, 8), 2500);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.mycelium, 8), 0, new ItemStack((Block)Blocks.brown_mushroom, 2), new ItemStack((Block)Blocks.red_mushroom, 2), new ItemStack(Items.clay_ball, 1), new ItemStack(Blocks.sand, 4), 1650);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.golden_apple, 1, 1), 2, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 2L), new ItemStack(Items.gold_ingot, 64), null, null, 10000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.golden_apple, 1, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), new ItemStack(Items.gold_ingot, 7), null, null, 10000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.golden_carrot, 1, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), new ItemStack(Items.gold_nugget, 6), null, null, 10000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.speckled_melon, 1, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), new ItemStack(Items.gold_nugget, 6), null, null, 10000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.apple, 32, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.mushroom_stew, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.bread, 64, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.porkchop, 12, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.cooked_porkchop, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.beef, 12, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.cooked_beef, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.fish, 12, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.cooked_fished, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.chicken, 12, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.cooked_chicken, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.melon, 64, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.melon_seeds, 1, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.pumpkin, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.rotten_flesh, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.spider_eye, 32, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.carrot, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.potato, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.poisonous_potato, 12, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.baked_potato, 24, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.cookie, 64, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.cake, 8, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.brown_mushroom_block, 12, 32767), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.red_mushroom_block, 12, 32767), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.brown_mushroom, 32, 32767), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.red_mushroom, 32, 32767), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.nether_wart, 32, 32767), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_ModHandler.getIC2Item("terraWart", 16L), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Items.IC2_Resin.get(4L), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rubber, 12L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Plastic, 6L), GT_ModHandler.getIC2Item("compressedPlantBall", 1L), GT_ModHandler.getIC2Item("plantBall", 1L), 1300);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Blocks.soul_sand, 16), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L), new ItemStack(Blocks.sand, 10), 2500);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Items.lava_bucket, 8), 0, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L), 5000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Lava, 8L), 0, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 17L), 6000);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Netherrack, 16L), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L), 2400);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Endstone, 16L), 2, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium_3, 1L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), new ItemStack(Blocks.sand, 12), 4800);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2L), 0, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1L), GT_Items.TE_Slag.get(1L, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Ash, 1L)), null, null, 250);
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 16L), 1, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 8L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold, 8L), GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Helium, 1L), null, 25000);
        
        for (final MaterialStack[] tMats : this.mAlloySmelterList) {
            final ItemStack tDust1 = GT_OreDictUnificator.get(OrePrefixes.dust, tMats[0].mMaterial, tMats[0].mAmount);
            final ItemStack tDust2 = GT_OreDictUnificator.get(OrePrefixes.dust, tMats[1].mMaterial, tMats[1].mAmount);
            final ItemStack tIngot1 = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[0].mMaterial, tMats[0].mAmount);
            final ItemStack tIngot2 = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[1].mMaterial, tMats[1].mAmount);
            final ItemStack tOutputIngot = GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount);
            
            if (tOutputIngot != null) {
                GT_ModHandler.addAlloySmelterRecipe(tIngot1, tDust2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false);
                GT_ModHandler.addAlloySmelterRecipe(tIngot1, tIngot2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false);
                GT_ModHandler.addAlloySmelterRecipe(tDust1, tIngot2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false);
                GT_ModHandler.addAlloySmelterRecipe(tDust1, tDust2, tOutputIngot, (int)tMats[2].mAmount * 50, 16, false);
            }
        }
    }
}
