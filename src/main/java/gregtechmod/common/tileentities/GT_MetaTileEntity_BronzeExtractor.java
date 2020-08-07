package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeExtractor extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeExtractor(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_BronzeExtractor() {
		
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 169);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeExtractor();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null && null != (mOutputItem1 = GT_ModHandler.getExtractorOutput(mInventory[2], true, mInventory[3]))) {
    		mEUt = 3;
    		mMaxProgresstime = 800;
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 342;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 343;
	}
	
	@Override
	public String getDescription() {
		return "Steam powered Extractor";
	}
}