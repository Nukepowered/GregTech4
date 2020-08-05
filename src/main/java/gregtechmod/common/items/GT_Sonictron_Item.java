package gregtechmod.common.items;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.common.tileentities.GT_TileEntity_Sonictron;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class GT_Sonictron_Item extends GT_Generic_Item {
	public GT_Sonictron_Item(String aName) {
		super(aName, null);
		setMaxStackSize(1);
		setNoRepair();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		setCurrentIndex(aStack, 0);
		return aStack;
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (!par3World.isRemote && par3World.getBlockId(par4, par5, par6) == GregTech_API.sBlockList[1].blockID && par3World.getBlockMetadata(par4, par5, par6) == 6) {
			GT_TileEntity_Sonictron tSonictron = (GT_TileEntity_Sonictron)par3World.getBlockTileEntity(par4, par5, par6);
        	if (tSonictron != null) {
            	ItemStack[] tInventory = getNBTInventory(aStack);
        		if (par2EntityPlayer.isSneaking()) {
        			copyInventory(tSonictron.mInventory, tInventory);
        		} else {
        			copyInventory(tInventory, tSonictron.mInventory);
        		}
            	setNBTInventory(aStack, tInventory);
	            tSonictron.sendClientData = true;
                return true;
        	}
        }
		setCurrentIndex(aStack, -1);
        return false;
    }
    
    private void copyInventory(ItemStack aInventory[], ItemStack aNewContent[]) {
    	for (int i = 0; i < 64; i++) {
    		if (aNewContent[i] == null)
    			aInventory[i] = null;
    		else
    			aInventory[i] = GT_Utility.copy(aNewContent[i]);
    	}
    }
    
    public static int getCurrentIndex(ItemStack aStack) {
    	NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
    	if (tNBTTagCompound == null) tNBTTagCompound = new NBTTagCompound();
    	return tNBTTagCompound.getInteger("mCurrentIndex");
    }

    public static int getTickTimer(ItemStack aStack) {
    	NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
    	if (tNBTTagCompound == null) tNBTTagCompound = new NBTTagCompound();
    	return tNBTTagCompound.getInteger("mTickTimer");
    }

    public static NBTTagCompound setCurrentIndex(ItemStack aStack, int aIndex) {
    	NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
    	if (tNBTTagCompound == null) tNBTTagCompound = new NBTTagCompound();
    	tNBTTagCompound.setInteger("mCurrentIndex", aIndex);
    	return tNBTTagCompound;
    }

    public static NBTTagCompound setTickTimer(ItemStack aStack, int aTime) {
    	NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
    	if (tNBTTagCompound == null) tNBTTagCompound = new NBTTagCompound();
    	tNBTTagCompound.setInteger("mTickTimer", aTime);
    	return tNBTTagCompound;
    }
    
    public static ItemStack[] getNBTInventory(ItemStack aStack) {
    	ItemStack[] tInventory = new ItemStack[64];
    	NBTTagCompound tNBT = aStack.getTagCompound();
    	if (tNBT == null) return tInventory;
    	
    	NBTTagList tNBT_ItemList = tNBT.getTagList("Inventory");
        for (int i = 0; i < tNBT_ItemList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tNBT_ItemList.tagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < tInventory.length) {
                tInventory[slot] = GT_Utility.loadItem(tag);
            }
        }
        return tInventory;
    }
    
    public static NBTTagCompound setNBTInventory(ItemStack aStack, ItemStack[] aInventory) {
    	NBTTagCompound tNBT = aStack.getTagCompound();
    	if (tNBT == null) tNBT = new NBTTagCompound();
    	
        NBTTagList tNBT_ItemList = new NBTTagList();
        for (int i = 0; i < aInventory.length; i++) {
            ItemStack stack = aInventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                tNBT_ItemList.appendTag(tag);
            }
        }
        tNBT.setTag("Inventory", tNBT_ItemList);
        aStack.setTagCompound(tNBT);
        return tNBT;
    }
    
    public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        setNBTInventory(aStack, new ItemStack[64]);
    }
    
    @Override
    public void onUpdate(ItemStack aStack, World par2World, Entity aPlayer, int aTimer, boolean aIsInHand) {
		int	tTickTimer		= getTickTimer(aStack),
			tCurrentIndex	= getCurrentIndex(aStack);
			
		if (tTickTimer++%2==0&&tCurrentIndex>-1) {
		    ItemStack[] tInventory = getNBTInventory(aStack);
			GT_Mod.gregtechproxy.doSonictronSound(tInventory[tCurrentIndex], aPlayer.worldObj, aPlayer.posX, aPlayer.posY, aPlayer.posZ);
			if (++tCurrentIndex>63) tCurrentIndex=-1;
		}
		
		setTickTimer(aStack, tTickTimer);
		setCurrentIndex(aStack, tCurrentIndex);
	}

    public boolean getShareTag() {
        return true;
    }
}
