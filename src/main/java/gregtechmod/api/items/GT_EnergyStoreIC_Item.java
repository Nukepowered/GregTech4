package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import ic2.api.item.IElectricItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_EnergyStoreIC_Item extends GT_EnergyStore_Item implements IElectricItem {
	public GT_EnergyStoreIC_Item(int aID, String aUnlocalized, String aEnglish, int aCharge, int aTransfer, int aTier, int aEmptyID, int aFullID) {
		super(aID, aUnlocalized, aEnglish, aCharge, aTransfer, aTier, aEmptyID, aFullID);
	}
	
	@Override
	public boolean canProvideEnergy(ItemStack aStack) {
		return true;
	}
	
	@Override
	public Item getChargedItem(ItemStack aStack) {
		return GregTech_API.sItemList[mFullID];
	}
	
	@Override
	public Item getEmptyItem(ItemStack aStack) {
		return GregTech_API.sItemList[mEmptyID];
	}
	
	@Override
	public double getMaxCharge(ItemStack aStack) {
		return (double)mCharge;
	}
	
	@Override
	public double getTransferLimit(ItemStack aStack) {
		return (double)mTransfer;
	}

}