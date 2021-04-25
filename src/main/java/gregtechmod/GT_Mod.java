package gregtechmod;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Element;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ItemTextures;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGT_Mod;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.metatileentity.MetaPipeEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_CircuitryBehavior;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_ItsNotMyFaultException;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Shaped_Recipe;
import gregtechmod.api.util.GT_Shapeless_NBT_Keeping_Recipe;
import gregtechmod.api.util.GT_Shapeless_Recipe;
import gregtechmod.api.util.GT_Utility;

import gregtechmod.common.GT_DummyWorld;
import gregtechmod.common.GT_GUIHandler;
import gregtechmod.common.GT_OreDictHandler;
import gregtechmod.common.GT_Proxy;
import gregtechmod.common.GT_RecipeAdder;
import gregtechmod.common.GT_TickHandler;
import gregtechmod.common.GT_Worldgenerator;
import gregtechmod.common.RecipeHandler;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.blocks.GT_BlockMetaID_Block2;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import gregtechmod.common.blocks.GT_BlockMetaID_Ore;
import gregtechmod.common.blocks.GT_BlockMetaID_Stone1;
import gregtechmod.common.blocks.GT_Block_LightSource;
import gregtechmod.common.covers.GT_Cover_Generic;
import gregtechmod.common.covers.GT_Cover_None;
import gregtechmod.common.covers.GT_Cover_Redstone;
import gregtechmod.common.items.GT_MetaBlock2_Item;
import gregtechmod.common.items.GT_MetaBlock_Item;
import gregtechmod.common.items.GT_MetaMachine_Item;
import gregtechmod.common.items.GT_MetaOre_Item;
import gregtechmod.common.items.GT_MetaStone1_Item;
import gregtechmod.common.network.GT_ConnectionHandler;
import gregtechmod.common.network.GT_NetworkHandler;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.render.GT_Block_Renderer;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_ComputerCube;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_LightSource;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_PlayerDetector;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Superconductor;

import gregtechmod.loaders.load.GT_CoverBehaviorLoader;
import gregtechmod.loaders.load.GT_ItemIterator;
import gregtechmod.loaders.load.GT_LiquidAndFuelLoader;
import gregtechmod.loaders.load.GT_SonictronLoader;
import gregtechmod.loaders.misc.GT_CoverLoader;
import gregtechmod.loaders.misc.GT_TooEasyModeLoader;
import gregtechmod.loaders.postload.GT_BlockResistanceLoader;
import gregtechmod.loaders.postload.GT_BookAndLootLoader;
import gregtechmod.loaders.postload.GT_CraftingRecipeLoader;
import gregtechmod.loaders.postload.GT_CropLoader;
import gregtechmod.loaders.postload.GT_ItemMaxStacksizeLoader;
import gregtechmod.loaders.postload.GT_MachineRecipeLoader;
import gregtechmod.loaders.postload.GT_MinableRegistrator;
import gregtechmod.loaders.postload.GT_RecyclerBlacklistLoader;
import gregtechmod.loaders.postload.GT_RecyclingRecipeLoader;
import gregtechmod.loaders.postload.GT_ScrapboxDropLoader;
import gregtechmod.loaders.postload.GT_SeedFlowerIterator;
import gregtechmod.loaders.postload.GT_UUMRecipeLoader;
import gregtechmod.loaders.postload.GT_Worldgenloader;
import gregtechmod.loaders.preload.GT_CircuitBehaviorLoader;
import gregtechmod.loaders.preload.GT_DictRegistratorPostItem;
import gregtechmod.loaders.preload.GT_DictRegistratorPreItem;
import gregtechmod.loaders.preload.GT_ItemLoader;
import gregtechmod.loaders.preload.GT_MetaTileEntityLoader;
import gregtechmod.loaders.preload.GT_OreProcessingLoader;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerRegisterEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;

import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;

/**
 * @author Gregorius Techneticies
 */
@Mod(modid = "gregtech_addon", name="GregTech-Addon", version="4.14.25", useMetadata=false, dependencies="required-after:IC2; after:Railcraft; after:ThermalExpansion; after:ThermalExpansion|Transport; after:ThermalExpansion|Energy; after:ThermalExpansion|Factory; before:RedPowerCore; before:RedPowerBase; before:RedPowerMachine; before:RedPowerCompat; before:RedPowerWiring; before:RedPowerLogic; before:RedPowerLighting; before:RedPowerWorld; before:RedPowerControl;")
public class GT_Mod implements IGT_Mod {
    @Instance
    public static GT_Mod instance;
    
    @SidedProxy(clientSide = "gregtechmod.common.GT_Client", serverSide = "gregtechmod.common.GT_Server")
    public static GT_Proxy gregtechproxy;
    
	public static volatile int VERSION = 414;
	public static volatile int REQUIRED_IC2 = 397;
    public static boolean 
    		sThaumiumObtainable = false,
    		sNerfDustCrafting = true,
    		sSortToTheEnd = true,
    		sCraftingUnification = true,
    		sInventoryUnification = true,
    		sIncreaseDungeonLoot = true,
    		sAxeWhenAdventure = true,
    		sSurvivalIntoAdventure = false,
    		sNerfedWoodPlank = true,
    		sNerfedWoodenTools = true,
    		sNerfedStoneTools = true,
    		sInvisibleOres = false,
    		sHardRock = false,
    		sHungerEffect = true,
    		mOnline = true,
    		mAlreadyPlayed = false,
    		mDoNotInit = false;
	public static int
			sItemDespawnTime = 6000,
			sUpgradeCount = 4,
			sBlockStackSize = 64,
			sOreStackSize = 64,
			sWoodStackSize = 64,
			sPlankStackSize = 64;
    /** Needed for getting the Save Files */
    public static World mUniverse = null;
    /** Cape Lists! Dunno why, but as of now 9 people donated, without leaving a MC-Username for a Cape. */
    public static final ArrayList<String> mGregTechCapeList 	= new ArrayList<>(Arrays.asList(new String[] {"Plem", "invultri", "Malevolence_", "Archibald_McShane", "Sirbab", "kehaan", "bpgames123", "semig0d", "9000bowser", "Sovereignty89", "Kris1432", "xander_cage_", "samuraijp", "bsaa", "SpwnX", "tworf", "Kadah", "kanni", "Stute", "Hegik", "Onlyme", "t3hero", "Hotchi", "jagoly", "Nullav", "BH5432", "Sibmer", "inceee", "foxxx0", "Hartok", "TMSama", "Shlnen", "Carsso", "zessirb", "meep310", "Seldron", "yttr1um", "hohounk", "freebug", "Sylphio", "jmarler", "Saberawr", "r00teniy", "Neonbeta", "yinscape", "voooon24", "Quintine", "peach774", "lepthymo", "bildeman", "Kremnari", "Aerosalo", "OndraSter", "oscares91", "mr10movie", "Daxx367x2", "EGERTRONx", "aka13_404", "Abouttabs", "Johnstaal", "djshiny99", "megatronp", "DZCreeper", "Kane_Hart", "Truculent", "vidplace7", "simon6689", "MomoNasty", "UnknownXLV", "goreacraft", "Fluttermine", "Daddy_Cecil", "MrMaleficus", "TigersFangs", "cublikefoot", "chainman564", "NikitaBuker", "Misha999777", "25FiveDetail", "AntiCivilBoy", "michaelbrady", "xXxIceFirexXx", "Speedynutty68", "GarretSidzaka", "HallowCharm977", "mastermind1919", "The_Hypersonic", "diamondguy2798", "zF4ll3nPr3d4t0r", "CrafterOfMines57", "XxELIT3xSNIP3RxX", "adamros", "alexbegt"}));
    public static final ArrayList<String> mBrainTechCapeList	= new ArrayList<>(Arrays.asList(new String[] {"Friedi4321"}));
    
