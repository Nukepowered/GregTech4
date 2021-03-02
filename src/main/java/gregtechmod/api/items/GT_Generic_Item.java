package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.*;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Extended by most Items, also used as a fallback Item, to prevent the accidental deletion when Errors occur.
 */
public class GT_Generic_Item extends Item {
	public IIcon mIcon;
	protected final String mTooltip;
	
	public GT_Generic_Item(String aUnlocalized, String aTooltipKey) {
		this.mTooltip = aTooltipKey;
		setUnlocalizedName(aUnlocalized);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
	}
	
	public final GT_Generic_Item registerAtOreDict(Object aName, short aDamage) {
		GT_OreDictUnificator.registerOreLater(aName, new ItemStack(this, 1, aDamage));
		return this;
	}
	
	public final GT_Generic_Item registerAtOreDictWildcard(Object aName) {
		return registerAtOreDict(aName, GregTech_API.ITEM_WILDCARD_DAMAGE);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {
		mIcon = aIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + (GT_Config.system?"troll":getUnlocalizedName()));
    }
	
    public boolean doesSneakBypassUse(World aWorld, int aX, int aY, int aZ) {
        return true;
    }
	
	@Override
    public IIcon getIconFromDamage(int par1) {
        return mIcon;
    }
	
	public int getTier(ItemStack aStack) {
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean advanced) {
		if (getMaxDamage() > 0 && !getHasSubtypes() && advanced) aList.add((aStack.getMaxDamage() - getDamage(aStack)) + " / " + aStack.getMaxDamage());
	    if (GT_Utility.isStringValid(mTooltip)) aList.add(I18n.format(mTooltip));
	    if (GT_ModHandler.isElectricItem(aStack)) aList.add(I18n.format("item.electric.tier.tooltip", getTier(aStack)));
	    addAdditionalToolTips(aList, aStack);
	}
	
	@SideOnly(Side.CLIENT)
	protected void addAdditionalToolTips(List<String> aList, ItemStack aStack) {
	}
}