package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class GT_MetaTileEntity_CropHarvestor extends GT_MetaTileEntity_ElectricBufferSmall {
	
	public GT_MetaTileEntity_CropHarvestor(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_CropHarvestor() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return true;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public int getMinimumStoredEU()						{return 1000+(int)Math.pow(4, getBaseMetaTileEntity().getOverclockerUpgradeCount())*100;}
    @Override public int maxEUStore()								{return 10000;}
    @Override public int maxMJStore()								{return maxEUStore();}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 110);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_CropHarvestor();
	}
	
	@Override
	public void onPostTick() {
		if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().isAllowedToWork() && (getBaseMetaTileEntity().hasWorkJustBeenEnabled() || getBaseMetaTileEntity().getTimer()%20 == 0)) {
			int tOverclockerFactor = (int)Math.pow(4, getBaseMetaTileEntity().getOverclockerUpgradeCount());
			if (getBaseMetaTileEntity().getUniversalEnergyStored() >= 100*tOverclockerFactor) {
				for (int i = 0, j = (int)Math.pow(2, getBaseMetaTileEntity().getOverclockerUpgradeCount()); i < j; i++) {
					TileEntity tTileEntity = getBaseMetaTileEntity().getTileEntityAtSideAndDistance(getBaseMetaTileEntity().getFrontFacing(), i+1);
					try {
						if (tTileEntity != null && tTileEntity instanceof ic2.api.crops.ICropTile) {
							if (!ic2.api.crops.Crops.instance.getCropList()[((ic2.api.crops.ICropTile)tTileEntity).getID()].canGrow((ic2.api.crops.ICropTile)tTileEntity) && ic2.api.crops.Crops.instance.getCropList()[((ic2.api.crops.ICropTile)tTileEntity).getID()].canBeHarvested((ic2.api.crops.ICropTile)tTileEntity)) {
								if (((ic2.api.crops.ICropTile)tTileEntity).harvest(false)) {
									getBaseMetaTileEntity().decreaseStoredEnergyUnits(100*tOverclockerFactor, true);
									break;
								}
							}
						} else {
							break;
						}
					} catch(Throwable e) {}
					if (mInventory[0] == null && getBaseMetaTileEntity().getUniversalEnergyStored() >= 64 * tOverclockerFactor && (mInventory[0] = GT_Utility.suckOneItemStackAt(getBaseMetaTileEntity().getWorld(),
							getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), 1+i),
							getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), 1+i),
							getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), 1+i), 1, 1, 1)) != null) {
						getBaseMetaTileEntity().decreaseStoredEnergyUnits(mInventory[0].stackSize*tOverclockerFactor, true);
						break;
					}
				}
			}
		}
		super.onPostTick();
	}
	
	@Override
	public String getDescription() {
		return "Harvests the Cropsticks in front of it";
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != getBaseMetaTileEntity().getFrontFacing() && aSide != getBaseMetaTileEntity().getBackFacing();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return 118+(aRedstone?8:0);
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
