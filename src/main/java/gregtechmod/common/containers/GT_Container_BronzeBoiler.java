package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.machines.steam.GT_MetaTileEntity_Boiler_Bronze;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_BronzeBoiler extends GT_ContainerMetaTile_Machine {

	public GT_Container_BronzeBoiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  2, 116, 62));
        addSlotToContainer(new Slot(mTileEntity,  0,  44, 26));
        addSlotToContainer(new Slot(mTileEntity,  1,  44, 62));
        addSlotToContainer(new Slot(mTileEntity,  3, 116, 26));
    }
    
    public int getSlotCount() {
    	return 4;
    }
    
    public int getShiftClickSlotCount() {
    	return 1;
    }

    public SyncedField<Integer> mTemperature 		= new SyncedField<>("mTemperature"		, new Integer(2));
    public SyncedField<Integer> mProcessingEnergy 	= new SyncedField<>("mProcessingEnergy"	, new Integer(0));
    public SyncedField<Integer> mSteamAmount 		= new SyncedField<>("mSteamAmount"		, new Integer(0));
    public SyncedField<Integer> mWaterAmount 		= new SyncedField<>("mWaterAmount"		, new Integer(0));
    
    protected void write(JsonObject data, boolean force) {
    	GT_MetaTileEntity_Boiler_Bronze m = (GT_MetaTileEntity_Boiler_Bronze) mTileEntity.getMetaTileEntity();
    	
    	mTemperature.updateAndWriteChanges(data, force, Math.min(54, Math.max(0, (m.mTemperature * 54) / 490)));
    	mProcessingEnergy.updateAndWriteChanges(data, force, Math.min(14, Math.max(m.mProcessingEnergy>0?1:0, (m.mProcessingEnergy * 14) / 640)));
    	mSteamAmount.updateAndWriteChanges(data, force, Math.min(54, Math.max(0, ((m.mSteam == null ? 0 : m.mSteam.amount) * 54) / 15900)));
    	mWaterAmount.updateAndWriteChanges(data, force, Math.min(54, Math.max(0, ((m.mFluid[0] == null ? 0 : m.mFluid[0].amount) * 54) / 15900)));
    }
    
    @Override
    public void prepareChanges(JsonObject data, boolean force) {
    	super.prepareChanges(data, force);
    	this.write(data, force);
    }
    
    @Override
    @SideOnly(Side.CLIENT)    
    public void processChanges(JsonObject data) {
    	super.processChanges(data);
    	mTemperature.readChanges(data);
    	mProcessingEnergy.readChanges(data);
    	mSteamAmount.readChanges(data);
    	mWaterAmount.readChanges(data);
    }
}
