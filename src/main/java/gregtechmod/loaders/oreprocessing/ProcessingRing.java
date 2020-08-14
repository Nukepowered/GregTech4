package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingRing implements IOreRecipeRegistrator {

   public ProcessingRing() {
      OrePrefixes.ring.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(!aMaterial.contains(SubTag.NO_SMASHING) && GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.hammerrings, OrePrefixes.ring.get(aMaterial), true)) {
         GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new Object[]{"H ", " S", Character.valueOf('H'), GT_ToolDictNames.craftingToolHardHammer, Character.valueOf('S'), OrePrefixes.stick.get(aMaterial)});
         GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), new Object[]{GT_ToolDictNames.craftingToolForgeHammer, OrePrefixes.stick.get(aMaterial)});
      }

   }
}
