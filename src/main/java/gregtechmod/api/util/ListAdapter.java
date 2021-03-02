package gregtechmod.api.util;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Objects;
import java.util.RandomAccess;

/**
 * An array to list adapter with range controll function
 * This adapter is used to go through array contents or replace it, not to extend and etc.
 * 
 * @author TheDarkDnKTv
 */
public class ListAdapter<E> extends AbstractList<E> implements RandomAccess {
	protected E[] data;
	private int idxStart;
	private int idxEnd;
	
	public ListAdapter(E[] items) {
		this(items, 0, items.length-1);
	}
	
	public ListAdapter(E[] items, int fromIdx, int toIdx) {
		Objects.requireNonNull(items);
		assert fromIdx <= toIdx : "Index FROM can not be greater than index TO";
		this.data = items;
		this.idxStart = fromIdx;
		this.idxEnd = toIdx;
	}
	
    protected void rangeCheck(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+data.length;
    }
	
    private int getIdxWithOffset(int index) {
    	return index + idxStart;
    }
    
    @Override
    public E set(int index, E element) {
    	rangeCheck(index);
    	E old = data[getIdxWithOffset(index)];
    	data[getIdxWithOffset(index)] = element;
    	return old;
    }
    
	@Override
	public E get(int index) {
		rangeCheck(index);
		return data[getIdxWithOffset(index)];
	}

	@Override
	public int size() {
		return idxEnd - idxStart + 1;
	}
	
	@Override
	public E remove(int index) {
		rangeCheck(index);
		E value = data[getIdxWithOffset(index)];
		data[getIdxWithOffset(index)] = null;
		return value;
	}
	
	@Override
	public void clear() {
		for (int i = idxStart; i <= idxEnd; i++) {
			data[i] = null;
		}
	}
	
	@Override
    public boolean isEmpty() {
		for (int i = idxStart; i <= idxEnd; i++) {
			if (data[i] != null) return false;
		}
		
		return true;
	}
	
	@Override
	public boolean add(E item) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void add(int index, E item) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean addAll(Collection<? extends E> vals) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> vals) {
		throw new UnsupportedOperationException();
	}
}
