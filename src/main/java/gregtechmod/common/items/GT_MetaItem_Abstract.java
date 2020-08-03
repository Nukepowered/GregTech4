package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_MetaItem_Abstract extends Item {
	protected static final short MAXIMUM_META_IDS = 512;
	
	public String[] mToolTipList = new String[MAXIMUM_META_IDS];
	public ItemStack[] mStackList = new ItemStack[MAXIMUM_META_IDS];
	public boolean[] mGlowList = new boolean[MAXIMUM_META_IDS];
	public Icon[] mIconList = new Icon[MAXIMUM_META_IDS];
	
	public GT_MetaItem_Abstract(int aID, String aName) {
		super(aID);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
        setMaxDamage(0);
        setHasSubtypes(true);
        Arrays.fill(mGlowList, false);
        Arrays.fill(mToolTipList, "");
        setUnlocalizedName(aName);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
    	for (int i = 0; i < MAXIMUM_META_IDS; i++) if (mStackList[i] != null) {
    		mIconList[i] = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + (GregTech_API.sConfiguration.system?"troll":getUnlocalizedName() + "/" + i));
    	}
    }
	
	@Override
    public Icon getIconFromDamage(int aIndex) {
        return mIconList[aIndex];
    }
	
	@Override
    public int getMetadata(int aIndex) {
        return aIndex;
    }
	
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		if (aStack.getItemDamage() >= 0 && aStack.getItemDamage() < MAXIMUM_META_IDS && !mToolTipList[aStack.getItemDamage()].equals("")) aList.add(mToolTipList[aStack.getItemDamage()]);
    }
	
	@Override
    public String getUnlocalizedName(ItemStack aStack) {
    	return getUnlocalizedName() + "." + aStack.getItemDamage();
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
		for (int i = 0; i < MAXIMUM_META_IDS; i++) if (mStackList[i] != null) {
			var3.add(getUnunifiedStack(i, 1));
		}
    }
	
	@Override
    public boolean hasEffect(ItemStack aStack) {
        return (aStack.getItemDamage() >= 0 && aStack.getItemDamage() < MAXIMUM_META_IDS && mGlowList[aStack.getItemDamage()]) || super.hasEffect(aStack);
    }
	
	public static ItemStack[] getStackList() {
		return null;
	}
	
	public ItemStack getUnunifiedStack(int aMeta, int aCount) {
		if (aMeta < 0 || aMeta >= MAXIMUM_META_IDS || mStackList[aMeta] == null) return null;
		return GT_Utility.copy(aCount, mStackList[aMeta]);
	}
	
	public ItemStack getStack(int aMeta, int aCount) {
		if (aMeta < 0 || aMeta >= MAXIMUM_META_IDS || mStackList[aMeta] == null) return null;
		ItemStack rStack = GT_OreDictUnificator.get(true, mStackList[aMeta]);
		rStack.stackSize = aCount;
		return rStack;
	}
}