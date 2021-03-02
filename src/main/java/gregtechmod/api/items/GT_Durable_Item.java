package gregtechmod.api.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

/**
 * This is just a basic Tool, which has normal durability and doesn't break Blocks.
 */
public class GT_Durable_Item extends GT_Generic_Item {
	
	public GT_Durable_Item(String aUnlocalized, String aTooltip, int aMaxDamage) {
		super(aUnlocalized, aTooltip);
		setMaxDamage(aMaxDamage);
		setMaxStackSize(1);
	}
	
	@Override
    public int getItemEnchantability() {
        return 0;
    }
	
	@Override
    public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {
        return false;
    }
	
	@Override
    public boolean getIsRepairable(ItemStack aStack, ItemStack aSecondStack) {
        return false;
    }
	
	@Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	protected void addAdditionalToolTips(List<String> aList, ItemStack aStack) {
		if (aStack != null && aStack.getItem() != null && aStack.getItem() instanceof GT_Durable_Item) {
			int durability = aStack.getMaxDamage() - aStack.getItemDamage();
			aList.add(I18n.format("item.durability.tooltip", durability, aStack.getMaxDamage()));
		}
	}
}