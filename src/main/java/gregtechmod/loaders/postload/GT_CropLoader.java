package gregtechmod.loaders.postload;

import gregtechmod.api.util.GT_BaseCrop;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Material;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_CropLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.out.println("GT_Mod: Register Crops to IC2.");
        try {
	        new GT_BaseCrop(124, "Indigo"			, "Eloraam"					, GT_MetaItem_Material.instance.getStack(8, 1)	, null																																						, GT_MetaItem_Material.instance.getStack(8, 4)	, 2, 4,    0, 1, 4, 1, 1, 0, 4, 0, new String[] {"Flower", "Color", "Ingredient"});
	        new GT_BaseCrop(125, "Flax"				, "Eloraam"					, new ItemStack(Item.silk, 1)					, null																																						, null											, 2, 4,    0, 1, 4, 1, 1, 2, 0, 1, new String[] {"Silk", "Vine", "Addictive"});
	        new GT_BaseCrop(126, "Oilberries"		, "Spacetoad"				, GT_MetaItem_Material.instance.getStack(7, 1)	, null																																						, null											, 9, 4,    0, 1, 4, 6, 1, 2, 1,12, new String[] {"Fire", "Dark", "Reed", "Rotten", "Coal", "Oil"});
	        new GT_BaseCrop(127, "Bobsyeruncleranks", "GenerikB"				, GT_OreDictUnificator.get("dustSmallEmerald",1), new ItemStack[] {new ItemStack(Item.emerald, 1)}																											, null											,11, 4,    0, 1, 4, 4, 0, 8, 2, 9, new String[] {"Shiny", "Vine", "Emerald", "Berylium", "Crystal"});
	        new GT_BaseCrop(128, "Diareed"			, "Direwolf20"				, GT_OreDictUnificator.get("dustSmallDiamond",1), new ItemStack[] {new ItemStack(Item.diamond, 1)}																											, null											,12, 4,    0, 1, 4, 5, 0,10, 2,10, new String[] {"Fire", "Shiny", "Reed", "Coal", "Diamond", "Crystal"});
	        new GT_BaseCrop(129, "Withereed"		, "CovertJaguar"			, GT_OreDictUnificator.get("dustCoal", 1)		, new ItemStack[] {new ItemStack(Item.coal, 1), new ItemStack(Item.coal, 1)}																				, null											, 8, 4,    0, 1, 4, 2, 0, 4, 1, 3, new String[] {"Fire", "Undead", "Reed", "Coal", "Rotten", "Wither"});
	        new GT_BaseCrop(130, "Blazereed"		, "Mr. Brain"				, new ItemStack(Item.blazePowder, 1)			, new ItemStack[] {new ItemStack(Item.blazeRod, 1)}																											, null											, 6, 4,    0, 1, 4, 0, 4, 1, 0, 0, new String[] {"Fire", "Blaze", "Reed", "Sulfur"});
	        new GT_BaseCrop(131, "Eggplant"			, "Link"					, new ItemStack(Item.egg, 1)					, new ItemStack[] {new ItemStack(Item.chickenRaw, 1), new ItemStack(Item.feather , 1), new ItemStack(Item.feather , 1), new ItemStack(Item.feather , 1)}	, null											, 6, 3,  900, 2, 3, 0, 4, 1, 0, 0, new String[] {"Chicken", "Egg", "Edible", "Feather", "Flower", "Addictive"});
	        new GT_BaseCrop(132, "Corium"			, "Gregorius Techneticies"	, new ItemStack(Item.leather, 1)				, null																																						, null											, 6, 4,    0, 1, 4, 0, 2, 3, 1, 0, new String[] {"Cow", "Silk", "Vine"});
	        new GT_BaseCrop(133, "Corpseplant"		, "Mr. Kenny"				, new ItemStack(Item.rottenFlesh, 1)			, new ItemStack[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.bone, 1)}									, null											, 5, 4,    0, 1, 4, 0, 2, 1, 0, 3, new String[] {"Toxic", "Undead", "Vine", "Edible", "Rotten"});
	        new GT_BaseCrop(134, "Creeperweed"		, "General Spaz"			, GT_OreDictUnificator.get("dustGunpowder", 1)	, null																																						, null											, 7, 4,    0, 1, 4, 3, 0, 5, 1, 3, new String[] {"Creeper", "Vine", "Explosive", "Fire", "Sulfur", "Saltpeter", "Coal"});
	        new GT_BaseCrop(135, "Enderbloom"		, "RichardG"				, GT_OreDictUnificator.get("dustEnderPearl", 1)	, new ItemStack[] {new ItemStack(Item.enderPearl, 1), new ItemStack(Item.enderPearl , 1), new ItemStack(Item.eyeOfEnder , 1)}								, null											,10, 4,    0, 1, 4, 5, 0, 2, 1, 6, new String[] {"Ender", "Flower", "Shiny"});
	        new GT_BaseCrop(136, "Meatrose"			, "VintageBeef"				, new ItemStack(Item.dyePowder, 1, 9)			, new ItemStack[] {new ItemStack(Item.beefRaw, 1), new ItemStack(Item.porkRaw , 1), new ItemStack(Item.chickenRaw , 1), new ItemStack(Item.fishRaw , 1)}	, null											, 7, 4, 1500, 1, 4, 0, 4, 1, 3, 0, new String[] {"Edible", "Flower", "Cow", "Fish", "Chicken", "Pig"});
	        new GT_BaseCrop(137, "Milkwart"			, "Mr. Brain"				, new ItemStack(Item.bucketMilk, 1)				, null																																						, null											, 6, 3,  900, 1, 3, 0, 3, 0, 1, 0, new String[] {"Edible", "Milk", "Cow"});
	        new GT_BaseCrop(138, "Slimeplant"		, "Neowulf"					, new ItemStack(Item.slimeBall, 1)				, null																																						, null											, 6, 4,    0, 3, 4, 3, 0, 0, 0, 2, new String[] {"Slime", "Bouncy", "Sticky", "Bush"});
	        new GT_BaseCrop(139, "Spidernip"		, "Mr. Kenny"				, new ItemStack(Item.silk, 1)					, new ItemStack[] {new ItemStack(Item.spiderEye, 1), new ItemStack(Block.web , 1)}																			, null											, 4, 4,  600, 1, 4, 2, 1, 4, 1, 3, new String[] {"Toxic", "Silk", "Spider", "Flower", "Ingredient", "Addictive"});
	        new GT_BaseCrop(140, "Tearstalks"		, "Neowulf"					, new ItemStack(Item.ghastTear, 1)				, null																																						, null											, 8, 4,    0, 1, 4, 1, 2, 0, 0, 0, new String[] {"Healing", "Nether", "Ingredient", "Reed", "Ghast"});
	        new GT_BaseCrop(141, "Tine"				, "Gregorius Techneticies"	, GT_OreDictUnificator.get("nuggetTin", 1)		, null																																						, null											, 5, 3,    0, 2, 3, 2, 0, 3, 0, 0, new String[] {"Shiny", "Metal", "Pine", "Tin", "Bush"});
	        new GT_BaseCrop(142, "Coppon"			, "Mr. Brain"				, GT_OreDictUnificator.get("nuggetCopper", 1)	, null																																						, null											, 6, 3,    0, 2, 3, 2, 0, 1, 1, 1, new String[] {"Shiny", "Metal", "Cotton", "Copper", "Bush"});
	        new GT_BaseCrop(143, "Brown Mushrooms"	, "Mr. Brain"				, new ItemStack(Block.mushroomBrown, 1)			, null																																						, new ItemStack(Block.mushroomBrown, 4)			, 1, 3,    0, 1, 3, 0, 2, 0, 0, 2, new String[] {"Edible", "Mushroom", "Ingredient"});
	        new GT_BaseCrop(144, "Red Mushrooms"	, "Mr. Kenny"				, new ItemStack(Block.mushroomRed, 1)			, null																																						, new ItemStack(Block.mushroomRed  , 4)			, 1, 3,    0, 1, 3, 0, 1, 3, 0, 2, new String[] {"Toxic", "Mushroom", "Ingredient"});
	        new GT_BaseCrop(145, "Argentia"			, "Eloraam"					, GT_OreDictUnificator.get("nuggetSilver", 1)	, null																																						, null											, 7, 4,    0, 3, 4, 2, 0, 1, 0, 0, new String[] {"Shiny", "Metal", "Silver", "Reed"});
	        new GT_BaseCrop(146, "Plumbilia"		, "KingLemming"				, GT_OreDictUnificator.get("nuggetLead", 1)		, null																																						, null											, 6, 4,    0, 3, 4, 2, 0, 3, 1, 1, new String[] {"Heavy", "Metal", "Lead", "Reed"});
        } catch(Throwable e) {
            GT_Log.err.println("GT_Mod: Failed to register Crops to IC2.");
        }
	}
}