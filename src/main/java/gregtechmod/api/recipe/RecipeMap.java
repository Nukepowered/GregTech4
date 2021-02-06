package gregtechmod.api.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import gregtechmod.api.util.GT_RecipeException;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

/**
 * Unificated map of recipes for GT machines
 * @author TheDarkDnKTv
 *
 */
public class RecipeMap<F extends RecipeFactory<F>> {
	
	private final transient Map<Integer, List<Recipe>> MAPPINGS = new HashMap<>(); 
	
	private final F factory;
	private final List<Recipe> recipeList;
	
	public final int minInputs;
	public final int maxInputs;
	public final int minOutputs;
	public final int maxOutputs;
	
	public RecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs, F facorty) {
		this.minInputs 	= minInputs;
		this.maxInputs 	= maxInputs;
		this.minOutputs = minOutputs;
		this.maxOutputs = maxOutputs;
		this.factory 	= facorty;
		this.recipeList = new ArrayList<Recipe>();
		this.factory.setRecipeMap(this);
	}
	
	/**
	 * @return a Recipe facotry of this recipe map
	 * @see RecipeFactory
	 */
	public F factory() {
		return factory;
	}
	
	public Recipe findRecipe(List<ItemStack> input) {
		if (input.size() < minInputs) 
			throw new IllegalArgumentException("Inputs of machine can not be smaller than minimum RecipeMap inputs amount");
		
		ItemStack first = null;
		for (ItemStack stack : input) {
			if (GT_Utility.isStackValid(stack)) {
				first = stack;
				break;
			}
		}
		
		Recipe result = null;
		if (GT_Utility.isStackValid(first)) {
			List<Recipe> recipes 		= MAPPINGS.get(GT_Utility.stackToInt(first));
			List<Recipe> recipesWild 	= MAPPINGS.get(GT_Utility.stackToWildcard(first));
			if (recipes != null)
				result = findRecipe(recipes, input);
			if (result == null && recipesWild != null)
				result = findRecipe(recipes, input);
		}
		
		return result;
	}
	
	private Recipe findRecipe(List<Recipe> recipes, List<ItemStack> input) {
		for (Recipe recipe : recipes) {
			if (recipe.matches(false, input)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	/**
	 * Will check is recipe suit this recipe map, otherwise will throw exception
	 * @param recipe
	 * @throws GT_RecipeException
	 */
	private void assertValidRecipe(Recipe recipe) {
		String error = "";
		
		if (recipe.getInputs().size() < minInputs) 			error += " - Inputs size less than minimum required(" 				+ minInputs + 	"), current: " + recipe.getInputs().size();
		if (recipe.getInputs().size() > maxInputs) 			error += " - Inputs size bigger than maximum allowed(" 				+ maxInputs + 	"), current: " + recipe.getInputs().size();
		if (recipe.getAllOutputs().size() < minOutputs) 	error += " - Total possible outputs less than minimum required(" 	+ minOutputs + 	"), current: " + recipe.getAllOutputs().size();
		if (recipe.getAllOutputs().size() > maxOutputs) 	error += " - Total possible outputs bigger than maximum allowed(" 	+ maxOutputs + 	"), current: " + recipe.getAllOutputs().size();
		
		if (!error.isEmpty()) throw new GT_RecipeException(recipe.toString() + " thrown exception on registeration for RecipeMap:\n" + error);
	}
	
	private void createMappings(Recipe toMap) {
		for (Ingredient ingr : toMap.getInputs()) { // Can eat additional time of loading, but may improove recipe searching speed
			for (ItemStack variant : ingr.getVariants()) {
				Integer value = ingr.isWildcard() ? GT_Utility.stackToWildcard(variant) : GT_Utility.stackToInt(variant);
				List<Recipe> recipes = MAPPINGS.get(value);
				recipes = recipes == null ? new ArrayList<>() : recipes;
				recipes.add(toMap);
				MAPPINGS.put(value, recipes);
			}
		}
	}
	
	public List<Recipe> getRecipes() {
		return Collections.unmodifiableList(recipeList);
	}
	
	/**
	 * Registering a recipe in this recipe map
	 */
	public boolean register(Recipe recipe) {
		this.assertValidRecipe(recipe);
		if (recipeList.add(recipe)) {
			 this.createMappings(recipe);
			 return true;
		}
		
		return false;
	}
	
	/**
	 * Removes recipe from this recipe map
	 */
	public boolean remove(Recipe recipe) {
		Objects.requireNonNull(recipe);
		return this.recipeList.remove(recipe);
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof RecipeMap && o == this;
	}
	
	@Override
	public int hashCode() {
		return minInputs + maxInputs + minOutputs + maxOutputs + factory.hashCode() + System.identityHashCode(recipeList); // To be sure, it is same list
	}
	
	@Override
	public String toString() {
		return String.format("RecipeMap(item-in: %d-%d, item-out: %d-%d)%s", minInputs, maxInputs, minOutputs, maxOutputs, recipeList);
	}
}
