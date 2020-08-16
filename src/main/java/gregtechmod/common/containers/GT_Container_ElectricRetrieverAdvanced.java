package gregtechmod.common.containers;

import gregtechmod.api.interfaces.*;
import gregtechmod.api.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import gregtechmod.common.tileentities.automation.*;
import gregtechmod.api.util.*;
import net.minecraft.inventory.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;

public class GT_Container_ElectricRetrieverAdvanced extends GT_ContainerMetaTile_Machine
{
    public int[] mTargetSlots;
    
    public GT_Container_ElectricRetrieverAdvanced(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
        this.mTargetSlots = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
    
    @Override
    public void addSlots(final InventoryPlayer aInventoryPlayer) {
        this.mTargetSlots = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 0, 64, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 1, 81, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 2, 98, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 3, 64, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 4, 81, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 5, 98, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 6, 64, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 7, 81, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 8, 98, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 119, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 136, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 153, 7, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 119, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 136, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 153, 24, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 119, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 136, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 153, 41, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 8, 63, false, true, 1));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 9, 26, 63, false, true, 1));
    }
    
    @Override
    public ItemStack slotClick(final int aSlotIndex, final int aMouseclick, final int aShifthold, final EntityPlayer aPlayer) {
        if (aSlotIndex < 0) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        final Slot tSlot = (Slot) this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.getMetaTileEntity() == null) {
                return null;
            }
            if (aSlotIndex == 18) {
                ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).bOutput;
                if (((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).bOutput) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
                }
                return null;
            }
            if (aSlotIndex == 19) {
                ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mPartialRequests = !((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mPartialRequests;
                if (((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mPartialRequests) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Partial Requests allowed");
                }
                else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Partial Requests forbidden");
                }
                return null;
            }
            if (aSlotIndex >= 0 && aSlotIndex < 9) {
                final ItemStack tStack = aPlayer.inventory.getItemStack();
                if (tStack != null) {
                    tSlot.putStack(GT_Utility.copy(tStack));
                }
                else if (tSlot.getStack() != null) {
                    if (aMouseclick == 0) {
                        final ItemStack func_75211_c = tSlot.getStack();
                        func_75211_c.stackSize -= ((aShifthold == 1) ? 8 : 1);
                        if (tSlot.getStack().stackSize <= 0) {
                            tSlot.putStack((ItemStack)null);
                        }
                    }
                    else {
                        final ItemStack func_75211_c2 = tSlot.getStack();
                        func_75211_c2.stackSize += ((aShifthold == 1) ? 8 : 1);
                        if (tSlot.getStack().stackSize > tSlot.getStack().getMaxStackSize()) {
                            tSlot.getStack().stackSize = tSlot.getStack().getMaxStackSize();
                        }
                    }
                }
                return null;
            }
            if (aSlotIndex >= 9 && aSlotIndex < 18) {
                ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mTargetSlots[aSlotIndex - 9] = Math.min(99, Math.max(0, ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mTargetSlots[aSlotIndex - 9] + ((aMouseclick == 0) ? -1 : 1) * ((aShifthold == 0) ? 1 : 16)));
                return null;
            }
        }
        return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.isClientSide() || this.mTileEntity.getMetaTileEntity() == null) {
            return;
        }
        this.mTargetSlots = new int[9];
        for (int i = 0; i < 9; ++i) {
            this.mTargetSlots[i] = ((GT_MetaTileEntity_ElectricRetrieverAdvanced)this.mTileEntity.getMetaTileEntity()).mTargetSlots[i];
        }
        
        @SuppressWarnings("rawtypes")
		Iterator var2 = crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            for (int i = 0; i < 9; i++) var1.sendProgressBarUpdate(this, 100+i, mTargetSlots[i]);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(final int par1, final int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100: {
                this.mTargetSlots[0] = par2;
                break;
            }
            case 101: {
                this.mTargetSlots[1] = par2;
                break;
            }
            case 102: {
                this.mTargetSlots[2] = par2;
                break;
            }
            case 103: {
                this.mTargetSlots[3] = par2;
                break;
            }
            case 104: {
                this.mTargetSlots[4] = par2;
                break;
            }
            case 105: {
                this.mTargetSlots[5] = par2;
                break;
            }
            case 106: {
                this.mTargetSlots[6] = par2;
                break;
            }
            case 107: {
                this.mTargetSlots[7] = par2;
                break;
            }
            case 108: {
                this.mTargetSlots[8] = par2;
                break;
            }
        }
    }
    
    @Override
    public int getSlotCount() {
        return 0;
    }
    
    @Override
    public int getShiftClickSlotCount() {
        return 0;
    }
}
