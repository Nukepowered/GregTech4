package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.enums.OrePrefixes;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_Spray_Ice_Item extends GT_Tool_Item {
	public GT_Spray_Ice_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, "item.GT_Spray_Ice.tooltip_main", aMaxDamage, aEntityDamage, true);
		addToEffectiveList(EntitySlime.class.getName());
		addToEffectiveList("BlueSlime");
		addToEffectiveList("SlimeClone");
		addToEffectiveList("MetalSlime");
		addToEffectiveList("EntityTFFireBeetle");
		addToEffectiveList("EntityTFMazeSlime");
		addToEffectiveList("EntityTFSlimeBeetle");
		setCraftingSound(GregTech_API.sSoundList.get(102));
		setBreakingSound(GregTech_API.sSoundList.get(102));
		setEntityHitSound(GregTech_API.sSoundList.get(102));
		setUsageAmounts(4, 16, 1);
		
		for (String tName : Arrays.asList(OrePrefixes.bucket.get(Materials.Water), OrePrefixes.cell.get(Materials.Water), OrePrefixes.capsule.get(Materials.Water))) {
			GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.ice, 1, 0), false, new Object[] {new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), tName});
		}
	}
	
	@Override
	public void onHitEntity(Entity aEntity) {
		if (aEntity instanceof EntityLiving) {
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 400, 2, false));
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 400, 2, false));
		}
	}
	
	@Override
	public Item getEmptyItem(ItemStack aStack) {
		ItemStack empty = GT_Items.Spray_Empty.get(1);
		aStack.func_150996_a(empty.getItem());
		aStack.stackSize = 1;
		aStack.setTagCompound(empty.getTagCompound());
		aStack.setItemDamage(empty.getItemDamage());
		return empty.getItem();
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
    	super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
    	if (aWorld.isRemote) {
    		return false;
    	}
    	aX += ForgeDirection.getOrientation(aSide).offsetX;
    	aY += ForgeDirection.getOrientation(aSide).offsetY;
    	aZ += ForgeDirection.getOrientation(aSide).offsetZ;
    	Block aBlock = aWorld.getBlock(aX, aY, aZ);
    	if (aBlock == null) return false;
    	byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
    	
    	if (aBlock == Blocks.water || aBlock == Blocks.flowing_water) {
    		if (aMeta == 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
    			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
        		aWorld.setBlock(aX, aY, aZ, Blocks.ice, 0, 3);
    			return true;
    		}
    		return false;
    	}
    	
    	if (aBlock == Blocks.lava || aBlock == Blocks.flowing_lava) {
    		if (aMeta == 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
    			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
        		aWorld.setBlock(aX, aY, aZ, Blocks.obsidian, 0, 3);
    			return true;
    		}
    		return false;
    	}
    	return false;
    }
}