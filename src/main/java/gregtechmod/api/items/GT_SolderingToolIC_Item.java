package gregtechmod.api.items;

import ic2.api.item.IElectricItem;

public class GT_SolderingToolIC_Item extends GT_SolderingTool_Item implements IElectricItem {
	public GT_SolderingToolIC_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aUnlocalized, aMaxDamage, aEntityDamage, aDischargedGTID);
	}
}