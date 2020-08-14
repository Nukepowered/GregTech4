package gregtechmod.loaders.oreprocessing;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingStone implements IOreRecipeRegistrator {

   public ProcessingStone() {
      OrePrefixes.stone.add((IOreRecipeRegistrator)this);
   }

   public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
      Block aBlock = Block.getBlockFromItem(aStack.getItem());
      switch(aMaterial) {
      case Sand:
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), new ItemStack(Blocks.sand, 1, 0), (ItemStack)null, 10, false);
         break;
      case Endstone:
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Endstone, 16L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Tungsten, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Endstone, 1L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Tungsten, 1L), 5, false);
         break;
      case Netherrack:
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Netherrack, 16L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Netherrack, 8L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 5L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)Materials.Netherrack, 1L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 1L), 5, false);
         break;
      case NetherBrick:
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Netherrack, 16L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.cell, (Object)Materials.Mercury, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Netherrack, 8L), GT_OreDictUnificator.get(OrePrefixes.nugget, (Object)Materials.Gold, 5L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         break;
      case Obsidian:
         if(aBlock != null) {
            aBlock.setResistance(20.0F);
         }

         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Items.IC2_Compressed_Coal_Ball.get(8L, new Object[0]), GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_Items.IC2_Compressed_Coal_Chunk.get(1L, new Object[0]), 400, 4);
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_ModHandler.getRCItem("cube.crushed.obsidian", 1L, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L)), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), 10, true);
         GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L), 200, 32);
         break;
      case Redrock:
      case Marble:
      case Basalt:
      case Quartzite:
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 1L), 10, false);
         break;
      case Flint:
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)aMaterial, 2L), new ItemStack(Items.flint, 1), 50, false);
         break;
      case GraniteBlack:
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, (Object)Materials.Advanced, 1L), GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_ModHandler.getIC2Item("reinforcedStone", 8L), 400, 4);
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 16L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)Materials.Thorium, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L), 200, 32);
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Thorium, 1L), 1, false);
         break;
      case GraniteRed:
         GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, (Object)Materials.Advanced, 1L), GT_Utility.copyAmount(8L, new Object[]{aStack}), GT_ModHandler.getIC2Item("reinforcedStone", 8L), 400, 4);
         GregTech_API.sRecipeAdder.addGrinderRecipe(GT_Utility.copyAmount(16L, new Object[]{aStack}), GT_Items.Cell_Water.get(1L, new Object[0]), GT_OreDictUnificator.get(OrePrefixes.dust, (Object)aMaterial, 16L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, (Object)Materials.Uranium, 1L), (ItemStack)null, GT_Items.Cell_Empty.get(1L, new Object[0]));
         GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.plate, (Object)aMaterial, 1L), 200, 32);
         GT_ModHandler.addPulverisationRecipe(GT_Utility.copyAmount(1L, new Object[]{aStack}), GT_OreDictUnificator.get(OrePrefixes.dustImpure, (Object)aMaterial, 1L), GT_OreDictUnificator.get(OrePrefixes.dustSmall, (Object)Materials.Uranium, 1L), 1, false);
      default: break;
      }
   }
}
