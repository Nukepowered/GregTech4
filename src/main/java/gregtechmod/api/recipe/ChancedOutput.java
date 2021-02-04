package gregtechmod.api.recipe;

import java.util.Optional;
import java.util.Random;

import net.minecraft.item.ItemStack;

/**
 * 
 * @author TheDarkDnKTv
 *
 */
public interface ChancedOutput {
	
	/**
	 * Get a chance of output in format:<br>
	 * Integer 9575 => 95.75%
	 */
	public int getChance();
	
	/**
	 * Returns a copy of output stack with no random applied
	 */
	public ItemStack getStack();
	
	public Optional<ItemStack> get(Random ranomIn);
	
}
