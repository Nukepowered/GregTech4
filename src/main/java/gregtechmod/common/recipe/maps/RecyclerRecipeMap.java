package gregtechmod.common.recipe.maps;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.ChancedStack;
import gregtechmod.common.recipe.RecipeEntry;
import gregtechmod.common.recipe.RecipeEntry.Match;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class RecyclerRecipeMap extends DummyRecipeMap {
	public RecyclerRecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs) {
		super(minInputs, maxInputs, minOutputs, maxOutputs);
	}

	@Override
	public Recipe findRecipe(List<ItemStack> inputs, List<FluidStack> fluidInputs, Predicate<Recipe> metaChecker) {
		for (ItemStack slot : inputs) {
			if (GT_Utility.isStackValid(slot)) {
	    		ItemStack instance = slot.copy();
	    		instance.stackSize = 1;
	    		
	    		ChancedStack st = new ChancedStack(GT_Items.IC2_Scrap.get(1), (GT_ModHandler.getRecyclerOutput(instance, 0) != null) ? 12_50 : 0);
	    		return new Recipe(0, 1, 45, false,
	    				Collections.singleton(RecipeEntry.singleton(instance, Match.STRICT)),
	    				Collections.emptyList(),
	    				Collections.singleton(st));
	    	}
		}
		
		return null;
	}
}
