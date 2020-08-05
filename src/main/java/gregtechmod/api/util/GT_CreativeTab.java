package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class GT_CreativeTab extends CreativeTabs {
	public static ItemStack icon;
	
	@SuppressWarnings("deprecation")
	public GT_CreativeTab() {
		super("GregTech");
		LanguageRegistry.instance().addStringLocalization("itemGroup.GregTech", "GregTech Intergalactical"); // FIXME: LANG
	}
	
	@Override
    public ItemStack getIconItemStack() {
		
		return icon != null ? icon = GregTech_API.getGregTechItem(55, 1, 0) : new ItemStack(Blocks.iron_block, 1);
    }

	@Override
	public Item getTabIconItem() {
		return null;
	}
}