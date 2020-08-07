package gregtechmod.common.network.packet;

import java.util.Arrays;
import java.util.stream.Collectors;

import io.netty.buffer.ByteBuf;

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
	public int[] aCovers;
	public byte aTextureData;
	public byte aUpdateData;
	public byte aRedstoneData;
	public byte aColorData;
	
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
		super.toBytes(buf);
	}
	
	@Override
	public String toString() {
		return String.format("GT_TileEntityPacket[x=%d, y=%d, z=%d, ID=%d, TextureData=%d, UpdateData=%d, RedstoneData=%d, ColorData=%d, CoverData=%s]",
				aX, aY, aZ, (int)aID, (int)aTextureData, (int)aUpdateData, (int)aRedstoneData, (int)aColorData,
				Arrays.stream(aCovers).mapToObj(i -> new Integer(i)).collect(Collectors.toList()).toString());
	}
}
