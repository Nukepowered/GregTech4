package gregtechmod.loaders.postload;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.RecipeHandler;
import gregtechmod.common.items.GT_MetaItem_Material;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_CraftingRecipeLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("Adding Tool Recipes.");
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Sword_Flint			.getUndamaged(1), false, true, false, new Object[] { "F" ,  "F" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Pickaxe_Flint			.getUndamaged(1), false, true, false, new Object[] {"FFF", " S ", " S ", 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Shovel_Flint			.getUndamaged(1), false, true, false, new Object[] { "F" ,  "S" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Axe_Flint				.getUndamaged(1), false, true, false, new Object[] {"FF" , "FS" , " S" , 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hoe_Flint				.getUndamaged(1), false, true, false, new Object[] {"FF" , " S" , " S" , 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Sword_Steel			.getUndamaged(1), false, true, false, new Object[] { "F" ,  "F" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Pickaxe_Steel			.getUndamaged(1), false, true, false, new Object[] {"FFF", " S ", " S ", 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Shovel_Steel			.getUndamaged(1), false, true, false, new Object[] { "F" ,  "S" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Axe_Steel				.getUndamaged(1), false, true, false, new Object[] {"FF" , "FS" , " S" , 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hoe_Steel				.getUndamaged(1), false, true, false, new Object[] {"FF" , " S" , " S" , 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Sword_TungstenSteel	.getUndamaged(1), false, true, false, new Object[] { "F" ,  "F" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Pickaxe_TungstenSteel	.getUndamaged(1), false, true, false, new Object[] {"FFF", " S ", " S ", 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Shovel_TungstenSteel	.getUndamaged(1), false, true, false, new Object[] { "F" ,  "S" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Axe_TungstenSteel		.getUndamaged(1), false, true, false, new Object[] {"FF" , "FS" , " S" , 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hoe_TungstenSteel		.getUndamaged(1), false, true, false, new Object[] {"FF" , " S" , " S" , 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Scoop_Aluminium		.getUndamaged(1), false, true, false, new Object[] {"SWS", "SSS", " S ", 'S', OrePrefixes.stick.get(Materials.Aluminium), 'W', new ItemStack(Blocks.wool, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        
        GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.chainmail_helmet		, 1));
        GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.chainmail_chestplate	, 1));
        GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.chainmail_leggings	, 1));
        GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.chainmail_boots		, 1));
        
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.chainmail_helmet		, 1), false, true, false, new Object[] {"RRR", "RHR"		, 'R', OrePrefixes.ring.get(Materials.Steel), 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.chainmail_chestplate	, 1), false, true, false, new Object[] {"RHR", "RRR", "RRR"	, 'R', OrePrefixes.ring.get(Materials.Steel), 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.chainmail_leggings		, 1), false, true, false, new Object[] {"RRR", "RHR", "R R"	, 'R', OrePrefixes.ring.get(Materials.Steel), 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(new ItemStack(Items.chainmail_boots			, 1), false, true, false, new Object[] {"R R", "RHR"		, 'R', OrePrefixes.ring.get(Materials.Steel), 'H', GT_ToolDictNames.craftingToolHardHammer});
        
        GT_Log.log.info("Adding Wool and Color releated Recipes.");
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  0), false, new Object[] {new ItemStack(Blocks.wool, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), "dyeWhite"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  1), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeOrange"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  2), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeMagenta"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  3), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeLightBlue"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  4), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeYellow"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  5), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeLime"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  6), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyePink"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  7), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeGray"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  8), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeLightGray"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1,  9), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeCyan"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 10), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyePurple"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 11), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeBlue"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 12), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeBrown"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 13), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeGreen"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 14), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeRed"});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.wool, 1, 15), false, new Object[] {new ItemStack(Blocks.wool, 1,  0), "dyeBlack"});
		
		GT_Log.log.info("Putting a Potato on a Stick.");
	    GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Food_Potato_On_Stick			.get(1), false, new Object[] {new ItemStack(Items.stick, 1), new ItemStack(Items.potato			, 1)});
	    GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Food_Potato_On_Stick_Roasted	.get(1), false, new Object[] {new ItemStack(Items.stick, 1), new ItemStack(Items.baked_potato	, 1)});
		
        GT_Log.log.info("Adding 'The holy Planks of Sengir'.");
    	ItemStack tStack = GT_MetaItem_Material.instance.getStack(15, 1);
    	tStack.setStackDisplayName("The holy Planks of Sengir");
    	tStack.addEnchantment(Enchantment.smite, 10);
    	GT_ModHandler.addCraftingRecipe(tStack, new Object[] {"XXX", "XDX", "XXX", 'X', new ItemStack(Items.nether_star, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'D', new ItemStack(Blocks.dragon_egg, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    	
    	GT_ModHandler.removeRecipe(new ItemStack[] {GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1)});
    	if (null != GT_Utility.setStack(GT_ModHandler.getRecipeOutput(true, new ItemStack[] {GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1)}), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Bronze, GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "bronzeingotcrafting", true) ? 1 : 2))) {
			GT_Log.log.info("Changing Forestrys Bronze Recipe");
		}
    	
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "enchantmenttable", false)) {
			GT_Log.log.info("Removing the Recipe of the Enchantment Table, to have more Fun at enchanting with the Anvil and Books from Dungeons.");
			GT_ModHandler.removeRecipe(new ItemStack(Blocks.enchanting_table, 1));
		}
		
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "enderchest", false)) {
			GT_ModHandler.removeRecipe(new ItemStack(Blocks.ender_chest, 1));
		}
		
        GT_Log.log.info("Adding Mixed Metal Ingot Recipes.");
        GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1));
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Iron), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Iron), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Iron), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Iron), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Iron), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Iron), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Nickel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Nickel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Nickel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Nickel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Nickel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 1), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Nickel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Invar), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Invar), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Invar), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Invar), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Invar), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Invar), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});

        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Steel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Steel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Steel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Steel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 2), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Steel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Steel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.StainlessSteel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.StainlessSteel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.StainlessSteel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.StainlessSteel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.StainlessSteel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.StainlessSteel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Titanium), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Titanium), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Titanium), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Titanium), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Titanium), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Titanium), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Tungsten), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Tungsten), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Tungsten), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Tungsten), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 3), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Tungsten), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 4), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.Tungsten), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.TungstenSteel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.TungstenSteel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 6), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.TungstenSteel), 'Y', OrePrefixes.plate.get(Materials.Bronze), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.TungstenSteel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 5), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.TungstenSteel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Zinc)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("mixedMetalIngot", 6), new Object[] {"X", "Y", "Z", 'X', OrePrefixes.plate.get(Materials.TungstenSteel), 'Y', OrePrefixes.plate.get(Materials.Brass), 'Z', OrePrefixes.plate.get(Materials.Aluminium)});
        
        GT_Log.log.info("Adding Rolling Machine Recipes.");
        GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.standard"		,  4), new Object[] {"X X", "X X", "X X", 'X', OrePrefixes.ingot.get(Materials.Aluminium)});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.standard"		, 32), new Object[] {"X X", "X X", "X X", 'X', OrePrefixes.ingot.get(Materials.Titanium)});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.standard"		, 32), new Object[] {"X X", "X X", "X X", 'X', "ingotTungsten"});
	    
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rail.reinforced"	, 32), new Object[] {"X X", "X X", "X X", 'X', OrePrefixes.ingot.get(Materials.TungstenSteel)});
	    
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar"				,  2), new Object[] {"  X", " X ", "X  ", 'X', OrePrefixes.ingot.get(Materials.Aluminium)});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar"				, 16), new Object[] {"  X", " X ", "X  ", 'X', OrePrefixes.ingot.get(Materials.Titanium)});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar"				, 16), new Object[] {"  X", " X ", "X  ", 'X', "ingotTungsten"});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("part.rebar"				, 48), new Object[] {"  X", " X ", "X  ", 'X', OrePrefixes.ingot.get(Materials.TungstenSteel)});
	    
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.light.blue"	,  8), new Object[] {"XXX", " X ", "XXX", 'X', OrePrefixes.ingot.get(Materials.Aluminium)});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.purple"		, 64), new Object[] {"XXX", " X ", "XXX", 'X', OrePrefixes.ingot.get(Materials.Titanium)});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.black"		, 64), new Object[] {"XXX", " X ", "XXX", 'X', "ingotTungsten"});
	    
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.light.blue"	,  8), new Object[] {"X X", "XXX", "X X", 'X', OrePrefixes.ingot.get(Materials.Aluminium)});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.purple"		, 64), new Object[] {"X X", "XXX", "X X", 'X', OrePrefixes.ingot.get(Materials.Titanium)});
	    GT_ModHandler.addRollingMachineRecipe(GT_ModHandler.getRCItem("post.metal.black"		, 64), new Object[] {"X X", "XXX", "X X", 'X', "ingotTungsten"});
	    
	    GT_Log.log.info("Shape forming Recipes.");
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Empty				.get(1), false,  true, true, false, new Object[] {"HF", "PP", "PP"	 , 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile, 'P', OrePrefixes.plate.get(Materials.Steel)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Mold_Credit			.get(1), false, false, true, false, new Object[] {"H  ", " P ", "   ", 'H', GT_ToolDictNames.craftingToolHardHammer, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Mold_Plate			.get(1), false, false, true, false, new Object[] {" H ", " P ", "   ", 'H', GT_ToolDictNames.craftingToolHardHammer, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Mold_Casing			.get(1), false, false, true, false, new Object[] {"  H", " P ", "   ", 'H', GT_ToolDictNames.craftingToolHardHammer, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Mold_Gear			.get(1), false, false, true, false, new Object[] {"   ", " PH", "   ", 'H', GT_ToolDictNames.craftingToolHardHammer, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Bolt		.get(1), false, false, true, false, new Object[] {"C  ", " P ", "   ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Cell		.get(1), false, false, true, false, new Object[] {" C ", " P ", "   ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Ingot		.get(1), false, false, true, false, new Object[] {"  C", " P ", "   ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Ring		.get(1), false, false, true, false, new Object[] {"   ", " PC", "   ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Rod			.get(1), false, false, true, false, new Object[] {"   ", " P ", "  C", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Wire		.get(1), false, false, true, false, new Object[] {"   ", " P ", " C ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Casing		.get(1), false, false, true, false, new Object[] {"   ", " P ", "C  ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Plate		.get(1), false, false, true, false, new Object[] {"   ", "CP ", "   ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Block		.get(1), false, false, true, false, new Object[] {"P C", "   ", "   ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Pipe_Small	.get(1), false, false, true, false, new Object[] {"P  ", "  C", "   ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Pipe_Large	.get(1), false, false, true, false, new Object[] {"P  ", "   ", "  C", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Pipe_Medium	.get(1), false, false, true, false, new Object[] {"P  ", "   ", " C ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Sword		.get(1), false, false, true, false, new Object[] {"  P", "   ", "  C", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Pickaxe		.get(1), false, false, true, false, new Object[] {"  P", "   ", " C ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Shovel		.get(1), false, false, true, false, new Object[] {"  P", "   ", "C  ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Axe			.get(1), false, false, true, false, new Object[] {"  P", "C  ", "   ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Hoe			.get(1), false, false, true, false, new Object[] {"   ", "   ", "C P", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Hammer		.get(1), false, false, true, false, new Object[] {"   ", "C  ", "  P", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_File		.get(1), false, false, true, false, new Object[] {"C  ", "   ", "  P", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Saw			.get(1), false, false, true, false, new Object[] {" C ", "   ", "  P", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Shape_Extruder_Gear		.get(1), false, false, true, false, new Object[] {"C  ", "   ", "P  ", 'C', GT_ToolDictNames.craftingToolWireCutter, 'P', GT_Items.Shape_Empty.get(1)});
	    
	    GT_Log.log.info("Basic Circuit adjustment Recipes.");
	    GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 0), new Object[] {OrePrefixes.circuit.get(Materials.Basic), GT_ToolDictNames.craftingToolScrewdriver, GT_ToolDictNames.craftingToolSolderingIron, GT_ToolDictNames.craftingToolSolderingMetal});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 1)	, false, false, true, false, new Object[] {"C  ", " P ", "   ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 2)	, false, false, true, false, new Object[] {" C ", " P ", "   ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 3)	, false, false, true, false, new Object[] {"  C", " P ", "   ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 4)	, false, false, true, false, new Object[] {"   ", " PC", "   ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 5)	, false, false, true, false, new Object[] {"   ", " P ", "  C", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 6)	, false, false, true, false, new Object[] {"   ", " P ", " C ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 7)	, false, false, true, false, new Object[] {"   ", " P ", "C  ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 8)	, false, false, true, false, new Object[] {"   ", "CP ", "   ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 9)	, false, false, true, false, new Object[] {"P C", "   ", "   ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 10), false, false, true, false, new Object[] {"P  ", "  C", "   ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 11), false, false, true, false, new Object[] {"P  ", "   ", "  C", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 12), false, false, true, false, new Object[] {"P  ", "   ", " C ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 13), false, false, true, false, new Object[] {"  P", "   ", "  C", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 14), false, false, true, false, new Object[] {"  P", "   ", " C ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 15), false, false, true, false, new Object[] {"  P", "   ", "C  ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 16), false, false, true, false, new Object[] {"  P", "C  ", "   ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 17), false, false, true, false, new Object[] {"   ", "   ", "C P", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 18), false, false, true, false, new Object[] {"   ", "C  ", "  P", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 19), false, false, true, false, new Object[] {"C  ", "   ", "  P", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 20), false, false, true, false, new Object[] {" C ", "   ", "  P", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 21), false, false, true, false, new Object[] {"C  ", "   ", "P  ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 22), false, false, true, false, new Object[] {" C ", "   ", "P  ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 23), false, false, true, false, new Object[] {"  C", "   ", "P  ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Circuit_Integrated.getWithDamage(1, 24), false, false, true, false, new Object[] {"   ", "  C", "P  ", 'C', GT_ToolDictNames.craftingToolScrewdriver, 'P', GT_Items.Circuit_Integrated.getWildcard(1)});
	  
		GT_Log.log.info("Beginning to add regular Crafting Recipes.");
		GT_ModHandler.addCraftingRecipe(GT_Items.Fuel_Can_Plastic_Empty.get(7), false, true, false, new Object[] {" PP", "P P", "PPP", 'P', OrePrefixes.plate.get(Materials.Plastic)});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechMaterial(60, 2), new Object[] {"S ", " P", 'S', GT_ToolDictNames.craftingToolSaw, 'P', new ItemStack(Blocks.wooden_slab, 1, 0)});
	    GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechMaterial(61, 2), new Object[] {"S ", " P", 'S', GT_ToolDictNames.craftingToolSaw, 'P', new ItemStack(Blocks.wooden_slab, 1, 1)});
	    GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechMaterial(62, 2), new Object[] {"S ", " P", 'S', GT_ToolDictNames.craftingToolSaw, 'P', new ItemStack(Blocks.wooden_slab, 1, 2)});
	    GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechMaterial(63, 2), new Object[] {"S ", " P", 'S', GT_ToolDictNames.craftingToolSaw, 'P', new ItemStack(Blocks.wooden_slab, 1, 3)});
	    GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("scaffold", 4), new Object[] {"WWW", " S ", "S S", 'W', "plankWood", 'S', OrePrefixes.stick.get(Materials.Wood)});
	    if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "machineblock", true)) {
	    	GT_ModHandler.addCraftingRecipe(null, new Object[] {"RRR", "R R", "RRR", 'R', OrePrefixes.plate.get(Materials.Iron)});
	    }
    	
	    GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("machine", 1, GregTech_API.getGregTechComponent(37, 1)), new Object[] {"RRR", "RWR", "RRR", 'R', OrePrefixes.plate.get(Materials.Iron), 'W', GT_ToolDictNames.craftingToolWrench});

		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 32, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium)			, 'W', GT_ToolDictNames.craftingToolWrench});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 33, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Bronze)			, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 34, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Brass)				, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 35, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Steel)				, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 36, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Titanium)			, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 38, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.TungstenSteel)		, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 39, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel)	, 'W', GT_ToolDictNames.craftingToolWrench});
		
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 51, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plate.get(Materials.Bronze)			, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 52, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plate.get(Materials.Steel)				, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 53, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plate.get(Materials.Magnalium)			, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 54, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plate.get(Materials.TungstenSteel)		, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 55, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plateAlloy.get("Carbon")				, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		
		GT_ModHandler.addCraftingRecipe(GT_Items.Frame_Iron					.get(2), new Object[] {"SSS", "SWS", "SSS", 'W', GT_ToolDictNames.craftingToolWrench, 'S', OrePrefixes.stick.get(Materials.Iron)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Frame_Aluminium			.get(2), new Object[] {"SSS", "SWS", "SSS", 'W', GT_ToolDictNames.craftingToolWrench, 'S', OrePrefixes.stick.get(Materials.Aluminium)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Frame_Steel				.get(2), new Object[] {"SSS", "SWS", "SSS", 'W', GT_ToolDictNames.craftingToolWrench, 'S', OrePrefixes.stick.get(Materials.Steel)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Frame_StainlessSteel		.get(2), new Object[] {"SSS", "SWS", "SSS", 'W', GT_ToolDictNames.craftingToolWrench, 'S', OrePrefixes.stick.get(Materials.StainlessSteel)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Frame_TungstenSteel		.get(2), new Object[] {"SSS", "SWS", "SSS", 'W', GT_ToolDictNames.craftingToolWrench, 'S', OrePrefixes.stick.get(Materials.TungstenSteel)});
	    
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Bronze_Medium			.get(2), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Steel_Medium			.get(2), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Steel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_StainlessSteel_Medium	.get(2), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_TungstenSteel_Medium	.get(2), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Brass_Medium			.get(2), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Brass), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Electrum_Medium		.get(2), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Electrum), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Platinum_Medium		.get(2), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Platinum), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Bronze_Small			.get(6), false, true, new Object[] {"PWP", "P P", "PHP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Steel_Small			.get(6), false, true, new Object[] {"PWP", "P P", "PHP", 'P', OrePrefixes.plate.get(Materials.Steel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_StainlessSteel_Small	.get(6), false, true, new Object[] {"PWP", "P P", "PHP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_TungstenSteel_Small	.get(6), false, true, new Object[] {"PWP", "P P", "PHP", 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Bronze_Large			.get(1), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Steel_Large			.get(1), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Steel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_StainlessSteel_Large	.get(1), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_TungstenSteel_Large	.get(1), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Brass_Large			.get(1), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Brass), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Electrum_Large		.get(1), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Electrum), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Pipe_Platinum_Large		.get(1), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Platinum), 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		
	    GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casinggold"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Gold), 'H', GT_ToolDictNames.craftingToolHardHammer});
	    GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casingiron"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Iron), 'H', GT_ToolDictNames.craftingToolHardHammer});
	    GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casingbronze"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Bronze), 'H', GT_ToolDictNames.craftingToolHardHammer});
	    GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casingcopper"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Copper), 'H', GT_ToolDictNames.craftingToolHardHammer});
	    GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casingtin"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Tin), 'H', GT_ToolDictNames.craftingToolHardHammer});
	    GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casinglead"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Lead), 'H', GT_ToolDictNames.craftingToolHardHammer});
		
	    GT_ModHandler.addCraftingRecipe(GT_Items.Component_Turbine_Bronze		.getUndamaged(1), new Object[] {"TTT", "TBT", "TTT", 'T', GT_OreDictNames.craftingTurbineBladeBronze, 'B', OrePrefixes.gearGt.get(Materials.Bronze)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Component_Turbine_Steel		.getUndamaged(1), new Object[] {"TTT", "TBT", "TTT", 'T', GT_OreDictNames.craftingTurbineBladeSteel, 'B', OrePrefixes.gearGt.get(Materials.Steel)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Component_Turbine_Magnalium	.getUndamaged(1), new Object[] {"TTT", "TBT", "TTT", 'T', GT_OreDictNames.craftingTurbineBladeMagnalium, 'B', OrePrefixes.gearGt.get(Materials.Magnalium)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Component_Turbine_TungstenSteel.getUndamaged(1), new Object[] {"TTT", "TBT", "TTT", 'T', GT_OreDictNames.craftingTurbineBladeTungstenSteel, 'B', OrePrefixes.gearGt.get(Materials.TungstenSteel)});
	    GT_ModHandler.addCraftingRecipe(GT_Items.Component_Turbine_Carbon		.getUndamaged(1), new Object[] {"TTT", "TBT", "TTT", 'T', GT_OreDictNames.craftingTurbineBladeCarbon, 'B', OrePrefixes.gearGt.get(Materials.Iron)});
	    
	    GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.gearGt, Materials.Iron, 1), new Object[] {GT_Items.Component_Turbine_Carbon.getWildcard(1)});
	    
	    GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Component_Turbine_Bronze			.getUndamaged(1), new Object[] {GT_Items.Component_Turbine_Bronze		.getWildcard(1), GT_ToolDictNames.craftingToolHardHammer, GT_ToolDictNames.craftingToolWrench, GT_ToolDictNames.craftingToolFile});
	    GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Component_Turbine_Steel			.getUndamaged(1), new Object[] {GT_Items.Component_Turbine_Steel		.getWildcard(1), GT_ToolDictNames.craftingToolHardHammer, GT_ToolDictNames.craftingToolWrench, GT_ToolDictNames.craftingToolFile});
	    GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Component_Turbine_Magnalium		.getUndamaged(1), new Object[] {GT_Items.Component_Turbine_Magnalium	.getWildcard(1), GT_ToolDictNames.craftingToolHardHammer, GT_ToolDictNames.craftingToolWrench, GT_ToolDictNames.craftingToolFile});
	    GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Component_Turbine_TungstenSteel	.getUndamaged(1), new Object[] {GT_Items.Component_Turbine_TungstenSteel.getWildcard(1), GT_ToolDictNames.craftingToolHardHammer, GT_ToolDictNames.craftingToolWrench, GT_ToolDictNames.craftingToolFile});
		
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Bronze)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Aluminium)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Steel)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Titanium)});
    	
    	GT_ModHandler.addCraftingRecipe(GT_Items.Battery_Hull_LV		.get(1), new Object[] {"C", "P", "P", 'P', OrePrefixes.plate.get(Materials.BatteryAlloy), 'C', GT_OreDictNames.craftingWireTin});
        GT_ModHandler.addCraftingRecipe(GT_Items.Battery_Hull_MV		.get(1), new Object[] {"C C", "PPP", "PPP", 'P', OrePrefixes.plate.get(Materials.BatteryAlloy), 'C', GT_OreDictNames.craftingWireCopper});
        GT_ModHandler.addCraftingRecipe(GT_Items.Energy_LapotronicOrb	.get(1), new Object[] {"LLL", "LIL", "LLL", 'L', OrePrefixes.battery.get(Materials.Master), 'I', OrePrefixes.plateAlloy.get("Iridium")});
    	GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(0, 4), new Object[] {"AWA", "LIL", "AWA", 'L', OrePrefixes.battery.get(Materials.Master), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'A', OrePrefixes.circuit.get(Materials.Advanced), 'W', OrePrefixes.plate.get(Materials.Tungsten)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(1, 4), new Object[] {"AEA", "EIE", "AEA", 'E', OrePrefixes.circuit.get(Materials.Data), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'A', OrePrefixes.circuit.get(Materials.Advanced)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(2, 4), new Object[] {"CCC", "WIW", "LLL", 'L', OrePrefixes.circuit.get(Materials.Master), 'W', OrePrefixes.plate.get(Materials.Tungsten), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'C', GT_OreDictNames.crafting60kCoolantStore});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Tool_DataOrb.getUndamaged(1), new Object[] {GT_Items.Tool_DataOrb.getWildcard(1)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(4, 1), new Object[] {"AGA", "RPB", "ALA", 'A', OrePrefixes.plate.get(Materials.Aluminium), 'L', OrePrefixes.dust.get(Materials.Glowstone), 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue", 'P', new ItemStack(Blocks.glass_pane, 1)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(5, 1), new Object[] {"GGG", "AAA", "CBC", 'A', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'G', new ItemStack(Blocks.glass, 1)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(5, 1), new Object[] {"GGG", "RRR", "CBC", 'R', OrePrefixes.plate.get(Materials.Iron), 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'G', new ItemStack(Blocks.glass, 1)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(17, 1), new Object[]{" D ", "DGD", " D ", 'D', OrePrefixes.dustSmall.get(Materials.Diamond), 'G', OrePrefixes.gearGt.get(Materials.CobaltBrass)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(18, 2), new Object[] {"DSD", "SGS", "DSD", 'G', "craftingIndustrialDiamond", 'D', OrePrefixes.dust.get(Materials.Diamond), 'S', GT_OreDictNames.craftingPlateSteel});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(23, 2), new Object[] {"TST", "SBS", "TST", 'B', "blockSteel", 'T', OrePrefixes.plate.get(Materials.Tungsten), 'S', GT_OreDictNames.craftingPlateSteel});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(16, 4), new Object[] {"CCC", "RRR", "SSS", 'C', GT_ModHandler.getIC2Item("carbonMesh", 1), 'R', "itemRubber", 'S', GT_ModHandler.getIC2Item("resin", 1)});
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "SolarPanel"	,  true)) GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent( 7, 1), new Object[] {"SGS", "CPC"		 , 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', new ItemStack(Blocks.glass_pane, 1), 'P', OrePrefixes.plateAlloy.get("Carbon"), 'S', OrePrefixes.plate.get(Materials.Silicon)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "SolarPanelLV", false)) GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(65, 1), new Object[] {"SSS", "STS", "SSS", 'S', GT_OreDictNames.craftingSolarPanel  , 'T', OrePrefixes.circuit.get(Materials.Basic)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "SolarPanelMV", false)) GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(66, 1), new Object[] {"SSS", "STS", "SSS", 'S', GT_OreDictNames.craftingSolarPanelLV, 'T', GT_OreDictNames.craftingMVTUpgrade});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "SolarPanelHV", false)) GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(67, 1), new Object[] {"SSS", "STS", "SSS", 'S', GT_OreDictNames.craftingSolarPanelMV, 'T', GT_OreDictNames.craftingHVTUpgrade});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorVent", 1, 1), "BAB", "AMA", "BAB", 'B', new ItemStack(Blocks.iron_bars, 1), 'A', OrePrefixes.plate.get(Materials.Aluminium), 'M', GT_ModHandler.getIC2Item("elemotor", 1));
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("reactorPlatingExplosive", 1), new Object[] {GT_ModHandler.getIC2Item("reactorPlating", 1), OrePrefixes.plate.get(Materials.Lead)});
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "wirelessRedstone", true)) {
        	GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(82, 1), new Object[] {"RAR", "QEQ", "CPC", 'A', OrePrefixes.circuit.get(Materials.Advanced), 'R', OrePrefixes.dust.get(Materials.Redstone), 'Q', GT_OreDictNames.craftingQuartz, 'P', OrePrefixes.plate.get(Materials.Gold)	, 'E', new ItemStack(Items.ender_pearl, 1), 'C', new ItemStack(Items.comparator, 1)});
        	GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(84, 1), new Object[] {"RAR", "QEQ", "CPC", 'A', OrePrefixes.circuit.get(Materials.Advanced), 'R', GT_OreDictNames.craftingRedstoneTorch	, 'Q', GT_OreDictNames.craftingQuartz, 'P', OrePrefixes.plate.get(Materials.Silver)	, 'E', new ItemStack(Items.ender_pearl, 1), 'C', new ItemStack(Items.comparator, 1)});
        }
        
        GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechComponent(82, 1), new Object[] {GregTech_API.getGregTechComponent(83, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechComponent(83, 1), new Object[] {GregTech_API.getGregTechComponent(82, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechComponent(84, 1), new Object[] {GregTech_API.getGregTechComponent(85, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechComponent(85, 1), new Object[] {GregTech_API.getGregTechComponent(84, 1)});
        
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 1,10), new Object[] {"CTC", "TMT", "CTC", 'C', OrePrefixes.plate.get(Materials.Chrome)  	, 'T', OrePrefixes.plate.get(Materials.Titanium)			, 'M', GT_OreDictNames.craftingRawMachineTier02});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 4,13), new Object[] {"AAA", "CMC", "AAA", 'C', OrePrefixes.circuit.get(Materials.Basic)	, 'A', OrePrefixes.plate.get(Materials.Iron)				, 'M', GT_OreDictNames.craftingRawMachineTier01});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 4,13), new Object[] {"AAA", "CMC", "AAA", 'C', OrePrefixes.circuit.get(Materials.Basic)	, 'A', OrePrefixes.plate.get(Materials.Aluminium)			, 'M', GT_OreDictNames.craftingRawMachineTier01});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 4,14), new Object[] {"SSS", "CMC", "SSS", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'S', GT_OreDictNames.craftingPlateSteel					, 'M', GT_OreDictNames.craftingRawMachineTier02});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 4,15), new Object[] {"TTT", "CMC", "TTT", 'C', OrePrefixes.circuit.get(Materials.Elite)	, 'T', OrePrefixes.plate.get(Materials.Chrome)				, 'M', GT_OreDictNames.craftingRawMachineTier04});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[4], 2,13), new Object[] {"PPP", "BHB", "PPP", 'P', OrePrefixes.plate.get(Materials.Bronze)	, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'H', GT_ToolDictNames.craftingToolHardHammer});
    	
    	boolean tNeedsSteel = GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "steelForIC2Machines", true);
    	
    	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("generator", 1));
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {"B"  , "M"  , "F"  , 'B', OrePrefixes.battery.get(Materials.Basic), 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier00, 'F', new ItemStack(Blocks.furnace, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    	if (!tNeedsSteel) {
	    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {" B ", "RRR", " F ", 'B', OrePrefixes.battery.get(Materials.Basic), 'R', OrePrefixes.plate.get(Materials.Iron)			, 'F', GT_OreDictNames.craftingIronFurnace});
	    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {" B ", "RRR", " F ", 'B', OrePrefixes.battery.get(Materials.Basic), 'R', OrePrefixes.plate.get(Materials.Aluminium)  	, 'F', GT_OreDictNames.craftingIronFurnace});
	    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {" B ", "RRR", " F ", 'B', OrePrefixes.battery.get(Materials.Basic), 'R', OrePrefixes.plate.get(Materials.Invar)      	, 'F', GT_OreDictNames.craftingIronFurnace});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "tesseracts", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 84), new Object[] {"TCT", "CEC", "TMT", 'C', OrePrefixes.circuit.get(Materials.Master)	, 'M', OrePrefixes.computer.get(Materials.Master)	, 'E', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "enderchest", false)?new ItemStack(Items.ender_eye, 1):GT_OreDictNames.craftingEnderChest, 'T', OrePrefixes.plate.get(Materials.Titanium)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 85), new Object[] {"TCT", "CEC", "TMT", 'C', OrePrefixes.circuit.get(Materials.Elite)	, 'M', GT_OreDictNames.craftingRawMachineTier02		, 'E', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "enderchest", false)?new ItemStack(Items.ender_eye, 1):GT_OreDictNames.craftingEnderChest, 'T', OrePrefixes.plate.get(Materials.Titanium)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "fusionreactor", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 80), new Object[] {"CCC", "PHP", "CCC", 'C', OrePrefixes.circuit.get(Materials.Master), 'P', OrePrefixes.computer.get(Materials.Master), 'H', new ItemStack(GregTech_API.sBlockList[0], 1,  1)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 81), new Object[] {"SCS", "CHC", "SCS", 'C', OrePrefixes.circuit.get(Materials.Master), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,103), 'S', GT_OreDictNames.craftingSuperconductor});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 82), new Object[] {"PWP", "CMC", "PCP", 'C', OrePrefixes.circuit.get(Materials.Master), 'M', GT_OreDictNames.craftingRawMachineTier04, 'W', new ItemStack(Blocks.chest, 1), 'P', GT_OreDictNames.craftingPump});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 83), new Object[] {"PCP", "CMC", "PWP", 'C', OrePrefixes.circuit.get(Materials.Master), 'M', GT_OreDictNames.craftingRawMachineTier04, 'W', new ItemStack(Blocks.chest, 1), 'P', GT_OreDictNames.craftingPump});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 1,  1), new Object[] {"ESE", "CMC", "EIE", 'I', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "iridiumreflector", true)?GT_Items.Reactor_NeutronReflector.getUndamaged(1):OrePrefixes.plateAlloy.get("Iridium"), 'S', GT_OreDictNames.craftingSuperconductor, 'E', OrePrefixes.circuit.get(Materials.Master), 'C', GT_OreDictNames.craftingHeatingCoilTier02, 'M', GT_OreDictNames.craftingRawMachineTier04});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "lightningrod", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,102), new Object[] {"IAI", "AMA", "IAI", 'I', OrePrefixes.circuit.get(Materials.Master), 'A', GT_OreDictNames.craftingRawMachineTier04, 'M', new ItemStack(GregTech_API.sBlockList[1], 1,103)});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 4), new Object[] {"CGD", "GAG", "DGC", 'D', OrePrefixes.circuit.get(Materials.Master), 'G', GT_OreDictNames.craftingMonitorTier02, 'C', OrePrefixes.circuit.get(Materials.Ultimate), 'A', GT_OreDictNames.craftingRawMachineTier02});

    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "idsu", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,101), new Object[] {"IMI", "MCM", "IMI", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'C', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "enderchest", false)?new ItemStack(Items.ender_eye, 1):GT_OreDictNames.craftingEnderChest, 'M', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "aesu", true)?new ItemStack(GregTech_API.sBlockList[1], 1, 100):OrePrefixes.battery.get(Materials.Ultimate)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "aesu", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,100), new Object[] {"CQC", "CEC", "CMC", 'Q', OrePrefixes.computer.get(Materials.Master), 'C', OrePrefixes.circuit.get(Materials.Master), 'E', OrePrefixes.battery.get(Materials.Ultimate), 'M', GT_OreDictNames.craftingRawMachineTier04});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "digitalchest", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,48), new Object[] {"AAA", "ADA", "ASA", 'D', OrePrefixes.circuit.get(Materials.Ultimate), 'S', GT_OreDictNames.craftingMonitorTier02, 'A', OrePrefixes.plate.get(Materials.Iron)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,48), new Object[] {"AAA", "ADA", "ASA", 'D', OrePrefixes.circuit.get(Materials.Ultimate), 'S', GT_OreDictNames.craftingMonitorTier02, 'A', OrePrefixes.plate.get(Materials.Aluminium)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,75), new Object[] {"AAA", "ADA", "ASA", 'D', OrePrefixes.circuit.get(Materials.Ultimate), 'S', GT_OreDictNames.craftingMonitorTier02, 'A', GT_OreDictNames.craftingPlateSteel});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "quantumchest", true)) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,49), new Object[] {"ASA", "BTB", "ADA", 'A', OrePrefixes.circuit.get(Materials.Ultimate), 'S', GT_OreDictNames.craftingMonitorTier02, 'D', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "digitalchest", true)?new ItemStack(GregTech_API.sBlockList[1], 1, 48):OrePrefixes.circuit.get(Materials.Master), 'T', GT_OreDictNames.craftingTeleporter, 'B', GT_OreDictNames.craftingRawMachineTier04});
    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,49), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1, 3)});
            GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechComponent(29, 1), new Object[] {"ASA", "BTB", "ADA", 'A', OrePrefixes.circuit.get(Materials.Ultimate), 'S', GT_OreDictNames.craftingMonitorTier02, 'D', OrePrefixes.plate.get(Materials.Aluminium), 'T', GT_OreDictNames.craftingTeleporter, 'B', GT_OreDictNames.craftingTeleporter});
    		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "quantumtank", true)) GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,30), new Object[] {"APA", "PQP", "APA", 'A', OrePrefixes.circuit.get(Materials.Master), 'P', OrePrefixes.plate.get(Materials.Platinum), 'L', OrePrefixes.circuit.get(Materials.Master), 'Q', new ItemStack(GregTech_API.sBlockList[1], 1, 49)});
    	} else {
    		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "quantumtank", true)) GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,30), new Object[] {"ASA", "BTB", "ALA", 'A', OrePrefixes.circuit.get(Materials.Ultimate), 'S', GT_OreDictNames.craftingMonitorTier02, 'L', OrePrefixes.circuit.get(Materials.Master), 'T', GT_OreDictNames.craftingTeleporter, 'B', GT_OreDictNames.craftingRawMachineTier04});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 86), new Object[] {"WBW", "WCW", "PMP", 'C', OrePrefixes.circuit.get(Materials.Basic), 'M', GT_OreDictNames.craftingRawMachineTier01, 'W', GT_OreDictNames.craftingWireCopper, 'B', OrePrefixes.battery.get(Materials.Basic), 'P', OrePrefixes.plate.get(Materials.Iron)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 86), new Object[] {"WBW", "WCW", "PMP", 'C', OrePrefixes.circuit.get(Materials.Basic), 'M', GT_OreDictNames.craftingRawMachineTier01, 'W', GT_OreDictNames.craftingWireCopper, 'B', OrePrefixes.battery.get(Materials.Basic), 'P', OrePrefixes.plate.get(Materials.Aluminium)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 87), new Object[] {"TCT", "CMC"       , 'C', GT_OreDictNames.craftingChest, 'M', new ItemStack(GregTech_API.sBlockList[1], 1, 86), 'T', OrePrefixes.circuit.get(Materials.Basic)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 88), new Object[] {"CCC", "CMC", "TTT", 'C', GT_OreDictNames.craftingChest, 'M', new ItemStack(GregTech_API.sBlockList[1], 1, 87), 'T', OrePrefixes.circuit.get(Materials.Basic)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 89), new Object[] {"CCC", "CMC", "CTC", 'C', GT_OreDictNames.craftingChest, 'M', new ItemStack(GregTech_API.sBlockList[1], 1, 88), 'T', OrePrefixes.circuit.get(Materials.Advanced)});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 62), new Object[] {"RCR", "AEA", "RCR", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'A', GT_OreDictNames.craftingRawMachineTier02, 'E', GT_OreDictNames.craftingExtractor, 'R', OrePrefixes.plate.get(Materials.StainlessSteel)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 25), new Object[] {"RDR", "CEC", "RMR", 'E', GT_ModHandler.getIC2Item("electrolyzer", 1), 'M', GT_OreDictNames.craftingElectromagnet, 'D', GT_OreDictNames.craftingExtractor, 'R', GT_OreDictNames.craftingPlateSteel, 'C', OrePrefixes.circuit.get(Materials.Advanced)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 38), new Object[] {"RPR", "CGC", "RPR", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'P', GT_OreDictNames.craftingPump, 'G', "glassReinforced", 'R', OrePrefixes.plate.get(Materials.StainlessSteel)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 41), new Object[] {"RMR", "CEC", "RDR", 'M', GT_OreDictNames.craftingElectromagnet, 'D', GT_OreDictNames.craftingExtractor, 'E', GT_OreDictNames.craftingCompressor, 'R', OrePrefixes.plate.get(Materials.Invar), 'C', OrePrefixes.circuit.get(Materials.Advanced)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 41), new Object[] {"RMR", "CEC", "RDR", 'M', GT_OreDictNames.craftingElectromagnet, 'D', GT_OreDictNames.craftingExtractor, 'E', GT_OreDictNames.craftingCompressor, 'R', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Advanced)});
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "superconductorwire", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 4,12), new Object[] {"ALA", "CCC", "ALA", 'C', GT_OreDictNames.craftingSuperconductor, 'A', GT_OreDictNames.craftingRawMachineTier02, 'L', OrePrefixes.circuit.get(Materials.Master)});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 103), new Object[] {"LEL", "CAC", "LEL", 'C', GT_OreDictNames.craftingSuperconductor, 'A', GT_OreDictNames.craftingRawMachineTier04, 'L', OrePrefixes.circuit.get(Materials.Master), 'E', OrePrefixes.battery.get(Materials.Ultimate)});
        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 104), new Object[] {"LCL", "CSC", "LCL", 'C', GT_OreDictNames.craftingSuperconductor, 'S', new ItemStack(GregTech_API.sBlockList[1], 1, 103), 'L', OrePrefixes.circuit.get(Materials.Master)});
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "playerdetector", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 77), new Object[] {" E ", "ACA", " E ", 'C', OrePrefixes.computer.get(Materials.Master), 'A', OrePrefixes.circuit.get(Materials.Advanced), 'E', OrePrefixes.circuit.get(Materials.Data)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "massfabricator", true)) {
    		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("massFabricator", 1));
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("massFabricator", 1), new Object[] {"ETE", "ALA", "ETE", 'L', OrePrefixes.battery.get(Materials.Ultimate), 'A', GT_OreDictNames.craftingRawMachineTier04, 'E', OrePrefixes.circuit.get(Materials.Master), 'T', GT_OreDictNames.craftingTeleporter});
    	}
    	
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "scanner", true)) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 114), new Object[] {"PDP", "CMC", "PCP", 'M', GT_ModHandler.getIC2Item("scanner", 1), 'C', OrePrefixes.circuit.get(Materials.Elite), 'D', GT_OreDictNames.craftingMonitorTier02, 'P', OrePrefixes.plate.get(Materials.Titanium)});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "sonictron", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,  6), new Object[] {"CRC", "NAN", "CJC", 'C', OrePrefixes.circuit.get(Materials.Basic), 'N', new ItemStack(Blocks.noteblock, 1), 'A', GT_OreDictNames.craftingRawMachineTier02, 'J', new ItemStack(Blocks.jukebox, 1), 'R', "itemRecord"});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "electricautocraftingtable", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 16), new Object[] {"GBG", "CTC", "GAG", 'B', OrePrefixes.battery.get(Materials.Basic), 'A', GT_OreDictNames.craftingRawMachineTier02	, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'T', GT_OreDictNames.craftingWorkBench, 'G', OrePrefixes.plate.get(Materials.Electrum)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "advancedpump", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 47), new Object[] {"CPC", "PMP", "CPC", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'M', GT_OreDictNames.craftingRawMachineTier02	, 'P', GT_OreDictNames.craftingPump});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "wiremill", true)) {
    		tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, new ItemStack(Items.diamond, 1), null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.iron_ingot, 1), null});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 55), new Object[] {"BDB", "CMC", "BQB", 'M', GT_OreDictNames.craftingRawMachineTier02, 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.plate.get(Materials.Brass), 'D', tStack==null?OrePrefixes.gem.get(Materials.Diamond):tStack, 'Q', GT_OreDictNames.craftingConveyor});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "alloysmelter", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 56), new Object[] {"IHI", "CEC", "IQI", 'H', GT_OreDictNames.craftingHeatingCoilTier00, 'C', OrePrefixes.circuit.get(Materials.Basic), 'E', GT_OreDictNames.craftingElectricFurnace, 'I', OrePrefixes.plate.get(Materials.Invar), 'Q', GT_OreDictNames.craftingConveyor});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "lathe", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,111), new Object[] {"PCP", "GQG", "PMP", 'M', GT_OreDictNames.craftingRawMachineTier02, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'G', OrePrefixes.gearGt.get(Materials.Steel), 'P', GT_OreDictNames.craftingPlateSteel, 'Q', GT_OreDictNames.craftingConveyor});
    	}
    	
        if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "extruder", true)) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 115), new Object[] {"PGP", "HMS", "PCP", 'M', GT_OreDictNames.craftingRawMachineTier03, 'C', OrePrefixes.circuit.get(Materials.Elite), 'G', OrePrefixes.gearGt.get(Materials.Titanium), 'S', GT_OreDictNames.craftingDiamondBlade, 'H', GT_OreDictNames.craftingHeatingCoilTier02, 'P', OrePrefixes.plate.get(Materials.Titanium)});
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 115), new Object[] {"PGP", "HMS", "PCP", 'M', GT_OreDictNames.craftingRawMachineTier03, 'C', OrePrefixes.circuit.get(Materials.Elite), 'G', OrePrefixes.gearGt.get(Materials.TungstenSteel), 'S', GT_OreDictNames.craftingDiamondBlade, 'H', GT_OreDictNames.craftingHeatingCoilTier02, 'P', OrePrefixes.plate.get(Materials.TungstenSteel)});
        }
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,113), new Object[] {"PCP", "GSG", "PMP", 'M', GT_OreDictNames.craftingRawMachineTier02, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'G', OrePrefixes.gearGt.get(Materials.Steel), 'P', GT_OreDictNames.craftingPlateSteel, 'S', GT_OreDictNames.craftingDiamondBlade});
    	
		GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("canner", 1));
		GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("macerator", 1));
		GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("extractor", 1));
		GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("compressor", 1));
		GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("recycler", 1));
		GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("electroFurnace", 1));
		
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("canner", 1), new Object[] {"PCP", "PMP", "PPP", 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'C', OrePrefixes.circuit.get(Materials.Basic), 'P', OrePrefixes.plate.get(Materials.Tin)});
		
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "automaticmachines", true)) {
        	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "allowVanillaIC2MachinesFromAutomaticOnes", false)) {
	    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {GT_ModHandler.getIC2Item("macerator", 1)});
	    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,51), new Object[] {GT_ModHandler.getIC2Item("extractor", 1)});
	    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,52), new Object[] {GT_ModHandler.getIC2Item("compressor", 1)});
	    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,53), new Object[] {GT_ModHandler.getIC2Item("recycler", 1)});
	    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,54), new Object[] {GT_ModHandler.getIC2Item("electroFurnace", 1)});
	    		
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,50)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("extractor", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,51)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("compressor", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,52)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("recycler", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,53)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("electroFurnace", 1)	, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,54)});
        	}
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,51), new Object[] {"TMT", "TCT",        'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'C', OrePrefixes.circuit.get(Materials.Basic), 'T', GT_ModHandler.getIC2Item("treetap", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,52), new Object[] {"S S", "SMS", "SCS", 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'C', OrePrefixes.circuit.get(Materials.Basic), 'S', "stoneSmooth"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,53), new Object[] {" G ", "DMD", "RDR", 'M', GT_OreDictNames.craftingCompressor, 'G', OrePrefixes.dust.get(Materials.Glowstone), 'R', tNeedsSteel?GT_OreDictNames.craftingPlateSteel:OrePrefixes.plate.get(Materials.Iron), 'D', new ItemStack(Blocks.dirt, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,54), new Object[] {" C ", "RMR"       , 'M', tNeedsSteel?new ItemStack(GregTech_API.sBlockList[1], 1,138):GT_OreDictNames.craftingIronFurnace, 'C', OrePrefixes.circuit.get(Materials.Basic), 'R', OrePrefixes.dust.get(Materials.Redstone)});
    		
            if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "macerator", true)) {
            	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {"FDF", "DMD", "FAF", 'F', tNeedsSteel?GT_OreDictNames.craftingPlateSteel:new ItemStack(Items.flint, 1), 'A', OrePrefixes.circuit.get(Materials.Advanced), 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'D', OrePrefixes.gem.get(Materials.Diamond)});
            	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {"FDF", "DMD", "FAF", 'F', tNeedsSteel?GT_OreDictNames.craftingPlateSteel:new ItemStack(Items.flint, 1), 'A', OrePrefixes.circuit.get(Materials.Advanced), 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'D', OrePrefixes.dust.get(Materials.Diamond)});
            	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {"FDF", "AMA", "FAF", 'F', tNeedsSteel?GT_OreDictNames.craftingPlateSteel:new ItemStack(Items.flint, 1), 'A', OrePrefixes.circuit.get(Materials.Basic), 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'D', GT_OreDictNames.craftingGrinder});
            } else {
            	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {"FFF", "SMS", " C ", 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'C', OrePrefixes.circuit.get(Materials.Basic), 'F', new ItemStack(Items.flint, 1), 'S', "stoneCobble"});
            }
    	} else {
        	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "allowVanillaIC2MachinesFromAutomaticOnes", false)) {
        		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,50)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("extractor", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,51)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("compressor", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,52)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("recycler", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,53)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("electroFurnace", 1)	, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,54)});
        	}
        	
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("extractor"		, 1), new Object[] {"TMT", "TCT",        'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'C', OrePrefixes.circuit.get(Materials.Basic), 'T', GT_ModHandler.getIC2Item("treetap", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("compressor"		, 1), new Object[] {"S S", "SMS", "SCS", 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'C', OrePrefixes.circuit.get(Materials.Basic), 'S', "stoneSmooth"});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("recycler"			, 1), new Object[] {" G ", "DMD", "RDR", 'M', GT_OreDictNames.craftingCompressor, 'G', OrePrefixes.dust.get(Materials.Glowstone), 'R', tNeedsSteel?GT_OreDictNames.craftingPlateSteel:OrePrefixes.plate.get(Materials.Iron), 'D', new ItemStack(Blocks.dirt, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electroFurnace"	, 1), new Object[] {" C ", "RMR"       , 'M', tNeedsSteel?new ItemStack(GregTech_API.sBlockList[1], 1,138):GT_OreDictNames.craftingIronFurnace, 'C', OrePrefixes.circuit.get(Materials.Basic), 'R', OrePrefixes.dust.get(Materials.Redstone)});
    		
            if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "macerator", true)) {
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FDF", "DMD", "FAF", 'F', tNeedsSteel?GT_OreDictNames.craftingPlateSteel:new ItemStack(Items.flint, 1), 'A', OrePrefixes.circuit.get(Materials.Advanced), 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'D', OrePrefixes.gem.get(Materials.Diamond)});
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FDF", "DMD", "FAF", 'F', tNeedsSteel?GT_OreDictNames.craftingPlateSteel:new ItemStack(Items.flint, 1), 'A', OrePrefixes.circuit.get(Materials.Advanced), 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'D', OrePrefixes.dust.get(Materials.Diamond)});
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FDF", "AMA", "FAF", 'F', tNeedsSteel?GT_OreDictNames.craftingPlateSteel:new ItemStack(Items.flint, 1), 'A', OrePrefixes.circuit.get(Materials.Basic), 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'D', GT_OreDictNames.craftingGrinder});
            } else {
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FFF", "SMS", " C ", 'M', tNeedsSteel?GT_OreDictNames.craftingRawMachineTier02:GT_OreDictNames.craftingRawMachineTier01, 'C', OrePrefixes.circuit.get(Materials.Basic), 'F', new ItemStack(Items.flint, 1), 'S', "stoneCobble"});
            }
    	}
    	
		if (GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("inductionFurnace", 1))) GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("inductionFurnace", 1), new Object[] {"CCC", "CFC", "CMC", 'M', GT_OreDictNames.craftingRawMachineTier02, 'F', GT_OreDictNames.craftingElectricFurnace, 'C', OrePrefixes.ingot.get(Materials.Copper)});
		
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,57), new Object[] {GT_OreDictNames.craftingConveyor, GT_ModHandler.getIC2Item("canner", 1)});
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "microwaveoven", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,63), new Object[] {"AAA", "L M", "AAA", 'A', OrePrefixes.plate.get(Materials.Aluminium), 'L', OrePrefixes.plate.get(Materials.Lead), 'M', GT_OreDictNames.craftingElectromagnet});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "universalmacerator", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,64), new Object[] {"SGS", "SPS", "SBS", 'S', OrePrefixes.plate.get(Materials.Titanium), 'B', GT_OreDictNames.craftingRawMachineTier03, 'P', GT_OreDictNames.craftingMacerator, 'G', GT_OreDictNames.craftingGrinder});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "teleporter", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,90), new Object[] {"ETE", "TAT", "ELE", 'L', OrePrefixes.battery.get(Materials.Ultimate), 'A', GT_OreDictNames.craftingRawMachineTier04, 'E', OrePrefixes.circuit.get(Materials.Master), 'T', GT_OreDictNames.craftingTeleporter});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,59), new Object[] {"PCP", "RQR", "PCP", 'C', OrePrefixes.circuit.get(Materials.Basic), 'P', GT_OreDictNames.craftingPiston, 'R', GT_OreDictNames.craftingCompressor, 'Q', GT_OreDictNames.craftingConveyor});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,60), new Object[] {"CPC", "RQR", "CRC", 'C', OrePrefixes.circuit.get(Materials.Basic), 'P', GT_OreDictNames.craftingPiston, 'R', GT_OreDictNames.craftingPlateSteel, 'Q', GT_OreDictNames.craftingConveyor});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,61), new Object[] {"RPR", "CQC", "RRR", 'C', OrePrefixes.circuit.get(Materials.Basic), 'P', GT_OreDictNames.craftingPiston, 'R', GT_OreDictNames.craftingPlateSteel, 'Q', GT_OreDictNames.craftingConveyor});
        
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "automation", true)) {
    		// Translocators
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,17), new Object[] {"GBG", "CPC", "GAG", 'B', OrePrefixes.battery.get(Materials.Basic), 'A', GT_OreDictNames.craftingRawMachineTier00	, 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', OrePrefixes.plate.get(Materials.Electrum), 'P', GT_OreDictNames.craftingConveyor});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,20), new Object[] {"GBG", "CPC", "GAG", 'B', OrePrefixes.battery.get(Materials.Basic), 'A', GT_OreDictNames.craftingRawMachineTier02	, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'G', OrePrefixes.plate.get(Materials.Electrum), 'P', GT_OreDictNames.craftingConveyor});
    		
    		// Electric Sorters
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,23), new Object[] {"GBG", "CHC", "GAG", 'B', OrePrefixes.battery.get(Materials.Basic), 'A', GT_OreDictNames.craftingMonitorTier02		, 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', OrePrefixes.plate.get(Materials.Electrum), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,17)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,58), new Object[] {"GBG", "CHC", "GAG", 'B', OrePrefixes.battery.get(Materials.Basic), 'A', GT_OreDictNames.craftingMonitorTier02		, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'G', OrePrefixes.plate.get(Materials.Electrum), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,17)});
    		
    		// Adv. Buffer
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,21), new Object[] {"GBG", "CHC", "GAG", 'B', OrePrefixes.battery.get(Materials.Basic), 'A', GT_OreDictNames.craftingMonitorTier02		, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'G', OrePrefixes.plate.get(Materials.Electrum), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,18)});
    		
    		//Adv.Retriever
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,116), new Object[] {"SSS", "CHC", "SSS", 'S', OrePrefixes.plate.get(Materials.StainlessSteel), 'C', OrePrefixes.circuit.get(Materials.Elite), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,39)});
    		
    		// Electric Filter
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,127), new Object[] {"PPP", "MCN", "PPP", 'P', OrePrefixes.plate.get(Materials.Iron), 'C', OrePrefixes.circuit.get(Materials.Basic), 'M', new ItemStack(GregTech_API.sBlockList[1], 1,23), 'N', new ItemStack(GregTech_API.sBlockList[1], 1,19)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,127), new Object[] {"PPP", "MCN", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Basic), 'M', new ItemStack(GregTech_API.sBlockList[1], 1,23), 'N', new ItemStack(GregTech_API.sBlockList[1], 1,19)});
    		
    		// Electric Regulator
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,39), new Object[] {"CHC", "HAH", "CHC", 'A', GT_OreDictNames.craftingMonitorTier02, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,21)});
    		
    		// Electric Type Filter
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,126), new Object[] {"PPP", "MCN", "PPP", 'P', OrePrefixes.plate.get(Materials.Iron), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'M', new ItemStack(GregTech_API.sBlockList[1], 1,23), 'N', new ItemStack(GregTech_API.sBlockList[1], 1,19)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,126), new Object[] {"PPP", "MCN", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'M', new ItemStack(GregTech_API.sBlockList[1], 1,23), 'N', new ItemStack(GregTech_API.sBlockList[1], 1,19)});
    		
    		// large Electric Buffer
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,19), new Object[] {"GBG", "CBC", "GBG", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'G', OrePrefixes.plate.get(Materials.Electrum), 'B', GT_OreDictNames.craftingConveyor});
    		
    		// Inventory Manager
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,46), new Object[] {"CRC", "SMS", "CEC", 'M', OrePrefixes.computer.get(Materials.Master), 'C', OrePrefixes.circuit.get(Materials.Data), 'R', new ItemStack(GregTech_API.sBlockList[1], 1,39), 'S', new ItemStack(GregTech_API.sBlockList[1], 1,23), 'E', OrePrefixes.battery.get(Materials.Lithium)});
    		
    		// Large and Small Buffer crafting Recipes
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,19), new Object[] {" T ", "TCT", " T ", 'T', new ItemStack(GregTech_API.sBlockList[1], 1, 18)		, 'C', OrePrefixes.circuit.get(Materials.Advanced)});
    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 4,18), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1, 19)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "itemclearer", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,24), new Object[] {"GBG", "PMH", "GCG", 'B', OrePrefixes.battery.get(Materials.Basic), 'M', GT_OreDictNames.craftingRawMachineTier02	, 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', OrePrefixes.plate.get(Materials.Electrum), 'P', GT_OreDictNames.craftingTeleporter, 'H', new ItemStack(GregTech_API.sBlockList[1], 1,18)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "rockbreaker", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', OrePrefixes.gem.get(Materials.Diamond), 'A', GT_OreDictNames.craftingRawMachineTier02	, 'C', OrePrefixes.circuit.get(Materials.Basic), 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'O', OrePrefixes.plate.get(Materials.Invar)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', OrePrefixes.dust.get(Materials.Diamond), 'A', GT_OreDictNames.craftingRawMachineTier02	, 'C', OrePrefixes.circuit.get(Materials.Basic), 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'O', OrePrefixes.plate.get(Materials.Aluminium)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', OrePrefixes.gem.get(Materials.Diamond), 'A', GT_OreDictNames.craftingRawMachineTier02	, 'C', OrePrefixes.circuit.get(Materials.Basic), 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'O', OrePrefixes.plate.get(Materials.Invar)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', OrePrefixes.dust.get(Materials.Diamond), 'A', GT_OreDictNames.craftingRawMachineTier02	, 'C', OrePrefixes.circuit.get(Materials.Basic), 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'O', OrePrefixes.plate.get(Materials.Aluminium)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "cropharvestor", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,26), new Object[] {"GHG", "DPM", "GCG", 'D', GT_OreDictNames.craftingDiamondBlade, 'M', GT_OreDictNames.craftingRawMachineTier02, 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', OrePrefixes.plate.get(Materials.Electrum), 'P', GT_OreDictNames.craftingPiston, 'H', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "itemclearer", true)?new ItemStack(GregTech_API.sBlockList[1], 1, 24):new ItemStack(GregTech_API.sBlockList[1], 1, 18)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "scrapboxinator", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,27), new Object[] {"GHG", "DPM", "GCG", 'P', new ItemStack(Blocks.wooden_pressure_plate, 1), 'M', GT_OreDictNames.craftingRawMachineTier02	, 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', OrePrefixes.plate.get(Materials.Electrum), 'D', new ItemStack(Blocks.dispenser, 1), 'H', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "itemclearer", true)?new ItemStack(GregTech_API.sBlockList[1], 1, 24):new ItemStack(GregTech_API.sBlockList[1], 1, 18)});
    	}
    	
        if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "personalsafe", true)) {
            GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("personalSafe", 1));
        }
    	
        if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "tradeomat", true)) {
            GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("tradeOMat", 1));
        }
        
        if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "advancedsafe", true)) {
        	if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "personalsafe", true)) {
            	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 45), new Object[]{GT_ModHandler.getIC2Item("personalSafe", 1)});
        	}

        	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 45), new Object[]{"PCP", "PSP", "PPP", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'S', GT_OreDictNames.craftingChest, 'P', GT_OreDictNames.craftingPlateSteel});
        }
    	
        if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "electrictradeomat", true)) {
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "tradeomat", true)) {
            	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 76), new Object[]{GT_ModHandler.getIC2Item("tradeOMat", 1)});
            }

        	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 76), new Object[]{"PCP", "SMS", "PCP", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'S', GT_OreDictNames.craftingChest, 'P', GT_OreDictNames.craftingPlateSteel, Character.valueOf('M'), GT_OreDictNames.craftingMonitorTier02});
        }
        
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "industrialgrinder", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,28), new Object[] {"EAP", "GGG", "ABA", 'B', GT_OreDictNames.craftingRawMachineTier02, 'A', OrePrefixes.circuit.get(Materials.Advanced), 'G', GT_OreDictNames.craftingGrinder, 'E', new ItemStack(GregTech_API.sBlockList[1], 1,25), 'P', GT_OreDictNames.craftingPump});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "distillationtower", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,44), new Object[] {"CAC", "PBP", "EAE", 'B', GT_OreDictNames.craftingRawMachineTier04, 'A', OrePrefixes.circuit.get(Materials.Master), 'C', GT_OreDictNames.craftingCentrifuge, 'E', new ItemStack(GregTech_API.sBlockList[1], 1,25), 'P', GT_OreDictNames.craftingPump});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "sawmill", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,32), new Object[] {"PAP", "DDD", "ABA", 'B', GT_OreDictNames.craftingRawMachineTier02, 'A', OrePrefixes.circuit.get(Materials.Advanced), 'D', GT_OreDictNames.craftingDiamondBlade, 'P', GT_OreDictNames.craftingPump});
        }
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,33), new Object[] {"XXX", "X X", "CGC", 'X', OrePrefixes.plate.get(Materials.Iron), 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', GT_OreDictNames.craftingGenerator});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,33), new Object[] {"XXX", "X X", "CGC", 'X', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', GT_OreDictNames.craftingGenerator});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,34), new Object[] {"XCX", "WPW", "XCX", 'X', OrePrefixes.plate.get(Materials.Invar), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', GT_ModHandler.getIC2Item("windMill", 1), 'P', "glassReinforced"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,34), new Object[] {"XCX", "WPW", "XCX", 'X', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', GT_ModHandler.getIC2Item("windMill", 1), 'P', "glassReinforced"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,35), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.Invar), 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', GT_OreDictNames.craftingGeothermalGenerator, 'P', "glassReinforced"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,35), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', GT_OreDictNames.craftingGeothermalGenerator, 'P', "glassReinforced"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,36), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.Iron), 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', GT_OreDictNames.craftingGenerator, 'P', "glassReinforced"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,36), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.Aluminium), 'C', OrePrefixes.circuit.get(Materials.Basic), 'G', GT_OreDictNames.craftingGenerator, 'P', "glassReinforced"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,37), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.TungstenSteel), 'C', OrePrefixes.circuit.get(Materials.Master), 'G', GT_OreDictNames.craftingGenerator, 'P', GT_ModHandler.getIC2Item("hvTransformer", 1)});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,91), new Object[] {"PCP", "GMG", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', GT_OreDictNames.craftingRawMachineTier02, 'G', OrePrefixes.gearGt.get(Materials.Steel), 'C', GT_OreDictNames.craftingChest});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,92), new Object[] {"PPP", "GMG", "PCP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', GT_OreDictNames.craftingRawMachineTier02, 'G', OrePrefixes.gearGt.get(Materials.Steel), 'C', GT_OreDictNames.craftingChest});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,93), new Object[] {"GCG", "CMC", "GCG", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', GT_OreDictNames.craftingRawMachineTier02, 'G', OrePrefixes.gearGt.get(Materials.Steel), 'C', OrePrefixes.circuit.get(Materials.Advanced)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,94), new Object[] {"PPP", "GMG", "PHP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', GT_OreDictNames.craftingRawMachineTier02, 'G', OrePrefixes.gearGt.get(Materials.Steel), 'H', GT_ModHandler.getIC2Item("hvTransformer", 1)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,95), new Object[] {"PHP", "GMG", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', GT_OreDictNames.craftingRawMachineTier02, 'G', OrePrefixes.gearGt.get(Materials.Steel), 'H', GT_ModHandler.getIC2Item("hvTransformer", 1)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,96), new Object[] {"PMP", "GRG", "PRP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', GT_OreDictNames.craftingRawMachineTier02, 'G', OrePrefixes.gearGt.get(Materials.Steel), 'R', "glassReinforced"});
        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,97), new Object[]{"PCP", "GMG", "PGP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', GT_OreDictNames.craftingRawMachineTier02, 'G', GT_OreDictNames.craftingGearGTSteel, 'C', GT_OreDictNames.craftingChest});
        GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,98), new Object[]{"PGP", "GMG", "PCP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', GT_OreDictNames.craftingRawMachineTier02, 'G', GT_OreDictNames.craftingGearGTSteel, 'C', GT_OreDictNames.craftingChest});
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "multi_gasturbine", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,108), new Object[] {"XXX", "GMG", "XCX", 'G', OrePrefixes.gearGt.get(Materials.Titanium), 'X', new ItemStack(GregTech_API.sBlockList[1], 1,34), 'M', GT_OreDictNames.craftingRawMachineTier03, 'C', OrePrefixes.circuit.get(Materials.Master)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 108), new Object[]{"XXX", "GMG", "XCX", 'G', OrePrefixes.gearGt.get(Materials.TungstenSteel), 'X', new ItemStack(GregTech_API.sBlockList[1], 1, 34), 'M', GT_OreDictNames.craftingRawMachineTier03, 'C', OrePrefixes.circuit.get(Materials.Master)});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "multi_steamturbine", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,109), new Object[] {"XXX", "GMG", "XCX", 'G', OrePrefixes.gearGt.get(Materials.Steel), 'X', new ItemStack(GregTech_API.sBlockList[1], 1,34), 'M', GT_OreDictNames.craftingRawMachineTier02, 'C', OrePrefixes.circuit.get(Materials.Advanced)});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "multi_plasmaturbine", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,117), new Object[] {"XCX", "XMX", "GCG", 'G', OrePrefixes.gearGt.get(Materials.TungstenSteel), 'X', new ItemStack(GregTech_API.sBlockList[1], 1,37), 'M', GT_OreDictNames.craftingRawMachineTier04, 'C', OrePrefixes.circuit.get(Materials.Master)});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "multi_thermalboiler", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,110), new Object[] {"XMX", "GCG", "XMX", 'G', OrePrefixes.gearGt.get(Materials.Titanium), 'X', new ItemStack(GregTech_API.sBlockList[1], 1,35), 'M', GT_OreDictNames.craftingCentrifuge, 'C', OrePrefixes.circuit.get(Materials.Elite)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,110), new Object[] {"XMX", "GCG", "XMX", 'G', OrePrefixes.gearGt.get(Materials.TungstenSteel), 'X', new ItemStack(GregTech_API.sBlockList[1], 1,35), 'M', GT_OreDictNames.craftingCentrifuge, 'C', OrePrefixes.circuit.get(Materials.Elite)});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "dragoneggenergysiphon", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,40), new Object[] {"CTC", "ISI", "CLC", 'C', OrePrefixes.circuit.get(Materials.Master), 'L', OrePrefixes.battery.get(Materials.Ultimate), 'S', new ItemStack(GregTech_API.sBlockList[1], 1,103), 'T', GT_OreDictNames.craftingTeleporter, 'I', OrePrefixes.plateAlloy.get("Iridium")});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,42), new Object[] {"CTC", "PSP", "CLC", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'L', OrePrefixes.battery.get(Materials.Master), 'S', new ItemStack(Blocks.beacon, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'T', GT_OreDictNames.craftingTeleporter, 'P', GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Thaumium, 1)==null?OrePrefixes.plate.get(Materials.Platinum):OrePrefixes.ingot.get(Materials.Thaumium)});
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "magicenergyabsorber", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,43), new Object[] {"CSC", "IBI", "CMC", 'C', OrePrefixes.circuit.get(Materials.Master), 'S', GT_OreDictNames.craftingSuperconductor, 'B', new ItemStack(Blocks.beacon, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'M', new ItemStack(GregTech_API.sBlockList[1], 1,42), 'I', OrePrefixes.plateAlloy.get("Iridium")});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,29), new Object[] {"ACA", "CMC", "FCF", 'M', GT_OreDictNames.craftingRawMachineTier02, 'A', OrePrefixes.circuit.get(Materials.Basic), 'C', GT_OreDictNames.craftingHeatingCoilTier00, 'F', "craftingInductionFurnace"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,31), new Object[] {"ABA", "DCD", "ABA", 'B', GT_OreDictNames.craftingRawMachineTier02, 'D', OrePrefixes.circuit.get(Materials.Basic), 'C', GT_OreDictNames.craftingCompressor, 'A', OrePrefixes.plateAlloy.get("Advanced")});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,68), new Object[] {"PPP", "CAC", "PPP", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'A', GT_OreDictNames.craftingMonitorTier02, 'C', new ItemStack(Items.comparator, 1)});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,68), new Object[] {"PPP", "CAC", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium)	, 'A', GT_OreDictNames.craftingMonitorTier02, 'C', new ItemStack(Items.comparator, 1)});
		
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,78), new Object[] {"PPP", "CAC", "PPP", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'A', GT_OreDictNames.craftingEnergyMeter, 'C', new ItemStack(Items.comparator, 1)});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,78), new Object[] {"PPP", "CAC", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium)	, 'A', GT_OreDictNames.craftingEnergyMeter, 'C', new ItemStack(Items.comparator, 1)});
		
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2,65), new Object[] {"PGP", "GLG", "PGP", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'G', new ItemStack(Blocks.glass_pane, 1), 'L', new ItemStack(Blocks.redstone_lamp, 1)});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2,65), new Object[] {"PGP", "GLG", "PGP", 'P', OrePrefixes.plate.get(Materials.Aluminium)	, 'G', new ItemStack(Blocks.glass_pane, 1), 'L', new ItemStack(Blocks.redstone_lamp, 1)});
		
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "redstonecircuitblock", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,69), new Object[] {"PRP", "CAC", "PRP", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'A', OrePrefixes.circuit.get(Materials.Basic), 'C', new ItemStack(Items.comparator, 1), 'R', new ItemStack(Items.repeater, 1)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,69), new Object[] {"PRP", "CAC", "PRP", 'P', OrePrefixes.plate.get(Materials.Aluminium)	, 'A', OrePrefixes.circuit.get(Materials.Basic), 'C', new ItemStack(Items.comparator, 1), 'R', new ItemStack(Items.repeater, 1)});
        }
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 4,70), new Object[] {"WWW", "A A", "WWW", 'W', "plankWood", 'A', OrePrefixes.plate.get(Materials.Aluminium)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 4,71), new Object[] {"III", "A A", "III", 'I', OrePrefixes.plate.get(Materials.Iron), 'A', OrePrefixes.plate.get(Materials.Aluminium)});
    	
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,71), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,74)});
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,72), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,71)});
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,73), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,72)});
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,74), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,73)});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,112), new Object[] {"PWP", "PMP", "PCP", 'P', OrePrefixes.plate.get(Materials.Iron), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', GT_OreDictNames.craftingWorkBench, 'M', GT_OreDictNames.craftingRawMachineTier01});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,112), new Object[] {"PWP", "PMP", "PCP", 'P', OrePrefixes.plate.get(Materials.Aluminium)  , 'C', OrePrefixes.circuit.get(Materials.Advanced), 'W', GT_OreDictNames.craftingWorkBench, 'M', GT_OreDictNames.craftingRawMachineTier01});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,128), new Object[] {"PWP", "PMP", "PPP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_OreDictNames.craftingWorkBench , 'M', GT_OreDictNames.craftingRawMachineTier00});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,129), new Object[] {"PPP", "PWP", "BFB", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'F', GT_OreDictNames.craftingFurnace});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,130), new Object[] {"WDH", "GMG", "PKP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'H', GT_ToolDictNames.craftingToolHardHammer, 'M', GT_OreDictNames.craftingRawMachineTier00, 'D', OrePrefixes.gem.get(Materials.Diamond), 'G', OrePrefixes.gearGt.get(Materials.Bronze), 'K', GT_OreDictNames.craftingPiston});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,131), new Object[] {"PWP", "PFP", "BMB", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'M', GT_OreDictNames.craftingRawMachineTier00, 'F', GT_OreDictNames.craftingFurnace});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,132), new Object[] {"PPP", "FWF", "BBB", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'F', new ItemStack(GregTech_API.sBlockList[1], 1,131)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,133), new Object[] {"GKG", "PWP", "PMP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'M', GT_OreDictNames.craftingRawMachineTier00, 'G', OrePrefixes.gearGt.get(Materials.Bronze), 'K', GT_OreDictNames.craftingPiston});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,134), new Object[] {"PGP", "KWK", "PMP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'M', GT_OreDictNames.craftingRawMachineTier00, 'G', OrePrefixes.gearGt.get(Materials.Bronze), 'K', GT_OreDictNames.craftingPiston});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,135), new Object[] {"PPP", "KWK", "PMP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'M', GT_OreDictNames.craftingRawMachineTier00, 'K', GT_OreDictNames.craftingPiston});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,136), new Object[] {"PBP", "BWB", "PBP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'F', GT_OreDictNames.craftingFurnace});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,137), new Object[] {"PPP", "PWP", "BFB", 'P', GT_OreDictNames.craftingPlateSteel , 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'F', GT_OreDictNames.craftingFurnace});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,138), new Object[] {"PWP", "PFP", "BMB", 'P', GT_OreDictNames.craftingPlateSteel , 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'M', GT_OreDictNames.craftingRawMachineTier01, 'F', GT_OreDictNames.craftingFurnace});
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "destructopack", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Destructopack.getUndamaged(1), new Object[] {"ARA", "RLR", "ARA", 'A', OrePrefixes.circuit.get(Materials.Advanced), 'R', OrePrefixes.plate.get(Materials.Iron), 'L', new ItemStack(Items.lava_bucket, 1)});
    		GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Destructopack.getUndamaged(1), new Object[] {"ARA", "RLR", "ARA", 'A', OrePrefixes.circuit.get(Materials.Advanced), 'R', OrePrefixes.plate.get(Materials.Aluminium), 'L', new ItemStack(Items.lava_bucket, 1)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "heliumcoolant", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Reactor_Coolant_He_1.getUndamaged(1), new Object[] {" T ", "THT", " T ", 'H', OrePrefixes.cell.get(Materials.Helium), 'T', OrePrefixes.plate.get(Materials.Tin)});
    		GT_ModHandler.addCraftingRecipe(GT_Items.Reactor_Coolant_He_3.getUndamaged(1), new Object[] {"TTT", "HHH", "TTT", 'H', GT_Items.Reactor_Coolant_He_1.getWildcard(1), 'T', OrePrefixes.plate.get(Materials.Tin)});
    		GT_ModHandler.addCraftingRecipe(GT_Items.Reactor_Coolant_He_6.getUndamaged(1), new Object[] {"THT", "TCT", "THT", 'H', GT_Items.Reactor_Coolant_He_3.getWildcard(1), 'T', OrePrefixes.plate.get(Materials.Tin), 'C', OrePrefixes.plateDense.get(Materials.Copper)});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("overclockerUpgrade", 2), new Object[] {" H ", "WCW", "   ", 'H', GT_Items.Reactor_Coolant_He_1.getWildcard(1), 'W', GT_OreDictNames.craftingWireCopper, 'C', OrePrefixes.circuit.get(Materials.Basic)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "nakcoolant", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Reactor_Coolant_NaK_1.getUndamaged(1), new Object[] {"TNT", "KCK", "TNT", 'N', OrePrefixes.cell.get(Materials.Sodium), 'K', OrePrefixes.cell.get(Materials.Potassium), 'T', OrePrefixes.plate.get(Materials.Tin), 'C', GT_ModHandler.getIC2Item("reactorCoolantSimple", 1)});
    		GT_ModHandler.addCraftingRecipe(GT_Items.Reactor_Coolant_NaK_1.getUndamaged(1), new Object[] {"TKT", "NCN", "TKT", 'N', OrePrefixes.cell.get(Materials.Sodium), 'K', OrePrefixes.cell.get(Materials.Potassium), 'T', OrePrefixes.plate.get(Materials.Tin), 'C', GT_ModHandler.getIC2Item("reactorCoolantSimple", 1)});
    		GT_ModHandler.addCraftingRecipe(GT_Items.Reactor_Coolant_NaK_3.getUndamaged(1), new Object[] {"TTT", "HHH", "TTT", 'H', GT_Items.Reactor_Coolant_NaK_1.getWildcard(1), 'T', OrePrefixes.plate.get(Materials.Tin)});
    		GT_ModHandler.addCraftingRecipe(GT_Items.Reactor_Coolant_NaK_6.getUndamaged(1), new Object[] {"THT", "TCT", "THT", 'H', GT_Items.Reactor_Coolant_NaK_3.getWildcard(1), 'T', OrePrefixes.plate.get(Materials.Tin), 'C', OrePrefixes.plateDense.get(Materials.Copper)});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("overclockerUpgrade", 2), new Object[] {" H ", "WCW", "   ", 'H', GT_Items.Reactor_Coolant_NaK_1.getWildcard(1), 'W', GT_OreDictNames.craftingWireCopper, 'C', OrePrefixes.circuit.get(Materials.Basic)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "iridiumreflector", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Reactor_NeutronReflector.getUndamaged(1), new Object[] {"NNN", "NIN", "NNN", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'N', GT_ModHandler.getIC2Item("reactorReflectorThick", 1)});
    	}
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "lapotronpack", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Armor_LapotronicPack.getAlmostBroken(1), new Object[] {"CLC", "SPS", "CIC", 'L', OrePrefixes.battery.get(Materials.Ultimate), 'C', OrePrefixes.circuit.get(Materials.Master), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'P', OrePrefixes.battery.get(Materials.Ultimate), 'S', GT_OreDictNames.craftingSuperconductor});
    	}
    	
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "lithiumbatpack", true)) {
            GT_ModHandler.addCraftingRecipe(GT_Items.Armor_LithiumPack.getAlmostBroken(1), new Object[] {"LCL", "LAL", "L L", 'L', OrePrefixes.battery.get(Materials.Lithium), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'A', OrePrefixes.plate.get(Materials.Aluminium)});
        }

        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "lighthelmet", true)) {
            GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Armor_Lamp.getAlmostBroken(1), new Object[] {GT_ModHandler.getIC2Item("solarHelmet", 1), GT_ModHandler.getIC2Item("luminator", 1)});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "rockcutter", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Rockcutter.getAlmostBroken(1), new Object[] {"DS ", "DP ", "DCB", 'B', OrePrefixes.battery.get(Materials.Basic), 'C', OrePrefixes.circuit.get(Materials.Basic), 'P', OrePrefixes.plate.get(Materials.Titanium), 'S', OrePrefixes.stick.get(Materials.Titanium), 'D', OrePrefixes.dust.get(Materials.Diamond)});
    		GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Rockcutter.getAlmostBroken(1), new Object[] {"DS ", "DP ", "DCB", 'B', OrePrefixes.battery.get(Materials.Basic), 'C', OrePrefixes.circuit.get(Materials.Basic), 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'S', OrePrefixes.stick.get(Materials.TungstenSteel), 'D', OrePrefixes.dust.get(Materials.Diamond)});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "jackhammer", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Jackhammer_Bronze.getAlmostBroken(1), new Object[] {"SBS", " C ", " I ", 'B', OrePrefixes.battery.get(Materials.Basic), 'C', OrePrefixes.circuit.get(Materials.Basic), 'S', OrePrefixes.stick.get(Materials.Bronze), 'I', OrePrefixes.ingot.get(Materials.Bronze)});
            GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Jackhammer_Steel.getAlmostBroken(1), new Object[] {"SBS", " C ", " I ", 'B', OrePrefixes.battery.get(Materials.Basic), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'S', OrePrefixes.stick.get(Materials.StainlessSteel), 'I', OrePrefixes.ingot.get(Materials.Steel)});
            GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Jackhammer_Diamond.getAlmostBroken(1), new Object[] {"SBS", " C ", " D ", 'B', OrePrefixes.battery.get(Materials.Lithium), 'C', OrePrefixes.circuit.get(Materials.Advanced), 'S', OrePrefixes.stick.get(Materials.TungstenSteel), 'D', GT_OreDictNames.craftingIndustrialDiamond});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "teslastaff", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Teslastaff.getAlmostBroken(1), new Object[] {"LS ", "SI ", "  I", 'L', OrePrefixes.battery.get(Materials.Ultimate), 'S', GT_OreDictNames.craftingSuperconductor, 'I', OrePrefixes.plateAlloy.get("Iridium")});
        }
    	
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "cloakingdevice", true)) {
    		GT_ModHandler.addCraftingRecipe(GT_Items.Armor_Cloaking.getAlmostBroken(1), new Object[] {"CIC", "ILI", "CIC", 'L', OrePrefixes.battery.get(Materials.Ultimate), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'C', OrePrefixes.plate.get(Materials.Chrome)});
    	}
    	
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal, 1), new Object[] {GT_Items.Spray_Hydration.getWildcard(1), OrePrefixes.dust.get(Materials.Coal)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Tool_Mortar_Wood.getUndamaged(1), new Object[] {new ItemStack(Items.bowl, 1), new ItemStack(Items.flint, 1)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar	, "Coal"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal	, 1)	, new Object[] {GT_Items.Tool_Mortar_Wood.getWildcard(1), new ItemStack(Items.coal, 1)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar	, "Gold"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold			, 1)	, new Object[] {GT_Items.Tool_Mortar_Wood.getWildcard(1), "ingotGold"});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar	, "Clay"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Clay			, 1)	, new Object[] {GT_Items.Tool_Mortar_Wood.getWildcard(1), new ItemStack(Blocks.clay, 1)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar	, "Copper"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Copper		, 1)	, new Object[] {GT_Items.Tool_Mortar_Wood.getWildcard(1), OrePrefixes.ingot.get(Materials.Copper)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar	, "Tin"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tin			, 1)	, new Object[] {GT_Items.Tool_Mortar_Wood.getWildcard(1), OrePrefixes.ingot.get(Materials.Tin)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar	, "Silver"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver		, 1)	, new Object[] {GT_Items.Tool_Mortar_Wood.getWildcard(1), OrePrefixes.ingot.get(Materials.Silver)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar	, "Electrum", true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Electrum		, 1)	, new Object[] {GT_Items.Tool_Mortar_Wood.getWildcard(1), OrePrefixes.ingot.get(Materials.Electrum)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar	, "Wheat"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wheat		, 1)	, new Object[] {GT_Items.Tool_Mortar_Wood.getWildcard(1), new ItemStack(Items.wheat, 1)});
        
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Mortar_Iron.getUndamaged(1), new Object[] {" R ", "SRS", "SSS", 'S', "stoneBricks", 'R', OrePrefixes.ingot.get(Materials.Iron)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Coal"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.HydratedCoal	, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), new ItemStack(Items.coal, 1)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Gold"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold			, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), "ingotGold"});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Clay"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Clay			, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), new ItemStack(Blocks.clay, 1)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Copper"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Copper		, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), OrePrefixes.ingot.get(Materials.Copper)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Tin"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tin			, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), OrePrefixes.ingot.get(Materials.Tin)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Bronze"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bronze		, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), OrePrefixes.ingot.get(Materials.Bronze)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Silver"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver		, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), OrePrefixes.ingot.get(Materials.Silver)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Electrum"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Electrum		, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), OrePrefixes.ingot.get(Materials.Electrum)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Brass"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass		, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), "ingotBrass"});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Iron"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron			, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), OrePrefixes.ingot.get(Materials.Iron)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Flint"		, true)) GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.flint, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), new ItemStack(Blocks.gravel, 1)});
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Tools.mortar, "Wheat"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wheat		, 1), new Object[] {GT_Items.Tool_Mortar_Iron.getWildcard(1), new ItemStack(Items.wheat, 1)});
        
        if (!Materials.Steel.mBlastFurnaceRequired) {
            GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel, 1)	, new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Coal), OrePrefixes.dust.get(Materials.Coal)});
        }
        
        if(GT_Mod.sNerfDustCrafting) {
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall	, Materials.Electrum	, 6), new Object[] {OrePrefixes.dust.get(Materials.Silver), OrePrefixes.dust.get(Materials.Gold)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Brass		, 3), new Object[] {OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Zinc)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall	, Materials.Brass		, 9), new Object[] {OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Zinc)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Bronze		, 3), new Object[] {OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Tin)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall	, Materials.Bronze		, 9), new Object[] {OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tin)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall	, Materials.Invar		, 9), new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Nickel)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall	, Materials.Cupronickel	, 6), new Object[] {OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Copper)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall	, Materials.Nichrome	,15), new Object[] {OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Chrome)});
        } else {
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Electrum	, 2), new Object[] {OrePrefixes.dust.get(Materials.Silver), OrePrefixes.dust.get(Materials.Gold)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Brass		, 4), new Object[] {OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Zinc)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Brass		, 3), new Object[] {OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Zinc)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Bronze		, 4), new Object[] {OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Tin)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Bronze		, 3), new Object[] {OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tin)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Invar		, 3), new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Nickel)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Cupronickel	, 2), new Object[] {OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Copper)});
           GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Nichrome	, 5), new Object[] {OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Chrome)});
        }

        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Ultimet			, 9), new Object[] {OrePrefixes.dust.get(Materials.Cobalt), OrePrefixes.dust.get(Materials.Cobalt), OrePrefixes.dust.get(Materials.Cobalt), OrePrefixes.dust.get(Materials.Cobalt), OrePrefixes.dust.get(Materials.Cobalt), OrePrefixes.dust.get(Materials.Chrome), OrePrefixes.dust.get(Materials.Chrome), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Molybdenum)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.CobaltBrass		, 9), new Object[] {OrePrefixes.dust.get(Materials.Brass), OrePrefixes.dust.get(Materials.Brass), OrePrefixes.dust.get(Materials.Brass), OrePrefixes.dust.get(Materials.Brass), OrePrefixes.dust.get(Materials.Brass), OrePrefixes.dust.get(Materials.Brass), OrePrefixes.dust.get(Materials.Brass), OrePrefixes.dust.get(Materials.Aluminium), OrePrefixes.dust.get(Materials.Cobalt)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.StainlessSteel	, 9), new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Manganese), OrePrefixes.dust.get(Materials.Chrome)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Kanthal			, 3), new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Aluminium), OrePrefixes.dust.get(Materials.Chrome)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.Ultimet			, 1), new Object[] {OrePrefixes.dustTiny.get(Materials.Cobalt), OrePrefixes.dustTiny.get(Materials.Cobalt), OrePrefixes.dustTiny.get(Materials.Cobalt), OrePrefixes.dustTiny.get(Materials.Cobalt), OrePrefixes.dustTiny.get(Materials.Cobalt), OrePrefixes.dustTiny.get(Materials.Chrome), OrePrefixes.dustTiny.get(Materials.Chrome), OrePrefixes.dustTiny.get(Materials.Nickel), OrePrefixes.dustTiny.get(Materials.Molybdenum)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.CobaltBrass		, 1), new Object[] {OrePrefixes.dustTiny.get(Materials.Brass), OrePrefixes.dustTiny.get(Materials.Brass), OrePrefixes.dustTiny.get(Materials.Brass), OrePrefixes.dustTiny.get(Materials.Brass), OrePrefixes.dustTiny.get(Materials.Brass), OrePrefixes.dustTiny.get(Materials.Brass), OrePrefixes.dustTiny.get(Materials.Brass), OrePrefixes.dustTiny.get(Materials.Aluminium), OrePrefixes.dustTiny.get(Materials.Cobalt)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.StainlessSteel	, 1), new Object[] {OrePrefixes.dustTiny.get(Materials.Iron), OrePrefixes.dustTiny.get(Materials.Iron), OrePrefixes.dustTiny.get(Materials.Iron), OrePrefixes.dustTiny.get(Materials.Iron), OrePrefixes.dustTiny.get(Materials.Iron), OrePrefixes.dustTiny.get(Materials.Iron), OrePrefixes.dustTiny.get(Materials.Nickel), OrePrefixes.dustTiny.get(Materials.Manganese), OrePrefixes.dustTiny.get(Materials.Chrome)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustTiny	, Materials.Kanthal			, 3), new Object[] {OrePrefixes.dustTiny.get(Materials.Iron), OrePrefixes.dustTiny.get(Materials.Aluminium), OrePrefixes.dustTiny.get(Materials.Chrome)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust		, Materials.IronWood		, 1), new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.LiveRoot), OrePrefixes.dustTiny.get(Materials.Gold)});
        
   	 	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.gunpowder, 3), new Object[]{OrePrefixes.dust.get(Materials.Coal), OrePrefixes.dust.get(Materials.Sulfur), OrePrefixes.dust.get(Materials.Saltpeter), OrePrefixes.dust.get(Materials.Saltpeter)});
   	 	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.gunpowder, 2), new Object[]{OrePrefixes.dust.get(Materials.Charcoal), OrePrefixes.dust.get(Materials.Sulfur), OrePrefixes.dust.get(Materials.Saltpeter), OrePrefixes.dust.get(Materials.Saltpeter)});
        
   	 	GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 10), new Object[] {OrePrefixes.dust.get(Materials.Potassium), OrePrefixes.dust.get(Materials.Potassium), OrePrefixes.cell.get(Materials.Nitrogen), OrePrefixes.cell.get(Materials.Nitrogen), OrePrefixes.cell.get(Materials.Oxygen), OrePrefixes.cell.get(Materials.Oxygen), OrePrefixes.cell.get(Materials.Oxygen)});
   	 	GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("carbonFiber", 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 1), new Object[] {"GGG", "EDE", "GGG", 'G', new ItemStack(Blocks.glass, 1), 'D', OrePrefixes.dust.get(Materials.Silver), 'E', GT_Items.IC2_Energium_Dust.get(1)});
        GT_ModHandler.removeRecipeByOutput(GT_Items.IC2_Energium_Dust.get(1));
        if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "energycrystalruby", true)) {
           GT_ModHandler.addCraftingRecipe(GT_Items.IC2_Energium_Dust.get(9), false, false, new Object[] {"RDR", "DRD", "RDR", 'R', OrePrefixes.dust.get(Materials.Redstone), 'D', OrePrefixes.dust.get(Materials.Ruby)});
        } else {
           GT_ModHandler.addCraftingRecipe(GT_Items.IC2_Energium_Dust.get(9), false, false, new Object[] {"RDR", "DRD", "RDR", 'R', OrePrefixes.dust.get(Materials.Redstone), 'D', OrePrefixes.dust.get(Materials.Diamond)});
        }
        
        GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("lapotronCrystal", 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), false, false, new Object[] {"LCL", "LSL", "LCL", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'S', GT_ModHandler.getIC2Item("energyCrystal", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'L', OrePrefixes.dust.get(Materials.Lazurite)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), false, false, new Object[] {"LCL", "LSL", "LCL", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'S', GT_ModHandler.getIC2Item("energyCrystal", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'L', OrePrefixes.dust.get(Materials.Lapis)});
        if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "mininglaser", true) && GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("miningLaser", 1))) {
           GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("miningLaser", 1), new Object[] {"RHE", "TTC", " AA", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'H', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "heliumcoolant", true)?GT_Items.Reactor_Coolant_He_6.getWildcard(1):(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.gregtechrecipes, "nakcoolant", true)?GT_Items.Reactor_Coolant_NaK_6.getWildcard(1):GT_ModHandler.getIC2Item("reactorCoolantSix", 1)), 'R', GT_OreDictNames.craftingLenseRed, 'T', OrePrefixes.plate.get(Materials.Titanium), 'E', OrePrefixes.battery.get(Materials.Elite), 'A', OrePrefixes.plateAlloy.get(Materials.Advanced)});
        }
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("luminator", 16), new Object[] {"RTR", "GHG", "GGG", 'H', OrePrefixes.cell.get(Materials.Helium), 'T', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'R', GT_ModHandler.getIC2Item("casingiron", 1), 'G', new ItemStack(Blocks.glass, 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("luminator", 16), new Object[] {"RTR", "GHG", "GGG", 'H', OrePrefixes.cell.get(Materials.Mercury), 'T', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'R', GT_ModHandler.getIC2Item("casingiron", 1), 'G', new ItemStack(Blocks.glass, 1)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Coin_Chocolate			.get(1), new Object[]{OrePrefixes.dust.get(Materials.Chocolate), OrePrefixes.dust.get(Materials.Milk), OrePrefixes.dust.get(Materials.Sugar), OrePrefixes.nugget.get(Materials.Gold)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Copper				.get(8), false, false, true, new Object[] {GT_Items.Credit_Iron.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Iron				.get(8), false, false, true, new Object[] {GT_Items.Credit_Silver.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Silver				.get(8), false, false, true, new Object[] {GT_Items.Credit_Gold.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Gold				.get(8), false, false, true, new Object[] {GT_Items.Credit_Platinum.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Platinum			.get(8), false, false, true, new Object[] {GT_Items.Credit_Osmium.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Iron				.get(1), false, false, true, new Object[] {GT_Items.Credit_Copper.get(1), GT_Items.Credit_Copper.get(1), GT_Items.Credit_Copper.get(1), GT_Items.Credit_Copper.get(1), GT_Items.Credit_Copper.get(1), GT_Items.Credit_Copper.get(1), GT_Items.Credit_Copper.get(1), GT_Items.Credit_Copper.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Silver				.get(1), false, false, true, new Object[] {GT_Items.Credit_Iron.get(1), GT_Items.Credit_Iron.get(1), GT_Items.Credit_Iron.get(1), GT_Items.Credit_Iron.get(1), GT_Items.Credit_Iron.get(1), GT_Items.Credit_Iron.get(1), GT_Items.Credit_Iron.get(1), GT_Items.Credit_Iron.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Gold				.get(1), false, false, true, new Object[] {GT_Items.Credit_Silver.get(1), GT_Items.Credit_Silver.get(1), GT_Items.Credit_Silver.get(1), GT_Items.Credit_Silver.get(1), GT_Items.Credit_Silver.get(1), GT_Items.Credit_Silver.get(1), GT_Items.Credit_Silver.get(1), GT_Items.Credit_Silver.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Platinum			.get(1), false, false, true, new Object[] {GT_Items.Credit_Gold.get(1), GT_Items.Credit_Gold.get(1), GT_Items.Credit_Gold.get(1), GT_Items.Credit_Gold.get(1), GT_Items.Credit_Gold.get(1), GT_Items.Credit_Gold.get(1), GT_Items.Credit_Gold.get(1), GT_Items.Credit_Gold.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Osmium				.get(1), false, false, true, new Object[] {GT_Items.Credit_Platinum.get(1), GT_Items.Credit_Platinum.get(1), GT_Items.Credit_Platinum.get(1), GT_Items.Credit_Platinum.get(1), GT_Items.Credit_Platinum.get(1), GT_Items.Credit_Platinum.get(1), GT_Items.Credit_Platinum.get(1), GT_Items.Credit_Platinum.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Copper		.get(8), false, false, true, new Object[] {GT_Items.Credit_Greg_Cupronickel.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Cupronickel	.get(8), false, false, true, new Object[] {GT_Items.Credit_Greg_Silver.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Silver		.get(8), false, false, true, new Object[] {GT_Items.Credit_Greg_Gold.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Gold			.get(8), false, false, true, new Object[] {GT_Items.Credit_Greg_Platinum.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Platinum		.get(8), false, false, true, new Object[] {GT_Items.Credit_Greg_Osmium.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Osmium		.get(8), false, false, true, new Object[] {GT_Items.Credit_Greg_Naquadah.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Naquadah		.get(8), false, false, true, new Object[] {GT_Items.Credit_Greg_Neutronium.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Cupronickel	.get(1), false, false, true, new Object[] {GT_Items.Credit_Greg_Copper.get(1), GT_Items.Credit_Greg_Copper.get(1), GT_Items.Credit_Greg_Copper.get(1), GT_Items.Credit_Greg_Copper.get(1), GT_Items.Credit_Greg_Copper.get(1), GT_Items.Credit_Greg_Copper.get(1), GT_Items.Credit_Greg_Copper.get(1), GT_Items.Credit_Greg_Copper.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Silver		.get(1), false, false, true, new Object[] {GT_Items.Credit_Greg_Cupronickel.get(1), GT_Items.Credit_Greg_Cupronickel.get(1), GT_Items.Credit_Greg_Cupronickel.get(1), GT_Items.Credit_Greg_Cupronickel.get(1), GT_Items.Credit_Greg_Cupronickel.get(1), GT_Items.Credit_Greg_Cupronickel.get(1), GT_Items.Credit_Greg_Cupronickel.get(1), GT_Items.Credit_Greg_Cupronickel.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Gold			.get(1), false, false, true, new Object[] {GT_Items.Credit_Greg_Silver.get(1), GT_Items.Credit_Greg_Silver.get(1), GT_Items.Credit_Greg_Silver.get(1), GT_Items.Credit_Greg_Silver.get(1), GT_Items.Credit_Greg_Silver.get(1), GT_Items.Credit_Greg_Silver.get(1), GT_Items.Credit_Greg_Silver.get(1), GT_Items.Credit_Greg_Silver.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Platinum		.get(1), false, false, true, new Object[] {GT_Items.Credit_Greg_Gold.get(1), GT_Items.Credit_Greg_Gold.get(1), GT_Items.Credit_Greg_Gold.get(1), GT_Items.Credit_Greg_Gold.get(1), GT_Items.Credit_Greg_Gold.get(1), GT_Items.Credit_Greg_Gold.get(1), GT_Items.Credit_Greg_Gold.get(1), GT_Items.Credit_Greg_Gold.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Osmium		.get(1), false, false, true, new Object[] {GT_Items.Credit_Greg_Platinum.get(1), GT_Items.Credit_Greg_Platinum.get(1), GT_Items.Credit_Greg_Platinum.get(1), GT_Items.Credit_Greg_Platinum.get(1), GT_Items.Credit_Greg_Platinum.get(1), GT_Items.Credit_Greg_Platinum.get(1), GT_Items.Credit_Greg_Platinum.get(1), GT_Items.Credit_Greg_Platinum.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Naquadah		.get(1), false, false, true, new Object[] {GT_Items.Credit_Greg_Osmium.get(1), GT_Items.Credit_Greg_Osmium.get(1), GT_Items.Credit_Greg_Osmium.get(1), GT_Items.Credit_Greg_Osmium.get(1), GT_Items.Credit_Greg_Osmium.get(1), GT_Items.Credit_Greg_Osmium.get(1), GT_Items.Credit_Greg_Osmium.get(1), GT_Items.Credit_Greg_Osmium.get(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Credit_Greg_Neutronium	.get(1), false, false, true, new Object[] {GT_Items.Credit_Greg_Naquadah.get(1), GT_Items.Credit_Greg_Naquadah.get(1), GT_Items.Credit_Greg_Naquadah.get(1), GT_Items.Credit_Greg_Naquadah.get(1), GT_Items.Credit_Greg_Naquadah.get(1), GT_Items.Credit_Greg_Naquadah.get(1), GT_Items.Credit_Greg_Naquadah.get(1), GT_Items.Credit_Greg_Naquadah.get(1)});
        
        GT_ModHandler.removeRecipe(new ItemStack[] {tStack = GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Sulfur), 1), tStack, tStack, tStack, new ItemStack(Items.coal, 1, 0), tStack, tStack, tStack, tStack});
        GT_ModHandler.removeRecipe(new ItemStack[] {tStack = GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Sulfur), 1), tStack, tStack, tStack, new ItemStack(Items.coal, 1, 1), tStack, tStack, tStack, tStack});
        GT_ModHandler.removeRecipe(new ItemStack[] {tStack = new ItemStack(Items.wheat_seeds, 1), tStack, tStack, tStack, null, tStack, tStack, tStack, tStack});
        GT_ModHandler.removeRecipe(new ItemStack[] {null, tStack = new ItemStack(Items.coal, 1), null, tStack, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Iron, 1), tStack, null, tStack, null});
        
        GT_Log.log.info("Applying harder Recipes for several Blocks.");
        if (GT_ModHandler.removeRecipeByOutput(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Iridium, 1))) {
        	GT_ModHandler.addRollingMachineRecipe(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "iridiumplate", true)?GregTech_API.getGregTechMaterial(4, 1):GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Iridium, 1L), new Object[]{"IAI", "ADA", "IAI", 'D', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "iridiumplate", true)?GT_OreDictNames.craftingIndustrialDiamond:OrePrefixes.dust.get(Materials.Diamond), Character.valueOf('A'), OrePrefixes.plateAlloy.get("Advanced"), 'I', OrePrefixes.plate.get(Materials.Iridium)});
            GT_ModHandler.addCraftingRecipe(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "iridiumplate", true)?GregTech_API.getGregTechMaterial(4, 1):GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Iridium, 1L), new Object[]{"IAI", "ADA", "IAI", 'D', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "iridiumplate", true)?GT_OreDictNames.craftingIndustrialDiamond:OrePrefixes.dust.get(Materials.Diamond), Character.valueOf('A'), OrePrefixes.plateAlloy.get("Advanced"), 'I', OrePrefixes.plate.get(Materials.Iridium)});
    	}
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "blockbreaker", false)) {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Blocks.cobblestone, 1), new ItemStack(Items.iron_pickaxe, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.piston, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Items.redstone, 1), new ItemStack(Blocks.cobblestone, 1)}), new Object[] {"RGR", "RPR", "RCR" , 'G', GT_OreDictNames.craftingGrinder, 'C', OrePrefixes.circuit.get(Materials.Advanced), 'R', GT_OreDictNames.craftingPlateSteel, 'P', GT_OreDictNames.craftingPiston});
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "beryliumreflector", true) && GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("reactorReflectorThick", 1))) {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorReflectorThick", 1), new Object[] {" N ", "NBN", " N ", 'B', OrePrefixes.cell.get(Materials.Beryllium), 'N', GT_ModHandler.getIC2Item("reactorReflector", 1)});
        }
        
    	if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "windmill", true)) {
        	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("windMill", 1));
        }
    	
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "watermill", true)) {
        	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("waterMill", 1));
        }
		
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "solarpanel", true)) {
        	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("solarPanel", 1));
        }
        
        if (GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Diamond, 1) != null) {
        	tStack = GT_ModHandler.getRecipeOutput(new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Iron, 1), new ItemStack(Items.redstone, 1), GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Iron, 1), GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Gold, 1), GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Iron, 1), GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Gold, 1), GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Diamond, 1), new ItemStack(Items.diamond_pickaxe, 1), GT_OreDictUnificator.get(OrePrefixes.gear, Materials.Diamond, 1)});
            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "quarry", true)) {
            	GT_ModHandler.removeRecipeByOutput(tStack);
            	GT_ModHandler.addCraftingRecipe(tStack, new Object[]{"ICI", "GIG", "DPD", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'D', OrePrefixes.gear.get(Materials.Diamond), 'G', OrePrefixes.gear.get(Materials.Gold), 'I', OrePrefixes.gear.get(Materials.Steel), 'P', GT_ModHandler.getIC2Item("diamondDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
            }

            if(GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "quarry", false)) {
            	GT_ModHandler.removeRecipeByOutput(tStack);
            }
        }
        
        GT_Log.log.info("Applying Recipes for Tools");
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "nanosaber", true)) {
        	if (GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("nanoSaber", 1)))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("nanoSaber", 1), false, new Object[] {"PI ", "PI ", "CLC", 'L', OrePrefixes.battery.get(Materials.Master), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'P', OrePrefixes.plate.get(Materials.Platinum), 'C', OrePrefixes.circuit.get(Materials.Master)});
        }
        
		if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "namefix", true)) {
			GT_ModHandler.addCraftingRecipe(GT_ModHandler.removeRecipeByOutput(new ItemStack(Items.flint_and_steel, 1))?new ItemStack(Items.flint_and_steel, 1):null, new Object[] {"S ", " F", 'F', new ItemStack(Items.flint, 1), 'S', "nuggetSteel"});
		}
		
        if (GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("diamondDrill"		, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("diamondDrill"		, 1), false, true, false, new Object[] {" D ", "DMD", "TAT", 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'D', "craftingIndustrialDiamond", 'T', OrePrefixes.plate.get(Materials.Titanium), 'A', OrePrefixes.circuit.get(Materials.Advanced)});
        if (GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("miningDrill"		, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("miningDrill"		, 1), false, true, false, new Object[] {" S ", "SCS", "SBS", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        if (GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("chainsaw"			, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("chainsaw"			, 1), false, true, false, new Object[] {"BS ", "SCS", " SS", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        if (GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("electricHoe"		, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricHoe"		, 1), false, true, false, new Object[] {"SS ", " C ", " B ", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        if (GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("electricTreetap"	, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricTreetap"	, 1), false, true, false, new Object[] {" B ", "SCS", "S  ", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        if (GT_ModHandler.removeRecipeByOutput(GT_ModHandler.getIC2Item("electricWrench"	, 1)))	GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Wrench_Electric.getAlmostBroken(1), false, true, false, new Object[] {"S S", "SCS", " B ", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Tool_Wrench_Bronze.getUndamaged(1)		, false, false, new Object[] {GT_ModHandler.getIC2Item("wrench", 1, 0)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_Items.Tool_Wrench_Electric.getAlmostBroken(1)	, false, false, new Object[] {GT_ModHandler.getIC2Item("electricWrench", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("wrench", 1, 0)					, false, true, false, new Object[] {" B ", "BBB", "B B", 'B', OrePrefixes.ingot.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricWrench", 1, 0)			, false, true, false, new Object[] {" B ", "SCS", "S S", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Wrench_Advanced			.getAlmostBroken(1), false, true, false, new Object[]{"T T", "TCT", " B ", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'B', OrePrefixes.battery.get(Materials.Lithium), 'T', OrePrefixes.plate.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_SolderingIron_Electric	.getAlmostBroken(1), false, true, false, new Object[]{"R", "C", "B", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'R', OrePrefixes.ingot.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Crowbar_Iron				   .getUndamaged(1), false, true, false, new Object[]{" BI", "BIB", "IB ", 'I', OrePrefixes.stick.get(Materials.Iron), 'B', "dyeBlue"});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Screwdriver_Iron			   .getUndamaged(1), false, true, false, new Object[]{"I  ", " I ", "  S", 'I', OrePrefixes.stick.get(Materials.Iron), 'S', OrePrefixes.stick.get(Materials.Wood)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Screwdriver_TungstenSteel	   .getUndamaged(1), false, true, false, new Object[]{"I  ", " I ", "  S", 'I', OrePrefixes.stick.get(Materials.TungstenSteel), 'S', OrePrefixes.stick.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Wrench_Steel				   .getUndamaged(1), false, true, false, new Object[]{"S S", "SSS", " S ", 'S', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Wrench_Iron				   .getUndamaged(1), false, true, false, new Object[]{"I I", "III", " I ", 'I', OrePrefixes.ingot.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Wrench_TungstenSteel		   .getUndamaged(1), false, true, false, new Object[]{"T T", "TTT", " T ", 'T', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Wrench_Bronze				   .getUndamaged(1), false, true, false, new Object[]{"B B", "BBB", " B ", 'B', OrePrefixes.ingot.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hammer_Rubber				   .getUndamaged(1), false, true, false, new Object[]{"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Wood), 'R', OrePrefixes.ingot.get(Materials.Rubber)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hammer_Plastic			   .getUndamaged(1), false, true, false, new Object[]{"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Iron), 'R', OrePrefixes.ingot.get(Materials.Plastic)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hammer_Iron				   .getUndamaged(1), false, true, false, new Object[]{"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Wood), 'R', OrePrefixes.ingot.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hammer_Bronze				   .getUndamaged(1), false, true, false, new Object[]{"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Wood), 'R', OrePrefixes.ingot.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hammer_Steel				   .getUndamaged(1), false, true, false, new Object[]{"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Iron), 'R', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Hammer_TungstenSteel		   .getUndamaged(1), false, true, false, new Object[]{"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Steel), 'R', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_File_Iron					   .getUndamaged(1), false, true, false, new Object[]{"P", "P", "S", 'S', OrePrefixes.stick.get(Materials.Wood), 'P', OrePrefixes.plate.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_File_Bronze				   .getUndamaged(1), false, true, false, new Object[]{"P", "P", "S", 'S', OrePrefixes.stick.get(Materials.Wood), 'P', OrePrefixes.plate.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_File_Steel				   .getUndamaged(1), false, true, false, new Object[]{"P", "P", "S", 'S', OrePrefixes.stick.get(Materials.Iron), 'P', OrePrefixes.plate.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_File_TungstenSteel		   .getUndamaged(1), false, true, false, new Object[]{"P", "P", "S", 'S', OrePrefixes.stick.get(Materials.Steel), 'P', OrePrefixes.plate.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Saw_Iron					   .getUndamaged(1), false, true, false, new Object[]{"SSS", "PPS", "FH ", 'S', OrePrefixes.stick.get(Materials.Wood), 'P', OrePrefixes.plate.get(Materials.Iron), 'F', GT_ToolDictNames.craftingToolFile, 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Saw_Bronze			   	   .getUndamaged(1), false, true, false, new Object[]{"SSS", "PPS", "FH ", 'S', OrePrefixes.stick.get(Materials.Wood), 'P', OrePrefixes.plate.get(Materials.Bronze), 'F', GT_ToolDictNames.craftingToolFile, 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Saw_Steel			   		   .getUndamaged(1), false, true, false, new Object[]{"SSS", "PPS", "FH ", 'S', OrePrefixes.stick.get(Materials.Iron), 'P', OrePrefixes.plate.get(Materials.Steel), 'F', GT_ToolDictNames.craftingToolFile, 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Saw_TungstenSteel			   .getUndamaged(1), false, true, false, new Object[]{"SSS", "PPS", "FH ", 'S', OrePrefixes.stick.get(Materials.Steel), 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'F', GT_ToolDictNames.craftingToolFile, 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Saw_Electric				.getAlmostBroken(1), false, true, false, new Object[]{" SS", "SCS", "BS ", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Saw_Advanced				.getAlmostBroken(1), false, true, false, new Object[]{" SS", "SCS", "BS ", 'C', OrePrefixes.circuit.get(Materials.Advanced), 'B', OrePrefixes.battery.get(Materials.Lithium), 'S', OrePrefixes.plate.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Drill_Advanced			.getAlmostBroken(1), false, true, false, new Object[]{"DDD", "SCS", "SBS", 'C', OrePrefixes.circuit.get(Materials.Elite), 'B', OrePrefixes.battery.get(Materials.Lithium), 'S', OrePrefixes.plate.get(Materials.TungstenSteel), 'D', GT_OreDictNames.craftingIndustrialDiamond});
        GT_ModHandler.addCraftingRecipe(GT_Items.Tool_Screwdriver_Electric	.getAlmostBroken(1), false, true, false, new Object[]{"S  ", " SC", "  B", 'C', OrePrefixes.circuit.get(Materials.Basic), 'B', OrePrefixes.battery.get(Materials.Basic), 'S', OrePrefixes.stick.get(Materials.StainlessSteel)});
        
        GT_Log.log.info("Removing Q-Armor Recipes if configured.");
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "QHelmet"		, false)) GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumHelmet", 1));
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "QPlate"		, false)) GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumBodyarmor", 1));
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "QLegs"		, false)) GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumLeggings", 1));
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "QBoots"		, false)) GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumBoots", 1));
        
        ItemStack plastic = GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Plastic);
        if (GT_ModHandler.getRecipeOutput(plastic, plastic, null, plastic, plastic, null) != null) {
        	RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, plastic, plastic, null, plastic, plastic, null));
        	RecipeHandler.scheduleSmeltingToRemove((in, out) -> GT_OreDictUnificator.isItemStackInstanceOf(in, OrePrefixes.block, Materials.Plastic) && out.stackSize == 4);
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "ProjectRed_RedAlloyCompound", true)) {
	        ItemStack redstone = new ItemStack(Items.redstone), iron = new ItemStack(Items.iron_ingot);
	        RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, redstone, redstone, redstone, redstone, iron, redstone, redstone, redstone, redstone));
        }
        
        if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.disabledrecipes, "ProjectRed_ElectrotineCompound", true)) {
	        ItemStack electrotine = GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Electrotine), iron = new ItemStack(Items.iron_ingot);
	        RecipeHandler.scheduleCraftingToRemove(new RecipeHandler.InventoryRecipeMatcher(false, electrotine, electrotine, electrotine, electrotine, iron, electrotine, electrotine, electrotine, electrotine));
        }
	}
}
