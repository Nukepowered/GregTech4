package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;

public class GT_MetaTileEntity_Shelf_FileCabinet extends GT_MetaTileEntity_Shelf {
	public GT_MetaTileEntity_Shelf_FileCabinet(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Shelf_FileCabinet() {
		
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Shelf_FileCabinet();
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) return 223;
		if (aSide == 0) return 32;
		if (aSide == 1) return 29;
    	return 40;
	}
}
