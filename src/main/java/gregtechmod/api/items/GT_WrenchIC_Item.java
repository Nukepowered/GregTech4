package gregtechmod.api.items;

import ic2.api.item.IElectricItem;

public class GT_WrenchIC_Item extends GT_Wrench_Item implements IElectricItem {
	public GT_WrenchIC_Item(String aName, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aName, aMaxDamage, aEntityDamage, aDischargedGTID);
	}
}