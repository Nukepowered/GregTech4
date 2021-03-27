package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_BlastFurnace;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_BlastFurnace extends MultiblockContainerBase {

	public SyncedField<Integer> mHeatCapacity 	= new SyncedField<>("mHeatCapacity", Integer.valueOf(0));
	
	public GT_Container_BlastFurnace(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  34,  16));
        addSlotToContainer(new Slot(mTileEntity, 1,  34,  34));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 2,  86,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 3, 104,  25));
    }
    
    @Override
    public void prepareChanges(JsonObject data, boolean force) {
    	super.prepareChanges(data, force);
    	mHeatCapacity.updateAndWriteChanges(data, force, getMachine().mHeatCapacity);
    }
    
    @Override
    @SideOnly(Side.CLIENT)    
    public void processChanges(JsonObject data) {
    	super.processChanges(data);
    	mHeatCapacity.readChanges(data);
    }
    
    @Override
    public GT_MetaTileEntity_BlastFurnace getMachine() {
    	return (GT_MetaTileEntity_BlastFurnace) this.mTileEntity.getMetaTileEntity();
    }
    
    public int getSlotCount() {
    	return 4;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
