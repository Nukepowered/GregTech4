package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.enums.SubTag;
import gregtechmod.api.interfaces.IFoodStat;
import gregtechmod.api.interfaces.IIconContainer;
import gregtechmod.api.interfaces.IOnItemClick;
import gregtechmod.api.util.*;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;

import java.util.*;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Gregorius Techneticies
 * 
 * One Item for everything!
 * 
 * This brilliant Item Class is used for automatically generating all possible variations of Material Items, like Dusts, Ingots, Gems, Plates and similar.
 * It saves me a ton of work, when adding Items, because I always have to make a new Item SubType for each OreDict Prefix, when adding a new Material.
 * 
 * As you can see, up to 32766 Items can be generated using this Class. And the last 766 Items can be custom defined, just to save space and MetaData.
 * 
 * These Items can also have special RightClick abilities, electric Charge or even be set to become a Food alike Item.
 */
public abstract class GT_MetaGenerated_Item extends GT_Generic_Item implements ISpecialElectricItem, IElectricItemManager {
	/**
	 * All instances of this Item Class are listed here.
	 * This gets used to register the Renderer to all Items of this Type, if useStandardMetaItemRenderer() returns true.
	 * 
	 * You can also use the unlocalized Name gotten from getUnlocalizedName() as Key if you want to get a specific Item.
	 */
	public static final HashMap<String, GT_MetaGenerated_Item> sInstances = new HashMap<String, GT_MetaGenerated_Item>();
	
	/* ---------- CONSTRUCTOR AND MEMBER VARIABLES ---------- */
	
	private BitSet mEnabledItems = new BitSet(766);
	private final OrePrefixes[] mGeneratedPrefixList;
	public Icon[][] mIconList = new Icon[mEnabledItems.size()][1];
	
	public final HashMap<Short, ArrayList<IOnItemClick>> mClickBehaviors = new HashMap<Short, ArrayList<IOnItemClick>>();
	public final HashMap<Short, IFoodStat> mFoodStats = new HashMap<Short, IFoodStat>();
	public final HashMap<Short, Integer[]> mElectricStats = new HashMap<Short, Integer[]>();
	public final HashMap<Short, Short> mBurnValues = new HashMap<Short, Short>();
	
	/**
	 * Creates the Item using these Parameters.
	 * @param aID the Item ID.
	 * @param aUnlocalized The Unlocalized Name of this Item.
	 * @param aGeneratedPrefixList The OreDict Prefixes you want to have generated.
	 */
	public GT_MetaGenerated_Item(int aID, String aUnlocalized, OrePrefixes... aGeneratedPrefixList) {
		super(aID, aUnlocalized, "Generated Item", null, false);
		mGeneratedPrefixList = Arrays.copyOf(aGeneratedPrefixList, 32);
		setCreativeTab(GregTech_API.TAB_GREGTECH_MATERIALS);
        setHasSubtypes(true);
        setMaxDamage(0);
        
        sInstances.put(getUnlocalizedName(), this);
        
        for (int i = 0; i < 32000; i++) {
			OrePrefixes tPrefix = mGeneratedPrefixList[i / 1000];
			if (tPrefix == null) continue;
			Materials tMaterial = GregTech_API.sGeneratedMaterials[i % 1000];
			if (tMaterial == null) continue;
			if (doesMaterialAllowGeneration(tPrefix, tMaterial)) {
				GT_LanguageManager.addStringLocalization(getUnlocalizedName() + "." + i + ".name", getDefaultLocalization(tPrefix, tMaterial, i));
				GT_LanguageManager.addStringLocalization(getUnlocalizedName() + "." + i + ".tooltip", tMaterial.getToolTip(tPrefix.mMaterialAmount / GregTech_API.MATERIAL_UNIT));
				String tOreName = getOreDictString(tPrefix, tMaterial);
				tPrefix = OrePrefixes.getOrePrefix(tOreName);
//				if (tPrefix != null && tPrefix.mIsUnificatable) {
//					GT_OreDictUnificator.set(tOreName, new ItemStack(this, 1, i));
//				} else {
					GT_OreDictUnificator.registerOre(tOreName, new ItemStack(this, 1, i));
//				}
			}
		}
	}
	
