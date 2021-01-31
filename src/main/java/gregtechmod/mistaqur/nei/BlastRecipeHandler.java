package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMaps;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BlastFurnace;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;

public class BlastRecipeHandler extends GT_RecipeHandler {
	
	public class CachedBlastRecipe extends CachedGT_Recipe {
		public int mStartEU;

		public CachedBlastRecipe(Recipe aRecipe) {
			super(aRecipe);
			mStartEU = aRecipe.mStartEU;
		}
		
		@Override
		protected Pair<Integer, Integer> getInputAligment(int itemIdx) {
			return Pair.of(34 - sOffsetX, 16 - sOffsetY + (18 * itemIdx));
		}
		
		@Override
		protected Pair<Integer, Integer> getOutputAligment(int itemIdx) {
			return Pair.of(86 - sOffsetX + (18 * itemIdx), 25 - sOffsetY);
		}
	}
	
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(56-sOffsetX, 26-sOffsetY, 24, 15), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BlastFurnace.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(56-5, 26-11, 24, 15), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.blast_furnance.title");
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Blast";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIBlast.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.Blast";
	}
	
	@Override
	public List<Recipe> getRecipeList() {
		return RecipeMaps.sBlastRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(Recipe irecipe) {
		return new CachedBlastRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedBlastRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(time * ((CachedBlastRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(time * 1.0D / 20.0D)), 0xFF000000, false);
		drawText(30,100, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(((CachedBlastRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
		drawText(30,110, I18n.format("nei.blast_furnance.info", GT_Utility.parseNumberToString(((CachedBlastRecipe)arecipes.get(recipe)).mStartEU)), 0xFF000000, false);
	}

}
