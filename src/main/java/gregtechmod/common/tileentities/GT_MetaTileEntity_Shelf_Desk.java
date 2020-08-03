package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Shelf_Desk extends GT_MetaTileEntity_Shelf {
	public GT_MetaTileEntity_Shelf_Desk(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Shelf_Desk() {
		
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Shelf_Desk();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) return 222;
		if (aSide == 0) return 32;
		if (aSide == 1) return 29;
    	return 40;
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer) {
		ItemStack tStack = aPlayer.inventory.getStackInSlot(aPlayer.inventory.currentItem);
		if (tStack == null) {
			if (mInventory[0] != null && mInventory[0].stackSize > 0) {
				aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, mInventory[0]);
				getBaseMetaTileEntity().setInventorySlotContents(0, null);
				mType = 0;
			}
		} else {
			if (mInventory[0] == null) {
				aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, null);
				getBaseMetaTileEntity().setInventorySlotContents(0, tStack);
			}
		}
	}
}
