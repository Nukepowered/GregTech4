package gregtechmod.api.items;

import gregtechmod.api.util.GT_LanguageManager;

import java.util.List;

import net.minecraft.item.ItemStack;

public class GT_HardHammerMFR_Item extends GT_HardHammer_Item implements powercrystals.minefactoryreloaded.api.IMFRHammer {
	public GT_HardHammerMFR_Item(String aName, int aMaxDamage, int aEntityDamage) {
		super(aName, aMaxDamage, aEntityDamage);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
		aList.add(GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".tooltip_mfr", "Works as Minefactory Hammer too")); // FIXME: LANG
	}
}