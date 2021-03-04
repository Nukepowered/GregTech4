package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingPlateAlloy implements IOreRecipeRegistrator {

   public ProcessingPlateAlloy() {
      OrePrefixes.plateAlloy.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(aOreDictName.equals("plateAlloyCarbon")) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("generator", 1L), GT_Utility.copyAmount(4L, new Object[]{aStack}), GT_ModHandler.getIC2Item("windMill", 1L), 6400, 8);
      } else if(aOreDictName.equals("plateAlloyAdvanced")) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), new ItemStack(Blocks.glass, 7, 0), GT_ModHandler.getIC2Item("reinforcedGlass", 7L), 400, 4);
      }

   }
}
