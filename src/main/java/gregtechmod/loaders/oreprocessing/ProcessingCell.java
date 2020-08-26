package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.MaterialStack;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ProcessingCell implements IOreRecipeRegistrator {

   public ProcessingCell() {
      OrePrefixes.cell.add(this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aMaterial == Materials.Empty) {
         GT_ModHandler.removeRecipeByOutput(aStack);
         if(aModName.equalsIgnoreCase("AtomicScience")) {
            GT_ModHandler.addExtractionRecipe(GT_Items.Cell_Empty.get(1L, new Object[0]), aStack);
         }
      } else {
         if(aMaterial.mFuelPower > 0) {
            GregTech_API.sRecipeAdder.addFuel(GT_Utility.copyAmount(1L, aStack), GT_Utility.getFluidForFilledItem(aStack) == null?GT_Utility.getContainerItem(aStack):null, aMaterial.mFuelPower, aMaterial.mFuelType);
         }

         if(aMaterial == Materials.Ice) {
            GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Water, 1L));
         }

         if(aMaterial.mMaterialList.size() > 0 && (aMaterial.mExtraData & 3) != 0) {
            int tAllAmount = 0;

            MaterialStack tMat;
            for(Iterator<MaterialStack> tItemAmount = aMaterial.mMaterialList.iterator(); tItemAmount.hasNext(); tAllAmount = (int)((long)tAllAmount + tMat.mAmount)) {
               tMat = (MaterialStack)tItemAmount.next();
            }

            long tItemAmount1 = 0L;
            long tCapsuleCount = (long)(GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(new ItemStack[]{aStack}) * -tAllAmount);
            long tDensityMultiplier = aMaterial.getDensity() > 3628800L?aMaterial.getDensity() / 3628800L:1L;
            List<ItemStack> tList = new ArrayList<>();
            Iterator<MaterialStack> iterator = aMaterial.mMaterialList.iterator();
            
            while(iterator.hasNext()) {
               MaterialStack tMat1 = (MaterialStack)iterator.next();
               if(tMat1.mAmount > 0L) {
                  ItemStack tStack;
                  if(tMat1.mMaterial == Materials.Oxygen) {
                     tStack = GT_Items.Cell_Air.get(tMat1.mAmount * tDensityMultiplier / 2L, new Object[0]);
                  } else {
                     tStack = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)tMat1.mMaterial, tMat1.mAmount);
                     if(tStack == null) {
                        tStack = GT_OreDictUnificator.get(OrePrefixes.cell, (Object)tMat1.mMaterial, tMat1.mAmount);
                     }
                  }

                  if(tItemAmount1 + tMat1.mAmount * 3628800L <= (long)aStack.getMaxStackSize() * aMaterial.getDensity()) {
                     tItemAmount1 += tMat1.mAmount * 3628800L;
                     if(tStack != null) {
                        for(tStack.stackSize = (int)((long)tStack.stackSize * tDensityMultiplier); tStack.stackSize > 64; tStack.stackSize -= 64) {
                           if(tCapsuleCount + (long)(GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64) < 0L) {
                              if(tList.size() >= 3) {
                                 break;
                              }
                           } else if(tList.size() >= 4) {
                              break;
                           }

                           if(tCapsuleCount + (long)(GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64) > 64L) {
                              break;
                           }

                           tCapsuleCount += (long)(GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64);
                           tList.add(GT_Utility.copyAmount(64L, tStack));
                        }

                        if(tStack.stackSize > 0 && tCapsuleCount + (long)GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(new ItemStack[]{tStack}) <= 64L) {
                           if(tCapsuleCount + (long)GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(new ItemStack[]{tStack}) < 0L) {
                              if(tList.size() >= 3) {
                                 continue;
                              }
                           } else if(tList.size() >= 4) {
                              continue;
                           }

                           tCapsuleCount += (long)GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(new ItemStack[]{tStack});
                           tList.add(tStack);
                        }
                     }
                  }
               }
            }

            tItemAmount1 = (long)(tItemAmount1 * tDensityMultiplier % aMaterial.getDensity() > 0L?1:0) + tItemAmount1 * tDensityMultiplier / aMaterial.getDensity();
            if(tList.size() > 0) {
               if((aMaterial.mExtraData & 1) != 0) {
                  GregTech_API.sRecipeAdder.addElectrolyzerRecipe(GT_Utility.copyAmount(tItemAmount1, aStack), tCapsuleCount > 0L?(int)tCapsuleCount:0, (ItemStack)tList.get(0), tList.size() < 2?null:(ItemStack)tList.get(1), tList.size() < 3?null:(ItemStack)tList.get(2), tCapsuleCount < 0L?GT_Items.Cell_Empty.get(-tCapsuleCount, new Object[0]):(tList.size() < 4?null:(ItemStack)tList.get(3)), Math.max(1, Math.abs(aMaterial.getProtons() * 8 * (int)tItemAmount1)), Math.min(4, tList.size()) * 30);
               }

               if((aMaterial.mExtraData & 2) != 0) {
                  GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(tItemAmount1, aStack), tCapsuleCount > 0L?(int)tCapsuleCount:0, (ItemStack)tList.get(0), tList.size() < 2?null:(ItemStack)tList.get(1), tList.size() < 3?null:(ItemStack)tList.get(2), tCapsuleCount < 0L?GT_Items.Cell_Empty.get(-tCapsuleCount, new Object[0]):(tList.size() < 4?null:(ItemStack)tList.get(3)), Math.max(1, Math.abs(aMaterial.getMass() * 2 * (int)tItemAmount1)));
               }
            }
         }
      }

   }
}
