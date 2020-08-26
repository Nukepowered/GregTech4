package gregtechmod.common.containers;

import gregtechmod.api.interfaces.*;
import gregtechmod.api.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import gregtechmod.api.util.*;
import gregtechmod.common.tileentities.storage.*;
import net.minecraft.inventory.*;
import java.util.*;

public class GT_Container_TradeOMat_Settings extends GT_ContainerMetaTile_Machine
{
    public int mPerformedTrades;
    
    public GT_Container_TradeOMat_Settings(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
        this.mPerformedTrades = 0;
    }
    
    @Override
    public void addSlots(final InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 66, 152, 32));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 64, 8, 23, false, false, 0));
        this.addSlotToContainer(new GT_Slot_Holo((IInventory)this.mTileEntity, 65, 8, 41, false, false, 0));
    }
    
    @Override
    public ItemStack slotClick(final int aSlotIndex, final int aMouseclick, final int aShifthold, final EntityPlayer aPlayer) {
        if (aSlotIndex < 1 || aSlotIndex > 2) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        final Slot tSlot = (Slot) this.inventorySlots.get(aSlotIndex);
        if (tSlot != null) {
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
        }
        return null;
    }
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.mTileEntity.isClientSide() || this.mTileEntity.getMetaTileEntity() == null) {
            return;
        }
        this.mPerformedTrades = ((GT_MetaTileEntity_TradeOMat)this.mTileEntity.getMetaTileEntity()).mPerformedTrades;

        @SuppressWarnings("rawtypes")
		Iterator var2 = crafters.iterator();
        while (var2.hasNext()) {
        	ICrafting var3 = (ICrafting)var2.next();
            var3.sendProgressBarUpdate((Container)this, 100, this.mPerformedTrades & 0xFFFF);
            var3.sendProgressBarUpdate((Container)this, 101, this.mPerformedTrades >>> 16);
        }
    }
    
    @Override
    public void updateProgressBar(final int par1, final int par2) {
        super.updateProgressBar(par1, par2);
        switch (par1) {
            case 100: {
                this.mPerformedTrades = ((this.mPerformedTrades & 0xFFFF0000) | par2);
                break;
            }
            case 101: {
                this.mPerformedTrades = ((this.mPerformedTrades & 0xFFFF) | par2 << 16);
                break;
            }
        }
    }
    
    @Override
    public int getSlotCount() {
        return 1;
    }
    
    @Override
    public int getShiftClickSlotCount() {
        return 1;
    }
}
