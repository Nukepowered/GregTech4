package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeMacerator extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeMacerator(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
		loopLen = 290;
	}
	
	public GT_MetaTileEntity_BronzeMacerator(RecipeMap<?> recipeMap) {
		super(recipeMap);
		loopLen = 290;
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 164);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeMacerator(recipeLogic.recipeMap);
	}
	
	@Override
	public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 1) {
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(201), 10, 1.0F, aX, aY, aZ);
		}
	}
	
	@Override
	public void startProcess() {
		this.sendLoopStart((byte) 1);
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
		return "metatileentity.GT_BronzeMacerator.tooltip";
	}
}
