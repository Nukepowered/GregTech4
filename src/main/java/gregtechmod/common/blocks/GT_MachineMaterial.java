package gregtechmod.common.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class GT_MachineMaterial extends Material {
	public GT_MachineMaterial() {
		super(MapColor.ironColor);
		setRequiresTool();
		setImmovableMobility();
	}
	
    public boolean isOpaque() {
        return false;
    }
}