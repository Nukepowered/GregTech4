package gregtechmod.common.tileentities;

import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_Utility;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class GT_TileEntity_PlayerDetector extends GT_TileEntityMetaID_Machine {

	public byte mMode = 0, oRedstoneStrength = 0, mRedstoneStrength = 0;
	
    public boolean isAccessible(EntityPlayer aPlayer)	{return true;}
    public boolean isEnetInput()       					{return true;}
    public boolean isInputFacing(short aDirection)  	{return true;}
    public int maxEUStore()            					{return 10000;}
    public boolean ownerControl()						{return true;}
    public int maxEUInput()  							{return   32;}
    
	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}
    
    public void storeAdditionalData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mMode", mMode);
    }

    public void getAdditionalData(NBTTagCompound aNBT) {
    	mMode = (byte)aNBT.getInteger("mMode");
    }
    
    public void onPostTickUpdate() {
    	if (!worldObj.isRemote) {
    		if (mTickTimer%20==0 && mOwnerName != null && !mOwnerName.equals("")) {
    			if (oRedstoneStrength != mRedstoneStrength) {
		    		oRedstoneStrength = mRedstoneStrength;
    				mRedstone = !mRedstone;
    			}
				mActive = false;
				mRedstoneStrength = 0;
    			if (decreaseStoredEnergy(50, false)) {
    				Iterator<EntityPlayer> tIterator = worldObj.playerEntities.iterator();
    				while (tIterator.hasNext()) {
    					EntityPlayer tPlayer = tIterator.next();
    					int dist = Math.max(1, (int)tPlayer.getDistance(xCoord+0.5, yCoord+0.5, zCoord+0.5));
    					if (dist < 16) {
	    					if (mMode == 0) {
	    	    				mActive = true;
								mRedstoneStrength = (byte)(16-dist);
								break;
	    					}
	    					if (playerOwnsThis(tPlayer)) {
	    						if (mMode == 1) {
	    		    				mActive = true;
									mRedstoneStrength = (byte)(16-dist);
	    							break;
	    						}
	    					} else {
	    						if (mMode == 2) {
	    		    				mActive = true;
									mRedstoneStrength = (byte)(16-dist);
	    							break;
	    						}
	    					}
    					}
    				}
    			}
    		}
    	}
    }
	
    public void rightClick(EntityPlayer aPlayer) {
    	mMode = (byte)((mMode + 1) % 3);
    	
    	String tMessage = "";
    	
    	switch (mMode) {
    	case  0:
    		tMessage = "Detects all Players";
    		break;
    	case  1:
    		tMessage = "Detects only you";
    		break;
    	case  2:
    		tMessage = "Detects only other Players";
    		break;
    	}
    	
        GT_Utility.sendChatToPlayer(aPlayer, tMessage);
    }
    
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[13];}
    
    @Override
    public int getTexture(int aSide, int aMeta) {
    	return mActive?24:23;
    }
    
	@Override
	public byte getOutputRedstoneSignal(byte aSide) {
		return mRedstoneStrength;
	}
}
