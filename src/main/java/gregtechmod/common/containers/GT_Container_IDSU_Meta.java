package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Armor;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.energy.storage.GT_MetaTileEntity_IDSU;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_IDSU_Meta extends GT_ContainerMetaTile_Machine {
	
	public GT_Container_IDSU_Meta(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
	@Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0, 128,  14));
        addSlotToContainer(new Slot(mTileEntity, 1, 128,  50));
        
        addSlotToContainer(new GT_Slot_Armor(aInventoryPlayer, 36, 152, 59, 3, aInventoryPlayer.player));
        addSlotToContainer(new GT_Slot_Armor(aInventoryPlayer, 37, 152, 41, 2, aInventoryPlayer.player));
        addSlotToContainer(new GT_Slot_Armor(aInventoryPlayer, 38, 152, 23, 1, aInventoryPlayer.player));
        addSlotToContainer(new GT_Slot_Armor(aInventoryPlayer, 39, 152,  5, 0, aInventoryPlayer.player));
    }
	
    public int mPlayerHash;
    
    @SuppressWarnings("rawtypes")
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
        
        mPlayerHash = ((GT_MetaTileEntity_IDSU)mTileEntity.getMetaTileEntity()).mFrequency;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, mPlayerHash & 65535);
            var1.sendProgressBarUpdate(this, 101, mPlayerHash >>> 16);
        }
    }
    
    @Override
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mPlayerHash = mPlayerHash & -65536 | par2; break;
    	case 101: mPlayerHash = mPlayerHash &  65535 | par2 << 16; break;
    	}
    }
    
	@Override
    public int getSlotCount() {
    	return 2;
    }
	
	@Override
    public int getShiftClickSlotCount() {
    	return 2;
    }
}