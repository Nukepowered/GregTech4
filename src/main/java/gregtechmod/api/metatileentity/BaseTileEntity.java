package gregtechmod.api.metatileentity;

import java.lang.ref.SoftReference;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IHasWorldObjectAndCoords;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * The Functions my old TileEntities and my BaseMetaTileEntities have in common.
 * 
 * Basically everything a TileEntity should have.
 */
public abstract class BaseTileEntity extends TileEntity implements IHasWorldObjectAndCoords {
	/**
	 * If this TileEntity checks for the Chunk to be loaded before returning World based values.
	 * The AdvPump hacks this to false to ensure everything runs properly even when far Chunks are not actively loaded.
	 * But anything else should not cause worfin' Chunks, uhh I mean orphan Chunks.
	 */
	public boolean ignoreUnloadedChunks = true;
	
	/**
	 * Buffers adjacent TileEntities for faster access
	 * 
	 * "this" means that there is no TileEntity, while "null" means that it doesn't know if there is even a TileEntity and still needs to check that if needed.
	 */
	@SuppressWarnings({ "unchecked"})
	private final SoftReference<TileEntity> mBufferedTileEntities[] = new SoftReference[6];
	
    private final void clearNullMarkersFromTileEntityBuffer() {
    	for (int i = 0; i < mBufferedTileEntities.length; i++) {
    		SoftReference<TileEntity> reference = mBufferedTileEntities[i];
    		if (reference != null && reference.get() != null) {
    			if (reference.get() == this || reference.get().isInvalid()) {
    				mBufferedTileEntities[i] = null;
    			}
    		}
    	}
    }

	public void onPlaced() {}
	public void onRemoved() {}
    
