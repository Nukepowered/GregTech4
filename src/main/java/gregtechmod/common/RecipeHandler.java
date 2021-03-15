package gregtechmod.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;

/**
 * @author TheDarkDnKTv
 * Created for recipe removal in one iteration, more efficient than iterating for any item
 */
public class RecipeHandler {
	
	private static List<IRecipeMatcher> craftingRemove;
	private static List<IFurnanceMatcher> smeltingRemove;
	
	private static Multimap<Integer, IIC2Matcher> IC2Remove;
	private static Map<Integer, Map<IRecipeInput, RecipeOutput>> IC2Mapping;
	
	private static List<Runnable> onFinishTasks;
	
	//////////////////////
	// REGISTER METHODS //
	//////////////////////
	
	public static void scheduleCraftingToRemove(IRecipeMatcher matcher) {
		Objects.requireNonNull(matcher);
		craftingRemove.add(matcher);
	}
	
	public static void scheduleSmeltingToRemove(IFurnanceMatcher matcher) {
		Objects.requireNonNull(matcher);
		smeltingRemove.add(matcher);
	}
	
	public static void scheduleIC2RecipeToRemove(Map<IRecipeInput, RecipeOutput> recipeMap, IIC2Matcher matcher) {
		Objects.requireNonNull(recipeMap);
		Objects.requireNonNull(matcher);
		int hash = System.identityHashCode(recipeMap);
		IC2Mapping.put(hash, recipeMap);
		IC2Remove.put(hash, matcher);
	}
	
	public static void executeOnFinish(Runnable runnable) {
		Objects.requireNonNull(runnable);
		onFinishTasks.add(runnable);
	}
	
	//////////////
	// INTERNAL //
	//////////////
	
	private static void proceedTasks() {
		long time = System.currentTimeMillis();
		
		for (Runnable toRun : onFinishTasks) {
			toRun.run();
		}
		
		GT_Log.log.info(String.format("\tOnFinish tasks completed for %.3f seconds", (System.currentTimeMillis() - time) / 1000.0F));
	}
	
	private static void processSmelting() {
		long time = System.currentTimeMillis();
		
		@SuppressWarnings("unchecked")
		Map<ItemStack, ItemStack> smelting = FurnaceRecipes.smelting().getSmeltingList();
		Iterator<Entry<ItemStack, ItemStack>> iter = smelting.entrySet().iterator();
		
		while (iter.hasNext() && !smeltingRemove.isEmpty()) {
			Entry<ItemStack, ItemStack> entr = iter.next();
			for (IFurnanceMatcher matcher : smeltingRemove) {
				if (matcher.matches(entr.getKey(), entr.getValue())) {
					iter.remove();
				}
			}
		}
		
		GT_Log.log.info(String.format("\tSmelting processor finished for %.3f seconds", (System.currentTimeMillis() - time) / 1000.0F));
	}
	
	private static void processCrafting() {
		long time = System.currentTimeMillis();
		
		@SuppressWarnings("unchecked")
		Iterator<IRecipe> iter = CraftingManager.getInstance().getRecipeList().iterator();
		
		while (iter.hasNext() && !craftingRemove.isEmpty()) {
			IRecipe recipe = iter.next();
			Iterator<IRecipeMatcher> iter1 = craftingRemove.iterator();
			while (iter1.hasNext()) {
				IRecipeMatcher matcher = iter1.next();
				if (matcher.matches(recipe)) {
					if (!matcher.isReusable()) iter1.remove();
					iter.remove();
					break;
				}
			}
		}
		
		GT_Log.log.info(String.format("\tCrafting processor finished for %.3f seconds", (System.currentTimeMillis() - time) / 1000.0F));
	}
	
