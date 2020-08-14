package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;

public class ProcessingPlate9 implements IOreRecipeRegistrator {

   public ProcessingPlate9() {
      OrePrefixes.plateDense.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      GT_ModHandler.removeRecipeByOutput(aStack);
   }
}
