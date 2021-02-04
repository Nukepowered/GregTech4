package gregtechmod.api.recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author TheDarkDnKTv
 *
 */
public class RecipeMap {
	
	private final RecipeFactory factory;
	private final List<Recipe> recipeList;
	private final int minInputs;
	private final int maxInputs;
	private final int minOutputs;
	private final int maxOutputs;
	
	public RecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs) {
		this.minInputs 	= minInputs;
		this.maxInputs 	= maxInputs;
		this.minOutputs = minOutputs;
		this.maxOutputs = maxOutputs;
		recipeList 		= new ArrayList<Recipe>();
		factory 		= new RecipeFactory(this);
	}
	
	public RecipeFactory factory() {
		return factory;
	}
}
