package gregtechmod.api.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_EmptyTool_Item extends GT_Tool_Item {
	public GT_EmptyTool_Item(String aUnlocalized, int aMaxDamage, int aChargedGTID) {
		super(aUnlocalized, "item.tool.discharged.tooltip", aMaxDamage, 0, false, aChargedGTID, -1);
		try {
			Class.forName("codechicken.nei.api.API");
			codechicken.nei.api.API.hideItem(new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE));
		} catch(Throwable e) {/*Do nothing*/}
	}
	
	@Override
    public boolean hasContainerItem() {
        return false;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {}
}