	/* ---------- OVERRIDEABLE FUNCTIONS ---------- */
	
	/**
	 * @param aPrefix the OreDict Prefix
	 * @param aMaterial the Material
	 * @param aMetaData a Index from [0 - 31999]
	 * @return the Localized Name when default LangFiles are used.
	 */
	public String getDefaultLocalization(OrePrefixes aPrefix, Materials aMaterial, int aMetaData) {
		return aPrefix.mLocalizedMaterialPre + aMaterial.mDefaultLocalName + aPrefix.mLocalizedMaterialPost;
	}
	
	/**
	 * @param aMetaData a Index from [0 - 31999]
	 * @param aMaterial the Material
	 * @return an Icon Container for the Item Display.
	 */
	public abstract IIconContainer getIconContainer(int aMetaData, Materials aMaterial);
	
	/**
	 * @param aPrefix this can be null, you have to return false in that case
	 * @param aMaterial this can be null, you have to return false in that case
	 * @return if this Item should be generated and visible.
	 */
	public boolean doesMaterialAllowGeneration(OrePrefixes aPrefix, Materials aMaterial) {
		// You have to check for at least these Conditions in every Case! So add a super Call like the following for this before executing your Code:
		// if (!super.doesMaterialAllowGeneration(aPrefix, aMaterial)) return false;
		return aPrefix != null && aMaterial != null && !aPrefix.dontGenerateItem(aMaterial);
	}
	
	/**
	 * @param aPrefix always != null
	 * @param aMaterial always != null
	 * @param aDoShowAllItems this is the Configuration Setting of the User, if he wants to see all the Stuff like Tiny Dusts or Crushed Ores as well.
	 * @return if this Item should be visible in NEI or Creative
	 */
	public boolean doesShowInCreative(OrePrefixes aPrefix, Materials aMaterial, boolean aDoShowAllItems) {
		return true;
	}
	
	/**
	 * @return the name of the Item to be registered at the OreDict.
	 */
	public String getOreDictString(OrePrefixes aPrefix, Materials aMaterial) {
		return aPrefix.get(aMaterial);
	}
	
	/**
	 * @return the Color Modulation the Material is going to be rendered with.
	 */
	public short[] getRGBa(ItemStack aStack) {
		int aMetaData = getDamage(aStack);
		if (aMetaData < 0) return Materials._NULL.mRGBa;
		if (aMetaData < 32000) {
			Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
			if (tMaterial == null) return Materials._NULL.mRGBa;
			for (byte i = 0; i < tMaterial.mRGBa.length; i++) {
				if (tMaterial.mRGBa[i] > 255) tMaterial.mRGBa[i] = 255;
				if (tMaterial.mRGBa[i] < 0) tMaterial.mRGBa[i] = 0;
			}
			return tMaterial.mRGBa;
		}
        return Materials._NULL.mRGBa;
	}
	
	/**
	 * @return if this MetaGenerated Item should use my Default Renderer System.
	 */
	public boolean useStandardMetaItemRenderer() {
		return true;
	}
	
	/* ---------- FOR ADDING CUSTOM ITEMS INTO THE REMAINING 766 RANGE ---------- */
	
	/**
	 * This adds a Custom Item to the ending Range.
	 * @param aID The Id of the assigned Item [0 - 765] (The MetaData gets auto-shifted by +32000)
	 * @param aEnglish The Default Localized Name of the created Item
	 * @param aToolTip The Default ToolTip of the created Item, you can also insert null for having no ToolTip
	 * @param aFoodBehavior The Food Value of this Item. Can be null aswell.
	 * @param aOreDictNames The OreDict Names you want to give the Item.
	 * @return An ItemStack containing the newly created Item.
	 */
	public final ItemStack addItem(int aID, String aEnglish, String aToolTip, IFoodStat aFoodBehavior, Object... aOreDictNames) {
		if (aToolTip == null) aToolTip = "";
		if (aID >= 0 && aID < mEnabledItems.size()) {
			mEnabledItems.set(aID);
			GT_LanguageManager.addStringLocalization(getUnlocalizedName() + "." + (32000+aID) + ".name", aEnglish);
			GT_LanguageManager.addStringLocalization(getUnlocalizedName() + "." + (32000+aID) + ".tooltip", aToolTip);
			setFoodBehavior(32000+aID, aFoodBehavior);
			ItemStack rStack = new ItemStack(this, 1, 32000+aID);
			for (Object tOreDictName : aOreDictNames) GT_OreDictUnificator.registerOre(tOreDictName, rStack);
			return rStack;
		}
		return null;
	}
	
