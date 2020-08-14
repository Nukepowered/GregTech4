package gregtechmod.common.tileentities.automation;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_ElectricBufferAdvanced extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public int mTargetSlot = 0;
	
	public GT_MetaTileEntity_ElectricBufferAdvanced(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_ElectricBufferAdvanced() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public int maxEUInput()								{return 128;}
    @Override public int maxEUPulses()								{return 4;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxMJStore()								{return maxEUStore();}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 105);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ElectricBufferAdvanced();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
    	aNBT.setInteger("mTargetSlot", mTargetSlot);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mTargetSlot	= aNBT.getInteger("mTargetSlot");
	}
	
	@Override
	public String getDescription() {
		return "A Buffer, which lets you specify the target Slot!";
	}
	
	public void onPostTick() {
		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getUniversalEnergyStored() >= 500 && (getBaseMetaTileEntity().hasWorkJustBeenEnabled() || getBaseMetaTileEntity().getTimer()%200 == 0 || (mSuccess > 0 && getBaseMetaTileEntity().getTimer()%5 == 0) || mSuccess >= 20 || getBaseMetaTileEntity().hasInventoryBeenModified())) {
			TileEntity tTileEntity2 = getBaseMetaTileEntity().getTileEntityAtSide(getBaseMetaTileEntity().getBackFacing());
			
			if (tTileEntity2 != null) {
				if (tTileEntity2 instanceof IInventory) {
					int tPrice = 0;
					getBaseMetaTileEntity().decreaseStoredEnergyUnits(tPrice = GT_Utility.moveOneItemStackIntoSlot(getBaseMetaTileEntity(), (IInventory)tTileEntity2, getBaseMetaTileEntity().getBackFacing(), mTargetSlot, null, false, (byte)64, (byte)1, (byte)64, (byte)1)*3, true);
					if (tPrice > 0) {
						mSuccess = 30;
					}
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
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return 132+(aRedstone?8:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 113+(aRedstone?8:0);
		int tIndex = 132+(aRedstone?8:0);
		
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
