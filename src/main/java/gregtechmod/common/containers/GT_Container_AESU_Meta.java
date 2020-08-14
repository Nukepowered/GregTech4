package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Armor;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.energy.storage.GT_MetaTileEntity_AESU;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_AESU_Meta extends GT_ContainerMetaTile_Machine {
	
	public GT_Container_AESU_Meta(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
	@Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0, 128,  14));
        addSlotToContainer(new Slot(mTileEntity, 1, 128,  50));

        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 107,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 107, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 107, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 107, 59, false, false, 1));
        
        addSlotToContainer(new GT_Slot_Armor(aInventoryPlayer, 36, 152, 59, 3, aInventoryPlayer.player));
        addSlotToContainer(new GT_Slot_Armor(aInventoryPlayer, 37, 152, 41, 2, aInventoryPlayer.player));
        addSlotToContainer(new GT_Slot_Armor(aInventoryPlayer, 38, 152, 23, 1, aInventoryPlayer.player));
        addSlotToContainer(new GT_Slot_Armor(aInventoryPlayer, 39, 152,  5, 0, aInventoryPlayer.player));
    }
	
	@Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	int tOutput = ((GT_MetaTileEntity_AESU)mTileEntity.getMetaTileEntity()).mOutput;
	    	switch(aSlotIndex) {
	    	case 2:
	    		((GT_MetaTileEntity_AESU)mTileEntity.getMetaTileEntity()).mOutput = Math.min(8192, tOutput + (aShifthold==1?256:64));
	        	return null;
	    	case 3:
	    		((GT_MetaTileEntity_AESU)mTileEntity.getMetaTileEntity()).mOutput = Math.min(8192, tOutput + (aShifthold==1?16:1));
	        	return null;
	    	case 4:
	    		((GT_MetaTileEntity_AESU)mTileEntity.getMetaTileEntity()).mOutput = Math.max(   0, tOutput - (aShifthold==1?16:1));
	        	return null;
	    	case 5:
	    		((GT_MetaTileEntity_AESU)mTileEntity.getMetaTileEntity()).mOutput = Math.max(   0, tOutput - (aShifthold==1?256:64));
	        	return null;
	    	}
    	}
	    return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
	
	@Override
    public int getSlotCount() {
    	return 2;
    }
	
	@Override
    public int getShiftClickSlotCount() {
    	return 2;
    }
}
