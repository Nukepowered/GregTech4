package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_RecyclerBlacklistLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.out.println("GT_Mod: Adding Stuff to the Recycler Blacklist.");
        
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "easymobgrinderrecycling", true)) {
        	// Skeletons
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.arrow, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.bone, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.dyePowder, 1, 15));
			
			// Zombies
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.rottenFlesh, 1, 0));
			
			// Spiders
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.silk, 1, 0));
			
			// Chicken Eggs
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.egg, 1, 0));
        }
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "easystonerecycling", true)) {
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sand, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 2));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 3));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 4));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 5));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 6));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.sandStone, 1, 7));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.glass, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.glassBottle, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.potion, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getSmeltingOutput(new ItemStack(Block.stone, 1, 0), false, null));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.stone, 1, 0), null, null, new ItemStack(Block.stone, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.cobblestone, 1, 0), null, null, new ItemStack(Block.cobblestone, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.stone, 1, 0), null, new ItemStack(Block.stone, 1, 0), null, new ItemStack(Block.stone, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.stone, 1, 0), new ItemStack(Block.glass, 1, 0), new ItemStack(Block.stone, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.cobblestone, 1, 0), new ItemStack(Block.glass, 1, 0), new ItemStack(Block.cobblestone, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.glass, 1, 0), new ItemStack(Block.sandStone, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.sand, 1, 0), new ItemStack(Block.glass, 1, 0), new ItemStack(Block.sand, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0), new ItemStack(Block.sandStone, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.glass, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.glass, 1, 0), new ItemStack(Block.glass, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Block.glass, 1, 0), null, null, new ItemStack(Block.glass, 1, 0)}));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.thinGlass, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.cobblestone, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.cobblestoneWall, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stairsSandStone, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stairsCobblestone, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stairsStoneBrick, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.furnaceBurning, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.furnaceIdle, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneSingleSlab, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneDoubleSlab, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneSingleSlab, 1, 1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneDoubleSlab, 1, 1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneSingleSlab, 1, 3));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneDoubleSlab, 1, 3));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneSingleSlab, 1, 5));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneDoubleSlab, 1, 5));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneSingleSlab, 1, 7));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneDoubleSlab, 1, 7));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.pressurePlateStone, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneButton, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneBrick, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneBrick, 1, 1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneBrick, 1, 2));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stoneBrick, 1, 3));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.stone, 1, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.lever, 1, 0));
		}
		GT_ModHandler.addToRecyclerBlackList(new ItemStack(Item.snowball, 1));
		GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.ice, 1));
		GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.snow, 1));
		GT_ModHandler.addToRecyclerBlackList(new ItemStack(Block.blockSnow, 1));
	}
}
