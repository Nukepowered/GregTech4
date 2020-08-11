package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GT_Spray_Pepper_Item extends GT_Tool_Item {
	public GT_Spray_Pepper_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, "item.GT_Spray_Pepper.tooltip_main", aMaxDamage, aEntityDamage, true);
		setCraftingSound(GregTech_API.sSoundList.get(102));
		setBreakingSound(GregTech_API.sSoundList.get(102));
		setEntityHitSound(GregTech_API.sSoundList.get(102));
		setUsageAmounts(1, 8, 1);
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		aList.add(I18n.format("item.GT_Spray_Pepper.tooltip_1"));
		aList.add(I18n.format("item.GT_Spray_Pepper.tooltip_2"));
	}
	
	@Override
	public void onHitEntity(Entity aEntity) {
		if (aEntity instanceof EntityLiving) {
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.blindness.getId(), 1200, 2, false));
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.poison.getId(), 120, 2, false));
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 200, 2, false));
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.confusion.getId(), 600, 2, false));
		}
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
		return false;
    }
}