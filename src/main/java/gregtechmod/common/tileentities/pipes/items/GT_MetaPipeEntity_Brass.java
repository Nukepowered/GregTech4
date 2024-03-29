package gregtechmod.common.tileentities.pipes.items;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;

public class GT_MetaPipeEntity_Brass extends GT_MetaPipeEntity_Item {
	
	public GT_MetaPipeEntity_Brass(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaPipeEntity_Brass() {
		
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaPipeEntity_Brass();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aConnections, boolean aConnected, boolean aRedstone) {
		return aConnected?371:370;
	}
	
	@Override
	public float getThickNess() {
		return 0.5F;
	}
	
	@Override
	public int getPipeCapacity() {
		return 1;
	}
	
	@Override
	public int getStepSize() {
		return 32768;
	}
}