    public static final ArrayList<String>		mSoundNames		= new ArrayList<String>();
    public static final ArrayList<ItemStack>	mSoundItems		= new ArrayList<ItemStack>();
    public static final ArrayList<Integer>		mSoundCounts	= new ArrayList<Integer>();
    
    public static String sMessage = "";
    private boolean tNothingRegistered = true;
    
    private static final void checkVersions() {
    	if (   VERSION != GregTech_API			.VERSION
            || VERSION != BaseMetaTileEntity	.VERSION
            || VERSION != BaseMetaPipeEntity	.VERSION
            || VERSION != MetaTileEntity		.VERSION
            || VERSION != MetaPipeEntity		.VERSION
    	    || VERSION != GT_CircuitryBehavior	.VERSION
    	    || VERSION != GT_CoverBehavior		.VERSION
    		|| VERSION != GT_Config				.VERSION
    		|| VERSION != GT_LanguageManager	.VERSION
    		|| VERSION != GT_ModHandler			.VERSION
    		|| VERSION != GT_OreDictUnificator	.VERSION
    		|| VERSION != Recipe				.VERSION
    		|| VERSION != GT_Utility			.VERSION
    	    || VERSION != GT_RecipeRegistrator	.VERSION
    		|| VERSION != Element				.VERSION
    		|| VERSION != Materials				.VERSION
    		|| VERSION != OrePrefixes			.VERSION)
    		throw new GT_ItsNotMyFaultException("One of your Mods included GregTech-API Files inside it's download, mention this to the Mod Author, who does this bad thing, and tell him/her to use reflection. I have added a Version check, to prevent Authors from breaking my Mod that way.");
    }
    
    public GT_Mod() {
    	checkVersions();
    	if (GregTech_API.isGregTechLoaded()) throw new GT_ItsNotMyFaultException("Why did you install my Addon twice? Remove the second gregtechmod.zip out of your mods-Folder, you need only one of them.");
		GregTech_API.gregtechmod = this;
		GregTech_API.sRecipeAdder = new GT_RecipeAdder();
		GregTech_API.sDummyWorld = new GT_DummyWorld();
		GregTech_API.sGTCoverload.add(new GT_CoverLoader());
        GT_OreDictHandler.instance.registerHandler();
        
		for (int i = 0; i < mGregTechCapeList.size(); ++i) {
			mGregTechCapeList.set(i, mGregTechCapeList.get(i).toLowerCase());
		}
        
        for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
        	if (tData.filledContainer.getItem() == Items.potionitem) {
        		tData.fluid.amount = 0;
        		break;
        	}
        }
        
