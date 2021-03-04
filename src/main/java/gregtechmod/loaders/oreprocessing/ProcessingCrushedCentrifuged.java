package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

public class ProcessingCrushedCentrifuged implements IOreRecipeRegistrator {

   public ProcessingCrushedCentrifuged() {
      OrePrefixes.crushedCentrifuged.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, GT_Utility.selectItemInList(2, aMaterial, aMaterial.mOreByProducts), 1L), 10, false);
   }
}
