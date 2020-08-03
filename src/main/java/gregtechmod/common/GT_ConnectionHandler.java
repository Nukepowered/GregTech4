package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ConfigCategories;
import gregtechmod.api.util.GT_Log;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;

public class GT_ConnectionHandler implements IConnectionHandler {
	
	@Override
	public void playerLoggedIn(Player player, NetHandler aNetServerHandler, INetworkManager aManager) {
		
	}
	
	@Override
	public String connectionReceived(NetLoginHandler aNetHandler, INetworkManager aManager) {
		return null;
	}
	
	@Override
	public void connectionOpened(NetHandler aClientHandler, String aServer, int aPort, INetworkManager aManager) {
		
	}
	
	@Override
	public void connectionOpened(NetHandler aClientHandler, MinecraftServer aServer, INetworkManager aManager) {
		
	}
	
	@Override
	public void connectionClosed(INetworkManager aManager) {
		
	}
	
	@Override
	public void clientLoggedIn(NetHandler aClientHandler, INetworkManager aManager, Packet1Login aLoginPacket) {
		EntityPlayer aPlayer = aClientHandler.getPlayer();
		String aUserName = aPlayer.username;
		NetworkRegistry.instance().registerGuiHandler(GregTech_API.gregtechmod, new GT_GUIHandler());
		if (!GT_Mod.mAlreadyPlayed || aUserName.equalsIgnoreCase("richardg867")) {
			aPlayer.addChatMessage("GregTech is known for massivly changing the Tech Tree of modded Minecraft. Please make sure to look up Recipes via NEI (you will definetly need NEI), before complaining about missing Recipes. I needed to change some Recipes to prevent exploits and to improve the Tech Tree, even for regular unmodded Minecraft Recipes. Most of them are Configurable, so don't complain.");
			aPlayer.addChatMessage("~ Gregorius Techneticies");
			if (aUserName.equalsIgnoreCase("richardg867")) {
				aPlayer.addChatMessage("Is that enough of a disclaimer for you RichardG?");
			}
			try {
				GT_Log.mLogFile.createNewFile();
			} catch(Throwable e) {}
		}
		if (GT_Mod.sTinkersWarning) {
			aPlayer.addChatMessage("Exploit Warning: Please disable Fortune Autosmelting in the Tinkers Construct Config.");
		}
		if (GT_Mod.sMessage != null && GT_Mod.sMessage.length() > 5 && GregTech_API.sConfiguration.addAdvConfig(GT_ConfigCategories.news, GT_Mod.sMessage, true)) {
			aPlayer.addChatMessage(GT_Mod.sMessage);
		}
		/*try {
			int tVersion = Integer.parseInt(((String)Class.forName("ic2.core.IC2").getField("VERSION").get(null)).substring(6, 9));
			if (GregTech_API.DEBUG_MODE) GT_Log.out.println("Industrialcraft Version: " + tVersion);
			if (tVersion > 377) {
				aPlayer.addChatMessage("This Version of Industrialcraft is causing serious Problems with the Electricity Network. Downgrade to Industrialcraft Version 1.116.377-lf, to fix these Problems.");
			}
		} catch(Throwable e) {e.printStackTrace(GT_Log.err);}*/
		String tString = "List of banished Players";
		if (aUserName.equalsIgnoreCase("corysmart") || aUserName.equalsIgnoreCase("mdiyo") || aUserName.equalsIgnoreCase("swag10air")) {
//			GT_Mod.drawMessage();
		}
	}
}