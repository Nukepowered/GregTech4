package gregtechmod.integration.nei;

import codechicken.nei.NEIClientConfig;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerInputHandler;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;

import gregtechmod.api.gui.GT_FluidSlot;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class GT_NEIInputHandler implements IContainerInputHandler {
	
	@Override
	public boolean lastKeyTyped(GuiContainer gui, char keyChar, int keyID) {
		if (keyID == NEIClientConfig.getKeyBinding("gui.usage") || keyID == NEIClientConfig.getKeyBinding("gui.recipe")) {
			Slot slot = GuiContainerManager.getSlotMouseOver(gui);
			if (slot != null && slot instanceof GT_FluidSlot) {
				FluidStack fluid = ((GT_FluidSlot) slot).getFluid();
				if (GT_Utility.isFluidStackValid(fluid)) {
					return keyID == NEIClientConfig.getKeyBinding("gui.usage") ? GuiUsageRecipe.openRecipeGui("liquid", fluid.copy()) : GuiCraftingRecipe.openRecipeGui("liquid", fluid.copy());
				}
			}
		}
		
		return false;
	}

	@Override public boolean mouseClicked(GuiContainer gui, int mousex, int mousey, int button) { return false; }
	@Override public boolean keyTyped(GuiContainer gui, char keyChar, int keyCode) { return false; }
	@Override public void onKeyTyped(GuiContainer gui, char keyChar, int keyID) {}
	@Override public void onMouseClicked(GuiContainer gui, int mousex, int mousey, int button) {}
	@Override public void onMouseUp(GuiContainer gui, int mousex, int mousey, int button) {}
	@Override public boolean mouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) { return false; }
	@Override public void onMouseScrolled(GuiContainer gui, int mousex, int mousey, int scrolled) {}
	@Override public void onMouseDragged(GuiContainer gui, int mousex, int mousey, int button, long heldTime) {}
}
