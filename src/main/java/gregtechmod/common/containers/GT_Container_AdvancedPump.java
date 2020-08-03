package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_AdvancedPump extends GT_ContainerMetaTile_Machine {

	public GT_Container_AdvancedPump(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}
	
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0, 116,  17));
        addSlotToContainer(new Slot(mTileEntity, 1,  80,  17));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 2,  80,  53));
    }
    
    public int mContent = 0;
    
    public int getSlotCount() {
    	return 3;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
