package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_Spray_Color_Item extends GT_Tool_Item {
	public byte mColorMeta = 0;
	
	public GT_Spray_Color_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage, byte aColorMeta) {
		super(aUnlocalized, "item.GT_Spray_Color_Item.tooltip_1", aMaxDamage, aEntityDamage, true);
		registerAtOreDictWildcard(Dyes.get(mColorMeta = aColorMeta));
		setCraftingSound(GregTech_API.sSoundList.get(102));
		setBreakingSound(GregTech_API.sSoundList.get(102));
		setEntityHitSound(GregTech_API.sSoundList.get(102));
		setUsageAmounts(32, 3, 1);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		aList.add(I18n.format("item.GT_Spray_Color_Item.tooltip_3", getMaxDamage(), Dyes.get(mColorMeta).mName.toLowerCase()));
		aList.add(I18n.format("item.GT_Spray_Color_Item.tooltip_2", getMaxDamage() / getDamagePerContainerItemCraft()));
	}
	
	@Override
	public Item getEmptyItem(ItemStack aStack) {
		ItemStack empty = GT_Items.Spray_Empty.get(1);
		aStack.func_150996_a(empty.getItem());
		aStack.stackSize = 1;
		aStack.setTagCompound(empty.getTagCompound());
		aStack.setItemDamage(empty.getItemDamage());
		return empty.getItem();
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aWorld.isRemote) {
    		return false;
    	}
    	Block aBlock = aWorld.getBlock(aX, aY, aZ);
    	if (aBlock == null) return false;
    	byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
    	
    	if (aBlock == Blocks.carpet || aBlock == Blocks.hardened_clay || aBlock == Blocks.stained_hardened_clay || aBlock == GT_Items.TE_Rockwool.getBlock()) {
    		if (aMeta == (~mColorMeta & 15) && aBlock != Blocks.hardened_clay) return false;
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
	    		if (aBlock == Blocks.hardened_clay)
					aWorld.setBlock(aX, aY, aZ, Blocks.stained_hardened_clay, ~mColorMeta & 15, 3);
				else
					aWorld.setBlockMetadataWithNotify(aX, aY, aZ, ~mColorMeta & 15, 3);
			}
    		return true;
    	}
    	
    	if (aBlock.recolourBlock(aWorld, aX, aY, aZ, ForgeDirection.getOrientation(aSide), ~mColorMeta & 15)) {
    		GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer);
			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
    		return true;
    	}
    	
    	return false;
    }
}