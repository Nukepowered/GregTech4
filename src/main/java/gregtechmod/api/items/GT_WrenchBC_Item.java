package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import buildcraft.api.tools.IToolWrench;

public class GT_WrenchBC_Item extends GT_Wrench_Item implements IToolWrench {
	public GT_WrenchBC_Item(String aName, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aName, aMaxDamage, aEntityDamage, aDischargedGTID);
	}
	
	@Override
	public boolean canWrench(EntityPlayer aPlayer, int aX, int aY, int aZ) {
		return true;
	}
	
	@Override
	public void wrenchUsed(EntityPlayer aPlayer, int aX, int aY, int aZ) {
		GT_ModHandler.damageOrDechargeItem(aPlayer.inventory.getCurrentItem(), 1, 1000, aPlayer);
		GT_Utility.sendSoundToPlayers(aPlayer.worldObj, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
		aList.add(I18n.format("item.GT_Wrech.tooltip_bc"));
	}
}