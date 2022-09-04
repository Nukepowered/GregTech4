package gregtechmod.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class GT_Vanilla_Axe extends GT_Vanilla_Tool {
	@SuppressWarnings("unchecked")
	public GT_Vanilla_Axe(String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+3.0F, ItemAxe.field_150917_c);
		setHarvestLevel("axe", mHarvestLevel);
	}
	
    @Override
    public float func_150893_a(ItemStack aStack, Block aBlock) {
        return aBlock != null && (aBlock.getMaterial() == Material.wood || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.vine) ? this.efficiencyOnProperMaterial : super.func_150893_a(aStack, aBlock);
    }
}