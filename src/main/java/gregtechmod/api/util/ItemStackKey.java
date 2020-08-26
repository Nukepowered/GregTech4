package gregtechmod.api.util;

import java.util.Objects;

import gregtechmod.api.GregTech_API;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class ItemStackKey {
	
	private final ItemStack stack;
	public final boolean isWildcard;
	
	public static ItemStackKey from(ItemStack stack) {
		return from(stack, false);
	}
	
	public static ItemStackKey from(ItemStack stack, boolean isWildcard) {
		Objects.requireNonNull(stack);
		Objects.requireNonNull(stack.getItem());
		ItemStack stackCopy = stack.copy();
		stackCopy.stackSize = 1;
		return new ItemStackKey(stackCopy, isWildcard);
	}
	
	private ItemStackKey(ItemStack stack, boolean isWildcard) {
		this.isWildcard = isWildcard;
		this.stack = stack;
	}
	
	public ItemStack get() {
		return this.stack.copy();
	}
	
	public boolean isStackEquals(ItemStack stack) {
		if (GT_Utility.isStackValid(stack)) {
			if (isWildcard) {
				return this.stack.getItem() == stack.getItem();
			} else {
				return ItemStack.areItemStacksEqual(get(), stack) && ItemStack.areItemStackTagsEqual(get(), stack);
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("ItemStackKey[stack=%s, wildcard=%s]", stack.toString(), isWildcard);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ItemStackKey ? this.isStackEquals(((ItemStackKey) obj).stack) : false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(stack.getItem(), (isWildcard ? GregTech_API.ITEM_WILDCARD_DAMAGE : Items.feather.getDamage(stack)), stack.getTagCompound());
	}
}
