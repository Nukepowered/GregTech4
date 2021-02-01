package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GT_Container_ChemicalReactor extends GT_ContainerMetaTile_Machine {

	public GT_Container_ChemicalReactor(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot				(mTileEntity, 0,  70,  16));
        addSlotToContainer(new Slot				(mTileEntity, 1,  90,  16));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 2,  80,  46));
    }
    
    public int getSlotCount() {
    	return 3;
    }
    
    public int getShiftClickSlotCount() {
    	return 2;
    }
}
