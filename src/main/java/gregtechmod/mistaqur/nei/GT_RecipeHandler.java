package gregtechmod.mistaqur.nei;

import static codechicken.core.gui.GuiDraw.changeTexture;
import static codechicken.core.gui.GuiDraw.drawTexturedModalRect;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Recipe;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.common.event.FMLInterModComms;

public abstract class GT_RecipeHandler extends TemplateRecipeHandler {
	
	public static int sOffsetX = 5, sOffsetY = 11;
	
	public GT_RecipeHandler() {
		if (!NEI_GregTech_Config.sIsAdded) {
			FMLInterModComms.sendRuntimeMessage(GregTech_API.gregtechmod, "NEIPlugins", "register-crafting-handler", GregTech_API.MOD_ID+"@"+getRecipeName()+"@"+getRecipeId());
			API.registerRecipeHandler(this);
			API.registerUsageHandler(this);
		}
	}
	
	public abstract class CachedGT_Recipe extends CachedRecipe {
		public List<PositionedStack> products;
		public List<PositionedStack> resources;
		
		@Override
		public List<PositionedStack> getIngredients() {
	        return getCycledIngredients(cycleticks / 20, this.resources);
	    }
		
		@Override
		public PositionedStack getResult() {
			return null;
		}
		@Override
		public List<PositionedStack> getOtherStacks() {
			return products;
		}
	}
	
	@Override
	public abstract String getRecipeName();
	
	public abstract String getRecipeId();
	
	public abstract String getGuiTexture();

	@Override
	public abstract String getOverlayIdentifier();
	
	public abstract List<GT_Recipe> getRecipeList();
	
	public abstract CachedGT_Recipe getRecipe(GT_Recipe irecipe);
	
	@Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        changeTexture(getGuiTexture());
        drawTexturedModalRect(0, 0, sOffsetX, sOffsetY, 168, 79);
    }
    
	@Override
	public int recipiesPerPage() {
		return 1;
	}
	
	@Override
	public void loadTransferRects() {
		
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if(outputId.equals(getRecipeId())) {
			for (GT_Recipe irecipe : getRecipeList()) {
				arecipes.add(getRecipe(irecipe));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		ItemStack tStack = GT_OreDictUnificator.get(true, result);
		for (GT_Recipe irecipe : getRecipeList()) {
			CachedGT_Recipe recipe = getRecipe(irecipe);
			if (recipe.contains(recipe.products,tStack) || recipe.contains(recipe.products,result)) {
				arecipes.add(recipe);
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		ItemStack tStack = GT_OreDictUnificator.get(false, ingredient);
		for (GT_Recipe irecipe : getRecipeList()) {
			CachedGT_Recipe recipe = getRecipe(irecipe);
			if (recipe.contains(recipe.resources,tStack) || recipe.contains(recipe.resources,ingredient)) {
				arecipes.add(recipe);
			}
		}
	}
	
	public void drawText(int aX, int aY, String aString, int aColor, boolean aB) {
		Minecraft.getMinecraft().fontRenderer.drawString(aString, aX, aY, aColor);
	}
	
	public String toNumber(int aNumber) {
		String tString = "";
		boolean temp = true; 
		boolean negative = false;
		if (aNumber < 0) {
		  aNumber *= -1;
		  negative = true;
		}
		for (int i = 1000000000; i > 0; i /= 10) {
		  int tDigit = aNumber / i % 10;
		  if ((temp) && (tDigit != 0)) temp = false;
		  if (!temp) {
			tString = tString + tDigit;
			if (i != 1) for (int j = i; j > 0; j /= 1000) if (j == 1) tString = tString + ",";
		  }
		}
		if (tString.equals("")) tString = "0";
		return negative ? "-" + tString : tString;
	}
}
