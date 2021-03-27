package gregtechmod.common.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import gregtechmod.common.network.packet.MachineUIPacket;
import gregtechmod.common.network.packet.GT_Packet;
import gregtechmod.common.network.packet.GT_SoundPacket;
import gregtechmod.common.network.packet.GT_TileEntityPacket;
import net.minecraft.entity.player.EntityPlayerMP;

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
		GT_PacketHandler.registerClientMessage(GENERIC_CHANNEL, MachineUIPacket.class);
		GT_PacketHandler.registerClientMessage(TILEENTITY_PACKET_CHANNEL, GT_TileEntityPacket.class);
		GT_PacketHandler.registerClientMessage(SOUND_PACKET_CHANNEL, GT_SoundPacket.class);
	}
	
	public static <T extends GT_Packet> void sendPacket(T packet, EntityPlayerMP reciever) {
		if (packet instanceof GT_TileEntityPacket) {
			TILEENTITY_PACKET_CHANNEL.sendTo(packet, reciever);
		} else if (packet instanceof GT_SoundPacket) {
			SOUND_PACKET_CHANNEL.sendTo(packet, reciever);
		} else {
			GENERIC_CHANNEL.sendTo(packet, reciever);
		}
	}
	
	protected static <T extends GT_Packet> void registerServerMessage(SimpleNetworkWrapper channel, Class<T> type) {
		GT_PacketHandler.register(channel, type, Side.SERVER);
	}
	
	protected static <T extends GT_Packet> void registerClientMessage(SimpleNetworkWrapper channel, Class<T> type) {
		GT_PacketHandler.register(channel, type, Side.CLIENT);
	}
	
	private static <T extends GT_Packet> void register(SimpleNetworkWrapper channel, Class<T> type, Side side) {
		channel.registerMessage(GT_NetworkHandler.INSTANCE, type, discriminator++, side);
	}
}