	private static void processIC2() {
		long time = System.currentTimeMillis();
		
		for (Entry<Integer, Collection<IIC2Matcher>> recipeMap : IC2Remove.asMap().entrySet()) {
			Collection<IIC2Matcher> tasks = new ArrayList<>(recipeMap.getValue());
			Iterator<Entry<IRecipeInput, RecipeOutput>> iter = IC2Mapping.get(recipeMap.getKey()).entrySet().iterator();
			
			while (iter.hasNext() && !tasks.isEmpty()) {
				Entry<IRecipeInput, RecipeOutput> entry = iter.next();
				Iterator<IIC2Matcher> iter2 = tasks.iterator();
				while (iter2.hasNext()) {
					IIC2Matcher matcher = iter2.next();
					if (matcher.matches(entry.getKey(), entry.getValue())) {
						iter.remove();
						iter2.remove();
					}
				}
			}
		}
		
		GT_Log.log.info(String.format("\tIC2 Recipe processor finished for %.3f seconds", (System.currentTimeMillis() - time) / 1000.0F));
	}
	
	/**
	 * Called on POST to proceed all job 
	 */
	public static void activateHandler() {
		long time = System.currentTimeMillis();
		
		processSmelting();
		processCrafting();
		processIC2();
		proceedTasks();
		
		craftingRemove = null;
		smeltingRemove = null;
		onFinishTasks = null;
		IC2Mapping = null;
		IC2Remove = null;
		
		GT_Log.log.info(String.format("Recipe handler finished for %.3f seconds", (System.currentTimeMillis() - time) / 1000.0F));
	}
	
	static {
		craftingRemove = new ArrayList<>();
		smeltingRemove = new ArrayList<>();
		onFinishTasks = new ArrayList<>();
		
		IC2Remove = Multimaps.newListMultimap(new HashMap<>(), ArrayList::new);
		IC2Mapping = new HashMap<>();
	}
	
	/**
	 * 
	 * @author TheDarkDnKTv
	 *
	 */
	@FunctionalInterface
	public static interface IFurnanceMatcher {
		
		/**
		 * Returns true if both stacks matches criteria
		 * Input always is <u>valid</u> stack
		 */
		boolean matches(ItemStack in, ItemStack out);
	}
	
	/**
	 * 
	 * @author TheDarkDnKTv
	 *
	 */
	@FunctionalInterface
	public static interface IRecipeMatcher {
		/**
		 * 
		 * Returns true if recipe matches some criteria
		 */
		boolean matches(IRecipe recipe);
		
		/**
		 * Used to determine is this matcher should be executed for every recipe entry
		 * If returns true - will be removed from stack on first match
		 */
		default boolean isReusable() {
			return true;
		}
	}
	
	/**
	 * 
	 * @author TheDarkDnKTv
	 *
	 */
	@FunctionalInterface
	public static interface IIC2Matcher {
		
		/**
		 * 
		 * Returns true if recipe matches some criteria
		 */
		boolean matches(IRecipeInput in, RecipeOutput out);
	}
	
	/** Standard implementation, default use case of IRecipeMatcher
	 * @author TheDarkDnKTv
	 *
	 */
	public static class InventoryRecipeMatcher implements IRecipeMatcher {
		
		public final InventoryCrafting crafting;
		private final boolean reusable;
		
		/**
		 * 
		 * @param reusable determine if entry should be removed from iteration loop after first match or not
		 * @param pseudoInventory
		 */
		public InventoryRecipeMatcher(boolean reusable, ItemStack...pseudoInventory) {
			this.reusable = reusable;
			this.crafting = GT_Utility.getInventoryFromArray(pseudoInventory);
		}
		
		@Override
		public boolean matches(IRecipe recipe) {
			return recipe.matches(crafting, GregTech_API.sDummyWorld);
		}
		
		@Override
		public boolean isReusable() {
			return reusable;
		}
	}
	
	/**
	 * An output finder implementation
	 * @author TheDarkDnKTv
	 *
	 */
	public static class OutputMatcher implements IRecipeMatcher {
		
		private final boolean reusable;
		private final ItemStack output;
		
		public OutputMatcher(boolean reusable, ItemStack output) {
			Objects.requireNonNull(output);
			this.reusable = reusable;
			this.output = output;
		}

		@Override
		public boolean matches(IRecipe recipe) {
			return recipe.getRecipeOutput().isItemEqual(output);
		}
		
		@Override
		public boolean isReusable() {
			return reusable;
		}
	}
}
