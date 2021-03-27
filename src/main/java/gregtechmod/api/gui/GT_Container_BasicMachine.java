package gregtechmod.api.gui;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.common.network.SyncedField;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * The Container I use for all my Basic Machines
 */
public class GT_Container_BasicMachine extends GT_ContainerMetaTile_Machine {
	
	public GT_Container_BasicMachine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
    @Override
	public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  1,  35,  25));
        addSlotToContainer(new Slot(mTileEntity,  2,  53,  25));
        addSlotToContainer(new Slot(mTileEntity,  5,  80,  63));
        addSlotToContainer(new GT_Slot_Output(mTileEntity,  3, 107,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity,  4, 125,  25));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0,  8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0, 26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 0, 44, 63, false, true, 1));
    }
    
    public final SyncedField<Boolean> mOutputting		= new SyncedField<Boolean>("mOutputting", Boolean.FALSE);
    public final SyncedField<Boolean> mItemTransfer		= new SyncedField<Boolean>("mItemTransfer", Boolean.FALSE);
    public final SyncedField<Boolean> mSeperatedInputs	= new SyncedField<Boolean>("mSeperatedInputs", Boolean.FALSE);
    
    @Override
	public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < getSlotCount()) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    	return this.handleClick(aSlotIndex, aMouseclick, aShifthold, aPlayer) ? null : super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    /**
     * Custom slotClick to handle buttons and etc
     * @return true if super call is not needed
     */
    public boolean handleClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (mTileEntity.getMetaTileEntity() != null && tSlot != null) {
		    if (aSlotIndex == 5) {
		    	((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bOutput;
		    	return true;
		    } else if (aSlotIndex == 6) {
		    	((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bItemTransfer = !((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bItemTransfer;
		    	return true;
		    } else if (aSlotIndex == 7) {
		    	((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bSeperatedInputs = !((GT_MetaTileEntity_BasicMachine)mTileEntity.getMetaTileEntity()).bSeperatedInputs;
		    	return true;
		    }
    	}
	    
	    return false;
    }
    
    @Override
    public void prepareChanges(JsonObject data, boolean force) {
    	super.prepareChanges(data, force);
    	GT_MetaTileEntity_BasicMachine m = (GT_MetaTileEntity_BasicMachine) mTileEntity.getMetaTileEntity();
    	mOutputting.set(m.bOutput);
    	mItemTransfer.set(m.bItemTransfer);
    	mSeperatedInputs.set(m.bSeperatedInputs);

    	mOutputting.writeChanges(data, force);
    	mItemTransfer.writeChanges(data, force);
    	mSeperatedInputs.writeChanges(data, force);
    }
    
    @Override
    @SideOnly(Side.CLIENT)    
    public void processChanges(JsonObject data) {
    	super.processChanges(data);
    	mOutputting.readChanges(data);
    	mItemTransfer.readChanges(data);
    	mSeperatedInputs.readChanges(data);
    }
    
    @Override
	public int getSlotCount() {
    	return 5;
    }
    
    @Override
	public int getShiftClickSlotCount() {
    	return 2;
    }
}
