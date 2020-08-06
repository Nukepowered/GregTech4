package gregtechmod.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.network.packet.GT_Packet;
import gregtechmod.common.network.packet.GT_SoundPacket;
import gregtechmod.common.network.packet.GT_TileEntityPacket;

public abstract class GT_NetworkHandler<T extends GT_Packet> implements IMessageHandler<T, IMessage> {
	
	@Override
	public IMessage onMessage(T message, MessageContext ctx) {
		if (message instanceof GT_TileEntityPacket) {
			// TODO Packets
		} else if (message instanceof GT_SoundPacket) {
			GT_SoundPacket sound = (GT_SoundPacket) message;
			if (GT_Utility.sCurrentPlayer != null) {
				GT_Utility.doSoundAtClient(sound.aSoundName, sound.aSoundStrength, sound.aSoundModulation, sound.aX, sound.aY, sound.aZ);
			}
		} else if (message instanceof GT_Packet) {
			if (GregTech_API.DEBUG_MODE) GT_Log.log.info("Tick " + GregTech_API.sClientTickCounter + "  Bytes: " + message.data.capacity());
		} else {
			
		}
		
//		public void onPacketData(INetworkManager aManager, Packet250CustomPayload aPacket, Player aPlayer) {
//				if (aPacket.channel.equals(GregTech_API.TILEENTITY_PACKET_CHANNEL)) {
//					ByteArrayDataInput tIn = ByteStreams.newDataInput(aPacket.data);
//					int tX = tIn.readInt(), tY = tIn.readShort(), tZ = tIn.readInt();
//					if (aPlayer != null && aPlayer instanceof EntityPlayer) {
//						if (GregTech_API.DEBUG_MODE && (((EntityPlayer)aPlayer).username.equals("Player") || ((EntityPlayer)aPlayer).username.equals("GregoriusT"))) GT_Log.out.println("Received initial MetaTileEntity Data: " + aPacket.length + " Bytes @ (" + tX + ";" + tY + ";" + tZ + ") during Tick: " + GregTech_API.sClientTickCounter);
//						TileEntity tTileEntity = ((EntityPlayer)aPlayer).worldObj.getBlockTileEntity(tX, tY, tZ);
//						if (tTileEntity != null) {
//								 if (tTileEntity instanceof BaseMetaTileEntity) ((BaseMetaTileEntity)tTileEntity).receiveMetaTileEntityData(tIn.readShort(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readByte(), tIn.readByte(), tIn.readByte(), tIn.readByte());
//							else if (tTileEntity instanceof BaseMetaPipeEntity) ((BaseMetaPipeEntity)tTileEntity).receiveMetaTileEntityData(tIn.readShort(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readByte(), tIn.readByte(), tIn.readByte(), tIn.readByte());
//						}
//					}
//				}
//		}
		
		
		return null;
	}
}