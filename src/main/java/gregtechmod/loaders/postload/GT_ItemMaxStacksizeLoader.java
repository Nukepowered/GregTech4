package gregtechmod.loaders.postload;

import gregtechmod.GT_Mod;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class GT_ItemMaxStacksizeLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.out.println("GT_Mod: Changing maximum Stacksizes if configured.");
        
    	GT_ModHandler.getIC2Item("overclockerUpgrade", 1).getItem().setMaxStackSize(GT_Mod.sUpgradeCount);
    	Item.cake.setMaxStackSize(64);
    	
    	if (GT_Mod.sPlankStackSize < 64) {
	    	Item.itemsList[Block.woodSingleSlab.blockID].setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.itemsList[Block.woodDoubleSlab.blockID].setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.itemsList[Block.stairsWoodOak.blockID].setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.itemsList[Block.stairsWoodBirch.blockID].setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.itemsList[Block.stairsWoodJungle.blockID].setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.itemsList[Block.stairsWoodSpruce.blockID].setMaxStackSize(GT_Mod.sPlankStackSize);
	    }
    	
    	if (GT_Mod.sBlockStackSize < 64) {
	    	Item.itemsList[Block.stoneSingleSlab.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.stoneDoubleSlab.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.stairsBrick.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.stairsNetherBrick.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.stairsSandStone.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.stairsStoneBrick.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.stairsCobblestone.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.ice.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.slowSand.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.glowStone.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.snow.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.blockSnow.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.blockIron.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.blockGold.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.blockEmerald.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.blockLapis.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.blockDiamond.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.blockClay.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.redstoneLampIdle.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.redstoneLampActive.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.dirt.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.grass.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.mycelium.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.gravel.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.sand.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.brick.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.cloth.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.melon.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.pumpkin.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.pumpkinLantern.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.dispenser.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.obsidian.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.pistonBase.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.pistonStickyBase.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.workbench.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.glass.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.jukebox.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.anvil.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.chest.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.music.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.mobSpawner.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.bookShelf.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.furnaceIdle.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.itemsList[Block.furnaceBurning.blockID].setMaxStackSize(GT_Mod.sBlockStackSize);
    	}
	}
}
