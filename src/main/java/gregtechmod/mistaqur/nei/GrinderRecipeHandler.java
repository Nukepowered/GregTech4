package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_Grinder;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;
import codechicken.nei.PositionedStack;

public class GrinderRecipeHandler extends GT_RecipeHandler {
	
	public class CachedGrinderRecipe extends CachedGT_Recipe {
		public int mDuration, mEUt;

		public CachedGrinderRecipe(Recipe aRecipe) {
			resources = new ArrayList<PositionedStack>();
			if (aRecipe.getRepresentativeInput1() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 34 - sOffsetX, 16 - sOffsetY));
			if (aRecipe.getRepresentativeInput2() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(), 34 - sOffsetX, 34 - sOffsetY));

			products = new ArrayList<PositionedStack>();
			if (aRecipe.getOutput(0) != null)
				products.add(new PositionedStack(aRecipe.getOutput(0), 86 - sOffsetX, 25 - sOffsetY));
			if (aRecipe.getOutput(1) != null)
				products.add(new PositionedStack(aRecipe.getOutput(1), 104 - sOffsetX, 25 - sOffsetY));
			if (aRecipe.getOutput(2) != null)
				products.add(new PositionedStack(aRecipe.getOutput(2), 122 - sOffsetX, 25 - sOffsetY));
			if (aRecipe.getOutput(3) != null)
				products.add(new PositionedStack(aRecipe.getOutput(3), 140 - sOffsetX, 25 - sOffsetY));
			
			mDuration = aRecipe.mDuration;
			mEUt = aRecipe.mEUt;
		}
	}
	
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(56-sOffsetX, 26-sOffsetY, 24, 15), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_Grinder.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(56-5, 26-11, 24, 15), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() { 
		return StatCollector.translateToLocal("nei.grinder.title");
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Grinder";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIGrinder.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.Grinder";
	}
	
	@Override
	public List<Recipe> getRecipeList() {
		return Recipe.sGrinderRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(Recipe irecipe) {
		return new CachedGrinderRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedGrinderRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(time * ((CachedGrinderRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(time / 20.0D)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(((CachedGrinderRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
	}
}