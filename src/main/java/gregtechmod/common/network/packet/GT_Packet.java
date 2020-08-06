package gregtechmod.common.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public abstract class GT_Packet implements IMessage {
	public ByteBuf data;
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.data = buf;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		this.data = buf;
	}
}
