package gregtechmod.api.items;

import ic2.api.item.IElectricItem;
import net.minecraft.item.Item;

public class GT_WrenchIC_Item extends GT_Wrench_Item implements IElectricItem {
	public GT_WrenchIC_Item(Item aItem, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aItem, aUnlocalized, aEnglish, aMaxDamage, aEntityDamage, aDischargedGTID);
	}
}