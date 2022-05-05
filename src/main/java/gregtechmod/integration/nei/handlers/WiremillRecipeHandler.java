package gregtechmod.integration.nei.handlers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Wiremill;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.integration.nei.GT_RecipeHandler;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class WiremillRecipeHandler extends GT_RecipeHandler {
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(70-sOffsetX, 24-sOffsetY, 36, 18), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BasicMachine_Wiremill.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(70-5, 24-11, 36, 18), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.wiremill.title");
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Wiremill";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIWiremill.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.Wiremill";
	}
	
	@Override
	public RecipeMap<?> getRecipeList() {
		return RecipeMaps.WIREMILL;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(Recipe irecipe, ItemStack activatedStack, boolean crafting) {
		return new CachedGT_Recipe(irecipe, activatedStack, crafting) {
			@Override
			protected Pair<Integer, Integer> getInputAligment(int itemIdx) {
				return Pair.of(53 - sOffsetX + (54 * itemIdx), 25 - sOffsetY);
			}
			
			@Override
			protected Pair<Integer, Integer> getOutputAligment(int itemIdx) {
				return Pair.of(107 - sOffsetX, 25 - sOffsetY);
			}
		};
	}
	
	@Override
	public void drawExtras(int recipeIdx) {
		CachedGT_Recipe recipe = ((CachedGT_Recipe)arecipes.get(recipeIdx));
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(recipe.mDuration * recipe.mEUt)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(recipe.mDuration * 1.0D / 20.0D)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(recipe.mEUt)), 0xFF000000, false);
	}
}