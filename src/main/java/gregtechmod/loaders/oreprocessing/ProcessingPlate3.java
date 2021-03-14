package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

public class ProcessingPlate3 implements IOreRecipeRegistrator {

   public ProcessingPlate3() {
      OrePrefixes.plateTriple.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
      GT_ModHandler.removeRecipeByOutput(aStack);
      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateDense, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 3, 1), 24);
      }

      if(!aMaterial.contains(SubTag.NO_SMASHING) && GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammertripleplate, OrePrefixes.plate.get(aMaterial), true)) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"I", "B", "H", Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('I'), OrePrefixes.plateDouble.get(aMaterial), Character.valueOf('B'), OrePrefixes.plate.get(aMaterial)});
         GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial)});
      } else {
         GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial), OrePrefixes.plate.get(aMaterial)});
      }

   }
}
