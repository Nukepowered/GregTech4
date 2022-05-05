package gregtechmod.integration.nei.handlers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import codechicken.nei.recipe.TemplateRecipeHandler;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.integration.nei.GT_RecipeHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * @author TheDarkDnKTv
 *
 */
public class GeneratorHandler extends GT_RecipeHandler {
	
	protected RecipeMap<?> map;
	protected String name;
	protected List<Class<? extends GuiContainer>> overlays;
	/**
	 * 
	 */
	@SafeVarargs
	public GeneratorHandler(RecipeMap<?> recipeMap, String machineName, Class<? extends GuiContainer>...containers) {
		this(recipeMap, machineName, Arrays.asList(containers));
	}
	
	public GeneratorHandler(RecipeMap<?> recipeMap, String machineName, List<Class<? extends GuiContainer>> containers) {
		this.overlays = new ArrayList<>(containers);
		this.map = recipeMap;
		this.name = machineName;
		
		try {
			transferRects.add(new RecipeTransferRect(new Rectangle(96 - sOffsetX, 36 - sOffsetY, 18, 18), getRecipeId()));
			ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
			ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
			guis.addAll(overlays);
			transferRects2.add(new RecipeTransferRect(new Rectangle(76 - sOffsetX, 39 - sOffsetY, 18, 18), getRecipeId(), new Object[0]));
			RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch (Throwable e) {
			GT_Log.log.catching(e);
		}
	}
	
	@Override
    public TemplateRecipeHandler newInstance() {
        return new GeneratorHandler(map, name, overlays);
    }
	
	@Override
	public void loadTransferRects() {}
	
	@Override
	public RecipeMap<?> getRecipeList() {
		return map;
	}
	
	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("nei." + name + ".title");
	}

	@Override
	public String getRecipeId() {
		return "gregtech." + name;
	}

	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEIFuel.png";
	}

	@Override
	public String getOverlayIdentifier() {
		return "gregtech." + name;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(Recipe irecipe, ItemStack activatedStack, boolean crafting) {
		return new CachedGT_Recipe(irecipe, activatedStack, crafting) {
			protected Pair<Integer, Integer> getInputAligment(int itemIdx) {
				return Pair.of(67 - sOffsetY, 35 - sOffsetY + (itemIdx * 18));
			}
			
			protected Pair<Integer, Integer> getOutputAligment(int itemIdx) {
				return Pair.of(85 - sOffsetY, 35 - sOffsetY + (itemIdx * 18));
			}
			
			protected Pair<Integer, Integer> getFluidInputAligment(int itemIdx) {
				return Pair.of(67 - sOffsetY, 54 - sOffsetY + (itemIdx * 18));
			}
			
			protected Pair<Integer, Integer> getFluidOutputAligment(int itemIdx) {
				return Pair.of(85 - sOffsetY, 54 - sOffsetY + (itemIdx * 18));
			}
		};
	}
	
	public void drawExtras(int recipe) {
		CachedGT_Recipe rec = (CachedGT_Recipe) arecipes.get(recipe);
		drawText(30, 80, I18n.format("nei.extras.eu_total", GT_Utility.parseNumberToString(rec.mDuration * rec.mEUt)), 0xFF000000, false);
	}
}
