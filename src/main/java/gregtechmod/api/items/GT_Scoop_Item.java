package gregtechmod.api.items;

import gregtechmod.api.enums.GT_ToolDictNames;

public class GT_Scoop_Item extends GT_Tool_Item {
	public GT_Scoop_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aUnlocalized, "item.GT_Scoop_Item.tooltip", aMaxDamage, aEntityDamage, true, -1, aDisChargedGTID, aToolQuality, aToolStrength);
		registerAtOreDictWildcard(GT_ToolDictNames.craftingToolScoop);
		addToOreDictList("beeHive");
		setToolClasses("scoop");
		setUsageAmounts(1, 1, 1);
	}
}