package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.MaterialStack;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.items.GT_MetaItem_Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_OreDictHandler {
	public static final GT_OreDictHandler instance = new GT_OreDictHandler();
	
	private final List<net.minecraftforge.oredict.OreDictionary.OreRegisterEvent> mEvents = new ArrayList<net.minecraftforge.oredict.OreDictionary.OreRegisterEvent>();
	private final List<String> mIgnoredNames = Arrays.asList("meteoriteCoal", "hambone", "slimeball", "record", "blockCobble", "itemBacon", "itemJetpackAccelerator", "itemLazurite", "itemIridium", "itemTear", "itemClaw", "itemFertilizer", "itemTar", "itemSlimeball", "itemCoke", "itemBeeswax", "itemBeeQueen", "itemForcicium", "itemForcillium", "itemRoyalJelly", "itemHoneydew", "itemHoney", "itemPollen", "itemReedTypha", "itemSulfuricAcid", "itemPotash", "itemCompressedCarbon", "compressedCarbon", "itemBitumen", "itemBioFuel", "itemCokeSugar", "itemCokeCactus", "itemCharcoalSugar", "itemCharcoalCactus", "itemSludge", "itemEnrichedAlloy", "itemQuicksilver", "itemMercury", "itemOsmium", "itemUltimateCircuit", "itemEnergizedStar", "itemAntimatterMolecule", "itemAntimatterGlob", "itemCoal", "pressOreProcessor", "crusherOreProcessor", "grinderOreProcessor", "itemBoat", "blockRubber", "blockHoney", "blockHoneydew", "blockPeat", "blockRadioactive", "blockSlime", "blockCocoa", "blockSugarCane", "blockLeather", "blockClayBrick", "solarPanelHV", "itemHerbalMedicineCake", "itemCakeSponge", "itemFishandPumpkinCakeSponge", "itemSoulCleaver", "itemInstantCake", "itemWhippingCream", "itemGlisteningWhippingCream", "itemCleaver", "itemHerbalMedicineWhippingCream", "itemStrangeWhippingCream", "itemBlazeCleaver", "itemBakedCakeSponge", "itemMagmaCake", "itemGlisteningCake", "itemOgreCleaver", "itemFishandPumpkinCake", "itemMagmaWhippingCream", "universalCable", "cableRedNet", "stoneBowl", "crafterWood", "taintedSoil", "oreNaturalAluminum", "oreNaturalAluminium", "ganysNetherGlowBox", "brickXyEngineering", "breederUranium", "infiniteBattery", "eliteBattery", "advancedBattery", "wireMill", "itemMultimeter", "chunkLazurite", "itemRecord", "aluminumNatural", "aluminiumNatural", "naturalAluminum", "naturalAluminium", "antimatterMilligram", "antimatterGram", "strangeMatter", "batteryBox", "coalGenerator", "electricFurnace", "bronzeTube", "ironTube", "netherTube", "obbyTube", "unfinishedTank", "valvePart", "aquaRegia", "leatherSeal", "leatherSlimeSeal", "enrichedUranium", "batteryInfinite", "itemSuperconductor", "camoPaste", "CAMO_PASTE");
	private final List<String> mValidPrefixes = Arrays.asList("cell", "crafting", "ore", "oreNether", "oreEnd", "netherOre", "endOre", "stone", "pulp", "dust", "dustSmall", "nugget", "ingot", "gem", "log", "tree", "flower", "item", "wax", "brick", "plank", "sand", "glass", "dye", "slab", "stair", "stick", "clump", "paper", "book", "seed", "material", "storage", "tool", "crop", "list", "lumar", "plasma_", "molecule_");
	private final List<String> mIgnoredPrefixes = Arrays.asList("mffs", "projred", "ganys");
	private final List<String> mInvalidNames = Arrays.asList("10kEUStore", "blueDye", "MonazitOre", "quartzCrystal", "whiteLuminiteCrystal", "darkStoneIngot", "invisiumIngot", "demoniteOrb", "enderGem", "starconiumGem", "osmoniumIngot", "tapaziteGem", "zectiumIngot", "foolsRubyGem", "rubyGem", "meteoriteGem", "adamiteShard", "sapphireGem", "copperIngot", "ironStick", "goldStick", "diamondStick", "reinforcedStick", "draconicStick", "emeraldStick", "copperStick", "tinStick", "silverStick", "bronzeStick", "steelStick", "leadStick", "manyullynStick", "arditeStick", "cobaltStick", "aluminiumStick", "alumiteStick", "oilsandsOre", "superconductorWire", "sulfuricAcid", "conveyorBelt", "ironWire", "aluminumWire", "aluminiumWire", "silverWire", "tinWire", "dustSiliconSmall", "HSLA", "itemAntimatterTinyPile", "itemOsmiridiumAlloy", "itemOsmiridiumPlate", "AluminumOre", "plateHeavyT2", "blockWool", "alloyPlateEnergizedHardened", "gasWood", "alloyPlateEnergized", "SilverOre", "LeadOre", "TinOre", "CopperOre", "silverOre", "leadOre", "tinOre", "copperOre", "bauxiteOre", "HSLivingmetalIngot", "oilMoving", "oilStill", "oilBucket", "petroleumOre", "dieselFuel", "diamondNugget", "planks", "wood", "stick", "sticks", "naquadah", "obsidianRod", "stoneRod", "thaumiumRod", "steelRod", "netherrackRod", "woodRod", "ironRod", "cactusRod", "flintRod", "copperRod", "cobaltRod", "alumiteRod", "blueslimeRod", "arditeRod", "manyullynRod", "bronzeRod", "boneRod", "slimeRod");
	
	public final List<MaterialStack[]> mAlloySmelterList = new ArrayList<MaterialStack[]>(Arrays.asList(
		new MaterialStack[] {new MaterialStack(Materials.Copper		, 3), new MaterialStack(Materials.Tin		, 1), new MaterialStack(Materials.Bronze			, 2)},
		new MaterialStack[] {new MaterialStack(Materials.Copper		, 3), new MaterialStack(Materials.Zinc		, 1), new MaterialStack(Materials.Brass				, 4)},
		new MaterialStack[] {new MaterialStack(Materials.Copper		, 1), new MaterialStack(Materials.Nickel	, 1), new MaterialStack(Materials.Cupronickel		, 2)},
		new MaterialStack[] {new MaterialStack(Materials.Iron		, 2), new MaterialStack(Materials.Nickel	, 1), new MaterialStack(Materials.Invar				, 3)},
		new MaterialStack[] {new MaterialStack(Materials.Chrome		, 1), new MaterialStack(Materials.Nickel	, 4), new MaterialStack(Materials.Nichrome			, 5)},
		new MaterialStack[] {new MaterialStack(Materials.Tin		, 9), new MaterialStack(Materials.Antimony	, 1), new MaterialStack(Materials.SolderingAlloy	,10)},
		new MaterialStack[] {new MaterialStack(Materials.Lead		, 4), new MaterialStack(Materials.Antimony	, 1), new MaterialStack(Materials.BatteryAlloy		, 5)},
		new MaterialStack[] {new MaterialStack(Materials.Gold		, 1), new MaterialStack(Materials.Silver	, 1), new MaterialStack(Materials.Electrum			, 2)},
		new MaterialStack[] {new MaterialStack(Materials.Magnesium	, 1), new MaterialStack(Materials.Aluminium	, 2), new MaterialStack(Materials.Magnalium			, 3)}
	));
	
	private boolean mActivated = false;
	
	@net.minecraftforge.event.ForgeSubscribe
    public void registerOre(net.minecraftforge.oredict.OreDictionary.OreRegisterEvent aEvent) {
		if (GT_Mod.mDoNotInit || aEvent == null || aEvent.Ore == null || aEvent.Ore.getItem() == null || aEvent.Name == null || aEvent.Name.equals("") || mIgnoredNames.contains(aEvent.Name)) return;
		
		try {
		
		if (aEvent.Ore.stackSize != 1) {
			System.err.println("\nWARNING: '" + aEvent.Name + "' is either being misused by another Mod or has been wrongly registered, as the stackSize of the Event-Stack is not 1!!!");
		}
		
		aEvent.Ore.stackSize = 1;
		
		cpw.mods.fml.common.ModContainer tContainer = cpw.mods.fml.common.Loader.instance().activeModContainer();
    	String aMod = tContainer==null?"UNKNOWN_MOD_ID":tContainer.getModId();
    	
    	if (aMod.toLowerCase().contains("tconstruct")) return;
    	
    	if (aMod.equalsIgnoreCase(GregTech_API.MOD_ID)) aMod = "UNKNOWN_MOD_ID";
    	
		if (aEvent.Name.equals("stone"))		{GT_OreDictUnificator.registerOre("stoneSmooth", aEvent.Ore); return;}
		if (aEvent.Name.equals("cobblestone"))	{GT_OreDictUnificator.registerOre("stoneCobble", aEvent.Ore); return;}
    	
		if (mActivated) {
			System.err.println("\nWARNING: " + aMod + " attempted to register " + aEvent.Name + " very late at the OreDictionary! Some Functionality may not work as expected! Sometimes registration in Postload is required, but you should always register OreDictionary Items in the Load Phase whenever possible.");
		}
		
		if (aEvent.Name.contains(":") || aEvent.Name.contains(".") || aEvent.Name.contains("$")) return;
    	for (String tString : mIgnoredPrefixes) if (aEvent.Name.startsWith(tString)) return;
    	
		if (aEvent.Name.toLowerCase().contains("xych") || aEvent.Name.toLowerCase().contains("xyore")) {
			GT_ModHandler.addToRecyclerBlackList(aEvent.Ore);
			return;
		}
		
		if (aEvent.Name.equals("copperWire"))			{GT_OreDictUnificator.registerOre("craftingWireCopper"		, aEvent.Ore);}
    	if (aEvent.Name.equals("itemCopperWire"))		{GT_OreDictUnificator.registerOre("craftingWireCopper"		, aEvent.Ore); return;}
    	
		if (mInvalidNames.contains(aEvent.Name)) {
			System.err.println("\nWARNING: '" + aEvent.Name + "' is an invalid OreDictionary Name. The Name doesn't fit to the Type of Item and/or doesn't follow a proper OreDictionary Convention. If you are the Owner of the Mod who adds this Item, please do the following: ");
			if (aEvent.Name.equals("oilsandsOre")) {
				System.err.println("Please change it to 'sandOil'");
				GT_OreDictUnificator.registerOre("sandOil", aEvent.Ore);
			} else if (aEvent.Name.equals("10kEUStore")) {
				System.err.println("Use 'crafting10kEUStore', you forgot to add the prefix");
			} else if (aEvent.Name.equals("sulfuricAcid")) {
				System.err.println("Please use 'bottleSulfuricAcid' instead, since it is likely a vanilla bottle containing the Material 'SulfuricAcid'");
			} else if (aEvent.Name.equals("stick")) {
				System.err.println("Use 'stickWood' instead, it is already registered in vanilla-forge");
			} else if (aEvent.Name.equals("wood")) {
				System.err.println("Use 'logWood' instead, it is already registered in vanilla-forge");
			} else if (aEvent.Name.equals("plank")) {
				System.err.println("Use 'plankWood' instead, it is already registered in vanilla-forge");
			} else if (aEvent.Name.endsWith("Ingot")) {
				System.err.println("Put the 'Ingot' in the beginning of the Name to get 'ingot" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Ingot", "")) + "'");
			} else if (aEvent.Name.endsWith("Crystal")) {
				System.err.println("Put the 'Crystal' in the beginning of the Name to get 'crystal" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Crystal", "")) + "'");
			} else if (aEvent.Name.endsWith("Shard")) {
				System.err.println("Put the 'Shard' in the beginning of the Name to get 'shard" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Shard", "")) + "'");
			} else if (aEvent.Name.endsWith("Rod")) {
				System.err.println("Put the 'Rod' in the beginning of the Name to get 'rod" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Rod", "")) + "'");
			} else if (aEvent.Name.endsWith("Orb")) {
				System.err.println("Put the 'Orb' in the beginning of the Name to get 'orb" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Irb", "")) + "'");
			} else if (aEvent.Name.endsWith("Gem")) {
				System.err.println("Put the 'Gem' in the beginning of the Name to get 'gem" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Gem", "")) + "'");
			} else if (aEvent.Name.endsWith("Stick")) {
				System.err.println("Put the 'Stick' in the beginning of the Name to get 'stick" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Stick", "")) + "'");
			} else if (aEvent.Name.endsWith("Ore")) {
				System.err.println("Put the 'Ore' in the beginning of the Name to get 'ore" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Ore", "")) + "'");
			} else if (aEvent.Name.endsWith("Dye")) {
				System.err.println("Put the 'Dye' in the beginning of the Name to get 'dye" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Dye", "")) + "'");
			} else if (aEvent.Name.endsWith("Wire")) {
				System.err.println("Put the 'Wire' in the beginning of the Name to get 'wire" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Wire", "")) + "'");
			} else if (aEvent.Name.endsWith("Nugget")) {
				System.err.println("Put the 'Nugget' in the beginning of the Name to get 'nugget" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Nugget", "")) + "'");
			} else {
				System.err.println("I don't know exactly what to suggest about this Name, please consult me personally at GregTech.");
			}
			System.err.println("Private Prefixes could also be a solution if the first Suggestion doesn't apply. In that case the suggestion for the name is '" + aMod + ":" + aEvent.Name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
			System.err.println("If you are not the Owner then report it to the Owner of the Mod, which the Item belongs to.");
			return;
		}
		
		if (aEvent.Name.contains(" ")) {
			System.err.println("\nWARNING: '" + aEvent.Name + "' is an invalid OreDictionary Name, as it contains spaces! Register it without spaces to fix that.");
			GT_OreDictUnificator.registerOre(aEvent.Name.replaceAll(" ", ""), GT_Utility.copy(aEvent.Ore));
			aEvent.Ore.setItemName("Invalid OreDictionary Tag");
			return;
		}
		
		OrePrefixes aPrefix = OrePrefixes.getPrefix(aEvent.Name);
		
		if (aPrefix == null) {
	    	if (aEvent.Name.toLowerCase().equals(aEvent.Name)) {
	    		System.err.println("Improperly registered Ore: " + aEvent.Name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, as it is lowercased!!! Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be '" + aMod + ":" + aEvent.Name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
	    		return;
	    	}
	    	if (GT_Utility.sUpperCasedCharacters.contains(aEvent.Name.charAt(0))) {
	    		System.err.println("Improperly registered Ore: " + aEvent.Name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, because it starts with an uppercased Letter. Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be '" + aMod + ":" + aEvent.Name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
	    		return;
	    	}
		} else {
			String tName = aEvent.Name.replaceFirst(aPrefix.toString(), "");
			if (tName.length() > 0 && (GT_Utility.sUpperCasedCharacters.contains(tName.charAt(0)) || GT_Utility.sNumberedCharacters.contains(tName.charAt(0)) || tName.charAt(0) == '_')) {
				aPrefix.add(GT_Utility.copy(aEvent.Ore));
				if (aPrefix.mIsMaterialBased) {
					Materials aMaterial = Materials.get(tName);
					if (aMaterial != aMaterial.mMaterialInto) {
						GT_OreDictUnificator.registerOre(aPrefix, aMaterial.mMaterialInto, aEvent.Ore);
						return;
					}
					if (aPrefix.mMaterialAmount < GregTech_API.MATERIAL_UNIT) {
						GT_ModHandler.addToRecyclerBlackList(GT_Utility.copy(1, GT_Utility.copy(aEvent.Ore)));
					}
			    	if (aMaterial != Materials._NULL) {
			    	    aMaterial.add(GT_Utility.copy(aEvent.Ore));
			    		if (aPrefix.mIsUnificatable) {
			    			if (mActivated) GT_OreDictUnificator.add(aEvent.Name, aEvent.Ore); else GT_OreDictUnificator.addAssociation(aEvent.Name, aEvent.Ore);
						   	if (aPrefix == OrePrefixes.stick) {
						   		if (!GT_RecipeRegistrator.sRodMaterialList.contains(aMaterial)) GT_RecipeRegistrator.sRodMaterialList.add(aMaterial);
							} else if (aPrefix == OrePrefixes.ore) {
								aMaterial.add(GT_ModHandler.getIC2Item("crushed" + aMaterial + "Ore", 1));
								aMaterial.add(GT_ModHandler.getIC2Item("purifiedCrushed" + aMaterial + "Ore", 1));
						   	} else if (aPrefix == OrePrefixes.oreNether) {
						   		if (aMaterial == Materials.Quartz) {
						   			aMaterial.add(GT_ModHandler.getIC2Item("crushed" + Materials.NetherQuartz + "Ore", 1));
						   			aMaterial.add(GT_ModHandler.getIC2Item("purifiedCrushed" + Materials.NetherQuartz + "Ore", 1));
						   		}
						   	}
			    	    }
			    		/**------------------------------------------------------------------------*/
			    		try {
			    			switch (aPrefix) {
			    			case gem:
			    				if (aMaterial.toString().contains("Quartz")) GT_OreDictUnificator.registerOre("craftingQuartz", aEvent.Ore);
					    		break;
			    			case lense:
			    				if (aMaterial.mTransparent && aMaterial.mColor != Dyes._NULL) GT_OreDictUnificator.registerOre("craftingLense" + aMaterial.mColor.toString().replaceFirst("dye", ""), aEvent.Ore);
			    				break;
			    			case plate:
					    	    if (aMaterial == Materials.Steel || aMaterial == Materials.StainlessSteel) GT_OreDictUnificator.registerOre("craftingPlateSteel", aEvent.Ore);
					    	    break;
			    			case stick:
			    				if (aMaterial == Materials.Wood) GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
			    				break;
			    			case dust:
					    	    if (aMaterial == Materials.Sulfur) GT_OreDictUnificator.registerOre("craftingSulfurToGunpowder", aEvent.Ore);
					    	    if (aMaterial == Materials.Saltpeter) GT_OreDictUnificator.registerOre("craftingSaltpeterToGunpowder", aEvent.Ore);
					    	    if (aMaterial == Materials.Nikolite) {
					    			if (aEvent.Ore.getUnlocalizedName().equals("item.nikolite") && GT_ModHandler.mNikolite == null && aEvent.Ore.getItemDamage() == 6) {
					    	    		GT_ModHandler.mRuby = new ItemStack(aEvent.Ore.getItem(), 1, 0);
					    	    		GT_ModHandler.mGreenSapphire = new ItemStack(aEvent.Ore.getItem(), 1, 1);
					    	    		GT_ModHandler.mSapphire = new ItemStack(aEvent.Ore.getItem(), 1, 2);
					    	    		GT_ModHandler.mSilver = new ItemStack(aEvent.Ore.getItem(), 1, 3);
					    	    		GT_ModHandler.mTin = new ItemStack(aEvent.Ore.getItem(), 1, 4);
					    	    		GT_ModHandler.mCopper = new ItemStack(aEvent.Ore.getItem(), 1, 5);
					    	    		GT_ModHandler.mNikolite = new ItemStack(aEvent.Ore.getItem(), 1, 6);
					    			}
					    	    }
					    	    break;
			    			case nugget:
			    				if (aMaterial == Materials.Copper) {
					    			if (aEvent.Ore.getUnlocalizedName().equals("item.ItemNugget") && GT_ModHandler.mNuggetCopper == null && aEvent.Ore.getItemDamage() == 1) {
					    	    		GT_ModHandler.mNuggetIron = new ItemStack(aEvent.Ore.getItem(), 1, 0);
					    	    		GT_ModHandler.mNuggetCopper = new ItemStack(aEvent.Ore.getItem(), 1, 1);
					    	    		GT_ModHandler.mNuggetTin = new ItemStack(aEvent.Ore.getItem(), 1, 2);
					    	    		GT_ModHandler.mNuggetSilver = new ItemStack(aEvent.Ore.getItem(), 1, 3);
					    	    		GT_ModHandler.mNuggetLead = new ItemStack(aEvent.Ore.getItem(), 1, 4);
					    			}
					    			if (aEvent.Ore.getUnlocalizedName().equals("item.nuggetCopper") && GT_ModHandler.mCopperNugget == null && aEvent.Ore.getItemDamage() == 3) {
					    				GT_ModHandler.mIronNugget = new ItemStack(aEvent.Ore.getItem(), 1, 0);
					    	    		GT_ModHandler.mSilverNugget = new ItemStack(aEvent.Ore.getItem(), 1, 1);
					    	    		GT_ModHandler.mTinNugget = new ItemStack(aEvent.Ore.getItem(), 1, 2);
					    	    		GT_ModHandler.mCopperNugget = new ItemStack(aEvent.Ore.getItem(), 1, 3);
					    			}
			    				}
					    		break;
			    			case ingot:
			    				if (aMaterial == Materials.Brass) {
					    			if (aEvent.Ore.getUnlocalizedName().equals("item.ingotBrass") && GT_ModHandler.mBrass == null && aEvent.Ore.getItemDamage() == 2) {
					    	    		if (new ItemStack(aEvent.Ore.getItem(), 1, 0).getUnlocalizedName().contains("red")) {
					    					GT_ModHandler.mRedAlloy = new ItemStack(aEvent.Ore.getItem(), 1, 0);
					    		    		GT_ModHandler.mBlueAlloy = new ItemStack(aEvent.Ore.getItem(), 1, 1);
					    		    		GT_ModHandler.mBrass = new ItemStack(aEvent.Ore.getItem(), 1, 2);
					    		    		GT_ModHandler.mSiliconBoule = new ItemStack(aEvent.Ore.getItem(), 1, 3);
					    		    		GT_ModHandler.mSiliconWafer = new ItemStack(aEvent.Ore.getItem(), 1, 4);
					    		    		GT_ModHandler.mBlueWafer = new ItemStack(aEvent.Ore.getItem(), 1, 5);
					    		    		GT_ModHandler.mRedWafer = new ItemStack(aEvent.Ore.getItem(), 1, 6);
					    		    		GT_ModHandler.mRPTinPlate = new ItemStack(aEvent.Ore.getItem(), 1, 7);
					    		    		GT_ModHandler.mFineCopper = new ItemStack(aEvent.Ore.getItem(), 1, 8);
					    		    		GT_ModHandler.mFineIron = new ItemStack(aEvent.Ore.getItem(), 1, 9);
					    		    		GT_ModHandler.mCopperCoil = new ItemStack(aEvent.Ore.getItem(), 1, 10);
					    		    		GT_ModHandler.mBlutricMotor = new ItemStack(aEvent.Ore.getItem(), 1, 11);
					    		    		GT_ModHandler.mCanvas = new ItemStack(aEvent.Ore.getItem(), 1, 12);
					    		    		GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copy(1, GT_ModHandler.mSiliconBoule), GT_Utility.copy(16, GT_ModHandler.mSiliconWafer), 400, 8);
					    	    		}
					    	    	}
			    				}
			    				break;
			    			}
			    		} catch(Throwable e) {
			    			if (GregTech_API.DEBUG_MODE) e.printStackTrace(GT_Log.err);
			    		}
			    		/**------------------------------------------------------------------------*/
			    	} else {
			    		System.out.println("Material Name: " + aEvent.Name + " !!!Unknown Material detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
			    		return;
			    	}
				}
			} else if (!aPrefix.mIsSelfReferencing) {
				System.err.println("\nWARNING: '" + aEvent.Name + "' is an OreDictionary Name which may cause Problems, please use another one.");
				System.err.println("Private Prefixes are a solution. Please use '" + aMod + ":" + aEvent.Name + "' don't forget to insert the ':' inbetween the Mod ID and OreDict Name, that is the most important part.");
				return;
			}
			
			switch (aPrefix) {
			case stoneSmooth:
				GT_OreDictUnificator.registerOre("stone", aEvent.Ore);
				break;
			case stoneCobble:
				GT_OreDictUnificator.registerOre("cobblestone", aEvent.Ore);
				break;
			case crafting:
				if (tName.equals("RawMachineTier01"))	GT_OreDictUnificator.registerOre("craftingRawMachineTier00"		, aEvent.Ore);
				if (tName.equals("CircuitTier02"))		GT_OreDictUnificator.registerOre("calclavia:CIRCUIT_T1"			, aEvent.Ore);
				if (tName.equals("CircuitTier04"))		GT_OreDictUnificator.registerOre("calclavia:CIRCUIT_T2"			, aEvent.Ore);
				if (tName.equals("CircuitTier06"))		GT_OreDictUnificator.registerOre("calclavia:CIRCUIT_T3"			, aEvent.Ore);
				if (tName.equals("WireCopper"))			GT_OreDictUnificator.registerOre("calclavia:WIRE"				, aEvent.Ore);
				if (tName.equals("10kEUStore"))			GT_OreDictUnificator.registerOre("calclavia:BATTERY"			, aEvent.Ore);
				if (tName.equals("LiBattery"))			GT_OreDictUnificator.registerOre("calclavia:ADVANCED_BATTERY"	, aEvent.Ore);
				break;
			case circuit:
				if (tName.equals("Basic"))				GT_OreDictUnificator.registerOre("craftingCircuitTier02"		, aEvent.Ore);
				if (tName.equals("Advanced"))			GT_OreDictUnificator.registerOre("craftingCircuitTier04"		, aEvent.Ore);
				if (tName.equals("Elite"))				GT_OreDictUnificator.registerOre("craftingCircuitTier06"		, aEvent.Ore);
				break;
			case crystal:
				if (tName.equals("CertusQuartz"))		GT_OreDictUnificator.registerOre("gemCertusQuartz"				, aEvent.Ore);
		    	break;
			case pulp:
				if (tName.equals("Wood"))				GT_OreDictUnificator.registerOre("dustWood"						, aEvent.Ore);
		    	break;
			case wood:
				if (tName.equals("Rubber"))				GT_OreDictUnificator.registerOre("logRubber"					, aEvent.Ore);
				break;
			}
			
			if (aPrefix != aPrefix.mPrefixInto) {
				String tNewName = aEvent.Name.replaceFirst(aPrefix.toString(), aPrefix.mPrefixInto.toString());
				GT_OreDictUnificator.registerOre(tNewName, aEvent.Ore);
				if (!GT_OreDictUnificator.isRegisteringOres()) System.out.println("Auto-Re-Registered Ore from '" + aEvent.Name + "' to '" + tNewName + "', because of the depricated Prefix.");
				return;
			}
		}
    	
		if (mActivated) {
			registerRecipes(aEvent);
		} else {
			mEvents.add(aEvent);
		}
		
		} catch(Throwable e) {
			e.printStackTrace(GT_Log.err);
		}
    }
	
	/**
	 * Gets called during the PreLoad-Phase
	 */
    public void registerHandler() {
    	net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);
        for (String tOreName : net.minecraftforge.oredict.OreDictionary.getOreNames()) for (ItemStack tOreStack : net.minecraftforge.oredict.OreDictionary.getOres(tOreName)) registerOre(new net.minecraftforge.oredict.OreDictionary.OreRegisterEvent(tOreName, tOreStack));
	}
    
	/**
	 * Gets called during the PostLoad-Phase
	 */
    public void activateHandler() {
    	mActivated = true;
		for (net.minecraftforge.oredict.OreDictionary.OreRegisterEvent tEvent : mEvents) {
	    	OrePrefixes tPrefix = OrePrefixes.getPrefix(tEvent.Name);
			if (tPrefix != null && tPrefix.mIsUnificatable) {
				GT_OreDictUnificator.add(tEvent.Name, tEvent.Ore);
			}
		}
		for (net.minecraftforge.oredict.OreDictionary.OreRegisterEvent tEvent : mEvents) try {
			registerRecipes(tEvent);
		} catch(Throwable e) {
			e.printStackTrace(GT_Log.err);
		}
		mEvents.clear();
    }
    
    public void registerRecipes(net.minecraftforge.oredict.OreDictionary.OreRegisterEvent aEvent) {
    	if (aEvent.Ore == null || aEvent.Ore.getItem() == null) return;
    	
    	if (GregTech_API.SECONDARY_DEBUG_MODE) {
    		GT_Log.out.println("OreDictRegistration: " + aEvent.Name + " -> ID: " + aEvent.Ore.getItem() + ", Meta: " + aEvent.Ore.getItemDamage());
    	}
    	
		if (aEvent.Ore.stackSize != 1) {
			System.err.println("\nWARNING: '" + aEvent.Name + "' is either being misused by another Mod or has been wrongly registered, as the stackSize of the Event-Stack is not 1.");
		}
		
    	aEvent.Ore.stackSize = 1;
    	ArrayList<ItemStack> tList;
    	short aMeta = (short)aEvent.Ore.getItemDamage();
    	Item aItem = aEvent.Ore.getItem();
    	OrePrefixes aPrefix = OrePrefixes.getPrefix(aEvent.Name);
    	Materials aMaterial = aPrefix==null?Materials._NULL:OrePrefixes.getRealMaterial(aEvent.Name, aPrefix);
		ItemStack tStack, aStack = GT_Utility.copy(aEvent.Ore);
		
    	if (aPrefix != null) {
    		if (aPrefix.mMaterialAmount > 0 && aPrefix.mIsMaterialBased && aMaterial != Materials.Blaze) {
	    		if (!aPrefix.toString().startsWith("dust") && !aPrefix.toString().startsWith("cell")) {
	    			if ( aPrefix.mMaterialAmount      %  GregTech_API.MATERIAL_UNIT == 0) {
	    				GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.dust							, aMaterial, (int)( aPrefix.mMaterialAmount      / GregTech_API.MATERIAL_UNIT)), null, 0, true);
	    			} else
	    			if ((aPrefix.mMaterialAmount * 4) %  GregTech_API.MATERIAL_UNIT == 0) {
	    				GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.dustSmall					, aMaterial, (int)((aPrefix.mMaterialAmount * 4) / GregTech_API.MATERIAL_UNIT)), null, 0, true);
	        		} else
	        		if ((aPrefix.mMaterialAmount * 9) >= GregTech_API.MATERIAL_UNIT) {
	    				if (!GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.dustTiny				, aMaterial, (int)((aPrefix.mMaterialAmount * 9) / GregTech_API.MATERIAL_UNIT)), null, 0, true)) {
	    					if (!GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.dustSmall			, aMaterial, (int)((aPrefix.mMaterialAmount * 4) / GregTech_API.MATERIAL_UNIT)), null, 0, true)) {
	    						GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.dust					, aMaterial, (int)( aPrefix.mMaterialAmount      / GregTech_API.MATERIAL_UNIT)), null, 0, true);
	    					}
	    				}
	    			}
	    			
		    		if (aPrefix != OrePrefixes.ingot && aPrefix != OrePrefixes.nugget) {
		    			if ( aPrefix.mMaterialAmount      %  GregTech_API.MATERIAL_UNIT == 0) {
		    				GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.ingot			, aMaterial, (int)( aPrefix.mMaterialAmount      / GregTech_API.MATERIAL_UNIT)));
		    			} else
		    			if ((aPrefix.mMaterialAmount * 9) >= GregTech_API.MATERIAL_UNIT) {
		    				if (!GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.nugget	, aMaterial, (int)((aPrefix.mMaterialAmount * 9) / GregTech_API.MATERIAL_UNIT)))) {
		    					GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.ingot		, aMaterial, (int)( aPrefix.mMaterialAmount      / GregTech_API.MATERIAL_UNIT)));
		    				}
		    			}
		    		}
	    		}
    		}
    		
	    	switch (aPrefix) {
	    	case stone:
	    		switch (aMaterial) {
	    		case _NULL: break;
	    		case Sand:
	    			GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), new ItemStack(Block.sand, 1, 0), null, 10, false);
	            	break;
	    		case Endstone:
	    		    GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), 10, false);
	                break;
	    		case Netherrack:
	    		    GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), 10, false);
	                break;
	    		case Obsidian:
	    			if (aItem instanceof ItemBlock) Block.blocksList[((ItemBlock)aItem).getBlockID()].setResistance(20.0F);
	        		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("compressedCoalBall", 8), GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("coalChunk", 1), 400, 4);
	        		GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_ModHandler.getRCItem("cube.crushed.obsidian", 1, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1)), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), 10, true);
	        		break;
	    		case Redrock:
	    			GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), 10, false);
	            	break;
	    		case Marble:
	    			GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), 10, false);
	            	break;
	    		case Basalt:
	    			GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), 10, false);
	            	break;
	    		case Flint:
	    			GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 2), new ItemStack(Item.flint, 1), 50, false);
	            	break;
	    		case GraniteBlack:
	    			for (ItemStack tIteratedStack : GT_OreDictUnificator.getOres("plateAlloyAdvanced")) GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, tIteratedStack), GT_Utility.copy(8, aStack), GT_ModHandler.getIC2Item("reinforcedStone", 8), 400, 4);
	            	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Thorium, 1), 1, false);
	            	break;
	    		case GraniteRed:
	    			for (ItemStack tIteratedStack : GT_OreDictUnificator.getOres("plateAlloyAdvanced")) GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, tIteratedStack), GT_Utility.copy(8, aStack), GT_ModHandler.getIC2Item("reinforcedStone", 8), 400, 4);
	            	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Uranium, 1), 1, false);
	            	break;
	    		}
	    	case stoneCobble:
	        	if (aPrefix == OrePrefixes.stoneCobble) GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 1), new ItemStack(Block.lever, 1), 400, 1);
	    	case stoneBricks: case stoneChiseled: case stoneCracked: case stoneMossyBricks: case stoneMossy: case stoneSmooth:
	    		if (aItem instanceof ItemBlock) {
	        		if (aItem.getItemStackLimit() > GT_Mod.sBlockStackSize) aItem.setMaxStackSize(GT_Mod.sBlockStackSize);
	        		Block tBlock = Block.blocksList[((ItemBlock)aItem).getBlockID()];
	        		int tHarvestLevel = net.minecraftforge.common.MinecraftForge.getBlockHarvestLevel(tBlock, aMeta>=0||aMeta<16?aMeta:0, "pickaxe");
	        		if (tHarvestLevel <= 3) GregTech_API.sRecipeAdder.addJackHammerMinableBlock(tBlock, tHarvestLevel >= 3);
	        	}
	    		break;
	    	case cellPlasma:
	    		if (aMaterial != Materials.Empty) {
	    			GregTech_API.sRecipeAdder.addFuel(GT_Utility.copy(aStack), GT_ModHandler.getEmptyCell(1), 1024 * Math.max(1, aMaterial.getMass()), 4);
	    			GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1), Math.max(aMaterial.getMass() * 2, 1));
	    		}
	    		break;
	    	case cell:
		    	if (aMaterial != Materials.Empty) {
					if (aMaterial.mFuelPower > 0) GregTech_API.sRecipeAdder.addFuel(GT_Utility.copy(aStack), GT_ModHandler.getEmptyCell(1), aMaterial.mFuelPower, aMaterial.mFuelType);
					
					if (aMaterial.mMaterialList.size() > 0 && (aMaterial.mExtraData & 3) != 0) {
						int tAllAmount = 0;
						for (MaterialStack tMat : aMaterial.mMaterialList) tAllAmount+=tMat.mAmount;
						int tCapsuleCount = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(aStack) * -tAllAmount, tDensityMultiplier = aMaterial.getDensity() > GregTech_API.MATERIAL_UNIT ? (int)(aMaterial.getDensity() / GregTech_API.MATERIAL_UNIT) : 1;
						long tItemAmount = 0;
						tList = new ArrayList<ItemStack>();
						for (MaterialStack tMat : aMaterial.mMaterialList) if (tMat.mAmount > 0) {
							if (tMat.mMaterial == Materials.Oxygen) {
								tStack = GT_ModHandler.getAirCell((tMat.mAmount * tDensityMultiplier) / 2);
							} else {
								tStack = GT_OreDictUnificator.get(OrePrefixes.dust, tMat.mMaterial, tMat.mAmount);
								if (tStack == null)
								tStack = GT_OreDictUnificator.get(OrePrefixes.cell, tMat.mMaterial, tMat.mAmount);
							}
							if (tItemAmount +  tMat.mAmount*GregTech_API.MATERIAL_UNIT <= aStack.getMaxStackSize() * (long)aMaterial.getDensity()) {
			    				tItemAmount += tMat.mAmount*GregTech_API.MATERIAL_UNIT;
								if (tStack != null) {
									tStack.stackSize *= tDensityMultiplier;
									while (tStack.stackSize > 64 && (tCapsuleCount+GT_ModHandler.getCapsuleCellContainerCount(tStack)*64<0?tList.size()<3:tList.size()<4) && tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64 <= 64) {
				    					tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64;
				    					tList.add(GT_Utility.copy(64, tStack));
										tStack.stackSize -= 64;
									}
									if (tStack.stackSize > 0 && tCapsuleCount+GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack) <= 64 && (tCapsuleCount+GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack)<0?tList.size()<3:tList.size()<4)) {
										tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack);
										tList.add(tStack);
									}
								}
							}
						}
						tItemAmount = ((tItemAmount*tDensityMultiplier)%aMaterial.getDensity()>0?1:0) + ((tItemAmount*tDensityMultiplier)/aMaterial.getDensity());
		    			if (tList.size() > 0) {
							if ((aMaterial.mExtraData & 1) != 0) GregTech_API.sRecipeAdder.addElectrolyzerRecipe	(GT_Utility.copy((int)tItemAmount, aStack), tCapsuleCount>0?tCapsuleCount:0, tList.get(0), tList.size()<2?null:tList.get(1), tList.size()<3?null:tList.get(2), tCapsuleCount<0?GT_ModHandler.getEmptyCell(-tCapsuleCount):tList.size()<4?null:tList.get(3), Math.max(1, Math.abs(aMaterial.getProtons() * 8 * (int)tItemAmount)), Math.min(4, tList.size()) * 30);
							if ((aMaterial.mExtraData & 2) != 0) GregTech_API.sRecipeAdder.addCentrifugeRecipe		(GT_Utility.copy((int)tItemAmount, aStack), tCapsuleCount>0?tCapsuleCount:0, tList.get(0), tList.size()<2?null:tList.get(1), tList.size()<3?null:tList.get(2), tCapsuleCount<0?GT_ModHandler.getEmptyCell(-tCapsuleCount):tList.size()<4?null:tList.get(3), Math.max(1, Math.abs(aMaterial.getMass()    * 2 * (int)tItemAmount)));
						}
					}
					
					GregTech_API.sRecipeAdder.addCannerRecipe(aStack, null, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), GT_ModHandler.getEmptyCell(1), Math.max(aMaterial.getMass() / 2, 1), 2);
				}
	    		break;
	    	case dust:
	    		if (aMaterial.mFuelPower > 0) GregTech_API.sRecipeAdder.addFuel(GT_Utility.copy(aStack), null, aMaterial.mFuelPower, aMaterial.mFuelType);
	    		if (aMaterial.mAmplificationValue > 0) GT_ModHandler.addIC2MatterAmplifier(GT_Utility.copy(aStack), aMaterial.mAmplificationValue);
	    		GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 4), new Object[] {" X", "  ", 'X', aEvent.Name});
	    		GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny , aMaterial, 9), new Object[] {"X ", "  ", 'X', aEvent.Name});
	    		GregTech_API.sRecipeAdder.addCannerRecipe(aStack, GT_ModHandler.getEmptyCell(1), GT_OreDictUnificator.get(OrePrefixes.cell, aMaterial, 1), null, 100, 1);
	    		
	    		if (null != (tStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1))) {
	    			if (aMaterial.mBlastFurnaceRequired) {
	    				GT_ModHandler.removeFurnaceSmelting(aStack, tStack);
	    				GregTech_API.sRecipeAdder.addBlastRecipe(GT_Utility.copy(aStack), null, GT_Utility.copy(tStack), null, Math.max(aMaterial.getMass() * 10, 1) * aMaterial.mBlastFurnaceTemp / 200, 120, aMaterial.mBlastFurnaceTemp);
	    				if (aMaterial.mBlastFurnaceTemp <= 1000) GT_ModHandler.addRCBlastFurnaceRecipe(GT_Utility.copy(aStack), GT_Utility.copy(tStack), aMaterial.mBlastFurnaceTemp);
	    			} else {
	    				GT_ModHandler.addDustToIngotSmeltingRecipe(aStack, tStack);
	    			}
	    		} else {
	    			if (null == GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1)) {
	    				GT_ModHandler.addCompressionRecipe(GT_Utility.copy(9, aStack), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1));
	    			}
	    		}
	    		
	    		if (aMaterial.mMaterialList.size() > 0 && (aMaterial.mExtraData & 3) != 0) {
	    			int tCapsuleCount = 0, tDensityMultiplier = aMaterial.getDensity() > GregTech_API.MATERIAL_UNIT ? (int)(aMaterial.getDensity() / GregTech_API.MATERIAL_UNIT) : 1;
	    			long tItemAmount = 0;
					tList = new ArrayList<ItemStack>();
	    			for (MaterialStack tMat : aMaterial.mMaterialList) if (tMat.mAmount > 0) {
	    	    		
	    				if (tMat.mMaterial == Materials.Oxygen) {
	    					tStack = GT_ModHandler.getAirCell((tMat.mAmount) / 2);
	    				} else {
	    					tStack = GT_OreDictUnificator.get(OrePrefixes.dust, tMat.mMaterial, tMat.mAmount);
	    					if (tStack == null)
	    					tStack = GT_OreDictUnificator.get(OrePrefixes.cell, tMat.mMaterial, tMat.mAmount);
	    				}
	    				if (tItemAmount +  tMat.mAmount*GregTech_API.MATERIAL_UNIT <= aStack.getMaxStackSize() * (long)aMaterial.getDensity()) {
	    					tItemAmount += tMat.mAmount*GregTech_API.MATERIAL_UNIT;
							if (tStack != null) {
								tStack.stackSize *= tDensityMultiplier;
								while (tStack.stackSize > 64 && tList.size() < 4 && tCapsuleCount + GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64 <= 64) {
									tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCount(tStack) * 64;
			    					tList.add(GT_Utility.copy(64, tStack));
									tStack.stackSize -= 64;
								}
		    					if (tStack.stackSize > 0 && tList.size() < 4 && tCapsuleCount+GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack) <= 64) {
		    			    		tCapsuleCount += GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(tStack);
		    						tList.add(tStack);
		    					}
							}
	    				}
	    			}
	    			
	    			tItemAmount = ((tItemAmount*tDensityMultiplier)%aMaterial.getDensity()>0?1:0) + ((tItemAmount*tDensityMultiplier)/aMaterial.getDensity());
	    			if (tList.size() > 0) {
	    				if ((aMaterial.mExtraData & 1) != 0) GregTech_API.sRecipeAdder.addElectrolyzerRecipe	(GT_Utility.copy((int)tItemAmount, aStack), tCapsuleCount, tList.get(0), tList.size()<2?null:tList.get(1), tList.size()<3?null:tList.get(2), tList.size()<4?null:tList.get(3), Math.max(1, Math.abs(aMaterial.getProtons() * 2 * (int)tItemAmount)), Math.min(4, tList.size()) * 30);
	    				if ((aMaterial.mExtraData & 2) != 0) GregTech_API.sRecipeAdder.addCentrifugeRecipe		(GT_Utility.copy((int)tItemAmount, aStack), tCapsuleCount, tList.get(0), tList.size()<2?null:tList.get(1), tList.size()<3?null:tList.get(2), tList.size()<4?null:tList.get(3), Math.max(1, Math.abs(aMaterial.getMass()    * 8 * (int)tItemAmount)));
	    			}
	    		}
	    		
	    		for (MaterialStack[] tMats : mAlloySmelterList) {
	    			if (tMats[0].mMaterial == aMaterial) {
	    				for (ItemStack tOre : GT_OreDictUnificator.getOres(OrePrefixes.dust , tMats[1].mMaterial)) GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copy(tMats[0].mAmount, aStack), GT_Utility.copy(tMats[1].mAmount, tOre  ), GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount), tMats[2].mAmount * 50, 16);
	    				for (ItemStack tOre : GT_OreDictUnificator.getOres(OrePrefixes.ingot, tMats[1].mMaterial)) GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copy(tMats[0].mAmount, aStack), GT_Utility.copy(tMats[1].mAmount, tOre  ), GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount), tMats[2].mAmount * 50, 16);
	    			} else if (tMats[1].mMaterial == aMaterial) {
	    				for (ItemStack tOre : GT_OreDictUnificator.getOres(OrePrefixes.dust , tMats[0].mMaterial)) GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copy(tMats[0].mAmount, tOre  ), GT_Utility.copy(tMats[1].mAmount, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount), tMats[2].mAmount * 50, 16);
	    				for (ItemStack tOre : GT_OreDictUnificator.getOres(OrePrefixes.ingot, tMats[0].mMaterial)) GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copy(tMats[0].mAmount, tOre  ), GT_Utility.copy(tMats[1].mAmount, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount), tMats[2].mAmount * 50, 16);
	    			}
	    		}
	    		
	    		switch (aMaterial) {
	    		case _NULL: break;
	    		case Wood:
	    		    GT_ModHandler.addCompressionRecipe(GT_Utility.copy(8, aStack), GT_MetaItem_Material.instance.getStack(15, 1));
	    			break;
	    		case Wheat:
		    		GT_ModHandler.addSmeltingRecipe(GT_Utility.copy(1, aStack), new ItemStack(Item.bread, 1, 0));
		    		break;
	    		case Mercury:
		    		System.err.println("Quicksilver Dust?, To melt that, you don't even need a Furnace...");
	    			break;
	    		case Tetrahedrite:
			    	GT_ModHandler.addDustToIngotSmeltingRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1));
			    	break;
	    		case Cinnabar:
			    	GT_ModHandler.addDustToIngotSmeltingRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Mercury, 1));
			    	break;
	    		case Coal:
	    			GT_ModHandler.addLiquidTransposerFillRecipe(GT_Utility.copy(1, aStack), GT_ModHandler.getWater(125), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal, 1), 125);
			    	break;
	    		case Diamond:
			    	GregTech_API.sRecipeAdder.addImplosionRecipe(GT_Utility.copy(4, aStack), 32, GT_ModHandler.getIC2Item("industrialDiamond", 3, GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 3)), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 16));
			    	break;
	    		case Olivine: case Emerald: case Ruby: case Sapphire: case GreenSapphire: case Topaz: case Tanzanite:
			    	GregTech_API.sRecipeAdder.addImplosionRecipe(GT_Utility.copy(4, aStack), 24, GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 3), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 12));
			    	break;
	    		case GarnetRed: case GarnetYellow: case Amber:
			    	GregTech_API.sRecipeAdder.addImplosionRecipe(GT_Utility.copy(4, aStack), 16, GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 3), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 8));
			    	break;
	    		}
		    	break;
	    	case dustTiny:
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), new Object[] {aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name});
	    		
	    		if (null != (tStack = GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 1))) {
	    			if (aMaterial.mBlastFurnaceRequired) {
	    				GT_ModHandler.removeFurnaceSmelting(aStack, tStack);
	    			} else {
	    				GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_Utility.copy(aStack), tStack);
	    			}
	    		}
	    		
	    		break;
	    	case dustSmall:
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), new Object[] {aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name});
	    		break;
	    	case dustImpure:
	    		Materials tByProduct = aMaterial.mOreByProducts.size() > 0 ? aMaterial.mOreByProducts.get(aMaterial.mOreByProducts.size() - 1) : null;
	    		
	    		if (tByProduct == null) {
	    			GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(1, aStack), 0, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), null, null, null, Math.max(1, aMaterial.getMass()));
	    		} else {
	    			tStack = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tByProduct, GT_OreDictUnificator.get(OrePrefixes.nugget, tByProduct, 1), 1);
	    			if (tStack == null) {
	    				tStack = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tByProduct, 1);
	    				if (tStack == null) {
		    				tStack = GT_OreDictUnificator.get(OrePrefixes.dust, tByProduct, GT_OreDictUnificator.get(OrePrefixes.gem, tByProduct, 1), 1);
		    				if (tStack == null) {
			    				tStack = GT_OreDictUnificator.get(OrePrefixes.cell, tByProduct, 1);
			    				if (tStack == null) {
			    					GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(1, aStack), 0, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), null, null, null, Math.max(1, aMaterial.getMass()));
			    		    	} else {
				    				GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(9, aStack), 1, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 9), tStack, null, null, Math.max(1, aMaterial.getMass() * 72));
				    			}
			    			} else {
			    				GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(9, aStack), 0, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 9), tStack, null, null, Math.max(1, aMaterial.getMass() * 72));
			    			}
		    			} else {
		    				GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(2, aStack), 0, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 2), tStack, null, null, Math.max(1, aMaterial.getMass() * 16));
		    			}
	    			} else {
	    				GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(1, aStack), 0, GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1), tStack, null, null, Math.max(1, aMaterial.getMass() * 8));
	    			}
		    	}
	    		
	    		if (null != (tStack = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1))) {
	    			if (aMaterial.mBlastFurnaceRequired) {
	    				GT_ModHandler.removeFurnaceSmelting(aStack, tStack);
	    			} else {
	    				GT_ModHandler.addDustToIngotSmeltingRecipe(aStack, tStack);
	    			}
	    		}
	    		
	    		break;
	    	case ingot:
	    		if (aMaterial.mFuelPower > 0) GregTech_API.sRecipeAdder.addFuel(GT_Utility.copy(aStack), null, aMaterial.mFuelPower, aMaterial.mFuelType);
	    		GregTech_API.sRecipeAdder.addBenderRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1), 50, 20);
	    		GT_ModHandler.addCompressionRecipe(GT_Utility.copy(9, aStack), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1));
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 9), new Object[] {aEvent.Name});
	    		GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1), new Object[] {"F", "I", 'F', GT_ToolDictNames.craftingToolFile, 'I', aEvent.Name});
	    		GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.stick, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 2), Math.max(aMaterial.getMass() * 5, 1), 16);
	    		if (GT_ModHandler.getSmeltingOutput(aStack, false, null) == null) GT_ModHandler.addSmeltingRecipe(aStack, GT_OreDictUnificator.get(OrePrefixes.nugget, aMaterial, 9));
	    		
	    		if (null != (tStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1))) {
	    			if (aMaterial.mBlastFurnaceRequired) GT_ModHandler.removeFurnaceSmelting(tStack, aStack);
	    			GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copy(aStack), GT_Utility.copy(tStack), OrePrefixes.plate.get(aMaterial), true, true);
	    		} else {
	    			GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copy(aStack), GT_Utility.copy(aStack), OrePrefixes.plate.get(aMaterial), true, false);
	    		}
	    		
	    		for (MaterialStack[] tMats : mAlloySmelterList) {
	    			if (tMats[0].mMaterial == aMaterial) {
	    				for (ItemStack tOre : GT_OreDictUnificator.getOres(OrePrefixes.dust , tMats[1].mMaterial)) GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copy(tMats[0].mAmount, aStack), GT_Utility.copy(tMats[1].mAmount, tOre  ), GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount), tMats[2].mAmount * 50, 16);
	    				for (ItemStack tOre : GT_OreDictUnificator.getOres(OrePrefixes.ingot, tMats[1].mMaterial)) GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copy(tMats[0].mAmount, aStack), GT_Utility.copy(tMats[1].mAmount, tOre  ), GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount), tMats[2].mAmount * 50, 16);
	    			} else if (tMats[1].mMaterial == aMaterial) {
	    				for (ItemStack tOre : GT_OreDictUnificator.getOres(OrePrefixes.dust , tMats[0].mMaterial)) GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copy(tMats[0].mAmount, tOre  ), GT_Utility.copy(tMats[1].mAmount, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount), tMats[2].mAmount * 50, 16);
	    				for (ItemStack tOre : GT_OreDictUnificator.getOres(OrePrefixes.ingot, tMats[0].mMaterial)) GT_ModHandler.addAlloySmelterRecipe(GT_Utility.copy(tMats[0].mAmount, tOre  ), GT_Utility.copy(tMats[1].mAmount, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, tMats[2].mMaterial, tMats[2].mAmount), tMats[2].mAmount * 50, 16);
	    			}
	    		}
	    		
	        	if ((GT_ModHandler.mBCStoneGear != null && null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, GT_Utility.copy(1, aStack), null, GT_Utility.copy(1, aStack), GT_ModHandler.mBCStoneGear, GT_Utility.copy(1, aStack), null, GT_Utility.copy(1, aStack), null})))
	        		|| null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, GT_Utility.copy(1, aStack), null, GT_Utility.copy(1, aStack), new ItemStack(Item.ingotIron, 1), GT_Utility.copy(1, aStack), null, GT_Utility.copy(1, aStack), null}))
	        		|| null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, GT_Utility.copy(1, aStack), null, GT_Utility.copy(1, aStack), new ItemStack(Block.cobblestone, 1), GT_Utility.copy(1, aStack), null, GT_Utility.copy(1, aStack), null}))) {
	        		
	        		GregTech_API.sRecipeAdder.addCNCRecipe(GT_Utility.copy(4, aStack), tStack, 800, 1);
	        		
	        		if (GT_ModHandler.mBCStoneGear==null) {
	        			for (ItemStack tIteratedStack : GT_OreDictUnificator.getOres("stoneCobble")) GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, tIteratedStack), GT_Utility.copy(4, aStack), tStack, 1600, 2);
	        		} else {
	        			GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.mBCStoneGear, GT_Utility.copy(4, aStack), tStack, 1600, 2);
	        		}
	        	}
	        	
	        	if (aMaterial == Materials.Mercury) {
	        		System.err.println("Quicksilver Ingots?, Don't tell me there is an Armor made of that highly toxic and very likely to be melting Material!"); 
	        	}
	    		break;
	    	case ingotDouble:
	    	    if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.hammerdoubleingot, OrePrefixes.ingot.get(aMaterial), true)) {
	    			GT_ModHandler.addCraftingRecipe(GT_Utility.copy(aStack), new Object[] {"I", "I", "H", 'H', GT_ToolDictNames.craftingToolHardHammer, 'I', OrePrefixes.ingot.get(aMaterial)});
	    	    	GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copy(aStack), new Object[] {GT_ModHandler.getIC2Item("ForgeHammer", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(aMaterial), OrePrefixes.ingot.get(aMaterial)});
	    		}
	    	    break;
	    	case ingotHot:
	    	    GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1), Math.max(aMaterial.getMass() * 3, 1));
	    	    break;
	    	case block:
	    		ItemStack tStack1, tStack2, tStack3;
	        	GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.getFirstOre(OrePrefixes.plate.get(aMaterial), 9), Math.max(aMaterial.getMass() * 10, 1), 30);
	        	
	        	tStack1 = GT_OreDictUnificator.get(OrePrefixes.ingot	, aMaterial, 1);
	        	tStack2 = GT_OreDictUnificator.get(OrePrefixes.gem		, aMaterial, 1);
	        	tStack3 = GT_OreDictUnificator.get(OrePrefixes.dust		, aMaterial, 1);
	        	
	        	GT_ModHandler.removeRecipe(new ItemStack[] {GT_Utility.copy(aStack)});
	        	
	    		if (tStack1 != null) GT_ModHandler.removeRecipe(new ItemStack[] {tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1, tStack1});
	    		if (tStack2 != null) GT_ModHandler.removeRecipe(new ItemStack[] {tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2, tStack2});
	    		if (tStack3 != null) GT_ModHandler.removeRecipe(new ItemStack[] {tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3, tStack3});
	    		
	    		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.storageblockcrafting, OrePrefixes.block.get(aMaterial), false)) {
	    			if (tStack1 == null && tStack2 == null && tStack3 != null)	GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.getFirstOre(OrePrefixes.block.get(aMaterial), 1), new Object[] {"XXX", "XXX", "XXX", 'X', OrePrefixes.dust.get(aMaterial)});
	    			if (tStack2 != null)										GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.getFirstOre(OrePrefixes.block.get(aMaterial), 1), new Object[] {"XXX", "XXX", "XXX", 'X', OrePrefixes.gem.get(aMaterial)});
	        		if (tStack1 != null)										GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.getFirstOre(OrePrefixes.block.get(aMaterial), 1), new Object[] {"XXX", "XXX", "XXX", 'X', OrePrefixes.ingot.get(aMaterial)});
	        	}
	    		
	    		if (tStack1 != null) tStack1.stackSize = 9;
	    		if (tStack2 != null) tStack2.stackSize = 9;
	    		if (tStack3 != null) tStack3.stackSize = 9;
	    		
	    		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.storageblockdecrafting, OrePrefixes.block.get(aMaterial), tStack2 != null)) {
	    			if (tStack3 != null) GT_ModHandler.addShapelessCraftingRecipe(tStack3, new Object[] {OrePrefixes.block.get(aMaterial)});
	    			if (tStack2 != null) GT_ModHandler.addShapelessCraftingRecipe(tStack2, new Object[] {OrePrefixes.block.get(aMaterial)});
	    			if (tStack1 != null) GT_ModHandler.addShapelessCraftingRecipe(tStack1, new Object[] {OrePrefixes.block.get(aMaterial)});
	    		}
	    		
	        	if (aItem instanceof ItemBlock && GT_Mod.sBlockStackSize < aItem.getItemStackLimit()) aItem.setMaxStackSize(GT_Mod.sBlockStackSize);
	    		if (aEvent.Name.equalsIgnoreCase("blockQuicksilver")) System.err.println("'blockQuickSilver'?, In which Ice Desert can you actually place this as a solid Block?");
	        	else if (aEvent.Name.equals("blockIron")) GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("compressedCoalBall", 8), GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("coalChunk", 1), 400, 4);
	    		break;
	    	case gem:
	    		if (aMaterial.mFuelPower > 0) GregTech_API.sRecipeAdder.addFuel(GT_Utility.copy(aStack), null, aMaterial.mFuelPower*2, aMaterial.mFuelType);
	    		
	    		GT_ModHandler.addCompressionRecipe(GT_Utility.copy(9, aStack), GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1));
	    		GT_ModHandler.addSmeltingRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1));
				
	    		if (null == GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1)) GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.lense, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 1), Math.max(aMaterial.getMass(), 1), 16);
	    	    
	    		if (null != (tStack = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1)))
	    			GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copy(aStack), GT_Utility.copy(tStack), null, false, true);
	    		else
	    			GT_RecipeRegistrator.registerUsagesForMaterials(GT_Utility.copy(aStack), GT_Utility.copy(aStack), null, false, true);
	    		
		    	if (aMaterial == Materials.Apatite) {
		    		GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("fertilizer", 2), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1), 50, false);
		    	}
		    	else if (aMaterial == Materials.Coal || aMaterial == Materials.Charcoal) {
		    		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "torchesFromCoal", false)) GT_ModHandler.removeRecipe(new ItemStack[] {GT_Utility.copy(1, aStack), null, null, new ItemStack(Item.stick, 1, 0), null, null, null, null, null});
		        }
	    		break;
	    	case nugget:
		    	if (aMaterial == Materials.Gold) {
		    		if (GT_ModHandler.mBCStoneGear==null) {
		    			for (ItemStack tIteratedStack : GT_OreDictUnificator.getOres("stoneCobble")) GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, tIteratedStack), GT_Utility.copy(4, aStack), GT_ModHandler.getRCItem("part.gear.gold.plate", 1), 800, 1);
		    		} else {
		    			GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.mBCStoneGear, GT_Utility.copy(4, aStack), GT_ModHandler.getRCItem("part.gear.gold.plate", 1), 800, 1);
		    		}
		    	}
		    	GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.round, aMaterial, 1), null, Math.max(aMaterial.getMass() / 4, 1), 8);
		    	GregTech_API.sRecipeAdder.addAlloySmelterRecipe(GT_Utility.copy(9, aStack), null, GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1), Math.max(aMaterial.getMass() * 4, 1), 1);
		    	GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1), new Object[] {aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name, aEvent.Name});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.round, aMaterial, 1), new Object[] {"craftingToolFile", aEvent.Name, aEvent.Name});
	    		break;
	    	case plate:
	    		GT_ModHandler.removeRecipe(aStack);
	    		if (aMaterial.mFuelPower > 0) GregTech_API.sRecipeAdder.addFuel(GT_Utility.copy(aStack), null, aMaterial.mFuelPower, aMaterial.mFuelType);
	    		GT_ModHandler.addCompressionRecipe(GT_Utility.copy(9, aStack), GT_OreDictUnificator.get(OrePrefixes.plateDense, aMaterial, 1));
	    		GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.lense, aMaterial, 1), GT_OreDictUnificator.get(OrePrefixes.dustSmall, aMaterial, 1), Math.max(aMaterial.getMass() / 2, 1), 16);
	    	    GT_ModHandler.addSmeltingRecipe(GT_Utility.copy(aStack), GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1));
	    	    if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.hammerplating, OrePrefixes.ingot.get(aMaterial), true)) {
	    			GT_ModHandler.addCraftingRecipe(GT_Utility.copy(aStack), new Object[] {"H", "I", "I", 'H', GT_ToolDictNames.craftingToolHardHammer, 'I', OrePrefixes.ingot.get(aMaterial)});
	    	    	GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copy(aStack), new Object[] {GT_ModHandler.getIC2Item("ForgeHammer", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(aMaterial)});
	    		}
	    	    if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.hammerplating, OrePrefixes.ingotDouble.get(aMaterial), true)) {
	    			GT_ModHandler.addCraftingRecipe(GT_Utility.copy(aStack), new Object[] {"H", "I", 'H', GT_ToolDictNames.craftingToolHardHammer, 'I', OrePrefixes.ingotDouble.get(aMaterial)});
	    	    	GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copy(aStack), new Object[] {GT_ModHandler.getIC2Item("ForgeHammer", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingotDouble.get(aMaterial)});
	    		}
	    		break;
	    	case plateDense:
	    		GT_ModHandler.removeRecipe(aStack);
	    		if (aMaterial.mFuelPower > 0) GregTech_API.sRecipeAdder.addFuel(GT_Utility.copy(aStack), null, aMaterial.mFuelPower * 9, aMaterial.mFuelType);
	    		break;
	    	case plateAlloy:
	    		if (aEvent.Name.equals("plateAlloyCarbon")) {
	    			GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("generator", 1), GT_Utility.copy(4, aStack), GT_ModHandler.getIC2Item("windMill", 1), 6400, 8);
		    	}
	    		else if (aEvent.Name.equals("plateAlloyAdvanced")) {
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(2, aStack), new ItemStack(Block.glass, 7, 0), GT_ModHandler.getIC2Item("reinforcedGlass", 7), 400, 4);
		    	}
	    		else if (aEvent.Name.equals("plateAlloyIridium")) {
	    			
		    	}
	    		break;
	    	case bolt:
	    	   	GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.screw, aMaterial, 1), null, Math.max(aMaterial.getMass() / 8, 1), 4);
	    		break;
	    	case screw:
	    	   	break;
	    	case lense:
	    	   	break;
	    	case round:
	    		break;
	    	case stick:
	    		GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.bolt, aMaterial, 4), Math.max(aMaterial.getMass() * 2, 1), 4);
			    if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "torchesFromCoal", false)) {
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), new ItemStack(Item.coal, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Block.torchWood, 4), 400, 1);
		    	}
	    		break;
		    case oreNether:
	    		if (aItem instanceof ItemBlock && aItem.getItemStackLimit() > GT_Mod.sOreStackSize) aItem.setMaxStackSize(GT_Mod.sOreStackSize);
				if (aEvent.Name.equals("oreNetherQuartz")) {
	    			registerStandardOreRecipes(Materials.NetherQuartz, GT_Utility.copy(aStack), 1);
	    		} else {
	    			registerStandardOreRecipes(aMaterial, GT_Utility.copy(aStack), 2);
	    		}
				break;
		    case oreDense:
	    		if (aItem instanceof ItemBlock && aItem.getItemStackLimit() > GT_Mod.sOreStackSize) aItem.setMaxStackSize(GT_Mod.sOreStackSize);
	    		registerStandardOreRecipes(aMaterial, GT_Utility.copy(aStack), 2);
		    	break;
	    	case ore: case oreEnd:
	    		if (aItem instanceof ItemBlock && aItem.getItemStackLimit() > GT_Mod.sOreStackSize) aItem.setMaxStackSize(GT_Mod.sOreStackSize);
				registerStandardOreRecipes(aMaterial, GT_Utility.copy(aStack), 1);
				break;
		    case dye:
		    	if (aItem.getItemStackLimit() >= 16 && !aItem.hasContainerItem()) {
		    		for (ItemStack tIteratedStack : GT_OreDictUnificator.getOres("craftingSprayCan")) GregTech_API.sRecipeAdder.addCannerRecipe(GT_Utility.copy(16, aStack), GT_Utility.copy(1, tIteratedStack), GregTech_API.getGregTechItem(96+Dyes.get(aEvent.Name).mColor, 1, 0), null, 800, 1);
		    	}
		    	break;
	    	case wax:
		    	if (aEvent.Name.equals("waxMagical")) {
					GregTech_API.sRecipeAdder.addFuel(GT_Utility.copy(1, aStack), null, 6, 5);
		    	}
		    	break;
	    	case plank:
	    		if (aEvent.Name.startsWith("plankWood")) {
	    		    if (aItem instanceof ItemBlock && GT_Mod.sPlankStackSize < aItem.getItemStackLimit()) aItem.setMaxStackSize(GT_Mod.sPlankStackSize);
	    			GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1), null, 0, false);
	    		    GregTech_API.sRecipeAdder.addLatheRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.stick, Materials.Wood, 2), null, 10, 8);
	    		    GregTech_API.sRecipeAdder.addCNCRecipe(GT_Utility.copy(2, aStack), GT_ModHandler.mBCWoodGear, 800, 1);
	    		    GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(8, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1), new ItemStack(Block.music, 1), 800, 1);
	    	    	GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Diamond, 1), GT_Utility.copy(8, aStack), new ItemStack(Block.jukebox, 1), 1600, 1);
	    	    	//TODO: GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("industrialDiamond", 1), GT_Utility.copy(8, aStack), new ItemStack(Block.jukebox, 1), 1600, 1);
	    	    }
	    		break;
	    	case log:
	    	    if (aEvent.Name.equals("logRubber")) {
	    	    	if (GT_Utility.areStacksEqual(GT_ModHandler.getIC2Item("rubberWood", 1), GT_Utility.copy(1, aStack))) aMeta = GregTech_API.ITEM_WILDCARD_DAMAGE;
	    	    	GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(16, aStack), 5, GT_ModHandler.getIC2Item("resin", 8), GT_ModHandler.getIC2Item("plantBall", 6), GregTech_API.getGregTechItem(2, 1, 9), GregTech_API.getGregTechItem(2, 4, 8), 5000);
	        		GT_ModHandler.addSawmillRecipe(GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("resin", 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 16));
	        		GT_ModHandler.addExtractionRecipe(GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("rubber", 1));
	    	    }
		        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "wood2charcoalsmelting", false) && GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(GT_Utility.copy(1, aStack), false, null), new ItemStack(Item.coal, 1, 1))) GT_ModHandler.removeFurnaceSmelting(GT_Utility.copy(1, aStack), null);
		        if (aItem instanceof ItemBlock && GT_Mod.sWoodStackSize < aItem.getItemStackLimit()) aItem.setMaxStackSize(GT_Mod.sWoodStackSize);
		        if (aMeta == GregTech_API.ITEM_WILDCARD_DAMAGE) {
			    	for (int i = 0; i < 16; i++) {
				        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "wood2charcoalsmelting", false) && GT_Utility.areStacksEqual(GT_ModHandler.getSmeltingOutput(new ItemStack(aItem, 1, i), false, null), new ItemStack(Item.coal, 1, 1))) GT_ModHandler.removeFurnaceSmelting(new ItemStack(aItem, 1, i), null);
				    	tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(aItem, 1, i)});
				    	if (tStack != null) {
				    		ItemStack tPlanks = GT_Utility.copy(tStack);
				    		tPlanks.stackSize = (tPlanks.stackSize * 3) / 2;
				    		GregTech_API.sRecipeAdder.addCutterRecipe(new ItemStack(aItem, 1, i), GT_Utility.copy(GT_Mod.sNerfedWoodPlank?tStack.stackSize:(tStack.stackSize * 5) / 4, tStack), 200, 8);
					    	GT_ModHandler.addSawmillRecipe(new ItemStack(aItem, 1, i), tPlanks, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1));
					    	GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(aItem, 1, i)});
					    	GT_ModHandler.addCraftingRecipe(GT_Utility.copy(GT_Mod.sNerfedWoodPlank?tStack.stackSize:(tStack.stackSize * 5) / 4, tStack), new Object[] {"S", "L", 'S', "craftingToolSaw", 'L', new ItemStack(aItem, 1, i)});
					    	GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copy(tStack.stackSize / (GT_Mod.sNerfedWoodPlank?2:1), tStack), new Object[] {new ItemStack(aItem, 1, i)});
				    	}
		    		}
		    	} else {
			    	tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {GT_Utility.copy(1, aStack)});
			    	if (tStack != null) {
			    		ItemStack tPlanks = GT_Utility.copy(tStack);
			    		tPlanks.stackSize = (tPlanks.stackSize * 3) / 2;
			    		GregTech_API.sRecipeAdder.addCutterRecipe(GT_Utility.copy(1, aStack), GT_Utility.copy(GT_Mod.sNerfedWoodPlank?tStack.stackSize:(tStack.stackSize * 5) / 4, tStack), 200, 8);
				    	GT_ModHandler.addSawmillRecipe(GT_Utility.copy(1, aStack), tPlanks, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1));
			    		GT_ModHandler.removeRecipe(new ItemStack[] {GT_Utility.copy(1, aStack)});
				    	GT_ModHandler.addCraftingRecipe(GT_Utility.copy(GT_Mod.sNerfedWoodPlank?tStack.stackSize:(tStack.stackSize * 5) / 4, tStack), new Object[] {"S", "L", 'S', "craftingToolSaw", 'L', GT_Utility.copy(1, aStack)});
				    	GT_ModHandler.addShapelessCraftingRecipe(GT_Utility.copy(tStack.stackSize / (GT_Mod.sNerfedWoodPlank?2:1), tStack), new Object[] {GT_Utility.copy(1, aStack)});
			    	}
			    }
	    		break;
	    	case slab:
			    if (aEvent.Name.startsWith("slabWood")) {
			    	if (aItem instanceof ItemBlock && GT_Mod.sPlankStackSize < aItem.getItemStackLimit()) aItem.setMaxStackSize(GT_Mod.sPlankStackSize);
				    GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2), null, 0, false);
				    GregTech_API.sRecipeAdder.addCannerRecipe(GT_ModHandler.getRCItem("fluid.creosote.bucket", 1), GT_Utility.copy(3, aStack), GT_ModHandler.getRCItem("part.tie.wood", 1), new ItemStack(Item.bucketEmpty, 1), 200, 4);
				    GregTech_API.sRecipeAdder.addCannerRecipe(GT_ModHandler.getRCItem("fluid.creosote.cell", 1), GT_Utility.copy(3, aStack), GT_ModHandler.getRCItem("part.tie.wood", 1), GT_ModHandler.getEmptyCell(1), 200, 4);
				}
	    		break;
	    	case treeLeaves:
	    		if (aItem instanceof ItemBlock && GT_Mod.sWoodStackSize < aItem.getItemStackLimit()) aItem.setMaxStackSize(GT_Mod.sWoodStackSize);
			    GT_ModHandler.addCompressionRecipe(GT_Utility.copy(8, aStack), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
			    break;
	    	case treeSapling:
	    		if (aItem instanceof ItemBlock && GT_Mod.sWoodStackSize < aItem.getItemStackLimit()) aItem.setMaxStackSize(GT_Mod.sWoodStackSize);
		    	GT_ModHandler.addCompressionRecipe(GT_Utility.copy(4, aStack), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
			    GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Wood, 2), null, 0, false);
			    break;
	    	case sand:
	    		if (aEvent.Name.equals("sandCracked")) {
			    	if (aItem instanceof ItemBlock) {
			    		if (aItem.getItemStackLimit() > GT_Mod.sBlockStackSize) aItem.setMaxStackSize(GT_Mod.sBlockStackSize);
						GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.blocksList[((ItemBlock)aItem).getBlockID()], false);
			    	}
			    	GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(16, aStack), -1, GT_ModHandler.getFuelCan(25000), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 8), null, new ItemStack(Block.sand, 10), 2500);
	    		} else if (aEvent.Name.equals("sandOil")) {
	    		    GregTech_API.sRecipeAdder.addCentrifugeRecipe(GT_Utility.copy(2, aStack), 1, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil, 1), new ItemStack(Block.sand, 1, 0), null, null, 1000);
	    		}
	    		break;
	    	case crafting:
		    	if (aEvent.Name.equals("craftingLiBattery")) {
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("cropnalyzer", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GregTech_API.getGregTechItem(63, 1, GregTech_API.getGregTechItem(63, 1, 0).getMaxDamage()-1), 12800, 16);
	    		    GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1), GT_MetaItem_Component.instance.getStack(26, 1), 3200, 4);
		    	} else if (aEvent.Name.equals("craftingRedstoneTorch")) {
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1), GT_MetaItem_Component.instance.getStack(30, 1), 800, 16);
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Aluminium, 1), GT_MetaItem_Component.instance.getStack(87, 1), 800, 16);
		    	} else if (aEvent.Name.equals("craftingRawMachineTier01")) {
			    	GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), new ItemStack(Block.music, 4, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(GregTech_API.sBlockList[1], 1, 66), 800, 1);
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), new ItemStack(Block.stoneButton, 16, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(GregTech_API.sBlockList[1], 1, 67), 800, 1);
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), GT_MetaItem_Component.instance.getStack(22, 1), new ItemStack(GregTech_API.sBlockList[1], 1, 79), 1600, 2);
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), GT_MetaItem_Component.instance.getStack( 7, 1), GT_ModHandler.getIC2Item("solarPanel", 1), 1600, 2);
		    	} else if (aEvent.Name.equals("craftingGearTier02")) {
		    		for (ItemStack tIteratedStack : GT_OreDictUnificator.getOres("craftingGenerator" )) GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, aStack), GT_Utility.copy(1, tIteratedStack), GT_MetaItem_Component.instance.getStack(25, 1), 3200, 4);
		    	} else if (aEvent.Name.equals("craftingGenerator")) {
		    		for (ItemStack tIteratedStack : GT_OreDictUnificator.getOres("craftingGearTier02")) GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_Utility.copy(1, tIteratedStack), GT_Utility.copy(1, aStack), GT_MetaItem_Component.instance.getStack(25, 1), 3200, 4);
		    	} else if (aEvent.Name.equals("craftingWireCopper")) {
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_MetaItem_Component.instance.getStack(48, 1), GT_Utility.copy(3, aStack), GT_ModHandler.getIC2Item("electronicCircuit", 1), 800, 1);
		    		GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("frequencyTransmitter", 1), 800, 1);
		    	} else if (aEvent.Name.equals("craftingSprayCan")) {
		    		GregTech_API.sRecipeAdder.addCannerRecipe(GT_ModHandler.getIC2Item("grinPowder", 1), GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("weedEx", 1), null, 800, 1);
		    		GregTech_API.sRecipeAdder.addCannerRecipe(GT_ModHandler.getWaterCell(16), GT_Utility.copy(1, aStack), GregTech_API.getGregTechItem(95, 1, 0), GT_ModHandler.getEmptyCell(16), 1600, 2);
		    		GregTech_API.sRecipeAdder.addCannerRecipe(GT_ModHandler.getIC2Item("constructionFoamPellet", 16), GT_Utility.copy(1, aStack), GregTech_API.getGregTechItem(93, 1, 0), null, 1600, 2);
		    		GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.ConstructionFoam, 40), GT_Utility.copy(1, aStack), GregTech_API.getGregTechItem(93, 1, 0), GT_ModHandler.getEmptyCell(40), 1600, 2);
		    		GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Block.sand, 16, 0), GT_Utility.copy(1, aStack), GregTech_API.getGregTechItem(92, 1, 0), null, 1600, 2);
		    	    GregTech_API.sRecipeAdder.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen, 16), GT_Utility.copy(1, aStack), GregTech_API.getGregTechItem(91, 1, 0), GT_ModHandler.getEmptyCell(16), 1600, 2);
		    		
		    		for (Dyes tDye : Dyes.values()) if (tDye != Dyes._NULL) {
				        for (ItemStack tIteratedStack : GT_OreDictUnificator.getOres(tDye.toString())) {
					    	if (tIteratedStack.getMaxStackSize() >= 16 && !tIteratedStack.getItem().hasContainerItem()) {
					    		GregTech_API.sRecipeAdder.addCannerRecipe(GT_Utility.copy(16, tIteratedStack), GT_Utility.copy(1, aStack), GregTech_API.getGregTechItem(96+tDye.mColor, 1, 0), null, 800, 1);
					    	}
				        }
		    		}
		    	}
	    		break;
	    	case sheet: break; // sheetPlastic
	    	case fuel: break; // fuelCoke
	    	case brick: break; // brickPeat
	    	case item:
	    		if (aEvent.Name.equals("itemRubber")) {
	    	    	GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("tinCableItem", 1)	, GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("insulatedTinCableItem", 1), 100, 2);
	    		    GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("copperCableItem", 1)	, GT_Utility.copy(1, aStack), GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 100, 2);
	    	    	GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("goldCableItem", 1)	, GT_Utility.copy(2, aStack), GT_ModHandler.getIC2Item("insulatedGoldCableItem", 1), 200, 2);
	    	    	GregTech_API.sRecipeAdder.addAssemblerRecipe(GT_ModHandler.getIC2Item("ironCableItem", 1)	, GT_Utility.copy(3, aStack), GT_ModHandler.getIC2Item("insulatedIronCableItem", 1), 300, 2);
	    	    }
	    	    else if (aEvent.Name.equals("itemManganese")) {
	    	    	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Manganese, 1), null, 0, false);
	    	    }
	    	    else if (aEvent.Name.equals("itemMagnesium")) {
	    	    	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnesium, 1), null, 0, false);
	    	    }
	    	    else if (aEvent.Name.equals("itemPhosphorite") || aEvent.Name.equals("itemPhosphorus")) {
	    	    	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Phosphorus, 1), null, 0, false);
	    	    }
	    	    else if (aEvent.Name.equals("itemSulfur")) {
	    	    	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1), null, 0, false);
	    	    }
	    	    else if (aEvent.Name.equals("itemAluminum") || aEvent.Name.equals("itemAluminium")) {
	    	    	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium, 1), null, 0, false);
	    	    }
	    	    else if (aEvent.Name.equals("itemSaltpeter")) {
	    	    	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 1), null, 0, false);
	    	    }
	    	    else if (aEvent.Name.equals("itemUranium")) {
	    	    	GT_ModHandler.addPulverisationRecipe(GT_Utility.copy(1, aStack), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Uranium, 1), null, 0, false);
	    	    }
	    	    else {
	    	    	System.out.println("Item Name: " + aEvent.Name + " !!!Unknown Item detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
	    	    }
	    		break;
	    	default:
	    		break;
	    	}
    	} else {
    		System.out.println("Thingy Name: " + aEvent.Name + " !!!Unknown 'Thingy' detected!!! This Object seems to probably not follow a valid OreDictionary Convention, or I missed a Convention. Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it's just an Information.");
    	}
    }
    
	private static boolean registerStandardOreRecipes(Materials aMaterial, ItemStack aOreStack, int aMultiplier) {
		if (aOreStack == null || aMaterial == null) return false;
		GT_ModHandler.addValuableOre(aOreStack.itemID, aOreStack.getItemDamage(), aMaterial.mOreValue);
		Materials tMaterial = aMaterial.mOreReplacement, tPrimaryByMaterial = null, tSecondaryByMaterial = null;
		aMultiplier = Math.max(1, aMultiplier);
		aOreStack = GT_Utility.copy(aOreStack);
		aOreStack.stackSize = 1;
		
		ItemStack
		tIngot = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial.mDirectSmelting, 1),
		tGem = GT_OreDictUnificator.get(OrePrefixes.gem, tMaterial, 1),
		tTiny = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMaterial, GT_OreDictUnificator.get(OrePrefixes.nugget, tMaterial, 1), 1),
		tSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMaterial, 1),
		tDust = GT_OreDictUnificator.get(OrePrefixes.dust, tMaterial, tGem, 1),
		tCleaned = GT_OreDictUnificator.get(OrePrefixes.crushedPurified, tMaterial, tDust, 1),
		tCrushed = GT_OreDictUnificator.get(OrePrefixes.crushed, tMaterial, 2 * aMultiplier * aMaterial.mOreMultiplier),
		tPrimaryByProduct = null, tPrimaryByProductSmall = null, tSecondaryByProduct = null, tSecondaryByProductSmall = null;
		
		if (tCrushed == null) {
			tCrushed = GT_OreDictUnificator.get(OrePrefixes.dustImpure, tMaterial, GT_Utility.copy(tCrushed, tDust, tGem), 2 * aMultiplier * aMaterial.mOreMultiplier);
		}
		
		for (Materials tMat : aMaterial.mOreByProducts) {
			if (tPrimaryByProduct == null) {
				tPrimaryByMaterial = tMat;
				tPrimaryByProduct = GT_OreDictUnificator.get(OrePrefixes.dust, tMat, 1);
				tPrimaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMat, 1);
				if (tPrimaryByProductSmall == null) tPrimaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMat, GT_OreDictUnificator.get(OrePrefixes.nugget, tMat, 2), 2);
			}
			if (tSecondaryByProduct == null || tSecondaryByMaterial == tPrimaryByMaterial) {
				tSecondaryByMaterial = tMat;
				tSecondaryByProduct = GT_OreDictUnificator.get(OrePrefixes.dust, tMat, 1);
				tSecondaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustSmall, tMat, 1);
				if (tSecondaryByProductSmall == null) tSecondaryByProductSmall = GT_OreDictUnificator.get(OrePrefixes.dustTiny, tMat, GT_OreDictUnificator.get(OrePrefixes.nugget, tMat, 2), 2);
			}
		}
		
		if (tPrimaryByMaterial == null) tPrimaryByMaterial = tMaterial;
		if (tPrimaryByProduct == null) tPrimaryByProduct = tDust;
		if (tPrimaryByProductSmall == null) tPrimaryByProductSmall = tSmall;
		
		if (tSecondaryByMaterial == null) tSecondaryByMaterial = tPrimaryByMaterial;
		if (tSecondaryByProduct == null) tSecondaryByProduct = tPrimaryByProduct;
		if (tSecondaryByProductSmall == null) tSecondaryByProductSmall = tPrimaryByProductSmall;
		
		if (tIngot != null) {
			if (aMaterial.mBlastFurnaceRequired || aMaterial.mDirectSmelting.mBlastFurnaceRequired) {
				GT_ModHandler.removeFurnaceSmelting(aOreStack, null);
			} else {
				GT_ModHandler.addInductionSmelterRecipe(aOreStack, new ItemStack(Block.sand, 1), GT_Utility.mul(aMultiplier * (aMaterial.contains(SubTag.INDUCTIONSMELTING_LOW_OUTPUT)?1:2) * aMaterial.mSmeltingMultiplier, tIngot), GT_ModHandler.getTEItem("slagRich", 1), 300, 10 * aMultiplier);
				GT_ModHandler.addInductionSmelterRecipe(aOreStack, GT_ModHandler.getTEItem("slagRich", 1), GT_Utility.mul(aMultiplier * (aMaterial.contains(SubTag.INDUCTIONSMELTING_LOW_OUTPUT)?2:3) * aMaterial.mSmeltingMultiplier, tIngot), GT_ModHandler.getTEItem("slag", 1), 300, 95);
				GT_ModHandler.addSmeltingRecipe(aOreStack, tIngot);
			}
			
			if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_TRIPLE)) {
				GregTech_API.sRecipeAdder.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1), GT_Utility.mul(aMultiplier * 3 * aMaterial.mSmeltingMultiplier, tIngot), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2), tIngot.stackSize * 150, 120, 1500);
			} else if (aMaterial.contains(SubTag.BLASTFURNACE_CALCITE_DOUBLE)) {
				GregTech_API.sRecipeAdder.addBlastRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1), GT_Utility.mul(aMultiplier * 2 * aMaterial.mSmeltingMultiplier, tIngot), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.DarkAsh, 2), tIngot.stackSize * 150, 120, 1500);
			}
		}
		
		if (tCrushed != null) {
			if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.hammercrushing, OrePrefixes.ore.get(aMaterial), true)) GT_ModHandler.addCraftingRecipe(GT_Utility.copy(Math.max(1, tCrushed.stackSize / 2), tCrushed), new Object[] {"T", "O", 'T', GT_ToolDictNames.craftingToolHardHammer, 'O', OrePrefixes.ore.get(aMaterial)});
			GT_ModHandler.addPulverisationRecipe(aOreStack, GT_Utility.mul(aMultiplier, tCrushed), GT_Utility.mul(aMultiplier, tPrimaryByProduct), tPrimaryByProduct==null?0:(tPrimaryByProduct.stackSize*10*aMultiplier), true);
			if (tGem != null) {
				GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_ModHandler.getWaterCell(1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem), tSmall==null?GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned):GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 6, tSmall), GT_Utility.mul(aMultiplier * 2, tPrimaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				
				if (tSmall == null && tMaterial.contains(SubTag.WASHING_MERCURY)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem), GT_Utility.mul(aMultiplier * 3 * aMaterial.mOreMultiplier, tCleaned), GT_Utility.mul(aMultiplier * 2, tPrimaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				}
				if (tMaterial.contains(SubTag.WASHING_MERCURY)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem), GT_Utility.mul(aMultiplier * 3 * aMaterial.mOreMultiplier, tDust), GT_Utility.mul(aMultiplier * 2, tPrimaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				}
				if (tPrimaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem), tSmall==null?GT_Utility.mul(aMultiplier * 2 * aMaterial.mOreMultiplier, tCleaned):GT_Utility.mul(aMultiplier * 6 * aMaterial.mOreMultiplier, tSmall), GT_Utility.mul(aMultiplier * 2, tPrimaryByProduct), GT_ModHandler.getEmptyCell(1));
				}
				
				if (tSmall == null && tMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem), GT_Utility.mul(aMultiplier * 3 * aMaterial.mOreMultiplier, tCleaned), GT_Utility.mul(aMultiplier * 2, tPrimaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				}
				if (tMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem), GT_Utility.mul(aMultiplier * 3 * aMaterial.mOreMultiplier, tDust), GT_Utility.mul(aMultiplier * 2, tPrimaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				}
				if (tPrimaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier, tGem), tSmall==null?GT_Utility.mul(aMultiplier * 2 * aMaterial.mOreMultiplier, tCleaned):GT_Utility.mul(aMultiplier * 6 * aMaterial.mOreMultiplier, tSmall), GT_Utility.mul(aMultiplier * 2, tPrimaryByProduct), GT_ModHandler.getEmptyCell(1));
				}
			} else {
				GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_ModHandler.getWaterCell(1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned), GT_Utility.mul(aMultiplier, tPrimaryByProductSmall), GT_Utility.mul(aMultiplier, tSecondaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				
				if (tMaterial.contains(SubTag.WASHING_MERCURY)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 3, tCleaned), GT_Utility.mul(aMultiplier, tPrimaryByProductSmall), GT_Utility.mul(aMultiplier, tSecondaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				}
				if (tPrimaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned), GT_Utility.mul(aMultiplier, tPrimaryByProduct), GT_Utility.mul(aMultiplier, tSecondaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				}
				if (tSecondaryByMaterial.contains(SubTag.WASHING_MERCURY)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Mercury, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned), GT_Utility.mul(aMultiplier, tPrimaryByProductSmall), GT_Utility.mul(aMultiplier, tSecondaryByProduct), GT_ModHandler.getEmptyCell(1));
				}
				
				if (tMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 3, tCleaned), GT_Utility.mul(aMultiplier, tPrimaryByProductSmall), GT_Utility.mul(aMultiplier, tSecondaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				}
				if (tPrimaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned), GT_Utility.mul(aMultiplier, tPrimaryByProduct), GT_Utility.mul(aMultiplier, tSecondaryByProductSmall), GT_ModHandler.getEmptyCell(1));
				}
				if (tSecondaryByMaterial.contains(SubTag.WASHING_SODIUMPERSULFATE)) {
					GregTech_API.sRecipeAdder.addGrinderRecipe(aOreStack, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SodiumPersulfate, 1), GT_Utility.mul(aMultiplier * aMaterial.mOreMultiplier * 2, tCleaned), GT_Utility.mul(aMultiplier, tPrimaryByProductSmall), GT_Utility.mul(aMultiplier, tSecondaryByProduct), GT_ModHandler.getEmptyCell(1));
				}
			}
		}
		return true;
	}
}