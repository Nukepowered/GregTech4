package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.gui.GT_Slot_Holo;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.tileentities.machines.GT_MetaTileEntity_Teleporter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gson.JsonObject;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DimensionManager;

public class GT_Container_Teleporter extends GT_ContainerMetaTile_Machine {
	
	public final SyncedField<Integer> mTargetX 	= new SyncedField<>("mTargetX", Integer.valueOf(0));
	public final SyncedField<Integer> mTargetY 	= new SyncedField<>("mTargetY", Integer.valueOf(0));
	public final SyncedField<Integer> mTargetZ 	= new SyncedField<>("mTargetZ", Integer.valueOf(0));
	public final SyncedField<Integer> mTargetD 	= new SyncedField<>("mTargetD", Integer.valueOf(0));
	public final SyncedField<Boolean> mEgg		= new SyncedField<>("mEgg", Boolean.FALSE);
	
	public GT_Container_Teleporter(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}
	
	@Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,   8,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,   8, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,   8, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,   8, 59, false, false, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  26,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  26, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  26, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2,  26, 59, false, false, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 152,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 152, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 152, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 152, 59, false, false, 1));
        
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 134,  5, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 134, 23, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 134, 41, false, false, 1));
        addSlotToContainer(new GT_Slot_Holo(mTileEntity, 2, 134, 59, false, false, 1));
    }
	
	@Override
    public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
    	if (aSlotIndex < 0) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
	    if (inventorySlots.get(aSlotIndex) != null) {
	    	switch(aSlotIndex) {
	    	case 0:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX -= (aShifthold==1?512:64);
	        	return null;
	    	case 1:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY -= (aShifthold==1?512:64);
	        	return null;
	    	case 2:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ -= (aShifthold==1?512:64);
	        	return null;
	    	case 3:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD = this.getNextWorldId(aShifthold==1?16:8, true);
	        	return null;
	    	case 4:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX -= (aShifthold==1?16:1);
	        	return null;
	    	case 5:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY -= (aShifthold==1?16:1);
	        	return null;
	    	case 6:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ -= (aShifthold==1?16:1);
	        	return null;
	    	case 7:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD = this.getNextWorldId(aShifthold==1?4:1, true);
	        	return null;
	    	case 8:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX += (aShifthold==1?512:64);
	        	return null;
	    	case 9:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY += (aShifthold==1?512:64);
	        	return null;
	    	case 10:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ += (aShifthold==1?512:64);
	        	return null;
	    	case 11:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD = this.getNextWorldId(aShifthold==1?16:8, false);
	        	return null;
	    	case 12:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetX += (aShifthold==1?16:1);
	        	return null;
	    	case 13:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetY += (aShifthold==1?16:1);
	        	return null;
	    	case 14:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetZ += (aShifthold==1?16:1);
	        	return null;
	    	case 15:
	    		((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD = this.getNextWorldId(aShifthold==1?4:1, false);
	        	return null;
	    	}
    	}
	    return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }
	
	private int getNextWorldId(int intValue, boolean desc) {
		List<Integer> IDs = Arrays.asList(DimensionManager.getIDs());
		IDs.sort(Comparator.naturalOrder());
		
		int idx = IDs.indexOf(((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD);
		int offset = intValue * (desc ? -1 : 1);
		int newIdx = 0;
		
		if (idx < 0 || idx + offset > IDs.size()) {
			return ((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD;
		}
			
		newIdx = idx + offset;
		return newIdx < 0 || newIdx >= IDs.size() ? ((GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity()).mTargetD : IDs.get(newIdx);
	}
    
	@Override
	public void prepareChanges(JsonObject data, boolean force) {
		super.prepareChanges(data, force);
		GT_MetaTileEntity_Teleporter m = (GT_MetaTileEntity_Teleporter)mTileEntity.getMetaTileEntity();
		mTargetX.updateAndWriteChanges(data, force, m.mTargetX);
		mTargetY.updateAndWriteChanges(data, force, m.mTargetY);
		mTargetZ.updateAndWriteChanges(data, force, m.mTargetZ);
		mTargetD.updateAndWriteChanges(data, force, m.mTargetD);
		mEgg.updateAndWriteChanges(data, force, m.hasDimensionalTeleportCapability());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void processChanges(JsonObject data) {
		super.processChanges(data);
		mTargetX.readChanges(data);
		mTargetY.readChanges(data);
		mTargetZ.readChanges(data);
		mTargetD.readChanges(data);
		mEgg.readChanges(data);
	}
}
