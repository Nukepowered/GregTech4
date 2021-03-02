package gregtechmod.common.tileentities.machines.multi;

import java.lang.ref.WeakReference;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author TheDarkDnKTv
 *
 */
public abstract class MTEFusionBus extends MetaTileEntity {
	
	private WeakReference<IGregTechTileEntity> mFusionComputer;
	
	public MTEFusionBus(int aID, String aBasicName) {
		super(aID, aBasicName);
	}

	public MTEFusionBus() {}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public void saveNBTData(NBTTagCompound aNBT) 			{}
	@Override public void loadNBTData(NBTTagCompound aNBT) 			{}
	@Override public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) 	{ return false; }
	@Override public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) 	{ return false; }
    
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide()) {
    		if (mFusionComputer != null && mFusionComputer.get() != null) {
    			if (mFusionComputer.get().isInvalidTileEntity()) {
    				mFusionComputer.clear();
    				getBaseMetaTileEntity().setActive(false);
    			} else if (getBaseMetaTileEntity().isActive() != mFusionComputer.get().isActive()) {
    				getBaseMetaTileEntity().setActive(!getBaseMetaTileEntity().isActive());
    			}
    		}
    	}
    }
	
    public void setComputer(IGregTechTileEntity computer) {
    	mFusionComputer = new WeakReference<>(computer);
    }
}
