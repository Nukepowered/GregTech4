package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

public class GT_TileEntity_Sonictron extends GT_TileEntityMetaID_Machine {
	
	public int mCurrentIndex = -1;
	
	public boolean sendClientData = true, sendStacksizeData = false;
	
    public int getInventorySlotCount() 					{return 64;}
    public boolean isValidSlot(int aIndex)				{return false;}
    
	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}
	
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mCurrentIndex", mCurrentIndex);
    }
    
    public void getAdditionalData(NBTTagCompound aNBT) {
    	mCurrentIndex = aNBT.getInteger("mCurrentIndex");
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        super.setInventorySlotContents(slot, stack);
    	sendClientData = true;
    }
    
    public void onPostTickUpdate() {
    	try {
	    	if (isServerSide()) {
	    		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
			    	if (mCurrentIndex<0) {
			    		sendBlockEvent((byte)10, (byte)0);
			    		mCurrentIndex=0;
			    	}
			    	if (sendStacksizeData) {
			        	for (int i = 0; i < getInventorySlotCount(); i++) {
			        		if (mInventory[i] != null) sendBlockEvent((byte)(i-100), (byte)mInventory[i].stackSize);
			        	}
			    		sendStacksizeData = false;
			    	}
			    	if (sendClientData) {
			        	for (int i = 0; i < getInventorySlotCount(); i++) {
			        		sendBlockEvent((byte)(i+20), (byte)getSoundIndex(mInventory[i]));
			        	}
			    		sendClientData = false;
			    		sendStacksizeData = true;
			    	}
	    		}
		    	mRedstone = (mCurrentIndex == 63);
	    	}
	    	
			if (mTickTimer%2==0&&mCurrentIndex>-1) {
				GT_Mod.gregtechproxy.doSonictronSound(mInventory[mCurrentIndex], worldObj, xCoord+0.5, yCoord+0.5, zCoord+0.5);
				if (++mCurrentIndex>63) mCurrentIndex=-1;
			}
		} catch (Throwable e) {
			e.printStackTrace(GT_Log.err);
		}
    }
    
    public short getSoundIndex(ItemStack aStack) {
    	for (short i = 0; i < GT_Mod.mSoundItems.size(); i++) if (GT_Utility.areStacksEqual(GT_Mod.mSoundItems.get(i), aStack)) return i;
    	return (short)GT_Mod.mSoundItems.size();
    }
    
    public Packet getDescriptionPacket() {
    	sendClientData = true;
        return super.getDescriptionPacket();
    }
    
    public boolean isAccessible(EntityPlayer aPlayer) {
    	ItemStack tStack = aPlayer.getCurrentEquippedItem();
    	if (tStack == null) return true;
    	if (tStack.isItemEqual(GregTech_API.getGregTechItem(32, 1, 0))) return false;
    	if (tStack.isItemEqual(GregTech_API.getGregTechItem(43, 1, 0))) return false;
    	return true;
    }
    
    public boolean receiveClientEvent(int aEventID, int aValue) {
    	super.receiveClientEvent(aEventID, aValue);
    	try {
	    	if (worldObj.isRemote) {
		    	switch(aEventID) {
		    	case 10:
		    		mCurrentIndex = aValue;
		    		break;
		    	}
		    	if (aEventID >= 20 && aEventID - 20 < getInventorySlotCount() && aValue >= 0) {
		    		mInventory[aEventID-20] = (aValue < GT_Mod.mSoundItems.size() ? GT_Utility.copy(GT_Mod.mSoundItems.get(aValue)) : null);
		    	}
		    	if (aEventID >= -100 && aEventID + 100 < getInventorySlotCount() && aValue >= 0) {
		    		if (mInventory[aEventID+100]!=null) mInventory[aEventID+100].stackSize = aValue;
		    	}
	    	}
    	} catch (Throwable e) {
    		e.printStackTrace(GT_Log.err);
    	}
    	return true;
    }
    
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[6];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	return mCurrentIndex>-1?35:39;
    }
	@Override
	public void doEnergyExplosion() {}
}
