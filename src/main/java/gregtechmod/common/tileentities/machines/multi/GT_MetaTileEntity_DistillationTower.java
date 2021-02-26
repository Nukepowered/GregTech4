package gregtechmod.common.tileentities.machines.multi;

import java.util.List;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.BaseMultiWorkable;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ListAdapter;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_MetaTileEntity_DistillationTower extends BaseMultiWorkable {

	public GT_MetaTileEntity_DistillationTower(int aID, String mName) {
		super(aID, mName, RecipeMaps.DISTILLATION);
	}
	
	public GT_MetaTileEntity_DistillationTower() {
		super(RecipeMaps.DISTILLATION);
	}
	
	@Override public int getInvSize()								{return 6;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 127);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_DistillationTower();
	}
	
	@Override
	public List<ItemStack> getOutputItems() {
		return new ListAdapter<>(mInventory, 2, 5);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
    	super.saveNBTData(aNBT);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
    	super.loadNBTData(aNBT);
	}
	
	@Override
    protected boolean checkMachine() {
    	int xDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetX*2, yDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetY*2, zDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetZ*2;
    	for (int i = -1; i < 2; i++) for (int j = 0; j < 5; j++) for (int k = -1; k < 2; k++) {
    		Block tBlockID = getBaseMetaTileEntity().getBlockOffset(-xDir+i, -yDir+j, -zDir+k);
    		if (i!=0||k!=0||j==0||j==4) {
    			if (tBlockID != GregTech_API.sBlockList[0]) return false;
            	int tMeta = getBaseMetaTileEntity().getMetaIDOffset(-xDir+i, -yDir+j, -zDir+k);
            	if (j%2==0) {
            		if (tMeta != 13) return false;
            	} else {
            		if (tMeta != 15) return false;
            	}
    		} else {
    			if (!getBaseMetaTileEntity().getAirOffset(-xDir+i, -yDir+j, -zDir+k)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    @Override
    public void onPostTick() {
    	super.onPostTick();
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return aActive?69:68;
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 71;
		return 78;
	}

	@Override
	public String getDescription() {
		return "metatileentity.GT_DistillationTower.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_Utility.areStacksEqual(aStack, GT_ModHandler.getEmptyCell(1))?aIndex==1:aIndex==0;
	}
}
