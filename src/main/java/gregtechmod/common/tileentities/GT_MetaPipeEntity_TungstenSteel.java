package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Fluid;

public class GT_MetaPipeEntity_TungstenSteel extends GT_MetaPipeEntity_Fluid {
	
	public GT_MetaPipeEntity_TungstenSteel(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaPipeEntity_TungstenSteel() {
		
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaPipeEntity_TungstenSteel();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
		return aConnected?382:381;
	}
	
	@Override
	public float getThickNess() {
		return 0.5F;
	}
	
	@Override
	public int getFluidCapacityPerTick() {
		return 480;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}
}