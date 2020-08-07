package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeCompressor extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeCompressor(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_BronzeCompressor() {
		
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 168);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeCompressor();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null && null != (mOutputItem1 = GT_ModHandler.getCompressorOutput(mInventory[2], true, mInventory[3]))) {
    		mEUt = 4;
    		mMaxProgresstime = 600;
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 340;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 341;
	}
	
	@Override
	public String getDescription() {
		return "Steam powered Compressor";
	}
}