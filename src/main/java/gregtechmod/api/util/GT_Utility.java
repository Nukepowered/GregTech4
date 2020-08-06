package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.events.GT_ScannerEvent;
import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IDebugableBlock;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.interfaces.IUpgradableMachine;
import gregtechmod.api.items.GT_EnergyArmor_Item;
import gregtechmod.common.network.GT_PacketHandler;
import gregtechmod.common.network.packet.GT_SoundPacket;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;

import cpw.mods.fml.common.gameevent.PlayerEvent;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * Just a few Utility Functions I use.
 */
public class GT_Utility {
	public static volatile int VERSION = 404;
	
	/**
	 * Automatically getting set on the Client to get "the" Player
	 */
	public static EntityPlayer sCurrentPlayer = null;
	
	public static final List<Character> sNumberedCharacters = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	public static final List<Character> sUpperCasedCharacters = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	public static final List<Character> sLowerCasedCharacters = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	
	@Deprecated
	public static void applyUsagesForMaterials(ItemStack aMat, ItemStack aOutput, boolean aBackSmelting, boolean aBackMacerating) {
		GT_RecipeRegistrator.registerUsagesForMaterials(aMat, aOutput, null, aBackSmelting, aBackMacerating);
	}
	
	public static Field getPublicField(Object aObject, String aField) {
		Field rField = null;
		try {
			rField = aObject.getClass().getDeclaredField(aField);
		} catch (Throwable e) {}
		return rField;
	}
	
	public static Field getField(Object aObject, String aField) {
		Field rField = null;
		try {
			rField = aObject.getClass().getDeclaredField(aField);
			rField.setAccessible(true);
		} catch (Throwable e) {}
		return rField;
	}
	
	public static Field getField(Class<?> aObject, String aField) {
		Field rField = null;
		try {
			rField = aObject.getDeclaredField(aField);
			rField.setAccessible(true);
		} catch (Throwable e) {}
		return rField;
	}
	
	public static Method getMethod(Class<?> aObject, String aMethod, Class<?>... aParameterTypes) {
		Method rMethod = null;
		try {
			rMethod = aObject.getMethod(aMethod, aParameterTypes);
			rMethod.setAccessible(true);
		} catch (Throwable e) {}
		return rMethod;
	}

	public static Method getMethod(Object aObject, String aMethod, Class<?>... aParameterTypes) {
		Method rMethod = null;
		try {
			rMethod = aObject.getClass().getMethod(aMethod, aParameterTypes);
			rMethod.setAccessible(true);
		} catch (Throwable e) {}
		return rMethod;
	}

	public static Field getField(Object aObject, String aField, boolean aPrivate, boolean aLogErrors) {
		try {
			Field tField = (aObject instanceof Class) ? ((Class<?>)aObject).getDeclaredField(aField) : (aObject instanceof String) ? Class.forName((String)aObject).getDeclaredField(aField) : aObject.getClass().getDeclaredField(aField);
			if (aPrivate) tField.setAccessible(true);
			return tField;
		} catch (Throwable e) {
			if (aLogErrors) GT_Log.log.catching(e);
		}
		return null;
	}
	
	public static Object getFieldContent(Object aObject, String aField, boolean aPrivate, boolean aLogErrors) {
		try {
			Field tField = (aObject instanceof Class) ? ((Class<?>)aObject).getDeclaredField(aField) : (aObject instanceof String) ? Class.forName((String)aObject).getDeclaredField(aField) : aObject.getClass().getDeclaredField(aField);
			if (aPrivate) tField.setAccessible(true);
			return tField.get(aObject instanceof Class || aObject instanceof String ? null : aObject);
		} catch (Throwable e) {
			if (aLogErrors) GT_Log.log.catching(e);
		}
		return null;
	}
	
	public static Object callPublicMethod(Object aObject, String aMethod, Object... aParameters) {
		return callMethod(aObject, aMethod, false, false, true, aParameters);
	}

	public static Object callPrivateMethod(Object aObject, String aMethod, Object... aParameters) {
		return callMethod(aObject, aMethod, true, false, true, aParameters);
	}
	
	public static Object callMethod(Object aObject, String aMethod, boolean aPrivate, boolean aUseUpperCasedDataTypes, boolean aLogErrors, Object... aParameters) {
		try {
			Class<?>[] tParameterTypes = new Class<?>[aParameters.length];
			for (byte i = 0; i < aParameters.length; i++) {
				if (aParameters[i] instanceof Class) {
					tParameterTypes[i] = (Class<?>) aParameters[i];
					aParameters[i] = null;
				} else {
					tParameterTypes[i] = aParameters[i].getClass();
				}
				if (!aUseUpperCasedDataTypes) {
					if (tParameterTypes[i] == Boolean.class	) tParameterTypes[i] = boolean.class;	else
					if (tParameterTypes[i] == Byte.class	) tParameterTypes[i] = byte.class;		else
					if (tParameterTypes[i] == Short.class	) tParameterTypes[i] = short.class;		else
					if (tParameterTypes[i] == Integer.class	) tParameterTypes[i] = int.class;		else
					if (tParameterTypes[i] == Long.class	) tParameterTypes[i] = long.class;		else
					if (tParameterTypes[i] == Float.class	) tParameterTypes[i] = float.class;		else
					if (tParameterTypes[i] == Double.class	) tParameterTypes[i] = double.class;
				}
			}
			
			Method tMethod = (aObject instanceof Class) ? ((Class<?>) aObject).getMethod(aMethod, tParameterTypes) : aObject.getClass().getMethod(aMethod, tParameterTypes);
			if (aPrivate) tMethod.setAccessible(true);
			return tMethod.invoke(aObject, aParameters);
		} catch (Throwable e) {
			if (aLogErrors) GT_Log.log.catching(e);
		}
		return null;
	}
	
	public static Object callConstructor(String aClass, int aConstructorIndex, Object aReplacementObject, boolean aLogErrors, Object... aParameters) {
		try {
			return Class.forName(aClass).getConstructors()[aConstructorIndex].newInstance(aParameters);
		} catch (Throwable e) {
			if (aLogErrors) GT_Log.log.catching(e);
		}
		return aReplacementObject;
	}
	
	public static String capitalizeString(String aString) {
		if (aString != null && aString.length() > 0) return aString.substring(0, 1).toUpperCase() + aString.substring(1);
		return "";
	}
	
    public static boolean getPotion(EntityLivingBase aPlayer, int aPotionIndex) {
        try  {
        	Field tPotionHashmap = null;
        	
            Field[] var3 = EntityLiving.class.getDeclaredFields();
            int var4 = var3.length;
            
            for (int var5 = 0; var5 < var4; ++var5) {
                Field var6 = var3[var5];
                if (var6.getType() == HashMap.class) {
                    tPotionHashmap = var6;
                    tPotionHashmap.setAccessible(true);
                    break;
                }
            }
            
            if (tPotionHashmap != null) return ((HashMap<?, ?>)tPotionHashmap.get(aPlayer)).get(Integer.valueOf(aPotionIndex)) != null;
        } catch (Throwable e) {
        	if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
        }
    	return false;
    }
	
