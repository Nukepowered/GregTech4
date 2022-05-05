package gregtechmod.loaders.load;

import java.util.Iterator;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Loader;
import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.RecipeMaps;

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

		GT_Log.log.info("Scanning for certain kinds of compatible Machineblocks.");
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
		        
		GT_Log.log.info("Registering various Tools to be usable on GregTech Machines");
		GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.stick, 1)));
		GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack(Items.iron_ingot, 1), null, null, null, new ItemStack(Items.stick, 1)));

		GT_Log.log.info("Adding Food Recipes to the Automatic Canning Machine. (also during the following Item Iteration)");
		RecipeMaps.CANNING.factory().EUt(1).duration(100).inputs(new ItemStack(Items.rotten_flesh, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(2L)).output(GT_Items.IC2_Food_Can_Spoiled.get(2)).buildAndRegister();
		RecipeMaps.CANNING.factory().EUt(1).duration(100).inputs(new ItemStack(Items.spider_eye, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(1L)).output(GT_Items.IC2_Food_Can_Spoiled.get(1)).buildAndRegister();
		RecipeMaps.CANNING.factory().EUt(1).duration(100).inputs(new ItemStack(Items.poisonous_potato, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(1L)).output(GT_Items.IC2_Food_Can_Spoiled.get(1)).buildAndRegister();
		RecipeMaps.CANNING.factory().EUt(1).duration(600).inputs(new ItemStack(Items.cake, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(6L)).output(GT_Items.IC2_Food_Can_Filled.get(6)).buildAndRegister();
		RecipeMaps.CANNING.factory().EUt(1).duration(300).inputs(new ItemStack(Items.mushroom_stew, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(3L)).output(GT_Items.IC2_Food_Can_Filled.get(3)).output(new ItemStack(Items.bowl, 1)).buildAndRegister();
		
        if (Loader.isModLoaded("appliedenergistics2")) {
        	Optional<ItemStack> opt = appeng.api.AEApi.instance().definitions().blocks().quartz().maybeStack(1);
        	if (opt.isPresent()) 
        		GT_OreDictUnificator.add(OrePrefixes.block, Materials.CertusQuartz, opt.get());
        }
		
		GT_Log.log.info("Scanning ItemList.");
		Iterator<Item> iterator = Item.itemRegistry.iterator();
		while (iterator.hasNext()) {
			final Item tItem = iterator.next();
			final String tName;
			if (tItem != null && (tName = tItem.getUnlocalizedName()) != null) {
				try {
					if (tItem instanceof mods.railcraft.api.core.items.IToolCrowbar) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityRCCrowbars", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
								GT_Log.log.info("Removed infinite RC Crowbar: " + tName);
							}
						} else if (GregTech_API.registerCrowbar(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
							GT_Log.log.info("Registered valid RC Crowbar: " + tName);
						}
					}
				} catch (Throwable e) {}
				try {
					if (tItem instanceof cofh.api.item.IToolHammer) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityMFRHammers", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
								GT_Log.log.info("Removed infinite MFR Hammer: " + tName);
							}
						} else if (GregTech_API.registerCrowbar(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
							GT_Log.log.info("Registered valid MFR Hammer: " + tName);
						}
					}
				} catch (Throwable e) {}
				try {
					if (tItem instanceof buildcraft.api.tools.IToolWrench && !(tItem instanceof mods.railcraft.api.core.items.IToolCrowbar)) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityBCWrenches", false) && GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
								GT_Log.log.info("Removed infinite BC Wrench: " + tName);
							}
						} else if (GregTech_API.registerWrench(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) {
							GT_Log.log.info("Registered valid BC Wrench: " + tName);
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
						RecipeFactory<?> fac = RecipeMaps.CANNING.factory().EUt(1).duration(tFoodValue * 100).inputs(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_Items.IC2_Food_Can_Empty.get(tFoodValue)).output(GT_Items.IC2_Food_Can_Filled.get(tFoodValue));
						ItemStack container = GT_Utility.getContainerItem(new ItemStack(tItem, 1, 0));
						if (container != null) fac.output(container);
						fac.buildAndRegister();
					}
				} else if (tItem instanceof IFluidContainerItem) {
					GT_OreDictUnificator.addToBlacklist(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.ItemSensorLocationCard") || tName.equals("item.ItemEnergySensorLocationCard") || tName.equals("item.ItemEnergyArrayLocationCard") || tName.equals("item.ItemTextCard")) {
					RecipeMaps.ASSEMBLING.factory().EUt(32).duration(200).input(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)).output(GT_Items.Circuit_Basic.get(2)).buildAndRegister();
				} else if (tName.equals("item.ItemTimeCard")) {
					RecipeMaps.ASSEMBLING.factory().EUt(32).duration(100).input(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)).output(GT_Items.Circuit_Basic.get(1)).buildAndRegister();
				} else if (tName.equals("tile.beehives")) {
					final Block tBlock = Block.getBlockFromItem(tItem);
					if (tBlock != null) {
						((GT_Tool_Item) GT_Items.Tool_Scoop_Aluminium.getItem()) .addToMaterialList(tBlock.getMaterial());
					}
				} else if (tName.equals("tile.arsmagica2:ores.0")) {
					GT_OreDictUnificator.set(OrePrefixes.ore, Materials.Vinteum, new ItemStack(tItem, 1, 0));
				} else if (tName.equals("item.arsmagica2:itemOre")) {
					RecipeMaps.MAGIC_FUELS.factory().EUt(16).duration(16).input(new ItemStack(tItem, 3)).buildAndRegister();
				} else if (tName.equals("item.meefSteak") || tName.equals("item.venisonCooked")) {
					RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).inputs(GT_Items.Cell_Empty.get(1), new ItemStack(tItem, 16, 0)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
				} else if (tName.equals("item.meefRaw") || tName.equals("item.venisonRaw")) {
					RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).setShaped(true).inputs(GT_Items.Cell_Empty.get(1), new ItemStack(tItem, 12, 0)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Methane, 1L)).buildAndRegister();
				} else if (tName.equals("item.fieryBlood")) {
					RecipeMaps.MAGIC_FUELS.factory().EUt(32).duration(64).input(new ItemStack(tItem, 1)).buildAndRegister();
				} else if (tName.equals("tile.TFRoots")) {
					GT_ModHandler.addPulverisationRecipe(new ItemStack(tItem, 1, 0), new ItemStack(Items.stick, 2), new ItemStack(Items.stick, 1), 30);
					GT_ModHandler.addSawmillRecipe(new ItemStack(tItem, 1, 0), new ItemStack(Items.stick, 4), new ItemStack(Items.stick, 2));
					RecipeMaps.SAWMILL.factory().EUt(30).duration(200).setShaped(true).input(new ItemStack(tItem, 1, 0)).input(GT_ModHandler.getWater(1000)).outputs(new ItemStack(Items.stick, 2)).chanced(new ItemStack(Items.stick, 1), 7500).chanced(new ItemStack(Items.stick, 1), 2500).buildAndRegister();
					RecipeMaps.MAGIC_FUELS.factory().EUt(16).duration(2).input(new ItemStack(tItem, 1, 1)).output(new ItemStack(Items.stick, 4)).buildAndRegister();
				} else if (tName.equals("item.liveRoot")) {
					RecipeMaps.MAGIC_FUELS.factory().EUt(16).duration(1).input(new ItemStack(tItem, 1, 0)).output(new ItemStack(Items.stick, 2)).buildAndRegister();
					GT_RecipeRegistrator.registerBasicReverseMaceratingAndSmelting(new ItemStack(tItem, 1, 0), Materials.LiveRoot, GregTech_API.MATERIAL_UNIT);
				} else if (tName.equals("item.ironwoodIngot")) {
					GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.IronWood, new ItemStack(tItem, 1, 0));
				} else if (tName.equals("item.steeleafIngot")) {
					GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.SteelLeaf, new ItemStack(tItem, 1, 0));
				} else if (tName.equals("item.fieryIngot")) {
					GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.FieryBlood, new ItemStack(tItem, 1, 0));
				} else if (tName.equals("item.tconstruct.manual")) {
					GT_OreDictUnificator.registerOre("bookTinkersManual", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.arsmagica2:spell_parchment")) {
					GT_OreDictUnificator.registerOre("paperArsSpellParchment", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.arsmagica2:spell_recipe")) {
					GT_OreDictUnificator.registerOre("paperArsSpellRecipe", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.arsmagica2:spell_book")) {
					GT_OreDictUnificator.registerOre("bookArsSpells", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.myst.page")) {
					GT_OreDictUnificator.registerOre("paperMystcraft", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.myst.agebook")) {
					GT_OreDictUnificator.registerOre("bookMystcraftAge", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.myst.linkbook")) {
					GT_OreDictUnificator.registerOre("bookMystcraftLink", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.myst.notebook")) {
					GT_OreDictUnificator.registerOre("bookNotes", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.itemManuelBook")) {
					GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(tItem, 1, 0));
				} else if (tName.equals("item.blueprintItem")) {
					GT_OreDictUnificator.registerOre("paperBlueprint", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.ccprintout")) {
					GT_OreDictUnificator.registerOre("paperWritten", new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre("paperWritten", new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre("bookWritten", new ItemStack(tItem, 1, 2));
				} else if (tName.equals("item.blueprintItem")) {
					GT_OreDictUnificator.registerOre("paperBlueprint", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.wirelessmap")) {
					GT_OreDictUnificator.registerOre("paperMap", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.ItemResearchNotes")) {
					GT_OreDictUnificator.registerOre("paperResearch", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.ItemThaumonomicon")) {
					GT_OreDictUnificator.registerOre("bookThaumonomicon", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.ItemEssence")) {
					RecipeMaps.MAGIC_FUELS.factory().EUt(20).duration(8).input(RecipeEntry.singleton(new ItemStack(tItem, 1, 1), Match.DAMAGE)).output(new ItemStack(tItem, 1, 0)).buildAndRegister();
				} else if (tName.equals("item.ItemWispEssence")) {
					RecipeMaps.MAGIC_FUELS.factory().EUt(20).duration(4).input(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)).buildAndRegister();
				} else if (tName.equals("item.ItemResource")) {
					RecipeMaps.MAGIC_FUELS.factory().EUt(4).duration(1).input(new ItemStack(tItem, 1, 4)).buildAndRegister();
					GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Thaumium, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Mercury, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Amber, new ItemStack(tItem, 1, 6));
					GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.Mercury, new ItemStack(tItem, 1, 3));
					GT_Mod.sThaumiumObtainable = true;
				} else if (tName.equals("item.ItemShard")) {
					RecipeMaps.MAGIC_FUELS.factory().EUt(20).duration(8).input(new ItemStack(tItem, 1, 0)).buildAndRegister();
					RecipeMaps.MAGIC_FUELS.factory().EUt(20).duration(16).input(new ItemStack(tItem, 1, 1)).buildAndRegister();
					RecipeMaps.MAGIC_FUELS.factory().EUt(20).duration(8).input(new ItemStack(tItem, 1, 2)).buildAndRegister();
					RecipeMaps.MAGIC_FUELS.factory().EUt(20).duration(8).input(new ItemStack(tItem, 1, 3)).buildAndRegister();
					RecipeMaps.MAGIC_FUELS.factory().EUt(24).duration(10).input(new ItemStack(tItem, 1, 4)).buildAndRegister();
					RecipeMaps.MAGIC_FUELS.factory().EUt(24).duration(10).input(new ItemStack(tItem, 1, 5)).buildAndRegister();
					RecipeMaps.MAGIC_FUELS.factory().EUt(24).duration(24).input(new ItemStack(tItem, 1, 6)).buildAndRegister();
				} else if (tName.equals("item.ligniteCoal")) {
					GT_OreDictUnificator.set(OrePrefixes.gem, Materials.Lignite, new ItemStack(tItem, 1, 0));
				} else if (tName.equals("item.charmOfLife1")) {
					tCharmL = new ItemStack(tItem, 1, 0);
				} else if (tName.equals("item.charmOfLife2")) {
					tCharmLL = new ItemStack(tItem, 1, 0);
				} else if (tName.equals("item.charmOfKeeping1")) {
					tCharmI = new ItemStack(tItem, 1, 0);
				} else if (tName.equals("item.charmOfKeeping2")) {
					tCharmII = new ItemStack(tItem, 1, 0);
				} else if (tName.equals("item.charmOfKeeping3")) {
					tCharmIII = new ItemStack(tItem, 1, 0);
				} else if (tName.equals("tile.railcraft.brick.quarried")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 6));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 7));
				} else if (tName.equals("tile.railcraft.brick.abyssal")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 6));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 7));
				} else if (tName.equals("tile.extrabiomes.redrock") || tName.equals("tile.bop.redRocks")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock, new ItemStack(tItem, 1, 2));
				} else if (tName.equals("tile.rpstone")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(tItem, 1, 6));
				} else if (tName.equals("tile.sedimentaryStone")) {
					GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(tItem), false);
				} else if (tName.equals("tile.igneousStone") || tName.equals("tile.igneousStoneBrick") || tName.equals("tile.igneousCobblestone")) {
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
				} else if (tName.equals("tile.metamorphicStone") || tName.equals("tile.metamorphicStoneBrick") || tName.equals("tile.metamorphicCobblestone")) {
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
				} else if (tName.equals("tile.blockCosmeticSolid")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian		, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian		, new ItemStack(tItem, 1, 1));
				} else if (tName.equals("tile.enderchest")) {
					GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingEnderChest, new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("tile.autoWorkbenchBlock")) {
					GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWorkBench, new ItemStack(tItem, 1, 0));
				} else if (tName.equals("tile.pumpBlock")) {
					GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPump, new ItemStack(tItem, 1, 0));
					if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "BCPump", false)) {
						GT_ModHandler.removeRecipeByOutput(new ItemStack(tItem, 1, 0));
					}
				} else if (tName.equals("tile.tankBlock")) {
					GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingTank, new ItemStack(tItem, 1, 0));
				} else if (tName.equals("item.minotaurAxe")) {
					GT_ModHandler.addPulverisationRecipe(new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Diamond, 1L), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), 50, false);
				} else if (tName.equals("item.bucketFuel")) {
					GT_ModHandler.addCraftingRecipe(GT_ModHandler.getRecipeOutput(null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_ModHandler.getIC2Item("electronicCircuit", 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L), new ItemStack(tItem, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1L)), new Object[] { " S ", "SCS", "SLS", 'S', OrePrefixes.plate.get(Materials.Steel), 'C', OrePrefixes.circuit.get(Materials.Basic), 'L', OrePrefixes.cell.get(Materials.Lithium) });
				} else if (tName.equals("item.drawplateDiamond")) {
					GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolDrawplate, new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				} else if (tName.equals("item.canLava") || tName.equals("item.refractoryLava")) {
					RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(5000).input(new ItemStack(tItem, 8)).outputs(GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Electrum, 4L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 2L), GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1L)).buildAndRegister();
				}
			}
		}
			if (tCharmL != null)
				RecipeMaps.ASSEMBLING.factory().EUt(2).duration(800).input(GT_Utility.copyAmount(4L, tCharmL)).output(GT_Utility.copyAmount(1L, tCharmLL)).buildAndRegister();
			if (tCharmI != null)
				RecipeMaps.ASSEMBLING.factory().EUt(2).duration(800).input(GT_Utility.copyAmount(4L, tCharmI)).output(GT_Utility.copyAmount(1L, tCharmII)).buildAndRegister();
			if (tCharmII != null)
				RecipeMaps.ASSEMBLING.factory().EUt(2).duration(800).input(GT_Utility.copyAmount(4L, tCharmII)).output(GT_Utility.copyAmount(1L, tCharmIII)).buildAndRegister();
		}
	}