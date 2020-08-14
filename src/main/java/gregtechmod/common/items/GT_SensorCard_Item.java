package gregtechmod.common.items;

import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.items.GT_Generic_Item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import shedar.mods.ic2.nuclearcontrol.api.CardState;
import shedar.mods.ic2.nuclearcontrol.api.ICardWrapper;
import shedar.mods.ic2.nuclearcontrol.api.IPanelDataSource;
import shedar.mods.ic2.nuclearcontrol.api.IRemoteSensor;
import shedar.mods.ic2.nuclearcontrol.api.PanelSetting;
import shedar.mods.ic2.nuclearcontrol.api.PanelString;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GT_SensorCard_Item extends GT_Generic_Item implements IRemoteSensor, IPanelDataSource {
	
    public static final UUID CARD_TYPE = new UUID(0, 41);
    
	public GT_SensorCard_Item(String aName) {
		super(aName, "Insert into Display Panel"); // TODO localiztion
		setMaxStackSize(1);
	}
	
	private ChunkCoordinates getCoordinates(ItemStack aStack) {
		if (aStack != null) {
	        NBTTagCompound tNBT = aStack.getTagCompound();
	        if (tNBT != null) return new ChunkCoordinates(tNBT.getInteger("x"), tNBT.getInteger("y"), tNBT.getInteger("z"));
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addAdditionalToolTips(List aList, ItemStack aStack) {
		super.addAdditionalToolTips(aList, aStack);
        ChunkCoordinates target = getCoordinates(aStack);
        if (target == null) {
    		aList.add("Missing Coodinates!");
        } else {
    		aList.add("Device at:");
            aList.add(String.format("x: %d, y: %d, z: %d", target.posX, target.posY, target.posZ));
        }
    }
	
	@Override
	public CardState update(TileEntity panel, ICardWrapper card, int maxRange) {
        return this.update(panel.getWorldObj(), card, maxRange);
	}
	
	@Override
	public CardState update(World world, ICardWrapper aCard, int maxRange) {
		ChunkCoordinates target = aCard.getTarget();
		TileEntity tTileEntity = world.getTileEntity(target.posX, target.posY, target.posZ);
		if (tTileEntity != null && tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation) tTileEntity).isGivingInformation()) {
			String[] tInfoData = ((IGregTechDeviceInformation) tTileEntity).getInfoData();

			for (int i = 0; i < tInfoData.length; ++i) {
				aCard.setString("mString" + i, tInfoData[i]);
			}

			return CardState.OK;
		} else {
			return CardState.NO_TARGET;
		}
	}
	
	@Override
	public List<PanelString> getStringData(int displaySettings, ICardWrapper aCard, boolean showLabels) {
        LinkedList<PanelString> rList = new LinkedList<>();

        for(int i = 0; i < 8; ++i) {
           if((displaySettings & 1 << i) != 0) {
              PanelString line = new PanelString();
              line.textLeft = I18n.format(aCard.getString("mString" + i));
              rList.add(line);
           }
        }

        return rList;
        
//        	line.textLeft = StringUtils.getFormatted("sensor.eut", card.getString("mString3"), showLabels);
	}
	
	@Override
    public List<PanelSetting> getSettingsList() {
	      ArrayList<PanelSetting> rList = new ArrayList<>(30);

	      for(int i = 0; i < 8; ++i) {
	         rList.add(new PanelSetting("" + (i + 1), 1 << i, this.getCardType()));
	      }

	      return rList;
    }
	
	@Override
	public UUID getCardType() {
		return CARD_TYPE;
	}
	
	@SuppressWarnings("rawtypes") @Override @SideOnly(Side.CLIENT) public void getSubItems(Item var1, CreativeTabs aTab, List aList) {}
}
