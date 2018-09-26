package hw3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {

	@Test
	void test() {
		IDLList<Integer> i = new IDLList<Integer>();
		assertTrue(i.add(1));
		assertTrue(i.add(0,0));
		assertTrue(i.append(3));
		assertTrue(i.add(2,2));
		assertTrue(i.append(4));
		assertEquals(5, i.size());
		assertEquals(Integer.valueOf(4), i.removeAt(4));
		assertEquals(Integer.valueOf(3), i.get(3));
		assertEquals(Integer.valueOf(0), i.getHead());
		assertEquals(Integer.valueOf(3), i.getLast());
		assertEquals(Integer.valueOf(0), i.remove());
		assertEquals(Integer.valueOf(3), i.removeLast());
		assertEquals(Integer.valueOf(2), i.removeAt(1));
		assertTrue(i.remove(1));
		
	}
	
}
