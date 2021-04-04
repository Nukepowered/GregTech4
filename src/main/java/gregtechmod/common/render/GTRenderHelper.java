package gregtechmod.common.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * Helper class for rendering
 * 
 * DO Not forget to use glPushAttrib and glPushMatrix, and pop it at the end!
 * 
 * @author TheDarkDnKTv
 */
@SideOnly(Side.CLIENT)
public class GTRenderHelper {
	
	public static final Minecraft mc;
	public static final Tessellator tess;
	
	static {
		mc =  Minecraft.getMinecraft();
		tess = Tessellator.instance;
	}
	
	public static void drawStackAmount(int posX, int posY, int color, String str) {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        mc.fontRenderer.drawStringWithShadow(str, posX + 19 - 2 - mc.fontRenderer.getStringWidth(str), posY + 6 + 3, color);
	}
	
	public static void drawSlotDim(double posX, double posY, double zIndex, double width, double height) {
		 GL11.glDisable(GL11.GL_LIGHTING);
         GL11.glDisable(GL11.GL_TEXTURE_2D);
         GL11.glDisable(GL11.GL_ALPHA_TEST);
         GL11.glEnable(GL11.GL_BLEND);
         GL11.glColorMask(true, true, true, false);
         OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
         GL11.glShadeModel(GL11.GL_SMOOTH);
         tess.startDrawingQuads();
         tess.setColorRGBA_F(1.0F, 1.0F, 1.0F, 128 / 255.0F);
         GTRenderHelper.addQuad(posX, posY, zIndex, 16, 16);
         tess.draw();
	}
	
	public static void bindTexture(ResourceLocation res) {
		mc.renderEngine.bindTexture(res);
	}
	
	public static void drawQuad(double posX, double posY, double zIndex, double width, double height) {
		tess.startDrawingQuads();
		addQuad(posX, posY, zIndex, width, height);
		tess.draw();
	}
	
	public static void drawQuad(double posX, double posY, double zIndex, IIcon icon) {
		tess.startDrawingQuads();
		addQuadWithUV(posX, posY, zIndex, icon);
		tess.draw();
	}
	
	public static void drawQuad(double posX, double posY, double zIndex, double width, double height, double minU, double maxU, double minV, double maxV) {
		tess.startDrawingQuads();
		addQuadWithUV(posX, posY, zIndex, width, height, minU, maxU, minV, maxV);
		tess.draw();
	}
	
	public static void addQuad(double posX, double posY, double zIndex, double width, double height) {
        tess.addVertex(posX + width	, posY			, zIndex);
        tess.addVertex(posX			, posY			, zIndex);
        tess.addVertex(posX			, posY + height	, zIndex);
        tess.addVertex(posX + width	, posY + height	, zIndex);
	}
	
	public static void addQuadWithUV(double posX, double posY, double zIndex, IIcon icon) {
		addQuadWithUV(posX, posY, zIndex, icon.getIconWidth(), icon.getIconHeight(), icon.getMinU(), icon.getMaxU(), icon.getMinV(), icon.getMaxV());
	}
	
	public static void addQuadWithUV(double posX, double posY, double zIndex, double width, double height, double minU, double maxU, double minV, double maxV) {
		tess.addVertexWithUV(posX + width, posY			, zIndex, maxU, minV);
		tess.addVertexWithUV(posX		 , posY			, zIndex, minU, minV);
		tess.addVertexWithUV(posX		 , posY + height, zIndex, minU, maxV);
		tess.addVertexWithUV(posX + width, posY + height, zIndex, maxU, maxV);
	}
	
}
