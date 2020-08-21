package gregtechmod.api.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class GT_Config {
	public static volatile int VERSION = 408;
	
	public static boolean system = false;
	
	public static Configuration sConfigFileIDs;
	
	public static int addIDConfig(Object aCategory, String aName, int aDefault) {
		if (GT_Utility.isStringInvalid(aName)) return aDefault;
		Property tProperty = sConfigFileIDs.get(aCategory.toString().replaceAll("\\|", "."), aName.replaceAll("\\|", "."), aDefault);
		int rResult = tProperty.getInt(aDefault);
		if (!tProperty.wasRead()) sConfigFileIDs.save();
		return rResult;
	}
	
	public final Configuration mConfig;
	
	public GT_Config(Configuration aConfig) {
		mConfig = aConfig;
		mConfig.load();
		mConfig.save();
	}
	
	public static String getStackConfigName(ItemStack aStack) {
		if (GT_Utility.isStackInvalid(aStack)) return "";
		String rName;
		if (GT_Utility.isStringValid(rName = GT_OreDictUnificator.getAssociation(aStack))) return rName;
		try {if (GT_Utility.isStringValid(rName = aStack.getUnlocalizedName())) return rName;} catch (Throwable e) {/*Do nothing*/}
		return aStack.getItem() + "." + aStack.getItemDamage();
	}
	
	public boolean get(Object aCategory, ItemStack aStack, boolean aDefault) {
		return get(aCategory, getStackConfigName(aStack), aDefault);
	}
	
	public boolean get(Object aCategory, String aName, boolean aDefault) {
		if (GT_Utility.isStringInvalid(aName)) return aDefault;
		Property tProperty = mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName+"_"+aDefault).replaceAll("\\|", "_"), aDefault);
		boolean rResult = tProperty.getBoolean(aDefault);
		if (!tProperty.wasRead()) mConfig.save();
		return rResult;
	}
	
	public int get(Object aCategory, ItemStack aStack, int aDefault) {
		return get(aCategory, getStackConfigName(aStack), aDefault);
	}
	
	public int get(Object aCategory, String aName, int aDefault) {
		if (GT_Utility.isStringInvalid(aName)) return aDefault;
		Property tProperty = mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName+"_"+aDefault).replaceAll("\\|", "_"), aDefault);
		int rResult = tProperty.getInt(aDefault);
		if (!tProperty.wasRead()) mConfig.save();
		return rResult;
	}
	
	public double get(Object aCategory, ItemStack aStack, double aDefault) {
		return get(aCategory, getStackConfigName(aStack), aDefault);
	}
	
	public double get(Object aCategory, String aName, double aDefault) {
		if (GT_Utility.isStringInvalid(aName)) return aDefault;
		Property tProperty = mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName+"_"+aDefault).replaceAll("\\|", "_"), aDefault);
		double rResult = tProperty.getDouble(aDefault);
		if (!tProperty.wasRead()) mConfig.save();
		return rResult;
	}
	
	public static class JsonWrapper {
		private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		
		private JsonObject object;
		private final File config;
		
		public JsonWrapper(File config) {
			this.config = Objects.requireNonNull(config);
			this.load();
		}
		
		public boolean get(Object category, String keyName, boolean defaultValue) {
			return this.get(category, keyName, gson.toJsonTree(defaultValue)).getAsBoolean();
		}
		
		public boolean get(Object category, ItemStack keyItem, boolean defaultValue) {
			return this.get(category, getStackConfigName(keyItem), defaultValue);
		}
		
		public int get(Object category, String keyName, int defaultValue) {
			return this.get(category, keyName, gson.toJsonTree(defaultValue)).getAsInt();
		}
		
		public int get(Object category, ItemStack keyItem, int defaultValue) {
			return this.get(category, getStackConfigName(keyItem), gson.toJsonTree(defaultValue)).getAsInt();
		}
		
		public double get(Object category, String keyName, double defaultValue) {
			return this.get(category, keyName, gson.toJsonTree(defaultValue)).getAsDouble();
		}
		
		public double get(Object category, ItemStack keyItem, double defaultValue) {
			return this.get(category, getStackConfigName(keyItem), gson.toJsonTree(defaultValue)).getAsDouble();
		}
		
		public JsonElement get(Object category, String keyName, JsonElement defaultValue) {
			JsonObject catObj = null;
			if (object.has(category.toString()) && (catObj = object.get(category.toString()).getAsJsonObject()).has(keyName)) {
				return catObj.get(keyName);
			} else {
				catObj = catObj == null ? new JsonObject() : catObj;
				catObj.add(keyName, defaultValue);
				object.add(category.toString(), catObj);
			}
			
			return defaultValue;
		}
		
		public void load() {
			try {
				if (config.exists()) {
					try (FileReader reader = new FileReader(config)) {
						JsonElement confFile = new JsonParser().parse(reader);
						if (confFile.isJsonObject()) {
							this.object = confFile.getAsJsonObject();
						} else {
							Files.delete(config.toPath());
							this.load();
						}
					} catch (IOException e) {}
				} else {
					if (config.createNewFile()) {
						FileWriter writer = new FileWriter(config);
						this.object = new JsonObject();
						gson.toJson(this.object, writer);
					} else throw new IllegalStateException("Cannot create config file!");
				}
			} catch (Throwable e) {
				RuntimeException t = new IllegalStateException("Unable to load config", e);
				GT_Log.log.throwing(t);
				throw t;
			}
		}
		
		public void save() {
			try (FileWriter writer = new FileWriter(config)) {
				gson.toJson(this.object, writer);
			} catch (Throwable e) {
				RuntimeException t = new IllegalStateException("Unable to save config file!", e);
				GT_Log.log.throwing(t);
				throw t;
			}
		}
	}
}