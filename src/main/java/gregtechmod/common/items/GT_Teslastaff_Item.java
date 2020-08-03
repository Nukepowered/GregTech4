package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Teslastaff_Item extends ItemTool implements IElectricItem {
	public int mCharge, mTransfer, mTier;
	
    public GT_Teslastaff_Item(int aID, String aName) {
        super(aID, 0, EnumToolMaterial.GOLD, new Block[0]);
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
    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + getUnlocalizedName());
    }
    
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		aList.add("No warranty!");
    }
	
    @Override
    public boolean hitEntity(ItemStack aStack, EntityLivingBase aTarget, EntityLivingBase aPlayer) {
        if (aTarget instanceof EntityPlayer && aPlayer instanceof EntityPlayer && ElectricItem.canUse(aStack, 9000000)) {
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
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(this, 1), tUncharged = new ItemStack(this, 1, getMaxDamage());
        ElectricItem.charge(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        var3.add(tCharged);
        var3.add(tUncharged);
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
	public int getChargedItemId(ItemStack aStack) {
		return itemID;
	}
	
	@Override
	public int getEmptyItemId(ItemStack aStack) {
		return itemID;
	}
	
	@Override
	public int getMaxCharge(ItemStack aStack) {
		return mCharge;
	}
	
	@Override
	public int getTier(ItemStack aStack) {
		return mTier;
	}
	
	@Override
	public int getTransferLimit(ItemStack aStack) {
		return mTransfer;
	}
}
