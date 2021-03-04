package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import net.minecraft.item.ItemBlock;

public class ProcessingLeaves implements IOreRecipeRegistrator {

   public ProcessingLeaves() {
      OrePrefixes.treeLeaves.add((IOreRecipeRegistrator)this);
   }

   @SuppressWarnings("deprecation")
   public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      if(aStack.getItem() instanceof ItemBlock && GT_Mod.sWoodStackSize < aStack.getItem().getItemStackLimit()) {
         aStack.getItem().setMaxStackSize(GT_Mod.sWoodStackSize);
      }

   }
}
