package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class GT_Spray_Ice_Item extends GT_Tool_Item {
	public GT_Spray_Ice_Item( String aName, int aMaxDamage, int aEntityDamage) {
		super(aName, "Very effective against Slimes", aMaxDamage, aEntityDamage);
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
		setUsageAmounts(32, 16, 1);
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.ice, 1, 0), false, new Object[] {new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), new ItemStack(Items.water_bucket, 1)});
		GT_ModHandler.addShapelessCraftingRecipe(new ItemStack(Blocks.ice, 1, 0), false, new Object[] {new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getWaterCell(1)});
	}
	
	@Override
	public void onHitEntity(Entity aEntity) {
		if (aEntity instanceof EntityLiving) {
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 400, 2, false));
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 400, 2, false));
		}
	}
	
	@Override
	public ItemStack getEmptyItem(ItemStack aStack) {
		return GT_OreDictUnificator.getFirstOre("craftingSprayCan", 1);
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
    	Block aBlock = aWorld.getBlock(aX, aY, aZ)];
    	if (aBlock == null) return false;
    	byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
    	TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
    	
    	if (aBlock == Blocks.water || aBlock == Blocks.flowing_water) {
    		if (aMeta == 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
    			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
        		aWorld.setBlock(aX, aY, aZ, Block.ice.blockID, 0, 3);
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