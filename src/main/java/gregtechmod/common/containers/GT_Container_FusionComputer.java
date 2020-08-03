package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_Container_FusionComputer extends GT_ContainerMetaTile_Machine {
	
	public GT_Container_FusionComputer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}
	
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  155,  23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  155,  41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  155,  59, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  155,  77, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  155,  95, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  155, 113, false, false, 1));
    }
    
    public int getSlotCount() {
    	return 0;
    }
    
    public int getShiftClickSlotCount() {
    	return 0;
    }
    
    public boolean doesBindPlayerInventory() {
    	return false;
    }
}
