package gregtechmod.loaders.preload;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
        GT_Items.Credit_Copper.set(		GT_MetaItem_Material.addItem( 0, null	, "item.copper_credit.tooltip", false));
        GT_Items.Credit_Silver.set(		GT_MetaItem_Material.addItem( 1, null	, "item.silver_credit.tooltip", false));
        GT_Items.Credit_Gold.set(		GT_MetaItem_Material.addItem( 2, null	, "item.gold_credit.tooltip", false));
        GT_Items.Credit_Platinum.set(	GT_MetaItem_Material.addItem( 3, null	, "item.platinum_credit.tooltip", false));
        GT_Items.Credit_Osmium.set(		GT_MetaItem_Material.addItem(12, null	, "item.osmium_credit.tooltip", false));
		GT_MetaItem_Material.addItem(  4, null					, null, false);
		GT_MetaItem_Material.addItem(  5, OrePrefixes.ingotHot	, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(  6, OrePrefixes.ingot		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(  7, null					, null, false);
		GT_MetaItem_Material.addItem(  8, null					, null, false);
		GT_MetaItem_Material.addItem(  9, OrePrefixes.dye		, "Blue"					, false);
		
		GT_MetaItem_Material.addItem( 13, OrePrefixes.plate		, Materials.Magnalium		, false);
		GT_MetaItem_Material.addItem( 14, OrePrefixes.dust		, Materials.Wheat			, false);
		GT_MetaItem_Material.addItem( 15, OrePrefixes.plank		, Materials.Wood			, false);
		GT_MetaItem_Material.addItem( 16, OrePrefixes.ingot		, Materials.Iridium			, false);
		GT_MetaItem_Material.addItem( 17, OrePrefixes.ingot		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem( 18, OrePrefixes.ingot		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem( 19, OrePrefixes.ingot		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem( 20, OrePrefixes.ingot		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem( 21, OrePrefixes.ingot		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem( 22, OrePrefixes.ingot		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem( 23, OrePrefixes.ingot		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem( 24, OrePrefixes.ingot		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem( 25, OrePrefixes.ingot		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem( 26, OrePrefixes.ingot		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem( 27, OrePrefixes.ingot		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem( 28, OrePrefixes.ingot		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem( 29, OrePrefixes.ingot		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem( 30, OrePrefixes.ingot		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem( 31, OrePrefixes.ingot		, Materials.Antimony		, false);
		GT_MetaItem_Material.addItem( 32, OrePrefixes.gem		, Materials.Ruby			, false);
		GT_MetaItem_Material.addItem( 33, OrePrefixes.gem		, Materials.Sapphire		, false);
		GT_MetaItem_Material.addItem( 34, OrePrefixes.gem		, Materials.GreenSapphire	, false);
		GT_MetaItem_Material.addItem( 35, OrePrefixes.block		, Materials.Lazurite		, false);
		GT_MetaItem_Material.addItem( 36, OrePrefixes.plate		, Materials.Silicon			, false);
		GT_MetaItem_Material.addItem( 37, OrePrefixes.gem		, Materials.Olivine			, false);
		GT_OreDictUnificator.addLater("gemPeridot", GT_MetaItem_Material.instance.getUnunifiedStack(37, 1));
		GT_MetaItem_Material.addItem( 38, OrePrefixes.ingot		, Materials.Thorium			, true);
		GT_MetaItem_Material.addItem( 39, OrePrefixes.ingot		, Materials.Plutonium		, true);
		GT_MetaItem_Material.addItem( 40, OrePrefixes.ingot		, Materials.Magnalium		, false);
		GT_MetaItem_Material.addItem( 41, OrePrefixes.ingot		, Materials.SolderingAlloy	, false);
		GT_MetaItem_Material.addItem( 42, OrePrefixes.ingot		, Materials.BatteryAlloy	, false);
		GT_MetaItem_Material.addItem( 43, OrePrefixes.ingot		, Materials.Uranium			, true);
		GT_MetaItem_Material.addItem( 44, OrePrefixes.ingot		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem( 45, OrePrefixes.ingot		, Materials.Cupronickel		, false);
		GT_MetaItem_Material.addItem( 46, OrePrefixes.ingot		, Materials.Nichrome		, false);
		GT_MetaItem_Material.addItem( 47, OrePrefixes.ingot		, Materials.Kanthal			, false);
		
		GT_MetaItem_Material.addItem( 54, OrePrefixes.gem		, Materials.GarnetRed		, false);
		GT_OreDictUnificator.addLater("gemGarnet", GT_MetaItem_Material.instance.getUnunifiedStack(54, 1));
		GT_MetaItem_Material.addItem( 55, OrePrefixes.gem		, Materials.GarnetYellow	, false);
		
		GT_MetaItem_Material.addItem( 58, OrePrefixes.plate		, Materials.GarnetYellow	, false);
		GT_MetaItem_Material.addItem( 59, OrePrefixes.plate		, Materials.GarnetRed		, false);
		GT_MetaItem_Material.addItem( 60, null					, null						, false);
		GT_MetaItem_Material.addItem( 61, null					, null						, false);
		GT_MetaItem_Material.addItem( 62, null					, null						, false);
		GT_MetaItem_Material.addItem( 63, null					, null						, false);
		GT_MetaItem_Material.addItem( 64, OrePrefixes.plate		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem( 65, OrePrefixes.plate		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem( 66, OrePrefixes.plate		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem( 67, OrePrefixes.plate		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem( 68, OrePrefixes.plate		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem( 69, OrePrefixes.plate		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem( 70, OrePrefixes.plate		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem( 71, OrePrefixes.plate		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem( 72, OrePrefixes.plate		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem( 73, OrePrefixes.plate		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem( 74, OrePrefixes.plate		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem( 75, OrePrefixes.plate		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem( 76, OrePrefixes.plate		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem( 77, OrePrefixes.plate		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem( 78, OrePrefixes.plate		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem( 79, OrePrefixes.plate		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem( 80, OrePrefixes.plate		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem( 81, OrePrefixes.plate		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem( 82, OrePrefixes.plate		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem( 83, OrePrefixes.plate		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem( 84, OrePrefixes.plate		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem( 85, OrePrefixes.plate		, Materials.Iridium			, false);
		GT_MetaItem_Material.addItem( 86, OrePrefixes.plate		, Materials.BatteryAlloy	, false);
		GT_MetaItem_Material.addItem( 87, OrePrefixes.plate		, Materials.Diamond			, false);
		GT_MetaItem_Material.addItem( 88, OrePrefixes.plate		, Materials.Emerald			, false);
		GT_MetaItem_Material.addItem( 89, OrePrefixes.plate		, Materials.Ruby			, false);
		GT_MetaItem_Material.addItem( 90, OrePrefixes.plate		, Materials.Sapphire		, false);
		GT_MetaItem_Material.addItem( 91, OrePrefixes.plate		, Materials.GreenSapphire	, false);
		GT_MetaItem_Material.addItem( 92, OrePrefixes.plate		, Materials.Olivine			, false);
		GT_MetaItem_Material.addItem( 93, OrePrefixes.plate		, Materials.Redstone		, false);
		GT_MetaItem_Material.addItem( 94, OrePrefixes.plate		, Materials.Lapis			, false);
		GT_MetaItem_Material.addItem( 95, OrePrefixes.plate		, Materials.Coal			, false);
		GT_MetaItem_Material.addItem( 96, OrePrefixes.stick		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem( 97, OrePrefixes.stick		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem( 98, OrePrefixes.stick		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem( 99, OrePrefixes.stick		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem(100, OrePrefixes.stick		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem(101, OrePrefixes.stick		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem(102, OrePrefixes.stick		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem(103, OrePrefixes.stick		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem(104, OrePrefixes.stick		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem(105, OrePrefixes.stick		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem(106, OrePrefixes.stick		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem(107, OrePrefixes.stick		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem(108, OrePrefixes.stick		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem(109, OrePrefixes.stick		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem(110, OrePrefixes.stick		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem(111, OrePrefixes.stick		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem(112, OrePrefixes.stick		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem(113, OrePrefixes.stick		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem(114, OrePrefixes.stick		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem(115, OrePrefixes.stick		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(116, OrePrefixes.stick		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem(117, OrePrefixes.stick		, Materials.Iridium			, false);
		
		GT_MetaItem_Material.addItem(256, OrePrefixes.lense		, Materials.Diamond			, false);
		GT_MetaItem_Material.addItem(257, OrePrefixes.lense		, Materials.Ruby			, false);
		GT_MetaItem_Material.addItem(258, OrePrefixes.lense		, Materials.Emerald			, false);
		GT_MetaItem_Material.addItem(259, OrePrefixes.lense		, Materials.Sapphire		, false);
		GT_MetaItem_Material.addItem(260, OrePrefixes.lense		, Materials.GreenSapphire	, false);
		GT_MetaItem_Material.addItem(261, OrePrefixes.lense		, Materials.GarnetYellow	, false);
		GT_MetaItem_Material.addItem(262, OrePrefixes.lense		, Materials.GarnetRed		, false);
		GT_MetaItem_Material.addItem(263, OrePrefixes.lense		, Materials.Olivine			, false);
		GT_MetaItem_Material.addItem(264, OrePrefixes.lense		, Materials.Amber			, false);
		GT_MetaItem_Material.addItem(265, OrePrefixes.lense		, Materials.Tanzanite		, false);
		GT_MetaItem_Material.addItem(266, OrePrefixes.lense		, Materials.Topaz			, false);

		GT_MetaItem_Material.addItem(320, OrePrefixes.bolt		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem(321, OrePrefixes.bolt		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem(322, OrePrefixes.bolt		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem(323, OrePrefixes.bolt		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem(324, OrePrefixes.bolt		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem(325, OrePrefixes.bolt		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem(326, OrePrefixes.bolt		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem(327, OrePrefixes.bolt		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem(328, OrePrefixes.bolt		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem(329, OrePrefixes.bolt		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem(330, OrePrefixes.bolt		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem(331, OrePrefixes.bolt		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem(332, OrePrefixes.bolt		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem(333, OrePrefixes.bolt		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem(334, OrePrefixes.bolt		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem(335, OrePrefixes.bolt		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem(336, OrePrefixes.bolt		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem(337, OrePrefixes.bolt		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem(338, OrePrefixes.bolt		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem(339, OrePrefixes.bolt		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(340, OrePrefixes.bolt		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem(341, OrePrefixes.bolt		, Materials.Iridium			, false);

		GT_MetaItem_Material.addItem(384, OrePrefixes.screw		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem(385, OrePrefixes.screw		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem(386, OrePrefixes.screw		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem(387, OrePrefixes.screw		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem(388, OrePrefixes.screw		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem(389, OrePrefixes.screw		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem(390, OrePrefixes.screw		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem(391, OrePrefixes.screw		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem(392, OrePrefixes.screw		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem(393, OrePrefixes.screw		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem(394, OrePrefixes.screw		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem(395, OrePrefixes.screw		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem(396, OrePrefixes.screw		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem(397, OrePrefixes.screw		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem(398, OrePrefixes.screw		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem(399, OrePrefixes.screw		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem(400, OrePrefixes.screw		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem(401, OrePrefixes.screw		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem(402, OrePrefixes.screw		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem(403, OrePrefixes.screw		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(404, OrePrefixes.screw		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem(405, OrePrefixes.screw		, Materials.Iridium			, false);
		
		GT_MetaItem_Material.addItem(448, OrePrefixes.round		, Materials.Iron			, false);
		GT_MetaItem_Material.addItem(449, OrePrefixes.round		, Materials.Gold			, false);
		GT_MetaItem_Material.addItem(450, OrePrefixes.round		, Materials.StainlessSteel	, false);
		GT_MetaItem_Material.addItem(451, OrePrefixes.round		, Materials.Tin				, false);
		GT_MetaItem_Material.addItem(452, OrePrefixes.round		, Materials.Copper			, false);
		GT_MetaItem_Material.addItem(453, OrePrefixes.round		, Materials.Silver			, false);
		GT_MetaItem_Material.addItem(454, OrePrefixes.round		, Materials.Bronze			, false);
		GT_MetaItem_Material.addItem(455, OrePrefixes.round		, Materials.Electrum		, false);
		GT_MetaItem_Material.addItem(456, OrePrefixes.round		, Materials.Nickel			, false);
		GT_MetaItem_Material.addItem(457, OrePrefixes.round		, Materials.Invar			, false);
		GT_MetaItem_Material.addItem(458, OrePrefixes.round		, Materials.Lead			, false);
		GT_MetaItem_Material.addItem(459, OrePrefixes.round		, Materials.Aluminium		, false);
		GT_MetaItem_Material.addItem(460, OrePrefixes.round		, Materials.Chrome			, false);
		GT_MetaItem_Material.addItem(461, OrePrefixes.round		, Materials.Titanium		, false);
		GT_MetaItem_Material.addItem(462, OrePrefixes.round		, Materials.Steel			, false);
		GT_MetaItem_Material.addItem(463, OrePrefixes.round		, Materials.Platinum		, false);
		GT_MetaItem_Material.addItem(464, OrePrefixes.round		, Materials.Tungsten		, false);
		GT_MetaItem_Material.addItem(465, OrePrefixes.round		, Materials.Brass			, false);
		GT_MetaItem_Material.addItem(466, OrePrefixes.round		, Materials.Zinc			, false);
		GT_MetaItem_Material.addItem(467, OrePrefixes.round		, Materials.TungstenSteel	, false);
		GT_MetaItem_Material.addItem(468, OrePrefixes.round		, Materials.Osmium			, false);
		GT_MetaItem_Material.addItem(469, OrePrefixes.round		, Materials.Iridium			, false);
		
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
		
		GT_MetaItem_Dust.addItem(  0, Materials.EnderPearl		, false);
		GT_MetaItem_Dust.addItem(  1, Materials.EnderEye		, false);
		GT_MetaItem_Dust.addItem(  2, Materials.Lazurite		, false);
		GT_MetaItem_Dust.addItem(  3, Materials.Pyrite			, false);
		GT_MetaItem_Dust.addItem(  4, Materials.Calcite			, false);
		GT_MetaItem_Dust.addItem(  5, Materials.Sodalite		, false);
		GT_MetaItem_Dust.addItem(  6, Materials.Netherrack		, false);
		GT_MetaItem_Dust.addItem(  7, Materials.Flint			, false);
		GT_MetaItem_Dust.addItem(  8, Materials.Sulfur			, false);
		GT_MetaItem_Dust.addItem(  9, Materials.Saltpeter		, false);
		GT_MetaItem_Dust.addItem( 10, Materials.Endstone		, false);
		GT_MetaItem_Dust.addItem( 11, Materials.Cinnabar		, false);
		GT_MetaItem_Dust.addItem( 12, Materials.Manganese		, false);
		GT_MetaItem_Dust.addItem( 13, Materials.Magnesium		, false);
		GT_MetaItem_Dust.addItem( 14, Materials.Sphalerite		, false);
		GT_MetaItem_Dust.addItem( 15, Materials.Wood			, false);
		GT_MetaItem_Dust.addItem( 16, Materials.Uranium			, true);
		GT_MetaItem_Dust.addItem( 17, Materials.Bauxite			, false);
		GT_MetaItem_Dust.addItem( 18, Materials.Aluminium		, false);
		GT_MetaItem_Dust.addItem( 19, Materials.Titanium		, false);
		GT_MetaItem_Dust.addItem( 20, Materials.Chrome			, false);
		GT_MetaItem_Dust.addItem( 21, Materials.Electrum		, false);
		GT_MetaItem_Dust.addItem( 22, Materials.Tungsten		, false);
		GT_MetaItem_Dust.addItem( 23, Materials.Lead			, false);
		GT_MetaItem_Dust.addItem( 24, Materials.Zinc			, false);
		GT_MetaItem_Dust.addItem( 25, Materials.Brass			, false);
		GT_MetaItem_Dust.addItem( 26, Materials.Steel			, false);
		GT_MetaItem_Dust.addItem( 27, Materials.Platinum		, false);
		GT_MetaItem_Dust.addItem( 28, Materials.Nickel			, false);
		GT_MetaItem_Dust.addItem( 29, Materials.Invar			, false);
		GT_MetaItem_Dust.addItem( 30, Materials.Osmium			, false);
		GT_MetaItem_Dust.addItem( 31, Materials.Antimony		, false);
		GT_MetaItem_Dust.addItem( 32, Materials.Ruby			, false);
		GT_MetaItem_Dust.addItem( 33, Materials.Sapphire		, false);
		GT_MetaItem_Dust.addItem( 34, Materials.GreenSapphire	, false);
		GT_MetaItem_Dust.addItem( 35, Materials.Emerald			, false);
		GT_MetaItem_Dust.addItem( 36, Materials.Diamond			, false);
		GT_MetaItem_Dust.addItem( 37, Materials.Olivine			, false);
		
		GT_MetaItem_Dust.addItem( 40, Materials.Cupronickel		, false);
		GT_MetaItem_Dust.addItem( 41, Materials.Nichrome		, false);
		GT_MetaItem_Dust.addItem( 42, Materials.Kanthal			, false);
		GT_MetaItem_Dust.addItem( 43, Materials.StainlessSteel	, false);
		GT_MetaItem_Dust.addItem( 44, Materials.Galena			, false);
		GT_MetaItem_Dust.addItem( 45, Materials.Phosphorus		, false);
		GT_MetaItem_Dust.addItem( 46, Materials.Obsidian		, false);
		GT_MetaItem_Dust.addItem( 47, Materials.Charcoal		, false);
		
		GT_MetaItem_Dust.addItem( 54, Materials.GarnetRed		, false);
		GT_MetaItem_Dust.addItem( 55, Materials.GarnetYellow	, false);
		GT_MetaItem_Dust.addItem( 56, Materials.Pyrope			, false);
		GT_MetaItem_Dust.addItem( 57, Materials.Almandine		, false);
		GT_MetaItem_Dust.addItem( 58, Materials.Spessartine		, false);
		GT_MetaItem_Dust.addItem( 59, Materials.Andradite		, false);
		GT_MetaItem_Dust.addItem( 60, Materials.Grossular		, false);
		GT_MetaItem_Dust.addItem( 61, Materials.Uvarovite		, false);
		GT_MetaItem_Dust.addItem( 62, Materials.Ash				, false);
		GT_MetaItem_Dust.addItem( 63, Materials.DarkAsh			, false);
		GT_MetaItem_Dust.addItem( 64, Materials.Redrock			, false);
		GT_MetaItem_Dust.addItem( 65, Materials.Marble			, false);
		GT_MetaItem_Dust.addItem( 66, Materials.Basalt			, false);
		GT_MetaItem_Dust.addItem( 67, Materials.Iridium			, false);
		GT_MetaItem_Dust.addItem( 68, Materials.GraniteBlack    , false);
		GT_MetaItem_Dust.addItem( 69, Materials.GraniteRed		, false);
		GT_MetaItem_Dust.addItem( 70, Materials.PotassiumFeldspar, false);
		GT_MetaItem_Dust.addItem( 71, Materials.Biotite			, false);
		
		GT_MetaItem_Dust.addItem( 80, Materials.Thorium			, true);
		GT_MetaItem_Dust.addItem( 81, Materials.Plutonium		, true);
		
		GT_MetaItem_Dust.addItem(238, Materials.Lapis			, false);
		GT_MetaItem_Dust.addItem(239, Materials.Tetrahedrite	, false);
		GT_MetaItem_Dust.addItem(240, Materials.Coal			, false);
		GT_MetaItem_Dust.addItem(241, Materials.Iron			, false);
		GT_MetaItem_Dust.addItem(242, Materials.Gold			, false);
		GT_MetaItem_Dust.addItem(243, Materials.Copper			, false);
		GT_MetaItem_Dust.addItem(244, Materials.Tin				, false);
		GT_MetaItem_Dust.addItem(245, Materials.Bronze			, false);
		GT_MetaItem_Dust.addItem(246, Materials.Silver			, false);
		GT_MetaItem_Dust.addItem(247, Materials.Clay			, false);
		
		GT_MetaItem_SmallDust.addItem(248, Materials.Gunpowder	, false);
		GT_MetaItem_SmallDust.addItem(249, Materials.Redstone	, false);
		GT_MetaItem_SmallDust.addItem(250, Materials.Glowstone	, false);
		GT_MetaItem_SmallDust.addItem(251, Materials.Blaze		, false);
		
		GT_MetaItem_Cell.addItem(4, "molecule_1w", false, Materials.Tungsten);
		
		GT_Items.Spray_Empty.set(				GT_MetaItem_Component.addItem(56, "", null));
		
		GT_Items.Circuit_Master.set(			GT_MetaItem_Component.addItem( 0, OrePrefixes.circuit.get(Materials.Master)			, null));
		GT_Items.Circuit_Elite.set(				GT_MetaItem_Component.addItem( 1, OrePrefixes.circuit.get(Materials.Elite)			, null));
		GT_Items.Circuit_Data.set(				GT_MetaItem_Component.addItem( 3, OrePrefixes.circuit.get(Materials.Data)			, null));
		GT_Items.Circuit_Parts_Advanced.set(	GT_MetaItem_Component.addItem(24, OrePrefixes.circuitPart.get(Materials.Advanced)	, null));
		GT_Items.Circuit_Board_Basic.set(		GT_MetaItem_Component.addItem(48, OrePrefixes.circuitBoard.get(Materials.Basic)		, null));
		GT_Items.Circuit_Board_Advanced.set(	GT_MetaItem_Component.addItem(49, OrePrefixes.circuitBoard.get(Materials.Advanced)	, null));
		GT_Items.Circuit_Board_Elite.set(		GT_MetaItem_Component.addItem(50, OrePrefixes.circuitBoard.get(Materials.Elite)		, null));
		
		GT_Items.Shape_Empty.set(				GT_MetaItem_Component.addItem(199, "", null));
	    GT_Items.Shape_Mold_Credit.set(			GT_MetaItem_Component.addItem(200, "", null));
	    GT_Items.Shape_Extruder_Plate.set(		GT_MetaItem_Component.addItem(250, "", null));
	    GT_Items.Shape_Extruder_Rod.set(		GT_MetaItem_Component.addItem(251, "", null));
	    GT_Items.Shape_Extruder_Bolt.set(		GT_MetaItem_Component.addItem(252, "", null));
	    GT_Items.Shape_Extruder_Ring.set(		GT_MetaItem_Component.addItem(253, "", null));
	    GT_Items.Shape_Extruder_Cell.set(		GT_MetaItem_Component.addItem(254, "", null));
	    GT_Items.Shape_Extruder_Ingot.set(		GT_MetaItem_Component.addItem(255, "", null));
	    GT_Items.Shape_Extruder_Wire.set(		GT_MetaItem_Component.addItem(256, "", null));
	    GT_Items.Shape_Extruder_Casing.set(		GT_MetaItem_Component.addItem(257, "", null));
	    GT_Items.Shape_Extruder_Pipe_Small.set(	GT_MetaItem_Component.addItem(258, "", null));
	    GT_Items.Shape_Extruder_Pipe_Medium.set(GT_MetaItem_Component.addItem(259, "", null));
	    GT_Items.Shape_Extruder_Pipe_Large.set(	GT_MetaItem_Component.addItem(260, "", null));
	    GT_Items.Shape_Extruder_Block.set(		GT_MetaItem_Component.addItem(261, "", null));
	    GT_Items.Shape_Extruder_Sword.set(		GT_MetaItem_Component.addItem(262, "", null));
	    GT_Items.Shape_Extruder_Pickaxe.set(	GT_MetaItem_Component.addItem(263, "", null));
	    GT_Items.Shape_Extruder_Shovel.set(		GT_MetaItem_Component.addItem(264, "", null));
	    GT_Items.Shape_Extruder_Axe.set(		GT_MetaItem_Component.addItem(265, "", null));
	    GT_Items.Shape_Extruder_Hoe.set(		GT_MetaItem_Component.addItem(266, "", null));
	    GT_Items.Shape_Extruder_Hammer.set(		GT_MetaItem_Component.addItem(267, "", null));
	    GT_Items.Shape_Extruder_File.set(		GT_MetaItem_Component.addItem(268, "", null));
	    GT_Items.Shape_Extruder_Saw.set(		GT_MetaItem_Component.addItem(269, "", null));
	    GT_Items.Shape_Extruder_Gear.set(		GT_MetaItem_Component.addItem(270, "", null));
		
		GT_MetaItem_Component.addItem( 2, GT_OreDictNames.craftingSuperconductor	, null);
		GT_MetaItem_Component.addItem( 4, GT_OreDictNames.craftingMonitorTier02		, null);
		GT_MetaItem_Component.addItem( 5, GT_OreDictNames.craftingConveyor			, null);
		GT_MetaItem_Component.addItem( 6, GT_OreDictNames.craftingPump				, null);
		GT_MetaItem_Component.addItem( 7, GT_OreDictNames.craftingSolarPanel		, null);
		GT_MetaItem_Component.addItem( 8, GT_OreDictNames.craftingItemValve			, null);
		GT_MetaItem_Component.addItem( 9, GT_OreDictNames.craftingDrain				, null);
		GT_MetaItem_Component.addItem(10, GT_OreDictNames.craftingLiquidMeter		, null);
		GT_MetaItem_Component.addItem(11, GT_OreDictNames.craftingItemMeter			, null);
		GT_MetaItem_Component.addItem(12, OrePrefixes.battery.get(Materials.Elite)	, null);
		GT_MetaItem_Component.addItem(13, OrePrefixes.battery.get(Materials.Master)	, null);
		GT_MetaItem_Component.addItem(14, OrePrefixes.battery.get(Materials.Ultimate), null);
		GT_MetaItem_Component.addItem(15, GT_OreDictNames.craftingEnergyMeter		, null);
		GT_MetaItem_Component.addItem(16, GT_OreDictNames.craftingDuctTape			, null);
		GT_MetaItem_Component.addItem(17, GT_OreDictNames.craftingDiamondBlade		, null);
		GT_MetaItem_Component.addItem(18, GT_OreDictNames.craftingGrinder			, null);
		GT_MetaItem_Component.addItem(19, GT_OreDictNames.craftingHeatingCoilTier01	, null); //33% Iron 33% Chrome 33% Aluminium
		GT_MetaItem_Component.addItem(20, GT_OreDictNames.craftingHeatingCoilTier02	, null); //20% Chrome 80% Nickel
		GT_MetaItem_Component.addItem(21, GT_OreDictNames.craftingHeatingCoilTier00	, null); //50% Copper 50% Nickel
		GT_MetaItem_Component.addItem(22, GT_OreDictNames.craftingMachineParts		, null);
		GT_MetaItem_Component.addItem(23, GT_OreDictNames.craftingGrinder			, null);
		GT_MetaItem_Component.addItem(25, GT_OreDictNames.craftingPneumaticGenerator, null);
		GT_MetaItem_Component.addItem(26, OrePrefixes.battery.get(Materials.Lithium), null);
		GT_MetaItem_Component.addItem(27, GT_OreDictNames.craftingHVTUpgrade		, null);
		GT_MetaItem_Component.addItem(28, GT_OreDictNames.craftingEnergyCellUpgrade	, null);
		GT_MetaItem_Component.addItem(29, GT_OreDictNames.craftingQuantumChestUpgrade, null);
		GT_MetaItem_Component.addItem(30, GT_OreDictNames.craftingWorkDetector		, null);
		GT_MetaItem_Component.addItem(31, GT_OreDictNames.craftingWorkController	, null);
		GT_MetaItem_Component.addItem(32, GT_OreDictNames.craftingRawMachineTier01	, null);
		GT_MetaItem_Component.addItem(33, GT_OreDictNames.craftingRawMachineTier00	, null);
		GT_MetaItem_Component.addItem(34, GT_OreDictNames.craftingRawMachineTier00	, null);
		GT_MetaItem_Component.addItem(35, GT_OreDictNames.craftingRawMachineTier02	, null);
		GT_MetaItem_Component.addItem(36, GT_OreDictNames.craftingRawMachineTier03	, null);
		GT_MetaItem_Component.addItem(37, GT_OreDictNames.craftingRawMachineTier01	, null);
		GT_MetaItem_Component.addItem(38, GT_OreDictNames.craftingRawMachineTier03	, null);
		GT_MetaItem_Component.addItem(39, GT_OreDictNames.craftingRawMachineTier02	, null);
		
		GT_MetaItem_Component.addItem(51, GT_OreDictNames.craftingTurbineBladeBronze		, null);
		GT_MetaItem_Component.addItem(52, GT_OreDictNames.craftingTurbineBladeSteel			, null);
		GT_MetaItem_Component.addItem(53, GT_OreDictNames.craftingTurbineBladeMagnalium		, null);
		GT_MetaItem_Component.addItem(54, GT_OreDictNames.craftingTurbineBladeTungstenSteel	, null);
		GT_MetaItem_Component.addItem(55, GT_OreDictNames.craftingTurbineBladeCarbon		, null);
		//GT_MetaItem_Component.addItem(57, "Empty Battery Hull"			, "craftingBatteryHull"					, "For making Batteries");
		//GT_MetaItem_Component.addItem(58, "Empty Fuel Can"				, "craftingFuelCan"						, "Mainly for storing Fuel");
		
		GT_MetaItem_Component.addItem(64, GT_OreDictNames.craftingWorkBench				, null);
		GT_MetaItem_Component.addItem(65, GT_OreDictNames.craftingSolarPanelLV			, null);
		GT_MetaItem_Component.addItem(66, GT_OreDictNames.craftingSolarPanelMV			, null);
		GT_MetaItem_Component.addItem(67, GT_OreDictNames.craftingSolarPanelHV			, null);
		GT_MetaItem_Component.addItem(69, GT_OreDictNames.craftingShutter				, null);
		
		GT_MetaItem_Component.addItem(80, GT_OreDictNames.craftingSteamUpgrade			, null);
		GT_MetaItem_Component.addItem(81, GT_OreDictNames.craftingSteamTank				, null);
		GT_MetaItem_Component.addItem(82, GT_OreDictNames.craftingRedstoneReceiver		, null);
		GT_MetaItem_Component.addItem(83, GT_OreDictNames.craftingRedstoneReceiver		, null);
		GT_MetaItem_Component.addItem(84, GT_OreDictNames.craftingRedstoneTransmitter	, null);
		GT_MetaItem_Component.addItem(85, GT_OreDictNames.craftingRedstoneTransmitter	, null);
		GT_MetaItem_Component.addItem(86, GT_OreDictNames.craftingRedstoneConductor		, null);
		GT_MetaItem_Component.addItem(87, GT_OreDictNames.craftingRedstoneSignalizer	, null);
		GT_MetaItem_Component.addItem(88, GT_OreDictNames.craftingLock					, null);
		GT_MetaItem_Component.addItem(89, GT_OreDictNames.craftingMuffler				, null);
		
		GT_MetaItem_Component.addItem(96, "gearGtIron"					, null);
		GT_OreDictUnificator.registerOreLater("craftingGearTier01"			, GT_MetaItem_Component.instance.getUnunifiedStack( 96, 1));
		GT_MetaItem_Component.addItem(97, "gearGtBronze"				, null);
		GT_OreDictUnificator.registerOreLater("craftingGearTier01"			, GT_MetaItem_Component.instance.getUnunifiedStack( 97, 1));
		GT_MetaItem_Component.addItem(98, "gearGtSteel"					, null);
		GT_OreDictUnificator.registerOreLater("craftingGearTier02"			, GT_MetaItem_Component.instance.getUnunifiedStack( 98, 1));
		GT_MetaItem_Component.addItem(99, "gearGtTitanium"				, null);
		GT_OreDictUnificator.registerOreLater("craftingGearTier03"			, GT_MetaItem_Component.instance.getUnunifiedStack( 99, 1));
		GT_MetaItem_Component.addItem(100, "gearGtTungstenSteel"		, null);
		GT_OreDictUnificator.registerOreLater("craftingGearTier03"			, GT_MetaItem_Component.instance.getUnunifiedStack(100, 1));
		GT_MetaItem_Component.addItem(101, "gearGtIridium"				, null);
		GT_OreDictUnificator.registerOreLater("craftingGearTier04"			, GT_MetaItem_Component.instance.getUnunifiedStack(101, 1));
		GT_MetaItem_Component.addItem(102, "gearGtStainlessSteel"		, null);
		GT_OreDictUnificator.registerOreLater("craftingGearTier02"			, GT_MetaItem_Component.instance.getUnunifiedStack(102, 1));
		
		GT_MetaGenerated_Item_01 tMetaGenerated;
		GregTech_API.sItemList[8] = tMetaGenerated = new GT_MetaGenerated_Item_01();
		tMetaGenerated.setBurnValue(17000 + Materials.Wood.mMetaItemSubID, 1600);
		GT_Items.Credit_Greg_Copper.set(			tMetaGenerated.addItem(  0, "credit_copper"			, "item.GregTech_MetaGenerated_Item_01.credit_copper.tooltip"	, null));
		GT_Items.Credit_Greg_Cupronickel.set(		tMetaGenerated.addItem(  1, "credit_bronze"			, "item.GregTech_MetaGenerated_Item_01.credit_bronze.tooltip"	, null));
		GT_Items.Credit_Greg_Silver.set(			tMetaGenerated.addItem(  2,"credit_silver"			, "item.GregTech_MetaGenerated_Item_01.credit_silver.tooltip"	, null));
		GT_Items.Credit_Greg_Gold.set(				tMetaGenerated.addItem(  3, "credit_gold"			, "item.GregTech_MetaGenerated_Item_01.credit_gold.tooltip"		, null));
		GT_Items.Credit_Greg_Platinum.set(			tMetaGenerated.addItem(  4, "credit_diamond"		, "item.GregTech_MetaGenerated_Item_01.credit_diamond.tooltip"	, null));
		GT_Items.Credit_Greg_Osmium.set(			tMetaGenerated.addItem(  5, "credit_osmium"			, "item.GregTech_MetaGenerated_Item_01.credit_osmium.tooltip"	, null));
		GT_Items.Credit_Greg_Naquadah.set(			tMetaGenerated.addItem(  6, "credit_naquadah"		, "item.GregTech_MetaGenerated_Item_01.credit_naquadah.tooltip"	, null));
		GT_Items.Credit_Greg_Neutronium.set(		tMetaGenerated.addItem(  7, "credit_iridium"		, "item.GregTech_MetaGenerated_Item_01.credit_iridium.tooltip"	, null));
		GT_Items.Coin_Gold_Ancient.set(				tMetaGenerated.addItem(  8, "ancient_coin"			, "item.GregTech_MetaGenerated_Item_01.ancient_coin.tooltip"	, null));
		GT_Items.Coin_Doge.set(						tMetaGenerated.addItem(  9, "doge_coin"				, "item.GregTech_MetaGenerated_Item_01.doge_coin.tooltip"		, null));
		GT_Items.Coin_Chocolate.set(				tMetaGenerated.addItem( 10, "chocolate_coin"		, "item.GregTech_MetaGenerated_Item_01.chocolate_coin.tooltip"	, new GT_FoodStat(1, 0.1F, EnumAction.eat, GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L), true, false, new int[] { Potion.moveSpeed.id, 200, 1, 100 })));
		GT_Items.Bottle_Purple_Drink.set(			tMetaGenerated.addItem(100, "purple_drink"			, "item.GregTech_MetaGenerated_Item_01.purple_drink.tooltip"	, new GT_FoodStat(8, 0.2F, EnumAction.drink, GT_OreDictUnificator.get(OrePrefixes.bottle, Materials.Empty, 1L), false, false, new int[] { Potion.moveSlowdown.id, 400, 0, 100 })));
		GT_Items.Food_Potato_On_Stick.set(			tMetaGenerated.addItem(200, "potato_stick"			, "item.GregTech_MetaGenerated_Item_01.potato_stick.tooltip"	, new GT_FoodStat(1, 0.3F, EnumAction.eat, new ItemStack(Items.stick, 1), false, false, new int[0])));
		GT_Items.Food_Potato_On_Stick_Roasted.set(	tMetaGenerated.addItem(201, "roasted_potato_stick"	, "item.GregTech_MetaGenerated_Item_01.potato_stick.tooltip"	, new GT_FoodStat(6, 0.6F, EnumAction.eat, new ItemStack(Items.stick, 1), false, false, new int[0])));
		GT_Items.Shape_Mold_Plate.set(				tMetaGenerated.addItem(301, "mold_plates"			, "item.GregTech_MetaGenerated_Item_01.mold_plates.tooltip"		, null));
		GT_Items.Shape_Mold_Casing.set(				tMetaGenerated.addItem(302, "mold_casings"			, "item.GregTech_MetaGenerated_Item_01.mold_casings.tooltip"	, null));
		GT_Items.Shape_Mold_Gear.set(				tMetaGenerated.addItem(303, "mold_gears"			, "item.GregTech_MetaGenerated_Item_01.mold_gears.tooltip"		, null));
		GT_Items.Fuel_Can_Plastic_Empty.set(		tMetaGenerated.addItem(400, "fuel_can_empty"		, "item.GregTech_MetaGenerated_Item_01.fuel_can_empty.tooltip"	, null));
		GT_Items.Fuel_Can_Plastic_Filled.set(		tMetaGenerated.addItem(401, "fuel_can_full"			, "item.GregTech_MetaGenerated_Item_01.fuel_can_full.tooltip"	, null));
		GT_Items.Crop_Drop_Plumbilia.set(			tMetaGenerated.addItem(500, "source_lead"			, "item.GregTech_MetaGenerated_Item_01.source_lead.tooltip"		, null));
		GT_Items.Crop_Drop_Argentia.set(			tMetaGenerated.addItem(501, "source_silver"			, "item.GregTech_MetaGenerated_Item_01.source_silver.tooltip"	, null));
		GT_Items.Crop_Drop_Indigo.set(				tMetaGenerated.addItem(502, "source_blue_dye"		, "item.GregTech_MetaGenerated_Item_01.source_blue_dye.tooltip"	, null));
		GT_Items.Crop_Drop_Ferru.set(				tMetaGenerated.addItem(503, "source_iron"			, "item.GregTech_MetaGenerated_Item_01.source_iron.tooltip"		, null));
		GT_Items.Crop_Drop_Aurelia.set(				tMetaGenerated.addItem(504, "source_gold"			, "item.GregTech_MetaGenerated_Item_01.source_gold.tooltip"		, null));
		GT_Items.Crop_Drop_OilBerry.set(			tMetaGenerated.addItem(510, "berry_oil"				, "item.GregTech_MetaGenerated_Item_01.berry_oil.tooltip"		, null));
		GT_Items.Crop_Drop_BobsYerUncleRanks.set(	tMetaGenerated.addItem(511, "source_emeralds"		, "item.GregTech_MetaGenerated_Item_01.source_emeralds.tooltip"	, null));
		GT_Items.Crop_Drop_MilkWart.set(			tMetaGenerated.addItem(520, "source_milk"			, "item.GregTech_MetaGenerated_Item_01.source_milk.tooltip"		, null));
		GT_Items.Crop_Drop_Coppon.set(				tMetaGenerated.addItem(530, "coppon_fiber"			, "item.GregTech_MetaGenerated_Item_01.coppon_fiber.tooltip"	, null));
		short tLastID1 = 540;
		GT_Items.Crop_Drop_Tine.set(				tMetaGenerated.addItem(540, "source_tin"			, "item.GregTech_MetaGenerated_Item_01.source_tin.tooltip"		, null));
		tMetaGenerated.setBurnValue(32000 + tLastID1, 100);
		
		try {
			Map<String, ItemStack> toChange = new HashMap<>();
			toChange.put("aurelia", GT_Items.Crop_Drop_Aurelia.get(1));
			toChange.put("ferru", GT_Items.Crop_Drop_Ferru.get(1));
			
			Class<?> cropsCl = Class.forName("ic2.api.crops.Crops");
			Object instance = cropsCl.getField("instance").get(null);
			Method getCrop = cropsCl.getMethod("getCropCard", String.class, String.class);
			for (Entry<String, ItemStack> entry : toChange.entrySet()) {
				Object crop = getCrop.invoke(instance, "IC2", entry.getKey());
				Field mDrop = crop.getClass().getDeclaredField("mDrop");
				mDrop.setAccessible(true);
				mDrop.set(crop, entry.getValue());
			}
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.catching(e);
			}
		}
		
		GT_Items.Battery_Hull_LV.set(tMetaGenerated.addItem(600, "hull_battery_lv", "item.GregTech_MetaGenerated_Item_01.hull_battery_lv.tooltip", null));
		GT_Items.Battery_Hull_MV.set(tMetaGenerated.addItem(601, "hull_battery_mv", "item.GregTech_MetaGenerated_Item_01.hull_battery_mv.tooltip", null));
		tLastID1 = 610;
		GT_Items.Battery_SU_LV_SulfuricAcid.set(tMetaGenerated.addItem(610, "battery_lv_acid", "item.singleuse.tooltip", null, new Object[] { OrePrefixes.batterySingleuse.get(Materials.SulfuricAcid) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 12000, 32, 1, -2);
		tLastID1 = 611;
		GT_Items.Battery_SU_LV_Mercury.set(tMetaGenerated.addItem(611, "battery_lv_mercury", "item.singleuse.tooltip", null, new Object[] { OrePrefixes.batterySingleuse.get(Materials.Mercury) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 32000, 32, 1, -2);
		tLastID1 = 618;
		GT_Items.Battery_RE_LV_Lithium.set(tMetaGenerated.addItem(618, "battery_lv_lithium", "item.reusable.tooltip", null, new Object[] { OrePrefixes.battery.get(Materials.Lithium) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 100000, 32, 1, -3);
		tLastID1 = 619;
		GT_Items.Battery_RE_LV_Sodium.set(tMetaGenerated.addItem(619, "battery_lv_sodium", "item.reusable.tooltip", null, new Object[] { OrePrefixes.battery.get(Materials.Sodium) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, '\uc350', 32, 1, -3);
		tLastID1 = 620;
		GT_Items.Battery_SU_MV_SulfuricAcid.set(tMetaGenerated.addItem(620, "battery_mv_acid", "item.singleuse.tooltip", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, '\ubb80', 128, 2, -2);
		tLastID1 = 621;
		GT_Items.Battery_SU_MV_Mercury.set(tMetaGenerated.addItem(621, "battery_mv_mercury", "item.singleuse.tooltip", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 128000, 128, 2, -2);
		tLastID1 = 628;
		GT_Items.Battery_RE_MV_Lithium.set(tMetaGenerated.addItem(628, "battery_mv_lithium", "item.reusable.tooltip", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 400000, 128, 2, -3);
		tLastID1 = 629;
		GT_Items.Battery_RE_MV_Sodium.set(tMetaGenerated.addItem(629, "battery_mv_sodium", "item.reusable.tooltip", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 200000, 128, 2, -3);
		tLastID1 = 697;
		GT_Items.Energy_LapotronicOrb.set(tMetaGenerated.addItem(697, "lapotronic_orb", "", null, new Object[] { OrePrefixes.battery.get(Materials.Ultimate) }));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 100000000, 8192, 5, -3);
		tLastID1 = 698;
		GT_Items.ZPM.set(tMetaGenerated.addItem(698, "zpm", "", null));
		tMetaGenerated.setElectricStats(32000 + tLastID1, 2000000000, 131072, 7, -2);
		GT_Items.McGuffium_239.set(tMetaGenerated.addItem(765, "mc_guffim_239", "item.GregTech_MetaGenerated_Item_01.mc_guffim_239.tooltip", null));
		if (GregTech_API.DEBUG_MODE) {
			tMetaGenerated.addItem(700, "item.GT_Circuit.simple.tooltip", null, new Object[] { OrePrefixes.circuit.get(Materials.Primitive) });
			tMetaGenerated.addItem(701, "item.GT_Circuit.basic.tooltip", null, new Object[] { OrePrefixes.circuit.get(Materials.Basic) });
			tMetaGenerated.addItem(702, "item.GT_Circuit.good.tooltip", null, new Object[] { OrePrefixes.circuit.get(Materials.Good) });
			tMetaGenerated.addItem(703, "item.GT_Circuit.advanced.tooltip", null, new Object[] { OrePrefixes.circuit.get(Materials.Advanced) });
			tMetaGenerated.addItem(704, "item.GT_Circuit.data.tooltip", null, new Object[] { OrePrefixes.circuit.get(Materials.Data) });
			tMetaGenerated.addItem(705, "item.GT_Circuit.processor.tooltip", null, new Object[] { OrePrefixes.circuit.get(Materials.Elite) });
			tMetaGenerated.addItem(706, "item.GT_Circuit.HV_processor.tooltip", null, new Object[] { OrePrefixes.circuit.get(Materials.Master) });
			tMetaGenerated.addItem(707, "item.GT_Circuit.HV_data.tooltip", null, new Object[] { OrePrefixes.circuit.get(Materials.Ultimate) });
			tMetaGenerated.addItem(710, "item.GT_Circuit.basic_board.tooltip", null, new Object[] { OrePrefixes.circuitBoard.get(Materials.Basic) });
			tMetaGenerated.addItem(711, "item.GT_Circuit.advanced_board.tooltip", null, new Object[] { OrePrefixes.circuitBoard.get(Materials.Advanced) });
			tMetaGenerated.addItem(712, "item.GT_Circuit.processor_board.tooltip", null, new Object[] { OrePrefixes.circuitBoard.get(Materials.Elite) });
			tMetaGenerated.addItem(715, "item.GT_Circuit.advanced_parts.tooltip", null, new Object[] { OrePrefixes.circuitPart.get(Materials.Advanced) });
			tMetaGenerated.addItem(720, "item.GT_Circuit.superconductor_parts.tooltip", null, new Object[] { GT_OreDictNames.craftingSuperconductor });
		}
		
		GregTech_API.sItemList[9] = new GT_MetaGenerated_Item_02();
		GT_Items.Circuit_Integrated.set((GregTech_API.sItemList[10] = new GT_IntegratedCircuit_Item("GregTech_Integrated_Circuit")));
		GT_OreDictUnificator.addToBlacklist(GT_Items.Circuit_Integrated.getWildcard(1));
		
		GT_Log.log.info("Register Regular Items.");
		GT_Items.Display_Fluid.set((GregTech_API.sItemList[15] = new GT_FluidDisplayItem("GregTech_FluidDisplay")));
		GT_Items.NC_SensorCard.set((GregTech_API.sItemList[16] = (GT_Generic_Item) GT_Utility.callConstructor("gregtechmod.common.items.GT_SensorCard_Item", 0, (Object) null, false, "GregTech_Sensorcard")));
		if (GregTech_API.sItemList[16] == null) {
			GT_Items.NC_SensorCard.set((GregTech_API.sItemList[16] = new GT_Generic_Item("GregTech_Sensorcard", "util.missIC2NC")));
		}
		
		GT_Items.NC_SensorKit.set((GregTech_API.sItemList[17] = new GT_SensorKit_Item("GregTech_Sensorkit")));
	    GT_Items.Armor_Cheat.set(GregTech_API.sItemList[18] = GregTech_API.constructElectricArmorItem("Ultimate_Cheat_Armor", 1000000000, Integer.MAX_VALUE, 1, 10, -1, 100.0D, true, 1, tArmorID1));
	    GT_Items.Tool_Mortar_Iron.set((GregTech_API.sItemList[30] = new GT_Mortar_Item("Iron_Mortar", 64, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 1L))));
	    GT_Items.Tool_Mortar_Wood.set((GregTech_API.sItemList[31] = new GT_Generic_Item("Flint_Mortar", "item.Flint_Mortar.tooltip_main")));
	    GT_Items.Tool_Sonictron.set((GregTech_API.sItemList[32] = new GT_Sonictron_Item( "Sonictron")));
	    GT_Items.Tool_Destructopack.set((GregTech_API.sItemList[33] = new GT_Destructopack_Item("Destructopack")));
	    GT_Items.Reactor_Coolant_He_1.set(GregTech_API.sItemList[34] = GregTech_API.constructCoolantCellItem("60k_Helium_Coolantcell", '\uea60'));
	    GT_Items.Reactor_Coolant_He_3.set(GregTech_API.sItemList[35] = GregTech_API.constructCoolantCellItem("180k_Helium_Coolantcell", 180000));
	    GT_Items.Reactor_Coolant_He_6.set(GregTech_API.sItemList[36] = GregTech_API.constructCoolantCellItem("360k_Helium_Coolantcell", 360000));
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
			GT_Items.Tool_Teslastaff.set((GregTech_API.sItemList[47] = new GT_Teslastaff_Item("Teslastaff")));
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
	    GT_Items.Component_Turbine_Bronze.set((GregTech_API.sItemList[80] = new GT_Durable_Item("GT_Turbine_Bronze", "item.GT_Turbine_Bronze.tooltip_main", 15000)));
	    GT_Items.Component_Turbine_Steel.set((GregTech_API.sItemList[81] = new GT_Durable_Item("GT_Turbine_Steel", "item.GT_Turbine_Steel.tooltip_main", 10000)));
	    GT_Items.Component_Turbine_Magnalium.set((GregTech_API.sItemList[82] = new GT_Durable_Item("GT_Turbine_Magnalium", "item.GT_Turbine_Magnalium.tooltip_main", 10000)));
	    GT_Items.Component_Turbine_TungstenSteel.set((GregTech_API.sItemList[83] = new GT_Durable_Item("GT_Turbine_Tungstensteel", "item.GT_Turbine_Tungstensteel.tooltip_main", 30000)));
	    GT_Items.Component_Turbine_Carbon.set((GregTech_API.sItemList[84] = new GT_Durable_Item("GT_Turbine_Carbon", "item.GT_Turbine_Carbon.tooltip_main", 2500)));
	    GT_Items.Component_LavaFilter.set((GregTech_API.sItemList[85] = new GT_Durable_Item("GT_Lava_Filter", "item.GT_Lava_Filter.tooltip_main", 100)));
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
