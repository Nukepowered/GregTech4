package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricRegulatorAdvanced;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_ElectricRegulatorAdvanced extends GT_ContainerMetaTile_Machine {
	
	public final SyncedField<Integer[]> mTargetSlots = new SyncedField<>("mTargetSlots", Integer[].class);
	
	public GT_Container_ElectricRegulatorAdvanced(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  0,   8,  6));
        addSlotToContainer(new Slot(mTileEntity,  1,  26,  6));
        addSlotToContainer(new Slot(mTileEntity,  2,  44,  6));
        addSlotToContainer(new Slot(mTileEntity,  3,   8, 24));
        addSlotToContainer(new Slot(mTileEntity,  4,  26, 24));
        addSlotToContainer(new Slot(mTileEntity,  5,  44, 24));
        addSlotToContainer(new Slot(mTileEntity,  6,   8, 42));
        addSlotToContainer(new Slot(mTileEntity,  7,  26, 42));
        addSlotToContainer(new Slot(mTileEntity,  8,  44, 42));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  64,  7, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10,  81,  7, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 11,  98,  7, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 12,  64, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 13,  81, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 14,  98, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 15,  64, 41, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 16,  81, 41, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 17,  98, 41, false, true, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 119,  7, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 136,  7, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 153,  7, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 119, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 136, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 153, 24, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 119, 41, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 136, 41, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18, 153, 41, false, true, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18,   8, 63, false, true, 1));
        //addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18,  26, 63, false, true, 1));
        //addSlotToContainer(new GT_Slot_Holo(mTileEntity, 18,  44, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 1) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
	    	GT_MetaTileEntity_ElectricRegulatorAdvanced mte = (GT_MetaTileEntity_ElectricRegulatorAdvanced)mTileEntity.getMetaTileEntity();
		    if (aSlotIndex == 27) {
		    	mte.bOutput = !mte.bOutput;
	    		if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.energy_out." + mte.bOutput));
				return null;
		    } else if (aSlotIndex >= 9 && aSlotIndex < 18) {
		    	ItemStack tStack = aPlayer.inventory.getItemStack();
		    	if (tStack != null) {
			    	tSlot.putStack(GT_Utility.copy(tStack));
		    	} else {
		    		if (tSlot.getStack() != null) {
			    		if (aMouseclick == 0) {
			    			tSlot.getStack().stackSize-=(aShifthold==1?8:1);
			    			if (tSlot.getStack().stackSize <= 0) {
						    	tSlot.putStack(null);
			    			}
			    		} else {
			    			tSlot.getStack().stackSize+=(aShifthold==1?8:1);
			    			if (tSlot.getStack().stackSize > tSlot.getStack().getMaxStackSize()) {
			    				tSlot.getStack().stackSize = tSlot.getStack().getMaxStackSize();
			    			}
			    		}
		    		}
		    	}
		    	return null;
		    } else if (aSlotIndex >= 18 && aSlotIndex < 27) {
		    	mte.mTargetSlots[aSlotIndex-18]=Math.min(99, Math.max(0, mte.mTargetSlots[aSlotIndex-18] + ((aMouseclick==0?-1:+1)*(aShifthold==0?1:16))));
		    	return null;
		    }
    	}
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
	@Override
	public void prepareChanges(JsonObject data, boolean force) {
		super.prepareChanges(data, force);
		GT_MetaTileEntity_ElectricRegulatorAdvanced m = (GT_MetaTileEntity_ElectricRegulatorAdvanced)mTileEntity.getMetaTileEntity();
		Integer[] arr = new Integer[9];
		for (int i = 0; i < arr.length; i++) 
			arr[i] = m.mTargetSlots[i];
		mTargetSlots.updateAndWriteChanges(data, force, arr);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void processChanges(JsonObject data) {
		super.processChanges(data);
		mTargetSlots.readChanges(data);
	}
    
    public int getSlotCount() {
    	return 9;
    }
    
    public int getShiftClickSlotCount() {
    	return 9;
    }
}
