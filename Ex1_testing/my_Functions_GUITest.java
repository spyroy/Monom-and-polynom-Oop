package Ex1_testing;
import Ex1.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	void testInitFromFileAndSavetoFile() {
		functions data = new Functions_GUI();
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		String[] s4= {"3.1 +2.4x^2 -x^4","5 +2x -3.3x +0.1x^5","x +3"};
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
		
		String line="";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int i=0;
			try {
				while((line = reader.readLine())!= null) 
				{
					String func = line.substring(0, line.length());
					function tmp = new ComplexFunction(func);
					ComplexFunction cf = new ComplexFunction();
					cf=(ComplexFunction)cf.initFromString(s4[i]);
					assertEquals(cf, tmp);
					if(i==s4.length)
						break;
					i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}


	@Test
	void testDrawFunctionsIntIntRangeRangeInt() 
	{
		functions data = new Functions_GUI();
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		String[] s4= {"3.1 +2.4x^2 -x^4","5 +2x -3.3x +0.1x^5","x +3"};
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
		assertThrows(IllegalArgumentException.class, () ->data.drawFunctions(-1000,600,rx,ry,200));
		assertThrows(IllegalArgumentException.class, () ->data.drawFunctions(1000,-600,rx,ry,200));
		assertThrows(IllegalArgumentException.class, () ->data.drawFunctions(1000,600,rx,ry,-200));
	}

	@Test
	void testDrawFunctionsString() 
	{	
		JSONObject obj = new JSONObject();
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		obj.put("Height",600);
		obj.put("Width",1000);
		obj.put("Resolution",200);
		obj.put("Range_X", rx );
		obj.put("Range_Y",ry );
		try(FileWriter ffile = new FileWriter("gui_functions.txt"))
		{
			ffile.write(obj.toString());
			ffile.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(obj);
		functions data = new Functions_GUI();
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		String[] s4= {"3.1 +2.4x^2 -x^4","5 +2x -3.3x +0.1x^5","x +3"};
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
		data.drawFunctions("gui_functions.txt");
		

	}

}
