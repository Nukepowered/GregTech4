package gregtechmod.api.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import gregtechmod.api.util.GT_ItsNotMyFaultException;

/**
 * Unificated map of recipes for GT machines
 * @author TheDarkDnKTv
 *
 */
public class RecipeMap {
	
	private final RecipeFactory factory;
	private final List<Recipe> recipeList;
	
	public final int minInputs;
	public final int maxInputs;
	public final int minOutputs;
	public final int maxOutputs;
	
	public RecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs, RecipeFactory facorty) {
		this.minInputs 	= minInputs;
		this.maxInputs 	= maxInputs;
		this.minOutputs = minOutputs;
		this.maxOutputs = maxOutputs;
		this.factory 	= facorty;
		this.recipeList = new ArrayList<Recipe>();
		this.factory.se
	}
	
	/**
	 * @return a Recipe facotry of this recipe map
	 * @see RecipeFactory
	 */
	public RecipeFactory factory() {
		return factory;
	}
	
	/**
	 * Will check is recipe suit this recipe map, otherwise will throw exception
	 * @param recipe
	 * @throws GT_ItsNotMyFaultException
	 */
	private void assertValidRecipe(Recipe recipe) {
		// TODO: not inplemented method
		
		// throw new GT_RecipeException(recipe.toString() + " not suit this recipe map! Please ensure you used proper Recipe factory!");
	}
	
	public List<Recipe> getRecipes() {
		return Collections.unmodifiableList(recipeList);
	}
	
	/**
	 * Registering a recipe in this recipe map
	 */
	public boolean register(Recipe recipe) {
		this.assertValidRecipe(recipe);
		return recipeList.add(recipe);
	}
	
	/**
	 * Removes recipe from this recipe map
	 */
	public boolean remove(Recipe recipe) {
		Objects.requireNonNull(recipe);
		return this.recipeList.remove(recipe);
	}
}
