package gregtechmod.api.metatileentity.implementations;

import java.util.List;
import java.util.Map;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.InfoBuilder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This is the main construct for my Basic Machines such as the Automatic Extractor
 * Extend this class to make a simple Machine
 */
public abstract class GT_MetaTileEntity_BasicMachine extends MetaTileEntity {
	public boolean bAlloyInputFromOutputSide = false, bOutput = false, bOutputBlocked = false, bItemTransfer = false, bSeperatedInputs = false, bHasBeenUpdated = false, bStuttering = false;
	public int mMainFacing = -1, mProgresstime = 0, mMaxProgresstime = 0, mEUt = 0;
	
	public ItemStack mOutputItem1, mOutputItem2;
	
	public GT_MetaTileEntity_BasicMachine(int aID, String aName) {
		super(aID, aName);
	}
	
	public GT_MetaTileEntity_BasicMachine() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isOverclockerUpgradable()				{return getElectricTier()>0;}
	@Override public boolean isTransformerUpgradable()				{return getElectricTier()>0;}
	@Override public boolean isBatteryUpgradable()					{return getElectricTier()>0;}
	@Override public boolean isElectric()							{return getElectricTier()>0;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex > 0;}
	@Override public boolean isFacingValid(byte aFacing)			{return (mMainFacing > 1 || aFacing > 1);}
	@Override public boolean isEnetInput() 							{return getElectricTier()>0;}
	@Override public boolean isEnetOutput() 						{return getElectricTier()>0;}
	@Override public boolean isInputFacing(byte aSide)				{if (aSide==mMainFacing || getElectricTier()<=0) return false; return !isOutputFacing(aSide);}
	@Override public boolean isOutputFacing(byte aSide)				{if (aSide==mMainFacing || getElectricTier()<=0) return false; return bOutput?getBaseMetaTileEntity().getFrontFacing()==aSide:false;}
	@Override public boolean isTeleporterCompatible()				{return false;}
	@Override public int getMinimumStoredEU()						{return 1000;}
	@Override public int maxEUInput()								{return getElectricTier()>0?GregTech_API.VOLTAGES[getElectricTier()]:0;}
    @Override public int maxEUOutput()								{return bOutput&&getElectricTier()>0?GregTech_API.VOLTAGES[getElectricTier()]:0;}
    @Override public int maxEUStore()								{return getElectricTier()*getElectricTier()*2000;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public int getInvSize()								{return 6;}
	@Override public int dechargerSlotStartIndex()					{return 5;}
	@Override public int dechargerSlotCount()						{return getElectricTier()>0?1:0;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public int getProgresstime()							{return mProgresstime;}
	@Override public int maxProgresstime()							{return mMaxProgresstime;}
	@Override public int increaseProgress(int aProgress)			{mProgresstime += aProgress; return mMaxProgresstime-mProgresstime;}
	@Override public boolean isLiquidInput (byte aSide)				{return aSide != mMainFacing;}
	@Override public boolean isLiquidOutput(byte aSide)				{return aSide != mMainFacing;}
    
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setBoolean("bOutput", bOutput);
    	aNBT.setBoolean("bItemTransfer", bItemTransfer);
    	aNBT.setBoolean("bHasBeenUpdated", bHasBeenUpdated);
    	aNBT.setBoolean("bSeperatedInputs", bSeperatedInputs);
		aNBT.setBoolean("bAlloyInputFromOutputSide", bAlloyInputFromOutputSide);
    	aNBT.setInteger("mEUt", mEUt);
    	aNBT.setInteger("mMainFacing", mMainFacing);
    	aNBT.setInteger("mProgresstime", mProgresstime);
    	aNBT.setInteger("mMaxProgresstime", mMaxProgresstime);
        if (mOutputItem1 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem1.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem1", tNBT);
        }
        if (mOutputItem2 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem2.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem2", tNBT);
        }
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		bOutput = aNBT.getBoolean("bOutput");
		bItemTransfer = aNBT.getBoolean("bItemTransfer");
		bHasBeenUpdated = aNBT.getBoolean("bHasBeenUpdated");
		bSeperatedInputs = aNBT.getBoolean("bSeperatedInputs");
		bAlloyInputFromOutputSide = aNBT.getBoolean("bAlloyInputFromOutputSide");
    	mEUt = aNBT.getInteger("mEUt");
		mMainFacing = aNBT.getInteger("mMainFacing");
    	mProgresstime = aNBT.getInteger("mProgresstime");
    	mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
    	NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
    	if (tNBT1 != null) {
    		mOutputItem1 = GT_Utility.loadItem(tNBT1);
    	}
    	NBTTagCompound tNBT2 = (NBTTagCompound)aNBT.getTag("mOutputItem2");
    	if (tNBT2 != null) {
    		mOutputItem2 = GT_Utility.loadItem(tNBT2);
    	}
	}
	
	@Override
	public void onPostTick() {
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	boolean tSucceeded = false;
			if (mMainFacing < 2 && getBaseMetaTileEntity().getFrontFacing() > 1) {
				mMainFacing = getBaseMetaTileEntity().getFrontFacing();
			}
			if (mMainFacing >= 2 && !bHasBeenUpdated) {
				bHasBeenUpdated = true;
				getBaseMetaTileEntity().setFrontFacing(getBaseMetaTileEntity().getBackFacing());
			}
	    	if (mMaxProgresstime > 0) {
	    		getBaseMetaTileEntity().setActive(true);
	    		if (mProgresstime < 0 || getBaseMetaTileEntity().decreaseStoredEnergyUnits(mEUt*(int)Math.pow(4, getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
	    			if ((mProgresstime+=(int)Math.pow(2, getBaseMetaTileEntity().getOverclockerUpgradeCount()))>=mMaxProgresstime) {
	    		    	if (!getBaseMetaTileEntity().addStackToSlot(3, mOutputItem1)) getBaseMetaTileEntity().addStackToSlot(4, mOutputItem1);
	    		    	if (!getBaseMetaTileEntity().addStackToSlot(3, mOutputItem2)) getBaseMetaTileEntity().addStackToSlot(4, mOutputItem2);
	    				mOutputItem1 = null;
	    				mOutputItem2 = null;
	    				mProgresstime = 0;
	    				mMaxProgresstime = 0;
	    				tSucceeded = true;
	    				endProcess();
	    			}
    				bStuttering = false;
	    		} else {
	    			if (!bStuttering) {
	    				stutterProcess();
	    				if (useStandardStutterSound()) sendSound((byte)8);
	    				bStuttering = true;
	    			}
	    		}
	    	} else {
	    		getBaseMetaTileEntity().setActive(false);
	    	}
	    	
			if (bItemTransfer && (mInventory[3] != null || mInventory[4] != null) && getBaseMetaTileEntity().getFrontFacing() != mMainFacing && doesAutoOutput() && (tSucceeded || bOutputBlocked || getBaseMetaTileEntity().hasInventoryBeenModified() || getBaseMetaTileEntity().getTimer()%600 == 0) && getBaseMetaTileEntity().isUniversalEnergyStored(500)) {
				TileEntity tTileEntity2 = getBaseMetaTileEntity().getTileEntityAtSide(getBaseMetaTileEntity().getFrontFacing());
				int tCost = GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), tTileEntity2, getBaseMetaTileEntity().getFrontFacing(), getBaseMetaTileEntity().getBackFacing(), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
				if (tCost > 0) {
					getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
					tCost = GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), tTileEntity2, getBaseMetaTileEntity().getFrontFacing(), getBaseMetaTileEntity().getBackFacing(), null, false, (byte)64, (byte)1, (byte)64, (byte)1);
					if (tCost > 0) {
						getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
					}
				}
			}
			
			if (mInventory[3] == null && mInventory[4] == null) bOutputBlocked = false;
	    	
	    	if (allowToCheckRecipe()) {
	    		if (mMaxProgresstime <= 0 && getBaseMetaTileEntity().isAllowedToWork() && (tSucceeded || getBaseMetaTileEntity().hasInventoryBeenModified() || getBaseMetaTileEntity().getTimer()%600 == 0 || getBaseMetaTileEntity().hasWorkJustBeenEnabled()) && getBaseMetaTileEntity().isUniversalEnergyStored(getMinimumStoredEU()-100)) {
		    		checkRecipe();
		        	if (mInventory[1] != null && mInventory[1].stackSize <= 0) mInventory[1] = null;
		        	if (mInventory[2] != null && mInventory[2].stackSize <= 0) mInventory[2] = null;
		        	if (mMaxProgresstime > 0) {
			        	mOutputItem1 = GT_OreDictUnificator.get(true, mOutputItem1);
			        	mOutputItem2 = GT_OreDictUnificator.get(true, mOutputItem2);
			        	if (GT_Utility.isDebugItem(mInventory[5])) mMaxProgresstime = 1;
		        	} else {
			        	mOutputItem1 = null;
			        	mOutputItem2 = null;
		        	}
		        	if (mOutputItem1 != null && mOutputItem1.stackSize > 64) mOutputItem1.stackSize = 64;
		        	if (mOutputItem2 != null && mOutputItem2.stackSize > 64) mOutputItem2.stackSize = 64;
		        	
		        	if (mMaxProgresstime > 0) {
		        		startProcess();
		        	}
		    	}
	    	} else {
    			if (!bStuttering) {
    				stutterProcess();
    				if (useStandardStutterSound()) sendSound((byte)8);
    				bStuttering = true;
    			}
	    	}
	    }
	}
	
	@Override
	public void onValueUpdate(byte aValue) {
		mMainFacing = aValue;
	}
	
	@Override
	public byte getUpdateData() {
		return (byte)mMainFacing;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return mMainFacing<2?aSide==aFacing?aActive?getFrontFacingActive():getFrontFacingInactive():aSide==0?aActive?getBottomFacingActive():getBottomFacingInactive():aSide==1?aActive?getTopFacingActive():getTopFacingInactive():aActive?getSideFacingActive():getSideFacingInactive():aSide==mMainFacing?aActive?getFrontFacingActive():getFrontFacingInactive():(showPipeFacing()&&aSide==aFacing)?aSide==0?getBottomFacingPipe():aSide==1?getTopFacingPipe():getSideFacingPipe():aSide==0?aActive?getBottomFacingActive():getBottomFacingInactive():aSide==1?aActive?getTopFacingActive():getTopFacingInactive():aActive?getSideFacingActive():getSideFacingInactive();
	}
	
    public boolean spaceForOutput(ItemStack aOutput1, ItemStack aOutput2) {
    	if (mInventory[3] == null || aOutput1 == null || (mInventory[3].stackSize + aOutput1.stackSize <= mInventory[3].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[3], aOutput1)))
    	if (mInventory[4] == null || aOutput2 == null || (mInventory[4].stackSize + aOutput2.stackSize <= mInventory[4].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[4], aOutput2)))
    		return true;
    	bOutputBlocked = true;
    	return false;
    }
    
    public abstract void checkRecipe();
    
    public boolean hasTwoSeperateInputs() {
    	return false;
    }
    
    /** Fallback to the regular Machine Outside Texture */
	public int getSideFacingActive() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return getSideFacingInactive();
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getSideFacingInactive() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return 40;
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getFrontFacingActive() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return getFrontFacingInactive();
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getFrontFacingInactive() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return getSideFacingInactive();
	}

    /** Fallback to the regular Machine Outside Texture */
	public int getTopFacingActive() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return getTopFacingInactive();
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getTopFacingInactive() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return 29;
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getBottomFacingActive() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return getBottomFacingInactive();
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getBottomFacingInactive() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return 32;
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getBottomFacingPipe() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return 38;
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getTopFacingPipe() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return 79;
	}
	
    /** Fallback to the regular Machine Outside Texture */
	public int getSideFacingPipe() {
		// Since this relies on my Texture Indices I would recommend the use of @getTextureIcon in @MetaTileEntity
		return 36;
	}
	
	@Override
    public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 8) GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(210), 100, 1.0F, aX, aY, aZ);
	}
	
	public boolean doesAutoOutput() {
		return true;
	}

	public boolean allowToCheckRecipe() {
		return true;
	}
	
	public boolean showPipeFacing() {
		return true;
	}
	
	/** Called whenever the Machine successfully started a Process, useful for Sound Effects */
	public void startProcess() {
		//
	}
	
	/** Called whenever the Machine successfully finished a Process, useful for Sound Effects */
	public void endProcess() {
		//
	}
	
	/** Called whenever the Machine aborted a Process, useful for Sound Effects */
	public void abortProcess() {
		//
	}
	
	/** Called whenever the Machine aborted a Process but still works on it, useful for Sound Effects */
	public void stutterProcess() {
		//
	}
	
	public boolean useStandardStutterSound() {
		return true;
	}
	
	public int getElectricTier() {
		return 1;
	}
	
	@Override
	public Map<String, List<Object>> getInfoData() {
		return InfoBuilder.create()
				.newKey("sensor.progress.percentage", mProgresstime * 100.0D / mMaxProgresstime)
				.newKey("sensor.progress.secs", mProgresstime / 20)
				.newKey("sensor.progress.secs.1", mMaxProgresstime / 20)
				.build();
	}
	
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	@Override
	public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getFrontFacing()) {
			bAlloyInputFromOutputSide = !bAlloyInputFromOutputSide;
			GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation(bAlloyInputFromOutputSide ? "metatileentity.machines.input_allow" : "metatileentity.machines.input_deny"));
		}
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing() && (aSide != mMainFacing || GregTech_API.getCoverBehavior(aCoverID).isGUIClickable(aSide, aCoverID, 0, getBaseMetaTileEntity()));
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aSide!=mMainFacing?aIndex==3||aIndex==4:false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		if (aSide == mMainFacing || (!bAlloyInputFromOutputSide && aSide == getBaseMetaTileEntity().getFrontFacing())) return false;
		if (hasTwoSeperateInputs()&&GT_Utility.areStacksEqual(GT_OreDictUnificator.get(aStack), mInventory[aIndex==1?2:1])) return false;
		return bSeperatedInputs?aSide<2?aIndex==1:aIndex==2:aIndex==1||aIndex==2;
	}
}