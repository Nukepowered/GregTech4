package gregtechmod.api.gui;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.common.network.SyncedField;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * The Container I use for all my Basic Tanks
 */
public class GT_Container_BasicTank extends GT_ContainerMetaTile_Machine {

	public GT_Container_BasicTank(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
    @Override
	public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity, 0,  80,  17));
        addSlotToContainer(new GT_Slot_Output(mTileEntity, 1,  80,  53));
        addSlotToContainer(new GT_Slot_Render(mTileEntity, 2,  59,  42));
    }
    
    public SyncedField<Integer> mContent = new SyncedField<>("mContent", Integer.valueOf(0));
    
    @Override
    public void prepareChanges(JsonObject data, boolean force) {
    	super.prepareChanges(data, force);
    	if (((GT_MetaTileEntity_BasicTank)mTileEntity.getMetaTileEntity()).mFluid[0] != null)
    		mContent.set(((GT_MetaTileEntity_BasicTank)mTileEntity.getMetaTileEntity()).mFluid[0].amount);
    	else 
    		mContent.set(0);
    	
    	mContent.writeChanges(data, force);
    }
    
    @Override
    @SideOnly(Side.CLIENT)    
    public void processChanges(JsonObject data) {
    	mContent.readChanges(data);
    }
    
    @Override
	public int getSlotCount() {
    	return 2;
    }

    @Override
	public int getShiftClickSlotCount() {
    	return 1;
    }
}
