package gregtechmod.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * A class describing recipe one-slot ingredient
 * All items could possible be matched to current slot
 * @author TheDarkDnKTv
 *
 */
public interface Ingredient {
	/**
	 * Check if ItemStack matches for current slot
	 */
	public boolean match(ItemStack input);
	
	/**
	 * Returns all possible stacks could be matched here
	 */
	public List<ItemStack> getVariants();
	
	/**
	 * Get a ingredient count
	 */
	public int getCount();
}
