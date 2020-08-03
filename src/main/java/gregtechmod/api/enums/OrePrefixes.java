package gregtechmod.api.enums;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public enum OrePrefixes {
	@Deprecated oreGem(false, false, false, -1),
	@Deprecated leaves(false, false, false, -1),
	@Deprecated sapling(false, false, false, -1),
	@Deprecated itemDust(false, false, false, -1),
	@Deprecated ingotQuad(false, false, false, -1),
	@Deprecated dustDirty(false, false, false, -1),
	@Deprecated stoneBricksMossy(false, false, false, -1),
	
	oreNether			( true,  true, false,									-1), // Prefix of the Nether-Ores Mod. Causes Ores to double. Ore -> Material is a Oneway Operation!
	oreDense			( true,  true, false,									-1), // Prefix of the Dense-Ores Mod. Causes Ores to double. Ore -> Material is a Oneway Operation!
	oreEnd				( true,  true, false,									-1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
	ore					( true,  true, false,									-1), // Regular Ore Prefix. Ore -> Material is a Oneway Operation!
	crushedCentrifuged	( true,  true, false,									-1), 
	crushedPurified		( true,  true, false,									-1), 
	crushed				( true,  true, false,									-1), 
	ingotQuintuple		( true,  true, false, GregTech_API.MATERIAL_UNIT *		 5), // A quintuple Ingot.
	ingotQuadruple		( true,  true, false, GregTech_API.MATERIAL_UNIT *		 4), // A quadruple Ingot.
	ingotTriple			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 3), // A triple Ingot.
	ingotDouble			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 2), // A double Ingot. Similar to the double Ingots of TerrafirmaCraft.
	ingotHot			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // A hot Ingot, which has to be cooled down by a Vacuum Freezer.
	ingot				( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // A regular Ingot.
	gem					( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // A regular Gem or crystallized Metal worth one Dust.
	dustTiny			( true,  true, false, GregTech_API.MATERIAL_UNIT /		 9), // 1/9th of a Dust.
	dustSmall			( true,  true, false, GregTech_API.MATERIAL_UNIT /		 4), // 1/4th of a Dust.
	dustImpure			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // Dust with impurities. 1 Unit of Main Material and 1/9 - 1/4 Unit of secondary Material
	dust				( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // Pure Dust worth of one Ingot or Gem.
	nugget				( true,  true, false, GregTech_API.MATERIAL_UNIT /		 9), // A Nugget.
	plateAlloy			(false, false, false,									-1), // Special Alloys have this prefix.
	plateDense			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 9), // 9 Plates combined in one Item.
	plate				( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // Regular Plate made of one Ingot/Dust.
	block				( true,  true, false, GregTech_API.MATERIAL_UNIT *		 9), // Storage Block consisting out of 9 Ingots/Gems/Dusts.
	stick				( true,  true, false, GregTech_API.MATERIAL_UNIT /		 2), // Stick made of half an Ingot.
	lense				( true,  true, false,(GregTech_API.MATERIAL_UNIT * 3)  / 4), // 3/4 of a Plate or Gem used to shape a Lense. Normally only used on Transparent Materials.
	round				( true,  true, false, GregTech_API.MATERIAL_UNIT /		 9), // consisting out of one Nugget.
	bolt				( true,  true, false, GregTech_API.MATERIAL_UNIT /		 8), // consisting out of 1/8 Ingot or 1/4 Stick.
	screw				( true,  true, false, GregTech_API.MATERIAL_UNIT /		 8), // consisting out of a Bolt.
	cellPlasma			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // Hot Cell full of Plasma, which can be used in the Plasma Generator.
	cell				( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // Regular Gas/Fluid Cell.
	bucket				( true,  true,  true, GregTech_API.MATERIAL_UNIT *		 1), // A vanilla Iron Bucket filled with the Material.
	bottle				( true,  true, false, 									-1), // Glas Bottle containing a Fluid.
	crafting			(false, false, false,									-1), // Special Prefix used mainly for the Crafting Handler.
	log					(false, false, false,									-1), // Prefix used for Logs. Usually as "logWood".
	slab				(false, false, false,									-1), // Prefix used for Slabs. Usually as "slabWood" or "slabStone".
	stair				(false, false, false,									-1), // Prefix used for Stairs. Usually as "stairWood" or "stairStone".
	plank				(false, false, false, 									-1), // Prefix for Planks. Usually "plankWood".
	treeSapling			(false, false,  true,									-1), // Prefix for Saplings.
	treeLeaves			(false, false,  true,									-1), // Prefix for Leaves.
	tree				(false, false, false,									-1), // Prefix for Tree Parts.
	stoneCobble			(false, false,  true,									-1), // Cobblestone Prefix for all Cobblestones.
	stoneSmooth			(false, false,  true,									-1), // Smoothstone Prefix.
	stoneMossyBricks	(false, false,  true,									-1), // Mossy Stone Bricks.
	stoneMossy			(false, false,  true,									-1), // Mossy Cobble.
	stoneBricks			(false, false,  true,									-1), // Stone Bricks.
	stoneCracked		(false, false,  true,									-1), // Cracked Bricks.
	stoneChiseled		(false, false,  true,									-1), // Chiseled Stone.
	stone				(false,  true, false,									-1), // Prefix to determine which kind of Rock this is.
	rubble				( true,  true, false,									-1),
	scraps				( true,  true, false,									-1),
	scrap				(false, false, false,									-1),
	item				(false, false, false,									-1), // Random Item.
	book				(false, false, false,									-1), // Used for Books of any kind.
	paper				(false, false, false,									-1), // Used for Papers of any kind.
	dye					(false, false, false,									-1), // Used for dyes.
	armorHelmet			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 5), // vanilly Helmet
	armorChestplate		( true,  true, false, GregTech_API.MATERIAL_UNIT *		 8), // vanilly Chestplate
	armorLeggins		( true,  true, false, GregTech_API.MATERIAL_UNIT *		 7), // vanilly Pants
	armorBoots			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 4), // vanilly Boots
	toolSword			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 2), // vanilly Sword
	toolPickaxe			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 3), // vanilly Pickaxe
	toolShovel			( true,  true, false, GregTech_API.MATERIAL_UNIT *		 1), // vanilly Shovel
	toolAxe				( true,  true, false, GregTech_API.MATERIAL_UNIT *		 3), // vanilly Axe
	toolHoe				( true,  true, false, GregTech_API.MATERIAL_UNIT *		 2), // vanilly Hoe
	
	// random known prefixes without special abilities.
	junk				(false, false, false,									-1),
	bee					(false, false, false,									-1),
	pulp				(false, false, false,									-1),
	rod					(false, false, false,									-1),
	dirt				(false, false, false,									-1),
	sand				(false, false, false,									-1),
	grass				(false, false, false,									-1),
	gravel				(false, false, false,									-1),
	mushroom			(false, false, false,									-1),
	wood				(false, false, false,									-1),
	drop				(false, false, false,									-1),
	fuel				(false, false, false,									-1),
	panel				(false, false, false,									-1),
	brick				(false, false, false,									-1),
	chunk				(false, false, false,									-1),
	wire				(false, false, false,									-1),
	seed				(false, false, false,									-1),
	reed				(false, false, false,									-1),
	sheet				(false, false, false,									-1),
	crop				(false, false, false,									-1),
	plant				(false, false, false,									-1),
	coin				(false, false, false,									-1),
	lumar				(false, false, false,									-1),
	crystal				(false, false, false,									-1),
	ground				(false, false, false,									-1),
	cable				(false, false, false,									-1),
	reduced				(false, false, false,									-1),
	component			(false, false, false,									-1),
	crystalline			(false, false, false,									-1),
	cleanGravel			(false, false, false,									-1),
	dirtyGravel			(false, false, false,									-1),
	wax					(false, false, false,									-1),
	gear				(false, false, false,									-1),
	tool				(false, false, false,									-1),
	list				(false, false, false,									-1),
	food				(false, false, false,									-1),
	coral				(false, false, false,									-1),
	shard				(false, false, false,									-1),
	clump				(false, false, false,									-1),
	glass				(false, false, false,									-1),
	flower				(false, false, false,									-1),
	storage				(false, false, false,									-1),
	material			(false, false, false,									-1),
	plasma				(false, false, false,									-1),
	element				(false, false, false,									-1),
	molecule			(false, false, false,									-1),
	wafer				(false, false, false,									-1),
	batterySingleuse	(false, false, false,									-1),
	batteryReusable		(false, false, false,									-1),
	battery				(false, false, false,									-1),
	circuit				(false, false, false,									-1),
	compressed			(false, false, false,									-1);
	
	static {
		oreGem.mPrefixInto = ore;
		leaves.mPrefixInto = treeLeaves;
		sapling.mPrefixInto = treeSapling;
		itemDust.mPrefixInto = dust;
		dustDirty.mPrefixInto = dustImpure;
		ingotQuad.mPrefixInto = ingotQuadruple;
		stoneBricksMossy.mPrefixInto = stoneMossyBricks;
	}
	
	private final ArrayList<ItemStack> mPrefixedItems = new ArrayList<ItemStack>();
	
	public boolean add(ItemStack aStack) {
		if (aStack == null) return false;
		if (!contains(aStack)) mPrefixedItems.add(aStack);
		return true;
	}
	
	public boolean contains(ItemStack aStack) {
		if (aStack == null) return false;
		for (ItemStack tStack : mPrefixedItems) if (GT_Utility.areStacksEqual(aStack, tStack)) return true;
		return false;
	}
	
	public final boolean mIsUnificatable, mIsMaterialBased, mIsSelfReferencing;
	public OrePrefixes mPrefixInto = this;
	
	/**
	 * Used to determine the amount of Material this Prefix contains.
	 * Multiply or Divide GregTech_API.MATERIAL_UNIT to set the Amounts in comparision to one Ingot.
	 * 0 = Null
	 * Negative = Undefined Amount
	 */
	public final long mMaterialAmount;
	
	private OrePrefixes(boolean aIsUnificatable, boolean aIsMaterialBased, boolean aIsSelfReferencing, long aMaterialAmount) {
		mIsUnificatable = aIsUnificatable;
		mIsMaterialBased = aIsMaterialBased;
		mIsSelfReferencing = aIsSelfReferencing;
		mMaterialAmount = aMaterialAmount;
	}
	
	public static OrePrefixes getPrefix(String aOre) {
		for (OrePrefixes tPrefix : values()) if (aOre.startsWith(tPrefix.toString())) return tPrefix;
		return null;
	}
	
	public static String stripPrefix(String aOre) {
		for (OrePrefixes tPrefix : values()) {
			if (aOre.startsWith(tPrefix.toString())) {
				return aOre.replaceFirst(tPrefix.toString(), "");
			}
		}
		return aOre;
	}
	
	public static String replacePrefix(String aOre, OrePrefixes aPrefix) {
		for (OrePrefixes tPrefix : values()) {
			if (aOre.startsWith(tPrefix.toString())) {
				return aOre.replaceFirst(tPrefix.toString(), aPrefix.toString());
			}
		}
		return "";
	}
	
	public String get(Object aMaterial) {
		return toString() + aMaterial;
	}
	
	public static Materials getMaterial(String aOre) {
		return Materials.get(stripPrefix(aOre));
	}

	public static Materials getMaterial(String aOre, OrePrefixes aPrefix) {
		return Materials.get(aOre.replaceFirst(aPrefix.toString(), ""));
	}
	
	public static Materials getRealMaterial(String aOre, OrePrefixes aPrefix) {
		return Materials.getRealMaterial(aOre.replaceFirst(aPrefix.toString(), ""));
	}
	
	public static boolean isInstanceOf(String aName, OrePrefixes aPrefix) {
		return aName == null ? false : aName.startsWith(aPrefix.toString());
	}
	
	public static volatile int VERSION = 404;
}