package gregtechmod.common.tileentities.machines.basic;

import cofh.thermalexpansion.api.crafting.recipes.IPulverizerRecipe;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_PulverizerRecipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Pulverizer extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Pulverizer(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Pulverizer() {
		
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 149);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Pulverizer();
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null) {
	    	if (mInventory[3] == null && mInventory[4] == null) {
		    	if (mInventory[2].isItemEqual(new ItemStack(Blocks.gravel, 1))) {
		    		Object tObject = GT_ModHandler.getPulverizerRecipe(mInventory[2]);
				    try {
				    	if (tObject instanceof GT_PulverizerRecipe) {
					    	GT_PulverizerRecipe tRecipe = (GT_PulverizerRecipe)tObject;
				    		if (tRecipe != null && tRecipe.getInput().stackSize <= mInventory[2].stackSize) {
						    	if ((mOutputItem1 = tRecipe.getPrimaryOutput()) != null) {
					    			if (getBaseMetaTileEntity().getRandomNumber(100) < tRecipe.getSecondaryOutputChance()) {
					    				mOutputItem2 = tRecipe.getSecondaryOutput();
					    			}
					    			mInventory[2].stackSize-=tRecipe.getInput().stackSize;
									mMaxProgresstime = 300*tRecipe.getInput().stackSize;
									mEUt = 3;
					    			return;
						    	}
				    		}
		    			} else {
					    	IPulverizerRecipe tRecipe = (IPulverizerRecipe)tObject;
				    		if (tRecipe != null && tRecipe.getInput().stackSize <= mInventory[2].stackSize) {
						    	if ((mOutputItem1 = tRecipe.getPrimaryOutput()) != null) {
					    			if (getBaseMetaTileEntity().getRandomNumber(100) < tRecipe.getSecondaryOutputChance()) {
					    				mOutputItem2 = tRecipe.getSecondaryOutput();
					    			}
					    			mInventory[2].stackSize-=tRecipe.getInput().stackSize;
									mMaxProgresstime = 300*tRecipe.getInput().stackSize;
									mEUt = 3;
					    			return;
						    	}
				    		}
					    }
					} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
		    	}
			    try {
			    	for (ItemStack tOutput : mods.railcraft.api.crafting.RailcraftCraftingManager.rockCrusher.getRecipe(mInventory[2]).getRandomizedOuputs()) {
			    		if (tOutput != null) {
				    		if (mOutputItem1 == null) {
				    			mOutputItem1 = GT_Utility.copy(tOutput);
				    			continue;
				    		}
				    		if (GT_Utility.areStacksEqual(mOutputItem1, tOutput)) {
				    			mOutputItem1.stackSize += tOutput.stackSize;
				    			continue;
				    		}
				    		if (mOutputItem2 == null) {
				    			mOutputItem2 = GT_Utility.copy(tOutput);
				    			continue;
				    		}
				    		if (GT_Utility.areStacksEqual(mOutputItem2, tOutput)) {
				    			mOutputItem2.stackSize += tOutput.stackSize;
				    			continue;
				    		}
			    		}
			    	}
			    	if (mOutputItem1 != null) {
				    	mInventory[2].stackSize--;
					    mMaxProgresstime = 300;
					    mEUt = 4;
				    	return;
			    	}
			    } catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
				if (null != (mOutputItem1 = GT_ModHandler.getMaceratorOutput(mInventory[2], true, mInventory[3]))) {
	    			mOutputItem2 = null;
			    	mMaxProgresstime = 400;
			    	mEUt = 2;
			    	return;
			    }
	    	} else {
	    		bOutputBlocked = true;
	    	}
    	}
    }
	
	@Override
    public boolean needsImmidiateOutput() {
    	return true;
    }
    
	@Override
	public int getFrontFacingInactive() {
		return 256;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 257;
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
		return "Macerator + Pulverizer + Rock Crusher in one Machine";
	}
}