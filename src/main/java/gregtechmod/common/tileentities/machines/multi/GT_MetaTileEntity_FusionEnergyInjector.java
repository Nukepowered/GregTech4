package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;

public class GT_MetaTileEntity_FusionEnergyInjector extends MTEFusionBus {
	
	public GT_MetaTileEntity_FusionEnergyInjector(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_FusionEnergyInjector() {}
	
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
    @Override public int maxEUInput()								{return 8192;}
    @Override public int maxEUStore()								{return 10000000;}
	@Override public int getInvSize()								{return 0;}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_FusionEnergyInjector();
	}
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aActive?20:19;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Fusion_Energy.tooltip";
	}
}
