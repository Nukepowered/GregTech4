package gregtechmod.common.containers;

import com.google.gson.JsonObject;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.implementations.BaseMultiWorkable;
import gregtechmod.common.network.SyncedField;

import net.minecraft.entity.player.InventoryPlayer;

/** An base container class for all MTEs assignable from BaseMultiWorkable
 * @author TheDarkDnKTv
 *
 */
public abstract class MultiblockContainerBase extends GT_ContainerMetaTile_Machine {

	public SyncedField<Boolean> mMachine = new SyncedField<>("mMachine", Boolean.TRUE);
	
	public MultiblockContainerBase(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
		super(aInventoryPlayer, aTileEntity);
	}

    @Override
    public void prepareChanges(JsonObject data, boolean force) {
    	super.prepareChanges(data, force);
    	mMachine.updateAndWriteChanges(data, force, getMachine().isStructComplete());
    }
    
    @Override
    @SideOnly(Side.CLIENT)    
    public void processChanges(JsonObject data) {
    	super.processChanges(data);
    	mMachine.readChanges(data);
    }
	
	public BaseMultiWorkable getMachine() {
		return (BaseMultiWorkable) this.mTileEntity.getMetaTileEntity();
	}
}
