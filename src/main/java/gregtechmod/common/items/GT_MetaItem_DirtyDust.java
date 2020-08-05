package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_DirtyDust extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_DirtyDust(String aName) {
		super(aName);
		instance = this;
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack addItem(int aMeta, String aName, Materials aMaterial, boolean aGlow) {
		GT_LanguageManager.addStringLocalization(instance.getUnlocalizedName() + "." + aMeta + ".name", "Dirty Pile of " + aName);
		
		instance.mToolTipList[aMeta] = aMaterial.getToolTip(OrePrefixes.dustImpure.mMaterialAmount / GregTech_API.MATERIAL_UNIT);
		instance.mGlowList[aMeta] = aGlow;
		instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
		if (aMaterial != null) {
			GT_OreDictUnificator.add(OrePrefixes.dustDirty, aMaterial, instance.getUnunifiedStack(aMeta, 1));
		}
		return instance.getUnunifiedStack(aMeta, 1);
	}
}