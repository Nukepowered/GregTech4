package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * This is just a basic Tool, which has normal durability and could break Blocks.
 */
public class GT_Tool_Item extends GT_Generic_Item {
	public final List<String> mEffectiveAgainstList = new ArrayList<String>();
	public final List<Block> mEffectiveBlocksList = new ArrayList<Block>();
	public final List<String> mEffectiveOreDictList = new ArrayList<String>();
	public final List<Material> mEffectiveMaterialsList = new ArrayList<Material>();
	
	protected final int mEntityDamage, mChargedGTID, mDisChargedGTID, mToolQuality;
	protected final float mToolStrength;
	
	private short mDamagePerContainerCraft = 10, mDamagePerWeaponUse = 3, mDamagePerBlockBreak = 1;
	private int mTier = 1, mEUperBrokenBlock = 1000, mSilklevel = 0, mFortunelevel = 0, mLootinglevel = 0;
	private String mToolClass1, mToolClass2, mToolClass3, mCraftingSound, mBreakingSound, mBlockBreakSound, mEntityHitSound;
	private float mCraftingSoundStrength = 1, mBreakingSoundStrength = 1, mBlockBreakSoundStrength = 1, mEntityHitSoundStrength = 1;
	
	public GT_Tool_Item(String aName, String aTooltip, int aMaxDamage, int aEntityDamage) {
		this(aName, aTooltip, aMaxDamage, aEntityDamage, -1, -1);
	}
	
	public GT_Tool_Item(String aName, String aTooltip, int aMaxDamage, int aEntityDamage, int aChargedGTID, int aDisChargedGTID) {
		this(aName, aTooltip, aMaxDamage, aEntityDamage, aChargedGTID, aDisChargedGTID, 0, 0.0F);
	}
	
	public GT_Tool_Item(String aName, String aTooltip, int aMaxDamage, int aEntityDamage, int aChargedGTID, int aDisChargedGTID, int aToolQuality, float aToolStrength) {
		super(aName, aTooltip);
		mEntityDamage = aEntityDamage;
		mDisChargedGTID = aDisChargedGTID;
		mChargedGTID = aChargedGTID;
		mToolQuality = Math.max(aToolQuality, 0);
		mToolStrength = aToolStrength;
		setMaxDamage(aMaxDamage);
		setMaxStackSize(1);
		setNoRepair();
		setFull3D();
		GT_ModHandler.registerBoxableItemToToolBox(this);
	}
	
	public final GT_Tool_Item addToEffectiveList(String aEntityClassName) {
		mEffectiveAgainstList.add(aEntityClassName.substring(aEntityClassName.lastIndexOf(".")+1));
		return this;
	}
	
	public final GT_Tool_Item addToOreDictList(String aOreName) {
		mEffectiveOreDictList.add(aOreName);
		return this;
	}
	
	public final GT_Tool_Item addToBlockList(ItemStack aBlock) {
		if (aBlock != null && aBlock.getItem() != null && aBlock.getItem() instanceof ItemBlock) mEffectiveBlocksList.add(Block.getBlockFromItem(aBlock.getItem()));
		return this;
	}
	
	public final GT_Tool_Item addToBlockList(Block aBlock) {
		if (aBlock != null) mEffectiveBlocksList.add(aBlock);
		return this;
	}
	
	public final GT_Tool_Item addToMaterialList(Material aMaterial) {
		if (aMaterial != null) mEffectiveMaterialsList.add(aMaterial);
		return this;
	}
	
	public final GT_Tool_Item setElectricTier(int aTier) {
		mTier = Math.max(1, aTier);
		return this;
	}
	
	public final GT_Tool_Item setElectricConsumptionPerBrokenBlock(int aEU) {
		mEUperBrokenBlock = Math.max(0, aEU);
		return this;
	}

	public final GT_Tool_Item setPrimaryToolClass(String aClass) {
		mToolClass1 = aClass;
		return this;
	}
	
	public final GT_Tool_Item setSecondaryToolClass(String aClass) {
		mToolClass2 = aClass;
		return this;
	}

	public final GT_Tool_Item setTertiaryToolClass(String aClass) {
		mToolClass3 = aClass;
		return this;
	}

	public final GT_Tool_Item setCraftingSound(String aSound) {
		mCraftingSound = aSound;
		return this;
	}
	
	public final GT_Tool_Item setBreakingSound(String aSound) {
		mBreakingSound = aSound;
		return this;
	}

	public final GT_Tool_Item setBlockBreakSound(String aSound) {
		mBlockBreakSound = aSound;
		return this;
	}
	
	public final GT_Tool_Item setEntityHitSound(String aSound) {
		mEntityHitSound = aSound;
		return this;
	}
	
