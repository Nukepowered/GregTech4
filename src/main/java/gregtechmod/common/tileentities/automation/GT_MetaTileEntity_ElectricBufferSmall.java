package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_ElectricBufferSmall extends MetaTileEntity {

	public boolean bOutput = true, bRedstoneIfFull = false, bInvert = false;
	public int mSuccess = 0, mTargetStackSize = 0;
	
	public GT_MetaTileEntity_ElectricBufferSmall(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_ElectricBufferSmall() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex<getInvSize()-1;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isOutputFacing(byte aSide)				{return getBaseMetaTileEntity().getBackFacing() == aSide;}
	@Override public boolean isTeleporterCompatible()				{return false;}
	@Override public int getMinimumStoredEU()						{return 1000;}
	@Override public int maxEUInput()								{return 32;}
    @Override public int maxEUOutput()								{return bOutput?32:0;}
    @Override public int maxEUStore()								{return 1250;}
    @Override public int maxRFStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public int getInvSize()								{return 2;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 102);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricBufferSmall();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	aNBT.setBoolean("bInvert", bInvert);
    	aNBT.setBoolean("bOutput", bOutput);
    	aNBT.setBoolean("bRedstoneIfFull", bRedstoneIfFull);
    	aNBT.setInteger("mTargetStackSize", mTargetStackSize);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		bInvert = aNBT.getBoolean("bInvert");
		bOutput = aNBT.getBoolean("bOutput");
		bRedstoneIfFull = aNBT.getBoolean("bRedstoneIfFull");
		mTargetStackSize = aNBT.getInteger("mTargetStackSize");
	}
	
	@Override
	public void setItemNBT(NBTTagCompound aNBT) {
		super.setItemNBT(aNBT);
		if (mTargetStackSize > 0) aNBT.setInteger("mTargetStackSize", mTargetStackSize);
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getBackFacing();
	}
	
	@Override
	public void onScrewdriverRightClick(byte aSide, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aSide == getBaseMetaTileEntity().getBackFacing()) {
			mTargetStackSize=(byte)((mTargetStackSize+1)%64);
			if (mTargetStackSize == 0) {
				GT_Utility.sendChatToPlayer(aPlayer, "Do not regulate Item Stack Size");
			} else {
				GT_Utility.sendChatToPlayer(aPlayer, "Regulate Item Stack Size to: " + mTargetStackSize);
			}
		}
	}
	
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getUniversalEnergyStored() >= 500 && (getBaseMetaTileEntity().hasWorkJustBeenEnabled() || getBaseMetaTileEntity().getTimer()%200 == 0 || (getBaseMetaTileEntity().getTimer()%5 == 0 && (mSuccess > 0 || (mInventory[0] != null && getBaseMetaTileEntity().getTimer()%10 == 0 && getInvSize() <= 2))) || mSuccess >= 20 || getBaseMetaTileEntity().hasInventoryBeenModified())) {
			mSuccess--;
			if (getInvSize() > 2 || mInventory[0] != null) {
				int tCost = GT_Utility.moveOneItemStack(getBaseMetaTileEntity(), getBaseMetaTileEntity().getIInventoryAtSide(getBaseMetaTileEntity().getBackFacing()), getBaseMetaTileEntity().getBackFacing(), getBaseMetaTileEntity().getFrontFacing(), null, false, mTargetStackSize!=0?(byte)mTargetStackSize:64, mTargetStackSize!=0?(byte)mTargetStackSize:1, (byte)64, (byte)1)*(getInvSize()>10?2:1);
				if (tCost > 0) {
					mSuccess = 20;
					getBaseMetaTileEntity().decreaseStoredEnergyUnits(tCost, true);
				}
			}
			
			getBaseMetaTileEntity().setGenericRedstoneOutput(bInvert);
			
			if (bRedstoneIfFull) {
				getBaseMetaTileEntity().setGenericRedstoneOutput(!bInvert);
				for (int i = 0; i < mInventory.length; i++) if (isValidSlot(i)) {
					if (mInventory[i] == null) {
						getBaseMetaTileEntity().setGenericRedstoneOutput(bInvert);
						getBaseMetaTileEntity().decreaseStoredEnergyUnits(1, true);
						break;
					}
				}
			}
		}
	}
	
	@Override
	public String getDescription() {
		return "A small directable Hopper with an inbuilt Energy Conduit!"; // TODO locale
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return true;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return true;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return 130+(aRedstone?8:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 113+(aRedstone?8:0);

		int tIndex = 130+(aRedstone?8:0);
		
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
}
