package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.common.gui.GT_GUIContainer_BlastFurnace;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import codechicken.nei.PositionedStack;

public class BlastRecipeHandler extends GT_RecipeHandler {
	
	public class CachedBlastRecipe extends CachedGT_Recipe {
		public int mDuration, mEUt, mStartEU;

		public CachedBlastRecipe(GT_Recipe aRecipe) {
			resources = new ArrayList<PositionedStack>();
			if (aRecipe.getRepresentativeInput1() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 34 - sOffsetX, 16 - sOffsetY));
			if (aRecipe.getRepresentativeInput2() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(), 34 - sOffsetX, 34 - sOffsetY));

			products = new ArrayList<PositionedStack>();
			if (aRecipe.getOutput(0) != null)
				products.add(new PositionedStack(aRecipe.getOutput(0), 86 - sOffsetX, 25 - sOffsetY));
			if (aRecipe.getOutput(1) != null)
				products.add(new PositionedStack(aRecipe.getOutput(1),104 - sOffsetX, 25 - sOffsetY));
			
			mDuration = aRecipe.mDuration;
			mStartEU = aRecipe.mStartEU;
			mEUt = aRecipe.mEUt;
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
		return "Industrial Blast Furnace";  // TODO Locale
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
	public List<GT_Recipe> getRecipeList() {
		return GT_Recipe.sBlastRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
		return new CachedBlastRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedBlastRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 80, new StringBuilder().append("EU: ").append(toNumber(time*((CachedBlastRecipe)arecipes.get(recipe)).mEUt)).toString(), 0xFF000000, false);
		drawText(30, 90, new StringBuilder().append("Time: ").append(toNumber(time/20)).append(" secs").toString(), 0xFF000000, false);
		drawText(30,100, new StringBuilder().append("MaxEnergy: ").append(toNumber(((CachedBlastRecipe)arecipes.get(recipe)).mEUt)).append(" EU/t").toString(), 0xFF000000, false);
		drawText(30,110, new StringBuilder().append("Heat Capacity: ").append(toNumber(((CachedBlastRecipe)arecipes.get(recipe)).mStartEU)).append(" K").toString(), 0xFF000000, false);
	}

}
