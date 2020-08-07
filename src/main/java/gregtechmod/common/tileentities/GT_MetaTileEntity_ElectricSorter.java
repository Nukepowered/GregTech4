package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricSorter extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public byte mTargetDirection;
	
	public GT_MetaTileEntity_ElectricSorter(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_ElectricSorter() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return true;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxMJStore()								{return maxEUStore()/2;}
	@Override public int getMinimumStoredEU()						{return 2000;}
    @Override public boolean isValidSlot(int aIndex)				{return aIndex<1;}
    @Override public boolean isOutputFacing(byte aSide)				{return mTargetDirection == aSide || getBaseMetaTileEntity().getBackFacing() == aSide;}
	@Override public int getInvSize()								{return 11;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 107);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricSorter();
	}

	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
    	aNBT.setInteger("mTargetDirection", mTargetDirection);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mTargetDirection = (byte)aNBT.getInteger("mTargetDirection");
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != mTargetDirection && aSide != getBaseMetaTileEntity().getBackFacing();
	}
	
	@Override
	public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getBackFacing() || aSide == mTargetDirection) {
			mTargetStackSize=(byte)((mTargetStackSize+1)%64);
			if (mTargetStackSize == 0) {
				GT_Utility.sendChatToPlayer(aPlayer, "Do not regulate Item Stack Size");
			} else {
				GT_Utility.sendChatToPlayer(aPlayer, "Regulate Item Stack Size to: " + mTargetStackSize);
			}
		}
	}
	
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getUniversalEnergyStored() >= 1000 && (getBaseMetaTileEntity().hasWorkJustBeenEnabled() || getBaseMetaTileEntity().getTimer()%20 == 0 || (mSuccess > 0 && getBaseMetaTileEntity().getTimer()%5 == 0))) {
			if (mInventory[0] != null) {
				
				ArrayList<ItemStack> tList = new ArrayList<ItemStack>();
				for (int i = 1; i < 10; i++) tList.add(mInventory[i]);
				
				int tPrice = 0;
				
				getBaseMetaTileEntity().decreaseStoredEnergyUnits(tPrice = GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), getBaseMetaTileEntity().getIInventoryAtSide(mTargetDirection), getBaseMetaTileEntity().getBackFacing(), GT_Utility.getOppositeSide(mTargetDirection), tList, false, mTargetStackSize!=0?(byte)mTargetStackSize:64, mTargetStackSize!=0?(byte)mTargetStackSize:1, (byte)64, (byte)1)*3, true);
				if (tPrice <= 0) {
					getBaseMetaTileEntity().decreaseStoredEnergyUnits(tPrice = GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing()), getBaseMetaTileEntity().getBackFacing(), getBaseMetaTileEntity().getFrontFacing(), null, false, mTargetStackSize!=0?(byte)mTargetStackSize:64, mTargetStackSize!=0?(byte)mTargetStackSize:1, (byte)64, (byte)1)*2, true);
				}
				if (tPrice > 0) {
					mSuccess = 30;
				}
			}
			getBaseMetaTileEntity().setGenericRedstoneOutput(bInvert);
			if (bRedstoneIfFull) {
				getBaseMetaTileEntity().decreaseStoredEnergyUnits(1, true);
				if (mInventory[0] != null) {
					getBaseMetaTileEntity().setGenericRedstoneOutput(!bInvert);
				}
			}
		}
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == mTargetDirection)
			return 116+(aRedstone?8:0);
		if (aSide == aFacing)
			return 134+(aRedstone?8:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 113+(aRedstone?8:0);
		int tIndex = 134+(aRedstone?8:0);
		
		switch (aFacing) {
		case 0:
			return tIndex+64;
		case 1:
			return tIndex+32;
		case 2: switch (aSide) {
			case 0: return tIndex+32;
			case 1: return tIndex+32;
			case 4: return tIndex+16;
			case 5: return tIndex+48;
			}
		case 3: switch (aSide) {
			case 0: return tIndex+64;
			case 1: return tIndex+64;
			case 4: return tIndex+48;
			case 5: return tIndex+16;
			}
		case 4: switch (aSide) {
			case 0: return tIndex+16;
			case 1: return tIndex+16;
			case 2: return tIndex+48;
			case 3: return tIndex+16;
			}
		case 5: switch (aSide) {
			case 0: return tIndex+48;
			case 1: return tIndex+48;
			case 2: return tIndex+16;
			case 3: return tIndex+48;
			}
		}
		return tIndex;
	}
	
	@Override public void onValueUpdate(byte aValue) {
		mTargetDirection = aValue;
	}
	
	@Override public byte getUpdateData() {
		return mTargetDirection;
	}
	
	@Override
	public String getDescription() {
		return "The Sorter will put matching Items into the Blue Side.";
	}
}
