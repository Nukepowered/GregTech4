package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.GT_MetaTileEntity_ElectricRegulatorAdvanced;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_ElectricRegulatorAdvanced extends GT_ContainerMetaTile_Machine {

	public GT_Container_ElectricRegulatorAdvanced(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
    	mTargetSlots = new int[] {0,0,0,0,0,0,0,0,0};
    	
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
		    if (aSlotIndex == 27) {
		    	((GT_MetaTileEntity_ElectricRegulatorAdvanced)mTileEntity.getMetaTileEntity()).bOutput = !((GT_MetaTileEntity_ElectricRegulatorAdvanced)mTileEntity.getMetaTileEntity()).bOutput;
			    if (((GT_MetaTileEntity_ElectricRegulatorAdvanced)mTileEntity.getMetaTileEntity()).bOutput)
			    	GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
			    else
			    	GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
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
		    	((GT_MetaTileEntity_ElectricRegulatorAdvanced)mTileEntity.getMetaTileEntity()).mTargetSlots[aSlotIndex-18]=Math.min(99, Math.max(0, ((GT_MetaTileEntity_ElectricRegulatorAdvanced)mTileEntity.getMetaTileEntity()).mTargetSlots[aSlotIndex-18] + ((aMouseclick==0?-1:+1)*(aShifthold==0?1:16))));
		    	return null;
		    }
    	}
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public int[] mTargetSlots = new int[] {0,0,0,0,0,0,0,0,0};
    
    @SuppressWarnings("rawtypes")
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	mTargetSlots = new int[9];
    	for (int i = 0; i < 9; i++) mTargetSlots[i] = (((GT_MetaTileEntity_ElectricRegulatorAdvanced)mTileEntity.getMetaTileEntity()).mTargetSlots[i]);
    	
        Iterator var2 = crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            for (int i = 0; i < 9; i++) var1.sendProgressBarUpdate(this, 100+i, mTargetSlots[i]);
        }
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mTargetSlots[0] = par2; break;
    	case 101: mTargetSlots[1] = par2; break;
    	case 102: mTargetSlots[2] = par2; break;
    	case 103: mTargetSlots[3] = par2; break;
    	case 104: mTargetSlots[4] = par2; break;
    	case 105: mTargetSlots[5] = par2; break;
    	case 106: mTargetSlots[6] = par2; break;
    	case 107: mTargetSlots[7] = par2; break;
    	case 108: mTargetSlots[8] = par2; break;
    	}
    }
    
    public int getSlotCount() {
    	return 9;
    }
    
    public int getShiftClickSlotCount() {
    	return 9;
    }
}
