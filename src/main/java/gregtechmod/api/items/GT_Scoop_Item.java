package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.item.ItemStack;

public class GT_Scoop_Item extends GT_Tool_Item {
	public GT_Scoop_Item(int aID, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aID, aUnlocalized, aEnglish, "A Scoop which DOES work in Adventure Mode", aMaxDamage, aEntityDamage, true, -1, aDisChargedGTID, aToolQuality, aToolStrength);
		GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolScoop, new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		addToOreDictList("beeHive");
		setToolClasses("scoop");
		setUsageAmounts(1, 1, 1);
	}
}