	/**
	 * Adds a special Clicking Behavior to the Item.
	 * 
	 * Note: the boolean Behaviors sometimes won't be executed if another boolean Behavior returned true before.
	 * 
	 * @param aMetaValue the Meta Value of the Item you want to add it to. [0 - 32765]
	 * @param aClickBehavior the Click Behavior you want to add.
	 * @return the Item itself for convenience in constructing.
	 */
	public final GT_MetaGenerated_Item addClickBehavior(int aMetaValue, IOnItemClick aClickBehavior) {
		if (aMetaValue < 0 || aMetaValue >= 32000 + mEnabledItems.length() || aClickBehavior == null) return this;
		ArrayList<IOnItemClick> tList = mClickBehaviors.get((short)aMetaValue);
		if (tList == null) {
			tList = new ArrayList<IOnItemClick>(1);
			mClickBehaviors.put((short)aMetaValue, tList);
		}
		tList.add(aClickBehavior);
		return this;
	}
	
	/**
	 * Sets a Food Behavior for the Item.
	 * 
	 * @param aMetaValue the Meta Value of the Item you want to set it to. [0 - 32765]
	 * @param aFoodBehavior the Food Behavior you want to add.
	 * @return the Item itself for convenience in constructing.
	 */
	public final GT_MetaGenerated_Item setFoodBehavior(int aMetaValue, IFoodStat aFoodBehavior) {
		if (aMetaValue < 0 || aMetaValue >= 32000 + mEnabledItems.length()) return this;
		if (aFoodBehavior == null) mFoodStats.remove((short)aMetaValue); else mFoodStats.put((short)aMetaValue, aFoodBehavior);
		return this;
	}
	
	/**
	 * Sets the Furnace Burn Value for the Item.
	 * 
	 * @param aMetaValue the Meta Value of the Item you want to set it to. [0 - 32765]
	 * @param aValue 200 = 1 Burn Process = 500 EU, max = 32767 (that is 81917.5 EU)
	 * @return the Item itself for convenience in constructing.
	 */
	public final GT_MetaGenerated_Item setBurnValue(int aMetaValue, int aValue) {
		if (aMetaValue < 0 || aMetaValue >= 32000 + mEnabledItems.length() || aValue < 0) return this;
		if (aValue == 0) mBurnValues.remove((short)aMetaValue); else mBurnValues.put((short)aMetaValue, aValue>Short.MAX_VALUE?Short.MAX_VALUE:(short)aValue);
		return this;
	}
	
	/**
	 * @param aMetaValue the Meta Value of the Item you want to set it to. [0 - 32765]
	 * @param aMaxCharge Maximum Charge. (if this is < 0 it will remove the Electric Behavior)
	 * @param aTransferLimit Transfer Limit.
	 * @param aTier The electric Tier.
	 * @param aSpecialData If this Item has a Fixed Charge, like a SingleUse Battery (if > 0).
	 * Use -1 if you want to make this Battery chargeable. (the use and canUse Functions will still discharge if you just use this)
	 * Use -2 if you want to make this Battery dischargeable.
	 * Use -3 if you want to make this Battery charge/discharge-able.
	 * @return the Item itself for convenience in constructing.
	 */
	public final GT_MetaGenerated_Item setElectricStats(int aMetaValue, int aMaxCharge, int aTransferLimit, int aTier, int aSpecialData) {
		if (aMetaValue < 0 || aMetaValue >= 32000 + mEnabledItems.length()) return this;
		if (aMaxCharge < 0) mElectricStats.remove((short)aMetaValue); else {
			mElectricStats.put((short)aMetaValue, new Integer[] {aMaxCharge, Math.max(0, aTransferLimit), Math.max(0, aTier), aSpecialData});
			if (aMetaValue >= 32000) mIconList[aMetaValue-32000] = Arrays.copyOf(mIconList[aMetaValue-32000], Math.max(9, mIconList[aMetaValue-32000].length));
		}
		return this;
	}
	
