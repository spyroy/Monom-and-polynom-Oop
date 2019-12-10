package Ex1_testing;
import Ex1.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;
class my_Functions_GUITest {

	

	@Test
	void testAdd() {
		functions f = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		f.add(p1);
		f.add(p2);
		f.add(p3);
		Iterator<function> it = f.iterator();
		function f1 = new Polynom((Polynom) it.next());
		assertEquals(p1,f1 );
		function f2 = new Polynom((Polynom) it.next());
		assertEquals(p2,f2 );
		function f3 = new Polynom((Polynom) it.next());
		assertEquals(p3,f3 );
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());
		System.out.println();
	}

	@Test
	void testAddAll() {
		functions f = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ArrayList<function> r = new ArrayList();
		r.add(p1);
		r.add(p2);
		r.add(p3);
		f.addAll(r);
		Iterator<function> it = f.iterator();
		function f1 = new Polynom((Polynom) it.next());
		assertEquals(p1,f1 );
		function f2 = new Polynom((Polynom) it.next());
		assertEquals(p2,f2 );
		function f3 = new Polynom((Polynom) it.next());
		assertEquals(p3,f3 );
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());
		System.out.println();
	}

	@Test
	void testClear() {
		functions f = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ArrayList<function> r = new ArrayList();
		r.add(p1);
		r.add(p2);
		r.add(p3);
		f.addAll(r);
		f.clear();
		assertTrue(f.isEmpty());
	}

	@Test
	void testContains() {
		fail("Not yet implemented");
	}

	@Test
	void testContainsAll() {
		fail("Not yet implemented");
	}

	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	void testRetainAll() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	@Test
	void testToArray() {
		fail("Not yet implemented");
	}

	@Test
	void testToArrayTArray() {
		fail("Not yet implemented");
	}

	@Test
	void testInitFromFile() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveToFile() {
		fail("Not yet implemented");
	}

	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		fail("Not yet implemented");
	}

	@Test
	void testDrawFunctionsString() {
		fail("Not yet implemented");
	}

}
