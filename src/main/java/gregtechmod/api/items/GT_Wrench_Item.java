package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_Wrench_Item extends GT_Tool_Item {
	
	public GT_Wrench_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage, int aDischargedGTID) {
		super(aUnlocalized, "item.GT_Wrech.tooltip", aMaxDamage, aEntityDamage, true, -1, aDischargedGTID);
		GregTech_API.registerWrench(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		registerAtOreDictWildcard(GT_ToolDictNames.craftingToolWrench);
		addToEffectiveList(EntityIronGolem.class.getName());
		addToEffectiveList("EntityTFTowerGolem");
		addToEffectiveList("EntityGolemBase");
		addToEffectiveList("EntityGolemClay");
		addToEffectiveList("EntityGolemClayAdvanced");
		addToEffectiveList("EntityGolemIronGuardian");
		addToEffectiveList("EntityGolemStone");
		addToEffectiveList("EntityGolemStoneAdvanced");
		addToEffectiveList("EntityGolemStraw");
		addToEffectiveList("EntityGolemTallow");
		addToEffectiveList("EntityGolemTallowAdvanced");
		addToEffectiveList("EntityGolemWarrior");
		addToEffectiveList("EntityGolemWood");
		addToEffectiveList("EntityGolemWorker");
		addToEffectiveList("EntitySummonedEarthGolem");
		addToEffectiveList("EntityTowerGuardian");
		setCraftingSound(GregTech_API.sSoundList.get(100));
		setBreakingSound(GregTech_API.sSoundList.get(100));
		setUsageAmounts(8, 3, 1);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		aList.add(I18n.format("item.GT_Wrech.tooltip_1"));
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
    	if (aWorld.isRemote) {
    		return false;
    	}
    	Block aBlock = aWorld.getBlock(aX, aY, aZ);
    	if (aBlock == null) return false;
    	byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ), aTargetSide = GT_Utility.determineWrenchingSide((byte)aSide, hitX, hitY, hitZ);
    	TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
    	
    	try {
        	if (aTileEntity != null && aTileEntity instanceof ic2.api.tile.IWrenchable) {
        		if (((ic2.api.tile.IWrenchable)aTileEntity).wrenchCanSetFacing(aPlayer, aTargetSide)) {
        			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
            			((ic2.api.tile.IWrenchable)aTileEntity).setFacing(aTargetSide);
            			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
            		}
        			return true;
        		}
        		if (((ic2.api.tile.IWrenchable)aTileEntity).wrenchCanRemove(aPlayer)) {
        			int tDamage = (((ic2.api.tile.IWrenchable)aTileEntity).getWrenchDropRate() < 1.0F ? 10 : 3);
        			if (GT_ModHandler.damageOrDechargeItem(aStack, tDamage, tDamage*1000, aPlayer)) {
        				ItemStack tOutput = ((ic2.api.tile.IWrenchable)aTileEntity).getWrenchDrop(aPlayer);
            			for (ItemStack tStack : aBlock.getDrops(aWorld, aX, aY, aZ, aMeta, 0)) {
            				if (tOutput == null) {
                				aWorld.spawnEntityInWorld(new EntityItem(aWorld, aX+0.5, aY+0.5, aZ+0.5, tStack));
            				} else {
                				aWorld.spawnEntityInWorld(new EntityItem(aWorld, aX+0.5, aY+0.5, aZ+0.5, tOutput));
                				tOutput = null;
            				}
            			}
            			aWorld.setBlockToAir(aX, aY, aZ);
            			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
            		}
        			return true;
        		}
        		return true;
        	}
        } catch(Throwable e) {/*Do nothing*/}
//    	try {
//        	if (aTileEntity instanceof universalelectricity.prefab.tile.IRotatable) {
//        		if (((universalelectricity.prefab.tile.IRotatable)aTileEntity).getDirection().ordinal() != aTargetSide) {
//        			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
//            			((universalelectricity.prefab.tile.IRotatable)aTileEntity).setDirection(ForgeDirection.getOrientation(aTargetSide));
//            			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
//            		}
//        			return true;
//        		}
//        		return true;
//        	}
//        } catch(Throwable e) {/*Do nothing*/}
    	
    	if (aBlock == Blocks.log || aBlock == Blocks.hay_block) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta + 4) % 12, 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	
    	if (aBlock == Blocks.powered_repeater || aBlock == Blocks.unpowered_repeater) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta / 4) * 4  + (((aMeta%4) + 1) % 4), 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	
    	if (aBlock == Blocks.powered_comparator || aBlock == Blocks.unpowered_comparator) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta / 4) * 4  + (((aMeta%4) + 1) % 4), 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
    	
    	if (aBlock == Blocks.crafting_table || aBlock == Blocks.bookshelf) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
    			aWorld.spawnEntityInWorld(new EntityItem(aWorld, aX+0.5, aY+0.5, aZ+0.5, new ItemStack(aBlock, 1, aMeta)));
    			aWorld.setBlockToAir(aX, aY, aZ);
    			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
    		}
    		return true;
    	}
    	
    	if (aMeta == aTargetSide) {
	    	if (aBlock == Blocks.piston || aBlock == Blocks.sticky_piston || aBlock == Blocks.dispenser || aBlock == Blocks.dropper || aBlock == Blocks.furnace || aBlock == Blocks.lit_furnace || aBlock == Blocks.chest || aBlock == Blocks.trapped_chest || aBlock == Blocks.hopper) {
	    		if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
        			aWorld.spawnEntityInWorld(new EntityItem(aWorld, aX+0.5, aY+0.5, aZ+0.5, new ItemStack(aBlock, 1, 0)));
        			aWorld.setBlockToAir(aX, aY, aZ);
        			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
	    		}
	    		return true;
	    	}
    	} else {
	    	if (aBlock == Blocks.piston || aBlock == Blocks.sticky_piston || aBlock == Blocks.dispenser || aBlock == Blocks.dropper) {
				if (aMeta < 6 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
					aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aTargetSide, 3);
					GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
		    	}
	    		return true;
	    	}
	    	if (aBlock == Blocks.pumpkin || aBlock == Blocks.lit_pumpkin || aBlock == Blocks.furnace || aBlock == Blocks.lit_pumpkin || aBlock == Blocks.chest || aBlock == Blocks.trapped_chest) {
				if (aTargetSide > 1 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
					aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aTargetSide, 3);
					GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
		    	}
	    		return true;
	    	}
	    	if (aBlock == Blocks.hopper) {
				if (aTargetSide != 1 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
					aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aTargetSide, 3);
					GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
		    	}
	    		return true;
	    	}
    	}
    	
    	if (Arrays.asList(aBlock.getValidRotations(aWorld, aX, aY, aZ)).contains(ForgeDirection.getOrientation(aTargetSide))) {
    		if (!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, 1000)) {
    			if (aBlock.rotateBlock(aWorld, aX, aY, aZ, ForgeDirection.getOrientation(aTargetSide))) {
    				GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer);
    				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(100), 1.0F, -1, aX, aY, aZ);
    			}
    		}
    	}
    	
    	return false;
    }
}