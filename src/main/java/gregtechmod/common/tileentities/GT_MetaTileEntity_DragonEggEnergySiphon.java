package gregtechmod.common.tileentities;

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
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
import thaumcraft.common.tiles.TileNode;

public class GT_MetaTileEntity_DragonEggEnergySiphon extends MetaTileEntity {
	
	public static int sDragonEggEnergyPerTick = 128;
	public static boolean sAllowMultipleEggs = false, sAllowFlux = true;
	private SoftReference<TileNode> cachedNode = new SoftReference<>(null);
	
	public static GT_MetaTileEntity_DragonEggEnergySiphon mActiveSiphon = null;
	
	public GT_MetaTileEntity_DragonEggEnergySiphon(int aID, String mName) {
		super(aID, mName);
	}
	
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
	        				if (this.findNode()) {
	        					TileNode node = cachedNode.get();
		        				AspectList aspects = new AspectList();
		        				switch (getBaseMetaTileEntity().getRandomNumber(1000)) {
		        				case  0:  aspects.add(Aspect.MECHANISM, 3); break;
		            			case  1:  aspects.add(Aspect.VOID, 1); break;
		                		case  2:  aspects.add(Aspect.ELDRITCH, 2); break;
		                		case  3:  aspects.add(Aspect.EXCHANGE, 1); break;
		                		case  4:  aspects.add(Aspect.MAGIC, 1); break;
		                		case  5:  aspects.add(Aspect.MOTION, 3); break;
		                		case  6:  aspects.add(Aspect.AIR, 2); break;
		                		case  7:  aspects.add(Aspect.EARTH, 2); break;
		                		case  8:  aspects.add(Aspect.FIRE, 2); break;
		                		case  9:  aspects.add(Aspect.WATER, 2); break;
		                		case  10: aspects.add(Aspect.ORDER, 2); break;
		                		case  11: aspects.add(Aspect.ENTROPY, 2); break;
		        				}
		        				
		        				if (!aspects.aspects.isEmpty()) {
		        					Aspect toAdd = aspects.getAspects()[0];
		        					TileEntity tThis = (TileEntity) this.getBaseMetaTileEntity();
		        					node.getAspects().add(aspects);
		        					node.nodeChange();
		        					PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(node.xCoord, node.yCoord, node.zCoord, (byte)(node.xCoord - tThis.xCoord), (byte)(node.yCoord - tThis.yCoord), (byte)(node.zCoord - tThis.zCoord), toAdd.getColor()), 
		        							new NetworkRegistry.TargetPoint(node.getWorldObj().provider.dimensionId, node.xCoord, node.yCoord, node.zCoord, 32.0));
		        				}
	        				}
	        			} catch(Throwable e) {
	        				GT_Log.log.catching(e);
	        			}
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
    
    private boolean findNode() {
    	List<ArrayList<Integer>> nodesCoords = TileNode.locations.entrySet().stream()
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
    		if (tr != null && tr instanceof TileNode) {
    			TileNode node = (TileNode) tr;
    			if (node.isInvalid()) {
    				String key = node.getId();
    				node = null;
    				TileNode.locations.remove(key);
    				System.gc();
    				return false;
    			}
    			
    			this.cachedNode = new SoftReference<>(node);
    			return true;
    		} else {
    			TileNode.locations.remove(String.format("%d:%d:%d:%d", coords.get(0), coords.get(1), coords.get(2), coords.get(3)));
    		}
    	}
    	
    	return false;
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
