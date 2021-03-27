package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_DistillationTower extends MultiblockContainerBase {

	public GT_Container_DistillationTower(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  62,  41));
        addSlotToContainer(new Slot(mTileEntity, 1,  62,  59));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 2,  98,   5));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 3,  98,  23));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 4,  98,  41));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 5,  98,  59));
    }
    
    public int getSlotCount() {
    	return 6;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
