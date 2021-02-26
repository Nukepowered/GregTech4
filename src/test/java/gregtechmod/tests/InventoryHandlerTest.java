package gregtechmod.tests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import gregtechmod.api.util.InventoryHandler;

public class InventoryHandlerTest {

	public InventoryHandler<Integer> inventory;
	public Collection<Integer> extra;
	
	private Method countNulls;
	
	public InventoryHandlerTest() {
		inventory = new InventoryHandler<>(10);
		extra = new ArrayList<>();
		
		inventory.set(2, 28);
		inventory.set(5, 13);
		inventory.set(7, 63);
		inventory.set(9, 3234);
		
		extra.add(228);
		extra.add(146);
		extra.add(147);
		
		try {
			Method nulls = InventoryHandler.class.getDeclaredMethod("countNulls", int.class);
			nulls.setAccessible(true);
			countNulls = nulls;
		} catch(Throwable e) {}
	}
	
	@Test
	public void sizes() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assertTrue("Something went wrong: " + inventory.toString(), countNulls(0) == 6);
	}
	
	@Test
	public void addAllCheck() {
		System.out.print(inventory + " ");
		boolean result = inventory.addAll(extra);
		System.out.println(inventory);
		assertTrue("Was not added", result);
	}
	
	@Test
	public void clearTest() {
		inventory.clear();
		assertTrue("Not empty?", countNulls(0) == 10);
	}
	
	private int countNulls(int idx) {
		try {
			int value = (Integer) countNulls.invoke(inventory, 0);
			return value;
		} catch (Throwable e) {}
		
		return -1;
	}
}
