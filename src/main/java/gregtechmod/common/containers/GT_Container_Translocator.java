package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_Translocator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Translocator extends GT_ContainerMetaTile_Machine {

	public GT_Container_Translocator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0,  63,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  80,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,  97,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  3,  63, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  4,  80, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  5,  97, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  6,  63, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  7,  80, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  8,  97, 40, false, true, 1));

        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  26, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
		    if (aSlotIndex < 9) {
		    	tSlot.putStack(GT_Utility.copy(1, aPlayer.inventory.getItemStack()));
		    	return null;
		    } else if (aSlotIndex == 9) {
		    	((GT_MetaTileEntity_Translocator)mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_Translocator)mTileEntity.getMetaTileEntity()).bOutput;
			    if (((GT_MetaTileEntity_Translocator)mTileEntity.getMetaTileEntity()).bOutput)
			    	GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
		    	return null;
		    } else if (aSlotIndex == 10) {
		    	((GT_MetaTileEntity_Translocator)mTileEntity.getMetaTileEntity()).bInvertFilter = !((GT_MetaTileEntity_Translocator)mTileEntity.getMetaTileEntity()).bInvertFilter;
			    if (((GT_MetaTileEntity_Translocator)mTileEntity.getMetaTileEntity()).bInvertFilter)
			    	GT_Utility.sendChatToPlayer(aPlayer, "Inverted the Filter");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Filter works normal");
		    	return null;
		    }
	    }
    	
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    
    public int getSlotCount() {
    	return 0;
    }
    
    public int getShiftClickSlotCount() {
    	return 0;
    }
}
