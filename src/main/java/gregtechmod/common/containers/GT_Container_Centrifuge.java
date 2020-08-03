package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Centrifuge;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_Centrifuge extends GT_ContainerMetaTile_Machine {

	public GT_Container_Centrifuge(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot				(mTileEntity, 0,  80,  35));
        addSlotToContainer(new Slot				(mTileEntity, 1,  50,   5));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 2,  80,   5));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 3, 110,  35));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 4,  80,  65));
        addSlotToContainer(new GT_Slot_Output	(mTileEntity, 5,  50,  35));
        addSlotToContainer(new GT_Slot_Holo		(mTileEntity, 6, 110,  65, false, false, 64));
    }

    public int mProgress, mMaxProgress, mProgressScale;
    
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	mProgress = ((GT_MetaTileEntity_Centrifuge)mTileEntity.getMetaTileEntity()).getProgresstime();
    	mMaxProgress = ((GT_MetaTileEntity_Centrifuge)mTileEntity.getMetaTileEntity()).maxProgresstime();
    	mProgressScale = Math.max(0, Math.min(10, (mProgress>0?1:0) + (mProgress * 10) / (mMaxProgress<1?1:mMaxProgress)));
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, mProgress);
            var1.sendProgressBarUpdate(this, 101, mMaxProgress);
            var1.sendProgressBarUpdate(this, 102, mProgressScale);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mProgress = par2; break;
    	case 101: mMaxProgress = par2; break;
    	case 102: mProgressScale = par2; break;
    	}
    }
    
    public int getSlotCount() {
    	return 6;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
