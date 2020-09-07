package gregtechmod.mistaqur.nei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Rectangle;
import codechicken.nei.PositionedStack;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Extruder;
import net.minecraft.client.resources.I18n;
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

	public List<GT_Recipe> getRecipeList() {
		return GT_Recipe.sExtruderRecipes;
	}

	public GT_RecipeHandler.CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
		return new CachedExtruderRecipe(irecipe);
	}

	public void drawExtras(int recipe) {
		Integer time = Integer.valueOf(((CachedExtruderRecipe) this.arecipes.get(recipe)).mDuration);
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(time * ((CachedExtruderRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(time / 20.0D)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(((CachedExtruderRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
	}

	public class CachedExtruderRecipe extends GT_RecipeHandler.CachedGT_Recipe {

		public int mDuration;
		public int mEUt;

		public CachedExtruderRecipe(GT_Recipe aRecipe) {
			super();
			super.resources = new ArrayList<>();
			if (aRecipe.getRepresentativeInput1() != null) {
				super.resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(),
						35 - GT_RecipeHandler.sOffsetX, 25 - GT_RecipeHandler.sOffsetY));
			}

			if (aRecipe.getRepresentativeInput2() != null) {
				super.resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(),
						53 - GT_RecipeHandler.sOffsetX, 25 - GT_RecipeHandler.sOffsetY));
			}

			super.products = new ArrayList<>();
			if (aRecipe.getOutput(0) != null) {
				super.products.add(new PositionedStack(aRecipe.getOutput(0), 107 - GT_RecipeHandler.sOffsetX,
						25 - GT_RecipeHandler.sOffsetY));
			}

			this.mDuration = aRecipe.mDuration;
			this.mEUt = aRecipe.mEUt;
		}
	}
}
