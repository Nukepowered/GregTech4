package gregtechmod.mistaqur.nei;

import java.awt.Point;
import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class PositionedFluidStack {
	
	public int x;
	public int y;
	public FluidStack fluid;
	/**
	 * 
	 */
	public PositionedFluidStack(FluidStack fluid, int x, int y) {
		this.x = x;
		this.y = y;
		this.fluid = fluid;
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
		if (GT_Utility.isFluidStackValid(fluid)) {
			IIcon fluidIcon = fluid.getFluid().getIcon(fluid);
			fluidIcon = fluidIcon != null ? fluidIcon : fluid.getFluid().getFlowingIcon();
			fluidIcon = fluidIcon != null ? fluidIcon : fluid.getFluid().getStillIcon();
			if (fluidIcon == null) return;
			
			GuiDraw.changeTexture(TextureMap.locationBlocksTexture);
			int color = fluid.getFluid().getColor(fluid);
			GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
			GL11.glDisable(GL11.GL_BLEND);
			
			double minU = fluidIcon.getMinU();
            double maxU = fluidIcon.getMaxU();
            double minV = fluidIcon.getMinV();
            double maxV = fluidIcon.getMaxV();
            
            Tessellator.instance.startDrawingQuads();
            Tessellator.instance.addVertexWithUV(x		, y + 16	, 0, maxU, minV);
            Tessellator.instance.addVertexWithUV(x + 16	, y + 16	, 0, minU, minV);
            Tessellator.instance.addVertexWithUV(x + 16	, y			, 0, minU, maxV);
            Tessellator.instance.addVertexWithUV(x		, y			, 0, maxU, maxV);
            Tessellator.instance.draw();
            
            GL11.glEnable(GL11.GL_BLEND);
		}
	}
	
	public boolean fits(Point point) {
		return new Rectangle(x, y, 16, 16).contains(point);
	}
}
