package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

public class ProcessingCellPlasma implements IOreRecipeRegistrator {

   public ProcessingCellPlasma() {
      OrePrefixes.cellPlasma.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(aMaterial == Materials.Empty) {
         GT_ModHandler.removeRecipeByOutput(aStack);
      } else {
         GregTech_API.sRecipeAdder.addFuel(GT_Utility.copyAmount(1L, aStack), GT_Utility.getFluidForFilledItem(aStack) == null?GT_Utility.getContainerItem(aStack):null, 1024 * Math.max(1, aMaterial.getMass()), 4);
         GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 2, 1));
      }

   }
}
