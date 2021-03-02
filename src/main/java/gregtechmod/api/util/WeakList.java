package gregtechmod.api.util;

import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.RandomAccess;


/** Simple wrapper of Weak References & ArrayList
 * 
 * @author TheDarkDnKTv
 * 
 * @see {@link WeakReference}
 * @see {@link ArrayList}
 */
public class WeakList<E> extends AbstractList<E> implements RandomAccess, Cloneable {
	
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	private static final int DEFAULT_CAPACITY = 10;
	
	private transient WeakReference<E>[] elementData;
	private int size = 0;
	
	public WeakList() {
		elementData = newArray(DEFAULT_CAPACITY);
	}
	
	public WeakList(int initialCapacity) {
		if (initialCapacity < 0 || initialCapacity > MAX_ARRAY_SIZE)
			throw new IllegalArgumentException();
		elementData = newArray(DEFAULT_CAPACITY);
	}
	
	public WeakList(Collection<? extends E> c) {
		elementData = newArray(c.size() + 1);
		int idx = 0;
		Iterator<? extends E> iter = c.iterator();
		while (iter.hasNext())
			elementData[idx++] = new WeakReference<E>(iter.next());
	}
	
	@Override
	public E get(int idx) {
		rangeCheck(idx);
		WeakReference<E> ref = elementData[idx];
		return ref == null ? null : ref.get();
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void add(int index, E element) {
		if (index < 0)
			throw new IllegalArgumentException("Index: " + index);
		ensureCapacityInternal(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = new WeakReference<>(element);
		size++;
	}
	
	@Override
	public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData[index] == null ? null : elementData[index].get();

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        elementData[--size] = null;

        return oldValue;
	}
	
	@Override
    public void clear() {
        modCount++;

        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }
	
	/**
	 * Same as {@link WeakList#get()} but giving an otional
	 */
	public Optional<E> getSecure(int idx) {
		return Optional.ofNullable(this.get(idx));
	}
	
	/**
	 * Redirect of {@link WeakReference#isEnqueued()}
	 */
	public boolean isEnqueued(int idx) {
		rangeCheck(idx);
		WeakReference<E> ref = elementData[idx];
		return ref == null ? false : ref.isEnqueued();
	}
	
	/**
	 * Redirect of {@link WeakReference#clear()}
	 */
	public void clear(int idx) {
		rangeCheck(idx);
		WeakReference<E> ref = elementData[idx];
		if (ref != null) ref.clear();
	}
	
	/**
	 * Redirect of {@link WeakReference#enqueue()}
	 */
	public boolean enqueue(int idx) {
		rangeCheck(idx);
		WeakReference<E> ref = elementData[idx];
		return ref == null ? false : ref.enqueue();
	}

	private void rangeCheck(int idx) {
		if (idx < 0 || idx >= elementData.length) {
			throw new ArrayIndexOutOfBoundsException("Index: " + idx + ", Size: " + size);
		}
	}
	
	@SuppressWarnings("unchecked")
	private WeakReference<E>[] newArray(int size) {
		return (WeakReference<E>[]) new WeakReference[size];
	}
	
	private void ensureCapacityInternal(int minCapacity) {
		modCount++;
		
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
	}
	
	private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
	}
	
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0)
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
}
