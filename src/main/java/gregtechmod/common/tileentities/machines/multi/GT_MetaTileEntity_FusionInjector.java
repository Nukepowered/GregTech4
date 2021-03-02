package gregtechmod.common.tileentities.machines.multi;

import java.lang.ref.WeakReference;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;

import net.minecraft.entity.player.EntityPlayer;

public class GT_MetaTileEntity_FusionInjector extends GT_MetaTileEntity_BasicTank {
	
	private WeakReference<IGregTechTileEntity> mFusionComputer;
	
	public GT_MetaTileEntity_FusionInjector(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_FusionInjector() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 144);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_FusionInjector();
	}
	
	@Override public boolean doesFillContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean doesEmptyContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeFilled()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeEmptied()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return true;}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide()) {
    		if (mFusionComputer != null && mFusionComputer.get() != null) {
    			if (mFusionComputer.get().isInvalidTileEntity()) {
    				mFusionComputer.clear();
    				getBaseMetaTileEntity().setActive(false);
    			} else if (getBaseMetaTileEntity().isActive() != mFusionComputer.get().isActive()) {
    				getBaseMetaTileEntity().setActive(!getBaseMetaTileEntity().isActive());
    			}
    		}
    	}
    }
	
    public void setComputer(IGregTechTileEntity computer) {
    	mFusionComputer = new WeakReference<>(computer);
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 0 || aSide == 1) return 16;
    	return aActive?20:19;
	}
	
	@Override
	public int getCapacity() {
		return 10000;
	}
	
	@Override
	public String getDescription() {
		return "metatilentity.GT_Fusion_Injector.tooltip";
	}
	
	@Override
	public int getTankPressure() {
		return -100;
	}
}
