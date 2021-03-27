package gregtechmod.common.containers;

import gregtechmod.api.interfaces.*;
import net.minecraft.inventory.*;
import gregtechmod.api.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.ChatComponentTranslation;
import gregtechmod.common.tileentities.automation.*;
import gregtechmod.api.util.*;

public class GT_Container_ElectricTypeFilter extends GT_ContainerMetaTile_Machine
{
    public GT_Container_ElectricTypeFilter(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
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
        this.addSlotToContainer(new GT_Slot_Render((IInventory)this.mTileEntity, 9, 35, 23));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 8, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 26, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 44, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 62, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 10, 80, 63, false, true, 1));
    }
    
    @Override
    public ItemStack slotClick(final int aSlotIndex, final int aMouseclick, final int aShifthold, final EntityPlayer aPlayer) {
        if (aSlotIndex < 9) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        final Slot tSlot = (Slot) this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.getMetaTileEntity() == null) return null;
            GT_MetaTileEntity_ElectricTypeFilter mte = (GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity();
            
            if (aSlotIndex == 9) {
                mte.clickTypeIcon(aMouseclick != 0);
                return null;
            } else if (aSlotIndex == 10) {
				mte.bOutput = !mte.bOutput;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.energy_out." + mte.bOutput));
				return null;
			} else if (aSlotIndex == 11) {
				mte.bRedstoneIfFull = !mte.bRedstoneIfFull;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_if_full." + mte.bRedstoneIfFull));
				return null;
			} else if (aSlotIndex == 12) {
				mte.bInvert = !mte.bInvert;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_invert." + mte.bInvert));
				return null;
			} else if (aSlotIndex == 13) {
				mte.bInvertFilter = !mte.bInvertFilter;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.filter." + (mte.bInvertFilter ? "invert" : "normal")));
				return null;
			} else if (aSlotIndex == 14) {
				mte.bNBTAllowed = !mte.bNBTAllowed;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.items_with_nbt." + mte.bNBTAllowed));
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
