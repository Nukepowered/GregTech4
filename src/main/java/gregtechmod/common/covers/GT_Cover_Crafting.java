package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet100OpenWindow;

public class GT_Cover_Crafting extends GT_CoverBehavior {
	
	public GT_Cover_Crafting(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public boolean onCoverRightclick(byte aSide, int aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			((EntityPlayerMP)aPlayer).incrementWindowID();
			((EntityPlayerMP)aPlayer).playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(((EntityPlayerMP)aPlayer).currentWindowId, 1, "Crafting", 9, true));
			((EntityPlayerMP)aPlayer).openContainer = new ContainerWorkbench(((EntityPlayerMP)aPlayer).inventory, ((EntityPlayerMP)aPlayer).worldObj, aTileEntity.getXCoord(), aTileEntity.getYCoord(), aTileEntity.getZCoord()) {
			    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
			        return true;
			    }
			};
			((EntityPlayerMP)aPlayer).openContainer.windowId = ((EntityPlayerMP)aPlayer).currentWindowId;
			((EntityPlayerMP)aPlayer).openContainer.addCraftingToCrafters(((EntityPlayerMP)aPlayer));
		}
		return true;
	}
}