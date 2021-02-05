package gregtechmod.mistaqur.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.recipe.Ingredient;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.recipe.GuiRecipe;
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
	
	public class CachedGT_Recipe extends CachedRecipe {
		public List<PositionedStack> products;
		public List<PositionedStack> resources;
		public List<Integer> nonConsumables;
		public int mDuration, mEUt;
		
		public CachedGT_Recipe(Recipe aRecipe) {
			resources 		= new ArrayList<>();
			products 		= new ArrayList<>();
			nonConsumables 	= new ArrayList<>();
			
//			ItemStack[][] inputs = aRecipe.getRepresentativeInputs();
//			for (int i = 0; i < inputs.length; i++) {
//				ItemStack[] items = Arrays.stream(inputs[i]).filter(item -> item != null).toArray(a -> new ItemStack[a]);
//				if (items.length > 0) {
//					Pair<Integer, Integer> offsets = getInputAligment(i);
//					resources.add(new PositionedStack(items, offsets.getKey(), offsets.getValue()));
//				}
//			}
			
			List<Ingredient> inputs = aRecipe.getInputs();
			for (int i = 0; i < inputs.size(); i++) {
				Ingredient input = inputs.get(i);
				Pair<Integer, Integer> offsets = getInputAligment(i);
				if (input.getCount() == 0) {
					for (ItemStack s : input.getVariants()) {
						nonConsumables.add(GT_Utility.stackToInt(s));
					}
				}
				resources.add(new PositionedStack(input.getVariants(), offsets.getKey(), offsets.getValue()));
			}

			for (int i = 0; i < aRecipe.get.length; i++) {
				Pair<Integer, Integer> offsets = getOutputAligment(i);
				products.add(new PositionedStack(outputs[i], offsets.getKey(), offsets.getValue()));
			}
			
			mDuration = aRecipe.getDuration();
			mEUt = aRecipe.getEUt();
		}
		
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
		
		protected Pair<Integer, Integer> getInputAligment(int itemIdx) {
			return Pair.of(18 * itemIdx, 0);
		}
		
		protected Pair<Integer, Integer> getOutputAligment(int itemIdx) {
			return Pair.of((18 * itemIdx), 54);
		}
	}
	
	@Override
	public abstract String getRecipeName();
	
	public abstract String getRecipeId();
	
	public abstract String getGuiTexture();

	@Override
	public abstract String getOverlayIdentifier();
	
	public abstract List<Recipe> getRecipeList();
	
	public abstract CachedGT_Recipe getRecipe(Recipe irecipe);
	
	@Override
	public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe) {
		CachedRecipe r = arecipes.get(recipe);
		
		if (r instanceof CachedGT_Recipe && ((CachedGT_Recipe)r).nonConsumables.contains(GT_Utility.stackToInt(stack))) {
			currenttip.add(I18n.format("item.non_consumable.tooltip"));
		}
		
		return currenttip;
	}
	
	@Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        codechicken.lib.gui.GuiDraw.changeTexture(getGuiTexture());
        codechicken.lib.gui.GuiDraw.drawTexturedModalRect(0, 0, sOffsetX, sOffsetY, 168, 79);
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
			for (Recipe irecipe : getRecipeList()) {
				arecipes.add(getRecipe(irecipe));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		ItemStack tStack = GT_OreDictUnificator.get(true, result);
		for (Recipe irecipe : getRecipeList()) {
			CachedGT_Recipe recipe = getRecipe(irecipe);
			if (recipe.contains(recipe.products,tStack) || recipe.contains(recipe.products,result)) {
				arecipes.add(recipe);
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		ItemStack tStack = GT_OreDictUnificator.get(false, ingredient);
		for (Recipe irecipe : getRecipeList()) {
			CachedGT_Recipe recipe = getRecipe(irecipe);
			if (recipe.contains(recipe.resources,tStack) || recipe.contains(recipe.resources,ingredient)) {
				arecipes.add(recipe);
			}
		}
	}
	
	public void drawText(int aX, int aY, String aString, int aColor, boolean aB) {
		Minecraft.getMinecraft().fontRenderer.drawString(aString, aX, aY, aColor);
	}
}
