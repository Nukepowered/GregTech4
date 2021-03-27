package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;

public class GT_Container_ElectricItemClearer extends GT_ContainerMetaTile_Machine {

	public GT_Container_ElectricItemClearer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  0,  80,  23));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  1,  44, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 1) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
	    	GT_MetaTileEntity_ElectricBufferSmall mte = (GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity();
		    if (aSlotIndex == 1) {
				mte.bOutput = !mte.bOutput;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.energy_out." + mte.bOutput));
				return null;
			} else if (aSlotIndex == 2) {
				mte.bRedstoneIfFull = !mte.bRedstoneIfFull;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_if_full." + mte.bRedstoneIfFull));
				return null;
			} else if (aSlotIndex == 3) {
				mte.bInvert = !mte.bInvert;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_invert." + mte.bInvert));
				return null;
			}
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    
    public int getSlotCount() {
    	return 1;
    }
    
    public int getShiftClickSlotCount() {
    	return 1;
    }
}
