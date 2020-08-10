package gregtechmod.api.util;

import gregtechmod.api.interfaces.IFoodStat;
import gregtechmod.api.items.GT_MetaGenerated_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class GT_FoodStat implements IFoodStat {
	private final int mFoodLevel;
	private final int[] mPotionEffects;
	private final float mSaturation;
	private final EnumAction mAction;
	private final ItemStack mEmptyContainer;
	private final boolean mAlwaysEdible, mInvisibleParticles;
	
	/**
	 * @param aFoodLevel Amount of Food in Half Bacon [0 - 20]
	 * @param aSaturation Amount of Saturation [0.0F - 1.0F]
	 * @param aAction The Action to be used. If this is null, it uses the Eating Action
	 * @param aEmptyContainer An empty Container (Optional)
	 * @param aAlwaysEdible If this Item is always edible, like Golden Apples or Potions
	 * @param aInvisibleParticles If the Particles of the Potion Effects are invisible
	 * @param aPotionEffects An Array of Potion Effects with %4==0 Elements as follows
	 * ID of a Potion Effect. 0 for none
	 * Duration of the Potion in Ticks
	 * Level of the Effect. [0, 1, 2] are for [I, II, III]
	 * The likelihood that this Potion Effect takes place upon being eaten [1 - 100]
	 */
	public GT_FoodStat(int aFoodLevel, float aSaturation, EnumAction aAction, ItemStack aEmptyContainer, boolean aAlwaysEdible, boolean aInvisibleParticles, int... aPotionEffects) {
		mFoodLevel = aFoodLevel;
		mSaturation = aSaturation;
		mAction = aAction==null?EnumAction.eat:aAction;
		mPotionEffects = aPotionEffects;
		mEmptyContainer = GT_Utility.copy(aEmptyContainer);
		mInvisibleParticles = aInvisibleParticles;
		mAlwaysEdible = aAlwaysEdible;
	}
	
	@Override
	public int getFoodLevel(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mFoodLevel;
	}
	
	@Override
	public float getSaturation(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mSaturation;
	}
	
	@Override
	public void onEaten(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
        aStack.stackSize--;
        ItemStack tStack = GT_OreDictUnificator.get(GT_Utility.copy(mEmptyContainer));
        if (tStack != null && !aPlayer.inventory.addItemStackToInventory(tStack)) aPlayer.dropPlayerItem(tStack);
		aPlayer.worldObj.playSoundAtEntity(aPlayer, "random.burp", 0.5F, aPlayer.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		if (!aPlayer.worldObj.isRemote) {
			for (int i = 3; i < mPotionEffects.length; i+=4) {
				if (aPlayer.worldObj.rand.nextInt(100) < mPotionEffects[i]) {
					aPlayer.addPotionEffect(new PotionEffect(mPotionEffects[i-3], mPotionEffects[i-2], mPotionEffects[i-1], mInvisibleParticles));
				}
			}
		}
	}
	
	@Override
	public EnumAction getFoodAction(GT_MetaGenerated_Item aItem, ItemStack aStack) {
		return mAction;
	}
	
	@Override
	public boolean alwaysEdible(GT_MetaGenerated_Item aItem, ItemStack aStack, EntityPlayer aPlayer) {
		return mAlwaysEdible;
	}
}