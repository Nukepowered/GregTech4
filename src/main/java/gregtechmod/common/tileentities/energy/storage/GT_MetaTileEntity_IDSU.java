package gregtechmod.common.tileentities.energy.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map.Entry;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class GT_MetaTileEntity_IDSU extends MetaTileEntity {
	
	public int mFrequency = 0;
	
	public GT_MetaTileEntity_IDSU(int aID, String mName) {
		super(aID, mName);
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
	public void onFirstServerTick() {
		GregTech_API.sIDSUList.clear();
		if (GT_Mod.mUniverse != null && !GT_Mod.mUniverse.isRemote) {
			try {
				File dir = GT_Mod.getSaveDirectory();
				if (dir != null) {
					NBTTagCompound data = CompressedStreamTools.readCompressed(new FileInputStream(new File(dir, "data/GT_IDSU_Energyvalues.dat")));
					NBTTagList list = data.getTagList("Energy", 10);
					for (int i = 0; i < list.tagCount(); ++i) {
						NBTTagCompound tTag = list.getCompoundTagAt(i);
						GregTech_API.sIDSUList.put(tTag.getInteger("Hash"), tTag.getInteger("EU"));
					}
				}
			} catch (FileNotFoundException e) {
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void onServerStop() {
		if (GT_Mod.mUniverse != null && !GT_Mod.mUniverse.isRemote) {
			try {
				File dir = GT_Mod.getSaveDirectory();
				if (dir != null) {
					NBTTagCompound data = new NBTTagCompound();
					NBTTagList list = new NBTTagList();
					for (Entry<Integer, Integer> e : GregTech_API.sIDSUList.entrySet()) {
						NBTTagCompound entry = new NBTTagCompound();
						entry.setInteger("Hash", e.getKey());
						entry.setInteger("EU", e.getValue());
						list.appendTag(entry);
					}
					
					data.setTag("Energy", list);
					CompressedStreamTools.writeCompressed(data, new FileOutputStream(new File(dir, "data/GT_IDSU_Energyvalues.dat")));
				}
			} catch (FileNotFoundException e) {
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		
		GregTech_API.sIDSUList.clear();
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
		return "metatileentity.GT_IDSU.tooltip";
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
