package gregtechmod.api.util;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.enums.GT_Items;
import gregtechmod.api.enums.GT_ToolDictNames;
import gregtechmod.api.events.GT_ScannerEvent;
import gregtechmod.api.interfaces.*;
import gregtechmod.api.items.GT_EnergyArmor_Item;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaPipeEntity_Item;
import gregtechmod.common.network.GT_NetworkHandler;
import gregtechmod.common.network.packet.GT_Packet;
import gregtechmod.common.network.packet.GT_SoundPacket;

import ic2.api.crops.CropCard;
import ic2.api.crops.ICropTile;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.RecipeOutput;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * Just a few Utility Functions I use.
 */
public class GT_Utility {
	public static volatile int VERSION = 416;
	
	public static final List<Character> sNumberedCharacters   = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	public static final List<Character> sUpperCasedCharacters = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	public static final List<Character> sLowerCasedCharacters = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	
	public static final DecimalFormat sFormat = new DecimalFormat("###,###.##", new DecimalFormatSymbols(Locale.ENGLISH));
    
	public static InventoryCrafting getInventoryFromArray(ItemStack...stacks) {
		InventoryCrafting crafting = new InventoryCrafting(new Container() {
			@Override
			public boolean canInteractWith(EntityPlayer p_75145_1_) {
				return false;
			}
		}, 3, 3);
		
		try {
			ObfuscationReflectionHelper.setPrivateValue(InventoryCrafting.class, crafting, stacks, new String[]{"field_70466_a", "stackList"});
		} catch (Throwable e) {
			GT_Log.log.catching(e);
		}
		
		return crafting;
	}
	
	public static int hashFluidStack(FluidStack stack) {
		int code = 1;
    	code = 31*code + hashFluid(stack.getFluid());
    	code = 31*code + stack.amount;
    	if (stack.tag != null)
    		code = 31*code + stack.tag.hashCode();
    	return code;
	}
	
	public static int hashFluid(Fluid fluid) {
		int code = 1;
		code = 31*code + fluid.getName().hashCode();
		if (fluid.getUnlocalizedName() != null)
			code = 31*code + fluid.getUnlocalizedName().hashCode();
		code = 31*code + fluid.getColor();
		code += fluid.getDensity() +
				fluid.getLuminosity() +
				fluid.getTemperature() +
				fluid.getViscosity() +
				(fluid.isGaseous() ? 1 : 0);
		return code;
	}
	
