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

public class ProcessingIngot5 implements IOreRecipeRegistrator {

   public ProcessingIngot5() {
      OrePrefixes.ingotQuintuple.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(5L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateQuintuple, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 2, 1), 24);
      }

      if(!aMaterial.contains(SubTag.NO_SMASHING) && GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammerquintupleingot, OrePrefixes.ingot.get(aMaterial), true)) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"I", "B", "H", Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('I'), OrePrefixes.ingotQuadruple.get(aMaterial), Character.valueOf('B'), OrePrefixes.ingot.get(aMaterial)});
         GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial)});
      } else {
         GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial)});
      }

   }
}
