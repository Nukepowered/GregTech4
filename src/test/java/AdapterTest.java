import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import gregtechmod.api.util.ListAdapter;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

/**
 * @author TheDarkDnKTv
 *
 */
public class AdapterTest {
	
	
	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	public static ListAdapter<Integer> adapter;
	public static Integer[] values, sub;
	
	
	public AdapterTest() {
		//							0	1	2	3	4	5
		sub 	  = new Integer[] { 92, 52, 64, 61, 91, 86 };
		//							0	1	2	3	4	5	6	7	8	9
		values    = new Integer[] { 10, 72, 92, 52, 64, 61, 91, 86, 20, 48 };
		adapter   = new ListAdapter<>(values, 2, 7);
	}
	
	@Test
	public void subarray() {	 
		assertTrue("subarray not equal", Arrays.equals(sub, adapter.toArray()));
	}
	
	@Test
	public void get() {
		Integer a = adapter.get(3);
		assertTrue("Get returned wrong value: " + a, a.intValue() == 61);
	}
	
	@Test
	public void size() {
		assertTrue("Wrong size: " + adapter.size(), adapter.size() == sub.length);
	}
	
	@Test
	public void indexOf() {
		int idx = adapter.indexOf(64);
		assertTrue("Wrong index: " + idx, idx == 2);
	}
	
	@Test
	public void contains() {
		assertTrue("Not contains", adapter.contains(Integer.valueOf(52)));
	}
	
	@Test
	public void containsAll() {
		assertTrue("Not contains all", adapter.containsAll(Arrays.asList(sub)));
	}
	
	@Test
	public void iterator() {
		System.out.println("Iterator");
		Iterator<Integer> iter = adapter.iterator();
		while (iter.hasNext()) {
			System.err.print(iter.next() + " ");
		}
		
		System.out.println();
	}
	
	@Test
	public void set() {
		Integer old = adapter.set(5, 228);
		Integer n = adapter.get(5);
		assertTrue("Set returned wrong value: " + old, old.intValue() == 86);
		assertTrue("Get after set returned wrong value: " + n, n.intValue() == 228);
	}
	
	@Test
	public void remove() {
		Integer old = adapter.remove(2);
		assertTrue("Remove returned wrong number: " + old, old.intValue() == 64);
	}
	
	@Test
	public void clear() {
		System.out.print("Before clening: ");
		printArr(values);
		adapter.clear();
		System.out.print("After clening: ");
		printArr(values);
		assertTrue("Adapter not empty", adapter.isEmpty());
	}
	
	private <T> void printArr(T[] arr) {
		System.out.print('[');
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i != arr.length-1) System.out.print(", ");
		}
		System.out.println(']');
	}
}
