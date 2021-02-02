package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Recycler extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Recycler(int aID, String mName, List<Recipe> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Recycler(List<Recipe> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 134);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Recycler(recipeLogic.recipeMap);
	}
	
	@Override
	public void initRecipeLogic(List<Recipe> recipeMap) {
		super.initRecipeLogic(recipeMap);
		recipeLogic.setRecipeProvider(() -> {
	    	if (GT_Utility.isStackValid(mInventory[2])) {
	    		ItemStack instance = mInventory[2].copy();
	    		instance.stackSize = 1;
	    		return new Recipe(instance, null, null, null, null, null, 45, 1, 0, false) {
	    			@Override
	    			public ItemStack[] getOutputs() {
	    				return new ItemStack[] {GT_ModHandler.getRecyclerOutput(mInputs[0][0], getBaseMetaTileEntity().getRandomNumber(8))}; // FIXME made chanded output in Recipe 
	    			}
	    		};
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