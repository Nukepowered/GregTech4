package gregtechmod.api.util;

import java.util.Collection;

/**
 * @author TheDarkDnKTv
 * Class used for inventory handlers of any type with fixed lenght
 */
public class InventoryHandler<T> extends ListAdapter<T> {
	
	@SuppressWarnings("unchecked")
	public InventoryHandler(int capacity) {
		super((T[]) new Object[capacity]);
	}
	
	/**
	 * Will add an element in next empty slot, otherwise will not
	 */
	@Override
	public boolean add(T element) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == null) {
				data[i] = element;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Will work as method set, as growing is not allowed in this impl
	 */
	@Override
	public void add(int index, T item) {
		this.set(index, item);
	}
	
	/**
	 * Will add all stuff from fisrt idx, if there is nulls
	 */
	@Override
	public boolean addAll(Collection<? extends T> vals) {
		return this.addAll(0, vals);
	}
	
	/**
	 * Will add all stuff from index, if there is nulls
	 */
	@Override
	public boolean addAll(int index, Collection<? extends T> vals) {
		rangeCheck(index);
		if (vals.size() <= countNulls(index)) {
			start:
			for (T t : vals) {
				for (int idx = index; idx < data.length; idx++) {
					if (data[idx] == null) {
						data[idx] = t;
						continue start;
					}
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public int size() {
		return data.length;
	}
	
	private int countNulls(int idxFrom) {
		int nulls = 0;
		for (int idx = idxFrom; idx < data.length; idx++)
			if (data[idx] == null) nulls++;
		return nulls;
	}
}
