package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingIngot1 implements IOreRecipeRegistrator {

   public ProcessingIngot1() {
      OrePrefixes.ingot.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aMaterial.mFuelPower > 0) {
         GregTech_API.sRecipeAdder.addFuel(GT_Utility.copyAmount(1L, new Object[]{aStack}), (ItemStack)null, aMaterial.mFuelPower, aMaterial.mFuelType);
      }

      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GregTech_API.sRecipeAdder.addForgeHammerRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L), Math.max(aMaterial.getMass(), 1), 16);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 2, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateDouble, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 4, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(3L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateTriple, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 6, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateQuadruple, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 8, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(5L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateQuintuple, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 10, 1), 24);
         GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plateDense, (Object)aMaterial, 1L), Math.max(aMaterial.getMass() * 18, 1), 24);
      }

      if(!OrePrefixes.block.isIgnored(aMaterial)) {
         GT_ModHandler.addCompressionRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.block, (Object)aMaterial, 1L));
      }

      GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, (Object)aMaterial, 1L), new Object[]{"F", "I", Character.valueOf('F'), GT_ToolDictNames.craftingToolFile, Character.valueOf('I'), aOreDictName});
      GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.stick, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)aMaterial, 2L), Math.max(aMaterial.getMass() * 5, 1), 16);
      
	  if(!aMaterial.contains(SubTag.NO_SMELTING) && GT_ModHandler.getSmeltingOutput(aStack, false, (ItemStack)null) == null && GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)aMaterial, 1L) != null && !GT_ModHandler.addSmeltingRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)aMaterial, 9L))) {
		  GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)aMaterial, 9L), new Object[]{aOreDictName});
	  }

      ItemStack tStack;
      if(null != (tStack = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L))) {
         if(aMaterial.mBlastFurnaceRequired || aMaterial.contains(SubTag.NO_SMELTING)) {
            GT_ModHandler.removeFurnaceSmelting(tStack);
         }

         GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Utility.copyAmount(1L, new Object[]{tStack}), OrePrefixes.plate.get(aMaterial), !aMaterial.contains(SubTag.NO_SMELTING), true, !aMaterial.contains(SubTag.NO_SMASHING));
      } else {
         GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Utility.copyAmount(1L, new Object[]{aStack}), OrePrefixes.plate.get(aMaterial), !aMaterial.contains(SubTag.NO_SMELTING), false, !aMaterial.contains(SubTag.NO_SMASHING));
      }

      if(aMaterial == Materials.Mercury) {
         GT_Log.log.error("Quicksilver Ingots?, Don\'t tell me there is an Armor made of that highly toxic and very likely to be melting Material!");
      }
   }
}
