package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GT_MinableRegistrator implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("GT_Mod: Adding JackHammer minable Blocks.");
    	ItemStack tStack;
    	GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Blocks.glowstone, false);
    	tStack = GT_ModHandler.getIC2Item("constructionFoam", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(tStack.getItem()), false);
    	tStack = GT_ModHandler.getIC2Item("constructionFoamWall", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(tStack.getItem()), false);
    	tStack = GT_ModHandler.getIC2Item("reinforcedStone", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(tStack.getItem()), true);
    	tStack = GT_ModHandler.getIC2Item("reinforcedGlass", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(tStack.getItem()), true);
    	tStack = GT_ModHandler.getIC2Item("reinforcedDoorBlock", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(tStack.getItem()), true);
    	
        GT_Log.log.info("GT_Mod: Adding Blocks to the Miners Valuable List.");
    	GT_ModHandler.addValuableOre(new ItemStack(Blocks.glowstone), 1);
    	GT_ModHandler.addValuableOre(new ItemStack(Blocks.soul_sand), 1);
	}
}