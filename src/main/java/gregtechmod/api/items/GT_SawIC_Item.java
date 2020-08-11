package gregtechmod.api.items;

import ic2.api.item.IElectricItem;
import net.minecraft.item.Item;

public class GT_SawIC_Item extends GT_Saw_Item implements IElectricItem {
	public GT_SawIC_Item(Item aItem, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aItem, aUnlocalized, aEnglish, aMaxDamage, aEntityDamage, aToolQuality, aToolStrength, aEnergyConsumptionPerBlockBreak, aDisChargedGTID);
	}
}