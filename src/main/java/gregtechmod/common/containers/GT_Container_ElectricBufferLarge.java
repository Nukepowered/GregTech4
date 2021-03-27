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

public class GT_Container_ElectricBufferLarge extends GT_ContainerMetaTile_Machine {

	public GT_Container_ElectricBufferLarge(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
    	for (int y = 0; y < 3; y++) for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(mTileEntity, x+y*9, 8+x*18, 5+y*18));
        }
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  27,   8, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  27,  26, 63, false, true, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity,  27,  44, 63, false, true, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 27) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
	    	GT_MetaTileEntity_ElectricBufferSmall mte = (GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity();
			if (aSlotIndex == 27) {
				mte.bOutput = !mte.bOutput;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.energy_out." + mte.bOutput));
				return null;
			} else if (aSlotIndex == 28) {
				mte.bRedstoneIfFull = !mte.bRedstoneIfFull;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_if_full." + mte.bRedstoneIfFull));
				return null;
			} else if (aSlotIndex == 29) {
				mte.bInvert = !mte.bInvert;
				if (aPlayer.worldObj.isRemote)
					GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("metatileentity.status.redstone_invert." + mte.bInvert));
				return null;
			}
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    
    public int getSlotCount() {
    	return 27;
    }
    
    public int getShiftClickSlotCount() {
    	return 27;
    }
}
