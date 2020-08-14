package gregtechmod.common.containers;

import gregtechmod.GT_Mod;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.deprecated.GT_TileEntity_Sonictron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Sonictron extends GT_ContainerMetaID_Machine {
	
	public GT_Container_Sonictron(InventoryPlayer aInventoryPlayer, GT_TileEntity_Sonictron aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
    	for (int j = 0; j < 8; j++)
    		for (int i = 0; i < 8; i++)
    			addSlotToContainer(new GT_Slot_Holo(mTileEntity, i+j*8, 24+16*i, 19+16*j, false, true, 24));
    }
    
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aEntityPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aEntityPlayer);
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    ItemStack tStack = tSlot.getStack();
	    if (tSlot != null) {
	    	if (aShifthold == 1) {
		    	tSlot.putStack(null);
	    		return null;
	    	} else if (aMouseclick == 0) {
		    	if (tStack == null) {
			    	tSlot.putStack(GT_Utility.copy(GT_Mod.mSoundItems.get(0)));
			    	return null;
		    	} else {
		    		for (int i = 1; i < GT_Mod.mSoundItems.size(); i++) {
		    			if (GT_Utility.areStacksEqual(GT_Mod.mSoundItems.get(i-1), tStack)) {
					    	tSlot.putStack(GT_Utility.copy(GT_Mod.mSoundItems.get(i)));
					    	return null;
		    			}
		    		}
			    	tSlot.putStack(null);
		    		return null;
		    	}
	    	} else {
		    	if (tStack == null) {
			    	return null;
		    	} else {
		    		for (int i = 0; i < GT_Mod.mSoundItems.size(); i++) {
		    			if (GT_Utility.areStacksEqual(GT_Mod.mSoundItems.get(i), tStack)) {
				    		tStack.stackSize++;
				    		tStack.stackSize%=(GT_Mod.mSoundCounts.get(i)+1);
				    		if (tStack.stackSize == 0) tStack.stackSize++;
					    	return null;
		    			}
		    		}
		    	}
	    	}
    	}
    	return null;
    }
    
    public boolean doesBindPlayerInventory() {
    	return false;
    }
}
