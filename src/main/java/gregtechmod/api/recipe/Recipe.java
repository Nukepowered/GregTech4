package gregtechmod.api.recipe;

import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This File contains the functions used for Recipes. Please do not include this File AT ALL in your Moddownload as it ruins compatibility
 * This is just the Core of my Recipe System, if you just want to GET the Recipes I add, then you can access this File.
 * Do NOT add Recipes using the Constructors inside this Class, The GregTech_API File calls the correct Functions for these Constructors.
 * 
 * I know this File causes some Errors, because of missing Main Functions, but if you just need to compile Stuff, then remove said erroreous Functions.
 */
public class Recipe {
	public static volatile int VERSION = 410;
	
	private List<Ingredient> inputs;
	private List<ItemStack> outputs;
	private List<ChancedOutput> chancedOutputs;
	
	private int duration;
	private int EUt;
	private int startEU;
	private boolean shaped;
	// Use this to just disable a specific Recipe, but the Config enables that already for every single Recipe.
	public boolean enabled = true;
	
	public Recipe(int startEU, int EUt, int duration, boolean shaped, Collection<Ingredient> inputs, Collection<ItemStack> outputs, Collection<ChancedOutput> chancedOutputs) {
		this.startEU 		= startEU;
		this.EUt 			= EUt;
		this.duration 		= duration;
		this.shaped 		= shaped;
		this.inputs 		= new ArrayList<>(inputs);
		this.outputs 		= new ArrayList<>(outputs);
		this.chancedOutputs = new ArrayList<>(chancedOutputs);
	}
	
	/**
	 * Default match method
	 * @param decrease if set, itemstacks size will dercease
	 * @param input collection of input stacks even nulls
	 * @return true if recipe matches for machine <b>input</b>
	 */
	public boolean matches(boolean decrease, List<ItemStack> input) {
		assert input != null : "Input can not be null!";
		assert input.isEmpty() : "Input can not be empty!";
		assert input.size() >= inputs.size() : "Can not be less inputs of machine than recipe has!";

		boolean result;
		if (shaped)
			result = checkShaped(decrease, input);
		else
			result = checkShapeless(decrease, input);
		
		return result;
	}
	
