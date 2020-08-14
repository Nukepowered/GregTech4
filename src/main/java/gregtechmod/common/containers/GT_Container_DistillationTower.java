package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_DistillationTower;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_DistillationTower extends GT_ContainerMetaTile_Machine {

	public GT_Container_DistillationTower(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  62,  41));
        addSlotToContainer(new Slot(mTileEntity, 1,  62,  59));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 2,  98,   5));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 3,  98,  23));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 4,  98,  41));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 5,  98,  59));
    }

    public int mProgress, mMaxProgress, mProgressScale;
    public boolean mMachine = true;
    
    @SuppressWarnings("rawtypes")
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	mMachine = ((GT_MetaTileEntity_DistillationTower)mTileEntity.getMetaTileEntity()).mMachine;
    	mProgress = ((GT_MetaTileEntity_DistillationTower)mTileEntity.getMetaTileEntity()).getProgresstime();
    	mMaxProgress = ((GT_MetaTileEntity_DistillationTower)mTileEntity.getMetaTileEntity()).maxProgresstime();
    	mProgressScale = Math.max(0, Math.min(72, (mProgress>0?1:0) + (mProgress * 72) / (mMaxProgress<1?1:mMaxProgress)));
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, mProgress);
            var1.sendProgressBarUpdate(this, 101, mMaxProgress);
            var1.sendProgressBarUpdate(this, 102, mProgressScale);
            var1.sendProgressBarUpdate(this, 103, mMachine?1:0);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mProgress = par2; break;
    	case 101: mMaxProgress = par2; break;
    	case 102: mProgressScale = par2; break;
    	case 103: mMachine = (par2!=0); break;
    	}
    }
    
    public int getSlotCount() {
    	return 6;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
