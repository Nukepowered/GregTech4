package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;

import java.util.Random;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldSettings.GameType;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class GT_TickHandler {
	public static boolean isFirstTick = true;
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (GT_Mod.mDoNotInit || event.phase == Phase.START) return;
		GregTech_API.sServerTickCounter++;
	}
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (GT_Mod.mDoNotInit || event.phase == Phase.START) return;
		if (GregTech_API.sClientTickCounter++ % 30 == 0) GT_Utility.sPlayedSoundMap.clear();
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
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if (GT_Mod.mDoNotInit || event.phase == Phase.START) return;
		GregTech_API.sWorldTickCounter++;
        if (event.world != null) {
        	if (GT_Mod.mUniverse == null) GT_Mod.mUniverse = event.world;
        	
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
	            for (Object tItem : event.world.loadedEntityList) {
	            	if (tItem instanceof EntityItem && ((EntityItem)tItem).lifespan == 6000) {
	            		((EntityItem)tItem).lifespan = GT_Mod.sItemDespawnTime;
	            	}
	            }
            }
            
            if (GregTech_API.sServerTickCounter % 10000 == 0) GT_Mod.writeIDSUData();
        }
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    	if (event.phase != Phase.START && event.player != null && !event.player.isDead) {
    		if (event.player.worldObj.isRemote) {
    			if (event.player instanceof EntityPlayerSP) {
    				GT_Utility.sCurrentPlayer = event.player;
    			}
    			if (event.player.getDisplayName().equalsIgnoreCase("immibis")) {
    				for (int i = 1; i <= 42; i++) {
    					if (GregTech_API.sConfiguration.addAdvConfig("PumpkinOfShame", "Config.Random.Number."+new Random(i).nextInt(4200), true)) {
            				break;
    					}
    				}
    				
					event.player.inventory.armorInventory[3] = new ItemStack(Blocks.pumpkin, 1, 0);
					event.player.inventory.armorInventory[3].setStackDisplayName("Pumpkin of Shame");
    			}
    		} else {
    			if (GregTech_API.sServerTickCounter % 200==0 && (event.player.capabilities.allowEdit || event.player.isCurrentToolAdventureModeExempt(0, -10000, 0)) && !event.player.capabilities.isCreativeMode && GT_Mod.sSurvivalIntoAdventure) {
    				event.player.setGameType(GameType.ADVENTURE);
    				boolean temp = event.player.capabilities.allowEdit || event.player.isCurrentToolAdventureModeExempt(0, -10000, 0);
    				event.player.capabilities.allowEdit = false;
    				if (temp) {
    					GT_Utility.sendChatToPlayer(event.player, "Adventure Mode has been broken. Please consult the nice Guys at Forge to fix whatever they broke. Best solution would be some kind of spamming the responsible Person or similar.");
    				}
    				if (GT_Mod.sAxeWhenAdventure) {
    					if (!temp) GT_Utility.sendChatToPlayer(event.player, "It's dangerous to go alone. Take this with you.");
    					event.player.worldObj.spawnEntityInWorld(new EntityItem(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ, GregTech_API.getGregTechItem(127, 1, 0)));
    				}
    			}
    			if (GregTech_API.sServerTickCounter % 120 == 0) {
	            	int tCount = 64;
	        		for (int i = 0; i < 36; i++) {
	            		if (event.player.inventory.getStackInSlot(i) != null) {
	            			tCount+=(event.player.inventory.getStackInSlot(i).stackSize * 64) / Math.max(1, event.player.inventory.getStackInSlot(i).getMaxStackSize());
	            			if (GT_Mod.sInventoryUnification) GT_OreDictUnificator.setStack(true, event.player.inventory.getStackInSlot(i));
	            		}
	        		}
	            	for (int i = 0; i < 4; i++) {
	            		if (event.player.inventory.armorInventory[i] != null) {
	            			tCount += 256;
	            		}
	            	}
	            	
	            	if (GT_Mod.sHungerEffect && GregTech_API.sServerTickCounter % 2400 == 1200) event.player.addExhaustion(Math.max(1.0F, tCount/666.6F));
    			}
    		}
    	}
	}
}