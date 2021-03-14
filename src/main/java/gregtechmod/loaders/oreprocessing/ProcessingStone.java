package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.OreDictEntry;

import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ProcessingStone implements IOreRecipeRegistrator {

	public ProcessingStone() {
		OrePrefixes.stone.add(this);
	}

	@Override
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				switch (aMaterial) {
				case Endstone:
					RecipeMaps.GRINDER.factory().EUt(120).duration(16 * 100).input(RecipeEntry.fromStacks(16, entry.ores))
							.input(GT_ModHandler.getWater(1000))
							.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Endstone, 16L),
									GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L))
							.buildAndRegister();
					GT_ModHandler.addPulverisationRecipe(entry, 1,
							GT_OreDictUnificator.get(OrePrefixes.dustImpure, Materials.Endstone, 1L),
							GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Tungsten, 1L), 5);
					break;
				case Netherrack:
					RecipeMaps.GRINDER.factory().EUt(120).duration(16 * 100).input(RecipeEntry.fromStacks(16, entry.ores))
							.input(GT_ModHandler.getWater(1000))
							.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Netherrack, 16L),
									GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L))
							.buildAndRegister();
					RecipeMaps.GRINDER.factory().EUt(120).duration(16 * 100).input(RecipeEntry.fromStacks(16, entry.ores))
							.input(OrePrefixes.cell, Materials.Mercury)
							.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Netherrack, 8L),
									GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 5L),
									GT_Items.Cell_Empty.get(1))
							.buildAndRegister();
					GT_ModHandler.addPulverisationRecipe(entry, 1,
							GT_OreDictUnificator.get(OrePrefixes.dustImpure, Materials.Netherrack, 1L),
							GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L), 5);
					break;
				case NetherBrick:
					RecipeMaps.GRINDER.factory().EUt(120).duration(8 * 100).input(RecipeEntry.fromStacks(8, entry.ores))
							.input(GT_ModHandler.getWater(1000))
							.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Netherrack, 16L),
									GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 1L))
							.buildAndRegister();
					RecipeMaps.GRINDER.factory().EUt(120).duration(8 * 100).input(RecipeEntry.fromStacks(8, entry.ores))
							.input(OrePrefixes.cell, Materials.Mercury)
							.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Netherrack, 8L),
									GT_OreDictUnificator.get(OrePrefixes.nugget, Materials.Gold, 5L),
									GT_Items.Cell_Empty.get(1))
							.buildAndRegister();
					break;
				case Obsidian:
					RecipeMaps.ASSEMBLING.factory().EUt(4).duration(400)
							.inputs(GT_Items.IC2_Compressed_Coal_Ball.get(8)).input(RecipeEntry.fromStacks(1, entry.ores))
							.output(GT_Items.IC2_Compressed_Coal_Chunk.get(1)).buildAndRegister();
					RecipeMaps.CUTTING.factory().EUt(32).duration(200).input(RecipeEntry.fromStacks(1, entry.ores))
							.output(GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L)).buildAndRegister();
					GT_ModHandler.addPulverisationRecipe(entry, 1,
							GT_ModHandler.getRCItem("cube.crushed.obsidian", 1L,
									GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L)),
							GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L), 10);
					for (ItemStack aStack : entry.ores) {
						Block aBlock = Block.getBlockFromItem(aStack.getItem());
						if (aBlock != null)  aBlock.setResistance(20.0F);
					}
					break;
				case GraniteBlack:
					RecipeMaps.ASSEMBLING.factory().EUt(4).duration(400)
							.inputs(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Advanced, 1L))
							.input(RecipeEntry.fromStacks(1, entry.ores))
							.output(GT_ModHandler.getIC2Item("reinforcedStone", 8L)).buildAndRegister();
					RecipeMaps.GRINDER.factory().EUt(120).duration(16 * 100).input(RecipeEntry.fromStacks(16, entry.ores))
							.input(GT_ModHandler.getWater(1000))
							.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 16L),
									GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Thorium, 1L))
							.buildAndRegister();
					RecipeMaps.CUTTING.factory().EUt(32).duration(200).input(RecipeEntry.fromStacks(1, entry.ores))
							.output(GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L)).buildAndRegister();
					GT_ModHandler.addPulverisationRecipe(entry, 1,
							GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1L),
							GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Thorium, 1L), 1);
					break;
				case GraniteRed:
					RecipeMaps.ASSEMBLING.factory().EUt(4).duration(400)
							.inputs(GT_OreDictUnificator.get(OrePrefixes.plateAlloy, Materials.Advanced, 1L))
							.input(RecipeEntry.fromStacks(8, entry.ores))
							.output(GT_ModHandler.getIC2Item("reinforcedStone", 8L)).buildAndRegister();
					RecipeMaps.GRINDER.factory().EUt(120).duration(16 * 100).input(RecipeEntry.fromStacks(16, entry.ores))
							.input(GT_ModHandler.getWater(1000))
							.outputs(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 16L),
									GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Uranium, 1L))
							.buildAndRegister();
					RecipeMaps.CUTTING.factory().EUt(32).duration(200).input(RecipeEntry.fromStacks(1, entry.ores))
							.output(GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 1L)).buildAndRegister();
					GT_ModHandler.addPulverisationRecipe(entry, 1,
							GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1L),
							GT_OreDictUnificator.get(OrePrefixes.dustSmall, Materials.Uranium, 1L), 1);
					break;
				case Sand:
					GT_ModHandler.addPulverisationRecipe(entry, 1, new ItemStack(Blocks.sand, 1, 0), null, 10);
					break;
				case Redrock:
				case Marble:
				case Basalt:
				case Quartzite:
					GT_ModHandler.addPulverisationRecipe(entry, 1,
							GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1L),
							GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L), 10);
					break;
				case Flint:
					GT_ModHandler.addPulverisationRecipe(entry, 1,
							GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 2L), new ItemStack(Items.flint, 1), 50);
					break;
				default:
					break;
				}
			}
		}
	}
}
