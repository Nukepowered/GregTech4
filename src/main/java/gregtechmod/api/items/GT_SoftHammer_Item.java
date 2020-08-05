package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_SoftHammer_Item extends GT_Tool_Item {
	public GT_SoftHammer_Item(String aName, int aMaxDamage, int aEntityDamage) {
		super(aName, "To give a Machine a soft whack", aMaxDamage, aEntityDamage);
		GregTech_API.registerSoftHammer(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSoftHammer, new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		setCraftingSound(GregTech_API.sSoundList.get(101));
		setBreakingSound(GregTech_API.sSoundList.get(101));
		setEntityHitSound(GregTech_API.sSoundList.get(101));
	}
	
	@Override
	public void checkEnchantmentEffects(ItemStack aStack) {
		super.checkEnchantmentEffects(aStack);
		if (aStack != null) aStack.addEnchantment(Enchantment.knockback, 2);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addAdditionalToolTips(@SuppressWarnings("rawtypes") List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
		aList.add(I18n.format("item.GT_Hammer_Rubber.tooltip_1"));
		aList.add(I18n.format("item.GT_Hammer_Rubber.tooltip_2"));
		aList.add(I18n.format("item.GT_Hammer_Rubber.tooltip_4"));
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
    	
    	if (aBlock == Blocks.lit_redstone_lamp) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.isRemote = true;
				aWorld.setBlock(aX, aY, aZ, Blocks.redstone_lamp, 0, 0);
				aWorld.isRemote = false;
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	if (aBlock == Blocks.redstone_lamp) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.isRemote = true;
				aWorld.setBlock(aX, aY, aZ, Blocks.lit_redstone_lamp, 0, 0);
				aWorld.isRemote = false;
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	if (aBlock == Blocks.golden_rail) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.isRemote = true;
				aWorld.setBlock(aX, aY, aZ, aBlock, (aMeta + 8) % 16, 0);
				aWorld.isRemote = false;
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	if (aBlock == Blocks.activator_rail) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.isRemote = true;
				aWorld.setBlock(aX, aY, aZ, aBlock, (aMeta + 8) % 16, 0);
				aWorld.isRemote = false;
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	if (aBlock == Blocks.log || aBlock == Blocks.hay_block) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta + 4) % 12, 3);
			}
    		return true;
    	}
	    if (aBlock == Blocks.piston || aBlock == Blocks.sticky_piston || aBlock == Blocks.dispenser || aBlock == Blocks.dropper) {
			if (aMeta < 6 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta+1) % 6, 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1, aX, aY, aZ);
			}
	    	return true;
	    }
	    if (aBlock == Blocks.pumpkin || aBlock == Blocks.lit_pumpkin || aBlock == Blocks.furnace || aBlock == Blocks.lit_furnace || aBlock == Blocks.chest || aBlock == Blocks.trapped_chest) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, ((aMeta-1)%4)+2, 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1, aX, aY, aZ);
			}
	    	return true;
	    }
	    if (aBlock == Blocks.hopper) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta+1)%6==1?(aMeta+1)%6:2, 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(101), 1.0F, -1, aX, aY, aZ);
			}
	    	return true;
	    }
    	return false;
    }
}