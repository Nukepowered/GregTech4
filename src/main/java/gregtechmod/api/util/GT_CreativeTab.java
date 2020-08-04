package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class GT_CreativeTab extends CreativeTabs {

	@SuppressWarnings("deprecation")
	public GT_CreativeTab() {
		super("GregTech");
		LanguageRegistry.instance().addStringLocalization("itemGroup.GregTech", "GregTech Intergalactical");
	}
	
	@Override
    public ItemStack getIconItemStack() {
		ItemStack rStack = GregTech_API.getGregTechItem(55, 1, 0);
		if (rStack == null) rStack = new ItemStack(Blocks.iron_block, 1);
        return rStack;
    }

	@Override
	public Item getTabIconItem() {
		return null;
	}
}