package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_MinableRegistrator implements Runnable {
	@Override
	public void run() {
        GT_Log.out.println("GT_Mod: Adding JackHammer minable Blocks.");
    	ItemStack tStack;
    	GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.glowStone, false);
    	tStack = GT_ModHandler.getIC2Item("constructionFoam", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.blocksList[((ItemBlock)tStack.getItem()).getBlockID()], false);
    	tStack = GT_ModHandler.getIC2Item("constructionFoamWall", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.blocksList[((ItemBlock)tStack.getItem()).getBlockID()], false);
    	tStack = GT_ModHandler.getIC2Item("reinforcedStone", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.blocksList[((ItemBlock)tStack.getItem()).getBlockID()], true);
    	tStack = GT_ModHandler.getIC2Item("reinforcedGlass", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.blocksList[((ItemBlock)tStack.getItem()).getBlockID()], true);
    	tStack = GT_ModHandler.getIC2Item("reinforcedDoorBlock", 1);
    	if (tStack != null) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.blocksList[((ItemBlock)tStack.getItem()).getBlockID()], true);
    	
        GT_Log.out.println("GT_Mod: Adding Blocks to the Miners Valuable List.");
    	GT_ModHandler.addValuableOre(Block.glowStone.blockID, 0, 1);
    	GT_ModHandler.addValuableOre(Block.slowSand.blockID, 0, 1);
	}
}