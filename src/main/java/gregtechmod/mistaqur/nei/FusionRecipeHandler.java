package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_FusionComputer;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;
import codechicken.nei.PositionedStack;

public class FusionRecipeHandler extends GT_RecipeHandler {
	
	public class CachedFusionRecipe extends CachedGT_Recipe {
		public int mDuration;
		public int mEUt;
		public int mStartEU;
		
		public CachedFusionRecipe(GT_Recipe irecipe) {
			resources = new ArrayList<PositionedStack>();
			if (irecipe.getRepresentativeInput1() != null)
				resources.add(new PositionedStack(irecipe.getRepresentativeInput1(), 48 - sOffsetX, 17 - sOffsetY));
			if (irecipe.getRepresentativeInput2() != null)
				resources.add(new PositionedStack(irecipe.getRepresentativeInput2(), 48 - sOffsetX, 53 - sOffsetY));
			
			products = new ArrayList<PositionedStack>();
			if (irecipe.getOutput(0) != null)
				products.add(new PositionedStack(irecipe.getOutput(0), 108 - sOffsetX, 35 - sOffsetY));
			
			mDuration = irecipe.mDuration;
			mEUt = irecipe.mEUt;
			mStartEU = irecipe.mStartEU;
		}
	}

	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(47-sOffsetX, 34-sOffsetY, 50, 18), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_FusionComputer.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(154-5, 4-11, 18, 18), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.fusion_reactor.title");
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Fusionreactor";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIFusionreactor.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.fusion";
	}
	
	@Override
	public List<GT_Recipe> getRecipeList() {
		return GT_Recipe.sFusionRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
		return new CachedFusionRecipe(irecipe);
	}

	@Override
	public void drawExtras(int recipe) {
		CachedFusionRecipe t = ((CachedFusionRecipe)arecipes.get(recipe));
		drawText(30, 80, I18n.format("nei.fusion_reactor.info.1", GT_Utility.parseNumberToString(t.mStartEU)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(t.mEUt)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(t.mDuration / 20.0D)), 0xFF000000, false);
		if (t.mEUt < 0)
			drawText(30, 110, I18n.format("nei.fusion_reactor.info.2", GT_Utility.parseNumberToString(-t.mEUt * t.mDuration)), 0xFF000000, false);
		else
			drawText(30, 110,I18n.format("nei.fusion_reactor.info.2", GT_Utility.parseNumberToString(t.mEUt * t.mDuration)), 0xFF000000, false);
	}
}