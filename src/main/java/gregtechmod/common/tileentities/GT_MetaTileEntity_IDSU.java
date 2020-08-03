package gregtechmod.common.tileentities;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_IDSU extends MetaTileEntity {
	
	public int mFrequency = 0;
	
	public GT_MetaTileEntity_IDSU(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_IDSU() {
		
	}
	
	@Override public boolean isBatteryUpgradable()					{return false;}
    @Override public boolean isTransformingLowEnergy() 				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isEnetOutput()							{return true;}
	@Override public boolean isEnetInput()							{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return aSide == getBaseMetaTileEntity().getFrontFacing();}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isTeleporterCompatible()				{return true;}
	@Override public int maxEUInput()								{return 8192;}
    @Override public int maxEUOutput()								{return 8192;}
    @Override public int maxEUStore()								{return 1000000000;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public int getInvSize()								{return 3;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 151);}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_IDSU();
	}
	
	@Override public void saveNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override public void loadNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override public void onFirstTick() {
		mFrequency = getBaseMetaTileEntity().getOwnerName().hashCode();
	}
	
	@Override
    public int getInputTier() {
    	return 5;
    }
	
	@Override
    public int getOutputTier() {
    	return 5;
    }
    
	@Override
    public int rechargerSlotStartIndex() {
    	return 0;
    }
	
	@Override
    public int rechargerSlotCount() {
    	return 1;
    }
    
	@Override
    public int dechargerSlotStartIndex() {
    	return 1;
    }
	
	@Override
    public int dechargerSlotCount() {
    	return 1;
    }
	
	@Override
	public void onServerStart() {
		GregTech_API.sIDSUList.clear();
	}
	
	@Override
	public void onServerStop() {
		GregTech_API.sIDSUList.clear();
    }
	
	@Override
	public void setEUVar(int aEU) {
	    GregTech_API.sIDSUList.remove(mFrequency);
	    GregTech_API.sIDSUList.put(mFrequency, aEU);
	}
	
	@Override
	public int getEUVar() {
	    Integer tEU = GregTech_API.sIDSUList.get(mFrequency);
	    if (tEU == null) tEU = 0;
	    return tEU;
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==aFacing) return 311;
		return 22;
	}
	
	@Override
	public String getDescription() {
		return "Interdimensional Storage Unit";
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex < 2;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex < 2;
	}
}
