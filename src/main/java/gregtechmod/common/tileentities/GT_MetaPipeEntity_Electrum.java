package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;

public class GT_MetaPipeEntity_Electrum extends GT_MetaPipeEntity_Item {
	
	public GT_MetaPipeEntity_Electrum(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaPipeEntity_Electrum() {
		
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaPipeEntity_Electrum();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
		return aConnected?376:375;
	}
	
	@Override
	public float getThickNess() {
		return 0.5F;
	}
	
	@Override
	public int getPipeCapacity() {
		return 4;
	}
	
	@Override
	public int getStepSize() {
		return 16384;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}
}