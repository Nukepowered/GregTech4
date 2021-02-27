package gregtechmod.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import gregtechmod.api.util.InventoryHandler;
import gregtechmod.api.util.InventoryHandlerList;

/**
 * @author TheDarkDnKTv
 *
 */
public class MultipleAdapterTest {

	InventoryHandler<Integer> first;
	InventoryHandler<Integer> second;
	InventoryHandlerList<Integer> list;
	
	public MultipleAdapterTest() {
		first = new InventoryHandler<>(5);
		second = new InventoryHandler<>(6);
		
		first.set(0, 15);
		first.set(2, 53);
		second.set(0, 54);
		second.set(1, 94);
		second.set(3, 47);
		second.set(5, 35);

		list = new InventoryHandlerList<>(first, second);
	}
	
	@Test
	public void mergeTest() {
		System.err.println(first);
		System.err.println(second);
		System.err.println(list);
		assertTrue("Size: " + list.size(), list.size() == 11);
	}
	
	@Test
	public void getTest() {
		Integer value = list.get(5);
		assertTrue("Value: " + value, value == 54);
	}
}
