package gregtechmod.common.tileentities.machines.multi;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.BaseMultiFluidWorkable;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ListAdapter;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_Sawmill extends BaseMultiFluidWorkable {
	
	public GT_MetaTileEntity_Sawmill(int aID, String mName) {
		super(aID, mName, RecipeMaps.SAWMILL, 1, 0);
	}
	
	public GT_MetaTileEntity_Sawmill() {
		super(RecipeMaps.SAWMILL, 1, 0);
	}
	
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing == 0;}
	@Override public int getInvSize()								{return 6;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 116);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		GT_MetaTileEntity_Sawmill sawmill = new GT_MetaTileEntity_Sawmill();
		sawmill.getFluidInputs().set(0, GT_ModHandler.getWater(0));
		return sawmill;
	}
	
	@Override
	public List<ItemStack> getOutputItems() {
		return new ListAdapter<>(mInventory, 2, 4);
	}
	
    protected boolean checkMachine() {
    	for (int i = -1; i < 2; i++) for (int j = 1; j < 2; j++) for (int k = -1; k < 2; k++) {
    		if (getBaseMetaTileEntity().getBlockOffset(i, j-2, k) != GregTech_API.sBlockList[0]) return false;
            if (getBaseMetaTileEntity().getMetaIDOffset (i, j-2, k) != (i==0&&k==0?14:13)) return false;
    	}
    	return true;
    }
    
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_Utility.getFluidForFilledItem(aStack)!=null?aIndex==1:aIndex==0;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == 1)
			return 68+(aActive?1:0);
		if (aSide == 0)
			return 71;
		if (aSide < 4)
			return 76;
		return 75;
	}

	@Override
	public String getDescription() {
		return "metatileentity.GT_Sawmill.tooltip";
	}
	
	@Override
	public boolean canFill(ForgeDirection aSide, Fluid aFluid) {
		return getFluidAmount() < getCapacity();
	}
	
	@Override public FluidStack getFluid() {return fluidInputs.get(0).copy();}
	@Override public int getFluidAmount() {return fluidInputs.get(0).amount;}
	@Override public int getTankPressure() {return -100;}
	@Override public int getCapacity() {return 10000;}
}
