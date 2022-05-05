package gregtechmod.integration.nei.handlers;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.apache.commons.lang3.tuple.Pair;

import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.gui.GT_GUIContainer_BronzeBlastFurnace;
import gregtechmod.common.recipe.RecipeMaps;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * @author TheDarkDnKTv
 *
 */
public class BronzeBlastRecipeHandler extends BlastRecipeHandler {

	
	public class CachedBronzeBlastRecipe extends CachedGT_Recipe {
		public CachedBronzeBlastRecipe(Recipe recipe, ItemStack activatedStack, boolean crafting) {
			super(recipe, activatedStack, crafting);
		}
		
		@Override
		protected Pair<Integer, Integer> getInputAligment(int itemIdx) {
			return Pair.of(34 - sOffsetX, 16 - sOffsetY + (18 * itemIdx));
		}
		
		@Override
		protected Pair<Integer, Integer> getOutputAligment(int itemIdx) {
			return Pair.of(86 - sOffsetX + (18 * itemIdx), 25 - sOffsetY);
		}
	}
	
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(56-sOffsetX, 26-sOffsetY, 24, 15), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BronzeBlastFurnace.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(56-5, 26-11, 24, 15), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {GT_Log.log.catching(e);}
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei.bronze_blast_furnance.title");
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.BronzeBlast";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.BronzeBlast";
	}
	
	@Override
	public RecipeMap<?> getRecipeList() {
		return RecipeMaps.BRONZE_BLAST_FURNACE;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(Recipe irecipe, ItemStack activatedStack, boolean crafting) {
		return new CachedBronzeBlastRecipe(irecipe, activatedStack, crafting);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedBronzeBlastRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 90, I18n.format("nei.extras.time", GT_Utility.parseNumberToString(time * 1.0D / 20.0D)), 0xFF000000, false);
	}
}
