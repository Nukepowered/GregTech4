package gregtechmod.common.items;

import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class GT_Mortar_Item extends GT_Generic_Item {
	
	ItemStack mBrokenItem;
	
	public GT_Mortar_Item(String aName, int aMaxDamage, ItemStack aBrokenItem) {
		super(aName, "item.GT_Mortar_Item.tooltip");
		setMaxDamage(aMaxDamage-1);
		setMaxStackSize(1);
		setNoRepair();
		mBrokenItem = aBrokenItem;
	}
	
	@Override
    public ItemStack getContainerItem(ItemStack aStack) {
        return aStack.getItemDamage() >= getMaxDamage() ? GT_Utility.copy(mBrokenItem) : new ItemStack(this, 1, aStack.getItemDamage() + 1);
    }
    
	@Override
    public boolean hasContainerItem() {
        return true;
    }
    
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return false;
    }
}
