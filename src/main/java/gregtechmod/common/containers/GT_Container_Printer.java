package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Container_BasicMachine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author TheDarkDnKTv
 *
 */
public class GT_Container_Printer extends GT_Container_BasicMachine {
	public GT_Container_Printer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
    @Override
	public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  1,  35,  25));
        addSlotToContainer(new Slot(mTileEntity,  2,  53,  25));
        addSlotToContainer(new Slot(mTileEntity,  3,  80,  63));
        addSlotToContainer(new GT_Slot_Output(mTileEntity,  4, 107,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity,  5, 125,  25));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0, 26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0, 44, 63, false, true, 1));
    }
}
