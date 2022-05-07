package gregtechmod.api.recipe;

import java.util.*;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.Optional.Method;
import gregtechmod.api.GTConsts;
import gregtechmod.api.util.GT_RecipeException;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.integration.crafttweaker.recipe.CTRecipe;
import gregtechmod.integration.crafttweaker.recipe.CTRecipeFactory;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Unificated map of recipes for GT machines
 * @author TheDarkDnKTv
 *
 */
@ZenClass("mods.gregtechmod.recipe.RecipeMap")
public class RecipeMap<F extends RecipeFactory<F>> {
	
	protected final transient Map<Integer, List<Recipe>> MAPPINGS = new HashMap<>(); 
	
	protected final F factory;
	protected final List<Recipe> recipeList;
	
	public final int minInputs;
	public final int maxInputs;
	public final int minOutputs;
	public final int maxOutputs;
	public final int minFluidInputs;
	public final int maxFluidInputs;
	public final int minFluidOutputs;
	public final int maxFluidOutputs;
	
	public RecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs, F facorty) {
		this(minInputs, maxInputs, minOutputs, maxOutputs, 0, 0, 0, 0, facorty);
	}
	
	public RecipeMap(int minInputs, int maxInputs, int minOutputs, int maxOutputs, int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs, F facorty) {
		this.minInputs 	= minInputs;
		this.maxInputs 	= maxInputs;
		this.minOutputs = minOutputs;
		this.maxOutputs = maxOutputs;
		this.minFluidInputs = minFluidInputs;
		this.maxFluidInputs = maxFluidInputs;
		this.minFluidOutputs = minFluidOutputs;
		this.maxFluidOutputs = maxFluidOutputs;
		this.factory 	= facorty;
		this.recipeList = new ArrayList<Recipe>();
		if (facorty != null) this.factory.setRecipeMap(this);
	}
	
	/**
	 * @return a Recipe facotry of this recipe map
	 * @see RecipeFactory
	 */
	public F factory() {
		factory.reset();
		return factory;
	}
	
	public Recipe findRecipe(List<ItemStack> input, List<FluidStack> fluidInputs, Predicate<Recipe> metaChecker) {
		Set<Recipe> recipesTotal = this.getMappedRecipes(input, fluidInputs);
		Recipe result = null;
		if (!recipesTotal.isEmpty())
			result = findRecipe(recipesTotal, input, fluidInputs, metaChecker);
		return result != null && result.enabled ? result : null;
	}
	
	protected Recipe findRecipe(Collection<Recipe> recipes, List<ItemStack> input, List<FluidStack> fluidInputs, Predicate<Recipe> metaChecker) {
		if (recipes != null) {
			for (Recipe recipe : recipes) {
				if (metaChecker.test(recipe) && recipe.matches(false, input, fluidInputs)) {
					return recipe;
				}
			}
		}
		
		return null;
	}
	
	public Set<Recipe> getMappedRecipes(List<ItemStack> input, List<FluidStack> fluidInputs) {
		Set<Recipe> recipesTotal = new HashSet<>();
		for (FluidStack fluid : fluidInputs) {
			if (GT_Utility.isFluidStackValid(fluid)) {
				List<Recipe> recipesFluid = MAPPINGS.get(GT_Utility.fluidStackToInt(fluid));
				if (recipesFluid != null) 
					recipesTotal.addAll(recipesFluid);
			}
		}
		
		for (ItemStack item : input) {
			if (GT_Utility.isStackValid(item)) {
				List<Recipe> recipesItems = MAPPINGS.get(GT_Utility.stackToInt(item));
				List<Recipe> recipesItemsWild = MAPPINGS.get(GT_Utility.stackToInt(item, true));
				if (recipesItems != null) 
					recipesTotal.addAll(recipesItems);
				if (recipesItemsWild != null) 
					recipesTotal.addAll(recipesItemsWild);
			}
		}

		return recipesTotal;
	}
	
	/**
	 * Will check is recipe suit this recipe map, otherwise will throw exception
	 * @param recipe
	 * @throws GT_RecipeException
	 */
	protected void assertValidRecipe(Recipe recipe) {
		String error = "";
		
		if (recipe.getInputs().size() < minInputs) 				error += " - Inputs size less than minimum required(" 				+ minInputs + 		"), current: " + recipe.getInputs().size();
		if (recipe.getInputs().size() > maxInputs) 				error += " - Inputs size bigger than maximum allowed(" 				+ maxInputs + 		"), current: " + recipe.getInputs().size();
		if (recipe.getAllOutputs().size() < minOutputs) 		error += " - Total possible outputs less than minimum required(" 	+ minOutputs + 		"), current: " + recipe.getAllOutputs().size();
		if (recipe.getAllOutputs().size() > maxOutputs) 		error += " - Total possible outputs bigger than maximum allowed(" 	+ maxOutputs + 		"), current: " + recipe.getAllOutputs().size();
		
		if (recipe.getFluidInputs().size() < minFluidInputs)	error += " - Fluid inputs size less than minimum required("			+ minFluidInputs + 	"), current: " + recipe.getFluidInputs().size();
		if (recipe.getFluidInputs().size() > maxFluidInputs)	error += " - Fluid inputs size bigger than maximum allowed("		+ maxFluidInputs + 	"), current: " + recipe.getFluidInputs().size();
		if (recipe.getFluidOutputs().size() < minFluidOutputs)	error += " - Fluid outputs size less than minimum required("		+ minFluidOutputs + "), current: " + recipe.getFluidOutputs().size();
		if (recipe.getFluidOutputs().size() > maxFluidOutputs)	error += " - Fluid outputs outputs bigger than maximum allowed("	+ maxFluidOutputs + "), current: " + recipe.getFluidOutputs().size();
		
		
		if (!error.isEmpty()) throw new GT_RecipeException(recipe.toString() + " thrown exception on registeration for RecipeMap:\n" + error);
	}
	
	protected void createMappings(Recipe toMap) {
		IntConsumer addToMap = value -> {
			List<Recipe> recipes = MAPPINGS.get(value);
			recipes = recipes == null ? new ArrayList<>() : recipes;
			recipes.add(toMap);
			MAPPINGS.put(value, recipes);
		};
		
		for (Ingredient ingr : toMap.getInputs()) { // Can eat additional time of loading, but may improove recipe searching speed
			for (ItemStack variant : ingr.getVariants()) {
				addToMap.accept(GT_Utility.stackToInt(variant, ingr.isWildcard()));
			}
		}
		
		for (FluidStack fluid : toMap.getFluidInputs()) {
			addToMap.accept(GT_Utility.fluidStackToInt(fluid));
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
		if (recipeList.indexOf(recipe) < 0) {
			recipeList.add(recipe);
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
		
		for (Ingredient ingr : recipe.getInputs()) {
			for (ItemStack var : ingr.getVariants()) {
				int value = GT_Utility.stackToInt(var);
				int wild = GT_Utility.stackToInt(var, true);
				List<Recipe> var1 = MAPPINGS.get(value);
				List<Recipe> var2 = MAPPINGS.get(wild);
				if (var1 != null)
					var1.remove(recipe);
				if (var2 != null)
					var2.remove(recipe);
				MAPPINGS.replace(value, var1);
				MAPPINGS.replace(wild, var2);
			}
		}
		
		for (FluidStack fluid : recipe.getFluidInputs()) {
			int value = GT_Utility.fluidStackToInt(fluid);
			List<Recipe> var1 = MAPPINGS.get(value);
			if (var1 != null)
				var1.remove(recipe);
			MAPPINGS.replace(value, var1);
		}
		
		return this.recipeList.remove(recipe);
	}

	/*** CRAFT TWEAKER START ***/

	@ZenMethod("getRecipes")
	@Method(modid = GTConsts.CT_MODID)
	public List<CTRecipe> getRecipesCT() {
		return this.recipeList.stream()
			.map(CTRecipe::new)
			.collect(Collectors.toList());
	}

	@ZenMethod("findRecipe")
	@Method(modid = GTConsts.CT_MODID)
	public CTRecipe findRecipeCT(IItemStack[] itemsIn, ILiquidStack[] liquidsIn) {
		List<ItemStack> items = Lists.newArrayList(MineTweakerMC.getItemStacks(itemsIn));
		List<FluidStack> fluids = Lists.newArrayList(MineTweakerMC.getLiquidStacks(liquidsIn));
		Recipe recipe = this.findRecipe(items, fluids, meta -> true);
		return recipe == null ? null : new CTRecipe(recipe);
	}

	@ZenMethod("register")
	@Method(modid = GTConsts.CT_MODID)
	public boolean registerCT(CTRecipe recipe) {
		return this.register(recipe.backingRecipe);
	}

	@ZenMethod
	@Method(modid = GTConsts.CT_MODID)
	public boolean remove(CTRecipe recipe) {
		return recipe == null ? true :
			this.remove(recipe.backingRecipe);
	}

	@ZenMethod("factory")
	@Method(modid = GTConsts.CT_MODID)
	public CTRecipeFactory factoryCT() {
		return new CTRecipeFactory(this.factory());
	}

	@ZenGetter("minInputs")
	public int getMinInputs() {
		return minInputs;
	}

	@ZenGetter("maxInputs")
	public int getMaxInputs() {
		return maxInputs;
	}

	@ZenGetter("minOutputs")
	public int getMinOutputs() {
		return minOutputs;
	}

	@ZenGetter("maxOutputs")
	public int getMaxOutputs() {
		return maxOutputs;
	}

	@ZenGetter("minFluidInputs")
	public int getMinFluidInputs() {
		return minFluidInputs;
	}

	@ZenGetter("maxFluidInputs")
	public int getMaxFluidInputs() {
		return maxFluidInputs;
	}

	@ZenGetter("minFluidOutputs")
	public int getMinFluidOutputs() {
		return minFluidOutputs;
	}

	@ZenGetter("maxFluidOutputs")
	public int getMaxFluidOutputs() {
		return maxFluidOutputs;
	}

	/*** CRAFT TWEAKER END ***/

	@Override
	public boolean equals(Object o) {
		return o instanceof RecipeMap && o == this;
	}
	
	@Override
	public int hashCode() {
		return minInputs + maxInputs + minOutputs + maxOutputs + minFluidInputs + maxFluidInputs + minFluidOutputs + maxFluidOutputs + factory.hashCode() + System.identityHashCode(recipeList); // To be sure, it is same list
	}
	
	@Override
	public String toString() {
		return String.format("RecipeMap(item-in: %d-%d, item-out: %d-%d, fluid-in: %d-%d, fluid-out: %d-%d)%s",
				minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, recipeList);
	}
}
