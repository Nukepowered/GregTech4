package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.energy.production.GT_MetaTileEntity_MagicEnergyAbsorber;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_MagicEnergyAbsorber extends GT_ContainerMetaTile_Machine {
	
	public final SyncedField<Boolean> isActive1 = new SyncedField<>("isActive1", Boolean.FALSE);
	public final SyncedField<Boolean> isActive2 = new SyncedField<>("isActive2", Boolean.FALSE);
	
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

	@Override
	public void prepareChanges(JsonObject data, boolean force) {
		super.prepareChanges(data, force);
		isActive1.updateAndWriteChanges(data, force, ((GT_MetaTileEntity_MagicEnergyAbsorber)mTileEntity.getMetaTileEntity()).isActive1);
		isActive2.updateAndWriteChanges(data, force, ((GT_MetaTileEntity_MagicEnergyAbsorber)mTileEntity.getMetaTileEntity()).isActive2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void processChanges(JsonObject data) {
		super.processChanges(data);
		isActive1.readChanges(data);
		isActive2.readChanges(data);
	}
    
    public int getSlotCount() {
    	return 2;
    }

    public int getShiftClickSlotCount() {
    	return 1;
    }
}
