package gregtechmod.api.interfaces;

import gregtechmod.api.items.GT_MetaGenerated_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IOnItemClick {
	public boolean onItemUse(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ);
	public boolean onItemUseFirst(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ);
    public ItemStack onItemRightClick(GT_MetaGenerated_Item aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer);
}