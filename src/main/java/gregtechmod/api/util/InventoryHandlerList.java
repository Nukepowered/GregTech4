package gregtechmod.api.util;

import java.util.AbstractList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

/**
 * @author TheDarkDnKTv
 * An multi inventory adapter used for multiblocks
 */
public class InventoryHandlerList<E> extends AbstractList<E> {
	
	protected final TIntObjectMap<List<E>> indexMapping;
	protected final Map<Integer, Integer> offsetMap;
	
	@SafeVarargs
	public InventoryHandlerList(List<E>... initial) {
		Objects.requireNonNull(initial);
		indexMapping = new TIntObjectHashMap<>();
		offsetMap = new HashMap<>();
		int offset = 0;
		for (List<E> list : initial) {
			if (list == null)
				throw new IllegalArgumentException("Input lists can not be null!");
			for (int i = offset; i < offset + list.size(); i++)
				indexMapping.put(i, list);
			offsetMap.put(System.identityHashCode(list), offset);
			offset += list.size();
		}
	}
	
	public InventoryHandlerList(List<List<E>> lists) {
		if (lists == null) throw new IllegalArgumentException();
		indexMapping = new TIntObjectHashMap<>();
		offsetMap = new HashMap<>();
		int offset = 0;
		for (List<E> list : lists) {
			if (list == null)
				throw new IllegalArgumentException("Input lists can not be null!");
			for (int i = offset; i < offset + list.size(); i++)
				indexMapping.put(i, list);
			offsetMap.put(System.identityHashCode(list), offset);
			offset += list.size();
		}
	}
	
	@Override
	public E get(int index) {
		rangeCheck(index);
		List<E> handler = indexMapping.get(index);
		return handler.get(getTrueIdx(index, handler));
	}

    @Override
    public E set(int index, E element) {
    	rangeCheck(index);
    	List<E> handler = indexMapping.get(index);
    	int realIdx = this.getTrueIdx(index, handler);
    	E old = handler.get(realIdx);
    	handler.set(realIdx, element);
    	return old;
    }
    
	@Override
	public E remove(int index) {
		rangeCheck(index);
		List<E> handler = indexMapping.get(index);
    	int realIdx = this.getTrueIdx(index, handler);
		E value = handler.remove(realIdx);
		return value;
	}
	
	@Override
	public void clear() {
		for (List<E> handler : indexMapping.valueCollection()) {
			handler.clear();
		}
	}
	
	@Override
    public boolean isEmpty() {
		boolean result = true;
		for (List<E> handler : indexMapping.valueCollection()) {
			result &= handler.isEmpty();
		}
		
		return result;
	}
	
	@Override
	public int size() {
		return indexMapping.size();
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
	
    protected void rangeCheck(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    protected String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+indexMapping.size();
    }
    
    protected int getTrueIdx(int index, List<E> handler) {
    	int offset = offsetMap.get(System.identityHashCode(handler));
    	return index - offset;
    }
}
