package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_CreativeTab extends CreativeTabs {
	public static ItemStack icon;
	
	public GT_CreativeTab() {
		super("gregtech");
	}
	
	@Override
    public ItemStack getIconItemStack() {
		return icon != null ? icon : (icon = GregTech_API.getGregTechItem(55, 1, 0)) != null ? icon : new ItemStack(Blocks.iron_block, 1);
    }

	@Override
	public Item getTabIconItem() {
		return null;
	}
}