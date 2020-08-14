package gregtechmod.loaders.load;

import java.util.Iterator;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Cell;
import gregtechmod.common.items.GT_MetaItem_Material;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import powercrystals.minefactoryreloaded.api.IMFRHammer;

public class GT_ItemIterator implements Runnable {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void run() {
		Item tItem;
		String tName;
        ItemStack tCharmL = null, tCharmLL = null, tCharmI = null, tCharmII = null, tCharmIII = null, tStack, tStack2, tStack3;
        
        GT_Log.log.info("GT_Mod: Scanning for certain kinds of compatible Machineblocks.");
		if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2 = GT_OreDictUnificator.get("ingotBronze", 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
			GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustBronze" , 8), null, 0, false);
			GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotBronze", 8));
		}
		if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2 = GT_OreDictUnificator.get("plateBronze", 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
			GT_OreDictUnificator.registerOre("craftingRawMachineTier00", tStack);
			GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustBronze" , 8), null, 0, false);
			GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotBronze", 8));
		}
		if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2 = GT_OreDictUnificator.get("ingotIron", 1), tStack3 = new ItemStack(Blocks.glass, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get("ingotGold", 1), tStack3, tStack2, tStack3, tStack2}))) {
			GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustIron", 4), GT_OreDictUnificator.get("dustGold", 1), 0, false);
		}
		if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2 = GT_OreDictUnificator.get("ingotSteel", 1), tStack3 = new ItemStack(Blocks.glass, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get("ingotGold", 1), tStack3, tStack2, tStack3, tStack2}))) {
			GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustSteel", 4), GT_OreDictUnificator.get("dustGold", 1), 0, false);
		}
		
		GT_Log.log.info("GT_Mod: Registering various Tools to be usable on GregTech Machines");
		GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack[] {null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.stick, 1)}));
		GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Items.iron_ingot, 1), null, null, null, new ItemStack(Items.stick, 1)}));
		
        GT_Log.log.info("GT_Mod: Adding Food Recipes to the Automatic Canning Machine. (also during the following Item Iteration)");
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.rotten_flesh, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 2), GT_ModHandler.getIC2Item("filledTinCan", 2, 1), null, 200, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.spider_eye, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 1), GT_ModHandler.getIC2Item("filledTinCan", 1, 1), null, 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.poisonous_potato, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 1), GT_ModHandler.getIC2Item("filledTinCan", 1, 1), null, 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.cake, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 6), GT_ModHandler.getIC2Item("filledTinCan", 6, 0), null, 600, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Blocks.cake, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 6), GT_ModHandler.getIC2Item("filledTinCan", 6, 0), null, 600, 1);
		GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.mushroom_stew, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 3), GT_ModHandler.getIC2Item("filledTinCan", 3, 0), new ItemStack(Items.bowl, 1), 300, 1);
		
		GT_Log.log.info("GT_Mod: Scanning ItemList.");
		Iterator<Item> iter = Item.itemRegistry.iterator();
		while (iter.hasNext()) {
			if ((tItem = iter.next()) != null && (tName = tItem.getUnlocalizedName()) != null) {
				try {
					if (tItem instanceof mods.railcraft.api.core.items.IToolCrowbar) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "infiniteDurabilityRCCrowbars", false)) {
								if (GT_ModHandler.removeRecipe(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)))
						        	GT_Log.log.info("GT_Mod: Removed infinite RC Crowbar: " + tName);
							}
						} else {
							if (GregTech_API.registerCrowbar(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)))
								GT_Log.log.info("GT_Mod: Registered valid RC Crowbar: " + tName);
						}
					}
				} catch (Throwable e) {}
				try {
					if (tItem instanceof IMFRHammer) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "infiniteDurabilityMFRHammers", false)) {
								if (GT_ModHandler.removeRecipe(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)))
						        	GT_Log.log.info("GT_Mod: Removed infinite MFR Hammer: " + tName);
							}
						} else {
							if (GregTech_API.registerCrowbar(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)))
								GT_Log.log.info("GT_Mod: Registered valid MFR Hammer: " + tName);
						}
					}
				} catch (Throwable e) {}
				try {
					if (tItem instanceof buildcraft.api.tools.IToolWrench) {
						if (!tItem.isDamageable() && !GT_ModHandler.isElectricItem(new ItemStack(tItem, 1, 0))) {
							if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "infiniteDurabilityBCWrenches", false)) {
								if (GT_ModHandler.removeRecipe(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)))
						        	GT_Log.log.info("GT_Mod: Removed infinite BC Wrench: " + tName);
							}
						} else {
							if (GregTech_API.registerWrench(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)))
								GT_Log.log.info("GT_Mod: Registered valid BC Wrench: " + tName);
						}
					}
				} catch (Throwable e) {}
				if (tItem instanceof ItemBlock) {
					try {
						Block tBlock = Block.getBlockFromItem(((ItemBlock)tItem));
						if (tBlock != null) {
							if (GT_Mod.sBlockStackSize < tItem.getItemStackLimit()) {
								try {
									if (tBlock.isReplaceableOreGen(GregTech_API.sDummyWorld, 0, 0, 0, Blocks.stone)
									||  tBlock.isReplaceableOreGen(GregTech_API.sDummyWorld, 0, 0, 0, Blocks.netherrack)
									||  tBlock.isReplaceableOreGen(GregTech_API.sDummyWorld, 0, 0, 0, Blocks.end_stone)) {
										tItem.setMaxStackSize(GT_Mod.sBlockStackSize);
									}
								} catch(Throwable e) {
									GT_Log.log.catching(e);
								}
							}
						}
					} catch (IndexOutOfBoundsException e) {
						System.err.println("ERROR! A Mod attempted to return an invalid Block ID using ItemBlock! Please mention this to the respective Mod Author, who owns the following Item.");
						System.err.println("ITEM: " + tItem + " - " + tItem.getItemStackDisplayName(new ItemStack(tItem)));
						System.err.println("");
						e.printStackTrace();
						System.err.println("");
						if (!GregTech_API.DEBUG_MODE) throw new IndexOutOfBoundsException();
					}
				}
				
				if (tItem instanceof ItemFood && tItem != GT_ModHandler.getIC2Item("filledTinCan", 1, 0).getItem()) {
			        int tFoodValue = (int)Math.ceil(((ItemFood)tItem).func_150905_g(new ItemStack(tItem)) / 2.0D);
			        if (tFoodValue > 0) {
			        	GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", tFoodValue), GT_ModHandler.getIC2Item("filledTinCan", tFoodValue, 0), tItem.hasContainerItem()?new ItemStack(tItem.getContainerItem(), 1, 0):null, tFoodValue*100, 1);
			        }
			    }
			    if (tName.equals("tile.ArsMagica:ore_vinteum")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.ore, Materials.Vinteum, new ItemStack(tItem, 1, 0));
			    }
			    if (tName.equals("item.ArsMagica:vinteum_dust")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.dust, Materials.Vinteum, new ItemStack(tItem, 1, 0));
			    }
			    if (tName.equals("item.ArsMagica:purified_vinteum")) {
			    	GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), null, 256, 5);
			    }
			    if (tName.equals("item.meefSteak") || tName.equals("item.venisonCooked")) {
			    	GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 16, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
			    }
			    if (tName.equals("item.meefRaw") || tName.equals("item.venisonRaw")) {
			    	GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 12, 0), 1, GT_MetaItem_Cell.instance.getStack(9, 1), null, null, null, 5000);
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
			    }
			    if (tName.equals("item.ironwoodIngot")) {
			    	GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get("ingotIron", 1),  8, 5);
			    	GT_OreDictUnificator.registerOre(OrePrefixes.ingot, Materials.IronWood, new ItemStack(tItem, 1, 0));
			    }
			    if (tName.equals("item.steeleafIngot")) {
			    	GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get("dustSteel", 1), 24, 5);
			    	GT_OreDictUnificator.registerOre(OrePrefixes.ingot, Materials.SteelLeaf, new ItemStack(tItem, 1, 0));
				}
			    if (tName.equals("item.fieryIngot")) {
			    	GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get("ingotSteel", 1), 2048, 5);
			    	GT_OreDictUnificator.registerOre(OrePrefixes.ingot, Materials.FieryBlood, new ItemStack(tItem, 1, 0));
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
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1,  7), new ItemStack(tItem, 1, 0),  80, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 10), new ItemStack(tItem, 1, 0), 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 41), new ItemStack(tItem, 1, 0), 120, 5);
				}
				if (tName.equals("item.ItemWispEssence")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1,  7), new ItemStack(tItem, 1, 0),  80, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 10), new ItemStack(tItem, 1, 0), 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 41), new ItemStack(tItem, 1, 0), 120, 5);
				}
				if (tName.equals("item.ItemResource")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 3), null, 16, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 4), null,  4, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 6), null,  6, 5);
					GT_OreDictUnificator.registerOre(OrePrefixes.ingot, Materials.Thaumium, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.gem.get(Materials.QuickSilver), new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre("paperResearchFragment", new ItemStack(tItem, 1, 9));
				}
				if (tName.equals("item.ItemShard")) {
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 0), new ItemStack(tItem, 1, 5), 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 1), new ItemStack(tItem, 1, 5), 320, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 2), new ItemStack(tItem, 1, 5), 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 3), new ItemStack(tItem, 1, 5), 160, 5);
					GregTech_API.sRecipeAdder.addFuel(new ItemStack(tItem, 1, 4), new ItemStack(tItem, 1, 5), 240, 5);
				}
				if (tName.equals("item.ItemEnderDust")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.dust, Materials.EnderPearl, new ItemStack(tItem, 1, 0));
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
				if (tName.equals("tile.extrabiomes.redrock") || tName.equals("tile.bop.redRocks")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock		, new ItemStack(tItem, 1,  0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock		, new ItemStack(tItem, 1,  1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Redrock		, new ItemStack(tItem, 1,  2));
				}
				if (tName.equals("tile.rpstone")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble		, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt		, new ItemStack(tItem, 1, 1));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble		, new ItemStack(tItem, 1, 2));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt		, new ItemStack(tItem, 1, 3));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt		, new ItemStack(tItem, 1, 4));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt		, new ItemStack(tItem, 1, 5));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt		, new ItemStack(tItem, 1, 6));
				}
				if (tName.equals("tile.sedimentaryStone")) {
					
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
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Rhyolite		, new ItemStack(tItem, 1,10));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Andesite		, new ItemStack(tItem, 1,11));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Gabbro		, new ItemStack(tItem, 1,12));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt		, new ItemStack(tItem, 1,13));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Komatiite		, new ItemStack(tItem, 1,14));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Dacite		, new ItemStack(tItem, 1,15));
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
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble		, new ItemStack(tItem, 1,10));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Quartzite		, new ItemStack(tItem, 1,11));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Blueschist	, new ItemStack(tItem, 1,12));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Greenschist	, new ItemStack(tItem, 1,13));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Soapstone		, new ItemStack(tItem, 1,14));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Migmatite		, new ItemStack(tItem, 1,15));
				}
				
				if (tName.equals("tile.blockCosmeticSolid")) {
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian		, new ItemStack(tItem, 1, 0));
					GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Obsidian		, new ItemStack(tItem, 1, 1));
				}
				if (tName.equals("tile.enderchest")) {
					GT_OreDictUnificator.registerOre("craftingEnderChest", new ItemStack(tItem, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
				}
				if (tName.equals("tile.autoWorkbenchBlock")) {
					GT_OreDictUnificator.registerOre("craftingWorkBench", new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("tile.pumpBlock")) {
					GT_OreDictUnificator.registerOre("craftingPump", new ItemStack(tItem, 1, 0));
					if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "BCPump", false)) {
						GT_ModHandler.removeRecipe(new ItemStack(tItem, 1, 0));
					}
				}
				if (tName.equals("tile.tankBlock")) {
					GT_OreDictUnificator.registerOre("craftingTank", new ItemStack(tItem, 1, 0));
				}
				if (tName.equals("item.minotaurAxe")) {
					GT_ModHandler.addPulverisationRecipe(new ItemStack(tItem, 1, 0), GT_OreDictUnificator.get("dustDiamond", 1), GT_OreDictUnificator.get("dustWood", 1), 50, false);
				}
				
				if (tName.equals("item.bucketFuel")) {
					GT_ModHandler.mBCFuelBucket = new ItemStack(tItem, 1);
					GT_ModHandler.addCraftingRecipe(GT_ModHandler.getRecipeOutput(new ItemStack[] {null, GT_OreDictUnificator.get("ingotSteel", 1), null, GT_OreDictUnificator.get("ingotSteel", 1), GT_ModHandler.getIC2Item("electronicCircuit", 1), GT_OreDictUnificator.get("ingotSteel", 1), GT_OreDictUnificator.get("ingotSteel", 1), new ItemStack(tItem, 1), GT_OreDictUnificator.get("ingotSteel", 1)}), new Object[] {" S ", "SCS", "SLS", 'S', "plateSteel", 'C', "craftingCircuitTier02", 'L', "molecule_1li"});
				}
				
				if (tName.equals("item.drawplateDiamond"))	GT_ModHandler.mDiamondDrawplate	= new ItemStack(tItem, 1);
				
				if (tName.equals("item.woodenGearItem"))	GT_ModHandler.mBCWoodGear		= new ItemStack(tItem, 1);
				if (tName.equals("item.stoneGearItem"))		GT_ModHandler.mBCStoneGear		= new ItemStack(tItem, 1);
				if (tName.equals("item.ironGearItem"))		GT_ModHandler.mBCIronGear		= new ItemStack(tItem, 1);
				if (tName.equals("item.goldGearItem"))		GT_ModHandler.mBCGoldGear		= new ItemStack(tItem, 1);
				if (tName.equals("item.diamondGearItem"))	GT_ModHandler.mBCDiamondGear	= new ItemStack(tItem, 1);
				
				if (tName.equals("item.canLava"))			GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 16), 0, GT_OreDictUnificator.get("ingotElectrum", 1), GT_OreDictUnificator.get("ingotCopper", 4), GT_OreDictUnificator.get("dustSmallTungsten", 1), GT_OreDictUnificator.get("ingotTin", 6), 15000);
				if (tName.equals("item.refractoryLava"))	GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(tItem, 16), 0, GT_OreDictUnificator.get("ingotElectrum", 1), GT_OreDictUnificator.get("ingotCopper", 4), GT_OreDictUnificator.get("dustSmallTungsten", 1), GT_OreDictUnificator.get("ingotTin", 2), 10000);
			}
		}
		
        if (tCharmL  != null) GregTech_API.sRecipeAdder.addAssemblerRecipe(tCharmL .splitStack(4), null, tCharmLL .splitStack(1), 800, 2);
    	if (tCharmI  != null) GregTech_API.sRecipeAdder.addAssemblerRecipe(tCharmI .splitStack(4), null, tCharmII .splitStack(1), 800, 2);
    	if (tCharmII != null) GregTech_API.sRecipeAdder.addAssemblerRecipe(tCharmII.splitStack(4), null, tCharmIII.splitStack(1), 800, 2);
    	
        GT_Log.log.info("GT_Mod: Getting Storage Blocks of Redpower for the OreDictUnification.");
        if (GT_ModHandler.mNikolite != null) {
        	GT_OreDictUnificator.set("dustNikolite", GT_ModHandler.mNikolite, GT_Mod.sUnificatorRP);
        	tStack2 = GT_ModHandler.mRuby;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(32, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockRuby", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mSapphire;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(33, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockSapphire", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mGreenSapphire;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(34, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockGreenSapphire", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mSilver;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(17, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockSilver", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mCopper;
        	tStack3 = GT_ModHandler.getIC2Item("copperIngot", 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockCopper", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mTin;
        	tStack3 = GT_ModHandler.getIC2Item("tinIngot", 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockTin", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mBrass;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(25, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockBrass", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        }
	}
}