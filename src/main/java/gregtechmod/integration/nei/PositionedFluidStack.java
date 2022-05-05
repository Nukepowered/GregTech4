package gregtechmod.integration.nei;

import java.awt.Point;
import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.render.GTRenderHelper;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class PositionedFluidStack {
	
	@SideOnly(Side.CLIENT)
	protected static ResourceLocation DEFAULT_SLOT_OVERLAY;
	protected boolean renderOverlay;
	
	public int x;
	public int y;
	public FluidStack fluid;
	
	static {
		if (FMLCommonHandler.instance().getSide().isClient()) {
			DEFAULT_SLOT_OVERLAY = new ResourceLocation(GregTech_API.GUI_PATH + "overlays/FluidSlot.png");
		}
	}
	
	PositionedFluidStack(FluidStack fluid, int x, int y) {
		this(fluid, x, y, false);
	}
	
	PositionedFluidStack(FluidStack fluid, int x, int y, boolean renderOverlay) {
		this.x = x;
		this.y = y;
		this.fluid = fluid;
		this.renderOverlay = renderOverlay;
	}
	
	public boolean transfer(boolean usage) {
        if (GT_Utility.isFluidStackValid(fluid)) {
            if (usage) {
                if (!GuiUsageRecipe.openRecipeGui("liquid", new Object[] { fluid })) {
                    return false;
                }
            } else {
                if (!GuiCraftingRecipe.openRecipeGui("liquid", new Object[] { fluid })) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	public void draw() {
		if (renderOverlay) {
			GTRenderHelper.bindTexture(DEFAULT_SLOT_OVERLAY);
			GTRenderHelper.drawQuad(x-1, y-1, 0, 18, 18, 0.0F, 1.0F, 0.0F, 1.0F);
		}
		
		if (GT_Utility.isFluidStackValid(fluid)) {
			IIcon fluidIcon = fluid.getFluid().getIcon(fluid);
			fluidIcon = fluidIcon != null ? fluidIcon : fluid.getFluid().getFlowingIcon();
			fluidIcon = fluidIcon != null ? fluidIcon : fluid.getFluid().getStillIcon();
			if (fluidIcon == null) return;
			GTRenderHelper.bindTexture(TextureMap.locationBlocksTexture);
			int color = fluid.getFluid().getColor(fluid);
			GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
			GL11.glDisable(GL11.GL_BLEND);
			GTRenderHelper.drawQuad(x, y, 0, 16, 16, fluidIcon.getMinU(), fluidIcon.getMaxU(), fluidIcon.getMinV(), fluidIcon.getMaxV());
            GL11.glEnable(GL11.GL_BLEND);
		}
	}
	
	public boolean fits(Point point) {
		return new Rectangle(x, y, 16, 16).contains(point);
	}
}
