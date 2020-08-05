package gregtechmod.api.items;

import ic2.api.item.IElectricItem;

public class GT_SawIC_Item extends GT_Saw_Item implements IElectricItem {
	public GT_SawIC_Item(String aName, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aName, aMaxDamage, aEntityDamage, aToolQuality, aToolStrength, aEnergyConsumptionPerBlockBreak, aDisChargedGTID);
	}
}