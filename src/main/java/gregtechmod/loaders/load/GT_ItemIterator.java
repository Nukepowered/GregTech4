package gregtechmod.loaders.load;

import java.util.Iterator;

import buildcraft.api.tools.IToolWrench;
import cofh.api.item.IToolHammer;
import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import mods.railcraft.api.core.items.IToolCrowbar;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class GT_ItemIterator implements Runnable {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void run() {
		ItemStack tCharmL = null;
		ItemStack tCharmLL = null;
		ItemStack tCharmI = null;
		ItemStack tCharmII = null;
		ItemStack tCharmIII = null;

		GT_Log.log.info("GT_Mod: Scanning for certain kinds of compatible Machineblocks.");
		final ItemStack itemStack = null;
		final ItemStack[] aRecipe = new ItemStack[9];
		ItemStack tStack2 = aRecipe[0] = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, 1L);
		aRecipe[1] = tStack2;
		aRecipe[3] = (aRecipe[2] = tStack2);
		aRecipe[4] = null;
		aRecipe[6] = (aRecipe[5] = tStack2);
		aRecipe[8] = (aRecipe[7] = tStack2);
		ItemStack tStack3;
		        
		if (itemStack != (tStack3 = GT_ModHandler.getRecipeOutput(aRecipe))) {
			GT_ModHandler.addPulverisationRecipe(tStack3, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bronze, 8L), null, 0, false);
			GT_ModHandler.addSmeltingRecipe(tStack3, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, 8L));
		}
		final ItemStack itemStack2 = null;
		final ItemStack[] aRecipe2 = new ItemStack[9];
		tStack2 = (aRecipe2[0] = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Bronze, 1L));
		aRecipe2[1] = tStack2;
		aRecipe2[3] = (aRecipe2[2] = tStack2);
		aRecipe2[4] = null;
		aRecipe2[6] = (aRecipe2[5] = tStack2);
		aRecipe2[8] = (aRecipe2[7] = tStack2);
		        
		if (itemStack2 != (tStack3 = GT_ModHandler.getRecipeOutput(aRecipe2))) {
			GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier00, tStack3);
			GT_ModHandler.addPulverisationRecipe(tStack3, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bronze, 8L), null, 0, false);
			GT_ModHandler.addSmeltingRecipe(tStack3, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, 8L));
		}
		        
		final ItemStack itemStack3 = null;
		final ItemStack[] aRecipe3 = new ItemStack[9];
		tStack2 = (aRecipe3[0] = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1L));
		ItemStack tStack4 = aRecipe3[1] = new ItemStack(Blocks.glass, 1, 0);
		aRecipe3[2] = tStack2;
		aRecipe3[3] = tStack4;
		aRecipe3[4] = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 1L);
		aRecipe3[5] = tStack4;
		aRecipe3[6] = tStack2;
		aRecipe3[7] = tStack4;
		aRecipe3[8] = tStack2;

		if (itemStack3 != (tStack3 = GT_ModHandler.getRecipeOutput(aRecipe3))) {
			GT_ModHandler.addPulverisationRecipe(tStack3, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold, 1L), 0, false);
		}
		        
		final ItemStack itemStack4 = null;
		final ItemStack[] aRecipe4 = new ItemStack[9];
		tStack2 = (aRecipe4[0] = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L));
		tStack4 = (aRecipe4[1] = new ItemStack(Blocks.glass, 1, 0));
		aRecipe4[2] = tStack2;
		aRecipe4[3] = tStack4;
		aRecipe4[4] = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Gold, 1L);
		aRecipe4[5] = tStack4;
		aRecipe4[6] = tStack2;
		aRecipe4[7] = tStack4;
		aRecipe4[8] = tStack2;
		        
		if (itemStack4 != (tStack3 = GT_ModHandler.getRecipeOutput(aRecipe4))) {
			GT_ModHandler.addPulverisationRecipe(tStack3, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel, 4L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold, 1L), 0, false);
		}
		        
		GT_Log.log.info("GT_Mod: Registering various Tools to be usable on GregTech Machines");
		GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.stick, 1)));
		GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack(Items.iron_ingot, 1), null, null, null, new ItemStack(Items.stick, 1)));

		GT_Log.log.info("GT_Mod: Adding Food Recipes to the Automatic Canning Machine. (also during the following Item Iteration)");
		GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.rotten_flesh, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(2L), GT_Items.IC2_Food_Can_Spoiled.get(2L), null, 200, 1);
		GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.spider_eye, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(1L), GT_Items.IC2_Food_Can_Spoiled.get(1L), null, 100, 1);
		GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.poisonous_potato, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(1L), GT_Items.IC2_Food_Can_Spoiled.get(1L), null, 100, 1);
		GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.cake, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(6L), GT_Items.IC2_Food_Can_Filled.get(6L), null, 600, 1);
		GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.bowl, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(3L), GT_Items.IC2_Food_Can_Filled.get(3L), new ItemStack(Items.bowl, 1), 300, 1);
		        
		GT_Log.log.info("GT_Mod: Scanning ItemList.");
		Iterator<Item> iterator = Item.itemRegistry.iterator();
		while (iterator.hasNext()) {
			final Item tItem = iterator.next();
			final String tName;
			if (tItem != null && (tName = tItem.getUnlocalizedName()) != null) {
				try {
					if (tItem instanceof IToolCrowbar) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityRCCrowbars", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
								GT_Log.log.info("GT_Mod: Removed infinite RC Crowbar: " + tName);
							}
						} else if (GregTech_API.registerCrowbar(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
							GT_Log.log.info("GT_Mod: Registered valid RC Crowbar: " + tName);
						}
					}
				} catch (Throwable e) {}
				try {
					if (tItem instanceof IToolHammer) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityMFRHammers", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
								GT_Log.log.info("GT_Mod: Removed infinite MFR Hammer: " + tName);
							}
						} else if (GregTech_API.registerCrowbar(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
							GT_Log.log.info("GT_Mod: Registered valid MFR Hammer: " + tName);
						}
					}
				} catch (Throwable e) {}
				try {
					if (tItem instanceof IToolWrench && !(tItem instanceof IToolCrowbar)) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityBCWrenches", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
								GT_Log.log.info("GT_Mod: Removed infinite BC Wrench: " + tName);
							}
						} else if (GregTech_API.registerWrench(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
							GT_Log.log.info("GT_Mod: Registered valid BC Wrench: " + tName);
						}
					}
				} catch (Throwable e) {}
				if (tItem instanceof ItemBlock) {
					try {
						final Block tBlock = Block.getBlockFromItem(tItem);
						if (tBlock != null && GT_Mod.sBlockStackSize < tItem.getItemStackLimit()) {
							try {
								if (tBlock.isReplaceableOreGen(GregTech_API.sDummyWorld, 0, 0, 0, Blocks.stone) || tBlock.isReplaceableOreGen(GregTech_API.sDummyWorld, 0, 0, 0, Blocks.netherrack) || tBlock.isReplaceableOreGen(GregTech_API.sDummyWorld, 0, 0, 0, Blocks.end_stone)) {
									tItem.setMaxStackSize(GT_Mod.sBlockStackSize);
								}
							} catch (Throwable e) {
								GT_Log.log.catching(e);
							}
						}
					} catch (IndexOutOfBoundsException e) {
						GT_Log.log.error("ERROR! A Mod attempted to return an invalid Block ID using ItemBlock! Please mention this to the respective Mod Author, who owns the following Item.");
						GT_Log.log.error("ITEM: " + tItem + " - " + tItem.getItemStackDisplayName(new ItemStack(tItem)));
						GT_Log.log.error("ATTEMPTED BLOCK ID: " + ((ItemBlock) tItem).getUnlocalizedName());
						GT_Log.log.error("");
						GT_Log.log.throwing(e);
						if (!GregTech_API.DEBUG_MODE) {
							throw new IndexOutOfBoundsException();
						}
					}
				}
				if (tItem instanceof ItemFood && tItem != GT_Items.IC2_Food_Can_Filled.getItem() && tItem != GT_Items.IC2_Food_Can_Spoiled.getItem()) {
					final int tFoodValue = (int) Math.ceil(((ItemFood) tItem).func_150905_g(new ItemStack(tItem)) / 2.0D);
					if (tFoodValue > 0) {
						GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(tFoodValue), GT_Items.IC2_Food_Can_Filled.get(tFoodValue), GT_Utility.getContainerItem(new ItemStack(tItem, 1, 0)), tFoodValue * 100, 1);
					}
				}
				if (tItem instanceof IFluidContainerItem) {
					GT_OreDictUnificator.addToBlacklist(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.ItemSensorLocationCard") || tName.equals("item.ItemEnergySensorLocationCard") || tName.equals("item.ItemEnergyArrayLocationCard") || tName.equals("item.ItemTextCard")) {
					GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), null, GT_Items.Circuit_Basic.get(2), 200, 32);
				}
				if (tName.equals("item.ItemTimeCard")) {
					GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), null, GT_Items.Circuit_Basic.get(1), 100, 32);
				}
				if (tName.equals("tile.beehives")) {
					final Block tBlock = Block.getBlockFromItem(tItem);
					if (tBlock != null) {
						((GT_Tool_Item) GT_Items.Tool_Scoop_Aluminium.getItem()) .addToMaterialList(tBlock.getMaterial());
					}
				}
				if (tName.equals("tile.ArsMagica:ore_vinteum")) {
					GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Vinteum, new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("item.ArsMagica:purified_vinteum")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), null, 256, 5);
				}
				if (tName.equals("item.meefSteak") || tName.equals("item.venisonCooked")) {
					GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 16, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
				}
				if (tName.equals("item.meefRaw") || tName.equals("item.venisonRaw")) {
					GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 12, 0), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L), null, null, null, 5000);
				}
				if (tName.equals("item.fieryBlood")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), null, 2048, 5);
				}
				if (tName.equals("tile.TFRoots")) {
					GT_ModHandler.addPulverisationRecipe(new ItemStack(tItem, 1, 0), new ItemStack(Items.stick, 2), new ItemStack(Items.stick, 1), 30);
					GT_ModHandler.addSawmillRecipe(new ItemStack(tItem, 1, 0), new ItemStack(Items.stick, 4), new ItemStack(Items.stick, 2));
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 1), new ItemStack(Items.stick, 4), 32, 5);
				}
				if (tName.equals("item.liveRoot")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), new ItemStack(Items.stick, 2), 16, 5);
					GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(tItem, 1, 0), Materials.LiveRoot, GregTech_API.MATERIAL_UNIT);
				}
				if (tName.equals("item.ironwoodIngot")) {
					GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.IronWood, new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("item.steeleafIngot")) {
					GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.SteelLeaf, new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("item.fieryIngot")) {
					GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.FieryBlood, new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("item.tconstruct.manual")) {
					GT_OreDictUnificator.registerOre("bookTinkersManual", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.ArsMagica:spell_parchment")) {
					GT_OreDictUnificator.registerOre("paperArsSpellParchment", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.ArsMagica:spell_recipe")) {
					GT_OreDictUnificator.registerOre("paperArsSpellRecipe", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.ArsMagica:spell_book")) {
					GT_OreDictUnificator.registerOre("bookArsSpells", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.myst.page")) {
					GT_OreDictUnificator.registerOre("paperMystcraft", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.myst.agebook")) {
					GT_OreDictUnificator.registerOre("bookMystcraftAge", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.myst.linkbook")) {
					GT_OreDictUnificator.registerOre("bookMystcraftLink", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.myst.notebook")) {
					GT_OreDictUnificator.registerOre("bookNotes", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.itemManuelBook")) {
					GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("item.blueprintItem")) {
					GT_OreDictUnificator.registerOre("paperBlueprint", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.ccprintout")) {
					GT_OreDictUnificator.registerOre("paperWritten", new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre("paperWritten", new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(tItem, 1, 2));
				}
				if (tName.equals("item.blueprintItem")) {
					GT_OreDictUnificator.registerOre("paperBlueprint", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.wirelessmap")) {
					GT_OreDictUnificator.registerOre("paperMap", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.ItemResearchNotes")) {
					GT_OreDictUnificator.registerOre("paperResearch", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.ItemThaumonomicon")) {
					GT_OreDictUnificator.registerOre("bookThaumonomicon", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.ItemEssence")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 7), new ItemStack(tItem, 1, 0), 80, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 10), new ItemStack(tItem, 1, 0), 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 41), new ItemStack(tItem, 1, 0), 120, 5);
				}
				if (tName.equals("item.ItemWispEssence")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 7), new ItemStack(tItem, 1, 0), 80, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 10), new ItemStack(tItem, 1, 0), 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 41), new ItemStack(tItem, 1, 0), 120, 5);
				}
				if (tName.equals("item.ItemResource")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 4), null, 4, 5);
					GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Thaumium, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Mercury, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Amber, new ItemStack(tItem, 1, 6));
					GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.Mercury, new ItemStack(tItem, 1, 3));
					GT_Mod.sThaumiumObtainable = true;
				}
				if (tName.equals("item.ItemShard")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), null, 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 1), null, 320, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 2), null, 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 3), null, 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 4), null, 240, 5);
				}
				if (tName.equals("item.ligniteCoal")) {
					GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Lignite, new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("item.charmOfLife1")) {
					tCharmL = new ItemStack(tItem, 1, 0);
				}
				if (tName.equals("item.charmOfLife2")) {
					tCharmLL = new ItemStack(tItem, 1, 0);
				}
				if (tName.equals("item.charmOfKeeping1")) {
					tCharmI = new ItemStack(tItem, 1, 0);
				}
				if (tName.equals("item.charmOfKeeping2")) {
					tCharmII = new ItemStack(tItem, 1, 0);
				}
				if (tName.equals("item.charmOfKeeping3")) {
					tCharmIII = new ItemStack(tItem, 1, 0);
				}
				if (tName.equals("tile.railcraft.brick.quarried")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 6));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 7));
				}
				if (tName.equals("tile.railcraft.brick.abyssal")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 6));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 7));
				}
				if (tName.equals("tile.extrabiomes.redrock") || tName.equals("tile.bop.redRocks")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 2));
				}
				if (tName.equals("tile.rpstone")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 6));
				}
				if (tName.equals("tile.sedimentaryStone")) {
					GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(tItem), false);
				}
				if (tName.equals("tile.igneousStone") || tName.equals("tile.igneousStoneBrick") || tName.equals("tile.igneousCobblestone")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteRed	, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteBlack	, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Rhyolite		, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Andesite		, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gabbro		, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt		, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Komatiite		, new ItemStack(tItem, 1, 6));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Dacite		, new ItemStack(tItem, 1, 7));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteRed	, new ItemStack(tItem, 1, 8));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.GraniteBlack	, new ItemStack(tItem, 1, 9));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Rhyolite		, new ItemStack(tItem, 1, 10));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Andesite		, new ItemStack(tItem, 1, 11));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gabbro		, new ItemStack(tItem, 1, 12));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt		, new ItemStack(tItem, 1, 13));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Komatiite		, new ItemStack(tItem, 1, 14));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Dacite		, new ItemStack(tItem, 1, 15));
				}
				if (tName.equals("tile.metamorphicStone") || tName.equals("tile.metamorphicStoneBrick") || tName.equals("tile.metamorphicCobblestone")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gneiss		, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Eclogite		, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble		, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Quartzite		, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Blueschist	, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Greenschist	, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Soapstone		, new ItemStack(tItem, 1, 6));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Migmatite		, new ItemStack(tItem, 1, 7));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gneiss		, new ItemStack(tItem, 1, 8));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Eclogite		, new ItemStack(tItem, 1, 9));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble		, new ItemStack(tItem, 1, 10));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Quartzite		, new ItemStack(tItem, 1, 11));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Blueschist	, new ItemStack(tItem, 1, 12));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Greenschist	, new ItemStack(tItem, 1, 13));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Soapstone		, new ItemStack(tItem, 1, 14));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Migmatite		, new ItemStack(tItem, 1, 15));
				}
				if (tName.equals("tile.blockCosmeticSolid")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian		, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian		, new ItemStack(tItem, 1, 1));
				}
				if (tName.equals("tile.enderchest")) {
					GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingEnderChest, new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("tile.autoWorkbenchBlock")) {
					GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWorkBench, new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("tile.pumpBlock")) {
					GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPump, new ItemStack(tItem, 1, 0));
					if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "BCPump", false)) {
						GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 0));
					}
				}
				if (tName.equals("tile.tankBlock")) {
					GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingTank, new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("item.minotaurAxe")) {
					GT_ModHandler.addPulverisationRecipe(new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Diamond, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), 50, false);
				}
				if (tName.equals("item.bucketFuel")) {
					GT_ModHandler.addCraftingRecipe(GT_ModHandler.getRecipeOutput(null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_ModHandler.getIC2Item("electronicCircuit", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), new ItemStack(tItem, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L)), new Object[] { " S ", "SCS", "SLS", 'S', OrePrefixes.plate.get(Materials.Steel), 'C', OrePrefixes.circuit.get(Materials.Basic), 'L', OrePrefixes.cell.get(Materials.Lithium) });
				}
				if (tName.equals("item.drawplateDiamond")) {
					GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolDrawplate, new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("item.canLava")) {
					GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 8), 0, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L), 5000);
				}
				if (tName.equals("item.refractoryLava")) {
					GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 8), 0, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L), 5000);
				}
			}
		}
			if (tCharmL != null) {
				GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(4L, tCharmL), null,
						GT_Utility.copyAmount(1L, tCharmLL), 800, 2);
			}
			if (tCharmI != null) {
				GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(4L, tCharmI), null,
						GT_Utility.copyAmount(1L, tCharmII), 800, 2);
			}
			if (tCharmII != null) {
				GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copyAmount(4L, tCharmII), null,
						GT_Utility.copyAmount(1L, tCharmIII), 800, 2);
			}
		}
	}