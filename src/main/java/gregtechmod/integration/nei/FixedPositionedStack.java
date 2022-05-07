package gregtechmod.integration.nei;

import java.util.ArrayList;
import java.util.List;

import codechicken.nei.ItemList;
import codechicken.nei.PositionedStack;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author TheDarkDnKTv
 * Fixed {@link PositionedStack}, in default NEI's brocken {@link PositionedStack#generatePermutations()} method. Do not saves amounts
 */
public class FixedPositionedStack extends PositionedStack {
	
	protected boolean permutated = false;
	
	public FixedPositionedStack(Object object, int x, int y) {
		super(object, x, y);
	}

	public FixedPositionedStack(Object object, int x, int y, boolean genPerms) {
		super(object, x, y, genPerms);
	}
	
	
	@Override
	public void generatePermutations() {
		if (permutated) return;

		List<ItemStack> stacks = new ArrayList<ItemStack>();
		for (ItemStack item : items) {
			if (item == null || item.getItem() == null)
				continue;

			if (item.getItemDamage() == Short.MAX_VALUE) {
				List<ItemStack> permutations = ItemList.itemMap.get(item.getItem());
				if (!permutations.isEmpty()) {
					for (ItemStack stack : permutations) {
						ItemStack copy = stack.copy();
						copy.stackSize = item.stackSize;
						stacks.add(copy);
					}
				} else {
					ItemStack base = new ItemStack(item.getItem(), item.stackSize);
					base.stackTagCompound = item.stackTagCompound;
					stacks.add(base);
				}
				continue;
			}

			stacks.add(item.copy());
		}
		items = stacks.toArray(new ItemStack[0]);

		if (items.length == 0)
			items = new ItemStack[] { new ItemStack(Blocks.fire) };

		permutated = true;
		setPermutationToRender(0);
	}
}
