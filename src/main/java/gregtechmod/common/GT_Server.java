package gregtechmod.common;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class GT_Server extends GT_Proxy {
	public boolean isServerSide() {
		return true;
	}
	
	public boolean isClientSide() {
		return false;
	}
	
	public boolean isBukkitSide() {
		return false;
	}
	
	@Override
	public void doSonictronSound(ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		
	}
	
	@Override
	public int addArmor(String aPrefix) {
		return 0;
	}
}