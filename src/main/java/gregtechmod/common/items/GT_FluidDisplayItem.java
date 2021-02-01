package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_FluidDisplayItem extends GT_Generic_Item {
	public GT_FluidDisplayItem(String aName) {
		super(aName, null);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {
		
    }
	
	@Override
    public IIcon getIconFromDamage(int aMeta) {
    	Fluid tFluid = FluidRegistry.getFluid(aMeta);
    	if (tFluid != null) {
    		return tFluid.getStillIcon();
    	}
    	return GregTech_API.FAIL_ICON;
    }
    
	@Override
    public int getSpriteNumber() {
        return 0;
    }
	
	@Override
    public String getUnlocalizedName(ItemStack aStack) {
	    if (aStack != null) {
		    Fluid tFluid = FluidRegistry.getFluid(aStack.getItemDamage());
		    if (tFluid != null) {
		    	return tFluid.getUnlocalizedName();
		    }
	    }

        return "";
    }
	
	@Override
    public String getItemStackDisplayName(ItemStack aStack) {
		return StatCollector.translateToLocal(getUnlocalizedName(aStack));
    }
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List strs, boolean advMode) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null && tag.hasKey("amount")) {
			int amount = tag.getInteger("amount");
			strs.add(I18n.format("metatileentity.fluid.amount", GT_Utility.parseNumberToString(amount))); 
		}
	}
	
	@SuppressWarnings("rawtypes")
	@SideOnly(Side.CLIENT)
	@Override 
	public void getSubItems(Item var1, CreativeTabs aTab, List aList) {}
}