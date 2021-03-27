package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_AdvancedTranslocator;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_AdvancedTranslocator extends GT_ContainerMetaTile_Machine {

	public GT_Container_AdvancedTranslocator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
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
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9,  43,  5, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  9, 117,  5, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
	    	GT_MetaTileEntity_AdvancedTranslocator mte = (GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity();
	    	
		    if (aSlotIndex < 9) {
		    	ItemStack tStack = aPlayer.inventory.getItemStack();
		    	if (tStack != null) {
		    		tStack = GT_Utility.copy(1, tStack);
		    	}
		    	tSlot.putStack(tStack);
		    	return null;
		    } else if (aSlotIndex == 9) {
		    	mte.bOutput = !mte.bOutput;
		    	if (aPlayer.worldObj.isRemote) 
		    		GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.energy_out." + mte.bOutput));
		    	return null;
		    } else if (aSlotIndex == 10) {
		    	mte.bInvertFilter = !mte.bInvertFilter;
		    	if (aPlayer.worldObj.isRemote) 
		    		GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.filter." + (mte.bInvertFilter ? "invert" : "normal")));
		    	return null;
		    } else if (aSlotIndex == 11) {
		    	mte.mInputSide = (byte) ((mte.mInputSide + 1) % 6);
		    } else if (aSlotIndex == 12) {
		    	mte.mOutputSide = (byte) ((mte.mOutputSide + 1) % 6);
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public SyncedField<Byte> mInputSide 	= new SyncedField<>("mInputSide" , Byte.valueOf((byte)0));
    public SyncedField<Byte> mOutputSide 	= new SyncedField<>("mOutputSide", Byte.valueOf((byte)0));
    
    @Override
    public void prepareChanges(JsonObject data, boolean force) {
    	super.prepareChanges(data, force);
    	mInputSide.set(((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).mInputSide);
    	mOutputSide.set(((GT_MetaTileEntity_AdvancedTranslocator)mTileEntity.getMetaTileEntity()).mOutputSide);
    	
    	mInputSide.writeChanges(data, force);
    	mOutputSide.writeChanges(data, force);
    }
    
    @Override
    @SideOnly(Side.CLIENT)    
    public void processChanges(JsonObject data) {
    	super.processChanges(data);
    	
    	mInputSide.readChanges(data);
    	mInputSide.readChanges(data);
    }
}
