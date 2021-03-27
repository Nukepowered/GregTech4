package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Slot_Output;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.machines.multi.GT_MetaTileEntity_Grinder;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Container_Grinder extends MultiblockContainerBase {

	public final SyncedField<Integer> mWaterAmount = new SyncedField<>("mWaterAmount", new Integer(0));
	
	public GT_Container_Grinder(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  34,  16));
        addSlotToContainer(new Slot(mTileEntity, 1,  34,  34));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 2,  86,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 3, 104,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 4, 122,  25));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 5, 140,  25));
    }
    
	@Override
	public void prepareChanges(JsonObject data, boolean force) {
		super.prepareChanges(data, force);
		mWaterAmount.updateAndWriteChanges(data, force, ((GT_MetaTileEntity_Grinder)mTileEntity.getMetaTileEntity()).getFluidAmount());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void processChanges(JsonObject data) {
		super.processChanges(data);
		mWaterAmount.readChanges(data);
	}
    
    public int getSlotCount() {
    	return 6;
    }

    public int getShiftClickSlotCount() {
    	return 2;
    }
}
