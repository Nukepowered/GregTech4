package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.BaseMultiWorkable;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_VacuumFreezer extends BaseMultiWorkable {
	
	public GT_MetaTileEntity_VacuumFreezer(int aID, String mName) {
		super(aID, mName, RecipeMaps.VACUUM_FREEZER);
	}
	
	public GT_MetaTileEntity_VacuumFreezer() {
		super(RecipeMaps.VACUUM_FREEZER);
	}
	
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing == 0;}
	@Override public int getInvSize()								{return 2;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 122);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_VacuumFreezer();
	}
    
    protected boolean checkMachine() {
    	for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) {
    		if (i!=0||j!=0||k!=0) {
    			if (getBaseMetaTileEntity().getBlockOffset(i, j-2, k) != GregTech_API.sBlockList[0]) return false;
            	if (getBaseMetaTileEntity().getMetaIDOffset (i, j-2, k) != ((i==0&&j==0&&k!=0)||(i==0&&j!=0&&k==0)||(i!=0&&j==0&&k==0)?15:14)) return false;
    		} else {
    			if (!getBaseMetaTileEntity().getAirOffset(i, j-2, k)) return false;
    		}
    	}
    	return true;
    }
    
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex==1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex==0;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == 1)
			return 68+(aActive?1:0);
		if (aSide == 0)
			return 71;
		return 77;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_VacuumFreezer.tooltip";
	}
}
