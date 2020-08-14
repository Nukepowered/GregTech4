package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_Grinder extends GT_MetaTileEntity_BasicTank {

	public int mProgresstime = 0, mMaxProgresstime = 0, mEUt = 0, mUpdate = 5, mFluidAmount = 0;
	private ItemStack mOutputItem1, mOutputItem2, mOutputItem3, mOutputItem4;
	public boolean mMachine = false;
	
	public GT_MetaTileEntity_Grinder(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Grinder() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
	@Override public boolean isValidSlot(int aIndex)				{return true;}
    @Override public int maxEUInput()								{return 128;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxMJStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public int getInvSize()								{return 6;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 112);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public int getProgresstime()							{return mProgresstime;}
	@Override public int maxProgresstime()							{return mMaxProgresstime;}
	@Override public int increaseProgress(int aProgress)			{mProgresstime += aProgress; return mMaxProgresstime-mProgresstime;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Grinder();
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
    	mUpdate = 5;
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
	
    private boolean checkMachine() {
    	int xDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetX*2, yDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetY*2, zDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetZ*2;
    	for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) {
    		if (i!=0||j!=0||k!=0) {
    			if (getBaseMetaTileEntity().getBlockOffset(-xDir+i, -yDir+j, -zDir+k) != GregTech_API.sBlockList[0]) return false;
            	if (getBaseMetaTileEntity().getMetaIDOffset(-xDir+i, -yDir+j, -zDir+k) != (j==0?14:13)) return false;
    		} else {
    			if (getBaseMetaTileEntity().getBlockOffset(-xDir+i, -yDir+j, -zDir+k) != Blocks.water) return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public void onMachineBlockUpdate() {
    	mUpdate = 5;
    }
    
	@Override public boolean doesFillContainers()	{return false;}
	@Override public boolean doesEmptyContainers()	{return false;}
	@Override public boolean canTankBeFilled()		{return true;}
	@Override public boolean canTankBeEmptied()		{return true;}
	@Override public boolean displaysItemStack()	{return false;}
	@Override public boolean displaysStackSize()	{return false;}
	
    @Override
    public void onPostTick() {
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	if (mUpdate--==0) {
	        	mMachine = checkMachine();
	    	}
	    	if (mFluid == null) {
	    		mFluidAmount = 0;
	    	} else {
	    		mFluidAmount = mFluid.amount;
	    	}
	    	
	    	
    		getBaseMetaTileEntity().setActive(mMachine);
	    	if (mMachine && mMaxProgresstime > 0) {
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
    				if (GregTech_API.sConstantEnergy) {
    					mProgresstime = -10;
    					getBaseMetaTileEntity().setErrorDisplayID(1);
    				}
	    		}
	    	} else {
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
    	if (mInventory[2] == null || aRecipe.getOutput(0) == null || (mInventory[2].stackSize + aRecipe.getOutput(0).stackSize <= mInventory[2].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[2], aRecipe.getOutput(0))))
    	if (mInventory[3] == null || aRecipe.getOutput(1) == null || (mInventory[3].stackSize + aRecipe.getOutput(1).stackSize <= mInventory[3].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[3], aRecipe.getOutput(1))))
    	if (mInventory[4] == null || aRecipe.getOutput(2) == null || (mInventory[4].stackSize + aRecipe.getOutput(2).stackSize <= mInventory[4].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[4], aRecipe.getOutput(2))))
    	if (mInventory[5] == null || aRecipe.getOutput(3) == null || (mInventory[5].stackSize + aRecipe.getOutput(3).stackSize <= mInventory[5].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[5], aRecipe.getOutput(3))))
    		return true;
    	return false;
    }
    
    private boolean checkRecipe() {
    	if (!mMachine) return false;
    	GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sGrinderRecipes, mInventory[0], mInventory[1]);
    	if (tRecipe != null) {
    		if (spaceForOutput(tRecipe)) {
    			if (tRecipe.isRecipeInputEqual(true, true, mInventory[0], mInventory[1])) {
		        	if (mInventory[0] != null) if (mInventory[0].stackSize == 0) mInventory[0] = null;
		        	if (mInventory[1] != null) if (mInventory[1].stackSize == 0) mInventory[1] = null;
		        	mMaxProgresstime = tRecipe.mDuration;
		        	mEUt = tRecipe.mEUt;
		        	mOutputItem1 = GT_Utility.copy(tRecipe.getOutput(0));
		        	mOutputItem2 = GT_Utility.copy(tRecipe.getOutput(1));
		        	mOutputItem3 = GT_Utility.copy(tRecipe.getOutput(2));
		        	mOutputItem4 = GT_Utility.copy(tRecipe.getOutput(3));
		        	return true;
    			}
    		}
    	} else {
        	if (mFluid != null) {
        		ItemStack tStack = GT_Utility.fillFluidContainer(mFluid, GT_ModHandler.getEmptyCell(1));
        		FluidStack tFluid = GT_Utility.getFluidForFilledItem(tStack);
        		if (tStack != null && tFluid != null) {
        			tStack.stackSize = mFluid.amount / tFluid.amount;
        			int tAmount = tStack.stackSize;
        			tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sGrinderRecipes, mInventory[0], tStack);
    		    	if (tRecipe != null) {
    		    		if (spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, mInventory[0], tStack)) {
    			        	mFluid.amount -= (tAmount - tStack.stackSize) * tFluid.amount;
    		    			if (mFluid.amount <= 0) mFluid = null;
    		    			if (mInventory[0] != null) if (mInventory[0].stackSize == 0) mInventory[0] = null;
    			        	mMaxProgresstime = tRecipe.mDuration;
    			        	mEUt = tRecipe.mEUt;
    			        	mOutputItem1 = GT_Utility.copy(tRecipe.getOutput(0));
    			        	mOutputItem2 = GT_Utility.copy(tRecipe.getOutput(1));
    			        	mOutputItem3 = GT_Utility.copy(tRecipe.getOutput(2));
    			        	if (!GT_Utility.areStacksEqual(tRecipe.getOutput(3), GT_ModHandler.getEmptyCell(1))) mOutputItem4 = GT_Utility.copy(tRecipe.getOutput(3));
    			        	return true;
    		    		}
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
		return GT_Utility.getFluidForFilledItem(aStack)!=null?aIndex==1:aIndex==0;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return 68+(aActive?1:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 71;
		return 70;
	}
	
	@Override
	public String[] getInfoData() {
		return new String[] { "Progress:", this.mProgresstime / 20 + "secs", this.mMaxProgresstime / 20 + "secs" };
	}
	
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	@Override
	public String getDescription() {
		return "Ultimaceratron 42b";
	}
	
	@Override public int getTankPressure() {return -100;}
	@Override public int getCapacity() {return 10000;}
}
