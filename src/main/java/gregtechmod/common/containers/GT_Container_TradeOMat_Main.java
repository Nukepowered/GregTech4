package gregtechmod.common.containers;

import gregtechmod.api.interfaces.*;
import net.minecraft.inventory.*;
import gregtechmod.api.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public class GT_Container_TradeOMat_Main extends GT_ContainerMetaTile_Machine
{
    public GT_Container_TradeOMat_Main(final InventoryPlayer aInventoryPlayer, final IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }
    
    @Override
    public void addSlots(final InventoryPlayer aInventoryPlayer) {
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 54, 44, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 55, 62, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 56, 80, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 57, 98, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 58, 116, 14));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 59, 44, 50));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 60, 62, 50));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 61, 80, 50));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 62, 98, 50));
        this.addSlotToContainer(new Slot((IInventory)this.mTileEntity, 63, 116, 50));
        this.addSlotToContainer(new GT_Slot_Render((IInventory)this.mTileEntity, 64, 8, 14));
        this.addSlotToContainer(new GT_Slot_Render((IInventory)this.mTileEntity, 65, 8, 50));
    }
    
    @Override
    public ItemStack slotClick(final int aSlotIndex, final int aMouseclick, final int aShifthold, final EntityPlayer aPlayer) {
        if (aSlotIndex < 10 || aSlotIndex > 11) {
            return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        return null;
    }
    
    @Override
    public int getSlotCount() {
        return 10;
    }
    
    @Override
    public int getShiftClickSlotCount() {
        return 5;
    }
}