	/**
	 * Matching recipe to following input, in case of successfully found item in recipe input map will execute actionPerfomer
	 */
	private boolean checkShapeless(boolean decrease, List<ItemStack> inputs) {
		Pair<Boolean, Integer[]> items = this.matchItems(inputs);
		if (items.getKey()) {
			for (int i = 0; decrease && i < inputs.size(); i++) {
				ItemStack current = inputs.get(i);
				int newSize = items.getValue()[i];
				if (current == null || newSize == current.stackSize) continue;
				if (newSize > 0)
					current.stackSize = newSize;
				else
					inputs.set(i, null);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Will check if recipe matches for shaped recipe
	 * @param actionPerfomer will calls every loop, will put in a ItemStack of slot and amount needed for recipe
	 */
	private boolean checkShaped(boolean decrease, List<ItemStack> inputs) {
		for (int i = 0; i < this.inputs.size(); i++) {
			Ingredient ingr = this.inputs.get(i);
			ItemStack current = inputs.get(i);
			if (!ingr.match(current) || current.stackSize < ingr.getCount())
				return false;
		}
		
		for (int i = 0; decrease && i < this.inputs.size(); i++) { // Check this, not sure about working
			int toConsume = this.inputs.get(i).getCount();
			ItemStack current = inputs.get(i);
			if (current.stackSize == toConsume) {
				inputs.set(i, null);
			} else {
				current.stackSize -= toConsume;
			}
		}
		
		return true;
	}
	
	/**
	 * @param input
	 * @return boolean means if it could be consumed, Integer[] is mapping of item amounts per slot to change
	 */
	private Pair<Boolean, Integer[]> matchItems(List<ItemStack> input) {
		Integer[] itemAmountInSlots = new Integer[input.size()];
		
		for (int i = 0; i < input.size(); i++) {
			ItemStack itemInSlot = input.get(i);
			itemAmountInSlots[i] = itemInSlot == null ? 0 : itemInSlot.stackSize;
		}
		
		for (Ingredient ingr : inputs) {
			int ingrAmount = ingr.getCount();
			boolean consumed = false;
			
			if (ingrAmount == 0) {
				ingrAmount = 1;
				consumed = true;
			}
			
			for (int i = 0; i < input.size(); i++) {
				ItemStack inputStack = input.get(i);
				if (inputStack == null || !ingr.match(inputStack))
					continue;
				int toConsume = Math.min(itemAmountInSlots[i], ingrAmount);
				ingrAmount -= toConsume;
				if (!consumed)
					itemAmountInSlots[i] -= toConsume;
				if (ingrAmount == 0)
					break;
			}
			if (ingrAmount > 0)
				return Pair.of(false, itemAmountInSlots);
		}
		return Pair.of(true, itemAmountInSlots);
	}
	
	/////////////
	// GETTERS //
	/////////////
	
	public int getDuration() {
		return duration;
	}
	
	public int getEUtoStart() {
		return startEU;
	}
	
	public int getEUt() {
		return EUt;
	}
	
	public boolean isShaped() {
		return shaped;
	}
	
	/**
	 * @return list containing all possible outputs
	 */
	public List<ItemStack> getAllOutputs() {
		List<ItemStack> stacks = new ArrayList<>();
		stacks.addAll(outputs);
		chancedOutputs.forEach(ch -> stacks.add(ch.getStack()));
		return stacks;
	}
	
	/**
	* @return list of chanced outputs
	*/
	public List<ChancedOutput> getChancedOutputs() {
		return Collections.unmodifiableList(chancedOutputs);
	}
	
	/**
	 * @return list containing only 100% chanced outputs
	 */
	public List<ItemStack> getOutputs() {
		return Collections.unmodifiableList(outputs);
	}
	
	/**
	 * @return list of all recipe inputs
	 */
	public List<Ingredient> getInputs() {
		return Collections.unmodifiableList(inputs);
	}
	
	/** Get a recipe outputs with applied chance
	 * @param random will use to detect chance
	 * @return list of all recipe's outputs
	 */
	public List<ItemStack> getResults(Random random) {
		List<ItemStack> result = new ArrayList<>();
		result.addAll(GT_Utility.copy(outputs));
		result.addAll(chancedOutputs.stream()
			.map(c -> c.get(random))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList()));
		return result;
	}

	public void writeToNBT(NBTTagCompound data) {
		NBTTagList outputs = new NBTTagList();
		for (ItemStack out : this.getResults(new Random())) {
			NBTTagCompound stack = out.writeToNBT(new NBTTagCompound());
			outputs.appendTag(stack);
		}
		data.setInteger("recipeHash", this.hashCode());
		data.setTag("recipeOutput", outputs);
	}

	public static Recipe loadFromNBT(RecipeMap<?> recipeMap, NBTTagCompound data) {
		if (recipeMap != null && data.hasKey("recipeHash")) {
			int hash = data.getInteger("recipeHash");
			for (Recipe res : recipeMap.getRecipes()) {
				if (res.hashCode() == hash) {
					return res;
				}
			}
		}
		
		if (data.hasKey("recipeOutput")) {
			NBTTagList recipe = data.getTagList("recipeOutput", 10);
			List<ItemStack> stacks = new ArrayList<>();
			for (int i = 0; i < recipe.tagCount(); i++) {
				NBTTagCompound stackData = recipe.getCompoundTagAt(i);
				ItemStack stack = ItemStack.loadItemStackFromNBT(stackData);
				if (stack != null) {
					stacks.add(stack);
				} else GT_Log.log.error("Unable to load stack from tag: " + stackData);
			}
			
			return new Recipe(0, 0, 0, false,
					Collections.singleton(RecipeEntry.singleton(new ItemStack(Blocks.bedrock))),
					stacks,
					Collections.emptyList());
		}
		
		return null;
	}
	
	@Override
	public int hashCode() {
		return (startEU * EUt * duration) + (shaped ? 1 : 0) + inputs.hashCode() + outputs.hashCode() + chancedOutputs.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Recipe) {
			Recipe r = (Recipe) o;
			return r == this ||
					(r.duration == this.duration &&
					r.EUt == this.EUt &&
					r.startEU == this.startEU &&
					r.inputs.equals(inputs)) &&
					r.outputs.equals(outputs) &&
					r.chancedOutputs.equals(chancedOutputs);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("Recipe(startEU=%d, EUt=%d, duration=%d, shaped=%s, enabled=%s)\nin=%s\nout=%s\nchancedOut=%s",
				startEU,
				EUt,
				duration,
				Boolean.toString(shaped),
				Boolean.toString(enabled),
				inputs.toString(),
				outputs.toString(),
				chancedOutputs.toString());
	}
}