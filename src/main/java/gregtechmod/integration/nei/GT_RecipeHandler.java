package gregtechmod.integration.nei;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.recipe.ChancedOutput;
import gregtechmod.api.recipe.Ingredient;
import gregtechmod.api.recipe.Recipe;
import gregtechmod.api.recipe.RecipeMap;
import gregtechmod.api.util.GT_Utility;

import static codechicken.lib.gui.GuiDraw.changeTexture;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fluids.FluidStack;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.common.event.FMLInterModComms;

public abstract class GT_RecipeHandler extends TemplateRecipeHandler {
	
	public static int sOffsetX = 5, sOffsetY = 11;
	
	public GT_RecipeHandler() {
		if (!NEI_GregTech_Config.sIsAdded) {
			FMLInterModComms.sendRuntimeMessage(GregTech_API.gregtechmod, "NEIPlugins", "register-crafting-handler", GregTech_API.MOD_ID+"@"+getRecipeName()+"@"+getRecipeId());
//			API.registerRecipeHandler(this); // Oh nice, NEI checks if class not same... Scroll this, I'll just add it to fucking list
//			API.registerUsageHandler(this);
			GuiCraftingRecipe.craftinghandlers.add(this);
			GuiUsageRecipe.usagehandlers.add(this);
		}
	}
	
	public class CachedGT_Recipe extends CachedRecipe {
		public List<PositionedStack> products;
		public List<PositionedStack> resources;
		public List<PositionedFluidStack> fluidProducts;
		public List<PositionedFluidStack> fluidResources;
		
		public List<Integer> nonConsumables;
		public Map<Integer, Integer> chanced;
		
		public int mDuration, mEUt;
		
		public CachedGT_Recipe(Recipe recipe, ItemStack activatedStack, boolean crafting) {
			resources 		= new ArrayList<>();
			products 		= new ArrayList<>();
			fluidResources	= new ArrayList<>();
			fluidProducts 	= new ArrayList<>();
			nonConsumables 	= new ArrayList<>();
			chanced			= new HashMap<>();
			
			List<Ingredient> inputs = recipe.getInputs();

			for (int i = 0; i < inputs.size(); i++) {
				Ingredient input = inputs.get(i);
				Pair<Integer, Integer> offsets = getInputAligment(i);
				if (input.getCount() == 0) {
					for (ItemStack s : input.getVariants()) {
						nonConsumables.add(GT_Utility.stackToInt(s));
					}
				}
				
				
				List<ItemStack> items = !crafting && activatedStack != null && input.match(activatedStack) ?
						Lists.newArrayList(GT_Utility.copyAmount(input.getCount() > 0 ? input.getCount() : 1, activatedStack)) :
							input.getVariants();
				resources.add(new FixedPositionedStack(items, offsets.getKey(), offsets.getValue()));
			}
			
			int offset = 0;
			List<ItemStack> outputs = recipe.getOutputs();
			for (offset = 0; offset < outputs.size(); offset++) {
				Pair<Integer, Integer> offsets = getOutputAligment(offset);
				products.add(new FixedPositionedStack(outputs.get(offset), offsets.getKey(), offsets.getValue()));
			}
			
			List<ChancedOutput> chanced = recipe.getChancedOutputs();
			for (int i = 0; i < chanced.size(); i++) {
				Pair<Integer, Integer> offsets = getOutputAligment(i + offset);
				ChancedOutput output = chanced.get(i);
				products.add(new FixedPositionedStack(output.getStack(), offsets.getKey(), offsets.getValue()));
				this.chanced.put(GT_Utility.stackToInt(output.getStack()), output.getChance());
			}
			
			List<FluidStack> fluidInputs = recipe.getFluidInputs();
			for (int i = 0; i < fluidInputs.size(); i++) {
				Pair<Integer, Integer> offsets = getFluidInputAligment(i);
				FluidStack fluid = fluidInputs.get(i);
				fluidResources.add(new PositionedFluidStack(fluid, offsets.getKey(), offsets.getValue()));
			}
			
			List<FluidStack> fluidOutputs = recipe.getFluidOutputs();
			for (int i = 0; i < fluidOutputs.size(); i++) {
				Pair<Integer, Integer> offsets = getFluidOutputAligment(i + offset);
				FluidStack fluid = fluidOutputs.get(i);
				fluidProducts.add(new PositionedFluidStack(fluid, offsets.getKey(), offsets.getValue()));
			}
			
			mDuration = recipe.getDuration();
			mEUt = recipe.getEUt();
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
		
		protected Pair<Integer, Integer> getFluidInputAligment(int itemIdx) {
			return Pair.of(18 * itemIdx, 0);
		}
		
		protected Pair<Integer, Integer> getFluidOutputAligment(int itemIdx) {
			return Pair.of((18 * itemIdx), 54);
		}
	}
	
