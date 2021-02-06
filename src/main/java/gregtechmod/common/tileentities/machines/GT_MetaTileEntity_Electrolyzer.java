package gregtechmod.common.tileentities.machines;

import java.util.List;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.BasicFluidWorkable;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ListAdapter;
import gregtechmod.common.recipe.RecipeMaps;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_Electrolyzer extends BasicFluidWorkable {
	
	public GT_MetaTileEntity_Electrolyzer(int aID, String mName) {
		super(aID, mName, RecipeMaps.ELECTROLYZER);
	}
	
	public GT_MetaTileEntity_Electrolyzer() {
		super(RecipeMaps.ELECTROLYZER);
	}
	
    @Override public int maxEUInput()								{return 128;}
	@Override public int getInvSize()								{return 7;}
	@Override public int getInputSlot() 							{return 1;}
	@Override public int getOutputSlot() 							{return 2;}
	@Override public int getStackDisplaySlot() 						{return 6;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 109);}
	@Override public List<ItemStack> getInputItems() 				{return new ListAdapter<>(mInventory, 0, 1);}
	@Override public List<ItemStack> getOutputItems() 				{return new ListAdapter<>(mInventory, 2, 5);} 
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Electrolyzer();
	}
    
	@Override
	public void endProcess() {
		getBaseMetaTileEntity().setErrorDisplayID(0);
	}
    
    @Override
    public void stutterProcess() {
    	super.stutterProcess();
    	getBaseMetaTileEntity().setErrorDisplayID(1);
    }
    
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_Utility.areStacksEqual(aStack, GT_ModHandler.getEmptyCell(1))?aIndex==1:aIndex==0;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == 0)
			return 32;
		if (aSide == 1)
			return 29;
		return aActive?65:64;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_Electrolyzer.tooltip";
	}
	
	@Override public int getTankPressure() {return -100;}
	@Override public int getCapacity() {return 64000;}
}
