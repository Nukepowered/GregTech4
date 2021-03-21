package gregtechmod.common.network;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.GT_GUIHandler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.ForgeHooks;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GT_ConnectionHandler implements Runnable {
	
	@Override
	public void run() {
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void clientLoggedIn(ClientConnectedToServerEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(GregTech_API.gregtechmod, new GT_GUIHandler());
	}
	
	@SubscribeEvent
	public void playerLoggedIn(ServerConnectionFromClientEvent event) {
		NetHandlerPlayServer handler = (NetHandlerPlayServer) event.handler;
		EntityPlayerMP aPlayer = handler.playerEntity;
		if (aPlayer == null) return; 
		String aUserName = aPlayer.getDisplayName();
		if (!GT_Mod.mAlreadyPlayed || aUserName.equalsIgnoreCase("richardg867")) {
			handler.sendPacket(new S02PacketChat(new ChatComponentTranslation("gregtechmod.log_in.text_1")));
			handler.sendPacket(new S02PacketChat(new ChatComponentTranslation("gregtechmod.log_in.text_2")));
			IChatComponent comp = ForgeHooks.newChatWithLinks("https://github.com/Nukepowered/GregTech4");
			comp.getChatStyle().setColor(EnumChatFormatting.YELLOW);
			handler.sendPacket(new S02PacketChat(new ChatComponentText("Github page: ").appendSibling(comp)));
			handler.sendPacket(new S02PacketChat(new ChatComponentText("~ Gregorius Techneticies, TheDarkDnKTv")));
			
			if (aUserName.equalsIgnoreCase("richardg867")) {
				handler.sendPacket(new S02PacketChat(new ChatComponentText("Is that enough of a disclaimer for you RichardG?")));
			}
			
			GT_Mod.mAlreadyPlayed = true;
		}
		if (GT_Mod.sMessage != null && GT_Mod.sMessage.length() > 5 && GregTech_API.sSpecialFile.get(GT_ConfigCategories.news, GT_Mod.sMessage, true)) {
			handler.sendPacket(new S02PacketChat(new ChatComponentText(GT_Mod.sMessage)));
		}

		try {
			int e = Integer.parseInt(((String) Class.forName("ic2.core.IC2").getField("VERSION").get((Object) null)).substring(4, 7));
			if (GregTech_API.DEBUG_MODE) {
				GT_Log.log.info("Industrialcraft Version: " + e);
			}

			if (e < 820) {
				handler.sendPacket(new S02PacketChat(new ChatComponentText("GregTech: Please update your IndustrialCraft here:")));
				handler.sendPacket(new S02PacketChat(new ChatComponentText("http://ic2api.player.to:8080/job/IC2_experimental/?")));
			}
		} catch (Throwable e) {
			handler.sendPacket(new S02PacketChat(new ChatComponentText("GregTech: Please update your IndustrialCraft here:")));
			handler.sendPacket(new S02PacketChat(new ChatComponentText("http://ic2api.player.to:8080/job/IC2_experimental/?")));
		}
	}
}