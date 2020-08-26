package gregtechmod.common.containers;

import gregtechmod.api.interfaces.*;
import net.minecraft.inventory.*;
import gregtechmod.api.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
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
            if (aSlotIndex == 18) {
                ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bOutput;
                if (((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bOutput) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
                }
                return null;
            }
            if (aSlotIndex == 19) {
                ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull;
                if (((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Emit Redstone if slots contain something");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Redstone");
                }
                return null;
            }
            if (aSlotIndex == 20) {
                ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvert = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvert;
                if (((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvert) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Invert Redstone");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't invert Redstone");
                }
                return null;
            }
            if (aSlotIndex == 21) {
                ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter;
                if (((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Invert Filter");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't invert Filter");
                }
                return null;
            }
            if (aSlotIndex == 22) {
                ((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bIgnoreNBT = !((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bIgnoreNBT;
                if (((GT_MetaTileEntity_ElectricFilter)this.mTileEntity.getMetaTileEntity()).bIgnoreNBT) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Ignore NBT");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "NBT has to match");
                }
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
