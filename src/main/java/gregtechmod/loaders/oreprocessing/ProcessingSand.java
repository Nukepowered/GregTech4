package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ProcessingSand implements IOreRecipeRegistrator {

	public ProcessingSand() {
		OrePrefixes.sand.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			if (this.isExecutable(aPrefix, this.getMaterial(aPrefix, entry))) {
				if (entry.oreDictName.equals("sandCracked")) {
					RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(2500)
						.input(RecipeEntry.fromStacks(16, entry.ores, Match.DAMAGE))
						.output(GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 8))
						.output(new ItemStack(Blocks.sand, 10))
						.buildAndRegister();
					
					for (ItemStack aStack : entry.ores) {
						if (aStack.getItem() instanceof ItemBlock) {
							if (aStack.getItem().getItemStackLimit(aStack) > GT_Mod.sBlockStackSize) {
								aStack.getItem().setMaxStackSize(GT_Mod.sBlockStackSize);
							}
	
							GregTech_API.sRecipeAdder.addJackHammerMinableBlock(Block.getBlockFromItem(aStack.getItem()), false);
						}
					}
				} else if (entry.oreDictName.equals("sandOil")) {
					RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(1000)
						.input(RecipeEntry.fromStacks(2, entry.ores, Match.DAMAGE))
						.output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Oil))
						.output(new ItemStack(Blocks.sand, 1))
						.buildAndRegister();
				}
			}
		}
	}
}
