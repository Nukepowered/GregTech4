package gregtechmod.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import gregtechmod.api.util.InventoryHandler;
import gregtechmod.api.util.InventoryHandlerList;

/**
 * @author TheDarkDnKTv
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
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
	}
	
	@Test
	@Order(1)
	public void mergeTest() {
		System.out.println("Fisrt list: " + first);
		System.out.println("Second list: " + second);
		list = new InventoryHandlerList<>(first, second);
		System.out.print("New list: ");
		System.err.println(list);
		assertTrue("Size: " + list.size(), list.size() == 11);
	}
	
	@Test
	@Order(2)
	public void getTest() {
		Integer value = list.get(5);
		assertTrue("Value: " + value, value == 54);
	}
	
	@Test
	@Order(3)
	public void remove() {
		Integer a = list.remove(list.size() - 1);
		assertTrue(a == 35);
	}
	
	@Test
	@Order(4)
	public void emptyTest() {
		boolean value = list.isEmpty();
		list.clear();
		assertTrue(!value);
		System.out.println("Fisrt list after clean: " + first);
		System.out.println("Second list after clean: " + second);
		assertTrue("Not empty after cleaning", first.isEmpty() && second.isEmpty());
	}
}
