package gregtechmod.common.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

/**
 * Abstract packet, contains raw data buffer.
 * Fucking new packet system, in same time it is useful
 * @author iDnK
 *
 */
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
	
	/**
	 * Used to run some code on side after retrieving packet
	 */
	public abstract IMessage handle(MessageContext context);
}
