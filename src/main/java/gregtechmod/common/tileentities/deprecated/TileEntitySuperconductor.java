package gregtechmod.common.tileentities.deprecated;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ITexturesTileEntity;
import gregtechmod.api.metatileentity.BaseTileEntity;
import gregtechmod.api.util.GT_ModHandler;

import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.tile.IWrenchable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySuperconductor extends BaseTileEntity implements IEnergyConductor, ITexturesTileEntity, IWrenchable {

    private boolean addedToEnet = false;

    @Override public long getTimer() { return 0; }
    @Override public boolean canUpdate()
    {
        return false;
    } // no need to tick
    @Override public void setLightValue(byte aLightValue) {}
    @Override public boolean isInvalidTileEntity() { return isInvalid(); }
    @Override public IIcon getTextureIcon(byte aSide, byte aMeta) { return null; }
    @Override public double getConductionLoss() { return 0.0D; }
    @Override public double getInsulationEnergyAbsorption() { return GregTech_API.VOLTAGE_INSANE; }
    @Override public double getInsulationBreakdownEnergy() { return Integer.MAX_VALUE; }
    @Override public double getConductorBreakdownEnergy() { return Integer.MAX_VALUE; }
    @Override public void removeInsulation() { doExplosion(); }
    @Override public void removeConductor() { doExplosion(); }
    @Override public boolean acceptsEnergyFrom(TileEntity tileEntity, ForgeDirection forgeDirection) { return true; }
    @Override public boolean emitsEnergyTo(TileEntity tileEntity, ForgeDirection forgeDirection) { return true; }
    @Override public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int i) { return false; }
    @Override public short getFacing() { return 0; }
    @Override public void setFacing(short i) {}
    @Override public boolean wrenchCanRemove(EntityPlayer entityPlayer) { return true; }
    @Override public float getWrenchDropRate() { return 1.0F; }

    private void doExplosion() {
        worldObj.setBlock(xCoord, yCoord, yCoord, Blocks.air);
        worldObj.createExplosion(null, xCoord+0.5, yCoord+0.5, yCoord+0.5, 8.0F, true);
    }

    public boolean isAddedToEnergyNet() {
        return this.addedToEnet;
    }

    @Override
    public int getTextureIndex(byte aSide, byte aMeta) {
        if (xCoord == 0 && yCoord == 0 && zCoord == 0) return 103;

        boolean[] tConnectedSides = {
            this.getTileEntityAtSide((byte)0) instanceof IEnergyTile,
            this.getTileEntityAtSide((byte)1) instanceof IEnergyTile,
            this.getTileEntityAtSide((byte)5) instanceof IEnergyTile,
            this.getTileEntityAtSide((byte)3) instanceof IEnergyTile,
            this.getTileEntityAtSide((byte)4) instanceof IEnergyTile,
            this.getTileEntityAtSide((byte)2) instanceof IEnergyTile
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
    public void invalidate() {
        super.invalidate();
        this.onRemoved();
    }

    @Override
    public void validate() {
        super.validate();
        this.onPlaced();
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        this.onRemoved();
    }

    @Override
    public void onPlaced() {
        if (!this.worldObj.isRemote && !this.addedToEnet) {
            this.addedToEnet = GT_ModHandler.addTileToEnet(this.worldObj, this);
        }
    }

    @Override
    public void onRemoved() {
        if (!this.worldObj.isRemote && this.addedToEnet) {
            GT_ModHandler.removeTileFromEnet(this.worldObj, this);
        }
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(GregTech_API.sBlockList[1], 1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
    }
}
