package gregtechmod.common.items;

import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GT_Vanilla_Hoe extends GT_Vanilla_Tool {
    public GT_Vanilla_Hoe(int aID, String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aID, aUnlocalizedName, aMaterialName, 0, 0, aMaxDamage, 1.0F, aEntityDamage, new Block[] {});
		MinecraftForge.setToolClass(this, "hoe", mHarvestLevel);
	}
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return Item.hoeWood.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
    }
    
    public boolean getIsRepairable(ItemStack aStack1, ItemStack aStack2) {
        return GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "ingot" + mMaterialName) || GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "gem" + mMaterialName);
    }
}