package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingGear implements IOreRecipeRegistrator {

   public ProcessingGear() {
      OrePrefixes.gearGt.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      GT_ModHandler.removeRecipeByOutput(aStack);
      switch(ProcessingGear.NamelessClass1327650710.$SwitchMap$gregtechmod$api$enums$Materials[aMaterial.ordinal()]) {
      case 1:
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"SPS", "PTP", "SPS", Character.valueOf('P'), "plankWood", Character.valueOf('S'), OrePrefixes.stick.get(aMaterial), Character.valueOf('T'), GT_ToolDictNames.craftingToolSaw});
         break;
      case 2:
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"SPS", "PTP", "SPS", Character.valueOf('P'), "stoneSmooth", Character.valueOf('S'), new ItemStack(Blocks.stone_button, 1, 0), Character.valueOf('T'), GT_ToolDictNames.craftingToolFile});
         break;
      default:
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"SPS", "PTP", "SPS", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial), Character.valueOf('S'), OrePrefixes.stick.get(aMaterial), Character.valueOf('T'), GT_ToolDictNames.craftingToolWrench});
      }

   }

   // $FF: synthetic class
   static class NamelessClass1327650710 {

      // $FF: synthetic field
      static final int[] $SwitchMap$gregtechmod$api$enums$Materials = new int[Materials.values().length];


      static {
         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Wood.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$gregtechmod$api$enums$Materials[Materials.Stone.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
