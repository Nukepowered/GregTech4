package gregtechmod.common.tileentities;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaTileEntity_Shelf_Compartment extends GT_MetaTileEntity_Shelf {
	
	public static IIcon[] sIconList = new IIcon[256];
	
	public GT_MetaTileEntity_Shelf_Compartment(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Shelf_Compartment() {
		
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Shelf_Compartment();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == 0) return 32;
		if (aSide == 1) return 29;
    	return 40;
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing();
	}
	
	@Override
	public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getFrontFacing()) mType=(byte)((mType+1)%16);
	}
	
	@Override
	public IIcon getTextureIcon(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aSide==aFacing?sIconList[mType]:null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aBlockIconRegister) {
		for (int i = 0; i < 32; i++) {
			sIconList[i] = aBlockIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + "tile.Compartment/" + i);
		}
	}

	@Override
	public void onLeftclick(EntityPlayer aPlayer) {
		if (mInventory[0] != null && mInventory[0].stackSize > 0) {
			ItemStack tOutput = GT_Utility.copy(mInventory[0]);
			if (!aPlayer.isSneaking()) tOutput.stackSize = 1;
			getBaseMetaTileEntity().decrStackSize(0, tOutput.stackSize);
			EntityItem tEntity = new EntityItem(getBaseMetaTileEntity().getWorld(), getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5, getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), 1) + 0.5, tOutput);
			tEntity.motionX = 0;
			tEntity.motionY = 0;
			tEntity.motionZ = 0;
			getBaseMetaTileEntity().getWorld().spawnEntityInWorld(tEntity);
		}
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		ItemStack tStack = aPlayer.inventory.getStackInSlot(aPlayer.inventory.currentItem);
		if (tStack == null) {
			if (mInventory[0] != null && mInventory[0].stackSize > 0) {
				aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, mInventory[0]);
				getBaseMetaTileEntity().setInventorySlotContents(0, null);
			}
		} else {
			if (mInventory[0] == null) {
				aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
				getBaseMetaTileEntity().setInventorySlotContents(0, tStack);
			}
		}
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex == 0;
	}
}
