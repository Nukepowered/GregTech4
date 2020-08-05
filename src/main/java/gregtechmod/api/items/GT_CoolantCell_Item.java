package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICapsuleCellContainer;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_CoolantCell_Item extends GT_Generic_Item implements ICapsuleCellContainer {
    protected int heatStorage, mCellCount;
	
    public GT_CoolantCell_Item(String aName, int aMaxStore, int aCellCount) {
        super(aName, null);
        setMaxStackSize(1);
        setMaxDamage(100);
        setNoRepair();
        heatStorage = aMaxStore;
        mCellCount = aCellCount;
        setCreativeTab(GregTech_API.TAB_GREGTECH);
    }
    
    protected void setHeatForStack(ItemStack aStack, int aHeat) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        
        tNBT.setInteger("heat", aHeat);

        if (heatStorage > 0) {
            double var4 = (double)aHeat / (double)heatStorage;
            int var6 = (int)((double)aStack.getMaxDamage() * var4);

            if (var6 >= aStack.getMaxDamage()) {
                var6 = aStack.getMaxDamage() - 1;
            }
            aStack.setItemDamage(var6);
        }
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
		aList.add(I18n.format("item.coolant.stored.tooltip", getHeatOfStack(aStack)));
	}
	
    protected int getHeatOfStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
        return tNBT.getInteger("heat");
    }
    
	@Override
	public int CapsuleCellContainerCount(ItemStack aStack) {
		return mCellCount;
	}
}
