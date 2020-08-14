package gregtechmod.common.containers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricBufferSmall;
import gregtechmod.common.tileentities.automation.GT_MetaTileEntity_ElectricSorter;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_ElectricSorter extends GT_ContainerMetaTile_Machine {

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
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bOutput;
			    if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bOutput)
			    	GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
		    	return null;
		    } else if (aSlotIndex == 11) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bRedstoneIfFull = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bRedstoneIfFull;
		    	if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bRedstoneIfFull)
				    GT_Utility.sendChatToPlayer(aPlayer, "Emit Redstone if slot contains something");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Redstone");
		    	return null;
		    } else if (aSlotIndex == 12) {
		    	((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bInvert = !((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bInvert;
		    	if (((GT_MetaTileEntity_ElectricBufferSmall)mTileEntity.getMetaTileEntity()).bInvert)
				    GT_Utility.sendChatToPlayer(aPlayer, "Invert Redstone");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't invert Redstone");
		    	return null;
		    } else if (aSlotIndex == 13) {
		    	((GT_MetaTileEntity_ElectricSorter)mTileEntity.getMetaTileEntity()).mTargetDirection = (byte) ((((GT_MetaTileEntity_ElectricSorter)mTileEntity.getMetaTileEntity()).mTargetDirection + 1) % 6);
		    }
    	}
	    
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public int mTargetDirection;
    
    @SuppressWarnings("rawtypes")
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	mTargetDirection  = ((GT_MetaTileEntity_ElectricSorter)mTileEntity.getMetaTileEntity()).mTargetDirection;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, mTargetDirection);
        }
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mTargetDirection = par2; break;
    	}
    }
    
    public int getSlotCount() {
    	return 1;
    }
    
    public int getShiftClickSlotCount() {
    	return 1;
    }
}
