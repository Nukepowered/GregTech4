package gregtechmod.loaders.oreprocessing;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_OreDictNames;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.interfaces.IOreRecipeRegistrator;
import gregtechmod.api.util.GT_Shaped_Recipe;
import gregtechmod.api.util.OreDictEntry;
import gregtechmod.common.RecipeHandler;

public class ProcessingCircuit implements IOreRecipeRegistrator {

	public ProcessingCircuit() {
		OrePrefixes.circuit.add(this);
	}

	// FIXME removing all circuits crafing recipe, then register new
	public void registerOre(OrePrefixes aPrefix, List<OreDictEntry> entries) {
		for (OreDictEntry entry : entries) {
			Materials aMaterial = this.getMaterial(aPrefix, entry);
			if (this.isExecutable(aPrefix, aMaterial)) {
				switch (aMaterial) {
				case Basic:
					RecipeHandler.executeOnFinish(() -> {
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Items.Circuit_Basic.get(1), new Object[] { "CCC", "SRS", "CCC",
							'C', GT_OreDictNames.craftingWireCopper.toString(),
							'R', OrePrefixes.plate.get(Materials.Iron),
							'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true) ? OrePrefixes.plate.get(Materials.RedAlloy) : OrePrefixes.dust.get(Materials.Redstone)
						}));
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Items.Circuit_Basic.get(1), new Object[] { "CSC", "CRC", "CSC",
								'C', GT_OreDictNames.craftingWireCopper.toString(),
								'R', OrePrefixes.plate.get(Materials.Iron),
								'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true) ? OrePrefixes.plate.get(Materials.RedAlloy) : OrePrefixes.dust.get(Materials.Redstone)
						}));
					});
					break;
				case Advanced:
					RecipeHandler.executeOnFinish(() -> {
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Items.Circuit_Advanced.get(1), new Object[] { "SGS", "LCL", "SGS",
								'C', OrePrefixes.circuit.get(Materials.Basic),
								'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true) ? OrePrefixes.plate.get(Materials.RedAlloy) : OrePrefixes.dust.get(Materials.Redstone),
								'G', OrePrefixes.dust.get(Materials.Glowstone),
								'L', OrePrefixes.dust.get(Materials.Lazurite) }));
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Items.Circuit_Advanced.get(1), new Object[] { "SLS", "GCG", "SLS",
								'C', OrePrefixes.circuit.get(Materials.Basic),
								'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true) ? OrePrefixes.plate.get(Materials.RedAlloy) : OrePrefixes.dust.get(Materials.Redstone),
								'G', OrePrefixes.dust.get(Materials.Glowstone),
								'L', OrePrefixes.dust.get(Materials.Lazurite) }));
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Items.Circuit_Advanced.get(1), new Object[] { "SGS", "LCL", "SGS",
								'C', OrePrefixes.circuit.get(Materials.Basic),
								'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true) ? OrePrefixes.plate.get(Materials.RedAlloy) : OrePrefixes.dust.get(Materials.Redstone),
								'G', OrePrefixes.dust.get(Materials.Glowstone),
								'L', OrePrefixes.dust.get(Materials.Lapis) }));
						GameRegistry.addRecipe(new GT_Shaped_Recipe(GT_Items.Circuit_Advanced.get(1), new Object[] { "SLS", "GCG", "SLS",
								'C', OrePrefixes.circuit.get(Materials.Basic),
								'S', GregTech_API.sRecipeFile.get(GT_ConfigCategories.Recipes.harderrecipes, "circuitRedAlloy", true) ? OrePrefixes.plate.get(Materials.RedAlloy) : OrePrefixes.dust.get(Materials.Redstone),
								'G', OrePrefixes.dust.get(Materials.Glowstone),
								'L', OrePrefixes.dust.get(Materials.Lapis) }));
					});
					break;
				default: break;
				}
			}
		}
	}
}