	public final GT_Tool_Item setUsageAmounts(int aDamagePerContainerCraft, int aDamagePerWeaponUse, int aDamagePerBlockBreak) {
		mDamagePerContainerCraft = (short)aDamagePerContainerCraft;
		mDamagePerWeaponUse = (short)aDamagePerWeaponUse;
		mDamagePerBlockBreak = (short)aDamagePerBlockBreak;
		return this;
	}
	
	public final GT_Tool_Item setSilkyness(int aLevel) {
		mSilklevel = aLevel;
		return this;
	}

	public final GT_Tool_Item setLuckyness(int aLevel) {
		mFortunelevel = aLevel;
		return this;
	}
	
	public final GT_Tool_Item setLootyness(int aLevel) {
		mLootinglevel = aLevel;
		return this;
	}
	
	public final GT_Tool_Item registerAtOreDict(String aName) {
		GT_OreDictUnificator.registerOre(aName, new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		return this;
	}
	
	public void setMode(ItemStack aStack, int aMode) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
		checkEnchantmentEffects(aStack);
        tNBT.setInteger("mMode", aMode);
	}
	
	public int getMode(ItemStack aStack) {
		if (aStack == null) return 0;
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
		checkEnchantmentEffects(aStack);
        return tNBT.getInteger("mMode");
	}
	
	public final short getDamagePerContainerItemCraft() {
		return mDamagePerContainerCraft;
	}
	
	public final short getDamagePerWeaponUse() {
		return mDamagePerWeaponUse;
	}
	
	public final short getDamagePerBlockBreak() {
		return mDamagePerBlockBreak;
	}
	
	public void onHitEntity(Entity aEntity) {
		
	}
	
