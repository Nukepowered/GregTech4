package gregtechmod.loaders.postload;

import gregtechmod.GT_Mod;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class GT_BookAndLootLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("GT_Mod: Adding worldgenerated Chest Content.");
        
        if (GT_Mod.sIncreaseDungeonLoot) {
        	ChestGenHooks tChest;
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST				); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST				); tChest.setMax(tChest.getMax()+ 6); tChest.setMin(tChest.getMin()+ 3);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST		); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST		); tChest.setMax(tChest.getMax()+12); tChest.setMin(tChest.getMin()+ 6);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER	); tChest.setMax(tChest.getMax()+ 2); tChest.setMin(tChest.getMin()+ 1);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR			); tChest.setMax(tChest.getMax()+ 4); tChest.setMin(tChest.getMin()+ 2);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH			); tChest.setMax(tChest.getMax()+12); tChest.setMin(tChest.getMin()+ 6);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING		); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR		); tChest.setMax(tChest.getMax()+ 6); tChest.setMin(tChest.getMin()+ 3);
	        tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY			); tChest.setMax(tChest.getMax()+16); tChest.setMin(tChest.getMin()+ 8);
        }
        
        ItemStack tStack;
        tStack = GT_Utility.getWrittenBook("book.gt_manual_1.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_1.page.1"
        		, "book.gt_manual_1.page.2"
        		, "book.gt_manual_1.page.3"
        		, "book.gt_manual_1.page.4"
        		, "book.gt_manual_1.page.5"
        		, "book.gt_manual_1.page.6"
        		, "book.gt_manual_1.page.7"
        		, "book.gt_manual_1.page.8"
        		, "book.gt_manual_1.page.9"
        		, "book.gt_manual_1.page.10"
        		, "book.gt_manual_1.page.11"
        		, "book.gt_manual_1.page.12"
        		, "book.gt_manual_1.page.13"
        		, "book.gt_manual_1.page.14"
        		, "book.gt_manual_1.page.15"
        		, "book.gt_manual_1.page.16"
        		, "book.gt_manual_1.page.17"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  20));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_2.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_2.page.1"
        		, "book.gt_manual_2.page.2"
        		, "book.gt_manual_2.page.3"
        		, "book.gt_manual_2.page.4"
        		, "book.gt_manual_2.page.5"
        		, "book.gt_manual_2.page.6"
        		, "book.gt_manual_2.page.7"
        		, "book.gt_manual_2.page.8"
        		, "book.gt_manual_2.page.9"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  15));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_3.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_3.page.1"
        		, "book.gt_manual_3.page.2"
        		, "book.gt_manual_3.page.3"
        		, "book.gt_manual_3.page.4"
        		, "book.gt_manual_3.page.5"
        		, "book.gt_manual_3.page.6"
        		, "book.gt_manual_3.page.7"
      			});
	    if (tStack != null) {
	    ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
	    ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
	    ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
	    ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
	    ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
	    ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  20));
	    tStack = null;
	    }
	    
        tStack = GT_Utility.getWrittenBook("book.gt_manual_4.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_4.page.1"
        		, "book.gt_manual_4.page.2"
        		, "book.gt_manual_4.page.3"
        		, "book.gt_manual_4.page.4"
        		, "book.gt_manual_4.page.5"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  10));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_5.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_5.page.1"
        		, "book.gt_manual_5.page.2"
        		, "book.gt_manual_5.page.3"
        		, "book.gt_manual_5.page.4"
        		, "book.gt_manual_5.page.5"
        		, "book.gt_manual_5.page.6"
        		, "book.gt_manual_5.page.7"
        		, "book.gt_manual_5.page.8"
        		});
        if (tStack != null) {
        	ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        	ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   3));
        	ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   5));
            ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  20));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_6.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_6.page.1"
        		, "book.gt_manual_6.page.2"
        		, "book.gt_manual_6.page.3"
        		, "book.gt_manual_6.page.4"
        		, "book.gt_manual_6.page.5"
        		, "book.gt_manual_6.page.6"
        		, "book.gt_manual_6.page.7"
        		, "book.gt_manual_6.page.8"
        		, "book.gt_manual_6.page.9"
        		, "book.gt_manual_6.page.10"
        		});
        if (tStack != null) {
        	ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        	ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   3));
        	ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   5));
            ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  20));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_7.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_7.page.1"
        		, "book.gt_manual_7.page.2"
        		, "book.gt_manual_7.page.3"
        		, "book.gt_manual_7.page.4"
        		, "book.gt_manual_7.page.5"
        		, "book.gt_manual_7.page.6"
        		, "book.gt_manual_7.page.7"
        		, "book.gt_manual_7.page.8"
        		, "book.gt_manual_7.page.9"
        		, "book.gt_manual_7.page.10"
        		, "book.gt_manual_7.page.11"
        		, "book.gt_manual_7.page.12"
        		, "book.gt_manual_7.page.13"
        		, "book.gt_manual_7.page.14"
        		, "book.gt_manual_7.page.15"
        		, "book.gt_manual_7.page.16"
        		, "book.gt_manual_7.page.17"
        		, "book.gt_manual_7.page.18"
        		, "book.gt_manual_7.page.19"
        		, "book.gt_manual_7.page.20"
        		, "book.gt_manual_7.page.21"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   5));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_8.title", "book.gt_manual_8.author", new String[] {
        		  "book.gt_manual_8.page.1"
        		, "book.gt_manual_8.page.2"
        		, "book.gt_manual_8.page.3"
        		, "book.gt_manual_8.page.4"
        		, "book.gt_manual_8.page.5"
        		, "book.gt_manual_8.page.6"
        		, "book.gt_manual_8.page.7"
        		, "book.gt_manual_8.page.8"
        		, "book.gt_manual_8.page.9"
        		, "book.gt_manual_8.page.10"
        		, "book.gt_manual_8.page.11"
        		, "book.gt_manual_8.page.12"
        		, "book.gt_manual_8.page.13"
        		, "book.gt_manual_8.page.14"
        		, "book.gt_manual_8.page.15"
        		, "book.gt_manual_8.page.16"
        		, "book.gt_manual_8.page.17"
        		, "book.gt_manual_8.page.18"
        		, "book.gt_manual_8.page.19"
        		, "book.gt_manual_8.page.20"
        		, "book.gt_manual_8.page.21"
        		, "book.gt_manual_8.page.22"
        		, "book.gt_manual_8.page.23"
        		, "book.gt_manual_8.page.24"
        		, "book.gt_manual_8.page.25"
        		, "book.gt_manual_8.page.26"
        		, "book.gt_manual_8.page.27"
        		, "book.gt_manual_8.page.28"
        		, "book.gt_manual_8.page.29"
        		, "book.gt_manual_8.page.30"
        		, "book.gt_manual_8.page.31"
        		, "book.gt_manual_8.page.32"
        		, "book.gt_manual_8.page.33"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   3));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   5));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  10));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_9.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_9.page.1"
        		, "book.gt_manual_9.page.2"
        		, "book.gt_manual_9.page.3"
        		, "book.gt_manual_9.page.4"
        		, "book.gt_manual_9.page.5"
        		, "book.gt_manual_9.page.6"
        		, "book.gt_manual_9.page.7"
      			});
	    if (tStack != null) {
	    ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
	    ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
	    ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
	    ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  10));
	    tStack = null;
	    }
	    
        tStack = GT_Utility.getWrittenBook("book.gt_manual_10.author", "book.gt_manual_10.title", new String[] {
        		  "book.gt_manual_10.page.1"
        		, "book.gt_manual_10.page.2"
        		, "book.gt_manual_10.page.3"
        		, "book.gt_manual_10.page.4"
        		, "book.gt_manual_10.page.5"
        		, "book.gt_manual_10.page.6"
        		, "book.gt_manual_10.page.7"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  10));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_11.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_11.page.1"
        		, "book.gt_manual_11.page.2"
        		, "book.gt_manual_11.page.3"
        		, "book.gt_manual_11.page.4"
        		, "book.gt_manual_11.page.5"
        		, "book.gt_manual_11.page.6"
        		, "book.gt_manual_11.page.7"
        		, "book.gt_manual_11.page.8"
        		, "book.gt_manual_11.page.9"
        		, "book.gt_manual_11.page.10"
        		, "book.gt_manual_11.page.11"
        		, "book.gt_manual_11.page.12"
        		, "book.gt_manual_11.page.13"
        		, "book.gt_manual_11.page.14"
        		, "book.gt_manual_11.page.15"
        		, "book.gt_manual_11.page.16"
        		, "book.gt_manual_11.page.17"
        		, "book.gt_manual_11.page.18"
        		, "book.gt_manual_11.page.19"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  10));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_12.title", "book.gt_manual_1.author", new String[] {
        		  "book.gt_manual_12.page.1"
        		, "book.gt_manual_12.page.2"
        		, "book.gt_manual_12.page.3"
        		, "book.gt_manual_12.page.4"
        		, "book.gt_manual_12.page.5"
        		, "book.gt_manual_12.page.6"
        		, "book.gt_manual_12.page.7"
        		, "book.gt_manual_12.page.8"
        		, "book.gt_manual_12.page.9"
        		, "book.gt_manual_12.page.10"
        		, "book.gt_manual_12.page.11"
        		, "book.gt_manual_12.page.12"
        		, "book.gt_manual_12.page.13"
        		, "book.gt_manual_12.page.14"
        		, "book.gt_manual_12.page.15"
        		, "book.gt_manual_12.page.16"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  10));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_13.title", "book.gt_manual_13.author", new String[] {
        		  "book.gt_manual_13.page.1"
        		, "book.gt_manual_13.page.2"
        		, "book.gt_manual_13.page.3"
        		, "book.gt_manual_13.page.4"
        		, "book.gt_manual_13.page.5"
        		, "book.gt_manual_13.page.6"
        		, "book.gt_manual_13.page.7"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  10));
        tStack = null;
        }
        
        tStack = GT_Utility.getWrittenBook("book.gt_manual_14.title", "book.gt_manual_14.author", new String[] {
        		  "book.gt_manual_14.page.1"
        		, "book.gt_manual_14.page.2"
        		, "book.gt_manual_14.page.3"
        		, "book.gt_manual_14.page.4"
        		, "book.gt_manual_14.page.5"
        		, "book.gt_manual_14.page.6"
        		, "book.gt_manual_14.page.7"
        		, "book.gt_manual_14.page.8"
        		, "book.gt_manual_14.page.9"
        		, "book.gt_manual_14.page.10"
        		, "book.gt_manual_14.page.11"
        		, "book.gt_manual_14.page.12"
        		, "book.gt_manual_14.page.13"
        		, "book.gt_manual_14.page.14"
        		, "book.gt_manual_14.page.15"
        		, "book.gt_manual_14.page.16"
        		, "book.gt_manual_14.page.17"
        		, "book.gt_manual_14.page.18"
        		, "book.gt_manual_14.page.19"
        		, "book.gt_manual_14.page.20"
        		, "book.gt_manual_14.page.21"
        		, "book.gt_manual_14.page.22"
        		});
        if (tStack != null) {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,   2));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY		, new WeightedRandomChestContent(GT_Utility.copy(tStack)							, 1, 1,  10));
        tStack = null;
        }
        
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Items.Tool_Sword_Flint			.get(1)			, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Items.Tool_Pickaxe_Flint		.get(1)			, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Items.Tool_Shovel_Flint			.get(1)			, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Items.Tool_Axe_Flint			.get(1)			, 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST				, new WeightedRandomChestContent(GT_Items.Bottle_Purple_Drink		.get(1)			, 8, 16,  2));
        
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Items.Spray_Ice					.get(1)			, 1, 1, 20));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Items.Spray_Pepper				.get(1)			, 1, 1, 20));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_Items.Bottle_Purple_Drink		.get(1)			, 8, 16, 80));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Silver		, 1), 1, 6, 120));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Lead			, 1), 1, 6,  30));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel		, 1), 1, 6,  60));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze		, 1), 1, 6,  60));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Emerald		, 1), 1, 6,  20));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ruby			, 1), 1, 6,  20));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Sapphire		, 1), 1, 6,  20));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GreenSapphire	, 1), 1, 6,  20));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Olivine		, 1), 1, 6,  20));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetRed		, 1), 1, 6,  40));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetYellow	, 1), 1, 6,  40));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Manganese		, 1), 1, 3,  60));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST			, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome		, 1), 1, 3,  40));
        
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Silver		, 1), 4,16,  12));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Platinum		, 1), 2, 8,   4));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ruby			, 1), 2, 8,   2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Sapphire		, 1), 2, 8,   2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GreenSapphire	, 1), 2, 8,   2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Olivine		, 1), 2, 8,   2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetRed		, 1), 2, 8,   4));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GarnetYellow	, 1), 2, 8,   4));
        
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_Items.Coin_Gold_Ancient.get(				  1),16,64,  10));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_Items.ZPM.getWithCharge(1, 	  Integer.MAX_VALUE), 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Bronze			 , 1), 4, 16, 12));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.Ruby		 	, 1), 2,  8,  2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.Sapphire	 	, 1), 2, 8,   2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.GreenSapphire	, 1), 2, 8,   2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.Olivine		 	, 1), 2, 8,   2));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.GarnetRed		, 1), 2, 8,   4));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST	, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.GarnetYellow	, 1), 2, 8,   4));
        
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, new WeightedRandomChestContent(new ItemStack(Items.fire_charge				, 1), 2, 8,  30));
        
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_Items.Spray_Ice.get(						  1), 1, 1,  20));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Silver			, 1), 1, 4,  12));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Lead			, 1), 1, 4,   3));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Steel			, 1), 1, 4,   6));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Bronze			, 1), 1, 4,   6));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.Emerald			, 1), 1, 4,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.Ruby			, 1), 1, 4,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.Sapphire		, 1), 1, 4,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.GreenSapphire	, 1), 1, 4,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.Olivine			, 1), 1, 4,   2));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.GarnetRed		, 1), 1, 4,   4));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.gem	, Materials.GarnetYellow	, 1), 1, 4,   4));
        
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_Items.McGuffium_239.get(										  1), 1, 1,   1));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.dust	, Materials.Chrome		, 1), 1, 4,   6));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.dust	, Materials.Manganese	, 1), 1, 4,  12));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Steel		, 1), 4,12,  12));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Bronze		, 1), 4,12,  12));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Brass		, 1), 4,12,  12));
        
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_Items.McGuffium_239.get(										  1), 1, 1,  10));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_Items.Spray_Pepper.get(											  1), 1, 1,  20));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.dust	, Materials.Chrome		, 1), 1, 8,   6));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.dust	, Materials.Manganese	, 1), 1, 8,  12));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Steel		, 1), 8,32,  12));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING		, new WeightedRandomChestContent(GT_OreDictUnificator.get(OrePrefixes.ingot	, Materials.Bronze		, 1), 8,32,  12));
	}
}
