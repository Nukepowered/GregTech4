package gregtechmod.common.items;

import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Vanilla_Tool extends ItemTool {
    protected final int mHarvestLevel, mEnchantability;
    protected final String mMaterialName;
    
	public GT_Vanilla_Tool(String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage, Set<Block> aBlockList) {
		super(0.0F, ToolMaterial.STONE, aBlockList);
		mHarvestLevel = aHarvestLevel;
		mEnchantability = aEnchantability;
		efficiencyOnProperMaterial = aEfficiency;
		damageVsEntity = aEntityDamage;
		mMaterialName = aMaterialName;
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName(aUnlocalizedName);
		setTextureName(aUnlocalizedName);
		setMaxDamage(aMaxDamage);
		setMaxStackSize(1);
	}
	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }
    
    public int getItemEnchantability() {
        return mEnchantability;
    }
    
    public String getToolMaterialName() {
        return mMaterialName;
    }
    
    public boolean getIsRepairable(ItemStack aStack1, ItemStack aStack2) {
        return GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "ingot" + mMaterialName) || GT_OreDictUnificator.isItemStackInstanceOf(aStack2, "gem" + mMaterialName);
    }
}