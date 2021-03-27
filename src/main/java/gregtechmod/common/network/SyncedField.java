package gregtechmod.common.network;

import java.util.Arrays;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import gregtechmod.api.util.GT_Log;

/** A wrap class of any <u>primitive</u> types such as int, String etc
 * to simplify sync between server and client
 * @author TheDarkDnKTv
 *
 */
public final class SyncedField<T> {
	
	public final static Gson GSON = new GsonBuilder()
			.disableHtmlEscaping()
			.serializeNulls()
			.create();
	
	public final String name;
	public final Class<T> clz; 
	
	private T objectValue = null;
	private  T memberValue = null;
	
	public SyncedField(String name, Class<T> clz) {
		this.name = Objects.requireNonNull(name);
		this.clz = clz;
	}
	
	@SuppressWarnings("unchecked")
	public SyncedField(String name, T initialValue) {
		this(name, (Class<T>)initialValue.getClass());
		this.memberValue = this.objectValue = initialValue;
	}
	
	public T get() {
		return memberValue;
	}
	
	public void set(T value) {
		memberValue = value;
	}
	
	/**
	 * Used to set new value and check for write
	 * @param data
	 * @param force
	 * @param value
	 */
	public void updateAndWriteChanges(JsonObject data, boolean force, T value) {
		this.memberValue = value;
		this.writeChanges(data, force);
	}
	
	/**
	 * Transiever side method, check for changes and writes it if state is different
	 * @param data an value to write
	 * @param force force update
	 */
	public void writeChanges(JsonObject data, boolean force) {
		try {
			if ((objectValue == null && memberValue != null) ||
					(objectValue != null && memberValue == null) ||
					!(clz.isArray() ? Arrays.equals((Object[])objectValue, (Object[])memberValue) : objectValue.equals(memberValue))
					|| force) {
				data.add(name, GSON.toJsonTree(memberValue));
				objectValue = memberValue;
			}
		} catch (Throwable e) {
			GT_Log.log.error("Exception occured while writing changing");
			GT_Log.log.catching(e);
		}
	}
	
	/**
	 * Reciever method, reads changes if present
	 * @param data value to read
	 */
	public void readChanges(JsonObject data) {
		try {
			if (data.has(name)) {
				memberValue = GSON.fromJson(data.get(name), clz);
			}
		} catch (Throwable e) {
			GT_Log.log.error("Exception occured while reading changing");
			GT_Log.log.catching(e);
		}
	}
}
