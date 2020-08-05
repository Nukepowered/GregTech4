package gregtechmod.common.items;

import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_MetaItem_Component extends GT_MetaItem_Abstract {

	public static GT_MetaItem_Abstract instance;
	
	public GT_MetaItem_Component(String aName) {
		super(aName);
		instance = this;
	}
	
	public static ItemStack[] getStackList() {
		return instance.mStackList;
	}
	
	public static ItemStack addItem(int aMeta, String aName, String aMaterial, String aToolTip) {
		instance.mToolTipList[aMeta] = instance.getUnlocalizedName() + "." + aMeta + ".tooltip";
		
		instance.mStackList[aMeta] = new ItemStack(instance, 1, aMeta);
		if (aMaterial != null && !aMaterial.equals("")) {
			GT_OreDictUnificator.registerOre(aMaterial, instance.getUnunifiedStack(aMeta, 1));
		}
		return instance.getUnunifiedStack(aMeta, 1);
	}
}
