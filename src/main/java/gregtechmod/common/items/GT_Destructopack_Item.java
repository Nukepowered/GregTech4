package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Destructopack_Item extends GT_Generic_Item {
	
	public GT_Destructopack_Item(String aName) {
		super(aName, "item.GT_Destructopack.tooltip");
		setMaxStackSize(1);
		setNoRepair();
	}

    @Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		aPlayer.openGui(GregTech_API.gregtechmod, 33, aWorld, 0, 0, 0);
		return aStack;
	}
}
