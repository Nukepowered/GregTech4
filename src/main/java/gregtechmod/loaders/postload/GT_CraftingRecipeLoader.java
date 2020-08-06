package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Component;
import gregtechmod.common.items.GT_MetaItem_Dust;
import gregtechmod.common.items.GT_MetaItem_Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_CraftingRecipeLoader implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("GT_Mod: Adding Tool Recipes.");
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(124, 1, 0), false, true, false, new Object[] { "F" ,  "F" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(125, 1, 0), false, true, false, new Object[] {"FFF", " S ", " S ", 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(126, 1, 0), false, true, false, new Object[] { "F" ,  "S" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(127, 1, 0), false, true, false, new Object[] {"FF" , "FS" , " S" , 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(128, 1, 0), false, true, false, new Object[] {"FF" , " S" , " S" , 'S', OrePrefixes.stick.get(Materials.Wood), 'F', new ItemStack(Items.flint, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(129, 1, 0), false, true, false, new Object[] { "F" ,  "F" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(130, 1, 0), false, true, false, new Object[] {"FFF", " S ", " S ", 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(131, 1, 0), false, true, false, new Object[] { "F" ,  "S" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(132, 1, 0), false, true, false, new Object[] {"FF" , "FS" , " S" , 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(133, 1, 0), false, true, false, new Object[] {"FF" , " S" , " S" , 'S', OrePrefixes.stick.get(Materials.Iron), 'F', OrePrefixes.ingot.get(Materials.Steel)});
        
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(134, 1, 0), false, true, false, new Object[] { "F" ,  "F" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(135, 1, 0), false, true, false, new Object[] {"FFF", " S ", " S ", 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(136, 1, 0), false, true, false, new Object[] { "F" ,  "S" ,  "S" , 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(137, 1, 0), false, true, false, new Object[] {"FF" , "FS" , " S" , 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(138, 1, 0), false, true, false, new Object[] {"FF" , " S" , " S" , 'S', OrePrefixes.stick.get(Materials.Steel), 'F', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        
        GT_Log.log.info("GT_Mod: Adding Wool and Color releated Recipes.");
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
		
        GT_Log.log.info("GT_Mod: Adding 'The holy Planks of Sengir'.");
    	ItemStack tStack = GT_MetaItem_Material.instance.getStack(15, 1);
//    	tStack.setItemName("The holy Planks of Sengir");
    	tStack.addEnchantment(Enchantment.smite, 10);
    	GT_ModHandler.addCraftingRecipe(tStack, new Object[] {"XXX", "XDX", "XXX", 'X', new ItemStack(Items.nether_star, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'D', new ItemStack(Blocks.dragon_egg, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    	
    	GT_ModHandler.removeRecipe(new ItemStack[] {GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1)});
    	if (null != GT_ModHandler.removeRecipe(new ItemStack[] {GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1), null, GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Copper, 1), GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Tin, 1)})) {
			GT_Log.log.info("GT_Mod: Changing Forestrys Bronze Recipe");
			GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.ingot.get(Materials.Bronze), GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "bronzeingotcrafting", true)?1:2), new Object[] {OrePrefixes.ingot.get(Materials.Copper), OrePrefixes.ingot.get(Materials.Copper), OrePrefixes.ingot.get(Materials.Copper), OrePrefixes.ingot.get(Materials.Tin)});
		}
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "enchantmenttable", false)) {
			GT_Log.log.info("GT_Mod: Removing the Recipe of the Enchantment Table, to have more Fun at enchanting with the Anvil and Books from Dungeons.");
			GT_ModHandler.removeRecipe(new ItemStack(Blocks.enchanting_table, 1));
		}
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "enderchest", false)) {
			GT_ModHandler.removeRecipe(new ItemStack(Blocks.ender_chest, 1));
		}
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "8DepletedUranium", true)) {
			GT_ModHandler.removeRecipe(new ItemStack[] {GT_ModHandler.getEmptyCell(1), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getIC2Item("uraniumIngot", 1), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getEmptyCell(1), GT_ModHandler.getEmptyCell(1)});
		}
		
        GT_Log.log.info("GT_Mod: Adding Mixed Metal Ingot Recipes.");
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
        
        GT_Log.log.info("GT_Mod: Adding Rolling Machine Recipes.");
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
	    
		GT_Log.log.info("GT_Mod: Beginning to add regular Crafting Recipes.");
		
    	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Material.instance.getStack(60, 2), new Object[] {"S ", " P", 'S', GT_ToolDictNames.craftingToolSaw, 'P', new ItemStack(Blocks.wooden_slab, 1, 0)});
    	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Material.instance.getStack(61, 2), new Object[] {"S ", " P", 'S', GT_ToolDictNames.craftingToolSaw, 'P', new ItemStack(Blocks.wooden_slab, 1, 1)});
    	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Material.instance.getStack(62, 2), new Object[] {"S ", " P", 'S', GT_ToolDictNames.craftingToolSaw, 'P', new ItemStack(Blocks.wooden_slab, 1, 2)});
    	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Material.instance.getStack(63, 2), new Object[] {"S ", " P", 'S', GT_ToolDictNames.craftingToolSaw, 'P', new ItemStack(Blocks.wooden_slab, 1, 3)});
    	
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("scaffold", 4), new Object[] {"WWW", " S ", "S S", 'W', "plankWood", 'S', OrePrefixes.stick.get(Materials.Wood)});
        
		GT_ModHandler.addCraftingRecipe(null, new Object[] {" T ", "T T", " T ", 'T', OrePrefixes.ingot.get(Materials.Tin)});
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getEmptyCell(GregTech_API.sTinCellCountPer4Ingots), new Object[] {" T ", "T T", " T ", 'T', OrePrefixes.plate.get(Materials.Tin)});
		if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "tincellsfromplates", true)) GT_ModHandler.addCraftingRecipe(GT_ModHandler.getEmptyCell(GregTech_API.sTinCellCountPer4Ingots), new Object[] {" T ", "T T", " T ", 'T', OrePrefixes.ingot.get(Materials.Tin)});
		
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "machineblock", true)) {
			GT_ModHandler.addCraftingRecipe(null, new Object[] {"RRR", "R R", "RRR", 'R', OrePrefixes.plate.get(Materials.Iron)});
		}
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("machine", 1, GT_MetaItem_Component.instance.getStack(37, 1)), new Object[] {"RRR", "RWR", "RRR", 'R', OrePrefixes.plate.get(Materials.Iron), 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 32, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium)			, 'W', GT_ToolDictNames.craftingToolWrench});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 33, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Bronze)			, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 34, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Brass)				, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 35, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Steel)				, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 36, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.Titanium)			, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 38, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.TungstenSteel)		, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 39, 1), new Object[] {"PPP", "PWP", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel)	, 'W', GT_ToolDictNames.craftingToolWrench});
		
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 51, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plate.get(Materials.Bronze)			, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 52, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plate.get(Materials.Steel)				, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 53, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plate.get(Materials.Magnalium)			, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 54, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plate.get(Materials.TungstenSteel)		, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 55, 1), new Object[] {" H ", "PPP", " F ", 'P', OrePrefixes.plateAlloy.get("Carbon")				, 'H', GT_ToolDictNames.craftingToolHardHammer, 'F', GT_ToolDictNames.craftingToolFile});
		
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 96, 1), new Object[] {"SPS", "PWP", "SPS", 'P', OrePrefixes.plate.get(Materials.Iron)				, 'S', OrePrefixes.stick.get(Materials.Iron)			, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 97, 1), new Object[] {"SPS", "PWP", "SPS", 'P', OrePrefixes.plate.get(Materials.Bronze)			, 'S', OrePrefixes.stick.get(Materials.Bronze)			, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 98, 1), new Object[] {"SPS", "PWP", "SPS", 'P', OrePrefixes.plate.get(Materials.Steel)				, 'S', OrePrefixes.stick.get(Materials.Steel)			, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 99, 1), new Object[] {"SPS", "PWP", "SPS", 'P', OrePrefixes.plate.get(Materials.Titanium)			, 'S', OrePrefixes.stick.get(Materials.Titanium)		, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(100, 1), new Object[] {"SPS", "PWP", "SPS", 'P', OrePrefixes.plate.get(Materials.TungstenSteel)		, 'S', OrePrefixes.stick.get(Materials.TungstenSteel)	, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(101, 1), new Object[] {"SPS", "PWP", "SPS", 'P', OrePrefixes.plate.get(Materials.Iridium)			, 'S', OrePrefixes.stick.get(Materials.Iridium)			, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(102, 1), new Object[] {"SPS", "PWP", "SPS", 'P', OrePrefixes.plate.get(Materials.StainlessSteel)	, 'S', OrePrefixes.stick.get(Materials.StainlessSteel)	, 'W', GT_ToolDictNames.craftingToolWrench});
        
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2, 1800), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Bronze)			, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2, 1801), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Steel)				, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2, 1802), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel)	, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2, 1803), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.TungstenSteel)		, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1850), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Bronze)			, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1851), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Steel)				, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1852), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel)	, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1853), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.TungstenSteel)		, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2, 1900), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Brass)				, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2, 1901), false, true, new Object[] {"PPP", "W H", "PPP", 'P', OrePrefixes.plate.get(Materials.Electrum)			, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1950), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Brass)				, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 1951), false, true, new Object[] {"PHP", "P P", "PWP", 'P', OrePrefixes.plate.get(Materials.Electrum)			, 'H', GT_ToolDictNames.craftingToolHardHammer, 'W', GT_ToolDictNames.craftingToolWrench});
		
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casinggold"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Gold)		, 'H', GT_ToolDictNames.craftingToolHardHammer});
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casingiron"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'H', GT_ToolDictNames.craftingToolHardHammer});
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casingbronze"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Bronze)		, 'H', GT_ToolDictNames.craftingToolHardHammer});
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casingcopper"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Copper)		, 'H', GT_ToolDictNames.craftingToolHardHammer});
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casingtin"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Tin)		, 'H', GT_ToolDictNames.craftingToolHardHammer});
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("casinglead"	, 1), new Object[] {"H P", 'P', OrePrefixes.plate.get(Materials.Lead)		, 'H', GT_ToolDictNames.craftingToolHardHammer});
		
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(80, 1, 0), new Object[] {"TTT", "TBT", "TTT", 'T', "craftingTurbineBladeBronze"		, 'B', OrePrefixes.block.get(Materials.Bronze)});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(81, 1, 0), new Object[] {"TTT", "TBT", "TTT", 'T', "craftingTurbineBladeSteel"			, 'B', OrePrefixes.block.get(Materials.Steel)});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(82, 1, 0), new Object[] {"TTT", "TBT", "TTT", 'T', "craftingTurbineBladeMagnalium"		, 'B', OrePrefixes.block.get(Materials.Iron)});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(83, 1, 0), new Object[] {"TTT", "TBT", "TTT", 'T', "craftingTurbineBladeTungstenSteel"	, 'B', OrePrefixes.block.get(Materials.Steel)});
		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(84, 1, 0), new Object[] {"TTT", "TBT", "TTT", 'T', "craftingTurbineBladeCarbon"		, 'B', OrePrefixes.block.get(Materials.Iron)});
		
		GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(80, 1, 0), new Object[] {GregTech_API.getGregTechItem(80, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ToolDictNames.craftingToolHardHammer, GT_ToolDictNames.craftingToolWrench, GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(81, 1, 0), new Object[] {GregTech_API.getGregTechItem(81, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ToolDictNames.craftingToolHardHammer, GT_ToolDictNames.craftingToolWrench, GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(82, 1, 0), new Object[] {GregTech_API.getGregTechItem(82, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ToolDictNames.craftingToolHardHammer, GT_ToolDictNames.craftingToolWrench, GT_ToolDictNames.craftingToolFile});
		GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(83, 1, 0), new Object[] {GregTech_API.getGregTechItem(83, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ToolDictNames.craftingToolHardHammer, GT_ToolDictNames.craftingToolWrench, GT_ToolDictNames.craftingToolFile});
		
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Bronze)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Aluminium)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Steel)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(Blocks.piston, 1), new Object[] {"WWW", "CBC", "CRC", 'W', "plankWood", 'C', "stoneCobble", 'R', OrePrefixes.dust.get(Materials.Redstone), 'B', OrePrefixes.ingot.get(Materials.Titanium)});
    	
    	GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(37, 1, 0), new Object[] {"LLL", "LIL", "LLL", 'L', "crafting10kkEUStore", 'I', OrePrefixes.plateAlloy.get("Iridium")});
    	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(0, 4), new Object[] {"AWA", "LIL", "AWA", 'L', "crafting10kkEUStore", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'A', "craftingCircuitTier04", 'W', OrePrefixes.plate.get(Materials.Tungsten)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(1, 4), new Object[] {"AEA", "EIE", "AEA", 'E', "craftingCircuitTier05", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'A', "craftingCircuitTier04"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), new Object[] {"CCC", "WIW", "LLL", 'L', "craftingCircuitTier07", 'W', OrePrefixes.plate.get(Materials.Tungsten), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'C', GregTech_API.getGregTechItem(34, 1, 0)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), new Object[] {"CCC", "WIW", "LLL", 'L', "craftingCircuitTier07", 'W', OrePrefixes.plate.get(Materials.Tungsten), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'C', GregTech_API.getGregTechItem(60, 1, 0)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(2, 4), new Object[] {"CCC", "WIW", "LLL", 'L', "craftingCircuitTier07", 'W', OrePrefixes.plate.get(Materials.Tungsten), 'I', OrePrefixes.plateAlloy.get("Iridium"), 'C', GT_ModHandler.getIC2Item("reactorCoolantSix", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(43, 1, 0), new Object[] {GregTech_API.getGregTechItem(43, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(4, 1), new Object[] {"AGA", "RPB", "ALA", 'A', OrePrefixes.plate.get(Materials.Aluminium), 'L', OrePrefixes.dust.get(Materials.Glowstone), 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue", 'P', new ItemStack(Blocks.glass_pane, 1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(5, 1), new Object[] {"GGG", "AAA", "CBC", 'A', OrePrefixes.plate.get(Materials.Aluminium), 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'G', new ItemStack(Blocks.glass, 1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(5, 1), new Object[] {"GGG", "RRR", "CBC", 'R', OrePrefixes.plate.get(Materials.Iron), 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'G', new ItemStack(Blocks.glass, 1)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(17, 4), new Object[] {"DSD", "S S", "DSD", 'D', OrePrefixes.dust.get(Materials.Diamond), 'S', OrePrefixes.plate.get(Materials.StainlessSteel)});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(18, 2), new Object[] {"DSD", "SGS", "DSD", 'G', "craftingIndustrialDiamond", 'D', OrePrefixes.dust.get(Materials.Diamond), 'S', "craftingPlateSteel"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(23, 2), new Object[] {"TST", "SBS", "TST", 'B', "blockSteel", 'T', OrePrefixes.plate.get(Materials.Tungsten), 'S', "craftingPlateSteel"});
        GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(16, 4), new Object[] {"CCC", "RRR", "SSS", 'C', GT_ModHandler.getIC2Item("carbonMesh", 1), 'R', "itemRubber", 'S', GT_ModHandler.getIC2Item("resin", 1)});
        
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "SolarPanel"  , true )) GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack( 7, 1), new Object[] {"SGS", "CPC", 'C', "craftingCircuitTier02", 'G', new ItemStack(Blocks.glass_pane, 1), 'P', OrePrefixes.plateAlloy.get("Carbon"), 'S', OrePrefixes.plate.get(Materials.Silicon)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "SolarPanelLV", false)) GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(65, 1), new Object[] {"SSS", "STS", "SSS", 'S', "craftingSolarPanel"  , 'T', "craftingCircuitTier02"});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "SolarPanelMV", false)) GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(66, 1), new Object[] {"SSS", "STS", "SSS", 'S', "craftingSolarPanelLV", 'T', "craftingMVTUpgrade"});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "SolarPanelHV", false)) GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(67, 1), new Object[] {"SSS", "STS", "SSS", 'S', "craftingSolarPanelMV", 'T', "craftingHVTUpgrade"});
        /*
        GT_ModHandler.addCraftingRecipe(null, new Object[] {"CPC"				, 'C', GT_ModHandler.getIC2Item("reactorUraniumSimple", 1), 'P', GT_ModHandler.getIC2Item("denseCopperPlate", 1)});
        GT_ModHandler.addCraftingRecipe(null, new Object[] {" C ", "PPP", " C "	, 'C', GT_ModHandler.getIC2Item("reactorUraniumDual", 1)  , 'P', GT_ModHandler.getIC2Item("denseCopperPlate", 1)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(49, 1, 0)				, new Object[] {"CPC"				, 'C', GregTech_API.getGregTechItem(48, 1, 0), 'P', OrePrefixes.plate.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(50, 1, 0)				, new Object[] {" C ", "PPP", " C "	, 'C', GregTech_API.getGregTechItem(49, 1, 0), 'P', OrePrefixes.plate.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(52, 1, 0)				, new Object[] {"CPC"				, 'C', GregTech_API.getGregTechItem(51, 1, 0), 'P', OrePrefixes.plate.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(53, 1, 0)				, new Object[] {" C ", "PPP", " C "	, 'C', GregTech_API.getGregTechItem(52, 1, 0), 'P', OrePrefixes.plate.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorUraniumDual", 1)	, new Object[] {"CPC"				, 'C', GT_ModHandler.getIC2Item("reactorUraniumSimple", 1), 'P', OrePrefixes.plate.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorUraniumQuad", 1)	, new Object[] {" C ", "PPP", " C "	, 'C', GT_ModHandler.getIC2Item("reactorUraniumDual", 1)  , 'P', OrePrefixes.plate.get(Materials.Copper)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(49, 1, 0)				, new Object[] {"CPC"				, 'C', GregTech_API.getGregTechItem(48, 1, 0), 'P', OrePrefixes.plate.get(Materials.Lead)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(50, 1, 0)				, new Object[] {" C ", "PPP", " C "	, 'C', GregTech_API.getGregTechItem(49, 1, 0), 'P', OrePrefixes.plate.get(Materials.Lead)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(52, 1, 0)				, new Object[] {"CPC"				, 'C', GregTech_API.getGregTechItem(51, 1, 0), 'P', OrePrefixes.plate.get(Materials.Lead)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(53, 1, 0)				, new Object[] {" C ", "PPP", " C "	, 'C', GregTech_API.getGregTechItem(52, 1, 0), 'P', OrePrefixes.plate.get(Materials.Lead)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorUraniumDual", 1)	, new Object[] {"CPC"				, 'C', GT_ModHandler.getIC2Item("reactorUraniumSimple", 1), 'P', OrePrefixes.plate.get(Materials.Lead)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorUraniumQuad", 1)	, new Object[] {" C ", "PPP", " C "	, 'C', GT_ModHandler.getIC2Item("reactorUraniumDual", 1)  , 'P', OrePrefixes.plate.get(Materials.Lead)});
        */
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorVent", 1), new Object[] {"AIA", "I I", "AIA", 'I', new ItemStack(Items.iron_ingot, 1), 'A', OrePrefixes.plate.get(Materials.Aluminium)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("reactorPlatingExplosive", 1), new Object[] {GT_ModHandler.getIC2Item("reactorPlating", 1), OrePrefixes.plate.get(Materials.Lead)});
        
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "wirelessRedstone", true)) {
        	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(82, 1), new Object[] {"RAR", "QEQ", "CPC", 'A', "craftingCircuitTier04", 'R', OrePrefixes.dust.get(Materials.Redstone), 'Q', "craftingQuartz", 'P', OrePrefixes.plate.get(Materials.Gold)	, 'E', new ItemStack(Items.ender_pearl, 1), 'C', new ItemStack(Items.comparator, 1)});
        	GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(84, 1), new Object[] {"RAR", "QEQ", "CPC", 'A', "craftingCircuitTier04", 'R', "craftingRedstoneTorch"	, 'Q', "craftingQuartz", 'P', OrePrefixes.plate.get(Materials.Silver)	, 'E', new ItemStack(Items.ender_pearl, 1), 'C', new ItemStack(Items.comparator, 1)});
        }
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Component.instance.getStack(82, 1), new Object[] {GT_MetaItem_Component.instance.getStack(83, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Component.instance.getStack(83, 1), new Object[] {GT_MetaItem_Component.instance.getStack(82, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Component.instance.getStack(84, 1), new Object[] {GT_MetaItem_Component.instance.getStack(85, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Component.instance.getStack(85, 1), new Object[] {GT_MetaItem_Component.instance.getStack(84, 1)});
        
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 1,10), new Object[] {"CTC", "TMT", "CTC", 'C', OrePrefixes.plate.get(Materials.Chrome)  		, 'T', OrePrefixes.plate.get(Materials.Titanium)			, 'M', "craftingRawMachineTier02"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 4,13), new Object[] {"AAA", "CMC", "AAA", 'C', "craftingCircuitTier02", 'A', OrePrefixes.plate.get(Materials.Iron)				, 'M', "craftingRawMachineTier01"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 4,13), new Object[] {"AAA", "CMC", "AAA", 'C', "craftingCircuitTier02", 'A', OrePrefixes.plate.get(Materials.Aluminium)			, 'M', "craftingRawMachineTier01"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 4,14), new Object[] {"SSS", "CMC", "SSS", 'C', "craftingCircuitTier04", 'S', "craftingPlateSteel"		, 'M', "craftingRawMachineTier02"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 4,15), new Object[] {"TTT", "CMC", "TTT", 'C', "craftingCircuitTier06", 'T', OrePrefixes.plate.get(Materials.Chrome)			, 'M', "craftingRawMachineTier04"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[4], 2,13), new Object[] {"PPP", "BHB", "PPP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'H', GT_ToolDictNames.craftingToolHardHammer});
    	
    	boolean tNeedsSteel = GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "steelForIC2Machines", true);
    	
    	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("generator", 1));
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {"B"  , "M"  , "F"  , 'B', "crafting10kEUStore", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier00", 'F', new ItemStack(Blocks.furnace, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    	if (!tNeedsSteel) {
	    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {" B ", "RRR", " F ", 'B', "crafting10kEUStore", 'R', OrePrefixes.plate.get(Materials.Iron)		, 'F', "craftingIronFurnace"});
	    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {" B ", "RRR", " F ", 'B', "crafting10kEUStore", 'R', OrePrefixes.plate.get(Materials.Aluminium)  	, 'F', "craftingIronFurnace"});
	    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("generator", 1), new Object[] {" B ", "RRR", " F ", 'B', "crafting10kEUStore", 'R', OrePrefixes.plate.get(Materials.Invar)      	, 'F', "craftingIronFurnace"});
    	}
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "tesseracts", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 84), new Object[] {"TCT", "CEC", "TMT", 'C', "craftingCircuitTier07", 'M', "craftingCircuitTier10"		, 'E', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "enderchest", false)?new ItemStack(Items.ender_eye, 1):"craftingEnderChest", 'T', OrePrefixes.plate.get(Materials.Titanium)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 85), new Object[] {"TCT", "CEC", "TMT", 'C', "craftingCircuitTier06", 'M', "craftingRawMachineTier02"	, 'E', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "enderchest", false)?new ItemStack(Items.ender_eye, 1):"craftingEnderChest", 'T', OrePrefixes.plate.get(Materials.Titanium)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "fusionreactor", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 80), new Object[] {"CCC", "PHP", "CCC", 'C', "craftingCircuitTier07", 'P', "craftingCircuitTier10", 'H', new ItemStack(GregTech_API.sBlockList[0], 1,  1)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 81), new Object[] {"SCS", "CHC", "SCS", 'C', "craftingCircuitTier07", 'H', new ItemStack(GregTech_API.sBlockList[1], 1,103), 'S', "craftingSuperconductor"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 82), new Object[] {"PWP", "CMC", "PCP", 'C', "craftingCircuitTier07", 'M', "craftingRawMachineTier04", 'W', new ItemStack(Blocks.chest, 1), 'P', "craftingPump"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 83), new Object[] {"PCP", "CMC", "PWP", 'C', "craftingCircuitTier07", 'M', "craftingRawMachineTier04", 'W', new ItemStack(Blocks.chest, 1), 'P', "craftingPump"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 1,  1), new Object[] {"ESE", "CMC", "EIE", 'I', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "iridiumreflector", true)?GregTech_API.getGregTechItem(40, 1, 0):OrePrefixes.plateAlloy.get("Iridium"), 'S', "craftingSuperconductor", 'E', "craftingCircuitTier07", 'C', "craftingHeatingCoilTier02", 'M', "craftingRawMachineTier04"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "lightningrod", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,102), new Object[] {"IAI", "AMA", "IAI", 'I', "craftingCircuitTier07", 'A', "craftingRawMachineTier04", 'M', new ItemStack(GregTech_API.sBlockList[1], 1,103)});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 4), new Object[] {"CGD", "GAG", "DGC", 'D', "craftingCircuitTier07", 'G', "craftingMonitorTier02", 'C', "craftingCircuitTier08", 'A', "craftingRawMachineTier02"});
    	
    	//if (GregTech_API.sConfiguration.addAdvConfig(GT_Config_Category.gregtechrecipes, "lesu", true)) {
    		//GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 1, 6), new Object[] {"LLL", "LML", "LLL", 'M', "craftingCircuitTier02", 'L', "blockLazurite"});
    		//GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[0], 1, 6), new Object[] {"LLL", "LML", "LLL", 'M', "craftingCircuitTier02", 'L', "blockLapis"});
    		//GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 7), new Object[] {" L ", "ACA", " M ", 'A', "craftingCircuitTier04", 'C', new ItemStack(GregTech_API.sBlockList[0], 1, 6), 'L', GT_ModHandler.getIC2Item("lvTransformer", 1), 'M', GT_ModHandler.getIC2Item("mvTransformer", 1)});
    	//}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "idsu", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,101), new Object[] {"IMI", "MCM", "IMI", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'C', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "enderchest", false)?new ItemStack(Items.ender_eye, 1):"craftingEnderChest", 'M', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "aesu", true)?new ItemStack(GregTech_API.sBlockList[1], 1, 100):"crafting100kkEUStore"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "aesu", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,100), new Object[] {"CQC", "CEC", "CMC", 'Q', "craftingCircuitTier10", 'C', "craftingCircuitTier07", 'E', "crafting100kkEUStore", 'M', "craftingRawMachineTier04"});
    	}
    	//if (GregTech_API.sConfiguration.addAdvConfig(GT_Config_Category.gregtechrecipes, "chargeomat", true)) {
    		//GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,10), new Object[] {"BCB", "TMT", "BAB", 'C', "craftingCircuitTier10", 'M', "crafting100kkEUStore", 'A', "craftingRawMachineTier02", 'T', new ItemStack(Block.chest, 1), 'B', "craftingCircuitTier07"});
    	//}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "digitalchest", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,48), new Object[] {"AAA", "ADA", "ASA", 'D', "craftingCircuitTier08", 'S', "craftingMonitorTier02", 'A', OrePrefixes.plate.get(Materials.Iron)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,48), new Object[] {"AAA", "ADA", "ASA", 'D', "craftingCircuitTier08", 'S', "craftingMonitorTier02", 'A', OrePrefixes.plate.get(Materials.Aluminium)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,48), new Object[] {"AAA", "ADA", "ASA", 'D', "craftingCircuitTier08", 'S', "craftingMonitorTier02", 'A', "craftingPlateSteel"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "quantumchest", true)) {
            GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,49), new Object[] {"ASA", "BTB", "ADA", 'A', "craftingCircuitTier08", 'S', "craftingMonitorTier02", 'D', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "digitalchest", true)?new ItemStack(GregTech_API.sBlockList[1], 1,48):"craftingCircuitTier07", 'T', "craftingTeleporter", 'B', "craftingRawMachineTier04"});
    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,49), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1, 3)});
            GT_ModHandler.addCraftingRecipe(GT_MetaItem_Component.instance.getStack(29, 1), new Object[] {"ASA", "BTB", "ADA", 'A', "craftingCircuitTier08", 'S', "craftingMonitorTier02", 'D', OrePrefixes.plate.get(Materials.Aluminium), 'T', "craftingTeleporter", 'B', "craftingRawMachineTier04"});
    		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "quantumtank", true)) GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,30), new Object[] {"APA", "PQP", "APA", 'A', "craftingCircuitTier07", 'P', OrePrefixes.plate.get(Materials.Platinum), 'L', "craftingCircuitTier07", 'Q', new ItemStack(GregTech_API.sBlockList[1], 1, 49)});
    	} else {
    		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "quantumtank", true)) GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,30), new Object[] {"ASA", "BTB", "ALA", 'A', "craftingCircuitTier08", 'S', "craftingMonitorTier02", 'L', "craftingCircuitTier07", 'T', "craftingTeleporter", 'B', "craftingRawMachineTier04"});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 86), new Object[] {"WBW", "WCW", "PMP", 'C', "craftingCircuitTier02", 'M', "craftingRawMachineTier01", 'W', "craftingWireCopper", 'B', "crafting10kEUStore", 'P', OrePrefixes.plate.get(Materials.Iron)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 86), new Object[] {"WBW", "WCW", "PMP", 'C', "craftingCircuitTier02", 'M', "craftingRawMachineTier01", 'W', "craftingWireCopper", 'B', "crafting10kEUStore", 'P', OrePrefixes.plate.get(Materials.Aluminium)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 87), new Object[] {"TCT", "CMC"       , 'C', "craftingChest", 'M', new ItemStack(GregTech_API.sBlockList[1], 1, 86), 'T', "craftingCircuitTier02"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 88), new Object[] {"CCC", "CMC", "TTT", 'C', "craftingChest", 'M', new ItemStack(GregTech_API.sBlockList[1], 1, 87), 'T', "craftingCircuitTier02"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 89), new Object[] {"CCC", "CMC", "CTC", 'C', "craftingChest", 'M', new ItemStack(GregTech_API.sBlockList[1], 1, 88), 'T', "craftingCircuitTier04"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 62), new Object[] {"RCR", "AEA", "RCR", 'C', "craftingCircuitTier04", 'A', "craftingRawMachineTier02", 'E', "craftingExtractor", 'R', OrePrefixes.plate.get(Materials.StainlessSteel)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 25), new Object[] {"RDR", "CEC", "RMR", 'E', GT_ModHandler.getIC2Item("electrolyzer", 1), 'M', "craftingElectromagnet", 'D', "craftingExtractor", 'R', "craftingPlateSteel", 'C', "craftingCircuitTier04"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 38), new Object[] {"RPR", "CGC", "RPR", 'C', "craftingCircuitTier04", 'P', "craftingPump", 'G', "glassReinforced", 'R', OrePrefixes.plate.get(Materials.StainlessSteel)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 41), new Object[] {"RMR", "CEC", "RDR", 'M', "craftingElectromagnet", 'D', "craftingExtractor", 'E', "craftingCompressor", 'R', OrePrefixes.plate.get(Materials.Invar), 'C', "craftingCircuitTier04"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 41), new Object[] {"RMR", "CEC", "RDR", 'M', "craftingElectromagnet", 'D', "craftingExtractor", 'E', "craftingCompressor", 'R', OrePrefixes.plate.get(Materials.Aluminium), 'C', "craftingCircuitTier04"});
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "superconductorwire", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 4,12), new Object[] {"ALA", "CCC", "ALA", 'C', "craftingSuperconductor", 'A', "craftingRawMachineTier02", 'L', "craftingCircuitTier07"});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,103), new Object[] {"LEL", "CAC", "LEL", 'C', "craftingSuperconductor", 'A', "craftingRawMachineTier04", 'L', "craftingCircuitTier07", 'E', "crafting100kkEUStore"});
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "playerdetector", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 13), new Object[] {" E ", "ACA", " E ", 'C', "craftingCircuitTier10", 'A', "craftingCircuitTier04", 'E', "craftingCircuitTier05"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "massfabricator", true)) {
    		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("massFabricator", 1));
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("massFabricator", 1), new Object[] {"ETE", "ALA", "ETE", 'L', "crafting100kkEUStore", 'A', "craftingRawMachineTier04", 'E', "craftingCircuitTier07", 'T', "craftingTeleporter"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "sonictron", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,  6), new Object[] {"CRC", "NAN", "CJC", 'C', "craftingCircuitTier02", 'N', new ItemStack(Blocks.noteblock, 1), 'A', "craftingRawMachineTier02", 'J', new ItemStack(Blocks.jukebox, 1), 'R', "itemRecord"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "electricautocraftingtable", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 16), new Object[] {"GBG", "CTC", "GAG", 'B', "crafting10kEUStore", 'A', "craftingRawMachineTier02"	, 'C', "craftingCircuitTier04", 'T', "craftingWorkBench", 'G', OrePrefixes.plate.get(Materials.Electrum)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "advancedpump", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 47), new Object[] {"CPC", "PMP", "CPC", 'C', "craftingCircuitTier04", 'M', "craftingRawMachineTier02"	, 'P', "craftingPump"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "wiremill", true)) {
    		tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {null, new ItemStack(Items.diamond, 1), null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.iron_ingot, 1), null});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 55), new Object[] {"BDB", "CMC", "BQB", 'M', "craftingRawMachineTier02", 'C', "craftingCircuitTier02", 'B', OrePrefixes.plate.get(Materials.Brass), 'D', tStack==null?OrePrefixes.gem.get(Materials.Diamond):tStack, 'Q', "craftingConveyor"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "alloysmelter", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1, 56), new Object[] {"IHI", "CEC", "IQI", 'H', "craftingHeatingCoilTier00", 'C', "craftingCircuitTier02", 'E', "craftingElectricFurnace", 'I', OrePrefixes.plate.get(Materials.Invar), 'Q', "craftingConveyor"});
        }
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "lathe", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,111), new Object[] {"PCP", "GQG", "PMP", 'M', "craftingRawMachineTier02", 'C', "craftingCircuitTier04", 'G', "craftingGearTier02", 'P', "craftingPlateSteel", 'Q', "craftingConveyor"});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,113), new Object[] {"PCP", "GSG", "PMP", 'M', "craftingRawMachineTier02", 'C', "craftingCircuitTier04", 'G', "craftingGearTier02", 'P', "craftingPlateSteel", 'S', "craftingDiamondBlade"});
    	
		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("canner", 1));
		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("macerator", 1));
		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("extractor", 1));
		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("compressor", 1));
		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("recycler", 1));
		GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("electroFurnace", 1));
		
		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("canner", 1), new Object[] {"PCP", "PMP", "PPP", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'C', "craftingCircuitTier02", 'P', OrePrefixes.plate.get(Materials.Tin)});
		
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "automaticmachines", true)) {
        	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "allowVanillaIC2MachinesFromAutomaticOnes", false)) {
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
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,51), new Object[] {"TMT", "TCT",        'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'C', "craftingCircuitTier02", 'T', GT_ModHandler.getIC2Item("treetap", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,52), new Object[] {"S S", "SMS", "SCS", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'C', "craftingCircuitTier02", 'S', "stoneSmooth"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,53), new Object[] {" G ", "DMD", "RDR", 'M', "craftingCompressor", 'G', OrePrefixes.dust.get(Materials.Glowstone), 'R', tNeedsSteel?"craftingPlateSteel":OrePrefixes.plate.get(Materials.Iron), 'D', new ItemStack(Blocks.dirt, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,54), new Object[] {" C ", "RMR"       , 'M', tNeedsSteel?new ItemStack(GregTech_API.sBlockList[1], 1,138):"craftingIronFurnace", 'C', "craftingCircuitTier02", 'R', OrePrefixes.dust.get(Materials.Redstone)});
    		
            if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "macerator", true)) {
            	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {"FDF", "DMD", "FAF", 'F', tNeedsSteel?"craftingPlateSteel":new ItemStack(Items.flint, 1), 'A', "craftingCircuitTier04", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'D', OrePrefixes.gem.get(Materials.Diamond)});
            	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {"FDF", "DMD", "FAF", 'F', tNeedsSteel?"craftingPlateSteel":new ItemStack(Items.flint, 1), 'A', "craftingCircuitTier04", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'D', OrePrefixes.dust.get(Materials.Diamond)});
            	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {"FDF", "AMA", "FAF", 'F', tNeedsSteel?"craftingPlateSteel":new ItemStack(Items.flint, 1), 'A', "craftingCircuitTier02", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'D', "craftingGrinder"});
            } else {
            	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,50), new Object[] {"FFF", "SMS", " C ", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'C', "craftingCircuitTier02", 'F', new ItemStack(Items.flint, 1), 'S', "stoneCobble"});
            }
    	} else {
        	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "allowVanillaIC2MachinesFromAutomaticOnes", false)) {
        		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,50)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("extractor", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,51)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("compressor", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,52)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("recycler", 1)		, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,53)});
	    		GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("electroFurnace", 1)	, new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,54)});
        	}
        	
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("extractor"		, 1), new Object[] {"TMT", "TCT",        'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'C', "craftingCircuitTier02", 'T', GT_ModHandler.getIC2Item("treetap", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("compressor"		, 1), new Object[] {"S S", "SMS", "SCS", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'C', "craftingCircuitTier02", 'S', "stoneSmooth"});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("recycler"			, 1), new Object[] {" G ", "DMD", "RDR", 'M', "craftingCompressor", 'G', OrePrefixes.dust.get(Materials.Glowstone), 'R', tNeedsSteel?"craftingPlateSteel":OrePrefixes.plate.get(Materials.Iron), 'D', new ItemStack(Blocks.dirt, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electroFurnace"	, 1), new Object[] {" C ", "RMR"       , 'M', tNeedsSteel?new ItemStack(GregTech_API.sBlockList[1], 1,138):"craftingIronFurnace", 'C', "craftingCircuitTier02", 'R', OrePrefixes.dust.get(Materials.Redstone)});
    		
            if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "macerator", true)) {
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FDF", "DMD", "FAF", 'F', tNeedsSteel?"craftingPlateSteel":new ItemStack(Items.flint, 1), 'A', "craftingCircuitTier04", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'D', OrePrefixes.gem.get(Materials.Diamond)});
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FDF", "DMD", "FAF", 'F', tNeedsSteel?"craftingPlateSteel":new ItemStack(Items.flint, 1), 'A', "craftingCircuitTier04", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'D', OrePrefixes.dust.get(Materials.Diamond)});
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FDF", "AMA", "FAF", 'F', tNeedsSteel?"craftingPlateSteel":new ItemStack(Items.flint, 1), 'A', "craftingCircuitTier02", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'D', "craftingGrinder"});
            } else {
            	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("macerator", 1), new Object[] {"FFF", "SMS", " C ", 'M', tNeedsSteel?"craftingRawMachineTier02":"craftingRawMachineTier01", 'C', "craftingCircuitTier02", 'F', new ItemStack(Items.flint, 1), 'S', "stoneCobble"});
            }
    	}
    	
		if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("inductionFurnace", 1))) GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("inductionFurnace", 1), new Object[] {"CCC", "CFC", "CMC", 'M', "craftingRawMachineTier02", 'F', "craftingElectricFurnace", 'C', OrePrefixes.ingot.get(Materials.Copper)});
		
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,57), new Object[] {"craftingConveyor", GT_ModHandler.getIC2Item("canner", 1)});
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "microwaveoven", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,63), new Object[] {"AAA", "L M", "AAA", 'A', OrePrefixes.plate.get(Materials.Aluminium), 'L', OrePrefixes.plate.get(Materials.Lead), 'M', "craftingElectromagnet"});
        }
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "universalmacerator", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,64), new Object[] {"SGS", "SPS", "SBS", 'S', OrePrefixes.plate.get(Materials.Titanium), 'B', "craftingRawMachineTier03", 'P', "craftingMacerator", 'G', "craftingGrinder"});
        }
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "teleporter", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,90), new Object[] {"ETE", "TAT", "ELE", 'L', "crafting100kkEUStore", 'A', "craftingRawMachineTier04", 'E', "craftingCircuitTier07", 'T', "craftingTeleporter"});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,59), new Object[] {"PCP", "RQR", "PCP", 'C', "craftingCircuitTier02", 'P', "craftingPiston", 'R', "craftingCompressor", 'Q', "craftingConveyor"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,60), new Object[] {"CPC", "RQR", "CRC", 'C', "craftingCircuitTier02", 'P', "craftingPiston", 'R', "craftingPlateSteel", 'Q', "craftingConveyor"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,61), new Object[] {"RPR", "CQC", "RRR", 'C', "craftingCircuitTier02", 'P', "craftingPiston", 'R', "craftingPlateSteel", 'Q', "craftingConveyor"});
        
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "automation", true)) {
    		// Translocators
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,17), new Object[] {"GBG", "CPC", "GAG", 'B', "crafting10kEUStore", 'A', "craftingRawMachineTier00"	, 'C', "craftingCircuitTier02", 'G', OrePrefixes.plate.get(Materials.Electrum), 'P', "craftingConveyor"});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,20), new Object[] {"GBG", "CPC", "GAG", 'B', "crafting10kEUStore", 'A', "craftingRawMachineTier02"	, 'C', "craftingCircuitTier04", 'G', OrePrefixes.plate.get(Materials.Electrum), 'P', "craftingConveyor"});
    		
    		// Electric Sorters
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,23), new Object[] {"GBG", "CHC", "GAG", 'B', "crafting10kEUStore", 'A', "craftingMonitorTier02"		, 'C', "craftingCircuitTier02", 'G', OrePrefixes.plate.get(Materials.Electrum), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,17)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,58), new Object[] {"GBG", "CHC", "GAG", 'B', "crafting10kEUStore", 'A', "craftingMonitorTier02"		, 'C', "craftingCircuitTier04", 'G', OrePrefixes.plate.get(Materials.Electrum), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,17)});
    		
    		// Adv. Buffer
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,21), new Object[] {"GBG", "CHC", "GAG", 'B', "crafting10kEUStore", 'A', "craftingMonitorTier02"		, 'C', "craftingCircuitTier04", 'G', OrePrefixes.plate.get(Materials.Electrum), 'H', new ItemStack(GregTech_API.sBlockList[1], 1,18)});
    		
    		// Electric Regulator
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,39), new Object[] {"CHC", "HAH", "CHC", 'A', "craftingMonitorTier02", 'C', "craftingCircuitTier04", 'H', new ItemStack(GregTech_API.sBlockList[1], 1,21)});
    		
    		// large Electric Buffer
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,19), new Object[] {"GBG", "CBC", "GBG", 'C', "craftingCircuitTier04", 'G', OrePrefixes.plate.get(Materials.Electrum), 'B', "craftingConveyor"});
    		
    		// Inventory Manager
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,46), new Object[] {"CRC", "SMS", "CEC", 'M', "craftingCircuitTier10", 'C', "craftingCircuitTier05", 'R', new ItemStack(GregTech_API.sBlockList[1], 1,39), 'S', new ItemStack(GregTech_API.sBlockList[1], 1,23), 'E', "craftingLiBattery"});
    		
    		// Large and Small Buffer crafting Recipes
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,19), new Object[] {" T ", "TCT", " T ", 'T', new ItemStack(GregTech_API.sBlockList[1], 1, 18)		, 'C', "craftingCircuitTier04"});
    		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 4,18), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1, 19)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "itemclearer", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,24), new Object[] {"GBG", "PMH", "GCG", 'B', "crafting10kEUStore", 'M', "craftingRawMachineTier02"	, 'C', "craftingCircuitTier02", 'G', OrePrefixes.plate.get(Materials.Electrum), 'P', "craftingTeleporter", 'H', new ItemStack(GregTech_API.sBlockList[1], 1,18)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "rockbreaker", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', OrePrefixes.gem.get(Materials.Diamond), 'A', "craftingRawMachineTier02"	, 'C', "craftingCircuitTier02", 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'O', OrePrefixes.plate.get(Materials.Invar)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', OrePrefixes.dust.get(Materials.Diamond), 'A', "craftingRawMachineTier02"	, 'C', "craftingCircuitTier02", 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'O', OrePrefixes.plate.get(Materials.Aluminium)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', OrePrefixes.gem.get(Materials.Diamond), 'A', "craftingRawMachineTier02"	, 'C', "craftingCircuitTier02", 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'O', OrePrefixes.plate.get(Materials.Invar)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,22), new Object[] {"ODO", "CMC", "OAO", 'D', OrePrefixes.dust.get(Materials.Diamond), 'A', "craftingRawMachineTier02"	, 'C', "craftingCircuitTier02", 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'O', OrePrefixes.plate.get(Materials.Aluminium)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "cropharvestor", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,26), new Object[] {"GHG", "DPM", "GCG", 'D', "craftingDiamondBlade", 'M', "craftingRawMachineTier02", 'C', "craftingCircuitTier02", 'G', OrePrefixes.plate.get(Materials.Electrum), 'P', "craftingPiston", 'H', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "itemclearer", true)?new ItemStack(GregTech_API.sBlockList[1], 1,24):new ItemStack(GregTech_API.sBlockList[1], 1,18)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "scrapboxinator", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,27), new Object[] {"GHG", "DPM", "GCG", 'P', new ItemStack(Blocks.wooden_pressure_plate, 1), 'M', "craftingRawMachineTier02"	, 'C', "craftingCircuitTier02", 'G', OrePrefixes.plate.get(Materials.Electrum), 'D', new ItemStack(Blocks.dispenser, 1), 'H', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "itemclearer", true)?new ItemStack(GregTech_API.sBlockList[1], 1,24):new ItemStack(GregTech_API.sBlockList[1], 1,18)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "advancedsafe", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,45), new Object[] {"C", "S", "B", 'C', "craftingCircuitTier04", 'S', GT_ModHandler.getIC2Item("personalSafe", 1), 'B', new ItemStack(GregTech_API.sBlockList[1], 1,18)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "industrialgrinder", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,28), new Object[] {"EAP", "GGG", "ABA", 'B', "craftingRawMachineTier02", 'A', "craftingCircuitTier04", 'G', "craftingGrinder", 'E', new ItemStack(GregTech_API.sBlockList[1], 1,25), 'P', "craftingPump"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "distillationtower", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,44), new Object[] {"CAC", "PBP", "EAE", 'B', "craftingRawMachineTier04", 'A', "craftingCircuitTier07", 'C', "craftingCentrifuge", 'E', new ItemStack(GregTech_API.sBlockList[1], 1,25), 'P', "craftingPump"});
        }
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "sawmill", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,32), new Object[] {"PAP", "DDD", "ABA", 'B', "craftingRawMachineTier02", 'A', "craftingCircuitTier04", 'D', "craftingDiamondBlade", 'P', "craftingPump"});
        }
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,33), new Object[] {"XXX", "X X", "CGC", 'X', OrePrefixes.plate.get(Materials.Iron), 'C', "craftingCircuitTier02", 'G', "craftingGenerator"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,33), new Object[] {"XXX", "X X", "CGC", 'X', OrePrefixes.plate.get(Materials.Aluminium), 'C', "craftingCircuitTier02", 'G', "craftingGenerator"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,34), new Object[] {"XCX", "WPW", "XCX", 'X', OrePrefixes.plate.get(Materials.Invar), 'C', "craftingCircuitTier04", 'W', GT_ModHandler.getIC2Item("windMill", 1), 'P', "glassReinforced"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,34), new Object[] {"XCX", "WPW", "XCX", 'X', OrePrefixes.plate.get(Materials.Aluminium), 'C', "craftingCircuitTier04", 'W', GT_ModHandler.getIC2Item("windMill", 1), 'P', "glassReinforced"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,35), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.Invar), 'C', "craftingCircuitTier02", 'G', "craftingGeothermalGenerator", 'P', "glassReinforced"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,35), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.Aluminium), 'C', "craftingCircuitTier02", 'G', "craftingGeothermalGenerator", 'P', "glassReinforced"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,36), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.Iron), 'C', "craftingCircuitTier02", 'G', "craftingGenerator", 'P', "glassReinforced"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,36), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.Aluminium), 'C', "craftingCircuitTier02", 'G', "craftingGenerator", 'P', "glassReinforced"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,37), new Object[] {"XXX", "XPX", "CGC", 'X', OrePrefixes.plate.get(Materials.TungstenSteel), 'C', "craftingCircuitTier07", 'G', "craftingGenerator", 'P', GT_ModHandler.getIC2Item("hvTransformer", 1)});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,91), new Object[] {"PCP", "GMG", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', "craftingRawMachineTier02", 'G', "craftingGearTier02", 'C', "craftingChest"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,92), new Object[] {"PPP", "GMG", "PCP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', "craftingRawMachineTier02", 'G', "craftingGearTier02", 'C', "craftingChest"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,93), new Object[] {"GCG", "CMC", "GCG", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', "craftingRawMachineTier02", 'G', "craftingGearTier02", 'C', "craftingCircuitTier04"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,94), new Object[] {"PPP", "GMG", "PHP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', "craftingRawMachineTier02", 'G', "craftingGearTier02", 'H', GT_ModHandler.getIC2Item("hvTransformer", 1)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,95), new Object[] {"PHP", "GMG", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', "craftingRawMachineTier02", 'G', "craftingGearTier02", 'H', GT_ModHandler.getIC2Item("hvTransformer", 1)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,96), new Object[] {"PMP", "GRG", "PRP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'M', "craftingRawMachineTier02", 'G', "craftingGearTier02", 'R', "glassReinforced"});
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "multi_gasturbine", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,108), new Object[] {"XXX", "GMG", "XCX", 'G', "craftingGearTier03", 'X', new ItemStack(GregTech_API.sBlockList[1], 1,34), 'M', "craftingRawMachineTier03", 'C', "craftingCircuitTier07"});
        }
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "multi_steamturbine", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,109), new Object[] {"XXX", "GMG", "XCX", 'G', "craftingGearTier02", 'X', new ItemStack(GregTech_API.sBlockList[1], 1,34), 'M', "craftingRawMachineTier02", 'C', "craftingCircuitTier04"});
        }
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "multi_thermalboiler", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,110), new Object[] {"XMX", "GCG", "XMX", 'G', "craftingGearTier03", 'X', new ItemStack(GregTech_API.sBlockList[1], 1,35), 'M', "craftingCentrifuge", 'C', "craftingCircuitTier06"});
        }
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "dragoneggenergysiphon", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,40), new Object[] {"CTC", "ISI", "CLC", 'C', "craftingCircuitTier07", 'L', "crafting100kkEUStore", 'S', new ItemStack(GregTech_API.sBlockList[1], 1,103), 'T', "craftingTeleporter", 'I', OrePrefixes.plateAlloy.get("Iridium")});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,42), new Object[] {"CTC", "PSP", "CLC", 'C', "craftingCircuitTier04", 'L', "crafting10kkEUStore", 'S', new ItemStack(Blocks.beacon, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'T', "craftingTeleporter", 'P', GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Thaumium, 1)==null?OrePrefixes.plate.get(Materials.Platinum):OrePrefixes.ingot.get(Materials.Thaumium)});
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "magicenergyabsorber", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,43), new Object[] {"CSC", "IBI", "CMC", 'C', "craftingCircuitTier07", 'S', "craftingSuperconductor", 'B', new ItemStack(Blocks.beacon, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'M', new ItemStack(GregTech_API.sBlockList[1], 1,42), 'I', OrePrefixes.plateAlloy.get("Iridium")});
    	}
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,29), new Object[] {"ACA", "CMC", "FCF", 'M', "craftingRawMachineTier02", 'A', "craftingCircuitTier02", 'C', "craftingHeatingCoilTier00", 'F', "craftingInductionFurnace"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,31), new Object[] {"ABA", "DCD", "ABA", 'B', "craftingRawMachineTier02", 'D', "craftingCircuitTier02", 'C', "craftingCompressor", 'A', OrePrefixes.plateAlloy.get("Advanced")});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,68), new Object[] {"PPP", "CAC", "PPP", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'A', "craftingMonitorTier02", 'C', new ItemStack(Items.comparator, 1)});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,68), new Object[] {"PPP", "CAC", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium)	, 'A', "craftingMonitorTier02", 'C', new ItemStack(Items.comparator, 1)});
		
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,78), new Object[] {"PPP", "CAC", "PPP", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'A', "craftingEnergyMeter", 'C', new ItemStack(Items.comparator, 1)});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,78), new Object[] {"PPP", "CAC", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium)	, 'A', "craftingEnergyMeter", 'C', new ItemStack(Items.comparator, 1)});
		
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2,65), new Object[] {"PGP", "GLG", "PGP", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'G', new ItemStack(Blocks.glass_pane, 1), 'L', new ItemStack(Blocks.redstone_lamp, 1)});
		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 2,65), new Object[] {"PGP", "GLG", "PGP", 'P', OrePrefixes.plate.get(Materials.Aluminium)	, 'G', new ItemStack(Blocks.glass_pane, 1), 'L', new ItemStack(Blocks.redstone_lamp, 1)});
		
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "redstonecircuitblock", true)) {
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,69), new Object[] {"PRP", "CAC", "PRP", 'P', OrePrefixes.plate.get(Materials.Iron)		, 'A', "craftingCircuitTier02", 'C', new ItemStack(Items.comparator, 1), 'R', new ItemStack(Items.repeater, 1)});
    		GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,69), new Object[] {"PRP", "CAC", "PRP", 'P', OrePrefixes.plate.get(Materials.Aluminium)	, 'A', "craftingCircuitTier02", 'C', new ItemStack(Items.comparator, 1), 'R', new ItemStack(Items.repeater, 1)});
        }
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 4,70), new Object[] {"WWW", "A A", "WWW", 'W', "plankWood", 'A', OrePrefixes.plate.get(Materials.Aluminium)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 4,71), new Object[] {"III", "A A", "III", 'I', OrePrefixes.plate.get(Materials.Iron), 'A', OrePrefixes.plate.get(Materials.Aluminium)});
    	
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,71), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,74)});
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,72), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,71)});
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,73), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,72)});
    	GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,74), new Object[] {new ItemStack(GregTech_API.sBlockList[1], 1,73)});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,112), new Object[] {"PWP", "PMP", "PCP", 'P', OrePrefixes.plate.get(Materials.Iron), 'C', "craftingCircuitTier04", 'W', "craftingWorkBench", 'M', "craftingRawMachineTier01"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,112), new Object[] {"PWP", "PMP", "PCP", 'P', OrePrefixes.plate.get(Materials.Aluminium)  , 'C', "craftingCircuitTier04", 'W', "craftingWorkBench", 'M', "craftingRawMachineTier01"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,128), new Object[] {"PWP", "PMP", "PPP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', "craftingWorkBench" , 'M', "craftingRawMachineTier00"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,129), new Object[] {"PPP", "PWP", "BFB", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'F', "craftingFurnace"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,130), new Object[] {"WDH", "GMG", "PKP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'H', GT_ToolDictNames.craftingToolHardHammer, 'M', "craftingRawMachineTier00", 'D', OrePrefixes.gem.get(Materials.Diamond), 'G', "craftingGearTier01", 'K', "craftingPiston"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,131), new Object[] {"PWP", "PFP", "BMB", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'M', "craftingRawMachineTier00", 'F', "craftingFurnace"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,132), new Object[] {"PPP", "FWF", "BBB", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'F', new ItemStack(GregTech_API.sBlockList[1], 1,131)});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,133), new Object[] {"GKG", "PWP", "PMP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'M', "craftingRawMachineTier00", 'G', "craftingGearTier01", 'K', "craftingPiston"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,134), new Object[] {"PGP", "KWK", "PMP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'M', "craftingRawMachineTier00", 'G', "craftingGearTier01", 'K', "craftingPiston"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,135), new Object[] {"PPP", "KWK", "PMP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'M', "craftingRawMachineTier00", 'K', "craftingPiston"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,136), new Object[] {"PBP", "BWB", "PBP", 'P', OrePrefixes.plate.get(Materials.Bronze), 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'F', "craftingFurnace"});
    	
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,137), new Object[] {"PPP", "PWP", "BFB", 'P', "craftingPlateSteel" , 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'F', "craftingFurnace"});
    	GT_ModHandler.addCraftingRecipe(new ItemStack(GregTech_API.sBlockList[1], 1,138), new Object[] {"PWP", "PFP", "BMB", 'P', "craftingPlateSteel" , 'W', GT_ToolDictNames.craftingToolWrench, 'B', new ItemStack(Blocks.brick_block, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'M', "craftingRawMachineTier01", 'F', "craftingFurnace"});
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "destructopack", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(33, 1, 0), new Object[] {"ARA", "RLR", "ARA", 'A', "craftingCircuitTier04", 'R', OrePrefixes.plate.get(Materials.Iron), 'L', new ItemStack(Items.lava_bucket, 1)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(33, 1, 0), new Object[] {"ARA", "RLR", "ARA", 'A', "craftingCircuitTier04", 'R', OrePrefixes.plate.get(Materials.Aluminium), 'L', new ItemStack(Items.lava_bucket, 1)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "heliumcoolant", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(34, 1, 0), new Object[] {" T ", "THT", " T ", 'H', OrePrefixes.cell.get(Materials.Helium), 'T', OrePrefixes.plate.get(Materials.Tin)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(35, 1, 0), new Object[] {"TTT", "HHH", "TTT", 'H', GregTech_API.getGregTechItem(34, 1, 0), 'T', OrePrefixes.plate.get(Materials.Tin)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(36, 1, 0), new Object[] {"THT", "TCT", "THT", 'H', GregTech_API.getGregTechItem(35, 1, 0), 'T', OrePrefixes.plate.get(Materials.Tin), 'C', OrePrefixes.plateDense.get(Materials.Copper)});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("overclockerUpgrade", 2), new Object[] {" H ", "WCW", "   ", 'H', GregTech_API.getGregTechItem(34, 1, 0), 'W', "craftingWireCopper", 'C', "craftingCircuitTier02"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "nakcoolant", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(60, 1, 0), new Object[] {"TNT", "KCK", "TNT", 'N', OrePrefixes.cell.get(Materials.Sodium), 'K', OrePrefixes.cell.get(Materials.Potassium), 'T', OrePrefixes.plate.get(Materials.Tin), 'C', GT_ModHandler.getIC2Item("reactorCoolantSimple", 1)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(60, 1, 0), new Object[] {"TKT", "NCN", "TKT", 'N', OrePrefixes.cell.get(Materials.Sodium), 'K', OrePrefixes.cell.get(Materials.Potassium), 'T', OrePrefixes.plate.get(Materials.Tin), 'C', GT_ModHandler.getIC2Item("reactorCoolantSimple", 1)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(61, 1, 0), new Object[] {"TTT", "HHH", "TTT", 'H', GregTech_API.getGregTechItem(60, 1, 0), 'T', OrePrefixes.plate.get(Materials.Tin)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(62, 1, 0), new Object[] {"THT", "TCT", "THT", 'H', GregTech_API.getGregTechItem(61, 1, 0), 'T', OrePrefixes.plate.get(Materials.Tin), 'C', OrePrefixes.plateDense.get(Materials.Copper)});
    		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("overclockerUpgrade", 2), new Object[] {" H ", "WCW", "   ", 'H', GregTech_API.getGregTechItem(60, 1, 0), 'W', "craftingWireCopper", 'C', "craftingCircuitTier02"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "iridiumreflector", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(40, 1, 0), new Object[] {"NNN", "NIN", "NNN", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'N', GT_ModHandler.getIC2Item("reactorReflectorThick", 1)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "lapotronpack", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(45, 1, 0), new Object[] {"CLC", "SPS", "CIC", 'L', "crafting100kkEUStore", 'C', "craftingCircuitTier07", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'P', "crafting300kEUPack", 'S', "craftingSuperconductor"});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "lithiumbattery", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(56, 1, 0), new Object[] {" C ", "ALA", "ALA", 'C', "craftingWireGold", 'L', OrePrefixes.cell.get(Materials.Lithium), 'A', OrePrefixes.plate.get(Materials.Aluminium)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(56, 1, 0), new Object[] {" C ", "ALA", "ALA", 'C', "craftingWireGold", 'L', OrePrefixes.cell.get(Materials.Lithium), 'A', OrePrefixes.plate.get(Materials.BatteryAlloy)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "lithiumbatpack", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(58, 1, 0), new Object[] {"LCL", "LAL", "L L", 'L', "craftingLiBattery", 'C', "craftingCircuitTier04", 'A', OrePrefixes.plate.get(Materials.Aluminium)});
    	}
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "lighthelmet", true)) {
    		GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(44, 1, 0), new Object[] {GT_ModHandler.getIC2Item("solarHelmet", 1), GT_ModHandler.getIC2Item("luminator", 1)});	
    	}
    	
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "rockcutter", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(46, 1, 0), new Object[] {"DS ", "DP ", "DCB", 'B', "crafting10kEUStore", 'C', "craftingCircuitTier02", 'P', OrePrefixes.plate.get(Materials.Titanium), 'S', OrePrefixes.stick.get(Materials.Titanium), 'D', OrePrefixes.dust.get(Materials.Diamond)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(46, 1, 0), new Object[] {"DS ", "DP ", "DCB", 'B', "crafting10kEUStore", 'C', "craftingCircuitTier02", 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'S', OrePrefixes.stick.get(Materials.TungstenSteel), 'D', OrePrefixes.dust.get(Materials.Diamond)});
        }
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "jackhammer", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(39, 1, 0), new Object[] {"SBS", " C ", " I ", 'B', "crafting10kEUStore", 'C', "craftingCircuitTier02", 'S', OrePrefixes.stick.get(Materials.Bronze), 'I', OrePrefixes.ingot.get(Materials.Bronze)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(41, 1, 0), new Object[] {"SBS", " C ", " I ", 'B', "crafting10kEUStore", 'C', "craftingCircuitTier04", 'S', OrePrefixes.stick.get(Materials.StainlessSteel), 'I', OrePrefixes.ingot.get(Materials.Steel)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(42, 1, 0), new Object[] {"SBS", " C ", " D ", 'B', "craftingLiBattery", 'C', "craftingCircuitTier04", 'S', OrePrefixes.stick.get(Materials.Titanium), 'D', OrePrefixes.dust.get(Materials.Diamond)});
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(42, 1, 0), new Object[] {"SBS", " C ", " D ", 'B', "craftingLiBattery", 'C', "craftingCircuitTier04", 'S', OrePrefixes.stick.get(Materials.TungstenSteel), 'D', OrePrefixes.dust.get(Materials.Diamond)});
        }
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "teslastaff", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(47, 1, 0), new Object[] {"LS ", "SI ", "  I", 'L', "crafting100kkEUStore", 'S', "craftingSuperconductor", 'I', OrePrefixes.plateAlloy.get("Iridium")});
        }
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "cloakingdevice", true)) {
    		GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(38, 1, 0), new Object[] {"CIC", "ILI", "CIC", 'L', "crafting100kkEUStore", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'C', OrePrefixes.plate.get(Materials.Chrome)});
    	}
    	
    	GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1)	, new Object[] {GregTech_API.getGregTechItem(95, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.dust.get(Materials.Coal)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(31, 1, 0), new Object[] {new ItemStack(Items.bowl, 1), new ItemStack(Items.flint, 1)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Coal"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1)	, new Object[] {GregTech_API.getGregTechItem(31, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Items.coal, 1)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Gold"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Gold), 1)			, new Object[] {GregTech_API.getGregTechItem(31, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), "ingotGold"});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Clay"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Clay), 1)			, new Object[] {GregTech_API.getGregTechItem(31, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Blocks.clay, 1)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Copper"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Copper), 1)	, new Object[] {GregTech_API.getGregTechItem(31, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Copper)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Tin"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Tin), 1)			, new Object[] {GregTech_API.getGregTechItem(31, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Tin)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Silver"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Silver), 1)	, new Object[] {GregTech_API.getGregTechItem(31, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Silver)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Electrum"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Electrum), 1)		, new Object[] {GregTech_API.getGregTechItem(31, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Electrum)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Wheat"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Wheat), 1)		, new Object[] {GregTech_API.getGregTechItem(31, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Items.wheat, 1)});
        
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(30, 1, 0), new Object[] {" R ", "SRS", "SSS", 'S', "stoneBricks", 'R', OrePrefixes.ingot.get(Materials.Iron)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Coal"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("hydratedCoalDust", 1)	, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Items.coal, 1)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Gold"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Gold), 1)			, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), "ingotGold"});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Clay"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Clay), 1)			, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Blocks.clay, 1)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Copper"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Copper), 1)	, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Copper)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Tin"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Tin), 1)			, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Tin)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Bronze"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Bronze), 1)	, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Bronze)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Silver"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Silver), 1)	, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Silver)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Electrum"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Electrum), 1)		, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Electrum)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Brass"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Brass), 1)		, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), "ingotBrass"});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Iron"		, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Iron), 1)			, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), OrePrefixes.ingot.get(Materials.Iron)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Flint"	, true)) GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.flint, 1)												, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Blocks.gravel, 1)});
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.mortar, "Wheat"	, true)) GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Wheat), 1)		, new Object[] {GregTech_API.getGregTechItem(30, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Items.wheat, 1)});
        
        if (!GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.blastfurnacerequirements, "steel", true)) {
            GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Steel)	, 1)	, new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Coal), OrePrefixes.dust.get(Materials.Coal)});
        }
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Electrum)			, 2), new Object[] {OrePrefixes.dust.get(Materials.Silver), OrePrefixes.dust.get(Materials.Gold)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Electrum)			, 1), new Object[] {OrePrefixes.dustSmall.get(Materials.Silver), OrePrefixes.dustSmall.get(Materials.Silver), OrePrefixes.dustSmall.get(Materials.Gold), OrePrefixes.dustSmall.get(Materials.Gold)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Brass)				, 4), new Object[] {OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Zinc)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Brass)				, 1), new Object[] {OrePrefixes.dustSmall.get(Materials.Copper), OrePrefixes.dustSmall.get(Materials.Copper), OrePrefixes.dustSmall.get(Materials.Copper), OrePrefixes.dustSmall.get(Materials.Zinc)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Brass)				, 3), new Object[] {OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Zinc)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall.get(Materials.Brass)		, 3), new Object[] {OrePrefixes.dustSmall.get(Materials.Tetrahedrite), OrePrefixes.dustSmall.get(Materials.Tetrahedrite), OrePrefixes.dustSmall.get(Materials.Tetrahedrite), OrePrefixes.dustSmall.get(Materials.Zinc)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Bronze)			, 2), new Object[] {OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Copper), OrePrefixes.dust.get(Materials.Tin)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall.get(Materials.Bronze)		, 6), new Object[] {OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tetrahedrite), OrePrefixes.dust.get(Materials.Tin)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Invar)				, 3), new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Nickel)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall.get(Materials.Invar)		, 3), new Object[] {OrePrefixes.dustSmall.get(Materials.Iron), OrePrefixes.dustSmall.get(Materials.Iron), OrePrefixes.dustSmall.get(Materials.Nickel)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall.get(Materials.Bronze)		, 2), new Object[] {OrePrefixes.dustSmall.get(Materials.Copper), OrePrefixes.dustSmall.get(Materials.Copper), OrePrefixes.dustSmall.get(Materials.Copper), OrePrefixes.dustSmall.get(Materials.Tin)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dustSmall.get(Materials.Bronze)		, 1), new Object[] {OrePrefixes.dustSmall.get(Materials.Tetrahedrite), OrePrefixes.dustSmall.get(Materials.Tetrahedrite), OrePrefixes.dustSmall.get(Materials.Tetrahedrite), OrePrefixes.dustSmall.get(Materials.Tin)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.StainlessSteel)	, 9), new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Manganese), OrePrefixes.dust.get(Materials.Chrome)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Cupronickel)		, 2), new Object[] {OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Copper)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Nichrome)			, 5), new Object[] {OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Nickel), OrePrefixes.dust.get(Materials.Chrome)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Kanthal)			, 3), new Object[] {OrePrefixes.dust.get(Materials.Iron), OrePrefixes.dust.get(Materials.Aluminium), OrePrefixes.dust.get(Materials.Chrome)});
        
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.gunpowder, 3), new Object[] {OrePrefixes.dust.get(Materials.Coal), "craftingSulfurToGunpowder", "craftingSaltpeterToGunpowder", "craftingSaltpeterToGunpowder"});
        GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Items.gunpowder, 2), new Object[] {OrePrefixes.dust.get(Materials.Charcoal), "craftingSulfurToGunpowder", "craftingSaltpeterToGunpowder", "craftingSaltpeterToGunpowder"});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 4), new Object[] {GT_ModHandler.getIC2Item("fertilizer", 1), OrePrefixes.dust.get(Materials.Phosphorus)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 2), new Object[] {GT_ModHandler.getIC2Item("fertilizer", 1), new ItemStack(Items.dye, 1, 15)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 3), new Object[] {GT_ModHandler.getIC2Item("fertilizer", 1), OrePrefixes.dust.get(Materials.Sulfur), OrePrefixes.dust.get(Materials.Calcium)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 2), new Object[] {GT_ModHandler.getIC2Item("fertilizer", 1), OrePrefixes.dust.get(Materials.Ash), OrePrefixes.dust.get(Materials.Ash), OrePrefixes.dust.get(Materials.Ash)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("fertilizer", 2), new Object[] {GT_ModHandler.getIC2Item("fertilizer", 1), OrePrefixes.dust.get(Materials.DarkAsh)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Dust.instance.getStack(9, 10), new Object[] {OrePrefixes.cell.get(Materials.Potassium), OrePrefixes.cell.get(Materials.Potassium), OrePrefixes.cell.get(Materials.Nitrogen), OrePrefixes.cell.get(Materials.Nitrogen), OrePrefixes.cell.get(Materials.Oxygen), OrePrefixes.cell.get(Materials.Oxygen), OrePrefixes.cell.get(Materials.Oxygen)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("carbonFiber",1), new Object[] {OrePrefixes.cell.get(Materials.Carbon), OrePrefixes.cell.get(Materials.Carbon), OrePrefixes.cell.get(Materials.Carbon), OrePrefixes.cell.get(Materials.Carbon), OrePrefixes.cell.get(Materials.Carbon), OrePrefixes.cell.get(Materials.Carbon), OrePrefixes.cell.get(Materials.Carbon), OrePrefixes.cell.get(Materials.Carbon), OrePrefixes.cell.get(Materials.Carbon)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C", "Q", "R", 'Q', OrePrefixes.cell.get(Materials.Mercury), 'R', OrePrefixes.dust.get(Materials.Redstone), 'C', "craftingWireCopper"});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C", "R", "Q", 'Q', OrePrefixes.cell.get(Materials.Mercury), 'R', OrePrefixes.dust.get(Materials.Redstone), 'C', "craftingWireCopper"});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C", "S", "R", 'S', OrePrefixes.cell.get(Materials.SulfuricAcid), 'R', OrePrefixes.dust.get(Materials.Lead), 'C', "craftingWireCopper"});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("suBattery", 32), new Object[] {"C", "R", "S", 'S', OrePrefixes.cell.get(Materials.SulfuricAcid), 'R', OrePrefixes.dust.get(Materials.Lead), 'C', "craftingWireCopper"});
        
        GT_ModHandler.addCraftingRecipe(null, new Object[] {" C ", "TRT", "TRT", 'R', new ItemStack(Items.redstone), 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem", 1), 'T', OrePrefixes.ingot.get(Materials.Tin)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery",  1), false, new Object[] {" C ", "TRT", "TRT", 'C', "craftingWireTin", 'T', OrePrefixes.plate.get(Materials.Tin), 'R', OrePrefixes.dust.get(Materials.Redstone)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery",  2), false, new Object[] {" C ", "TLT", "TST", 'C', "craftingWireTin", 'T', OrePrefixes.plate.get(Materials.Tin), 'S', OrePrefixes.cell.get(Materials.SulfuricAcid), 'L', OrePrefixes.dust.get(Materials.Lead)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery",  2), false, new Object[] {" C ", "TST", "TLT", 'C', "craftingWireTin", 'T', OrePrefixes.plate.get(Materials.Tin), 'S', OrePrefixes.cell.get(Materials.SulfuricAcid), 'L', OrePrefixes.dust.get(Materials.Lead)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery",  2), false, new Object[] {" C ", "TRT", "TRT", 'C', "craftingWireTin", 'T', OrePrefixes.plate.get(Materials.BatteryAlloy), 'R', OrePrefixes.dust.get(Materials.Redstone)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery",  3), false, new Object[] {" C ", "TLT", "TST", 'C', "craftingWireTin", 'T', OrePrefixes.plate.get(Materials.BatteryAlloy), 'S', OrePrefixes.cell.get(Materials.SulfuricAcid), 'L', OrePrefixes.dust.get(Materials.Lead)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reBattery",  3), false, new Object[] {" C ", "TST", "TLT", 'C', "craftingWireTin", 'T', OrePrefixes.plate.get(Materials.BatteryAlloy), 'S', OrePrefixes.cell.get(Materials.SulfuricAcid), 'L', OrePrefixes.dust.get(Materials.Lead)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 4), new Object[] {"GGG", "XDX", "GGG", 'G', new ItemStack(Blocks.glass, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'X', OrePrefixes.ingot.get(Materials.Silver), 'D', GT_ModHandler.getIC2Item("energiumDust", 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("glassFiberCableItem", 4), new Object[] {"GGG", "XDX", "GGG", 'G', new ItemStack(Blocks.glass, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'X', OrePrefixes.ingot.get(Materials.Electrum), 'D', GT_ModHandler.getIC2Item("energiumDust", 1)});
        
        GT_ModHandler.addCraftingRecipe(null, new Object[] {"CCC", "SRS", "CCC", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem"	, 1), 'R', OrePrefixes.plate.get(Materials.Iron), 'S', new ItemStack(Items.redstone, 1)});
        GT_ModHandler.addCraftingRecipe(null, new Object[] {"CSC", "CRC", "CSC", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem"	, 1), 'R', OrePrefixes.plate.get(Materials.Iron), 'S', new ItemStack(Items.redstone, 1)});
        GT_ModHandler.addCraftingRecipe(null, new Object[] {"SCS", "SRS", "SSS", 'C', GT_ModHandler.getIC2Item("insulatedCopperCableItem"	, 1), 'R', OrePrefixes.plate.get(Materials.Iron), 'S', new ItemStack(Items.redstone, 1)});
        GT_ModHandler.addCraftingRecipe(null, new Object[] {"CCC", "SRS", "CCC", 'C', GT_ModHandler.getIC2Item("insulatedTinCableItem"		, 1), 'R', OrePrefixes.plate.get(Materials.Iron), 'S', new ItemStack(Items.redstone, 1)});
        GT_ModHandler.addCraftingRecipe(null, new Object[] {"CSC", "CRC", "CSC", 'C', GT_ModHandler.getIC2Item("insulatedTinCableItem"		, 1), 'R', OrePrefixes.plate.get(Materials.Iron), 'S', new ItemStack(Items.redstone, 1)});
        GT_ModHandler.addCraftingRecipe(null, new Object[] {"SCS", "SRS", "SSS", 'C', GT_ModHandler.getIC2Item("insulatedTinCableItem"		, 1), 'R', OrePrefixes.plate.get(Materials.Iron), 'S', new ItemStack(Items.redstone, 1)});
        
        //GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1));
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), new Object[] {"CCC", "SRS", "CCC", 'C', "craftingWireCopper", 'R', OrePrefixes.plate.get(Materials.Iron), 'S', OrePrefixes.dust.get(Materials.Redstone)});
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electronicCircuit", 1), new Object[] {"CSC", "CRC", "CSC", 'C', "craftingWireCopper", 'R', OrePrefixes.plate.get(Materials.Iron), 'S', OrePrefixes.dust.get(Materials.Redstone)});
    	
    	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1));
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), new Object[] {"SGS", "LCL", "SGS", 'C', GT_ModHandler.getIC2Item("electronicCircuit", 1), 'S', OrePrefixes.dust.get(Materials.Redstone), 'G', OrePrefixes.dust.get(Materials.Glowstone), 'L', OrePrefixes.dust.get(Materials.Lazurite)});
       	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), new Object[] {"SLS", "GCG", "SLS", 'C', GT_ModHandler.getIC2Item("electronicCircuit", 1), 'S', OrePrefixes.dust.get(Materials.Redstone), 'G', OrePrefixes.dust.get(Materials.Glowstone), 'L', OrePrefixes.dust.get(Materials.Lazurite)});
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), new Object[] {"SGS", "LCL", "SGS", 'C', GT_ModHandler.getIC2Item("electronicCircuit", 1), 'S', OrePrefixes.dust.get(Materials.Redstone), 'G', OrePrefixes.dust.get(Materials.Glowstone), 'L', OrePrefixes.dust.get(Materials.Lapis)});
       	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("advancedCircuit", 1), new Object[] {"SLS", "GCG", "SLS", 'C', GT_ModHandler.getIC2Item("electronicCircuit", 1), 'S', OrePrefixes.dust.get(Materials.Redstone), 'G', OrePrefixes.dust.get(Materials.Glowstone), 'L', OrePrefixes.dust.get(Materials.Lapis)});
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapPack", 1), new Object[] {"LCL", "LBL", "L L", 'C', "craftingCircuitTier04", 'B', "crafting60kEUPack",  'L', "blockLapis"});
    	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapPack", 1), new Object[] {"LCL", "LBL", "L L", 'C', "craftingCircuitTier04", 'B', "crafting60kEUPack",  'L', "chunkLazurite"});
    	
    	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("energyCrystal", 1));
    	
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "energycrystalruby", true)) {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("energyCrystal", 1), new Object[] {"DDD", "DRD", "DDD", 'D', OrePrefixes.dust.get(Materials.Redstone), 'R', OrePrefixes.gem.get(Materials.Ruby)});
        } else {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("energyCrystal", 1), new Object[] {"DDD", "DRD", "DDD", 'D', OrePrefixes.dust.get(Materials.Redstone), 'R', OrePrefixes.gem.get(Materials.Diamond)});
        }
        
    	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1));
    	
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "lapotroncrystalsapphire", true)) {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), new Object[] {"LCL", "LSL", "LCL", 'C', "craftingCircuitTier04", 'S', OrePrefixes.gem.get(Materials.Sapphire), 'L', OrePrefixes.dust.get(Materials.Lazurite)});
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), new Object[] {"LCL", "LSL", "LCL", 'C', "craftingCircuitTier04", 'S', OrePrefixes.gem.get(Materials.Sapphire), 'L', OrePrefixes.dust.get(Materials.Lapis)});
        } else {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), new Object[] {"LCL", "LSL", "LCL", 'C', "craftingCircuitTier04", 'S', GT_ModHandler.getIC2Item("energyCrystal", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'L', OrePrefixes.dust.get(Materials.Lazurite)});
            GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("lapotronCrystal", 1), new Object[] {"LCL", "LSL", "LCL", 'C', "craftingCircuitTier04", 'S', GT_ModHandler.getIC2Item("energyCrystal", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'L', OrePrefixes.dust.get(Materials.Lapis)});
        }
        
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "mininglaser", true)) {
        	if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("miningLaser", 1)))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("miningLaser", 1), new Object[] {"RHE", "TTC", " AA", 'C', "craftingCircuitTier04", 'H', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "heliumcoolant", true)?GregTech_API.getGregTechItem(36, 1, GregTech_API.ITEM_WILDCARD_DAMAGE):GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.gregtechrecipes, "nakcoolant", true)?GregTech_API.getGregTechItem(62, 1, GregTech_API.ITEM_WILDCARD_DAMAGE):GT_ModHandler.getIC2Item("reactorCoolantSix", 1), 'R', "craftingLenseRed", 'T', OrePrefixes.plate.get(Materials.Titanium), 'E', "crafting1kkEUStore", 'A', OrePrefixes.plateAlloy.get("Advanced")});
        }
        
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("luminator", 16), new Object[] {"RTR", "GHG", "GGG", 'H', OrePrefixes.cell.get(Materials.Helium), 'T', OrePrefixes.ingot.get(Materials.Tin), 'R', OrePrefixes.ingot.get(Materials.Iron), 'G', new ItemStack(Blocks.glass, 1)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("luminator", 16), new Object[] {"RTR", "GHG", "GGG", 'H', OrePrefixes.cell.get(Materials.Mercury), 'T', OrePrefixes.ingot.get(Materials.Tin), 'R', OrePrefixes.ingot.get(Materials.Iron), 'G', new ItemStack(Blocks.glass, 1)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getEmptyCell(1), new Object[] {GT_ModHandler.getAirCell(1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getEmptyCell(1), new Object[] {GT_ModHandler.getIC2Item("airCell", 1)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(0, 8)	, new Object[] {GT_ModHandler.getIC2Item("coin", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("coin", 8)			, new Object[] {GT_MetaItem_Material.instance.getStack(1, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(1, 8)	, new Object[] {GT_MetaItem_Material.instance.getStack(2, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(2, 8)	, new Object[] {GT_MetaItem_Material.instance.getStack(3, 1)});
        
        GT_ModHandler.addShapelessCraftingRecipe(GT_ModHandler.getIC2Item("coin", 1)			, new Object[] {GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1), GT_MetaItem_Material.instance.getStack(0, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(1, 1)	, new Object[] {GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1), GT_ModHandler.getIC2Item("coin", 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(2, 1)	, new Object[] {GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1), GT_MetaItem_Material.instance.getStack(1, 1)});
        GT_ModHandler.addShapelessCraftingRecipe(GT_MetaItem_Material.instance.getStack(3, 1)	, new Object[] {GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1), GT_MetaItem_Material.instance.getStack(2, 1)});
        
        GT_ModHandler.removeRecipe(new ItemStack[] {tStack = GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Sulfur), 1), tStack, tStack, tStack, new ItemStack(Items.coal, 1, 0), tStack, tStack, tStack, tStack});
        GT_ModHandler.removeRecipe(new ItemStack[] {tStack = GT_OreDictUnificator.get(OrePrefixes.dust.get(Materials.Sulfur), 1), tStack, tStack, tStack, new ItemStack(Items.coal, 1, 1), tStack, tStack, tStack, tStack});
        GT_ModHandler.removeRecipe(new ItemStack[] {tStack = new ItemStack(Items.wheat_seeds, 1), tStack, tStack, tStack, null, tStack, tStack, tStack, tStack});
		
        GT_Log.log.info("GT_Mod: Applying harder Recipes for several Blocks.");
        if (GT_ModHandler.removeRecipe(GT_OreDictUnificator.get(OrePrefixes.plateAlloy.get("Iridium"), 1))) {
    		GT_ModHandler.addRollingMachineRecipe(GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "iridiumplate", true)?GT_MetaItem_Material.instance.getStack(4, 1):GT_OreDictUnificator.get(OrePrefixes.plateAlloy.get("Iridium"), 1), new Object[] {"IAI", "ADA", "IAI", 'D', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "iridiumplate", true)?"craftingIndustrialDiamond":OrePrefixes.dust.get(Materials.Diamond), 'A', OrePrefixes.plateAlloy.get("Advanced"), 'I', OrePrefixes.plate.get(Materials.Iridium)});
    	}
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "blockbreaker", false)) {
        	GT_ModHandler.addCraftingRecipe(GT_ModHandler.removeRecipe(new ItemStack[] {new ItemStack(Blocks.cobblestone, 1), new ItemStack(Items.iron_pickaxe, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.piston, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Blocks.cobblestone, 1), new ItemStack(Items.redstone, 1), new ItemStack(Blocks.cobblestone, 1)}), new Object[] {"RGR", "RPR", "RCR" , 'G', "craftingGrinder", 'C', "craftingCircuitTier04", 'R', "craftingPlateSteel", 'P', "craftingPiston"});
        }
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "beryliumreflector", true)) {
        	if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("reactorReflectorThick", 1)))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("reactorReflectorThick", 1), new Object[] {" N ", "NBN", " N ", 'B', OrePrefixes.cell.get(Materials.Beryllium), 'N', GT_ModHandler.getIC2Item("reactorReflector", 1)});
        }
        
    	if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "windmill", true)) {
        	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("windMill", 1));
        }
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "watermill", true)) {
        	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("waterMill", 1));
        }
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "solarpanel", true)) {
        	GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("solarPanel", 1));
        }
        if (GT_ModHandler.mBCDiamondGear != null) {
            tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {GT_ModHandler.mBCIronGear, new ItemStack(Items.redstone, 1), GT_ModHandler.mBCIronGear, GT_ModHandler.mBCGoldGear, GT_ModHandler.mBCIronGear, GT_ModHandler.mBCGoldGear, GT_ModHandler.mBCDiamondGear, new ItemStack(Items.diamond_pickaxe, 1), GT_ModHandler.mBCDiamondGear});
    		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "quarry", true)) {
    			GT_ModHandler.removeRecipe(tStack);
    			GT_ModHandler.addCraftingRecipe(tStack, new Object[] {"ICI", "GIG", "DPD", 'C', "craftingCircuitTier04", 'D', GT_ModHandler.mBCDiamondGear, 'G', GT_ModHandler.mBCGoldGear, 'I', GT_ModHandler.getRCItem("part.gear.steel", 1, GT_ModHandler.mBCIronGear), 'P', GT_ModHandler.getIC2Item("diamondDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
    		}
    		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "quarry", false)) {
    			GT_ModHandler.removeRecipe(tStack);
    		}
        }
        
        GT_Log.log.info("GT_Mod: Applying Recipes for Tools");
        
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "nanosaber", true)) {
        	if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("nanoSaber", 1)))
        		GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("nanoSaber", 1), false, new Object[] {"PI ", "PI ", "CLC", 'L', "crafting10kkEUStore", 'I', OrePrefixes.plateAlloy.get("Iridium"), 'P', OrePrefixes.plate.get(Materials.Platinum), 'C', "craftingCircuitTier07"});
        }
        
		if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "namefix", true)) {
	        GT_ModHandler.addCraftingRecipe(GT_ModHandler.removeRecipe(new ItemStack(Items.flint_and_steel, 1))?new ItemStack(Items.flint_and_steel, 1):null, new Object[] {"S ", " F", 'F', new ItemStack(Items.flint, 1), 'S', "nuggetSteel"});
		}
		
        if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("diamondDrill"		, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("diamondDrill"		, 1), false, true, false, new Object[] {" D ", "DMD", "TAT", 'M', GT_ModHandler.getIC2Item("miningDrill", 1, GregTech_API.ITEM_WILDCARD_DAMAGE), 'D', "craftingIndustrialDiamond", 'T', OrePrefixes.plate.get(Materials.Titanium), 'A', "craftingCircuitTier04"});
        if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("miningDrill"		, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("miningDrill"		, 1), false, true, false, new Object[] {" S ", "SCS", "SBS", 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'S', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("chainsaw"			, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("chainsaw"			, 1), false, true, false, new Object[] {"BS ", "SCS", " SS", 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'S', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("electricHoe"		, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricHoe"		, 1), false, true, false, new Object[] {"SS ", " C ", " B ", 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'S', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("electricTreetap"	, 1)))	GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricTreetap"	, 1), false, true, false, new Object[] {" B ", "SCS", "S  ", 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'S', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        if (GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("electricWrench"	, 1)))	GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(70, 1, 0)			, false, true, false, new Object[] {"S S", "SCS", " B ", 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'S', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(69, 1, 0)		, false, false, new Object[] {GT_ModHandler.getIC2Item("wrench", 1, 0)});
        GT_ModHandler.addShapelessCraftingRecipe(GregTech_API.getGregTechItem(70, 1, 0)		, false, false, new Object[] {GT_ModHandler.getIC2Item("electricWrench", 1, GregTech_API.ITEM_WILDCARD_DAMAGE)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(71, 1, 0)				, false, true, false, new Object[] {"T T", "TCT", " B ", 'C', "craftingCircuitTier04", 'B', "craftingLiBattery", 'T', OrePrefixes.plate.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(77, 1, 0)				, false, true, false, new Object[] {"R"  , "C"  , "B"  , 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'R', OrePrefixes.ingot.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("wrench", 1, 0)			, false, true, false, new Object[] {" B ", "BBB", "B B", 'B', OrePrefixes.ingot.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(GT_ModHandler.getIC2Item("electricWrench", 1, 0)	, false, true, false, new Object[] {" B ", "SCS", "S S", 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'S', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 64, 1, 0), false, true, false, new Object[] {" BI", "BIB", "IB ", 'I', OrePrefixes.stick.get(Materials.Iron), 'B', "dyeBlue"});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 65, 1, 0), false, true, false, new Object[] {"I  ", " I ", "  S", 'I', OrePrefixes.stick.get(Materials.Iron), 'S', OrePrefixes.stick.get(Materials.Wood)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 65, 1, 0), false, true, false, new Object[] {"  I", " I ", "S  ", 'I', OrePrefixes.stick.get(Materials.Iron), 'S', OrePrefixes.stick.get(Materials.Wood)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 66, 1, 0), false, true, false, new Object[] {"S S", "SSS", " S ", 'S', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 67, 1, 0), false, true, false, new Object[] {"I I", "III", " I ", 'I', OrePrefixes.ingot.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 68, 1, 0), false, true, false, new Object[] {"T T", "TTT", " T ", 'T', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 69, 1, 0), false, true, false, new Object[] {"B B", "BBB", " B ", 'B', OrePrefixes.ingot.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 72, 1, 0), false, true, false, new Object[] {"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Wood)		, 'R', "itemRubber"});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 73, 1, 0), false, true, false, new Object[] {"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Wood)		, 'R', OrePrefixes.ingot.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 74, 1, 0), false, true, false, new Object[] {"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Wood)		, 'R', OrePrefixes.ingot.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 75, 1, 0), false, true, false, new Object[] {"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Iron)		, 'R', OrePrefixes.ingot.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 76, 1, 0), false, true, false, new Object[] {"RR ", "RRS", "RR ", 'S', OrePrefixes.stick.get(Materials.Steel)	, 'R', OrePrefixes.ingot.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 86, 1, 0), false, true, false, new Object[] {"P", "P", "S", 'S', OrePrefixes.stick.get(Materials.Wood)			, 'P', OrePrefixes.plate.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 87, 1, 0), false, true, false, new Object[] {"P", "P", "S", 'S', OrePrefixes.stick.get(Materials.Wood)			, 'P', OrePrefixes.plate.get(Materials.Bronze)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 88, 1, 0), false, true, false, new Object[] {"P", "P", "S", 'S', OrePrefixes.stick.get(Materials.Iron)			, 'P', OrePrefixes.plate.get(Materials.Steel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem( 89, 1, 0), false, true, false, new Object[] {"P", "P", "S", 'S', OrePrefixes.stick.get(Materials.Steel)			, 'P', OrePrefixes.plate.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(115, 1, 0), false, true, false, new Object[] {"SSS", "PPS", "FH ", 'S', OrePrefixes.stick.get(Materials.Wood)		, 'P', OrePrefixes.plate.get(Materials.Iron)		, 'F', GT_ToolDictNames.craftingToolFile, 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(116, 1, 0), false, true, false, new Object[] {"SSS", "PPS", "FH ", 'S', OrePrefixes.stick.get(Materials.Wood)		, 'P', OrePrefixes.plate.get(Materials.Bronze)		, 'F', GT_ToolDictNames.craftingToolFile, 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(117, 1, 0), false, true, false, new Object[] {"SSS", "PPS", "FH ", 'S', OrePrefixes.stick.get(Materials.Iron)		, 'P', OrePrefixes.plate.get(Materials.Steel)		, 'F', GT_ToolDictNames.craftingToolFile, 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(118, 1, 0), false, true, false, new Object[] {"SSS", "PPS", "FH ", 'S', OrePrefixes.stick.get(Materials.Steel)	, 'P', OrePrefixes.plate.get(Materials.TungstenSteel)	, 'F', GT_ToolDictNames.craftingToolFile, 'H', GT_ToolDictNames.craftingToolHardHammer});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(119, 1, 0), false, true, false, new Object[] {" SS", "SCS", "BS ", 'C', "craftingCircuitTier02", 'B', "crafting10kEUStore", 'S', GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.harderrecipes, "electricsteeltools", true)?OrePrefixes.plate.get(Materials.StainlessSteel):OrePrefixes.plate.get(Materials.Iron)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(120, 1, 0), false, true, false, new Object[] {" SS", "SCS", "BS ", 'C', "craftingCircuitTier04", 'B', "craftingLiBattery", 'S', OrePrefixes.plate.get(Materials.TungstenSteel)});
        GT_ModHandler.addCraftingRecipe(GregTech_API.getGregTechItem(123, 1, 0), false, true, false, new Object[] {"DDD", "SCS", "SBS", 'C', "craftingCircuitTier06", 'B', "craftingLiBattery", 'S', OrePrefixes.plate.get(Materials.TungstenSteel), 'D', "craftingIndustrialDiamond"});
        
        GT_Log.log.info("GT_Mod: Removing Q-Armor Recipes if configured.");
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "QHelmet"		, false)) GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumHelmet", 1));
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "QPlate"		, false)) GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumBodyarmor", 1));
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "QLegs"		, false)) GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumLeggings", 1));
        if (GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.disabledrecipes, "QBoots"		, false)) GT_ModHandler.removeRecipe(GT_ModHandler.getIC2Item("quantumBoots", 1));
	}
}
