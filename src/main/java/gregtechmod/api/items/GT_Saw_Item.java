package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GT_Saw_Item extends GT_Tool_Item {
	public GT_Saw_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aUnlocalized, "item.GT_Saw_Bronze.tooltip_main", aMaxDamage, aEntityDamage, true, -1, aDisChargedGTID, aToolQuality, aToolStrength);
		//GregTech_API.registerSaw(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		registerAtOreDict(GT_ToolDictNames.craftingToolSaw, GregTech_API.ITEM_WILDCARD_DAMAGE);
		if (GT_ModHandler.isElectricItem(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE))) setSilkyness(1);
		addToMaterialList(Material.leaves);
		addToMaterialList(Material.plants);
		addToMaterialList(Material.wood);
		addToMaterialList(Material.vine);
		addToMaterialList(Material.ice);
		addToOreDictList("treeLeaves");
		addToOreDictList("logRubber");
		addToBlockList(Blocks.sponge);
		addToBlockList(Blocks.hay_block);
		addToBlockList(Blocks.tnt);
		addToBlockList(Blocks.bed);
		setToolClasses("axe");
		setElectricConsumptionPerBrokenBlock(aEnergyConsumptionPerBlockBreak);
		setUsageAmounts(1, 3, 1);
	}
}