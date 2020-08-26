package gregtechmod.api.metatileentity;

import cofh.api.energy.IEnergyHandler;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This file contains all the needed 'implements' of the Interfaces for the Michael Jackson Stuff.
 */
public class BaseMetaTileEntityRF extends BaseMetaTileEntity implements IEnergyHandler {

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return hasRFConverterUpgrade() && inputEnergyFrom((byte)from.ordinal());
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		if (inputEnergyFrom((byte)from.ordinal()) && getRFCapacity() - getStoredRF() > 0) {
			int energy = Math.min(maxReceive, getRFCapacity() - getStoredRF());
			return simulate ? energy : (mStoredRF += energy);
		}
		
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return hasRFConverterUpgrade() ? getStoredRF() : 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return hasRFConverterUpgrade() ? getRFCapacity() : 0;
	}
}