package gregtechmod.common.containers;

import gregtechmod.api.interfaces.*;
import net.minecraft.inventory.*;
import gregtechmod.api.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
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
            if (this.mTileEntity.getMetaTileEntity() == null) {
                return null;
            }
            if (aSlotIndex == 9) {
                ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).clickTypeIcon(aMouseclick != 0);
                return null;
            }
            if (aSlotIndex == 10) {
                ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bOutput;
                if (((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bOutput) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
                }
                return null;
            }
            if (aSlotIndex == 11) {
                ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull;
                if (((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bRedstoneIfFull) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Emit Redstone if slots contain something");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Redstone");
                }
                return null;
            }
            if (aSlotIndex == 12) {
                ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvert = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvert;
                if (((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvert) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Invert Redstone");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't invert Redstone");
                }
                return null;
            }
            if (aSlotIndex == 13) {
                ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter;
                if (((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bInvertFilter) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Invert Filter");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't invert Filter");
                }
                return null;
            }
            if (aSlotIndex == 14) {
                ((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bNBTAllowed = !((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bNBTAllowed;
                if (((GT_MetaTileEntity_ElectricTypeFilter)this.mTileEntity.getMetaTileEntity()).bNBTAllowed) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Allow Items with NBT");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't allow Items with NBT");
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
