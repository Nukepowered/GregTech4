package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import ic2.api.item.IElectricItem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Debug_Item extends GT_Generic_Item implements IElectricItem {
	
    public GT_Debug_Item(int aID, String aName) {
		super(aID, aName, null);
		setMaxStackSize(1);
		setMaxDamage(0);
		setNoRepair();
	}
    
    public boolean getShareTag() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(this, 1), tUncharged = new ItemStack(this, 1, getMaxDamage());
        GT_ModHandler.chargeElectricItem(tCharged, 1000000000, Integer.MAX_VALUE, true, false);
        var3.add(tCharged);
        for (ItemStack tStack : GregTech_API.sBookList.values()) {
        	if (tStack != null) var3.add(tStack);
        }
    }
    
    private void setCharge(ItemStack aStack) {
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = new NBTTagCompound();
		tNBT.setInteger("charge", 1000000000);
        aStack.setTagCompound(tNBT);
    }
    
	@Override
	public boolean canProvideEnergy(ItemStack aStack) {
		setCharge(aStack);
		return true;
	}
	
	@Override
	public int getChargedItemId(ItemStack aStack) {
		setCharge(aStack);
		return itemID;
	}
	
	@Override
	public int getEmptyItemId(ItemStack aStack) {
		setCharge(aStack);
		return itemID;
	}
	
	@Override
	public int getMaxCharge(ItemStack aStack) {
		setCharge(aStack);
		return 2000000000;
	}
	
	@Override
	public int getTier(ItemStack aStack) {
		setCharge(aStack);
		return 1;
	}
	
	@Override
	public int getTransferLimit(ItemStack aStack) {
		setCharge(aStack);
		return 2000000000;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aClickX, float aClickY, float aClickZ) {
		if (aWorld.isRemote) {
    		GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(108), 1.0F, aX, aY, aZ);
    		return false;
    	}
		if (aPlayer instanceof EntityPlayerMP && GT_ModHandler.canUseElectricItem(aStack, 25000)) {
			ArrayList<String> tList = new ArrayList<String>();
			GT_ModHandler.useElectricItem(aStack, GT_Utility.getCoordinateScan(tList, aPlayer, aWorld, 1, aX, aY, aZ, aSide, aClickX, aClickY, aClickZ), aPlayer);
			for (int i = 0; i < tList.size(); i++) GT_Utility.sendChatToPlayer(aPlayer, tList.get(i));
	        return true;
	    }
        return false;
	}
}