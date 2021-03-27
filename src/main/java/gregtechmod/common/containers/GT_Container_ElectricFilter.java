package gregtechmod.common.containers;

import gregtechmod.api.interfaces.*;
import net.minecraft.inventory.*;
import gregtechmod.api.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.ChatComponentTranslation;
import gregtechmod.api.util.*;
import gregtechmod.common.tileentities.automation.*;

public class GT_Container_ElectricFilter extends GT_ContainerMetaTile_Machine
{
    public GT_Container_ElectricFilter(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }
    
    @Override
    public void addSlots(final InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 0, 98, 5));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 1, 116, 5));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 2, 134, 5));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 3, 98, 23));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 4, 116, 23));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 5, 134, 23));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 6, 98, 41));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 7, 116, 41));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 8, 134, 41));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 18, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 35, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 11, 52, 6, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 12, 18, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 13, 35, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 14, 52, 23, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 15, 18, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 16, 35, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 17, 52, 40, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 8, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 26, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 44, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 62, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 18, 80, 63, false, true, 1));
    }
    
    @Override
    public ItemStack slotClick(final int aSlotIndex, final int aMouseclick, final int aShifthold, final EntityPlayer aPlayer) {
        if (aSlotIndex < 9) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        final Slot tSlot = (Slot)this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.getMetaTileEntity() == null) {
                return null;
            }
            if (aSlotIndex < 18) {
                ItemStack tStack = aPlayer.inventory.getItemStack();
                if (tStack == null) {
                    tStack = tSlot.getStack();
                    if (aMouseclick == 0) {
                        tSlot.putStack((ItemStack)null);
                    }
                    else if (tStack != null) {
                        tStack.setItemDamage(32767);
                    }
                }
                else {
                    tSlot.putStack(GT_Utility.copyAmount(1L, tStack));
                }
                return null;
            }
            GT_MetaTileEntity_ElectricFilter mte = (GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity();
            if (aSlotIndex == 18) {
				mte.bOutput = !mte.bOutput;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.energy_out." + mte.bOutput));
				return null;
			} else if (aSlotIndex == 19) {
				mte.bRedstoneIfFull = !mte.bRedstoneIfFull;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_if_full." + mte.bRedstoneIfFull));
				return null;
			} else if (aSlotIndex == 20) {
				mte.bInvert = !mte.bInvert;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_invert." + mte.bInvert));
				return null;
			 }else if (aSlotIndex == 21) {
				mte.bInvertFilter = !mte.bInvertFilter;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.filter." + (mte.bInvertFilter ? "invert" : "normal")));
				return null;
			} else if (aSlotIndex == 22) {
				mte.bIgnoreNBT = !mte.bIgnoreNBT;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.ignore_nbt." + mte.bIgnoreNBT));
				return null;
			}
        }
        return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    @Override
    public int getSlotCount() {
        return 9;
    }
    
    @Override
    public int getShiftClickSlotCount() {
        return 9;
    }
}
