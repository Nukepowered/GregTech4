package gregtechmod.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSpade;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Shovel extends GT_Vanilla_Tool {
	public GT_Vanilla_Shovel(int aID, String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aID, aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+1.0F, ItemSpade.blocksEffectiveAgainst);
		MinecraftForge.setToolClass(this, "shovel", mHarvestLevel);
	}
	
    public boolean canHarvestBlock(Block aBlock) {
        return aBlock == Block.snow ? true : aBlock == Block.blockSnow;
    }
}