package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingItem implements IOreRecipeRegistrator {

   public ProcessingItem() {
      OrePrefixes.item.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aOreDictName.equals("itemManganese")) {
         GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), Materials.Manganese, 3628800L);
      } else if(aOreDictName.equals("itemSalt")) {
         GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), Materials.Salt, 3628800L);
      } else if(aOreDictName.equals("itemMagnesium")) {
         GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), Materials.Magnesium, 3628800L);
      } else if(!aOreDictName.equals("itemPhosphorite") && !aOreDictName.equals("itemPhosphorus")) {
         if(aOreDictName.equals("itemSulfur")) {
            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), Materials.Sulfur, 3628800L);
         } else if(!aOreDictName.equals("itemAluminum") && !aOreDictName.equals("itemAluminium")) {
            if(aOreDictName.equals("itemSaltpeter")) {
               GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), Materials.Saltpeter, 3628800L);
            } else if(aOreDictName.equals("itemUranium")) {
               GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), Materials.Uranium, 3628800L);
            } else if(!aOreDictName.equals("itemRubber")) {
               System.out.println("Item Name: " + aOreDictName + " !!!Unknown Item detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it\'s just an Information.");
            }
         } else {
            GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), Materials.Aluminium, 3628800L);
         }
      } else {
         GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(GT_Utility.copyAmount(1L, new Object[]{aStack}), Materials.Phosphorus, 3628800L);
      }

   }
}