	public void checkEnchantmentEffects(ItemStack aStack) {
		if (aStack != null) {
			if (aStack.hasTagCompound()) aStack.stackTagCompound.removeTag("ench");
			if (!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, mEUperBrokenBlock)) {
				if (mSilklevel		> 0) aStack.addEnchantment(Enchantment.silkTouch	, mSilklevel);
				if (mFortunelevel	> 0) aStack.addEnchantment(Enchantment.fortune		, mFortunelevel);
				if (mLootinglevel	> 0) aStack.addEnchantment(Enchantment.looting		, mLootinglevel);
			}
		}
	}
	
	public ItemStack getEmptyItem(ItemStack aStack) {
		return null;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {
		super.registerIcons(aIconRegister);
		if (mChargedGTID >= 0)		((GT_Generic_Item)GregTech_API.sItemList[mChargedGTID])		.mIcon = mIcon;
		if (mDisChargedGTID >= 0)	((GT_Generic_Item)GregTech_API.sItemList[mDisChargedGTID])	.mIcon = mIcon;
    }
	
	@Override
    public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
    	GT_ModHandler.useElectricItem(aStack, 0, aPlayer);
		checkEnchantmentEffects(aStack);
        return aStack;
    }
    
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		GT_ModHandler.useElectricItem(aStack, 0, aPlayer);
		checkEnchantmentEffects(aStack);
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item var1, CreativeTabs var2, List aList) {
		if (GT_ModHandler.isElectricItem(new ItemStack(this, 1))) {
	        ItemStack tCharged = new ItemStack(this, 1);
	        GT_ModHandler.chargeElectricItem(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
	        aList.add(tCharged);
	        return;
		}
		aList.add(new ItemStack(this, 1));
    }
	
	@Override
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
    }
	
	@Override
    public int getItemEnchantability() {
        return 0;
    }
	
	@Override
    public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {
        return false;
    }
	
	@Override
    public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {
        return false;
    }
	
	@Override
    public final ItemStack getContainerItem(ItemStack aStack) {
		checkEnchantmentEffects(aStack);
		aStack = GT_Utility.copy(aStack);
		if (aStack.getItemDamage() > aStack.getMaxDamage()) {
			GT_Utility.doSoundAtClient(mBreakingSound, mBreakingSoundStrength);
			ItemStack tStack = getEmptyItem(aStack);
			if (tStack != null) return tStack;
		} else {
			GT_Utility.doSoundAtClient(mCraftingSound, mCraftingSoundStrength);
			GT_ModHandler.damageOrDechargeItem(aStack, getDamagePerContainerItemCraft(), getDamagePerContainerItemCraft()*1000, null);
		}
		checkEnchantmentEffects(aStack);
		return aStack;
    }
	
	@Override
    public boolean hasContainerItem() {
        return true;
    }
	
	@Override
    public boolean hasEffect(ItemStack aStack) {
		checkEnchantmentEffects(aStack);
        return false;
    }
	
	@Override
    public final boolean hitEntity(ItemStack aStack, EntityLivingBase aEntity, EntityLivingBase aPlayer) {
		GT_Utility.sendSoundToPlayers(aEntity.worldObj, mEntityHitSound, mEntityHitSoundStrength, -1, (int)aEntity.posX, (int)aEntity.posY, (int)aEntity.posZ);
		checkEnchantmentEffects(aStack);
		if (mEntityDamage > 1) {
			GT_ModHandler.damageOrDechargeItem(aStack, getDamagePerWeaponUse(), getDamagePerWeaponUse() * 1000, aPlayer);
			if (aEntity != null) onHitEntity(aEntity);
			checkEnchantmentEffects(aStack);
			return true;
		}
        return false;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Multimap getAttributeModifiers(ItemStack stack) {
        Multimap multimap = super.getAttributeModifiers(stack);
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", mEntityDamage>0?mEntityDamage:1, 0));
        return multimap;
    }
	
//	@Override // FIXME: dynamic damage
//	public float getDamageVsEntity() { // To provide with entities, use Item#onLeftClickEntity
//		checkEnchantmentEffects(aStack);
//		int tDamage = mEntityDamage;
//		if (mEffectiveAgainstList.contains(GT_Utility.getClassName(aAttackedEntity))) tDamage *= 2;
//		if (tDamage > 1) {
//			if (GT_ModHandler.isElectricItem(aStack)) {
//				if (GT_ModHandler.canUseElectricItem(aStack, getDamagePerWeaponUse() * 1000)) {
//					return tDamage;
//				} else {
//					return 1;
//				}
//			} else {
//				return tDamage;
//			}
//		}
//        return 1.0F;
//    }
	
	private final boolean isEffectiveAgainst(Block aBlock, int aMeta) {
		if (mToolStrength > 1 && aBlock != null) {
			Float tHarvestLevel = ReflectionHelper.getPrivateValue(Block.class, aBlock, new String[] {"blockHardness", "field_149782_v"});
			if (getPrimaryToolClass() != null) {
				if (tHarvestLevel >= 0) return tHarvestLevel <= mToolQuality;
			}
			if (getSecondaryToolClass() != null) {
				if (tHarvestLevel >= 0) return tHarvestLevel <= mToolQuality;
			}
			if (getTertiaryToolClass() != null) {
				if (tHarvestLevel >= 0) return tHarvestLevel <= mToolQuality;
			}
			if (mEffectiveMaterialsList.contains(aBlock.getMaterial())) return true;
			if (mEffectiveBlocksList.contains(aBlock)) return true;
			for (String tString : mEffectiveOreDictList) {
				if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(aBlock, 1, aMeta==-1?GregTech_API.ITEM_WILDCARD_DAMAGE:aMeta), tString)) return true;
			}
		}
		return false;
	}
	
    public final String getPrimaryToolClass() {
        return mToolClass1;
    }
    
    public final String getSecondaryToolClass() {
        return mToolClass2;
    }
    
    public final String getTertiaryToolClass() {
        return mToolClass3;
    }
    
	@Override
    public final boolean canHarvestBlock(Block aBlock, ItemStack aStack) {
		return func_150897_b(aBlock) && (!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, mEUperBrokenBlock));
    }
	
	@Override
    public final boolean func_150897_b(Block aBlock) {
		return mToolStrength > 1 && isEffectiveAgainst(aBlock, -1);
    }
    
    @Override
    public float getDigSpeed(ItemStack aStack, Block aBlock, int aMeta) {
		checkEnchantmentEffects(aStack);
    	if (mToolStrength <= 1 || !isEffectiveAgainst(aBlock, aMeta) || (GT_ModHandler.isElectricItem(aStack) && !GT_ModHandler.canUseElectricItem(aStack, mEUperBrokenBlock))) return 1.0F;
        return mToolStrength;
    }
    
	@Override
    public final boolean onBlockDestroyed(ItemStack aStack, World aWorld, Block aBlock, int aX, int aY, int aZ, EntityLivingBase aPlayer) {
		GT_Utility.sendSoundToPlayers(aWorld, mBlockBreakSound, mBlockBreakSoundStrength, -1, aX, aY, aZ);
		checkEnchantmentEffects(aStack);
		if (mToolStrength > 1 && (double) aBlock.getBlockHardness(aWorld, aX, aY, aZ) != 0.0D) {
        	GT_ModHandler.damageOrDechargeItem(aStack, getDamagePerBlockBreak(), getDamagePerBlockBreak()*mEUperBrokenBlock, aPlayer);
    		checkEnchantmentEffects(aStack);
        }
        return true;
    }
    
	@Override
    public final boolean doesContainerItemLeaveCraftingGrid(ItemStack aStack) {
        return false;
    }
    
	public final boolean canProvideEnergy(ItemStack aStack) {
		return false;
	}
	
	public final int getMaxCharge(ItemStack aStack) {
		return getMaxDamage() * 1000;
	}
	
	@Override
	public final int getTier(ItemStack aStack) {
		return mTier;
	}
	
	public final int getTransferLimit(ItemStack aStack) {
		return (int)(Math.pow(2, mTier) * 128);
	}
}