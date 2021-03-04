package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

public class ProcessingToolHeadPickaxe implements IOreRecipeRegistrator {

   public ProcessingToolHeadPickaxe() {
      OrePrefixes.toolHeadPickaxe.add(this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, aStack), false, true, new Object[]{"PII", "F H", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial), Character.valueOf('I'), OrePrefixes.ingot.get(aMaterial), Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('F'), GT_ToolDictNames.craftingToolFile});
      }

   }
}
