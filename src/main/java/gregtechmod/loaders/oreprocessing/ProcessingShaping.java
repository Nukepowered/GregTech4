package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingShaping implements IOreRecipeRegistrator {

   public ProcessingShaping() {
      OrePrefixes.ingot.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L) != null && !aMaterial.contains(SubTag.NO_SMELTING)) {
         int tAmount = (int)(aPrefix.mMaterialAmount / 3628800L);
         if(tAmount > 0 && tAmount <= 64 && aPrefix.mMaterialAmount % 3628800L == 0L) {
            if(!OrePrefixes.block.isIgnored(aMaterial)) {
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_Items.Shape_Extruder_Block.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.block, (Object)aMaterial, (long)tAmount), 10 * tAmount, 128);
            }

            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Pipe_Small.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.pipeSmall, (Object)aMaterial, (long)tAmount), 8 * tAmount, 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_Items.Shape_Extruder_Pipe_Medium.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.pipeMedium, (Object)aMaterial, (long)tAmount), 24 * tAmount, 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(6L, new Object[]{aStack}), GT_Items.Shape_Extruder_Pipe_Large.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.pipeLarge, (Object)aMaterial, (long)tAmount), 48 * tAmount, 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Ingot.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, (long)tAmount), 10, 80);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Plate.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 1 * tAmount, tAmount), 128);
            if(tAmount * 2 <= 64) {
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Rod.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.stick, (Object)aMaterial, (long)(tAmount * 2)), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 96);
            }

            if(tAmount * 8 <= 64) {
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Bolt.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.bolt, (Object)aMaterial, (long)(tAmount * 8)), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            }

            if(tAmount * 4 <= 64) {
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Ring.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.ring, (Object)aMaterial, (long)(tAmount * 4)), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 96);
            }

            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Extruder_Sword.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.toolHeadSword, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_Items.Shape_Extruder_Pickaxe.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.toolHeadPickaxe, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 3 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Shovel.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.toolHeadShovel, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 1 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_Items.Shape_Extruder_Axe.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.toolHeadAxe, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 3 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Extruder_Hoe.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.toolHeadHoe, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(6L, new Object[]{aStack}), GT_Items.Shape_Extruder_Hammer.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.toolHeadHammer, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 6 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Extruder_File.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.toolHeadFile, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Extruder_Saw.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.toolHeadSaw, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), GT_Items.Shape_Extruder_Gear.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.gearGt, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 5 * tAmount, tAmount), 128);
            GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Mold_Plate.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 2 * tAmount, tAmount), 32);
            GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_Items.Shape_Mold_Gear.get(0L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.gearGt, (Object)aMaterial, (long)tAmount), Math.max(aMaterial.getMass() * 10 * tAmount, tAmount), 32);
            switch(ProcessingShaping.NamelessClass1439261823.$SwitchMap$gregtechmod$api$enums$Materials[aMaterial.ordinal()]) {
            case 1:
               if(tAmount * 6 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Wire.get(0L, new Object[0]), GT_ModHandler.getIC2Item("ironCableItem", (long)(tAmount * 6)), tAmount * 8, 64);
               }

               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Cell.get(0L, new Object[0]), GT_ModHandler.getIC2Item("fuelRod", (long)tAmount), tAmount * 32, 64);
               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casingiron", (long)(tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Mold_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casingiron", (long)(tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case 2:
               if(tAmount * 4 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Wire.get(0L, new Object[0]), GT_ModHandler.getIC2Item("tinCableItem", (long)(tAmount * 4)), tAmount * 3, 64);
               }

               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Extruder_Cell.get(0L, new Object[0]), GT_Items.Cell_Empty.get((long)tAmount, new Object[0]), tAmount * 32, 64);
               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casingtin", (long)(tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Mold_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casingtin", (long)(tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case 3:
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Wire.get(0L, new Object[0]), GT_Items.Tool_SolderingMaterial_Tin.get((long)tAmount, new Object[0]), tAmount * 8, 64);
               break;
            case 4:
               GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Wire.get(0L, new Object[0]), GT_Items.Tool_SolderingMaterial_Lead.get((long)tAmount, new Object[0]), tAmount * 8, 64);
               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casinglead", (long)(tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Mold_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casinglead", (long)(tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case 5:
               if(tAmount * 3 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Wire.get(0L, new Object[0]), GT_ModHandler.getIC2Item("copperCableItem", (long)(tAmount * 3)), tAmount * 4, 64);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casingcopper", (long)(tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Mold_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casingcopper", (long)(tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case 6:
               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casingbronze", (long)(tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Mold_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casingbronze", (long)(tAmount * 3)), tAmount * 128, 12);
               }
               break;
            case 7:
               if(tAmount * 6 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Wire.get(0L, new Object[0]), GT_ModHandler.getIC2Item("goldCableItem", (long)(tAmount * 6)), tAmount * 4, 64);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addExtruderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.Shape_Extruder_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casinggold", (long)(tAmount * 2)), tAmount * 32, 48);
               }

               if(tAmount * 2 <= 64) {
                  GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_Items.Shape_Mold_Casing.get(0L, new Object[0]), GT_ModHandler.getIC2Item("casinggold", (long)(tAmount * 3)), tAmount * 128, 12);
               }
            }
         }
      }

   }

   // $FF: synthetic class
   static class NamelessClass1439261823 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$Materials = new int[Materials.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Iron.ordinal()] = 1;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Tin.ordinal()] = 2;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.SolderingAlloy.ordinal()] = 3;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Lead.ordinal()] = 4;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Copper.ordinal()] = 5;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Bronze.ordinal()] = 6;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Gold.ordinal()] = 7;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
