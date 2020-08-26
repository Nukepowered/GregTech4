package gregtechmod.api.metatileentity;

import gregtechmod.api.interfaces.IIC2TileEntity;
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
	
	@Override
	public double injectEnergy(ForgeDirection aDirection, double aAmount, double aVoltage) {
		int amps = (int)(aAmount / aVoltage);
		return injectEnergyUnits((byte)aDirection.ordinal(), (int)aVoltage, amps) ? 0 : aVoltage * amps;
	}
	@Override public boolean isTeleporterCompatible(ForgeDirection side) {return hasValidMetaTileEntity() && mMetaTileEntity.isTeleporterCompatible();}
	@Override public boolean acceptsEnergyFrom(TileEntity aEmitter, ForgeDirection aDirection) {return inputEnergyFrom((byte)aDirection.ordinal());}
	@Override public boolean emitsEnergyTo(TileEntity aReceiver, ForgeDirection aDirection) {return outputsEnergyTo((byte)aDirection.ordinal());}
}