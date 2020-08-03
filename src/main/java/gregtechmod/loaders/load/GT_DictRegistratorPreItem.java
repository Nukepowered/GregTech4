package gregtechmod.loaders.load;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_DictRegistratorPreItem implements Runnable {
	@Override
	public void run() {
        GT_Log.out.println("GT_Mod: Adding certain Items to the Unification Blacklist.");
		GT_OreDictUnificator.addToBlacklist(GT_ModHandler.getIC2Item("industrialDiamond", 1));
		
        GT_Log.out.println("GT_Mod: Register OreDict Entries of Non-GT-Items.");
        GT_OreDictUnificator.registerOre("molecule_2o"								, GT_ModHandler.getAirCell(1));
    	GT_OreDictUnificator.registerOre("molecule_2o"								, GT_ModHandler.getIC2Item("airCell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Empty			, GT_ModHandler.getEmptyCell(1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Empty			, GT_ModHandler.getIC2Item("cell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Empty			, GT_ModHandler.getIC2Item("cellEmpty", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Oxygen			, GT_ModHandler.getAirCell(1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Oxygen			, GT_ModHandler.getIC2Item("airCell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Lava			, GT_ModHandler.getLavaCell(1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Lava			, GT_ModHandler.getIC2Item("lavaCell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Water			, GT_ModHandler.getWaterCell(1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Water			, GT_ModHandler.getIC2Item("waterCell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.Creosote		, GT_ModHandler.getRCItem("fluid.creosote.cell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.BioFuel			, GT_ModHandler.getIC2Item("biofuelCell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.CoalFuel		, GT_ModHandler.getIC2Item("coalfuelCell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.UUMatter		, GT_ModHandler.getIC2Item("UuMatterCell", 1));
    	GT_OreDictUnificator.add(OrePrefixes.cell		, Materials.ConstructionFoam, GT_ModHandler.getIC2Item("CFCell", 1));
    	
		GT_OreDictUnificator.registerOre(OrePrefixes.gem, Materials.Diamond			, GT_ModHandler.getIC2Item("industrialDiamond", 1));
		GT_OreDictUnificator.registerOre("craftingIndustrialDiamond"				, GT_ModHandler.getIC2Item("industrialDiamond", 1));
		
		GT_OreDictUnificator.registerOre("stoneObsidian"			, new ItemStack(Block.obsidian			, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre("stoneMossy"				, new ItemStack(Block.cobblestoneMossy	, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneCobble"				, new ItemStack(Block.cobblestoneMossy	, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneCobble"				, new ItemStack(Block.cobblestone		, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneSmooth"				, new ItemStack(Block.stone				, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneBricks"				, new ItemStack(Block.stoneBrick		, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneMossy"				, new ItemStack(Block.stoneBrick		, 1, 1));
        GT_OreDictUnificator.registerOre("stoneCracked"				, new ItemStack(Block.stoneBrick		, 1, 2));
        GT_OreDictUnificator.registerOre("stoneChiseled"			, new ItemStack(Block.stoneBrick		, 1, 3));
        GT_OreDictUnificator.registerOre("stoneSand"				, new ItemStack(Block.sandStone			, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneNetherrack"			, new ItemStack(Block.netherrack		, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneNetherBrick"			, new ItemStack(Block.netherBrick		, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneEndstone"			, new ItemStack(Block.whiteStone		, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
        GT_OreDictUnificator.registerOre("stoneAbyssal"				, GT_ModHandler.getRCItem("cube.stone.abyssal", 1));
        GT_OreDictUnificator.registerOre("stoneQuarried"			, GT_ModHandler.getRCItem("cube.stone.quarried", 1));
    	
        GT_OreDictUnificator.registerOre("itemIridium"				, GT_ModHandler.getIC2Item("iridiumOre", 1));
        
    	GT_OreDictUnificator.add("plateAlloyIridium"		, GT_ModHandler.getIC2Item("iridiumPlate", 1));
    	GT_OreDictUnificator.add("plateAlloyAdvanced"		, GT_ModHandler.getIC2Item("advancedAlloy", 1));
    	GT_OreDictUnificator.add("plateAlloyCarbon"			, GT_ModHandler.getIC2Item("carbonPlate", 1));
    	GT_OreDictUnificator.add("plateDenseCopper"			, GT_ModHandler.getIC2Item("denseCopperPlate", 1, GT_ModHandler.getIC2Item("denseplatecopper", 1)));
    	GT_OreDictUnificator.add("plateDenseTin"			, GT_ModHandler.getIC2Item("denseplatetin", 1));
    	GT_OreDictUnificator.add("plateDenseBronze"			, GT_ModHandler.getIC2Item("denseplatebronze", 1));
    	GT_OreDictUnificator.add("plateDenseGold"			, GT_ModHandler.getIC2Item("denseplategold", 1));
    	GT_OreDictUnificator.add("plateDenseIron"			, GT_ModHandler.getIC2Item("denseplateiron", 1));
    	GT_OreDictUnificator.add("plateDenseRefinedIron"	, GT_ModHandler.getIC2Item("denseplateadviron", 1));
    	GT_OreDictUnificator.add("plateDenseLead"			, GT_ModHandler.getIC2Item("denseplatelead", 1));
    	GT_OreDictUnificator.add("plateDenseLapis"			, GT_ModHandler.getIC2Item("denseplatelapi", 1, GT_ModHandler.getIC2Item("denseplatelapis", 1)));
    	GT_OreDictUnificator.add("plateDenseObsidian"		, GT_ModHandler.getIC2Item("denseplateobsidian", 1));
    	
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.record13, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordCat, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordBlocks, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordStrad, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordStal, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordFar, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordMall, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordMellohi, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordWard, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordChirp, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.record11, 1));
    	GT_OreDictUnificator.registerOre("itemRecord" , new ItemStack(Item.recordWait, 1));
    	
    	if (Block.enderChest != null)
    	GT_OreDictUnificator.registerOre("craftingEnderChest"	, new ItemStack(Block.enderChest, 1));
    	
    	GT_OreDictUnificator.registerOre("glassReinforced"		, GT_ModHandler.getIC2Item("reinforcedGlass", 1));
    	GT_OreDictUnificator.registerOre("glassReinforced"		, GT_ModHandler.getTEItem("hardenedGlass", 1));
    	
        GT_Log.out.println("GT_Mod: Register Unification Entries");
        
    	GT_OreDictUnificator.add("oreCoal"			, new ItemStack(Block.oreCoal, 1));
    	GT_OreDictUnificator.add("oreIron"			, new ItemStack(Block.oreIron, 1));
    	GT_OreDictUnificator.add("oreLapis"			, new ItemStack(Block.oreLapis, 1));
    	GT_OreDictUnificator.add("oreRedstone"		, new ItemStack(Block.oreRedstone, 1));
    	GT_OreDictUnificator.add("oreRedstone"		, new ItemStack(Block.oreRedstoneGlowing, 1));
    	GT_OreDictUnificator.add("oreGold"			, new ItemStack(Block.oreGold, 1));
    	GT_OreDictUnificator.add("oreDiamond"		, new ItemStack(Block.oreDiamond, 1));
    	GT_OreDictUnificator.add("oreEmerald"		, new ItemStack(Block.oreEmerald, 1));
        GT_OreDictUnificator.add("oreNetherQuartz"	, new ItemStack(Block.oreNetherQuartz, 1));
        
    	GT_OreDictUnificator.add("oreGalena"			, new ItemStack(GregTech_API.sBlockList[2], 1, 1));
    	GT_OreDictUnificator.add("oreIridium"			, new ItemStack(GregTech_API.sBlockList[2], 1, 2));
    	GT_OreDictUnificator.add("oreRuby"				, new ItemStack(GregTech_API.sBlockList[2], 1, 3));
    	GT_OreDictUnificator.add("oreSapphire"			, new ItemStack(GregTech_API.sBlockList[2], 1, 4));
    	GT_OreDictUnificator.add("oreBauxite"			, new ItemStack(GregTech_API.sBlockList[2], 1, 5));
    	GT_OreDictUnificator.add("orePyrite"			, new ItemStack(GregTech_API.sBlockList[2], 1, 6));
    	GT_OreDictUnificator.add("oreCinnabar"			, new ItemStack(GregTech_API.sBlockList[2], 1, 7));
    	GT_OreDictUnificator.add("oreSphalerite"		, new ItemStack(GregTech_API.sBlockList[2], 1, 8));
    	GT_OreDictUnificator.add("oreTungstate"			, new ItemStack(GregTech_API.sBlockList[2], 1, 9));
    	GT_OreDictUnificator.add("oreCooperite"			, new ItemStack(GregTech_API.sBlockList[2], 1,10));
    	GT_OreDictUnificator.add("oreOlivine"			, new ItemStack(GregTech_API.sBlockList[2], 1,11));
    	GT_OreDictUnificator.add("oreSodalite"			, new ItemStack(GregTech_API.sBlockList[2], 1,12));
    	GT_OreDictUnificator.add("oreTetrahedrite"		, new ItemStack(GregTech_API.sBlockList[2], 1,13));
    	GT_OreDictUnificator.add("oreCassiterite"		, new ItemStack(GregTech_API.sBlockList[2], 1,14));
    	GT_OreDictUnificator.add("oreNickel"			, new ItemStack(GregTech_API.sBlockList[2], 1,15));
        
    	GT_OreDictUnificator.registerOre("stoneGraniteBlack", new ItemStack(GregTech_API.sBlockList[5], 1, 0));
    	GT_OreDictUnificator.registerOre("stoneGraniteBlack", new ItemStack(GregTech_API.sBlockList[5], 1, 1));
    	GT_OreDictUnificator.registerOre("stoneGraniteBlack", new ItemStack(GregTech_API.sBlockList[5], 1, 2));
    	GT_OreDictUnificator.registerOre("stoneGraniteBlack", new ItemStack(GregTech_API.sBlockList[5], 1, 3));
    	GT_OreDictUnificator.registerOre("stoneGraniteBlack", new ItemStack(GregTech_API.sBlockList[5], 1, 4));
    	GT_OreDictUnificator.registerOre("stoneGraniteBlack", new ItemStack(GregTech_API.sBlockList[5], 1, 5));
    	GT_OreDictUnificator.registerOre("stoneGraniteBlack", new ItemStack(GregTech_API.sBlockList[5], 1, 6));
    	GT_OreDictUnificator.registerOre("stoneGraniteBlack", new ItemStack(GregTech_API.sBlockList[5], 1, 7));
    	
    	GT_OreDictUnificator.registerOre("stoneGraniteRed"	, new ItemStack(GregTech_API.sBlockList[5], 1, 8));
    	GT_OreDictUnificator.registerOre("stoneGraniteRed"	, new ItemStack(GregTech_API.sBlockList[5], 1, 9));
    	GT_OreDictUnificator.registerOre("stoneGraniteRed"	, new ItemStack(GregTech_API.sBlockList[5], 1,10));
    	GT_OreDictUnificator.registerOre("stoneGraniteRed"	, new ItemStack(GregTech_API.sBlockList[5], 1,11));
    	GT_OreDictUnificator.registerOre("stoneGraniteRed"	, new ItemStack(GregTech_API.sBlockList[5], 1,12));
    	GT_OreDictUnificator.registerOre("stoneGraniteRed"	, new ItemStack(GregTech_API.sBlockList[5], 1,13));
    	GT_OreDictUnificator.registerOre("stoneGraniteRed"	, new ItemStack(GregTech_API.sBlockList[5], 1,14));
    	GT_OreDictUnificator.registerOre("stoneGraniteRed"	, new ItemStack(GregTech_API.sBlockList[5], 1,15));
    	
		GT_OreDictUnificator.add("craftingSaltpeterToGunpowder"	, GT_ModHandler.getTEItem("crystalNiter", 1));
		GT_OreDictUnificator.add("craftingSulfurToGunpowder"	, GT_ModHandler.getTEItem("crystalSulfur", 1));
		
		GT_OreDictUnificator.add("itemRubber"			, GT_ModHandler.getIC2Item("rubber", 1));
		GT_OreDictUnificator.add("gemIridium"			, GT_ModHandler.getIC2Item("iridiumOre", 1));
	    GT_OreDictUnificator.add("gemEnderEye"			, new ItemStack(Item.eyeOfEnder, 1));
	    GT_OreDictUnificator.add("gemEnderPearl"		, new ItemStack(Item.enderPearl, 1));
	    GT_OreDictUnificator.add("gemDiamond"			, new ItemStack(Item.diamond, 1));
		GT_OreDictUnificator.add("gemEmerald"			, new ItemStack(Item.emerald, 1));
	    GT_OreDictUnificator.add("gemCoal"				, new ItemStack(Item.coal, 1, 0));
	    GT_OreDictUnificator.add("gemCharcoal"			, new ItemStack(Item.coal, 1, 1));
		GT_OreDictUnificator.add("gemLapis"				, new ItemStack(Item.dyePowder, 1, 4));
    	GT_OreDictUnificator.add("gemNetherQuartz"		, new ItemStack(Item.netherQuartz, 1));
    	GT_OreDictUnificator.add("gemNetherStar"		, new ItemStack(Item.netherStar, 1));
		GT_OreDictUnificator.add(OrePrefixes.nugget	, Materials.Gold			, new ItemStack(Item.goldNugget, 1));
		GT_OreDictUnificator.add(OrePrefixes.ingot	, Materials.Gold			, new ItemStack(Item.ingotGold, 1));
		GT_OreDictUnificator.add(OrePrefixes.ingot	, Materials.Iron			, new ItemStack(Item.ingotIron, 1));
		GT_OreDictUnificator.add(OrePrefixes.dust	, Materials.Sugar			, new ItemStack(Item.sugar, 1));
		GT_OreDictUnificator.add(OrePrefixes.stick	, Materials.Wood			, new ItemStack(Item.stick, 1));
		
		GT_OreDictUnificator.add(OrePrefixes.ingot	, Materials.Tin				, GT_ModHandler.getIC2Item("tinIngot", 1));
		GT_OreDictUnificator.add(OrePrefixes.ingot	, Materials.Copper			, GT_ModHandler.getIC2Item("copperIngot", 1));
		GT_OreDictUnificator.add(OrePrefixes.ingot	, Materials.Bronze			, GT_ModHandler.getIC2Item("bronzeIngot", 1));
		GT_OreDictUnificator.add(OrePrefixes.dust	, Materials.HydratedCoal	, GT_ModHandler.getIC2Item("hydratedCoalDust", 1));
		GT_OreDictUnificator.add(OrePrefixes.dust	, Materials.Redstone		, new ItemStack(Item.redstone, 1));
    	GT_OreDictUnificator.add(OrePrefixes.dust	, Materials.Gunpowder		, new ItemStack(Item.gunpowder, 1));
    	GT_OreDictUnificator.add(OrePrefixes.dust	, Materials.Glowstone		, new ItemStack(Item.glowstone, 1));
    	GT_OreDictUnificator.add(OrePrefixes.dust	, Materials.Blaze			, new ItemStack(Item.blazePowder, 1));
    	GT_OreDictUnificator.add(OrePrefixes.stick	, Materials.Blaze			, new ItemStack(Item.blazeRod, 1));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Iron			, new ItemStack(Block.blockIron, 1, 0));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Gold			, new ItemStack(Block.blockGold, 1, 0));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Diamond			, new ItemStack(Block.blockDiamond, 1, 0));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Emerald			, new ItemStack(Block.blockEmerald, 1, 0));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Lapis			, new ItemStack(Block.blockLapis, 1, 0));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Coal			, new ItemStack(Block.coalBlock, 1, 0));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Redstone		, new ItemStack(Block.blockRedstone, 1, 0));
		GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Copper			, GT_ModHandler.getIC2Item("copperBlock", 1));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Tin				, GT_ModHandler.getIC2Item("tinBlock", 1));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Bronze			, GT_ModHandler.getIC2Item("bronzeBlock", 1));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Uranium			, GT_ModHandler.getIC2Item("uraniumBlock", 1));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Silver			, new ItemStack(GregTech_API.sBlockList[0], 1, 3));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Ruby			, new ItemStack(GregTech_API.sBlockList[0], 1, 4));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Sapphire		, new ItemStack(GregTech_API.sBlockList[0], 1, 5));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Aluminium		, new ItemStack(GregTech_API.sBlockList[0], 1, 7));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Titanium		, new ItemStack(GregTech_API.sBlockList[0], 1, 8));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Chrome			, new ItemStack(GregTech_API.sBlockList[0], 1, 9));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Steel			, new ItemStack(GregTech_API.sBlockList[0], 1,11));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Brass			, new ItemStack(GregTech_API.sBlockList[0], 1,12));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Lead			, new ItemStack(GregTech_API.sBlockList[4], 1, 0));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Electrum		, new ItemStack(GregTech_API.sBlockList[4], 1, 1));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Zinc			, new ItemStack(GregTech_API.sBlockList[4], 1, 2));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Olivine			, new ItemStack(GregTech_API.sBlockList[4], 1, 3));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.GreenSapphire	, new ItemStack(GregTech_API.sBlockList[4], 1, 4));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Platinum		, new ItemStack(GregTech_API.sBlockList[4], 1, 5));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Tungsten		, new ItemStack(GregTech_API.sBlockList[4], 1, 6));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Nickel			, new ItemStack(GregTech_API.sBlockList[4], 1, 7));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Invar			, new ItemStack(GregTech_API.sBlockList[4], 1,10));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Osmium			, new ItemStack(GregTech_API.sBlockList[4], 1,11));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.Iridium			, new ItemStack(GregTech_API.sBlockList[4], 1,12));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.GarnetYellow	, new ItemStack(GregTech_API.sBlockList[4], 1,14));
        GT_OreDictUnificator.add(OrePrefixes.block	, Materials.GarnetRed		, new ItemStack(GregTech_API.sBlockList[4], 1,15));
        
        GT_Log.out.println("GT_Mod: Register other Mods Unification Targets.");
        if (GT_Mod.sUnificatorFR) {
        	GT_OreDictUnificator.override("ingotCopper"		, GT_ModHandler.getFRItem("ingotCopper", 1));
        	GT_OreDictUnificator.override("ingotTin"		, GT_ModHandler.getFRItem("ingotTin", 1));
        	GT_OreDictUnificator.override("ingotBronze"		, GT_ModHandler.getFRItem("ingotBronze", 1));
        	GT_OreDictUnificator.override("dustAsh"			, GT_ModHandler.getFRItem("ash", 1));
        	GT_OreDictUnificator.override("dustWood"		, GT_ModHandler.getFRItem("woodPulp", 1));
        	GT_OreDictUnificator.override("pulpWood"		, GT_ModHandler.getFRItem("woodPulp", 1));
        }
        if (GT_Mod.sUnificatorRC) {
        	GT_OreDictUnificator.override("blockSteel"		, GT_ModHandler.getRCItem("cube.steel", 1));
        	GT_OreDictUnificator.override("nuggetIron"		, GT_ModHandler.getRCItem("nugget.iron", 1));
        	GT_OreDictUnificator.override("nuggetSteel"		, GT_ModHandler.getRCItem("nugget.steel", 1));
        	GT_OreDictUnificator.override("ingotSteel"		, GT_ModHandler.getRCItem("part.ingot.steel", 1));
        	GT_OreDictUnificator.override("dustCharcoal"	, GT_ModHandler.getRCItem("dust.charcoal", 1));
        	GT_OreDictUnificator.override("dustObsidian"	, GT_ModHandler.getRCItem("dust.obsidian", 1));
        	GT_OreDictUnificator.override("dustSaltpeter"	, GT_ModHandler.getRCItem("dust.saltpeter", 1));
        	GT_OreDictUnificator.override("dustSulfur"		, GT_ModHandler.getRCItem("dust.sulfur", 1));
        }
        if (GT_Mod.sUnificatorTE) {
        	GT_OreDictUnificator.override("dustWood"		, GT_ModHandler.getTEItem("sawdust", 1));
    		GT_OreDictUnificator.override("pulpWood"		, GT_ModHandler.getTEItem("sawdust", 1));
        	GT_OreDictUnificator.override("dustGold"		, GT_ModHandler.getTEItem("dustGold", 1));
        	GT_OreDictUnificator.override("dustBrass"		, GT_ModHandler.getTEItem("dustBrass", 1));
        	GT_OreDictUnificator.override("dustBronze"		, GT_ModHandler.getTEItem("dustBronze", 1));
        	GT_OreDictUnificator.override("dustCopper"		, GT_ModHandler.getTEItem("dustCopper", 1));
        	GT_OreDictUnificator.override("dustElectrum"	, GT_ModHandler.getTEItem("dustElectrum", 1));
        	GT_OreDictUnificator.override("dustInvar"		, GT_ModHandler.getTEItem("dustInvar", 1));
        	GT_OreDictUnificator.override("dustIron"		, GT_ModHandler.getTEItem("dustIron", 1));
        	GT_OreDictUnificator.override("dustLead"		, GT_ModHandler.getTEItem("dustLead", 1));
        	GT_OreDictUnificator.override("dustNickel"		, GT_ModHandler.getTEItem("dustNickel", 1));
        	GT_OreDictUnificator.override("dustObsidian"	, GT_ModHandler.getTEItem("dustObsidian", 1));
        	GT_OreDictUnificator.override("dustPlatinum"	, GT_ModHandler.getTEItem("dustPlatinum", 1));
        	GT_OreDictUnificator.override("dustSilver"		, GT_ModHandler.getTEItem("dustSilver", 1));
        	GT_OreDictUnificator.override("dustTin"			, GT_ModHandler.getTEItem("dustTin", 1));
        	GT_OreDictUnificator.override("ingotCopper"		, GT_ModHandler.getTEItem("ingotCopper", 1));
        	GT_OreDictUnificator.override("ingotElectrum"	, GT_ModHandler.getTEItem("ingotElectrum", 1));
        	GT_OreDictUnificator.override("ingotInvar"		, GT_ModHandler.getTEItem("ingotInvar", 1));
        	GT_OreDictUnificator.override("ingotLead"		, GT_ModHandler.getTEItem("ingotLead", 1));
        	GT_OreDictUnificator.override("ingotNickel"		, GT_ModHandler.getTEItem("ingotNickel", 1));
        	GT_OreDictUnificator.override("ingotPlatinum"	, GT_ModHandler.getTEItem("ingotPlatinum", 1));
        	GT_OreDictUnificator.override("ingotSilver"		, GT_ModHandler.getTEItem("ingotSilver", 1));
        	GT_OreDictUnificator.override("ingotTin"		, GT_ModHandler.getTEItem("ingotTin", 1));
        	GT_OreDictUnificator.override("nuggetCopper"	, GT_ModHandler.getTEItem("nuggetCopper", 1));
        	GT_OreDictUnificator.override("nuggetElectrum"	, GT_ModHandler.getTEItem("nuggetElectrum", 1));
        	GT_OreDictUnificator.override("nuggetInvar"		, GT_ModHandler.getTEItem("nuggetInvar", 1));
        	GT_OreDictUnificator.override("nuggetLead"		, GT_ModHandler.getTEItem("nuggetLead", 1));
        	GT_OreDictUnificator.override("nuggetNickel"	, GT_ModHandler.getTEItem("nuggetNickel", 1));
        	GT_OreDictUnificator.override("nuggetPlatinum"	, GT_ModHandler.getTEItem("nuggetPlatinum", 1));
        	GT_OreDictUnificator.override("nuggetSilver"	, GT_ModHandler.getTEItem("nuggetSilver", 1));
        	GT_OreDictUnificator.override("nuggetTin"		, GT_ModHandler.getTEItem("nuggetTin", 1));
        	GT_OreDictUnificator.override("blockCopper"		, GT_ModHandler.getTEItem("blockCopper", 1));
        	GT_OreDictUnificator.override("blockElectrum"	, GT_ModHandler.getTEItem("blockElectrum", 1));
        	GT_OreDictUnificator.override("blockInvar"		, GT_ModHandler.getTEItem("blockInvar", 1));
        	GT_OreDictUnificator.override("blockLead"		, GT_ModHandler.getTEItem("blockLead", 1));
        	GT_OreDictUnificator.override("blockNickel"		, GT_ModHandler.getTEItem("blockNickel", 1));
        	GT_OreDictUnificator.override("blockPlatinum"	, GT_ModHandler.getTEItem("blockPlatinum", 1));
        	GT_OreDictUnificator.override("blockSilver"		, GT_ModHandler.getTEItem("blockSilver", 1));
        	GT_OreDictUnificator.override("blockTin"		, GT_ModHandler.getTEItem("blockTin", 1));
        }
        if (GT_Mod.sUnificatorRP) {
        	GT_OreDictUnificator.override("gemRuby"			, GT_ModHandler.mRuby);
        	GT_OreDictUnificator.override("gemSapphire"		, GT_ModHandler.mSapphire);
        	GT_OreDictUnificator.override("gemGreenSapphire", GT_ModHandler.mGreenSapphire);
        	GT_OreDictUnificator.override("ingotSilver"		, GT_ModHandler.mSilver);
        	GT_OreDictUnificator.override("ingotCopper"		, GT_ModHandler.mCopper);
        	GT_OreDictUnificator.override("ingotTin"		, GT_ModHandler.mTin);
        	GT_OreDictUnificator.override("ingotBrass"		, GT_ModHandler.mBrass);
        	GT_OreDictUnificator.override("nuggetIron"		, GT_ModHandler.mIronNugget);
        	GT_OreDictUnificator.override("nuggetSilver"	, GT_ModHandler.mSilverNugget);
        	GT_OreDictUnificator.override("nuggetTin"		, GT_ModHandler.mTinNugget);
        	GT_OreDictUnificator.override("nuggetCopper"	, GT_ModHandler.mCopperNugget);
        }
        if (GT_Mod.sUnificatorTC) {
        	GT_OreDictUnificator.override("nuggetIron"		, GT_ModHandler.mNuggetIron);
        	GT_OreDictUnificator.override("nuggetSilver"	, GT_ModHandler.mNuggetSilver);
        	GT_OreDictUnificator.override("nuggetTin"		, GT_ModHandler.mNuggetTin);
        	GT_OreDictUnificator.override("nuggetCopper"	, GT_ModHandler.mNuggetCopper);
        	GT_OreDictUnificator.override("nuggetLead"		, GT_ModHandler.mNuggetLead);
        }
        
    	//GT_OreDictUnificator.override("plateTin"		, GT_ModHandler.getRCItem("part.plate.tin", 1)); needs 50% Iron and 50% Tin to be crafted
    	GT_OreDictUnificator.override("plateIron"		, GT_ModHandler.getRCItem("part.plate.iron", 1));
    	GT_OreDictUnificator.override("plateSteel"		, GT_ModHandler.getRCItem("part.plate.steel", 1));
	}
}
