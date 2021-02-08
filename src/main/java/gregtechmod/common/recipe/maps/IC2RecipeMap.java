package gregtechmod.common.recipe.maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeEntry;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;

import net.minecraft.item.ItemStack;

/** A Recipe map for usual IC2 machines, used as adapter
 * @author TheDarkDnKTv
 *
 */
public class IC2RecipeMap extends DummyRecipeMap {
	private final Map<IRecipeInput, RecipeOutput> recipeList;
	
	public IC2RecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs, Supplier<Map<IRecipeInput, RecipeOutput>> recipeMapGetter) {
		super(minInputs, maxInputs, minOutputs, maxOutputs);
		this.recipeList = recipeMapGetter.get();
	}
	
	@Override
	public Recipe findRecipe(List<ItemStack> input) {
		for (ItemStack in : input) {
			if (GT_Utility.isStackValid(in)) {
				ItemStack inValid = in.copy();
				for (Entry<IRecipeInput, RecipeOutput> e : recipeList.entrySet()) {
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
