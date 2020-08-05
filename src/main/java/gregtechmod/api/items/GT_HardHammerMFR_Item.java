package gregtechmod.api.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class GT_HardHammerMFR_Item extends GT_HardHammer_Item implements powercrystals.minefactoryreloaded.api.IMFRHammer {
	public GT_HardHammerMFR_Item(String aName, int aMaxDamage, int aEntityDamage) {
		super(aName, aMaxDamage, aEntityDamage);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
		aList.add(I18n.format("item.GT_Hammer.tooltip_mfr"));
	}
}