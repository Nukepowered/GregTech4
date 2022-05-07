package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.interfaces.IGT_RecipeAdder;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.recipe.RecipeFactory;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_RecipeAdder implements IGT_RecipeAdder {

	
	@Override
	public RecipeMap<? extends RecipeFactory<?>> fusionRecipes() {
		return RecipeMaps.FUSION_REACTOR;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> centrifugeRecipes() {
		return RecipeMaps.CENTRIFUGE;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> electrolyzerRecipes() {
		return RecipeMaps.ELECTROLYZER;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> chemicalRecipes() {
		return RecipeMaps.CHEMICAL;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> blastRecipes() {
		return RecipeMaps.BLAST_FURNACE;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> canningRecipes() {
		return RecipeMaps.CANNING;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> alloySmelterRecipes() {
		return RecipeMaps.ALLOY_SMELTING;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> circuitAssemblerRecipes() {
		return RecipeMaps.ASSEMBLING;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> hammerRecipes() {
		return RecipeMaps.HAMMER;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> wiremillRecipes() {
		return RecipeMaps.WIREMILL;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> bendingRecipes() {
		return RecipeMaps.BENDING;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> extruderRecipes() {
		return RecipeMaps.EXTRUDING;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> implosionCompressorRecipes() {
		return RecipeMaps.IMPLOSION_COMPRESSOR;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> grinderRecipes() {
		return RecipeMaps.GRINDER;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> distillationrRecipes() {
		return RecipeMaps.DISTILLATION;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> latheRecipes() {
		return RecipeMaps.LATHE;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> cutterRecipes() {
		return RecipeMaps.CUTTING;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> freezerRecipes() {
		return RecipeMaps.VACUUM_FREEZER;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> sawmillRecipes() {
		return RecipeMaps.SAWMILL;
	}
	
	@Override
	public RecipeMap<? extends RecipeFactory<?>> turbineFuels() {
		return RecipeMaps.TURBINE_FUELS;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> dieselFuels() {
		return RecipeMaps.DIESEL_FUELS;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> semifluidFuels() {
		return RecipeMaps.DENSE_FUELS;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> thermalFuels() {
		return RecipeMaps.HOT_FUELS;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> plasmaFuels() {
		return RecipeMaps.PLASMA_FUELS;
	}

	@Override
	public RecipeMap<? extends RecipeFactory<?>> magicFuels() {
		return RecipeMaps.MAGIC_FUELS;
	}

	@Override
	public boolean addJackHammerMinableBlock(Block aBlock, boolean aDiamondOnly) {
		if (aBlock != null && GregTech_API.sPreloadFinished) {
			if (!aDiamondOnly) {
				((GT_Tool_Item) GT_Items.Tool_Jackhammer_Bronze.getItem()).addToBlockList(aBlock);
			}

			if (!aDiamondOnly) {
				((GT_Tool_Item) GT_Items.Tool_Jackhammer_Steel.getItem()).addToBlockList(aBlock);
			}

			((GT_Tool_Item) GT_Items.Tool_Jackhammer_Diamond.getItem()).addToBlockList(aBlock);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addSonictronSound(ItemStack aItemStack, String aSoundName) {
		if (aItemStack != null && aSoundName != null && !aSoundName.equals("")) {
			GT_Mod.mSoundItems.add(aItemStack);
			GT_Mod.mSoundNames.add(aSoundName);
			if (aSoundName.startsWith("note.")) {
				GT_Mod.mSoundCounts.add(25);
			} else {
				GT_Mod.mSoundCounts.add(1);
			}

			return true;
		} else {
			return false;
		}
	}
}
