package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Armor;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.energy.storage.GT_MetaTileEntity_IDSU;

import com.google.gson.JsonObject;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
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
	
    public SyncedField<Integer> mPlayerHash = new SyncedField<Integer>("mPlayerHash", new Integer(0));
    
    @Override
    public void prepareChanges(JsonObject data, boolean force) {
    	super.prepareChanges(data, force);
    	if (mPlayerHash != null) {
    		mPlayerHash.updateAndWriteChanges(data, force, ((GT_MetaTileEntity_IDSU)mTileEntity.getMetaTileEntity()).mFrequency);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void processChanges(JsonObject data) {
    	super.processChanges(data);
    	if (mPlayerHash != null) mPlayerHash.readChanges(data);
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