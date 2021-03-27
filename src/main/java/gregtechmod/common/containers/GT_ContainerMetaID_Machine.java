package gregtechmod.common.containers;

import gregtechmod.api.gui.GT_Container;
import gregtechmod.common.network.GT_PacketHandler;
import gregtechmod.common.network.SyncedField;
import gregtechmod.common.network.packet.MachineUIPacket;
import gregtechmod.common.tileentities.deprecated.GT_TileEntityMetaID_Machine;

import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_ContainerMetaID_Machine extends GT_Container {

	protected GT_TileEntityMetaID_Machine mOldTileEntity;
	
	public int mTimer = 0;
	
    public final SyncedField<Integer> mEnergy 	= new SyncedField<>("mEnergy", new Integer(0));
    public final SyncedField<Integer> mStorage 	= new SyncedField<>("mStorage", new Integer(0));
    public final SyncedField<Integer> mOutput 	= new SyncedField<>("mOutput", new Integer(0));
    public final SyncedField<Integer> mInput 	= new SyncedField<>("mInput", new Integer(0));
    
    public GT_ContainerMetaID_Machine (InventoryPlayer aInventoryPlayer, GT_TileEntityMetaID_Machine aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
        mOldTileEntity = aTileEntity;
        addSlots(aInventoryPlayer);
        if (doesBindPlayerInventory()) bindPlayerInventory(aInventoryPlayer);
//        detectAndSendChanges();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        mTimer++;
    	if (mTileEntity.getWorld().isRemote) return;
    	
    	JsonObject json = new JsonObject();
        mStorage.updateAndWriteChanges(json, mTimer % 500 == 10, mOldTileEntity.maxEUStore());
        mEnergy.updateAndWriteChanges(json, mTimer % 500 == 10, mOldTileEntity.getStored());
        mOutput.updateAndWriteChanges(json, mTimer % 500 == 10, mOldTileEntity.maxEUOutput());
        mInput.updateAndWriteChanges(json, mTimer % 500 == 10, mOldTileEntity.maxEUInput());
        if (!json.entrySet().isEmpty()) {
    		MachineUIPacket packet = new MachineUIPacket(windowId, json);
    		for (ICrafting player : (List<ICrafting>)this.crafters) {
    			if (player instanceof EntityPlayerMP) {
        			GT_PacketHandler.sendPacket(packet, (EntityPlayerMP)player);
    			}
    		}
    	}
    }
    
    /**
     * Client side method to read changes from data
     */
    @SideOnly(Side.CLIENT)    
    public void processChanges(JsonObject data) {
    	mStorage.readChanges(data);
    	mEnergy.readChanges(data);
    	mOutput.readChanges(data);
    	mInput.readChanges(data);
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return mTileEntity.isUseableByPlayer(player);
    }
}