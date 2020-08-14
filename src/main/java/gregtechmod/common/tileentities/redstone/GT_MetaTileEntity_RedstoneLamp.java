package gregtechmod.common.tileentities.redstone;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_RedstoneLamp extends MetaTileEntity {
	
	public GT_MetaTileEntity_RedstoneLamp(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_RedstoneLamp() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return true;}
    @Override public boolean isValidSlot(int aIndex) 				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
    @Override public int getInvSize()								{return 0;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_RedstoneLamp();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		return false;
	}
	
    @Override
    public void onPreTick() {
	    if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().isServerSide()) {
	    	getBaseMetaTileEntity().setLightValue(getBaseMetaTileEntity().getStrongestRedstone());
	    	getBaseMetaTileEntity().setActive(getBaseMetaTileEntity().getStrongestRedstone()>0);
		}
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		return aActive?292:291;
	}
	
	@Override
	public String getDescription() {
		return "Redstone Controlled Lamp";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return false;
	}
}
