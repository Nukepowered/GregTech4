package gregtechmod.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Pickaxe extends GT_Vanilla_Tool {
	public GT_Vanilla_Pickaxe(int aID, String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aID, aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+2.0F, ItemPickaxe.blocksEffectiveAgainst);
		MinecraftForge.setToolClass(this, "pickaxe", mHarvestLevel);
	}
	
    public boolean canHarvestBlock(Block aBlock) {
        return aBlock == Block.obsidian ? mHarvestLevel >= 3 : (aBlock != Block.blockDiamond && aBlock != Block.oreDiamond ? (aBlock != Block.oreEmerald && aBlock != Block.blockEmerald ? (aBlock != Block.blockGold && aBlock != Block.oreGold ? (aBlock != Block.blockIron && aBlock != Block.oreIron ? (aBlock != Block.blockLapis && aBlock != Block.oreLapis ? (aBlock != Block.oreRedstone && aBlock != Block.oreRedstoneGlowing ? (aBlock.blockMaterial == Material.rock ? true : (aBlock.blockMaterial == Material.iron ? true : aBlock.blockMaterial == Material.anvil)) : mHarvestLevel >= 2) : mHarvestLevel >= 1) : mHarvestLevel >= 1) : mHarvestLevel >= 2) : mHarvestLevel >= 2) : mHarvestLevel >= 2);
    }
    
    public float getStrVsBlock(ItemStack aStack, Block aBlock) {
        return aBlock != null && (aBlock.blockMaterial == Material.iron || aBlock.blockMaterial == Material.anvil || aBlock.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(aStack, aBlock);
    }
}