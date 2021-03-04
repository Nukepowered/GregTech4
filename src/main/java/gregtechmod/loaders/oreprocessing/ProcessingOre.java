package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.Iterator;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingOre implements IOreRecipeRegistrator {

   public ProcessingOre() {
      OrePrefixes.ore.add((IOreRecipeRegistrator)this);
      OrePrefixes.oreEnd.add((IOreRecipeRegistrator)this);
      OrePrefixes.oreNether.add((IOreRecipeRegistrator)this);
      OrePrefixes.oreDense.add((IOreRecipeRegistrator)this);
   }

   @SuppressWarnings("deprecation")
   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(aStack.getItem() instanceof ItemBlock && aStack.getItem().getItemStackLimit() > GT_Mod.sOreStackSize) {
         aStack.getItem().setMaxStackSize(GT_Mod.sOreStackSize);
      }

      if(aMaterial == Materials.Oilsands) {
         GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Oil, aPrefix != OrePrefixes.oreNether && aPrefix != OrePrefixes.oreDense?1L:2L), new ItemStack(Blocks.sand, 1, 0), (ItemStack)null, (ItemStack)null, aPrefix != OrePrefixes.oreNether && aPrefix != OrePrefixes.oreDense?1000:2000);
      } else {
         registerStandardOreRecipes(aPrefix, aMaterial, GT_Utility.copyAmount(1L, new Object[]{aStack}), aPrefix != OrePrefixes.oreNether && aPrefix != OrePrefixes.oreDense?1:2);
      }

   }

   private static boolean registerStandardOreRecipes(OrePrefixes aPrefix, Materials aMaterial, ItemStack aOreStack, int aMultiplier) {
      if(aOreStack != null && aMaterial != null) {
         GT_ModHandler.addValuableOre(aOreStack.copy(), aMaterial.mOreValue);
         Materials tMaterial = aMaterial.mOreReplacement;
         Materials tPrimaryByMaterial = null;
         Materials tSecondaryByMaterial = null;
         aMultiplier = Math.max(1, aMultiplier);
         aOreStack = GT_Utility.copyAmount(1L, new Object[]{aOreStack});
         aOreStack.stackSize = 1;
         ItemStack tIngot = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial.mDirectSmelting, 1L);
         ItemStack tGem = GT_OreDictUnificator.get(OrePrefixes.gem, (Object)tMaterial, 1L);
         ItemStack tSmeltInto = tIngot == null?(aMaterial.contains(SubTag.SMELTING_TO_GEM)?GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial.mDirectSmelting, GT_OreDictUnificator.get(OrePrefixes.crystal, tMaterial.mDirectSmelting, GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial, GT_OreDictUnificator.get(OrePrefixes.crystal, (Object)tMaterial, 1L), 1L), 1L), 1L):null):tIngot;
         ItemStack tSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)tMaterial, 1L);
         ItemStack tDust = GT_OreDictUnificator.get(OrePrefixes.dust, tMaterial, tGem, 1L);
         ItemStack tCleaned = GT_OreDictUnificator.get(OrePrefixes.crushedPurified, tMaterial, tDust, 1L);
         ItemStack tCrushed = GT_OreDictUnificator.get(OrePrefixes.crushed, (Object)tMaterial, (long)(aMaterial.mOreMultiplier * aMultiplier));
         ItemStack tPrimaryByProduct = null;
         ItemStack tPrimaryByProductSmall = null;
         ItemStack tSecondaryByProduct = null;
         ItemStack tSecondaryByProductSmall = null;
         if(tCrushed == null) {
            tCrushed = GT_OreDictUnificator.get(OrePrefixes.dustImpure, tMaterial, GT_Utility.copyAmount((long)(aMaterial.mOreMultiplier * aMultiplier), new Object[]{tCleaned, tDust, tGem}), (long)(aMaterial.mOreMultiplier * aMultiplier));
         }

         Iterator<Materials> iterator = aMaterial.mOreByProducts.iterator();

         while(iterator.hasNext()) {
            Materials tMat = (Materials)iterator.next();
            if(tPrimaryByProduct == null) {
               tPrimaryByMaterial = tMat;
               tPrimaryByProduct = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)tMat, 1L);
               tPrimaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)tMat, 1L);
               if(tPrimaryByProductSmall == null) {
                  tPrimaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMat, GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)tMat, 2L), 2L);
               }
            }

            if(tSecondaryByProduct == null || tSecondaryByMaterial == tPrimaryByMaterial) {
               tSecondaryByMaterial = tMat;
               tSecondaryByProduct = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)tMat, 1L);
               tSecondaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)tMat, 1L);
               if(tSecondaryByProductSmall == null) {
                  tSecondaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMat, GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)tMat, 2L), 2L);
               }
            }
         }

         if(tPrimaryByMaterial == null) {
            tPrimaryByMaterial = tMaterial;
         }

         if(tPrimaryByProduct == null) {
            tPrimaryByProduct = tDust;
         }

         if(tPrimaryByProductSmall == null) {
            tPrimaryByProductSmall = tSmall;
         }

         if(tSecondaryByMaterial == null) {
            tSecondaryByMaterial = tPrimaryByMaterial;
         }

         if(tSecondaryByProduct == null) {
            tSecondaryByProduct = tPrimaryByProduct;
         }

         if(tSecondaryByProductSmall == null) {
            tSecondaryByProductSmall = tPrimaryByProductSmall;
         }

         if(tSmeltInto != null) {
            if(!aMaterial.mBlastFurnaceRequired && !aMaterial.mDirectSmelting.mBlastFurnaceRequired) {
               GT_ModHandler.addInductionSmelterRecipe(aOreStack, new ItemStack(Blocks.sand, 1), GT_Utility.mul((long)(aMultiplier * (aMaterial.contains(SubTag.INDUCTIONSMELTING_LOW_OUTPUT)?1:2) * aMaterial.mSmeltingMultiplier), new Object[]{tSmeltInto}), GT_Items.TE_Slag_Rich.get(1L, new Object[0]), 300 * aMultiplier, 10 * aMultiplier);
               GT_ModHandler.addInductionSmelterRecipe(aOreStack, GT_Items.TE_Slag_Rich.get((long)aMultiplier, new Object[0]), GT_Utility.mul((long)(aMultiplier * (aMaterial.contains(SubTag.INDUCTIONSMELTING_LOW_OUTPUT)?2:3) * aMaterial.mSmeltingMultiplier), new Object[]{tSmeltInto}), GT_Items.TE_Slag.get((long)aMultiplier, new Object[0]), 300 * aMultiplier, 95);
               GT_ModHandler.addSmeltingRecipe(aOreStack, GT_Utility.copyAmount((long)(aMultiplier * aMaterial.mSmeltingMultiplier), new Object[]{tSmeltInto}));
            } else {
               GT_ModHandler.removeFurnaceSmelting(aOreStack);
            }

            if(aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_TRIPLE)) {
               GregTech_API.sRecipeAdder.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Calcite, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * 3 * aMaterial.mSmeltingMultiplier), new Object[]{tSmeltInto}), GT_Items.TE_Slag.get(1L, new Object[]{GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 1L)}), tSmeltInto.stackSize * 500, 120, 1500);
            } else if(aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_DOUBLE)) {
               GregTech_API.sRecipeAdder.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Calcite, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mSmeltingMultiplier), new Object[]{tSmeltInto}), GT_Items.TE_Slag.get(1L, new Object[]{GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.DarkAsh, 1L)}), tSmeltInto.stackSize * 500, 120, 1500);
            }
         }

         if(tCrushed != null) {
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammercrushing, aPrefix.get(aMaterial), true)) {
               GT_ModHandler.addCraftingRecipe(GT_Utility.copy(tCrushed), new Object[]{"T", "O", Character.valueOf('T'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('O'), aPrefix.get(aMaterial)});
            }

            GregTech_API.sRecipeAdder.addForgeHammerRecipe(aOreStack, GT_Utility.copy(tCrushed), 16, 10);
            GT_ModHandler.addPulverisationRecipe(aOreStack, GT_Utility.mul(2L, tCrushed), tMaterial.contains(SubTag.PULVERIZING_CINNABAR)?GT_OreDictUnificator.get(OrePrefixes.crystal, Materials.Cinnabar, GT_Utility.copyAmount(1L, new Object[]{tPrimaryByProduct}), 1L):GT_Utility.copyAmount(1L, new Object[]{tPrimaryByProduct}), tPrimaryByProduct == null?0:tPrimaryByProduct.stackSize * 10 * aMultiplier * aMaterial.mByProductMultiplier, true);
            if(tGem != null) {
               GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_Items.Cell_Water.get((long)aMultiplier, new Object[0]), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier), new Object[]{tGem}), tSmall == null?GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 2), new Object[]{tCleaned}):GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 6), new Object[]{tSmall}), GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               if(tSmall == null && tMaterial.contains(SubTag.WASHING_MERCURY)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier), new Object[]{tGem}), GT_Utility.mul((long)(aMultiplier * 3 * aMaterial.mOreMultiplier), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tMaterial.contains(SubTag.WASHING_MERCURY)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier), new Object[]{tGem}), GT_Utility.mul((long)(aMultiplier * 3 * aMaterial.mOreMultiplier), new Object[]{tDust}), GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tPrimaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier), new Object[]{tGem}), tSmall == null?GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mOreMultiplier), new Object[]{tCleaned}):GT_Utility.mul((long)(aMultiplier * 6 * aMaterial.mOreMultiplier), new Object[]{tSmall}), GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProduct}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tSmall == null && tMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SodiumPersulfate, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier), new Object[]{tGem}), GT_Utility.mul((long)(aMultiplier * 3 * aMaterial.mOreMultiplier), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SodiumPersulfate, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier), new Object[]{tGem}), GT_Utility.mul((long)(aMultiplier * 3 * aMaterial.mOreMultiplier), new Object[]{tDust}), GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tPrimaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SodiumPersulfate, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier), new Object[]{tGem}), tSmall == null?GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mOreMultiplier), new Object[]{tCleaned}):GT_Utility.mul((long)(aMultiplier * 6 * aMaterial.mOreMultiplier), new Object[]{tSmall}), GT_Utility.mul((long)(aMultiplier * 2 * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProduct}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }
            } else {
               GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_Items.Cell_Water.get((long)aMultiplier, new Object[0]), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 2), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tSecondaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               if(tMaterial.contains(SubTag.WASHING_MERCURY)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 3), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tSecondaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tPrimaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 2), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProduct}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tSecondaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tSecondaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 2), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tSecondaryByProduct}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SodiumPersulfate, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 3), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tSecondaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tPrimaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SodiumPersulfate, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 2), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProduct}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tSecondaryByProductSmall}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }

               if(tSecondaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
                  GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.SodiumPersulfate, (long)aMultiplier), GT_Utility.mul((long)(aMultiplier * aMaterial.mOreMultiplier * 2), new Object[]{tCleaned}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tPrimaryByProductSmall}), GT_Utility.mul((long)(aMultiplier * aMaterial.mByProductMultiplier), new Object[]{tSecondaryByProduct}), GT_Items.Cell_Empty.get((long)aMultiplier, new Object[0]));
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
