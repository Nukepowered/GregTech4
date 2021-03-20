package gregtechmod.api.interfaces;

import net.minecraft.item.ItemStack;

/**
 * Implementing this interface will mark item as IC2 reactor component which <u>stores</u> heat
 * Used to fix IC2 through ASM to not allow put back warm cells
 * @author TheDarkDnKTv
 * 
 */
public interface IHeatComponent {
	
	/**
	 * @return amount of heat stored in stack
	 */
	int getHeatStored(ItemStack stack);
}
