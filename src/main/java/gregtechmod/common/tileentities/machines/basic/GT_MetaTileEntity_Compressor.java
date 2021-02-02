package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Compressor extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Compressor(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Compressor(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 133);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Compressor(recipeLogic.recipeMap);
	}
	
	@Override
	protected void initRecipeLogic(List<Recipe> recipeMap) {
		super.initRecipeLogic(recipeMap);;
		recipeLogic.setRecipeProvider(() -> {
			ItemStack output = null;
			if (mInventory[2] != null && (output = GT_ModHandler.getCompressorOutput(mInventory[2], false, mInventory[3])) != null) {
				return new Recipe(mInventory[2].copy(), null, output, null, null, null, 400, 2, 0, false); // TODO add methods get input by output, or all recipe here
			}
			
			return null;
		});
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
		return 230;
	}
	
	@Override
	public int getTopFacingActive() {
		return 234;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Compressor.tooltip";
	}
}
