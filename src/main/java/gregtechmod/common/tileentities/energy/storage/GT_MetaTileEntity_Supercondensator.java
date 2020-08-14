package gregtechmod.common.tileentities.energy.storage;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GT_MetaTileEntity_Supercondensator extends MetaTileEntity {
	
	public GT_MetaTileEntity_Supercondensator(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_Supercondensator() {
		
	}
	
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return true;}
	@Override public boolean isFacingValid(byte aFacing)			{return true;}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
	@Override public boolean isEnetOutput()							{return true;}
	@Override public boolean isEnetInput()							{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return aSide == getBaseMetaTileEntity().getFrontFacing();}
	@Override public boolean isInputFacing(byte aSide)				{return !isOutputFacing(aSide);}
	@Override public boolean isTeleporterCompatible()				{return true;}
	@Override public int maxEUInput()								{return GregTech_API.VOLTAGE_ULTIMATE;}
    @Override public int maxEUOutput()								{return getBaseMetaTileEntity().isAllowedToWork()?GregTech_API.VOLTAGE_ULTIMATE:0;}
    @Override public int maxEUStore()								{return 10000000;}
	@Override public int getInvSize()								{return 0;}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		return false;
	}
	
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_Supercondensator();
	}
	
	@Override public void saveNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override public void loadNBTData(NBTTagCompound aNBT) {
		
	}
	
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
		if (aSide==aFacing) return 103;
		return 108;
	}
	
	@Override
	public String getDescription() {
		return "To output massive amounts of Energy in a short period of time.";
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
