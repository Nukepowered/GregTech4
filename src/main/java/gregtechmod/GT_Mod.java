package gregtechmod;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGT_Mod;
import gregtechmod.api.interfaces.IGT_RecipeAdder;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_ItsNotMyFaultException;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_DummyWorld;
import gregtechmod.common.GT_OreDictHandler;
import gregtechmod.common.GT_Proxy;
import gregtechmod.common.GT_Worldgenerator;
import gregtechmod.common.blocks.GT_BlockMetaID_Block;
import gregtechmod.common.blocks.GT_BlockMetaID_Block2;
import gregtechmod.common.blocks.GT_BlockMetaID_Machine;
import gregtechmod.common.blocks.GT_BlockMetaID_Ore;
import gregtechmod.common.covers.GT_Cover_Generic;
import gregtechmod.common.covers.GT_Cover_None;
import gregtechmod.common.covers.GT_Cover_Redstone;
import gregtechmod.common.items.GT_MetaBlock2_Item;
import gregtechmod.common.items.GT_MetaBlock_Item;
import gregtechmod.common.items.GT_MetaMachine_Item;
import gregtechmod.common.items.GT_MetaOre_Item;
import gregtechmod.common.render.GT_Block_Renderer;
import gregtechmod.loaders.misc.GT_CoverLoader;
import gregtechmod.loaders.misc.GT_TooEasyModeLoader;
import gregtechmod.loaders.preload.GT_InitHardCodedCapeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Gregorius Techneticies
 */
@Mod(modid = "gregtech_addon", name="GregTech-Addon", version="MC1710", useMetadata=false, dependencies="required-after:IC2; after:UndergroundBiomes; after:factorization; after:Railcraft; after:ThermalExpansion; after:ThermalExpansion|Transport; after:ThermalExpansion|Energy; after:ThermalExpansion|Factory; after:XyCraft; after:MetallurgyCore; after:MetallurgyBase; after:MetallurgyEnder; after:MetallurgyFantasy; after:MetallurgyNether; after:MetallurgyPrecious; after:MetallurgyUtility; after:BuildCraft|Silicon; after:BuildCraft|Core; after:BuildCraft|Transport; after:BuildCraft|Factory; after:BuildCraft|Energy; after:BuildCraft|Builders; after:LiquidUU; after:TwilightForest; after:Forestry; after:RedPowerCore; after:RedPowerBase; after:RedPowerMachine; after:RedPowerCompat; after:RedPowerWiring; after:RedPowerLogic; after:RedPowerLighting; after:RedPowerWorld; after:RedPowerControl; after:Tubestuff; after:ICBM; after:Mekanism; after:MekanismGenerators; after:MekanismTools; after:ThaumicTinkerer; after:LiquidXP; after:MineFactoryReloaded; after:TConstruct; after:factorization.misc; after:AtomicScience; after:MFFS; after:ICBM|Contraption; after:ICBM|Explosion; after:ICBM|Sentry; after:mmmPowersuits;")
// @NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {GregTech_API.GENERIC_CHANNEL, GregTech_API.SOUND_PACKET_CHANNEL, GregTech_API.TILEENTITY_PACKET_CHANNEL/*, "EB", "RC", "XT|TradeSel", "mystcraft", "fz.bounce", "TwilightForest", "mmmPowerSuits", "tfmagicmap", "FOR", "BC", "TC", "TCF", "NuclearControl", "TConstruct", "BasicComponents", "RecipeRemover", "IronChest", "MFFS", "ICBM", "CoFH", "SC2", "ICBM|C", "ICBM|E", "InventoryTweaks", "BIN", "tfmazemap", "BCLP"/**/}, packetHandler = GT_PacketHandler.class, connectionHandler = GT_ConnectionHandler.class)
public class GT_Mod implements IGT_Mod, IGT_RecipeAdder {
    @Instance
    public static GT_Mod instance;
    
    @SidedProxy(clientSide = "gregtechmod.common.GT_Client", serverSide = "gregtechmod.common.GT_Server")
    public static GT_Proxy gregtechproxy;
    
	public static volatile int VERSION = 404;
	
    public static boolean sInventoryUnification = true, sIncreaseDungeonLoot = true, sAxeWhenAdventure = true, sSurvivalIntoAdventure = false, sPatchLightUpdateLag = false, sNerfedWoodPlank = true, sNerfedWoodenTools = true, sNerfedStoneTools = true, sInvisibleOres = false, sTinkersWarning = false, sHardRock = false, sHungerEffect = true, sUnificatorRP = false, sUnificatorTE = false, sUnificatorFR = false, sUnificatorRC = false, sUnificatorTC = false, mOnline = true, mAlreadyPlayed = false, mDetectIDConflicts = false, mDoNotInit = false;
    
	public static int sItemDespawnTime = 6000, sUpgradeCount = 4, sBarrelItemCount = 32768, sQuantumItemCount = 2000000000, sBlockStackSize = 64, sOreStackSize = 64, sWoodStackSize = 64, sPlankStackSize = 64;
    
	/** Getting set to null after the Mod has been loaded */
    public static int[] sItemIDs = new int[256], sBlockIDs = new int[] {4058, 4059, 4060, 4061, 4057, 4062};
    /** Needed for getting the Save Files */
    public static World mUniverse = null;
    /** Cape Lists! Dunno why, but as of now 9 people donated, without leaving a MC-Username for a Cape. */
    public static final ArrayList<String> sPremiumNames = new ArrayList<String>(), sAdminNames = new ArrayList<String>(), mBrainTechCapeList = new ArrayList<String>(), mGregTechCapeList = new ArrayList<String>();
    
    public static final ArrayList<String>		mSoundNames		= new ArrayList<String>();
    public static final ArrayList<ItemStack>	mSoundItems		= new ArrayList<ItemStack>();
    public static final ArrayList<Integer>		mSoundCounts	= new ArrayList<Integer>();
    
    public static String sMessage = "";
    
    static {
//    	checkVersions();
    }
    
//    private static final void checkVersions() { // Will uncomment in the end
//    	if (   VERSION != GregTech_API			.VERSION
//            || VERSION != BaseMetaTileEntity	.VERSION
//            || VERSION != BaseMetaPipeEntity	.VERSION
//            || VERSION != MetaTileEntity		.VERSION
//            || VERSION != MetaPipeEntity		.VERSION
//    	    || VERSION != GT_CircuitryBehavior	.VERSION
//    	    || VERSION != GT_CoverBehavior		.VERSION
//    		|| VERSION != GT_Config				.VERSION
//    		|| VERSION != GT_LanguageManager	.VERSION
//    		|| VERSION != GT_ModHandler			.VERSION
//    		|| VERSION != GT_OreDictUnificator	.VERSION
//    		|| VERSION != GT_Recipe				.VERSION
//    		|| VERSION != GT_Utility			.VERSION
//    	    || VERSION != GT_RecipeRegistrator	.VERSION
//    		|| VERSION != Element				.VERSION
//    		|| VERSION != Materials				.VERSION
//    		|| VERSION != OrePrefixes			.VERSION)
//    		throw new GT_ItsNotMyFaultException("One of your Mods included GregTech-API Files inside it's download, mention this to the Mod Author, who does this bad thing, and tell him/her to use reflection. I have added a Version check, to prevent Authors from breaking my Mod that way.");
//    }
    
    public GT_Mod() {
//    	checkVersions();
    	if (GregTech_API.isGregTechLoaded()) throw new GT_ItsNotMyFaultException("Why did you install my Addon twice? Remove the second gregtechmod.zip out of your mods-Folder, you need only one of them.");
		GregTech_API.gregtechmod = this;
		GregTech_API.sRecipeAdder = this;
		GregTech_API.sDummyWorld = new GT_DummyWorld();
		GregTech_API.sGTCoverload.add(new GT_CoverLoader());
        GT_OreDictHandler.instance.registerHandler();
    	new GT_Cover_None();
		new GT_Cover_Generic();
		new GT_Cover_Redstone();
    }
    
    @SuppressWarnings("rawtypes")
	@EventHandler
    public void preload(FMLPreInitializationEvent aEvent) {
//    	checkVersions();
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
    	tFile = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "BlockItemIDs.cfg");
    	Configuration tConfig2 = new Configuration(tFile);
    	tConfig2.load();
    	tFile = new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "DynamicConfig.cfg");
    	Configuration tConfig3 = new Configuration(tFile);
    	tConfig3.load();
