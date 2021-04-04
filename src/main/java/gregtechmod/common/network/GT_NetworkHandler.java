package gregtechmod.common.network;

import java.util.EnumMap;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleChannelHandlerWrapper;
import cpw.mods.fml.common.network.simpleimpl.SimpleIndexedCodec;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;

import gregtechmod.common.network.packet.MachineUIPacket;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.network.packet.FluidInventoryPacket;
import gregtechmod.common.network.packet.GT_Packet;
import gregtechmod.common.network.packet.GT_SoundPacket;
import gregtechmod.common.network.packet.GT_TileEntityPacket;

import net.minecraft.entity.player.EntityPlayerMP;

public final class GT_NetworkHandler implements Runnable {
	
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
		registerHandler(GENERIC_CHANNEL				, Side.CLIENT);
		registerHandler(TILEENTITY_PACKET_CHANNEL	, Side.CLIENT);
		registerHandler(SOUND_PACKET_CHANNEL		, Side.CLIENT);
		
		registerType(GENERIC_CHANNEL			, MachineUIPacket.class);
		registerType(TILEENTITY_PACKET_CHANNEL	, GT_TileEntityPacket.class);
		registerType(SOUND_PACKET_CHANNEL		, GT_SoundPacket.class);
		registerType(GENERIC_CHANNEL			, FluidInventoryPacket.class);
	}
	
	/**
	 * Used to send packet to specify player
	 * SERVER -> CLIENT
	 */
	public static <T extends GT_Packet> void sendPacket(T packet, EntityPlayerMP reciever) {
		if (GregTech_API.DEBUG_MODE && GregTech_API.SECONDARY_DEBUG_MODE)
			GT_Log.log.info("Packet '" + packet.getClass().getSimpleName() + "' been sent to '" + reciever.getDisplayName() + "' data: " + packet.toString());
		if (packet instanceof GT_TileEntityPacket) {
			TILEENTITY_PACKET_CHANNEL.sendTo(packet, reciever);
		} else if (packet instanceof GT_SoundPacket) {
			SOUND_PACKET_CHANNEL.sendTo(packet, reciever);
		} else {
			GENERIC_CHANNEL.sendTo(packet, reciever);
		}
	}
	
	private static <T extends GT_Packet> void registerType(SimpleNetworkWrapper channel, Class<T> type) {
		try {
			SimpleIndexedCodec codec = ReflectionHelper.getPrivateValue(SimpleNetworkWrapper.class, channel, "packetCodec");
			codec.addDiscriminator(++discriminator, type);
		} catch (Throwable e) {
			GT_Log.log.error("Unable to register packet type");
			GT_Log.log.catching(e);
		}
	}
	
	private static void registerHandler(SimpleNetworkWrapper channel, Side side) {
		try {
			EnumMap<Side, FMLEmbeddedChannel> channels = ReflectionHelper.getPrivateValue(SimpleNetworkWrapper.class, channel, "channels");
			FMLEmbeddedChannel realChannel = channels.get(side);
			SimpleChannelHandlerWrapper<GT_Packet, IMessage> handler = new SimpleChannelHandlerWrapper<>(GT_PacketHandler.INSTANCE, side, GT_Packet.class);
			String type1 = realChannel.findChannelHandlerNameForType(SimpleIndexedCodec.class);
			realChannel.pipeline().addAfter(type1, "GTNetHandler", handler);
		} catch (Throwable e) {
			GT_Log.log.error("Unable to register channel handler");
			GT_Log.log.catching(e);
		}
	}
}