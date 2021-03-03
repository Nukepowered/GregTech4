package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingStoneCobble implements IOreRecipeRegistrator {

   public ProcessingStoneCobble() {
      OrePrefixes.stoneCobble.add(this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      RecipeMaps.ASSEMBLING.factory().EUt(1).duration(400).input(OrePrefixes.stick, Materials.Wood).input(GT_Utility.copyAmount(1L, aStack)).output(new ItemStack(Blocks.lever, 1)).buildAndRegister();
   }
}
