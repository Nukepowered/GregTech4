package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.common.gui.GT_GUIContainer_Centrifuge;

import java.awt.Rectangle;
import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import codechicken.nei.PositionedStack;

public class CentrifugeRecipeHandler extends GT_RecipeHandler {
	
	public class CachedCentrifugeRecipe extends CachedGT_Recipe {
		public int mDuration;

		public CachedCentrifugeRecipe(GT_Recipe aRecipe) {
			resources = new ArrayList<PositionedStack>();
			if (aRecipe.mInput1 != null)
				resources.add(new PositionedStack(aRecipe.mInput1, 80 - sOffsetX, 35 - sOffsetY + 7));
			if (aRecipe.mInput2 != null)
				resources.add(new PositionedStack(aRecipe.mInput2, 50 - sOffsetX, 5 - sOffsetY + 7));

			products = new ArrayList<PositionedStack>();
			if (aRecipe.mOutput1 != null)
				products.add(new PositionedStack(aRecipe.mOutput1, 80 - sOffsetX, 5 - sOffsetY + 7));
			if (aRecipe.mOutput2 != null)
				products.add(new PositionedStack(aRecipe.mOutput2, 110 - sOffsetX, 35 - sOffsetY + 7));
			if (aRecipe.mOutput3 != null)
				products.add(new PositionedStack(aRecipe.mOutput3, 80 - sOffsetX, 65 - sOffsetY + 7));
			if (aRecipe.mOutput4 != null)
				products.add(new PositionedStack(aRecipe.mOutput4, 50 - sOffsetX, 35 - sOffsetY + 7));

			mDuration = aRecipe.mDuration;
		}
	}
	
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(79-sOffsetX, 22-sOffsetY + 7, 18, 12), getRecipeId()));
		transferRects.add(new RecipeTransferRect(new Rectangle(67-sOffsetX, 34-sOffsetY + 7, 12, 18), getRecipeId()));
		transferRects.add(new RecipeTransferRect(new Rectangle(97-sOffsetX, 34-sOffsetY + 7, 12, 18), getRecipeId()));
		transferRects.add(new RecipeTransferRect(new Rectangle(79-sOffsetX, 52-sOffsetY + 7, 18, 12), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_Centrifuge.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(79-5, 22-11, 18, 12), getRecipeId(), new Object[0]));
		transferRects2.add(new RecipeTransferRect(new Rectangle(67-5, 34-11, 12, 18), getRecipeId(), new Object[0]));
		transferRects2.add(new RecipeTransferRect(new Rectangle(97-5, 34-11, 12, 18), getRecipeId(), new Object[0]));
		transferRects2.add(new RecipeTransferRect(new Rectangle(79-5, 52-11, 18, 12), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {e.printStackTrace(GT_Log.out);}
	}
	
	@Override
	public String getRecipeName() {
		return "Industrial Centrifuge";
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Centrifuge";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEICentrifuge.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.centrifuge";
	}
	
	@Override
	public ArrayList<GT_Recipe> getRecipeList() {
		return GT_Recipe.sCentrifugeRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
		return new CachedCentrifugeRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedCentrifugeRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 90, new StringBuilder().append("EU: ").append(toNumber(time*5)).toString(), 0xFF000000, false);
		drawText(30,100, new StringBuilder().append("Time: ").append(toNumber(time/20)).append(" secs").toString(), 0xFF000000, false);
	}
}