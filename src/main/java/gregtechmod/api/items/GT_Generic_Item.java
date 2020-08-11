package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.*;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
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
	
	
	public GT_Generic_Item(Item aItem, String aUnlocalized, String aEnglish, String aEnglishTooltip) {
		this(aItem, aUnlocalized, aEnglish, aEnglishTooltip, true);
	}
	//TODO: сделать локализацию
	public GT_Generic_Item(Item aItem, String aUnlocalized, String aEnglish, String aEnglishTooltip, boolean aWriteToolTipIntoLangFile) {
		super();
		setUnlocalizedName(aUnlocalized);
//		GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".name", aEnglish);
//		if (GT_Utility.isStringValid(aEnglishTooltip)) GT_LanguageManager.addStringLocalization(mTooltip = getUnlocalizedName() + ".tooltip_main", aEnglishTooltip, aWriteToolTipIntoLangFile); else mTooltip = null;
		setCreativeTab(GregTech_API.TAB_GREGTECH);
	}
	
	public final GT_Generic_Item registerAtOreDict(String aName, short aDamage) {
		GT_OreDictUnificator.registerOre(aName, new ItemStack(this, 1, aDamage));
		return this;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {
		mIcon = aIconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + (GT_Config.system?"troll":getUnlocalizedName()));
    }
	
	//TODO: Посмотри, нужен ли вообще такой метод
    public boolean shouldPassSneakingClickToBlock(World aWorld, int aX, int aY, int aZ) {
        return true;
    }
	
	@Override
    public IIcon getIconFromDamage(int par1) {
        return mIcon;
    }
	
	public int getTier(ItemStack aStack) {
		return 0;
	}
	
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
//		if (getMaxDamage() > 0 && !getHasSubtypes()) aList.add((aStack.getMaxDamage() - getDamage(aStack)) + " / " + aStack.getMaxDamage());
//	    if (mTooltip != null) aList.add(GT_LanguageManager.getTranslation(mTooltip));
//	    if (GT_ModHandler.isElectricItem(aStack)) aList.add("Tier: " + getTier(aStack));
	    addAdditionalToolTips(aList, aStack);
	}
	
	protected void addAdditionalToolTips(List aList, ItemStack aStack) {
		//
	}
}