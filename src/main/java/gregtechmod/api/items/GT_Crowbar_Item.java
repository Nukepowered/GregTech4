package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Crowbar_Item extends GT_Tool_Item {
	public GT_Crowbar_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, "item.GT_Crowbar.tooltip_main", aMaxDamage, aEntityDamage, true, -1, -1, 5, 20.0F);
		GregTech_API.registerCrowbar(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		registerAtOreDict(GT_ToolDictNames.craftingToolCrowbar, GregTech_API.ITEM_WILDCARD_DAMAGE);
		addToBlockList(Blocks.rail);
		addToBlockList(Blocks.golden_rail);
		addToBlockList(Blocks.detector_rail);
		addToBlockList(Blocks.activator_rail);
		addToBlockList(GT_ModHandler.getRCItem("track.boarding", 1));
		addToBlockList(GT_ModHandler.getRCItem("track.elevator", 1));
		setUsageAmounts(1, 2, 1);
	}
	
	protected boolean isRCCrowbar() {
		return false;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
		aList.add(I18n.format("item.GT_Crowbar.tooltip_1"));
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aWorld.isRemote) {
    		return false;
    	}
		if (isRCCrowbar()) return false;
    	Block aBlock = aWorld.getBlock(aX, aY, aZ);
    	if (aBlock == null) return false;
    	byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
    	
    	if (aBlock == Blocks.rail) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.isRemote = true;
				aWorld.setBlock(aX, aY, aZ, aBlock, (aMeta + 1) % 10, 0);
				aWorld.isRemote = false;
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(0), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	if (aBlock == Blocks.golden_rail || aBlock == Blocks.detector_rail || aBlock == Blocks.activator_rail) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.isRemote = true;
				aWorld.setBlock(aX, aY, aZ, aBlock, ((aMeta / 8) * 8) + (((aMeta%8)+1) % 6), 0);
				aWorld.isRemote = false;
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(0), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	return false;
    }
}