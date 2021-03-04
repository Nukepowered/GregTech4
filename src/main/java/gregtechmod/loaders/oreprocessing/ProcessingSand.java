package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingSand implements IOreRecipeRegistrator {

   public ProcessingSand() {
      OrePrefixes.sand.add((IOreRecipeRegistrator)this);
   }

   @SuppressWarnings("deprecation")
   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(aOreDictName.equals("sandCracked")) {
         if(aStack.getItem() instanceof ItemBlock) {
            if(aStack.getItem().getItemStackLimit() > GT_Mod.sBlockStackSize) {
               aStack.getItem().setMaxStackSize(GT_Mod.sBlockStackSize);
            }

            GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(aStack.getItem()), false);
         }

         GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), -1, GT_ModHandler.getFuelCan(25000), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Saltpeter, 8L), (ItemStack)null, new ItemStack(Blocks.sand, 10), 2500);
      } else if(aOreDictName.equals("sandOil")) {
         GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copyAmount(2L, new Object[]{aStack}), 1, GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Oil, 1L), new ItemStack(Blocks.sand, 1, 0), (ItemStack)null, (ItemStack)null, 1000);
      }

   }
}
