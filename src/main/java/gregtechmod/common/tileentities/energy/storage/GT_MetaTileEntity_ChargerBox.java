package gregtechmod.common.tileentities.energy.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ChargerBox extends MetaTileEntity {
	
	public byte mTier = 0;
	
	public boolean mCharge = false, mDecharge = false;
	
	public GT_MetaTileEntity_ChargerBox(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_ChargerBox() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return false;}
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isEnetOutput()							{return true;}
	@Override public boolean isEnetInput()							{return true;}
    @Override public boolean isTransformingLowEnergy() 				{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return aSide == getBaseMetaTileEntity().getFrontFacing();}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public int maxEUInput()								{return 32;}
    @Override public int maxEUOutput()								{return 32;}
    @Override public int maxEUStore()								{return getBaseMetaTileEntity().getOutputVoltage()*getBaseMetaTileEntity().getOutputAmperage()*16+10000;}
	@Override public boolean isValidSlot(int aIndex)				{return true;}
	@Override public int getInvSize()								{return 1;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer,  96);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ChargerBox();
	}
	
	@Override public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setByte("mTier", mTier);
	}
	
	@Override public void loadNBTData(NBTTagCompound aNBT) {
		mTier = aNBT.getByte("mTier");
	}
	
	@Override
    public int rechargerSlotStartIndex() {
    	return 0;
    }
	
	@Override
    public int rechargerSlotCount() {
    	return mCharge   && getBaseMetaTileEntity().getStoredEU() * 2 > getBaseMetaTileEntity().getEUCapacity() / 3 ? getInvSize() : 0;
    }
	
	@Override
    public int dechargerSlotStartIndex() {
    	return 0;
    }
	
	@Override
    public int dechargerSlotCount() {
    	return mDecharge && getBaseMetaTileEntity().getStoredEU()     < getBaseMetaTileEntity().getEUCapacity() / 3 ? getInvSize() : 0;
    }
	
	@Override
    public int getDechargeCyles() {
    	return 1;
    }
	
	@Override
    public int getChargeCyles() {
    	return 1;
    }
    
	@Override
	public void onPreTick() {
		if (getBaseMetaTileEntity().isServerSide()) {
			mTier     = getBaseMetaTileEntity().getTransformerUpgradeCount();
			mCharge   = getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getStoredEU() * 2 > getBaseMetaTileEntity().getEUCapacity() / 3;
			mDecharge = getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getStoredEU()     < getBaseMetaTileEntity().getEUCapacity() / 3;
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
	
	@Override
	public String getDescription() {
		return "For storing Energy using Batteries and similar";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return !GT_ModHandler.isElectricItem(mInventory[aIndex]) || (getBaseMetaTileEntity().isAllowedToWork() && (getBaseMetaTileEntity().getStoredEU() < getBaseMetaTileEntity().getEUCapacity() / 3 ? !GT_ModHandler.canUseElectricItem(mInventory[aIndex], 1) : GT_ModHandler.canUseElectricItem(mInventory[aIndex], (int)GT_ModHandler.getMaxElectricCharge(mInventory[aIndex]))));
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_ModHandler.isElectricItem(aStack);
	}
}
