package gregtechmod.common.recipe.maps;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeEntry;

import net.minecraft.item.ItemStack;

/** A Recipe map for usual IC2 machines, used as adapter
 * @author TheDarkDnKTv
 *
 */
public class IC2RecipeMap extends DummyRecipeMap {
	private final Function<ItemStack, List<ItemStack>> recipeGetter;
	
	public IC2RecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs, Function<ItemStack, List<ItemStack>> recipeGetter) {
		super(minInputs, maxInputs, minOutputs, maxOutputs);
		this.recipeGetter = recipeGetter;
	}
	
	@Override
	public Recipe findRecipe(List<ItemStack> input) {
		for (ItemStack in : input) {
			if (GT_Utility.isStackValid(in)) {
				ItemStack inValid = in.copy();
				List<ItemStack> results = recipeGetter.apply(inValid);
				if (results != null && !results.isEmpty()) {
					inValid.stackSize = in.stackSize - inValid.stackSize;
					return new Recipe(0, 2, 200, false,
							Collections.singleton(RecipeEntry.singleton(inValid)),
							results,
							Collections.emptyList());
				}
			}
		}
		
		return null;
	}
}
