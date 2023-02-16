package gregtechmod.common.recipe.maps;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class FurnanceRecipeMap extends DummyRecipeMap {

	/**
	 * @param minInputs
	 * @param maxInputs
	 * @param minOutputs
	 * @param maxOutputs
	 */
	public FurnanceRecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs) {
		super(minInputs, maxInputs, minOutputs, maxOutputs);
	}

	@Override
	public Recipe findRecipe(List<ItemStack> inputs, List<FluidStack> fluidInputs, Predicate<Recipe> metaChecker) {
		ItemStack output;

		for (int i = 0; i < inputs.size(); i++) {
			ItemStack in = inputs.get(i);
			if (in != null && null != (output = GT_ModHandler.getSmeltingOutput(in, false, null))) {
				in = in.copy();
				in.stackSize = 1;
				// It shall cook at 3 EU/t, if this Machine is overclocked then it will consume more
				// The time it usually needs, the Heating Coils re decreasing this Time, and if the Machine is overclocked, then it gets processed faster
				return new Recipe(0, 3, 130, false,
					Collections.singletonList(RecipeEntry.singleton(in, Match.STRICT)),
					Collections.singletonList(output),
					Collections.emptyList()
				);
			}
		}
		
		return null;
	}
}
