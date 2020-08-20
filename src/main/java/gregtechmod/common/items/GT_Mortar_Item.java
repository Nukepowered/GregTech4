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
	
    public ItemStack getContainerItemStack(ItemStack aStack) {
        if (aStack.getItemDamage() >= getMaxDamage()) return GT_Utility.copy(mBrokenItem);
        return new ItemStack(this, 1, aStack.getItemDamage()+1);
    }
    
    public boolean hasContainerItem() {
        return true;
    }
    
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return false;
    }
}
