package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_Lathe extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Lathe(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Lathe(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 159);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Lathe(recipeLogic.recipeMap);
	}
	
	@Override
	protected void initRecipeLogic(List<Recipe> recipeMap) {
		recipeLogic = new RecipeLogic(recipeMap, this) {
			@Override
			public void moveItems() {
				GT_Utility.moveStackFromSlotAToSlotB(getBaseMetaTileEntity(), getBaseMetaTileEntity(), 1, 2, (byte)64, (byte)1, (byte)64, (byte)1);
			}
		};
	}
	
	@Override
	public int getFrontFacingInactive() {
		return 304;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 305;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Lathe.tooltip";
	}
}
