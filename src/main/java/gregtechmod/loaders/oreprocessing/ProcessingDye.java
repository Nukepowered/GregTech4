package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingDye implements IOreRecipeRegistrator {

   public ProcessingDye() {
      OrePrefixes.dye.add((IOreRecipeRegistrator)this);
   }

   @SuppressWarnings("deprecation")
   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      Dyes aDye = Dyes.get(aOreDictName);
      if(aDye.mColor >= 0 && aDye.mColor < 16 && aStack.getItem().getItemStackLimit() >= 16 && GT_Utility.getContainerItem(aStack) == null) {
         GregTech_API.sRecipeAdder.addCannerRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Spray_Empty.get(1L, new Object[0]), GT_Items.SPRAY_CAN_DYES[aDye.mColor].get(1L, new Object[0]), (ItemStack)null, 800, 1);
      }

   }
}
