package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Macerator extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Macerator(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Macerator() {
		
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 131);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Macerator();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null && null != (mOutputItem1 = GT_ModHandler.getMaceratorOutput(mInventory[2], true, mInventory[3]))) {
    		mEUt = 2;
    		mMaxProgresstime = 400;
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 244;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 245;
	}
	
	@Override
	public int getTopFacingInactive() {
		return 228;
	}
	
	@Override
	public int getTopFacingActive() {
		return 229;
	}
	
	@Override
	public String getDescription() {
		return "Maceratron-E-901";
	}
}
