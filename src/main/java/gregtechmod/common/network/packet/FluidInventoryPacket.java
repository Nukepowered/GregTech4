package gregtechmod.common.network.packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

import gregtechmod.api.gui.GT_Container;
import gregtechmod.api.gui.GT_FluidSlot;

import io.netty.buffer.ByteBuf;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author TheDarkDnKTv
 *
 */
public class FluidInventoryPacket extends GT_Packet {
	
	private int windowId;
	private List<SlotData> slots;
	
	public FluidInventoryPacket(Map<Integer, GT_FluidSlot> slots, int windowId) {
		this();
		this.windowId = windowId;
		
		for (Entry<Integer, GT_FluidSlot> slot : slots.entrySet()) {
			this.slots.add(new SlotData(slot.getKey(), slot.getValue().getFluid()));
		}
	}
	
	public FluidInventoryPacket() {
		this.windowId = -1;
		this.slots = new ArrayList<>();
	}

	
	@Override
	public void fromBytes(ByteBuf buf) {
		super.fromBytes(buf);
		
		this.windowId = buf.readInt();
		int max = buf.readByte();
		for (int i = 0; i < max; i++) {
			slots.add(new SlotData(buf.readByte(), buf.readShort(), buf.readInt()));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		super.toBytes(buf);
		
		buf.writeInt(windowId);
		buf.writeByte(slots.size());
		for (SlotData slot : slots) {
			buf.writeByte(slot.slotIdx);
			buf.writeShort(slot.fluidId);
			buf.writeInt(slot.amount);
		}
	}
	
	@Override
	public IMessage handle(MessageContext context) {
		if (context.side != Side.CLIENT)
			throw new IllegalStateException("Somehow client packet handling on server!"); 
		if (Minecraft.getMinecraft().thePlayer == null) 
			throw new IllegalStateException("Player client-side is null!"); 
		Container container = Minecraft.getMinecraft().thePlayer.openContainer;
		if (container != null && container.windowId == windowId) {
			GT_Container cont = (GT_Container) container;
			for (SlotData data : slots) {
				GT_FluidSlot slot = cont.fluidSlots.get(data.slotIdx);
				Fluid fluid = FluidRegistry.getFluid(data.fluidId);
				slot.setFluid(fluid != null ? new FluidStack(fluid, data.amount) : null);
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("[id=%s, slotData=%s]", windowId, slots);
	}
	
	static class SlotData {
		
		int fluidId;
		int amount;
		int slotIdx;
		
		SlotData(int slotIdx, FluidStack fluid) {
			this(slotIdx, fluid == null ? -1 : fluid.getFluidID(), fluid == null ? 0 : fluid.amount);
		}
		
		SlotData(int slotIdx, int fluidID, int amount) {
			this.slotIdx = slotIdx;
			this.fluidId = fluidID;
			this.amount = amount;
		}
		
		@Override
		public String toString() {
			return String.format("(fluidId=%d, amount=%d, slotIdx=%d)", fluidId, amount, slotIdx);
		}
	}
}
