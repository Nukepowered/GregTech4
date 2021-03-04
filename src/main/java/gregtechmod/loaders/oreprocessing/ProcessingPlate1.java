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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingPlate1 implements IOreRecipeRegistrator {

   public ProcessingPlate1() {
      OrePrefixes.plate.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      GT_ModHandler.removeRecipeByOutput(aStack);
      GT_ModHandler.removeRecipe(new ItemStack[]{aStack});
      if(aMaterial.mFuelPower > 0) {
         GregTech_API.sRecipeAdder.addFuel(GT_Utility.copyAmount(1L, new Object[]{aStack}), (ItemStack)null, aMaterial.mFuelPower, aMaterial.mFuelType);
      }

      GT_Utility.removeSimpleIC2MachineRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_ModHandler.getCompressorRecipeList(), GT_OreDictUnificator.get(OrePrefixes.plateDense, (Object)aMaterial, 1L));
      GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.lense, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() / 2, 1), 16);
      if(aMaterial == Materials.Paper) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, aStack, true)?2L:3L, new Object[]{aStack}), new Object[]{"XXX", Character.valueOf('X'), new ItemStack(Items.reeds, 1, 32767)});
      }

      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateDouble, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 2, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateTriple, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 3, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateQuadruple, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 4, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(5L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateQuintuple, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 5, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateDense, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 9, 1), 24);
         if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammerplating, OrePrefixes.ingot.get(aMaterial), true)) {
            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"H", "I", "I", Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('I'), OrePrefixes.ingot.get(aMaterial)});
            GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.ingot.get(aMaterial)});
         }

         if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammerplating, OrePrefixes.ingotDouble.get(aMaterial), true)) {
            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"H", "I", Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('I'), OrePrefixes.ingotDouble.get(aMaterial)});
            GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), new Object[]{GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.ingotDouble.get(aMaterial)});
         }
      }

      if(!aMaterial.contains(SubTag.NO_SMELTING)) {
         GT_ModHandler.addSmeltingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L));
      }

   }
}
