package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_FluidDisplayItem extends GT_Generic_Item {
	public GT_FluidDisplayItem(int aID, String aName) {
		super(aID, aName, null);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister aIconRegister) {
		
    }
	
	@Override
    public Icon getIconFromDamage(int aMeta) {
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
		    	return GT_Utility.capitalizeString(tFluid.getUnlocalizedName().replaceAll("fluid.", "").replaceAll("tile.", ""));
		    }
	    }
        return "";
    }
	
	@Override
    public String getItemStackDisplayName(ItemStack aStack) {
        return getUnlocalizedName(aStack);
    }
	
	@Override
    public String getItemDisplayName(ItemStack aStack) {
        return getUnlocalizedName(aStack);
    }
	
	@Override @SideOnly(Side.CLIENT) public void getSubItems(int var1, CreativeTabs aTab, List aList) {}
}