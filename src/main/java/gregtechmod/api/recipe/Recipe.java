package gregtechmod.api.recipe;

import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ItemStackKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fluids.FluidStack;

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
	public static volatile int VERSION = 416;
	
	private List<Ingredient> itemInputs;
	private List<ItemStack> itemOutputs;
	private List<ChancedOutput> chancedOutputs;
	private List<FluidStack> fluidInputs;
	private List<FluidStack> fluidOutputs;
	
	private Map<String, Object> metadata;
	
	private int duration;
	private int EUt;
	private int startEU;
	private boolean shaped;
	// Use this to just disable a specific Recipe, but the Config enables that already for every single Recipe.
	public boolean enabled = true;
	
	public Recipe(int startEU, int EUt, int duration, boolean shaped, Collection<Ingredient> inputs, Collection<ItemStack> outputs, Collection<ChancedOutput> chancedOutputs) {
		this(startEU, EUt, duration, shaped, inputs, outputs, chancedOutputs, Collections.emptyList(), Collections.emptyList(), Collections.emptyMap());
	}
	
	public Recipe(int startEU, int EUt, int duration, boolean shaped,
			Collection<Ingredient> itemInputs,
			Collection<ItemStack> itemOutputs,
			Collection<ChancedOutput> chancedOutputs,
			Collection<FluidStack> fluidInputs,
			Collection<FluidStack> fluidOutputs,
			Map<String, Object> meta) {
		this.startEU 		= startEU;
		this.EUt 			= EUt;
		this.duration 		= duration;
		this.shaped 		= shaped;
		this.itemInputs 		= new ArrayList<>(itemInputs);
		this.itemOutputs 		= new ArrayList<>(itemOutputs);
		this.chancedOutputs 	= new ArrayList<>(chancedOutputs);
		this.fluidInputs 		= new ArrayList<>(fluidInputs);
		this.fluidOutputs		= new ArrayList<>(fluidOutputs);
		this.metadata			= new HashMap<>(meta);
	}
	
	/**
	 * Default match method
	 * @param decrease if set, itemstacks size will dercease
	 * @param input collection of input stacks even nulls
	 * @return true if recipe matches for machine <b>input</b>
	 */
	public boolean matches(boolean decrease, List<ItemStack> input, List<FluidStack> fluidInputs) {
		assert input != null && fluidInputs != null : "Item/Fluid input can not be null!";
		assert input.isEmpty() : "Input can not be empty!";
		assert input.size() >= itemInputs.size() : "Can not be less inputs of machine than recipe has!";

		boolean result;
		if (shaped)
			result = checkShaped(decrease, input, fluidInputs);
		else
			result = checkShapeless(decrease, input, fluidInputs);
		
		return result;
	}
	
	/**
	 * Matching recipe to following input, in case of successfully found item in recipe input map will execute actionPerfomer
	 */
	private boolean checkShapeless(boolean decrease, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
		Pair<Boolean, Integer[]> items = this.matchItems(inputs);
		Pair<Boolean, Integer[]> fluids = this.matchFluids(fluidInputs);
		if (items.getKey() && fluids.getKey()) {
			for (int i = 0; decrease && i < inputs.size() && !inputs.isEmpty(); i++) {
				ItemStack current = inputs.get(i);
				int newSize = items.getValue()[i];
				if (current == null || newSize == current.stackSize) continue;
				if (newSize > 0)
					current.stackSize = newSize;
				else
					inputs.remove(i);
			}
			
			for (int i = 0; decrease && i < fluidInputs.size() && !fluidInputs.isEmpty(); i++) {
				FluidStack current = fluidInputs.get(i);
				int newSize = fluids.getValue()[i];
				if (current == null || newSize == current.amount) continue;
				if (newSize > 0)
					current.amount = newSize;
				else
					fluidInputs.remove(i);
			}
			
			return true;
		}
		return false;
	}
	
	/**
	 * Will check if recipe matches for shaped recipe
	 */
	private boolean checkShaped(boolean decrease, List<ItemStack> inputs, List<FluidStack> fluidInputs) {
		for (int i = 0; i < this.itemInputs.size(); i++) {
			Ingredient ingr = this.itemInputs.get(i);
			ItemStack current = inputs.get(i);
			if (!ingr.match(current) || current.stackSize < ingr.getCount())
				return false;
		}
		
		for (int i = 0; i < this.fluidInputs.size(); i++) {
			FluidStack current = fluidInputs.get(i);
			FluidStack rCurrent = this.fluidInputs.get(i);
			if (!rCurrent.isFluidEqual(current) || current.amount < rCurrent.amount)
				return false;
		}
		
		for (int i = 0; decrease && i < this.itemInputs.size(); i++) { // Check this, not sure about working
			int toConsume = this.itemInputs.get(i).getCount();
			ItemStack current = inputs.get(i);
			if (current.stackSize == toConsume) {
				inputs.remove(i);
			} else {
				current.stackSize -= toConsume;
			}
		}
		
		for (int i = 0; decrease && i < this.fluidInputs.size(); i++) {
			int toConsume = this.fluidInputs.get(i).amount;
			FluidStack current = fluidInputs.get(i);
			if (current.amount == toConsume) {
				fluidInputs.remove(i);
			} else {
				current.amount -= toConsume;
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
		
		for (Ingredient ingr : getInputs()) {
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
	
	private Pair<Boolean, Integer[]> matchFluids(List<FluidStack> input) {
		Integer[] fluidAmountInSlots = new Integer[input.size()];
		
		for (int i = 0; i < input.size(); i++) {
			FluidStack fluidInSlot = input.get(i);
			fluidAmountInSlots[i] = fluidInSlot == null ? 0 : fluidInSlot.amount;
		}
		
		for (FluidStack ingr : getFluidInputs()) {
			int ingrAmount = ingr.amount;
			boolean consumed = false;
			
			if (ingrAmount == 0) {
				ingrAmount = 1;
				consumed = true;
			}
			
			for (int i = 0; i < input.size(); i++) {
				FluidStack inputStack = input.get(i);
				if (inputStack == null || !ingr.isFluidEqual(inputStack))
					continue;
				int toConsume = Math.min(fluidAmountInSlots[i], ingrAmount);
				ingrAmount -= toConsume;
				if (!consumed)
					fluidAmountInSlots[i] -= toConsume;
				if (ingrAmount == 0)
					break;
			}
			if (ingrAmount > 0)
				return Pair.of(false, fluidAmountInSlots);
		}
		
		return Pair.of(true, fluidAmountInSlots);
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
	
	public boolean isFluidRecipe() {
		return !fluidInputs.isEmpty() || !fluidOutputs.isEmpty();
	}
	
	public Object getMeta(String name) {
		return metadata.get(name);
	}

	public List<String> getMetaKeys() {
		return new ArrayList<>(metadata.keySet());
	}
	
	/**
	 * @return list containing all possible outputs
	 */
	public List<ItemStack> getAllOutputs() {
		List<ItemStack> stacks = new ArrayList<>();
		stacks.addAll(itemOutputs);
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
		return Collections.unmodifiableList(itemOutputs);
	}
	
	/**
	 * @return list of all recipe fluid outputs
	 */
	public List<FluidStack> getFluidOutputs() {
		return Collections.unmodifiableList(fluidOutputs);
	}
	
	/**
	 * @return list of all recipe inputs
	 */
	public List<Ingredient> getInputs() {
		return Collections.unmodifiableList(itemInputs);
	}
	
	/**
	 * @return list of all recipe fluid inputs
	 */
	public List<FluidStack> getFluidInputs() {
		return Collections.unmodifiableList(fluidInputs);
	}
	
	/** Get a recipe outputs with applied chance
	 * @param random will use to detect chance
	 * @return list of all recipe's outputs
	 */
	public List<ItemStack> getResults(Random random) {
		List<ItemStack> result = new ArrayList<>();
		result.addAll(GT_Utility.copy(itemOutputs));
		result.addAll(chancedOutputs.stream()
			.map(c -> c.get(random))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList()));
		return result;
	}
	
	////////////////////
	// NBT WRITE/READ //
	////////////////////
	
	public void writeToNBT(NBTTagCompound data) {
		NBTTagList itemOut = new NBTTagList();
		NBTTagList fluidOut = new NBTTagList();
		for (ItemStack out : this.getResults(new Random())) 
			itemOut.appendTag(out.writeToNBT(new NBTTagCompound()));
		for (FluidStack out : this.getFluidOutputs())
			fluidOut.appendTag(out.writeToNBT(new NBTTagCompound()));
		
		data.setInteger("recipeHash", this.hashCode());
		data.setTag("recipeItemOutput", itemOut);
		data.setTag("recipeFluidOutput", fluidOut);
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
		
		if (data.hasKey("recipeItemOutput") || data.hasKey("recipeFluidOutput")) {
			NBTTagList itemOut = data.getTagList("recipeItemOutput", 10);
			NBTTagList fluidOut = data.getTagList("recipeFluidOutput", 10);
			List<ItemStack> stacks = new ArrayList<>();
			List<FluidStack> fluids = new ArrayList<>();
			
			for (int i = 0; itemOut != null && i < itemOut.tagCount(); i++) {
				NBTTagCompound stackData = itemOut.getCompoundTagAt(i);
				ItemStack stack = ItemStack.loadItemStackFromNBT(stackData);
				if (stack != null) {
					stacks.add(stack);
				} else GT_Log.log.error("Unable to load item stack from tag: " + stackData);
			}
			
			for (int i = 0; fluidOut != null && i < fluidOut.tagCount(); i++) {
				NBTTagCompound stackData = fluidOut.getCompoundTagAt(i);
				FluidStack stack = FluidStack.loadFluidStackFromNBT(stackData);
				if (stack != null) {
					fluids.add(stack);
				} else GT_Log.log.error("Unable to load fluid stack from tag: " + stackData);
			}
			
			return new Recipe(0, 0, 0, false,
					Collections.emptyList(),
					stacks,
					Collections.emptyList(),
					Collections.emptyList(),
					fluids,
					Collections.emptyMap()) {
				
				@Override
				public boolean matches(boolean decrease, List<ItemStack> input, List<FluidStack> fluidInputs) {
					return false;
				}
			};
		}
		
		return null;
	}
	
	public static boolean doesListSame(List<ItemStack> list1, List<ItemStack> list2) {
		List<ItemStackKey> comp1 = Lists.transform(list1, s -> ItemStackKey.from(s));
		List<ItemStackKey> comp2 = Lists.transform(list2, s -> ItemStackKey.from(s));
		return comp1.size() == comp2.size() && comp1.containsAll(comp2);
	}
	
	///////////////
	// OVERRIDES //
	///////////////
	
	@Override
	public int hashCode() {
		int hash = (3 * startEU) + (3 * EUt) + (3 * duration) + (shaped ? 1 : 0)
				+ itemInputs.hashCode()
				+ itemOutputs.hashCode()
				+ chancedOutputs.hashCode()
				+ metadata.hashCode();
		for (FluidStack fluid : fluidInputs) 
			hash += GT_Utility.hashFluidStack(fluid);
		for (FluidStack fluid : fluidOutputs) 
			hash += GT_Utility.hashFluidStack(fluid);
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Recipe) {
			Recipe r = (Recipe) o;
			return r == this ||
					(r.duration == this.duration &&
					r.EUt == this.EUt &&
					r.startEU == this.startEU &&
					r.itemInputs.equals(itemInputs)) &&
					doesListSame(r.itemOutputs, itemOutputs) &&
					r.chancedOutputs.equals(chancedOutputs) &&
					r.fluidInputs.equals(fluidInputs) &&
					r.fluidOutputs.equals(fluidOutputs) &&
					r.metadata.equals(metadata);
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("Recipe(startEU=%d, EUt=%d, duration=%d, shaped=%s, enabled=%s)\nin=%s\nout=%s\nchancedOut=%s\nfluidIn=%s\nfluidOut=%s\nmeta=%s",
				startEU,
				EUt,
				duration,
				Boolean.toString(shaped),
				Boolean.toString(enabled),
				itemInputs.toString(),
				itemOutputs.toString(),
				chancedOutputs.toString(),
				fluidInputs.toString(),
				fluidOutputs.toString(),
				metadata.toString());
	}
}