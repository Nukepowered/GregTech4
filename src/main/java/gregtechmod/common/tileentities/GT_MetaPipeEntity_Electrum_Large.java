package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;

public class GT_MetaPipeEntity_Electrum_Large extends GT_MetaPipeEntity_Item {
	
	public GT_MetaPipeEntity_Electrum_Large(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaPipeEntity_Electrum_Large() {
		
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaPipeEntity_Electrum_Large();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
		return aConnected?377:375;
	}
	
	@Override
	public float getThickNess() {
		return 0.75F;
	}
	
	@Override
	public int getPipeCapacity() {
		return 8;
	}
	
	@Override
	public int getStepSize() {
		return 8192;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}
}