package Ex1_testing;
import Ex1.*;

import static org.junit.Assert.assertFalse;
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
		assertTrue(f.contains(p1));
		assertTrue(f.contains(p2));
		assertTrue(f.contains(p2));
	}

	@Test
	void testContainsAll() {
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
		assertTrue(f.containsAll(r));
	}

	@Test
	void testIsEmpty() {
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
		assertFalse(f.isEmpty());
		f.clear();
		assertTrue(f.isEmpty());
	}

	@Test
	void testIterator() {
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
		assertEquals(it.next(), p1);
		assertEquals(it.next(), p2);
		assertEquals(it.next(), p3);
	}

	@Test
	void testRemove() {
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
		f.remove(p1);
		assertFalse(f.contains(p1));
		f.remove(p2);
		assertFalse(f.contains(p2));
		f.remove(p3);
		assertFalse(f.contains(p3));
		assertTrue(f.isEmpty());
	}

	@Test
	void testRemoveAll() {
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
		f.removeAll(r);
		assertTrue(f.isEmpty());
	}

	@Test
	void testRetainAll() {
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
		f.addAll(r);
		assertFalse(f.contains(p3));
		assertTrue(f.contains(p2));
		assertTrue(f.contains(p1));
	}

	@Test
	void testSize() {
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
		assertEquals(3, 3, 0.0000000001);
		f.remove(p3);
		assertEquals(2, 2, 0.0000000001);
		f.remove(p2);
		assertEquals(1, 1, 0.0000000001);
		f.remove(p1);
		assertEquals(0, 0, 0.0000000001);
	}

	@Test
	void testInitFromFile() {
		functions data = new Functions_GUI();
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
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
		data.addAll(r);
		try {
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(1000,600,rx,ry,200);
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
