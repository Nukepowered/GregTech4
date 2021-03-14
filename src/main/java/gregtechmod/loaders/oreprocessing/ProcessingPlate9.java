package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;

public class ProcessingPlate9 implements IOreRecipeRegistrator {

   public ProcessingPlate9() {
      OrePrefixes.plateDense.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
      GT_ModHandler.removeRecipeByOutput(aStack);
   }
}
