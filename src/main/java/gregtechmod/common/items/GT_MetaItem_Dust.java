package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Dust extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Dust(String aName) {
		super(aName);
		instance = this;
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, Materials aMaterial, boolean aGlow) {
		instance.mToolTipList[aMeta] = aMaterial.getToolTip(OrePrefixes.dust.mMaterialAmount / GregTech_API.MATERIAL_UNIT);
		instance.mGlowList[aMeta] = aGlow;
		instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
		if (aMaterial != null) {
			GT_OreDictUnificator.addLater(OrePrefixes.dust, aMaterial, instance.getUnunifiedStack(aMeta, 1));
		}
		GT_MetaItem_SmallDust.addItem(aMeta, aMaterial, aGlow);
		GT_MetaItem_DirtyDust.addItem(aMeta, aMaterial, aGlow);
		return instance.getUnunifiedStack(aMeta, 1);
	}
}