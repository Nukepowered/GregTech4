package gregtechmod.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class GT_DummyWorld extends World {
	
	public GT_IteratorRandom mRandom = new GT_IteratorRandom();
	public ItemStack mLastSetBlock = null;
	
	public GT_DummyWorld(ISaveHandler par1iSaveHandler, String par2Str, WorldProvider par3WorldProvider, WorldSettings par4WorldSettings, Profiler par5Profiler) {
		super(par1iSaveHandler, par2Str, par4WorldSettings, par3WorldProvider, par5Profiler, null);
		rand = mRandom;
	}
	
	public GT_DummyWorld() {
		this(
		new ISaveHandler() {
			@Override public void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2) {}
			@Override public void saveWorldInfo(WorldInfo var1) {}
			@Override public WorldInfo loadWorldInfo() {return null;}
			@Override public IPlayerFileData getSaveHandler() {return null;}
			@Override public File getMapFileFromName(String var1) {return null;}
			@Override public IChunkLoader getChunkLoader(WorldProvider var1) {return null;}
			@Override public void flush() {}
			@Override public void checkSessionLock() throws MinecraftException {}
			@Override public String getWorldDirectoryName() {return null;}
		},
		"DUMMY_DIMENSION",
		new WorldProvider() {
			@Override public String getDimensionName() {return "DUMMY_DIMENSION";}
		},
		new WorldSettings(new WorldInfo(new NBTTagCompound())),
		new Profiler()
		);
	}
	
	@Override
	protected IChunkProvider createChunkProvider() {
		return null;
	}
	
	@Override
	public Entity getEntityByID(int aEntityID) {
		return null;
	}
	
	@Override
	public boolean setBlock(int aX, int aY, int aZ, int aID, int aMeta, int aFlags) {
		mLastSetBlock = new ItemStack(aID, 1, aMeta);
		return true;
	}
	
	@Override
    public BiomeGenBase getBiomeGenForCoords(int aX, int aZ) {
		if (aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32) return BiomeGenBase.plains;
        return BiomeGenBase.ocean;
    }
    
	@Override
	public int getFullBlockLightValue(int aX, int aY, int aZ) {
		return 10;
	}
	
	@Override
	public int getBlockId(int aX, int aY, int aZ) {
		if (aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32) return aY == 64?Block.grass.blockID:0;
		return 0;
	}
	
	@Override
	public int getBlockMetadata(int aX, int aY, int aZ) {
		return 0;
	}
	
	@Override
	public boolean canBlockSeeTheSky(int aX, int aY, int aZ) {
		if (aX >= 16 && aZ >= 16 && aX < 32 && aZ < 32) return aY > 64;
		return true;
	}
	
}
