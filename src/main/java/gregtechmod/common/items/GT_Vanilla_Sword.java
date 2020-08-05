package gregtechmod.common.items;

import gregtechmod.api.util.GT_OreDictUnificator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class GT_Vanilla_Sword extends ItemSword {
    protected final int mHarvestLevel, mEnchantability;
    protected final String mMaterialName;
    protected final float efficiencyOnProperMaterial, damageVsEntity;
    
	public GT_Vanilla_Sword(String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(ToolMaterial.STONE);
		mHarvestLevel = aHarvestLevel;
		mEnchantability = aEnchantability;
		efficiencyOnProperMaterial = aEfficiency;
		damageVsEntity = aEntityDamage + 4.0F;
		mMaterialName = aMaterialName;
		setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName(aUnlocalizedName);
		setTextureName(aUnlocalizedName);
		setMaxDamage(aMaxDamage);
		setMaxStackSize(1);
		setHarvestLevel("sword", mHarvestLevel);
	}
	
    @Override
    public float func_150931_i() {
        return damageVsEntity - 4.0F;
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
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Multimap getItemAttributeModifiers() {
        Multimap multimap = HashMultimap.create();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damageVsEntity, 0));
        return multimap;
    }
}