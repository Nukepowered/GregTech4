package gregtechmod.common;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.world.GT_Worldgen;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.common.MinecraftForge;

public class GT_Worldgenerator implements IWorldGenerator {
	
	public static boolean sAsteroids = true;
	public static boolean sGeneratedOres[] = new boolean[] {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
	
	public GT_Worldgenerator() {
		MinecraftForge.EVENT_BUS.register(this);
		GameRegistry.registerWorldGenerator(this,1);
	}
	
	@SubscribeEvent
	public void receiveWorldgenEvent(net.minecraftforge.event.terraingen.PopulateChunkEvent.Post aEvent) {
		generate(aEvent.rand, aEvent.chunkX, aEvent.chunkZ, aEvent.world, aEvent.chunkProvider, aEvent.chunkProvider);
	}
	
	@Override
    public void generate(Random aRandom, int aX, int aZ, World aWorld, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
		aRandom = new Random(aRandom.nextInt());
		aX *= 16;
		aZ *= 16;
		BiomeGenBase tBiome = aWorld.getBiomeGenForCoords(aX+8, aZ+8);
    	int tDimensionType = 0;
    	
    	if (tBiome.equals(BiomeGenBase.hell) || aWorld.provider.dimensionId == -1 || aChunkGenerator instanceof ChunkProviderHell) {
    		tDimensionType = -1;
    	} else if (tBiome.equals(BiomeGenBase.sky) || aWorld.provider.dimensionId == +1 || aChunkGenerator instanceof ChunkProviderEnd) {
    		generateEnd(aWorld, aRandom, aX, aZ, aChunkGenerator, aChunkProvider);
    		tDimensionType = +1;
    	} else {
    		tDimensionType =  0;
    	}
    	
    	for (GT_Worldgen tWorldGen : GregTech_API.sWorldgenList) {
    		try {
    			tWorldGen.executeWorldgen(aWorld, aRandom, tBiome.biomeName, tDimensionType, aX, aZ, aChunkGenerator, aChunkProvider);
    		} catch (Throwable e) {
    			GT_Log.log.catching(e);
    		}
    	}
    	
		Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
    	if (tChunk != null) tChunk.isModified = true;
    }
    
    private void generateEnd(World aWorld, Random aRandom, int aX, int aZ, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    	if (sAsteroids && aRandom.nextInt(100) == 0) {
    		int tPosY = 10 + aRandom.nextInt(237);
    		if (aRandom.nextInt(25) == 0) {
    			new GT_MinableOreGenerator(Blocks.end_stone, 0, 100+aRandom.nextInt(101), true, Blocks.air).generate(aWorld, aRandom, aX + aRandom.nextInt(16), tPosY, aZ + aRandom.nextInt(16));
    			if (sGeneratedOres[9])
            		for (int i = 0; i < 5; i++)
            			new GT_MinableOreGenerator(GregTech_API.sBlockList[2], 9,16, true, Blocks.end_stone).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
            	if (sGeneratedOres[10])
            		for (int i = 0; i < 2; i++)
            			new GT_MinableOreGenerator(GregTech_API.sBlockList[2],10, 4, true, Blocks.end_stone).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
             	if (sGeneratedOres[11])
            		for (int i = 0; i < 7; i++)
            			new GT_MinableOreGenerator(GregTech_API.sBlockList[2],11, 8, true, Blocks.end_stone).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
             	if (sGeneratedOres[12])
            		for (int i = 0; i <12; i++)
            			new GT_MinableOreGenerator(GregTech_API.sBlockList[2],12,16, true, Blocks.end_stone).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41), aZ - 8 + aRandom.nextInt(24));
    		}
    		for (int i = 0; i < 5; i++)
    			new GT_MinableOreGenerator(Blocks.end_stone, 0, 30+aRandom.nextInt(31), true, Blocks.air).generate(aWorld, aRandom, aX + aRandom.nextInt(16), tPosY+aRandom.nextInt(51)-25, aZ + aRandom.nextInt(16));
    		if (sGeneratedOres[9])
        		for (int i = 0; i < 5; i++)
        			new GT_MinableOreGenerator(GregTech_API.sBlockList[2], 9,12, true, Blocks.end_stone).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41)-20, aZ - 8 + aRandom.nextInt(24));
        	if (sGeneratedOres[10])
        		for (int i = 0; i < 1; i++)
        			new GT_MinableOreGenerator(GregTech_API.sBlockList[2],10, 4, true, Blocks.end_stone).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41)-20, aZ - 8 + aRandom.nextInt(24));
         	if (sGeneratedOres[11])
        		for (int i = 0; i < 3; i++)
        			new GT_MinableOreGenerator(GregTech_API.sBlockList[2],11, 8, true, Blocks.end_stone).generate(aWorld, aRandom, aX - 8 + aRandom.nextInt(24), tPosY+aRandom.nextInt(41)-20, aZ - 8 + aRandom.nextInt(24));
    	}
    	if (sGeneratedOres[9])
    		for (int i = 0; i < 4; i++)
    			new GT_MinableOreGenerator(GregTech_API.sBlockList[2], 9,16, false, Blocks.end_stone).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
    	if (sGeneratedOres[10])
    		for (int i = 0; i < 1; i++)
    			new GT_MinableOreGenerator(GregTech_API.sBlockList[2],10, 4, false, Blocks.end_stone).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
    	if (sGeneratedOres[11])
    		for (int i = 0; i < 5; i++)
    			new GT_MinableOreGenerator(GregTech_API.sBlockList[2],11, 8, false, Blocks.end_stone).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
    	if (sGeneratedOres[12])
    		for (int i = 0; i < 8; i++)
    			new GT_MinableOreGenerator(GregTech_API.sBlockList[2],12,16, false, Blocks.end_stone).generate(aWorld, aRandom, aX + aRandom.nextInt(16), aRandom.nextInt(128), aZ + aRandom.nextInt(16));
    }
}