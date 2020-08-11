package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_OreDictUnificator;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_SolderingTool_Item extends GT_Tool_Item {
	public GT_SolderingTool_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aUnlocalized, "item.Soldering_Iron.tooltip", aMaxDamage, aEntityDamage, true, -1, aDischargedGTID);
		GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolSolderingIron, new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GregTech_API.registerSolderingTool(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		setCraftingSound(GregTech_API.sSoundList.get(103));
		setBreakingSound(GregTech_API.sSoundList.get(103));
		setEntityHitSound(GregTech_API.sSoundList.get(103));
		setUsageAmounts(1, 1, 1);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		aList.add(I18n.format("item.Soldering_Iron.tooltip_1"));
		aList.add(I18n.format("item.Soldering_Iron.tooltip_2"));
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aWorld.isRemote) {
    		return false;
    	}
    	return false;
    }
}