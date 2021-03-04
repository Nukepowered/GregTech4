package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.MaterialStack;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingDust implements IOreRecipeRegistrator {

   public ProcessingDust() {
      OrePrefixes.dust.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(aMaterial.mFuelPower > 0) {
         GregTech_API.sRecipeAdder.addFuel(GT_Utility.copyAmount(1L, new Object[]{aStack}), (ItemStack)null, aMaterial.mFuelPower, aMaterial.mFuelType);
      }

      if(aMaterial.mAmplificationValue > 0) {
         GT_ModHandler.addIC2MatterAmplifier(GT_Utility.copyAmount(1L, new Object[]{aStack}), aMaterial.mAmplificationValue);
      }

      GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)aMaterial, 4L), new Object[]{" X", "  ", Character.valueOf('X'), aOreDictName});
      GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)aMaterial, 9L), new Object[]{"X ", "  ", Character.valueOf('X'), aOreDictName});
      GregTech_API.sRecipeAdder.addCannerRecipe(aStack, aMaterial == Materials.Milk?GT_Items.Cell_Water.get(1L, new Object[0]):GT_Items.Cell_Empty.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)aMaterial, 1L), (ItemStack)null, 100, 1);
      ItemStack tStack;
      if(null != (tStack = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L)) && !aMaterial.contains(SubTag.NO_SMELTING)) {
         if(aMaterial.mBlastFurnaceRequired) {
            GT_ModHandler.removeFurnaceSmelting(aStack);
            GregTech_API.sRecipeAdder.addBlastRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), (ItemStack)null, aMaterial.mBlastFurnaceTemp > 1750?GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial, tStack, 1L):GT_Utility.copyAmount(1L, new Object[]{tStack}), (ItemStack)null, Math.max(aMaterial.getMass() / 40, 1) * aMaterial.mBlastFurnaceTemp, 120, aMaterial.mBlastFurnaceTemp);
            if(aMaterial.mBlastFurnaceTemp <= 1000) {
               GT_ModHandler.addRCBlastFurnaceRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Utility.copyAmount(1L, new Object[]{tStack}), aMaterial.mBlastFurnaceTemp);
            }
         } else {
            GT_ModHandler.addSmeltingRecipe(aStack, tStack);
         }
      } else {
         if(!OrePrefixes.block.isIgnored(aMaterial) && null == GT_OreDictUnificator.get(OrePrefixes.gem, (Object)aMaterial, 1L)) {
            GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.block, (Object)aMaterial, 1L));
         }

         if((OrePrefixes.block.isIgnored(aMaterial) || null == GT_OreDictUnificator.get(OrePrefixes.block, (Object)aMaterial, 1L)) && aMaterial != Materials.GraniteRed && aMaterial != Materials.GraniteBlack && aMaterial != Materials.Obsidian && aMaterial != Materials.Glowstone && aMaterial != Materials.Paper) {
            GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L));
         }
      }

      if(aMaterial.mMaterialList.size() > 0 && (aMaterial.mExtraData & 3) != 0) {
         long tItemAmount = 0L;
         long tCapsuleCount = 0L;
         long tDensityMultiplier = aMaterial.getDensity() > 3628800L?aMaterial.getDensity() / 3628800L:1L;
         List<ItemStack> tList = new ArrayList<>();
         Iterator<MaterialStack> iterator = aMaterial.mMaterialList.iterator();

         while(iterator.hasNext()) {
            MaterialStack tMat = (MaterialStack)iterator.next();
            if(tMat.mAmount > 0L) {
               if(tMat.mMaterial == Materials.Oxygen) {
                  tStack = GT_Items.Cell_Air.get(tMat.mAmount / 2L, new Object[0]);
               } else {
                  tStack = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)tMat.mMaterial, tMat.mAmount);
                  if(tStack == null) {
                     tStack = GT_OreDictUnificator.get(OrePrefixes.cell, (Object)tMat.mMaterial, tMat.mAmount);
                  }
               }

               if(tItemAmount + tMat.mAmount * 3628800L <= (long)aStack.getMaxStackSize() * aMaterial.getDensity()) {
                  tItemAmount += tMat.mAmount * 3628800L;
                  if(tStack != null) {
                     for(tStack.stackSize = (int)((long)tStack.stackSize * tDensityMultiplier); tStack.stackSize > 64 && tList.size() < 4 && tCapsuleCount + (long)(GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64) <= 64L; tStack.stackSize -= 64) {
                        tCapsuleCount += (long)(GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64);
                        tList.add(GT_Utility.copyAmount(64L, new Object[]{tStack}));
                     }

                     if(tStack.stackSize > 0 && tList.size() < 4 && tCapsuleCount + (long)GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(new ItemStack[]{tStack}) <= 64L) {
                        tCapsuleCount += (long)GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(new ItemStack[]{tStack});
                        tList.add(tStack);
                     }
                  }
               }
            }
         }

         tItemAmount = (long)(tItemAmount * tDensityMultiplier % aMaterial.getDensity() > 0L?1:0) + tItemAmount * tDensityMultiplier / aMaterial.getDensity();
         if(tList.size() > 0) {
            if((aMaterial.mExtraData & 1) != 0) {
               GregTech_API.sRecipeAdder.addElectrolyzerRecipe(GT_Utility.copyAmount(tItemAmount, new Object[]{aStack}), (int)tCapsuleCount, (ItemStack)tList.get(0), tList.size() < 2?null:(ItemStack)tList.get(1), tList.size() < 3?null:(ItemStack)tList.get(2), tList.size() < 4?null:(ItemStack)tList.get(3), Math.max(1, Math.abs(aMaterial.getProtons() * 2 * (int)tItemAmount)), Math.min(4, tList.size()) * 30);
            }

            if((aMaterial.mExtraData & 2) != 0) {
               GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(tItemAmount, new Object[]{aStack}), (int)tCapsuleCount, (ItemStack)tList.get(0), tList.size() < 2?null:(ItemStack)tList.get(1), tList.size() < 3?null:(ItemStack)tList.get(2), tList.size() < 4?null:(ItemStack)tList.get(3), Math.max(1, Math.abs(aMaterial.getMass() * 8 * (int)tItemAmount)));
            }
         }
      }

      switch(ProcessingDust.NamelessClass1657544856.$SwitchMap$gregtechmod$api$enums$Materials[aMaterial.ordinal()]) {
      case 1:
      default:
         break;
      case 2:
         GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new ItemStack(Items.bread, 1, 0));
         break;
      case 3:
         GregTech_API.sRecipeAdder.addCannerRecipe(aStack, new ItemStack(Items.water_bucket, 1), new ItemStack(Items.milk_bucket, 1), (ItemStack)null, 100, 1);
         break;
      case 4:
         GT_Log.log.error("Quicksilver Dust?, To melt that, you don\'t even need a Furnace...");
         break;
      case 5:
      case 6:
      case 7:
         GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Copper, 6L));
         break;
      case 8:
         GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Nickel, 6L));
         break;
      case 9:
         GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Nickel, 1L));
         break;
      case 10:
         GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)Materials.Tin, 1L));
         break;
      case 11:
         GT_ModHandler.addLiquidTransposerFillRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_ModHandler.getWater(125L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.HydratedCoal, 1L), 125);
         break;
      case 12:
         GT_ModHandler.addLiquidTransposerEmptyRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_ModHandler.getWater(125L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Coal, 1L), 125);
         break;
      case 13:
         GregTech_API.sRecipeAdder.addImplosionRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), 32, GT_Items.IC2_Industrial_Diamond.get(3L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 16L));
         break;
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
         GregTech_API.sRecipeAdder.addImplosionRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), 24, GT_OreDictUnificator.get(OrePrefixes.gem, (Object)aMaterial, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 12L));
         break;
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
         GregTech_API.sRecipeAdder.addImplosionRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), 16, GT_OreDictUnificator.get(OrePrefixes.gem, (Object)aMaterial, 3L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 8L));
      }

   }

   // $FF: synthetic class
   static class NamelessClass1657544856 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$Materials = new int[Materials.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials._NULL.ordinal()] = 1;
         } catch (NoSuchFieldError var31) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Wheat.ordinal()] = 2;
         } catch (NoSuchFieldError var30) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Milk.ordinal()] = 3;
         } catch (NoSuchFieldError var29) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Mercury.ordinal()] = 4;
         } catch (NoSuchFieldError var28) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Tetrahedrite.ordinal()] = 5;
         } catch (NoSuchFieldError var27) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Chalcopyrite.ordinal()] = 6;
         } catch (NoSuchFieldError var26) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Malachite.ordinal()] = 7;
         } catch (NoSuchFieldError var25) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Pentlandite.ordinal()] = 8;
         } catch (NoSuchFieldError var24) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Garnierite.ordinal()] = 9;
         } catch (NoSuchFieldError var23) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Cassiterite.ordinal()] = 10;
         } catch (NoSuchFieldError var22) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Coal.ordinal()] = 11;
         } catch (NoSuchFieldError var21) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.HydratedCoal.ordinal()] = 12;
         } catch (NoSuchFieldError var20) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Diamond.ordinal()] = 13;
         } catch (NoSuchFieldError var19) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Opal.ordinal()] = 14;
         } catch (NoSuchFieldError var18) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Olivine.ordinal()] = 15;
         } catch (NoSuchFieldError var17) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Emerald.ordinal()] = 16;
         } catch (NoSuchFieldError var16) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Ruby.ordinal()] = 17;
         } catch (NoSuchFieldError var15) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Sapphire.ordinal()] = 18;
         } catch (NoSuchFieldError var14) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.GreenSapphire.ordinal()] = 19;
         } catch (NoSuchFieldError var13) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Topaz.ordinal()] = 20;
         } catch (NoSuchFieldError var12) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.BlueTopaz.ordinal()] = 21;
         } catch (NoSuchFieldError var11) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Tanzanite.ordinal()] = 22;
         } catch (NoSuchFieldError var10) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.FoolsRuby.ordinal()] = 23;
         } catch (NoSuchFieldError var9) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.GarnetRed.ordinal()] = 24;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.GarnetYellow.ordinal()] = 25;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Jasper.ordinal()] = 26;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Amber.ordinal()] = 27;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Monazite.ordinal()] = 28;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Forcicium.ordinal()] = 29;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Forcillium.ordinal()] = 30;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Force.ordinal()] = 31;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
