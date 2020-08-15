package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_RecyclerBlacklistLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("GT_Mod: Adding Stuff to the Recycler Blacklist.");
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "easymobgrinderrecycling", true)) {
        	// Skeletons
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.arrow			, 1		, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.bone			, 1		, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.dye			, 1		, 15));
			
			// Zombies
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.rotten_flesh	, 1		, 0));
			
			// Spiders
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.string			, 1		, 0));
			
			// Chicken Eggs
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.egg			, 1		, 0));
        }
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "easystonerecycling", true)) {
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sand			, 1		, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone		, 1		, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone		, 1		, 1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone		, 1		, 2));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone		, 1		, 3));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone		, 1		, 4));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone		, 1		, 5));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone		, 1		, 6));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone		, 1		, 7));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.glass			, 1		, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.glass_bottle	, 1		, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.potionitem		, 1		, 0));
			
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getSmeltingOutput(new ItemStack(Blocks.stone, 1, 0), false, null));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.stone			, 1		, 0)	, null										, null										, new ItemStack(Blocks.stone, 1, 0)																										}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.cobblestone	, 1		, 0)	, null										, null										, new ItemStack(Blocks.cobblestone, 1, 0)																								}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.stone			, 1		, 0)	, null										, new ItemStack(Blocks.stone, 1, 0)			, null											, new ItemStack(Blocks.stone, 1, 0)														}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.stone			, 1		, 0)	, new ItemStack(Blocks.glass, 1, 0)			, new ItemStack(Blocks.stone, 1, 0)																																					}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.cobblestone	, 1		, 0)	, new ItemStack(Blocks.glass, 1, 0)			, new ItemStack(Blocks.cobblestone, 1, 0)																																			}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.sandstone		, 1		, 0)	, new ItemStack(Blocks.glass, 1, 0)			, new ItemStack(Blocks.sandstone, 1, 0)																																				}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.sand			, 1		, 0)	, new ItemStack(Blocks.glass, 1, 0)			, new ItemStack(Blocks.sand, 1, 0)																																					}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.sandstone		, 1		, 0)	, new ItemStack(Blocks.sandstone, 1, 0)		, new ItemStack(Blocks.sandstone, 1, 0)		, new ItemStack(Blocks.sandstone, 1, 0)			, new ItemStack(Blocks.sandstone, 1, 0)		, new ItemStack(Blocks.sandstone, 1, 0)		}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.glass			, 1		, 0)																																																									}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.glass			, 1		, 0)	, new ItemStack(Blocks.glass, 1, 0)																																																}));
			GT_ModHandler.addToRecyclerBlackList(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Blocks.glass			, 1		, 0)	, null, null, new ItemStack(Blocks.glass, 1, 0)																																													}));
			
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.glass_pane			, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.cobblestone			, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.cobblestone_wall		, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.sandstone_stairs		, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_stairs			, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_brick_stairs	, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.lit_furnace			, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.furnace				, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_slab			, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.double_stone_slab		, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_slab			, 1	, 1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.double_stone_slab		, 1	, 1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_slab			, 1	, 3));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.double_stone_slab		, 1	, 3));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_slab			, 1	, 5));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.double_stone_slab		, 1	, 5));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_slab			, 1	, 7));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.double_stone_slab		, 1	, 7));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_pressure_plate	, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone_button			, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stonebrick			, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stonebrick			, 1	, 1));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stonebrick			, 1	, 2));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stonebrick			, 1	, 3));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.stone					, 1	, 0));
			GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.lever					, 1	, 0));
		}
		GT_ModHandler.addToRecyclerBlackList(new ItemStack(Items.snowball					, 1	));
		GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.ice						, 1	));
		GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.snow						, 1	));
		GT_ModHandler.addToRecyclerBlackList(new ItemStack(Blocks.snow_layer				, 1	));
	}
}
