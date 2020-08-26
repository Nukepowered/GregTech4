package gregtechmod.common.tileentities.energy.production;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_ModHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Lightningrod extends MetaTileEntity {
	
	public int mOutput = 0;
	
	public GT_MetaTileEntity_Lightningrod(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Lightningrod() {
		
	}
	
	@Override public boolean isEnetOutput()							{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return aSide > 1;}
	@Override public boolean isTeleporterCompatible()				{return false;}
    @Override public int maxEUOutput()								{return 8192;}
    @Override public int maxEUStore()								{return 100000000;}
	@Override public int getInvSize()								{return 0;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		return false;
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Lightningrod();
	}
	
	@Override public void saveNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override public void loadNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override
    public void onPostTick() {
	    if (getBaseMetaTileEntity().isServerSide()) {
	    	if (getBaseMetaTileEntity().getTimer()%256==0&&(getBaseMetaTileEntity().getWorld().isThundering()||(getBaseMetaTileEntity().getWorld().isRaining()&&getBaseMetaTileEntity().getRandomNumber(10)==0))) {
	    		int rodvalue = 0;
	    		boolean rodvalid = true;
	    		
	        	for (int i = getBaseMetaTileEntity().getYCoord() + 1; i < getBaseMetaTileEntity().getWorld().getHeight()-1; i++) {
	        		if (rodvalid && getBaseMetaTileEntity().getBlockOffset(0, i-getBaseMetaTileEntity().getYCoord(), 0) == Block.getBlockFromItem(GT_ModHandler.getIC2Item("ironFence", 1).getItem())) {
	        			rodvalue++;
	        		} else {
	        			rodvalid = false;
	        			if ( getBaseMetaTileEntity().getBlockOffset(0, i-getBaseMetaTileEntity().getYCoord(), 0) != Blocks.air) {
	        				rodvalue=0;
	        				break;
	        			}
	        		}
	        	}
	        	
	        	if (!getBaseMetaTileEntity().getWorld().isThundering() && getBaseMetaTileEntity().getYCoord()+rodvalue<128) rodvalue=0;
	        	
	        	if (getBaseMetaTileEntity().getRandomNumber(4096*getBaseMetaTileEntity().getWorld().getHeight())<rodvalue*(getBaseMetaTileEntity().getYCoord()+rodvalue)) {
	            	setEUVar(25000000);
	    	    	getBaseMetaTileEntity().getWorld().addWeatherEffect(new EntityLightningBolt(getBaseMetaTileEntity().getWorld(), getBaseMetaTileEntity().getXCoord(), getBaseMetaTileEntity().getYCoord()+rodvalue, getBaseMetaTileEntity().getZCoord()));
	    		}
	    	}
	    }
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==0) return  3;
		if (aSide==1) return 21;
		return 311;
	}
	
	@Override
	public String getDescription() {
		return "tile.BlockMetaID_Machine.Lightningrod.tooltip";
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
