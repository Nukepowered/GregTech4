package gregtechmod.api.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_EmptyTool_Item extends GT_Tool_Item {
	public GT_EmptyTool_Item(Item aItem, String aUnlocalized, String aEnglish, int aMaxDamage, int aChargedGTID) {
		super(aItem, aUnlocalized, aEnglish, "Empty. You need to recharge it.", aMaxDamage, 0, false, aChargedGTID, -1);
		try {
			Class.forName("codechicken.nei.api.API");
			codechicken.nei.api.API.hideItem(new ItemStack(aItem));
		} catch(Throwable e) {/*Do nothing*/}
	}
	
	@Override
    public boolean hasContainerItem() {
        return false;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {
		//
    }
}