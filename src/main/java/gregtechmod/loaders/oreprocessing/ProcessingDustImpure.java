package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingDustImpure implements IOreRecipeRegistrator {

   public ProcessingDustImpure() {
      OrePrefixes.dustPure.add((IOreRecipeRegistrator)this);
      OrePrefixes.dustImpure.add((IOreRecipeRegistrator)this);
      OrePrefixes.dustRefined.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      Materials tByProduct = (Materials)GT_Utility.selectItemInList(aPrefix == OrePrefixes.dustPure?1:(aPrefix == OrePrefixes.dustRefined?2:0), aMaterial, aMaterial.mOreByProducts);
      ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tByProduct, GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)tByProduct, 1L), 1L);
      if(tStack == null) {
         tStack = GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)tByProduct, 1L);
         if(tStack == null) {
            tStack = GT_OreDictUnificator.get(OrePrefixes.dust, tByProduct, GT_OreDictUnificator.get(OrePrefixes.gem, (Object)tByProduct, 1L), 1L);
            if(tStack == null) {
               tStack = GT_OreDictUnificator.get(OrePrefixes.cell, (Object)tByProduct, 1L);
               if(tStack == null) {
                  GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), 0, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), (ItemStack)null, (ItemStack)null, (ItemStack)null, Math.max(1, aMaterial.getMass()));
               } else {
                  GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), 1, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 9L), tStack, (ItemStack)null, (ItemStack)null, Math.max(1, aMaterial.getMass() * 72));
               }
            } else {
               GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), 0, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 9L), tStack, (ItemStack)null, (ItemStack)null, Math.max(1, aMaterial.getMass() * 72));
            }
         } else {
            GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), 0, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 2L), tStack, (ItemStack)null, (ItemStack)null, Math.max(1, aMaterial.getMass() * 16));
         }
      } else {
         GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), 0, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), tStack, (ItemStack)null, (ItemStack)null, Math.max(1, aMaterial.getMass() * 8));
      }

   }
}
