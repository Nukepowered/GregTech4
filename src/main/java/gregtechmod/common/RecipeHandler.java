package gregtechmod.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
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
				}
			}
		}
		
		GT_Log.log.info(String.format("\tCrafting processor finished for %.3f seconds", (System.currentTimeMillis() - time) / 1000.0F));
	}
	
	/**
	 * Called on POST to proceed all job 
	 */
	public static void activateHandler() {
		long time = System.currentTimeMillis();
		
		processSmelting();
		processCrafting();
		proceedTasks();
		
		craftingRemove = null;
		smeltingRemove = null;
		onFinishTasks = null;
		
		GT_Log.log.info(String.format("Recipe handler finished for %.3f seconds", (System.currentTimeMillis() - time) / 1000.0F));
	}
	
	static {
		craftingRemove = new ArrayList<>();
		smeltingRemove = new ArrayList<>();
		onFinishTasks = new ArrayList<>();
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
}
