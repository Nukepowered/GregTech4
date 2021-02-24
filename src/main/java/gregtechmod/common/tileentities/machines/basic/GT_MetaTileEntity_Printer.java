package gregtechmod.common.tileentities.machines.basic;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.ListAdapter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Printer extends GT_MetaTileEntity_BasicMachine {
	
	public GT_MetaTileEntity_Printer(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_Printer(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 142);}
	@Override public int dechargerSlotStartIndex()					{return 0;}
	@Override public int dechargerSlotCount()						{return 0;}
	@Override public List<ItemStack> getInputItems()				{return new ListAdapter<>(mInventory, 1, 3);}
	@Override public List<ItemStack> getOutputItems()				{return new ListAdapter<>(mInventory, 4, 5);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Printer(recipeLogic.recipeMap);
	}
	
	@Override
    public boolean hasTwoSeperateInputs() {
    	return true;
    }
    
	@Override
	public int getFrontFacingInactive() {
		return 33;
	}
	
	@Override
	public int getFrontFacingActive() {
		return 34;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Printer.tooltip";
	}
}
