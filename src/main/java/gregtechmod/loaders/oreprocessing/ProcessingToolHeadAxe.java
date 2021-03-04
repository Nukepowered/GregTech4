package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

public class ProcessingToolHeadAxe implements IOreRecipeRegistrator {

   public ProcessingToolHeadAxe() {
      OrePrefixes.toolHeadAxe.add(this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, aStack), false, true, new Object[]{"PIH", "P  ", "F  ", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial), Character.valueOf('I'), OrePrefixes.ingot.get(aMaterial), Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('F'), GT_ToolDictNames.craftingToolFile});
      }

   }
}
