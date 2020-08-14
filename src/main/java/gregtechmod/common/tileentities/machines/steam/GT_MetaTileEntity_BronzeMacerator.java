package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_PulverizerRecipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeMacerator extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeMacerator(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_BronzeMacerator() {
		
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 164);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeMacerator();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		if (mInventory[2] != null) {
			if (OrePrefixes.ore.contains(mInventory[2])) {
		    	Object tObject = GT_ModHandler.getPulverizerRecipe(mInventory[2]);
			   	if (tObject instanceof GT_PulverizerRecipe) {
				   	GT_PulverizerRecipe tRecipe = (GT_PulverizerRecipe)tObject;
			    	if (tRecipe != null && tRecipe.getInput().stackSize <= mInventory[2].stackSize) {
					   	if ((mOutputItem1 = tRecipe.getPrimaryOutput()) != null) {
				    		if (mOutputItem1.stackSize > 1) mOutputItem1.stackSize /= 2;
				    		if (getBaseMetaTileEntity().getRandomNumber(300) < tRecipe.getSecondaryOutputChance()) {
				    			mOutputItem2 = tRecipe.getSecondaryOutput();
				    		}
				    		if (spaceForOutput(mOutputItem1, tRecipe.getSecondaryOutput())) {
					   			mInventory[2].stackSize-=tRecipe.getInput().stackSize;
								mMaxProgresstime = 400*tRecipe.getInput().stackSize;
								mEUt = 10;
				    		}
					   	}
			    	}
				}
			} else if (OrePrefixes.ingot.contains(mInventory[2]) && null != (mOutputItem1 = GT_ModHandler.getMaceratorOutput(mInventory[2], true, mInventory[3]))) {
		    	mEUt = 6;
		    	mMaxProgresstime = 200;
		    } else if (OrePrefixes.gem.contains(mInventory[2]) && null != (mOutputItem1 = GT_ModHandler.getMaceratorOutput(mInventory[2], true, mInventory[3]))) {
		    	mEUt = 8;
		    	mMaxProgresstime = 400;
		    } else if (null != (mOutputItem1 = GT_ModHandler.getMaceratorOutput(mInventory[2], true, mInventory[3]))) {
		    	mEUt = 6;
		    	mMaxProgresstime = 300;
		    }
    	}
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 326;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 327;
	}
	
	@Override
	public int getTopFacingInactive() {
		return 328;
	}
	
	@Override
	public int getTopFacingActive() {
		return 329;
	}
	
	@Override
	public String getDescription() {
		return "Grinding up Ores and also gives byproducts";
	}
}
