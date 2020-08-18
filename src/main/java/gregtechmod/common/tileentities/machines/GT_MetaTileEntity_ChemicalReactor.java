package gregtechmod.common.tileentities.machines;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ChemicalReactor extends MetaTileEntity {
	
	private int mProgresstime = 0, mMaxProgresstime = 0, mEUt = 0;
	private ItemStack mOutputItem1;
	
	public GT_MetaTileEntity_ChemicalReactor(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_ChemicalReactor() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
    @Override public int maxEUInput()								{return 32;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public int getInvSize()								{return 3;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 124);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public int getProgresstime()							{return mProgresstime;}
	@Override public int maxProgresstime()							{return mMaxProgresstime;}
	@Override public int increaseProgress(int aProgress)			{mProgresstime += aProgress; return mMaxProgresstime-mProgresstime;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ChemicalReactor();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setInteger("mEUt", mEUt);
    	aNBT.setInteger("mProgresstime", mProgresstime);
    	aNBT.setInteger("mMaxProgresstime", mMaxProgresstime);

        if (mOutputItem1 != null) {
            NBTTagCompound tNBT = new NBTTagCompound();
        	mOutputItem1.writeToNBT(tNBT);
            aNBT.setTag("mOutputItem1", tNBT);
        }
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
    	mEUt = aNBT.getInteger("mEUt");
    	mProgresstime = aNBT.getInteger("mProgresstime");
    	mMaxProgresstime = aNBT.getInteger("mMaxProgresstime");
    	
    	NBTTagCompound tNBT1 = (NBTTagCompound)aNBT.getTag("mOutputItem1");
    	if (tNBT1 != null) {
    		mOutputItem1 = GT_Utility.loadItem(tNBT1);
    	}
	}
	
    @Override
    public void onPostTick() {
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	if (mMaxProgresstime > 0) {
	    		getBaseMetaTileEntity().setActive(true);
	    		if (mProgresstime < 0 || getBaseMetaTileEntity().decreaseStoredEnergyUnits(mEUt*(int)Math.pow(4, getBaseMetaTileEntity().getOverclockerUpgradeCount()), false)) {
	    			if ((mProgresstime+=(int)Math.pow(2, getBaseMetaTileEntity().getOverclockerUpgradeCount()))>=mMaxProgresstime) {
	    				addOutputProducts();
	    				mOutputItem1 = null;
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
    }
    
    private boolean spaceForOutput(GT_Recipe aRecipe) {
    	if (mInventory[2] == null || aRecipe.getOutput(0) == null || (mInventory[2].stackSize + aRecipe.getOutput(0).stackSize <= mInventory[2].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[2], aRecipe.getOutput(0))))
    		return true;
    	return false;
    }
    
    private boolean checkRecipe() {
    	GT_Recipe tRecipe = GT_Recipe.findEqualRecipe(false, false, GT_Recipe.sChemicalRecipes, mInventory[0], mInventory[1]);
    	
    	if (tRecipe != null) {
    		if (spaceForOutput(tRecipe) && tRecipe.isRecipeInputEqual(true, true, mInventory[0], mInventory[1])) {
	        	if (mInventory[0] != null) if (mInventory[0].stackSize == 0) mInventory[0] = null;
	        	if (mInventory[1] != null) if (mInventory[1].stackSize == 0) mInventory[1] = null;
	        	
	        	mMaxProgresstime = tRecipe.mDuration;
	        	mEUt = tRecipe.mEUt;
	        	mOutputItem1 = GT_Utility.copy(tRecipe.getOutput(0));
	        	return true;
    		}
    	}
    	return false;
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == 0)
			return 32;
		if (aSide == 1)
			return 29;
		return aActive?67:66;
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
		return "metatileentity.GT_ChemicalReactor.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex<2&&!GT_Utility.areStacksEqual(aStack, mInventory[aIndex==0?1:0]);
	}
}
