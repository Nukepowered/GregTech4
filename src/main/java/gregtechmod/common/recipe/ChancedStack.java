package gregtechmod.common.recipe;

import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import gregtechmod.api.recipe.ChancedOutput;
import gregtechmod.api.util.GT_Utility;
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
	
	@Override
	public int hashCode() {
		return GT_Utility.stackToInt(stack) + chance;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof ChancedOutput) {
			ChancedOutput ch = (ChancedOutput) o;
			return ch.getStack().isItemEqual(stack) && ch.getChance() == chance;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this)
				.append("stack", stack)
				.append("chance", (chance * 1.0D / 100) + "%")
				.toString();
	}
}
