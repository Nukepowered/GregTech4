package gregtechmod.common.items;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;

public class GT_Vanilla_Shovel extends GT_Vanilla_Tool {
	@SuppressWarnings("unchecked")
	public GT_Vanilla_Shovel(String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+1.0F, ItemSpade.field_150916_c);
		setHarvestLevel("shovel", mHarvestLevel);
	}
	
    public boolean canHarvestBlock(Block aBlock) {
        return aBlock == Blocks.snow_layer ? true : aBlock == Blocks.snow;
    }
}