	public static String toIndexNumbers(String string) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int relativeIndex = charArray[i] - '0';
            if (relativeIndex >= 0 && relativeIndex <= 9) {
                int newChar = '\u2080' + relativeIndex;
                charArray[i] = (char) newChar;
            }
        }
        
        return new String(charArray);
    }
	
	public static Field getPublicField(Object aObject, String aField) {
		Field rField = null;
		try {
			rField = aObject.getClass().getDeclaredField(aField);
		} catch (Throwable e) {/*Do nothing*/}
		return rField;
	}
	
	public static Field getField(Object aObject, String aField) {
		Field rField = null;
		try {
			rField = aObject.getClass().getDeclaredField(aField);
			rField.setAccessible(true);
		} catch (Throwable e) {/*Do nothing*/}
		return rField;
	}
	
	public static Field getField(Class<?> aObject, String aField) {
		Field rField = null;
		try {
			rField = aObject.getDeclaredField(aField);
			rField.setAccessible(true);
		} catch (Throwable e) {/*Do nothing*/}
		return rField;
	}
	
	public static Method getMethod(Class<?> aObject, String aMethod, Class<?>... aParameterTypes) {
		Method rMethod = null;
		try {
			rMethod = aObject.getMethod(aMethod, aParameterTypes);
			rMethod.setAccessible(true);
		} catch (Throwable e) {/*Do nothing*/}
		return rMethod;
	}

	public static Method getMethod(Object aObject, String aMethod, Class<?>... aParameterTypes) {
		Method rMethod = null;
		try {
			rMethod = aObject.getClass().getMethod(aMethod, aParameterTypes);
			rMethod.setAccessible(true);
		} catch (Throwable e) {/*Do nothing*/}
		return rMethod;
	}

	public static Field getField(Object aObject, String aField, boolean aPrivate, boolean aLogErrors) {
		try {
			Field tField = (aObject instanceof Class)?((Class<?>)aObject).getDeclaredField(aField):(aObject instanceof String)?Class.forName((String)aObject).getDeclaredField(aField):aObject.getClass().getDeclaredField(aField);
			if (aPrivate) tField.setAccessible(true);
			return tField;
		} catch (Throwable e) {
			if (aLogErrors) GT_Log.log.catching(e);
		}
		return null;
	}
	
	public static Object getFieldContent(Object aObject, String aField, boolean aPrivate, boolean aLogErrors) {
		try {
			Field tField = (aObject instanceof Class)?((Class<?>)aObject).getDeclaredField(aField):(aObject instanceof String)?Class.forName((String)aObject).getDeclaredField(aField):aObject.getClass().getDeclaredField(aField);
			if (aPrivate) tField.setAccessible(true);
			return tField.get(aObject instanceof Class || aObject instanceof String ? null : aObject);
		} catch (Throwable e) {
			if (aLogErrors) GT_Log.log.catching(e);
		}
		return null;
	}
	
	public static void setFieldContent(Object aObject, Object value, String aField, boolean aPrivate, boolean aLogErrors) {
		Field f = getField(aObject, aField, aPrivate, aLogErrors);
		try {
			f.set(aObject, value);
		} catch (Throwable e) {
			if (aLogErrors) GT_Log.log.catching(e);
		}
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
					tParameterTypes[i] = (Class<?>)aParameters[i];
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
			
			Method tMethod = (aObject instanceof Class)?((Class<?>)aObject).getMethod(aMethod, tParameterTypes):aObject.getClass().getMethod(aMethod, tParameterTypes);
			if (aPrivate) tMethod.setAccessible(true);
			return tMethod.invoke(aObject, aParameters);
		} catch (Throwable e) {
			if (aLogErrors) GT_Log.log.catching(e);
		}
		return null;
	}
	
	public static Object callConstructor(String aClass, int aConstructorIndex, Object aReplacementObject, boolean aLogErrors, Object... aParameters) {
		if (aConstructorIndex < 0) {
			try {
				for (Constructor<?> tConstructor : Class.forName(aClass).getConstructors()) {
					try {
						return tConstructor.newInstance(aParameters);
					} catch (Throwable e) {/*Do nothing*/}
				}
			} catch (Throwable e) {
				if (aLogErrors) GT_Log.log.catching(e);
			}
		} else {
			try {
				return Class.forName(aClass).getConstructors()[aConstructorIndex].newInstance(aParameters);
			} catch (Throwable e) {
				if (aLogErrors) GT_Log.log.catching(e);
			}
		}
		return aReplacementObject;
	}
	
	public static String capitalizeString(String aString) {
		if (aString != null && aString.length() > 0) return aString.substring(0, 1).toUpperCase() + aString.substring(1);
		return "";
	}
	
    @SuppressWarnings("unchecked")
	public static boolean getPotion(EntityLivingBase aPlayer, int aPotionIndex) {
        try  {
        	Field tPotionHashmap = null;
        	
            Field[] var3 = EntityLivingBase.class.getDeclaredFields();
            int var4 = var3.length;
            
            for (int var5 = 0; var5 < var4; ++var5) {
                Field var6 = var3[var5];
                if (var6.getType() == HashMap.class) {
                    tPotionHashmap = var6;
                    tPotionHashmap.setAccessible(true);
                    break;
                }
            }
            
            if (tPotionHashmap != null) return ((HashMap<Integer, PotionEffect>)tPotionHashmap.get(aPlayer)).get(aPotionIndex) != null;
        } catch (Throwable e) {
        	if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);
        }
    	return false;
    }
	
    public static String getClassName(Object aObject) {
    	if (aObject == null) return "null";
    	return aObject.getClass().getName().substring(aObject.getClass().getName().lastIndexOf(".")+1);
    }
    
    @SuppressWarnings("unchecked")
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

            if (tPotionHashmap != null) ((HashMap<Integer, PotionEffect>)tPotionHashmap.get(aPlayer)).remove(aPotionIndex);
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
	
	@SuppressWarnings("unchecked")
	public static ItemStack suckOneItemStackAt(World aWorld, double aX, double aY, double aZ, double aL, double aH, double aW) {
		for (EntityItem tItem : (ArrayList<EntityItem>)aWorld.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+aL, aY+aH, aZ+aW))) {
			if (!tItem.isDead) {
				aWorld.removeEntity(tItem);
				tItem.setDead();
				return tItem.getEntityItem();
			}
		}
		return null;
	}

	public static byte getOppositeSide(int aSide) {
		return (byte)ForgeDirection.getOrientation(aSide).getOpposite().ordinal();
	}
	
	public static byte getTier(int aValue) {
		byte i = -1;
		while (++i < GregTech_API.VOLTAGES.length) if (aValue <= GregTech_API.VOLTAGES[i]) return i;
		return i;
	}
	
	public static void sendChatToPlayer(EntityPlayer aPlayer, String aChatMessage) {
		GT_Utility.sendChatToPlayer(aPlayer, new ChatComponentText(aChatMessage));
	}
	
	public static void sendChatToPlayer(EntityPlayer aPlayer, IChatComponent aChatMessage) {
		if (aPlayer != null && aChatMessage != null) {
			aPlayer.addChatMessage(aChatMessage);
		}
	}
	
	public static boolean TE_CHECK = false, BC_CHECK = false, CHECK_ALL = true;
	
	public static void checkAvailabilities() {
		if (CHECK_ALL) {
			try {
				Class<?> tClass = cofh.api.transport.IItemDuct.class;
				tClass.getCanonicalName();
				TE_CHECK = true;
			} catch(Throwable e) {/**/}
			try {
				Class<?> tClass = buildcraft.api.transport.IPipeTile.class;
				tClass.getCanonicalName();
				BC_CHECK = true;
			} catch(Throwable e) {/**/}
			CHECK_ALL = false;
		}
	}
	
	public static boolean isConnectableNonInventoryPipe(Object aTileEntity, int aSide) {
		if (aTileEntity == null) return false;
		checkAvailabilities();
		if (TE_CHECK) if (aTileEntity instanceof cofh.api.transport.IItemDuct) return true;
		if (BC_CHECK) if (aTileEntity instanceof buildcraft.api.transport.IPipeTile) return ((buildcraft.api.transport.IPipeTile)aTileEntity).isPipeConnected(ForgeDirection.getOrientation(aSide));
		return false;
	}
	
	/**
	 * Moves Stack from Inv-Slot to Inv-Slot, without checking if its even allowed.
	 * @return the Amount of moved Items
	 */
	public static byte moveStackIntoPipe(IInventory aTileEntity1, Object aTileEntity2, int[] aGrabSlots, int aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
		if (aTileEntity1 == null || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMaxMoveAtOnce <= 0 || aMinMoveAtOnce > aMaxMoveAtOnce) return 0;
		if (aTileEntity2 != null) {
			checkAvailabilities();
			if (TE_CHECK && aTileEntity2 instanceof cofh.api.transport.IItemDuct) {
				for (int i = 0; i < aGrabSlots.length; i++) {
					if (listContainsItem(aFilter, aTileEntity1.getStackInSlot(aGrabSlots[i]), true, aInvertFilter)) {
						if (isAllowedToTakeFromSlot(aTileEntity1, aGrabSlots[i], (byte)aGrabFrom, aTileEntity1.getStackInSlot(aGrabSlots[i]))) {
							if (Math.max(aMinMoveAtOnce, aMinTargetStackSize) <= aTileEntity1.getStackInSlot(aGrabSlots[i]).stackSize) {
								ItemStack tStack = copyAmount(Math.min(aTileEntity1.getStackInSlot(aGrabSlots[i]).stackSize, Math.min(aMaxMoveAtOnce, aMaxTargetStackSize)), aTileEntity1.getStackInSlot(aGrabSlots[i]));
								ItemStack remains = ((cofh.api.transport.IItemDuct)aTileEntity2).insertItem(ForgeDirection.getOrientation(aPutTo), copy(tStack));
								byte tMovedItemCount = (byte)(tStack.stackSize - (remains == null ? 0 : remains.stackSize));
								if (tMovedItemCount >= 0) {
									aTileEntity1.decrStackSize(aGrabSlots[i], tMovedItemCount);
									aTileEntity1.markDirty();
									return tMovedItemCount;
								}
							}
						}
					}
				}
				
				return 0;
			}
			if (BC_CHECK && aTileEntity2 instanceof buildcraft.api.transport.IPipeTile) {
				for (int i = 0; i < aGrabSlots.length; i++) {
					if (listContainsItem(aFilter, aTileEntity1.getStackInSlot(aGrabSlots[i]), true, aInvertFilter)) {
						if (isAllowedToTakeFromSlot(aTileEntity1, aGrabSlots[i], (byte)aGrabFrom, aTileEntity1.getStackInSlot(aGrabSlots[i]))) {
							if (Math.max(aMinMoveAtOnce, aMinTargetStackSize) <= aTileEntity1.getStackInSlot(aGrabSlots[i]).stackSize) {
								ItemStack tStack = copyAmount(Math.min(aTileEntity1.getStackInSlot(aGrabSlots[i]).stackSize, Math.min(aMaxMoveAtOnce, aMaxTargetStackSize)), aTileEntity1.getStackInSlot(aGrabSlots[i]));
								byte tMovedItemCount = (byte)((buildcraft.api.transport.IPipeTile)aTileEntity2).injectItem(copy(tStack), false, ForgeDirection.getOrientation(aPutTo), null);
								if (tMovedItemCount >= Math.max(aMinMoveAtOnce, aMinTargetStackSize)) {
									tMovedItemCount = (byte)(((buildcraft.api.transport.IPipeTile)aTileEntity2).injectItem(copyAmount(tMovedItemCount, tStack), true, ForgeDirection.getOrientation(aPutTo), null));
									aTileEntity1.decrStackSize(aGrabSlots[i], tMovedItemCount);
									aTileEntity1.markDirty();
									return tMovedItemCount;
								}
							}
						}
					}
				}
				return 0;
			}
		}
		return 0;
	}
	
	/**
	 * Moves Stack from Inv-Slot to Inv-Slot, without checking if its even allowed. (useful for internal Inventory Operations)
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
				aTileEntity1.markDirty();
				if (tStack3 != null) {
					if (tStack2 == null) {
						aTileEntity2.setInventorySlotContents(aPutTo, copy(tStack3));
						aTileEntity1.markDirty();
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
	public static byte moveOneItemStack(Object aTileEntity1, Object aTileEntity2, byte aGrabFrom, byte aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
		if (aTileEntity1 != null && aTileEntity1 instanceof IInventory) return moveOneItemStack((IInventory)aTileEntity1, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, true);
		return 0;
	}
	
	/**
	 * This is only because I needed an additional Parameter for the Double Chest Check.
	 */
	private static byte moveOneItemStack(IInventory aTileEntity1, Object aTileEntity2, byte aGrabFrom, byte aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce, boolean aDoCheckChests) {
		if (aTileEntity1 == null || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMaxMoveAtOnce <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMinMoveAtOnce > aMaxMoveAtOnce) return 0;
		
		int[] tGrabSlots = null;
		if (aTileEntity1 instanceof ISidedInventory) tGrabSlots = ((ISidedInventory)aTileEntity1).getAccessibleSlotsFromSide(aGrabFrom);
		if (tGrabSlots == null) {
			tGrabSlots = new int[aTileEntity1.getSizeInventory()];
			for (int i = 0; i < tGrabSlots.length; i++) tGrabSlots[i] = i;
		}
		
		if (aTileEntity2 != null && aTileEntity2 instanceof IInventory) {
			int[] tPutSlots = null;
			if (aTileEntity2 instanceof ISidedInventory) tPutSlots = ((ISidedInventory)aTileEntity2).getAccessibleSlotsFromSide(aPutTo);
			
			if (tPutSlots == null) {
				tPutSlots = new int[((IInventory)aTileEntity2).getSizeInventory()];
				for (int i = 0; i < tPutSlots.length; i++) tPutSlots[i] = i;
			}
			
			for (int i = 0; i < tGrabSlots.length; i++) {
				if (listContainsItem(aFilter, aTileEntity1.getStackInSlot(tGrabSlots[i]), true, aInvertFilter) && isAllowedToTakeFromSlot(aTileEntity1, tGrabSlots[i], aGrabFrom, aTileEntity1.getStackInSlot(tGrabSlots[i]))) {
					for (int j = 0; j < tPutSlots.length; j++) {
						if (isAllowedToPutIntoSlot((IInventory)aTileEntity2, tPutSlots[j], aPutTo, aTileEntity1.getStackInSlot(tGrabSlots[i]))) {
							byte tMovedItemCount = moveStackFromSlotAToSlotB(aTileEntity1, (IInventory)aTileEntity2, tGrabSlots[i], tPutSlots[j], aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
							if (tMovedItemCount > 0) return tMovedItemCount;
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
					} else if (tTileEntity1.adjacentChestZPos != null) {
						tAmount = moveOneItemStack(tTileEntity1.adjacentChestZPos, aTileEntity2, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
					}
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
					} else if (tTileEntity2.adjacentChestZPos != null) {
						tAmount = moveOneItemStack(aTileEntity1, tTileEntity2.adjacentChestZPos, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce, false);
					}
					if (tAmount != 0) return tAmount;
				}
			}
		}
		
		return moveStackIntoPipe(aTileEntity1, aTileEntity2, tGrabSlots, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
	}
	
	/**
	 * Moves Stack from Inv-Side to Inv-Slot.
	 * @return the Amount of moved Items
	 */
	public static byte moveOneItemStackIntoSlot(Object aTileEntity1, Object aTileEntity2, byte aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
		if (aTileEntity1 == null || !(aTileEntity1 instanceof IInventory) || aPutTo < 0 || aMaxTargetStackSize <= 0 || aMinTargetStackSize <= 0 || aMaxMoveAtOnce <= 0 || aMinTargetStackSize > aMaxTargetStackSize || aMinMoveAtOnce > aMaxMoveAtOnce) return 0;
		
		int[] tGrabSlots = null;
		if (aTileEntity1 instanceof ISidedInventory) tGrabSlots = ((ISidedInventory)aTileEntity1).getAccessibleSlotsFromSide(aGrabFrom);
		if (tGrabSlots == null) {
			tGrabSlots = new int[((IInventory)aTileEntity1).getSizeInventory()];
			for (int i = 0; i < tGrabSlots.length; i++) tGrabSlots[i] = i;
		}
		
		if (aTileEntity2 != null && aTileEntity2 instanceof IInventory) {
			for (int i = 0; i < tGrabSlots.length; i++) {
				if (listContainsItem(aFilter, ((IInventory)aTileEntity1).getStackInSlot(tGrabSlots[i]), true, aInvertFilter)) {
					if (isAllowedToTakeFromSlot((IInventory)aTileEntity1, tGrabSlots[i], aGrabFrom, ((IInventory)aTileEntity1).getStackInSlot(tGrabSlots[i]))) {
						if (isAllowedToPutIntoSlot((IInventory)aTileEntity2, aPutTo, (byte)6, ((IInventory)aTileEntity1).getStackInSlot(tGrabSlots[i]))) {
							byte tMovedItemCount = moveStackFromSlotAToSlotB((IInventory)aTileEntity1, (IInventory)aTileEntity2, tGrabSlots[i], aPutTo, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
							if (tMovedItemCount > 0) return tMovedItemCount;
						}
					}
				}
			}
		}
		
		moveStackIntoPipe(((IInventory)aTileEntity1), aTileEntity2, tGrabSlots, aGrabFrom, aPutTo, aFilter, aInvertFilter, aMaxTargetStackSize, aMinTargetStackSize, aMaxMoveAtOnce, aMinMoveAtOnce);
		return 0;
	}
	
	/**
	 * Moves Stack from Inv-Slot to Inv-Slot.
	 * @return the Amount of moved Items
	 */
	public static byte moveFromSlotToSlot(IInventory aTileEntity1, IInventory aTileEntity2, int aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, byte aMaxTargetStackSize, byte aMinTargetStackSize, byte aMaxMoveAtOnce, byte aMinMoveAtOnce) {
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
			return ((aStack1.getTagCompound() == null) == (aStack2.getTagCompound() == null)) && (aStack1.getTagCompound() == null || aStack1.getTagCompound().equals(aStack2.getTagCompound())) && (Items.feather.getDamage(aStack1) == Items.feather.getDamage(aStack2) || Items.feather.getDamage(aStack1) == GregTech_API.ITEM_WILDCARD_DAMAGE || Items.feather.getDamage(aStack2) == GregTech_API.ITEM_WILDCARD_DAMAGE);
		}
		return false;
	}
	
	public static boolean areStacksEqual(ItemStack aStack1, ItemStack aStack2) {
		return areStacksEqual(aStack1, aStack2, false);
	}
	
	public static boolean areStacksEqual(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {
		return aStack1 != null && aStack2 != null && aStack1.getItem() == aStack2.getItem() && (aIgnoreNBT || ((aStack1.getTagCompound() == null) == (aStack2.getTagCompound() == null)) && (aStack1.getTagCompound() == null || aStack1.getTagCompound().equals(aStack2.getTagCompound()))) && (Items.feather.getDamage(aStack1) == Items.feather.getDamage(aStack2) || Items.feather.getDamage(aStack1) == GregTech_API.ITEM_WILDCARD_DAMAGE || Items.feather.getDamage(aStack2) == GregTech_API.ITEM_WILDCARD_DAMAGE);
	}
	
	public static boolean areUnificationsEqual(ItemStack aStack1, ItemStack aStack2) {
		return areUnificationsEqual(aStack1, aStack2, false);
	}
	
	public static boolean areUnificationsEqual(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {
		return areStacksEqual(GT_OreDictUnificator.get(aStack1), GT_OreDictUnificator.get(aStack2), aIgnoreNBT);
	}
	
	public static String getFluidName(Fluid aFluid, boolean aLocalized) {
		if (aFluid == null) return "";
		String rName = aLocalized ? aFluid.getLocalizedName(new FluidStack(aFluid, 1)) : aFluid.getUnlocalizedName();
		if (rName.contains(".")) return capitalizeString(rName.replaceAll("fluid.", "").replaceAll("tile.", ""));
		return rName;
	}
	
	public static String getFluidName(FluidStack aFluid, boolean aLocalized) {
		if (aFluid == null) return "";
		return getFluidName(aFluid.getFluid(), aLocalized);
	}
	
	public static boolean areFluidStackSame(FluidStack f1, FluidStack f2) {
		return f1 == null && f2 == null ? true : (f1 != null && f2 != null ? f1.isFluidEqual(f2) && f1.amount == f2.amount : false);
	}
	
    public static ItemStack fillFluidContainer(FluidStack aFluid, ItemStack aStack) {
		if (isStackInvalid(aStack) || aFluid == null) return null;
    	if (aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getFluid(aStack) == null && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) <= aFluid.amount) {
			((IFluidContainerItem)aStack.getItem()).fill(aStack = copyAmount(1, aStack), aFluid, true);
			return aStack;
    	}
        return FluidContainerRegistry.fillFluidContainer(aFluid, aStack);
    }
    
    public static boolean containsFluid(ItemStack aStack, FluidStack aFluid) {
		if (isStackInvalid(aStack) || aFluid == null) return false;
    	if (aStack.getItem() instanceof IFluidContainerItem) {
			return FluidStack.areFluidStackTagsEqual(aFluid, ((IFluidContainerItem)aStack.getItem()).getFluid(aStack = copyAmount(1, aStack)));
    	}
    	
    	FluidStack tFluid = FluidContainerRegistry.getFluidForFilledItem(aStack);
    	return tFluid != null && tFluid.amount <= aFluid.amount && tFluid.isFluidEqual(aFluid);
    }
    
	public static FluidStack getFluidForFilledItem(ItemStack aStack) {
		if (isStackInvalid(aStack)) return null;
		if (aStack.getItem() instanceof IFluidContainerItem) return ((IFluidContainerItem) aStack.getItem()).drain(aStack, Integer.MAX_VALUE, false);
		FluidStack rFluid = FluidContainerRegistry.getFluidForFilledItem(aStack);
		if (rFluid != null) return rFluid.copy();
		return null;
	}
	
	public static ItemStack getContainerForFilledItem(ItemStack aStack) {
		if (isStackInvalid(aStack)) return null;
		if (FluidContainerRegistry.isContainer(aStack))
			return FluidContainerRegistry.drainFluidContainer(aStack);
		else if (aStack.getItem() instanceof IFluidContainerItem) {
			((IFluidContainerItem)aStack.getItem()).drain(aStack = copyAmount(1, aStack), Integer.MAX_VALUE, true);
			return aStack;
		}
		
		return null;
	}
	
	public static int getFilledContainerCapacity(ItemStack container) {
		if (container.getItem() instanceof IFluidContainerItem) {
			return ((IFluidContainerItem)container.getItem()).getFluid(container).amount;
		} else {
			return FluidContainerRegistry.getContainerCapacity(container);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getContainerItem(ItemStack aStack) {
		if (isStackInvalid(aStack)) return null;
		if (aStack.getItem().hasContainerItem()) return aStack.getItem().getContainerItem(aStack);
		/** These are all special Cases, in which it is intended to have only GT Blocks outputting those Container Items */
		if (GT_Items.Cell_Empty.isStackEqual(aStack, false, true)) return null;
		
		int tCapsuleCount = GT_ModHandler.getCapsuleCellContainerCount(aStack);
		if (tCapsuleCount > 0) return GT_Items.Cell_Empty.get(tCapsuleCount);
		
		if (GT_OreDictUnificator.isItemStackInstanceOf(aStack, GT_ToolDictNames.craftingToolForgeHammer) || GT_OreDictUnificator.isItemStackInstanceOf(aStack, GT_ToolDictNames.craftingToolWireCutter)) {
			return copyMetaData(Items.feather.getDamage(aStack) + 1, aStack);
		}
		
		return null;
	}
	
	public static boolean removeSimpleIC2MachineRecipe(ItemStack aInput, Map<IRecipeInput, RecipeOutput> aRecipeList, ItemStack aOutput) {
		if ((isStackInvalid(aInput) && isStackInvalid(aOutput)) || aRecipeList == null) return false;
		boolean rReturn = false;
		Iterator<Map.Entry<IRecipeInput, RecipeOutput>> tIterator = aRecipeList.entrySet().iterator();
		while (tIterator.hasNext()) {
			Map.Entry<IRecipeInput, RecipeOutput> tEntry = tIterator.next();
			if (aInput == null || tEntry.getKey().matches(aInput)) {
				List<ItemStack> tList = tEntry.getValue().items;
				if (tList != null) for (ItemStack tOutput : tList) if (aOutput == null || aOutput.isItemEqual(tOutput)) {
					tIterator.remove();
					rReturn = true;
					break;
				}
			}
		}
		return rReturn;
	}
	
	public static boolean addSimpleIC2MachineRecipe(ItemStack aInput, Map<IRecipeInput, RecipeOutput> aRecipeList, NBTTagCompound aNBT, Object... aOutput) {
		if (isStackInvalid(aInput) || aOutput.length == 0 || aRecipeList == null) return false;
		String tOreName = GT_OreDictUnificator.getAssociation(aInput);
		IRecipeInput in = isStringValid(tOreName) ? 
				new RecipeInputOreDict(tOreName, aInput.stackSize) : 
				new RecipeInputItemStack(copy(aInput), aInput.stackSize);
		return addSimpleIC2MachineRecipe(in, aRecipeList, aNBT, GT_OreDictUnificator.getStackArray(true, aOutput));
	}
	
	public static boolean addSimpleIC2MachineRecipe(IRecipeInput input, Map<IRecipeInput, RecipeOutput> aRecipeList, NBTTagCompound meta, ItemStack...outputs) {
		outputs = Arrays.stream(outputs)
				.filter(val -> val != null)
				.toArray(i -> new ItemStack[i]);
		if (input == null || outputs.length == 0 || aRecipeList == null) return false; 		
		return aRecipeList.put(input, new RecipeOutput(meta, outputs)) != null;
	}
	
	private static int sBookCount = 0;
	
	public static ItemStack getWrittenBook(String aTitle, String aAuthor, String... aPages) {
		if (isStringInvalid(aTitle) || isStringInvalid(aAuthor) || aPages.length <= 0) return null;
		sBookCount++;
		ItemStack rStack = new ItemStack(Items.written_book, 1);
        NBTTagCompound tNBT = new NBTTagCompound();
        tNBT.setString("title", aTitle);
        tNBT.setString("author", aAuthor);
        NBTTagList tNBTList = new NBTTagList();
        for (byte i = 0; i < aPages.length; i++) {
	        if (i < 48) {
	        	if (aPages[i].length() < 256)
	        		tNBTList.appendTag(new NBTTagString(aPages[i]));
	        	else
	        		GT_Log.log.error("WARNING: String for written Book too long! -> " + aPages[i]);
	        } else {
        		GT_Log.log.error("WARNING: Too much Pages for written Book! -> " + aTitle);
	        	break;
	        }
        }
		tNBTList.appendTag(new NBTTagString("Credits to " + aAuthor + " for writing this Book. This was Book Nr. " + sBookCount + " at its creation. Gotta get 'em all!"));
        tNBT.setTag("pages", tNBTList);
        rStack.setTagCompound(tNBT);
        GregTech_API.sBookList.put(aTitle, rStack);
		return copy(rStack);
	}
	
	public static Map<GT_PlayedSound, Integer> sPlayedSoundMap = new HashMap<GT_PlayedSound, Integer>();
	
	public static boolean doSoundAtClient(String aSoundName, int aTimeUntilNextSound, float aSoundStrength) {
		return doSoundAtClient(aSoundName, aTimeUntilNextSound, aSoundStrength, GregTech_API.gregtechmod.getThePlayer());
	}
	
	public static boolean doSoundAtClient(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, Entity aEntity) {
		if (aEntity == null) return false;
		return doSoundAtClient(aSoundName, aTimeUntilNextSound, aSoundStrength, aEntity.posX, aEntity.posY, aEntity.posZ);
	}
	
	public static boolean doSoundAtClient(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, double aX, double aY, double aZ) {
		return doSoundAtClient(aSoundName, aTimeUntilNextSound, aSoundStrength, 0.9F + new Random().nextFloat() * 0.2F, aX, aY, aZ);
	}
	
	public static boolean doSoundAtClient(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, float aSoundModulation, double aX, double aY, double aZ) {
		GT_PlayedSound tSound;
		if (isStringInvalid(aSoundName) || !FMLCommonHandler.instance().getEffectiveSide().isClient() || sPlayedSoundMap.keySet().contains(tSound = new GT_PlayedSound(aSoundName, aX, aY, aZ)) || GregTech_API.gregtechmod.getThePlayer() == null || !GregTech_API.gregtechmod.getThePlayer().worldObj.isRemote) return false;
		GregTech_API.gregtechmod.getThePlayer().worldObj.playSound(aX, aY, aZ, aSoundName, aSoundStrength, aSoundModulation, false);
		sPlayedSoundMap.put(tSound, aTimeUntilNextSound);
		return true;
	}
	
	public static boolean sendSoundToPlayers(World aWorld, String aSoundName, float aSoundStrength, float aSoundModulation, int aX, int aY, int aZ) {
		if (isStringInvalid(aSoundName) || aWorld == null || aWorld.isRemote) return false;
		if (aSoundName == null || aSoundName.equals("") || aWorld == null || aWorld.isRemote) return false;
		aSoundModulation = aSoundModulation < 0 ? (1.0F + (aWorld.rand.nextFloat() - aWorld.rand.nextFloat()) * 0.2F) * 0.7F : aSoundModulation;
		GT_SoundPacket tPacket = new GT_SoundPacket(aSoundName, aX, aY, aZ, aSoundStrength, aSoundModulation);
        sendPacketToAllPlayersInRange(aWorld, tPacket, aX, aZ);
        
		return true;
	}
	
	public static void sendPacketToAllPlayersInRange(World aWorld, GT_Packet aPacket, int aX, int aZ) {
		for (Object tObject : aWorld.playerEntities) {
        	if (tObject instanceof EntityPlayerMP) {
        		EntityPlayerMP tPlayer = (EntityPlayerMP)tObject;
				Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
				if (tPlayer.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(tPlayer, tChunk.xPosition, tChunk.zPosition)) {
					if (GregTech_API.DEBUG_MODE) GT_Log.log.info("sent Packet to " + tPlayer.getDisplayName());
					GT_NetworkHandler.sendPacket(aPacket, tPlayer);
				}
        	} else {
        		break;
        	}
        }
	}
	
	public static String parseChanceString(int chance) {
		return parseNumberToString(chance * 1.0D / 100.0D);
	}
	
	public static int stackToInt(ItemStack aStack) {
		if (isStackInvalid(aStack)) return -1;
		return stackToInt(aStack, Items.feather.getDamage(aStack) == GregTech_API.ITEM_WILDCARD_DAMAGE);
	}
	
	/**
	 * Could be used to save items to NBT, but only to match existing stacks, not load it from this number
	 * For saving with ability to load better to save stack direct to NBT
	 */
	public static int stackToInt(ItemStack aStack, boolean aForceWildcard) {
		if (isStackInvalid(aStack)) return -1;
		int meta = aForceWildcard ? GregTech_API.ITEM_WILDCARD_DAMAGE : Items.feather.getDamage(aStack);
		return System.identityHashCode(aStack.getItem()) * 11 + meta;
	}
	
	public static int fluidStackToInt(FluidStack fluid) {
    	int code = 1;
    	code = 31*code + fluid.getFluid().hashCode();
    	if (fluid.tag != null)
    		code = 31*code + fluid.tag.hashCode();
		return code < 0 ? code : -code;
	}
	
	public static ItemStack getCoverByID(int aStack) {
		return GregTech_API.sCoversItems.get(Integer.valueOf(aStack));
	}
	
	public static boolean isBlockValid(Object aBlock) {
		return aBlock != null &&  (aBlock instanceof Block) && Block.getIdFromBlock((Block)aBlock) != 0;
	}
	
	public static boolean isBlockInvalid(Object aBlock) {
		return aBlock == null || !(aBlock instanceof Block) || Block.getIdFromBlock(((Block)aBlock)) == 0;
	}
	
	public static boolean isStringValid(Object aString) {
		return aString != null && !aString.toString().isEmpty();
	}
	
	public static boolean isStringInvalid(Object aString) {
		return aString == null || aString.toString().isEmpty();
	}
	
	public static boolean isStackValid(Object aStack) {
		return aStack != null &&  (aStack instanceof ItemStack) && ((ItemStack)aStack).getItem() != null && ((ItemStack)aStack).stackSize >= 0;
	}
	
	public static boolean isStackInvalid(Object aStack) {
		return aStack == null || !(aStack instanceof ItemStack) || ((ItemStack)aStack).getItem() == null || ((ItemStack)aStack).stackSize <  0;
	}
	
	public static boolean isFluidStackValid(FluidStack fluid) {
		return fluid != null && fluid.getFluid() != null && fluid.amount > 0;
	}
	
	public static boolean isDebugItem(ItemStack aStack) {
		return GT_Items.Tool_Cheat.isStackEqual(aStack, true, true) || areStacksEqual(GT_ModHandler.getIC2Item("debug", 1), aStack, true);
	}
	
	public static boolean isItemStackInIntList(ItemStack aStack, Collection<Integer> aList) {
		if (isStackInvalid(aStack) || aList == null) return false;
		return aList.contains(stackToInt(aStack, false)) || aList.contains(stackToInt(aStack, true));
	}
	
	public static boolean isItemStackInList(ItemStack aStack, Collection<ItemStackKey> aList) {
		return isItemStackInList(aStack, aList, false);
	}
	
	public static boolean isItemStackInList(ItemStack aStack, Collection<ItemStackKey> aList, boolean wildcard) {
		if (isStackInvalid(aStack) || aList == null) return false;
		return aList.contains(ItemStackKey.from(aStack, true));
	}

	public static boolean isOpaqueBlock(World aWorld, int aX, int aY, int aZ) {
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		if (tBlock != null) {
			return tBlock.isOpaqueCube();
		}
		
		return false;
	}
	
	public static boolean hasBlockHitBox(World aWorld, int aX, int aY, int aZ) {
		if (!aWorld.isAirBlock(aX, aY, aZ)) {
			return aWorld.getBlock(aX, aY, aZ).getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ) != null;
		}
		
		return false;
	}
	
	/**
	 * Converts a Number to a String with format like 10,000.10
	 */
    public static <T extends Number> String parseNumberToString(T aNumber) {
    	return sFormat.format(aNumber);
    }
    
    public static NBTTagCompound getNBTContainingBoolean(NBTTagCompound aNBT, Object aTag, boolean aValue) {
    	if (aNBT == null) aNBT = new NBTTagCompound();
    	aNBT.setBoolean(aTag.toString(), aValue);
    	return aNBT;
    }
    
    public static NBTTagCompound getNBTContainingByte(NBTTagCompound aNBT, Object aTag, byte aValue) {
    	if (aNBT == null) aNBT = new NBTTagCompound();
    	aNBT.setByte(aTag.toString(), aValue);
    	return aNBT;
    }
    
    public static NBTTagCompound getNBTContainingShort(NBTTagCompound aNBT, Object aTag, short aValue) {
    	if (aNBT == null) aNBT = new NBTTagCompound();
    	aNBT.setShort(aTag.toString(), aValue);
    	return aNBT;
    }
    
    public static NBTTagCompound getNBTContainingInteger(NBTTagCompound aNBT, Object aTag, int aValue) {
    	if (aNBT == null) aNBT = new NBTTagCompound();
    	aNBT.setInteger(aTag.toString(), aValue);
    	return aNBT;
    }
    
    public static NBTTagCompound getNBTContainingFloat(NBTTagCompound aNBT, Object aTag, float aValue) {
    	if (aNBT == null) aNBT = new NBTTagCompound();
    	aNBT.setFloat(aTag.toString(), aValue);
    	return aNBT;
    }
    
    public static NBTTagCompound getNBTContainingDouble(NBTTagCompound aNBT, Object aTag, double aValue) {
    	if (aNBT == null) aNBT = new NBTTagCompound();
    	aNBT.setDouble(aTag.toString(), aValue);
    	return aNBT;
    }
    
    public static NBTTagCompound getNBTContainingString(NBTTagCompound aNBT, Object aTag, Object aValue) {
    	if (aNBT == null) aNBT = new NBTTagCompound();
    	if (aValue == null) return aNBT;
    	aNBT.setString(aTag.toString(), aValue.toString());
    	return aNBT;
    }
    
	public static ItemStack setStack(ItemStack aSetStack, ItemStack aToStack) {
		if (isStackInvalid(aSetStack) || isStackInvalid(aToStack)) return null;
		aSetStack.func_150996_a(aToStack.getItem());
		((ItemStack)aSetStack).stackSize = ((ItemStack)aToStack).stackSize;
		Items.feather.setDamage((ItemStack)aSetStack, Items.feather.getDamage((ItemStack)aToStack));
		((ItemStack)aSetStack).setTagCompound(((ItemStack)aToStack).getTagCompound());
		return (ItemStack)aSetStack;
	}
	
	public static ItemStack[] copyStackArray(ItemStack... aStacks) {
		ItemStack[] rStacks = new ItemStack[aStacks.length];
		for (int i = 0; i < aStacks.length; i++) rStacks[i] = copy(aStacks[i]);
		return rStacks;
	}
	
	public static ItemStack copy(ItemStack... aStacks) {
		for (ItemStack tStack : aStacks) if (isStackValid(tStack)) return tStack.copy();
		return null;
	}
	
	public static FluidStack copy(FluidStack fluid, int amount) {
		fluid = fluid.copy();
		fluid.amount = amount;
		return fluid;
	}
	
	public static List<ItemStack> copy(Collection<ItemStack> stacks) {
		return stacks.stream().map(ItemStack::copy).collect(Collectors.toList());
	}
	
	public static ItemStack copy(int amount, ItemStack stack) {
		if (amount >= 0 && stack != null) {
			ItemStack copy = stack.copy();
			copy.stackSize = amount;
			return copy;
		}
		
		return null;
	}
	
	public static ItemStack copyAmount(long aAmount, ItemStack... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (isStackInvalid(rStack)) return null;
		if (aAmount > 64) aAmount = 64; else if (aAmount == -1) aAmount = 111; else if (aAmount < 0) aAmount = 0;
		rStack.stackSize = (byte)aAmount;
		return rStack;
	}
	
	public static ItemStack copyAmount(long aAmount, Object... aStacks) {
		ItemStack rStack = copy(GT_Utility.cast(aStacks));
		
		if (isStackInvalid(rStack)) return null;
		if (aAmount > 64) aAmount = 64; else if (aAmount == -1) aAmount = 111; else if (aAmount < 0) aAmount = 0;
		rStack.stackSize = (byte)aAmount;
		return rStack;
	}
	
	public static ItemStack[] cast(Object...objects) {
		Objects.requireNonNull(objects);
		ItemStack[] result = new ItemStack[objects.length];
		try {
			for (int i  = 0; i < objects.length; i++)
				result[i] = (ItemStack) objects[i];
		} catch (ClassCastException e) {
			GT_Log.log.throwing(e);
			throw new IllegalArgumentException("Array can not contain not ItemStacks!");
		}
		
		return result;
	}
	
	public static ItemStack copyMetaData(long aMetaData, ItemStack... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (isStackInvalid(rStack)) return null;
		Items.feather.setDamage(rStack, (short)aMetaData);
		return rStack;
	}
	
	public static ItemStack copyAmountAndMetaData(long aAmount, long aMetaData, ItemStack... aStacks) {
		ItemStack rStack = copyAmount(aAmount, aStacks);
		if (isStackInvalid(rStack)) return null;
		Items.feather.setDamage(rStack, (short)aMetaData);
		return rStack;
	}
	
	/**
	 * returns a copy of an ItemStack with its Stacksize being multiplied by aMultiplier
	 */
	public static ItemStack mul(long aMultiplier, ItemStack... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (rStack == null) return null;
		rStack.stackSize *= aMultiplier;
		return rStack;
	}
	
	public static ItemStack mul(long aMultiplier, Object... aStacks) {
		return GT_Utility.mul(aMultiplier, GT_Utility.cast(aStacks));
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
	
	public static <E> E selectItemInList(int aIndex, E aReplacement, List<E> aList) {
		if (aList == null || aList.isEmpty()) return aReplacement;
		if (aList.size() <= aIndex) return aList.get(aList.size() - 1);
		if (aIndex < 0) return aList.get(0);
		return aList.get(aIndex);
	}
	
	@SafeVarargs
	public static <E> E selectItemInList(int aIndex, E aReplacement, E... aList) {
		if (aList == null || aList.length == 0) return aReplacement;
		if (aList.length <= aIndex) return aList[aList.length - 1];
		if (aIndex < 0) return aList[0];
		return aList[aIndex];
	}
	
    public static Map<GT_MetaPipeEntity_Item, Long> scanPipes(GT_MetaPipeEntity_Item aMetaTileEntity, Map<GT_MetaPipeEntity_Item, Long> aMap, long aStep, boolean aSuckItems) {
    	aStep+=aMetaTileEntity.getStepSize();
    	if (aMetaTileEntity.pipeCapacityCheck()) if (aMap.get(aMetaTileEntity) == null || aMap.get(aMetaTileEntity) > aStep) {
    		aMap.put(aMetaTileEntity, aStep);
	    	for (byte i = 0; i < 6; i++) {
	    		if (aSuckItems) {
		    		if (aMetaTileEntity.getBaseMetaTileEntity().getCoverBehaviorAtSide(i).letsItemsIn(i, aMetaTileEntity.getBaseMetaTileEntity().getCoverIDAtSide(i), aMetaTileEntity.getBaseMetaTileEntity().getCoverDataAtSide(i), aMetaTileEntity.getBaseMetaTileEntity())) {
			    		IGregTechTileEntity tItemPipe = aMetaTileEntity.getBaseMetaTileEntity().getIGregTechTileEntityAtSide(i);
	    				if (aMetaTileEntity.getBaseMetaTileEntity().getColorization() >= 0) {
		    				byte tColor = tItemPipe.getColorization();
		    				if (tColor >= 0 && (tColor & 15) != (aMetaTileEntity.getBaseMetaTileEntity().getColorization() & 15)) {
		    					continue;
		    				}
		    			}
			    		if (tItemPipe != null && tItemPipe instanceof BaseMetaPipeEntity) {
				    		IMetaTileEntity tMetaTileEntity = tItemPipe.getMetaTileEntity();
				    		if (tMetaTileEntity != null && tMetaTileEntity instanceof GT_MetaPipeEntity_Item && tItemPipe.getCoverBehaviorAtSide(getOppositeSide(i)).letsItemsOut(getOppositeSide(i), tItemPipe.getCoverIDAtSide(getOppositeSide(i)), tItemPipe.getCoverDataAtSide(getOppositeSide(i)), tItemPipe)) {
				    			scanPipes((GT_MetaPipeEntity_Item)tMetaTileEntity, aMap, aStep, aSuckItems);
				    		}
			    		}
		    		}
	    		} else {
		    		if (aMetaTileEntity.getBaseMetaTileEntity().getCoverBehaviorAtSide(i).letsItemsOut(i, aMetaTileEntity.getBaseMetaTileEntity().getCoverIDAtSide(i), aMetaTileEntity.getBaseMetaTileEntity().getCoverDataAtSide(i), aMetaTileEntity.getBaseMetaTileEntity())) {
			    		IGregTechTileEntity tItemPipe = aMetaTileEntity.getBaseMetaTileEntity().getIGregTechTileEntityAtSide(i);
	    				if (tItemPipe != null) {
	    					if (aMetaTileEntity.getBaseMetaTileEntity().getColorization() >= 0) {
			    				byte tColor = tItemPipe.getColorization();
			    				if (tColor >= 0 && (tColor & 15) != (aMetaTileEntity.getBaseMetaTileEntity().getColorization() & 15)) {
			    					continue;
			    				}
			    			}
				    		if (tItemPipe instanceof BaseMetaPipeEntity) {
					    		IMetaTileEntity tMetaTileEntity = tItemPipe.getMetaTileEntity();
					    		if (tMetaTileEntity != null && tMetaTileEntity instanceof GT_MetaPipeEntity_Item && tItemPipe.getCoverBehaviorAtSide(getOppositeSide(i)).letsItemsIn(getOppositeSide(i), tItemPipe.getCoverIDAtSide(getOppositeSide(i)), tItemPipe.getCoverDataAtSide(getOppositeSide(i)), tItemPipe)) {
					    			scanPipes((GT_MetaPipeEntity_Item)tMetaTileEntity, aMap, aStep, aSuckItems);
					    		}
				    		}
	    				}
		    		}
	    		}
	    	}
    	}
    	return aMap;
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
				aPlayer.mcServer.getConfigurationManager().transferPlayerToDimension(aPlayer, aDimension);
				aPlayer.playerNetServerHandler.setPlayerLocation(aX+0.5, aY+0.5, aZ+0.5, aPlayer.rotationYaw, aPlayer.rotationPitch);
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
			
			if (aEntity instanceof EntityLivingBase) {
				((EntityLivingBase)aEntity).setPositionAndUpdate(aX, aY, aZ);
			} else {
				aEntity.setPosition(aX, aY, aZ);
			}
			
            tOriginalWorld.resetUpdateEntityTick();
            tTargetWorld.resetUpdateEntityTick();
			return true;
		}
		return false;
	}
	
	public static int getCoordinateScan(ArrayList<String> aList, EntityPlayer aPlayer, World aWorld, int aScanLevel, int aX, int aY, int aZ, int aSide, float aClickX, float aClickY, float aClickZ) {
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
		    tList.add("Block class: §e" + tBlock.getClass().getSimpleName());
		    if (tTileEntity != null) tList.add("TileEntity class: §e" + tTileEntity.getClass().getSimpleName());
		    tList.add("Hardness: " + tBlock.getBlockHardness(aWorld, aX, aY, aZ) + "  Blast Resistance: " + tBlock.getExplosionResistance(aPlayer, aWorld, aX, aY, aZ, aPlayer.posX, aPlayer.posY, aPlayer.posZ));
		    tList.add("-----");
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
		    	tList.add("** Machine upgrades **");
		    	if (0 < (tValue = ((IUpgradableMachine)tTileEntity).getOverclockerUpgradeCount())) tList.add(" - " + tValue	+ " Overclocker Upgrades");
		    	if (0 < (tValue = ((IUpgradableMachine)tTileEntity).getTransformerUpgradeCount())) tList.add(" - " + tValue	+ " Transformer Upgrades");
		    	if (0 < (tValue = ((IUpgradableMachine)tTileEntity).getUpgradeStorageVolume()   )) tList.add(" - " + tValue	+ " Upgraded EU Capacity");
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
				ICropTile crop = (ICropTile) tTileEntity;
				CropCard card = crop.getCrop();
				
				if (crop.getScanLevel() < 4) {
					rEUAmount+=10000;
					crop.setScanLevel((byte)4);
				}
				if (card != null) {
					rEUAmount+=1000;
					tList.add("Type -- Crop-Name: " + card.name()
			        		+ "  Growth: " + crop.getGrowth()
			        		+ "  Gain: " + crop.getGain()
			        		+ "  Resistance: " + crop.getResistance()
			        		);
			        tList.add("Plant -- Fertilizer: " + crop.getNutrientStorage()
			        		+ "  Water: " + crop.getHydrationStorage()
			        		+ "  Weed-Ex: " + crop.getWeedExStorage()
			        		+ "  Scan-Level: " + crop.getScanLevel()
			        		);
			        tList.add("Environment -- Nutrients: " + crop.getNutrients()
			        		+ "  Humidity: " + crop.getHumidity()
			        		+ "  Air-Quality: " + crop.getAirQuality()
			        		);
			        String tString = "";
			        for (String tAttribute : card.attributes())
			        	tString += ", " + tAttribute;
			        tList.add("Attributes:" + tString.replaceFirst(",", ""));
			        tList.add("Discovered by: " + card.discoveredBy());
			        if (!aWorld.isRemote && aPlayer.capabilities.isCreativeMode) 
			        	crop.setSize((byte) Math.min(crop.getSize() + 1, crop.getCrop().maxSize()));
				}
			}} catch(Throwable e) {if (GregTech_API.DEBUG_MODE) GT_Log.log.catching(e);}
    	}
	    try {if (tBlock instanceof IDebugableBlock) {
	    	rEUAmount+=500;
	        ArrayList<String> temp = ((IDebugableBlock)tBlock).getDebugInfo(aPlayer, aX, aY, aZ, aScanLevel);
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
	 * @return an Array containing the X and the Y Coordinate of the clicked Point, with the top left Corner as Origin, like on the Texture Sheet. return values should always be between 0.0F and 1.0F as long as aX, aY and aZ are valid.
	 */
	public static float[] getClickedFacingCoords(byte aSide, float aX, float aY, float aZ) {
		switch (aSide) {
		case  0: return new float[] {1-aX,   aZ};
		case  1: return new float[] {  aX,   aZ};
		case  2: return new float[] {1-aX, 1-aY};
		case  3: return new float[] {  aX, 1-aY};
		case  4: return new float[] {  aZ, 1-aY};
		case  5: return new float[] {1-aZ, 1-aY};
		default: return new float[] {0.5F, 0.5F};
		}
	}
	
	/**
	 * This Function determines the direction a Block gets when being Wrenched.
	 * returns -1 if invalid. Even though that could never happen.
	 */
	public static byte determineWrenchingSide(byte aSide, float aX, float aY, float aZ) {
		byte tBack = getOppositeSide(aSide);
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