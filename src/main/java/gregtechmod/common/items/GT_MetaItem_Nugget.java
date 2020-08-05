package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Nugget extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Nugget(String aName) {
		super(aName);
		instance = this;
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, Materials aMaterial, boolean aGlow) {
		GT_LanguageManager.addStringLocalization(instance.getUnlocalizedName() + "." + aMeta + ".name", aMaterial + " Nugget");
		
		instance.mToolTipList[aMeta] = aMaterial.getToolTip(OrePrefixes.nugget.mMaterialAmount / GregTech_API.MATERIAL_UNIT);
		instance.mGlowList[aMeta] = aGlow;
		
		instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
		if (aMaterial != null) {
			GT_OreDictUnificator.add(OrePrefixes.nugget.get(aMaterial), instance.getUnunifiedStack(aMeta, 1));
		}
		return instance.getUnunifiedStack(aMeta, 1);
	}
}