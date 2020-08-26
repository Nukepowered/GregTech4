package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;

public class GT_File_Item extends GT_Tool_Item {
	public GT_File_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, "item.GT_File_Item.tooltip_main", aMaxDamage, aEntityDamage, true);
		//GregTech_API.registerFile(new ItemStack(itemID, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		registerAtOreDict(GT_ToolDictNames.craftingToolFile, GregTech_API.ITEM_WILDCARD_DAMAGE);
		setUsageAmounts(1, 3, 2);
	}
}