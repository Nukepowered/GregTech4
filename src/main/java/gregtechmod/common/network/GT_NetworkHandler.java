package gregtechmod.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.network.packet.GT_Packet;

/**
 * Used to handle any GT's packets
 * @author TheDarkDnKTv
 */
public final class GT_NetworkHandler implements IMessageHandler<GT_Packet, IMessage> {
	
	public static final GT_NetworkHandler INSTANCE = new GT_NetworkHandler();
	
	private GT_NetworkHandler() {}
	
	@Override
	public IMessage onMessage(GT_Packet message, MessageContext ctx) {
		if (GregTech_API.DEBUG_MODE && GregTech_API.SECONDARY_DEBUG_MODE) {
			GT_Log.log.trace("Processing income packet " + message.getClass().getSimpleName() + " on side " + ctx.side);
		}
		
		IMessage value = null;
		
		try {
			value = message.handle(ctx);
		} catch (Throwable e) {
			if (GregTech_API.DEBUG_MODE && GregTech_API.SECONDARY_DEBUG_MODE) {
				GT_Log.log.error("Error occured processing packet");
				GT_Log.log.catching(e);
			}
		}
		
		return value;
	}
}