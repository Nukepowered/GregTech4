package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BatteryCharger extends GT_MetaTileEntity_ChargerBox {
	
	public GT_MetaTileEntity_BatteryCharger(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_BatteryCharger() {
		
	}
	
	@Override public int getInvSize()								{return 9;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer,  98);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BatteryCharger();
	}
}
