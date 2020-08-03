package gregtechmod.common;


public class GT_Bukkit extends GT_Server {
	public boolean isServerSide() {
		return true;
	}
	
	public boolean isClientSide() {
		return false;
	}
	
	public boolean isBukkitSide() {
		return true;
	}
}