        MinecraftForge.EVENT_BUS.register(this);
    	new GT_Cover_None();
		new GT_Cover_Generic();
		new GT_Cover_Redstone();
		new GT_ItemTextures();
    }
    
    @SuppressWarnings("rawtypes")
	@EventHandler
    public void preload(FMLPreInitializationEvent aEvent) {
    	checkVersions();
    	
    	try {
            Integer.parseInt(((String)Class.forName("ic2.core.IC2").getField("VERSION").get((Object)null)).substring(4, 7));
         } catch (Throwable var16) {
            throw new GT_ItsNotMyFaultException("Ancient IndustrialCraft Version detected, please update your IndustrialCraft here: ic2api.player.to:8080/job/IC2_experimental/?");
         }
    	
    	for (Runnable tRunnable : GregTech_API.sBeforeGTPreload) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    	File tFile = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "GregTech.cfg");
    	Configuration tConfig1 = new Configuration(tFile);
    	tConfig1.load();
    	
    	File gtDir = new File(aEvent.getModConfigurationDirectory(), "GregTech");
		GregTech_API.sRecipeFile 			= new GT_Config(new Configuration(new File(gtDir, "DynamicConfig.cfg")));
		GregTech_API.sMachineFile 			= new GT_Config(new Configuration(new File(gtDir, "MachineStats.cfg")));
		GregTech_API.sWorldgenFile 			= new GT_Config(new Configuration(new File(gtDir, "WorldGeneration.cfg")));
		GregTech_API.sMaterialProperties 	= new GT_Config(new Configuration(new File(gtDir, "MaterialProperties.cfg")));
		GregTech_API.sUnification 			= new GT_Config(new Configuration(new File(gtDir, "Unification.cfg")));
		GregTech_API.sSpecialFile			= new GT_Config(new Configuration(new File(gtDir, "Other.cfg")));
		GregTech_API.sIDFile				= new GT_Config(GT_Config.sConfigFileIDs = new Configuration(new File(gtDir, "IDs.cfg")));
		
		GregTech_API.sSpecialFile.mConfig.getCategory("enchants").setComment("There you can set allowed enchant IDs to specific GT tools\nIt will let you apply choosen enchant in anvil to GT tool");
		
    	mDoNotInit = (!tFile.getAbsolutePath().toLowerCase().contains("voltz")) && (tFile.getAbsolutePath().toLowerCase().contains(".technic") || tFile.getAbsolutePath().toLowerCase().contains("tekkit"));
    	if (mDoNotInit) {
            GT_Log.log.warn("Detected Technic Launcher.");
            GT_Log.log.warn("Errored.");
    		/**
    		 * Hello Tekkit user. I'm well aware that you decompiled my Addon to get it into Tekkit.
    		 * However, I will not change this Code, even though I know about that.
    		 * Why? Because it's to prevent Kakermix from adding it, without being kind enough to just ask, and not to prevent Tekkit Users from playing.
    		 * If he would ask me, then I will remove this barrier in future Versions of this Addon.
    		 * Don't try to make me think you are him. I already got PMs by People, who were obviously not him.
    		 */
    		GT_Log.log.error("*"); GT_Log.log.error("*"); GT_Log.log.error("*");
    		GT_Log.log.error("Hello, Gregorius Techneticies here,");
    		GT_Log.log.error("I see you most likely use Tekkit, but this Mod won't load, until Kakermix asks me PERSONALLY for the inclusion of my Mod. So bug HIM for it.");
    		GT_Log.log.error("PS. I could have exploded your Worlds, but i didn't for Publicityreasons.");
    		GT_Log.log.error("PPS. This Addon is Part of the FTB-Pack. It's even easier to use than Tekkit");
    		GT_Log.log.error("PPPS. There has been already so many Discoussions about me detecting Tekkit more or less precicely or even ACCIDENTLY on non-Tekkit Users, and NO YOU WON'T GET ME TO 'JUST' REMOVE THIS, GO LIVE WITH IT OR FIX IT YOURSELF!");
    		GT_Log.log.error("*"); GT_Log.log.error("*"); GT_Log.log.error("*");
    		tConfig1.get("Switching this won't help", "Tekkitsupport", false);
    		tConfig1.save();
        	return;
    	}
    	
    	GT_Log.mOreDictLogFile = new File(aEvent.getModConfigurationDirectory().getParentFile(), "logs/GT_OreDict.log");
        if(!GT_Log.mOreDictLogFile.exists()) {
           try {
              GT_Log.mOreDictLogFile.createNewFile();
           } catch (Throwable e) {}
        }
         
        try {
            List<String> bufferedLog = ((GT_Log.LogBuffer) GT_Log.ore).mBufferedOreDictLog;
            GT_Log.ore = new PrintStream(GT_Log.mOreDictLogFile);
            GT_Log.ore.println("**********************************************************************");
            GT_Log.ore.println("* This is the complete Log of the GregTech OreDictionary Handler     *");
            GT_Log.ore.println("* Everything in the OreDict goes through it sometimes causing Errors *");
            GT_Log.ore.println("* These Errors are getting logged aswell as properly registered Ores *");
            GT_Log.ore.println("* If you see something fishy going on in this Log, such as improper  *");
            GT_Log.ore.println("* Items being registered, then mention it to the corresponding Mod   *");
            GT_Log.ore.println("* In case it mentions GregTech itself improperly registering Stuff   *");
            GT_Log.ore.println("* then please contact me about that immediatly                       *");
            GT_Log.ore.println("*                                                                    *");
            GT_Log.ore.println("* In case of something being \'ignored properly\', that one isnt a Bug *");
            GT_Log.ore.println("**********************************************************************");
            bufferedLog.forEach(line -> GT_Log.ore.println(line));
         } catch (Throwable e) {}
    	
    	GT_Log.log.info("Preload-Phase started!");
    	GregTech_API.sPreloadStarted = true;
    	
        GT_Log.log.info("Getting required Items of other Mods.");
        GT_Items.Cell_Empty					.set(GT_ModHandler.getIC2Item("cell", 1L));
        GT_Items.Cell_Water					.set(GT_ModHandler.getIC2Item("waterCell", 1L));
        GT_Items.Cell_Lava					.set(GT_ModHandler.getIC2Item("lavaCell", 1L));
        GT_Items.Cell_Air					.set(GT_ModHandler.getIC2Item("airCell", 1L));
        GT_Items.IC2_Mixed_Metal_Ingot		.set(GT_ModHandler.getIC2Item("mixedMetalIngot", 1L));
        GT_Items.IC2_Fertilizer				.set(GT_ModHandler.getIC2Item("fertilizer", 1L));
        GT_Items.IC2_Resin					.set(GT_ModHandler.getIC2Item("resin", 1L));
        GT_Items.IC2_Crop_Seeds				.set(GT_ModHandler.getIC2Item("cropSeed", 1L));
        GT_Items.IC2_Grin_Powder			.set(GT_ModHandler.getIC2Item("grinPowder", 1L));
        GT_Items.IC2_Energium_Dust			.set(GT_ModHandler.getIC2Item("energiumDust", 1L));
        GT_Items.IC2_Scrap					.set(GT_ModHandler.getIC2Item("scrap", 1L));
        GT_Items.IC2_Scrapbox				.set(GT_ModHandler.getIC2Item("scrapBox", 1L));
        GT_Items.IC2_Fuel_Rod_Empty			.set(GT_ModHandler.getIC2Item("fuelRod", 1L));
        GT_Items.IC2_Food_Can_Empty			.set(GT_ModHandler.getIC2Item("tinCan", 1L));
        GT_Items.IC2_Food_Can_Filled		.set(GT_ModHandler.getIC2Item("filledTinCan", 1L, 0));
        GT_Items.IC2_Food_Can_Spoiled		.set(GT_ModHandler.getIC2Item("filledTinCan", 1L, 1));
        GT_Items.IC2_Industrial_Diamond		.set(GT_ModHandler.getIC2Item("industrialDiamond", 1L, new ItemStack(Items.diamond, 1)));
        GT_Items.IC2_Compressed_Coal_Ball	.set(GT_ModHandler.getIC2Item("compressedCoalBall", 1L));
        GT_Items.IC2_Compressed_Coal_Chunk	.set(GT_ModHandler.getIC2Item("coalChunk", 1L));
        GT_Items.Tool_Sword_Bronze			.set(GT_ModHandler.getIC2Item("bronzeSword", 1L));
        GT_Items.Tool_Pickaxe_Bronze		.set(GT_ModHandler.getIC2Item("bronzePickaxe", 1L));
        GT_Items.Tool_Shovel_Bronze			.set(GT_ModHandler.getIC2Item("bronzeShovel", 1L));
        GT_Items.Tool_Axe_Bronze			.set(GT_ModHandler.getIC2Item("bronzeAxe", 1L));
        GT_Items.Tool_Hoe_Bronze			.set(GT_ModHandler.getIC2Item("bronzeHoe", 1L));
        GT_Items.Tool_Hammer_Forge			.set(GT_ModHandler.getIC2Item("ForgeHammer", 1L));
        GT_Items.Credit_Iron				.set(GT_ModHandler.getIC2Item("coin", 1L));
        GT_Items.Circuit_Basic				.set(GT_ModHandler.getIC2Item("electronicCircuit", 1L));
        GT_Items.Circuit_Advanced			.set(GT_ModHandler.getIC2Item("advancedCircuit", 1L));
        GT_Items.Upgrade_Overclocker		.set(GT_ModHandler.getIC2Item("overclockerUpgrade", 1L));
        GT_Items.Upgrade_Transformer		.set(GT_ModHandler.getIC2Item("transformerUpgrade", 1L));
        
        GT_Items.Upgrade_Battery			.set(GT_ModHandler.getIC2Item("energyStorageUpgrade", 1L));
        GT_Log.log.info("Setting Configs");
		if (tConfig1.get("general", "TooEasyMode", false).getBoolean(false)) GregTech_API.sAfterGTPostload.add(new GT_TooEasyModeLoader());
    	GregTech_API.DEBUG_MODE									= tConfig1.get("general", "Debug", false).getBoolean(false);
    	GregTech_API.SECONDARY_DEBUG_MODE						= tConfig1.get("general", "Debug2", false).getBoolean(false);
    	GregTech_API.TICKS_FOR_LAG_AVERAGING 					= tConfig1.get("general", "TicksForLagAveragingWithScanner", 25).getInt(25);
        GregTech_API.MILLISECOND_THRESHOLD_UNTIL_LAG_WARNING	= tConfig1.get("general", "MillisecondsPassedInGTTileEntityUntilLagWarning", 100).getInt(100);
        
    	if (tConfig1.get("general", "disable_STDOUT", false).getBoolean(false)) System.out.close();
    	if (tConfig1.get("general", "disable_STDERR", false).getBoolean(false)) System.err.close();
        
        GregTech_API.IC_ENERGY_COMPATIBILITY 					= tConfig1.get("compatibility", "Industrialcraft.Energy", true).getBoolean(true);
        GregTech_API.RF_ENERGY_COMPATIBILITY					= tConfig1.get("compatibility", "Cofh.Energy", true).getBoolean(true);
        
        GregTech_API.sMachineExplosions 						= tConfig1.get("machines", "machines_explosion_damage", true).getBoolean(false);
        GregTech_API.sMachineFlammable 							= tConfig1.get("machines", "machines_flammable", true).getBoolean(false);
        GregTech_API.sMachineNonWrenchExplosions 				= tConfig1.get("machines", "explosions_on_nonwrenching", true).getBoolean(false);
        GregTech_API.sMachineWireFire 							= tConfig1.get("machines", "wirefire_on_explosion", true).getBoolean(false);
        GregTech_API.sMachineFireExplosions 					= tConfig1.get("machines", "fire_causes_explosions", true).getBoolean(false);
        GregTech_API.sMachineRainExplosions 					= tConfig1.get("machines", "rain_causes_explosions", true).getBoolean(false);
        GregTech_API.sMachineThunderExplosions 					= tConfig1.get("machines", "lightning_causes_explosions", true).getBoolean(false);
        GregTech_API.sConstantEnergy 							= tConfig1.get("machines", "constant_need_of_energy", true).getBoolean(false);
        GregTech_API.sColoredGUI 								= tConfig1.get("machines", "colored_guis_when_painted", true).getBoolean(false);
        GregTech_API.sDoShowAllItemsInCreative 					= tConfig1.get("general", "show_all_metaitems_in_creative_and_NEI", false).getBoolean(false);
        
    	sItemDespawnTime		= tConfig1.get("general", "ItemDespawnTime"				, 6000 ).getInt(6000);
    	sNerfDustCrafting		= tConfig1.get("general", "NerfDustCrafting"			, true ).getBoolean(true);
    	sIncreaseDungeonLoot	= tConfig1.get("general", "IncreaseDungeonLoot"			, true ).getBoolean(true);
    	sAxeWhenAdventure		= tConfig1.get("general", "AdventureModeStartingAxe"	, true ).getBoolean(true);
    	sSurvivalIntoAdventure	= tConfig1.get("general", "forceAdventureMode"			, false).getBoolean(false);
    	sHungerEffect			= tConfig1.get("general", "AFK_Hunger"					, false).getBoolean(false);
    	sHardRock				= tConfig1.get("general", "harderstone"					, false).getBoolean(false);
    	sInvisibleOres			= tConfig1.get("general", "hiddenores"					, true ).getBoolean(true);
    	sInventoryUnification	= tConfig1.get("general", "InventoryUnification"		, true ).getBoolean(true);
    	sCraftingUnification 	= tConfig1.get("general", "CraftingUnification"			, true ).getBoolean(true);
    	sNerfedWoodPlank		= tConfig1.get("general", "WoodNeedsSawForCrafting"		, true ).getBoolean(true);
    	sNerfedWoodenTools		= tConfig1.get("general", "smallerWoodToolDurability"	, true ).getBoolean(true);
    	sNerfedStoneTools		= tConfig1.get("general", "smallerStoneToolDurability"	, true ).getBoolean(true);
    	sSortToTheEnd 			= tConfig1.get("general", "EnsureToBeLoadedLast"		, true ).getBoolean(true);
    	mOnline 				= tConfig1.get("general", "online"						, true).getBoolean(false);
    	
    	if(tConfig1.get("general", "hardermobspawners", true).getBoolean(true)) Blocks.mob_spawner.setHardness(2000.0F);
    	GT_BlockMetaID_Block.mConnectedMachineTextures = tConfig1.get("general", "ConnectedMachineCasingTextures", true).getBoolean(false);
    	
    	sUpgradeCount							= Math.min(64, Math.max( 1, tConfig1.get("features", "UpgradeStacksize"		,  4).getInt()));
    	sOreStackSize							= Math.min(64, Math.max(16, tConfig1.get("features", "MaxOreStackSize"			, 64).getInt()));
    	sWoodStackSize							= Math.min(64, Math.max(16, tConfig1.get("features", "MaxLogStackSize"			, 64).getInt()));
    	sPlankStackSize							= Math.min(64, Math.max(16, tConfig1.get("features", "MaxPlankStackSize"		, 64).getInt()));
    	sBlockStackSize							= Math.min(64, Math.max(16, tConfig1.get("features", "MaxOtherBlockStackSize"	, 64).getInt()));
    	
    	GT_Worldgenerator.sAsteroids 			= GregTech_API.sWorldgenFile.get("worldgen.end", "EnderAsteroids"	, true);
        GT_Worldgenerator.sGeneratedOres[9]		= GregTech_API.sWorldgenFile.get("worldgen.end", "Tungstateore"		, true);
        GT_Worldgenerator.sGeneratedOres[10] 	= GregTech_API.sWorldgenFile.get("worldgen.end", "Cooperiteore"		, true);
        GT_Worldgenerator.sGeneratedOres[11] 	= GregTech_API.sWorldgenFile.get("worldgen.end", "Olivineore"		, true);
        GT_Worldgenerator.sGeneratedOres[12] 	= GregTech_API.sWorldgenFile.get("worldgen.end", "Sodaliteore"		, true);
    	
        GT_Config.system = (!GregTech_API.DEBUG_MODE && Calendar.getInstance().get(Calendar.MONTH) + 1 == 4 && Calendar.getInstance().get(Calendar.DATE) >= 1 && Calendar.getInstance().get(Calendar.DATE) <= 2);
        Materials.init(GregTech_API.sMaterialProperties);
        
        GT_Log.log.info("Saving Configs");
        tConfig1.save();
        
    	GT_Log.log.info("Removing all original Scrapbox Drops.");
        try {
        	GT_Utility.getField("ic2.core.item.ItemScrapbox$Drop", "topChance", true, true).set(null, 0);
        	((List)GT_Utility.getFieldContent(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "drops", true, true)).clear();
        } catch(Throwable e) {
        	if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
        }
        
    	GT_Log.log.info("Adding Scrap with a Weight of 200.0F to the Scrapbox Drops.");
        GT_ModHandler.addScrapboxDrop(200.0F, GT_ModHandler.getIC2Item("scrap", 1));
        
        if (isClientSide()) {
	        GT_Log.log.info("Register BlockRenderer");
		    new GT_Block_Renderer();
	        GT_Log.log.info("Downloading Cape List.");
		    try {new Thread(new Runnable() {@Override public void run() {try {
				Scanner tScanner = new Scanner(new URL("http://gregtech.mechaenetia.com/com/gregoriust/gregtech/supporterlist.txt").openStream());
				while (tScanner.hasNextLine()) {
				    String tName = tScanner.nextLine();
				    if (!GT_Mod.mGregTechCapeList.contains(tName.toLowerCase())) GT_Mod.mGregTechCapeList.add(tName.toLowerCase());
				}
			} catch(Throwable e) {}}}).start();} catch(Throwable e) {}
	        GT_Log.log.info("Downloading News.");
		    try {new Thread(new Runnable() {@Override public void run() {try {
				Scanner tScanner = new Scanner(new URL("http://gregtech.mechaenetia.com/com/gregoriust/gregtech/message.txt").openStream());
			    while (tScanner.hasNextLine()) sMessage += tScanner.nextLine() + " ";
			} catch(Throwable e) {}}}).start();} catch(Throwable e) {}
		}
        
        GT_Log.log.info("Adding Blocks.");
		GameRegistry.registerBlock(GregTech_API.sBlockList[0] = new GT_BlockMetaID_Block  (), GT_MetaBlock_Item.class	, "block");
		GameRegistry.registerBlock(GregTech_API.sBlockList[1] = new GT_BlockMetaID_Machine(), GT_MetaMachine_Item.class	, "machine");
		GameRegistry.registerBlock(GregTech_API.sBlockList[2] = new GT_BlockMetaID_Ore	  (), GT_MetaOre_Item.class		, "ore");
		GameRegistry.registerBlock(GregTech_API.sBlockList[3] = new GT_Block_LightSource  (), ItemBlock.class			, "light_source");
		GameRegistry.registerBlock(GregTech_API.sBlockList[4] = new GT_BlockMetaID_Block2 (), GT_MetaBlock2_Item.class	, "block_2");
		GameRegistry.registerBlock(GregTech_API.sBlockList[5] = new GT_BlockMetaID_Stone1 (), GT_MetaStone1_Item.class	, "stone");
		
		GregTech_API.registerMachineBlock(GregTech_API.sBlockList[0], new boolean[]{true, true, false, false, false, false, true, false, false, false, true, false, false, true, true, true});
		GregTech_API.registerMachineBlock(GregTech_API.sBlockList[1], new boolean[]{true});
		GregTech_API.registerMachineBlock(GregTech_API.sBlockList[4], new boolean[]{false, false, false, false, false, false, false, false, true, true, false, false, false, true});
		GregTech_API.registerMachineBlock(GregTech_API.sBlockList[4], 13);
       
		GT_Log.log.info("Register the few old TileEntities.");
		GameRegistry.registerTileEntity(GT_TileEntity_ComputerCube.class	, "GregTech_Computercube");
		GameRegistry.registerTileEntity(GT_TileEntity_Sonictron.class		, "Sonictron");
		GameRegistry.registerTileEntity(GT_TileEntity_Superconductor.class	, "Superconductorwire");
		GameRegistry.registerTileEntity(GT_TileEntity_PlayerDetector.class	, "Playerdetector");
		GameRegistry.registerTileEntity(GT_TileEntity_LightSource.class		, "GT_LightSource");
		
		GT_Log.log.info("Registering the BaseMetaTileEntity.");
		GameRegistry.registerTileEntity(GregTech_API.constructBaseMetaTileEntity().getClass(), "MetatileEntity");
		
		GT_Log.log.info("Registering the BaseMetaPipeEntity.");
		GameRegistry.registerTileEntity(BaseMetaPipeEntity.class, "MetaPipeEntity");
		
		GT_Log.log.info("Testing BaseMetaTileEntity.");
		if (GregTech_API.constructBaseMetaTileEntity() == null) {
			GT_Log.log.error("Fatal Error ocurred while initializing TileEntities, crashing Minecraft.");
			throw new RuntimeException("");
		} else {
            new GT_MetaTileEntityLoader().run();
            new GT_ItemLoader().run();
			new GT_OreProcessingLoader().run();
            new GT_DictRegistratorPreItem().run();
            new GT_DictRegistratorPostItem().run();
            new GT_CircuitBehaviorLoader().run();
            new GT_CoverBehaviorLoader().run();
            new GT_SonictronLoader().run();
        	GT_OreDictUnificator.activateUnificator();
            
    		GT_Log.log.info("Registering tools");
    		for (Runnable toRun : GregTech_API.sToolsRegiter) {
    			if (toRun != null) {
    				try {
    					toRun.run();
    				} catch (Throwable e) {
    					GT_Log.log.catching(e);
    				}
    			}
    		}
    		GregTech_API.sToolsRegiter.clear();
        	
            gregtechproxy.registerRenderers();
			for (FluidContainerData tGregTech : FluidContainerRegistry.getRegisteredFluidContainerData()) {
				if (tGregTech.filledContainer.getItem() == Items.potionitem) {
					tGregTech.fluid.amount = 0;
					break;
				}
			}
            
			if(sSortToTheEnd) {
				try {
					GT_Log.log.info("Sorting GregTech to the end of the Mod List for further processing.");
					LoadController controller = (LoadController) GT_Utility.getFieldContent(Loader.instance(), "modController", true, true);
					List<ModContainer> mods = controller.getActiveModList();
					ArrayList<ModContainer> sorted = new ArrayList<>();
					ModContainer mod = null;

					for (short i = 0; i < mods.size(); ++i) {
						ModContainer tMod = mods.get(i);
						if (tMod.getModId().equalsIgnoreCase(GregTech_API.MOD_ID)) {
							mod = tMod;
						} else {
							sorted.add(tMod);
						}
					}

					if (mod != null)
						sorted.add(mod);

					GT_Utility.getField(controller, "activeModList", true, true).set(controller, sorted);
				} catch (Throwable e) {
					if (GregTech_API.DEBUG_MODE) {
						GT_Log.log.catching(e);
					}
				}
			}
		}
		
    	new GT_NetworkHandler().run();
    	new GT_ConnectionHandler().run();

    	RecipeSorter.register("gregtech_addon:shaped"					, GT_Shaped_Recipe.class				, RecipeSorter.Category.SHAPED		, "after:forge:shapedore before:minecraft:shapeless");
    	RecipeSorter.register("gregtech_addon:shaped_nbt_keeping"		, GT_Shapeless_NBT_Keeping_Recipe.class	, RecipeSorter.Category.SHAPED		, "after:gregtech_addon:shaped before:minecraft:shapeless");
    	RecipeSorter.register("gregtech_addon:shapeless"				, GT_Shapeless_Recipe.class				, RecipeSorter.Category.SHAPELESS	, "after:minecraft:shapeless");
    	RecipeSorter.register("gregtech_addon:shapeless_nbt_keeping"	, GT_Shapeless_NBT_Keeping_Recipe.class	, RecipeSorter.Category.SHAPELESS	, "after:gregtech_addon:shapeless");
    	
        GregTech_API.sPreloadFinished = true;
        GT_Log.log.info("Preload-Phase finished!");
    	for (Runnable tRunnable : GregTech_API.sAfterGTPreload) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    }
    
    @EventHandler
    public void load(FMLInitializationEvent aEvent) {
    	if (mDoNotInit || GregTech_API.sLoadStarted) return;
    	
        GT_Items.TE_Slag					.set(GT_ModHandler.getTEItem("slag", 1L));
        GT_Items.TE_Slag_Rich				.set(GT_ModHandler.getTEItem("slagRich", 1L));
        GT_Items.TE_Rockwool				.set(GT_ModHandler.getTEItem("rockwool", 1L));
        try {
        	 GT_Items.TE_Hardened_Glass.set(new ItemStack(cofh.thermalexpansion.block.TEBlocks.blockGlass));
        } catch (Throwable e) {}
    	
    	for (Runnable tRunnable : GregTech_API.sBeforeGTLoad) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    	
    	checkVersions();
        GT_Log.log.info("Beginning Load-Phase.");
    	GregTech_API.sLoadStarted = true;
    	
		if (sSortToTheEnd) {
			new GT_ItemIterator().run();
			GT_OreDictHandler.instance.registerUnificationEntries();
			new GT_LiquidAndFuelLoader().run();
		}
    	
		for (FluidContainerData tGregTech : FluidContainerRegistry.getRegisteredFluidContainerData()) {
			if (tGregTech.filledContainer.getItem() == Items.potionitem) {
				tGregTech.fluid.amount = 0;
				break;
			}
		}
		
		// Changing blast resistance for reinforced stone, wither should break this
		try {
			ItemStack reinforcedStone = GT_ModHandler.getIC2Item("reinforcedStone", 1);
			ItemBlock block = (ItemBlock) reinforcedStone.getItem();
			block.field_150939_a.setResistance(30.0F);
		} catch (Throwable e) {}
		
    	GregTech_API.sLoadFinished = true;
        GT_Log.log.info("Load-Phase finished!");
    	for (Runnable tRunnable : GregTech_API.sAfterGTLoad) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
	}
	
    @EventHandler
    public void postload(FMLPostInitializationEvent aEvent) {
    	if (mDoNotInit || GregTech_API.sPostloadStarted) return;
    	
    	for (Runnable tRunnable : GregTech_API.sBeforeGTPostload) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    	
    	checkVersions();
    	
        GT_Log.log.info("Beginning PostLoad-Phase.");
    	GregTech_API.sPostloadStarted = true;
		
    	GT_Log.log.info("Adding Configs specific for MetaTileEntities");
        for(IMetaTileEntity tRunnable : GregTech_API.mMetaTileList) {
           try {
              if(tRunnable != null) {
                 tRunnable.onConfigLoad(GregTech_API.sMachineFile);
              }
           } catch (Throwable e) {
              GT_Log.log.catching(e);
           }
        }
    	
		if (sSortToTheEnd) {
			GT_OreDictHandler.instance.registerUnificationEntries();
		} else {
			new GT_ItemIterator().run();
			GT_OreDictHandler.instance.registerUnificationEntries();
			new GT_LiquidAndFuelLoader().run();
		}
        
		new GT_BookAndLootLoader().run();
		new GT_ItemMaxStacksizeLoader().run();
		new GT_BlockResistanceLoader().run();
		new GT_RecyclerBlacklistLoader().run();
		new GT_MinableRegistrator().run();
		new GT_SeedFlowerIterator().run();
		new GT_CraftingRecipeLoader().run();
		new GT_MachineRecipeLoader().run();
		new GT_ScrapboxDropLoader().run();
		new GT_UUMRecipeLoader().run();
		new GT_CropLoader().run();
		new GT_Worldgenloader().run();
		new GT_RecyclingRecipeLoader().run();

        GT_RecipeRegistrator.registerUsagesForMaterials(new ItemStack(Blocks.planks, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1L), null, false, true, false);
        GT_Log.log.info("Activating OreDictionary Handler, this can take some time, as it scans the whole OreDictionary");
        
        GT_Log.log.info("If your Log stops here, you were too impatient. Wait a bit more next time, before killing Minecraft with the Task Manager.");
        GT_OreDictHandler.instance.activateHandler();
        
        GT_Log.log.info("Activating Recipe handler");
        RecipeHandler.activateHandler();
        GT_Log.log.info("Congratulations, you have been waiting long enough. Have a Cake.");
        
        
        GT_Log.log.info("Adding Stone related Recipes");
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 0)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 7	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 1)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 0	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 2)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 0	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 3)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 0	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 4)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 0	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 5)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 0	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 6)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 0	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 7)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 0	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 8)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 15));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 9)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 8	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 10)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 8	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 11)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 8	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 12)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 8	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 13)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 8	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 14)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 8	));
        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 15)			, new ItemStack(GregTech_API.sBlockList[5]	, 1	, 8	));
        
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(200).input(new ItemStack(GregTech_API.sBlockList[5], 1,  3)).output(new ItemStack(GregTech_API.sBlockList[5], 1,  4)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(200).input(new ItemStack(GregTech_API.sBlockList[5], 1, 11)).output(new ItemStack(GregTech_API.sBlockList[5], 1, 12)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(200).input(new ItemStack(GregTech_API.sBlockList[5], 1,  7)).output(new ItemStack(GregTech_API.sBlockList[5], 1,  6)).buildAndRegister();
        RecipeMaps.ASSEMBLING.factory().EUt(4).duration(200).input(new ItemStack(GregTech_API.sBlockList[5], 1, 15)).output(new ItemStack(GregTech_API.sBlockList[5], 1, 14)).buildAndRegister();
        
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.stonebrick			, 1		, 3)	, new Object[] { new ItemStack(Blocks.double_stone_slab		, 1	, 8)	, GT_ToolDictNames.craftingToolFile });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 6)	, new Object[] { new ItemStack(GregTech_API.sBlockList[5]	, 1	, 7)	, GT_ToolDictNames.craftingToolFile });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 14)	, new Object[] { new ItemStack(GregTech_API.sBlockList[5]	, 1	, 15)	, GT_ToolDictNames.craftingToolFile });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.cobblestone			, 1		, 0)	, new Object[] { new ItemStack(Blocks.stone					, 1	, 0)	, GT_ToolDictNames.craftingToolHardHammer });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.stonebrick, 1, 2)		, 				  new Object[] { new ItemStack(Blocks.stonebrick			, 1	, 0)	, GT_ToolDictNames.craftingToolHardHammer });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 4)	, new Object[] { new ItemStack(GregTech_API.sBlockList[5]	, 1	, 3)	, GT_ToolDictNames.craftingToolHardHammer });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 1		, 12)	, new Object[] { new ItemStack(GregTech_API.sBlockList[5]	, 1	, 11)	, GT_ToolDictNames.craftingToolHardHammer });
        
        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 4	, 3)				, new Object[] { "XX", "XX", 'X', new ItemStack(GregTech_API.sBlockList[5]	, 4	, 0) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 4	, 11)				, new Object[] { "XX", "XX", 'X', new ItemStack(GregTech_API.sBlockList[5]	, 4	, 8) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 4	, 3)				, new Object[] { "XX", "XX", 'X', new ItemStack(GregTech_API.sBlockList[5]	, 4	, 7) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5]	, 4	, 11)				, new Object[] { "XX", "XX", 'X', new ItemStack(GregTech_API.sBlockList[5]	, 4	, 15) });
        
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.double_stone_slab	, 1	, 8)	, false				, new Object[] { new ItemStack(Blocks.double_stone_slab			, 1	, 0) });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.double_stone_slab	, 1	, 0)	, false				, new Object[] { new ItemStack(Blocks.double_stone_slab			, 1	, 8) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.double_stone_slab			, 1	, 0)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.stone_slab	, 1	, 0) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.cobblestone				, 1	, 0)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.stone_slab	, 1	, 3) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.brick_block				, 1	, 0)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.stone_slab	, 1	, 4) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.stonebrick					, 1	, 0)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.stone_slab	, 1	, 5) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.nether_brick				, 1	, 0)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.stone_slab	, 1	, 6) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.quartz_block				, 1	, 0)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.stone_slab	, 1	, 7) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.double_stone_slab			, 1	, 8)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.stone_slab	, 1	, 8) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.planks						, 1	, 0)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.wooden_slab, 1	, 0) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.planks						, 1	, 1)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.wooden_slab, 1	, 1) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.planks						, 1	, 2)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.wooden_slab, 1	, 2) });
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.planks						, 1	, 3)	, false	, false		, new Object[] { "B", "B", 'B', new ItemStack(Blocks.wooden_slab, 1	, 3) });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.stick				, 2	, 0)	, false				, new Object[] { new ItemStack(Blocks.deadbush, 1, 32767) });
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.stick				, 2	, 0)	, false				, new Object[] { new ItemStack(Blocks.tallgrass, 1, 0) });
        
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.comparator, 1, 0), false, false, new Object[] { " T ", "TQT", "SSS", 'Q', "craftingQuartz", 'S', "stoneSmooth", 'T', "craftingRedstoneTorch" });
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "ic2forgehammer", true)) {
            GT_ModHandler.removeRecipeByOutput(GT_Items.Tool_Hammer_Forge.getWildcard(1L, new Object[0]));
        }
        
        ItemStack tMat = new ItemStack(Items.iron_ingot);
        ItemStack tStack;
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.PressurePlate", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, null, null, null, null, null, null, null))) {
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { "XXT", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench });
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Bucket", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, null, tMat, null, tMat, null, null, null, null))) {
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { "XTX", " X ", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench });
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Minecart", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, null, tMat, tMat, tMat, tMat, null, null, null))) {
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { "XTX", "XXX", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench });
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Door", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, null, tMat, tMat, null, tMat, tMat, null))) {
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { "XX ", "XXT", "XX ", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench });
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Cauldron", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, null, tMat, tMat, null, tMat, tMat, tMat, tMat))) {
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { "X X", "XTX", "XXX", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench });
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Hopper", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, null, tMat, tMat, new ItemStack(Blocks.chest, 1, 0), tMat, null, tMat, null))) {
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { "XWX", "XCX", " X ", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench, 'C', "craftingChest" });
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Iron.Bars", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, tMat, tMat, tMat, tMat, null, null, null))) {
            final ItemStack itemStack = tStack;
            itemStack.stackSize /= 2;
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { " W ", "XXX", "XXX", 'X', "stickIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench });
        }
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("ironFence", 6L), new Object[] { "XXX", "XXX", " W ", 'X', "stickIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench });
        tMat = new ItemStack(Items.gold_ingot);
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Gold.PressurePlate", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, null, null, null, null, null, null, null))) {
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { "XXT", 'X', "plateGold", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotGold", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench });
        }
        
        tMat = GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Rubber, 1L);
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.recipereplacements, "Rubber.Sheet", true) && null != (tStack = GT_ModHandler.removeRecipe(tMat, tMat, tMat, tMat, tMat, tMat, null, null, null))) {
            GT_ModHandler.addCraftingRecipe(tStack, new Object[] { "XXX", "XXX", 'X', "plateRubber" });
        }
        
        tStack = GT_ModHandler.removeRecipe(new ItemStack(Blocks.planks, 1, 0), null, null, new ItemStack(Blocks.planks, 1, 0));
        if (tStack != null) {
            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank ? (tStack.stackSize) : ((tStack.stackSize * 5 / 4)), tStack), new Object[] { "S", "P", "P", 'P', "plankWood", 'S', GT_ToolDictNames.craftingToolSaw });
            GT_ModHandler.addCraftingRecipe(GT_Utility.copyAmount(GT_Mod.sNerfedWoodPlank ? ((tStack.stackSize / 2)) : (tStack.stackSize), tStack), new Object[] { "P", "P", 'P', "plankWood" });
        }
        
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(Blocks.wooden_pressure_plate, 1, 0), new Object[] { "PP", 'P', "plankWood" }));
		/*
		GT_Log.log.info("Adding Default Description Set of the Computer Cube");
		GT_ComputercubeDescription.addStandardDescriptions();
		*/
        if (sNerfedWoodenTools) {
    		GT_Log.log.info("Nerfing Wood Tool Durability");
        	Items.wooden_sword.setMaxDamage(12);
        	Items.stone_pickaxe.setMaxDamage(12);
        	Items.wooden_shovel.setMaxDamage(12);
        	Items.wooden_axe.setMaxDamage(12);
        	Items.wooden_hoe.setMaxDamage(12);
        }
        
        if (sNerfedStoneTools) {
    		GT_Log.log.info("Nerfing Stone Tool Durability");
        	Items.stone_sword.setMaxDamage(48);
        	Items.stone_pickaxe.setMaxDamage(48);
        	Items.stone_shovel.setMaxDamage(48);
        	Items.stone_axe.setMaxDamage(48);
        	Items.stone_hoe.setMaxDamage(48);
        }
        
		if (!isClientSide()) {
			GT_Log.log.info("Starting Cover Load Phase Serverside");
			GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSilver")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateRuby")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSapphire")		, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateAluminium")		, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTitanium")		, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateChrome")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSteel")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateBrass")			, "");
			GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateLead")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateElectrum")		, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateZinc")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateOlivine")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGreenSapphire")	, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("platePlatinum")		, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTungsten")		, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateNickel")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTungstenSteel")	, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateAlloyIridium")	, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateInvar")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateOsmium")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateIridium")			, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateDenseBronze")		, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGarnetYellow")	, "");
	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGarnetRed")		, "");
	    	
	    	for (Runnable tRunnable : GregTech_API.sGTCoverload) {
	    		try {
	    			tRunnable.run();
	    		} catch(Throwable e) {
	    			GT_Log.log.catching(e);
	    		}
	    	}
		}
		
		for (FluidContainerData tGregTech : FluidContainerRegistry.getRegisteredFluidContainerData()) {
			if (tGregTech.filledContainer.getItem() == Items.potionitem) {
				tGregTech.fluid.amount = 0;
				break;
			}
		}
		
        GT_Log.log.info("Adding buffered Recipes.");
        GT_ModHandler.stopBufferingCraftingRecipes();
        
    	GregTech_API.sPostloadFinished = true;
        GT_Log.log.info("PostLoad-Phase finished!");
        
        if (GregTech_API.DEBUG_MODE) {
	        try {
	        	GT_Log.log.info("Printing registered Channels");
	        	EnumMap<Side, Map<String, FMLEmbeddedChannel>> channels = ReflectionHelper.getPrivateValue(NetworkRegistry.class, NetworkRegistry.INSTANCE, new String[] {"channels"});
	        	if (channels != null) {
	        		Map<String, FMLEmbeddedChannel> serverChannel = channels.get(Side.SERVER);
	        		serverChannel.keySet().forEach(key -> GT_Log.log.info("  " + key));
	        	}
	        } catch(Throwable e) {
	        	GT_Log.log.catching(e);
	        }
        }
        
    	for (Runnable tRunnable : GregTech_API.sAfterGTPostload) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    	
    	GT_Log.log.info("Loading finished, deallocating temporary Init Variables.");
    	GregTech_API.sBeforeGTPreload = null;
    	GregTech_API.sAfterGTPreload = null;
    	GregTech_API.sBeforeGTLoad = null;
    	GregTech_API.sAfterGTLoad = null;
    	GregTech_API.sBeforeGTPostload = null;
    	GregTech_API.sAfterGTPostload = null;
    }
	
    	/*
    	try {((CommandHandler)aEvent.getServer().getCommandManager()).registerCommand(new CommandBase() {
			@Override public String getCommandName() {return "xyzd";}
			@Override public String getCommandUsage(ICommandSender aSender) {return "";}
			@Override public int getRequiredPermissionLevel() {return 0;}
			@Override public boolean canCommandSenderUseCommand(ICommandSender aSender) {return true;}
			@Override public void processCommand(ICommandSender aSender, String[] aParameters) {
				if (aParameters.length >= 3) {
		            EntityPlayerMP aPlayer = getCommandSenderAsPlayer(aSender);
		            if (aPlayer != null && (aPlayer.username.equals("GregoriusT") || aPlayer.username.equals("Player"))) {
		            	try {
							if (aPlayer.ridingEntity != null) aPlayer.mountEntity(null);
							if (aPlayer.riddenByEntity != null) aPlayer.riddenByEntity.mountEntity(null);
							
			            	if (aParameters.length >= 4) {
			            		GT_Utility.moveEntityToDimensionAtCoords(aPlayer, Integer.parseInt(aParameters[3]), Integer.parseInt(aParameters[0])+0.5, Integer.parseInt(aParameters[1])+0.5, Integer.parseInt(aParameters[2])+0.5);
			            	} else {
			            		aPlayer.setPositionAndUpdate(Integer.parseInt(aParameters[0]), Integer.parseInt(aParameters[1]), Integer.parseInt(aParameters[2]));
			            	}
		            	} catch(Throwable e) {}
		            }
				}
			}
    	});} catch(Throwable e) {}
    	*/
    
	@EventHandler
	public void start(FMLServerStartingEvent aEvent) {
		if (!mDoNotInit) {
			for (Runnable tRunnable : GregTech_API.sBeforeGTServerstart) {
				try {
					tRunnable.run();
				} catch (Throwable e) {
					GT_Log.log.catching(e);
				}
			}

			GT_Log.log.info("ServerStart-Phase started!");
			mUniverse = null;
			GT_TickHandler.isFirstTick = true;
			NetworkRegistry.INSTANCE.registerGuiHandler(GregTech_API.gregtechmod, new GT_GUIHandler());

			for (IMetaTileEntity mte : GregTech_API.mMetaTileList) {
				try {
					if (mte != null)
						mte.onServerStart();
				} catch (Throwable e) {
					GT_Log.log.catching(e);
				}
			}

			for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData()) {
				if (data.filledContainer.getItem() == Items.potionitem) {
					data.fluid.amount = 0;
				}
			}

			GT_Log.log.info("ServerStart-Phase finished!");
			for (Runnable toRun : GregTech_API.sAfterGTServerstart) {
				try {
					toRun.run();
				} catch (Throwable e) {
					GT_Log.log.catching(e);
				}
			}
		}
	}
    
    @EventHandler
    public void start(FMLServerStartedEvent aEvent) {
		if (!mDoNotInit) {
			GregTech_API.sWirelessRedstone.clear();
		}
    }
    
    @EventHandler
    public void stop(FMLServerStoppingEvent aEvent) {
    	if (mDoNotInit) return;
    	
    	for (Runnable tRunnable : GregTech_API.sBeforeGTServerstop) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    	
    	for (IMetaTileEntity tMetaTileEntity : GregTech_API.mMetaTileList) {
    		try {
	    		if (tMetaTileEntity != null) tMetaTileEntity.onServerStop();
	    	} catch(Throwable e) {
	    		GT_Log.log.catching(e);
	    	}
    	}
    	
    	mUniverse = null;
    	GregTech_API.sWirelessRedstone.clear();
    	
		try {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.info("*");
				GT_Log.log.info("Printing List of all registered Objects inside the OreDictionary, now with free extra Sorting:");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				String[] names = OreDictionary.getOreNames();
				Arrays.sort(names);
				for (String name : names) {
					int tAmount = OreDictionary.getOres(name).size();
					if (tAmount > 0) {
						GT_Log.log.info((tAmount < 10 ? " " : "") + tAmount + "x " + name);
					}
				}
				
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("Outputting all the Names inside the Itemslist, this List can become very long");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				
				@SuppressWarnings("unchecked")
				Iterator<Item> items = Item.itemRegistry.iterator();
				while (items.hasNext()) {
					Item item = items.next();
					GT_Log.log.info(item.getUnlocalizedName());
					if (item.getHasSubtypes()) {
						try {
							for (int meta = 0; meta < 16; ++meta) {
								GT_Log.log.info(new ItemStack(item, 1, meta).getUnlocalizedName());
							}
						} catch (Throwable e) {}
					}
				}
				
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("Outputting all the Names registered by Railcraft");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");

				try {
					for (String name : mods.railcraft.api.core.items.TagList.getTags()) {
						GT_Log.log.info(name);
					}
				} catch (Throwable e) {}

				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("Outputting all the Names inside the Biomeslist");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				
				
				for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray()) {
					if (biome != null) {
						GT_Log.log.info(biome.biomeID + " = " + biome.biomeName);
					}
				}

				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("Printing List of generatable Materials");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");

				for (int i = 0; i < GregTech_API.sGeneratedMaterials.length; ++i) {
					if (GregTech_API.sGeneratedMaterials[i] == null) {
						GT_Log.log.info("Index " + i + ":" + null);
					} else {
						GT_Log.log.info("Index " + i + ":" + GregTech_API.sGeneratedMaterials[i]);
					}
				}

				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("END GregTech-Debug");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
				GT_Log.log.info("*");
			}
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.catching(e);
			}
		}
    	
    	for (Runnable tRunnable : GregTech_API.sAfterGTServerstop) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    }
    
    @SubscribeEvent
	public void onFluidContainerRegistration(FluidContainerRegisterEvent aFluidEvent) {
		if (this.tNothingRegistered) {
			for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) {
				if (tData.filledContainer.getItem() == Items.potionitem) {
					tData.fluid.amount = 0;
				}
			}
			
			this.tNothingRegistered = false;
		}

		GT_OreDictUnificator.addToBlacklist(aFluidEvent.data.filledContainer);
	}
	
	public boolean addSonictronSound(ItemStack aItemStack, String aSoundName) {
		if (aItemStack == null || aSoundName == null || aSoundName.equals("")) return false;
		mSoundItems.add(aItemStack);
		mSoundNames.add(aSoundName);
		if (aSoundName.startsWith("note.")) {
			mSoundCounts.add(25);
		} else {
			mSoundCounts.add(1);
		}
		return true;
	}

	
    public static File getSaveDirectory() {
        return mUniverse != null ? mUniverse.getSaveHandler().getWorldDirectory() : null;
    }
    
    @Override
	public boolean allowPacketToBeSent(Packet aPacket, EntityPlayerMP aPlayer) {
		return true;
	}
	
	@Override
	public boolean isServerSide() {
		return gregtechproxy.isServerSide();
	}
	
	@Override
	public boolean isClientSide() {
		return gregtechproxy.isClientSide();
	}

	@Override
	public EntityPlayer getThePlayer() {
		return gregtechproxy.getThePlayer();
	}

	public int addArmor(String aArmorPrefix) {
		return gregtechproxy.addArmor(aArmorPrefix);
	}

	public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		gregtechproxy.doSonictronSound(aStack, aWorld, aX, aY, aZ);
	}

	static {
		checkVersions();
	}
}
