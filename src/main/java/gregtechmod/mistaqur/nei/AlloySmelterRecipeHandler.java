package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_AlloySmelter;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Compressor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_E_Furnace;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Extractor;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Macerator;
import gregtechmod.common.gui.GT_GUIContainer_Scrapboxinator;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;
import codechicken.nei.PositionedStack;

public class AlloySmelterRecipeHandler extends GT_RecipeHandler {
	
	public class CachedAlloySmelterRecipe extends CachedGT_Recipe {
		public int mDuration, mEUt;

		public CachedAlloySmelterRecipe(GT_Recipe aRecipe) {
			resources = new ArrayList<PositionedStack>();
			if (aRecipe.getRepresentativeInput1() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput1(), 35 - sOffsetX, 25 - sOffsetY));
			if (aRecipe.getRepresentativeInput2() != null)
				resources.add(new PositionedStack(aRecipe.getRepresentativeInput2(), 53 - sOffsetX, 25 - sOffsetY));

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
		guis.add(GT_GUIContainer_BasicMachine_AlloySmelter.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(70-5, 24-11, 36, 18), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		
		guis = new ArrayList<Class<? extends GuiContainer>>();
		transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BasicMachine_E_Furnace.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(70-5, 24-11, 36, 18), "smelting", new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		
		guis = new ArrayList<Class<? extends GuiContainer>>();
		transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BasicMachine_Macerator.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(70-5, 24-11, 36, 18), "ic2.macerator", new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		
		guis = new ArrayList<Class<? extends GuiContainer>>();
		transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BasicMachine_Extractor.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(70-5, 24-11, 36, 18), "ic2.extractor", new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		
		guis = new ArrayList<Class<? extends GuiContainer>>();
		transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BasicMachine_Compressor.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(70-5, 24-11, 36, 18), "ic2.compressor", new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		
		guis = new ArrayList<Class<? extends GuiContainer>>();
		transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_Scrapboxinator.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(61-5, 62-11, 18, 18), "ic2.scrapbox", new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.alloy_smelter.title");
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Alloy";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIAlloySmelter.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.Alloy";
	}
	
	@Override
	public List<GT_Recipe> getRecipeList() {
		return GT_Recipe.sAlloySmelterRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
		return new CachedAlloySmelterRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedAlloySmelterRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(time * ((CachedAlloySmelterRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(time / 20.0D)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(((CachedAlloySmelterRecipe)arecipes.get(recipe)).mEUt)), 0xFF000000, false);
	}

}
