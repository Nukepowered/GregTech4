package gregtechmod.common.containers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricSorter;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_ElectricSorter extends GT_ContainerMetaTile_Machine {

	public final SyncedField<Byte> mTargetDirection = new SyncedField<>("mTargetDirection", new Byte((byte) 0));
	
	public GT_Container_ElectricSorter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  0,  25,  23));
    	
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  63,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  2,  80,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  3,  97,  6, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  4,  63, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  5,  80, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  6,  97, 23, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  7,  63, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  8,  80, 40, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  97, 40, false, true, 1));

        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10,  44, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 10, 134, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 1) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
	    	GT_MetaTileEntity_ElectricBufferSmall mte = (GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity();
		    if (aSlotIndex < 10) {
		    	ItemStack tStack = aPlayer.inventory.getItemStack();
		    	if (tStack == null) {
		    		tStack = tSlot.getStack();
		    		if (aMouseclick == 0) {
		    			tSlot.putStack(null);
		    		} else {
		    			if (tStack != null) {
		    				tStack.setItemDamage(GregTech_API.ITEM_WILDCARD_DAMAGE);
		    			}
		    		}
		    	} else {
			    	tSlot.putStack(GT_Utility.copy(1, tStack));
		    	}
		    	return null;
		    } else if (aSlotIndex == 10) {
				mte.bOutput = !mte.bOutput;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.energy_out." + mte.bOutput));
				return null;
			} else if (aSlotIndex == 11) {
				mte.bRedstoneIfFull = !mte.bRedstoneIfFull;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_if_full." + mte.bRedstoneIfFull));
				return null;
			} else if (aSlotIndex == 12) {
				mte.bInvert = !mte.bInvert;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_invert." + mte.bInvert));
				return null;
			} else if (aSlotIndex == 13) {
		    	((GT_MetaTileEntity_ElectricSorter)mTileEntity.getMetaTileEntity()).mTargetDirection = (byte) ((((GT_MetaTileEntity_ElectricSorter)mTileEntity.getMetaTileEntity()).mTargetDirection + 1) % 6);
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }

	@Override
	public void prepareChanges(JsonObject data, boolean force) {
		super.prepareChanges(data, force);
		mTargetDirection.updateAndWriteChanges(data, force, ((GT_MetaTileEntity_ElectricSorter)mTileEntity.getMetaTileEntity()).mTargetDirection);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void processChanges(JsonObject data) {
		super.processChanges(data);
		mTargetDirection.readChanges(data);
	}
    
    public int getSlotCount() {
    	return 1;
    }
    
    public int getShiftClickSlotCount() {
    	return 1;
    }
}
