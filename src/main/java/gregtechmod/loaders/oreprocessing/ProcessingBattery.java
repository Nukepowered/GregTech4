package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

public class ProcessingBattery implements IOreRecipeRegistrator {

   public ProcessingBattery() {
      OrePrefixes.battery.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(aMaterial == Materials.Lithium) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GT_ModHandler.getIC2Item("cropnalyzer", 1L, 32767), GT_Items.Tool_Scanner.getAlmostBroken(1L, new Object[0]), 12800, 16);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)Materials.Aluminium, 1L), GregTech_API.getGregTechComponent(26, 1), 3200, 4);
      }

   }
}
