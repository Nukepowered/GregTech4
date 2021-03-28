package gregtechmod.loaders.postload;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import gregtechmod.common.GT_DummyWorld;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GT_SeedFlowerIterator implements Runnable {
	@Override
	public void run() {
        GT_Log.log.info("Iterating through the Seed-List of ForgeHooks, with a brilliant and 100% Reflection-free Method, to add Recipes for gaining Seed Oil from Seeds.");
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
						RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(200).setShaped(true).input(tSeed).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SeedOil)).buildAndRegister();
					}
				}
			}
			
			GT_Log.log.info("Iterating through the Grass-Flower-List of ForgeHooks, with a brilliant and 100% Reflection-free Method, to add Extractor Recipes for gaining more Dye from Flowers and also Compression Recipes for Plantballs.");
			tWorld.mRandom.mIterationStep = Integer.MAX_VALUE;
			while (tWorld.mRandom.mIterationStep > 1) {
				try {
//					ForgeHooks.plantGrass(tWorld, 24, 65, 24); // TODO; do not planting grass
					ItemDye.applyBonemeal(new ItemStack(Items.dye, 64, 0), tWorld, 24, 64, 24, new EntityPlayer(tWorld, new GameProfile(UUID.randomUUID(), "ILYAPIDOR")) {
						@Override
						public ChunkCoordinates getPlayerCoordinates() {return null;}
						@Override
						public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {return false;}
						@Override
						public void addChatMessage(IChatComponent p_145747_1_) {}
					});
					if (tWorld.mLastSetBlock != null) {
						ItemStack tColor = GT_ModHandler.getRecipeOutput(new ItemStack[] {tWorld.mLastSetBlock});
						if (GT_OreDictUnificator.isItemStackDye(tColor)) {
							tColor = GT_Utility.copy(tColor);
							tColor.stackSize++;
							GT_ModHandler.addExtractionRecipe(tWorld.mLastSetBlock, tColor);
						}
						GT_ModHandler.addCompressionRecipe(GT_Utility.copy(8, tWorld.mLastSetBlock), GT_ModHandler.getIC2Item("plantBall", 1));
					}
				} catch(Throwable e) {
					GT_Log.log.warn("Minor Bug: Wasn't able to simulate the planting of a Flower with Bonemeal, to add Extractor Recipe for Dye:\n");
					GT_Log.log.catching(e);
				}
			}
        } catch (Throwable e) {
        	GT_Log.log.warn("failed to iterate somehow, maybe it's your Forge Version causing it. But it's not that important\n");
        	GT_Log.log.catching(e);
        }
        
		if (temp) {
			GT_Log.log.info("Forestry was properly loaded, so the Seed Recipes got added to the Squeezer.");
		} else {
			GT_Log.log.info("Forestry was NOT loaded, so the Recipes got added to the Industrial Centrifuge.");
			RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(200).setShaped(true).input(new ItemStack(Items.melon_seeds, 64, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SeedOil)).buildAndRegister();
			RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(200).setShaped(true).input(new ItemStack(Items.pumpkin_seeds, 64, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SeedOil)).buildAndRegister();
			RecipeMaps.CENTRIFUGE.factory().EUt(5).duration(200).setShaped(true).input(new ItemStack(Items.wheat_seeds, 64, 0)).input(GT_Items.Cell_Empty.get(1)).output(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.SeedOil)).buildAndRegister();
	    }
		
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.red_flower, 1, 0), new ItemStack(Items.dye, 3, 1));
        GT_ModHandler.addExtractionRecipe(new ItemStack(Blocks.yellow_flower, 1, 0), new ItemStack(Items.dye, 3, 11));
	}
}
