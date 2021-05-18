package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.RecipeHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.common.recipe.RecipeEntry.Match;

import net.minecraft.item.ItemStack;

public class ProcessingBlock implements IOreRecipeRegistrator {

	public ProcessingBlock() {
		OrePrefixes.block.add(this);
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial) ) {
				int outFromBlock = aMaterial == Materials.NetherQuartz || aMaterial == Materials.CertusQuartz ? 4 : 9;
				
				if (GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial) != null) 
					RecipeMaps.CUTTING.factory().EUt(30).duration(Math.max(aMaterial.getMass() * 10, 1))
						.input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE))
						.output(GT_OreDictUnificator.get(OrePrefixes.plate, aMaterial, 9))
						.buildAndRegister();
				ItemStack tStack1 = GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial, 1L);
				ItemStack tStack2 = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L);
				ItemStack tStack3 = GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, 1L);
				
				if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.storageblockcrafting, OrePrefixes.block.get(aMaterial), false)) {
					if (tStack1 == null && tStack2 == null && tStack3 != null) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L),
								new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), OrePrefixes.dust.get(aMaterial) }));
					}

					if (tStack2 != null) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L),
								new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), OrePrefixes.gem.get(aMaterial) }));
					}

					if (tStack1 != null) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.block, aMaterial, 1L),
								new Object[] { "XXX", "XXX", "XXX", Character.valueOf('X'), OrePrefixes.ingot.get(aMaterial) }));
					}
				}

				if (tStack1 != null) {
					tStack1.stackSize = outFromBlock;
				}

				if (tStack2 != null) {
					tStack2.stackSize = outFromBlock;
				}

				if (tStack3 != null) {
					tStack3.stackSize = outFromBlock;
				}

				if (GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.storageblockdecrafting, OrePrefixes.block.get(aMaterial), tStack2 != null)) {
					if (tStack3 != null) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addShapelessCraftingRecipe(tStack3, new Object[] { OrePrefixes.block.get(aMaterial) }));
					}

					if (tStack2 != null) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addShapelessCraftingRecipe(tStack2, new Object[] { OrePrefixes.block.get(aMaterial) }));
					}

					if (tStack1 != null) {
						RecipeHandler.executeOnFinish(() -> GT_ModHandler.addShapelessCraftingRecipe(tStack1, new Object[] { OrePrefixes.block.get(aMaterial) }));
					}
				}

				if (aMaterial == Materials.Mercury) {
					GT_Log.log.error("\'blockQuickSilver\'?, In which Ice Desert can you actually place this as a solid Block?");
				} else if (aMaterial == Materials.Iron) {
					RecipeMaps.ASSEMBLING.factory().EUt(4).duration(400)
						.input(RecipeEntry.fromStacks(entry.ores, Match.DAMAGE))
						.input(GT_Items.IC2_Compressed_Coal_Ball.get(8))
						.output(GT_Items.IC2_Compressed_Coal_Chunk.get(1))
						.buildAndRegister();
				}
			}
		}
	}
}
