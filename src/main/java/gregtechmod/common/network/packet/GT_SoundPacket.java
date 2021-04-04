package gregtechmod.common.network.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import gregtechmod.api.util.GT_Utility;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class GT_SoundPacket extends GT_Packet {
	public int x;
	public int y;
	public int z;
	public String soundName;
	public float soundStrength;
	public float soundModulation;
	
	public GT_SoundPacket() {}
	
	public GT_SoundPacket(String soundName, int x, int y, int z, float volume, float modulation) {
		this.soundName = soundName;
		this.x = x;
		this.y = y;
		this.z = z;
		this.soundStrength = volume;
		this.soundModulation = modulation;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		soundStrength = buf.readFloat();
		soundModulation = buf.readFloat();
		soundName = ByteBufUtils.readUTF8String(buf);
		super.fromBytes(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeFloat(soundStrength);
		buf.writeFloat(soundModulation);
		ByteBufUtils.writeUTF8String(buf, soundName);
		super.toBytes(buf);
	}

	@Override
	public IMessage handle(MessageContext context) {
		if (context.side == Side.CLIENT && Minecraft.getMinecraft().thePlayer != null) {
			GT_Utility.doSoundAtClient(soundName, 0, soundStrength, soundModulation, x, y, z);
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("[x=%d, y=%d, z=%d, name=%s, strengh=%f, modulation=%f]", x, y, z, soundName, soundStrength, soundModulation);
	}
}