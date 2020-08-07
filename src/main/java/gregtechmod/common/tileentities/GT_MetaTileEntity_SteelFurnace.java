package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Steel;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_SteelFurnace extends GT_MetaTileEntity_BasicMachine_Steel {
	
	public GT_MetaTileEntity_SteelFurnace(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_SteelFurnace() {
		
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 172);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_SteelFurnace();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
		if (mInventory[2] != null && null != (mOutputItem1 = GT_ModHandler.getSmeltingOutput(mInventory[2], true, mInventory[3]))) {
	    	mEUt = 6;
    		mMaxProgresstime = 150;
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 360;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 361;
	}
	
	@Override
	public int getSideFacingInactive() {
		return 362;
	}
	
	@Override
	public int getBottomFacingInactive() {
		return 363;
	}
	
	@Override
	public String getDescription() {
		return "Better than Bronze Furnii";
	}
}
