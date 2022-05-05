package gregtechmod.integration.nei.handlers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_FusionComputer;
import gregtechmod.common.recipe.RecipeMaps;
import gregtechmod.integration.nei.GT_RecipeHandler;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class FusionRecipeHandler extends GT_RecipeHandler {
	
	public class CachedFusionRecipe extends CachedGT_Recipe {
		public int mStartEU;
		
		public CachedFusionRecipe(Recipe irecipe, ItemStack activatedStack, boolean crafting) {
			super(irecipe, activatedStack, crafting);
			mStartEU = irecipe.getEUtoStart();
		}
		
		@Override
		protected Pair<Integer, Integer> getInputAligment(int itemIdx) {
			return Pair.of(48 - sOffsetX, 17 - sOffsetY + (36 * itemIdx));
		}
		
		@Override
		protected Pair<Integer, Integer> getOutputAligment(int itemIdx) {
			return Pair.of(108 - sOffsetX, 35 - sOffsetY);
		}
		
		@Override
		protected Pair<Integer, Integer> getFluidInputAligment(int itemIdx) {
			return this.getInputAligment(itemIdx);
		}
		
		@Override
		protected Pair<Integer, Integer> getFluidOutputAligment(int itemIdx) {
			return this.getOutputAligment(itemIdx);
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
	public RecipeMap<?> getRecipeList() {
		return RecipeMaps.FUSION_REACTOR;
	}
	
	@Override
	public CachedFusionRecipe getRecipe(Recipe irecipe, ItemStack activatedStack, boolean crafting) {
		return new CachedFusionRecipe(irecipe, activatedStack, crafting);
	}

	@Override
	public void drawExtras(int recipe) {
		CachedFusionRecipe t = ((CachedFusionRecipe)arecipes.get(recipe));
		drawText(30, 80, I18n.format("nei.fusion_reactor.info.1", GT_Utility.parseNumberToString(t.mStartEU)), 0xFF000000, false);
		drawText(30, 90, I18n.format("nei.extras.eut", GT_Utility.parseNumberToString(t.mEUt)), 0xFF000000, false);
		drawText(30, 100, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(t.mDuration * 1.0D / 20.0D)), 0xFF000000, false);
		if (t.mEUt < 0)
			drawText(30, 110, I18n.format("nei.fusion_reactor.info.2", GT_Utility.parseNumberToString(-t.mEUt * t.mDuration)), 0xFF000000, false);
		else
			drawText(30, 110,I18n.format("nei.fusion_reactor.info.2", GT_Utility.parseNumberToString(t.mEUt * t.mDuration)), 0xFF000000, false);
	}
}