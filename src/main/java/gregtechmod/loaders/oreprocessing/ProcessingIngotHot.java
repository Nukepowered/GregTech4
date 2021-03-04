package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

public class ProcessingIngotHot implements IOreRecipeRegistrator {

   public ProcessingIngotHot() {
      OrePrefixes.ingotHot.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 3, 1));
   }
}
