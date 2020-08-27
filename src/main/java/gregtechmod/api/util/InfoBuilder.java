package gregtechmod.api.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InfoBuilder {
	private Map<String, List<Object>> keySet = new LinkedHashMap<>();
	private String currentKey = null;
	private List<Object> currentList = null;
	
	private InfoBuilder() {}
	
	public static InfoBuilder create() {
		return new InfoBuilder();
	}
	
	public Map<String, List<Object>> build() {
		this.putKey();
		currentKey = null;
		currentList = null;
		return Collections.unmodifiableMap(keySet);
	}
	
	public InfoBuilder newKey(String key) {
		if (GT_Utility.isStringInvalid(key))
			throw new IllegalArgumentException("Key is invalid");
		this.putKey();
		currentKey = key;
		currentList = new ArrayList<>();
		return this;
	}
	
	public InfoBuilder newKey(String key, Object...values) {
		this.newKey(key);
		for (Object value : values) currentList.add(value);
		return this;
	}
	
	public InfoBuilder setKey(String key) {
		if (GT_Utility.isStringInvalid(key))
			throw new IllegalArgumentException("Key is invalid");
		this.putKey();
		if (!keySet.containsKey(key)) this.newKey(key);
		currentKey = key;
		currentList = keySet.get(key);
		return this;
	}
	
	public InfoBuilder addValue(Object obj) {
		if (GT_Utility.isStringInvalid(currentKey) || currentList == null) {
			throw new IllegalStateException("Can not add value until new key not created");
		}
		
		currentList.add(obj);
		return this;
	}
	
	private void putKey() {
		if (GT_Utility.isStringValid(currentKey) && currentList != null) {
			keySet.put(currentKey, currentList);
		}
	}
}
