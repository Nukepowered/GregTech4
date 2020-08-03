package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Recycler extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Recycler(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_Recycler() {
		
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 134);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Recycler();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null && spaceForOutput(GT_ModHandler.getIC2Item("scrap", 1), null)) {
    		mOutputItem1 = GT_ModHandler.getRecyclerOutput(mInventory[2], getBaseMetaTileEntity().getRandomNumber(8));
    		mEUt = 1;
    		mMaxProgresstime = 45;
    		mInventory[2].stackSize--;
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 248;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 249;
	}
	
	@Override
	public int getTopFacingInactive() {
		return 232;
	}
	
	@Override
	public int getTopFacingActive() {
		return 233;
	}
	
	@Override
	public String getDescription() {
		return "compress, burn, obliterate and filter EVERYTHING";
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_ModHandler.getRecyclerOutput(aStack, 0)==null?false:super.allowPutStack(aIndex, aSide, aStack);
	}
}