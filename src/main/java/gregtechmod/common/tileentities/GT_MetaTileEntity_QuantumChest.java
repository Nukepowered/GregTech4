package gregtechmod.common.tileentities;

import gregtechmod.GT_Mod;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;

public class GT_MetaTileEntity_QuantumChest extends GT_MetaTileEntity_Barrel {
	
	public GT_MetaTileEntity_QuantumChest(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
		isDigitalChest = false;
	}

	public GT_MetaTileEntity_QuantumChest() {
		isDigitalChest = false;
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_QuantumChest();
	}
	
	@Override
	public int getMaxItemCount() {
		return GT_Mod.sQuantumItemCount - 192;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) return 55;
    	if (aSide == 0) return 3;
    	return 0;
	}
	
	@Override
	public String getDescription() {
		return "2 Milliards, uhhm I mean Billions, Items to store here!";
	}
}
