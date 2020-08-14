package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingToolHeadHammer implements IOreRecipeRegistrator {

   public ProcessingToolHeadHammer() {
      OrePrefixes.toolHeadHammer.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), false, true, new Object[]{"II ", "IIH", "II ", Character.valueOf('P'), OrePrefixes.plate.get(aMaterial), Character.valueOf('I'), OrePrefixes.ingot.get(aMaterial), Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('F'), GT_ToolDictNames.craftingToolFile});
      }

   }
}
