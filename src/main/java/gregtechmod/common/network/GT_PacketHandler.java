package gregtechmod.common.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import gregtechmod.common.network.packet.GT_Packet;
import gregtechmod.common.network.packet.GT_SoundPacket;
import gregtechmod.common.network.packet.GT_TileEntityPacket;

public final class GT_PacketHandler implements Runnable {
	private static byte discriminator = 0;
	
	public final static SimpleNetworkWrapper GENERIC_CHANNEL;
	public final static SimpleNetworkWrapper TILEENTITY_PACKET_CHANNEL;
	public final static SimpleNetworkWrapper SOUND_PACKET_CHANNEL;
	
	static {
		GENERIC_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("gregtech");
		TILEENTITY_PACKET_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("GTTile");
		SOUND_PACKET_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("GTSound");
	}
	
	public void run() {
		GT_PacketHandler.registerClientMessage(GENERIC_CHANNEL, GT_Packet.class);
		GT_PacketHandler.registerClientMessage(TILEENTITY_PACKET_CHANNEL, GT_TileEntityPacket.class);
		GT_PacketHandler.registerClientMessage(SOUND_PACKET_CHANNEL, GT_SoundPacket.class);
	}
	
	protected static <T extends GT_Packet> void registerServerMessage(SimpleNetworkWrapper channel, Class<T> type) {
		GT_PacketHandler.register(channel, type, Side.SERVER);
	}
	
	protected static <T extends GT_Packet> void registerClientMessage(SimpleNetworkWrapper channel, Class<T> type) {
		GT_PacketHandler.register(channel, type, Side.CLIENT);
	}
	
	private static <T extends GT_Packet> void register(SimpleNetworkWrapper channel, Class<T> type, Side side) {
		channel.registerMessage(new GT_NetworkHandler<T>() {}, type, discriminator++, side);
	}
}