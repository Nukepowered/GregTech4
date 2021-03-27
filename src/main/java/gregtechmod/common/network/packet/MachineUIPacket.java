package gregtechmod.common.network.packet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.util.GT_Log;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.network.SyncedField;

import io.netty.buffer.ByteBuf;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;

/** A machine UIs sync packet, will call an update on client side
 * @author TheDarkDnKTv
 *
 */
public class MachineUIPacket extends GT_Packet {
	
	private int windowId;
	private JsonObject data;
	
	public MachineUIPacket() {}
	
	public MachineUIPacket(int windowId, JsonObject data) {
		this.windowId = windowId;
		this.data = data;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			windowId = buf.readInt();
			String json = ByteBufUtils.readUTF8String(buf);
			data = new JsonParser().parse(json).getAsJsonObject();
		} catch (Throwable e) {
			GT_Log.log.catching(e);
		}
		
		super.fromBytes(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		try {
			buf.writeInt(windowId);
			String json = SyncedField.GSON.toJson(data);
			ByteBufUtils.writeUTF8String(buf, json);
		} catch (Throwable e) {
			GT_Log.log.catching(e);
		}
		
		super.toBytes(buf);
	}
	
	@Override
	public IMessage handle(MessageContext context) {
		if (context.side == Side.CLIENT && Minecraft.getMinecraft().thePlayer != null && data != null && !data.entrySet().isEmpty()) {
			Container opened = Minecraft.getMinecraft().thePlayer.openContainer;
			if (opened != null && opened.windowId == this.windowId) {
				if (opened instanceof GT_ContainerMetaTile_Machine) {
					((GT_ContainerMetaTile_Machine) opened).processChanges(data);
				} else if (opened instanceof GT_ContainerMetaID_Machine) {
					((GT_ContainerMetaID_Machine ) opened).processChanges(data); // for compat
				}
			}
		}
		
		return null;
	}

}
