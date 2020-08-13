package gregtechmod.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.network.packet.GT_Packet;
import gregtechmod.common.network.packet.GT_SoundPacket;
import gregtechmod.common.network.packet.GT_TileEntityPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

/**
 * Used to handle packets on client side
 * @author iDnK
 * 
 * @param <T> should be GT_Packet or their child class
 */
public abstract class GT_NetworkHandler<T extends GT_Packet> implements IMessageHandler<T, IMessage> {
	
	@Override
	public IMessage onMessage(T message, MessageContext ctx) {
		if (message instanceof GT_TileEntityPacket) {
			GT_TileEntityPacket packet = (GT_TileEntityPacket) message;
			if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.worldObj != null) {
				String name = Minecraft.getMinecraft().thePlayer.getDisplayName();
				if (GregTech_API.DEBUG_MODE && (name.equals("Player") || name.equals("GregoriusT") || name.equals("The_DnK")))
					GT_Log.log.info(String.format("Received initial MetaTileEntity Data: %s Bytes @ (%d;%d;%d) during Tick: %s", packet.data.capacity(), packet.aX, packet.aY, packet.aZ, GregTech_API.sClientTickCounter));
				TileEntity tTile = Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(packet.aX, packet.aY, packet.aZ);
				if (tTile != null) {
					if (tTile instanceof BaseMetaTileEntity) {
						((BaseMetaTileEntity) tTile).receiveMetaTileEntityData(packet.aID, packet.aCovers, packet.aTextureData, packet.aUpdateData, packet.aRedstoneData, packet.aColorData);
					} else if (tTile instanceof BaseMetaPipeEntity) {
						((BaseMetaPipeEntity) tTile).receiveMetaTileEntityData(packet.aID, packet.aCovers, packet.aTextureData, packet.aUpdateData, packet.aRedstoneData, packet.aColorData);
					}
				}
			}
		} else if (message instanceof GT_SoundPacket) {
			GT_SoundPacket sound = (GT_SoundPacket) message;
			if (Minecraft.getMinecraft().thePlayer != null) {
				GT_Utility.doSoundAtClient(sound.aSoundName, 0, sound.aSoundStrength, sound.aSoundModulation, sound.aX, sound.aY, sound.aZ);
			}
		} else if (message instanceof GT_Packet) {
			if (GregTech_API.DEBUG_MODE) GT_Log.log.info("Tick " + GregTech_API.sClientTickCounter + "  Bytes: " + message.data.capacity());
		} else {
			
		}
		
		return null;
	}
}