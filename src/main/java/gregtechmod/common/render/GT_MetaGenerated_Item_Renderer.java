package gregtechmod.common.render;

import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import gregtechmod.api.util.GT_Utility;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;


public class GT_MetaGenerated_Item_Renderer implements IItemRenderer {

	protected static final ResourceLocation ENCHANT_GLITH = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	protected TextureManager manager;
	
	public GT_MetaGenerated_Item_Renderer() {
		for (GT_MetaGenerated_Item tItem : GT_MetaGenerated_Item.sInstances.values()) {
			if (tItem != null && tItem.useStandardMetaItemRenderer()) {
				MinecraftForgeClient.registerItemRenderer(tItem, this);
			}
		}
	}

	public boolean handleRenderType(ItemStack aStack, ItemRenderType aType) {
		if (manager == null) manager = Minecraft.getMinecraft().renderEngine;
		return !GT_Utility.isStackInvalid(aStack) && aStack.getItemDamage() >= 0 ? aType != ItemRenderType.FIRST_PERSON_MAP : false;
	}

	public boolean shouldUseRenderHelper(ItemRenderType aType, ItemStack aStack, ItemRendererHelper aHelper) {
		return GT_Utility.isStackInvalid(aStack) ? false : aType == ItemRenderType.ENTITY;
	}

