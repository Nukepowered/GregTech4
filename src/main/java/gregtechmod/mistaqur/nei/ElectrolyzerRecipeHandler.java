package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.common.gui.GT_GUIContainer_Electrolyzer;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import codechicken.nei.PositionedStack;

public class ElectrolyzerRecipeHandler extends GT_RecipeHandler {
	
	public class CachedElectrolyzerRecipe extends CachedGT_Recipe {
		public int mDuration, mEUt;

		public CachedElectrolyzerRecipe(GT_Recipe aRecipe) {
			resources = new ArrayList<PositionedStack>();
			if (aRecipe.getRepresentativeInput1() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 80 - sOffsetX, 46 - sOffsetY));
			if (aRecipe.getRepresentativeInput2() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(), 50 - sOffsetX, 46 - sOffsetY));

			products = new ArrayList<PositionedStack>();
			if (aRecipe.getOutput(0) != null)
				products.add(new PositionedStack(aRecipe.getOutput(0), 50 - sOffsetX, 16 - sOffsetY));
			if (aRecipe.getOutput(1) != null)
				products.add(new PositionedStack(aRecipe.getOutput(1), 70 - sOffsetX, 16 - sOffsetY));
			if (aRecipe.getOutput(2) != null)
				products.add(new PositionedStack(aRecipe.getOutput(2), 90 - sOffsetX, 16 - sOffsetY));
			if (aRecipe.getOutput(3) != null)
				products.add(new PositionedStack(aRecipe.getOutput(3),110 - sOffsetX, 16 - sOffsetY));

			mDuration = aRecipe.mDuration;
			mEUt = aRecipe.mEUt;
		}
	}
	
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(74-sOffsetX, 33-sOffsetY, 30, 10), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_Electrolyzer.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(74-5, 33-11, 30, 10), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return "Industrial Electrolyzer";
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Electrolyzer";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIElectrolyzer.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.electrolyzer";
	}
	
	@Override
	public List<GT_Recipe> getRecipeList() {
		return GT_Recipe.sElectrolyzerRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
		return new CachedElectrolyzerRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedElectrolyzerRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 80, new StringBuilder().append("EU: ").append(toNumber(time*((CachedElectrolyzerRecipe)arecipes.get(recipe)).mEUt)).toString(), 0xFF000000, false);
		drawText(30, 90, new StringBuilder().append("Time: ").append(toNumber(time/20)).append(" secs").toString(), 0xFF000000, false);
		drawText(30,100, new StringBuilder().append("MaxEnergy: ").append(toNumber(((CachedElectrolyzerRecipe)arecipes.get(recipe)).mEUt)).append(" EU/t").toString(), 0xFF000000, false);
	}
}