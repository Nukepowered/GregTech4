package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingDustSmall implements IOreRecipeRegistrator {

   public ProcessingDustSmall() {
      OrePrefixes.dustSmall.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), new Object[]{aOreDictName, aOreDictName, aOreDictName, aOreDictName});
      if(aMaterial.mBlastFurnaceRequired) {
         GregTech_API.sRecipeAdder.addBlastRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), (ItemStack)null, aMaterial.mBlastFurnaceTemp > 1750?GT_OreDictUnificator.get(OrePrefixes.ingotHot, aMaterial, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), 1L):GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), (ItemStack)null, Math.max(aMaterial.getMass() / 40, 1) * aMaterial.mBlastFurnaceTemp, 120, aMaterial.mBlastFurnaceTemp);
      } else {
         GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), (ItemStack)null, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), 130, 3, true);
      }

   }
}
