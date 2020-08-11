package gregtechmod.common;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class GT_Proxy {
	public static GT_TickHandler mTickHandler;
	public static GT_FuelHandler mFuelHandler;
	
	/**
	 * This means that Server specific Basefiles are definitely existing! Not if the World is actually server side or not!
	 */
	public abstract boolean isServerSide();

	/**
	 * This means that Client specific Basefiles are definitely existing! Not if the World is actually client side or not!
	 */
	public abstract boolean isClientSide();
	
	public abstract void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ);
	
	public abstract int addArmor(String aPrefix);
	
	public boolean registerRenderers() {
		return false;
	}
	
	public EntityPlayer getThePlayer() {
		return null;
	}
	
	static {
		mTickHandler = new GT_TickHandler();
		mFuelHandler = new GT_FuelHandler();
		FMLCommonHandler.instance().bus().register(mTickHandler);
	}
}