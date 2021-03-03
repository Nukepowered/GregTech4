package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_LanguageManager;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_MetaStone1_Item extends ItemBlock {
    public GT_MetaStone1_Item(Block par1) {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
        setUnlocalizedName(GT_LanguageManager.mNameList4[0]);
        setCreativeTab(GregTech_API.TAB_GREGTECH);
    }
    
    @Override
    public int getMetadata(int par1) {
        return par1;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean par4) {
		if (aStack.getItemDamage() % 8 > 2) aList.add(I18n.format("block.deny_mobs_spawn.tooltip"));
    }
	
	@Override
    public String getUnlocalizedName(ItemStack aStack) {
    	return getUnlocalizedName() + "." + GT_LanguageManager.mNameList4[aStack.getItemDamage()];
    }
}