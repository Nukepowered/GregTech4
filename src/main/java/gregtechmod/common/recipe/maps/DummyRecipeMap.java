package gregtechmod.common.recipe.maps;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_ItsNotMyFaultException;
import gregtechmod.common.recipe.factory.SimpleRecipeFactory;

/**
 * A dummy recipe map used for custom recipe providers as for macerator of extractor
 * @author TheDarkDnKTv
 *
 */
public abstract class DummyRecipeMap extends RecipeMap<SimpleRecipeFactory> {

	protected DummyRecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs) {
		super(minInputs, maxInputs, minOutputs, maxOutputs, null);
	}
	
	protected DummyRecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInput, int maxFluidInput, int minFluidOutput, int maxFluidOutput) {
		super(minInputs, maxInputs, minOutputs, maxOutputs, minFluidInput, maxFluidInput, minFluidOutput, maxFluidOutput, null);
	}
	
	@Override
	public final SimpleRecipeFactory factory() {
		throw new GT_ItsNotMyFaultException("It is not allowed to call factory() method for Dummy recipe maps!\n This maps type created to GENERATE recipes in runtime, not to store it.");
	}
	
	@Override
	public final boolean register(Recipe recipe) {
		return false;
	}
	
	@Override
	public final boolean remove(Recipe recipe) {
		return false;
	}
}
