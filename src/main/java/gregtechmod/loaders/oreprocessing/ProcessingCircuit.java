package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingCircuit implements IOreRecipeRegistrator {

   public ProcessingCircuit() {
      OrePrefixes.circuit.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      switch(ProcessingCircuit.NamelessClass653157110.$SwitchMap$gregtechmod$api$enums$Materials[aMaterial.ordinal()]) {
      case 1:
         if(!GT_Utility.areStacksEqual(aStack, GT_Items.Circuit_Integrated.getWildcard(1L, new Object[0]))) {
            GT_ModHandler.removeRecipeByOutput(aStack);
            GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Basic.get(1L, new Object[0]), new Object[]{"CCC", "SRS", "CCC", Character.valueOf('C'), GT_OreDictNames.craftingWireCopper, Character.valueOf('R'), OrePrefixes.plate.get(Materials.Iron), Character.valueOf('S'), GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true)?OrePrefixes.plate.get(Materials.RedAlloy):OrePrefixes.dust.get(Materials.Redstone)});
            GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Basic.get(1L, new Object[0]), new Object[]{"CSC", "CRC", "CSC", Character.valueOf('C'), GT_OreDictNames.craftingWireCopper, Character.valueOf('R'), OrePrefixes.plate.get(Materials.Iron), Character.valueOf('S'), GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true)?OrePrefixes.plate.get(Materials.RedAlloy):OrePrefixes.dust.get(Materials.Redstone)});
         }
         break;
      case 2:
         GT_ModHandler.removeRecipeByOutput(aStack);
         GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Advanced.get(1L, new Object[0]), new Object[]{"SGS", "LCL", "SGS", Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Basic), Character.valueOf('S'), GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true)?OrePrefixes.plate.get(Materials.RedAlloy):OrePrefixes.dust.get(Materials.Redstone), Character.valueOf('G'), OrePrefixes.dust.get(Materials.Glowstone), Character.valueOf('L'), OrePrefixes.dust.get(Materials.Lazurite)});
         GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Advanced.get(1L, new Object[0]), new Object[]{"SLS", "GCG", "SLS", Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Basic), Character.valueOf('S'), GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true)?OrePrefixes.plate.get(Materials.RedAlloy):OrePrefixes.dust.get(Materials.Redstone), Character.valueOf('G'), OrePrefixes.dust.get(Materials.Glowstone), Character.valueOf('L'), OrePrefixes.dust.get(Materials.Lazurite)});
         GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Advanced.get(1L, new Object[0]), new Object[]{"SGS", "LCL", "SGS", Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Basic), Character.valueOf('S'), GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true)?OrePrefixes.plate.get(Materials.RedAlloy):OrePrefixes.dust.get(Materials.Redstone), Character.valueOf('G'), OrePrefixes.dust.get(Materials.Glowstone), Character.valueOf('L'), OrePrefixes.dust.get(Materials.Lapis)});
         GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Advanced.get(1L, new Object[0]), new Object[]{"SLS", "GCG", "SLS", Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Basic), Character.valueOf('S'), GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true)?OrePrefixes.plate.get(Materials.RedAlloy):OrePrefixes.dust.get(Materials.Redstone), Character.valueOf('G'), OrePrefixes.dust.get(Materials.Glowstone), Character.valueOf('L'), OrePrefixes.dust.get(Materials.Lapis)});
      }

   }

   // $FF: synthetic class
   static class NamelessClass653157110 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$Materials = new int[Materials.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Basic.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Advanced.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
