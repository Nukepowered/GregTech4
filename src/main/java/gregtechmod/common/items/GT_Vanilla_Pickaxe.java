package gregtechmod.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class GT_Vanilla_Pickaxe extends GT_Vanilla_Tool {
	@SuppressWarnings("unchecked")
	public GT_Vanilla_Pickaxe(String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+2.0F, ItemPickaxe.field_150915_c);
		setHarvestLevel("pickaxe", mHarvestLevel);
	}
	
	@Override
    public boolean func_150897_b(Block aBlock) {
        return aBlock == Blocks.obsidian ? mHarvestLevel >= 3 : (aBlock != Blocks.diamond_block && aBlock != Blocks.diamond_ore ? (aBlock != Blocks.emerald_ore && aBlock != Blocks.emerald_block ? (aBlock != Blocks.gold_block && aBlock != Blocks.gold_ore ? (aBlock != Blocks.iron_block && aBlock != Blocks.iron_ore ? (aBlock != Blocks.lapis_block && aBlock != Blocks.lapis_ore ? (aBlock != Blocks.redstone_ore && aBlock != Blocks.lit_redstone_ore ? (aBlock.getMaterial() == Material.rock ? true : (aBlock.getMaterial() == Material.iron ? true : aBlock.getMaterial() == Material.anvil)) : mHarvestLevel >= 2) : mHarvestLevel >= 1) : mHarvestLevel >= 1) : mHarvestLevel >= 2) : mHarvestLevel >= 2) : mHarvestLevel >= 2);
    }
    
    @Override
    public float func_150893_a(ItemStack aStack, Block aBlock) {
        return aBlock != null && (aBlock.getMaterial() == Material.iron || aBlock.getMaterial() == Material.anvil || aBlock.getMaterial() == Material.rock) ? this.efficiencyOnProperMaterial : super.func_150893_a(aStack, aBlock);
    }
}