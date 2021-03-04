package gregtechmod.loaders.oreprocessing;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingBlock implements IOreRecipeRegistrator {

   public ProcessingBlock() {
      OrePrefixes.block.add((IOreRecipeRegistrator)this);
   }

   @SuppressWarnings("deprecation")
public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
      GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copyAmount(1L, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 9L), Math.max(aMaterial.getMass() * 10, 1), 30);
      ItemStack tStack1 = GT_OreDictUnificator.get(OrePrefixes.ingot, (Object)aMaterial, 1L);
      ItemStack tStack2 = GT_OreDictUnificator.get(OrePrefixes.gem, (Object)aMaterial, 1L);
      ItemStack tStack3 = GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L);
      GT_ModHandler.removeRecipe(new ItemStack[]{GT_Utility.copyAmount(1L, aStack)});
      if(tStack1 != null) {
         GT_ModHandler.removeRecipe(new ItemStack[]{tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1});
      }

      if(tStack2 != null) {
         GT_ModHandler.removeRecipe(new ItemStack[]{tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
      }

      if(tStack3 != null) {
         GT_ModHandler.removeRecipe(new ItemStack[]{tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
      }

      if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.storageblockcrafting, OrePrefixes.block.get(aMaterial), false)) {
         if(tStack1 == null && tStack2 == null && tStack3 != null) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.block, (Object)aMaterial, 1L), new Object[]{"XXX", "XXX", "XXX", Character.valueOf('X'), OrePrefixes.dust.get(aMaterial)});
         }

         if(tStack2 != null) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.block, (Object)aMaterial, 1L), new Object[]{"XXX", "XXX", "XXX", Character.valueOf('X'), OrePrefixes.gem.get(aMaterial)});
         }

         if(tStack1 != null) {
            GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.block, (Object)aMaterial, 1L), new Object[]{"XXX", "XXX", "XXX", Character.valueOf('X'), OrePrefixes.ingot.get(aMaterial)});
         }
      }

      if(tStack1 != null) {
         tStack1.stackSize = 9;
      }

      if(tStack2 != null) {
         tStack2.stackSize = 9;
      }

      if(tStack3 != null) {
         tStack3.stackSize = 9;
      }

      if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.storageblockdecrafting, OrePrefixes.block.get(aMaterial), tStack2 != null)) {
         if(tStack3 != null) {
            GT_ModHandler.addShapelessCraftingRecipe(tStack3, new Object[]{OrePrefixes.block.get(aMaterial)});
         }

         if(tStack2 != null) {
            GT_ModHandler.addShapelessCraftingRecipe(tStack2, new Object[]{OrePrefixes.block.get(aMaterial)});
         }

         if(tStack1 != null) {
            GT_ModHandler.addShapelessCraftingRecipe(tStack1, new Object[]{OrePrefixes.block.get(aMaterial)});
         }
      }

      if(aStack.getItem() instanceof ItemBlock && GT_Mod.sBlockStackSize < aStack.getItem().getItemStackLimit()) {
         aStack.getItem().setMaxStackSize(GT_Mod.sBlockStackSize);
      }

      if(aMaterial == Materials.Mercury) {
         GT_Log.log.error("\'blockQuickSilver\'?, In which Ice Desert can you actually place this as a solid Block?");
      } else if(aMaterial == Materials.Iron) {
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.IC2_Compressed_Coal_Ball.get(8L, new Object[0]), GT_Utility.copyAmount(1L, aStack), GT_Items.IC2_Compressed_Coal_Chunk.get(1L, new Object[0]), 400, 4);
      }

   }
}
