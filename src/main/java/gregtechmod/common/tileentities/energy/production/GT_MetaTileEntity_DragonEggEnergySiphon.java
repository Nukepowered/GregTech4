package gregtechmod.common.tileentities.energy.production;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cpw.mods.fml.common.network.NetworkRegistry;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_Log;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_MetaTileEntity_DragonEggEnergySiphon extends MetaTileEntity {
	public static int sDragonEggEnergyPerTick = 128;
	public static boolean sAllowMultipleEggs = false, sAllowFlux = true;
	private SoftReference<Object> cachedNode = new SoftReference<>(null);
	
	public static SoftReference<GT_MetaTileEntity_DragonEggEnergySiphon> mActiveSiphon = new SoftReference<>(null);
	
	public GT_MetaTileEntity_DragonEggEnergySiphon(int aID, String mName) {
		super(aID, mName);
	}
	
	public GT_MetaTileEntity_DragonEggEnergySiphon() {}
	
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
    	sDragonEggEnergyPerTick	= aConfig.get(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.EnergyPerTick", 1024);
    	sAllowMultipleEggs		= aConfig.get(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.AllowMultipleEggs", false);
    	sAllowFlux				= aConfig.get(GT_ConfigCategories.machineconfig, "DragonEggEnergySiphon.OutputFlux", true);
	}
	
	@Override
	public void onServerStart() {
		mActiveSiphon.clear();
	}
	
	@Override
	public void onServerStop() {
		mActiveSiphon.clear();
    }
	
    @Override
    public void onFirstTick() {
    	mActiveSiphon.clear();
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
    				if (sAllowFlux && GT_MetaTileEntity_MagicEnergyAbsorber.THAUMCRAFT_LOADED) {
	        			try {
	        				if (this.findNode()) {
	        					thaumcraft.common.tiles.TileNode node = (thaumcraft.common.tiles.TileNode) cachedNode.get();
	        					thaumcraft.api.aspects.AspectList aspects = new thaumcraft.api.aspects.AspectList();
		        				switch (getBaseMetaTileEntity().getRandomNumber(5000)) {
		        				case  0:  aspects.add(thaumcraft.api.aspects.Aspect.MECHANISM, 3); break;
		            			case  1:  aspects.add(thaumcraft.api.aspects.Aspect.VOID, 1); break;
		                		case  2:  aspects.add(thaumcraft.api.aspects.Aspect.ELDRITCH, 2); break;
		                		case  3:  aspects.add(thaumcraft.api.aspects.Aspect.EXCHANGE, 1); break;
		                		case  4:  aspects.add(thaumcraft.api.aspects.Aspect.MAGIC, 1); break;
		                		case  5:  aspects.add(thaumcraft.api.aspects.Aspect.MOTION, 3); break;
		                		case  6:  aspects.add(thaumcraft.api.aspects.Aspect.AIR, 2); break;
		                		case  7:  aspects.add(thaumcraft.api.aspects.Aspect.EARTH, 2); break;
		                		case  8:  aspects.add(thaumcraft.api.aspects.Aspect.FIRE, 2); break;
		                		case  9:  aspects.add(thaumcraft.api.aspects.Aspect.WATER, 2); break;
		                		case  10: aspects.add(thaumcraft.api.aspects.Aspect.ORDER, 2); break;
		                		case  11: aspects.add(thaumcraft.api.aspects.Aspect.ENTROPY, 2); break;
		        				}
		        				
		        				if (!aspects.aspects.isEmpty()) {
		        					thaumcraft.api.aspects.Aspect toAdd = aspects.getAspects()[0];
		        					TileEntity tThis = (TileEntity) this.getBaseMetaTileEntity();
		        					node.getAspects().add(aspects);
		        					node.nodeChange();
		        					thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround(new thaumcraft.common.lib.network.fx.PacketFXEssentiaSource(node.xCoord, node.yCoord, node.zCoord, (byte)(node.xCoord - tThis.xCoord), (byte)(node.yCoord - tThis.yCoord), (byte)(node.zCoord - tThis.zCoord), toAdd.getColor()), 
		        							new NetworkRegistry.TargetPoint(node.getWorldObj().provider.dimensionId, node.xCoord, node.yCoord, node.zCoord, 32.0));
		        				}
	        				}
	        			} catch(Throwable e) {
	        				GT_Log.log.catching(e);
	        			}
    				}
    			}
    			if (mActiveSiphon.get() != this && !sAllowMultipleEggs)
    				if (mActiveSiphon.get() == null || mActiveSiphon.get().getBaseMetaTileEntity() == null || mActiveSiphon.get().getBaseMetaTileEntity().isInvalidTileEntity() || !mActiveSiphon.get().hasEgg())
    					mActiveSiphon  = new SoftReference<>(this);
    				else
    					getBaseMetaTileEntity().doExplosion(Integer.MAX_VALUE);
    		} else {
    			if (mActiveSiphon.get() == this) {
    				mActiveSiphon.clear();
    			}
    		}
    	}
    }
    
    private boolean findNode() {
    	List<ArrayList<Integer>> nodesCoords = thaumcraft.common.tiles.TileNode.locations.entrySet().stream()
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
		TileEntity tThis = (TileEntity) this.getBaseMetaTileEntity();
		World tWorld = tThis.getWorldObj();
		
		List<Integer> coords = null;
		double dist = Double.MAX_VALUE;
		for (ArrayList<Integer> nodeEntry : nodesCoords) {
			if (nodeEntry.get(0) == tWorld.provider.dimensionId) {
				double deltaX = tThis.xCoord + 0.5 - nodeEntry.get(1);
		        double deltaY = tThis.yCoord + 0.5 - nodeEntry.get(2);
		        double deltaZ = tThis.zCoord + 0.5 - nodeEntry.get(3);
		        double temp = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
		        if (temp < dist) {
		        	dist = temp;
		        	coords = nodeEntry;
		        }
			}
		}
		
    	if (coords != null && !coords.isEmpty()) {
    		TileEntity tr = tWorld.getTileEntity(coords.get(1), coords.get(2), coords.get(3));
    		if (tr != null && tr instanceof thaumcraft.common.tiles.TileNode) {
    			thaumcraft.common.tiles.TileNode node = (thaumcraft.common.tiles.TileNode) tr;
    			if (node.isInvalid()) {
    				String key = node.getId();
    				node = null;
    				thaumcraft.common.tiles.TileNode.locations.remove(key);
    				System.gc();
    				return false;
    			}
    			
    			this.cachedNode = new SoftReference<>(node);
    			return true;
    		} else {
    			thaumcraft.common.tiles.TileNode.locations.remove(String.format("%d:%d:%d:%d", coords.get(0), coords.get(1), coords.get(2), coords.get(3)));
    		}
    	}
    	
    	return false;
    }
    
	@Override
	public boolean isGivingInformation() {
		return true;
	}
	
	@Override
	public String getDescription() {
		return "metatileentity.GT_DragonEggEnergySiphon.tooltip";
	}
	
    @Override
	public void inValidate() {
		if (mActiveSiphon.get() == this) {
			mActiveSiphon.clear();
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
