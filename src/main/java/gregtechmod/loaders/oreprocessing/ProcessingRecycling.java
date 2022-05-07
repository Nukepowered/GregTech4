package gregtechmod.loaders.oreprocessing;

import java.util.List;

import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_RecipeRegistrator;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.item.ItemStack;

public class ProcessingRecycling implements IOreRecipeRegistrator {

	public ProcessingRecycling() {
		for (OrePrefixes tPrefix : OrePrefixes.values()) {
			if (tPrefix.mIsMaterialBased && tPrefix.mMaterialAmount > 0L && tPrefix != OrePrefixes.ingotHot && tPrefix != OrePrefixes.cellPlasma) {
				tPrefix.add(this);
			}
		}
	}

	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		if (!aPrefix.toString().startsWith("dust") && !aPrefix.toString().startsWith("crushed")) {
			for (OreDictEntry entry : entries) {
				Materials aMaterial = this.getMaterial(aPrefix, entry);
				if (this.isExecutable(aPrefix, aMaterial) && aMaterial != Materials.Blaze) {
					for (ItemStack aStack : entry.ores) {
						if (aPrefix.mIsContainer) {
							if (aMaterial != Materials.Empty && aPrefix != OrePrefixes.cell) {
								if (aMaterial == Materials.Mercury) {
									RecipeMaps.CANNING.factory().EUt(2).duration(Math.max(aMaterial.getMass() / 2, 1))
										.input(aStack)
										.output(GT_Utility.getContainerItem(aStack))
										.output(GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, aPrefix.mMaterialAmount / 3628800L))
										.buildAndRegister();
								}
	
								if ((aMaterial.mTypes & 15) != 0 && aMaterial != Materials.Creosote
												&& aMaterial != Materials.Mercury && aMaterial != Materials.SulfuricAcid
												&& aMaterial != Materials.BioFuel && aMaterial != Materials.Water
												&& aMaterial != Materials.Nitrogen && aMaterial != Materials.ConstructionFoam) {
									
									RecipeMaps.CANNING.factory().EUt(2).duration(Math.max(aMaterial.getMass() / 2, 1))
										.input(aStack)
										.output(GT_Utility.getContainerItem(aStack))
										.output(GT_OreDictUnificator.get(OrePrefixes.dust, aMaterial, aPrefix.mMaterialAmount / 3628800L))
										.buildAndRegister();
								}
							}
						} else {
							GT_RecipeRegistrator.registerBasicReverseMacerating(aStack, aMaterial, aPrefix.mMaterialAmount);
							if (GT_OreDictUnificator.get(OrePrefixes.ingot, aMaterial) != null
									&& !aMaterial.contains(SubTag.NO_SMELTING) && aPrefix != OrePrefixes.ingot
									&& aPrefix != OrePrefixes.nugget) {
								GT_RecipeRegistrator.registerBasicReverseSmelting(aStack, aMaterial, aPrefix.mMaterialAmount, true);
							}
						}
					}
				}
			}
		}
	}
}
