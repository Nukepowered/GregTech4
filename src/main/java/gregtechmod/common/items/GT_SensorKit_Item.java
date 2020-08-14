package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.items.GT_Generic_Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class GT_SensorKit_Item extends GT_Generic_Item {

	public GT_SensorKit_Item(String aName) {
		super(aName, null);
		setCreativeTab(GregTech_API.TAB_GREGTECH);
		setMaxStackSize(1);
	}
	
    protected ChunkCoordinates getTargetCoordinates(World world, int x, int y, int z, ItemStack stack) {
    	TileEntity tTileEntity = world.getTileEntity(x, y, z);
        if (tTileEntity != null && tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
            ChunkCoordinates coordinates = new ChunkCoordinates();
            coordinates.posX = x;
            coordinates.posY = y;
            coordinates.posZ = z;
            return coordinates;
        }
        return null;
    }
    
    private void setCoordinates(ItemStack aStack, int x, int y, int z) {
	    if (aStack != null) {
	        NBTTagCompound tNBT = aStack.getTagCompound();
	        if (tNBT == null) tNBT = new NBTTagCompound();
	        tNBT.setInteger("x", x);
	        tNBT.setInteger("y", y);
	        tNBT.setInteger("z", z);
	        aStack.setTagCompound(tNBT);
    	}
    }
    
    @Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float var8, float var9, float var10) {
    	if (aPlayer != null && aPlayer instanceof EntityPlayerMP) {
	        ChunkCoordinates position = getTargetCoordinates(aWorld, aX, aY, aZ, aStack);
	        if (position != null) {
	            ItemStack sensorLocationCard = new ItemStack(GregTech_API.sItemList[16], 1);
	            setCoordinates(sensorLocationCard, position.posX, position.posY, position.posZ);
	            aPlayer.inventory.mainInventory[aPlayer.inventory.currentItem] = sensorLocationCard;
	        }
        	return true;
        }
        return false;
    }
}