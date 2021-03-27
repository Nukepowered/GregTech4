package gregtechmod.common.containers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.redstone.GT_MetaTileEntity_RedstoneCircuitBlock;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_RedstoneCircuitBlock extends GT_ContainerMetaTile_Machine {

	public final SyncedField<Integer[]> mData 	= new SyncedField<>("mData", Integer[].class);
	public final SyncedField<Integer> mGate 	= new SyncedField<>("mGate", new Integer(0));
	
	public GT_Container_RedstoneCircuitBlock(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0,   8,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0,   8, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0,   8, 42, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0,   8, 60, false, true, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0, 152,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0, 152, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  0, 152, 42, false, true, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  26,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,  26, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  3,  26, 42, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  4,  26, 60, false, true, 1));
    }
    
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0 || aSlotIndex > 6) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
		    if (aSlotIndex < 4) {
		    	ItemStack tStack = aPlayer.inventory.getItemStack();
		    	if (tStack == null) {
		    		((GT_MetaTileEntity_RedstoneCircuitBlock)mTileEntity.getMetaTileEntity()).changeGateData(aSlotIndex, aMouseclick==0?aShifthold==0?+1:aShifthold==1?+128:+16:aShifthold==0?-1:aShifthold==1?-128:-16);
		    	} else {
		    		tStack = GT_Utility.copy(tStack);
		    		if (aMouseclick != 0) tStack.setItemDamage(GregTech_API.ITEM_WILDCARD_DAMAGE);
		    		((GT_MetaTileEntity_RedstoneCircuitBlock)mTileEntity.getMetaTileEntity()).stackGateData(aSlotIndex, tStack);
		    	}
		    	return null;
		    } else if (aSlotIndex == 4) {
		    	((GT_MetaTileEntity_RedstoneCircuitBlock)mTileEntity.getMetaTileEntity()).switchOutput();
		    } else if (aSlotIndex == 5) {
		    	mTileEntity.setActive(!mTileEntity.isActive());
		    } else if (aSlotIndex == 6) {
		    	if (aMouseclick == 0)
		    		((GT_MetaTileEntity_RedstoneCircuitBlock)mTileEntity.getMetaTileEntity()).switchGateForward(aShifthold != 0);
		    	else
		    		((GT_MetaTileEntity_RedstoneCircuitBlock)mTileEntity.getMetaTileEntity()).switchGateBackward(aShifthold != 0);
		    }
    	}
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
	@Override
	public void prepareChanges(JsonObject data, boolean force) {
		super.prepareChanges(data, force);
		GT_MetaTileEntity_RedstoneCircuitBlock m = (GT_MetaTileEntity_RedstoneCircuitBlock)mTileEntity.getMetaTileEntity();
		Integer[] arr = new Integer[8];
		for (int i = 0; i < arr.length; i++)
			arr[i] = m.mGateData[i];
		
		mGate.updateAndWriteChanges(data, force, m.mGate);
		mData.updateAndWriteChanges(data, force, arr);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void processChanges(JsonObject data) {
		super.processChanges(data);
		mGate.readChanges(data);
		mData.readChanges(data);
	}
}
