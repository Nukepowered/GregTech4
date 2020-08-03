package gregtechmod.loaders.postload;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.GT_DummyWorld;
import gregtechmod.common.items.GT_MetaItem_Cell;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GT_SeedFlowerIterator implements Runnable {
	@Override
	public void run() {
        GT_Log.out.println("GT_Mod: Iterating through the Seed-List of ForgeHooks, with a brilliant and 100% Reflection-free Method, to add Recipes for gaining Seed Oil from Seeds.");
		boolean temp = false;
        try {
        	GT_DummyWorld tWorld = (GT_DummyWorld)GregTech_API.sDummyWorld;
			FluidStack tLiquid = FluidRegistry.getFluidStack("seedoil", 15);
			
			while (tWorld.mRandom.mIterationStep > 0) {
				ItemStack tSeed = ForgeHooks.getGrassSeed(tWorld);
				if (tSeed != null) {
					if (tLiquid != null && GT_ModHandler.addSqueezerRecipe(tSeed, tLiquid, 15)) {
						temp = true;
					} else {
						tSeed = GT_Utility.copy(tSeed);
						tSeed.stackSize = 64;
						GregTech_API.sRecipeAdder.addCentrifugeRecipe(tSeed, 1, GT_MetaItem_Cell.instance.getStack(24, 1), null, null, null, 200);
					}
				}
			}
			
			GT_Log.out.println("GT_Mod: Iterating through the Grass-Flower-List of ForgeHooks, with a brilliant and 100% Reflection-free Method, to add Extractor Recipes for gaining more Dye from Flowers and also Compression Recipes for Plantballs.");
			tWorld.mRandom.mIterationStep = Integer.MAX_VALUE;
			while (tWorld.mRandom.mIterationStep > 0) {
				try {
					ForgeHooks.plantGrass(tWorld, 24, 65, 24);
					if (tWorld.mLastSetBlock != null) {
						ItemStack tColor = GT_ModHandler.getRecipeOutput(new ItemStack[] {tWorld.mLastSetBlock});
						if (GT_OreDictUnificator.isItemStackDye(tColor)) {
							tColor = GT_Utility.copy(tColor);
							tColor.stackSize++;
							GT_ModHandler.addExtractionRecipe(tWorld.mLastSetBlock, tColor);
						}
						GT_ModHandler.addCompressionRecipe(GT_Utility.copy(8, tWorld.mLastSetBlock), GT_ModHandler.getIC2Item("compressedPlantBall", 1));
					}
				} catch(Throwable e) {
					GT_Log.err.println("Minor Bug: Wasn't able to simulate the planting of a Flower with Bonemeal, to add Extractor Recipe for Dye:\n");
					e.printStackTrace(GT_Log.err);
				}
			}
        } catch (Throwable e) {
        	GT_Log.out.println("GT_Mod: failed to iterate somehow, maybe it's your Forge Version causing it. But it's not that important\n");
        	e.printStackTrace(GT_Log.err);
        }
        
		if (temp) {
			GT_Log.out.println("GT_Mod: Forestry was properly loaded, so the Seed Recipes got added to the Squeezer.");
		} else {
			GT_Log.out.println("GT_Mod: Forestry was NOT loaded, so the Recipes got added to the Industrial Centrifuge.");
			GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Item.melonSeeds, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(24, 1), null, null, null, 200);
			GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Item.pumpkinSeeds, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(24, 1), null, null, null, 200);
			GregTech_API.sRecipeAdder.addCentrifugeRecipe(new ItemStack(Item.seeds, 64, 0), 1, GT_MetaItem_Cell.instance.getStack(24, 1), null, null, null, 200);
	    }
		
        GT_ModHandler.addExtractionRecipe(new ItemStack(Block.plantRed, 1, 0), new ItemStack(Item.dyePowder, 3, 1));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Block.plantYellow, 1, 0), new ItemStack(Item.dyePowder, 3, 11));
	}
}
