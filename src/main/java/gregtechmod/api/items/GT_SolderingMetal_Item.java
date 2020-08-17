package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import net.minecraft.item.ItemStack;

public class GT_SolderingMetal_Item extends GT_Tool_Item {
	public GT_SolderingMetal_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, "item.GT_SolderingLead.tooltip_main", aMaxDamage, aEntityDamage, false);
		GregTech_API.registerSolderingMetal(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		registerAtOreDictWildcard(GT_ToolDictNames.craftingToolSolderingMetal);
		setUsageAmounts(1, 3, 1);
	}
}