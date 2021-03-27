package gregtechmod.api.gui;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.network.GT_PacketHandler;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.network.packet.MachineUIPacket;

import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * The Container I use for all my MetaTileEntities
 */
public class GT_ContainerMetaTile_Machine extends GT_Container {
    
    public GT_ContainerMetaTile_Machine(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
    	super(aInventoryPlayer, aTileEntity);
    	mTileEntity = aTileEntity;
        
    	if (mTileEntity != null && mTileEntity.getMetaTileEntity() != null) {
            addSlots(aInventoryPlayer);
            if (doesBindPlayerInventory()) bindPlayerInventory(aInventoryPlayer);
//            detectAndSendChanges(); // FIXME check all UIs
    	} else {
    		aInventoryPlayer.player.closeScreen();
    	}
    }
    
    public final SyncedField<Boolean> mActive 			= new SyncedField<>("mActive"			, Boolean.FALSE);
    public final SyncedField<Integer> mMaxProgressTime 	= new SyncedField<>("mMaxProgressTime"	, new Integer(0));
    public final SyncedField<Integer> mProgressTime 	= new SyncedField<>("mProgressTime"		, new Integer(0));
    public final SyncedField<Integer> mEnergy			= new SyncedField<>("mEnergy"			, new Integer(0));
    public final SyncedField<Integer> mStorage 			= new SyncedField<>("mStorage"			, new Integer(0));
    public final SyncedField<Integer> mOutput 			= new SyncedField<>("mOutput"			, new Integer(0));
    public final SyncedField<Integer> mInput 			= new SyncedField<>("mInput"			, new Integer(0));
    public final SyncedField<Integer> mDisplayErrorCode = new SyncedField<>("mDisplayErrorCode"	, new Integer(0));
    
    public int mTimer = 0;
    
    @SuppressWarnings("unchecked")
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
        mStorage.set(mTileEntity.getEUCapacity());
    	mEnergy.set(mTileEntity.getStoredEU());
    	mOutput.set(mTileEntity.getOutputVoltage());
    	mInput.set(mTileEntity.getInputVoltage());
    	mDisplayErrorCode.set(mTileEntity.getErrorDisplayID());
    	mProgressTime.set(mTileEntity.getProgress());
    	mMaxProgressTime.set(mTileEntity.getMaxProgress());
    	mActive.set(mTileEntity.isActive());
    	mTimer++;
    	
    	JsonObject json = new JsonObject();
    	this.prepareChanges(json, mTimer % 500 == 10);
    	if (!json.entrySet().isEmpty()) {
    		MachineUIPacket packet = new MachineUIPacket(windowId, json);
    		for (ICrafting player : (List<ICrafting>)this.crafters) {
    			if (player instanceof EntityPlayerMP) {
        			GT_PacketHandler.sendPacket(packet, (EntityPlayerMP)player);
    			}
    		}
    	}
    }
    
//    @Override
//    public void prepareChanges(JsonObject data, boolean force) {
//    	super.prepareChanges(data, force);
//    	
//    }
//    
//    @Override
//    @SideOnly(Side.CLIENT)    
//    public void processChanges(JsonObject data) {
//    	super.processChanges(data);
//    	
//    }
    
    /**
     * Server side method to write changes to data object
     */
    public void prepareChanges(JsonObject data, boolean force) {
    	mActive.writeChanges(data, force);
    	mMaxProgressTime.writeChanges(data, force);
    	mProgressTime.writeChanges(data, force);
    	mEnergy.writeChanges(data, force);
    	mStorage.writeChanges(data, force);
    	mOutput.writeChanges(data, force);
    	mInput.writeChanges(data, force);
    	mDisplayErrorCode.writeChanges(data, force);
    }
    
    /**
     * Client side method to read changes from data
     */
    @SideOnly(Side.CLIENT)    
    public void processChanges(JsonObject data) {
    	mActive.readChanges(data);
    	mMaxProgressTime.readChanges(data);
    	mProgressTime.readChanges(data);
    	mEnergy.readChanges(data);
    	mStorage.readChanges(data);
    	mOutput.readChanges(data);
    	mInput.readChanges(data);
    	mDisplayErrorCode.readChanges(data);
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return mTileEntity.isUseableByPlayer(player);
    }
}