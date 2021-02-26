package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.BaseMultiWorkable;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GT_MetaTileEntity_ImplosionCompressor extends BaseMultiWorkable {
	
	public GT_MetaTileEntity_ImplosionCompressor(int aID, String mName) {
		super(aID, mName, RecipeMaps.IMPLOSION_COMPRESSOR);
	}
	
	public GT_MetaTileEntity_ImplosionCompressor() {
		super(RecipeMaps.IMPLOSION_COMPRESSOR);
	}
	
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing == 0;}
    @Override public int maxEUInput()								{return 32;}
	@Override public int getInvSize()								{return 4;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 115);}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ImplosionCompressor();
	}
	
    protected boolean checkMachine() {
    	for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) {
    		if (i!=0||j!=0||k!=0) {
    			if (getBaseMetaTileEntity().getBlockOffset(i, j-2, k) != GregTech_API.sBlockList[0]) return false;
            	if (getBaseMetaTileEntity().getMetaIDOffset (i, j-2, k) != (i==0||j==0||k==0?14:13)) return false;
    		} else {
    			if (!getBaseMetaTileEntity().getAirOffset(i, j-2, k)) return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public void endProcess() {
    	sendSound((byte)1);
    }
    
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_Utility.areStacksEqual(aStack, GT_ModHandler.getIC2Item("industrialTnt", 1, new ItemStack(Blocks.tnt, 1)))?aIndex==1:aIndex==0;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == 1)
			return 68+(aActive?1:0);
		if (aSide == 0)
			return 71;
		if (aSide < 4)
			return 74;
		return 73;
	}
	
	@Override
    public void doSound(byte aIndex, double aX, double aY, double aZ) {
		GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(5), 5, 1.0F, aX, aY, aZ);
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_ImplosionCompressor.tooltip";
	}
}
