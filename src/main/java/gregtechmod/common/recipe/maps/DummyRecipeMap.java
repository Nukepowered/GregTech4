package gregtechmod.common.recipe.maps;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
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
	
	@Override
	public SimpleRecipeFactory factory() {
		return null;
	}
	
	@Override
	public boolean register(Recipe recipe) {
		return false;
	}
	
	@Override
	public boolean remove(Recipe recipe) {
		return false;
	}
}
