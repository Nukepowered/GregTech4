package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class GT_MetaTileEntity_Boiler_Bronze extends GT_MetaTileEntity_BasicTank {
	
	public int mTemperature = 20, mProcessingEnergy = 0, mLossTimer = 0;
	public FluidStack mSteam = null;
	public boolean mHadNoWater = false;
	
	public GT_MetaTileEntity_Boiler_Bronze(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Boiler_Bronze() {
		
	}
	
	@Override public boolean isElectric()							{return false;}
	@Override public boolean isPneumatic()							{return false;}
	@Override public boolean isSteampowered()						{return false;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isValidSlot(int aIndex)				{return true;}
	@Override public int getInvSize()								{return 4;}
	@Override public int getProgresstime()							{return mTemperature;}
	@Override public int maxProgresstime()							{return 500;}
	
	@Override public void onRightclick(EntityPlayer aPlayer) {
		if (aPlayer != null) {
			if (GT_Utility.areStacksEqual(aPlayer.getCurrentEquippedItem(), new ItemStack(Items.bucket, 1))) {
				fill(GT_ModHandler.getWater(1000 * aPlayer.getCurrentEquippedItem().stackSize), true);
				aPlayer.getCurrentEquippedItem().func_150996_a(Items.bucket);
			} else {
				getBaseMetaTileEntity().openGUI(aPlayer, 163);
			}
		}
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Boiler_Bronze();
	}
	
	@Override public boolean doesFillContainers()	{return true;}
	@Override public boolean doesEmptyContainers()	{return true;}
	@Override public boolean canTankBeFilled()		{return true;}
	@Override public boolean canTankBeEmptied()		{return true;}
	@Override public boolean displaysItemStack()	{return false;}
	@Override public boolean displaysStackSize()	{return false;}
	
	@Override public boolean isFluidInputAllowed(FluidStack aFluid) {return GT_ModHandler.isWater(aFluid);}
	
	@Override public FluidStack getDrainableStack() {return mSteam;}
	@Override public FluidStack setDrainableStack(FluidStack aFluid) {mSteam = aFluid; return mSteam;}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return GregTech_API.getCoverBehavior(aCoverID).isSimpleCover();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
    	aNBT.setInteger("mLossTimer", mLossTimer);
    	aNBT.setInteger("mTemperature", mTemperature);
    	aNBT.setInteger("mProcessingEnergy", mProcessingEnergy);
		if (mSteam != null) {
			try {
				aNBT.setTag("mSteam", mSteam.writeToNBT(new NBTTagCompound())); // TODO "mSteam"
			} catch(Throwable e) {}
		}
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mLossTimer = aNBT.getInteger("mLossTimer");
		mTemperature = aNBT.getInteger("mTemperature");
		mProcessingEnergy = aNBT.getInteger("mProcessingEnergy");
		mSteam = FluidStack.loadFluidStackFromNBT(aNBT.getCompoundTag("mSteam"));
	}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide()) {
    		if (mTemperature <= 20) {
    			mTemperature = 20;
    			mLossTimer = 0;
    		}
    		
    		if (++mLossTimer>45) {
    			mTemperature--;
    			mLossTimer = 0;
    		}
    		
	    	for (byte i = 1; mSteam != null && i < 6; i++) if (i != getBaseMetaTileEntity().getFrontFacing()) {
	    		IFluidHandler tTileEntity = getBaseMetaTileEntity().getITankContainerAtSide(i);
	    		if (tTileEntity != null) {
		    		int tFilledAmount = tTileEntity.fill(ForgeDirection.getOrientation(i).getOpposite(), getBaseMetaTileEntity().drain(ForgeDirection.getOrientation(i), 1000, false), false);
		    		if (tFilledAmount > 0) {
		    			tTileEntity.fill(ForgeDirection.getOrientation(i).getOpposite(), getBaseMetaTileEntity().drain(ForgeDirection.getOrientation(i), tFilledAmount, true), true);
		    		}
	    		}
	    	}
    		
    		if (getBaseMetaTileEntity().getTimer()%25==0) {
	    		if (mTemperature > 100) {
	    			if (mFluid == null || !GT_ModHandler.isWater(mFluid) || mFluid.amount <= 0) {
	    				mHadNoWater = true;
	    			} else {
	    				if (mHadNoWater) {
	        				getBaseMetaTileEntity().doExplosion(2048);
	        				return;
	    				}
	    				mFluid.amount--;
		    			if (mSteam == null) {
		    				mSteam = GT_ModHandler.getSteam(150);
		    			} else {
		    				if (GT_ModHandler.isSteam(mSteam)) {
		    					mSteam.amount+=150;
		    				} else {
		    					mSteam = GT_ModHandler.getSteam(150);
		    				}
		    			}
	    			}
	    		} else {
    				mHadNoWater = false;
	    		}
    		}
    		
    		if (mSteam != null) {
	    		if (mSteam.amount > 16000) {
					sendSound((byte)1);
	    			mSteam.amount = 16000;
	    		}
    		}
    		
    		if (mProcessingEnergy <= 0 && getBaseMetaTileEntity().isAllowedToWork()) {
    			if (mInventory[2] != null) {
    				if (mInventory[2].getItem() == Items.coal) {
    					mProcessingEnergy += 160;
    					getBaseMetaTileEntity().decrStackSize(2, 1);
    					if (getBaseMetaTileEntity().getRandomNumber(3) == 0) {
	    					if (mInventory[3] == null) {
	    						mInventory[3] = GT_OreDictUnificator.get("dustDarkAsh", 1);
	    					} else {
	    						if (mInventory[3].stackSize < mInventory[3].getMaxStackSize()) {
		    						if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[3], "dustDarkAsh")) {
		    							mInventory[3].stackSize++;
		    						}
		    					}
	    					}
    					}
    				} else if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[2], "fuelCoke")) {
    					mProcessingEnergy += 640;
    					getBaseMetaTileEntity().decrStackSize(2, 1);
    					if (getBaseMetaTileEntity().getRandomNumber(2) == 0) {
	    					if (mInventory[3] == null) {
	    						mInventory[3] = GT_OreDictUnificator.get("dustAsh", 1);
	    					} else {
	    						if (mInventory[3].stackSize < mInventory[3].getMaxStackSize()) {
		    						if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[3], "dustAsh")) {
		    							mInventory[3].stackSize++;
		    						}
		    					}
	    					}
    					}
    				}
    			}
    		}
    		
    		if (mTemperature < 500 && mProcessingEnergy > 0 && getBaseMetaTileEntity().getTimer()%12==0) {
    			mProcessingEnergy--;
    			mTemperature++;
    		}
    		
    		getBaseMetaTileEntity().setActive(mProcessingEnergy > 0);
		}
    }
    
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex==1 || aIndex==3;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex==2;
	}
	
	@Override
    public void doSound(byte aIndex, double aX, double aY, double aZ) {
		if (aIndex == 1) {
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(4), 2, 1.0F, aX, aY, aZ);
	        for (int l = 0; l < 8; ++l) getBaseMetaTileEntity().getWorld().spawnParticle("largesmoke", aX - 0.5 + Math.random(), aY, aZ - 0.5 + Math.random(), 0, 0, 0);
		}
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 0) return 323;
    	if (aSide == 1) return 322;
    	if (aSide == aFacing) return aActive?325:324;
    	return 321;
	}
	
	@Override
	public String getDescription() {
		return "An early way to get Steam Power";
	}
	
	@Override
	public int getCapacity() {
		return 16000;
	}
	
	@Override
	public int getTankPressure() {
		return +100;
	}
}