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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_Grinder extends BaseMultiFluidWorkable {
	
	public GT_MetaTileEntity_Grinder(int aID, String mName) {
		super(aID, mName, RecipeMaps.GRINDER, 1, 0);
	}
	
	public GT_MetaTileEntity_Grinder() {
		super(RecipeMaps.GRINDER, 1, 0);
	}
	
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public boolean isValidSlot(int aIndex)				{return true;}
	@Override public int getInvSize()								{return 6;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 112);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		GT_MetaTileEntity_Grinder m = new GT_MetaTileEntity_Grinder();
		m.fluidInputs.set(0, GT_ModHandler.getWater(0));
		return m;
	}
	
    protected boolean checkMachine() {
    	int xDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetX*2, yDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetY*2, zDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetZ*2;
    	for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) {
    		if (i!=0||j!=0||k!=0) {
    			if (getBaseMetaTileEntity().getBlockOffset(-xDir+i, -yDir+j, -zDir+k) != GregTech_API.sBlockList[0]) return false;
            	if (getBaseMetaTileEntity().getMetaIDOffset(-xDir+i, -yDir+j, -zDir+k) != (j==0?14:13)) return false;
    		} else {
    			if (getBaseMetaTileEntity().getBlockOffset(-xDir+i, -yDir+j, -zDir+k) != Blocks.water) return false;
    		}
    	}
    	return true;
    }
    
	@Override
	public List<ItemStack> getOutputItems() {
		return new ListAdapter<>(mInventory, 2, 5);
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
		if (aSide == aFacing)
			return 68+(aActive?1:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 71;
		return 70;
	}
	
	@Override
	public String getDescription() {
		return "metatilentity.GT_Grinder.tooltip";
	}
	
	@Override public int getTankPressure() {return -100;}
	@Override public int getCapacity() {return 10000;}
	
	@Override public int getFluidAmount() {
		FluidStack stack = null;
		if (fluidInputs.size() > 0 && (stack = fluidInputs.get(0)) != null) {
			return stack.amount;
		}
		
		return 0;
	}
}
