package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_ElectricItemClearer extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public GT_MetaTileEntity_ElectricItemClearer(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_ElectricItemClearer() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxRFStore()								{return maxEUStore();}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 108);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricItemClearer();
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing() && aSide != getBaseMetaTileEntity().getBackFacing();
	}
	
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide() && mInventory[0] == null && getBaseMetaTileEntity().getUniversalEnergyStored() >= 64*(int)Math.pow(2, getBaseMetaTileEntity().getOverclockerUpgradeCount()) && (getBaseMetaTileEntity().hasWorkJustBeenEnabled() || getBaseMetaTileEntity().getTimer()%20 == 0 || mSuccess == 20)) {
			if (null != (mInventory[0] = GT_Utility.suckOneItemStackAt(getBaseMetaTileEntity().getWorld()
					, getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), (2+getBaseMetaTileEntity().getOverclockerUpgradeCount()))-(1+getBaseMetaTileEntity().getOverclockerUpgradeCount())
					, getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), (2+getBaseMetaTileEntity().getOverclockerUpgradeCount()))-(1+getBaseMetaTileEntity().getOverclockerUpgradeCount())
					, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), (2+getBaseMetaTileEntity().getOverclockerUpgradeCount()))-(1+getBaseMetaTileEntity().getOverclockerUpgradeCount())
					, 3+getBaseMetaTileEntity().getOverclockerUpgradeCount()*2, 3+getBaseMetaTileEntity().getOverclockerUpgradeCount()*2, 3+getBaseMetaTileEntity().getOverclockerUpgradeCount()*2))) {
				getBaseMetaTileEntity().decreaseStoredEnergyUnits(mInventory[0].stackSize*(int)Math.pow(2, getBaseMetaTileEntity().getOverclockerUpgradeCount()), true);
				mSuccess = 20;
			}
		}
		super.onPostTick();
	}
	
	@Override
	public String getDescription() {
		return "Clears out a 3x3x3 in front of it"; // TODO locale
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return 117+(aRedstone?8:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 113+(aRedstone?8:0);
		
		int tIndex = 128+(aRedstone?8:0);
		
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
