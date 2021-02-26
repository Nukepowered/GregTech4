package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_VacuumFreezer;

import java.util.Iterator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_VacuumFreezer extends GT_ContainerMetaTile_Machine {
	
	public GT_Container_VacuumFreezer(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  34,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 1,  86,  25));
    }
    
    public boolean mMachine = true;
    
    @SuppressWarnings("rawtypes")
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	mMachine = ((GT_MetaTileEntity_VacuumFreezer)mTileEntity.getMetaTileEntity()).isStructComplete();
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 103, mMachine?1:0);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	if (par1 == 103) {
    		mMachine = par2 != 0;
    	}
    }
    
    public int getSlotCount() {
    	return 2;
    }

    public int getShiftClickSlotCount() {
    	return 1;
    }
}
