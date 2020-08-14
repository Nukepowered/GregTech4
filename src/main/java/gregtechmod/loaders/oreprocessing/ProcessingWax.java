package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingWax implements IOreRecipeRegistrator {

   public ProcessingWax() {
      OrePrefixes.wax.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aOreDictName.equals("waxMagical")) {
         GregTech_API.sRecipeAdder.addFuel(GT_Utility.copyAmount(1L, new Object[]{aStack}), (ItemStack)null, 6, 5);
      }

   }
}
