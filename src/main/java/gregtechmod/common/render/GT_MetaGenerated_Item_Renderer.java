package gregtechmod.common.render;

import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;

public class GT_MetaGenerated_Item_Renderer implements IItemRenderer {

   public GT_MetaGenerated_Item_Renderer() {
      for (GT_MetaGenerated_Item tItem : GT_MetaGenerated_Item.sInstances.values()) {
    	  if (tItem != null && tItem.useStandardMetaItemRenderer()) {
    		  MinecraftForgeClient.registerItemRenderer(tItem, this);
    	  }
      }
   }

   public boolean handleRenderType(ItemStack aStack, ItemRenderType aType) {
      return !GT_Utility.isStackInvalid(aStack) && aStack.getItemDamage() >= 0?aType == ItemRenderType.EQUIPPED_FIRST_PERSON || aType == ItemRenderType.INVENTORY || aType == ItemRenderType.EQUIPPED || aType == ItemRenderType.ENTITY:false;
   }

   public boolean shouldUseRenderHelper(ItemRenderType aType, ItemStack aStack, ItemRendererHelper aHelper) {
      return GT_Utility.isStackInvalid(aStack)?false:aType == ItemRenderType.ENTITY;
   }

   public void renderItem(ItemRenderType type, ItemStack aStack, Object ... data) {
      if(!GT_Utility.isStackInvalid(aStack)) {
         short aMetaData = (short)aStack.getItemDamage();
         GT_MetaGenerated_Item aItem = (GT_MetaGenerated_Item)aStack.getItem();
         GL11.glEnable(3042);
         if(type == ItemRenderType.ENTITY) {
            if(RenderItem.renderInFrame) {
               GL11.glScalef(0.85F, 0.85F, 0.85F);
               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslated(-0.5D, -0.42D, 0.0D);
            } else {
               GL11.glTranslated(-0.5D, -0.42D, 0.0D);
            }
         }

         GL11.glColor3f(1.0F, 1.0F, 1.0F);
         IIcon tIcon;
         if(aMetaData < 32000) {
            IIconContainer tStats = aItem.getIconContainer(aMetaData);
            IIcon tCharge = null;
            IIcon tFluidIcon = null;
            if(tStats == null) {
               tIcon = aStack.getIconIndex();
            } else {
               tIcon = tStats.getIcon();
               tCharge = tStats.getOverlayIcon();
            }

            if(tIcon == null) {
               return;
            }

            if(tCharge != null) {
               FluidStack tModulation = GT_Utility.getFluidForFilledItem(aStack);
               if(tModulation != null) {
                  tFluidIcon = tModulation.getFluid().getIcon(tModulation);
               }
            }

            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
            GL11.glBlendFunc(770, 771);
            if(tFluidIcon == null) {
               short[] tModulation1 = aItem.getRGBa(aStack);
               GL11.glColor3f((float)tModulation1[0] / 255.0F, (float)tModulation1[1] / 255.0F, (float)tModulation1[2] / 255.0F);
            }

            if(type.equals(ItemRenderType.INVENTORY)) {
               renderIcon(tIcon, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
            } else {
               ItemRenderer.renderItemIn2D(Tessellator.instance, tIcon.getMaxU(), tIcon.getMinV(), tIcon.getMinU(), tIcon.getMaxV(), tIcon.getIconWidth(), tIcon.getIconHeight(), 0.0625F);
            }

            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            if(tFluidIcon != null) {
               Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
               GL11.glBlendFunc(770, 771);
               GL11.glDepthFunc(514);
               if(type.equals(ItemRenderType.INVENTORY)) {
                  renderIcon(tFluidIcon, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
               } else {
                  ItemRenderer.renderItemIn2D(Tessellator.instance, tFluidIcon.getMaxU(), tFluidIcon.getMinV(), tFluidIcon.getMinU(), tFluidIcon.getMaxV(), tFluidIcon.getIconWidth(), tFluidIcon.getIconHeight(), 0.0625F);
               }

               GL11.glDepthFunc(515);
            }

            if(tCharge != null) {
               Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
               GL11.glBlendFunc(770, 771);
               if(type.equals(ItemRenderType.INVENTORY)) {
                  renderIcon(tCharge, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
               } else {
                  ItemRenderer.renderItemIn2D(Tessellator.instance, tCharge.getMaxU(), tCharge.getMinV(), tCharge.getMinU(), tCharge.getMaxV(), tCharge.getIconWidth(), tCharge.getIconHeight(), 0.0625F);
               }
            }
         } else {
            Integer[] tStats1 = (Integer[])aItem.mElectricStats.get(Short.valueOf(aMetaData));
            if(tStats1 != null && tStats1[3].intValue() < 0) {
               long tCharge1 = (long)aItem.getCharge(aStack);
               if(tCharge1 <= 0L) {
                  tIcon = aItem.mIconList[aMetaData - 32000][1];
               } else if(tCharge1 >= (long)tStats1[0].intValue()) {
                  tIcon = aItem.mIconList[aMetaData - 32000][8];
               } else {
                  tIcon = aItem.mIconList[aMetaData - 32000][7 - (int)Math.max(0L, Math.min(5L, ((long)tStats1[0].intValue() - tCharge1) * 6L / (long)tStats1[0].intValue()))];
               }
            } else {
               tIcon = aItem.mIconList[aMetaData - 32000][0];
            }

            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
            GL11.glBlendFunc(770, 771);
            if(type.equals(ItemRenderType.INVENTORY)) {
               renderIcon(tIcon, 16.0D, 0.001D, 0.0F, 0.0F, -1.0F);
            } else {
               ItemRenderer.renderItemIn2D(Tessellator.instance, tIcon.getMaxU(), tIcon.getMinV(), tIcon.getMinU(), tIcon.getMaxV(), tIcon.getIconWidth(), tIcon.getIconHeight(), 0.0625F);
            }
         }

         GL11.glDisable(3042);
      }
   }

   public static void renderIcon(IIcon icon, double size, double z, float nx, float ny, float nz) {
      renderIcon(icon, 0.0D, 0.0D, size, size, z, nx, ny, nz);
   }

   public static void renderIcon(IIcon icon, double xStart, double yStart, double xEnd, double yEnd, double z, float nx, float ny, float nz) {
      if(icon != null) {
         Tessellator.instance.startDrawingQuads();
         Tessellator.instance.setNormal(nx, ny, nz);
         if(nz > 0.0F) {
            Tessellator.instance.addVertexWithUV(xStart, yStart, z, (double)icon.getMinU(), (double)icon.getMinV());
            Tessellator.instance.addVertexWithUV(xEnd, yStart, z, (double)icon.getMaxU(), (double)icon.getMinV());
            Tessellator.instance.addVertexWithUV(xEnd, yEnd, z, (double)icon.getMaxU(), (double)icon.getMaxV());
            Tessellator.instance.addVertexWithUV(xStart, yEnd, z, (double)icon.getMinU(), (double)icon.getMaxV());
         } else {
            Tessellator.instance.addVertexWithUV(xStart, yEnd, z, (double)icon.getMinU(), (double)icon.getMaxV());
            Tessellator.instance.addVertexWithUV(xEnd, yEnd, z, (double)icon.getMaxU(), (double)icon.getMaxV());
            Tessellator.instance.addVertexWithUV(xEnd, yStart, z, (double)icon.getMaxU(), (double)icon.getMinV());
            Tessellator.instance.addVertexWithUV(xStart, yStart, z, (double)icon.getMinU(), (double)icon.getMinV());
         }

         Tessellator.instance.draw();
      }
   }
}
