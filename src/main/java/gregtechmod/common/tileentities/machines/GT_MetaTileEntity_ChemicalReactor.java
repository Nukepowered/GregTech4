package gregtechmod.common.tileentities.machines;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ListAdapter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_ChemicalReactor extends GT_MetaTileEntity_BasicMachine {

	public GT_MetaTileEntity_ChemicalReactor(int aID, String mName, RecipeMap<?> recipeMap) {
		super(aID, mName, recipeMap);
	}
	
	public GT_MetaTileEntity_ChemicalReactor(RecipeMap<?> recipeMap) {
		super(recipeMap);
	}
	
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public int getInvSize()								{return 3;}
	@Override public int dechargerSlotCount() 						{return 0;}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
		getBaseMetaTileEntity().openGUI(aPlayer, 124);
	}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ChemicalReactor(recipeLogic.recipeMap);
	}
    
	@Override
    public List<ItemStack> getInputItems() {
		return new ListAdapter<>(mInventory, 0, 1);
	}
	
	@Override
    public List<ItemStack> getOutputItems() {
		return new ListAdapter<>(mInventory, 2, 2);
	}
    
    @Override
    public void startProcess() {
    	getBaseMetaTileEntity().setErrorDisplayID(0);
    }
    
    @Override
    public void stutterProcess() {
    	getBaseMetaTileEntity().setErrorDisplayID(1);
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == 0)
			return 32;
		if (aSide == 1)
			return 29;
		return aActive?67:66;
	}

	@Override
	public String getDescription() {
		return "metatileentity.GT_ChemicalReactor.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex<2&&!GT_Utility.areStacksEqual(aStack, mInventory[aIndex==0?1:0]);
	}
}