	@Override public final World getWorld () {return      worldObj;}
	@Override public final int   getXCoord() {return        xCoord;}
	@Override public final short getYCoord() {return (short)yCoord;}
	@Override public final int   getZCoord() {return        zCoord;}
	@Override public final int   getOffsetX(byte aSide, int aMultiplier) {return         xCoord + ForgeDirection.getOrientation(aSide).offsetX * aMultiplier ;}
    @Override public final short getOffsetY(byte aSide, int aMultiplier) {return (short)(yCoord + ForgeDirection.getOrientation(aSide).offsetY * aMultiplier);}
    @Override public final int   getOffsetZ(byte aSide, int aMultiplier) {return         zCoord + ForgeDirection.getOrientation(aSide).offsetZ * aMultiplier ;}
    @Override public final boolean isServerSide() {return !worldObj.isRemote;}
	@Override public final boolean isClientSide() {return  worldObj.isRemote;}
	@Override public final boolean openGUI(EntityPlayer aPlayer, int aID) {return openGUI(aPlayer, aID, GregTech_API.gregtechmod);}
    @Override public final boolean openGUI(EntityPlayer aPlayer, int aID, Object aMod) {if (aPlayer == null || aMod == null) return false; aPlayer.openGui(aMod, aID, worldObj, xCoord, yCoord, zCoord); return true;}
    @Override public final int getRandomNumber(int aRange) {return worldObj.rand.nextInt(aRange);}
	@Override public final BiomeGenBase getBiome(int aX, int aZ) {return worldObj.getBiomeGenForCoords(aX, aZ);}
	@Override public final BiomeGenBase getBiome() {return getBiome(xCoord, zCoord);}
    @Override public final Block getBlockOffset(int aX, int aY, int aZ) {return getBlock(xCoord+aX, yCoord+aY, zCoord+aZ);}
    @Override public final Block getBlockAtSide(byte aSide) {return getBlockAtSideAndDistance(aSide, 1);}
    @Override public final Block getBlockAtSideAndDistance(byte aSide, int aDistance) {return getBlock(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
    @Override public final byte getMetaIDOffset(int aX, int aY, int aZ) {return getMetaID(xCoord+aX, yCoord+aY, zCoord+aZ);}
    @Override public final byte getMetaIDAtSide(byte aSide) {return getMetaIDAtSideAndDistance(aSide, 1);}
    @Override public final byte getMetaIDAtSideAndDistance(byte aSide, int aDistance) {return getMetaID(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
    @Override public final byte getLightLevelOffset(int aX, int aY, int aZ) {return getLightLevel(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public final byte getLightLevelAtSide(byte aSide) {return getLightLevelAtSideAndDistance(aSide, 1);}
	@Override public final byte getLightLevelAtSideAndDistance(byte aSide, int aDistance) {return getLightLevel(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public final boolean getOpacityOffset(int aX, int aY, int aZ) {return getOpacity(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public final boolean getOpacityAtSide(byte aSide) {return getOpacityAtSideAndDistance(aSide, 1);}
	@Override public final boolean getOpacityAtSideAndDistance(byte aSide, int aDistance) {return getOpacity(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public final boolean getSkyOffset(int aX, int aY, int aZ) {return getSky(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public final boolean getSkyAtSide(byte aSide) {return getSkyAtSideAndDistance(aSide, 1);}
	@Override public final boolean getSkyAtSideAndDistance(byte aSide, int aDistance) {return getSky(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public final boolean getAirOffset(int aX, int aY, int aZ) {return getAir(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public final boolean getAirAtSide(byte aSide) {return getAirAtSideAndDistance(aSide, 1);}
	@Override public final boolean getAirAtSideAndDistance(byte aSide, int aDistance) {return getAir(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public final TileEntity getTileEntityOffset(int aX, int aY, int aZ) {return getTileEntity(xCoord+aX, yCoord+aY, zCoord+aZ);}
    @Override public final TileEntity getTileEntityAtSideAndDistance(byte aSide, int aDistance) {if (aDistance == 1) return getTileEntityAtSide(aSide); return getTileEntity(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
    @Override public final IInventory getIInventory(int aX, int aY, int aZ) {TileEntity tTileEntity = getTileEntity(aX, aY, aZ); if (tTileEntity instanceof IInventory) return (IInventory)tTileEntity; return null;}
    @Override public final IInventory getIInventoryOffset(int aX, int aY, int aZ) {TileEntity tTileEntity = getTileEntityOffset(aX, aY, aZ); if (tTileEntity instanceof IInventory) return (IInventory)tTileEntity; return null;}
    @Override public final IInventory getIInventoryAtSide(byte aSide) {TileEntity tTileEntity = getTileEntityAtSide(aSide); if (tTileEntity instanceof IInventory) return (IInventory)tTileEntity; return null;}
    @Override public final IInventory getIInventoryAtSideAndDistance(byte aSide, int aDistance) {TileEntity tTileEntity = getTileEntityAtSideAndDistance(aSide, aDistance); if (tTileEntity instanceof IInventory) return (IInventory)tTileEntity; return null;}
    @Override public final IFluidHandler getITankContainer(int aX, int aY, int aZ) {TileEntity tTileEntity = getTileEntity(aX, aY, aZ); if (tTileEntity instanceof IFluidHandler) return (IFluidHandler)tTileEntity; return null;}
    @Override public final IFluidHandler getITankContainerOffset(int aX, int aY, int aZ) {TileEntity tTileEntity = getTileEntityOffset(aX, aY, aZ); if (tTileEntity instanceof IFluidHandler) return (IFluidHandler)tTileEntity; return null;}
    @Override public final IFluidHandler getITankContainerAtSide(byte aSide) {TileEntity tTileEntity = getTileEntityAtSide(aSide); if (tTileEntity instanceof IFluidHandler) return (IFluidHandler)tTileEntity; return null;}
    @Override public final IFluidHandler getITankContainerAtSideAndDistance(byte aSide, int aDistance) {TileEntity tTileEntity = getTileEntityAtSideAndDistance(aSide, aDistance); if (tTileEntity instanceof IFluidHandler) return (IFluidHandler)tTileEntity; return null;}
    @Override public final IGregTechTileEntity getIGregTechTileEntity(int aX, int aY, int aZ) {TileEntity tTileEntity = getTileEntity(aX, aY, aZ); if (tTileEntity instanceof IGregTechTileEntity) return (IGregTechTileEntity)tTileEntity; return null;}
    @Override public final IGregTechTileEntity getIGregTechTileEntityOffset(int aX, int aY, int aZ) {TileEntity tTileEntity = getTileEntityOffset(aX, aY, aZ); if (tTileEntity instanceof IGregTechTileEntity) return (IGregTechTileEntity)tTileEntity; return null;}
    @Override public final IGregTechTileEntity getIGregTechTileEntityAtSide(byte aSide) {TileEntity tTileEntity = getTileEntityAtSide(aSide); if (tTileEntity instanceof IGregTechTileEntity) return (IGregTechTileEntity)tTileEntity; return null;}
    @Override public final IGregTechTileEntity getIGregTechTileEntityAtSideAndDistance(byte aSide, int aDistance) {TileEntity tTileEntity = getTileEntityAtSideAndDistance(aSide, aDistance); if (tTileEntity instanceof IGregTechTileEntity) return (IGregTechTileEntity)tTileEntity; return null;}
    
    @Override
    public final Block getBlock(int aX, int aY, int aZ) {
    	if (ignoreUnloadedChunks && (aX >> 4 != xCoord >> 4 || aZ >> 4 != zCoord >> 4) && !worldObj.blockExists(aX, aY, aZ)) return Blocks.air;
    	return worldObj.getBlock(aX, aY, aZ);
    }
    
    @Override
    public final byte getMetaID(int aX, int aY, int aZ) {
    	if (ignoreUnloadedChunks && (aX >> 4 != xCoord >> 4 || aZ >> 4 != zCoord >> 4) && !worldObj.blockExists(aX, aY, aZ)) return 0;
    	return (byte)worldObj.getBlockMetadata(aX, aY, aZ);
    }
    
    @Override
    public final byte getLightLevel(int aX, int aY, int aZ) {
    	if (ignoreUnloadedChunks && (aX >> 4 != xCoord >> 4 || aZ >> 4 != zCoord >> 4) && !worldObj.blockExists(aX, aY, aZ)) return 0;
    	return (byte)(worldObj.getLightBrightness(aX, aY, aZ)*15);
    }
    
    @Override
    public final boolean getSky(int aX, int aY, int aZ) {
    	if (ignoreUnloadedChunks && (aX >> 4 != xCoord >> 4 || aZ >> 4 != zCoord >> 4) && !worldObj.blockExists(aX, aY, aZ)) return true;
    	return worldObj.canBlockSeeTheSky(aX, aY, aZ);
    }
    
    @Override
    public final boolean getOpacity(int aX, int aY, int aZ) {
    	if (ignoreUnloadedChunks && (aX >> 4 != xCoord >> 4 || aZ >> 4 != zCoord >> 4) && !worldObj.blockExists(aX, aY, aZ)) return false;
    	return GT_Utility.isOpaqueBlock(worldObj, aX, aY, aZ);
    }
    
    @Override
    public final boolean getAir(int aX, int aY, int aZ) {
    	if (ignoreUnloadedChunks && (aX >> 4 != xCoord >> 4 || aZ >> 4 != zCoord >> 4) && !worldObj.blockExists(aX, aY, aZ)) return true;
    	return worldObj.isAirBlock(aX, aY, aZ);
    }
    
    @Override
    public final TileEntity getTileEntity(int aX, int aY, int aZ) {
    	if (ignoreUnloadedChunks && (aX >> 4 != xCoord >> 4 || aZ >> 4 != zCoord >> 4) && !worldObj.blockExists(aX, aY, aZ)) return null;
    	return worldObj.getTileEntity(aX, aY, aZ);
    }
    
    @Override
    public final TileEntity getTileEntityAtSide(byte aSide) {
    	if (aSide >= 0 && aSide < 6) {
    		SoftReference<TileEntity> reference = mBufferedTileEntities[aSide];
    		if (reference != null && reference.get() != null && !reference.get().isInvalid()) {
    			if (reference.get() == this || reference.isEnqueued()) this.clearNullMarkersFromTileEntityBuffer();
    			return reference.get();
    		} else {
    			TileEntity tile = getTileEntity(getOffsetX(aSide, 1), getOffsetY(aSide, 1), getOffsetZ(aSide, 1));
    			mBufferedTileEntities[aSide] = new SoftReference<TileEntity>(tile);
    			return tile;
    		}
    	}
    	
    	return null;
    }
    
    @Override
    public void validate() {
    	clearNullMarkersFromTileEntityBuffer();
    	super.validate();
    }
    
    @Override
    public void invalidate() {
    	clearNullMarkersFromTileEntityBuffer();
    	super.invalidate();
    }
    
    @Override
    public void onChunkUnload() {
    	clearNullMarkersFromTileEntityBuffer();
    	super.onChunkUnload();
    }
    
    public final void onAdjacentBlockChange(int aX, int aY, int aZ) {
    	clearNullMarkersFromTileEntityBuffer();
    }
    
	@Override
	public final void sendBlockEvent(byte aID, byte aValue) {
		try {
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockOffset(0, 0, 0), aID, aValue);
		} catch(Throwable e) {
			if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
		}
	}
}
