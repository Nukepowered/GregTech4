package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

public class GT_Cover_Crafting extends GT_CoverBehavior {
	
	public GT_Cover_Crafting(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public boolean onCoverRightclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			EntityPlayerMP tMPPlayer = (EntityPlayerMP) aPlayer;
			tMPPlayer.getNextWindowId();
			tMPPlayer.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(tMPPlayer.currentWindowId, 1, "Crafting", 9, true));
			tMPPlayer.openContainer = new ContainerWorkbench(tMPPlayer.inventory, tMPPlayer.worldObj, aTileEntity.getXCoord(), aTileEntity.getYCoord(), aTileEntity.getZCoord()) {
			    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
			        return true;
			    }
			};
			tMPPlayer.openContainer.windowId = tMPPlayer.currentWindowId;
			tMPPlayer.openContainer.addCraftingToCrafters(tMPPlayer);
		}
		return true;
	}
}