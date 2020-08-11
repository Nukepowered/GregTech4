package gregtechmod.api.util;

import gregtechmod.api.enums.GT_Items;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_CreativeTab extends CreativeTabs {

	public GT_CreativeTab() {
		super("GregTech");
	}
	
	@Override
    public ItemStack getIconItemStack() {
        return GT_Items.Tool_Cheat.getUndamaged(1, new ItemStack(Blocks.iron_block, 1));
    }

	@Override
	public Item getTabIconItem() {
		return null;
	}
}