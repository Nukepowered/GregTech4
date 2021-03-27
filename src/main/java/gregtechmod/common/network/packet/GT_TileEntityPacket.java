package gregtechmod.common.network.packet;

import java.util.Arrays;
import java.util.stream.Collectors;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.BaseMetaTileEntity;
import gregtechmod.api.util.GT_Log;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

/**
 * Data packet for MTE updates
 * @author iDnK
 *
 */
public class GT_TileEntityPacket extends GT_Packet {
	public int aX;
	public int aY;
	public int aZ;
	public short aID;
	public int[] aCovers = new int[6];
	public byte aTextureData;
	public byte aUpdateData;
	public byte aRedstoneData;
	public byte aColorData;
	public boolean aRFUpgrade;
	
	@Override
	public void fromBytes(ByteBuf buf) {
		aX = buf.readInt();
		aY = buf.readInt();
		aZ = buf.readInt();
		aID = buf.readShort();
		for (int i = 0; i < 6; i++)
			aCovers[i] = buf.readInt();
		aTextureData = buf.readByte();
		aUpdateData = buf.readByte();
		aRedstoneData = buf.readByte();
		aColorData = buf.readByte();
		aRFUpgrade = buf.readBoolean();
		super.fromBytes(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(aX);
		buf.writeInt(aY);
		buf.writeInt(aZ);
		buf.writeShort(aID);
		for (int i = 0; i < 6; i++)
			buf.writeInt(aCovers[i]);
		buf.writeByte(aTextureData);
		buf.writeByte(aUpdateData);
		buf.writeByte(aRedstoneData);
		buf.writeByte(aColorData);
		buf.writeBoolean(aRFUpgrade);
		super.toBytes(buf);
	}
	
	@Override
	public String toString() {
		return String.format("GT_TileEntityPacket[x=%d, y=%d, z=%d, ID=%d, TextureData=%d, UpdateData=%d, RedstoneData=%d, ColorData=%d, RFUpgrade=%s, CoverData=%s]",
				aX, aY, aZ, (int)aID, (int)aTextureData, (int)aUpdateData, (int)aRedstoneData, (int)aColorData, aRFUpgrade,
				Arrays.stream(aCovers).mapToObj(i -> new Integer(i)).collect(Collectors.toList()).toString());
	}

	@Override
	public IMessage handle(MessageContext context) {
		if (context.side == Side.CLIENT) {
			if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.worldObj != null) {
				String name = Minecraft.getMinecraft().thePlayer.getDisplayName();
				if (GregTech_API.DEBUG_MODE && (name.equals("Player") || name.equals("GregoriusT") || name.equals("The_DnK")))
					GT_Log.log.info(String.format("Received initial MetaTileEntity Data: %s Bytes @ (%d;%d;%d) during Tick: %s", data.capacity(), aX, aY, aZ, GregTech_API.sClientTickCounter));
				TileEntity tTile = Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(aX, aY, aZ);
				if (tTile != null) {
					if (tTile instanceof BaseMetaTileEntity) {
						((BaseMetaTileEntity) tTile).receiveMetaTileEntityData(aID, aCovers, aTextureData, aUpdateData, aRedstoneData, aColorData);
					} else if (tTile instanceof BaseMetaPipeEntity) {
						((BaseMetaPipeEntity) tTile).receiveMetaTileEntityData(aID, aCovers, aTextureData, aUpdateData, aRedstoneData, aColorData);
					}
				}
			}
		}
		
		return null;
	}
}
