package gregtechmod.api.interfaces;

import gregtechmod.common.network.packet.GT_Packet;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Interface used by the Mods Main Class to reference to internals.
 * 
 * Don't even think about including this File in your Mod.
 */
public interface IGT_Mod {
	/**
	 * Just checks if said Packet should be sent to the Client. Currently does nothing important.
	 */
    public boolean allowPacketToBeSent(GT_Packet aPacket, EntityPlayerMP aPlayer);
    
	/**
	 * This means that Server specific Basefiles are definitely existing! Not if the World is actually server side or not!
	 */
	public boolean isServerSide();
	
	/**
	 * This means that Client specific Basefiles are definitely existing! Not if the World is actually client side or not!
	 */
	public boolean isClientSide();
}