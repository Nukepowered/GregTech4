package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Teleporter;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Teleporter extends GT_ContainerMetaTile_Machine {
	
	public GT_Container_Teleporter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}
	
	@Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,   8,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,   8, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,   8, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,   8, 59, false, false, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  26,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  26, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  26, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  26, 59, false, false, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 152,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 152, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 152, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 152, 59, false, false, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 134,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 134, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 134, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 134, 59, false, false, 1));
    }
	
	@Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	switch(aSlotIndex) {
	    	case 0:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX -= (aShifthold==1?512:64);
	        	return null;
	    	case 1:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY -= (aShifthold==1?512:64);
	        	return null;
	    	case 2:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ -= (aShifthold==1?512:64);
	        	return null;
	    	case 3:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD -= (aShifthold==1?16:8);
	        	return null;
	    	case 4:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX -= (aShifthold==1?16:1);
	        	return null;
	    	case 5:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY -= (aShifthold==1?16:1);
	        	return null;
	    	case 6:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ -= (aShifthold==1?16:1);
	        	return null;
	    	case 7:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD -= (aShifthold==1?4:1);
	        	return null;
	    	case 8:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX += (aShifthold==1?512:64);
	        	return null;
	    	case 9:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY += (aShifthold==1?512:64);
	        	return null;
	    	case 10:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ += (aShifthold==1?512:64);
	        	return null;
	    	case 11:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD += (aShifthold==1?16:8);
	        	return null;
	    	case 12:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX += (aShifthold==1?16:1);
	        	return null;
	    	case 13:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY += (aShifthold==1?16:1);
	        	return null;
	    	case 14:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ += (aShifthold==1?16:1);
	        	return null;
	    	case 15:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD += (aShifthold==1?4:1);
	        	return null;
	    	}
    	}
	    return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
	
	public int mTargetX = 0, mTargetY = 0, mTargetZ = 0, mTargetD = 0, mEgg = 0;
	
    @SuppressWarnings("rawtypes")
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
        
        mTargetX = ((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX;
        mTargetY = ((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY;
        mTargetZ = ((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ;
        mTargetD = ((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD;
        mEgg     = ((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).hasDimensionalTeleportCapability()?1:0;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, mTargetX & 65535);
            var1.sendProgressBarUpdate(this, 101, mTargetX >>> 16);
            var1.sendProgressBarUpdate(this, 102, mTargetY & 65535);
            var1.sendProgressBarUpdate(this, 103, mTargetY >>> 16);
            var1.sendProgressBarUpdate(this, 104, mTargetZ & 65535);
            var1.sendProgressBarUpdate(this, 105, mTargetZ >>> 16);
            var1.sendProgressBarUpdate(this, 106, mTargetD & 65535);
            var1.sendProgressBarUpdate(this, 107, mTargetD >>> 16);
            var1.sendProgressBarUpdate(this, 108, mEgg);
        }
    }
    
    @Override
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mTargetX = mTargetX & -65536 | par2; break;
    	case 101: mTargetX = mTargetX &  65535 | par2 << 16; break;
    	case 102: mTargetY = mTargetY & -65536 | par2; break;
    	case 103: mTargetY = mTargetY &  65535 | par2 << 16; break;
    	case 104: mTargetZ = mTargetZ & -65536 | par2; break;
    	case 105: mTargetZ = mTargetZ &  65535 | par2 << 16; break;
    	case 106: mTargetD = mTargetD & -65536 | par2; break;
    	case 107: mTargetD = mTargetD &  65535 | par2 << 16; break;
    	case 108: mEgg = par2; break;
    	}
    }
}
