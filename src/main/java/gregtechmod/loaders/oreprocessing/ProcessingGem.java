package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingGem implements IOreRecipeRegistrator {

   public ProcessingGem() {
      OrePrefixes.gem.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aMaterial.mFuelPower > 0) {
         GregTech_API.sRecipeAdder.addFuel(GT_Utility.copyAmount(1L, new Object[]{aStack}), (ItemStack)null, aMaterial.mFuelPower * 2, aMaterial.mFuelType);
      }

      if(!OrePrefixes.block.isIgnored(aMaterial)) {
         GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.block, (Object)aMaterial, 1L));
      }

      if(!aMaterial.contains(SubTag.NO_SMELTING)) {
         GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L));
      }

      if(null == GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L)) {
         GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.lense, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)aMaterial, 1L), Math.max(aMaterial.getMass(), 1), 16);
      }

      ItemStack tStack;
      if(null != (tStack = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L))) {
         GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Utility.copyAmount(1L, new Object[]{tStack}), (String)null, false, true, false);
      } else {
         GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Utility.copyAmount(1L, new Object[]{aStack}), (String)null, false, true, false);
      }

      switch(aMaterial) {
      case _NULL:
      default:
         break;
      case Coal:
      case Charcoal:
         if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", false)) {
            GT_ModHandler.removeRecipe(new ItemStack[]{GT_Utility.copyAmount(1L, new Object[]{aStack}), null, null, new ItemStack(Items.stick, 1, 0)});
         }
         break;
      case Mercury:
         GregTech_API.sRecipeAdder.addCannerRecipe(aStack, GT_Items.Cell_Empty.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)aMaterial, 1L), (ItemStack)null, 100, 1);
      }

   }
}
