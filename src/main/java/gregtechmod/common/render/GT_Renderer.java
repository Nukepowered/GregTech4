package gregtechmod.common.render;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GT_Renderer extends RenderPlayer {
	
	public GT_Renderer() {
		MinecraftForge.EVENT_BUS.register(this);
		setRenderManager(RenderManager.instance);
	}
	
	@SubscribeEvent
	public void receiveRenderEvent(RenderPlayerEvent.Pre aEvent) {
		if (GT_Utility.getFullInvisibility(aEvent.entityPlayer)) {aEvent.setCanceled(true); return;}
	}
	
	@SubscribeEvent
	public void receiveRenderSpecialsEvent(RenderPlayerEvent.Specials.Pre aEvent) {
		AbstractClientPlayer aPlayer = (AbstractClientPlayer)aEvent.entityPlayer;
		float aPartialTicks = aEvent.partialRenderTick;
		
		if (GT_Utility.getFullInvisibility(aPlayer)) {aEvent.setCanceled(true); return;}
		
		if (aPlayer.isInvisible()) return;
        if (GT_Utility.getPotion(aPlayer, Integer.valueOf(Potion.invisibility.id))) return;
        
        try {
            ResourceLocation tResource = null;
            
            if (GT_Mod.sAdminNames.contains(aPlayer.getDisplayName().toLowerCase()))
               	tResource = new ResourceLocation(GregTech_API.MOD_ID, "textures/AdminCape.png");
	    	if (GT_Mod.sPremiumNames.contains(aPlayer.getDisplayName().toLowerCase()))
	            tResource = new ResourceLocation(GregTech_API.MOD_ID, "textures/PremiumCape.png");
		    if (GT_Mod.mBrainTechCapeList.contains(aPlayer.getDisplayName().toLowerCase()))
	           	tResource = new ResourceLocation(GregTech_API.MOD_ID, "textures/BrainTechCape.png");
	    	if (GT_Mod.mGregTechCapeList.contains(aPlayer.getDisplayName().toLowerCase()))
	            tResource = new ResourceLocation(GregTech_API.MOD_ID, "textures/GregTechCape.png");
    		if (aPlayer.getDisplayName().equalsIgnoreCase("Mr_Brain"))
    			tResource = new ResourceLocation(GregTech_API.MOD_ID, "textures/MrBrainCape.png");
    		if (aPlayer.getDisplayName().equalsIgnoreCase("GregoriusT"))
    			tResource = new ResourceLocation(GregTech_API.MOD_ID, "textures/GregoriusCape.png");
    		if (aPlayer.getDisplayName().equalsIgnoreCase("The_DnK"))
    			tResource = new ResourceLocation(GregTech_API.MOD_ID, "textures/GregTechCape.png");
    		
    		
	        if (!aPlayer.getHideCape()) {
	        	bindTexture(tResource);
	            GL11.glPushMatrix();
	            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
	            double d0 = aPlayer.field_71091_bM + (aPlayer.field_71094_bP - aPlayer.field_71091_bM) * (double)aPartialTicks - (aPlayer.prevPosX + (aPlayer.posX - aPlayer.prevPosX) * (double)aPartialTicks);
	            double d1 = aPlayer.field_71096_bN + (aPlayer.field_71095_bQ - aPlayer.field_71096_bN) * (double)aPartialTicks - (aPlayer.prevPosY + (aPlayer.posY - aPlayer.prevPosY) * (double)aPartialTicks);
	            double d2 = aPlayer.field_71097_bO + (aPlayer.field_71085_bR - aPlayer.field_71097_bO) * (double)aPartialTicks - (aPlayer.prevPosZ + (aPlayer.posZ - aPlayer.prevPosZ) * (double)aPartialTicks);
	            float f6 = aPlayer.prevRenderYawOffset + (aPlayer.renderYawOffset - aPlayer.prevRenderYawOffset) * aPartialTicks;
	            double d3 = (double)MathHelper.sin(f6 * (float)Math.PI / 180.0F);
	            double d4 = (double)(-MathHelper.cos(f6 * (float)Math.PI / 180.0F));
	            float f7 = (float)d1 * 10.0F;
	            float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
	            float f9 = (float)(d0 * d4 - d2 * d3) * 100.0F;
	            if (f7 < -6.0F) f7 = -6.0F;
	            if (f7 > 32.0F) f7 = 32.0F;
	            if (f8 <  0.0F) f8 =  0.0F;
	            float f10 = aPlayer.prevCameraYaw + (aPlayer.cameraYaw - aPlayer.prevCameraYaw) * aPartialTicks;
	            f7 += MathHelper.sin((aPlayer.prevDistanceWalkedModified + (aPlayer.distanceWalkedModified - aPlayer.prevDistanceWalkedModified) * aPartialTicks) * 6.0F) * 32.0F * f10;
	            if (aPlayer.isSneaking()) {
	                f7 += 25.0F;
	            }
	            GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
	            GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
	            GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				((ModelBiped)mainModel).renderCloak(0.0625F);
	            GL11.glPopMatrix();
	        }
	    } catch (Throwable e) {
	    	if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
	    }
	}
}