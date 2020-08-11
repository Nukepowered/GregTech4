package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.Dyes;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_Spray_Color_Item extends GT_Tool_Item {
	public byte mColorMeta = 0;
	
	public GT_Spray_Color_Item(Item aItem, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage, byte aColorMeta) {
		super(aItem, aUnlocalized, aEnglish, "To give the World more Color", aMaxDamage, aEntityDamage, true);
		GT_OreDictUnificator.registerOre(Dyes.get(mColorMeta = aColorMeta), new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		setCraftingSound(GregTech_API.sSoundList.get(102));
		setBreakingSound(GregTech_API.sSoundList.get(102));
		setEntityHitSound(GregTech_API.sSoundList.get(102));
		setUsageAmounts(32, 3, 1);
	}
	
	@Override
	//TODO: Localization
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
//		aList.add(GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".tooltip_3", "Enough for dying " + getMaxDamage() + " Blocks in World " + Dyes.get(mColorMeta).mName.toLowerCase()));
//		aList.add(GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".tooltip_2", "Enough for crafting " + (getMaxDamage()/getDamagePerContainerItemCraft()) + " times"));
	}
	
	@Override
	public ItemStack getEmptyItem(ItemStack aStack) {
		return GT_Items.Spray_Empty.get(1);
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
//    	TileEntity aTileEntity = aWorld.getBlockTileEntity(aX, aY, aZ);
    	
    	if (aBlock == Blocks.carpet || aBlock == Blocks.hardened_clay || aBlock == Blocks.stained_hardened_clay || aBlock == GT_Items.TE_Rockwool.getBlock()) {
    		if (aMeta == (~mColorMeta & 15) && aBlock != Blocks.hardened_clay) return false;
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
	    		if (aBlock == Blocks.hardened_clay)
					aWorld.setBlock(aX, aY, aZ, Blocks.stained_hardened_clay, ~mColorMeta & 15, 3);
				else
					aWorld.setBlockMetadataWithNotify(aX, aY, aZ, ~mColorMeta & 15, 3);
			}
    		return true;
    	}
    	
    	if (aBlock.recolourBlock(aWorld, aX, aY, aZ, ForgeDirection.getOrientation(aSide), ~mColorMeta & 15)) {
    		GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer);
			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
    		return true;
    	}
    	
    	return false;
    }
}