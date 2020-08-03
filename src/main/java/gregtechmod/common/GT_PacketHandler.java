package gregtechmod.common;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class GT_PacketHandler implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager aManager, Packet250CustomPayload aPacket, Player aPlayer) {
		try {
			if (aPacket.channel.equals(GregTech_API.TILEENTITY_PACKET_CHANNEL)) {
				ByteArrayDataInput tIn = ByteStreams.newDataInput(aPacket.data);
				int tX = tIn.readInt(), tY = tIn.readShort(), tZ = tIn.readInt();
				if (aPlayer != null && aPlayer instanceof EntityPlayer) {
					if (GregTech_API.DEBUG_MODE && (((EntityPlayer)aPlayer).username.equals("Player") || ((EntityPlayer)aPlayer).username.equals("GregoriusT"))) GT_Log.out.println("Received initial MetaTileEntity Data: " + aPacket.length + " Bytes @ (" + tX + ";" + tY + ";" + tZ + ") during Tick: " + GregTech_API.sClientTickCounter);
					TileEntity tTileEntity = ((EntityPlayer)aPlayer).worldObj.getBlockTileEntity(tX, tY, tZ);
					if (tTileEntity != null) {
							 if (tTileEntity instanceof BaseMetaTileEntity) ((BaseMetaTileEntity)tTileEntity).receiveMetaTileEntityData(tIn.readShort(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readByte(), tIn.readByte(), tIn.readByte(), tIn.readByte());
						else if (tTileEntity instanceof BaseMetaPipeEntity) ((BaseMetaPipeEntity)tTileEntity).receiveMetaTileEntityData(tIn.readShort(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readByte(), tIn.readByte(), tIn.readByte(), tIn.readByte());
					}
				}
			} else if (aPacket.channel.equals(GregTech_API.SOUND_PACKET_CHANNEL)) {
				ByteArrayDataInput tIn = ByteStreams.newDataInput(aPacket.data);
				int tX = tIn.readInt(), tY = tIn.readShort(), tZ = tIn.readInt();
				if (aPlayer != null && aPlayer instanceof EntityPlayer) {
					GT_Utility.doSoundAtClient(tIn.readUTF(), tIn.readFloat(), tIn.readFloat(), tX, tY, tZ);
				}
			} else if (aPacket.channel.equals(GregTech_API.GENERIC_CHANNEL)) {
				if (GregTech_API.DEBUG_MODE) GT_Log.out.println("Tick " + GregTech_API.sClientTickCounter + " @ " + aPacket.channel + " -> " + aPacket.data.length + " Bytes");
			} else {
				 GT_Log.out.println("Tick " + GregTech_API.sClientTickCounter + " @ " + aPacket.channel + " -> " + aPacket.data.length + " Bytes");
			}
		} catch(Throwable e) {
			e.printStackTrace(GT_Log.err);
		}
	}
}