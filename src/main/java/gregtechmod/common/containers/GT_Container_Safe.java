package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Safe extends GT_ContainerMetaTile_Machine {

	public GT_Container_Safe(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
    	for (int y = 0; y < 3; y++) for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(mTileEntity, 1+x+y*9, 8+x*18, 23+y*18));
        }
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 28, 80, 5, false, false, 0));
    }
    
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex != 27) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null && aShifthold == 0 && aMouseclick == 1) {
			tSlot.putStack(GT_Utility.copy(0, aPlayer.inventory.getItemStack()));
    	}
    	return null;
    }
    
    public int getSlotCount() {
    	return 27;
    }
    
    public int getShiftClickSlotCount() {
    	return 27;
    }
}
