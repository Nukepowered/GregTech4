package gregtechmod.api.metatileentity;

import gregtechmod.api.interfaces.IIC2TileEntity;
import ic2.api.Direction;
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
	
	public double injectEnergy(ForgeDirection aDirection, double aAmount, double voltage) {return injectEnergyUnits((byte)aDirection.ordinal(), (int)aAmount, (int)voltage)?0:aAmount;}
	public boolean isTeleporterCompatible(Direction aSide) {return hasValidMetaTileEntity() && mMetaTileEntity.isTeleporterCompatible();}
	public boolean acceptsEnergyFrom(TileEntity aReceiver, Direction aDirection) {return inputEnergyFrom((byte)aDirection.toSideValue());}
    public boolean emitsEnergyTo(TileEntity aReceiver, Direction aDirection) {return outputsEnergyTo((byte)aDirection.toSideValue());}
}