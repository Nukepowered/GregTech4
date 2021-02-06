package gregtechmod.common.recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import gregtechmod.api.recipe.Ingredient;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ItemStackKey;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import scala.actors.threadpool.Arrays;

/** An implementation of Ingredient interface
 * 
 * NEVER INCLUDE ANYTHING FROM common PACKAGE IN YOUR MODS!
 * @author TheDarkDnKTv
 */
public class RecipeEntry implements Ingredient {
	
	/** For internal use only */
	private Collection<ItemStackKey> comparableVariants;
	private HashSet<ItemStack> variants;
	private EnumSet<Match> options;			// Options for matching
	private int count;						// Using for non consumable
	
	private RecipeEntry() {
		variants = new HashSet<>();
		options = EnumSet.noneOf(Match.class);
		count = 0;
		comparableVariants = Collections2.transform(variants, stack -> ItemStackKey.from(stack));
	}
	
	/**
	 * Will create singleton ingredient with size of stack
	 */
	public static Ingredient singleton(ItemStack stack, Match...options) {
		assert GT_Utility.isStackValid(stack) : "Stack can not be null or invalid!";
		return singleton(stack, stack.stackSize, options);
	}
	
	/**
	 * Will create singleton ingredient with size of count variable
	 */
	public static Ingredient singleton(ItemStack stack, int count, Match...options) {
		assert GT_Utility.isStackValid(stack) : "Stack can not be invalid or null!";
		
		RecipeEntry result = new RecipeEntry();
		result.variants.add(stack.copy());
		result.count = count;
		result.addOptions(options);
		return result;
	}
	
	/**
	 * Create an Ingredient with size of count variable
	 */
	public static Ingredient fromStacks(int count, Collection<ItemStack> stacks, Match...options) {
		if (stacks.size() > 0) {
			RecipeEntry result = new RecipeEntry();
			result.count = count;
			result.addOptions(options);
			for (ItemStack stack : stacks) {
				assert GT_Utility.isStackValid(stack) : "Stack cannot be invalid, or null!";
				result.variants.add(stack.copy());
			}
			
			return result;
		}
		
		throw new IllegalArgumentException("An empty stack array present " + stacks.size());
	}
	
	/**
	 * Create an Ingredient with size of 1
	 */
	public static Ingredient fromStacks(Collection<ItemStack> stacks, Match...options) {
		return fromStacks(1, stacks, options);
	}
	
	/**
	 * WARNING! If stack has several OreDict names, it will add all items of all names!
	 * Fot strict tag use method below
	 */
	/**
	 * @param stack
	 * @param count
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Ingredient oreDict(ItemStack stack, int count, Match...options) {
		assert GT_Utility.isStackValid(stack) : "Stack can not be invalid or null!";
		
		List<ItemStack> stacks = new ArrayList<>();
		int ids[] = OreDictionary.getOreIDs(stack);
		
		if (ids.length > 0) {
			RecipeEntry result = new RecipeEntry();
			for (int id :ids)
				stacks.addAll(OreDictionary.getOres(id));
			// Making set of ItemStackKey first, then back again to ItemStack just for remove duplicates of items
			// Should execute by O(2n)
			Set<ItemStackKey> setOfStacks = new HashSet<>(Lists.transform(stacks, item -> ItemStackKey.from(item)));
			result.count = count;
			result.addOptions(options);
			result.variants.addAll(Collections2.transform(setOfStacks, item -> item.get()));
			
			/* Tip from Arch, O(n^2 + n)
			List<Intgere> itemStacksToRemove = new ArrayList<>();
			for (int i = 0; i < stacks.size(); i++) {
    			for (int j = i + 1; j < stacks.size(); j++) {
         			ItemStack stack1 = stacks.get(i);
         			ItemStack stack2 = stacks.get(j);
         			if (ItemStack.areItemStackEqual(stack1, stack2)) 
             			itemStacksToRemove.add(i);
    			}
			}
			
			for (int i = itemStacksToRemove.size()-1; i >= 0; i--) 
				stacks.remove(i);
			*/
			
			return result;
		}
		
		throw new IllegalArgumentException("Wrong ore dictionary stack supplied: " + stack);
	}
	
	/**
	 * Create an OreDict Ingredient with size of 1
	 */
	public static Ingredient oreDict(ItemStack stack, Match...options) {
		return oreDict(stack, 1, options);
	}
	
	public static Ingredient oreDict(String name, int count, Match...options) {
		assert name != null && !name.isEmpty() : "Invalid OreDict name supplied";
		
		RecipeEntry result = new RecipeEntry();
		result.count = count;
		List<ItemStack> entries = OreDictionary.getOres(name);
		if (!entries.isEmpty()) {
			result.variants.addAll(entries);
			result.addOptions(options);
		} else
			throw new IllegalArgumentException("Wrong ore dictionary key supplied: " + name);
		return result;
	}
	
	/**
	 * Create an OreDict Ingredient with size of 1
	 */
	public static Ingredient oreDict(String name, Match...options) {
		return oreDict(name, 1, options);
	}
	
	private boolean matchNBT(ItemStack stack1, ItemStack stack2) {
		return (stack1.stackTagCompound == null && stack2.stackTagCompound == null) ||
				(stack1.stackTagCompound != null && stack1.stackTagCompound.equals(stack2.stackTagCompound));
	}
	
	@Override
	public boolean match(ItemStack input) {
		if (GT_Utility.isStackValid(input)) {
			for (ItemStack stack : variants) {
				if (stack.getItem() == input.getItem() &&
						(options.contains(Match.DAMAGE) ? stack.getItemDamage() == input.getItemDamage() : true) &&
						(options.contains(Match.NBT) ? matchNBT(input, stack) : true)) {
					return true;
				}
			}
		} else
			throw new IllegalArgumentException("Input stack is invalid or null");

		return false;
	}

	@Override
	public List<ItemStack> getVariants() {
		List<ItemStack> vars = new ArrayList<>();
		for (ItemStack stack : variants) vars.add(stack.copy());
		return vars;
	}
	
	@Override
	public int getCount() {
		return count;
	}
	
	private void addOptions(Match...options) {
		for (Match option : options) {
			if (option != null) {
				this.options.add(option);
			} else throw new IllegalArgumentException("Options can not be null!");
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof RecipeEntry) {
			RecipeEntry e = (RecipeEntry) o;
			return e.getCount() == this.getCount() && 
					e.options.equals(this.options) && 
					e.comparableVariants.size() == this.comparableVariants.size() && 
					e.comparableVariants.containsAll(this.comparableVariants);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return count - Arrays.hashCode(options.toArray()) + Arrays.hashCode(comparableVariants.toArray());
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("count=" + count)
				.append("variants=" + Collections2.transform(variants, stack -> stack.getItem()))
				.append("matches=" + options)
				.build();
	}
	
	/**
	 * By default will match items with any damage and NBT
	 * @author TheDarkDnKTv
	 *
	 */
	public static enum Match {
		/** Check damage match */
		DAMAGE,
		/** Check NBT match */
		NBT;
	}
}
