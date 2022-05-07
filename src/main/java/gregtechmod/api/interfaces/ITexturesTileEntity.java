package gregtechmod.api.interfaces;

import net.minecraft.util.IIcon;

public interface ITexturesTileEntity {

    int getTextureIndex(byte aSide, byte aMeta);

    IIcon getTextureIcon(byte aSide, byte aMeta);
}
