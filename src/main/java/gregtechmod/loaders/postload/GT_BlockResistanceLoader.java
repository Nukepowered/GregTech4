package gregtechmod.loaders.postload;

import gregtechmod.GT_Mod;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.MinecraftForge;

public class GT_BlockResistanceLoader implements Runnable {
	@Override
	public void run() {
    	Block.brick.setResistance(20.0F);
    	Block.hardenedClay.setResistance(15.0F);
    	Block.stainedClay.setResistance(15.0F);
    	Block.blockIron.setResistance(30.0F);
    	Block.blockDiamond.setResistance(60.0F);
    	
    	if (GT_Mod.sHardRock) {
    		Block.stone.blockHardness = 16.0F;
    		Block.brick.blockHardness = 32.0F;
    		Block.hardenedClay.blockHardness = 32.0F;
    		Block.stainedClay.blockHardness = 32.0F;
    		Block.cobblestone.blockHardness = 12.0F;
    		Block.stoneBrick.blockHardness = 24.0F;
    	}
    	
    	MinecraftForge.setBlockHarvestLevel(Block.bed, "axe", 0);
    	MinecraftForge.setBlockHarvestLevel(Block.hay, "axe", 0);
    	MinecraftForge.setBlockHarvestLevel(Block.tnt, "pickaxe", 0);
    	MinecraftForge.setBlockHarvestLevel(Block.sponge, "axe", 0);
    	MinecraftForge.setBlockHarvestLevel(Block.silverfish, "pickaxe", 0);
    	
    	GT_Utility.callMethod(Material.tnt, "func_85158_p", true, false, false);
    	GT_Utility.callMethod(Material.tnt, "setAdventureModeExempt", true, false, false);
    	
    	try {
	    	ItemAxe.blocksEffectiveAgainst[0] = Block.bed;
	    	ItemAxe.blocksEffectiveAgainst[1] = Block.hay;
	    	ItemAxe.blocksEffectiveAgainst[2] = Block.sponge;
	    	
	    	ItemPickaxe.blocksEffectiveAgainst[0] = Block.silverfish;
	    	ItemPickaxe.blocksEffectiveAgainst[3] = Block.tnt;
    	} catch(Throwable e) {}
	}
}
