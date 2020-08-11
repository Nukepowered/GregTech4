package gregtechmod.api.items;

import ic2.api.item.IElectricItem;
import net.minecraft.item.Item;

public class GT_ScrewdriverIC_Item extends GT_Screwdriver_Item implements IElectricItem {
	public GT_ScrewdriverIC_Item(Item aItem, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aItem, aUnlocalized, aEnglish, aMaxDamage, aEntityDamage, aDischargedGTID);
	}
}