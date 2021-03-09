package gregtechmod.loaders.oreprocessing;

import java.util.List;
import java.util.stream.Collectors;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ProcessingCrafting implements IOreRecipeRegistrator {

	public ProcessingCrafting() {
		OrePrefixes.crafting.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> dictEntry) {
		for (OreDictEntry entry : dictEntry) {
			if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry))) {
				if (entry.oreDictName.equals(GT_OreDictNames.craftingRedstoneTorch.toString())) {
					RecipeMaps.ASSEMBLING.factory().EUt(16).duration(800).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(OrePrefixes.plate, Materials.Iron, 1).output(GregTech_API.getGregTechComponent(30, 1)).buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(16).duration(800).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(OrePrefixes.plate, Materials.Aluminium, 1).output(GregTech_API.getGregTechComponent(87, 1)).buildAndRegister();
				} else if (entry.oreDictName.equals(GT_OreDictNames.craftingRawMachineTier01.toString())) {
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(800).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(new ItemStack(Blocks.noteblock, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)).output(new ItemStack(GregTech_API.sBlockList[1], 1, 66)).buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(800).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(new ItemStack(Blocks.stone_button, 1, GregTech_API.ITEM_WILDCARD_DAMAGE)).output(new ItemStack(GregTech_API.sBlockList[1], 1, 67)).buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(2).duration(1600).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(GregTech_API.getGregTechComponent(7, 1)).output(GT_ModHandler.getIC2Item("solarPanel", 1)).buildAndRegister();
					ItemStack machineBlock = new ItemStack(GregTech_API.sBlockList[1], 1, 79);
					List<ItemStack> filtered = entry.ores.stream().filter(s -> !s.isItemEqual(machineBlock)).collect(Collectors.toList());
					RecipeMaps.ASSEMBLING.factory().EUt(2).duration(1600).input(RecipeEntry.fromStacks(filtered, Match.DAMAGE)).input(GregTech_API.getGregTechComponent(22, 1)).output(new ItemStack(GregTech_API.sBlockList[1], 1, 79)).buildAndRegister();
				} else if (entry.oreDictName.equals(GT_OreDictNames.craftingGenerator.toString())) {
					RecipeMaps.ASSEMBLING.factory().EUt(4).duration(3200).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(OrePrefixes.gearGt, Materials.Steel, 1).output(GregTech_API.getGregTechComponent(25, 1)).buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(4).duration(3200).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(OrePrefixes.gearGt, Materials.StainlessSteel, 1).output(GregTech_API.getGregTechComponent(25, 1)).buildAndRegister();
				} else if (entry.oreDictName.equals(GT_OreDictNames.craftingWireCopper.toString())) {
					RecipeMaps.ASSEMBLING.factory().EUt(2).duration(1600).input(RecipeEntry.fromStacks(2, entry.ores, Match.DAMAGE)).input(OrePrefixes.plate, Materials.BatteryAlloy, 6).output(GT_Items.Battery_Hull_MV.get(1)).buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(800).input(RecipeEntry.fromStacks(3, entry.ores, Match.DAMAGE)).input(GT_Items.Circuit_Board_Basic.get(1)).output(GT_Items.Circuit_Basic.get(1)).buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(800).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(GT_Items.Circuit_Basic.get(1)).output(GT_ModHandler.getIC2Item("frequencyTransmitter", 1L)).buildAndRegister();
				} else if (entry.oreDictName.equals(GT_OreDictNames.craftingWireTin.toString())) {
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(800).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(OrePrefixes.plate, Materials.BatteryAlloy, 2).output(GT_Items.Battery_Hull_LV.get(1)).buildAndRegister();
					RecipeMaps.ASSEMBLING.factory().EUt(1).duration(800).input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE)).input(GT_Items.Circuit_Basic.get(1)).output(GT_ModHandler.getIC2Item("frequencyTransmitter", 1L)).buildAndRegister();
				}
			}
		}
	}
}
