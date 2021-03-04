package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingNugget implements IOreRecipeRegistrator {

   public ProcessingNugget() {
      OrePrefixes.nugget.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.round, (Object)aMaterial, 1L), (ItemStack)null, Math.max(aMaterial.getMass() / 4, 1), 8);
      GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copyAmount(9L, new Object[]{aStack}), (ItemStack)null, GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), 200, 2);
      GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L), new Object[]{aOreDictName, aOreDictName, aOreDictName, aOreDictName, aOreDictName, aOreDictName, aOreDictName, aOreDictName, aOreDictName});
      GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.round, (Object)aMaterial, 1L), new Object[]{GT_ToolDictNames.craftingToolFile, aOreDictName, aOreDictName});
   }
}
