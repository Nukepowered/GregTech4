package gregtechmod.api.recipe;

import java.util.ArrayList;
import java.util.List;

import gregtechmod.api.util.GT_Utility;

import net.minecraft.item.ItemStack;

/**
 * A Factory class for creating Recipe instances
 * @author TheDarkDnKTv
 *
 */
public abstract class RecipeFactory {
	
	protected RecipeMap map;
	protected List<ItemStack> outputItems;
	protected List<ChancedOutput> chancedOutput;
	protected List<Ingredient> inputItems;
	protected int EUt;
	protected int startEU;
	protected int duration;
	protected boolean shaped;
	
	protected StringBuilder errors;
	// TODO maybe create a fluid I/O
	
	
	protected RecipeFactory() {
		this.outputItems 	= new ArrayList<>();
		this.chancedOutput 	= new ArrayList<>();
		this.inputItems 	= new ArrayList<>();
		this.reset();
	}
	
	protected RecipeFactory(RecipeMap map) {
		this();
		this.map = map;
	}
	
	/** INTERNAL USE ONLY */
	protected void reset() {
		errors = new StringBuilder();
		outputItems.clear();
		chancedOutput.clear();
		inputItems.clear();
		EUt = -1;
		startEU = 0;
		duration = -1;
		shaped = false;
	}
	
	public void setRecipeMap(RecipeMap map) {
		assert 		map != null : "Supplied a null map!";
		assert this.map == null : "It is not allowed to change recipe map if alredy set!";
		
		this.map = map;
	}
	
	public RecipeFactory EUt(int energy) {
		EUt = energy;
		return this;
	}
	
	public RecipeFactory duration(int amount) {
		duration = amount;
		return this;
	}
	
	public RecipeFactory setShaped(boolean value) {
		this.shaped = value;
		return this;
	}
	
	public abstract RecipeFactory nonConsumable(ItemStack stack);
	
	public RecipeFactory input(ItemStack stack) {
		return this.input(stack, true, false);
	}
	
	public RecipeFactory input(ItemStack stack, boolean checkNBT) {
		return this.input(stack, true, checkNBT);
	}
	
	/**
	 * @param checkDamage if false, will check by wildcard (any item)
	 * @param checkNBT will check is NBT tags equals
	 */
	public abstract RecipeFactory input(ItemStack stack, boolean checkDamage, boolean checkNBT);
	
	public abstract RecipeFactory inputs(ItemStack...stacks);
	
	public RecipeFactory input(String oreDict) {
		return this.input(oreDict, 1);
	}
	
	public abstract RecipeFactory input(String oreDict, int amount);
	
	public RecipeFactory input(Ingredient ingredient) {
		if (ingredient != null && !ingredient.getVariants().isEmpty()) {
			this.inputItems.add(ingredient);
		} else {
			errors.append(" - Supplied wrong ingredient: ");
			errors.append(ingredient == null ? "null" : (ingredient.getCount() + "x of " + ingredient.getVariants()));
			errors.append('\n');
		}
		
		return this;
	}
	
	public RecipeFactory output(ItemStack stack) {
		if (GT_Utility.isStackInvalid(stack))
			errors.append("Found non-valid stack: " + stack);
		this.outputItems.add(stack);
		return this;
	}
	
	public RecipeFactory outputs(ItemStack...stacks) {
		for (ItemStack stack : stacks)
			this.output(stack);
		return this;
	}
	
	/**
	 * Building the recipe
	 * @return instance of builded recipe
	 * @throws gregtechmod.api.util.GT_RecipeException if missed some attributes of recipe
	 */
	public abstract Recipe build();
	
	/**
	 * Building recipe and registering it in recipe map
	 * @return true if recipe was registered
	 * @throws GT_ItsNotMyFaultException if missed some attributes of recipe
	 */
	public boolean buildAndRegister() {
		Recipe result = this.build();
		if (result != null) {
			return this.map.register(result);
		}
		
		return false;
	}
}