	@Override
	public abstract String getRecipeName();
	
	public abstract String getRecipeId();
	
	public abstract String getGuiTexture();

	@Override
	public abstract String getOverlayIdentifier();
	
	public abstract RecipeMap<?> getRecipeList();
	
	public abstract CachedGT_Recipe getRecipe(Recipe irecipe, ItemStack activatedStack, boolean crafting);
	
	protected void provideTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, CachedGT_Recipe recipe, Point relMouse) {
		for (PositionedFluidStack fluid : recipe.fluidResources) {
			if (fluid.fits(relMouse)) {
				currenttip.add(fluid.fluid.getLocalizedName());
				currenttip.add(EnumChatFormatting.GRAY.toString() + I18n.format("metatileentity.fluid.amount", GT_Utility.parseNumberToString(fluid.fluid.amount)));
			}
		}
		
		for (PositionedFluidStack fluid : recipe.fluidProducts) {
			if (fluid.fits(relMouse)) {
				currenttip.add(fluid.fluid.getLocalizedName());
				currenttip.add(EnumChatFormatting.GRAY.toString() + I18n.format("metatileentity.fluid.amount", GT_Utility.parseNumberToString(fluid.fluid.amount)));
			}
		}
	}
	
	@Override
	public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipeId) {
		super.handleItemTooltip(gui, stack, currenttip, recipeId);
		CachedRecipe r = arecipes.get(recipeId);
		
		if (r instanceof CachedGT_Recipe) {
			CachedGT_Recipe recipe = (CachedGT_Recipe) r;
			if (GT_Utility.isStackValid(stack)) {
				Integer stackInt = GT_Utility.stackToInt(stack);
				if (recipe.nonConsumables.contains(stackInt)) {
					currenttip.add(I18n.format("item.non_consumable.tooltip"));
				} else if ((stackInt = recipe.chanced.get(stackInt)) != null) {
					currenttip.add(I18n.format("item.chanced.tooltip", GT_Utility.parseChanceString(stackInt)));
				}
			} else {
				Point mouse = GuiDraw.getMousePosition();
				Point offset = gui.getRecipePosition(recipeId);
				Point relMouse = new Point(mouse.x - (gui.width - 176) / 2 - offset.x, mouse.y - (gui.height - 166) / 2 - offset.y);
				provideTooltip(gui, stack, currenttip, recipe, relMouse);
			}
		}
		
		return currenttip;
	}
	
    @Override
    public boolean keyTyped(GuiRecipe gui, char keyChar, int keyCode, int recipe) {
        if (keyCode == NEIClientConfig.getKeyBinding("gui.recipe")) {
            if (transferFluid(gui, recipe, false)) {
                return true;
            }
        } else if (keyCode == NEIClientConfig.getKeyBinding("gui.usage")) {
            if (transferFluid(gui, recipe, true)) {
                return true;
            }
        }
        return super.keyTyped(gui, keyChar, keyCode, recipe);
    }
    
    @Override
    public boolean mouseClicked(GuiRecipe gui, int button, int recipe) {
        if (button == 0) {
            if (transferFluid(gui, recipe, false)) {
                return true;
            }
        } else if (button == 1) {
            if (transferFluid(gui, recipe, true)) {
                return true;
            }
        }
        return super.mouseClicked(gui, button, recipe);
    }
	
	protected boolean transferFluid(GuiRecipe guiRecipe, int recipe, boolean usage) {
        CachedGT_Recipe crecipe = (CachedGT_Recipe) this.arecipes.get(recipe);
        Point mousepos = GuiDraw.getMousePosition();
        Point offset = guiRecipe.getRecipePosition(recipe);
        Point relMouse = new Point(mousepos.x - (guiRecipe.width - 176) / 2 - offset.x, mousepos.y - (guiRecipe.height - 166) / 2 - offset.y);
        
        for (PositionedFluidStack stack : crecipe.fluidResources) {
            if (stack.fits(relMouse)) {
                return stack.transfer(usage);
            }
        }
        
        for (PositionedFluidStack stack : crecipe.fluidProducts) {
            if (stack.fits(relMouse)) {
                return stack.transfer(usage);
            }
        }
        
        return false;
    }
	
	@Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        codechicken.lib.gui.GuiDraw.changeTexture(getGuiTexture());
        codechicken.lib.gui.GuiDraw.drawTexturedModalRect(0, 0, sOffsetX, sOffsetY, 168, 79);
    }
    
	@Override
    public void drawForeground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        GL11.glDisable(GL11.GL_LIGHTING);
        changeTexture(getGuiTexture());
        drawFluidStacks(recipe);
        drawExtras(recipe);
    }
	
	public void drawFluidStacks(int recipe) {
		CachedGT_Recipe cached = (CachedGT_Recipe) arecipes.get(recipe);
		for (PositionedFluidStack fluid : cached.fluidResources)
			fluid.draw();
		for (PositionedFluidStack fluid : cached.fluidProducts)
			fluid.draw();
	}
	
	@Override
	public int recipiesPerPage() {
		return 1;
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("liquid") && results[0] instanceof FluidStack && ((FluidStack) results[0]).getFluid() != null) {
			this.loadCraftingRecipes((FluidStack) results[0]);
		} else if(outputId.equals(getRecipeId())) {
			for (Recipe irecipe : getRecipeList().getRecipes()) {
				arecipes.add(getRecipe(irecipe, null, false));
			}
		} else super.loadCraftingRecipes(outputId, results);
	}
	
    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("liquid") && ingredients[0] instanceof FluidStack && ((FluidStack) ingredients[0]).getFluid() != null) {
            this.loadUsageRecipes((FluidStack) ingredients[0]);
        } else {
            super.loadUsageRecipes(inputId, ingredients);
        }
    }
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		FluidStack fluid = GT_Utility.getFluidForFilledItem(result);
		for (Recipe irecipe : getRecipeList().getRecipes()) {
			CachedGT_Recipe recipe = getRecipe(irecipe, result, true);
			if (recipe.contains(recipe.products,result) || irecipe.getFluidOutputs().contains(fluid)) {
				arecipes.add(recipe);
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		FluidStack fluid = GT_Utility.getFluidForFilledItem(ingredient);
		for (Recipe irecipe : getRecipeList().getRecipes()) {
			CachedGT_Recipe recipe = getRecipe(irecipe, ingredient, false);
			if (recipe.contains(recipe.resources,ingredient) || irecipe.getFluidInputs().contains(fluid)) {
				arecipes.add(recipe);
			}
		}
	}
	
    public void loadUsageRecipes(FluidStack ingredient) {
    	for (Recipe irecipe : getRecipeList().getRecipes()) {
			if (irecipe.getFluidInputs().contains(ingredient)) {
				arecipes.add(getRecipe(irecipe, null, false));
				continue;
			}
			
			for (Ingredient ingr : irecipe.getInputs()) {
				ItemStack cell = GT_Utility.fillFluidContainer(ingredient, GT_Items.Cell_Empty.get(1));
				if (ingr.match(cell)) {
					arecipes.add(getRecipe(irecipe, cell, false));
				}
			}
		}
    }
    
    public void loadCraftingRecipes(FluidStack result) {
		for (Recipe irecipe : getRecipeList().getRecipes()) {
			if (irecipe.getFluidOutputs().contains(result)) {
				arecipes.add(getRecipe(irecipe, null, true));
				continue;
			}
			
			for (ItemStack stack : irecipe.getAllOutputs()) {
				FluidStack stack1 = GT_Utility.getFluidForFilledItem(stack);
				if (stack1 != null && result.isFluidEqual(stack1)) {
					arecipes.add(getRecipe(irecipe, stack, true));
				}
			}
		}
    }
    
	public void drawText(int aX, int aY, String aString, int aColor, boolean aB) {
		Minecraft.getMinecraft().fontRenderer.drawString(aString, aX, aY, aColor);
	}
}
