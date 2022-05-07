package gregtechmod.api.enums;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public enum OrePrefixes {
	@Deprecated pulp	(false, false, false, false, false, -1),
	@Deprecated leaves	(false, false, false, false, false, -1),
	@Deprecated sapling	(false, false, false, false, false, -1),
	@Deprecated itemDust(false, false, false, false, false, -1),
	
	oreNether			(true, true,  false,  false, false, -1), // Prefix of the Nether-Ores Mod. Causes Ores to double. Ore -> Material is a Oneway Operation!
	oreDense			(true, true,  false,  false, false, -1), // Prefix of the Dense-Ores Mod. Causes Ores to double. Ore -> Material is a Oneway Operation!
	oreEnd				(true, true,  false,  false, false, -1), // In case of an End-Ores Mod. Ore -> Material is a Oneway Operation!
	@Deprecated oreGem	(false, false, false, false, false, -1),
	ore					(true, true,  false,  false, false, -1), // Regular Ore Prefix. Ore -> Material is a Oneway Operation! Introduced by Eloraam
	crushedCentrifuged	(true, true,  false,  false, false, -1), 
	crushedPurified		(true, true,  false,  false, false, -1), 
	crushed				(true, true,  false,  false, false, -1), 
	ingotQuintuple		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 5), // A quintuple Ingot.
	ingotQuadruple		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 4), // A quadruple Ingot.
	@Deprecated ingotQuad(false, false, false, false, false, -1),
	ingotTriple			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // A triple Ingot.
	ingotDouble			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // A double Ingot. Similar to the double Ingots of TerrafirmaCraft.
	ingotHot			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // A hot Ingot, which has to be cooled down by a Vacuum Freezer.
	ingot				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // A regular Ingot. Introduced by Eloraam
	gem					(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // A regular Gem or crystallized Metal worth one Dust. Introduced by Eloraam
	@Deprecated dustDirty(false, false, false, false, false, -1),
	dustTiny			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 9), // 1/9th of a Dust.
	dustSmall			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 4), // 1/4th of a Dust.
	dustImpure			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // Dust with impurities. 1 Unit of Main Material and 1/9 - 1/4 Unit of secondary Material
	dustRefined			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1),
	dustPure			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // Dust without impurities.
	dust				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // Pure Dust worth of one Ingot or Gem. Introduced by Alblaka.
	nugget				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 9), // A Nugget. Introduced by Eloraam
	plateAlloy			(true, false,  false, false, false, -1), // Special Alloys have this prefix.
	plateDense			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 9), // 9 Plates combined in one Item.
	plateQuintuple		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 5), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	plateQuadruple		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 4), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	@Deprecated plateQuad(false, false, false, false, false, -1),
	plateTriple			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	plateDouble			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	plate				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // Regular Plate made of one Ingot/Dust. Introduced by Calclavia
	block				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 9), // Storage Block consisting out of 9 Ingots/Gems/Dusts. Introduced by CovertJaguar
	stick				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 2), // Stick made of half an Ingot. Introduced by Eloraam
	lense				(true, true,  false,  false, false, (GregTech_API.MATERIAL_UNIT * 3)   / 4), // 3/4 of a Plate or Gem used to shape a Lense. Normally only used on Transparent Materials.
	round				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 9), // consisting out of one Nugget.
	bolt				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 8), // consisting out of 1/8 Ingot or 1/4 Stick.
	screw				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 8), // consisting out of a Bolt.
	ring				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT /		 4), // consisting out of 1/2 Stick.
	cellPlasma			(true, true,  true,  true,  false,  GregTech_API.MATERIAL_UNIT *		 1), // Hot Cell full of Plasma, which can be used in the Plasma Generator.
	cell				(true, true,  true,  true,  false,  GregTech_API.MATERIAL_UNIT *		 1), // Regular Gas/Fluid Cell. Introduced by Calclavia
	bucket				(true, true,  true,  true,  false,  GregTech_API.MATERIAL_UNIT *		 1), // A vanilla Iron Bucket filled with the Material.
	bottle				(true, true,  true,  true,  false,  -1), // Glas Bottle containing a Fluid.
	capsule				(false, true, true,  true,  false,  GregTech_API.MATERIAL_UNIT *		 1),
	crystal				(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 1),
	craftingTool		(false, false, false, false, false, -1), // Special Prefix used mainly for the Crafting Handler.
	crafting			(false, false, false, false, false, -1), // Special Prefix used mainly for the Crafting Handler.
	craft				(false, false, false, false, false, -1), // Special Prefix used mainly for the Crafting Handler.
	log					(false, false, false, false, false, -1), // Prefix used for Logs. Usually as "logWood". Introduced by Eloraam
	slab				(false, false, false, false, false, -1), // Prefix used for Slabs. Usually as "slabWood" or "slabStone". Introduced by SirSengir
	stair				(false, false, false, false, false, -1), // Prefix used for Stairs. Usually as "stairWood" or "stairStone". Introduced by SirSengir
	plank				(false, false, false, false, false, -1), // Prefix for Planks. Usually "plankWood". Introduced by Eloraam
	treeSapling			(false, false, true, false,  false, -1), // Prefix for Saplings.
	treeLeaves			(false, false, true, false,  false, -1), // Prefix for Leaves.
	tree				(false, false, false, false, false, -1), // Prefix for Tree Parts.
	stoneCobble			(false, false, true, false,  false, -1), // Cobblestone Prefix for all Cobblestones.
	stoneSmooth			(false, false, true, false,  false, -1), // Smoothstone Prefix.
	stoneMossyBricks	(false, false, true, false,  false, -1), // Mossy Stone Bricks.
	stoneMossy			(false, false, true, false,  false, -1), // Mossy Cobble.
	@Deprecated stoneBricksMossy(false, false, false, false, false, -1),
	stoneBricks			(false, false, true, false,  false, -1), // Stone Bricks.
	@Deprecated stoneBrick(false, false, false, false, false, -1),
	stoneCracked		(false, false, true, false,  false, -1), // Cracked Bricks.
	stoneChiseled		(false, false, true, false,  false, -1), // Chiseled Stone.
	stone				(false, true, true,  false,  false, -1), // Prefix to determine which kind of Rock this is.
	cobblestone			(false, true, true,  false,  false, -1),
	glass				(false, false, true, false,  false, -1),
	record				(false, false, true, false,  false, -1),
	rubble				(true, true,  true,  false,  false, -1),
	scraps				(true, true,  false,  false, false, -1),
	scrap				(false, false, false, false, false, -1),
	item				(false, false, false, false, false, -1), // Random Item. Introduced by Alblaka
	book				(false, false, false, false, false, -1), // Used for Books of any kind.
	paper				(false, false, false, false, false, -1), // Used for Papers of any kind.
	dye					(false, false, false, false, false, -1), // Used for the 16 dyes. Introduced by Eloraam
	armorHelmet			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 5), // vanilly Helmet
	armorChestplate		(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 8), // vanilly Chestplate
	armorLeggins		(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 7), // vanilly Pants
	armorBoots			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 4), // vanilly Boots
	armor				(false, false, false, false, false, -1),
	toolHeadSword		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // consisting out of 2 Ingots.
	toolHeadPickaxe		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // consisting out of 3 Ingots.
	toolHeadShovel		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // consisting out of 1 Ingots.
	toolHeadAxe			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // consisting out of 3 Ingots.
	toolHeadHoe			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // consisting out of 2 Ingots.
	toolHeadFile		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // consisting out of 2 Ingots.
	toolHeadHammer		(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 6), // consisting out of 6 Ingots.
	toolHeadSaw			(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // consisting out of 2 Ingots.
	toolHeadScrewdriver	(true, true				,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // consisting out of 1 Ingots.
	toolSword			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // vanilly Sword
	toolPickaxe			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // vanilly Pickaxe
	toolShovel			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 1), // vanilly Shovel
	toolAxe				(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 3), // vanilly Axe
	toolHoe				(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // vanilly Hoe
	toolShears			(false, true, false,  false, false, GregTech_API.MATERIAL_UNIT *		 2), // vanilly Shears
	tool				(false, false, false, false, false, -1), // toolPot, toolSkillet, toolSaucepan, toolBakeware, toolCuttingboard, toolMortarandpestle, toolMixingbowl, toolJuicer
	pipeTiny			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT /		 2),
	pipeSmall			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT *		 1),
	pipeMedium			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT *		 3),
	pipeLarge			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT *		 6),
	pipeHuge			(true, true,  false,  false, true, GregTech_API.MATERIAL_UNIT *		12),
	pipe				(false, false, false, false, false, -1),
	gearGt				(true, true,  false,  false, false, GregTech_API.MATERIAL_UNIT *		 4), // Introduced by me because BuildCraft has ruined the gear Prefix...
	
	/* Electric Components.
	 * 
	 * usual Materials for this are:
	 * Primitive (Tier 1)
	 * Basic (Tier 2) as used by UE as well : IC2 Circuit and RE-Battery
	 * Good (Tier 3)
	 * Advanced (Tier 4) as used by UE as well : Advanced Circuit, Advanced Battery and Lithium Battery
	 * Data (Tier 5) : Data Storage Circuit
	 * Elite (Tier 6) as used by UE as well : Energy Crystal and Data Control Circuit
	 * Master (Tier 7) : Energy Flow Circuit and Lapotron Crystal
	 * Ultimate (Tier 8) : Data Orb and Lapotronic Energy Orb
	 * Infinite (Cheaty)
	 */
	batterySingleuse	(false, true, false,  false, false, -1),
	battery				(false, true, false,  false, false, -1), // Introduced by Calclavia
	circuitBoard		(true, true,  false,  false, false, -1), // Board needed for creating a Circuit of the same Tier
	circuitPart			(true, true,  false,  false, false, -1), // Part needed for creating a Circuit of the same Tier
	circuit				(true, true,  false,  false, false, -1), // Introduced by Calclavia
	computer			(true, true,  false,  false, true, -1), // A whole Computer. "computerMaster" = ComputerCube
	
	// random known prefixes without special abilities.
	cluster				(false, false, false, false, false, -1),
	grafter				(false, false, false, false, false, -1),
	scoop				(false, false, false, false, false, -1),
	frame				(false, false, false, false, false, -1),
	tome				(false, false, false, false, false, -1),
	junk				(false, false, false, false, false, -1),
	bee					(false, false, false, false, false, -1),
	rod					(false, false, false, false, false, -1),
	dirt				(false, false, false, false, false, -1),
	sand				(false, false, false, false, false, -1),
	grass				(false, false, false, false, false, -1),
	gravel				(false, false, false, false, false, -1),
	mushroom			(false, false, false, false, false, -1),
	wood				(false, false, false, false, false, -1), // Introduced by Eloraam
	drop				(false, false, false, false, false, -1),
	fuel				(false, false, false, false, false, -1),
	panel				(false, false, false, false, false, -1),
	brick				(false, false, false, false, false, -1),
	chunk				(false, false, false, false, false, -1),
	wire				(false, false, false, false, false, -1),
	seed				(false, false, false, false, false, -1),
	reed				(false, false, false, false, false, -1),
	sheet				(false, false, false, false, false, -1),
	crop				(false, false, false, false, false, -1),
	plant				(false, false, false, false, false, -1),
	coin				(false, false, false, false, false, -1),
	lumar				(false, false, false, false, false, -1),
	ground				(false, false, false, false, false, -1),
	cable				(false, false, false, false, false, -1),
	reduced				(false, false, false, false, false, -1),
	component			(false, false, false, false, false, -1),
	crystalline			(false, false, false, false, false, -1),
	cleanGravel			(false, false, false, false, false, -1),
	dirtyGravel			(false, false, false, false, false, -1),
	wax					(false, false, false, false, false, -1),
	wall				(false, false, false, false, false, -1),
	tube				(false, false, false, false, false, -1),
	list				(false, false, false, false, false, -1),
	food				(false, false, false, false, false, -1),
	gear				(false, false, false, false, false, -1), // Introduced by SirSengir
	coral				(false, false, false, false, false, -1),
	shard				(false, false, false, false, false, -1),
	clump				(false, false, false, false, false, -1),
	flower				(false, false, false, false, false, -1),
	storage				(false, false, false, false, false, -1),
	material			(false, false, false, false, false, -1),
	plasma				(false, false, false, false, false, -1),
	element				(false, false, false, false, false, -1),
	molecule			(false, false, false, false, false, -1),
	wafer				(false, false, false, false, false, -1),
	compressed			(false, false, false, false, false, -1),
	fertilizer			(false, false, false, false, false, -1),
	chest				(false, false, false, false, false, -1),
	raw					(false, false, false, false, false, -1),
	pane				(false, false, false, false, false, -1), // New forge prefix for panes
	chipset				(false, false, false, false, false, -1), // BuildCraft chipsets
	slimeball			(false, false, false, false, false, -1); // Introduced by MFR
	
	static {
		pulp.mPrefixInto = dust;
		oreGem.mPrefixInto = ore;
		leaves.mPrefixInto = treeLeaves;
		sapling.mPrefixInto = treeSapling;
		itemDust.mPrefixInto = dust;
		dustDirty.mPrefixInto = dustImpure;
		ingotQuad.mPrefixInto = ingotQuadruple;
		plateQuad.mPrefixInto = plateQuadruple;
		stoneBrick.mPrefixInto = stoneBricks;
		stoneBricksMossy.mPrefixInto = stoneMossyBricks;
		
		block.ignoreMaterials(Materials.Glowstone, Materials.DarkIron);
		
		// These are only the important ones.
		gem.mNotGeneratedItems.add(Materials.Coal);
		gem.mNotGeneratedItems.add(Materials.Charcoal);
		gem.mNotGeneratedItems.add(Materials.NetherStar);
		gem.mNotGeneratedItems.add(Materials.Diamond);
		gem.mNotGeneratedItems.add(Materials.Emerald);
		gem.mNotGeneratedItems.add(Materials.Lapis);
		gem.mNotGeneratedItems.add(Materials.NetherQuartz);
		gem.mNotGeneratedItems.add(Materials.EnderPearl);
		gem.mNotGeneratedItems.add(Materials.EnderEye);
		gem.mNotGeneratedItems.add(Materials.Flint);
		dust.mNotGeneratedItems.add(Materials.Bone);
		dust.mNotGeneratedItems.add(Materials.Redstone);
		dust.mNotGeneratedItems.add(Materials.Glowstone);
		dust.mNotGeneratedItems.add(Materials.Gunpowder);
		dust.mNotGeneratedItems.add(Materials.Sugar);
		dust.mNotGeneratedItems.add(Materials.Blaze);
		stick.mNotGeneratedItems.add(Materials.Wood);
		stick.mNotGeneratedItems.add(Materials.Bone);
		stick.mNotGeneratedItems.add(Materials.Blaze);
		ingot.mNotGeneratedItems.add(Materials.Iron);
		ingot.mNotGeneratedItems.add(Materials.Gold);
		nugget.mNotGeneratedItems.add(Materials.Gold);
		plate.mNotGeneratedItems.add(Materials.Paper);
		cell.mNotGeneratedItems.add(Materials.Empty);
		cell.mNotGeneratedItems.add(Materials.Water);
		cell.mNotGeneratedItems.add(Materials.Lava);
		cell.mNotGeneratedItems.add(Materials.Oxygen);
		cell.mNotGeneratedItems.add(Materials.ConstructionFoam);
		cell.mNotGeneratedItems.add(Materials.UUMatter);
		cell.mNotGeneratedItems.add(Materials.BioFuel);
		cellPlasma.mNotGeneratedItems.add(Materials.Empty);
		bucket.mNotGeneratedItems.add(Materials.Empty);
		bucket.mNotGeneratedItems.add(Materials.Lava);
		bucket.mNotGeneratedItems.add(Materials.Milk);
		bucket.mNotGeneratedItems.add(Materials.Water);
		bottle.mNotGeneratedItems.add(Materials.Empty);
		bottle.mNotGeneratedItems.add(Materials.Water);
		bottle.mNotGeneratedItems.add(Materials.Milk);
		block.mNotGeneratedItems.add(Materials.Iron);
		block.mNotGeneratedItems.add(Materials.Gold);
		block.mNotGeneratedItems.add(Materials.Lapis);
		block.mNotGeneratedItems.add(Materials.Emerald);
		block.mNotGeneratedItems.add(Materials.Redstone);
		block.mNotGeneratedItems.add(Materials.Diamond);
		block.mNotGeneratedItems.add(Materials.Coal);
	}
	
	public final ArrayList<ItemStack> mPrefixedItems = new ArrayList<ItemStack>();
	
	public boolean add(ItemStack aStack) {
		if (aStack == null) return false;
		if (!contains(aStack)) mPrefixedItems.add(aStack);
		while (mPrefixedItems.contains(null)) mPrefixedItems.remove(null);
		return true;
	}
	
	public boolean contains(ItemStack aStack) {
		if (aStack == null) return false;
		for (ItemStack tStack : mPrefixedItems) if (GT_Utility.areStacksEqual(aStack, tStack, !tStack.hasTagCompound())) return true;
		return false;
	}
	
	public boolean dontGenerateItem(Materials aMaterial) {
		return mNotGeneratedItems.contains(aMaterial);
	}
	
	public boolean ignoreMaterials(Materials... aMaterials) {
		for (Materials tMaterial : aMaterials) mIgnoredMaterials.add(tMaterial);
		return true;
	}
	
	public boolean isIgnored(Materials aMaterial) {
		return mIgnoredMaterials.contains(aMaterial);
	}
	
	public boolean add(IOreRecipeRegistrator aRegistrator) {
		if (aRegistrator == null) return false;
		return mOreProcessing.add(aRegistrator);
	}
	
	public void processOre(List<OreDictEntry> entries) {
		for (IOreRecipeRegistrator tRegistrator : mOreProcessing) {
			tRegistrator.registerOre(this, entries);
		}
	}
	
	public final String mUnlocalizedName;
	public final boolean mIsUnificatable, mIsMaterialBased, mIsSelfReferencing, mIsContainer, mDontUnificateActively;
	public OrePrefixes mPrefixInto = this;
	private final ArrayList<Materials> mNotGeneratedItems = new ArrayList<Materials>(), mIgnoredMaterials = new ArrayList<Materials>();
	public final ArrayList<IOreRecipeRegistrator> mOreProcessing = new ArrayList<IOreRecipeRegistrator>();
	
	/**
	 * Used to determine the amount of Material this Prefix contains.
	 * Multiply or Divide GregTech_API.MATERIAL_UNIT to set the Amounts in comparision to one Ingot.
	 * 0 = Null
	 * Negative = Undefined Amount
	 */
	public final long mMaterialAmount;
	
	private OrePrefixes(boolean aIsUnificatable, boolean aIsMaterialBased, boolean aIsSelfReferencing, boolean aIsContainer, boolean aDontUnificateActively, long aMaterialAmount) {
		mIsUnificatable = aIsUnificatable;
		mIsMaterialBased = aIsMaterialBased;
		mIsSelfReferencing = aIsSelfReferencing;
		mIsContainer = aIsContainer;
		mDontUnificateActively = aDontUnificateActively;
		mMaterialAmount = aMaterialAmount;
		mUnlocalizedName = "oreprefixes." + name();
	}
	
	public static OrePrefixes getOrePrefix(String aOre) {
		for (OrePrefixes tPrefix : values()) if (aOre.startsWith(tPrefix.toString())) {
			if (tPrefix == oreNether && aOre.equals("oreNetherQuartz")) return ore;
			return tPrefix;
		}
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
	
	public static OrePrefixes getPrefix(String aPrefixName) {
		return getPrefix(aPrefixName, null);
	}
	
	public static OrePrefixes getPrefix(String aPrefixName, OrePrefixes aReplacement) {
		try {
			OrePrefixes value = OrePrefixes.valueOf(aPrefixName);
			if (value != null) return value;
		} catch (IllegalArgumentException e) {}
		
		return aReplacement;
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
	
	public static volatile int VERSION = 416;
}