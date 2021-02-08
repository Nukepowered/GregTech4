package gregtechmod.common.recipe.maps;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeEntry;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;

import net.minecraft.item.ItemStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class MaceratorRecipeMap extends DummyRecipeMap {

	public MaceratorRecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs) {
		super(minInputs, maxInputs, minOutputs, maxOutputs);
	}
	
	@Override
	public Recipe findRecipe(List<ItemStack> input) {
		for (ItemStack in : input) {
			if (GT_Utility.isStackValid(in)) {
				ItemStack inValid = in.copy();
				for (Entry<IRecipeInput, RecipeOutput> e : GT_ModHandler.getMaceratorRecipeList().entrySet()) {
					if (e.getKey().matches(inValid)) {
						inValid.stackSize = e.getKey().getAmount();
						return new Recipe(0, 2, 400, false,
								Collections.singleton(RecipeEntry.singleton(inValid)),
								e.getValue().items,
								Collections.emptyList());
					}
				}
			}
		}
		
		return null;
	}
}
