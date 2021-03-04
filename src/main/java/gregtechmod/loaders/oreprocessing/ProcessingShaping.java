package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

public class ProcessingShaping implements IOreRecipeRegistrator {

   public ProcessingShaping() {
      OrePrefixes.ingot.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L) != null && !aMaterial.contains(SubTag.NO_SMELTING)) {
         int tAmount = (int)(aPrefix.mMaterialAmount / GregTech_API.MATERIAL_UNIT);
         if(tAmount > 0 && tAmount <= 64 && aPrefix.mMaterialAmount % GregTech_API.MATERIAL_UNIT == 0L) {
            if(!OrePrefixes.block.isIgnored(aMaterial)) {
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(9, aStack), GT_Items.Shape_Extruder_Block.get(0), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, tAmount), 10 * tAmount, 128);
            }

            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Pipe_Small.get(0), GT_OreDictUnificator.get(OrePrefixes.pipeSmall, aMaterial, tAmount), 8 * tAmount, 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(3, aStack), GT_Items.Shape_Extruder_Pipe_Medium.get(0), GT_OreDictUnificator.get(OrePrefixes.pipeMedium, aMaterial, tAmount), 24 * tAmount, 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(6, aStack), GT_Items.Shape_Extruder_Pipe_Large.get(0), GT_OreDictUnificator.get(OrePrefixes.pipeLarge, aMaterial, tAmount), 48 * tAmount, 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Ingot.get(0), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, tAmount), 10, 80);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Plate.get(0), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, tAmount), Math.max(aMaterial.getMass() * 1 * tAmount, tAmount), 128);
            if(tAmount * 2 <= 64) {
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Rod.get(0), GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, (tAmount * 2)), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 96);
            }

            if(tAmount * 8 <= 64) {
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Bolt.get(0), GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, (tAmount * 8)), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            }

            if(tAmount * 4 <= 64) {
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Ring.get(0), GT_OreDictUnificator.get(OrePrefixes.ring, aMaterial, (tAmount * 4)), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 96);
            }

            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Extruder_Sword.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, aMaterial, tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(3, aStack), GT_Items.Shape_Extruder_Pickaxe.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, aMaterial, tAmount), Math.max(aMaterial.getMass() * 3 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Shovel.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, aMaterial, tAmount), Math.max(aMaterial.getMass() * 1 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(3, aStack), GT_Items.Shape_Extruder_Axe.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, aMaterial, tAmount), Math.max(aMaterial.getMass() * 3 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Extruder_Hoe.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, aMaterial, tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(6, aStack), GT_Items.Shape_Extruder_Hammer.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, aMaterial, tAmount), Math.max(aMaterial.getMass() * 6 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Extruder_File.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, aMaterial, tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Extruder_Saw.get(0), GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, aMaterial, tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(4, aStack), GT_Items.Shape_Extruder_Gear.get(0), GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial, tAmount), Math.max(aMaterial.getMass() * 5 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Mold_Plate.get(0), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 32);
            GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(8, aStack), GT_Items.Shape_Mold_Gear.get(0), GT_OreDictUnificator.get(OrePrefixes.gearGt, aMaterial, tAmount), Math.max(aMaterial.getMass() * 10 * tAmount, tAmount), 32);
            switch(aMaterial) {
            case Iron:
               if(tAmount * 6 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Wire.get(0), GT_ModHandler.getIC2Item("ironCableItem", (tAmount * 6)), tAmount * 8, 64);
               }

               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Cell.get(0), GT_ModHandler.getIC2Item("fuelRod", tAmount), tAmount * 32, 64);
               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Casing.get(0), GT_ModHandler.getIC2Item("casingiron", (tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Mold_Casing.get(0), GT_ModHandler.getIC2Item("casingiron", (tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case Tin:
               if(tAmount * 4 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Wire.get(0), GT_ModHandler.getIC2Item("tinCableItem", (tAmount * 4)), tAmount * 3, 64);
               }

               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Extruder_Cell.get(0), GT_Items.Cell_Empty.get(tAmount), tAmount * 32, 64);
               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Casing.get(0), GT_ModHandler.getIC2Item("casingtin", (tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Mold_Casing.get(0), GT_ModHandler.getIC2Item("casingtin", (tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case SolderingAlloy:
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Wire.get(0), GT_Items.Tool_SolderingMaterial_Tin.get(tAmount), tAmount * 8, 64);
               break;
            case Lead:
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Wire.get(0), GT_Items.Tool_SolderingMaterial_Lead.get(tAmount), tAmount * 8, 64);
               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Casing.get(0), GT_ModHandler.getIC2Item("casinglead", (tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Mold_Casing.get(0), GT_ModHandler.getIC2Item("casinglead", (tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case Copper:
               if(tAmount * 3 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Wire.get(0), GT_ModHandler.getIC2Item("copperCableItem", (tAmount * 3)), tAmount * 4, 64);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Casing.get(0), GT_ModHandler.getIC2Item("casingcopper", (tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Mold_Casing.get(0), GT_ModHandler.getIC2Item("casingcopper", (tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case Bronze:
               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Casing.get(0), GT_ModHandler.getIC2Item("casingbronze", (tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Mold_Casing.get(0), GT_ModHandler.getIC2Item("casingbronze", (tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case Gold:
               if(tAmount * 6 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Wire.get(0), GT_ModHandler.getIC2Item("goldCableItem", (tAmount * 6)), tAmount * 4, 64);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1, aStack), GT_Items.Shape_Extruder_Casing.get(0), GT_ModHandler.getIC2Item("casinggold", (tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2, aStack), GT_Items.Shape_Mold_Casing.get(0), GT_ModHandler.getIC2Item("casinggold", (tAmount * 3)), tAmount * 128, 12);
               }
            default: break;
            }
         }
      }
   }
}
