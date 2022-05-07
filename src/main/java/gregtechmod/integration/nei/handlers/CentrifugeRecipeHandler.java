package gregtechmod.integration.nei.handlers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_Centrifuge;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.integration.nei.GT_RecipeHandler;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class CentrifugeRecipeHandler extends GT_RecipeHandler {
	
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
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.centrifuge.title");
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
	public RecipeMap<?> getRecipeList() {
		return RecipeMaps.CENTRIFUGE;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(Recipe irecipe, ItemStack activatedStack, boolean crafting) {
		return new CachedGT_Recipe(irecipe, activatedStack, crafting) {
			@Override
			protected Pair<Integer, Integer> getInputAligment(int itemIdx) {
				int x = itemIdx == 0 ? 80 - sOffsetX : 50 - sOffsetX;
				int y = itemIdx == 0 ? 35 - sOffsetY + 7 : 5 - sOffsetY + 7;
				return Pair.of(x, y);
			}
			
			@Override
			protected Pair<Integer, Integer> getOutputAligment(int itemIdx) {
				switch(itemIdx) {
				case 0: return Pair.of(80 - sOffsetX, 5 - sOffsetY + 7);
				case 1: return Pair.of(110 - sOffsetX, 35 - sOffsetY + 7);
				case 2: return Pair.of(80 - sOffsetX, 65 - sOffsetY + 7);
				case 3: return Pair.of(50 - sOffsetX, 35 - sOffsetY + 7);
				default: return Pair.of(0, 0);
				}
			}
			
			@Override
			protected Pair<Integer, Integer> getFluidInputAligment(int itemIdx) {
				return Pair.of(110 - sOffsetX + (itemIdx * 18), 65 - sOffsetY + 7);
			}
		};
	}
	
	@Override
	public void drawExtras(int recipe) {
		CachedGT_Recipe rec = (CachedGT_Recipe) arecipes.get(recipe);
		drawText(30, 90, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(rec.mDuration * 5)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(rec.mDuration * 1.0D / 20.0D)), 0xFF000000, false);
	}
}