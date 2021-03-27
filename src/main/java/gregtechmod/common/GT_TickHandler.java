package gregtechmod.common;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.Materials;
import gregtechmod.api.interfaces.IMetaTileEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_PlayedSound;
import gregtechmod.api.util.GT_Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings.GameType;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class GT_TickHandler {
	public static boolean isFirstTick = true;
	private long mAnimationTick = 0L;
	private boolean mAnimationDirection = false;
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if (GT_Mod.mDoNotInit || event.phase == Phase.START) return;
		GregTech_API.sServerTickCounter++;
	}
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (GT_Mod.mDoNotInit || event.phase == Phase.START) return;
		GregTech_API.sClientTickCounter++;
		this.mAnimationTick++;
		if (this.mAnimationTick % 50L == 0L)
			this.mAnimationDirection = !this.mAnimationDirection;
		
		if(this.mAnimationDirection) {
            ++Materials.Plutonium241.mRGBa[0];
            ++Materials.Plutonium241.mRGBa[1];
            ++Materials.Plutonium241.mRGBa[2];
            ++Materials.InfusedGold.mRGBa[0];
            ++Materials.InfusedGold.mRGBa[1];
            ++Materials.InfusedGold.mRGBa[2];
            ++Materials.Uranium235.mRGBa[0];
            ++Materials.Uranium235.mRGBa[1];
            ++Materials.Uranium235.mRGBa[2];
            ++Materials.NetherStar.mRGBa[0];
            ++Materials.NetherStar.mRGBa[1];
            ++Materials.NetherStar.mRGBa[2];
            ++Materials.Glowstone.mRGBa[0];
            ++Materials.Glowstone.mRGBa[1];
            ++Materials.Sunnarium.mRGBa[0];
            ++Materials.Sunnarium.mRGBa[1];
            ++Materials.Pyrotheum.mRGBa[0];
            ++Materials.Pyrotheum.mRGBa[1];
            ++Materials.Enderium.mRGBa[0];
            ++Materials.Enderium.mRGBa[1];
            ++Materials.Enderium.mRGBa[2];
            ++Materials.Thaumium.mRGBa[0];
            ++Materials.Thaumium.mRGBa[2];
            ++Materials.Vinteum.mRGBa[0];
            ++Materials.Vinteum.mRGBa[1];
            ++Materials.Vinteum.mRGBa[2];
            ++Materials.Force.mRGBa[0];
            ++Materials.Force.mRGBa[1];
         } else {
            --Materials.Plutonium241.mRGBa[0];
            --Materials.Plutonium241.mRGBa[1];
            --Materials.Plutonium241.mRGBa[2];
            --Materials.InfusedGold.mRGBa[0];
            --Materials.InfusedGold.mRGBa[1];
            --Materials.InfusedGold.mRGBa[2];
            --Materials.Uranium235.mRGBa[0];
            --Materials.Uranium235.mRGBa[1];
            --Materials.Uranium235.mRGBa[2];
            --Materials.NetherStar.mRGBa[0];
            --Materials.NetherStar.mRGBa[1];
            --Materials.NetherStar.mRGBa[2];
            --Materials.Glowstone.mRGBa[0];
            --Materials.Glowstone.mRGBa[1];
            --Materials.Sunnarium.mRGBa[0];
            --Materials.Sunnarium.mRGBa[1];
            --Materials.Pyrotheum.mRGBa[0];
            --Materials.Pyrotheum.mRGBa[1];
            --Materials.Enderium.mRGBa[0];
            --Materials.Enderium.mRGBa[1];
            --Materials.Enderium.mRGBa[2];
            --Materials.Thaumium.mRGBa[0];
            --Materials.Thaumium.mRGBa[2];
            --Materials.Vinteum.mRGBa[0];
            --Materials.Vinteum.mRGBa[1];
            --Materials.Vinteum.mRGBa[2];
            --Materials.Force.mRGBa[0];
            --Materials.Force.mRGBa[1];
         }
		
	}
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if (GT_Mod.mDoNotInit || event.phase == Phase.START) return;
		GregTech_API.sWorldTickCounter++;
        if (event.world != null) {
        	if (GT_Mod.mUniverse == null) GT_Mod.mUniverse = event.world;
        	
            if (isFirstTick) {
                for (IMetaTileEntity tMetaTileEntity : GregTech_API.mMetaTileList) {
                	try {
                		if (tMetaTileEntity != null) tMetaTileEntity.onFirstServerTick();
                	} catch (Throwable e) {
                		GT_Log.log.catching(e);
                	}
                }

                isFirstTick = false;
            }
            
            if (GregTech_API.sServerTickCounter % 20 == 0) {
	            for (Object tItem : event.world.loadedEntityList) {
	            	if (tItem instanceof EntityItem && ((EntityItem)tItem).lifespan == 6000) {
	            		((EntityItem)tItem).lifespan = GT_Mod.sItemDespawnTime;
	            	}
	            }
            }
        }
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    	if (event.phase != Phase.START && event.player != null && !event.player.isDead) {
    		if (event.player.worldObj.isRemote) {
    			List<GT_PlayedSound> vals = new ArrayList<>();
    			for (Entry<GT_PlayedSound, Integer> entry : GT_Utility.sPlayedSoundMap.entrySet()) {
    				if (entry.getValue() < 0) {
    					vals.add(entry.getKey());
    				} else {
    					entry.setValue(entry.getValue() - 1);
    				}
    			}
    			
    			vals.forEach(sound -> GT_Utility.sPlayedSoundMap.remove(sound));
    			if (event.player.getDisplayName().equalsIgnoreCase("immibis")) {
    				for (int i = 1; i <= 42; i++) {
    					if (GregTech_API.sSpecialFile.get("PumpkinOfShame", "Config.Random.Number."+new Random(i).nextInt(4200), true)) {
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
    					GT_Utility.sendChatToPlayer(event.player, new ChatComponentTranslation("gregtech.adventure.1"));
    				}
    				if (GT_Mod.sAxeWhenAdventure) {
    					if (!temp) GT_Utility.sendChatToPlayer(event.player, new ChatComponentTranslation("gregtech.adventure.2"));
    					event.player.worldObj.spawnEntityInWorld(new EntityItem(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ, GT_Items.Tool_Axe_Flint.get(1)));
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