package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_Electrolyzer extends GT_ContainerMetaTile_Machine {

	public GT_Container_Electrolyzer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot				(mTileEntity, 0,  80,  46));
        addSlotToContainer(new Slot				(mTileEntity, 1,  50,  46));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 2,  50,  16));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 3,  70,  16));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 4,  90,  16));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 5, 110,  16));
        addSlotToContainer(new GT_Slot_Holo		(mTileEntity, 6, 110,  46, false, false, 64));
    }
    
    public int getSlotCount() {
    	return 6;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
