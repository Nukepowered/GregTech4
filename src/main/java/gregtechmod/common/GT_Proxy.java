package gregtechmod.common;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class GT_Proxy {
	public static GT_TickHandler mServerTickHandler = new GT_TickHandler(true);
	public static GT_FuelHandler mFuelHandler = new GT_FuelHandler();
	
	/**
	 * This means that Server specific Basefiles are definitely existing! Not if the World is actually server side or not!
	 */
	public abstract boolean isServerSide();

	/**
	 * This means that Client specific Basefiles are definitely existing! Not if the World is actually client side or not!
	 */
	public abstract boolean isClientSide();
	
	/**
	 * This means that Bukkit specific Basefiles are definitely existing! Not if the World is actually bukkit server or not!
	 */
	public abstract boolean isBukkitSide();
	
	public abstract void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ);
	
	public abstract int addArmor(String aPrefix);
}