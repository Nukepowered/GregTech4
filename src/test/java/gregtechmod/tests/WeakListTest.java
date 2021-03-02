package gregtechmod.tests;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import gregtechmod.api.util.WeakList;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author TheDarkDnKTv
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class WeakListTest {
	
	WeakList<Obj> testList;
	
	public WeakListTest() {
		testList = new WeakList<>();
	}

	@Test
	@Order(1)
	public void size() {
		assertTrue(testList.toString(), testList.size() == 0);
	}
	
	@Test
	@Order(2)
	public void add() {
		testList.add(new Obj("LMAO"));
		testList.add(new Obj("KEK"));
		testList.add(new Obj("KEK1"));
		testList.add(new Obj("KEK2"));
		testList.add(new Obj("KEK3"));
		System.out.println(testList);
		assertTrue(testList.toString(), testList.size() == 5);
	}
	
	@Test
	@Order(3)
	public void get() {
		Obj a = testList.get(1);
		assertTrue("KEK".equals(a.value));
	}
	
	@Test
	@Order(4)
	public void remove() {
		Obj a = testList.remove(3);
		System.out.println(a);
		assertTrue(testList.size() == 4);
	}
	
	@Test
	@Order(5)
	public void gc() {
		System.gc();
		System.err.println(testList);
		assertTrue(!testList.getSecure(0).isPresent());
	}
	
	private static class Obj {
		final String value;
		
		Obj(String value) {this.value = value;}
		
		@Override
		public String toString() {
			return value;
		}
	}
}
