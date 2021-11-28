package gregtechmod.common.tileentities.energy.storage;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class GT_MetaTileEntity_AESU extends MetaTileEntity {
	
	public int mOutput = 0;
	
	public GT_MetaTileEntity_AESU(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_AESU() {
		
	}
	
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isEnetOutput()							{return true;}
	@Override public boolean isEnetInput()							{return true;}
    @Override public boolean isTransformingLowEnergy() 				{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return aSide == getBaseMetaTileEntity().getFrontFacing();}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isTeleporterCompatible()				{return true;}
	@Override public int maxEUInput()								{return 8192;}
    @Override public int maxEUOutput()								{return mOutput;}
    @Override public int maxEUStore()								{return 100000000;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public int getInvSize()								{return 3;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 150);}
    @Override public String getSpecialVoltageToolTip()				{return StatCollector.translateToLocal("metatileentity.TileEntity_EUp_OUT.AESU.tooltip");}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_AESU();
	}
	
	@Override public void saveNBTData(NBTTagCompound aNBT) {
		aNBT.setInteger("mOutput", mOutput);
	}
	
	@Override public void loadNBTData(NBTTagCompound aNBT) {
		mOutput = aNBT.getInteger("mOutput");
	}
	
	@Override
    public int getInputTier() {
    	return 5;
    }
	
	@Override
    public int getOutputTier() {
    	return GT_Utility.getTier(mOutput);
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
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==aFacing) return 311;
		return 16;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_AESU.tooltip";
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
