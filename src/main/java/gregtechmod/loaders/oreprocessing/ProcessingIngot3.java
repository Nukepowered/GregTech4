package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingIngot3 implements IOreRecipeRegistrator {

   public ProcessingIngot3() {
      OrePrefixes.ingotTriple.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateTriple, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 2, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateDense, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 6, 1), 24);
      }

      if(!aMaterial.contains(SubTag.NO_SMASHING) && GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammertripleingot, OrePrefixes.ingot.get(aMaterial), true)) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"I", "B", "H", Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('I'), OrePrefixes.ingotDouble.get(aMaterial), Character.valueOf('B'), OrePrefixes.ingot.get(aMaterial)});
         GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial)});
      } else {
         GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial)});
      }

   }
}
