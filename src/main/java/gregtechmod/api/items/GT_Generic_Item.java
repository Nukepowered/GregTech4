package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Config;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Extended by most Items, also used as a fallback Item, to prevent the accidental deletion when Errors occur.
 */
public class GT_Generic_Item extends Item {
	
	public IIcon mIcon;
	
	private final String mTooltip;
	
	public GT_Generic_Item(String aName, String aTooltip) {
		super();
		setUnlocalizedName(aName);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
		mTooltip = aTooltip == null || aTooltip.equals("") ? "" : aTooltip;
	}
	
	public final GT_Generic_Item registerAtOreDict(String aName, short aDamage) {
		GT_OreDictUnificator.registerOre(aName, new ItemStack(this, 1, aDamage));
		return this;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {
		mIcon = aIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + (GT_Config.system ? "troll" : getUnlocalizedName()));
    }
	
	@Override
    public IIcon getIconFromDamage(int par1) {
        return mIcon;
    }
	
	public int getTier(ItemStack aStack) {
		return 0;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public final void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		if (getMaxDamage() > 0 && !getHasSubtypes()) aList.add((aStack.getMaxDamage() - aStack.getItemDamage()) + " / " + aStack.getMaxDamage());
	    if (mTooltip != null && !mTooltip.equals("")) aList.add(I18n.format(mTooltip));
	    if (GT_ModHandler.isElectricItem(aStack)) aList.add(I18n.format("item.electric.tier.tooltip", getTier(aStack)));
	    addAdditionalToolTips(aList, aStack);
	}
	
	public void addAdditionalToolTips(List<String> aList, ItemStack aStack) {}
}