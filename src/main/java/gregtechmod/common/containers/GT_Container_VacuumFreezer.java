package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_VacuumFreezer extends MultiblockContainerBase {
	
	public GT_Container_VacuumFreezer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  34,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 1,  86,  25));
    }
    
    public int getSlotCount() {
    	return 2;
    }

    public int getShiftClickSlotCount() {
    	return 1;
    }
}