	/* ---------- INTERNAL OVERRIDES ---------- */
	
    @Override
    public final int getMaxItemUseDuration(ItemStack aStack) {
        return mFoodStats.get((short)getDamage(aStack)) == null ? 0 : 32;
    }
    
    @Override
	public final EnumAction getItemUseAction(ItemStack aStack) {
    	IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
        return tStat == null ? EnumAction.none : tStat.getFoodAction(this, aStack);
    }
    
    @Override
	public final ItemStack onEaten(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
    	IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
    	if (tStat != null) {
            aPlayer.getFoodStats().addStats(tStat.getFoodLevel(this, aStack, aPlayer), tStat.getSaturation(this, aStack, aPlayer));
            tStat.onEaten(this, aStack, aPlayer);
    	}
        return aStack;
    }
    
	@Override
	public final boolean onItemUse(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		ArrayList<IOnItemClick> tList = mClickBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IOnItemClick tBehavior : tList) if (tBehavior.onItemUse(this, aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ)) return true;
    	return false;
	}
	
	@Override
	public final boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		ArrayList<IOnItemClick> tList = mClickBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IOnItemClick tBehavior : tList) if (tBehavior.onItemUseFirst(this, aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ)) return true;
		return false;
	}
	
    @Override
	public final ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
    	ArrayList<IOnItemClick> tList = mClickBehaviors.get((short)getDamage(aStack));
		if (tList != null) for (IOnItemClick tBehavior : tList) aStack = tBehavior.onItemRightClick(this, aStack, aWorld, aPlayer);
		IFoodStat tStat = mFoodStats.get((short)getDamage(aStack));
    	if (tStat != null && aPlayer.canEat(tStat.alwaysEdible(this, aStack, aPlayer))) {
    		aPlayer.setItemInUse(aStack, getMaxItemUseDuration(aStack));
    	}
		return aStack;
    }
    
	public final IIconContainer getIconContainer(int aMetaData) {
		if (aMetaData < 0) return null;
		if (aMetaData < 32000) {
			Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
			if (tMaterial == null) return null;
			return getIconContainer(aMetaData, tMaterial);
		}
		return null;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public final void getSubItems(int var1, CreativeTabs aCreativeTab, List aList) {
        for (int i = 0; i < 32000; i++) if (doesMaterialAllowGeneration(mGeneratedPrefixList[i / 1000], GregTech_API.sGeneratedMaterials[i % 1000]) && doesShowInCreative(mGeneratedPrefixList[i / 1000], GregTech_API.sGeneratedMaterials[i % 1000], GregTech_API.sDoShowAllItemsInCreative)) aList.add(new ItemStack(this, 1, i));
        for (int i = 0, j = mEnabledItems.length(); i < j; i++) if (mEnabledItems.get(i)) {
    		Integer[] tStats = mElectricStats.get((short)(32000+i));
    		if (tStats != null && tStats[3] < 0) {
    			ItemStack tStack = new ItemStack(this, 1, 32000+i);
    			setCharge(tStack, tStats[0]);
            	aList.add(tStack);
    		}
    		if (tStats == null || tStats[3] != -2) {
            	aList.add(new ItemStack(this, 1, 32000+i));
    		}
        }
    }
	
	@Override
    public final String getUnlocalizedName(ItemStack aStack) {
    	return getUnlocalizedName() + "." + getDamage(aStack);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public final void registerIcons(IconRegister aIconRegister) {
		for (short i = 0, j = (short)mEnabledItems.length(); i < j; i++) if (mEnabledItems.get(i)) {
			for (byte k = 1; k < mIconList[i].length; k++) {
				mIconList[i][k] = aIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + (GT_Config.system?"troll":getUnlocalizedName() + "/" + i + "/" + k));
			}
    		mIconList[i][0] = aIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + (GT_Config.system?"troll":getUnlocalizedName() + "/" + i));
    	}
    }
	
	@Override
    public final Icon getIconFromDamage(int aMetaData) {
		if (aMetaData < 0) return null;
		if (aMetaData < 32000) {
			Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
			if (tMaterial == null) return null;
			IIconContainer tIcon = getIconContainer(aMetaData, tMaterial);
			if (tIcon != null) return tIcon.getIcon();
			return null;
		}
		return aMetaData-32000<mIconList.length?mIconList[aMetaData-32000][0]:null;
    }
	
	@Override
	@SuppressWarnings("deprecation")
    public final boolean hasEffect(ItemStack aStack) {
		if (super.hasEffect(aStack)) return true;
		int aMetaData = getDamage(aStack);
		if (aMetaData < 0) return false;
		if (aMetaData < 32000) {
			Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
			if (tMaterial == null) return false;
			return tMaterial.isRadioactive() || tMaterial.contains(SubTag.ENCHANTMENT_GLOW);
		}
        return false;
    }
	
	@Override
    public final boolean hasEffect(ItemStack aStack, int aPass) {
		if (super.hasEffect(aStack, aPass)) return true;
		int aMetaData = getDamage(aStack);
		if (aMetaData < 0) return false;
		if (aMetaData < 32000) {
			Materials tMaterial = GregTech_API.sGeneratedMaterials[aMetaData % 1000];
			if (tMaterial == null) return false;
			return tMaterial.isRadioactive() || tMaterial.contains(SubTag.ENCHANTMENT_GLOW);
		}
        return false;
    }
    
	@Override
    public final void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		String tKey = getUnlocalizedName() + "." + getDamage(aStack) + ".tooltip", tString = GT_LanguageManager.getTranslation(tKey);
		if (GT_Utility.isStringValid(tString) && !tKey.equals(tString)) aList.add(tString);
		
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats != null) {
			if (tStats[3] > 0) {
				aList.add("Contains " + tStats[3] + " EU   Tier: " + (tStats[2]>0?tStats[2]:1));
			} else {
				int tCharge = getCharge(aStack);
				if (tStats[3] == -2 && tCharge <= 0) {
					aList.add("Empty. You should recycle it properly.");
				} else {
					aList.add(tCharge + " / " + tStats[0] + " EU - Voltage: " + GregTech_API.VOLTAGES[tStats[2]>0?tStats[2]<GregTech_API.VOLTAGES.length?tStats[2]:GregTech_API.VOLTAGES.length-1:1]);
				}
			}
		}
	}
	
	@Override
	public final int getMaxCharge(ItemStack aStack) {
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats == null) return 0;
		return tStats[0];
	}
	
	@Override
	public final int getTransferLimit(ItemStack aStack) {
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats == null) return 0;
		return Math.max(tStats[1], tStats[3]);
	}
	
	@Override
	public final boolean canProvideEnergy(ItemStack aStack) {
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats == null) return false;
		return tStats[3] > 0 || (aStack.stackSize == 1 && (tStats[3] == -2 || tStats[3] == -3));
	}
	
	@Override
	public final int charge(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aSimulate) {
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats == null || tStats[2] > aTier || !(tStats[3] == -1 || tStats[3] == -3 || (tStats[3] < 0 && aCharge == Integer.MAX_VALUE)) || aStack.stackSize != 1) return 0;
		int tChargeBefore = getCharge(aStack), tNewCharge = Math.min(tStats[0], tChargeBefore + (aIgnoreTransferLimit?aCharge:Math.min(tStats[1], aCharge)));
		if (!aSimulate) setCharge(aStack, tNewCharge);
		return tNewCharge-tChargeBefore;
	}
	
	@Override
	public final int discharge(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreTransferLimit, boolean aSimulate) {
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats == null || tStats[2] > aTier) return 0;
		if (tStats[3] > 0) {
			if (aCharge < tStats[3] || aStack.stackSize < 1) return 0;
			if (!aSimulate) aStack.stackSize--;
			return tStats[3];
		}
		int tChargeBefore = getCharge(aStack), tNewCharge = Math.max(0, tChargeBefore - (aIgnoreTransferLimit?aCharge:Math.min(tStats[1], aCharge)));
		if (!aSimulate) setCharge(aStack, tNewCharge);
		return tChargeBefore-tNewCharge;
	}
	
	@Override
	public final int getCharge(ItemStack aStack) {
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats == null) return 0;
		if (tStats[3] > 0) return tStats[3];
		NBTTagCompound tNBT = aStack.getTagCompound();
		return tNBT==null?0:tNBT.getInteger("GT.ItemCharge");
	}
	
	@Override
	public final boolean canUse(ItemStack aStack, int aAmount) {
		return getCharge(aStack) >= aAmount;
	}
	
	@Override
	public final boolean use(ItemStack aStack, int aAmount, EntityLivingBase aPlayer) {
		if (aPlayer.worldObj.isRemote) return false;
		if (aAmount <= 0) {
			chargeFromArmor(aStack, aPlayer);
			return true;
		}
		int transfer = discharge(aStack, aAmount, Integer.MAX_VALUE, true, true);
		if (transfer == aAmount) {
			discharge(aStack, aAmount, Integer.MAX_VALUE, true, false);
			chargeFromArmor(aStack, aPlayer);
			return true;
		}
		return false;
	}
	
	@Override
	public final void chargeFromArmor(ItemStack aStack, EntityLivingBase aPlayer) {
		if (aPlayer.worldObj.isRemote) return;
		for (int i = 1; i < 5; i++) {
			ItemStack tArmor = aPlayer.getCurrentItemOrArmor(i);
			if (GT_ModHandler.isElectricItem(tArmor)) {
				IElectricItem tArmorItem = (IElectricItem)tArmor.getItem();
				if (tArmorItem.canProvideEnergy(tArmor) && tArmorItem.getTier(tArmor) >= getTier(aStack)) {
					int tCharge = ElectricItem.manager.discharge(tArmor, charge(aStack, Integer.MAX_VALUE-1, Integer.MAX_VALUE, true, true), Integer.MAX_VALUE, true, false);
					if (tCharge > 0) {
						charge(aStack, tCharge, Integer.MAX_VALUE, true, false);
						if (aPlayer instanceof EntityPlayer) {
							Container tContainer = ((EntityPlayer)aPlayer).openContainer;
							if (tContainer != null) tContainer.detectAndSendChanges();
						}
					}
				}
			}
		}
	}
	
	public final boolean setCharge(ItemStack aStack, int aCharge) {
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats == null || tStats[3] > 0) return false;
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = new NBTTagCompound();
		tNBT.removeTag("GT.ItemCharge");
		aCharge = Math.min(aCharge, tStats[0]);
		if (aCharge > 0) tNBT.setInteger("GT.ItemCharge", aCharge);
		if (tNBT.getTags().isEmpty()) aStack.setTagCompound(null); else aStack.setTagCompound(tNBT);
		return true;
	}
	
	@Override
	public final int getItemStackLimit(ItemStack aStack) {
		Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage());
		if (tStats != null && (tStats[3] == -1 || tStats[3] == -3) && getCharge(aStack) > 0) return 1;
		return 64;
	}
	
    @Override public final int getTier(ItemStack aStack) {Integer[] tStats = mElectricStats.get((short)aStack.getItemDamage()); return tStats==null?Integer.MAX_VALUE:tStats[2]>0?tStats[2]:1;}
	@Override public final String getToolTip(ItemStack aStack) {return null;} // This has its own ToolTip Handler, no need to let the IC2 Handler screw us up at this Point
	@Override public final int getChargedItemId(ItemStack aStack) {return itemID;} // ID Changes? How primitive
	@Override public final int getEmptyItemId(ItemStack aStack) {return itemID;} // ID Changes? How primitive
	@Override public final IElectricItemManager getManager(ItemStack aStack) {return this;} // We are our own Manager
	@Override public final boolean getShareTag() {return true;} // just to be sure.
}