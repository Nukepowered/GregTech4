package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.item.ItemStack;

public class ProcessingWax implements IOreRecipeRegistrator {

   public ProcessingWax() {
      OrePrefixes.wax.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aOreDictName.equals("waxMagical")) {
         RecipeMaps.MAGIC_FUELS.factory().EUt(6).duration(1).input(GT_Utility.copyAmount(1L, aStack)).buildAndRegister();
      }
   }
}
