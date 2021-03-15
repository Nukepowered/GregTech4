package gregtechmod.api.util;

import java.util.Objects;

import gregtechmod.api.GregTech_API;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class ItemStackKey {
	
	private final ItemStack stack;
	private final int stackSize;
	public final boolean isWildcard;
	
	public static ItemStackKey from(ItemStack stack) {
		return from(stack, stack.stackSize, false);
	}
	
	public static ItemStackKey from(ItemStack stack, boolean isWildcard) {
		return from(stack, stack.stackSize, isWildcard);
	}
	
	public static ItemStackKey from(ItemStack stack, int count) {
		return from(stack, count, false);
	}
	
	public static ItemStackKey from(ItemStack stack, int count, boolean isWildcard) {
		Objects.requireNonNull(stack);
		Objects.requireNonNull(stack.getItem());
		ItemStack stackCopy = stack.copy();
		stackCopy.stackSize = 1;
		return new ItemStackKey(stackCopy, isWildcard, count);
	}
	
	private ItemStackKey(ItemStack stack, boolean isWildcard, int stackSize) {
		this.isWildcard = isWildcard;
		this.stack = stack;
		this.stackSize = stackSize;
	}
	
	public ItemStack get() {
		ItemStack copy = this.stack.copy();
		copy.stackSize = this.stackSize;
		return copy;
	}
	
	public int getStackSize() {
		return stackSize;
	}
	
	public boolean isStackEquals(ItemStack stack) {
		if (GT_Utility.isStackValid(stack)) {
			if (isWildcard) {
				return this.stack.getItem() == stack.getItem();
			} else {
				return this.stack.isItemEqual(stack);
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("ItemStackKey[stack=%s, wildcard=%s, amount=%s]", stack.toString(), isWildcard, stackSize);
	}
	
	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof ItemStackKey ? this.isStackEquals(((ItemStackKey) obj).stack) : false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(stack.getItem(), (isWildcard ? GregTech_API.ITEM_WILDCARD_DAMAGE : Items.feather.getDamage(stack)));//, stack.getTagCompound());
	}
}
