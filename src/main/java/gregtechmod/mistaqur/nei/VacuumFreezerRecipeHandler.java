package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_VacuumFreezer;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;
import codechicken.nei.PositionedStack;

public class VacuumFreezerRecipeHandler extends GT_RecipeHandler {
	
	public class CachedVacuumFreezerRecipe extends CachedGT_Recipe {
		public int mDuration, mEUt;

		public CachedVacuumFreezerRecipe(Recipe aRecipe) {
			resources = new ArrayList<PositionedStack>();
			if (aRecipe.getRepresentativeInput1() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 54 - sOffsetX, 25 - sOffsetY));
			
			products = new ArrayList<PositionedStack>();
			if (aRecipe.getOutput(0) != null)
				products.add(new PositionedStack(aRecipe.getOutput(0), 106 - sOffsetX, 25 - sOffsetY));
			
			mDuration = aRecipe.mDuration;
			mEUt = aRecipe.mEUt;
		}
	}
	
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(76-sOffsetX, 26-sOffsetY, 24, 15), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_VacuumFreezer.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(56-5, 26-11, 24, 15), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.vacuum_freezer.title");
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.VacuumFreezer";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIVacuumFreezer.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.VacuumFreezer";
	}
	
	@Override
	public List<Recipe> getRecipeList() {
		return Recipe.sVacuumRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(Recipe irecipe) {
		return new CachedVacuumFreezerRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedVacuumFreezerRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(time * ((CachedVacuumFreezerRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(time / 20.0D)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(((CachedVacuumFreezerRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
	}
}