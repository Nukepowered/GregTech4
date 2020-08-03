package gregtechmod.common.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Axe extends GT_Vanilla_Tool {
	public GT_Vanilla_Axe(int aID, String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aID, aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+3.0F, ItemAxe.blocksEffectiveAgainst);
		MinecraftForge.setToolClass(this, "axe", mHarvestLevel);
	}
	
    public float getStrVsBlock(ItemStack aStack, Block aBlock) {
        return aBlock != null && (aBlock.blockMaterial == Material.wood || aBlock.blockMaterial == Material.plants || aBlock.blockMaterial == Material.vine) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(aStack, aBlock);
    }
}