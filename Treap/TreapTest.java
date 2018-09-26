package hw5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void test1() {
		Treap<Integer> testTree = new Treap<Integer>();
		assertTrue(testTree.add(4,19)); 
		assertTrue(testTree.add(2,31));
		assertTrue(testTree.add(6,70)); 
		assertTrue(testTree.add(1,84));
		assertTrue(testTree.add(3,12)); 
		assertTrue(testTree.add(5,83));
		assertTrue(testTree.add(7,26));
		assertTrue(testTree.find(7));
		assertFalse(testTree.find(90));
		assertTrue(testTree.delete(7));
		assertFalse(testTree.delete(55));
	}
	
	void test2() {
		Treap<Integer> testTree = new Treap<Integer>();
		assertTrue(testTree.add(8,12)); 
		assertTrue(testTree.add(3,55));
		assertTrue(testTree.add(90,7)); 
		assertTrue(testTree.add(11,84));
		assertTrue(testTree.add(34,3)); 
		assertTrue(testTree.add(5,81));
		assertTrue(testTree.add(7,268));
		assertTrue(testTree.find(34));
		assertFalse(testTree.find(9));
		assertTrue(testTree.delete(34));
		assertFalse(testTree.delete(55));
		assertFalse(testTree.find(34));
	}
	
	void test3() {
		Treap<Integer> testTree = new Treap<Integer>();
		assertTrue(testTree.add(6, 7)); 
		assertTrue(testTree.add(4, 77));
		assertTrue(testTree.add(9, 0)); 
		assertTrue(testTree.add(12, 3));
		assertTrue(testTree.add(8, 65)); 
		assertTrue(testTree.add(52,8));
		assertTrue(testTree.add(7,34));
		assertTrue(testTree.find(54));
		assertFalse(testTree.find(13));
		assertTrue(testTree.delete(12));
		assertFalse(testTree.delete(78));
		assertFalse(testTree.find(12));
	}
	

}
