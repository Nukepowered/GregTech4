package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IGT_RecipeAdder;
import gregtechmod.api.items.GT_Tool_Item;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.common.recipe.RecipeMaps;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_RecipeAdder implements IGT_RecipeAdder {

	@Override
	public boolean addFusionReactorRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, int aStartEU) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("fusionreactor", aOutput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aInput2, aOutput1, aDuration, aEUt, aStartEU);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addCentrifugeRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("centrifuge", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aInput2 > 0 ? GT_Items.Cell_Empty.get((long) aInput2, new Object[0]) : (aInput2 < 0 ? GT_Items.IC2_Fuel_Can_Empty.get((long) (-aInput2), new Object[0]) : null), aOutput1, aOutput2, aOutput3, aOutput4, aDuration);
				return true;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public boolean addElectrolyzerRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("electrolyzer", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aInput2 > 0 ? GT_Items.Cell_Empty.get((long) aInput2, new Object[0]) : (aInput2 < 0 ? GT_Items.IC2_Fuel_Can_Empty.get((long) (-aInput2), new Object[0]) : null), aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("chemicalreactor", aOutput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aInput2, aOutput1, aDuration);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addBlastRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2,
			int aDuration, int aEUt, int aLevel) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("blastfurnace", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aInput2, aOutput1, aOutput2, aDuration, aEUt, aLevel);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addCannerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2,
			int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("canning", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aEUt, aInput2, aDuration, aOutput1, aOutput2);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if (aInput2 == null && (OrePrefixes.ingot.contains(aInput1) || OrePrefixes.dust.contains(aInput1)
					|| OrePrefixes.gem.contains(aInput1))) {
				return false;
			} else if ((aDuration = GregTech_API.sRecipeFile.get("alloysmelting", aInput2 == null ? aInput1 : aOutput1,
					aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aInput2, aEUt, aDuration, aOutput1);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addCNCRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		return aInput1 != null && aOutput1 != null ? GregTech_API.sRecipeFile.get("cnc", aOutput1, aDuration) > 0 : false;
	}

	@Override
	public boolean addLatheRecipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("lathe", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aOutput1, aOutput2, aDuration, aEUt);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addCutterRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("cutting", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aDuration, aOutput1, aEUt);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration,
			int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("assembling", aOutput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aEUt, aInput2, aDuration, aOutput1);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addWiremillRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("wiremill", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aEUt, aDuration, aOutput1);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addBenderRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("bender", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aEUt, aDuration, aInput1, aOutput1);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addExtruderRecipe(ItemStack aInput1, ItemStack aShape, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 != null && aShape != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("extruder", aOutput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aEUt, aDuration, aInput1, aShape, aOutput1);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addImplosionRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aInput2 = GregTech_API.sRecipeFile.get("implosion", aInput1, aInput2)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aInput2, aOutput1, aOutput2);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addDistillationRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("distillation", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4, aDuration, aEUt);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addVacuumFreezerRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration) {
		if (aInput1 != null && aOutput1 != null) {
			if ((aDuration = GregTech_API.sRecipeFile.get("vacuumfreezer", aInput1, aDuration)) <= 0) {
				return false;
			} else {
				new Recipe(aInput1, aOutput1, aDuration);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addGrinderRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		return this.addGrinderRecipe(aInput1, GT_Items.Cell_Water.get((long) (-aInput2), new Object[0]), aOutput1, aOutput2, aOutput3, aOutput4);
	}

	@Override
	public boolean addGrinderRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {
		if (aInput1 != null && aOutput1 != null) {
			if (!GregTech_API.sRecipeFile.get("grinder", aInput1, true)) {
				return false;
			} else {
				new Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3, aOutput4);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addSawmillRecipe(ItemStack aInput1, int aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		return this.addSawmillRecipe(aInput1, GT_Items.Cell_Water.get((long) (-aInput2), new Object[0]), aOutput1, aOutput2, aOutput3);
	}

	@Override
	public boolean addSawmillRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3) {
		if (aInput1 != null && aOutput1 != null) {
			if (!GregTech_API.sRecipeFile.get("industrialsawmill", aInput1, true)) {
				return false;
			} else {
				new Recipe(aInput1, aInput2, aOutput1, aOutput2, aOutput3);
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addFuel(ItemStack aInput1, ItemStack aOutput1, int aEU, int aType) {
		if (aInput1 == null) {
			return false;
		} else {
			new Recipe(aInput1, aOutput1, GregTech_API.sRecipeFile.get("fuel_" + aType, aInput1, aEU), aType);
			return true;
		}
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

	@Override
	public boolean addComputercubeDescriptionSet(ItemStack[] aItemStack, String[] aText) {
		return false;
	}

	@Override
	public boolean addForgeHammerRecipe(ItemStack aInput1, ItemStack aOutput1, int aDuration, int aEUt) {
		if (aInput1 != null && aOutput1 != null) {
			if (!GregTech_API.sRecipeFile.get("forgehammer", aOutput1, true)) {
				return false;
			} else {
				Recipe.addRecipe(RecipeMaps.sHammerRecipes, true, aInput1, null, aOutput1, null, null, null, aDuration, aEUt, 0);
				return true;
			}
		} else {
			return false;
		}
	}
}
