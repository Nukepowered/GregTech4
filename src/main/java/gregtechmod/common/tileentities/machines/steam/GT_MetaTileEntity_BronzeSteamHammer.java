package gregtechmod.common.tileentities.machines.steam;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeSteamHammer extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeSteamHammer(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_BronzeSteamHammer(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 167);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeSteamHammer(recipeLogic.recipeMap);
	}
	
	@Override
    public void checkRecipe() {
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
		GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 3, 4, (byte)64, (byte)1, (byte)64, (byte)1);
    	if (mInventory[2] != null) {
			String tOreName = GT_OreDictUnificator.getAssociation(mInventory[2]);
	    	if (tOreName != null) {
	    		if (tOreName.startsWith("ingot") && mInventory[2].stackSize >= 2 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.getFirstOre(tOreName.replaceFirst("ingot", "plate"), 1), null) && mOutputItem1 != null) {
		    		mEUt = 32;
		    		mMaxProgresstime = 30;
		    		mInventory[2].stackSize-=2;
		    	} else if (tOreName.startsWith("plate") && mInventory[2].stackSize >= 10 && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.getFirstOre(tOreName.replaceFirst("plate", "plateDense"), 1), null) && mOutputItem1 != null) {
		    		mEUt = 32;
		    		mMaxProgresstime = 240;
		    		mInventory[2].stackSize-=10;
		    	} else if (tOreName.startsWith("ore") && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.getFirstOre(tOreName.replaceFirst("ore", "dust"), 1), null) && mOutputItem1 != null) {
		    		mEUt = 32;
		    		mMaxProgresstime = 10;
		    		mInventory[2].stackSize-=1;
		    	} else if (tOreName.startsWith("gem") && spaceForOutput(mOutputItem1 = GT_OreDictUnificator.getFirstOre(tOreName.replaceFirst("gem", "dust"), 1), null) && mOutputItem1 != null) {
		    		mEUt = 32;
		    		mMaxProgresstime = 20;
		    		mInventory[2].stackSize-=1;
		    	}
	    	}
    	}
    }
	
	@Override
	public void startProcess() {
		sendSound((byte)10);
	}
	
	@Override
    public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 10) GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(1), 100, 1.0F, 0.01F, aX, aY, aZ);
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 336;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 337;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeSteamHammer.tooltip";
	}
}
