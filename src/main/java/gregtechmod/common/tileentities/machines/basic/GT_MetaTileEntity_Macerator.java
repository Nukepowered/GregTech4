package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Macerator extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Macerator(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Macerator(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 131);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Macerator(recipeLogic.recipeMap);
	}
	
	@Override
	protected void initRecipeLogic(List<Recipe> recipeMap) {
		super.initRecipeLogic(recipeMap);
		recipeLogic.setRecipeProvider(() -> {
			ItemStack output = null;
	    	if (mInventory[2] != null && null != (output = GT_ModHandler.getMaceratorOutput(mInventory[2], true, mInventory[3]))) {
	    		return new Recipe(mInventory[2].copy(), null, output, null, null, null, 400, 2, 0, false); // TODO add methods get input by output, or all recipe here
	    	}
	    	
    		return null;
		});
    }
	
	@Override
	public int getFrontFacingInactive() {
		return 244;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 245;
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
		return "metatileentity.GT_Macerator.tooltip";
	}
}
