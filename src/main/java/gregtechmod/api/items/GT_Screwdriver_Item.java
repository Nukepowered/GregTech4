package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Screwdriver_Item extends GT_Tool_Item {
	public GT_Screwdriver_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aUnlocalized, "item.GT_Screwdriver.tooltip_main", aMaxDamage, aEntityDamage, true, -1, aDischargedGTID);
		GregTech_API.registerScrewdriver(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		registerAtOreDictWildcard(GT_ToolDictNames.craftingToolScrewdriver);
		addToEffectiveList(EntityCaveSpider.class.getName());
		addToEffectiveList(EntitySpider.class.getName());
		addToEffectiveList("EntityTFHedgeSpider");
		addToEffectiveList("EntityTFKingSpider");
		addToEffectiveList("EntityTFSwarmSpider");
		addToEffectiveList("EntityTFTowerBroodling");
		setCraftingSound(GregTech_API.sSoundList.get(100));
		setBreakingSound(GregTech_API.sSoundList.get(100));
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		aList.add(I18n.format("item.GT_Screwdriver.tooltip_1"));
		aList.add(I18n.format("item.GT_Screwdriver.tooltip_2"));
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
    	
    	if (aBlock == Blocks.powered_repeater || aBlock == Blocks.unpowered_repeater) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 200, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta / 4) * 4  + (((aMeta%4) + 1) % 4), 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	if (aBlock == Blocks.powered_comparator || aBlock == Blocks.unpowered_comparator) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 200, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta / 4) * 4  + (((aMeta%4) + 1) % 4), 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	return false;
    }
}