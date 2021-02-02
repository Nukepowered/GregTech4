package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Assembler extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Assembler(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Assembler(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 141);}
	
	@Override
	protected void initRecipeLogic(List<Recipe> recipeMap) {
		super.initRecipeLogic(recipeMap);
		recipeLogic.moveItems = false;
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Assembler(recipeLogic.recipeMap);
	}
	
	@Override
    public boolean hasTwoSeperateInputs() {
    	return true;
    }
	
	@Override
	public int getTopFacingInactive() {
		return 237;
	}
	
	@Override
	public int getTopFacingActive() {
		return 237;
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 224;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 225;
	}
	@Override
	public String getDescription() {
		return "metatileentity.GT_Assembler.tooltip";
	}
}
