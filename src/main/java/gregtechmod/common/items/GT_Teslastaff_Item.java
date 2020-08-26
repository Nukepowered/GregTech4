package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Teslastaff_Item extends ItemTool implements IElectricItem {
	public int mCharge, mTransfer, mTier;
	
    public GT_Teslastaff_Item(String aName) {
        super(0, ToolMaterial.GOLD, Collections.emptySet());
		setCreativeTab(GregTech_API.TAB_GREGTECH);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		setUnlocalizedName(aName);
        mCharge = 100000000;
        mTransfer = 8192;
        mTier = 4;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + getUnlocalizedName());
    }
    
	@SuppressWarnings("unchecked")
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		aList.add(I18n.format("item.GT_Teslastaff.tooltip"));
    }
	
    @Override
    public boolean hitEntity(ItemStack aStack, EntityLivingBase aTarget, EntityLivingBase aPlayer) {
        if (aTarget instanceof EntityPlayer && aPlayer instanceof EntityPlayer && ElectricItem.manager.canUse(aStack, 9000000)) {
            EntityPlayer tTarget = (EntityPlayer)aTarget, tPlayer = (EntityPlayer)aPlayer;
            GT_ModHandler.useElectricItem(aStack, 9000000, tPlayer);
            for (int i = 0; i < 4; i++) {
            	if (tTarget.inventory.armorInventory[i] != null) {
            		if (tTarget.inventory.armorInventory[i].getItem() instanceof IElectricItem) {
            			tTarget.inventory.armorInventory[i] = null;
            		}
            	}
            }
            aPlayer.attackEntityFrom(DamageSource.magic, 19);
            aTarget.attackEntityFrom(DamageSource.magic, 19);
        }
        return true;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List items) {
        ItemStack tCharged = new ItemStack(item, 1), tUncharged = new ItemStack(item, 1, getMaxDamage());
        ElectricItem.manager.charge(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        items.add(tCharged);
        items.add(tUncharged);
    }
    
	@Override
    public int getItemEnchantability() {
        return 0;
    }
	
	@Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return false;
    }
	
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }
	
    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }
    
	@Override
	public boolean canProvideEnergy(ItemStack aStack) {
		return false;
	}
	
	@Override
	public double getMaxCharge(ItemStack aStack) {
		return mCharge;
	}
	
	@Override
	public int getTier(ItemStack aStack) {
		return mTier;
	}
	
	@Override
	public double getTransferLimit(ItemStack aStack) {
		return mTransfer;
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		return new ItemStack(this, 1).getItem();
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		return new ItemStack(this, 1, getMaxDamage()).getItem();
	}
}
