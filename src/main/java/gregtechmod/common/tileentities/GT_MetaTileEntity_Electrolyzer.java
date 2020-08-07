package gregtechmod.common.tileentities;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_Electrolyzer extends GT_MetaTileEntity_BasicTank {

	private int mProgresstime = 0, mMaxProgresstime = 0, mEUt = 0;
	private ItemStack mOutputItem1, mOutputItem2, mOutputItem3, mOutputItem4;
	
	public GT_MetaTileEntity_Electrolyzer(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Electrolyzer() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
    @Override public boolean isValidSlot(int aIndex) 				{return aIndex < 6;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
    @Override public int maxEUInput()								{return 128;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxMJStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public int getInvSize()								{return 7;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 109);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public int getProgresstime()							{return mProgresstime;}
	@Override public int maxProgresstime()							{return mMaxProgresstime;}
	@Override public int increaseProgress(int aProgress)			{mProgresstime += aProgress; return mMaxProgresstime-mProgresstime;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Electrolyzer();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
    	aNBT.setInteger("mEUt", mEUt);
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
        if (mOutputItem3 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem3.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem3", tNBT);
        }
        if (mOutputItem4 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem4.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem4", tNBT);
        }
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
    	mEUt = aNBT.getInteger("mEUt");
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
    	NBTTagCompound tNBT3 = (NBTTagCompound)aNBT.getTag("mOutputItem3");
    	if (tNBT3 != null) {
    		mOutputItem3 = GT_Utility.loadItem(tNBT3);
    	}
    	NBTTagCompound tNBT4 = (NBTTagCompound)aNBT.getTag("mOutputItem4");
    	if (tNBT4 != null) {
    		mOutputItem4 = GT_Utility.loadItem(tNBT4);
    	}
	}
	
	@Override public boolean doesFillContainers()	{return false;}
	@Override public boolean doesEmptyContainers()	{return false;}
	@Override public boolean canTankBeFilled()		{return true;}
	@Override public boolean canTankBeEmptied()		{return true;}
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return true;}
	
	@Override public int getInputSlot() {return 1;}
	@Override public int getOutputSlot() {return 2;}
	@Override public int getStackDisplaySlot() {return 6;}
	
    @Override
    public void onPostTick() {
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	if (mMaxProgresstime > 0) {
	    		getBaseMetaTileEntity().setActive(true);
	    		if (mProgresstime < 0 || getBaseMetaTileEntity().decreaseStoredEnergyUnits(mEUt*(int)Math.pow(4, getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
	    			if ((mProgresstime+=(int)Math.pow(2, getBaseMetaTileEntity().getOverclockerUpgradeCount()))>=mMaxProgresstime) {
	    				addOutputProducts();
	    				mOutputItem1 = null;
	    				mOutputItem2 = null;
	    				mOutputItem3 = null;
	    				mOutputItem4 = null;
	    				mProgresstime = 0;
	    				mMaxProgresstime = 0;
	    				getBaseMetaTileEntity().setErrorDisplayID(0);
	    			}
	    		} else {
		    		getBaseMetaTileEntity().setActive(false);
    				if (GregTech_API.sConstantEnergy) {
    					mProgresstime = -10;
	    				getBaseMetaTileEntity().setErrorDisplayID(1);
    				}
	    		}
	    	} else {
	    		getBaseMetaTileEntity().setActive(false);
	    		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getUniversalEnergyStored() > 100) checkRecipe();
	    	}
		}
    }
    
    private void addOutputProducts() {
    	if (mOutputItem1 != null)
	    	if (mInventory[2] == null)
	    		mInventory[2] = GT_Utility.copy(mOutputItem1);
	    	else if (GT_Utility.areStacksEqual(mInventory[2], mOutputItem1))
	    		mInventory[2].stackSize = Math.min(mOutputItem1.getMaxStackSize(), mOutputItem1.stackSize + mInventory[2].stackSize);
    	
    	if (mOutputItem2 != null)
	    	if (mInventory[3] == null)
	    		mInventory[3] = GT_Utility.copy(mOutputItem2);
	    	else if (GT_Utility.areStacksEqual(mInventory[3], mOutputItem2))
	    		mInventory[3].stackSize = Math.min(mOutputItem2.getMaxStackSize(), mOutputItem2.stackSize + mInventory[3].stackSize);
    	
    	if (mOutputItem3 != null)
	    	if (mInventory[4] == null)
	    		mInventory[4] = GT_Utility.copy(mOutputItem3);
	    	else if (GT_Utility.areStacksEqual(mInventory[4], mOutputItem3))
	    		mInventory[4].stackSize = Math.min(mOutputItem3.getMaxStackSize(), mOutputItem3.stackSize + mInventory[4].stackSize);
    	
    	if (mOutputItem4 != null)
	    	if (mInventory[5] == null)
	    		mInventory[5] = GT_Utility.copy(mOutputItem4);
	    	else if (GT_Utility.areStacksEqual(mInventory[5], mOutputItem4))
	    		mInventory[5].stackSize = Math.min(mOutputItem4.getMaxStackSize(), mOutputItem4.stackSize + mInventory[5].stackSize);
    }
    
    private boolean spaceForOutput(GT_Recipe aRecipe) {
    	if (mInventory[2] == null || aRecipe.mOutput1 == null || (mInventory[2].stackSize + aRecipe.mOutput1.stackSize <= mInventory[2].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[2], aRecipe.mOutput1)))
    	if (mInventory[3] == null || aRecipe.mOutput2 == null || (mInventory[3].stackSize + aRecipe.mOutput2.stackSize <= mInventory[3].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[3], aRecipe.mOutput2)))
    	if (mInventory[4] == null || aRecipe.mOutput3 == null || (mInventory[4].stackSize + aRecipe.mOutput3.stackSize <= mInventory[4].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[4], aRecipe.mOutput3)))
    	if (mInventory[5] == null || aRecipe.mOutput4 == null || (mInventory[5].stackSize + aRecipe.mOutput4.stackSize <= mInventory[5].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[5], aRecipe.mOutput4)))
    		return true;
    	return false;
    }
    
    private boolean checkRecipe() {
    	GT_Recipe tRecipe = GT_Recipe.findEqualElectrolyzerRecipe(mInventory[0], mInventory[1]);
    	if (tRecipe != null) {
    		if (spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, mInventory[0], mInventory[1])) {
	        	if (mInventory[0] != null) if (mInventory[0].stackSize == 0) mInventory[0] = null;
	        	if (mInventory[1] != null) if (mInventory[1].stackSize == 0) mInventory[1] = null;
	        	mMaxProgresstime = tRecipe.mDuration;
	        	mEUt = tRecipe.mEUt;
	        	mOutputItem1 = GT_Utility.copy(tRecipe.mOutput1);
	        	mOutputItem2 = GT_Utility.copy(tRecipe.mOutput2);
	        	mOutputItem3 = GT_Utility.copy(tRecipe.mOutput3);
	        	mOutputItem4 = GT_Utility.copy(tRecipe.mOutput4);
	        	return true;
    		}
    	}
    	if (mFluid != null) {
    		ItemStack tStack = GT_Utility.fillFluidContainer(mFluid, new ItemStack(Items.bucket, 1));
    		FluidStack tFluid = GT_Utility.getFluidForFilledItem(tStack);
    		if (tStack != null && tFluid != null) {
    			tStack.stackSize = mFluid.amount / tFluid.amount;
    			int tAmount = tStack.stackSize;
    			tRecipe = GT_Recipe.findEqualElectrolyzerRecipe(tStack, mInventory[1]);
		    	if (tRecipe != null) {
		    		if (spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, tStack, mInventory[1])) {
			        	mFluid.amount -= (tAmount - tStack.stackSize) * tFluid.amount;
		    			if (mFluid.amount <= 0) mFluid = null;
		    			if (mInventory[1] != null) if (mInventory[1].stackSize == 0) mInventory[1] = null;
			        	mMaxProgresstime = tRecipe.mDuration;
			        	mEUt = tRecipe.mEUt;
			        	mOutputItem1 = GT_Utility.copy(tRecipe.mOutput1);
			        	mOutputItem2 = GT_Utility.copy(tRecipe.mOutput2);
			        	mOutputItem3 = GT_Utility.copy(tRecipe.mOutput3);
			        	if (!GT_Utility.areStacksEqual(tRecipe.mOutput4, new ItemStack(Items.bucket, 1))) mOutputItem4 = GT_Utility.copy(tRecipe.mOutput4);
			        	return true;
		    		}
		    	}
    		}
    	}
    	return false;
    }

	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_Utility.areStacksEqual(aStack, GT_ModHandler.getEmptyCell(1))?aIndex==1:aIndex==0;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == 0)
			return 32;
		if (aSide == 1)
			return 29;
		return aActive?65:64;
	}
	
	@Override
	public String getMainInfo() {
		return "Progress:";
	}
	@Override
	public String getSecondaryInfo() {
		return (mProgresstime/20)+"secs";
	}
	@Override
	public String getTertiaryInfo() {
		return "/"+(mMaxProgresstime/20)+"secs";
	}
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	@Override
	public String getDescription() {
		return "Give your Water a shocking Experience with this Device";
	}
	
	@Override public int getTankPressure() {return -100;}
	@Override public int getCapacity() {return 64000;}
}
