package gregtechmod.integration.nei.handlers;

import java.util.Collections;
import java.awt.Rectangle;

import org.apache.commons.lang3.tuple.Pair;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Extruder;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.integration.nei.GT_RecipeHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ExtruderRecipeHandler extends GT_RecipeHandler {
	public void loadTransferRects() {
		try {
			this.transferRects.add(new RecipeTransferRect(new Rectangle(70 - GT_RecipeHandler.sOffsetX, 24 - GT_RecipeHandler.sOffsetY, 36, 18),this.getRecipeId()));
			RecipeTransferRectHandler.registerRectsToGuis(Collections.singletonList(GT_GUIContainer_BasicMachine_Extruder.class),
					Collections.singletonList(new RecipeTransferRect(new Rectangle(65, 13, 36, 18), this.getRecipeId())));
		} catch (Throwable e) {
			GT_Log.log.catching(e);
		}

	}

	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.extruder.title");
	}

	public String getRecipeId() {
		return "gregtech.Extruder";
	}

	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIExtruder.png";
	}

	public String getOverlayIdentifier() {
		return "gregtech.extruder";
	}

	public RecipeMap<?> getRecipeList() {
		return RecipeMaps.EXTRUDING;
	}

	@Override
	public CachedGT_Recipe getRecipe(Recipe irecipe, ItemStack activatedStack, boolean crafting) {
		return new CachedGT_Recipe(irecipe, activatedStack, crafting) {
			@Override
			protected Pair<Integer, Integer> getInputAligment(int itemIdx) {
				return Pair.of(35 - sOffsetX + (18 * itemIdx), 25 - sOffsetY);
			}
			
			@Override
			protected Pair<Integer, Integer> getOutputAligment(int itemIdx) {
				return Pair.of(107 - sOffsetX, 25 - sOffsetY);
			}
		};
	}

	public void drawExtras(int recipe) {
		CachedGT_Recipe rec = (CachedGT_Recipe) arecipes.get(recipe);
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(rec.mDuration * rec.mEUt)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(rec.mDuration * 1.0D / 20.0D)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(rec.mEUt)), 0xFF000000, false);
	}
}
