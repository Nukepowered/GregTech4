package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ProgressManager;
import cpw.mods.fml.common.ProgressManager.ProgressBar;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

@SuppressWarnings("deprecation")
public class GT_OreDictHandler {
	public static final GT_OreDictHandler instance = new GT_OreDictHandler();
	
	private final Map<OrePrefixes, List<OreDictEntry>> mEvents = new HashMap<>();
	private final List<String> mIgnoredItems = Arrays.asList(new String[]{"itemRawRubber", "itemSilicon", "itemBacon", "itemJetpackAccelerator", "itemLazurite", "itemIridium", "itemTear", "itemClaw", "itemFertilizer", "itemTar", "itemSlimeball", "itemCoke", "itemBeeswax", "itemBeeQueen", "itemForcicium", "itemForcillium", "itemRoyalJelly", "itemHoneydew", "itemHoney", "itemPollen", "itemReedTypha", "itemSulfuricAcid", "itemPotash", "itemCompressedCarbon", "itemBitumen", "itemBioFuel", "itemCokeSugar", "itemCokeCactus", "itemCharcoalSugar", "itemCharcoalCactus", "itemSludge", "itemEnrichedAlloy", "itemQuicksilver", "itemMercury", "itemOsmium", "itemUltimateCircuit", "itemEnergizedStar", "itemAntimatterMolecule", "itemAntimatterGlob", "itemCoal", "itemBoat", "itemHerbalMedicineCake", "itemCakeSponge", "itemFishandPumpkinCakeSponge", "itemSoulCleaver", "itemInstantCake", "itemWhippingCream", "itemGlisteningWhippingCream", "itemCleaver", "itemHerbalMedicineWhippingCream", "itemStrangeWhippingCream", "itemBlazeCleaver", "itemBakedCakeSponge", "itemMagmaCake", "itemGlisteningCake", "itemOgreCleaver", "itemFishandPumpkinCake", "itemMagmaWhippingCream", "itemMultimeter", "itemSuperconductor"});
	private final List<String> mIgnoredNames = Arrays.asList(new String[]{"whiteStone", "stoneSlab", "clayBowl", "clayPlate", "ceramicBowl", "ceramicPlate", "ovenRack", "clayCup", "ceramicCup", "batteryBox", "transmutationStone", "torchRedstoneActive", "coal", "charcoal", "cloth", "cobblestoneSlab", "stoneBrickSlab", "cobblestoneWall", "stoneBrickWall", "cobblestoneStair", "stoneBrickStair", "blockCloud", "blockDirt", "blockTyrian", "blockCarpet", "blockFft", "blockLavastone", "blockHolystone", "blockConcrete", "sunnariumPart", "brSmallMachineCyaniteProcessor", "meteoriteCoal", "blockCobble", "pressOreProcessor", "crusherOreProcessor", "grinderOreProcessor", "blockRubber", "blockHoney", "blockHoneydew", "blockPeat", "blockRadioactive", "blockSlime", "blockCocoa", "blockSugarCane", "blockLeather", "blockClayBrick", "solarPanelHV", "cableRedNet", "stoneBowl", "crafterWood", "taintedSoil", "brickXyEngineering", "breederUranium", "wireMill", "chunkLazurite", "aluminumNatural", "aluminiumNatural", "naturalAluminum", "naturalAluminium", "antimatterMilligram", "antimatterGram", "strangeMatter", "coalGenerator", "electricFurnace", "unfinishedTank", "valvePart", "aquaRegia", "leatherSeal", "leatherSlimeSeal", "hambone", "slimeball", "enrichedUranium", "camoPaste", "livingrock", "quicksilver", "nuggetQuicksilver", "dirt", "grass", "gravel", "mycelium", "podzol", "netherrack", "ice", "chest"});
	private final List<String> mInvalidNames = Arrays.asList(new String[]{"bloodstoneOre", "universalCable", "bronzeTube", "ironTube", "netherTube", "obbyTube", "infiniteBattery", "eliteBattery", "advancedBattery", "10kEUStore", "blueDye", "MonazitOre", "quartzCrystal", "whiteLuminiteCrystal", "darkStoneIngot", "invisiumIngot", "demoniteOrb", "enderGem", "starconiumGem", "osmoniumIngot", "tapaziteGem", "zectiumIngot", "foolsRubyGem", "rubyGem", "meteoriteGem", "adamiteShard", "sapphireGem", "copperIngot", "ironStick", "goldStick", "diamondStick", "reinforcedStick", "draconicStick", "emeraldStick", "copperStick", "tinStick", "silverStick", "bronzeStick", "steelStick", "leadStick", "manyullynStick", "arditeStick", "cobaltStick", "aluminiumStick", "alumiteStick", "oilsandsOre", "copperWire", "superconductorWire", "sulfuricAcid", "conveyorBelt", "ironWire", "aluminumWire", "aluminiumWire", "silverWire", "tinWire", "dustSiliconSmall", "AluminumOre", "plateHeavyT2", "blockWool", "alloyPlateEnergizedHardened", "gasWood", "alloyPlateEnergized", "SilverOre", "LeadOre", "TinOre", "CopperOre", "silverOre", "leadOre", "tinOre", "copperOre", "bauxiteOre", "HSLivingmetalIngot", "oilMoving", "oilStill", "oilBucket", "petroleumOre", "dieselFuel", "diamondNugget", "planks", "wood", "stick", "sticks", "naquadah", "obsidianRod", "stoneRod", "thaumiumRod", "steelRod", "netherrackRod", "woodRod", "ironRod", "cactusRod", "flintRod", "copperRod", "cobaltRod", "alumiteRod", "blueslimeRod", "arditeRod", "manyullynRod", "bronzeRod", "boneRod", "slimeRod"});
	private final List<String> mIgnoredPrefixes = Arrays.asList(new String[]{"reactor", "mffs", "projred", "ganys"});
	private boolean mActivated = false;
	
