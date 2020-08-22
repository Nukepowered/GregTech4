package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_MachineBox extends GT_MetaTileEntity_BasicTank {
	
	public byte mTier = 0;
	
	public GT_MetaTileEntity_MachineBox(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_MachineBox() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isEnetOutput()							{return true;}
	@Override public boolean isEnetInput()							{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return aSide == getBaseMetaTileEntity().getFrontFacing();}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public int getMinimumStoredEU()						{return 1000;}
	@Override public int maxEUInput()								{return 32;}
    @Override public int maxEUOutput()								{return 32;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 1;}
	@Override public int getInvSize()								{return 2;}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_MachineBox();
	}
	
	@Override public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
		aNBT.setByte("mTier", mTier);
	}
	
	@Override public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mTier = aNBT.getByte("mTier");
	}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		return false;
	}
	
	@Override
	public void onPreTick() {
		if (getBaseMetaTileEntity().isServerSide()) {
			mTier = getBaseMetaTileEntity().getTransformerUpgradeCount();
		}
	}
	
	@Override public void onValueUpdate(byte aValue) {
		mTier = aValue;
	}
	
	@Override public byte getUpdateData() {
		return mTier;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==aFacing) {
			if (mTier <= 0) return 282;
			if (mTier == 1) return 310;
			if (mTier == 2) return 17;
			if (mTier == 3) return 18;
			if (mTier >= 4) return 311;
		}
		if (mTier <= 0) {
			if (aSide==0) return 32;
			if (aSide==1) return 29;
	    	return 40;
		}
		if (mTier == 1) {
			return 9;
		}
		return 16;
	}
	
	@Override public boolean doesFillContainers()	{return false;}
	@Override public boolean doesEmptyContainers()	{return false;}
	@Override public boolean canTankBeFilled()		{return true;}
	@Override public boolean canTankBeEmptied()		{return true;}
	@Override public boolean displaysItemStack()	{return false;}
	@Override public boolean displaysStackSize()	{return false;}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_MachineBox.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex == 0;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex == 0;
	}
	
	@Override
	public int getCapacity() {
		return 1000;
	}
	
	@Override
	public int getTankPressure() {
		return 0;
	}
}
