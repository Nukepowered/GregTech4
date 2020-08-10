package gregtechmod.api.world;

import java.util.Collection;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class GT_Worldgen_Ore_Normal extends GT_Worldgen_Ore {
	public GT_Worldgen_Ore_Normal(String aName, boolean aDefault, Block aBlock, int aBlockMeta, int aDimensionType, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid) {
		super(aName, aDefault, aBlock, aBlockMeta, aDimensionType, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid);
	}
	
	@Override
	public boolean executeWorldgen(World aWorld, Random aRandom, String aBiome, int aDimensionType, int aChunkX, int aChunkZ, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
		if (aDimensionType == mDimensionType && (mBiomeList.isEmpty() || mBiomeList.contains(aBiome)) && (mProbability <= 1 || aRandom.nextInt(mProbability) == 0)) {
			for (int i = 0; i < mAmount; i++) {
				int tX = aChunkX + aRandom.nextInt(16);
				int tY = mMinY + aRandom.nextInt(mMaxY - mMinY);
				int tZ = aChunkZ + aRandom.nextInt(16);
				if (mAllowToGenerateinVoid || !aWorld.isAirBlock(tX, tY, tZ)) {
					float var6 = aRandom.nextFloat() * (float)Math.PI;
			        double devXPos = (double)((float)(tX + 8) + MathHelper.sin(var6) * (float)mSize / 8.0F);
			        double devXNeg = (double)((float)(tX + 8) - MathHelper.sin(var6) * (float)mSize / 8.0F);
			        double devZPos = (double)((float)(tZ + 8) + MathHelper.cos(var6) * (float)mSize / 8.0F);
			        double devZNeg = (double)((float)(tZ + 8) - MathHelper.cos(var6) * (float)mSize / 8.0F);
			        double devYMax = (double)(tY + aRandom.nextInt(3) - 2);
			        double devYMin = (double)(tY + aRandom.nextInt(3) - 2);
			        
			        for (int j = 0; j <= mSize; ++j) {
			            double currX = devXPos + (devXNeg - devXPos) * (double)j / (double)mSize;
			            double currY = devYMax + (devYMin - devYMax) * (double)j / (double)mSize;
			            double currZ = devZPos + (devZNeg - devZPos) * (double)j / (double)mSize;
			            double randMult = aRandom.nextDouble() * (double)mSize / 16.0D;
			            double var28 = (double)(MathHelper.sin((float)j * (float)Math.PI / (float)mSize) + 1.0F) * randMult + 1.0D;
			            double var30 = (double)(MathHelper.sin((float)j * (float)Math.PI / (float)mSize) + 1.0F) * randMult + 1.0D;
			            int var32 = MathHelper.floor_double(currX - var28 / 2.0D);
			            int var33 = MathHelper.floor_double(currY - var30 / 2.0D);
			            int var34 = MathHelper.floor_double(currZ - var28 / 2.0D);
			            int var35 = MathHelper.floor_double(currX + var28 / 2.0D);
			            int var36 = MathHelper.floor_double(currY + var30 / 2.0D);
			            int var37 = MathHelper.floor_double(currZ + var28 / 2.0D);
			            
			            for (int o = var32; o <= var35; ++o) {
			                double var39 = ((double)o + 0.5D - currX) / (var28 / 2.0D);
			                if (var39 * var39 < 1.0D) {
			                    for (int p = var33; p <= var36; ++p) {
			                        double var42 = ((double)p + 0.5D - currY) / (var30 / 2.0D);
			                        if (var39 * var39 + var42 * var42 < 1.0D) {
			                            for (int u = var34; u <= var37; ++u) {
			                                double var45 = ((double)u + 0.5D - currZ) / (var28 / 2.0D);
			                                Block block = aWorld.getBlock(o, p, u);
			                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D) {
			                                	if ((mAllowToGenerateinVoid && aWorld.isAirBlock(o, p, u)) || 
			                                			((block.isReplaceableOreGen(aWorld, o, p, u, Blocks.stone) || block.isReplaceableOreGen(aWorld, o, p, u, Blocks.end_stone) || block.isReplaceableOreGen(aWorld, o, p, u, Blocks.netherrack)))) {
				                                    aWorld.setBlock(o, p, u, mBlock, mBlockMeta, 0);
				                                }
			                                }
			                            }
			                        }
			                    }
			                }
			            }
			        }
				}
	        }
			return true;
		}
        return false;
	}
}