	public void renderItem(ItemRenderType type, ItemStack aStack, Object... data) {
		if (!GT_Utility.isStackInvalid(aStack)) {
			GT_MetaGenerated_Item item = (GT_MetaGenerated_Item) aStack.getItem();
			int meta = aStack.getItemDamage();

			GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT);
			GL11.glPushMatrix();
			{
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glEnable(GL11.GL_BLEND);
				if (type == ItemRenderType.ENTITY) {
					if (RenderItem.renderInFrame) {
						GL11.glScalef(0.85F, 0.85F, 0.85F);
						GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
						GL11.glTranslated(-0.5D, -0.42D, 0.0D);
					} else {
						GL11.glTranslated(-0.5D, -0.42D, 0.0D);
					}
				}

				IIcon icon;
				if (meta < 32000) {
					IIconContainer tStats = item.getIconContainer(meta);
					IIcon secondIcon = null;
					IIcon fluidIcon = null;
					if (tStats == null) {
						icon = aStack.getIconIndex();
					} else {
						icon = tStats.getIcon();
						if ((secondIcon = tStats.getOverlayIcon()) != null) {
							FluidStack tModulation = GT_Utility.getFluidForFilledItem(aStack);
							if (tModulation != null) {
								fluidIcon = tModulation.getFluid().getIcon(tModulation);
							}
						}
					}

					if (icon == null) return;
					if (fluidIcon == null) {
						short[] tModulation1 = item.getRGBa(aStack);
						GL11.glColor3f((float) tModulation1[0] / 255.0F, (float) tModulation1[1] / 255.0F, (float) tModulation1[2] / 255.0F);
					}
					
					manager.bindTexture(TextureMap.locationItemsTexture);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					this.renderIcon(type, icon);
					GL11.glColor3f(1.0F, 1.0F, 1.0F);
					if (fluidIcon != null) {
						manager.bindTexture(TextureMap.locationBlocksTexture);
						GL11.glDepthFunc(GL11.GL_EQUAL);
						this.renderIcon(type, fluidIcon);
						GL11.glDepthFunc(GL11.GL_LEQUAL);
					}
					
					if (secondIcon != null) {
						manager.bindTexture(TextureMap.locationItemsTexture);
						this.renderIcon(type, secondIcon);
					}
				} else {
					Integer[] tStats1 = item.mElectricStats.get((short)meta);
					if (tStats1 != null && tStats1[3] < 0) {
						long tCharge1 = (long) item.getCharge(aStack);
						if (tCharge1 <= 0L) {
							icon = item.mIconList[meta - 32000][1];
						} else if (tCharge1 >= tStats1[0]) {
							icon = item.mIconList[meta - 32000][8];
						} else {
							icon = item.mIconList[meta - 32000][7 - (int) Math.max(0, Math.min(5, (tStats1[0] - tCharge1) * 6 / tStats1[0]))];
						}
					} else {
						icon = item.mIconList[meta - 32000][0];
					}

					manager.bindTexture(TextureMap.locationItemsTexture);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					this.renderIcon(type, icon);
				}
				
				if (aStack.hasEffect(0))
					this.renderEffect(type);
			}
			GL11.glPopMatrix();
			GL11.glPopAttrib();
		}
	}
	
	protected void renderEffect(ItemRenderType type) {
		if (type == ItemRenderType.INVENTORY) {
			this.renderEnchantGUI();
		} else {
			this.renderEnchant3D();
		}
	}
	
	/**
	 * Render an enchant glith in 3D
	 * Example: item being held in hand
	 */
	protected void renderEnchant3D() {
		GL11.glDepthFunc(GL11.GL_EQUAL);
		manager.bindTexture(ENCHANT_GLITH);
		GL14.glBlendFuncSeparate(GL11.GL_SRC_COLOR, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ZERO);
		float brightness = 0.76F;
		float moveOffset = (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
		GL11.glColor4f(0.5F * brightness, 0.25F * brightness, 0.8F * brightness, 1.0F);
		GL11.glMatrixMode(GL11.GL_TEXTURE);
		GL11.glPushMatrix();
		{
			GL11.glScalef(0.125F, 0.125F, 0.125F);
			GL11.glTranslatef(moveOffset, 0.0F, 0.0F);
			GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
			ItemRenderer.renderItemIn2D(Tessellator.instance, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
		}
		GL11.glPopMatrix();
		moveOffset = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
		GL11.glPushMatrix();
		{
			GL11.glScalef(0.125F, 0.125F, 0.125F);
			GL11.glTranslatef(-moveOffset, 0.0F, 0.0F);
			GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
			ItemRenderer.renderItemIn2D(Tessellator.instance, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
		}
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
	}

	/**
	 * Render enchant glith in GUI slot
	 */
	protected void renderEnchantGUI() {

		
		GL11.glDepthFunc(GL11.GL_EQUAL);
		GL11.glDepthMask(false);
		manager.bindTexture(ENCHANT_GLITH);
		GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_DST_COLOR);		
		GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
		this.renderGlith();
		GL11.glDepthMask(true);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
	}

	protected void renderGlith() {
		GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
		for (int i = 0; i < 2; i++) {
			float scale = 1 / 16f;
			float uOffset = (float) (Minecraft.getSystemTime() % (long) (3000 + i * 1873)) / (3000.0F + (float) (i * 1873)) * 16.0F;
			float uExtraOffset = 4.0F;
			Tessellator.instance.startDrawingQuads();
			Tessellator.instance.addVertexWithUV(0		, 16, 0.0D, (uOffset + 1 * uExtraOffset) * scale	, 1 * scale);
			Tessellator.instance.addVertexWithUV(16 + 1	, 16, 0.0D, (uOffset + 1 + 1 * uExtraOffset) * scale, 1 * scale);
			Tessellator.instance.addVertexWithUV(16 + 1	, 0 , 0.0D, (uOffset + 1) * scale					, 0.0F);
			Tessellator.instance.addVertexWithUV(0		, 0 , 0.0D, uOffset * scale							, 0.0F);
			Tessellator.instance.draw();
			uExtraOffset = -1.0F;
		}
	}
	
	protected void renderIcon(ItemRenderType type, IIcon icon) {
		if (type == ItemRenderType.INVENTORY) {
			renderIcon(icon, 16.0D, 0.0D, 0.0F, 0.0F, -1.0F);
		} else {
			ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
		}
	}
	
	protected void renderIcon(IIcon icon, double size, double z, float nx, float ny, float nz) {
		renderIcon(icon, 0.0D, 0.0D, size, size, z, nx, ny, nz);
	}

	protected void renderIcon(IIcon icon, double xStart, double yStart, double xEnd, double yEnd, double z,
			float nx, float ny, float nz) {
		if (icon != null) {
			Tessellator.instance.startDrawingQuads();
			Tessellator.instance.setNormal(nx, ny, nz);
			if (nz > 0.0F) {
				Tessellator.instance.addVertexWithUV(xStart, yStart, z, (double) icon.getMinU(),
						(double) icon.getMinV());
				Tessellator.instance.addVertexWithUV(xEnd, yStart, z, (double) icon.getMaxU(), (double) icon.getMinV());
				Tessellator.instance.addVertexWithUV(xEnd, yEnd, z, (double) icon.getMaxU(), (double) icon.getMaxV());
				Tessellator.instance.addVertexWithUV(xStart, yEnd, z, (double) icon.getMinU(), (double) icon.getMaxV());
			} else {
				Tessellator.instance.addVertexWithUV(xStart, yEnd, z, (double) icon.getMinU(), (double) icon.getMaxV());
				Tessellator.instance.addVertexWithUV(xEnd, yEnd, z, (double) icon.getMaxU(), (double) icon.getMaxV());
				Tessellator.instance.addVertexWithUV(xEnd, yStart, z, (double) icon.getMaxU(), (double) icon.getMinV());
				Tessellator.instance.addVertexWithUV(xStart, yStart, z, (double) icon.getMinU(),
						(double) icon.getMinV());
			}

			Tessellator.instance.draw();
		}
	}
}
