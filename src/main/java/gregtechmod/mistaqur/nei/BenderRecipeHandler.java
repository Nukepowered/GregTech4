package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Bender;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;
import codechicken.nei.PositionedStack;

public class BenderRecipeHandler extends GT_RecipeHandler {
	
	public class CachedBenderRecipe extends CachedGT_Recipe {
		public int mDuration, mEUt;

		public CachedBenderRecipe(GT_Recipe aRecipe) {
			resources = new ArrayList<PositionedStack>();
			if (aRecipe.getRepresentativeInput1() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 35 - sOffsetX, 25 - sOffsetY));
			
			if (aRecipe.getRepresentativeInput2() != null)
				super.resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(), 53 - sOffsetX, 25 - sOffsetY));
			
			products = new ArrayList<PositionedStack>();
			if (aRecipe.getOutput(0) != null)
				products.add(new PositionedStack(aRecipe.getOutput(0), 107 - sOffsetX, 25 - sOffsetY));
			
			mDuration = aRecipe.mDuration;
			mEUt = aRecipe.mEUt;
		}
	}
	
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(70-sOffsetX, 24-sOffsetY, 36, 18), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BasicMachine_Bender.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(70-5, 24-11, 36, 18), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.bender.title");
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Bender";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIBender.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.Bender";
	}
	
	@Override
	public List<GT_Recipe> getRecipeList() {
		return GT_Recipe.sBenderRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
		return new CachedBenderRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedBenderRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(time * ((CachedBenderRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(time / 20.0D)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(((CachedBenderRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
	    drawText(30, 110, I18n.format("nei.bender.info.1"), -16777216, false);
	    drawText(30, 120, I18n.format("nei.bender.info.2"), -16777216, false);
	}
}
