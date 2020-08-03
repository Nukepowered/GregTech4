package gregtechmod.common.tileentities;

import gregtechmod.api.util.GT_LanguageManager;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.entity.player.EntityPlayer;

public class GT_TileEntity_Superconductor extends GT_TileEntityMetaID_Machine implements IEnergyConductor {
	
    public boolean isFacingValid(int aFacing) 			{return false;}
    public boolean isAccessible(EntityPlayer aPlayer)	{return false;}
    public boolean isEnetOutput()      					{return true;}
    public boolean isEnetInput()       					{return true;}
    public boolean isOutputFacing(short aDirection) 	{return true;}
    public boolean isInputFacing(short aDirection)  	{return true;}
    public int maxEUInput()            					{return Integer.MAX_VALUE;}
    public int maxEUOutput()           					{return Integer.MAX_VALUE;}
    
    @Override public String getInvName() {return GT_LanguageManager.mNameList1[12];}

	@Override
	public float getWrenchDropRate() {
		return 1.0F;
	}
	
    @Override
    public int getTexture(int aSide, int aMeta) {
    	if (xCoord == 0 && yCoord == 0 && zCoord == 0) return 103;
    	
    	boolean[] tConnectedSides = {
        	worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyTile,
        	worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyTile,
    		worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord) instanceof IEnergyTile,
    		worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1) instanceof IEnergyTile,
    		worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyTile,
    		worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyTile
    	};
    	
    	switch (aSide) {
    	case 0:
    		if (tConnectedSides[0]) return 103;
    		
    		if (tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return 102;

    		if (!tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return 98;
    		if (tConnectedSides[4]&&!tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return 99;
    		if (tConnectedSides[4]&&tConnectedSides[5]&&!tConnectedSides[2]&&tConnectedSides[3]) return 100;
    		if (tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&!tConnectedSides[3]) return 101;

    		if (!tConnectedSides[4]&&!tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return 104;
    		if (tConnectedSides[4]&&!tConnectedSides[5]&&!tConnectedSides[2]&&tConnectedSides[3]) return 105;
    		if (tConnectedSides[4]&&tConnectedSides[5]&&!tConnectedSides[2]&&!tConnectedSides[3]) return 106;
    		if (!tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&!tConnectedSides[3]) return 107;
    		
    		if (!tConnectedSides[4]&&!tConnectedSides[5]&&!tConnectedSides[2]&&!tConnectedSides[3]) return 103;
    		
    		if (!tConnectedSides[4]&&!tConnectedSides[2]) return 97;
    		if (!tConnectedSides[5]&&!tConnectedSides[3]) return 96;
    		
    	case 1:
    		if (tConnectedSides[1]) return 103;
    		
    		if (tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return 102;

    		if (!tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return 98;
    		if (tConnectedSides[4]&&!tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return 99;
    		if (tConnectedSides[4]&&tConnectedSides[5]&&!tConnectedSides[2]&&tConnectedSides[3]) return 100;
    		if (tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&!tConnectedSides[3]) return 101;
    		
    		if (!tConnectedSides[4]&&!tConnectedSides[5]&&tConnectedSides[2]&&tConnectedSides[3]) return 104;
    		if (tConnectedSides[4]&&!tConnectedSides[5]&&!tConnectedSides[2]&&tConnectedSides[3]) return 105;
    		if (tConnectedSides[4]&&tConnectedSides[5]&&!tConnectedSides[2]&&!tConnectedSides[3]) return 106;
    		if (!tConnectedSides[4]&&tConnectedSides[5]&&tConnectedSides[2]&&!tConnectedSides[3]) return 107;
    		
    		if (!tConnectedSides[4]&&!tConnectedSides[5]&&!tConnectedSides[2]&&!tConnectedSides[3]) return 103;
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[4]) return 97;
    		if (!tConnectedSides[3]&&!tConnectedSides[5]) return 96;
    	case 2:
    		if (tConnectedSides[5]) return 103;
    		if (tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return 102;

    		if (!tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return 98;
    		if (tConnectedSides[2]&&!tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return 101;
    		if (tConnectedSides[2]&&tConnectedSides[0]&&!tConnectedSides[4]&&tConnectedSides[1]) return 100;
    		if (tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&!tConnectedSides[1]) return 99;
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return 107;
    		if (tConnectedSides[2]&&!tConnectedSides[0]&&!tConnectedSides[4]&&tConnectedSides[1]) return 106;
    		if (tConnectedSides[2]&&tConnectedSides[0]&&!tConnectedSides[4]&&!tConnectedSides[1]) return 105;
    		if (!tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&!tConnectedSides[1]) return 104;
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[0]&&!tConnectedSides[4]&&!tConnectedSides[1]) return 103;
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[4]) return 97;
    		if (!tConnectedSides[0]&&!tConnectedSides[1]) return 96;
    	case 3:
    		if (tConnectedSides[3]) return 103;
    		if (tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return 102;

    		if (!tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return 100;
    		if (tConnectedSides[2]&&!tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return 101;
    		if (tConnectedSides[2]&&tConnectedSides[0]&&!tConnectedSides[4]&&tConnectedSides[1]) return 98;
    		if (tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&!tConnectedSides[1]) return 99;
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[0]&&tConnectedSides[4]&&tConnectedSides[1]) return 106;
    		if (tConnectedSides[2]&&!tConnectedSides[0]&&!tConnectedSides[4]&&tConnectedSides[1]) return 107;
    		if (tConnectedSides[2]&&tConnectedSides[0]&&!tConnectedSides[4]&&!tConnectedSides[1]) return 104;
    		if (!tConnectedSides[2]&&tConnectedSides[0]&&tConnectedSides[4]&&!tConnectedSides[1]) return 105;
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[0]&&!tConnectedSides[4]&&!tConnectedSides[1]) return 103;
    		
    		if (!tConnectedSides[2]&&!tConnectedSides[4]) return 97;
    		if (!tConnectedSides[0]&&!tConnectedSides[1]) return 96;
    	case 4:
    		if (tConnectedSides[4]) return 103;
    		if (tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return 102;

    		if (!tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return 101;
    		if (tConnectedSides[0]&&!tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return 100;
    		if (tConnectedSides[0]&&tConnectedSides[3]&&!tConnectedSides[1]&&tConnectedSides[5]) return 99;
    		if (tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&!tConnectedSides[5]) return 98;
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return 106;
    		if (tConnectedSides[0]&&!tConnectedSides[3]&&!tConnectedSides[1]&&tConnectedSides[5]) return 105;
    		if (tConnectedSides[0]&&tConnectedSides[3]&&!tConnectedSides[1]&&!tConnectedSides[5]) return 104;
    		if (!tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&!tConnectedSides[5]) return 107;
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[3]&&!tConnectedSides[1]&&!tConnectedSides[5]) return 103;
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[1]) return 96;
    		if (!tConnectedSides[3]&&!tConnectedSides[5]) return 97;
    	case 5:
    		if (tConnectedSides[2]) return 103;
    		if (tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return 102;

    		if (!tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return 101;
    		if (tConnectedSides[0]&&!tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return 98;
    		if (tConnectedSides[0]&&tConnectedSides[3]&&!tConnectedSides[1]&&tConnectedSides[5]) return 99;
    		if (tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&!tConnectedSides[5]) return 100;
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[3]&&tConnectedSides[1]&&tConnectedSides[5]) return 107;
    		if (tConnectedSides[0]&&!tConnectedSides[3]&&!tConnectedSides[1]&&tConnectedSides[5]) return 104;
    		if (tConnectedSides[0]&&tConnectedSides[3]&&!tConnectedSides[1]&&!tConnectedSides[5]) return 105;
    		if (!tConnectedSides[0]&&tConnectedSides[3]&&tConnectedSides[1]&&!tConnectedSides[5]) return 106;
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[3]&&!tConnectedSides[1]&&!tConnectedSides[5]) return 103;
    		
    		if (!tConnectedSides[0]&&!tConnectedSides[1]) return 96;
    		if (!tConnectedSides[3]&&!tConnectedSides[5]) return 97;
    	default: return 103;
    	}
    }
    
	@Override
	public double getConductionLoss() {
		return 0.0D;
	}
	@Override
	public int getInsulationEnergyAbsorption() {
		return 8192;
	}
	@Override
	public int getInsulationBreakdownEnergy() {
		return Integer.MAX_VALUE;
	}
	@Override
	public int getConductorBreakdownEnergy() {
		return Integer.MAX_VALUE;
	}
	@Override
	public void removeInsulation() {
		doExplosion(10000);
	}
	@Override
	public void removeConductor() {
		doExplosion(10000);
	}
	@Override
	public void doEnergyExplosion() {}
}
