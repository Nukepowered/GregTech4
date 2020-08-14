package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.energy.production.GT_MetaTileEntity_MagicEnergyAbsorber;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_MagicEnergyAbsorber extends GT_ContainerMetaTile_Machine {

	public GT_Container_MagicEnergyAbsorber(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  80,  17));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 1,  80,  53));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  10,  35, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  10,  18, false, false, 1));
    }

    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 2) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    	Slot tSlot = (Slot)inventorySlots.get(aSlotIndex);
	    if (tSlot != null) {
	    	if (mTileEntity.getMetaTileEntity() == null) return null;
		    if (aSlotIndex == 2) {
		    	((GT_MetaTileEntity_MagicEnergyAbsorber)mTileEntity.getMetaTileEntity()).isActive1 = !((GT_MetaTileEntity_MagicEnergyAbsorber)mTileEntity.getMetaTileEntity()).isActive1;
		    	return null;
		    } else if (aSlotIndex == 3) {
		    	((GT_MetaTileEntity_MagicEnergyAbsorber)mTileEntity.getMetaTileEntity()).isActive2 = !((GT_MetaTileEntity_MagicEnergyAbsorber)mTileEntity.getMetaTileEntity()).isActive2;
		    	return null;
		    }
    	}
    	return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
    
    public boolean isActive1 = false, isActive2 = false;

    @SuppressWarnings("rawtypes")
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	isActive1 = ((GT_MetaTileEntity_MagicEnergyAbsorber)mTileEntity.getMetaTileEntity()).isActive1;
    	isActive2 = ((GT_MetaTileEntity_MagicEnergyAbsorber)mTileEntity.getMetaTileEntity()).isActive2;
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, isActive1?1:0);
            var1.sendProgressBarUpdate(this, 101, isActive2?1:0);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: isActive1 = (par2!=0); break;
    	case 101: isActive2 = (par2!=0); break;
    	}
    }
    
    public int getSlotCount() {
    	return 2;
    }

    public int getShiftClickSlotCount() {
    	return 1;
    }
}
