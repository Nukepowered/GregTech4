package gregtechmod.loaders.postload;

import gregtechmod.GT_Mod;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class GT_ItemMaxStacksizeLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("Changing maximum Stacksizes if configured.");
        
    	GT_ModHandler.getIC2Item("overclockerUpgrade", 1).getItem().setMaxStackSize(GT_Mod.sUpgradeCount);
    	Items.cake.setMaxStackSize(64);
    	
    	if (GT_Mod.sPlankStackSize < 64) {
	    	Item.getItemFromBlock(Blocks.wooden_slab).setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.getItemFromBlock(Blocks.double_wooden_slab).setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.getItemFromBlock(Blocks.oak_stairs).setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.getItemFromBlock(Blocks.birch_stairs).setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.getItemFromBlock(Blocks.jungle_stairs).setMaxStackSize(GT_Mod.sPlankStackSize);
	    	Item.getItemFromBlock(Blocks.spruce_stairs).setMaxStackSize(GT_Mod.sPlankStackSize);
	    }
    	
    	if (GT_Mod.sBlockStackSize < 64) {
    		Item.getItemFromBlock(Blocks.stone_slab).setMaxStackSize(GT_Mod.sBlockStackSize);
    		Item.getItemFromBlock(Blocks.double_stone_slab).setMaxStackSize(GT_Mod.sBlockStackSize);
    		Item.getItemFromBlock(Blocks.brick_stairs).setMaxStackSize(GT_Mod.sBlockStackSize);
    		Item.getItemFromBlock(Blocks.nether_brick_stairs).setMaxStackSize(GT_Mod.sBlockStackSize);
    		Item.getItemFromBlock(Blocks.sandstone_stairs).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.stone_brick_stairs).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.stone_stairs).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.ice).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.soul_sand).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.glowstone).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.snow_layer).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.snow).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.iron_block).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.gold_block).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.emerald_block).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.lapis_block).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.diamond_block).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.clay).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.redstone_lamp).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.lit_redstone_lamp).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.dirt).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.grass).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.mycelium).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.gravel).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.sand).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.brick_block).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.wool).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.melon_block).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.pumpkin).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.lit_pumpkin).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.dispenser).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.obsidian).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.piston).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.sticky_piston).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.crafting_table).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.glass).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.jukebox).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.anvil).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.chest).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.noteblock).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.mob_spawner).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.bookshelf).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.furnace).setMaxStackSize(GT_Mod.sBlockStackSize);
	    	Item.getItemFromBlock(Blocks.lit_furnace).setMaxStackSize(GT_Mod.sBlockStackSize);
    	}
	}
}
