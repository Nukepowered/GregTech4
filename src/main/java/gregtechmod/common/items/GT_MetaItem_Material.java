package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.MaterialStack;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Material extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Material(String aName) {
		super(aName);
		instance = this;
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, OrePrefixes aPrefix, Object aMaterial, boolean aGlowing) {		
		instance.mToolTipList[aMeta] = aMaterial==null?"":(aMaterial instanceof Materials)?((Materials)aMaterial).getToolTip(aPrefix.mMaterialAmount / GregTech_API.MATERIAL_UNIT):aMaterial.toString();
		instance.mGlowList[aMeta] = aGlowing;
		instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
		if (aMaterial != null && aPrefix != null) {
			if (aPrefix.mIsUnificatable)
				GT_OreDictUnificator.add(aPrefix.get((aMaterial instanceof MaterialStack)?((MaterialStack)aMaterial).mMaterial:aMaterial), instance.getUnunifiedStack(aMeta, 1));
			else
				GT_OreDictUnificator.registerOre(aPrefix.get((aMaterial instanceof MaterialStack)?((MaterialStack)aMaterial).mMaterial:aMaterial), instance.getUnunifiedStack(aMeta, 1));
		}
		return instance.getUnunifiedStack(aMeta, 1);
	}
}