    public static String getClassName(Object aObject) {
    	if (aObject == null) return "null";
    	return aObject.getClass().getName().substring(aObject.getClass().getName().lastIndexOf(".")+1);
    }
    
    public static void removePotion(EntityLivingBase aPlayer, int aPotionIndex) {
        try  {
        	Field tPotionHashmap = null;
        	
            Field[] var3 = EntityLiving.class.getDeclaredFields();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Field var6 = var3[var5];
                if (var6.getType() == HashMap.class) {
                    tPotionHashmap = var6;
                    tPotionHashmap.setAccessible(true);
                    break;
                }
            }

            if (tPotionHashmap != null) ((HashMap<?, ?>)tPotionHashmap.get(aPlayer)).remove(Integer.valueOf(aPotionIndex));
        } catch (Throwable e) {
        	if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
        }
    }
	
	public static boolean getFullInvisibility(EntityPlayer aPlayer) {
		try {
			if (aPlayer.isInvisible()) {
				for (int i = 0; i < 4; i++) {
					if (aPlayer.inventory.armorInventory[i] != null) {
						if (aPlayer.inventory.armorInventory[i].getItem() instanceof GT_EnergyArmor_Item) {
							if ((((GT_EnergyArmor_Item)aPlayer.inventory.armorInventory[i].getItem()).mSpecials & 512) != 0) {
								if (GT_ModHandler.canUseElectricItem(aPlayer.inventory.armorInventory[i], 10000)) {
									return true;
								}
							}
						}
					}
				}
			}
		} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
		return false;
	}
	
	public static ItemStack suckOneItemStackAt(World aWorld, int aX, int aY, int aZ, int aL, int aH, int aW) {
		@SuppressWarnings("unchecked")
		ArrayList<EntityItem> tList = (ArrayList<EntityItem>)aWorld.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+aL, aY+aH, aZ+aW));
		if (tList.size()>0) {
			aWorld.removeEntity(tList.get(0));
			return tList.get(0).getEntityItem();
		}
		return null;
	}

	public static byte getOppositeSide(int aSide) {
		return (byte)ForgeDirection.getOrientation(aSide).getOpposite().ordinal();
	}
	
	public static byte getTier(int aValue) {
		return (byte)(aValue<=32?1:aValue<=128?2:aValue<=512?3:aValue<=2048?4:aValue<=8192?5:6);
	}
	
	public static void sendChatToPlayer(EntityPlayer aPlayer, String aChatMessage) {
		if (aPlayer != null && aPlayer instanceof EntityPlayerMP && aChatMessage != null) {
			aPlayer.addChatMessage(new ChatComponentText(aChatMessage));
		}
	}
	
	public static void sendTrChatToPlayer(EntityPlayer aPlayer, String pattern, Object...format) {
		if (aPlayer != null && aPlayer instanceof EntityPlayerMP && pattern != null) {
			aPlayer.addChatMessage(new ChatComponentTranslation(pattern, format));
		}
	}
	
	/**
	 * Moves Stack from Inv-Slot to Inv-Slot, without checking if its even allowed.
	 * @return the Amount of moved Items
	 */
	public static byte moveStackFromSlotAToSlotB(IInventory aTileEntity1, IInventory aTileEntity2, int aGrabFrom, int aPutTo, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
		if (aTileEntity1 == null || aTileEntity2 == null || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMaxMoveAtOnce <= 0 || aMinMoveAtOnce > aMaxMoveAtOnce) return 0;
		
		ItemStack tStack1 = aTileEntity1.getStackInSlot(aGrabFrom), tStack2 = aTileEntity2.getStackInSlot(aPutTo), tStack3 = null;
		if (tStack1 != null) {
			if (tStack2 != null && !areStacksEqual(tStack1, tStack2)) return 0;
			tStack3 = copy(tStack1);
			aMaxTargetStackSize = (byte)Math.min(aMaxTargetStackSize, Math.min(tStack3.getMaxStackSize(), Math.min(tStack2==null?Integer.MAX_VALUE:tStack2.getMaxStackSize(), aTileEntity2.getInventoryStackLimit())));
			tStack3.stackSize = Math.min(tStack3.stackSize, aMaxTargetStackSize - (tStack2 == null?0:tStack2.stackSize));
			if (tStack3.stackSize > aMaxMoveAtOnce) tStack3.stackSize = aMaxMoveAtOnce;
			if (tStack3.stackSize + (tStack2==null?0:tStack2.stackSize) >= aMinTargetStackSize && tStack3.stackSize >= aMinMoveAtOnce) {
				tStack3 = aTileEntity1.decrStackSize(aGrabFrom, tStack3.stackSize);
				if (tStack3 != null) {
					if (tStack2 == null) {
						aTileEntity2.setInventorySlotContents(aPutTo, copy(tStack3));
					} else {
						tStack2.stackSize += tStack3.stackSize;
					}
					return (byte)tStack3.stackSize;
				}
			}
		}
		return 0;
	}
	
	public static boolean isAllowedToTakeFromSlot(IInventory aTileEntity, int aSlot, byte aSide, ItemStack aStack) {
		if (ForgeDirection.getOrientation(aSide) == ForgeDirection.UNKNOWN) {
			return isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)0, aStack)
				|| isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)1, aStack)
				|| isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)2, aStack)
				|| isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)3, aStack)
				|| isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)4, aStack)
				|| isAllowedToTakeFromSlot(aTileEntity, aSlot, (byte)5, aStack);
		}
		if (aTileEntity instanceof ISidedInventory) return ((ISidedInventory)aTileEntity).canExtractItem(aSlot, aStack, aSide);
		return true;
	}
	
	public static boolean isAllowedToPutIntoSlot(IInventory aTileEntity, int aSlot, byte aSide, ItemStack aStack) {
		if (ForgeDirection.getOrientation(aSide) == ForgeDirection.UNKNOWN) {
			return isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)0, aStack)
				|| isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)1, aStack)
				|| isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)2, aStack)
				|| isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)3, aStack)
				|| isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)4, aStack)
				|| isAllowedToPutIntoSlot(aTileEntity, aSlot, (byte)5, aStack);
		}
		if (aTileEntity instanceof ISidedInventory && !((ISidedInventory)aTileEntity).canInsertItem(aSlot, aStack, aSide)) return false;
		return aTileEntity.isItemValidForSlot(aSlot, aStack);
	}
	
	/**
	 * Moves Stack from Inv-Side to Inv-Side.
	 * @return the Amount of moved Items
	 */
	public static byte moveOneItemStack(IInventory aTileEntity1, IInventory aTileEntity2, byte aGrabFrom, byte aPutTo, ArrayList<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
		return moveOneItemStack(aTileEntity1, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, true);
	}
	
	/**
	 * This is only because I needed an additional Parameter for the Double Chest Check.
	 */
	private static byte moveOneItemStack(IInventory aTileEntity1, IInventory aTileEntity2, byte aGrabFrom, byte aPutTo, ArrayList<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce, boolean aDoCheckChests) {
		if (aTileEntity1 == null || aTileEntity2 == null || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMaxMoveAtOnce <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMinMoveAtOnce > aMaxMoveAtOnce) return 0;
		
		int[] tGrabSlots = null, tPutSlots = null;
		
		if (aTileEntity1 instanceof ISidedInventory) tGrabSlots = ((ISidedInventory)aTileEntity1).getAccessibleSlotsFromSide(aGrabFrom);
		if (aTileEntity2 instanceof ISidedInventory) tPutSlots = ((ISidedInventory)aTileEntity2).getAccessibleSlotsFromSide(aPutTo);
		
		if (tGrabSlots == null) {
			tGrabSlots = new int[aTileEntity1.getSizeInventory()];
			for (int i = 0; i < tGrabSlots.length; i++) tGrabSlots[i] = i;
		}
		if (tPutSlots == null) {
			tPutSlots = new int[aTileEntity2.getSizeInventory()];
			for (int i = 0; i < tPutSlots.length; i++) tPutSlots[i] = i;
		}
		
		for (int i = 0; i < tGrabSlots.length; i++) {
			for (int j = 0; j < tPutSlots.length; j++) {
				if (listContainsItem(aFilter, aTileEntity1.getStackInSlot(tGrabSlots[i]), true, aInvertFilter)) {
					if (isAllowedToTakeFromSlot(aTileEntity1, tGrabSlots[i], aGrabFrom, aTileEntity1.getStackInSlot(tGrabSlots[i]))) {
						if (isAllowedToPutIntoSlot(aTileEntity2, tPutSlots[j], aPutTo, aTileEntity1.getStackInSlot(tGrabSlots[i]))) {
							byte tMovedItemCount = moveStackFromSlotAToSlotB(aTileEntity1, aTileEntity2, tGrabSlots[i], tPutSlots[j], aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
							if (tMovedItemCount > 0) return tMovedItemCount;
						}
					}
				}
			}
		}
		
		if (aDoCheckChests && aTileEntity1 instanceof TileEntityChest) {
			TileEntityChest tTileEntity1 = (TileEntityChest)aTileEntity1;
			if (tTileEntity1.adjacentChestChecked) {
				byte tAmount = 0;
				if (tTileEntity1.adjacentChestXNeg != null) {
					tAmount = moveOneItemStack(tTileEntity1.adjacentChestXNeg, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
				} else if (tTileEntity1.adjacentChestZNeg != null) {
					tAmount = moveOneItemStack(tTileEntity1.adjacentChestZNeg, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
				} else if (tTileEntity1.adjacentChestXPos != null) {
					tAmount = moveOneItemStack(tTileEntity1.adjacentChestXPos, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
				}
//				} else if (tTileEntity1.adjacentChestZPosition != null) {
//					tAmount = moveOneItemStack(tTileEntity1.adjacentChestZPosition, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
//				} // TODO
				if (tAmount != 0) return tAmount;
			}
		}
		if (aDoCheckChests && aTileEntity2 instanceof TileEntityChest) {
			TileEntityChest tTileEntity2 = (TileEntityChest)aTileEntity2;
			if (tTileEntity2.adjacentChestChecked) {
				byte tAmount = 0;
				if (tTileEntity2.adjacentChestXNeg != null) {
					tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestXNeg, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
				} else if (tTileEntity2.adjacentChestZNeg != null) {
					tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestZNeg, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
				} else if (tTileEntity2.adjacentChestXPos != null) {
					tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestXPos, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
				}
//				} else if (tTileEntity2.adjacentChestZPosition != null) {
//					tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestZPosition, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
//				} // TODO
				if (tAmount != 0) return tAmount;
			}
		}
		return 0;
	}
	
	/**
	 * Moves Stack from Inv-Side to Inv-Slot.
	 * @return the Amount of moved Items
	 */
	public static byte moveOneItemStackIntoSlot(IInventory aTileEntity1, IInventory aTileEntity2, byte aGrabFrom, int aPutTo, ArrayList<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
		if (aTileEntity1 == null || aTileEntity2 == null || aPutTo < 0 || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMaxMoveAtOnce <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMinMoveAtOnce > aMaxMoveAtOnce) return 0;
		
		int[] tGrabSlots = null;
		
		if (aTileEntity1 instanceof ISidedInventory) tGrabSlots = ((ISidedInventory)aTileEntity1).getAccessibleSlotsFromSide(aGrabFrom);
		
		if (tGrabSlots == null) {
			tGrabSlots = new int[aTileEntity1.getSizeInventory()];
			for (int i = 0; i < tGrabSlots.length; i++) tGrabSlots[i] = i;
		}
		
		for (int i = 0; i < tGrabSlots.length; i++) {
			if (listContainsItem(aFilter, aTileEntity1.getStackInSlot(tGrabSlots[i]), true, aInvertFilter)) {
				if (isAllowedToTakeFromSlot(aTileEntity1, tGrabSlots[i], aGrabFrom, aTileEntity1.getStackInSlot(tGrabSlots[i]))) {
					if (isAllowedToPutIntoSlot(aTileEntity2, aPutTo, (byte)6, aTileEntity1.getStackInSlot(tGrabSlots[i]))) {
						byte tMovedItemCount = moveStackFromSlotAToSlotB(aTileEntity1, aTileEntity2, tGrabSlots[i], aPutTo, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
						if (tMovedItemCount > 0) return tMovedItemCount;
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * Moves Stack from Inv-Slot to Inv-Slot.
	 * @return the Amount of moved Items
	 */
	public static byte moveFromSlotToSlot(IInventory aTileEntity1, IInventory aTileEntity2, int aGrabFrom, int aPutTo, ArrayList<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
		if (aTileEntity1 == null || aTileEntity2 == null || aGrabFrom < 0 || aPutTo < 0 || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMaxMoveAtOnce <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMinMoveAtOnce > aMaxMoveAtOnce) return 0;
		if (listContainsItem(aFilter, aTileEntity1.getStackInSlot(aGrabFrom), true, aInvertFilter)) {
			if (isAllowedToTakeFromSlot(aTileEntity1, aGrabFrom, (byte)6, aTileEntity1.getStackInSlot(aGrabFrom))) {
				if (isAllowedToPutIntoSlot(aTileEntity2, aPutTo, (byte)6, aTileEntity1.getStackInSlot(aGrabFrom))) {
					byte tMovedItemCount = moveStackFromSlotAToSlotB(aTileEntity1, aTileEntity2, aGrabFrom, aPutTo, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
					if (tMovedItemCount > 0) return tMovedItemCount;
				}
			}
		}
		return 0;
	}
	
	public static boolean listContainsItem(Collection<ItemStack> aList, ItemStack aStack, boolean aTrueIfListEmpty, boolean aInvertFilter) {
		if (aStack == null || aStack.stackSize < 1) return false;
		if (aList == null) return aTrueIfListEmpty;
		while (aList.contains(null)) aList.remove(null);
		if (aList.size() < 1) return aTrueIfListEmpty;
		Iterator<ItemStack> tIterator = aList.iterator();
		ItemStack tStack = null;
		while (tIterator.hasNext()) if ((tStack = tIterator.next())!= null && areStacksEqual(aStack, tStack)) return !aInvertFilter;
		return aInvertFilter;
	}
	
	public static boolean areStacksOrToolsEqual(ItemStack aStack1, ItemStack aStack2) {
		if (aStack1 != null && aStack2 != null && aStack1.getItem() == aStack2.getItem()) {
			if (aStack1.getItem().isDamageable()) return true;
			return ((aStack1.getTagCompound() == null) == (aStack2.getTagCompound() == null)) && (aStack1.getTagCompound() == null || aStack1.getTagCompound().equals(aStack2.getTagCompound())) && (aStack1.getItemDamage() == aStack2.getItemDamage() || aStack1.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE || aStack2.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE);
		} else {
			return false;
		}
	}
	
	public static boolean areStacksEqual(ItemStack aStack1, ItemStack aStack2) {
		return aStack1 != null && aStack2 != null && aStack1.getItem() == aStack2.getItem() && ((aStack1.getTagCompound() == null) == (aStack2.getTagCompound() == null)) && (aStack1.getTagCompound() == null || aStack1.getTagCompound().equals(aStack2.getTagCompound())) && (aStack1.getItemDamage() == aStack2.getItemDamage() || aStack1.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE || aStack2.getItemDamage() == GregTech_API.ITEM_WILDCARD_DAMAGE);
	}
	
	public static boolean areStacksEqual(GT_MultiStack aStack1, ItemStack aStack2) {
		return aStack1 != null && aStack2 != null && aStack1.isItemEqual(aStack2);
	}
	
	public static boolean areStacksEqual(ItemStack aStack1, GT_MultiStack aStack2) {
		return aStack1 != null && aStack2 != null && aStack2.isItemEqual(aStack1);
	}
	
    public static ItemStack fillFluidContainer(FluidStack aFluid, ItemStack aStack) {
		if (aStack == null || aFluid == null) return null;
    	if (aStack.getItem() != null && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getFluid(aStack) == null && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) <= aFluid.amount) {
			((IFluidContainerItem)aStack.getItem()).fill(aStack = copy(1, aStack), aFluid, true);
			return aStack;
    	}
        return FluidContainerRegistry.fillFluidContainer(aFluid, aStack);
    }
    
    public static boolean containsFluid(ItemStack aStack, FluidStack aFluid) {
		if (aStack == null || aFluid == null) return false;
    	if (aStack.getItem() != null && aStack.getItem() instanceof IFluidContainerItem) {
			return FluidStack.areFluidStackTagsEqual(aFluid, ((IFluidContainerItem)aStack.getItem()).getFluid(aStack = copy(1, aStack)));
    	}
    	return FluidContainerRegistry.containsFluid(aStack, aFluid);
    }
    
	public static FluidStack getFluidForFilledItem(ItemStack aStack) {
		if (aStack == null) return null;
		FluidStack rFluid = FluidContainerRegistry.getFluidForFilledItem(aStack);
		if (rFluid == null && aStack.getItem() != null && aStack.getItem() instanceof IFluidContainerItem) {
			rFluid = ((IFluidContainerItem)aStack.getItem()).drain(copy(1, aStack), Integer.MAX_VALUE, true);
		}
		return rFluid;
	}
	
	public static ItemStack getContainerForFilledItem(ItemStack aStack) {
		if (aStack == null) return null;
		for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData())
			if (areStacksEqual(tData.filledContainer, aStack))
				return tData.emptyContainer;
		if (aStack.getItem() != null && aStack.getItem() instanceof IFluidContainerItem) {
			((IFluidContainerItem)aStack.getItem()).drain(aStack = copy(1, aStack), Integer.MAX_VALUE, true);
			return aStack;
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getContainerItem(ItemStack aStack) {
		if (aStack == null || aStack.getItem() == null) return null;
		
		if (aStack.getItem().hasContainerItem()) return aStack.getItem().getContainerItem(aStack);
		
		if (areStacksEqual(aStack, GT_ModHandler.getEmptyCell(1))) return null;
		
		int tCapsuleCount = GT_ModHandler.getCapsuleCellContainerCount(aStack);
		if (tCapsuleCount > 0) return GT_ModHandler.getEmptyCell(tCapsuleCount);
		
		if (areStacksOrToolsEqual(GT_ModHandler.getIC2Item("cutter", 1), aStack) || areStacksOrToolsEqual(GT_ModHandler.getIC2Item("ForgeHammer", 1), aStack)) {
			aStack = copy(aStack);
			aStack.setItemDamage(aStack.getItemDamage() + 1);
			return aStack;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean removeSimpleIC2MachineRecipe(ItemStack aInput, Map<?, ?> aRecipeList, ItemStack aOutput) {
		if ((aInput == null && aOutput == null) || aRecipeList == null) return false;
		try {
			for (Map.Entry<Object, Object> tEntry : ((Map<Object, Object>)aRecipeList).entrySet()) {
				Object temp = callMethod(tEntry.getKey(), "matches", false, false, false, aInput);
				if (aInput == null || (tEntry.getKey() instanceof ItemStack && areStacksEqual((ItemStack)tEntry.getKey(), aInput)) || (temp instanceof Boolean && (Boolean)temp)) {
					if (tEntry.getValue() == null) {
						aRecipeList.remove(tEntry.getKey());
						return removeSimpleIC2MachineRecipe(aInput, aRecipeList, aOutput);
					} else {
						if (tEntry.getValue() instanceof ItemStack) {
							if (aOutput == null || areStacksEqual((ItemStack)tEntry.getValue(), aOutput)) {
								aRecipeList.remove(tEntry.getKey());
								removeSimpleIC2MachineRecipe(aInput, aRecipeList, aOutput);
								return true;
							}
						} else {
							List<ItemStack> tList = (List<ItemStack>)GT_Utility.getFieldContent(tEntry.getValue(), "items", false, false);
							if (tList != null) {
								for (ItemStack tOutput : tList) {
									if (aOutput == null || areStacksEqual(tOutput, aOutput)) {
										aRecipeList.remove(tEntry.getKey());
										removeSimpleIC2MachineRecipe(aInput, aRecipeList, aOutput);
										return true;
									}
								}
							}
						}
					}
				}
			}
		} catch(Throwable e) {}
		return false;
	}
	
	public static boolean addSimpleIC2MachineRecipe(ItemStack aInput, Map<Object, Object> aRecipeList, ItemStack aOutput) {
		if (aInput == null || aOutput == null || aRecipeList == null) return false;
		aRecipeList.put(callConstructor("ic2.api.recipe.RecipeInputItemStack", 0, copy(aInput), false, copy(aInput)), callConstructor("ic2.api.recipe.RecipeOutput", 0, callConstructor("ic2.api.recipe.RecipeOutput", 1, copy(aOutput), false, null, new ItemStack[] {copy(aOutput)}), false, null, new ItemStack[] {copy(aOutput)}));
		return true;
	}
	
	private static int sBookCount = 0;
	
	public static ItemStack getWrittenBook(String aTitle, String aAuthor, String[] aPages) {
		if (aTitle == null || aAuthor == null || aPages == null || aPages.length <= 0 || aTitle.equals("") || aAuthor.equals("")) return null;
		sBookCount++;
		ItemStack rStack = new ItemStack(Items.written_book, 1);
        NBTTagCompound tNBT = new NBTTagCompound();
        tNBT.setString("title", GT_LanguageManager.addStringLocalization("Book." + aTitle + ".Name", aTitle));
        tNBT.setString("author", aAuthor);
        NBTTagList tNBTList = new NBTTagList();
        for (byte i = 0; i < aPages.length; i++) {
        	aPages[i] = GT_LanguageManager.addStringLocalization("Book." + aTitle + ".Page" + ((i<10)?"0"+i:i), aPages[i]);
	        if (i < 48) {
	        	if (aPages[i].length() < 256)
	        		tNBTList.appendTag(new NBTTagString(aPages[i]));
	        	else
	        		GT_Log.log.warn("WARNING: String for written Book too long! -> " + aPages[i]);
	        } else {
        		GT_Log.log.warn("WARNING: Too much Pages for written Book! -> " + aTitle);
	        	break;
	        }
        }
		tNBTList.appendTag(new NBTTagString("Credits to " + aAuthor + " for writing this Book. This was Book Nr. " + sBookCount + " at its creation. Gotta get 'em all!"));
        tNBT.setTag("pages", tNBTList);
        rStack.setTagCompound(tNBT);
        GregTech_API.sBookList.put(aTitle, rStack);
		return copy(rStack);
	}
	
	/**
	 * this List is getting cleared every tick and just prevents Sound bombing.
	 */
	public static Collection<String> sPlayedSoundMap = new ArrayList<String>();
	
	public static boolean doSoundAtClient(String aSoundName, float aSoundStrength) {
		if (sPlayedSoundMap.contains(aSoundName) || aSoundName == null || aSoundName.equals("") || sCurrentPlayer == null || !sCurrentPlayer.worldObj.isRemote) return false;
		sCurrentPlayer.worldObj.playSound(sCurrentPlayer.posX, sCurrentPlayer.posY, sCurrentPlayer.posZ, aSoundName, aSoundStrength, (1.0F + (sCurrentPlayer.worldObj.rand.nextFloat() - sCurrentPlayer.worldObj.rand.nextFloat()) * 0.2F) * 0.7F, false);
		sPlayedSoundMap.add(aSoundName);
		return true;
	}
	
	public static boolean doSoundAtClient(String aSoundName, float aSoundStrength, Entity aEntity) {
		if (aEntity == null) return false;
		return doSoundAtClient(aSoundName, aSoundStrength, aEntity.posX, aEntity.posY, aEntity.posZ);
	}
	
	public static boolean doSoundAtClient(String aSoundName, float aSoundStrength, double aX, double aY, double aZ) {
		return doSoundAtClient(aSoundName, aSoundStrength, (1.0F + (sCurrentPlayer.worldObj.rand.nextFloat() - sCurrentPlayer.worldObj.rand.nextFloat()) * 0.2F) * 0.7F, aX, aY, aZ);
	}

	public static boolean doSoundAtClient(String aSoundName, float aSoundStrength, float aSoundModulation, double aX, double aY, double aZ) {
		if (sPlayedSoundMap.contains(aSoundName) || aSoundName == null || aSoundName.equals("") || sCurrentPlayer == null || !sCurrentPlayer.worldObj.isRemote || !GregTech_API.gregtechmod.isClientSide()) return false;
		sCurrentPlayer.worldObj.playSound(aX, aY, aZ, aSoundName, aSoundStrength, aSoundModulation, false);
		sPlayedSoundMap.add(aSoundName);
		return true;
	}
	
	public static boolean sendSoundToPlayers(World aWorld, String aSoundName, float aSoundStrength, float aSoundModulation, int aX, int aY, int aZ) {
		if (aSoundName == null || aSoundName.equals("") || aWorld == null || aWorld.isRemote) return false;
//        ByteArrayDataOutput tOut = ByteStreams.newDataOutput();
//
//        tOut.writeInt(aX);
//        tOut.writeShort(aY);
//        tOut.writeInt(aZ);
//		
//        tOut.writeUTF(aSoundName);
//        tOut.writeFloat(aSoundStrength);
//        tOut.writeFloat(aSoundModulation < 0 ? (1.0F + (aWorld.rand.nextFloat() - aWorld.rand.nextFloat()) * 0.2F) * 0.7F : aSoundModulation);
		aSoundModulation = aSoundModulation < 0 ? (1.0F + (aWorld.rand.nextFloat() - aWorld.rand.nextFloat()) * 0.2F) * 0.7F : aSoundModulation;
		GT_SoundPacket tPacket = new GT_SoundPacket(aSoundName, aX, aY, aZ, aSoundStrength, aSoundModulation);
        sendPacketToAllPlayersInRange(aWorld, tPacket, aX, aZ);
        
		return true;
	}
	
	public static void sendPacketToAllPlayersInRange(World aWorld, GT_SoundPacket aPacket, int aX, int aZ) {
        for (Object tObject : aWorld.playerEntities) {
        	if (tObject instanceof EntityPlayerMP) {
        		EntityPlayerMP tPlayer = (EntityPlayerMP)tObject;
				if (GregTech_API.gregtechmod.allowPacketToBeSent(aPacket, tPlayer)) {
					Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
					if (tPlayer.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(tPlayer, tChunk.xPosition, tChunk.zPosition)) {
						if (GregTech_API.DEBUG_MODE) GT_Log.log.debug("sent Packet to " + tPlayer.getDisplayName());
						GT_PacketHandler.SOUND_PACKET_CHANNEL.sendTo(aPacket, tPlayer);
					}
	        	}
        	} else {
        		break;
        	}
        }
	}
	
	public static int stackToInt(ItemStack aStack) {
		if (aStack == null || aStack.getItem() == null) return 0;
		return Item.getIdFromItem(aStack.getItem()) | (aStack.getItemDamage() << 16);
	}
	
	public static ItemStack intToStack(int aStack) {
		int tID = aStack&(~0>>>16), tMeta = aStack>>>16;
		if (tID > 0 && Item.itemRegistry.containsId(tID)) return new ItemStack(Item.getItemById(tID), 1, tMeta);
		return null;
	}
	
	public static long stacksToLong(ItemStack aStack1, ItemStack aStack2) {
		if (aStack1 == null) return 0;
		return ((long)stackToInt(aStack1)) | (((long)stackToInt(aStack2)) << 32);
	}

	public static boolean isDebugItem(ItemStack aStack) {
		if (aStack == null) return false;
		return areStacksEqual(aStack, new ItemStack(GregTech_API.sItemList[18], 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
	}
	
	public static boolean isItemStackInList(ItemStack aStack, Collection<Integer> aList) {
		if (aStack == null || aList == null || aStack.getItem() == null) return false;
		ItemStack tStack = copy(aStack); tStack.setItemDamage(GregTech_API.ITEM_WILDCARD_DAMAGE);
		return aList.contains(stackToInt(tStack)) || aList.contains(stackToInt(aStack));
	}
	
	public static boolean hasBlockHitBox(World aWorld, int aX, int aY, int aZ) {
		Block bl = aWorld.getBlock(aX, aY, aZ);
		if (bl != Blocks.air) {
			return bl.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ) != null;
		}
		
		return false;
	}
	
	/**
	 * Why isn't there a "copy(int aAmount)" in ItemStack itself, WHY!?!
	 */
	public static ItemStack copy(int aAmount, ItemStack... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (rStack == null) return null;
		rStack.stackSize = aAmount;
		return rStack;
	}
	
	public static ItemStack copy(ItemStack... aStacks) {
		for (ItemStack tStack : aStacks) if (tStack != null && tStack.getItem() != null) return tStack.copy();
		return null;
	}
	
	public static ItemStack copy(int aAmount, int aMetaData, ItemStack... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (rStack == null) return null;
		rStack.setItemDamage(aMetaData);
		rStack.stackSize = aAmount;
		return rStack;
	}
	
	public static ItemStack copy(int aAmount, boolean aSetWildcardDamage, ItemStack... aStacks) {
		if (aSetWildcardDamage) return copy(aAmount, GregTech_API.ITEM_WILDCARD_DAMAGE, aStacks);
		return copy(aAmount, aStacks);
	}
	
	/**
	 * returns a copy of an ItemStack with its Stacksize being multiplied by aMultiplier
	 */
	public static ItemStack mul(int aMultiplier, ItemStack... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (rStack == null) return null;
		rStack.stackSize *= aMultiplier;
		return rStack;
	}
	
	/**
	 * Loads an ItemStack properly.
	 */
	public static ItemStack loadItem(NBTTagCompound aNBT) {
		ItemStack rStack = ItemStack.loadItemStackFromNBT(aNBT);
		try {
			if (rStack != null && (rStack.getItem().getClass().getName().startsWith("ic2.core.migration"))) {
				rStack.getItem().onUpdate(rStack, GregTech_API.sDummyWorld, null, 0, false);
			}
		} catch(Throwable e) {
			GT_Log.log.catching(e);
		}
		return GT_OreDictUnificator.get(true, rStack);
	}
	
	/**
	 * Why the fuck do neither Java nor Guava have a Function to do this?
	 */
    public static <K, V extends Comparable<V>> LinkedHashMap<K,V> sortMapByValuesAcending(Map<K,V> aMap) {
        List<Map.Entry<K,V>> tEntrySet = new LinkedList<Map.Entry<K,V>>(aMap.entrySet());
        Collections.sort(tEntrySet, new Comparator<Map.Entry<K,V>>() {@Override public int compare(Entry<K, V> aValue1, Entry<K, V> aValue2) {return aValue1.getValue().compareTo(aValue2.getValue());}});
        LinkedHashMap<K,V> rMap = new LinkedHashMap<K,V>();
        for (Map.Entry<K,V> tEntry : tEntrySet) rMap.put(tEntry.getKey(), tEntry.getValue());
        return rMap;
    }
    
	/**
	 * Why the fuck do neither Java nor Guava have a Function to do this?
	 */
    public static <K, V extends Comparable<V>> LinkedHashMap<K,V> sortMapByValuesDescending(Map<K,V> aMap) {
        List<Map.Entry<K,V>> tEntrySet = new LinkedList<Map.Entry<K,V>>(aMap.entrySet());
        Collections.sort(tEntrySet, new Comparator<Map.Entry<K,V>>() {@Override public int compare(Entry<K, V> aValue1, Entry<K, V> aValue2) {return -aValue1.getValue().compareTo(aValue2.getValue());}});
        LinkedHashMap<K,V> rMap = new LinkedHashMap<K,V>();
        for (Map.Entry<K,V> tEntry : tEntrySet) rMap.put(tEntry.getKey(), tEntry.getValue());
        return rMap;
    }
    
	/**
	 * This checks if the Dimension is really a Dimension and not another Planet or something.
	 * Used for my Teleporter.
	 */
	public static boolean isRealDimension(int aDimensionID) {
		Class<?> provider = null;
		try {
			provider = Class.forName("com.xcompwiz.mystcraft.world.WorldProviderMyst");
			if (provider.isAssignableFrom(DimensionManager.getProvider(aDimensionID).getClass())) return true;
		} catch (Throwable e) {}
		try {
			provider = Class.forName("twilightforest.world.WorldProviderTwilightForest");
			if (provider.isAssignableFrom(DimensionManager.getProvider(aDimensionID).getClass())) return true;
		} catch (Throwable e) {}
		return GregTech_API.sDimensionalList.contains(aDimensionID);
	}
	
	public static boolean moveEntityToDimensionAtCoords(Entity aEntity, int aDimension, double aX, double aY, double aZ) {
		WorldServer tTargetWorld = DimensionManager.getWorld(aDimension), tOriginalWorld = DimensionManager.getWorld(aEntity.worldObj.provider.dimensionId);
		if (tTargetWorld != null && tOriginalWorld != null) {
			if (aEntity.ridingEntity != null) aEntity.mountEntity(null);
			if (aEntity.riddenByEntity != null) aEntity.riddenByEntity.mountEntity(null);
			
			if (aEntity instanceof EntityPlayerMP) {
				EntityPlayerMP aPlayer = (EntityPlayerMP)aEntity;
				int fromDim = aPlayer.dimension;
		        aPlayer.dimension = aDimension;
		        aPlayer.playerNetServerHandler.sendPacket(new S07PacketRespawn(aDimension, aPlayer.worldObj.difficultySetting, tTargetWorld.getWorldInfo().getTerrainType(), aPlayer.theItemInWorldManager.getGameType()));
		        tOriginalWorld.removePlayerEntityDangerously(aPlayer);
		        aPlayer.isDead = false;
		        aPlayer.setWorld(tTargetWorld);
		        MinecraftServer.getServer().getConfigurationManager().func_72375_a(aPlayer, tOriginalWorld);
		        aPlayer.playerNetServerHandler.setPlayerLocation(aX+0.5, aY+0.5, aZ+0.5, aPlayer.rotationYaw, aPlayer.rotationPitch);
		        aPlayer.theItemInWorldManager.setWorld(tTargetWorld);
		        MinecraftServer.getServer().getConfigurationManager().updateTimeAndWeatherForPlayer(aPlayer, tTargetWorld);
		        MinecraftServer.getServer().getConfigurationManager().syncPlayerInventory(aPlayer);
		        Iterator<?> tIterator = aPlayer.getActivePotionEffects().iterator();
		        while (tIterator.hasNext()) {
		            PotionEffect potioneffect = (PotionEffect)tIterator.next();
		            aPlayer.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(aPlayer.getEntityId(), potioneffect));
		        }
		        aPlayer.playerNetServerHandler.setPlayerLocation(aX+0.5, aY+0.5, aZ+0.5, aPlayer.rotationYaw, aPlayer.rotationPitch);
		        MinecraftForge.EVENT_BUS.post(new PlayerEvent.PlayerChangedDimensionEvent(aPlayer, fromDim, aDimension)); // GameRegistry.onPlayerChangedDimension(aPlayer);  FIXME need an event handler for changind dimension
			} else {
				aEntity.setPosition(aX+0.5, aY+0.5, aZ+0.5);
				aEntity.worldObj.removeEntity(aEntity);
				aEntity.dimension = aDimension;
				aEntity.isDead = false;
	            Entity tNewEntity = EntityList.createEntityByName(EntityList.getEntityString(aEntity), tTargetWorld);
	            if (tNewEntity != null) {
	            	tNewEntity.copyDataFrom(aEntity, true);
		            aEntity.setDead();
	            	tNewEntity.isDead = false;
	            	boolean temp = tNewEntity.forceSpawn;
		            tNewEntity.forceSpawn = true;
	            	tTargetWorld.spawnEntityInWorld(tNewEntity);
		            tNewEntity.forceSpawn = temp;
	            	tNewEntity.isDead = false;
	            	aEntity = tNewEntity;
	            }
			}
			
			if (aEntity instanceof EntityLiving) {
				((EntityLiving)aEntity).setPositionAndUpdate(aX, aY, aZ);
			} else {
				aEntity.setPosition(aX, aY, aZ);
			}
			
            tOriginalWorld.resetUpdateEntityTick();
            tTargetWorld.resetUpdateEntityTick();
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static synchronized int getCoordinateScan(ArrayList<String> aList, EntityPlayer aPlayer, World aWorld, int aScanLevel, int aX, int aY, int aZ, int aSide, float aClickX, float aClickY, float aClickZ) {
		if (aList == null) return 0;
		
		ArrayList<String> tList = new ArrayList<String>();
		int rEUAmount = 0;
		
		TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
	    
	    Block tBlock = aWorld.getBlock(aX, aY, aZ);
	    
    	tList.add("-----");
	    try {
		    if (tTileEntity != null && tTileEntity instanceof IInventory)
		    	tList.add("Name: " + ((IInventory)tTileEntity).getInventoryName() + "  ID: " + Block.getIdFromBlock(tBlock) + "  MetaData: " + aWorld.getBlockMetadata(aX, aY, aZ));
		    else
		    	tList.add("Name: " + tBlock.getUnlocalizedName() + "  ID: " + Block.getIdFromBlock(tBlock) + "  MetaData: " + aWorld.getBlockMetadata(aX, aY, aZ));
		    
		    tList.add("Hardness: " + tBlock.getBlockHardness(aWorld, aX, aY, aZ) + "  Blast Resistance: " + tBlock.getExplosionResistance(aPlayer, aWorld, aX, aY, aZ, aPlayer.posX, aPlayer.posY, aPlayer.posZ));
		} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
	    if (tTileEntity != null) {
			try {if (tTileEntity instanceof IFluidHandler) {
				rEUAmount+=500;
			    FluidTankInfo[] tTanks = ((IFluidHandler)tTileEntity).getTankInfo(ForgeDirection.getOrientation(aSide));
			    if (tTanks != null) for (byte i = 0; i < tTanks.length; i++) {
			    	tList.add("Tank " + i + ": " + (tTanks[i].fluid==null?0:tTanks[i].fluid.amount) + " / " + tTanks[i].capacity + " " + (tTanks[i].fluid==null?"":GT_Utility.capitalizeString(tTanks[i].fluid.getFluid().getUnlocalizedName().replaceFirst("fluid.", ""))));
			    }
			}} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.reactor.IReactorChamber) {
				rEUAmount+=500;
			    tTileEntity = (TileEntity)(((ic2.api.reactor.IReactorChamber)tTileEntity).getReactor());
			}} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.reactor.IReactor) {
				rEUAmount+=500;
				tList.add("Heat: " + ((ic2.api.reactor.IReactor)tTileEntity).getHeat() + "/" + ((ic2.api.reactor.IReactor)tTileEntity).getMaxHeat()
						+ "  HEM: " + ((ic2.api.reactor.IReactor)tTileEntity).getHeatEffectModifier() + "  Base EU Output: "/* + ((ic2.api.reactor.IReactor)tTileEntity).getOutput()*/);
			}} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.tile.IWrenchable) {
				rEUAmount+=100;
		        tList.add("Facing: " + ((ic2.api.tile.IWrenchable)tTileEntity).getFacing() + " / Chance: " + (((ic2.api.tile.IWrenchable)tTileEntity).getWrenchDropRate()*100) + "%");
			    tList.add(((ic2.api.tile.IWrenchable)tTileEntity).wrenchCanRemove(aPlayer)?"You can remove this with a Wrench":"You can NOT remove this with a Wrench");
			}} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.energy.tile.IEnergyTile) {
				rEUAmount+=200;
			    //aList.add(((ic2.api.energy.tile.IEnergyTile)tTileEntity).isAddedToEnergyNet()?"Added to E-net":"Not added to E-net! Bug?");
			}} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.energy.tile.IEnergySink) {
				rEUAmount+=400;
		        //aList.add("Demanded Energy: " + ((ic2.api.energy.tile.IEnergySink)tTileEntity).demandsEnergy());
		        tList.add("Sink Tier: " + ((ic2.api.energy.tile.IEnergySink)tTileEntity).getSinkTier());
		    }} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.energy.tile.IEnergySource) {
				rEUAmount+=400;
		        //aList.add("Max Energy Output: " + ((ic2.api.energy.tile.IEnergySource)tTileEntity).getMaxEnergyOutput());
		    }} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.energy.tile.IEnergyConductor) {
				rEUAmount+=200;
		        tList.add("Conduction Loss: " + ((ic2.api.energy.tile.IEnergyConductor)tTileEntity).getConductionLoss());
		    }} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.tile.IEnergyStorage) {
				rEUAmount+=200;
		        tList.add("Contained Energy: " + ((ic2.api.tile.IEnergyStorage)tTileEntity).getStored() + " of " + ((ic2.api.tile.IEnergyStorage)tTileEntity).getCapacity());
		        //aList.add(((ic2.api.tile.IEnergyStorage)tTileEntity).isTeleporterCompatible(ic2.api.Direction.YP)?"Teleporter Compatible":"Not Teleporter Compatible");
			}} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof IUpgradableMachine) {
				rEUAmount+=500;
		    	int tValue = 0;
		    	if (0 < (tValue = ((IUpgradableMachine)tTileEntity).getOverclockerUpgradeCount())) tList.add(tValue	+ " Overclocker Upgrades");
		    	if (0 < (tValue = ((IUpgradableMachine)tTileEntity).getTransformerUpgradeCount())) tList.add(tValue	+ " Transformer Upgrades");
		    	if (0 < (tValue = ((IUpgradableMachine)tTileEntity).getUpgradeStorageVolume()   )) tList.add(tValue	+ " Upgraded EU Capacity");
		    }} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof IMachineProgress) {
				rEUAmount+=400;
		    	int tValue = 0;
		    	if (0 < (tValue = ((IMachineProgress)tTileEntity).getMaxProgress())) tList.add("Progress: " + tValue + " / " + ((IMachineProgress)tTileEntity).getProgress());
		    }} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ICoverable) {
				rEUAmount+=300;
		    	String tString = ((ICoverable)tTileEntity).getCoverBehaviorAtSide((byte)aSide).getDescription((byte)aSide, ((ICoverable)tTileEntity).getCoverIDAtSide((byte)aSide), ((ICoverable)tTileEntity).getCoverDataAtSide((byte)aSide), (ICoverable)tTileEntity);
		    	if (tString != null && !tString.equals("")) tList.add(tString);
		    }} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof IGregTechTileEntity) {
		    	tList.add("Owned by: " + ((IGregTechTileEntity)tTileEntity).getOwnerName());
		    }} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
			try {if (tTileEntity instanceof ic2.api.crops.ICropTile) {
				if (((ic2.api.crops.ICropTile)tTileEntity).getScanLevel() < 4) {
					rEUAmount+=10000;
					((ic2.api.crops.ICropTile)tTileEntity).setScanLevel((byte)4);
				}
				if (((ic2.api.crops.ICropTile)tTileEntity).getID() >= 0 && ((ic2.api.crops.ICropTile)tTileEntity).getID() < ic2.api.crops.Crops.instance.getCropList().length && ic2.api.crops.Crops.instance.getCropList()[((ic2.api.crops.ICropTile)tTileEntity).getID()] != null) {
					rEUAmount+=1000;
					tList.add("Type -- Crop-Name: " + ic2.api.crops.Crops.instance.getCropList()[((ic2.api.crops.ICropTile)tTileEntity).getID()].name()
			        		+ "  Growth: " + ((ic2.api.crops.ICropTile)tTileEntity).getGrowth()
			        		+ "  Gain: " + ((ic2.api.crops.ICropTile)tTileEntity).getGain()
			        		+ "  Resistance: " + ((ic2.api.crops.ICropTile)tTileEntity).getResistance()
			        		);
			        tList.add("Plant -- Fertilizer: " + ((ic2.api.crops.ICropTile)tTileEntity).getNutrientStorage()
			        		+ "  Water: " + ((ic2.api.crops.ICropTile)tTileEntity).getHydrationStorage()
			        		+ "  Weed-Ex: " + ((ic2.api.crops.ICropTile)tTileEntity).getWeedExStorage()
			        		+ "  Scan-Level: " + ((ic2.api.crops.ICropTile)tTileEntity).getScanLevel()
			        		);
			        tList.add("Environment -- Nutrients: " + ((ic2.api.crops.ICropTile)tTileEntity).getNutrients()
			        		+ "  Humidity: " + ((ic2.api.crops.ICropTile)tTileEntity).getHumidity()
			        		+ "  Air-Quality: " + ((ic2.api.crops.ICropTile)tTileEntity).getAirQuality()
			        		);
			        String tString = "";
			        for (String tAttribute : ic2.api.crops.Crops.instance.getCropList()[((ic2.api.crops.ICropTile)tTileEntity).getID()].attributes()) {
			        	tString += ", " + tAttribute;
			        }
			        tList.add("Attributes:" + tString.replaceFirst(",", ""));
			        tList.add("Discovered by: " + ic2.api.crops.Crops.instance.getCropList()[((ic2.api.crops.ICropTile)tTileEntity).getID()].discoveredBy());
				}
			}} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
    	}
	    try {if (tBlock instanceof IDebugableBlock) {
	    	rEUAmount+=500;
	        ArrayList<String> temp = ((IDebugableBlock)tBlock).getDebugInfo(aPlayer, aX, aY, aZ, 3);
	        if (temp != null) tList.addAll(temp);
	    }} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
	    
	    GT_ScannerEvent tEvent = new GT_ScannerEvent(aWorld, aPlayer, aX, aY, aZ, (byte)aSide, aScanLevel, tBlock, tTileEntity, tList, aClickX, aClickY, aClickZ);
	    tEvent.mEUCost = rEUAmount;
	    MinecraftForge.EVENT_BUS.post(tEvent);
	    if (!tEvent.isCanceled()) {
	    	aList.addAll(tList);
	    }
		return tEvent.mEUCost;
	}
	
	/**
	 * This Function determines the direction a Block gets when being Wrenched.
	 * returns -1 if invalid. Even though that could never happen.
	 */
	public static byte determineWrenchingSide(byte aSide, float aX, float aY, float aZ) {
		byte tBack = GT_Utility.getOppositeSide(aSide);
		switch (aSide) {
		case  0: case  1:
			if (aX < 0.25) {
				if (aZ < 0.25) return tBack;
				if (aZ > 0.75) return tBack;
				return 4;
			}
			if (aX > 0.75) {
				if (aZ < 0.25) return tBack;
				if (aZ > 0.75) return tBack;
				return 5;
			}
			if (aZ < 0.25) return 2;
			if (aZ > 0.75) return 3;
			return aSide;
		case  2: case  3:
			if (aX < 0.25) {
				if (aY < 0.25) return tBack;
				if (aY > 0.75) return tBack;
				return 4;
			}
			if (aX > 0.75) {
				if (aY < 0.25) return tBack;
				if (aY > 0.75) return tBack;
				return 5;
			}
			if (aY < 0.25) return 0;
			if (aY > 0.75) return 1;
			return aSide;
		case  4: case  5:
			if (aZ < 0.25) {
				if (aY < 0.25) return tBack;
				if (aY > 0.75) return tBack;
				return 2;
			}
			if (aZ > 0.75) {
				if (aY < 0.25) return tBack;
				if (aY > 0.75) return tBack;
				return 3;
			}
			if (aY < 0.25) return 0;
			if (aY > 0.75) return 1;
			return aSide;
		}
		return -1;
	}
}