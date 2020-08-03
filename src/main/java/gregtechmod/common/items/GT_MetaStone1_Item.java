package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_LanguageManager;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class GT_MetaStone1_Item extends ItemBlock {
    public GT_MetaStone1_Item(int par1) {
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
    
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean par4) {
		if (aStack.getItemDamage() % 8 > 2) aList.add("Mobs can't spawn on this Block");
    }
	
	@Override
    public String getUnlocalizedName(ItemStack aStack) {
    	return getUnlocalizedName() + "." + GT_LanguageManager.mNameList4[aStack.getItemDamage()];
    }
}