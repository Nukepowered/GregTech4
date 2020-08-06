package gregtechmod.common.tileentities;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class GT_TileEntity_LightSource extends TileEntity {
	private int mTickTimer = 0;
	
    public GT_TileEntity_LightSource() {}
    
	@Override
	public void updateEntity() {
    	if (worldObj.isRemote) return;
    	@SuppressWarnings("unchecked")
    	Iterator<EntityPlayer> tIterator = worldObj.playerEntities.iterator();
    	
    	boolean temp = true;
    	
    	if (++mTickTimer%20==0) {
    		while (tIterator.hasNext() && temp) {
    			EntityPlayer tPlayer = tIterator.next();
    			if (MathHelper.floor_double(tPlayer.posX  )==xCoord&&
    				MathHelper.floor_double(tPlayer.posY+1)==yCoord&&
    				MathHelper.floor_double(tPlayer.posZ  )==zCoord)
    				temp = false;
    		}
    		if (temp) worldObj.setBlockToAir(xCoord, yCoord, zCoord);
    	}
    }
}
