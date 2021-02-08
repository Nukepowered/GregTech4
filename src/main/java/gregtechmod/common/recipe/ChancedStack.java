package gregtechmod.common.recipe;

import java.util.Optional;
import java.util.Random;

import gregtechmod.api.recipe.ChancedOutput;
import gregtechmod.common.recipe.factory.SimpleRecipeFactory;
import net.minecraft.item.ItemStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class ChancedStack implements ChancedOutput {
	private int chance;
	private ItemStack stack;
	
	public ChancedStack(ItemStack stack, int chance) {
		this.stack = stack.copy();
		this.chance = chance;
	}
	
	@Override
	public int getChance() {
		return chance;
	}

	@Override
	public ItemStack getStack() {
		return stack.copy();
	}

	@Override
	public Optional<ItemStack> get(Random random) {
		return Optional.ofNullable(random.nextInt(SimpleRecipeFactory.MAX_CHANCE) <= chance ? stack.copy() : null);
	}
}
