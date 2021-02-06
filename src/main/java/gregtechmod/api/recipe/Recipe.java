package gregtechmod.api.recipe;

import gregtechmod.api.util.GT_Utility;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
		return Collections.unmodifiableList(stacks);
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
	
	//	public void checkCellBalance() {
//		if (!GregTech_API.SECONDARY_DEBUG_MODE || mInputs.length < 1) return;
//		
//		int tInputAmount  = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(getFirstInputs());
//		int tOutputAmount = GT_ModHandler.getCapsuleCellContainerCountMultipliedWithStackSize(mOutputs);
//		
//		if (tInputAmount < tOutputAmount) {
//			if (!Materials.Tin.contains(getFirstInputs())) {
//				GT_Log.log.catching(new Exception());
//			}
//		} else if (tInputAmount > tOutputAmount) {
//			if (!Materials.Tin.contains(mOutputs)) {
//				GT_Log.log.catching(new Exception());
//			}
//		}
//	}
//	
//	public static boolean addRecipe(List<Recipe> aList, boolean aShapeless, ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt, int aStartEU) {
//		return addRecipe(aList, aShapeless, new Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt, aStartEU, true));
//	}
//	
//	public static boolean addRecipe(List<Recipe> aList, boolean aShapeless, Recipe aRecipe) {
//		if (aList.contains(aRecipe)) return false;
//		aRecipe.addToLists(aList);
//		return true;
//	}
//	
//	/**
//	 * Default constructor, will create simple recipe
//	 * @param aInput1
//	 * @param aInput2
//	 * @param aOutput1
//	 * @param aOutput2
//	 * @param aOutput3
//	 * @param aOutput4
//	 * @param aDuration
//	 * @param aEUt
//	 * @param aStartEU
//	 * @param unification will forcely add oredict variants to input
//	 */
//	public Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt, int aStartEU, boolean unification) {
//		if (aInput1 == null) {
//			mInputs = new ItemStack [0][];
//		} else if (aInput2 == null) {
//			mInputs = new ItemStack[][]{ unification ? applyOreDict(aInput1) : new ItemStack[] {aInput1}};
//		} else {
//			mInputs = new ItemStack[][] { unification ? applyOreDict(aInput1) : new ItemStack[] {aInput1}, unification ? applyOreDict(aInput2) : new ItemStack[] {aInput2}};
//		}
//		
//		mOutputs = new ItemStack[] {aOutput1, aOutput2, aOutput3, aOutput4};
//		mDuration = aDuration;
//		mStartEU = aStartEU;
//		mEUt = aEUt;
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aOutput1, int aStartEU, int aType) {
//		this(aInput1, aOutput1, null, null, null, aStartEU, aType);
//	}
//	
//	// aStartEU = EU per Liter! If there is no Liquid for this Object, then it gets multiplied with 1000!
//	public Recipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aStartEU, int aType) {
//		this(aInput1, null, aOutput1, aOutput2, aOutput3, aOutput4, 0, 0, Math.max(1, aStartEU), true);
//		
//		if (mInputs.length > 0 && aStartEU > 0) {
//			switch (aType) {
//			// Diesel Generator
//			case 0:
//				addToLists(RecipeMaps.sDieselFuels);
//				break;
//			// Gas Turbine
//			case 1:
//				addToLists(RecipeMaps.sTurbineFuels);
//				break;
//			// Thermal Generator
//			case 2:
//				addToLists(RecipeMaps.sHotFuels);
//				break;
//			// Plasma Generator
//			case 4:
//				addToLists(RecipeMaps.sPlasmaFuels);
//				break;
//			// Magic Generator
//			case 5:
//				addToLists(RecipeMaps.sMagicFuels);
//				break;
//			// Fluid Generator. Usually 3. Every wrong Type ends up in the Semifluid Generator
//			default:
//				addToLists(RecipeMaps.sDenseLiquidFuels);
//				break;
//			}
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, int aStartEU) {
//		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), aEUt, Math.max(Math.min(aStartEU, 160000000), 0), true);
//		if (mInputs.length > 1 && findEqualRecipe(true, sFusionRecipes, aInput1, aInput2) == null) {
//			addToLists(sFusionRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
//		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), 5, 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, sCentrifugeRecipes, aInput1, aInput2) == null) {
//			addToLists(sCentrifugeRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
//		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), Math.max(aEUt, 1), 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, sElectrolyzerRecipes, aInput1, aInput2) == null) {
//			addToLists(sElectrolyzerRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
//		this(aInput1, null, aOutput1, aOutput2, null, null, aDuration, aEUt, 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sLatheRecipes, aInput1) == null) {
//			addToLists(sLatheRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, int aDuration, ItemStack aOutput1, int aEUt) {
//		this(aInput1, null, aOutput1, null, null, null, aDuration, aEUt, 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sCutterRecipes, aInput1) == null) {
//			addToLists(sCutterRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
//		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, null, 200*aInput1.stackSize, 30, 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, sSawmillRecipes, aInput1, aInput2) == null) {
//			addToLists(sSawmillRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
//		this(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, 100*aInput1.stackSize, 120, 0, true);
//		if (mInputs.length > 0 && aInput2 != null && mOutputs[0] != null && findEqualRecipe(false, sGrinderRecipes, aInput1, aInput2) == null) {
//			addToLists(sGrinderRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, int aCellAmount, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
//		this(aInput1, aCellAmount>0?GT_Items.Cell_Empty.get(Math.min(64, Math.max(1, aCellAmount))):null, aOutput1, aOutput2, aOutput3, aOutput4, Math.max(aDuration, 1), Math.max(aEUt, 1), 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, sDistillationRecipes, aInput1, aCellAmount>0?GT_Items.Cell_Empty.get(Math.min(64, Math.max(1, aCellAmount))):null) == null) {
//			addToLists(sDistillationRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt, int aLevel) {
//		this(aInput1, aInput2, aOutput1, aOutput2, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), aLevel > 0 ? aLevel : 100, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sBlastRecipes, aInput1, aInput2) == null) {
//			addToLists(sBlastRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
//		this(aInput1, GT_ModHandler.getIC2Item("industrialTnt", aInput2>0?aInput2<64?aInput2:64:1, new ItemStack(Blocks.tnt, aInput2>0?aInput2<64?aInput2:64:1)), aOutput1, aOutput2, null, null, 20, 30, 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, sImplosionRecipes, aInput1, GT_ModHandler.getIC2Item("industrialTnt", aInput2>0?aInput2<64?aInput2:64:1, new ItemStack(Blocks.tnt, aInput2>0?aInput2<64?aInput2:64:1))) == null) {
//			addToLists(sImplosionRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, int aEUt, int aDuration, ItemStack aOutput1) {
//		this(aInput1, null, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sWiremillRecipes, aInput1) == null) {
//			addToLists(sWiremillRecipes);
//		}
//	}
//	
//	public Recipe(int aEUt, int aDuration, ItemStack aInput1, ItemStack aOutput1) {
//		this(aInput1, GT_Items.Circuit_Integrated.getWithDamage(0, aInput1.stackSize), aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(false, sBenderRecipes, aInput1) == null) {
//			addToLists(sBenderRecipes);
//		}
//	}
//	
//	public Recipe(int aEUt, int aDuration, ItemStack aInput1, ItemStack aShape, ItemStack aOutput1) {
//		this(aInput1, aShape, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0, true);
//		if (mInputs.length > 1 && mOutputs[0] != null && findEqualRecipe(false, sExtruderRecipes, aInput1) == null) {
//			addToLists(sExtruderRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, int aEUt, ItemStack aInput2, int aDuration, ItemStack aOutput1) {
//		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sAssemblerRecipes, aInput1, aInput2) == null) {
//			addToLists(sAssemblerRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aInput2, int aEUt, int aDuration, ItemStack aOutput1) {
//		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sAlloySmelterRecipes, aInput1, aInput2) == null) {
//			addToLists(sAlloySmelterRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, int aEUt, ItemStack aInput2, int aDuration, ItemStack aOutput1, ItemStack aOutput2) {
//		this(aInput1, aInput2, aOutput1, aOutput2, null, null, Math.max(aDuration, 1), Math.max(aEUt, 1), 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sCannerRecipes, aInput1, aInput2) == null) {
//			addToLists(sCannerRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aOutput1, int aDuration) {
//		this(aInput1, null, aOutput1, null, null, null, Math.max(aDuration, 1), 120, 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sVacuumRecipes, aInput1) == null) {
//			addToLists(sVacuumRecipes);
//		}
//	}
//	
//	public Recipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration) {
//		this(aInput1, aInput2, aOutput1, null, null, null, Math.max(aDuration, 1), 30, 0, true);
//		if (mInputs.length > 0 && mOutputs[0] != null && findEqualRecipe(true, sChemicalRecipes, aInput1) == null) {
//			addToLists(sChemicalRecipes);
//		}
//	}
	
	public void writeToNBT(NBTTagCompound data) {
		data.setInteger("recipeHash", this.hashCode());
	}

	public static Recipe loadFromNBT(RecipeMap<?> recipeMap, NBTTagCompound data) {
		if (data.hasKey("recipeHash")) {
			int hash = data.getInteger("recipeHash");
			for (Recipe res : recipeMap.getRecipes()) {
				if (res.hashCode() == hash) {
					return res;
				}
			}
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