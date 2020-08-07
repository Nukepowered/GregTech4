package gregtechmod.common.network;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.common.GT_GUIHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
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
		if (!GT_Mod.mAlreadyPlayed || aUserName.equalsIgnoreCase("richardg867") || aUserName.equalsIgnoreCase("The_DnK")) {
			handler.sendPacket(new S02PacketChat(new ChatComponentText("GregTech is known for massivly changing the Tech Tree of modded Minecraft. Please make sure to look up Recipes via NEI (you will definetly need NEI), before complaining about missing Recipes. I needed to change some Recipes to prevent exploits and to improve the Tech Tree, even for regular unmodded Minecraft Recipes. Most of them are Configurable, so don't complain.")));
			handler.sendPacket(new S02PacketChat(new ChatComponentText("~ Gregorius Techneticies")));
			if (aUserName.equalsIgnoreCase("richardg867")) {
				handler.sendPacket(new S02PacketChat(new ChatComponentText("Is that enough of a disclaimer for you RichardG?")));
			}
		}
		if (GT_Mod.sTinkersWarning) {
			handler.sendPacket(new S02PacketChat(new ChatComponentText("Exploit Warning: Please disable Fortune Autosmelting in the Tinkers Construct Config.")));
		}
		if (GT_Mod.sMessage != null && GT_Mod.sMessage.length() > 5 && GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.news, GT_Mod.sMessage, true)) {
			handler.sendPacket(new S02PacketChat(new ChatComponentText(GT_Mod.sMessage)));
		}
		
		@SuppressWarnings("unused")
		String tString = "List of banished Players";
		if (aUserName.equalsIgnoreCase("corysmart") || aUserName.equalsIgnoreCase("mdiyo") || aUserName.equalsIgnoreCase("swag10air")) {
			GT_Mod.drawMessage();
		}
	}
}