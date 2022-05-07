package gregtechmod.common.tileentities.machines.multi;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IRecipeWorkable;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.BaseMultiWorkable;
import gregtechmod.api.recipe.RecipeLogic;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_MetaTileEntity_BronzeBlastFurnace extends BaseMultiWorkable {
	
	public GT_MetaTileEntity_BronzeBlastFurnace(int aID, String mName) {
		super(aID, mName, RecipeMaps.BRONZE_BLAST_FURNACE);
	}
	
	public GT_MetaTileEntity_BronzeBlastFurnace() {
		super(RecipeMaps.BRONZE_BLAST_FURNACE);
	}
	
	@Override public boolean isSteampowered()						{return false;}
	@Override public boolean isElectric()							{return false;}
	@Override public boolean isPneumatic()							{return false;}
	@Override public boolean isTransformerUpgradable()				{return false;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return false;}
	@Override public boolean isEnetInput() 							{return false;}
	@Override public boolean isEnetOutput() 						{return false;}
	@Override public boolean isInputFacing(byte aSide)				{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return false;}
	@Override public boolean isTeleporterCompatible()				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return aFacing > 1;}
	@Override public int getMinimumStoredEU()						{return 1000;}
	@Override public int maxEUInput()								{return 0;}
    @Override public int maxEUOutput()								{return 0;}
    @Override public int maxEUStore()								{return 0;}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return GregTech_API.getCoverBehavior(aCoverID).isSimpleCover() && super.allowCoverOnSide(aSide, aCoverID);
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_BronzeBlastFurnace();
	}
	
	@Override
	public void initRecipeLogic(RecipeMap<?> map) {
		recipeLogic = new BronzeBlastRecipeLogic(map, this);
	}
	
	@Override
	public void onRightclick(EntityPlayer aPlayer) {
	    getBaseMetaTileEntity().openGUI(aPlayer, 170);
	}
	
	@Override
    protected boolean checkMachine() {
    	int xDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getBackFacing()).offsetX, zDir = ForgeDirection.getOrientation(getBaseMetaTileEntity().getBackFacing()).offsetZ;
    	for (int i = -1; i < 2; i++) for (int j = -1; j < 3; j++) for (int k = -1; k < 2; k++) if (xDir+i != 0 || j != 0 || zDir+k != 0) {
    		if (i!=0||j==-1||k!=0) {
    			if (getBaseMetaTileEntity().getBlockOffset(xDir+i, j, zDir+k) != GregTech_API.sBlockList[4] || getBaseMetaTileEntity().getMetaIDOffset(xDir+i, j, zDir+k) != 13) return false;
    		} else {
    			if (getBaseMetaTileEntity().getBlockOffset(xDir+i, j, zDir+k) != Blocks.lava && !getBaseMetaTileEntity().getAirOffset(xDir+i, j, zDir+k)) return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isClientSide()) {
    		if (getBaseMetaTileEntity().isActive()) {
    			getBaseMetaTileEntity().getWorld().spawnParticle("largesmoke", getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1) + Math.random(), getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1) + Math.random(), 0, 0.3, 0);
    		}
    	}
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	if (needCheckStruct) {
	    		structComplete = checkMachine();
	    		needCheckStruct = false;
	    		getBaseMetaTileEntity().setErrorDisplayID(structComplete ? 0 : 1);
	    	} else if (!needCheckStruct && !structComplete && getBaseMetaTileEntity().getTimer() % 600 == 0) {
	    		structComplete = checkMachine();
	    	}
	    	
    		if (structComplete) recipeLogic.update();
    		if (recipeLogic.isActive()) {
    			if (getBaseMetaTileEntity().getAir(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()  , getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1))) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()  , getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1), Blocks.lava, 1, 2);
    			if (getBaseMetaTileEntity().getAir(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1))) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1), Blocks.lava, 1, 2);
    		} else {
    			if (getBaseMetaTileEntity().getBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()  , getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1)) == Blocks.lava) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()  , getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1), Blocks.air, 0, 2);
    			if (getBaseMetaTileEntity().getBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1)) == Blocks.lava) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getBackFacing(), 1), getBaseMetaTileEntity().getYCoord()+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getBackFacing(), 1), Blocks.air, 0, 2);
            }
		}
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) return aActive?348:347;
		if (aSide == 0) return 315;
		if (aSide == 1) return 314;
		return 316;
	}
	
	@Override
	public boolean isGivingInformation() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_BronzeBlastFurnace.tooltip";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex>1;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex<2&&!GT_Utility.areStacksEqual(aStack, mInventory[aIndex==0?1:0]);
	}
	
	private static class BronzeBlastRecipeLogic extends RecipeLogic {
		public BronzeBlastRecipeLogic(RecipeMap<?> recipeMap, IRecipeWorkable machine) {
			super(recipeMap, machine);
		}
		
		@Override
		public boolean update() {
			boolean success = false;
			IGregTechTileEntity base = getMachine().getBaseMetaTileEntity();
			overclockersCount = base.getOverclockerUpgradeCount();
			
			if (base.isAllowedToWork()) {
				if (progressTime > 0) {
					int tmp = progressTime;
					success = updateRecipeProgress();
					if (tmp == 0 && !success) {
						throw new IllegalStateException();
					}
				}
				
				if (progressTime == 0) {
					if (base.hasInventoryBeenModified() || base.hasWorkJustBeenEnabled() || success || base.getTimer() % 600 == 0) {// || isInputNonEmpty()) {
						trySerachRecipe();
					}
				}
			}
			
			return success;
		}
		
		@Override
		protected boolean updateRecipeProgress() {
			if (++progressTime >= maxProgressTime) {
				progressTime = 0;
				maxProgressTime = 0;
				EUt = 0;
				
				endRecipe(previousRecipe);
				getMachine().endProcess();
				return true;
			}
			
			return false;
		}
	}
}