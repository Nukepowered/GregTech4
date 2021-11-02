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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_Scanner_Item extends GT_Generic_Item implements IElectricItem {
	
    public GT_Scanner_Item(String aName) {
		super(aName, "item.GT_Scanner.tooltip");
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
	}
    
    public boolean getShareTag() {
        return true;
    }
    
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs var2, List items) {
        ItemStack tCharged = new ItemStack(this, 1), tUncharged = new ItemStack(this, 1, getMaxDamage());
        GT_ModHandler.chargeElectricItem(tCharged, Integer.MAX_VALUE, 1, true, false);
        items.add(tCharged);
        items.add(tUncharged);
    }
    
	@Override
	public boolean canProvideEnergy(ItemStack aStack) {
		return false;
	}

	@Override
	public double getMaxCharge(ItemStack aStack) {
		return 300000;
	}
	
	@Override
	public int getTier(ItemStack aStack) {
		return 1;
	}
	
	@Override
	public double getTransferLimit(ItemStack aStack) {
		return 100;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float aClickX, float aClickY, float aClickZ) {
		if (aWorld.isRemote) {
    		GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(108), 0, 1.0F, aX, aY, aZ);
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

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		return new ItemStack(this, 1).getItem();
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		return new ItemStack(this, 1, getMaxDamage()).getItem();
	}
}
