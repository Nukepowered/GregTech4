package gregtechmod.api.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GT_EmptyTool_Item extends GT_Tool_Item {
	public GT_EmptyTool_Item(String aName, int aMaxDamage, int aChargedGTID) {
		super(aName, "Empty. You need to recharge it.", aMaxDamage, 0, aChargedGTID, -1); // FIXME lang
		try {
			Class.forName("codechicken.nei.api.API");
			codechicken.nei.api.API.hideItem(new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE));
		} catch(Throwable e) {}
	}
	
	@Override
    public boolean hasContainerItem() {
        return false;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {
		
    }
}