//    	GT_Log.mLogFile = new File(aEvent.getModConfigurationDirectory().getParentFile(), "GregTech.log");
//    	if (GT_Log.mLogFile.exists()) {
//    		mAlreadyPlayed = true;
//    		try {
//    			GT_Log.out = GT_Log.err = new PrintStream(GT_Log.mLogFile);
//    		} catch (FileNotFoundException e) {
//        		GT_Log.out = System.out;
//        		GT_Log.log = System.err;
//    		}
//    	}
//    	
    	mDoNotInit = (!tFile.getAbsolutePath().toLowerCase().contains("voltz")) && (tFile.getAbsolutePath().toLowerCase().contains(".technic") || tFile.getAbsolutePath().toLowerCase().contains("tekkit"));
    	if (mDoNotInit) {
            GT_Log.log.warn("GT_Mod: Detected Technic Launcher.");
            GT_Log.log.warn("GT_Mod: Errored.");
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
    	
    	if (Loader.isModLoaded("TConstruct")) sTinkersWarning = new Configuration(new File(aEvent.getModConfigurationDirectory(), "TinkersWorkshop.txt")).get("difficulty changes", "Enable Auto-Smelt and Fortune interaction", true).getBoolean(true);
    	
    	GT_Log.log.info("GT_Mod: Preload-Phase started!");
    	GregTech_API.sPreloadStarted = true;
    	
    	new GT_InitHardCodedCapeList().run();
    	
    	GT_Log.log.info("GT_Mod: Creating Config Object.");
    	GregTech_API.sConfiguration = new GT_Config(tConfig1, tConfig2, tConfig3);
    	
    	GT_Log.log.info("GT_Mod: Setting Configs");
    	if (GT_Config.sConfigFileStandard.get("general", "TooEasyMode", false).getBoolean(false)) GregTech_API.sAfterGTPostload.add(new GT_TooEasyModeLoader());
    	GregTech_API.DEBUG_MODE							= GT_Config.sConfigFileStandard.get("general", "Debug", false).getBoolean(false);
    	GregTech_API.SECONDARY_DEBUG_MODE				= GT_Config.sConfigFileStandard.get("general", "Debug2", false).getBoolean(false);
    	
    	if (GT_Config.sConfigFileStandard.get("general", "disable_STDOUT", false).getBoolean(false)) System.out.close();
    	if (GT_Config.sConfigFileStandard.get("general", "disable_STDERR", false).getBoolean(false)) System.err.close();
    	
    	GregTech_API.UE_ENERGY_COMPATIBILITY			= GT_Config.sConfigFileStandard.get("compatibility", "UniversalElectricity.Energy"	, true ).getBoolean(true);
    	GregTech_API.IC_ENERGY_COMPATIBILITY			= GT_Config.sConfigFileStandard.get("compatibility", "Industrialcraft.Energy"		, true ).getBoolean(true);
    	GregTech_API.BC_ENERGY_COMPATIBILITY			= GT_Config.sConfigFileStandard.get("compatibility", "Buildcraft.Energy"			, true ).getBoolean(true);
    	
    	GregTech_API.sMachineExplosions					= GT_Config.sConfigFileStandard.get("machines", "machines_explosion_damage"			, true ).getBoolean(false);
    	GregTech_API.sMachineFlammable					= GT_Config.sConfigFileStandard.get("machines", "machines_flammable"				, true ).getBoolean(false);
    	GregTech_API.sMachineFireExplosions				= GT_Config.sConfigFileStandard.get("machines", "fire_causes_explosions"			, true ).getBoolean(false);
    	GregTech_API.sMachineNonWrenchExplosions		= GT_Config.sConfigFileStandard.get("machines", "explosions_on_nonwrenching"		, true ).getBoolean(false);
    	GregTech_API.sMachineWireFire					= GT_Config.sConfigFileStandard.get("machines", "wirefire_on_explosion"				, true ).getBoolean(false);
    	GregTech_API.sMachineRainExplosions				= GT_Config.sConfigFileStandard.get("machines", "rain_causes_explosions"			, true ).getBoolean(false);
    	GregTech_API.sMachineThunderExplosions			= GT_Config.sConfigFileStandard.get("machines", "lightning_causes_explosions"		, true ).getBoolean(false);
    	GregTech_API.sConstantEnergy					= GT_Config.sConfigFileStandard.get("machines", "constant_need_of_energy"			, true ).getBoolean(false);
    	GregTech_API.sColoredGUI						= GT_Config.sConfigFileStandard.get("machines", "colored_guis_when_painted"			, true ).getBoolean(false);
    	
    	sUnificatorTC			= GT_Config.sConfigFileStandard.get("unificatortargets", "Thaumcraft"		, true ).getBoolean(false);
    	sUnificatorRP			= GT_Config.sConfigFileStandard.get("unificatortargets", "Redpower"			, true ).getBoolean(false);
    	sUnificatorRC			= GT_Config.sConfigFileStandard.get("unificatortargets", "Railcraft"		, false).getBoolean(false);
    	sUnificatorTE			= GT_Config.sConfigFileStandard.get("unificatortargets", "ThermalExpansion"	, false).getBoolean(false);
    	sUnificatorFR			= GT_Config.sConfigFileStandard.get("unificatortargets", "Forestry"			, false).getBoolean(false);
    	
    	sItemDespawnTime		= GT_Config.sConfigFileStandard.get("general", "ItemDespawnTime"			, 6000 ).getInt(6000);
    	sIncreaseDungeonLoot	= GT_Config.sConfigFileStandard.get("general", "IncreaseDungeonLoot"		, true ).getBoolean(true);
    	sAxeWhenAdventure		= GT_Config.sConfigFileStandard.get("general", "AdventureModeStartingAxe"	, true ).getBoolean(true);
    	sSurvivalIntoAdventure	= GT_Config.sConfigFileStandard.get("general", "forceAdventureMode"			, false).getBoolean(false);
    	sHungerEffect			= GT_Config.sConfigFileStandard.get("general", "AFK_Hunger"					, false).getBoolean(false);
    	sHardRock				= GT_Config.sConfigFileStandard.get("general", "harderstone"				, false).getBoolean(false);
    	sInvisibleOres			= GT_Config.sConfigFileStandard.get("general", "hiddenores"					, true ).getBoolean(true);
    	sInventoryUnification	= GT_Config.sConfigFileStandard.get("general", "InventoryUnification"		, true ).getBoolean(true);
    	sNerfedWoodPlank		= GT_Config.sConfigFileStandard.get("general", "WoodNeedsSawForCrafting"	, true ).getBoolean(true);
    	sNerfedWoodenTools		= GT_Config.sConfigFileStandard.get("general", "smallerWoodToolDurability"	, true ).getBoolean(true);
    	sNerfedStoneTools		= GT_Config.sConfigFileStandard.get("general", "smallerStoneToolDurability"	, true ).getBoolean(true);
    	//sPatchLightUpdateLag	= GT_Config.sConfigFileStandard.get("general", "patchingLightUpdateLag"		, true ).getBoolean(true);
    	float tScrapChance		= GT_Config.sConfigFileStandard.get("general", "weightForScrapFromScrapboxing", 200).getInt(200);
//        
//    	sBlockIDs[ 0] = GT_Config.sConfigFileIDs.getBlock("Block"					,  sBlockIDs[ 0]).getInt();
//    	sBlockIDs[ 1] = GT_Config.sConfigFileIDs.getBlock("Machine"					,  sBlockIDs[ 1]).getInt();
//    	sBlockIDs[ 2] = GT_Config.sConfigFileIDs.getBlock("Ore"						,  sBlockIDs[ 2]).getInt();
//    	sBlockIDs[ 3] = GT_Config.sConfigFileIDs.getBlock("LightSource"				,  sBlockIDs[ 3]).getInt();
//    	sBlockIDs[ 4] = GT_Config.sConfigFileIDs.getBlock("Block2"					,  sBlockIDs[ 4]).getInt();
//    	sBlockIDs[ 5] = GT_Config.sConfigFileIDs.getBlock("Stone1"					,  sBlockIDs[ 5]).getInt();
//    	
//    	sItemIDs[  0] = GT_Config.sConfigFileIDs.getItem("MATERIALS"  				, 21000).getInt();
//    	sItemIDs[  1] = GT_Config.sConfigFileIDs.getItem("DUSTS"  					, 21001).getInt();
//    	sItemIDs[  2] = GT_Config.sConfigFileIDs.getItem("CELLS"  					, 21002).getInt();
//    	sItemIDs[  3] = GT_Config.sConfigFileIDs.getItem("COMPONENTS"  				, 21003).getInt();
//    	sItemIDs[  4] = GT_Config.sConfigFileIDs.getItem("SMALLDUSTS"  				, 21004).getInt();
//    	sItemIDs[  5] = GT_Config.sConfigFileIDs.getItem("NUGGETS"  				, 21005).getInt();
//    	sItemIDs[  6] = GT_Config.sConfigFileIDs.getItem("DIRTYDUSTS"  				, 21006).getInt();
//    	sItemIDs[  7] = GT_Config.sConfigFileIDs.getItem("TINYDUSTS"  				, 21007).getInt();
//    	
//    	sItemIDs[ 15] = GT_Config.sConfigFileIDs.getItem("LIQUIDDISPLAY"  			, 21015).getInt();
//    	sItemIDs[ 16] = GT_Config.sConfigFileIDs.getItem("NCSensorCard"				, 21016).getInt();
//    	sItemIDs[ 17] = GT_Config.sConfigFileIDs.getItem("NCSensorKit"				, 21017).getInt();
//    	sItemIDs[ 18] = GT_Config.sConfigFileIDs.getItem("CheatyDevice"				, 21018).getInt();
//    	
//    	sItemIDs[ 30] = GT_Config.sConfigFileIDs.getItem("IronMortar"				, 21030).getInt();
//    	sItemIDs[ 31] = GT_Config.sConfigFileIDs.getItem("Mortar"					, 21031).getInt();
//    	sItemIDs[ 32] = GT_Config.sConfigFileIDs.getItem("HandheldSonictron"		, 21032).getInt();
//    	sItemIDs[ 33] = GT_Config.sConfigFileIDs.getItem("Destructopack"			, 21033).getInt();
//    	sItemIDs[ 34] = GT_Config.sConfigFileIDs.getItem("Heliumcoolant060k"		, 21034).getInt();
//    	sItemIDs[ 35] = GT_Config.sConfigFileIDs.getItem("Heliumcoolant120k"		, 21035).getInt();
//    	sItemIDs[ 36] = GT_Config.sConfigFileIDs.getItem("Heliumcoolant180k"		, 21036).getInt();
//    	sItemIDs[ 37] = GT_Config.sConfigFileIDs.getItem("LapotronicEnergycrystal"	, 21037).getInt();
//    	sItemIDs[ 38] = GT_Config.sConfigFileIDs.getItem("CloakingDevice"			, 21038).getInt();
//    	sItemIDs[ 39] = GT_Config.sConfigFileIDs.getItem("JackHammerIron"			, 21039).getInt();
//    	sItemIDs[ 40] = GT_Config.sConfigFileIDs.getItem("Neutronreflector"			, 21040).getInt();
//    	sItemIDs[ 41] = GT_Config.sConfigFileIDs.getItem("JackHammerSteel"			, 21041).getInt();
//    	sItemIDs[ 42] = GT_Config.sConfigFileIDs.getItem("JackHammerDiamond"		, 21042).getInt();
//    	sItemIDs[ 43] = GT_Config.sConfigFileIDs.getItem("Dataorb"					, 21043).getInt();
//    	sItemIDs[ 44] = GT_Config.sConfigFileIDs.getItem("Lamphelmet"				, 21044).getInt();
//    	sItemIDs[ 45] = GT_Config.sConfigFileIDs.getItem("Lapotronpack"				, 21045).getInt();
//    	sItemIDs[ 46] = GT_Config.sConfigFileIDs.getItem("Rockcutter"				, 21046).getInt();
//    	sItemIDs[ 47] = GT_Config.sConfigFileIDs.getItem("Teslastaff"				, 21047).getInt();
//    	sItemIDs[ 48] = GT_Config.sConfigFileIDs.getItem("Thorium1"					, 21048).getInt();
//    	sItemIDs[ 49] = GT_Config.sConfigFileIDs.getItem("Thorium2"					, 21049).getInt();
//    	sItemIDs[ 50] = GT_Config.sConfigFileIDs.getItem("Thorium4"					, 21050).getInt();
//    	sItemIDs[ 51] = GT_Config.sConfigFileIDs.getItem("Plutonium1"				, 21051).getInt();
//    	sItemIDs[ 52] = GT_Config.sConfigFileIDs.getItem("Plutonium2"				, 21052).getInt();
//    	sItemIDs[ 53] = GT_Config.sConfigFileIDs.getItem("Plutonium4"				, 21053).getInt();
//    	sItemIDs[ 54] = GT_Config.sConfigFileIDs.getItem("LithiumCell"				, 21054).getInt();
//    	sItemIDs[ 55] = GT_Config.sConfigFileIDs.getItem("DebugScanner"				, 21055).getInt();
//    	sItemIDs[ 56] = GT_Config.sConfigFileIDs.getItem("LithiumbatteryEmpty"		, 21056).getInt();
//    	sItemIDs[ 57] = GT_Config.sConfigFileIDs.getItem("LithiumbatteryFull"		, 21057).getInt();
//		sItemIDs[ 58] = GT_Config.sConfigFileIDs.getItem("Lithiumpack"				, 21058).getInt();
//		//Shield
//		sItemIDs[ 60] = GT_Config.sConfigFileIDs.getItem("NaKcoolant060k"			, 21060).getInt();
//    	sItemIDs[ 61] = GT_Config.sConfigFileIDs.getItem("NaKcoolant120k"			, 21061).getInt();
//    	sItemIDs[ 62] = GT_Config.sConfigFileIDs.getItem("NaKcoolant180k"			, 21062).getInt();
//    	sItemIDs[ 63] = GT_Config.sConfigFileIDs.getItem("Scanner"					, 21063).getInt();
//    	sItemIDs[ 64] = GT_Config.sConfigFileIDs.getItem("Crowbar"					, 21064).getInt();
//    	sItemIDs[ 65] = GT_Config.sConfigFileIDs.getItem("Screwdriver"				, 21065).getInt();
//    	sItemIDs[ 66] = GT_Config.sConfigFileIDs.getItem("WrenchSteel"				, 21066).getInt();
//    	sItemIDs[ 67] = GT_Config.sConfigFileIDs.getItem("WrenchRefIron"			, 21067).getInt();
//    	sItemIDs[ 68] = GT_Config.sConfigFileIDs.getItem("WrenchTungstensteel"		, 21068).getInt();
//    	sItemIDs[ 69] = GT_Config.sConfigFileIDs.getItem("WrenchBronze"				, 21069).getInt();
//    	sItemIDs[ 70] = GT_Config.sConfigFileIDs.getItem("WrenchElectric"			, 21070).getInt();
//    	sItemIDs[ 71] = GT_Config.sConfigFileIDs.getItem("WrenchAdvanced"			, 21071).getInt();
//    	sItemIDs[ 72] = GT_Config.sConfigFileIDs.getItem("HammerRubber"				, 21072).getInt();
//    	sItemIDs[ 73] = GT_Config.sConfigFileIDs.getItem("HammerIron"				, 21073).getInt();
//    	sItemIDs[ 74] = GT_Config.sConfigFileIDs.getItem("HammerBronze"				, 21074).getInt();
//    	sItemIDs[ 75] = GT_Config.sConfigFileIDs.getItem("HammerSteel"				, 21075).getInt();
//    	sItemIDs[ 76] = GT_Config.sConfigFileIDs.getItem("HammerTungstenSteel"		, 21076).getInt();
//    	sItemIDs[ 77] = GT_Config.sConfigFileIDs.getItem("SolderingTool"			, 21077).getInt();
//    	sItemIDs[ 78] = GT_Config.sConfigFileIDs.getItem("SolderingTin"				, 21078).getInt();
//    	sItemIDs[ 79] = GT_Config.sConfigFileIDs.getItem("SolderingLead"			, 21079).getInt();
//    	sItemIDs[ 80] = GT_Config.sConfigFileIDs.getItem("TurbineBronze"			, 21080).getInt();
//    	sItemIDs[ 81] = GT_Config.sConfigFileIDs.getItem("TurbineSteel"				, 21081).getInt();
//    	sItemIDs[ 82] = GT_Config.sConfigFileIDs.getItem("TurbineMagnalium"			, 21082).getInt();
//    	sItemIDs[ 83] = GT_Config.sConfigFileIDs.getItem("TurbineTungstensteel"		, 21083).getInt();
//    	sItemIDs[ 84] = GT_Config.sConfigFileIDs.getItem("TurbineCarbon"			, 21084).getInt();
//    	sItemIDs[ 85] = GT_Config.sConfigFileIDs.getItem("LavaFilter"				, 21085).getInt();
//    	sItemIDs[ 86] = GT_Config.sConfigFileIDs.getItem("FileIron"					, 21086).getInt();
//    	sItemIDs[ 87] = GT_Config.sConfigFileIDs.getItem("FileBronze"				, 21087).getInt();
//    	sItemIDs[ 88] = GT_Config.sConfigFileIDs.getItem("FileSteel"				, 21088).getInt();
//    	sItemIDs[ 89] = GT_Config.sConfigFileIDs.getItem("FileTungstenSteel"		, 21089).getInt();
//    	sItemIDs[ 90] = GT_Config.sConfigFileIDs.getItem("Spray_Bug"				, 21090).getInt();
//    	sItemIDs[ 91] = GT_Config.sConfigFileIDs.getItem("Spray_Ice"				, 21091).getInt();
//    	sItemIDs[ 92] = GT_Config.sConfigFileIDs.getItem("Spray_Hardener"			, 21092).getInt();
//    	sItemIDs[ 93] = GT_Config.sConfigFileIDs.getItem("Spray_CFoam"				, 21093).getInt();
//    	sItemIDs[ 94] = GT_Config.sConfigFileIDs.getItem("Spray_Pepper"				, 21094).getInt();
//    	sItemIDs[ 95] = GT_Config.sConfigFileIDs.getItem("Spray_Hydration"			, 21095).getInt();
//    	sItemIDs[ 96] = GT_Config.sConfigFileIDs.getItem("Spray_00"					, 21096).getInt();
//    	sItemIDs[ 97] = GT_Config.sConfigFileIDs.getItem("Spray_01"					, 21097).getInt();
//    	sItemIDs[ 98] = GT_Config.sConfigFileIDs.getItem("Spray_02"					, 21098).getInt();
//    	sItemIDs[ 99] = GT_Config.sConfigFileIDs.getItem("Spray_03"					, 21099).getInt();
//    	sItemIDs[100] = GT_Config.sConfigFileIDs.getItem("Spray_04"					, 21100).getInt();
//    	sItemIDs[101] = GT_Config.sConfigFileIDs.getItem("Spray_05"					, 21101).getInt();
//    	sItemIDs[102] = GT_Config.sConfigFileIDs.getItem("Spray_06"					, 21102).getInt();
//    	sItemIDs[103] = GT_Config.sConfigFileIDs.getItem("Spray_07"					, 21103).getInt();
//    	sItemIDs[104] = GT_Config.sConfigFileIDs.getItem("Spray_08"					, 21104).getInt();
//    	sItemIDs[105] = GT_Config.sConfigFileIDs.getItem("Spray_09"					, 21105).getInt();
//    	sItemIDs[106] = GT_Config.sConfigFileIDs.getItem("Spray_10"					, 21106).getInt();
//    	sItemIDs[107] = GT_Config.sConfigFileIDs.getItem("Spray_11"					, 21107).getInt();
//    	sItemIDs[108] = GT_Config.sConfigFileIDs.getItem("Spray_12"					, 21108).getInt();
//    	sItemIDs[109] = GT_Config.sConfigFileIDs.getItem("Spray_13"					, 21109).getInt();
//    	sItemIDs[110] = GT_Config.sConfigFileIDs.getItem("Spray_14"					, 21110).getInt();
//    	sItemIDs[111] = GT_Config.sConfigFileIDs.getItem("Spray_15"					, 21111).getInt();
//    	sItemIDs[112] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_01"			, 21112).getInt();
//    	sItemIDs[113] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_02"			, 21113).getInt();
//    	sItemIDs[114] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_03"			, 21114).getInt();
//    	sItemIDs[115] = GT_Config.sConfigFileIDs.getItem("Saw_Iron"					, 21115).getInt();
//    	sItemIDs[116] = GT_Config.sConfigFileIDs.getItem("Saw_Bronze"				, 21116).getInt();
//    	sItemIDs[117] = GT_Config.sConfigFileIDs.getItem("Saw_Steel"				, 21117).getInt();
//    	sItemIDs[118] = GT_Config.sConfigFileIDs.getItem("Saw_Tungstensteel"		, 21118).getInt();
//    	sItemIDs[119] = GT_Config.sConfigFileIDs.getItem("Saw_Electric"				, 21119).getInt();
//    	sItemIDs[120] = GT_Config.sConfigFileIDs.getItem("Saw_Advanced"				, 21120).getInt();
//    	sItemIDs[121] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_04"			, 21121).getInt();
//    	sItemIDs[122] = GT_Config.sConfigFileIDs.getItem("Empty_Tool_05"			, 21122).getInt();
//    	sItemIDs[123] = GT_Config.sConfigFileIDs.getItem("Drill_Advanced"			, 21123).getInt();
//    	sItemIDs[124] = GT_Config.sConfigFileIDs.getItem("Flint_Sword"				, 21124).getInt();
//    	sItemIDs[125] = GT_Config.sConfigFileIDs.getItem("Flint_Pickaxe"			, 21125).getInt();
//    	sItemIDs[126] = GT_Config.sConfigFileIDs.getItem("Flint_Shovel"				, 21126).getInt();
//    	sItemIDs[127] = GT_Config.sConfigFileIDs.getItem("Flint_Axe"				, 21127).getInt();
//    	sItemIDs[128] = GT_Config.sConfigFileIDs.getItem("Flint_Hoe"				, 21128).getInt();
//    	sItemIDs[129] = GT_Config.sConfigFileIDs.getItem("Steel_Sword"				, 21129).getInt();
//    	sItemIDs[130] = GT_Config.sConfigFileIDs.getItem("Steel_Pickaxe"			, 21130).getInt();
//    	sItemIDs[131] = GT_Config.sConfigFileIDs.getItem("Steel_Shovel"				, 21131).getInt();
//    	sItemIDs[132] = GT_Config.sConfigFileIDs.getItem("Steel_Axe"				, 21132).getInt();
//    	sItemIDs[133] = GT_Config.sConfigFileIDs.getItem("Steel_Hoe"				, 21133).getInt();
//    	sItemIDs[134] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Sword"		, 21134).getInt();
//    	sItemIDs[135] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Pickaxe"	, 21135).getInt();
//    	sItemIDs[136] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Shovel"		, 21136).getInt();
//    	sItemIDs[137] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Axe"		, 21137).getInt();
//    	sItemIDs[138] = GT_Config.sConfigFileIDs.getItem("TungstenSteel_Hoe"		, 21138).getInt();
    	
    	mOnline = GT_Config.sConfigFileStandard.get("general", "online", true).getBoolean(false);
    	GT_BlockMetaID_Block.mConnectedMachineTextures = GT_Config.sConfigFileStandard.get("general", "ConnectedMachineCasingTextures", true).getBoolean(false);
    	
    	GregTech_API.sTinCellCountPer4Ingots = Math.min(64, Math.max(1, GT_Config.sConfigFileStandard.get("features", "TincellsPer4Tin", 4).getInt()));
		
    	sUpgradeCount													= Math.min(64, Math.max( 1, GT_Config.sConfigFileStandard.get("features", "UpgradeStacksize"		,  4).getInt()));
    	sOreStackSize													= Math.min(64, Math.max(16, GT_Config.sConfigFileStandard.get("features", "MaxOreStackSize"			, 64).getInt()));
    	sWoodStackSize													= Math.min(64, Math.max(16, GT_Config.sConfigFileStandard.get("features", "MaxLogStackSize"			, 64).getInt()));
    	sPlankStackSize													= Math.min(64, Math.max(16, GT_Config.sConfigFileStandard.get("features", "MaxPlankStackSize"		, 64).getInt()));
    	sBlockStackSize													= Math.min(64, Math.max(16, GT_Config.sConfigFileStandard.get("features", "MaxOtherBlockStackSize"	, 64).getInt()));
    	
    	sBarrelItemCount												= Math.max(193, GT_Config.sConfigFileStandard.get("features", "DigitalChestMaxItemCount", 32768).getInt());
    	sQuantumItemCount												= Math.max(sBarrelItemCount, GT_Config.sConfigFileStandard.get("features", "QuantumChestMaxItemCount", 2000000000).getInt());
    	
    	GregTech_API.sTinCellCountPer4Ingots = Math.min(64, Math.max(1, GregTech_API.sTinCellCountPer4Ingots));
    	
    	GT_Worldgenerator.sAsteroids			= GregTech_API.sConfiguration.addAdvConfig("worldgen.end", "EnderAsteroids"	, true );
    	GT_Worldgenerator.sGeneratedOres[ 9]	= GregTech_API.sConfiguration.addAdvConfig("worldgen.end", "Tungstateore"	, true );
    	GT_Worldgenerator.sGeneratedOres[10]	= GregTech_API.sConfiguration.addAdvConfig("worldgen.end", "Cooperiteore"	, true );
    	GT_Worldgenerator.sGeneratedOres[11]	= GregTech_API.sConfiguration.addAdvConfig("worldgen.end", "Olivineore"		, true );
    	GT_Worldgenerator.sGeneratedOres[12]	= GregTech_API.sConfiguration.addAdvConfig("worldgen.end", "Sodaliteore"	, true );
    	
    	GT_Config.system = (Calendar.getInstance().get(2) + 1 == 4 && Calendar.getInstance().get(5) >= 1 && Calendar.getInstance().get(5) <= 2);
    	
    	Materials.init(GregTech_API.sConfiguration);
    	
    	GT_Log.log.info("GT_Mod: Saving Configs");
    	GT_Config.sConfigFileStandard.save();
    	GT_Config.sConfigFileIDs.save();
    	
    	GT_Log.log.info("GT_Mod: Generating Lang-File"); // TODO move localization to resources
    	GT_LanguageManager.sLangFile = new Configuration(new File(new File(aEvent.getModConfigurationDirectory(), "GregTech"), "GregTech.lang"));
    	GT_LanguageManager.sLangFile.load();
    	
    	GT_Log.log.info("GT_Mod: Removing all original Scrapbox Drops.");
        try {
        	GT_Utility.getField("ic2.core.item.ItemScrapbox$Drop", "topChance", true, true).set(null, 0);
        	((List)GT_Utility.getFieldContent(GT_Utility.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", true, true), "drops", true, true)).clear();
        } catch(Throwable e) {
        	if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
        }
        
        
        GT_Log.log.info("GT_Mod: Adding Scrap with a Weight of " + tScrapChance + " to the Scrapbox Drops.");
        GT_ModHandler.addScrapboxDrop(tScrapChance, GT_ModHandler.getIC2Item("scrap", 1));
        
		if (isClientSide()) {
	        GT_Log.log.info("GT_Mod: Register BlockRenderer");
		    new GT_Block_Renderer();
	        GT_Log.log.info("GT_Mod: Downloading Cape List.");
		    try {new Thread(new Runnable() {@Override public void run() {try {
				Scanner tScanner = new Scanner(new URL("https://dl.dropbox.com/u/88825306/CapeList.txt").openStream());
				while (tScanner.hasNextLine()) {
				    String tName = tScanner.nextLine();
				    if (!GT_Mod.mGregTechCapeList.contains(tName.toLowerCase())) GT_Mod.mGregTechCapeList.add(tName.toLowerCase());
				}
			} catch(Throwable e) {}}}).start();} catch(Throwable e) {}
	        GT_Log.log.info("GT_Mod: Downloading News.");
		    try {new Thread(new Runnable() {@Override public void run() {try {
				Scanner tScanner = new Scanner(new URL("https://dl.dropboxusercontent.com/u/88825306/Message.txt").openStream());
			    while (tScanner.hasNextLine()) sMessage += tScanner.nextLine() + " ";
			} catch(Throwable e) {}}}).start();} catch(Throwable e) {}
		}
    	
        GregTech_API.sPreloadFinished = true;
        GT_Log.log.info("GT_Mod: Preload-Phase finished!");
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
    	if (mDoNotInit) return;
    	
    	for (Runnable tRunnable : GregTech_API.sBeforeGTLoad) {
    		try {
    			tRunnable.run();
    		} catch(Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    	
//    	checkVersions();
        GT_Log.log.info("GT_Mod: Beginning Load-Phase.");
    	GregTech_API.sLoadStarted = true;
    	
    	GT_Log.log.info("GT_Mod: Adding Blocks.");
		GameRegistry.registerBlock(GregTech_API.sBlockList[0] = new GT_BlockMetaID_Block  (), GT_MetaBlock_Item.class	, "GT_Block");
		GameRegistry.registerBlock(GregTech_API.sBlockList[1] = new GT_BlockMetaID_Machine(), GT_MetaMachine_Item.class	, GT_LanguageManager.mNameList1[0]);
		GameRegistry.registerBlock(GregTech_API.sBlockList[2] = new GT_BlockMetaID_Ore	  (), GT_MetaOre_Item.class		, GT_LanguageManager.mNameList2[0]);
		GameRegistry.registerBlock(GregTech_API.sBlockList[4] = new GT_BlockMetaID_Block2 (), GT_MetaBlock2_Item.class	, GT_LanguageManager.mNameList3[0]);
//		GameRegistry.registerBlock(GregTech_API.sBlockList[3] = new GT_Block_LightSource  (), ItemBlock.class			, "GT_TransparentTileEntity"	  );
//		GameRegistry.registerBlock(GregTech_API.sBlockList[5] = new GT_BlockMetaID_Stone1 (), GT_MetaStone1_Item.class	, GT_LanguageManager.mNameList4[0]);
//		
//		LanguageRegistry.addName(GregTech_API.sBlockList[0], GT_LanguageManager.mRegionalNameList0[0]);
//		LanguageRegistry.addName(GregTech_API.sBlockList[1], GT_LanguageManager.mRegionalNameList1[0]);
//		LanguageRegistry.addName(GregTech_API.sBlockList[2], GT_LanguageManager.mRegionalNameList2[0]);
//		LanguageRegistry.addName(GregTech_API.sBlockList[4], GT_LanguageManager.mRegionalNameList3[0]);
//		LanguageRegistry.addName(GregTech_API.sBlockList[5], GT_LanguageManager.mRegionalNameList4[0]);
//		
//		for (int i=0;i<16;i++) {
//			GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Block."		+ GT_LanguageManager.mNameList0[i] + ".name", GT_LanguageManager.mRegionalNameList0[i]);
//			GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Machine."	+ GT_LanguageManager.mNameList1[i] + ".name", GT_LanguageManager.mRegionalNameList1[i]);
//			GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Ore."		+ GT_LanguageManager.mNameList2[i] + ".name", GT_LanguageManager.mRegionalNameList2[i]);
//			GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Block2."		+ GT_LanguageManager.mNameList3[i] + ".name", GT_LanguageManager.mRegionalNameList3[i]);
//			GT_LanguageManager.addStringLocalization("tile.BlockMetaID_Stone1."		+ GT_LanguageManager.mNameList4[i] + ".name", GT_LanguageManager.mRegionalNameList4[i]);
//		}
//		
//		GregTech_API.registerMachineBlock(GregTech_API.sBlockList[0].blockID, (1|2|64|1024|8192|16384|32768));
//		GregTech_API.registerMachineBlock(GregTech_API.sBlockList[1].blockID, (1));
//		GregTech_API.registerMachineBlock(GregTech_API.sBlockList[4].blockID, (256|512|8192));
//		
//        GT_Log.out.println("GT_Mod: Register old TileEntities.");
//		GameRegistry.registerTileEntity(GT_TileEntity_ComputerCube.class	, GT_LanguageManager.mNameList1[ 4]);
//		GameRegistry.registerTileEntity(GT_TileEntity_Sonictron.class		, GT_LanguageManager.mNameList1[ 6]);
//		GameRegistry.registerTileEntity(GT_TileEntity_Superconductor.class	, GT_LanguageManager.mNameList1[12]);
//		GameRegistry.registerTileEntity(GT_TileEntity_PlayerDetector.class	, GT_LanguageManager.mNameList1[13]);
//		GameRegistry.registerTileEntity(GT_TileEntity_LightSource.class		, "GT_LightSource");
//		
//		GameRegistry.registerTileEntity(GregTech_API.constructBaseMetaTileEntity().getClass(), "MetatileEntity");
//		GameRegistry.registerTileEntity(BaseMetaPipeEntity.class, "MetaPipeEntity");
//		
//		GT_Log.out.println("GT_Mod: Testing BaseMetaTileEntity.");
//		if (GregTech_API.constructBaseMetaTileEntity() == null) {
//			GT_Log.out.println("GT_Mod: Fatal Error ocurred while initializing TileEntities, crashing Minecraft.");
//			throw new RuntimeException("");
//		}
//		
//		new GT_MetaTileEntityLoader().run();
//		new GT_DictRegistratorPreItem().run();
//		new GT_ItemLoader().run();
//		new GT_DictRegistratorPostItem().run();
//		new GT_CircuitBehaviorLoad().run();
//		
//        GT_Log.out.println("GT_Mod: Adding Configs specific for MetaTileEntities");
//    	try {
//	    	for (IMetaTileEntity tMetaTileEntity : GregTech_API.mMetaTileList) {
//	    		if (tMetaTileEntity != null) tMetaTileEntity.onConfigLoad(GregTech_API.sConfiguration);
//	    	}
//    	} catch(Throwable e) {
//    		e.printStackTrace(GT_Log.err);
//    	}
//    	
//    	GregTech_API.sLoadFinished = true;
//        GT_Log.out.println("GT_Mod: Load-Phase finished!");
//    	for (Runnable tRunnable : GregTech_API.sAfterGTLoad) {
//    		try {
//    			tRunnable.run();
//    		} catch(Throwable e) {
//    			e.printStackTrace(GT_Log.err);
//    		}
//    	}
	}
	
    @EventHandler
    public void postload(FMLPostInitializationEvent aEvent) {
//    	if (mDoNotInit) return;
//    	
//    	for (Runnable tRunnable : GregTech_API.sBeforeGTPostload) {
//    		try {
//    			tRunnable.run();
//    		} catch(Throwable e) {
//    			e.printStackTrace(GT_Log.err);
//    		}
//    	}
//    	
//    	checkVersions();
//    	
//        GT_Log.out.println("GT_Mod: Beginning PostLoad-Phase.");
//    	GregTech_API.sPostloadStarted = true;
//        
//        GT_Log.out.println("GT_Mod: Checking if Items got overloaded.");
//		for (int i = 0; i < GregTech_API.sItemList.length; i++) {
//			if (GregTech_API.sItemList[i] != null && Item.itemsList[GregTech_API.sItemList[i].itemID] != GregTech_API.sItemList[i]) {
//		        GT_Log.err.println("GT_Mod: Another Mods ItemID is conflicting.");
//		        GT_Log.err.println("GT_Mod: Errored.");
//				throw new GT_ItsNotMyFaultException("One of the GregTech-Items got overloaded. Check the ID-Config of the Mod you just installed for Conflicts mentioned in the ForgeModLoader0.log with 'CONFLICT @'. That is an Item-ID-Conflict! Don't bother ANY Modauthor with that, we won't help you at all! Also if you are one of the very few Idiots, who can't read this (I'm not assuming you are that stupid Person, ID-Conflicts can happen to everyone), then don't f***ing go to my Thread and complain!!! Yes, that rage was fully justified, as I got a few Idiots already.");
//			}
//		}
//		
//    	new GT_ItemIterator().run();
//		
//		new GT_LiquidAndFuelLoader().run();
//        new GT_ItemMaxStacksizeLoader().run();
//    	new GT_BlockResistanceLoader().run();
//        new GT_RecyclerBlacklistLoader().run();
//		new GT_MinableRegistrator().run();
//		new GT_SeedFlowerIterator().run();
//		new GT_CraftingRecipeLoader().run();
//    	new GT_BookAndLootLoader().run();
//        new GT_MachineRecipeLoader().run();
//        new GT_ScrapboxDropLoader().run();
//        new GT_UUMRecipeLoader().run();
//		new GT_CropLoader().run();
//		new GT_Worldgenloader().run();
//        new GT_SonictronLoader().run();
//        new GT_RecyclingRecipeLoader().run();
//        
//        GT_Log.out.println("GT_Mod: Activating OreDictionary Handler, this can take some time, as it scans the whole OreDictionary");
//        System.out.println("If your Log stops here, you were too impatient. Wait a bit more next time, before killing Minecraft with the Task Manager.");
//        GT_OreDictHandler.instance.activateHandler();
//        System.out.println("Congratulations, you have been waiting long enough. Have a Cake.");
//        
//        GT_Log.out.println("GT_Mod: Adding Stone related Recipes");
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 0), new ItemStack(GregTech_API.sBlockList[5], 1, 7));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 1), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 2), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 3), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 4), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 5), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 6), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 7), new ItemStack(GregTech_API.sBlockList[5], 1, 0));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 8), new ItemStack(GregTech_API.sBlockList[5], 1,15));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 9), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,10), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,11), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,12), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,13), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,14), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
//        GT_ModHandler.addSmeltingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,15), new ItemStack(GregTech_API.sBlockList[5], 1, 8));
//		GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 3), null, new ItemStack(GregTech_API.sBlockList[5], 1, 4), 200, 4);
//		GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,11), null, new ItemStack(GregTech_API.sBlockList[5], 1,12), 200, 4);
//		GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 7), null, new ItemStack(GregTech_API.sBlockList[5], 1, 6), 200, 4);
//		GregTech_API.sRecipeAdder.addAssemblerRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,15), null, new ItemStack(GregTech_API.sBlockList[5], 1,14), 200, 4);
//        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.stoneBrick          , 1, 3), new Object[] {new ItemStack(Block.stoneDoubleSlab     , 1, 8), GT_ToolDictNames.craftingToolFile});
//        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 6), new Object[] {new ItemStack(GregTech_API.sBlockList[5], 1, 7), GT_ToolDictNames.craftingToolFile});
//        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,14), new Object[] {new ItemStack(GregTech_API.sBlockList[5], 1,15), GT_ToolDictNames.craftingToolFile});
//        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.cobblestone         , 1, 0), new Object[] {new ItemStack(Block.stone               , 1, 0), GT_ToolDictNames.craftingToolHardHammer});
//        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.stoneBrick          , 1, 2), new Object[] {new ItemStack(Block.stoneBrick          , 1, 0), GT_ToolDictNames.craftingToolHardHammer});
//        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1, 4), new Object[] {new ItemStack(GregTech_API.sBlockList[5], 1, 3), GT_ToolDictNames.craftingToolHardHammer});
//        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 1,12), new Object[] {new ItemStack(GregTech_API.sBlockList[5], 1,11), GT_ToolDictNames.craftingToolHardHammer});
//        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 4, 3), new Object[] {"XX", "XX", 'X', new ItemStack(GregTech_API.sBlockList[5], 4, 0)});
//        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 4,11), new Object[] {"XX", "XX", 'X', new ItemStack(GregTech_API.sBlockList[5], 4, 8)});
//        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 4, 3), new Object[] {"XX", "XX", 'X', new ItemStack(GregTech_API.sBlockList[5], 4, 7)});
//        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[5], 4,11), new Object[] {"XX", "XX", 'X', new ItemStack(GregTech_API.sBlockList[5], 4,15)});
//        
//        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.stoneDoubleSlab, 1, 8), false, new Object[] {new ItemStack(Block.stoneDoubleSlab, 1, 0)});
//		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Block.stoneDoubleSlab, 1, 0), false, new Object[] {new ItemStack(Block.stoneDoubleSlab, 1, 8)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.stoneDoubleSlab		, 1, 0), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.stoneSingleSlab, 1, 0)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.cobblestone			, 1, 0), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.stoneSingleSlab, 1, 3)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.brick				, 1, 0), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.stoneSingleSlab, 1, 4)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.stoneBrick			, 1, 0), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.stoneSingleSlab, 1, 5)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.netherBrick			, 1, 0), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.stoneSingleSlab, 1, 6)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.blockNetherQuartz	, 1, 0), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.stoneSingleSlab, 1, 7)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.stoneDoubleSlab		, 1, 8), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.stoneSingleSlab, 1, 8)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.planks				, 1, 0), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.woodSingleSlab, 1, 0)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.planks				, 1, 1), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.woodSingleSlab, 1, 1)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.planks				, 1, 2), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.woodSingleSlab, 1, 2)});
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Block.planks				, 1, 3), false, false, new Object[] {"B", "B"	, 'B', new ItemStack(Block.woodSingleSlab, 1, 3)});
//		
//		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.stick, 2, 0), false, new Object[] {new ItemStack(Block.deadBush, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
//		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Item.stick, 2, 0), false, new Object[] {new ItemStack(Block.tallGrass, 1, 0)});
//		
//		GT_ModHandler.addCraftingRecipe(new ItemStack(Item.comparator, 1, 0), false, false, new Object[] {" T ", "TQT", "SSS", 'Q', "craftingQuartz", 'S', "stoneSmooth", 'T', "craftingRedstoneTorch"});
//		
//		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "ic2forgehammer", true)) {
//			GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("ForgeHammer", 1));
//		}
//        
//        ItemStack tMat = new ItemStack(Item.ingotIron), tStack;
//        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, "Iron.PressurePlate", true))
//			if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, null, null, null, null, null, null, null})))
//				GT_ModHandler.addCraftingRecipe(tStack, new Object[] {              "XXT", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench});
//		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, "Iron.Bucket", true))
//			if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, null, tMat, null, null, null, null})))
//				GT_ModHandler.addCraftingRecipe(tStack, new Object[] {       "XTX", " X ", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench});
//		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, "Iron.Minecart", true))
//			if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, tMat, tMat, tMat, null, null, null})))
//				GT_ModHandler.addCraftingRecipe(tStack, new Object[] {       "XTX", "XXX", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench});
//		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, "Iron.Door", true))
//			if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, null, tMat, tMat, null, tMat, tMat, null})))
//				GT_ModHandler.addCraftingRecipe(tStack, new Object[] {"XX ", "XXT", "XX ", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench});
//		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, "Iron.Cauldron", true))
//			if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, tMat, null, tMat, tMat, tMat, tMat})))
//				GT_ModHandler.addCraftingRecipe(tStack, new Object[] {"X X", "XTX", "XXX", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench});
//		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, "Iron.Hopper", true))
//			if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {tMat, null, tMat, tMat, new ItemStack(Block.chest, 1, 0), tMat, null, tMat, null})))
//				GT_ModHandler.addCraftingRecipe(tStack, new Object[] {"XWX", "XCX", " X ", 'X', "plateIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench, 'C', "craftingChest"});
//		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, "Iron.Bars", true))
//			if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, tMat, tMat, tMat, tMat, null, null, null}))) {
//				tStack.stackSize /= 2;
//				GT_ModHandler.addCraftingRecipe(tStack, new Object[] {" W ", "XXX", "XXX", 'X', "stickIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench});
//			}
//        
//    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("ironFence", 6), new Object[] {"XXX", "XXX", " W ", 'X', "stickIron", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotIron", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench});
//    	
//    	tMat = new ItemStack(Item.ingotGold);
//    	
//    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.recipereplacements, "Gold.PressurePlate", true))
//			if (null != (tStack = GT_ModHandler.removeRecipe(new ItemStack[] {tMat, tMat, null, null, null, null, null, null, null})))
//				GT_ModHandler.addCraftingRecipe(tStack, new Object[] {              "XXT", 'X', "plateGold", 'T', GT_ToolDictNames.craftingToolHardHammer, 'S', "stickWood", 'I', "ingotGold", 'F', GT_ToolDictNames.craftingToolFile, 'W', GT_ToolDictNames.craftingToolWrench});
//        
//        
//    	tStack = GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Block.planks, 1, 0), null, null, new ItemStack(Block.planks, 1, 0)});
//    	if (tStack != null) {
//    		GT_ModHandler.addCraftingRecipe(GT_Utility.copy(sNerfedWoodPlank?tStack.stackSize:(tStack.stackSize * 5) / 4, tStack), new Object[] {"S", "P", "P", 'P', "plankWood", 'S', GT_ToolDictNames.craftingToolSaw});
//		    GT_ModHandler.addCraftingRecipe(GT_Utility.copy(sNerfedWoodPlank?tStack.stackSize/2:tStack.stackSize, tStack), new Object[] {"P", "P", 'P', "plankWood"});
//    	}
//    	
//    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.pressurePlatePlanks, 1, 0), new Object[] {"PP", 'P', "plankWood"}));
//		/*
//		GT_Log.out.println("GT_Mod: Adding Default Description Set of the Computer Cube");
//		GT_ComputercubeDescription.addStandardDescriptions();
//		*/
//        if (sNerfedWoodenTools) {
//    		GT_Log.out.println("GT_Mod: Nerfing Wood Tool Durability");
//        	Item.swordWood.setMaxDamage(12);
//        	Item.pickaxeWood.setMaxDamage(12);
//        	Item.shovelWood.setMaxDamage(12);
//        	Item.axeWood.setMaxDamage(12);
//        	Item.hoeWood.setMaxDamage(12);
//        }
//        
//        if (sNerfedStoneTools) {
//    		GT_Log.out.println("GT_Mod: Nerfing Stone Tool Durability");
//        	Item.swordStone.setMaxDamage(48);
//        	Item.pickaxeStone.setMaxDamage(48);
//        	Item.shovelStone.setMaxDamage(48);
//        	Item.axeStone.setMaxDamage(48);
//        	Item.hoeStone.setMaxDamage(48);
//        }
//        
//		if (!isClientSide()) {
//			GT_Log.out.println("GT_Mod: Starting Cover Load Phase Serverside");
//			GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSilver")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateRuby")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSapphire")		, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateAluminium")		, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTitanium")		, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateChrome")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateSteel")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateBrass")			, "");
//			GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateLead")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateElectrum")		, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateZinc")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateOlivine")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGreenSapphire")	, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("platePlatinum")		, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTungsten")		, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateNickel")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateTungstenSteel")	, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateAlloyIridium")	, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateInvar")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateOsmium")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateIridium")			, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateDenseBronze")		, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGarnetYellow")	, "");
//	    	GregTech_API.registerCover(GT_OreDictUnificator.getOres("plateGarnetRed")		, "");
//	    	
//	    	for (Runnable tRunnable : GregTech_API.sGTCoverload) {
//	    		try {
//	    			tRunnable.run();
//	    		} catch(Throwable e) {
//	    			e.printStackTrace(GT_Log.err);
//	    		}
//	    	}
//		}
//		
//		new GT_CoverBehaviorLoader().run();
//		
//		if (sPatchLightUpdateLag) {
//	        GT_Log.out.println("GT_Mod: Patching Light Update Lag");
//			try {
//				Chunk.class.getMethods()[Chunk.class.getMethods().length - 2] = Chunk.class.getMethods()[Chunk.class.getMethods().length - 3];
//			} catch(Throwable e) {
//				if (GregTech_API.DEBUG_MODE) e.printStackTrace(GT_Log.err);
//			}
//		}
//		
//        GT_Log.out.println("GT_Mod: Adding buffered Recipes.");
//        GT_ModHandler.stopBufferingCraftingRecipes();
//        
//    	GregTech_API.sPostloadFinished = true;
//        GT_Log.out.println("GT_Mod: PostLoad-Phase finished!");
//        
//        if (GregTech_API.DEBUG_MODE) {
//	        try {
//	        	GT_Log.out.println("GT_Mod: Printing registered Channels");
//	        	com.google.common.collect.ArrayListMultimap<String, Object> tMap = (com.google.common.collect.ArrayListMultimap<String, Object>)GT_Utility.getField(NetworkRegistry.instance(), "universalPacketHandlers").get(NetworkRegistry.instance());
//	        	for (String tLine : tMap.keySet()) GT_Log.out.println(tLine);
//	        } catch(Throwable e) {
//	        	e.printStackTrace(GT_Log.err);
//	        }
//        }
//        
//    	for (Runnable tRunnable : GregTech_API.sAfterGTPostload) {
//    		try {
//    			tRunnable.run();
//    		} catch(Throwable e) {
//    			e.printStackTrace(GT_Log.err);
//    		}
//    	}
//    	
//        GT_Log.out.println("GT_Mod: Loading finished, deallocating temporary Init Variables.");
//    	sItemIDs = null;
//    	sBlockIDs = null;
//    	
//    	GregTech_API.sBeforeGTPreload = null;
//    	GregTech_API.sAfterGTPreload = null;
//    	GregTech_API.sBeforeGTLoad = null;
//    	GregTech_API.sAfterGTLoad = null;
//    	GregTech_API.sBeforeGTPostload = null;
//    	GregTech_API.sAfterGTPostload = null;
    }
    /*
    @EventHandler
    public void loadcomplete(FMLLoadCompleteEvent aEvent) {
    	// Why is this not being called?
    }
    */
    @EventHandler
    public void start(FMLServerStartingEvent aEvent) {
//    	if (mDoNotInit) return;
//    	
//    	for (Runnable tRunnable : GregTech_API.sBeforeGTServerstart) {
//    		try {
//    			tRunnable.run();
//    		} catch(Throwable e) {
//    			e.printStackTrace(GT_Log.err);
//    		}
//    	}
//    	mUniverse = null;
//    	GT_TickHandler.isFirstTick = true;
//		new GT_GUIHandler();
//    	
//    	try {
//	    	for (IMetaTileEntity tMetaTileEntity : GregTech_API.mMetaTileList) {
//	    		if (tMetaTileEntity != null) tMetaTileEntity.onServerStart();
//	    	}
//    	} catch(Throwable e) {
//    		e.printStackTrace(GT_Log.err);
//    	}
    	
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
    	
//    	for (Runnable tRunnable : GregTech_API.sAfterGTServerstart) {
//    		try {
//    			tRunnable.run();
//    		} catch(Throwable e) {
//    			e.printStackTrace(GT_Log.err);
//    		}
//    	}
    }
    
    @EventHandler
    public void start(FMLServerStartedEvent aEvent) {
//    	if (mDoNotInit) return;
//    	GT_Recipe.reinit();
    }
    
    @EventHandler
    public void stop(FMLServerStoppingEvent aEvent) {
//    	if (mDoNotInit) return;
//    	
//    	for (Runnable tRunnable : GregTech_API.sBeforeGTServerstop) {
//    		try {
//    			tRunnable.run();
//    		} catch(Throwable e) {
//    			e.printStackTrace(GT_Log.err);
//    		}
//    	}
//    	
//    	writeIDSUData();
//    	mUniverse = null;
//    	GregTech_API.sWirelessRedstone.clear();
//    	
//    	try {
//	    	for (IMetaTileEntity tMetaTileEntity : GregTech_API.mMetaTileList) {
//	    		if (tMetaTileEntity != null) tMetaTileEntity.onServerStop();
//	    	}
//    	} catch(Throwable e) {
//    		e.printStackTrace(GT_Log.err);
//    	}
//    	
//    	try {
//		if (GregTech_API.DEBUG_MODE || GT_Log.out != System.out) {
//			if (GregTech_API.DEBUG_MODE) System.out.println("BEGIN GregTech-Item-Print");
//			GT_Log.out.println("*");
//			GT_Log.out.println("Printing List of all registered Objects inside the OreDictionary, now with free extra Sorting:");
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//			
//			String[] tList = OreDictionary.getOreNames();
//			Arrays.sort(tList);
//			for (String tOreName : tList) {
//				int tAmount = OreDictionary.getOres(tOreName).size();
//				if (tAmount > 0) GT_Log.out.println((tAmount<10?" ":"") + tAmount + "x " + tOreName);
//			}
//			
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//			GT_Log.out.println("Outputting all the Names inside the Itemslist, this List can become very long");
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//			
//			for (int i = 0; i < Item.itemsList.length; i++) {
//		    	if (Item.itemsList[i] != null) {
//		    		GT_Log.out.println(Item.itemsList[i].getUnlocalizedName());
//					if (Item.itemsList[i].getHasSubtypes()) {
//						String tName = "";
//						for (int j = 0; j < 16; j++) {
//							try {
//							tName = Item.itemsList[i].getUnlocalizedName(new ItemStack(Item.itemsList[i], 1, j));
//				    		if (tName != null && !tName.equals(""))
//				    			GT_Log.out.println(j + ": " + Item.itemsList[i].getUnlocalizedName());
//							} catch (Throwable e) {}
//						}
//					}
//		    	}
//		    }
//			
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//			GT_Log.out.println("Outputting all the Names registered by Railcraft");
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//			
//			try {
//				for (String tName : mods.railcraft.api.core.items.TagList.getTags())
//				GT_Log.out.println(tName);
//			} catch (Throwable e) {}
//			
//			if (GregTech_API.DEBUG_MODE) {
//				System.out.println("*"); System.out.println("*"); System.out.println("*");
//				System.out.println("Outputting all the Names registered by Thermal Expansion");
//				System.out.println("*"); System.out.println("*"); System.out.println("*");
//				
//				try {
//					thermalexpansion.api.item.ItemRegistry.printItemNames();
//				} catch (Throwable e) {}
//			}
//			
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//			GT_Log.out.println("Outputting all the Names inside the Biomeslist");
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//			
//			for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
//		    	if (BiomeGenBase.biomeList[i] != null)
//		    		GT_Log.out.println(BiomeGenBase.biomeList[i].biomeID + " = " + BiomeGenBase.biomeList[i].biomeName);
//		    }
//			
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//			GT_Log.out.println("END GregTech-Debug");
//			GT_Log.out.println("*"); GT_Log.out.println("*"); GT_Log.out.println("*");
//    	}
//		} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) e.printStackTrace(GT_Log.err);}
//    	for (Runnable tRunnable : GregTech_API.sAfterGTServerstop) {
//    		try {
//    			tRunnable.run();
//    		} catch(Throwable e) {
//    			e.printStackTrace(GT_Log.err);
//    		}
//    	}
    }
    
	public boolean addFusionReactorRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, int aStartEU) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("fusionreactor", aOutput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aInput2, aOutput1, aDuration, aEUt, aStartEU);
		return true;
	}
	
	public boolean addCentrifugeRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("centrifuge", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aInput2>0?GT_ModHandler.getEmptyCell(aInput2):aInput2<0?GT_ModHandler.getEmptyFuelCan(-aInput2):null, aOutput1, aOutput2, aOutput3, aOutput4, aDuration);
		return true;
	}
	
	public boolean addElectrolyzerRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("electrolyzer", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aInput2>0?GT_ModHandler.getEmptyCell(aInput2):aInput2<0?GT_ModHandler.getEmptyFuelCan(-aInput2):null, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt);
		return true;
	}
	
	public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("chemicalreactor", aOutput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aInput2, aOutput1, aDuration);
		return true;
	}
	
	public boolean addBlastRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("blastfurnace", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aDuration, aEUt, aLevel);
		return true;
	}
	
	public boolean addCannerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("canning", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aEUt, aInput2, aDuration, aOutput1, aOutput2);
		return true;
	}
	
	public boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if (aInput2 == null && (OrePrefixes.ingot.contains(aInput1) || OrePrefixes.dust.contains(aInput1) || OrePrefixes.gem.contains(aInput1))) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("alloysmelting", aInput2==null?aInput1:aOutput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aInput2, aEUt, aDuration, aOutput1);
		return true;
	}
	
	public boolean addCNCRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("cnc", aOutput1, aDuration)) <= 0) return false;
		//XXXnew GT_Recipe(aInput1, aEUt, aOutput1, aDuration);
		return true;
	}
	
	public boolean addLatheRecipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("lathe", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aOutput1, aOutput2, aDuration, aEUt);
		return true;
	}
	
	public boolean addCutterRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("cutting", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aDuration, aOutput1, aEUt);
		return true;
	}
	
	public boolean addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("assembling", aOutput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aEUt, aInput2, aDuration, aOutput1);
		return true;
	}
	
	public boolean addWiremillRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("wiremill", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aEUt, aDuration, aOutput1);
		return true;
	}
	
	public boolean addBenderRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("bender", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aEUt, aDuration, aInput1, aOutput1);
		return true;
	}
	
	public boolean addImplosionRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aInput2   = GregTech_API.sConfiguration.addAdvConfig("implosion", aInput1, aInput2)) <= 0) return false;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2);
		return true;
	}
	
	public boolean addDistillationRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("distillation", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt);
		return true;
	}
	
	public boolean addVacuumFreezerRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration) {
		if (aInput1 == null || aOutput1 == null) return false;
		if ((aDuration = GregTech_API.sConfiguration.addAdvConfig("vacuumfreezer", aInput1, aDuration)) <= 0) return false;
		new GT_Recipe(aInput1, aOutput1, aDuration);
		return true;
	}
	
	public boolean addGrinderRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		return addGrinderRecipe(aInput1, GT_ModHandler.getWaterCell(-aInput2), aOutput1, aOutput2, aOutput3, aOutput4);
	}
	
	public boolean addGrinderRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		if (aInput1 == null || aOutput1 == null) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig("grinder", aInput1, true)) return false;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4);
		return true;
	}
	
	public boolean addSawmillRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		return addSawmillRecipe(aInput1, GT_ModHandler.getWaterCell(-aInput2), aOutput1, aOutput2, aOutput3);
	}
	
	public boolean addSawmillRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		if (aInput1 == null || aOutput1 == null) return false;
		if (!GregTech_API.sConfiguration.addAdvConfig("industrialsawmill", aInput1, true)) return false;
		new GT_Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3);
		return true;
	}
	
	public boolean addFuel(ItemStack aInput1, ItemStack aOutput1, int aEU, int aType) {
		if (aInput1 == null) return false;
		new GT_Recipe(aInput1, aOutput1, GregTech_API.sConfiguration.addAdvConfig("fuel_"+aType, aInput1, aEU), aType);
		return true;
	}
	
	public boolean addJackHammerMinableBlock(Block aBlock, boolean aDiamondOnly) {
		if (aBlock != null && GregTech_API.sLoadFinished) {
			if (!aDiamondOnly) ((GT_Tool_Item)GregTech_API.sItemList[39]).addToBlockList(aBlock);
			if (!aDiamondOnly) ((GT_Tool_Item)GregTech_API.sItemList[41]).addToBlockList(aBlock);
			((GT_Tool_Item)GregTech_API.sItemList[42]).addToBlockList(aBlock);
			return true;
		}
		return false;
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
	
	public boolean addComputercubeDescriptionSet(ItemStack[] aItemStack, String[] aText) {
		//new GT_ComputercubeDescription(aText, aItemStack);
		//return true;
		return false;
	}
    
    public boolean allowPacketToBeSent(Packet aPacket, EntityPlayerMP aPlayer) {
    	return true;
    }
    
    public static File getSaveDirectory() {
    	if (mUniverse == null) return null;
        SaveHandler tSaveHandler = (SaveHandler)mUniverse.getSaveHandler();
        File rFile = null;
        Field[] tFields = SaveHandler.class.getDeclaredFields();
        for (int i = 0; i < tFields.length; ++i) {
            if (tFields[i].getType() == File.class)  {
            	tFields[i].setAccessible(true);
                try {
                    File tFile = (File)tFields[i].get(tSaveHandler);
                    if (rFile == null || rFile.getParentFile() == tFile) {
                    	rFile = tFile;
                    }
                } catch (Exception e) {
                	
                }
            }
        }
        return rFile;
    }
    
    public static void writeIDSUData() {
    	if (mUniverse != null && !mUniverse.isRemote) {
			try {
		        File tDirectory = getSaveDirectory();
		        if (tDirectory != null) {
			        NBTTagCompound tNBT = new NBTTagCompound();
		            NBTTagList tList = new NBTTagList();
		            Iterator<Entry<Integer, Integer>> tIterator = GregTech_API.sIDSUList.entrySet().iterator();
		            while (tIterator.hasNext()) {
		                Entry<Integer, Integer> tEntry = tIterator.next();
			            NBTTagCompound tTag = new NBTTagCompound();
			            tTag.setInteger("Hash", tEntry.getKey());
				        tTag.setInteger("EU", tEntry.getValue());
					    tList.appendTag(tTag);
		            }
				    tNBT.setTag("Energy", tList);
				    CompressedStreamTools.writeCompressed(tNBT, new FileOutputStream(new File(tDirectory, "GT_IDSU_Energyvalues.dat")));
		        }
			} catch (Throwable e) {
				e.printStackTrace();
			}
    	}
    }
    
    public static void readIDSUData() {
    	if (mUniverse != null && !mUniverse.isRemote) {
    		GregTech_API.sIDSUList.clear();
			try {
	            File tDirectory = getSaveDirectory();
	            if (tDirectory != null) {
			        NBTTagCompound tNBT = CompressedStreamTools.readCompressed(new FileInputStream(new File(tDirectory, "GT_IDSU_Energyvalues.dat")));
			        NBTTagList tList = tNBT.getTagList("Energy", 10);
			        
				    for (int i = 0; i < tList.tagCount(); i++) {
			            NBTTagCompound tTag = (NBTTagCompound)tList.getCompoundTagAt(i);
			            GregTech_API.sIDSUList.put(tTag.getInteger("Hash"), tTag.getInteger("EU"));
			        }
	        	}
			} catch (Throwable e) {
               	if (!(e instanceof java.io.FileNotFoundException))
               		e.printStackTrace();
			}
	    }
    }
    
	@Override
	public boolean isServerSide() {
		return gregtechproxy.isServerSide();
	}
	
	@Override
	public boolean isClientSide() {
		return gregtechproxy.isClientSide();
	}
}
