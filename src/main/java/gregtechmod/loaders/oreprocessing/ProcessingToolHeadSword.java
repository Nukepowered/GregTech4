package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingToolHeadSword implements IOreRecipeRegistrator {

   public ProcessingToolHeadSword() {
      OrePrefixes.toolHeadSword.add(this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(!aMaterial.contains(SubTag.NO_SMASHING)) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1, aStack), false, true, new Object[]{" P ", "FPH", 'P', OrePrefixes.plate.get(aMaterial), 'I', OrePrefixes.ingot.get(aMaterial), 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
      }
   }
}
