package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GT_Saw_Item extends GT_Tool_Item {
	public GT_Saw_Item(String aName, int aMaxDamage, int aEntityDamage, int aToolQuality, float aToolStrength, int aEnergyConsumptionPerBlockBreak, int aDisChargedGTID) {
		super(aName, "For sawing Logs into Planks", aMaxDamage, aEntityDamage, -1, aDisChargedGTID, aToolQuality, aToolStrength); // FIXME lang
		//GregTech_API.registerSaw(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSaw, new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		addToBlockList(GT_ModHandler.getIC2Item("rubberLeaves", 1));
		addToMaterialList(Material.leaves);
		addToMaterialList(Material.plants);
		addToMaterialList(Material.wood);
		addToMaterialList(Material.vine);
		addToMaterialList(Material.ice);
		addToOreDictList("treeLeaves");
		addToOreDictList("logRubber");
		setPrimaryToolClass("axe");
		addToBlockList(Blocks.sponge);
		addToBlockList(Blocks.hay_block);
		addToBlockList(Blocks.tnt);
		addToBlockList(Blocks.bed);
		setElectricConsumptionPerBrokenBlock(aEnergyConsumptionPerBlockBreak);
		setUsageAmounts(1, 3, 1);
	}
	
	@Override
	public void checkEnchantmentEffects(ItemStack aStack) {
		super.checkEnchantmentEffects(aStack);
		if (aStack != null && GT_ModHandler.isElectricItem(aStack)) aStack.addEnchantment(Enchantment.silkTouch, 1);
	}
}