	@SubscribeEvent
    public void registerOre(OreRegisterEvent aEvent) {
		if (GT_Mod.mDoNotInit || aEvent == null || aEvent.Ore == null || aEvent.Ore.getItem() == null || aEvent.Name == null || aEvent.Name.equals("") || mIgnoredNames.contains(aEvent.Name)) return;
		
		try {
			if (aEvent.Ore.stackSize != 1) {
				GT_Log.log.warn("WARNING: '" + aEvent.Name + "' is either being misused by another Mod or has been wrongly registered, as the stackSize of the Event-Stack is not 1!!!");
			}
			
			aEvent.Ore.stackSize = 1;
			
			ModContainer tContainer = Loader.instance().activeModContainer();
			String aMod = tContainer == null ? "minecraft" : tContainer.getModId();         
			
         ////TO DO: Make this into a config
         List<String> Blacklist = new ArrayList<String>(); 
         
         Blacklist.add("TConstruct");
         Blacklist.add("Xycraft");
         Blacklist.add("Natura");
         Blacklist.add("Natural");
         Blacklist.add("BiblioCraft");
         Blacklist.add("BiblioWoods");
         Blacklist.add("Botania");
         Blacklist.add("Botany");
         Blacklist.add("ExtraTiC");
         Blacklist.add("ExtraTrees");
         Blacklist.add("ExtraUtilities");
         Blacklist.add("ForbiddenMagic");
         Blacklist.add("ForgeMicroblock");
         Blacklist.add("ForgottenRelics");
         Blacklist.add("HardcoreEnderExpansion");
         Blacklist.add("NuclearCraft");
         Blacklist.add("OpenBlocks");
         Blacklist.add("OpenComputers");
         Blacklist.add("RandomThings");
         Blacklist.add("StorageDrawers");
         Blacklist.add("ThaumicExploration");
         Blacklist.add("WarpDrive");
         Blacklist.add("chisel");
         Blacklist.add("rftools");
         Blacklist.add("thaumcraftneiplugin");
         Blacklist.add("thaumicdyes");        
         Blacklist.add("ProjRed|Illumination");
         //

	    	if (Blacklist.contains(aMod)) return;
			if (mActivated || GregTech_API.sPostloadStarted || GT_Mod.sSortToTheEnd && GregTech_API.sLoadFinished) {
				GT_Log.log.warn("WARNING: " + aMod + " attempted to register " + aEvent.Name + " very late at the OreDictionary! Some Functionality may not work as expected! Sometimes registration in Postload is required, but you should always register OreDictionary Items in the Load Phase whenever possible.");
			}
	    	
			String e = aMod + " -> " + aEvent.Name;
			String tAssosiation = GT_OreDictUnificator.getAssociation(aEvent.Ore);
			if(GT_Utility.isStringValid(tAssosiation) && tAssosiation.equals(aEvent.Name)) {
                GT_Log.ore.println(e + " is ambiguous, this is an Error.");
                GT_Log.log.warn("WARNING: The OreDict-Registration of " + aEvent.Name + " by " + aMod + " is ambiguous. Please check if the Item hasn\'t already been registered under that Name, before registering it a second time!");
             } else {
            	 if (aEvent.Name.startsWith("item") && this.mIgnoredItems.contains(aEvent.Name)) {
            		 GT_Log.ore.println(e);
            		 if (aEvent.Name.equals("itemCopperWire")) {
            			 GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireCopper, aEvent.Ore);
            		 }
            	 } else if(this.mIgnoredNames.contains(aEvent.Name)) {
                     GT_Log.ore.println(e + " is getting ignored via hardcode.");
                  } else if(aEvent.Name.equals("stone")) {
                     GT_OreDictUnificator.registerOre("stoneSmooth", aEvent.Ore);
                  } else if(aEvent.Name.equals("cobblestone")) {
                     GT_OreDictUnificator.registerOre("stoneCobble", aEvent.Ore);
                  } else if(!aEvent.Name.contains("|") && !aEvent.Name.contains("*") && !aEvent.Name.contains(":") && !aEvent.Name.contains(".") && !aEvent.Name.contains("$")) {
                      for (String tName : this.mIgnoredPrefixes) {
                    	  if (aEvent.Name.startsWith(tName)) {
                    		  GT_Log.ore.println(e + " is using an ignored Prefix and is therefor getting ignored via hardcode.");
                              return;
                    	  }
                      }

                      if(aEvent.Name.equals("copperWire")) {
                         GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingWireCopper, aEvent.Ore);
                      }

                      if(aEvent.Name.equals("sheetPlastic")) {
                         GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Plastic, aEvent.Ore);
                      }

                      if(aEvent.Name.contains(" ")) {
                         GT_Log.ore.println(e + " is getting re-registered because the OreDict Name containing invalid spaces.");
                         GT_Log.log.warn("WARNING: \'" + aEvent.Name + "\' is an invalid OreDictionary Name, as it contains spaces! Register it without spaces to fix that.");
                         GT_OreDictUnificator.registerOre(aEvent.Name.replaceAll(" ", ""), GT_Utility.copyAmount(1L, aEvent.Ore));
                      } else if(this.mInvalidNames.contains(aEvent.Name)) {
                         GT_Log.ore.println(e + " is wrongly registered and therefor getting ignored.");
                         GT_Log.log.error("WARNING: \'" + aEvent.Name + "\' is an invalid OreDictionary Name. The Name doesn\'t fit to the Type of Item and/or doesn\'t follow a proper OreDictionary Convention. If you are the Owner of the Mod who adds this Item, please do the following: ");
                         if(aEvent.Name.equals("oilsandsOre")) {
                        	GT_Log.log.error("Please change it to \'sandOil\'");
                            GT_OreDictUnificator.registerOre("sandOil", aEvent.Ore);
                         } else if(aEvent.Name.equals("10kEUStore")) {
                            GT_Log.log.error("Use \'crafting10kEUStore\', you forgot to add the prefix");
                         } else if(aEvent.Name.equals("sulfuricAcid")) {
                            GT_Log.log.error("Please use \'bottleSulfuricAcid\' instead, since it is likely a vanilla bottle containing the Material \'SulfuricAcid\'");
                         } else if(aEvent.Name.equals("stick")) {
                            GT_Log.log.error("Use \'stickWood\' instead, it is already registered in vanilla-forge");
                         } else if(aEvent.Name.equals("wood")) {
                            GT_Log.log.error("Use \'logWood\' instead, it is already registered in vanilla-forge");
                         } else if(aEvent.Name.equals("plank")) {
                            GT_Log.log.error("Use \'plankWood\' instead, it is already registered in vanilla-forge");
                         } else if(aEvent.Name.endsWith("Tube")) {
                            GT_Log.log.error("Put the \'Tube\' in the beginning of the Name to get \'tube" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Tube", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Cable")) {
                            GT_Log.log.error("Put the \'Cable\' in the beginning of the Name to get \'cable" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Cable", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Battery")) {
                            GT_Log.log.error("Put the \'Battery\' in the beginning of the Name to get \'battery" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Battery", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Ingot")) {
                            GT_Log.log.error("Put the \'Ingot\' in the beginning of the Name to get \'ingot" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Ingot", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Crystal")) {
                            GT_Log.log.error("Put the \'Crystal\' in the beginning of the Name to get \'crystal" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Crystal", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Shard")) {
                            GT_Log.log.error("Put the \'Shard\' in the beginning of the Name to get \'shard" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Shard", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Rod")) {
                            GT_Log.log.error("Put the \'Rod\' in the beginning of the Name to get \'rod" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Rod", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Orb")) {
                            GT_Log.log.error("Put the \'Orb\' in the beginning of the Name to get \'orb" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Irb", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Gem")) {
                            GT_Log.log.error("Put the \'Gem\' in the beginning of the Name to get \'gem" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Gem", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Stick")) {
                            GT_Log.log.error("Put the \'Stick\' in the beginning of the Name to get \'stick" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Stick", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Plate")) {
                            GT_Log.log.error("Put the \'Plate\' in the beginning of the Name to get \'plate" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Plate", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Ore")) {
                            GT_Log.log.error("Put the \'Ore\' in the beginning of the Name to get \'ore" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Ore", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Dye")) {
                            GT_Log.log.error("Put the \'Dye\' in the beginning of the Name to get \'dye" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Dye", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Wire")) {
                            GT_Log.log.error("Put the \'Wire\' in the beginning of the Name to get \'wire" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Wire", "")) + "\'");
                         } else if(aEvent.Name.endsWith("Nugget")) {
                            GT_Log.log.error("Put the \'Nugget\' in the beginning of the Name to get \'nugget" + GT_Utility.capitalizeString(aEvent.Name.replaceFirst("Nugget", "")) + "\'");
                         } else {
                            GT_Log.log.error("I don\'t know exactly what to suggest about this Name, please consult me personally at GregTech.");
                         }

                         GT_Log.log.error("Private Prefixes could also be a solution if the first Suggestion doesn\'t apply. In that case the suggestion for the name is \'" + aMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                         GT_Log.log.error("If you are not the Owner then report it to the Owner of the Mod, which the Item belongs to.");
                      } else {
                         OrePrefixes aPrefix = OrePrefixes.getOrePrefix(aEvent.Name);
                         String tName = "";
                         if(aPrefix == null) {
                            if(aEvent.Name.toLowerCase().equals(aEvent.Name) && !aEvent.Name.equals("glowstone")) {
                               GT_Log.log.error("Improperly registered Ore: " + aEvent.Name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, as it is 100% lowercased!!! Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be \'" + aMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                               GT_Log.ore.println(e + " is invalid due to being solely lowercased.");
                               return;
                            }

                            if(aEvent.Name.toUpperCase().equals(aEvent.Name)) {
                               GT_Log.log.error("Improperly registered Ore: " + aEvent.Name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, as it is 100% uppercased!!! Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be \'" + aMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                               GT_Log.ore.println(e + " is invalid due to being solely uppercased.");
                               return;
                            }

                            if(GT_Utility.sUpperCasedCharacters.contains(Character.valueOf(aEvent.Name.charAt(0)))) {
                               GT_Log.log.error("Improperly registered Ore: " + aEvent.Name + " !!!Improperly registered Ore detected!!! This Object does not follow any OreDictionary Convention, because it starts with an uppercased Letter. Please report this to its Modauthor for a fix. If nothing proper is found, a good suggestion for its Name would be \'" + aMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                               GT_Log.ore.println(e + " is invalid due to the first character being uppercased.");
                               return;
                            }
                         } else {
                            if(aPrefix != aPrefix.mPrefixInto) {
                               tName = aEvent.Name.replaceFirst(aPrefix.toString(), aPrefix.mPrefixInto.toString());
                               if(!GT_OreDictUnificator.isRegisteringOres()) {
                                  GT_Log.ore.println(e + " uses a depricated Prefix, and is getting re-registered as " + tName);
                               }

                               GT_OreDictUnificator.registerOre(tName, aEvent.Ore);
                               return;
                            }

                            tName = aEvent.Name.replaceFirst(aPrefix.toString(), "");
                            if(tName.length() > 0) {
                               if(GT_Utility.sUpperCasedCharacters.contains(Character.valueOf(tName.charAt(0))) || GT_Utility.sNumberedCharacters.contains(Character.valueOf(tName.charAt(0))) || tName.charAt(0) == 95) {
                                  if(aPrefix.mDontUnificateActively || Block.getBlockFromItem(aEvent.Ore.getItem()) != null) {
                                     GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                  }

                                  if(aPrefix.mIsMaterialBased) {
                                     Materials aMaterial = Materials.get(tName);
                                     if(!aPrefix.isIgnored(aMaterial)) {
                                        aPrefix.add(GT_Utility.copyAmount(1L, aEvent.Ore));
                                     }

                                     if(aMaterial != aMaterial.mMaterialInto) {
                                        GT_OreDictUnificator.registerOre(aPrefix, aMaterial.mMaterialInto, aEvent.Ore);
                                        if(!GT_OreDictUnificator.isRegisteringOres()) {
                                           GT_Log.ore.println(e + " uses a deprecated Material and is getting re-registered as " + aPrefix.get(aMaterial.mMaterialInto));
                                        }

                                        return;
                                     }

                                     if(aPrefix.mMaterialAmount >= 0L && aPrefix.mMaterialAmount < 3628800L || aMaterial == Materials.Stone) {
                                        GT_ModHandler.addToRecyclerBlackList(GT_Utility.copyAmount(1L, aEvent.Ore));
                                     }

                                     if(aMaterial == Materials._NULL) {
                                        GT_Log.log.warn("Material Name: " + aEvent.Name + " !!!Unknown Material detected!!! Please report to GregTech Intergalactical for additional compatiblity. This is not an Error, it\'s just an Information.");
                                        GT_Log.ore.println(e + " uses an unknown Material. Report this to GregTech.");
                                        return;
                                     }
                                     
                                     for (Materials tReRegisteredMaterial : aMaterial.mOreReRegistrations) {
                                    	 GT_OreDictUnificator.registerOre(aPrefix, tReRegisteredMaterial, aEvent.Ore);
                                     }
                                     
                                     aMaterial.add(GT_Utility.copyAmount(1L, aEvent.Ore));
                                     switch(aPrefix) {
                                     case battery:
                                        if(aMaterial == Materials.Basic) {
                                           GT_OreDictUnificator.registerOre("crafting10kEUStore", aEvent.Ore);
                                           GT_OreDictUnificator.registerOre("calclavia:BATTERY", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Advanced) {
                                           GT_OreDictUnificator.registerOre("crafting100kEUStore", aEvent.Ore);
                                           GT_OreDictUnificator.registerOre("calclavia:ADVANCED_BATTERY", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Lithium) {
                                           GT_OreDictUnificator.registerOre("crafting100kEUStore", aEvent.Ore);
                                           GT_OreDictUnificator.registerOre("craftingLiBattery", aEvent.Ore);
                                           GT_OreDictUnificator.registerOre("calclavia:ADVANCED_BATTERY", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Elite) {
                                           GT_OreDictUnificator.registerOre("crafting1kkEUStore", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Master) {
                                           GT_OreDictUnificator.registerOre("crafting10kkEUStore", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Ultimate) {
                                           GT_OreDictUnificator.registerOre("crafting100kkEUStore", aEvent.Ore);
                                        }
                                        break;
                                     case circuit:
                                        if(aMaterial == Materials.Basic) {
                                           GT_OreDictUnificator.registerOre("craftingCircuitTier02", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Advanced) {
                                           GT_OreDictUnificator.registerOre("craftingCircuitTier04", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Data) {
                                           GT_OreDictUnificator.registerOre("craftingCircuitTier05", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Elite) {
                                           GT_OreDictUnificator.registerOre("craftingCircuitTier06", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Master) {
                                           GT_OreDictUnificator.registerOre("craftingCircuitTier07", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Ultimate) {
                                           GT_OreDictUnificator.registerOre("craftingCircuitTier08", aEvent.Ore);
                                        }
                                        break;
                                     case crystal:
                                        if(aMaterial == Materials.CertusQuartz) {
                                           GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.CertusQuartz, aEvent.Ore);
                                        }
                                        break;
                                     case gem:
                                        switch(aMaterial) {
                                        case Lapis:
                                        case Sodalite:
                                           GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
                                           break;
                                        case Lazurite:
                                           GT_OreDictUnificator.registerOre(Dyes.dyeCyan, aEvent.Ore);
                                           break;
                                        case Chocolate:
                                           GT_OreDictUnificator.registerOre(Dyes.dyeBrown, aEvent.Ore);
                                           break;
                                        case CertusQuartz:
                                           GT_OreDictUnificator.registerOre(OrePrefixes.crystal, Materials.CertusQuartz, aEvent.Ore);
                                        case Quartz:
                                        case Quartzite:
                                        case NetherQuartz:
                                           GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingQuartz, aEvent.Ore);
                                        default:
                                           break;
                                        }
                                     case lense:
                                        if(aMaterial.mTransparent && aMaterial.mColor != Dyes._NULL) {
                                           GT_OreDictUnificator.registerOre("craftingLense" + aMaterial.mColor.toString().replaceFirst("dye", ""), aEvent.Ore);
                                        }
                                        break;
                                     case plate:
                                        if(aMaterial == Materials.Steel || aMaterial == Materials.StainlessSteel) {
                                           GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingPlateSteel, aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Plastic) {
                                           GT_OreDictUnificator.registerOre("sheetPlastic", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Rubber) {
                                           GT_OreDictUnificator.registerOre("sheetRubber", aEvent.Ore);
                                        }
                                        break;
                                     case cell:
                                        if(aMaterial == Materials.Empty) {
                                           GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                        }
                                        break;
                                     case gearGt:
                                        if(aMaterial == Materials.Steel || aMaterial == Materials.StainlessSteel) {
                                           GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingGearGTSteel, aEvent.Ore);
                                        }

                                        GT_OreDictUnificator.registerOre(OrePrefixes.gear, aMaterial, aEvent.Ore);
                                        break;
                                     case stick:
                                        if(!GT_RecipeRegistrator.sRodMaterialList.contains(aMaterial)) {
                                           GT_RecipeRegistrator.sRodMaterialList.add(aMaterial);
                                        }

                                        if(aMaterial == Materials.Wood) {
                                           GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Tin || aMaterial == Materials.Lead || aMaterial == Materials.SolderingAlloy) {
                                           GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSolderingMetal, aEvent.Ore);
                                        }
                                        break;
                                     case dust:
                                        if(aMaterial == Materials.Wood) {
                                           GT_OreDictUnificator.registerOre("pulpWood", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Lapis) {
                                           GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Lazurite) {
                                           GT_OreDictUnificator.registerOre(Dyes.dyeCyan, aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Sodalite) {
                                           GT_OreDictUnificator.registerOre(Dyes.dyeBlue, aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.YellowLimonite) {
                                           GT_OreDictUnificator.registerOre(Dyes.dyeYellow, aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.BrownLimonite) {
                                           GT_OreDictUnificator.registerOre(Dyes.dyeBrown, aEvent.Ore);
                                        }
                                        break;
                                     case ingot:
                                        if(aMaterial == Materials.Rubber) {
                                           GT_OreDictUnificator.registerOre("itemRubber", aEvent.Ore);
                                        }

                                        if(aMaterial == Materials.Brass && aEvent.Ore.getItemDamage() == 2 && aEvent.Ore.getUnlocalizedName().equals("item.ingotBrass") && (new ItemStack(aEvent.Ore.getItem(), 1, 0)).getUnlocalizedName().contains("red")) {
                                           GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.RedAlloy, new ItemStack(aEvent.Ore.getItem(), 1, 0));
                                           GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.BlueAlloy, new ItemStack(aEvent.Ore.getItem(), 1, 1));
                                           GT_OreDictUnificator.set(OrePrefixes.ingot, Materials.Brass, new ItemStack(aEvent.Ore.getItem(), 1, 2));
                                        }
									 default: break;
                                     }

                                     if(aPrefix.mIsUnificatable && !aMaterial.mUnificatable) {
                                        return;
                                     }
                                  } else {
                                     aPrefix.add(GT_Utility.copyAmount(1L, aEvent.Ore));
                                  }
                               }
                            } else {
                            	if (aEvent.Name.equals("sand")) {
                            		GT_OreDictUnificator.registerOre(OrePrefixes.block, Materials.Sand, aEvent.Ore);
                            	} else if (aEvent.Name.equals("dye")) { 
                            		GT_OreDictUnificator.registerOre(Dyes.dyeWhite, aEvent.Ore);
                            	} else if (!aPrefix.mIsSelfReferencing) {
                            		GT_Log.log.error("WARNING: \'" + aEvent.Name + "\' is an OreDictionary Name which may cause Problems, due to being a Prefix, please use another one.");
                            		GT_Log.log.error("Private Prefixes are a solution. Please use \'" + aMod + ":" + aEvent.Name + "\' don\'t forget to insert the \':\' inbetween the Mod ID and OreDict Name, that is the most important part.");
                            		GT_Log.ore.println(e + " uses a Prefix as full OreDict Name, and is therefor invalid.");
                            		return;
                               }

                               aPrefix.add(GT_Utility.copyAmount(1L, aEvent.Ore));
                            }

                            switch(aPrefix) {
                            case stoneSmooth:
                               GT_OreDictUnificator.registerOre("stone", aEvent.Ore);
                               break;
                            case stoneCobble:
                               GT_OreDictUnificator.registerOre("cobblestone", aEvent.Ore);
                               break;
                            case sheet:
                               if(tName.equals("sheetPlastic")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.plate, Materials.Plastic, aEvent.Ore);
                               }
                               break;
                            case crafting:
                               if(tName.equals("ToolSolderingMetal")) {
                                  GregTech_API.registerSolderingMetal(aEvent.Ore);
                               }

                               if(tName.equals("IndustrialDiamond")) {
                                  GT_OreDictUnificator.addToBlacklist(aEvent.Ore);
                               }

                               if(tName.equals("RawMachineTier01")) {
                                  GT_OreDictUnificator.registerOre(GT_OreDictNames.craftingRawMachineTier00, aEvent.Ore);
                               }

                               if(tName.equals("CircuitTier02")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Basic, aEvent.Ore);
                               }

                               if(tName.equals("CircuitTier04")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Advanced, aEvent.Ore);
                               }

                               if(tName.equals("CircuitTier05")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Data, aEvent.Ore);
                               }

                               if(tName.equals("CircuitTier06")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Elite, aEvent.Ore);
                               }

                               if(tName.equals("CircuitTier07")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Master, aEvent.Ore);
                               }

                               if(tName.equals("CircuitTier08")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.circuit, Materials.Ultimate, aEvent.Ore);
                               }

                               if(tName.equals("WireCopper")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.wire, Materials.Copper, aEvent.Ore);
                               }
                               break;
                            case wood:
                               if(tName.equals("Rubber")) {
                                  GT_OreDictUnificator.registerOre("logRubber", aEvent.Ore);
                               }
                               break;
                            case item:
                               if(tName.equals("Rubber")) {
                                  GT_OreDictUnificator.registerOre(OrePrefixes.ingot, Materials.Rubber, aEvent.Ore);
                               }
                            default: break;
                            }
                         }

                         GT_Log.ore.println(e);
                         List<OreDictEntry> list = mEvents.get(aPrefix);
                         OreDictEntry entry = OreDictEntry.create(aEvent.Name);
                         ItemStack copy = aEvent.Ore.copy();
                         if (list != null) {
                        	 int idx = list.indexOf(entry);
                        	 if (idx >= 0) {
                        		 list.get(idx).add(aMod, copy);
                        	 } else {
                        		 entry.add(aMod, copy);
                        		 list.add(entry);
                        	 }
                         } else {
                        	 list = new ArrayList<>();
                        	 entry.add(aMod, copy);
                    		 list.add(entry);
                         }
                         
                         this.mEvents.put(aPrefix, list);
                      }
                   } else {
                      GT_Log.ore.println(e + " is using a private Prefix and is therefor getting ignored properly.");
                   }
             }
		} catch(Throwable e) {
			GT_Log.log.catching(e);
		}
    }
	
	/**
	 * Gets called during the PreLoad-Phase
	 */
    public void registerHandler() {
    	MinecraftForge.EVENT_BUS.register(this);
        for (String tOreName : OreDictionary.getOreNames())
        	for (ItemStack tOreStack : OreDictionary.getOres(tOreName))
        		registerOre(new OreRegisterEvent(tOreName, tOreStack));
	}
    
	/**
	 * Gets called during the PostLoad-Phase
	 */
    public void activateHandler() {
    	mActivated = true;
    	long time = System.currentTimeMillis();
    	
    	ProgressBar bar = ProgressManager.push("Handling OreDict", mEvents.keySet().size(), false);
    	
    	for (Entry<OrePrefixes, List<OreDictEntry>> e : mEvents.entrySet()) {
    		bar.step(String.valueOf(e.getKey()));
    		
    		if (e.getKey() != null) {
    			e.getKey().processOre(e.getValue());
    		} else {
    			StringBuilder app = new StringBuilder();
    			app.append("WRONG ORE DICTIONARY NAMES DETECTED FOR PREFIX: ");
    			app.append(e.getKey());
    			app.append("\n");
    			
    			for (OreDictEntry entry : e.getValue()) {
    				app.append('\t');
    				app.append(entry.oreDictName);
    				app.append(":\n");
    				
    				for (Entry<ItemStack, String> en : entry.modMap.entrySet()) {
    					app.append("\t\t");
    					app.append("mod: ");
    					app.append(en.getValue());
    					app.append(", stack: ");
    					app.append(en.getKey());
    					app.append('\n');
    				}
    			}
    			
    			app.append("This Objects seems to probably not follow a valid OreDictionary Convention, or I missed a Convention. ");
    			app.append("Please report to GregTech Intergalactical for additional compatiblity. ");
    			app.append("This is not an Error, it's just an Information.");
    			GT_Log.log.warn(app.toString());
    		}
    	}
    	
    	ProgressManager.pop(bar);
    	mEvents.clear();
		GT_Log.log.warn(String.format("Time spent for oredict iterating: %.3f seconds", (System.currentTimeMillis() - time) / 1000.0D));
    }
    
	public void registerUnificationEntries() {
		GregTech_API.sUnification.mConfig.save();
		GregTech_API.sUnification.mConfig.load();
		
		for (Entry<OrePrefixes, List<OreDictEntry>> e : mEvents.entrySet()) {
			if (e.getKey() == null || !e.getKey().mIsUnificatable) {
				continue;
			}
			
			for (OreDictEntry entry : e.getValue()) {
				for (ItemStack ore : entry.ores) {
					GT_OreDictUnificator.addAssociation(entry.oreDictName, ore);
					String modName = entry.modMap.get(ore);
					
					if (GT_OreDictUnificator.isBlacklisted(ore)) {
						continue;
					}
					if (!modName.equals("minecraft") && GregTech_API.sUnification.get(GT_ConfigCategories.specialunificationtargets + "." + modName, entry.oreDictName, false)) {
						GT_OreDictUnificator.set(entry.oreDictName, ore, true, true);
					} else {
						GT_OreDictUnificator.set(entry.oreDictName, ore, false, true);
					}
				}
			}
		}
		
		GregTech_API.sUnification.mConfig.save();
	}
}