package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_MetaTileEntity_BronzeBlastFurnace extends MetaTileEntity {

	public int mProgresstime = 0, mMaxProgresstime = 0, mUpdate = 5;
	public ItemStack mOutputItem1, mOutputItem2;
	public boolean mMachine = false;
	
	public GT_MetaTileEntity_BronzeBlastFurnace(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_BronzeBlastFurnace() {
		
	}
	
	@Override public boolean isSteampowered()						{return false;}
	@Override public boolean isElectric()							{return false;}
	@Override public boolean isPneumatic()							{return false;}
	@Override public boolean isTransformerUpgradable()				{return false;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return false;}
	@Override public boolean isEnetInput() 							{return false;}
	@Override public boolean isEnetOutput() 						{return false;}
	@Override public boolean isInputFacing(byte aSide)				{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return false;}
	@Override public boolean isTeleporterCompatible()				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public int getMinimumStoredEU()						{return 1000;}
	@Override public int maxEUInput()								{return 0;}
    @Override public int maxEUOutput()								{return 0;}
    @Override public int maxEUStore()								{return 0;}
    @Override public int maxRFStore()								{return 0;}
    @Override public int maxSteamStore()							{return 0;}
	@Override public int getInvSize()								{return 4;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public int getProgresstime()							{return mProgresstime;}
	@Override public int maxProgresstime()							{return mMaxProgresstime;}
	@Override public int increaseProgress(int aProgress)			{mProgresstime += aProgress; return mMaxProgresstime-mProgresstime;}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return GregTech_API.getCoverBehavior(aCoverID).isSimpleCover() && super.allowCoverOnSide(aSide, aCoverID);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeBlastFurnace();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
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
    	mUpdate = 5;
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
	public void onRightclick(EntityPlayer aPlayer) {
	    getBaseMetaTileEntity().openGUI(aPlayer, 170);
	}
	
    private boolean checkMachine() {
    	int xDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getBackFacing()).offsetX, zDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getBackFacing()).offsetZ;
    	for (int i = -1; i < 2; i++) for (int j = -1; j < 3; j++) for (int k = -1; k < 2; k++) if (xDir+i != 0 || j != 0 || zDir+k != 0) {
    		if (i!=0||j==-1||k!=0) {
    			if (getBaseMetaTileEntity().getBlockOffset(xDir+i, j, zDir+k) != GregTech_API.sBlockList[4] || getBaseMetaTileEntity().getMetaIDOffset(xDir+i, j, zDir+k) != 13) return false;
    		} else {
    			if (getBaseMetaTileEntity().getBlockOffset(xDir+i, j, zDir+k) != Blocks.lava && !getBaseMetaTileEntity().getAirOffset(xDir+i, j, zDir+k)) return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public void onMachineBlockUpdate() {
    	mUpdate = 5;
    }
    
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isClientSide()) {
    		if (getBaseMetaTileEntity().isActive()) {
    			getBaseMetaTileEntity().getWorld().spawnParticle("largesmoke", getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1) + Math.random(), getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1) + Math.random(), 0, 0.3, 0);
    		}
    	}
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	if (mUpdate--==0) {
	        	mMachine = checkMachine();
	    	}
	    	
	    	if (mMachine) {
		    	if (mMaxProgresstime > 0) {
		    		if (++mProgresstime>=mMaxProgresstime) {
		    			addOutputProducts();
		    			mOutputItem1 = null;
		    			mOutputItem2 = null;
		    			mProgresstime = 0;
		    			mMaxProgresstime = 0;
		    		}
		    	} else {
		    		if (getBaseMetaTileEntity().isAllowedToWork()) checkRecipe();
		    	}
	    	}
	    	
    		getBaseMetaTileEntity().setActive(mMaxProgresstime > 0 && mMachine);
    		
    		if (getBaseMetaTileEntity().isActive()) {
    			if (getBaseMetaTileEntity().getAir(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()  , getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1))) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()  , getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1), Blocks.lava, 1, 2);
    			if (getBaseMetaTileEntity().getAir(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1))) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1), Blocks.lava, 1, 2);
    		} else {
    			if (getBaseMetaTileEntity().getBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()  , getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1)) == Blocks.lava) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()  , getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1), Blocks.air, 0, 2);
    			if (getBaseMetaTileEntity().getBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1)) == Blocks.lava) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1), Blocks.air, 0, 2);
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
    }
    
    private boolean spaceForOutput(ItemStack aStack1, ItemStack aStack2) {
    	if (mInventory[2] == null || aStack1 == null || (mInventory[2].stackSize + aStack1.stackSize <= mInventory[2].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[2], aStack1)))
    	if (mInventory[3] == null || aStack2 == null || (mInventory[3].stackSize + aStack2.stackSize <= mInventory[3].getMaxStackSize() && GT_Utility.areStacksEqual(mInventory[3], aStack2)))
    		return true;
    	return false;
    }
    
    private boolean checkRecipe() {
    	if (!mMachine) return false;
    	
		if (mInventory[0] != null && mInventory[1] != null && mInventory[0].stackSize >= 1) {
			if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[0], "dustIron") || GT_OreDictUnificator.isItemStackInstanceOf(mInventory[0], "ingotIron")) {
				if (mInventory[1].getItem() == Items.coal && mInventory[1].stackSize >= 4 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 1), mOutputItem2 = GT_OreDictUnificator.get("dustDarkAsh", 4))) {
					getBaseMetaTileEntity().decrStackSize(0, 1);
					getBaseMetaTileEntity().decrStackSize(1, 4);
					mMaxProgresstime = 7200;
					return true;
				}
				if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "fuelCoke") && mInventory[1].stackSize >= 2 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 1), mOutputItem2 = GT_OreDictUnificator.get("dustAsh", 4))) {
					getBaseMetaTileEntity().decrStackSize(0, 1);
					getBaseMetaTileEntity().decrStackSize(1, 2);
					mMaxProgresstime = 4800;
					return true;
				}
				if (mInventory[0].stackSize >= 9 && (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "blockCoal") || GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "blockCharcoal")) && mInventory[1].stackSize >= 4 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 9), mOutputItem2 = GT_OreDictUnificator.get("dustDarkAsh", 36))) {
					getBaseMetaTileEntity().decrStackSize(0, 9);
					getBaseMetaTileEntity().decrStackSize(1, 4);
					mMaxProgresstime = 64800;
					return true;
				}
			} else if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[0], "dustSteel")) {
				if (mInventory[1].getItem() == Items.coal && mInventory[1].stackSize >= 2 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 1), mOutputItem2 = GT_OreDictUnificator.get("dustDarkAsh", 2))) {
					getBaseMetaTileEntity().decrStackSize(0, 1);
					getBaseMetaTileEntity().decrStackSize(1, 2);
					mMaxProgresstime = 3600;
					return true;
				}
				if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "fuelCoke") && mInventory[1].stackSize >= 1 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 1), mOutputItem2 = GT_OreDictUnificator.get("dustAsh", 2))) {
					getBaseMetaTileEntity().decrStackSize(0, 1);
					getBaseMetaTileEntity().decrStackSize(1, 1);
					mMaxProgresstime = 2400;
					return true;
				}
				if (mInventory[0].stackSize >= 9 && (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "blockCoal") || GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "blockCharcoal")) && mInventory[1].stackSize >= 2 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 9), mOutputItem2 = GT_OreDictUnificator.get("dustDarkAsh", 18))) {
					getBaseMetaTileEntity().decrStackSize(0, 9);
					getBaseMetaTileEntity().decrStackSize(1, 2);
					mMaxProgresstime = 32400;
					return true;
				}
			} else if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[0], "blockIron")) {
				if (mInventory[1].getItem() == Items.coal && mInventory[1].stackSize >= 36 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 9), mOutputItem2 = GT_OreDictUnificator.get("dustDarkAsh", 36))) {
					getBaseMetaTileEntity().decrStackSize(0, 1);
					getBaseMetaTileEntity().decrStackSize(1, 36);
					mMaxProgresstime = 64800;
					return true;
				}
				if (GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "fuelCoke") && mInventory[1].stackSize >= 18 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 9), mOutputItem2 = GT_OreDictUnificator.get("dustAsh", 36))) {
					getBaseMetaTileEntity().decrStackSize(0, 1);
					getBaseMetaTileEntity().decrStackSize(1, 18);
					mMaxProgresstime = 43200;
					return true;
				}
				if ((GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "blockCoal") || GT_OreDictUnificator.isItemStackInstanceOf(mInventory[1], "blockCharcoal")) && mInventory[1].stackSize >= 4 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.get("ingotSteel", 9), mOutputItem2 = GT_OreDictUnificator.get("dustDarkAsh", 36))) {
					getBaseMetaTileEntity().decrStackSize(0, 1);
					getBaseMetaTileEntity().decrStackSize(1, 4);
					mMaxProgresstime = 64800;
					return true;
				}
			}
		}
		
		mOutputItem1 = null;
		mOutputItem2 = null;
    	return false;
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) return aActive?348:347;
		if (aSide == 0) return 315;
		if (aSide == 1) return 314;
		return 316;
	}
	@Override
	public boolean isGivingInformation() {
		return false;
	}
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeBlastFurnace.tooltip";
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