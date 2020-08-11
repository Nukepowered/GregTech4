package gregtechmod.api.interfaces;

import net.minecraft.util.IIcon;

public interface IIconContainer {
	/**
	 * @return A regular Icon.
	 */
	public IIcon getIcon();
	
	/**
	 * @return Icon of the Overlay (or null if there is no Icon)
	 */
	public IIcon getOverlayIcon();
	
	/**
	 * @return X of the Overlay (0-15)
	 */
	public int getOverlayX();
	
	/**
	 * @return Y of the Overlay (0-15)
	 */
	public int getOverlayY();
	
	/**
	 * @return Width of the Overlay (1-16)
	 */
	public int getOverlayWidth();
	
	/**
	 * @return Height of the Overlay (1-16)
	 */
	public int getOverlayHeight();
}
