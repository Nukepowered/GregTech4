package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingPlank implements IOreRecipeRegistrator {

   public ProcessingPlank() {
      OrePrefixes.plank.add((IOreRecipeRegistrator)this);
   }

   @SuppressWarnings("deprecation")
   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      if(aOreDictName.startsWith("plankWood")) {
         if(aStack.getItem() instanceof ItemBlock && GT_Mod.sPlankStackSize < aStack.getItem().getItemStackLimit()) {
            aStack.getItem().setMaxStackSize(GT_Mod.sPlankStackSize);
         }

         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Wood, 1L), (ItemStack)null, 0, false);
         GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.stick, (Object)Materials.Wood, 2L), (ItemStack)null, 10, 8);
         GregTech_API.sRecipeAdder.addCNCRecipe(GT_Utility.copyAmount(4L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.gearGt, (Object)Materials.Wood, 1L), 800, 1);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Redstone, 1L), new ItemStack(Blocks.noteblock, 1), 800, 1);
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, (Object)Materials.Diamond, 1L), GT_Utility.copyAmount(8L, new Object[]{aStack}), new ItemStack(Blocks.jukebox, 1), 1600, 1);
      }

   }
}
