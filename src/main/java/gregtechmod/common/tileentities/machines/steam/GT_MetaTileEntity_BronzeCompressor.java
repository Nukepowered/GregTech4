package gregtechmod.common.tileentities.machines.steam;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine_Bronze;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_BronzeCompressor extends GT_MetaTileEntity_BasicMachine_Bronze {
	
	public GT_MetaTileEntity_BronzeCompressor(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
		loopLen = 290;
	}
	
	public GT_MetaTileEntity_BronzeCompressor(RecipeMap<?> recipeMap) {
		super(recipeMap);
		loopLen = 290;
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 168);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeCompressor(recipeLogic.recipeMap);
	}
	
	@Override
	public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 1) {
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(203), 10, 1.0F, aX, aY, aZ);
		}
	}
	
	@Override
	public void startProcess() {
		this.sendLoopStart((byte) 1);
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
		return "metatileentity.GT_BronzeCompressor.name";
	}
}