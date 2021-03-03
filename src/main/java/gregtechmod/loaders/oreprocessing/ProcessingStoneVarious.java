package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ProcessingStoneVarious implements IOreRecipeRegistrator {

   public ProcessingStoneVarious() {
      OrePrefixes.stone.add(this);
      OrePrefixes.stoneCobble.add(this);
      OrePrefixes.stoneBricks.add(this);
      OrePrefixes.stoneChiseled.add(this);
      OrePrefixes.stoneCracked.add(this);
      OrePrefixes.stoneMossy.add(this);
      OrePrefixes.stoneMossyBricks.add(this);
      OrePrefixes.stoneSmooth.add(this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      Block aBlock = Block.getBlockFromItem(aStack.getItem());
      if(aBlock != null) {
         if(aStack.getMaxStackSize() > GT_Mod.sBlockStackSize) {
            aStack.getItem().setMaxStackSize(GT_Mod.sBlockStackSize);
         }
         
         int meta = (aStack.getItemDamage() < 0 || aStack.getItemDamage() >= 16) ? 0 : aStack.getItemDamage();
         int tHarvestLevel = "pickaxe".equals(aBlock.getHarvestTool(meta)) ? aBlock.getHarvestLevel(meta) : 0;
         if(tHarvestLevel <= 3) {
            GregTech_API.sRecipeAdder.addJackHammerMinableBlock(aBlock, tHarvestLevel >= 3);
         }
      }

   }
}
