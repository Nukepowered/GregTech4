package gregtechmod.loaders.postload;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.util.GT_BaseCrop;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_OreDictUnificator;

import ic2.api.crops.CropCard;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_CropLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("Register Crops to IC2.");
        try {
	        new GT_BaseCrop(124, "gt_crops.indigo"				, "Eloraam"					, GT_Items.Crop_Drop_Indigo.get(1)				, null																																						, GT_Items.Crop_Drop_Indigo.get(4)				, 2, 4,    0, 1, 4, 1, 1, 0, 4, 0, new String[] {"Flower", "Color", "Ingredient"});
	        new GT_BaseCrop(125, "gt_crops.flax"				, "Eloraam"					, new ItemStack(Items.string, 1)				, null																																						, null											, 2, 4,    0, 1, 4, 1, 1, 2, 0, 1, new String[] {"Silk", "Vine", "Addictive"});
	        new GT_BaseCrop(126, "gt_crops.oilberries"			, "Spacetoad"				, GT_Items.Crop_Drop_OilBerry.get(1)			, null																																						, null											, 9, 4,    0, 1, 4, 6, 1, 2, 1,12, new String[] {"Fire", "Dark", "Reed", "Rotten", "Coal", "Oil"});
	        new GT_BaseCrop(127, "gt_crops.bobsyeruncleranks"	, "GenerikB"				, GT_Items.Crop_Drop_BobsYerUncleRanks.get(1)	, new ItemStack[] {new ItemStack(Items.emerald, 1)}																											, null											,11, 4,    0, 1, 4, 4, 0, 8, 2, 9, new String[] {"Shiny", "Vine", "Emerald", "Berylium", "Crystal"});
	        new GT_BaseCrop(128, "gt_crops.diareed"				, "Direwolf20"				, GT_OreDictUnificator.get("dustSmallDiamond",1), new ItemStack[] {new ItemStack(Items.diamond, 1)}																											, null											,12, 4,    0, 1, 4, 5, 0,10, 2,10, new String[] {"Fire", "Shiny", "Reed", "Coal", "Diamond", "Crystal"});
	        new GT_BaseCrop(129, "gt_crops.withereed"			, "CovertJaguar"			, GT_OreDictUnificator.get("dustCoal", 1)		, new ItemStack[] {new ItemStack(Items.coal, 1), new ItemStack(Items.coal, 1)}																				, null											, 8, 4,    0, 1, 4, 2, 0, 4, 1, 3, new String[] {"Fire", "Undead", "Reed", "Coal", "Rotten", "Wither"});
	        new GT_BaseCrop(130, "gt_crops.blazereed"			, "Mr. Brain"				, new ItemStack(Items.blaze_powder, 1)			, new ItemStack[] {new ItemStack(Items.blaze_rod, 1)}																										, null											, 6, 4,    0, 1, 4, 0, 4, 1, 0, 0, new String[] {"Fire", "Blaze", "Reed", "Sulfur"});
	        new GT_BaseCrop(131, "gt_crops.eggplant"			, "Link"					, new ItemStack(Items.egg, 1)					, new ItemStack[] {new ItemStack(Items.chicken, 1), new ItemStack(Items.feather , 1), new ItemStack(Items.feather , 1), new ItemStack(Items.feather , 1)}	, null											, 6, 3,  900, 2, 3, 0, 4, 1, 0, 0, new String[] {"Chicken", "Egg", "Edible", "Feather", "Flower", "Addictive"});
	        new GT_BaseCrop(132, "gt_crops.corium"				, "Gregorius Techneticies"	, new ItemStack(Items.leather, 1)				, null																																						, null											, 6, 4,    0, 1, 4, 0, 2, 3, 1, 0, new String[] {"Cow", "Silk", "Vine"});
	        new GT_BaseCrop(133, "gt_crops.corpseplant"			, "Mr. Kenny"				, new ItemStack(Items.rotten_flesh, 1)			, new ItemStack[] {new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.bone, 1)}											, null											, 5, 4,    0, 1, 4, 0, 2, 1, 0, 3, new String[] {"Toxic", "Undead", "Vine", "Edible", "Rotten"});
	        new GT_BaseCrop(134, "gt_crops.creeperweed"			, "General Spaz"			, GT_OreDictUnificator.get("dustGunpowder", 1)	, null																																						, null											, 7, 4,    0, 1, 4, 3, 0, 5, 1, 3, new String[] {"Creeper", "Vine", "Explosive", "Fire", "Sulfur", "Saltpeter", "Coal"});
	        new GT_BaseCrop(135, "gt_crops.enderbloom"			, "RichardG"				, GT_OreDictUnificator.get("dustEnderPearl", 1)	, new ItemStack[] {new ItemStack(Items.ender_pearl, 1), new ItemStack(Items.ender_pearl , 1), new ItemStack(Items.ender_eye , 1)}							, null											,10, 4,    0, 1, 4, 5, 0, 2, 1, 6, new String[] {"Ender", "Flower", "Shiny"});
	        new GT_BaseCrop(136, "gt_crops.meatrose"			, "VintageBeef"				, new ItemStack(Items.dye, 1, 9)				, new ItemStack[] {new ItemStack(Items.beef, 1), new ItemStack(Items.porkchop , 1), new ItemStack(Items.chicken , 1), new ItemStack(Items.fish , 1)}		, null											, 7, 4, 1500, 1, 4, 0, 4, 1, 3, 0, new String[] {"Edible", "Flower", "Cow", "Fish", "Chicken", "Pig"});
	        new GT_BaseCrop(137, "gt_crops.milkwart"			, "Mr. Brain"				, GT_Items.Crop_Drop_MilkWart.get(1)			, null																																						, null											, 6, 3,  900, 1, 3, 0, 3, 0, 1, 0, new String[] {"Edible", "Milk", "Cow"});
	        new GT_BaseCrop(138, "gt_crops.slimeplant"			, "Neowulf"					, new ItemStack(Items.slime_ball, 1)			, null																																						, null											, 6, 4,    0, 3, 4, 3, 0, 0, 0, 2, new String[] {"Slime", "Bouncy", "Sticky", "Bush"});
	        new GT_BaseCrop(139, "gt_crops.spidernip"			, "Mr. Kenny"				, new ItemStack(Items.string, 1)				, new ItemStack[] {new ItemStack(Items.spider_eye, 1), new ItemStack(Blocks.web , 1)}																		, null											, 4, 4,  600, 1, 4, 2, 1, 4, 1, 3, new String[] {"Toxic", "Silk", "Spider", "Flower", "Ingredient", "Addictive"});
	        new GT_BaseCrop(140, "gt_crops.tearstalks"			, "Neowulf"					, new ItemStack(Items.ghast_tear, 1)			, null																																						, null											, 8, 4,    0, 1, 4, 1, 2, 0, 0, 0, new String[] {"Healing", "Nether", "Ingredient", "Reed", "Ghast"});
	        new GT_BaseCrop(141, "gt_crops.tine"				, "Gregorius Techneticies"	, GT_Items.Crop_Drop_Tine.get(1)				, null																																						, null											, 5, 3,    0, 2, 3, 2, 0, 3, 0, 0, new String[] {"Shiny", "Metal", "Pine", "Tin", "Bush"});
	        
	        // Changing drop of default IC2 crops
	        GT_Log.log.info("Changing default IC2 crop loot");
	        Map<CropCard, ItemStack> crops = new HashMap<>();
	        
	        crops.put(ic2.core.crop.IC2Crops.cropPlumbiscus	, GT_Items.Crop_Drop_Plumbilia	.get(1));
	        crops.put(ic2.core.crop.IC2Crops.cropShining	, GT_Items.Crop_Drop_Argentia	.get(1));
	        crops.put(ic2.core.crop.IC2Crops.cropFerru		, GT_Items.Crop_Drop_Ferru		.get(1));
	        crops.put(ic2.core.crop.IC2Crops.cropAurelia	, GT_Items.Crop_Drop_Aurelia	.get(1));
	        crops.put(ic2.core.crop.IC2Crops.cropCyprium	, GT_Items.Crop_Drop_Coppon		.get(1));
	         
	        for (Entry<CropCard, ItemStack> entry : crops.entrySet()) {
	        	try {
		        	Field f = entry.getKey().getClass().getDeclaredField("mDrop");
		        	f.setAccessible(true);
		        	f.set(entry.getKey(), entry.getValue());
	        	} catch (Throwable e) {
	        		GT_Log.log.error("Unable to change drop for IC2:" + entry.getKey().name());
	        		if (GregTech_API.DEBUG_MODE) {
	        			GT_Log.log.catching(e);
	        		}
	        	}
	        }
	        
        } catch(Throwable e) {
            GT_Log.log.error("Failed to register Crops to IC2.");
            if (GregTech_API.DEBUG_MODE) {
            	GT_Log.log.catching(e);
            }
        }
	}
}