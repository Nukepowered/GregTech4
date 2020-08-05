package gregtechmod.common.items;

import java.util.Collections;

import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Vanilla_Hoe extends GT_Vanilla_Tool {
    @SuppressWarnings("unchecked")
	public GT_Vanilla_Hoe(String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aUnlocalizedName, aMaterialName, 0, 0, aMaxDamage, 1.0F, aEntityDamage, Collections.EMPTY_SET);
		setHarvestLevel("hoe", mHarvestLevel);
	}
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return Items.wooden_hoe.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
    }
    
    public boolean getIsRepairable(ItemStack aStack1, ItemStack aStack2) {
        return GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "ingot" + mMaterialName) || GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "gem" + mMaterialName);
    }
}