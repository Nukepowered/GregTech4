package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeAlloySmelter extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeAlloySmelter(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
		loopLen = 130;
	}
	
	public GT_MetaTileEntity_BronzeAlloySmelter(RecipeMap<?> recipeMap) {
		super(recipeMap);
		loopLen = 130;
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 166);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeAlloySmelter(recipeLogic.recipeMap);
	}
	
	@Override
	public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 1) {
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(202), 10, 1.0F, aX, aY, aZ);
		}
	}
	
	@Override
	public void startProcess() {
		this.sendLoopStart((byte) 1);
	}
	
	@Override
    public boolean hasTwoSeperateInputs() {
    	return true;
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 332;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 333;
	}
	
	@Override
	public int getSideFacingInactive() {
		return 334;
	}
	
	@Override
	public int getBottomFacingInactive() {
		return 335;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeAlloySmelter.tooltip";
	}
}
