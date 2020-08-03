package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Maintenance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class GT_Container_MaintenanceHatch extends GT_ContainerMetaTile_Machine {
	
	public GT_Container_MaintenanceHatch(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}
	
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  80,  35, false, false, 1));
    }
    
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex != 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    	if (aPlayer.inventory.getItemStack() != null) {
    		((GT_MetaTileEntity_Hatch_Maintenance)mTileEntity.getMetaTileEntity()).onToolClick(aPlayer.inventory.getItemStack(), aPlayer);
    		if (aPlayer.inventory.getItemStack().stackSize <= 0) aPlayer.inventory.setItemStack(null);
    	}
		return null;
    }
}