package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.items.GT_MetaItem_SmallDust;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_RecyclingRecipeLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.out.println("GT_Mod: Adding all the Reverse Recipes for the Furnace/Macerator/Sawmill.");
        
        GT_RecipeRegistrator.registerUsagesForMaterials(new ItemStack(Block.planks, 1), GT_MetaItem_SmallDust.instance.getStack(15, 4), null, false, true);
    	
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.helmetChain, 1)			, GT_MetaItem_SmallDust.instance.getStack(26, 5), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.plateChain, 1)			, GT_MetaItem_SmallDust.instance.getStack(26, 8), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.legsChain, 1)			, GT_MetaItem_SmallDust.instance.getStack(26, 7), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.bootsChain, 1)			, GT_MetaItem_SmallDust.instance.getStack(26, 4), null, 0, true);
        
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.pocketSundial, 1)		, GT_OreDictUnificator.get("dustGold", 4), GT_OreDictUnificator.get("dustRedstone", 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.compass, 1)				, GT_OreDictUnificator.get("dustIron", 4), GT_OreDictUnificator.get("dustRedstone", 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.shears, 1)				, GT_OreDictUnificator.get("dustIron", 2), null, 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.minecartEmpty, 1)		, GT_OreDictUnificator.get("dustIron", 5), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.bucketEmpty, 1)			, GT_OreDictUnificator.get("dustIron", 3), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.doorIron, 1)			, GT_OreDictUnificator.get("dustIron", 6), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.cauldron, 1)			, GT_OreDictUnificator.get("dustIron", 7), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.fenceIron, 2)			, GT_MetaItem_SmallDust.instance.getStack(241, 3), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("ironFence", 2)	, GT_MetaItem_SmallDust.instance.getStack(241, 3), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("fuelCan", 1)		, GT_OreDictUnificator.get("dustTin", 7), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("tinCan", 1)		, GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Tin, 2), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getIC2Item("ironFurnace", 1)	, GT_OreDictUnificator.get("dustIron", 5), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.pressurePlateGold, 1)	, GT_OreDictUnificator.get("dustGold", 2), null, 0, true);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.pressurePlateIron, 1)	, GT_OreDictUnificator.get("dustIron", 2), null, 0, true);
        
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Item.pocketSundial, 1)		, GT_OreDictUnificator.get("ingotGold", 4));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Item.compass, 1)				, GT_OreDictUnificator.get("ingotIron", 4));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Item.shears, 1)				, GT_OreDictUnificator.get("ingotIron", 2));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Item.minecartEmpty, 1)		, GT_OreDictUnificator.get("ingotIron", 5));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Item.bucketEmpty, 1)			, GT_OreDictUnificator.get("ingotIron", 3));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Item.doorIron, 1)				, GT_OreDictUnificator.get("ingotIron", 6));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Item.cauldron, 1)				, GT_OreDictUnificator.get("ingotIron", 7));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Block.anvil, 1, 0)			, GT_OreDictUnificator.get("ingotIron", 31));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Block.anvil, 1, 1)			, GT_OreDictUnificator.get("ingotIron", 20));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Block.anvil, 1, 2)			, GT_OreDictUnificator.get("ingotIron", 10));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_ModHandler.getIC2Item("fuelCan", 1)		, GT_OreDictUnificator.get("ingotTin", 7));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_ModHandler.getIC2Item("ironFurnace", 1)	, GT_OreDictUnificator.get("ingotIron", 5));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Block.pressurePlateGold, 1)	, GT_OreDictUnificator.get("ingotGold", 2));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Block.pressurePlateIron, 1)	, GT_OreDictUnificator.get("ingotIron", 2));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(Block.hopperBlock, 1)			, GT_OreDictUnificator.get("ingotIron", 5));
        GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 32, 1), GT_OreDictUnificator.get("ingotAluminium"			, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 33, 1), GT_OreDictUnificator.get("ingotBronze"				, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 34, 1), GT_OreDictUnificator.get("ingotBrass"				, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 35, 1), GT_OreDictUnificator.get("ingotSteel"				, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 36, 1), GT_OreDictUnificator.get("ingotTitanium"			, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 37, 1), GT_OreDictUnificator.get("ingotIron"				, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 38, 1), GT_OreDictUnificator.get("ingotTungstenSteel"		, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 39, 1), GT_OreDictUnificator.get("ingotStainlessSteel"		, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 51, 1), GT_OreDictUnificator.get("ingotBronze"				, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 52, 1), GT_OreDictUnificator.get("ingotSteel"				, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 53, 1), GT_OreDictUnificator.get("ingotMagnalium"			, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 54, 1), GT_OreDictUnificator.get("ingotTungstenSteel"		, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 96, 1), GT_OreDictUnificator.get("ingotIron"				, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 97, 1), GT_OreDictUnificator.get("ingotBronze"				, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 98, 1), GT_OreDictUnificator.get("ingotSteel"				, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack( 99, 1), GT_OreDictUnificator.get("ingotTitanium"			, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack(100, 1), GT_OreDictUnificator.get("ingotTungstenSteel"		, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack(101, 1), GT_OreDictUnificator.get("ingotIridium"			, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(GT_MetaItem_Component.instance.getStack(102, 1), GT_OreDictUnificator.get("ingotStainlessSteel"		, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1800), GT_OreDictUnificator.get("ingotBronze"			, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1801), GT_OreDictUnificator.get("ingotSteel"			, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1802), GT_OreDictUnificator.get("ingotStainlessSteel"	, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1803), GT_OreDictUnificator.get("ingotTungstenSteel"	, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1900), GT_OreDictUnificator.get("ingotBrass"			, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1901), GT_OreDictUnificator.get("ingotElectrum"		, 3));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1850), GT_OreDictUnificator.get("ingotBronze"			, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1851), GT_OreDictUnificator.get("ingotSteel"			, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1852), GT_OreDictUnificator.get("ingotStainlessSteel"	, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1853), GT_OreDictUnificator.get("ingotTungstenSteel"	, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1950), GT_OreDictUnificator.get("ingotBrass"			, 6));
		GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1951), GT_OreDictUnificator.get("ingotElectrum"		, 6));
		
        GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 32, 1), GT_OreDictUnificator.get("dustAluminium"				, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 33, 1), GT_OreDictUnificator.get("dustBronze"					, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 34, 1), GT_OreDictUnificator.get("dustBrass"					, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 35, 1), GT_OreDictUnificator.get("dustSteel"					, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 36, 1), GT_OreDictUnificator.get("dustTitanium"				, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 37, 1), GT_OreDictUnificator.get("dustIron"					, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 38, 1), GT_OreDictUnificator.getFirstOre("dustTungstenSteel"	, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 39, 1), GT_OreDictUnificator.get("dustStainlessSteel"			, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 51, 1), GT_OreDictUnificator.get("dustBronze"					, 3));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 52, 1), GT_OreDictUnificator.get("dustSteel"					, 3));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 53, 1), GT_OreDictUnificator.getFirstOre("dustMagnalium"		, 3));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 54, 1), GT_OreDictUnificator.getFirstOre("dustTungstenSteel"	, 3));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 96, 1), GT_OreDictUnificator.get("dustIron"					, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 97, 1), GT_OreDictUnificator.get("dustBronze"					, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 98, 1), GT_OreDictUnificator.get("dustSteel"					, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack( 99, 1), GT_OreDictUnificator.get("dustTitanium"				, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack(100, 1), GT_OreDictUnificator.getFirstOre("dustTungstenSteel"	, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack(101, 1), GT_OreDictUnificator.getFirstOre("dustIridium"		, 6));
		GT_ModHandler.addPulverisationRecipe(GT_MetaItem_Component.instance.getStack(102, 1), GT_OreDictUnificator.get("dustStainlessSteel"			, 6));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1800), GT_OreDictUnificator.get("dustBronze"				, 3));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1801), GT_OreDictUnificator.get("dustSteel"				, 3));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1802), GT_OreDictUnificator.get("dustStainlessSteel"		, 3));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1803), GT_OreDictUnificator.getFirstOre("dustTungstenSteel"		, 3));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1900), GT_OreDictUnificator.get("dustBrass"				, 3));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1901), GT_OreDictUnificator.get("dustElectrum"			, 3));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1850), GT_OreDictUnificator.get("dustBronze"				, 6));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1851), GT_OreDictUnificator.get("dustSteel"				, 6));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1852), GT_OreDictUnificator.get("dustStainlessSteel"		, 6));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1853), GT_OreDictUnificator.getFirstOre("dustTungstenSteel"		, 6));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1950), GT_OreDictUnificator.get("dustBrass"				, 6));
		GT_ModHandler.addPulverisationRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1951), GT_OreDictUnificator.get("dustElectrum"			, 6));
		
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.enchantmentTable, 1), GT_OreDictUnificator.get("dustDiamond", 2), GT_OreDictUnificator.get("dustObsidian", 4), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.furnaceIdle, 1), new ItemStack(Block.sand, 7), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.furnaceBurning, 1), new ItemStack(Block.sand, 7), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.stoneButton, 1), new ItemStack(Block.sand, 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.sign, 1, 0), GT_OreDictUnificator.get("dustWood", 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.doorWood, 1, 0), GT_OreDictUnificator.get("dustWood", 6), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.chest, 1, 0), GT_OreDictUnificator.get("dustWood", 8), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.woodenButton, 1, 0), GT_OreDictUnificator.get("dustWood", 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.pressurePlateStone, 1), new ItemStack(Block.sand, 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.pressurePlatePlanks, 1), GT_OreDictUnificator.get("dustWood", 2), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.torchRedstoneActive, 1), GT_OreDictUnificator.get("dustSmallWood", 2), new ItemStack(Item.redstone, 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.torchRedstoneIdle, 1), GT_OreDictUnificator.get("dustSmallWood", 2), new ItemStack(Item.redstone, 1), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.lever, 1), new ItemStack(Block.sand, 1), GT_OreDictUnificator.get("dustSmallWood", 2), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Block.ladder, 1, 0), GT_OreDictUnificator.get("dustWood", 1), null, 0, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.itemFrame, 1, 0), new ItemStack(Item.leather, 1), GT_OreDictUnificator.get("dustWood", 4), 95, false);
        GT_ModHandler.addPulverisationRecipe(new ItemStack(Item.bow, 1, 0), new ItemStack(Item.silk, 3), GT_OreDictUnificator.get("dustSmallWood", 3), 95, false);
        
        //GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Item.ingotIron, 1), null, new ItemStack(Item.ingotIron, 1), new ItemStack(Item.ingotIron, 1), new ItemStack(Item.stick, 1), new ItemStack(Item.ingotIron, 1), new ItemStack(Item.ingotIron, 1), null, new ItemStack(Item.ingotIron, 1)}), GT_OreDictUnificator.get("dustIron", 6), GT_OreDictUnificator.get("dustSmallWood", 2), 95, false);
        //GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Item.ingotGold, 1), null, new ItemStack(Item.ingotGold, 1), new ItemStack(Item.ingotGold, 1), new ItemStack(Item.stick, 1), new ItemStack(Item.ingotGold, 1), new ItemStack(Item.ingotGold, 1), new ItemStack(Item.redstone, 1), new ItemStack(Item.ingotGold, 1)}), GT_OreDictUnificator.get("dustGold", 6), new ItemStack(Item.redstone, 1), 95, false);
        
        ItemStack tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, GT_OreDictUnificator.get("ingotTin", 1), null, GT_OreDictUnificator.get("ingotTin", 1), null, GT_OreDictUnificator.get("ingotTin", 1), null, null, null});
        if (tStack != null) {
        	int tTinCanNuggetCount = 27/tStack.stackSize;
        	if (tTinCanNuggetCount > 0) {
            	tStack.stackSize = 1;
        		if (tTinCanNuggetCount % 9 == 0)
        			GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotTin", tTinCanNuggetCount/9));
        		else
        			GT_ModHandler.addSmeltingAndAlloySmeltingRecipe(tStack, GT_OreDictUnificator.get("nuggetTin", tTinCanNuggetCount));
        	}
        }
        
        if (16%GregTech_API.sTinCellCountPer4Ingots==0) {
        	GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getEmptyCell(1), GT_MetaItem_SmallDust.instance.getStack(244, 16/GregTech_API.sTinCellCountPer4Ingots), null, 0, true);
        } else {
        	GT_ModHandler.addPulverisationRecipe(GT_ModHandler.getEmptyCell(4), GT_MetaItem_SmallDust.instance.getStack(244, 64/GregTech_API.sTinCellCountPer4Ingots), null, 0, true);
        }
    }
}