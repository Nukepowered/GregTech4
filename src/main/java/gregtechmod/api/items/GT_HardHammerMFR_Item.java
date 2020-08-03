package gregtechmod.api.items;

import gregtechmod.api.util.GT_LanguageManager;

import java.util.List;

import net.minecraft.item.ItemStack;

public class GT_HardHammerMFR_Item extends GT_HardHammer_Item implements powercrystals.minefactoryreloaded.api.IToolHammerAdvanced {
	public GT_HardHammerMFR_Item(int aID, String aName, int aMaxDamage, int aEntityDamage) {
		super(aID, aName, aMaxDamage, aEntityDamage);
	}
	
	@Override
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
		aList.add(GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".tooltip_mfr", "Works as Minefactory Hammer too"));
	}
	
	@Override
	public boolean isActive(ItemStack stack) {
		return true;
	}
}