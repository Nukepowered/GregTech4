package gregtechmod.api.metatileentity;

import gregtechmod.api.interfaces.IIC2TileEntity;
import ic2.api.energy.EnergyNet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This file contains all the needed 'implements' of the Interfaces for the Industrial Craft Stuff.
 */
public class BaseMetaTileEntityIC extends BaseMetaTileEntity implements IIC2TileEntity {
	public BaseMetaTileEntityIC() {
		super();
	}
	
	/**
	 * Override of runtime-created default IEnergySink method
	 * This method call original injectEnergy, but in this instance we have real network source tier, so we can calculate current
	 */
	
	public double injectEnergy(ForgeDirection aDirection, double aAmount, double fakeVoltage, int netMaxSouceTier) {
		long realVoltage = (long) EnergyNet.instance.getPowerFromTier(netMaxSouceTier);
		double amps = aAmount / realVoltage;
		return injectEnergyUnits((byte)aDirection.ordinal(), (int)realVoltage, amps) ? 0 : realVoltage * amps;
	}
	
	@Override public double injectEnergy(ForgeDirection aDirection, double aAmount, double aVoltage) { return aAmount; }
	@Override public boolean isTeleporterCompatible(ForgeDirection side) {return hasValidMetaTileEntity() && mMetaTileEntity.isTeleporterCompatible();}
	@Override public boolean acceptsEnergyFrom(TileEntity aEmitter, ForgeDirection aDirection) {return inputEnergyFrom((byte)aDirection.ordinal());}
	@Override public boolean emitsEnergyTo(TileEntity aReceiver, ForgeDirection aDirection) {return outputsEnergyTo((byte)aDirection.ordinal());}
}