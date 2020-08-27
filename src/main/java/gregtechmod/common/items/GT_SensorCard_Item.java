package gregtechmod.common.items;

import gregtechmod.api.interfaces.IGregTechDeviceInformation;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

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
		super(aName, "item.GT_SensorCard_Item.tooltip");
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
    		aList.add(I18n.format("item.GT_SensorCard_Item.tooltip.info.missing"));
        } else {
    		aList.add(I18n.format("item.GT_SensorCard_Item.tooltip.info.1"));
            aList.add(I18n.format("item.GT_SensorCard_Item.tooltip.info.2", target.posX, target.posY, target.posZ));
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
			Map<String, List<Object>> tInfoData = ((IGregTechDeviceInformation) tTileEntity).getInfoData();
			
			if (tInfoData != null && !tInfoData.isEmpty()) {
				JsonObject data = new JsonObject();
				for (Entry<String, List<Object>> entry : tInfoData.entrySet()) {
					JsonArray values = new JsonArray();
					for (Object obj : entry.getValue()) {
						JsonElement value = null;
						if (obj instanceof Number) {
							Number num = (Number) obj;
							num = num instanceof Double && ((Double) num).isNaN() ? 0 : num;
							value = new JsonPrimitive(num);
						} else if (obj instanceof Item) {
							value = new JsonPrimitive(((Item) obj).getUnlocalizedName());
						} else if (obj instanceof ItemStack) {
							ItemStack itemStack = (ItemStack) obj;
							JsonObject stack = new JsonObject();
							stack.add("amount", new JsonPrimitive(itemStack.stackSize));
							stack.add("damage", new JsonPrimitive(itemStack.getItemDamage()));
							stack.add("itemID", new JsonPrimitive(Item.getIdFromItem(itemStack.getItem())));
							value = stack;
						} else {
							value = new JsonPrimitive(obj.toString());
						}
						
						if (value != null) values.add(value);
					}
					data.add(entry.getKey(), values);
				}
				
				String jsonData = new Gson().toJson(data);
				aCard.setString("aData", jsonData);
			}

			return CardState.OK;
		} else {
			return CardState.NO_TARGET;
		}
	}
	
	@Override
	public List<PanelString> getStringData(int displaySettings, ICardWrapper aCard, boolean showLabels) {
        List<PanelString> rList = new ArrayList<>();
        String jsonData = aCard.getString("aData");
		JsonObject data = new JsonParser().parse(jsonData).getAsJsonObject();
        
		int i = 0;
        for (Entry<String, JsonElement> entry : data.entrySet()) {
        	PanelString str = new PanelString();
        	List<Object> format = new LinkedList<>();
        	for (JsonElement value : entry.getValue().getAsJsonArray()) {
        		if (value.isJsonObject()) {
        			JsonObject stackData = value.getAsJsonObject();
        			ItemStack stack = new ItemStack(Item.getItemById(stackData.get("itemID").getAsInt()),
        					stackData.get("amount").getAsInt(),
        					stackData.get("damage").getAsInt());
        			if (GT_Utility.isStackValid(stack)) {
        				format.add(stack.getDisplayName());
        			}
        		} else if (value.isJsonPrimitive()) {
        			JsonPrimitive val = value.getAsJsonPrimitive();
        			if (val.isString()) {
        				format.add(I18n.format(val.getAsString()));
        			} else if (val.isNumber()) {
        				format.add(new DecimalFormat("#.##").format(val.getAsNumber()));
        			}
        		}
        	}
        	
        	if (showLabels) {
        		str.textLeft = I18n.format(entry.getKey(), format.toArray());
        	} else {
        		str.textLeft = "";
        		format.forEach(val -> str.textLeft += " " + val.toString());
        	}
        	
        	if ((displaySettings & 1 << i) != 0) rList.add(str);
        	i++;
        }
        
        return rList;
	}
	
	@Override
    public List<PanelSetting> getSettingsList() {
	      ArrayList<PanelSetting> rList = new ArrayList<>(30);

	      for(int i = 0; i < 6; ++i) {
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
