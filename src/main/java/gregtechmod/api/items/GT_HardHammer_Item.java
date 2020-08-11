package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidBlock;

public class GT_HardHammer_Item extends GT_Tool_Item {
	public GT_HardHammer_Item(Item aItem, String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
		super(aItem, aUnlocalized, aEnglish, "To give a Machine a hard whack", aMaxDamage, aEntityDamage, true);
		GregTech_API.registerHardHammer(new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		GT_OreDictUnificator.registerOre(GT_ToolDictNames.craftingToolHardHammer, new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
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
		setCraftingSound(GregTech_API.sSoundList.get(1));
		setBreakingSound(GregTech_API.sSoundList.get(2));
		setToolClasses("hammer");
		setUsageAmounts(4, 3, 1);
	}
	
	@Override
	//TODO:Localization
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
//		aList.add(GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".tooltip_1", "Used to craft Plates from Ingots"));
//		aList.add(GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".tooltip_2", "Can rotate some Blocks as well"));
//		aList.add(GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".tooltip_3", "Also used to toggle general Machine states"));
//		aList.add(GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".tooltip_4", "Usable as Prospectors Hammer"));
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
    	
    	if (aBlock == Blocks.log || aBlock == Blocks.hay_block) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta + 4) % 12, 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1, aX, aY, aZ);
			}
    		return true;
    	}
	    if (aBlock == Blocks.piston || aBlock == Blocks.sticky_piston || aBlock == Blocks.dispenser || aBlock == Blocks.dropper) {
			if (aMeta < 6 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta+1) % 6, 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1, aX, aY, aZ);
			}
	    	return true;
	    }
	    if (aBlock == Blocks.pumpkin || aBlock == Blocks.lit_pumpkin || aBlock == Blocks.furnace || aBlock == Blocks.lit_furnace || aBlock == Blocks.chest || aBlock == Blocks.trapped_chest) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, ((aMeta-1)%4)+2, 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1, aX, aY, aZ);
			}
	    	return true;
	    }
	    if (aBlock == Blocks.hopper) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, (aMeta+1)%6==1?(aMeta+1)%6:2, 3);
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1, aX, aY, aZ);
			}
	    	return true;
	    }
	    
    	String tString = GT_OreDictUnificator.getAssociation(new ItemStack(aBlock, 1, aMeta));
    	if (tString != null && tString.startsWith("ore")) {
			GT_Utility.sendChatToPlayer(aPlayer, "This is " + GT_Utility.capitalizeString(tString.replaceFirst("ore", "")) + " Ore.");
			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1, aX, aY, aZ);
    		return true;
    	}
    	
	    if (aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone) || aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, GregTech_API.sBlockList[5]) || aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack) || aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone)) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1, aX, aY, aZ);
				int tX = aX, tY = aY, tZ = aZ, tMetaID = 0;
				Block tBlock = null;
	            for (byte i = 0; i < 7; i++) {
	            	tX -= ForgeDirection.getOrientation(aSide).offsetX;
	            	tY -= ForgeDirection.getOrientation(aSide).offsetY;
	            	tZ -= ForgeDirection.getOrientation(aSide).offsetZ;
	            	
			    	tBlock = aWorld.getBlock(tX, tY, tZ);
		    		if (tBlock == Blocks.lava || tBlock == Blocks.flowing_lava) {
		    			GT_Utility.sendChatToPlayer(aPlayer, "There is Lava behind this Rock.");
				    	break;
		    		}
		    		if (tBlock == Blocks.water || tBlock == Blocks.flowing_water  || (tBlock !=null && tBlock instanceof IFluidBlock)) {
		    			GT_Utility.sendChatToPlayer(aPlayer, "There is a Liquid behind this Rock.");
				    	break;
			    	}
		    		if (tBlock == Blocks.monster_egg || !GT_Utility.hasBlockHitBox(aWorld, tX, tY, tZ)) {
		    			GT_Utility.sendChatToPlayer(aPlayer, "There is an Air Pocket behind this Rock.");
				    	break;
		    		}
		    		if (tBlock != aBlock) {
		    			if (i < 4) GT_Utility.sendChatToPlayer(aPlayer, "Material is changing behind this Rock.");
				    	break;
		    		}
		    	}
	            
			    Random tRandom = new Random(aX^aY^aZ^aSide);
			    for (byte i = 0; i < 11; i++) {
			    	tX = aX-5+tRandom.nextInt(11);
			    	tY = aY-5+tRandom.nextInt(11);
			    	tZ = aZ-5+tRandom.nextInt(11);
			    	tBlock = aWorld.getBlock(tX, tY, tZ);
			    	tMetaID = aWorld.getBlockMetadata(tX, tY, tZ);
			    	tString = GT_OreDictUnificator.getAssociation(new ItemStack(tBlock, 1, tMetaID));
			    	if (tString != null && tString.startsWith("ore")) {
					    GT_Utility.sendChatToPlayer(aPlayer, "Found traces of " + GT_Utility.capitalizeString(tString.replaceFirst("ore", "")) + " Ore.");
						GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(1), 1.0F, -1, aX, aY, aZ);
			    		return true;
			    	}
			    }
			    GT_Utility.sendChatToPlayer(aPlayer, "No Ores found.");
			}
    		return true;
	    }
	    
    	return false;
    }
}