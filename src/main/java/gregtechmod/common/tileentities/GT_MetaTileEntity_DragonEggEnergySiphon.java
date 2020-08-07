package gregtechmod.common.tileentities;

import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.common.aura.AuraManager;

public class GT_MetaTileEntity_DragonEggEnergySiphon extends MetaTileEntity {
	
	public static int sDragonEggEnergyPerTick = 128;
	public static boolean sAllowMultipleEggs = false, sAllowFlux = true;
	
	public static GT_MetaTileEntity_DragonEggEnergySiphon mActiveSiphon = null;
	
	public GT_MetaTileEntity_DragonEggEnergySiphon(int aID, String mName) {
		super(aID, mName);
	}
	// TODO thaumcraft kekw
	public GT_MetaTileEntity_DragonEggEnergySiphon() {
		
	}
	
	@Override public boolean unbreakable()							{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return aSide != 1;}
	@Override public int maxEUOutput()								{return sDragonEggEnergyPerTick;}
	@Override public int getInvSize()								{return 0;}
	@Override public int maxEUStore()								{return getMinimumStoredEU()+sDragonEggEnergyPerTick*2;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_DragonEggEnergySiphon();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {}
	
	@Override
	public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ) {
		return false;
	}
	
	@Override
	public void onConfigLoad(GT_Config aConfig) {
    	sDragonEggEnergyPerTick	= aConfig.addAdvConfig(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.EnergyPerTick", 1024);
    	sAllowMultipleEggs		= aConfig.addAdvConfig(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.AllowMultipleEggs", false);
    	sAllowFlux				= aConfig.addAdvConfig(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.OutputFlux", true);
	}
	
	@Override
	public void onServerStart() {
    	mActiveSiphon = null;
	}
	
	@Override
	public void onServerStop() {
    	mActiveSiphon = null;
    }
	
    @Override
    public void onFirstTick() {
    	mActiveSiphon = null;
    }
    
	@Override
	public boolean allowCoverOnSide(byte aSide, int aCoverID) {
		return aSide != 1;
	}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide()) {
			getBaseMetaTileEntity().setActive(false);
    		if (getBaseMetaTileEntity().isAllowedToWork() && hasEgg()) {
    			getBaseMetaTileEntity().setActive(true);
    			if (getBaseMetaTileEntity().increaseStoredEnergyUnits(sDragonEggEnergyPerTick, false)) {
    				if (sAllowFlux) {
	        			try {
	        				ObjectTags tTags = new ObjectTags();
	        				switch (getBaseMetaTileEntity().getRandomNumber(1000)) {
	        				case  0: tTags.add(EnumTag.MECHANISM, 3); break;
	        				case  1: tTags.add(EnumTag.CONTROL, 1); break;
	            			case  2: tTags.add(EnumTag.VOID, 1); break;
	                		case  3: tTags.add(EnumTag.FLUX, 2); break;
	                		case  4: tTags.add(EnumTag.ELDRITCH, 2); break;
	                		case  5: tTags.add(EnumTag.EXCHANGE, 1); break;
	                		case  6: tTags.add(EnumTag.MAGIC, 1); break;
	                		case  7: tTags.add(EnumTag.POWER, 1); break;
	                		case  8: tTags.add(EnumTag.MOTION, 3); break;
	                		case  9: tTags.add(EnumTag.SPIRIT, 5); break;
	                		default: tTags = null; break;
	        				}
	        				if (tTags != null) AuraManager.addFluxToClosest(getBaseMetaTileEntity().getWorld(), getBaseMetaTileEntity().getXCoord(), getBaseMetaTileEntity().getYCoord(), getBaseMetaTileEntity().getZCoord(), tTags);
	        			} catch(Throwable e) {}
    				}
    			}
    			if (mActiveSiphon != this && !sAllowMultipleEggs)
    				if (mActiveSiphon == null || mActiveSiphon.getBaseMetaTileEntity() == null || mActiveSiphon.getBaseMetaTileEntity().isInvalidTileEntity() || !mActiveSiphon.hasEgg())
    					mActiveSiphon = this;
    				else
    					getBaseMetaTileEntity().doExplosion(Integer.MAX_VALUE);
    		} else {
    			if (mActiveSiphon == this) {
    				mActiveSiphon = null;
    			}
    		}
    	}
    }
    
	@Override
	public String getMainInfo() {
		return getBaseMetaTileEntity().isActive()?"Active":"Inactive";
	}
	@Override
	public String getSecondaryInfo() {
		return "Output: " + sDragonEggEnergyPerTick + " EU/t";
	}
	@Override
	public String getTertiaryInfo() {
		return "";
	}
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	@Override
	public String getDescription() {
		return "Harness the teleportation Power of the Dragon Egg!";
	}
	
    @Override
	public void inValidate() {
		if (mActiveSiphon == this) {
			mActiveSiphon = null;
		}
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 1) return 89;
    	return aActive?87:83;
	}
	
	public boolean hasEgg() {
		return Blocks.dragon_egg == getBaseMetaTileEntity().getBlockOffset(0, 1, 0);
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
