package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GT_Spray_Foam_Item extends GT_Tool_Item {
	public GT_Spray_Foam_Item(String aUnlocalized, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, "item.GT_Spray_Foam.tooltip_main", aMaxDamage, aEntityDamage, true);
		setCraftingSound(GregTech_API.sSoundList.get(102));
		setBreakingSound(GregTech_API.sSoundList.get(102));
		setEntityHitSound(GregTech_API.sSoundList.get(102));
		setUsageAmounts(25, 3, 1);
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
	
	public void switchMode(ItemStack aStack, EntityPlayer aPlayer) {
        setMode(aStack, (getMode(aStack) + 1) % 3);
        switch (getMode(aStack)) {
        case 0: GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("item.GT_Spray_Foam.mode_1")); break;
        case 1: GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("item.GT_Spray_Foam.mode_2")); break;
        case 2: GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentTranslation("item.GT_Spray_Foam.mode_3")); break;
        }
	}
	
	@Override
	public void addAdditionalToolTips(List<String> aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
        switch (getMode(aStack)) {
        case 0: aList.add(I18n.format("item.GT_Spray_Foam.mode_1")); break;
        case 1: aList.add(I18n.format("item.GT_Spray_Foam.mode_2")); break;
        case 2: aList.add(I18n.format("item.GT_Spray_Foam.mode_3")); break;
        }
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (aPlayer.isSneaking()) switchMode(aStack, aPlayer);
        return super.onItemRightClick(aStack, aWorld, aPlayer);
    }
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
    	super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aPlayer.isSneaking()) return false;
		if (aWorld.isRemote) {
    		return false;
    	}
    	Block aBlock = aWorld.getBlock(aX, aY, aZ);
    	if (aBlock == null) return false;
    	TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
    	
    	try {
    		if (GT_Utility.getClassName(aTileEntity).startsWith("TileEntityCable")) {
    			if (GT_Utility.getPublicField(aTileEntity, "foamed").getByte(aTileEntity) == 0) {
    				if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
    					GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
    		    		GT_Utility.callPublicMethod(aTileEntity, "changeFoam", (byte)1);
    	    			return true;
    				}
    			}
    			return false;
    		}
    	} catch(Throwable e) {
    		if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
    	}
    	
    	if (aTileEntity instanceof BaseMetaPipeEntity && (((BaseMetaPipeEntity)aTileEntity).mConnections & -64) == 0) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
				((BaseMetaPipeEntity)aTileEntity).mConnections |= 64;
			}
			return true;
    	}
    	aX += ForgeDirection.getOrientation(aSide).offsetX;
    	aY += ForgeDirection.getOrientation(aSide).offsetY;
    	aZ += ForgeDirection.getOrientation(aSide).offsetZ;
    	
    	ItemStack tStack = GT_ModHandler.getIC2Item("constructionFoam", 1);
    	if (tStack != null && tStack.getItem() instanceof ItemBlock) {
            int tRotationPitch = Math.round(aPlayer.rotationPitch);
            byte tSide = 0;
            if (tRotationPitch >= 65) {
            	tSide = 1;
            } else if (tRotationPitch <= -65) {
            	tSide = 0;
            } else {
                switch (MathHelper.floor_double((aPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) {
                case 0: tSide = 2; break;
                case 1: tSide = 5; break;
                case 2: tSide = 3; break;
                case 3: tSide = 4; break;
                }
            }
    		switch (getMode(aStack)) {
    		case 0:
    			if (GT_Utility.isAirBlock(aWorld, aX, aY, aZ) && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
    				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
    	    		aWorld.setBlock(aX, aY, aZ, Block.getBlockFromItem((ItemBlock)tStack.getItem()), tStack.getItemDamage(), 3);
    	    		return true;
    			}
    			break;
    		case 1:
	            for (byte i = 0; i < 4; i++) {
	    			if (GT_Utility.isAirBlock(aWorld, aX, aY, aZ) && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
	    				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
	    	    		aWorld.setBlock(aX, aY, aZ, Block.getBlockFromItem((ItemBlock)tStack.getItem()), tStack.getItemDamage(), 3);
	    			} else {
	    				if (i == 0) return false;
	    				break;
	    			}
	            	aX -= ForgeDirection.getOrientation(tSide).offsetX;
	            	aY -= ForgeDirection.getOrientation(tSide).offsetY;
	            	aZ -= ForgeDirection.getOrientation(tSide).offsetZ;
	            }
	    		return true;
    		case 2:
    			boolean temp = false,
    			tXFactor = (ForgeDirection.getOrientation(tSide).offsetX == 0),
    			tYFactor = (ForgeDirection.getOrientation(tSide).offsetY == 0),
    			tZFactor = (ForgeDirection.getOrientation(tSide).offsetZ == 0);
    			
	            aX -= (tXFactor ? 1 : 0);
	            aY -= (tYFactor ? 1 : 0);
	            aZ -= (tZFactor ? 1 : 0);
	            
	            for (byte i = 0; i < 3; i++) for (byte j = 0; j < 3; j++) {
			    	if (GT_Utility.isAirBlock(aWorld, aX + (tXFactor?i:0), aY + (!tXFactor&&tYFactor?i:0) + (!tZFactor&&tYFactor?j:0), aZ + (tZFactor?j:0))) {
			    		if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
			    			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
			        		aWorld.setBlock(aX + (tXFactor?i:0), aY + (!tXFactor&&tYFactor?i:0) + (!tZFactor&&tYFactor?j:0), aZ + (tZFactor?j:0), Block.getBlockFromItem((ItemBlock)tStack.getItem()), tStack.getItemDamage(), 3);
			    			temp = true;
			    		} else {
			    			break;
			    		}
			    	}
	            }
	    		return temp;
    		}
    	}
    	return false;
    }
}