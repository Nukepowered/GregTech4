package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class GT_TickHandler implements ITickHandler {
	public static boolean isFirstTick = true;
	
	public GT_TickHandler(boolean aServer) {
		TickRegistry.registerTickHandler(this, aServer?Side.SERVER:Side.CLIENT);
	}
	
    @Override
    public void tickStart(EnumSet<TickType> aType, Object... aData) {
    	
    }
    
    @Override
    public synchronized void tickEnd(EnumSet<TickType> aType, Object... aData) {
    	if (GT_Mod.mDoNotInit) return;
        if (aType.contains(TickType.SERVER)) {
        	GregTech_API.sServerTickCounter++;
        }
        if (aType.contains(TickType.CLIENT)) {
        	if (GregTech_API.sClientTickCounter++%30 == 0) GT_Utility.sPlayedSoundMap.clear();
        	/*
    		try {
    			if (GT_ConnectionHandler.sClientManager != null) {
    				if (GregTech_API.DEBUG_MODE && mTickCounter % 100 == 0) {
    					GT_ConnectionHandler.sClientManager.addToSendQueue(new Packet250CustomPayload("UNREGISTER", "DIChannel".getBytes()));
    					GT_ConnectionHandler.sClientManager.addToSendQueue(new Packet250CustomPayload("UNREGISTER", "BC".getBytes()));
    				}
    			}
			} catch (Throwable e) {
				if (GregTech_API.DEBUG_MODE) e.printStackTrace(GT_Log.err);
			}
			*/
        }
        
        if (aType.contains(TickType.WORLD)) {
        	GregTech_API.sWorldTickCounter++;
            World tWorld = (World)aData[0];
            if (tWorld != null) {
            	if (GT_Mod.mUniverse == null) GT_Mod.mUniverse = tWorld;
            	
	            if (isFirstTick) {
	            	GT_Mod.readIDSUData();
	            	isFirstTick = false;
	            }
	            
	            /*
	            if (mServerTickCounter % 1000 == 100 || mClientTickCounter % 1000 == 100) {
		            ItemStack tStack = GT_ModHandler.getAllRecipeOutput(new ItemStack[] {new ItemStack(Block.wood, 1, 0)}, tWorld);
		            if (   (GT_Mod.sNerfedStoneTools  && (Item.pickaxeStone.getMaxDamage() != 48 || Item.shovelStone.getMaxDamage() != 48 || Item.axeStone.getMaxDamage() != 48 || Item.swordStone.getMaxDamage() != 48 || Item.hoeStone.getMaxDamage() != 48))
		            	|| (GT_Mod.sNerfedWoodenTools && (Item.pickaxeWood .getMaxDamage() != 12 || Item.shovelWood .getMaxDamage() != 12 || Item.axeWood .getMaxDamage() != 12 || Item.swordWood .getMaxDamage() != 12 || Item.hoeWood .getMaxDamage() != 12))
		            	|| !GT_Utility.areStacksEqual(tStack, new ItemStack(Block.planks, 1, 0)) || tStack == null || tStack.stackSize != (GT_Mod.sNerfedWoodPlank?2:4)) {
		            	throw new GT_ItsNotMyFaultException("Another Mod decided to ACTIVELY break a fully configurable Feature of GregTech. Please report this to said Mod Author and not to me, as I can't do anything against that misbehavior. If the detection of breaking my Features, results in incompatibility between said Mod and GregTech, then I don't care.");
		            }
	            }
	            */
	            
	            if (GregTech_API.sServerTickCounter%20==0) {
		            for (Object tItem : tWorld.loadedEntityList) {
		            	if (tItem instanceof EntityItem && ((EntityItem)tItem).lifespan == 6000) {
		            		((EntityItem)tItem).lifespan = GT_Mod.sItemDespawnTime;
		            	}
		            }
	            }
	            
	            if (GregTech_API.sServerTickCounter%10000==0) GT_Mod.writeIDSUData();
            }
        }
        
        if (aType.contains(TickType.PLAYER)) {
        	EntityPlayer tPlayer = (EntityPlayer)aData[0];
        	if (tPlayer != null && !tPlayer.isDead) {
        		if (tPlayer.worldObj.isRemote) {
        			if (tPlayer instanceof EntityPlayerSP) {
        				GT_Utility.sCurrentPlayer = tPlayer;
        			}
        			if (tPlayer.username.equalsIgnoreCase("immibis")) {
        				for (int i = 1; i <= 42; i++) {
        					if (GregTech_API.sConfiguration.addAdvConfig("PumpkinOfShame", "Config.Random.Number."+new Random(i).nextInt(4200), true)) {
                				tPlayer.inventory.armorInventory[3] = new ItemStack(Block.pumpkin, 1, 0);
                				tPlayer.inventory.armorInventory[3].setItemName("Pumpkin of Shame");
                				break;
        					}
        				}
        			}
        		} else {
        			if (GregTech_API.sServerTickCounter%200==0 && (tPlayer.capabilities.allowEdit || tPlayer.isCurrentToolAdventureModeExempt(0, -10000, 0)) && !tPlayer.capabilities.isCreativeMode && GT_Mod.sSurvivalIntoAdventure) {
        				tPlayer.setGameType(EnumGameType.ADVENTURE);
        				boolean temp = tPlayer.capabilities.allowEdit || tPlayer.isCurrentToolAdventureModeExempt(0, -10000, 0);
        				tPlayer.capabilities.allowEdit = false;
        				if (temp) {
        					GT_Utility.sendChatToPlayer(tPlayer, "Adventure Mode has been broken. Please consult the nice Guys at Forge to fix whatever they broke. Best solution would be some kind of spamming the responsible Person or similar.");
        				}
        				if (GT_Mod.sAxeWhenAdventure) {
        					if (!temp) GT_Utility.sendChatToPlayer(tPlayer, "It's dangerous to go alone. Take this with you.");
        					tPlayer.worldObj.spawnEntityInWorld(new EntityItem(tPlayer.worldObj, tPlayer.posX, tPlayer.posY, tPlayer.posZ, GregTech_API.getGregTechItem(127, 1, 0)));
        				}
        			}
        			if (GregTech_API.sServerTickCounter % 120 == 0) {
		            	int tCount = 64;
		        		for (int i = 0; i < 36; i++) {
		            		if (tPlayer.inventory.getStackInSlot(i) != null) {
		            			tCount+=(tPlayer.inventory.getStackInSlot(i).stackSize * 64) / Math.max(1, tPlayer.inventory.getStackInSlot(i).getMaxStackSize());
		            			if (GT_Mod.sInventoryUnification) GT_OreDictUnificator.setStack(true, tPlayer.inventory.getStackInSlot(i));
		            		}
		        		}
		            	for (int i = 0; i < 4; i++) {
		            		if (tPlayer.inventory.armorInventory[i] != null) {
		            			tCount+=256;
		            		}
		            	}
		            	
		            	if (GT_Mod.sHungerEffect && GregTech_API.sServerTickCounter % 2400 == 1200) tPlayer.addExhaustion(Math.max(1.0F, tCount/666.6F));
        			}
        		}
        	}
        }
    }
    
    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.RENDER, TickType.WORLD, TickType.PLAYER, TickType.SERVER, TickType.CLIENT);
    }

    @Override
    public String getLabel() { return "GT_TickHandler"; }
}