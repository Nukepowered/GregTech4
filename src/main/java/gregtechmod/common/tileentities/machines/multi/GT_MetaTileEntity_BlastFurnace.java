package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.BaseMultiWorkable;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_MetaTileEntity_BlastFurnace extends BaseMultiWorkable {

	public int mHeatCapacity = 0, mHeatingCoilTier = 0;
	
	public GT_MetaTileEntity_BlastFurnace(int aID, String mName) {
		super(aID, mName, RecipeMaps.BLAST_FURNACE);
	}

	public GT_MetaTileEntity_BlastFurnace() {
		super(RecipeMaps.BLAST_FURNACE);
	}
	
	@Override
	protected void initRecipeLogic(RecipeMap<?> map) {
		super.initRecipeLogic(map);
		recipeLogic.setMetadataVerify(recipe -> {
			Object data = recipe.getMeta("minTemp");
			if (data != null && data instanceof Integer)
				return mHeatCapacity >= ((Integer)data);
			
			return false;
		});
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BlastFurnace();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
    	aNBT.setByte("mHeatingCoilTier", (byte)mHeatingCoilTier);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
    	mHeatingCoilTier = aNBT.getByte("mHeatingCoilTier");
	}
	
	@Override
	public void setItemNBT(NBTTagCompound aNBT) {
		if (mHeatingCoilTier > 0) aNBT.setByte("mHeatingCoilTier", (byte)mHeatingCoilTier);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
	    ItemStack tPlayerItem = aPlayer.inventory.getCurrentItem();
	    if (mHeatingCoilTier <= 0 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier01")) {
	    	if (tPlayerItem.stackSize > 3 || aPlayer.capabilities.isCreativeMode) {
	    		if (!aPlayer.capabilities.isCreativeMode) tPlayerItem.stackSize-=4;
		    	mHeatingCoilTier = 1;
		    	needCheckStruct = true;
	    	}
	    	return;
	    }
	    if (mHeatingCoilTier == 1 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier02")) {
	    	if (tPlayerItem.stackSize > 3 || aPlayer.capabilities.isCreativeMode) {
	    		if (!aPlayer.capabilities.isCreativeMode) tPlayerItem.stackSize-=4;
		    	mHeatingCoilTier = 2;
		    	needCheckStruct = true;
	    	}
	    	return;
	    }
	    if (mHeatingCoilTier == 2 && GT_OreDictUnificator.isItemStackInstanceOf(tPlayerItem, "craftingHeatingCoilTier03")) {
	    	if (tPlayerItem.stackSize > 3 || aPlayer.capabilities.isCreativeMode) {
	    		if (!aPlayer.capabilities.isCreativeMode) tPlayerItem.stackSize-=4;
		    	mHeatingCoilTier = 3;
		    	needCheckStruct = true;
	    	}
	    	return;
	    }
	    getBaseMetaTileEntity().openGUI(aPlayer, 113);
	}
	
	@Override
    protected boolean checkMachine() {
    	int xDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetX*2, yDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetY*2, zDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetZ*2;
    	mHeatCapacity = mHeatingCoilTier * 500;
    	for (int i = -1; i < 2; i++) for (int j = 0; j < 4; j++) for (int k = -1; k < 2; k++) {
    		Block tBlockID = getBaseMetaTileEntity().getBlockOffset(-xDir+i, -yDir+j, -zDir+k);
    		if (i!=0||(j!=1&&j!=2)||k!=0) {
    			if (tBlockID != GregTech_API.sBlockList[0]) return false;
            	int tMeta = getBaseMetaTileEntity().getMetaIDOffset(-xDir+i, -yDir+j, -zDir+k);
    			if (tMeta == 13)
            		mHeatCapacity += 30;
            	else if (tMeta == 14)
            		mHeatCapacity += 50;
            	else if (tMeta == 15)
            		mHeatCapacity += 70;
            	else return false;
    		} else {
    			if (tBlockID == Blocks.lava || tBlockID == Blocks.flowing_lava) {
    				mHeatCapacity += 250;
    			} else if (!getBaseMetaTileEntity().getAirOffset(-xDir+i, -yDir+j, -zDir+k)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    @Override
    public void onPostTick() {
    	super.onPostTick();
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	if (!structComplete) {
	    		mHeatCapacity = 0;
	    	}  
		}
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing)
			return 68+(aActive?1:0);
		if (GT_Utility.getOppositeSide(aSide) == aFacing)
			return 71;
		return 72;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BlastFurnace.tooltip";
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
