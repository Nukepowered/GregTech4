package gregtechmod.common.tileentities.machines.basic;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Recycler extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Recycler(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Recycler(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 134);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Recycler(recipeLogic.recipeMap);
	}
	
	@Override
	public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 1) {
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(204), 10, 1.0F, aX, aY, aZ);
		}
	}
	
	@Override
	public void startProcess() {
		this.sendLoopStart((byte) 1);
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
		return "metatileentity.GT_Recycler.tooltip";
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_ModHandler.getRecyclerOutput(aStack, 0)==null?false:super.allowPutStack(aIndex, aSide, aStack);
	}
}