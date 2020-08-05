package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_LanguageManager;
import gregtechmod.api.util.GT_OreDictUnificator;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_Spray_Pepper_Item extends GT_Tool_Item {
	public GT_Spray_Pepper_Item(String aName, int aMaxDamage, int aEntityDamage) {
		super(aName, "To defend yourself against Bears", aMaxDamage, aEntityDamage);
		setCraftingSound(GregTech_API.sSoundList.get(102));
		setBreakingSound(GregTech_API.sSoundList.get(102));
		setEntityHitSound(GregTech_API.sSoundList.get(102));
		setUsageAmounts(1, 8, 1);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addAdditionalToolTips(@SuppressWarnings("rawtypes") List aList, ItemStack aStack) {
		aList.add(I18n.format("item.GT_Spray_Pepper.tooltip_1",getUnlocalizedName()));
		aList.add(I18n.format("item.GT_Spray_Pepper.tooltip_2",getUnlocalizedName()));
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
	public ItemStack getEmptyItem(ItemStack aStack) {
		return GT_OreDictUnificator.getFirstOre("craftingSprayCan", 1);
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
    	TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
    	
    	return false;
    }
}