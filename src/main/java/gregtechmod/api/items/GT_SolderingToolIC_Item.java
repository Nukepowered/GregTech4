package gregtechmod.api.items;

import ic2.api.item.IElectricItem;
import net.minecraft.item.Item;

public class GT_SolderingToolIC_Item extends GT_SolderingTool_Item implements IElectricItem {
	public GT_SolderingToolIC_Item(Item aItem, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aItem, aUnlocalized, aEnglish, aMaxDamage, aEntityDamage, aDischargedGTID);
	}
}