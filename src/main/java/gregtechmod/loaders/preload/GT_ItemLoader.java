package gregtechmod.loaders.preload;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.items.GT_Durable_Item;
import gregtechmod.api.items.GT_File_Item;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.items.GT_Saw_Item;
import gregtechmod.api.items.GT_Scoop_Item;
import gregtechmod.api.items.GT_Screwdriver_Item;
import gregtechmod.api.items.GT_SoftHammer_Item;
import gregtechmod.api.items.GT_SolderingMetal_Item;
import gregtechmod.api.items.GT_Spray_Bug_Item;
import gregtechmod.api.items.GT_Spray_Color_Item;
import gregtechmod.api.items.GT_Spray_Foam_Item;
import gregtechmod.api.items.GT_Spray_Hardener_Item;
import gregtechmod.api.items.GT_Spray_Hydration_Item;
import gregtechmod.api.items.GT_Spray_Ice_Item;
import gregtechmod.api.items.GT_Spray_Pepper_Item;
import gregtechmod.api.util.GT_FoodStat;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.items.GT_Dataorb_Item;
import gregtechmod.common.items.GT_Debug_Item;
import gregtechmod.common.items.GT_Destructopack_Item;
import gregtechmod.common.items.GT_FluidDisplayItem;
import gregtechmod.common.items.GT_IntegratedCircuit_Item;
import gregtechmod.common.items.GT_MetaGenerated_Item_01;
import gregtechmod.common.items.GT_MetaGenerated_Item_02;
import gregtechmod.common.items.GT_MetaItem_Cell;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.items.GT_MetaItem_DirtyDust;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_Material;
import gregtechmod.common.items.GT_MetaItem_Nugget;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import gregtechmod.common.items.GT_MetaItem_TinyDust;
import gregtechmod.common.items.GT_Mortar_Item;
import gregtechmod.common.items.GT_NeutronReflector_Item;
import gregtechmod.common.items.GT_Scanner_Item;
import gregtechmod.common.items.GT_SensorKit_Item;
import gregtechmod.common.items.GT_Sonictron_Item;
import gregtechmod.common.items.GT_Teslastaff_Item;
import gregtechmod.common.items.GT_Vanilla_Axe;
import gregtechmod.common.items.GT_Vanilla_Hoe;
import gregtechmod.common.items.GT_Vanilla_Pickaxe;
import gregtechmod.common.items.GT_Vanilla_Shovel;
import gregtechmod.common.items.GT_Vanilla_Sword;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class GT_ItemLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("Register Armor Textures.");
		int tArmorID1 = GT_Mod.gregtechproxy.addArmor("lapotronpack"), tArmorID2 = GT_Mod.gregtechproxy.addArmor("lithiumbatpack"), tArmorID3 = GT_Mod.gregtechproxy.addArmor("cloakingdevice");
		
        GT_Log.log.info("Register Meta-ID Items.");
		GregTech_API.sItemList[ 0] = new GT_MetaItem_Material	("GT_Materials");
		GregTech_API.sItemList[ 1] = new GT_MetaItem_Dust		("GT_Dusts");
		GregTech_API.sItemList[ 2] = new GT_MetaItem_Cell		("GT_Cells");
		GregTech_API.sItemList[ 3] = new GT_MetaItem_Component	("GT_Components");
		GregTech_API.sItemList[ 4] = new GT_MetaItem_SmallDust	("GT_SmallDusts");
		GregTech_API.sItemList[ 5] = new GT_MetaItem_Nugget		("GT_Nuggets");
		GregTech_API.sItemList[ 6] = new GT_MetaItem_DirtyDust	("GT_DirtyDusts");
		GregTech_API.sItemList[ 7] = new GT_MetaItem_TinyDust	("GT_TinyDusts");
		
        GT_Log.log.info("Adding All Sub-Items with their OreDict and LiquidDict Entries.");
        GT_Items.Credit_Copper.set(		GT_MetaItem_Material.addItem( 0, "Copper Credit"	, null, "0.125 Credits"	, false)); // TODO: LOCALE
        GT_Items.Credit_Silver.set(		GT_MetaItem_Material.addItem( 1, "Silver Credit"	, null, "8 Credits"		, false));
        GT_Items.Credit_Gold.set(		GT_MetaItem_Material.addItem( 2, "Gold Credit"		, null, "64 Credits"	, false));
        GT_Items.Credit_Platinum.set(	GT_MetaItem_Material.addItem( 3, "Platinum Credit"	, null, "512 Credits"	, false));
        GT_Items.Credit_Osmium.set(		GT_MetaItem_Material.addItem(12, "Osmium Credit"	, null, "4096 Credits"	, false));
		GT_MetaItem_Material.addItem(  4, "Iridium Alloy Ingot"		, null, null, false);
		GT_MetaItem_Material.addItem(  5, "Hot Tungstensteel Ingot"	, OrePrefixes.ingotHot	, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(  6, "Tungstensteel Ingot"		, OrePrefixes.ingot		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(  7, "Oil Berry"				, null, null, false);
		GT_MetaItem_Material.addItem(  8, "Indigo Blossom"			, null, null, false);
		GT_MetaItem_Material.addItem(  9, "Indigo Dye"				, OrePrefixes.dye		, "Blue"					, false);
		
		GT_MetaItem_Material.addItem( 13, "Magnalium Plate"			, OrePrefixes.plate		, Materials.Magnalium		, false);
		GT_MetaItem_Material.addItem( 14, "Flour"					, OrePrefixes.dust		, Materials.Wheat			, false);
		GT_MetaItem_Material.addItem( 15, "Wood Plate"				, OrePrefixes.plank		, Materials.Wood			, false);
		GT_MetaItem_Material.addItem( 16, "Iridium Ingot"			, OrePrefixes.ingot		, Materials.Iridium			, false);
		GT_MetaItem_Material.addItem( 17, "Silver Ingot"			, OrePrefixes.ingot		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem( 18, "Aluminium Ingot"			, OrePrefixes.ingot		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem( 19, "Titanium Ingot"			, OrePrefixes.ingot		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem( 20, "Chrome Ingot"			, OrePrefixes.ingot		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem( 21, "Electrum Ingot"			, OrePrefixes.ingot		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem( 22, "Tungsten Ingot"			, OrePrefixes.ingot		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem( 23, "Lead Ingot"				, OrePrefixes.ingot		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem( 24, "Zinc Ingot"				, OrePrefixes.ingot		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem( 25, "Brass Ingot"				, OrePrefixes.ingot		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem( 26, "Steel Ingot"				, OrePrefixes.ingot		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem( 27, "Platinum Ingot"			, OrePrefixes.ingot		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem( 28, "Nickel Ingot"			, OrePrefixes.ingot		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem( 29, "Invar Ingot"				, OrePrefixes.ingot		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem( 30, "Osmium Ingot"			, OrePrefixes.ingot		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem( 31, "Antimony Ingot"			, OrePrefixes.ingot		, Materials.Antimony		, false);
		GT_MetaItem_Material.addItem( 32, "Ruby"					, OrePrefixes.gem		, Materials.Ruby			, false);
		GT_MetaItem_Material.addItem( 33, "Sapphire"				, OrePrefixes.gem		, Materials.Sapphire		, false);
		GT_MetaItem_Material.addItem( 34, "Green Sapphire"			, OrePrefixes.gem		, Materials.GreenSapphire	, false);
		GT_MetaItem_Material.addItem( 35, "Chunk of Lazurite"		, OrePrefixes.block		, Materials.Lazurite		, false);
		GT_MetaItem_Material.addItem( 36, "Silicon Plate"			, OrePrefixes.plate		, Materials.Silicon			, false);
		GT_MetaItem_Material.addItem( 37, "Olivine"					, OrePrefixes.gem		, Materials.Olivine			, false);
		GT_OreDictUnificator.addLater("gemPeridot", GT_MetaItem_Material.instance.getUnunifiedStack(37, 1));
		GT_MetaItem_Material.addItem( 38, "Thorium Ingot"			, OrePrefixes.ingot		, Materials.Thorium			, true);
		GT_MetaItem_Material.addItem( 39, "Plutonium Ingot"			, OrePrefixes.ingot		, Materials.Plutonium		, true);
		GT_MetaItem_Material.addItem( 40, "Magnalium Ingot"			, OrePrefixes.ingot		, Materials.Magnalium		, false);
		GT_MetaItem_Material.addItem( 41, "Soldering Alloy Ingot"	, OrePrefixes.ingot		, Materials.SolderingAlloy	, false);
		GT_MetaItem_Material.addItem( 42, "Battery Alloy Ingot"		, OrePrefixes.ingot		, Materials.BatteryAlloy	, false);
		GT_MetaItem_Material.addItem( 43, "Uranium Ingot"			, OrePrefixes.ingot		, Materials.Uranium			, true);
		GT_MetaItem_Material.addItem( 44, "Stainless Steel Ingot"	, OrePrefixes.ingot		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem( 45, "Cupronickel Ingot"		, OrePrefixes.ingot		, Materials.Cupronickel		, false);
		GT_MetaItem_Material.addItem( 46, "Nichrome Ingot"			, OrePrefixes.ingot		, Materials.Nichrome		, false);
		GT_MetaItem_Material.addItem( 47, "Kanthal Ingot"			, OrePrefixes.ingot		, Materials.Kanthal			, false);
		
		GT_MetaItem_Material.addItem( 54, "Red Garnet"				, OrePrefixes.gem		, Materials.GarnetRed		, false);
		GT_OreDictUnificator.addLater("gemGarnet", GT_MetaItem_Material.instance.getUnunifiedStack(54, 1));
		GT_MetaItem_Material.addItem( 55, "Yellow Garnet"			, OrePrefixes.gem		, Materials.GarnetYellow	, false);
		
		GT_MetaItem_Material.addItem( 58, "Yellow Garnet Plate"		, OrePrefixes.plate		, Materials.GarnetYellow	, false);
		GT_MetaItem_Material.addItem( 59, "Red Garnet Plate"		, OrePrefixes.plate		, Materials.GarnetRed		, false);
		GT_MetaItem_Material.addItem( 60, "Oak Plank"				, null					, null						, false);
		GT_MetaItem_Material.addItem( 61, "Spruce Plank"			, null					, null						, false);
		GT_MetaItem_Material.addItem( 62, "Birch Plank"				, null					, null						, false);
		GT_MetaItem_Material.addItem( 63, "Jungle Plank"			, null					, null						, false);
		GT_MetaItem_Material.addItem( 64, "Iron Plate"				, OrePrefixes.plate		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem( 65, "Gold Plate"				, OrePrefixes.plate		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem( 66, "Stainless Steel Plate"	, OrePrefixes.plate		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem( 67, "Tin Plate"				, OrePrefixes.plate		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem( 68, "Copper Plate"			, OrePrefixes.plate		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem( 69, "Silver Plate"			, OrePrefixes.plate		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem( 70, "Bronze Plate"			, OrePrefixes.plate		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem( 71, "Electrum Plate"			, OrePrefixes.plate		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem( 72, "Nickel Plate"			, OrePrefixes.plate		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem( 73, "Invar Plate"				, OrePrefixes.plate		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem( 74, "Lead Plate"				, OrePrefixes.plate		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem( 75, "Aluminium Plate"			, OrePrefixes.plate		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem( 76, "Chrome Plate"			, OrePrefixes.plate		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem( 77, "Titanium Plate"			, OrePrefixes.plate		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem( 78, "Steel Plate"				, OrePrefixes.plate		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem( 79, "Platinum Plate"			, OrePrefixes.plate		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem( 80, "Tungsten Plate"			, OrePrefixes.plate		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem( 81, "Brass Plate"				, OrePrefixes.plate		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem( 82, "Zinc Plate"				, OrePrefixes.plate		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem( 83, "Tungstensteel Plate"		, OrePrefixes.plate		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem( 84, "Osmium Plate"			, OrePrefixes.plate		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem( 85, "Iridium Plate"			, OrePrefixes.plate		, Materials.Iridium			, false);
		GT_MetaItem_Material.addItem( 86, "Battery Alloy Plate"		, OrePrefixes.plate		, Materials.BatteryAlloy	, false);
		GT_MetaItem_Material.addItem( 87, "Diamond Plate"			, OrePrefixes.plate		, Materials.Diamond			, false);
		GT_MetaItem_Material.addItem( 88, "Emerald Plate"			, OrePrefixes.plate		, Materials.Emerald			, false);
		GT_MetaItem_Material.addItem( 89, "Ruby Plate"				, OrePrefixes.plate		, Materials.Ruby			, false);
		GT_MetaItem_Material.addItem( 90, "Sapphire Plate"			, OrePrefixes.plate		, Materials.Sapphire		, false);
		GT_MetaItem_Material.addItem( 91, "Green Sapphire Plate"	, OrePrefixes.plate		, Materials.GreenSapphire	, false);
		GT_MetaItem_Material.addItem( 92, "Olivine Plate"			, OrePrefixes.plate		, Materials.Olivine			, false);
		GT_MetaItem_Material.addItem( 93, "Redstone Plate"			, OrePrefixes.plate		, Materials.Redstone		, false);
		GT_MetaItem_Material.addItem( 94, "Lapis Plate"				, OrePrefixes.plate		, Materials.Lapis			, false);
		GT_MetaItem_Material.addItem( 95, "Coal Plate"				, OrePrefixes.plate		, Materials.Coal			, false);
		GT_MetaItem_Material.addItem( 96, "Iron Rod"				, OrePrefixes.stick		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem( 97, "Gold Rod"				, OrePrefixes.stick		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem( 98, "Stainless Steel Rod"		, OrePrefixes.stick		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem( 99, "Tin Rod"					, OrePrefixes.stick		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem(100, "Copper Rod"				, OrePrefixes.stick		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem(101, "Silver Rod"				, OrePrefixes.stick		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem(102, "Bronze Rod"				, OrePrefixes.stick		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem(103, "Electrum Rod"			, OrePrefixes.stick		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem(104, "Nickel Rod"				, OrePrefixes.stick		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem(105, "Invar Rod"				, OrePrefixes.stick		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem(106, "Lead Rod"				, OrePrefixes.stick		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem(107, "Aluminium Rod"			, OrePrefixes.stick		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem(108, "Chrome Rod"				, OrePrefixes.stick		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem(109, "Titanium Rod"			, OrePrefixes.stick		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem(110, "Steel Rod"				, OrePrefixes.stick		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem(111, "Platinum Rod"			, OrePrefixes.stick		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem(112, "Tungsten Rod"			, OrePrefixes.stick		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem(113, "Brass Rod"				, OrePrefixes.stick		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem(114, "Zinc Rod"				, OrePrefixes.stick		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem(115, "Tungstensteel Rod"		, OrePrefixes.stick		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(116, "Osmium Rod"				, OrePrefixes.stick		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem(117, "Iridium Rod"				, OrePrefixes.stick		, Materials.Iridium			, false);
		
		GT_MetaItem_Material.addItem(256, "Diamond Lense"			, OrePrefixes.lense		, Materials.Diamond			, false);
		GT_MetaItem_Material.addItem(257, "Ruby Lense"				, OrePrefixes.lense		, Materials.Ruby			, false);
		GT_MetaItem_Material.addItem(258, "Emerald Lense"			, OrePrefixes.lense		, Materials.Emerald			, false);
		GT_MetaItem_Material.addItem(259, "Sapphire Lense"			, OrePrefixes.lense		, Materials.Sapphire		, false);
		GT_MetaItem_Material.addItem(260, "Green Sapphire Lense"	, OrePrefixes.lense		, Materials.GreenSapphire	, false);
		GT_MetaItem_Material.addItem(261, "Yellow Garnet Lense"		, OrePrefixes.lense		, Materials.GarnetYellow	, false);
		GT_MetaItem_Material.addItem(262, "Red Garnet Lense"		, OrePrefixes.lense		, Materials.GarnetRed		, false);
		GT_MetaItem_Material.addItem(263, "Olivine Lense"			, OrePrefixes.lense		, Materials.Olivine			, false);
		GT_MetaItem_Material.addItem(264, "Amber Lense"				, OrePrefixes.lense		, Materials.Amber			, false);
		GT_MetaItem_Material.addItem(265, "Tanzanite Lense"			, OrePrefixes.lense		, Materials.Tanzanite		, false);
		GT_MetaItem_Material.addItem(266, "Topaz Lense"				, OrePrefixes.lense		, Materials.Topaz			, false);

		GT_MetaItem_Material.addItem(320, "Iron Bolt"				, OrePrefixes.bolt		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem(321, "Gold Bolt"				, OrePrefixes.bolt		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem(322, "Stainless Steel Bolt"	, OrePrefixes.bolt		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem(323, "Tin Bolt"				, OrePrefixes.bolt		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem(324, "Copper Bolt"				, OrePrefixes.bolt		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem(325, "Silver Bolt"				, OrePrefixes.bolt		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem(326, "Bronze Bolt"				, OrePrefixes.bolt		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem(327, "Electrum Bolt"			, OrePrefixes.bolt		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem(328, "Nickel Bolt"				, OrePrefixes.bolt		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem(329, "Invar Bolt"				, OrePrefixes.bolt		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem(330, "Lead Bolt"				, OrePrefixes.bolt		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem(331, "Aluminium Bolt"			, OrePrefixes.bolt		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem(332, "Chrome Bolt"				, OrePrefixes.bolt		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem(333, "Titanium Bolt"			, OrePrefixes.bolt		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem(334, "Steel Bolt"				, OrePrefixes.bolt		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem(335, "Platinum Bolt"			, OrePrefixes.bolt		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem(336, "Tungsten Bolt"			, OrePrefixes.bolt		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem(337, "Brass Bolt"				, OrePrefixes.bolt		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem(338, "Zinc Bolt"				, OrePrefixes.bolt		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem(339, "Tungstensteel Bolt"		, OrePrefixes.bolt		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(340, "Osmium Bolt"				, OrePrefixes.bolt		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem(341, "Iridium Bolt"			, OrePrefixes.bolt		, Materials.Iridium			, false);

		GT_MetaItem_Material.addItem(384, "Iron Screw"				, OrePrefixes.screw		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem(385, "Gold Screw"				, OrePrefixes.screw		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem(386, "Stainless Steel Screw"	, OrePrefixes.screw		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem(387, "Tin Screw"				, OrePrefixes.screw		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem(388, "Copper Screw"			, OrePrefixes.screw		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem(389, "Silver Screw"			, OrePrefixes.screw		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem(390, "Bronze Screw"			, OrePrefixes.screw		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem(391, "Electrum Screw"			, OrePrefixes.screw		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem(392, "Nickel Screw"			, OrePrefixes.screw		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem(393, "Invar Screw"				, OrePrefixes.screw		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem(394, "Lead Screw"				, OrePrefixes.screw		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem(395, "Aluminium Screw"			, OrePrefixes.screw		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem(396, "Chrome Screw"			, OrePrefixes.screw		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem(397, "Titanium Screw"			, OrePrefixes.screw		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem(398, "Steel Screw"				, OrePrefixes.screw		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem(399, "Platinum Screw"			, OrePrefixes.screw		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem(400, "Tungsten Screw"			, OrePrefixes.screw		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem(401, "Brass Screw"				, OrePrefixes.screw		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem(402, "Zinc Screw"				, OrePrefixes.screw		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem(403, "Tungstensteel Screw"		, OrePrefixes.screw		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(404, "Osmium Screw"			, OrePrefixes.screw		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem(405, "Iridium Screw"			, OrePrefixes.screw		, Materials.Iridium			, false);
		
		GT_MetaItem_Material.addItem(448, "Iron Round"				, OrePrefixes.round		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem(449, "Gold Round"				, OrePrefixes.round		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem(450, "Stainless Steel Round"	, OrePrefixes.round		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem(451, "Tin Round"				, OrePrefixes.round		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem(452, "Copper Round"			, OrePrefixes.round		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem(453, "Silver Round"			, OrePrefixes.round		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem(454, "Bronze Round"			, OrePrefixes.round		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem(455, "Electrum Round"			, OrePrefixes.round		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem(456, "Nickel Round"			, OrePrefixes.round		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem(457, "Invar Round"				, OrePrefixes.round		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem(458, "Lead Round"				, OrePrefixes.round		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem(459, "Aluminium Round"			, OrePrefixes.round		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem(460, "Chrome Round"			, OrePrefixes.round		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem(461, "Titanium Round"			, OrePrefixes.round		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem(462, "Steel Round"				, OrePrefixes.round		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem(463, "Platinum Round"			, OrePrefixes.round		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem(464, "Tungsten Round"			, OrePrefixes.round		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem(465, "Brass Round"				, OrePrefixes.round		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem(466, "Zinc Round"				, OrePrefixes.round		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem(467, "Tungstensteel Round"		, OrePrefixes.round		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(468, "Osmium Round"			, OrePrefixes.round		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem(469, "Iridium Round"			, OrePrefixes.round		, Materials.Iridium			, false);
		
		GT_MetaItem_Nugget.addItem( 16, Materials.Iridium		, false);
		GT_MetaItem_Nugget.addItem( 17, Materials.Silver		, false);
		GT_MetaItem_Nugget.addItem( 18, Materials.Aluminium		, false);
		GT_MetaItem_Nugget.addItem( 19, Materials.Titanium		, false);
		GT_MetaItem_Nugget.addItem( 20, Materials.Chrome		, false);
		GT_MetaItem_Nugget.addItem( 21, Materials.Electrum		, false);
		GT_MetaItem_Nugget.addItem( 22, Materials.Tungsten		, false);
		GT_MetaItem_Nugget.addItem( 23, Materials.Lead			, false);
		GT_MetaItem_Nugget.addItem( 24, Materials.Zinc			, false);
		GT_MetaItem_Nugget.addItem( 25, Materials.Brass			, false);
		GT_MetaItem_Nugget.addItem( 26, Materials.Steel			, false);
		GT_MetaItem_Nugget.addItem( 27, Materials.Platinum		, false);
		GT_MetaItem_Nugget.addItem( 28, Materials.Nickel		, false);
		GT_MetaItem_Nugget.addItem( 29, Materials.Invar			, false);
		GT_MetaItem_Nugget.addItem( 30, Materials.Osmium		, false);
		GT_MetaItem_Nugget.addItem( 31, Materials.Antimony		, false);
		
		GT_MetaItem_Nugget.addItem(241, Materials.Iron			, false);
		GT_MetaItem_Nugget.addItem(243, Materials.Copper		, false);
		GT_MetaItem_Nugget.addItem(244, Materials.Tin			, false);
		GT_MetaItem_Nugget.addItem(245, Materials.Bronze		, false);
		
		GT_MetaItem_Dust.addItem(  0, "Ender Pearl Dust"		, Materials.EnderPearl		, false);
		GT_MetaItem_Dust.addItem(  1, "Ender Eye Dust"			, Materials.EnderEye		, false);
		GT_MetaItem_Dust.addItem(  2, "Lazurite Dust"			, Materials.Lazurite		, false);
		GT_MetaItem_Dust.addItem(  3, "Pyrite Dust"				, Materials.Pyrite			, false);
		GT_MetaItem_Dust.addItem(  4, "Calcite Dust"			, Materials.Calcite			, false);
		GT_MetaItem_Dust.addItem(  5, "Sodalite Dust"			, Materials.Sodalite		, false);
		GT_MetaItem_Dust.addItem(  6, "Netherrack Dust"			, Materials.Netherrack		, false);
		GT_MetaItem_Dust.addItem(  7, "Flint Dust"				, Materials.Flint			, false);
		GT_MetaItem_Dust.addItem(  8, "Sulfur Dust"				, Materials.Sulfur			, false);
		GT_MetaItem_Dust.addItem(  9, "Saltpeter Dust"			, Materials.Saltpeter		, false);
		GT_MetaItem_Dust.addItem( 10, "Endstone Dust"			, Materials.Endstone		, false);
		GT_MetaItem_Dust.addItem( 11, "Cinnabar Dust"			, Materials.Cinnabar		, false);
		GT_MetaItem_Dust.addItem( 12, "Manganese Dust"			, Materials.Manganese		, false);
		GT_MetaItem_Dust.addItem( 13, "Magnesium Dust"			, Materials.Magnesium		, false);
		GT_MetaItem_Dust.addItem( 14, "Sphalerite Dust"			, Materials.Sphalerite		, false);
		GT_MetaItem_Dust.addItem( 15, "Wood Pulp"				, Materials.Wood			, false);
		GT_MetaItem_Dust.addItem( 16, "Uranium Dust"			, Materials.Uranium			,  true);
		GT_MetaItem_Dust.addItem( 17, "Bauxite Dust"			, Materials.Bauxite			, false);
		GT_MetaItem_Dust.addItem( 18, "Aluminium Dust"			, Materials.Aluminium		, false);
		GT_MetaItem_Dust.addItem( 19, "Titanium Dust"			, Materials.Titanium		, false);
		GT_MetaItem_Dust.addItem( 20, "Chrome Dust"				, Materials.Chrome			, false);
		GT_MetaItem_Dust.addItem( 21, "Electrum Dust"			, Materials.Electrum		, false);
		GT_MetaItem_Dust.addItem( 22, "Tungsten Dust"			, Materials.Tungsten		, false);
		GT_MetaItem_Dust.addItem( 23, "Lead Dust"				, Materials.Lead			, false);
		GT_MetaItem_Dust.addItem( 24, "Zinc Dust"				, Materials.Zinc			, false);
		GT_MetaItem_Dust.addItem( 25, "Brass Dust"				, Materials.Brass			, false);
		GT_MetaItem_Dust.addItem( 26, "Steel Dust"				, Materials.Steel			, false);
		GT_MetaItem_Dust.addItem( 27, "Platinum Dust"			, Materials.Platinum		, false);
		GT_MetaItem_Dust.addItem( 28, "Nickel Dust"				, Materials.Nickel			, false);
		GT_MetaItem_Dust.addItem( 29, "Invar Dust"				, Materials.Invar			, false);
		GT_MetaItem_Dust.addItem( 30, "Osmium Dust"				, Materials.Osmium			, false);
		GT_MetaItem_Dust.addItem( 31, "Antimony Dust"			, Materials.Antimony		, false);
		GT_MetaItem_Dust.addItem( 32, "Ruby Dust"				, Materials.Ruby			, false);
		GT_MetaItem_Dust.addItem( 33, "Sapphire Dust"			, Materials.Sapphire		, false);
		GT_MetaItem_Dust.addItem( 34, "Green Sapphire Dust"		, Materials.GreenSapphire	, false);
		GT_MetaItem_Dust.addItem( 35, "Emerald Dust"			, Materials.Emerald			, false);
		GT_MetaItem_Dust.addItem( 36, "Diamond Dust"			, Materials.Diamond			, false);
		GT_MetaItem_Dust.addItem( 37, "Olivine Dust"			, Materials.Olivine			, false);
		
		GT_MetaItem_Dust.addItem( 40, "Cupronickel Dust"		, Materials.Cupronickel		, false);
		GT_MetaItem_Dust.addItem( 41, "Nichrome Dust"			, Materials.Nichrome		, false);
		GT_MetaItem_Dust.addItem( 42, "Kanthal Dust"			, Materials.Kanthal			, false);
		GT_MetaItem_Dust.addItem( 43, "Stainless Steel Dust"	, Materials.StainlessSteel	, false);
		GT_MetaItem_Dust.addItem( 44, "Galena Dust"				, Materials.Galena			, false);
		GT_MetaItem_Dust.addItem( 45, "Phosphorus Dust"			, Materials.Phosphorus		, false);
		GT_MetaItem_Dust.addItem( 46, "Obsidian Dust"			, Materials.Obsidian		, false);
		GT_MetaItem_Dust.addItem( 47, "Charcoal Dust"			, Materials.Charcoal		, false);
		
		GT_MetaItem_Dust.addItem( 54, "Red Garnet Dust"			, Materials.GarnetRed		, false);
		GT_MetaItem_Dust.addItem( 55, "Yellow Garnet Dust"		, Materials.GarnetYellow	, false);
		GT_MetaItem_Dust.addItem( 56, "Pyrope Dust"				, Materials.Pyrope			, false);
		GT_MetaItem_Dust.addItem( 57, "Almandine Dust"			, Materials.Almandine		, false);
		GT_MetaItem_Dust.addItem( 58, "Spessartine Dust"		, Materials.Spessartine		, false);
		GT_MetaItem_Dust.addItem( 59, "Andradite Dust"			, Materials.Andradite		, false);
		GT_MetaItem_Dust.addItem( 60, "Grossular Dust"			, Materials.Grossular		, false);
		GT_MetaItem_Dust.addItem( 61, "Uvarovite Dust"			, Materials.Uvarovite		, false);
		GT_MetaItem_Dust.addItem( 62, "Ashes"					, Materials.Ash				, false);
		GT_MetaItem_Dust.addItem( 63, "Dark Ashes"				, Materials.DarkAsh			, false);
		GT_MetaItem_Dust.addItem( 64, "Redrock Dust"			, Materials.Redrock			, false);
		GT_MetaItem_Dust.addItem( 65, "Marble Dust"				, Materials.Marble			, false);
		GT_MetaItem_Dust.addItem( 66, "Basalt Dust"				, Materials.Basalt			, false);
		GT_MetaItem_Dust.addItem( 67, "Iridium Dust"			, Materials.Iridium			, false);
		GT_MetaItem_Dust.addItem( 68, "Black Granite Dust"      , Materials.GraniteBlack	, false);
		GT_MetaItem_Dust.addItem( 69, "Red Granite Dust"		, Materials.GraniteRed		, false);
		GT_MetaItem_Dust.addItem( 70, "Potassium Feldspar Dust"	, Materials.PotassiumFeldspar, false);
		GT_MetaItem_Dust.addItem( 71, "Biotite Dust"			, Materials.Biotite			, false);
		
		GT_MetaItem_Dust.addItem( 80, "Thorium Dust"			, Materials.Thorium			,  true);
		GT_MetaItem_Dust.addItem( 81, "Plutonium Dust"			, Materials.Plutonium		,  true);
		
		GT_MetaItem_Dust.addItem(238, "Lapis Lazuli Dust"		, Materials.Lapis			, false);
		GT_MetaItem_Dust.addItem(239, "Tetrahedrite Dust"		, Materials.Tetrahedrite	, false);
		GT_MetaItem_Dust.addItem(240, "Coal Dust"				, Materials.Coal			, false);
		GT_MetaItem_Dust.addItem(241, "Iron Dust"				, Materials.Iron			, false);
		GT_MetaItem_Dust.addItem(242, "Gold Dust"				, Materials.Gold			, false);
		GT_MetaItem_Dust.addItem(243, "Copper Dust"				, Materials.Copper			, false);
		GT_MetaItem_Dust.addItem(244, "Tin Dust"				, Materials.Tin				, false);
		GT_MetaItem_Dust.addItem(245, "Bronze Dust"				, Materials.Bronze			, false);
		GT_MetaItem_Dust.addItem(246, "Silver Dust"				, Materials.Silver			, false);
		GT_MetaItem_Dust.addItem(247, "Clay Dust"				, Materials.Clay			, false);
		
		GT_MetaItem_SmallDust.addItem(248, "Gunpowder"			, Materials.Gunpowder		, false);
		GT_MetaItem_SmallDust.addItem(249, "Redstone Dust"		, Materials.Redstone		, false);
		GT_MetaItem_SmallDust.addItem(250, "Glowstone Dust"		, Materials.Glowstone		, false);
		GT_MetaItem_SmallDust.addItem(251, "Blaze Powder"		, Materials.Blaze			, false);
		
		GT_MetaItem_Cell.addItem(  0, "Hydrogen Cell"			, "molecule_1h"			, false, Materials.Hydrogen);
		GT_MetaItem_Cell.addItem(  1, "Deuterium Cell"			, "molecule_1d"			, false, Materials.Deuterium);
		GT_MetaItem_Cell.addItem(  2, "Tritium Cell"			, "molecule_1t"			, false, Materials.Tritium);
		GT_MetaItem_Cell.addItem(  3, "Helium Cell"				, "molecule_1he"		, false, Materials.Helium);
		GT_MetaItem_Cell.addItem(  4, "Wolframium Cell"			, "molecule_1w"			, false, Materials.Tungsten);
		GT_MetaItem_Cell.addItem(  5, "Lithium Cell"			, "molecule_1li"		, false, Materials.Lithium);
		GT_MetaItem_Cell.addItem(  6, "Helium-3 Cell"			, "molecule_1he3"		, false, Materials.Helium_3);
		GT_MetaItem_Cell.addItem(  7, "Silicon Cell"			, "molecule_1si"		, false, Materials.Silicon);
		GT_MetaItem_Cell.addItem(  8, "Carbon Cell"				, "molecule_1c"			, false, Materials.Carbon);
		GT_MetaItem_Cell.addItem(  9, "Methane Cell"			, "molecule_1me"		, false, Materials.Methane);
		GT_MetaItem_Cell.addItem( 10, "Berylium Cell"			, "molecule_1be"		, false, Materials.Beryllium);
		GT_MetaItem_Cell.addItem( 11, "Calcium Cell"			, "molecule_1ca"		, false, Materials.Calcium);
		GT_MetaItem_Cell.addItem( 12, "Sodium Cell"				, "molecule_1na"		, false, Materials.Sodium);
		GT_MetaItem_Cell.addItem( 13, "Chlorine Cell"			, "molecule_1cl"		, false, Materials.Chlorine);
		GT_MetaItem_Cell.addItem( 14, "Potassium Cell"			, "molecule_1k"			, false, Materials.Potassium);
		GT_MetaItem_Cell.addItem( 15, "Nitrogen Cell"			, "molecule_1n"			, false, Materials.Nitrogen);
		GT_MetaItem_Cell.addItem( 16, "Mercury Cell"			, "molecule_1hg"		, false, Materials.Mercury);
		GT_MetaItem_Cell.addItem( 17, "Oil Cell"				, ""					, false, Materials.Oil);
		GT_MetaItem_Cell.addItem( 18, "Diesel Cell"				, ""					, false, Materials.Fuel);
		GT_MetaItem_Cell.addItem( 19, "Ethanol Cell"			, ""					, false, Materials.Ethanol);
		GT_MetaItem_Cell.addItem( 20, "Biomass Cell"			, ""					, false, Materials.Biomass);
		
		GT_MetaItem_Cell.addItem( 22, "Nitro-Diesel Cell"		, ""					, false, Materials.NitroFuel);
		GT_MetaItem_Cell.addItem( 23, "Ice Cell"				, ""					, false, Materials.Ice);
		GT_MetaItem_Cell.addItem( 24, "Seed Oil Cell"			, ""					, false, Materials.SeedOil);
		
		GT_MetaItem_Cell.addItem( 32, "Sodium Persulfate Cell"	, "molecule_2na_2s_8o"	, false, Materials.SodiumPersulfate);
		GT_MetaItem_Cell.addItem( 33, "Calcium Carbonate Cell"	, "molecule_1ca_1c_3o"	, false, Materials.Calcite);
		GT_MetaItem_Cell.addItem( 34, "Glyceryl Cell"			, "molecule_3c_5h_3n_9o", false, Materials.Glyceryl);
		GT_MetaItem_Cell.addItem( 35, "Nitro-Coalfuel Cell"		, ""					, false, Materials.NitroCoalFuel);
		GT_MetaItem_Cell.addItem( 36, "Sulfur Cell"				, "molecule_1s"			, false, Materials.Sulfur);
		GT_MetaItem_Cell.addItem( 37, "Sodium Sulfide Cell"		, "molecule_1na_1s"		, false, Materials.SodiumSulfide);
		GT_MetaItem_Cell.addItem( 38, "Nitrogen Dioxide Cell"	, "molecule_1n_2o"		, false, Materials.NitrogenDioxide);
		GT_MetaItem_Cell.addItem( 39, "Nitrocarbon Cell"		, "molecule_1n_1c"		, false, Materials.NitroCarbon);
		GT_MetaItem_Cell.addItem( 40, "Sulfuric Acid Cell"		, "molecule_2h_1s_4o"	, false, Materials.SulfuricAcid);
		
		GT_MetaItem_Cell.addItem(131, "Helium Plasma Cell"		, "plasma_1he"			, true , Materials.Helium);
		
		GT_Items.Spray_Empty.set(GT_MetaItem_Component.addItem(56, "Empty Spray Can", "", "Used for making Sprays and storing Colors"));
		
		GT_Items.Circuit_Master.set(			GT_MetaItem_Component.addItem( 0, "Energy Flow Circuit"		, OrePrefixes.circuit.get(Materials.Master)			, "Manages large amounts of Energy"));
		GT_Items.Circuit_Elite.set(				GT_MetaItem_Component.addItem( 1, "Data Control Circuit"	, OrePrefixes.circuit.get(Materials.Elite)			, "Basic Computer Processor"));
		GT_Items.Circuit_Data.set(				GT_MetaItem_Component.addItem( 3, "Data Storage Circuit"	, OrePrefixes.circuit.get(Materials.Data)			, "Stores Data"));
		GT_Items.Circuit_Parts_Advanced.set(	GT_MetaItem_Component.addItem(24, "Advanced Circuit Parts"	, OrePrefixes.circuitPart.get(Materials.Advanced)	, "Part of advanced Circuitry"));
		GT_Items.Circuit_Board_Basic.set(		GT_MetaItem_Component.addItem(48, "Basic Circuit Board"		, OrePrefixes.circuitBoard.get(Materials.Basic)		, "Just a simple Circuit Plate"));
		GT_Items.Circuit_Board_Advanced.set(	GT_MetaItem_Component.addItem(49, "Advanced Circuit Board"	, OrePrefixes.circuitBoard.get(Materials.Advanced)	, "Standard Circuit Plate"));
		GT_Items.Circuit_Board_Elite.set(		GT_MetaItem_Component.addItem(50, "Processor Circuit Board"	, OrePrefixes.circuitBoard.get(Materials.Elite)		, "Highly advanced Circuit Plate"));
		
		GT_Items.Shape_Empty.set(				GT_MetaItem_Component.addItem(199, "Empty Shape Plate", "", "Raw Plate to make Molds and Extruder Shapes"));
	    GT_Items.Shape_Mold_Credit.set(			GT_MetaItem_Component.addItem(200, "Coinage Mold", "", "Use this unique Mold in a Printer with Industrial Credits to make them secure"));
	    GT_Items.Shape_Extruder_Plate.set(		GT_MetaItem_Component.addItem(250, "Extruder Shape (Plate)", "", "Extruder Shape for making Plates"));
	    GT_Items.Shape_Extruder_Rod.set(		GT_MetaItem_Component.addItem(251, "Extruder Shape (Rod)", "", "Extruder Shape for making Rods"));
	    GT_Items.Shape_Extruder_Bolt.set(		GT_MetaItem_Component.addItem(252, "Extruder Shape (Bolt)", "", "Extruder Shape for making Bolts"));
	    GT_Items.Shape_Extruder_Ring.set(		GT_MetaItem_Component.addItem(253, "Extruder Shape (Ring)", "", "Extruder Shape for making Rings"));
	    GT_Items.Shape_Extruder_Cell.set(		GT_MetaItem_Component.addItem(254, "Extruder Shape (Cell)", "", "Extruder Shape for making Cells"));
	    GT_Items.Shape_Extruder_Ingot.set(		GT_MetaItem_Component.addItem(255, "Extruder Shape (Ingot)", "", "Extruder Shape for, wait, can\'t we just use a Furnace?"));
	    GT_Items.Shape_Extruder_Wire.set(		GT_MetaItem_Component.addItem(256, "Extruder Shape (Wire)", "", "Extruder Shape for making Wires"));
	    GT_Items.Shape_Extruder_Casing.set(		GT_MetaItem_Component.addItem(257, "Extruder Shape (Casing)", "", "Extruder Shape for making Item Casings"));
	    GT_Items.Shape_Extruder_Pipe_Small.set(	GT_MetaItem_Component.addItem(258, "Extruder Shape (Small Pipe)", "", "Extruder Shape for making small Pipes"));
	    GT_Items.Shape_Extruder_Pipe_Medium.set(GT_MetaItem_Component.addItem(259, "Extruder Shape (Normal Pipe)", "", "Extruder Shape for making Pipes"));
	    GT_Items.Shape_Extruder_Pipe_Large.set(	GT_MetaItem_Component.addItem(260, "Extruder Shape (Large Pipe)", "", "Extruder Shape for making large Pipes"));
	    GT_Items.Shape_Extruder_Block.set(		GT_MetaItem_Component.addItem(261, "Extruder Shape (Block)", "", "Extruder Shape for making Blocks"));
	    GT_Items.Shape_Extruder_Sword.set(		GT_MetaItem_Component.addItem(262, "Extruder Shape (Sword Blade)", "", "Extruder Shape for making Swords"));
	    GT_Items.Shape_Extruder_Pickaxe.set(	GT_MetaItem_Component.addItem(263, "Extruder Shape (Pickaxe Head)", "", "Extruder Shape for making Pickaxes"));
	    GT_Items.Shape_Extruder_Shovel.set(		GT_MetaItem_Component.addItem(264, "Extruder Shape (Shovel Head)", "", "Extruder Shape for making Shovels"));
	    GT_Items.Shape_Extruder_Axe.set(		GT_MetaItem_Component.addItem(265, "Extruder Shape (Axe Head)", "", "Extruder Shape for making Axes"));
	    GT_Items.Shape_Extruder_Hoe.set(		GT_MetaItem_Component.addItem(266, "Extruder Shape (Hoe Head)", "", "Extruder Shape for making Hoes"));
	    GT_Items.Shape_Extruder_Hammer.set(		GT_MetaItem_Component.addItem(267, "Extruder Shape (Hammer Head)", "", "Extruder Shape for making Hammers"));
	    GT_Items.Shape_Extruder_File.set(		GT_MetaItem_Component.addItem(268, "Extruder Shape (File Head)", "", "Extruder Shape for making Files"));
	    GT_Items.Shape_Extruder_Saw.set(		GT_MetaItem_Component.addItem(269, "Extruder Shape (Saw Blade)", "", "Extruder Shape for making Saws"));
	    GT_Items.Shape_Extruder_Gear.set(		GT_MetaItem_Component.addItem(270, "Extruder Shape (Gear)", "", "Extruder Shape for making Gears"));
		
		GT_MetaItem_Component.addItem( 2, "Superconductor"				, GT_OreDictNames.craftingSuperconductor	, "Conducts Energy losslessly");
		GT_MetaItem_Component.addItem( 4, "Computer Monitor"			, GT_OreDictNames.craftingMonitorTier02		, "Displays things");
		GT_MetaItem_Component.addItem( 5, "Conveyor Module"				, GT_OreDictNames.craftingConveyor			, "Moves Items around");
		GT_MetaItem_Component.addItem( 6, "Pump Module"					, GT_OreDictNames.craftingPump				, "Moves Liquids around");
		GT_MetaItem_Component.addItem( 7, "Solar Panel"					, GT_OreDictNames.craftingSolarPanel		, "Makes Energy from Sun");
		GT_MetaItem_Component.addItem( 8, "Item Transport Valve"		, GT_OreDictNames.craftingItemValve			, "Moves Items and Liquids at once!");
		GT_MetaItem_Component.addItem( 9, "Drain"						, GT_OreDictNames.craftingDrain				, "Collects Liquids and Rain");
		GT_MetaItem_Component.addItem(10, "Redstone Liquid Detector"	, GT_OreDictNames.craftingLiquidMeter		, "Outputs Redstone depending on stored Liquids");
		GT_MetaItem_Component.addItem(11, "Redstone Item Detector"		, GT_OreDictNames.craftingItemMeter			, "Outputs Redstone depending on stored Items");
		GT_MetaItem_Component.addItem(12, "Energy Crystal Upgrade"		, OrePrefixes.battery.get(Materials.Elite)	, "Adds 1 Million EU to the Energy Capacity");
		GT_MetaItem_Component.addItem(13, "Lapotron Crystal Upgrade"	, OrePrefixes.battery.get(Materials.Master)	, "Adds 10 Million EU to the Energy Capacity");
		GT_MetaItem_Component.addItem(14, "Energy Orb Upgrade"			, OrePrefixes.battery.get(Materials.Ultimate), "Adds 100 Million EU to the Energy Capacity");
		GT_MetaItem_Component.addItem(15, "Redstone Energy Meter"		, GT_OreDictNames.craftingEnergyMeter		, "Outputs Redstone depending on stored Energy");
		GT_MetaItem_Component.addItem(16, "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84", GT_OreDictNames.craftingDuctTape, "If you can't fix it with this, use more of it!");
		GT_MetaItem_Component.addItem(17, "Diamond Sawblade"			, GT_OreDictNames.craftingDiamondBlade		, "Caution! This is very sharp.");
		GT_MetaItem_Component.addItem(18, "Diamond Grinder"				, GT_OreDictNames.craftingGrinder			, "Fancy Grinding Head");
		GT_MetaItem_Component.addItem(19, "Kanthal Heating Coil"		, GT_OreDictNames.craftingHeatingCoilTier01	, "Standard Heating Coil"); //33% Iron 33% Chrome 33% Aluminium
		GT_MetaItem_Component.addItem(20, "Nichrome Heating Coil"		, GT_OreDictNames.craftingHeatingCoilTier02	, "Advanced Heating Coil"); //20% Chrome 80% Nickel
		GT_MetaItem_Component.addItem(21, "Cupronickel Heating Coil"	, GT_OreDictNames.craftingHeatingCoilTier00	, "Cheap and simple Heating Coil"); //50% Copper 50% Nickel
		GT_MetaItem_Component.addItem(22, "Machine Parts"				, GT_OreDictNames.craftingMachineParts		, "Random Machine Parts");
		GT_MetaItem_Component.addItem(23, "Wolframium Grinder"			, GT_OreDictNames.craftingGrinder			, "Regular Grinding Head");
		GT_MetaItem_Component.addItem(25, "Pneumatic Generator Upgrade"	, GT_OreDictNames.craftingPneumaticGenerator, "Lets Machines accept MJ at 1MJ -> 1EU (yes, that is lossy)");
		GT_MetaItem_Component.addItem(26, "Lithium Battery Upgrade"		, OrePrefixes.battery.get(Materials.Lithium), "Adds 100000 EU to the Energy Capacity");
		GT_MetaItem_Component.addItem(27, "HV-Transformer Upgrade"		, GT_OreDictNames.craftingHVTUpgrade		, "Higher Tier of Transformer Upgrade");
		GT_MetaItem_Component.addItem(28, "RS-Energy-Cell Upgrade"		, GT_OreDictNames.craftingEnergyCellUpgrade	, "Adds 100000 MJ to the Energy Capacity");
		GT_MetaItem_Component.addItem(29, "Quantum-Chest Upgrade"		, GT_OreDictNames.craftingQuantumChestUpgrade, "Upgrades a Digital Chest to a Quantum Chest");
		GT_MetaItem_Component.addItem(30, "Active Machine Detector"		, GT_OreDictNames.craftingWorkDetector		, "Emits Restone if the Machine has work");
		GT_MetaItem_Component.addItem(31, "Redstone Machine Controller"	, GT_OreDictNames.craftingWorkController	, "This can control Machines with Redstone");
		GT_MetaItem_Component.addItem(32, "Aluminium Machine Hull"		, GT_OreDictNames.craftingRawMachineTier01	, "Machine Block");
		GT_MetaItem_Component.addItem(33, "Bronze Machine Hull"			, GT_OreDictNames.craftingRawMachineTier00	, "Cheap Machine Block");
		GT_MetaItem_Component.addItem(34, "Brass Machine Hull"			, GT_OreDictNames.craftingRawMachineTier00	, "Cheap Machine Block");
		GT_MetaItem_Component.addItem(35, "Steel Machine Hull"			, GT_OreDictNames.craftingRawMachineTier02	, "Advanced Machine Block");
		GT_MetaItem_Component.addItem(36, "Titanium Machine Hull"		, GT_OreDictNames.craftingRawMachineTier03	, "Very Advanced Machine Block");
		GT_MetaItem_Component.addItem(37, "Iron Machine Hull"			, GT_OreDictNames.craftingRawMachineTier01	, "Machine Block");
		GT_MetaItem_Component.addItem(38, "Tungstensteel Machine Hull"	, GT_OreDictNames.craftingRawMachineTier03	, "Very Advanced Machine Block");
		GT_MetaItem_Component.addItem(39, "Stainless Machine Hull"		, GT_OreDictNames.craftingRawMachineTier02	, "Advanced Machine Block");
		
		GT_MetaItem_Component.addItem(51, "Bronze Turbine Blade"		, GT_OreDictNames.craftingTurbineBladeBronze, "Heavy Turbine Blade");
		GT_MetaItem_Component.addItem(52, "Steel Turbine Blade"			, GT_OreDictNames.craftingTurbineBladeSteel	, "Standard Turbine Blade");
		GT_MetaItem_Component.addItem(53, "Magnalium Turbine Blade"		, GT_OreDictNames.craftingTurbineBladeMagnalium, "Light Turbine Blade");
		GT_MetaItem_Component.addItem(54, "Tungstensteel Turbine Blade"	, GT_OreDictNames.craftingTurbineBladeTungstenSteel, "Durable Turbine Blade");
		GT_MetaItem_Component.addItem(55, "Carbon Turbine Blade"		, GT_OreDictNames.craftingTurbineBladeCarbon, "Ultralight Turbine Blade");
		//GT_MetaItem_Component.addItem(57, "Empty Battery Hull"			, "craftingBatteryHull"					, "For making Batteries");
		//GT_MetaItem_Component.addItem(58, "Empty Fuel Can"				, "craftingFuelCan"						, "Mainly for storing Fuel");
		
		GT_MetaItem_Component.addItem(64, "Crafting Module"				, GT_OreDictNames.craftingWorkBench			, "A Workbench on a Cover");
		GT_MetaItem_Component.addItem(65, "LV Solar Panel"				, GT_OreDictNames.craftingSolarPanelLV		, "Makes Energy from Sun at 8EU/t");
		GT_MetaItem_Component.addItem(66, "MV Solar Panel"				, GT_OreDictNames.craftingSolarPanelMV		, "Makes Energy from Sun at 64 EU/t");
		GT_MetaItem_Component.addItem(67, "HV Solar Panel"				, GT_OreDictNames.craftingSolarPanelHV		, "Makes Energy from Sun at 512 EU/t");
		GT_MetaItem_Component.addItem(69, "Shutter"						, GT_OreDictNames.craftingShutter			, "Blocks Inventory/Tank Side. Usage together with Machine Controller.");
		
		GT_MetaItem_Component.addItem(80, "Steam Upgrade"				, GT_OreDictNames.craftingSteamUpgrade		, "Lets Machines consume Steam at 2mB per EU (lossless)");
		GT_MetaItem_Component.addItem(81, "Steam Tank"					, GT_OreDictNames.craftingSteamTank			, "Increases Steam Capacity by 64 Buckets");
		GT_MetaItem_Component.addItem(82, "Redstone Receiver (IN)"		, GT_OreDictNames.craftingRedstoneReceiver	, "Receives Signals into the attached Machine");
		GT_MetaItem_Component.addItem(83, "Redstone Receiver (OUT)"		, GT_OreDictNames.craftingRedstoneReceiver	, "Receives Signals out of the attached Machine");
		GT_MetaItem_Component.addItem(84, "Redstone Transmitter (IN)"	, GT_OreDictNames.craftingRedstoneTransmitter, "Transmitts Signals from the attached Machine");
		GT_MetaItem_Component.addItem(85, "Redstone Transmitter (OUT)"	, GT_OreDictNames.craftingRedstoneTransmitter, "Transmitts Signals at the attached Machine");
		GT_MetaItem_Component.addItem(86, "Redstone Conductor"			, GT_OreDictNames.craftingRedstoneConductor	, "Throughputs Redstone to the Cover Facing");
		GT_MetaItem_Component.addItem(87, "Redstone Signalizer"			, GT_OreDictNames.craftingRedstoneSignalizer, "Applies a constant Redstone Signal to a Machine");
		GT_MetaItem_Component.addItem(88, "Lock Upgrade"				, GT_OreDictNames.craftingLock				, "Makes a Machine private for the one, who applies the Upgrade");
		GT_MetaItem_Component.addItem(89, "Muffler Upgrade"				, GT_OreDictNames.craftingMuffler			, "Silents a Machine.");
		
		GT_MetaItem_Component.addItem( 96, "Iron Gear"					, "gearGtIron"					, "An Iron Gear");
		GT_OreDictUnificator.registerOreLater("craftingGearTier01"			, GT_MetaItem_Component.instance.getUnunifiedStack( 96, 1));
		GT_MetaItem_Component.addItem( 97, "Bronze Gear"				, "gearGtBronze"				, "A Bronze Gear");
		GT_OreDictUnificator.registerOreLater("craftingGearTier01"			, GT_MetaItem_Component.instance.getUnunifiedStack( 97, 1));
		GT_MetaItem_Component.addItem( 98, "Steel Gear"					, "gearGtSteel"					, "A Steel Gear");
		GT_OreDictUnificator.registerOreLater("craftingGearTier02"			, GT_MetaItem_Component.instance.getUnunifiedStack( 98, 1));
		GT_MetaItem_Component.addItem( 99, "Titanium Gear"				, "gearGtTitanium"				, "A Titanium Gear");
		GT_OreDictUnificator.registerOreLater("craftingGearTier03"			, GT_MetaItem_Component.instance.getUnunifiedStack( 99, 1));
		GT_MetaItem_Component.addItem(100, "Tungstensteel Gear"			, "gearGtTungstenSteel"			, "A Tungstensteel Gear");
		GT_OreDictUnificator.registerOreLater("craftingGearTier03"			, GT_MetaItem_Component.instance.getUnunifiedStack(100, 1));
		GT_MetaItem_Component.addItem(101, "Iridium Gear"				, "gearGtIridium"				, "An Iridium Gear");
		GT_OreDictUnificator.registerOreLater("craftingGearTier04"			, GT_MetaItem_Component.instance.getUnunifiedStack(101, 1));
		GT_MetaItem_Component.addItem(102, "Stainless Steel Gear"		, "gearGtStainlessSteel"		, "A Stainless Steel Gear");
		GT_OreDictUnificator.registerOreLater("craftingGearTier02"			, GT_MetaItem_Component.instance.getUnunifiedStack(102, 1));
		
		GT_MetaGenerated_Item_01 tMetaGenerated;
		GregTech_API.sItemList[8] = tMetaGenerated = new GT_MetaGenerated_Item_01();
		tMetaGenerated.setBurnValue(17000 + Materials.Wood.mMetaItemSubID, 1600);
		GT_Items.Credit_Greg_Copper.set(tMetaGenerated.addItem(0, "credit_copper", "0.125 Credits", null)); // TODO locale, put locale key here, where credit_copper, additional locale name, check in game
		GT_Items.Credit_Greg_Cupronickel.set(tMetaGenerated.addItem(1, "1 Credit", null));
		GT_Items.Credit_Greg_Silver.set(tMetaGenerated.addItem(2, "8 Credits", null));
		GT_Items.Credit_Greg_Gold.set(tMetaGenerated.addItem(3, "64 Credits", null));
		GT_Items.Credit_Greg_Platinum.set(tMetaGenerated.addItem(4, "512 Credits", null));
		GT_Items.Credit_Greg_Osmium.set(tMetaGenerated.addItem(5, "4096 Credits", null));
		GT_Items.Credit_Greg_Naquadah.set(tMetaGenerated.addItem(6, "32768 Credits", null));
		GT_Items.Credit_Greg_Neutronium.set(tMetaGenerated.addItem(7, "262144 Credits", null));
		GT_Items.Coin_Gold_Ancient.set(tMetaGenerated.addItem(8, "Found in ancient Ruins", null));
		GT_Items.Coin_Doge.set(tMetaGenerated.addItem(9, "wow much coin how money so crypto plz mine v rich very currency wow", null));
		GT_Items.Coin_Chocolate.set(tMetaGenerated.addItem(10, "Wrapped in Gold", new GT_FoodStat(1, 0.1F, EnumAction.eat, GT_OreDictUnificator.get(OrePrefixes.nugget, (Object) Materials.Gold, 1L), true, false, new int[] { Potion.moveSpeed.id, 200, 1, 100 })));
		GT_Items.Bottle_Purple_Drink.set(tMetaGenerated.addItem(100, "How about Lemonade. Or some Ice Tea? I got Purple Drink!", new GT_FoodStat(8, 0.2F, EnumAction.drink, GT_OreDictUnificator.get(OrePrefixes.bottle, (Object) Materials.Empty, 1L), false, false, new int[] { Potion.moveSlowdown.id, 400, 0, 100 })));
		GT_Items.Food_Potato_On_Stick.set(tMetaGenerated.addItem(200, "Totally looks like a Crab Claw", new GT_FoodStat(1, 0.3F, EnumAction.eat, new ItemStack(Items.stick, 1), false, false, new int[0])));
		GT_Items.Food_Potato_On_Stick_Roasted.set(tMetaGenerated.addItem(201, "Totally looks like a Crab Claw", new GT_FoodStat(6, 0.6F, EnumAction.eat, new ItemStack(Items.stick, 1), false, false, new int[0])));
		GT_Items.Shape_Mold_Plate.set(tMetaGenerated.addItem(301, "Mold for making Plates", null));
		GT_Items.Shape_Mold_Casing.set(tMetaGenerated.addItem(302, "Mold for making Item Casings", null));
		GT_Items.Shape_Mold_Gear.set(tMetaGenerated.addItem(303, "Mold for making Gears", null));
		GT_Items.Fuel_Can_Plastic_Empty.set(tMetaGenerated.addItem(400, "Used to store Fuels", null));
		GT_Items.Fuel_Can_Plastic_Filled.set(tMetaGenerated.addItem(401, "Burns well in Diesel Generators", null));
		GT_Items.Crop_Drop_Plumbilia.set(tMetaGenerated.addItem(500, "Source of Lead", null));
		GT_Items.Crop_Drop_Argentia.set(tMetaGenerated.addItem(501, "Source of Silver", null));
		GT_Items.Crop_Drop_Indigo.set(tMetaGenerated.addItem(502, "Used for Blue Dye", null));
		GT_Items.Crop_Drop_Ferru.set(tMetaGenerated.addItem(503, "Source of Iron", null));
		GT_Items.Crop_Drop_Aurelia.set(tMetaGenerated.addItem(504, "Source of Gold", null));
		GT_Items.Crop_Drop_OilBerry.set(tMetaGenerated.addItem(510, "Oil in Berry form", null));
		GT_Items.Crop_Drop_BobsYerUncleRanks.set(tMetaGenerated.addItem(511, "Source of Emeralds", null));
		GT_Items.Crop_Drop_MilkWart.set(tMetaGenerated.addItem(520, "Source of Milk", null));
		GT_Items.Crop_Drop_Coppon.set(tMetaGenerated.addItem(530, "ORANGE WOOOOOOOL!!!", null));
		short tLastID1 = 540;
		GT_Items.Crop_Drop_Tine.set(tMetaGenerated.addItem(540, "Source of Tin", null));
		tMetaGenerated.setBurnValue(32000 + tLastID1, 100);
		
		try {
			Class<?> cropsCl = Class.forName("ic2.api.crops.Crops");
			Field mDrop = Class.forName("ic2.core.crop.Ic2CropCard").getDeclaredField("mDrop");
			Object instance = cropsCl.getField("instance").get(null);
			List<?> crops = new ArrayList<>((Collection<?>) cropsCl.getMethod("getCrops").invoke(instance));
			mDrop.set(crops.get(13), GT_Items.Crop_Drop_Ferru.get(1));
			mDrop.set(crops.get(14), GT_Items.Crop_Drop_Aurelia.get(1));
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.catching(e);
			}
		}
		
		GT_Items.Battery_Hull_LV.set(tMetaGenerated.addItem(600, "An empty LV Battery Hull", null));
		GT_Items.Battery_Hull_MV.set(tMetaGenerated.addItem(601, "An empty MV Battery Hull", null));
		tLastID1 = 610;
		GT_Items.Battery_SU_LV_SulfuricAcid.set(tMetaGenerated.addItem(610, "Single Use", null, new Object[] { OrePrefixes.batterySingleuse.get(Materials.SulfuricAcid) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 12000, 32, 1, -2);
		tLastID1 = 611;
		GT_Items.Battery_SU_LV_Mercury.set(tMetaGenerated.addItem(611, "Single Use", null, new Object[] { OrePrefixes.batterySingleuse.get(Materials.Mercury) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 32000, 32, 1, -2);
		tLastID1 = 618;
		GT_Items.Battery_RE_LV_Lithium.set(tMetaGenerated.addItem(618, "Reusable", null, new Object[] { OrePrefixes.battery.get(Materials.Lithium) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 100000, 32, 1, -3);
		tLastID1 = 619;
		GT_Items.Battery_RE_LV_Sodium.set(tMetaGenerated.addItem(619, "Reusable", null, new Object[] { OrePrefixes.battery.get(Materials.Sodium) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, '\uc350', 32, 1, -3);
		tLastID1 = 620;
		GT_Items.Battery_SU_MV_SulfuricAcid.set(tMetaGenerated.addItem(620, "Single Use", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, '\ubb80', 128, 2, -2);
		tLastID1 = 621;
		GT_Items.Battery_SU_MV_Mercury.set(tMetaGenerated.addItem(621, "Single Use", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 128000, 128, 2, -2);
		tLastID1 = 628;
		GT_Items.Battery_RE_MV_Lithium.set(tMetaGenerated.addItem(628, "Reusable", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 400000, 128, 2, -3);
		tLastID1 = 629;
		GT_Items.Battery_RE_MV_Sodium.set(tMetaGenerated.addItem(629, "Reusable", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 200000, 128, 2, -3);
		tLastID1 = 697;
		GT_Items.Energy_LapotronicOrb.set(tMetaGenerated.addItem(697, "", null, new Object[] { OrePrefixes.battery.get(Materials.Ultimate) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 100000000, 8192, 5, -3);
		tLastID1 = 698;
		GT_Items.ZPM.set(tMetaGenerated.addItem(698, "", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 2000000000, 131072, 7, -2);
		GT_Items.McGuffium_239.set(tMetaGenerated.addItem(765, "Useful for any kind of Plot", null));
		if (GregTech_API.DEBUG_MODE) {
			tMetaGenerated.addItem(700, "A very simple Circuit", null, new Object[] { OrePrefixes.circuit.get(Materials.Primitive) });
			tMetaGenerated.addItem(701, "A basic Circuit", null, new Object[] { OrePrefixes.circuit.get(Materials.Basic) });
			tMetaGenerated.addItem(702, "A good Circuit", null, new Object[] { OrePrefixes.circuit.get(Materials.Good) });
			tMetaGenerated.addItem(703, "An advanced Circuit", null, new Object[] { OrePrefixes.circuit.get(Materials.Advanced) });
			tMetaGenerated.addItem(704, "A Data Storage Chip", null, new Object[] { OrePrefixes.circuit.get(Materials.Data) });
			tMetaGenerated.addItem(705, "A Processor", null, new Object[] { OrePrefixes.circuit.get(Materials.Elite) });
			tMetaGenerated.addItem(706, "A High Voltage Processor", null, new Object[] { OrePrefixes.circuit.get(Materials.Master) });
			tMetaGenerated.addItem(707, "A High Capacity Data Storage", null, new Object[] { OrePrefixes.circuit.get(Materials.Ultimate) });
			tMetaGenerated.addItem(710, "A basic Board", null, new Object[] { OrePrefixes.circuitBoard.get(Materials.Basic) });
			tMetaGenerated.addItem(711, "An advanced Board", null, new Object[] { OrePrefixes.circuitBoard.get(Materials.Advanced) });
			tMetaGenerated.addItem(712, "A Processor Board", null, new Object[] { OrePrefixes.circuitBoard.get(Materials.Elite) });
			tMetaGenerated.addItem(715, "Advanced Circuit Parts", null, new Object[] { OrePrefixes.circuitPart.get(Materials.Advanced) });
			tMetaGenerated.addItem(720, "A piece of Super Conductor", null, new Object[] { GT_OreDictNames.craftingSuperconductor });
		}
		
		GregTech_API.sItemList[9] = new GT_MetaGenerated_Item_02();
		GT_Items.Circuit_Integrated.set((GregTech_API.sItemList[10] = new GT_IntegratedCircuit_Item("GregTech_Integrated_Circuit")));
		GT_OreDictUnificator.addToBlacklist(GT_Items.Circuit_Integrated.getWildcard(1));
		
		GT_Log.log.info("Register Regular Items.");
		GT_Items.Display_Fluid.set((GregTech_API.sItemList[15] = new GT_FluidDisplayItem("GregTech_FluidDisplay")));
		GT_Items.NC_SensorCard.set((GregTech_API.sItemList[16] = (GT_Generic_Item) GT_Utility.callConstructor("gregtechmod.common.items.GT_SensorCard_Item", 0, (Object) null, false, "GregTech_Sensorcard")));
		if (GregTech_API.sItemList[16] == null) {
			GT_Items.NC_SensorCard.set((GregTech_API.sItemList[16] = new GT_Generic_Item("GregTech_Sensorcard", "Nuclear Control not installed")));
		}
		
		GT_Items.NC_SensorKit.set((GregTech_API.sItemList[17] = new GT_SensorKit_Item("GregTech_Sensorkit")));
	    GT_Items.Armor_Cheat.set(GregTech_API.sItemList[18] = GregTech_API.constructElectricArmorItem("Ultimate_Cheat_Armor", 1000000000, Integer.MAX_VALUE, 1, 10, -1, 100.0D, true, 1, tArmorID1));
	    GT_Items.Tool_Mortar_Iron.set((GregTech_API.sItemList[30] = new GT_Mortar_Item("Iron_Mortar", 64, GT_OreDictUnificator.get(OrePrefixes.dust, (Object)Materials.Iron, 1L))));
	    GT_Items.Tool_Mortar_Wood.set((GregTech_API.sItemList[31] = new GT_Generic_Item("Flint_Mortar", "Used to turn Ingots into Dust")));
	    GT_Items.Tool_Sonictron.set((GregTech_API.sItemList[32] = new GT_Sonictron_Item( "Sonictron")));
	    GT_Items.Tool_Destructopack.set((GregTech_API.sItemList[33] = new GT_Destructopack_Item("Destructopack")));
	    GT_Items.Reactor_Coolant_He_1.set(GregTech_API.sItemList[34] = GregTech_API.constructCoolantCellItem("60k_Helium_Coolantcell", '\uea60'));
	    GT_Items.Reactor_Coolant_He_3.set(GregTech_API.sItemList[35] = GregTech_API.constructCoolantCellItem("180k_Helium_Coolantcell", 180000));
	    GT_Items.Reactor_Coolant_He_6.set(GregTech_API.sItemList[36] = GregTech_API.constructCoolantCellItem("360k_Helium_Coolantcell", 360000));
	    GregTech_API.sItemList[37] = GregTech_API.constructElectricEnergyStorageItem("Lapotronic_Energyorb", 100000000, 8192, 5, 37, 37);
	    GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Energy_LapotronicOrb.get(1), new Object[]{new ItemStack(GregTech_API.sItemList[37], 1, 32767)});
	    GT_Items.Armor_Cloaking.set(GregTech_API.sItemList[38] = GregTech_API.constructElectricArmorItem("Cloaking_Device", 100000000, 8192, 5, 0, 512, 0.0D, false, 1, tArmorID3));
	    GT_Items.Tool_Jackhammer_Bronze.set((GregTech_API.sItemList[39] = GregTech_API.constructElectricDrillItem("Bronze_Jack_Hammer", 10, 0, 2, 8.0F, 50, -1).setElectricTier(1).setBlockBreakSound(GregTech_API.sSoundList.get(107)).setEntityHitSound(GregTech_API.sSoundList.get(107))));
        
		try {
			GT_Items.Reactor_NeutronReflector.set((GregTech_API.sItemList[40] = new GT_NeutronReflector_Item("Iridium_Neutronreflector", 0)));
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.catching(e);
			}
		}
	    
		GT_Items.Tool_Jackhammer_Steel.set((GregTech_API.sItemList[41] = GregTech_API.constructElectricDrillItem("Steel_Jack_Hammer", 10, 0, 2, 16.0F, 100, -1).setElectricTier(1).setBlockBreakSound(GregTech_API.sSoundList.get(107)).setEntityHitSound(GregTech_API.sSoundList.get(107))));
		GT_Items.Tool_Jackhammer_Diamond.set((GregTech_API.sItemList[42] = GregTech_API.constructElectricDrillItem("Diamond_Jack_Hammer", 100, 0, 3, 45.0F, 250, -1).setElectricTier(2).setBlockBreakSound(GregTech_API.sSoundList.get(107)).setEntityHitSound(GregTech_API.sSoundList.get(107))));
		GT_Items.Tool_DataOrb.set((GregTech_API.sItemList[43] = new GT_Dataorb_Item("Dataorb")));
		GT_Items.Circuit_Ultimate.set(GT_Items.Tool_DataOrb.getItem());
		GT_Items.Armor_Lamp.set(GregTech_API.sItemList[44] = GregTech_API.constructElectricArmorItem("Lighthelmet", 10000, 32, 1, 0, 48, 0.0D, false, 0, tArmorID1));
		GT_Items.Armor_LapotronicPack.set(GregTech_API.sItemList[45] = GregTech_API.constructElectricArmorItem("Lapotronpack", 100000000, 8192, 5, 0, 0, 0.0D, true, 1, tArmorID1));
		GT_Items.Tool_Rockcutter.set((GregTech_API.sItemList[46] = GregTech_API.constructElectricDrillItem("Rockcutter", 10, 0, 5, 3.0F, 1000, -1).setElectricTier(1).addToMaterialList(Material.anvil).addToMaterialList(Material.iron).addToMaterialList(Material.rock).setToolClasses(new String[]{"pickaxe"}).setSilkyness(3)));
	    
		try {
			GT_Items.Tool_Teslastaff.set( (GregTech_API.sItemList[47] = new GT_Teslastaff_Item("Teslastaff")));
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.catching(e);
			}
		}
		
		GT_Items.Tool_Scoop_Aluminium.set(GregTech_API.sItemList[48] = new GT_Scoop_Item("GT_Scoop_Aluminium", 20, 0, 2, 10.0F, 1000, -1));
		
		try {
			GT_Items.Tool_Cheat.set(GregTech_API.sItemList[55] = new GT_Debug_Item("Debug_Scanner"));
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.catching(e);
			}
		}
		
		GT_Items.Energy_Lithium_Empty.set(GregTech_API.sItemList[56] = GregTech_API.constructElectricEnergyStorageItem("Lithium_Battery_Empty", 100000, 128, 1, 56, 57).setMaxStackSize(16).setMaxDamage(0));
		GT_Items.Energy_Lithium.set(GregTech_API.sItemList[57] = GregTech_API.constructElectricEnergyStorageItem("Lithium_Battery", 100000, 128, 1, 56, 57));
		GT_Items.Armor_LithiumPack.set(GregTech_API.sItemList[58] = GregTech_API.constructElectricArmorItem("Lithium_Batpack", 600000, 128, 1, 0, 0, 0.0D, true, 1, tArmorID2));
		GT_Items.Reactor_Coolant_NaK_1.set(GregTech_API.sItemList[60] = GregTech_API.constructCoolantCellItem("60k_NaK_Coolantcell", '\uea60'));
		GT_Items.Reactor_Coolant_NaK_3.set(GregTech_API.sItemList[61] = GregTech_API.constructCoolantCellItem("180k_NaK_Coolantcell", 180000));
		GT_Items.Reactor_Coolant_NaK_6.set(GregTech_API.sItemList[62] = GregTech_API.constructCoolantCellItem("360k_NaK_Coolantcell", 360000));
		
		try {
			GT_Items.Tool_Scanner.set(GregTech_API.sItemList[63] = new GT_Scanner_Item("GT_Scanner"));
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.catching(e);
			}
		}
		
		GT_Items.Tool_Crowbar_Iron.set((GregTech_API.sItemList[64] = GregTech_API.constructCrowbarItem("GT_Crowbar", 256, 6)));
	    GT_Items.Tool_Screwdriver_Iron.set((GregTech_API.sItemList[65] = new GT_Screwdriver_Item("GT_Screwdriver", 256, 4, -1)));
	    GT_Items.Tool_Wrench_Steel.set((GregTech_API.sItemList[66] = GregTech_API.constructWrenchItem("GT_Wrench_Steel", 1280, 8, -1)));
	    GT_Items.Tool_Wrench_Iron.set((GregTech_API.sItemList[67] = GregTech_API.constructWrenchItem("GT_Wrench_Iron", 128, 4, -1)));
	    GT_Items.Tool_Wrench_TungstenSteel.set((GregTech_API.sItemList[68] = GregTech_API.constructWrenchItem("GT_Wrench_TungstenSteel", 5120, 10, -1)));
	    GT_Items.Tool_Wrench_Bronze.set((GregTech_API.sItemList[69] = GregTech_API.constructWrenchItem("GT_Wrench_Bronze", 256, 6, -1)));
	    GT_Items.Tool_Wrench_Electric.set((GregTech_API.sItemList[70] = GregTech_API.constructElectricWrenchItem("GT_Wrench_Electric", 16, 0, 112)));
	    GT_Items.Tool_Wrench_Advanced.set((GregTech_API.sItemList[71] = GregTech_API.constructElectricWrenchItem("GT_Wrench_Advanced", 128, 0, 113)));
	    GT_Items.Tool_Hammer_Rubber.set((GregTech_API.sItemList[72] = new GT_SoftHammer_Item("GT_Hammer_Rubber", 128, 4)));
	    GT_Items.Tool_Hammer_Iron.set((GregTech_API.sItemList[73] = GregTech_API.constructHardHammerItem("GT_Hammer_Iron", 128, 4)));
	    GT_Items.Tool_Hammer_Bronze.set((GregTech_API.sItemList[74] = GregTech_API.constructHardHammerItem("GT_Hammer_Bronze", 256, 6)));
	    GT_Items.Tool_Hammer_Steel.set((GregTech_API.sItemList[75] = GregTech_API.constructHardHammerItem("GT_Hammer_Steel", 1280, 8)));
	    GT_Items.Tool_Hammer_TungstenSteel.set((GregTech_API.sItemList[76] = GregTech_API.constructHardHammerItem("GT_Hammer_Tungstensteel", 5120, 10)));
	    GT_Items.Tool_SolderingIron_Electric.set((GregTech_API.sItemList[77] = GregTech_API.constructElectricSolderingToolItem("GT_SolderingTool", 10, 0, 114)));
	    GT_Items.Tool_SolderingMaterial_Tin.set((GregTech_API.sItemList[78] = new GT_SolderingMetal_Item("GT_SolderingTin", 50, 0)));
	    GT_Items.Tool_SolderingMaterial_Lead.set((GregTech_API.sItemList[79] = new GT_SolderingMetal_Item("GT_SolderingLead", 10, 0)));
	    GT_Items.Component_Turbine_Bronze.set((GregTech_API.sItemList[80] = new GT_Durable_Item("GT_Turbine_Bronze", "Turbine Efficiency:  60%", 15000)));
	    GT_Items.Component_Turbine_Steel.set((GregTech_API.sItemList[81] = new GT_Durable_Item("GT_Turbine_Steel", "Turbine Efficiency:  80%", 10000)));
	    GT_Items.Component_Turbine_Magnalium.set((GregTech_API.sItemList[82] = new GT_Durable_Item("GT_Turbine_Magnalium", "Turbine Efficiency: 100%", 10000)));
	    GT_Items.Component_Turbine_TungstenSteel.set((GregTech_API.sItemList[83] = new GT_Durable_Item("GT_Turbine_Tungstensteel", "Turbine Efficiency:  90%", 30000)));
	    GT_Items.Component_Turbine_Carbon.set((GregTech_API.sItemList[84] = new GT_Durable_Item("GT_Turbine_Carbon", "Turbine Efficiency: 125%", 2500)));
	    GT_Items.Component_LavaFilter.set((GregTech_API.sItemList[85] = new GT_Durable_Item("GT_Lava_Filter", "Filters Lava in Thermal Boilers", 100)));
	    GT_Items.Tool_File_Iron.set((GregTech_API.sItemList[86] = new GT_File_Item("GT_File_Iron", 128, 2)));
	    GT_Items.Tool_File_Bronze.set((GregTech_API.sItemList[87] = new GT_File_Item("GT_File_Bronze", 256, 3)));
	    GT_Items.Tool_File_Steel.set((GregTech_API.sItemList[88] = new GT_File_Item("GT_File_Steel", 1280, 3)));
	    GT_Items.Tool_File_TungstenSteel.set((GregTech_API.sItemList[89] = new GT_File_Item("GT_File_Tungstensteel", 5120, 4)));
	    GT_Items.Spray_Bug.set((GregTech_API.sItemList[90] = new GT_Spray_Bug_Item("GT_Spray_Bug", 128, 2)));
	    GT_Items.Spray_Ice.set((GregTech_API.sItemList[91] = new GT_Spray_Ice_Item("GT_Spray_Ice", 512, 4)));
	    GT_Items.Spray_Hardener.set((GregTech_API.sItemList[92] = new GT_Spray_Hardener_Item("GT_Spray_Hardener", 256, 0)));
	    GT_Items.Spray_CFoam.set((GregTech_API.sItemList[93] = new GT_Spray_Foam_Item("GT_Spray_Foam", 400, 0)));
	    GT_Items.Spray_Pepper.set((GregTech_API.sItemList[94] = new GT_Spray_Pepper_Item("GT_Spray_Pepper", 128, 2)));
	    GT_Items.Spray_Hydration.set((GregTech_API.sItemList[95] = new GT_Spray_Hydration_Item("GT_Spray_Hydration", 2560, 0)));
	    GT_Items.Spray_Color_00.set((GregTech_API.sItemList[96] = new GT_Spray_Color_Item("GT_Spray_00", 2048, 0, (byte)0)));
	    GT_Items.Spray_Color_01.set((GregTech_API.sItemList[97] = new GT_Spray_Color_Item("GT_Spray_01", 2048, 0, (byte)1)));
	    GT_Items.Spray_Color_02.set((GregTech_API.sItemList[98] = new GT_Spray_Color_Item("GT_Spray_02", 2048, 0, (byte)2)));
	    GT_Items.Spray_Color_03.set((GregTech_API.sItemList[99] = new GT_Spray_Color_Item("GT_Spray_03", 2048, 0, (byte)3)));
	    GT_Items.Spray_Color_04.set((GregTech_API.sItemList[100] = new GT_Spray_Color_Item("GT_Spray_04", 2048, 0, (byte)4)));
	    GT_Items.Spray_Color_05.set((GregTech_API.sItemList[101] = new GT_Spray_Color_Item("GT_Spray_05", 2048, 0, (byte)5)));
	    GT_Items.Spray_Color_06.set((GregTech_API.sItemList[102] = new GT_Spray_Color_Item("GT_Spray_06", 2048, 0, (byte)6)));
	    GT_Items.Spray_Color_07.set((GregTech_API.sItemList[103] = new GT_Spray_Color_Item("GT_Spray_07", 2048, 0, (byte)7)));
	    GT_Items.Spray_Color_08.set((GregTech_API.sItemList[104] = new GT_Spray_Color_Item("GT_Spray_08", 2048, 0, (byte)8)));
	    GT_Items.Spray_Color_09.set((GregTech_API.sItemList[105] = new GT_Spray_Color_Item("GT_Spray_09", 2048, 0, (byte)9)));
	    GT_Items.Spray_Color_10.set((GregTech_API.sItemList[106] = new GT_Spray_Color_Item("GT_Spray_10", 2048, 0, (byte)10)));
	    GT_Items.Spray_Color_11.set((GregTech_API.sItemList[107] = new GT_Spray_Color_Item("GT_Spray_11", 2048, 0, (byte)11)));
	    GT_Items.Spray_Color_12.set((GregTech_API.sItemList[108] = new GT_Spray_Color_Item("GT_Spray_12", 2048, 0, (byte)12)));
	    GT_Items.Spray_Color_13.set((GregTech_API.sItemList[109] = new GT_Spray_Color_Item("GT_Spray_13", 2048, 0, (byte)13)));
	    GT_Items.Spray_Color_14.set((GregTech_API.sItemList[110] = new GT_Spray_Color_Item("GT_Spray_14", 2048, 0, (byte)14)));
	    GT_Items.Spray_Color_15.set((GregTech_API.sItemList[111] = new GT_Spray_Color_Item("GT_Spray_15", 2048, 0, (byte)15)));
	    GregTech_API.sItemList[112] = GregTech_API.constructEmptyElectricToolItem("Empty_Electric_Wrench", 16, 70);
	    GregTech_API.sItemList[113] = GregTech_API.constructEmptyElectricToolItem("Empty_Advanced_Wrench", 128, 71);
	    GregTech_API.sItemList[114] = GregTech_API.constructEmptyElectricToolItem("Empty_Soldering_Tool", 10, 77);
	    GT_Items.Tool_Saw_Iron.set((GregTech_API.sItemList[115] = new GT_Saw_Item("GT_Saw_Iron", 128, 2, 2, 3.0F, 0, -1)));
	    GT_Items.Tool_Saw_Bronze.set((GregTech_API.sItemList[116] = new GT_Saw_Item("GT_Saw_Bronze", 256, 3, 2, 4.0F, 0, -1)));
	    GT_Items.Tool_Saw_Steel.set((GregTech_API.sItemList[117] = new GT_Saw_Item("GT_Saw_Steel", 1280, 4, 3, 6.0F, 0, -1)));
	    GT_Items.Tool_Saw_TungstenSteel.set((GregTech_API.sItemList[118] = new GT_Saw_Item("GT_Saw_Tungstensteel", 5120, 5, 4, 8.0F, 0, -1)));
	    GT_Items.Tool_Saw_Electric.set((GregTech_API.sItemList[119] = GregTech_API.constructElectricSawItem("GT_Saw_Electric", 16, 9, 3, 10.0F, 100, 121).setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(104))).setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(104))).setBlockBreakSound((String)GregTech_API.sSoundList.get(Integer.valueOf(104))).setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(105)))));
	    GT_Items.Tool_Saw_Advanced.set((GregTech_API.sItemList[120] = GregTech_API.constructElectricSawItem("GT_Saw_Advanced", 128, 12, 4, 12.0F, 200, 122).setCraftingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(104))).setBreakingSound((String)GregTech_API.sSoundList.get(Integer.valueOf(104))).setBlockBreakSound((String)GregTech_API.sSoundList.get(Integer.valueOf(104))).setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(105)))));
	    GregTech_API.sItemList[121] = GregTech_API.constructEmptyElectricToolItem("Empty_Electric_Saw", 10, 119);
	    GregTech_API.sItemList[122] = GregTech_API.constructEmptyElectricToolItem("Empty_Advanced_Saw", 10, 120);
	    GT_Items.Tool_Drill_Advanced.set((GregTech_API.sItemList[123] = GregTech_API.constructElectricDrillItem("GT_Drill_Advanced", 128, 8, 5, 35.0F, 250, -1).addToMaterialList(Material.anvil).addToMaterialList(Material.iron).addToMaterialList(Material.rock).setToolClasses(new String[]{"pickaxe", "shovel"}).setBlockBreakSound((String)GregTech_API.sSoundList.get(Integer.valueOf(106))).setEntityHitSound((String)GregTech_API.sSoundList.get(Integer.valueOf(106)))));
	    GT_Items.Tool_Sword_Flint.set((GregTech_API.sItemList[124] = new GT_Vanilla_Sword("GT_Flint_Sword", "Flint", 1, 5, GT_Mod.sNerfedStoneTools?128:256, 4.0F, 1.0F)));
	    GT_Items.Tool_Pickaxe_Flint.set((GregTech_API.sItemList[125] = new GT_Vanilla_Pickaxe("GT_Flint_Pickaxe", "Flint", 1, 5, GT_Mod.sNerfedStoneTools?128:256, 4.0F, 1.0F)));
	    GT_Items.Tool_Shovel_Flint.set((GregTech_API.sItemList[126] = new GT_Vanilla_Shovel("GT_Flint_Shovel", "Flint", 1, 5, GT_Mod.sNerfedStoneTools?128:256, 4.0F, 1.0F)));
	    GT_Items.Tool_Axe_Flint.set((GregTech_API.sItemList[127] = new GT_Vanilla_Axe("GT_Flint_Axe", "Flint", 1, 5, GT_Mod.sNerfedStoneTools?128:256, 4.0F, 1.0F)));
	    GT_Items.Tool_Hoe_Flint.set((GregTech_API.sItemList[128] = new GT_Vanilla_Hoe("GT_Flint_Hoe", "Flint", 1, 5, GT_Mod.sNerfedStoneTools?128:256, 4.0F, 1.0F)));
	    GT_Items.Tool_Sword_Steel.set((GregTech_API.sItemList[129] = new GT_Vanilla_Sword("GT_Steel_Sword", "Steel", 2, 14, 1280, 8.0F, 2.0F)));
	    GT_Items.Tool_Pickaxe_Steel.set((GregTech_API.sItemList[130] = new GT_Vanilla_Pickaxe("GT_Steel_Pickaxe", "Steel", 2, 14, 1280, 8.0F, 2.0F)));
	    GT_Items.Tool_Shovel_Steel.set((GregTech_API.sItemList[131] = new GT_Vanilla_Shovel("GT_Steel_Shovel", "Steel", 2, 14, 1280, 8.0F, 2.0F)));
	    GT_Items.Tool_Axe_Steel.set((GregTech_API.sItemList[132] = new GT_Vanilla_Axe("GT_Steel_Axe", "Steel", 2, 14, 1280, 8.0F, 2.0F)));
	    GT_Items.Tool_Hoe_Steel.set((GregTech_API.sItemList[133] = new GT_Vanilla_Hoe("GT_Steel_Hoe", "Steel", 2, 14, 1280, 8.0F, 2.0F)));
	    GT_Items.Tool_Sword_TungstenSteel.set((GregTech_API.sItemList[134] = new GT_Vanilla_Sword("GT_TungstenSteel_Sword", "TungstenSteel", 4, 5, 5120, 12.0F, 3.0F)));
	    GT_Items.Tool_Pickaxe_TungstenSteel.set((GregTech_API.sItemList[135] = new GT_Vanilla_Pickaxe("GT_TungstenSteel_Pickaxe", "TungstenSteel", 4, 5, 5120, 12.0F, 3.0F)));
	    GT_Items.Tool_Shovel_TungstenSteel.set((GregTech_API.sItemList[136] = new GT_Vanilla_Shovel("GT_TungstenSteel_Shovel", "TungstenSteel", 4, 5, 5120, 12.0F, 3.0F)));
	    GT_Items.Tool_Axe_TungstenSteel.set((GregTech_API.sItemList[137] = new GT_Vanilla_Axe("GT_TungstenSteel_Axe", "TungstenSteel", 4, 5, 5120, 12.0F, 3.0F)));
	    GT_Items.Tool_Hoe_TungstenSteel.set((GregTech_API.sItemList[138] = new GT_Vanilla_Hoe("GT_TungstenSteel_Hoe",  "TungstenSteel", 4, 5, 5120, 12.0F, 3.0F)));
	    GT_Items.Tool_Screwdriver_TungstenSteel.set((GregTech_API.sItemList[139] = new GT_Screwdriver_Item("GT_Screwdriver_Tungstensteel", 5120, 6, -1)));
	    GT_Items.Tool_Screwdriver_Electric.set((GregTech_API.sItemList[140] = GregTech_API.constructElectricScrewdriverItem("GT_Screwdriver_Electric", 16, 0, 141)));
	    GregTech_API.sItemList[141] = GregTech_API.constructEmptyElectricToolItem("Empty_Screwdriver_Electric", 16, 140);
	    GT_Items.Tool_Hammer_Plastic.set((GregTech_API.sItemList[142] = new GT_SoftHammer_Item("GT_Hammer_Plastic", 1280, 5)));

	    
		GT_Log.log.info("Register items"); 
		ArrayList<Item> gtItems = new ArrayList<>(Arrays.asList(GregTech_API.sItemList));
		gtItems.removeIf(i -> i == null);
		for (Item item : GregTech_API.sItemList) {
			if (item != null) {
				GameRegistry.registerItem(item, item.getUnlocalizedName());
			}
		}
		
		
        GT_Log.log.info("Hiding certain Items from NEI.");
		try {
			Class.forName("codechicken.nei.api.API");
			codechicken.nei.api.API.hideItem(new ItemStack(GregTech_API.sBlockList[3]));
			codechicken.nei.api.API.hideItem(new ItemStack(GregTech_API.sItemList[ 4], 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
			codechicken.nei.api.API.hideItem(new ItemStack(GregTech_API.sItemList[ 5], 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
			codechicken.nei.api.API.hideItem(new ItemStack(GregTech_API.sItemList[ 6], 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
			codechicken.nei.api.API.hideItem(new ItemStack(GregTech_API.sItemList[ 7], 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
			codechicken.nei.api.API.hideItem(new ItemStack(GregTech_API.sItemList[15], 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
			codechicken.nei.api.API.hideItem(new ItemStack(GregTech_API.sItemList[16], 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
			codechicken.nei.api.API.hideItem(GT_ModHandler.getIC2Item("wrench", 1));
			codechicken.nei.api.API.hideItem(GT_ModHandler.getIC2Item("electricWrench", 1));
			codechicken.nei.api.API.hideItem(GT_ModHandler.getIC2Item("chainsaw", 1));
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.catching(e);
			}
		}
	}
}
