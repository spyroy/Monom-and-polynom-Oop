package myMath;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTestt {

	@Test
	void testGetComp() {
		Monom_Comperator test = new Monom_Comperator();
		System.out.println("checks if initializing Monom using other Monom is equal to the Monom: 1.11x^12");
		Monom o = new Monom(1.11, 12);
		Monom o2 = new Monom(o);
		assertEquals(o.toString(), o2.toString());
		boolean flag = (test.compare4(o, o2));
		System.out.println("   is the monom are equal?     " + flag  + "  (should return true)\n");
	}

	@Test
	void testMonombad() {
		String[] badmonoms = { "++x", "-", "--x", "--5" , "2x^y", "", "1.1x^^" , "++11", "+" , "^" , "x^-3", "." };
		System.out.println("test bad monoms, 12 error exceptions for bad monoms:");
		for(String bad_monoms : badmonoms) {
			Monom o = new Monom(bad_monoms);	
		}
		System.out.println("   12 error expected\n");
	}

	@Test
	void testgoodMonom() {
		String[] goodmonoms = { "x", "-x", "x^5", "5" , "2x^2", "-3x" , "5.0x" , "5.0", "66.66x^6" , "-7.2x^5", "2x^0", "-10x"};
		System.out.println("printing all 12 good monoms:");
		int k =1 ;
		for(String good_monoms : goodmonoms) {
				Monom o = new Monom(good_monoms);
				System.out.println("   "  + "good monom " + k + ":  " + o.toString());	
				k++;
		}
		System.out.println("   12 monoms expected\n");
	}

	@Test
	void testGet_coefficient() {
		System.out.println("checks if Monom has the right coefficient : 5.3x^11");
		Monom o = new Monom(5.3,11);
		assertEquals(5.3,o.get_coefficient(),0.0000001);
		boolean flag = (5.3 == o.get_coefficient());
		System.out.println("   is the cofficient equals to 5.3?   " + flag + "  (should return true)\n" );
	}

	@Test
	void testGet_power() {
		System.out.println("checks if Monom has the right power : 5.3x^11");
		Monom o = new Monom(5.3,11);
		assertEquals(11, o.get_power());
		boolean flag1 = (11 == o.get_power());
		System.out.println("   is the power equals to 11?   " + flag1 + "  (should return true)\n");
	}

	@Test
	void testDerivative() {
		Monom_Comperator test = new Monom_Comperator();
		System.out.println("prints true of the derivative is correct:");
		Monom[] monom = {new Monom("7x^6"),new Monom("x^14"),new Monom("11.11x^0"),new Monom("1x^1"),new Monom("5.5"),new Monom("0")};
		Monom[] dv = {new Monom("42x^5"),new Monom("14x^13"),new Monom("0"),new Monom("1"),new Monom("0"),new Monom("0")};
		for(int i=0; i < monom.length;i++) {
			assertEquals(monom[i].derivative().toString(), dv[i].toString());
			System.out.println("   " + monom[i].toString() + "  derivative: " + dv[i].toString() + "   " + test.compare4(monom[i].derivative(), dv[i]) + "  (should return true)");
		}
		System.out.println();
	}

	@Test
	void testF() {
		System.out.println("prints true if f function is working");
		double y = 3.2;
		Monom o = new Monom("5.17x^2");
		double result = 5.17*Math.pow(y, 2);
		assertEquals(result, o.f(y),0.0000001);
		boolean fun = result == o.f(y);
		System.out.println("   f(3.2) = " +  result + " ? " + fun + " (should return true)\n");
	}

	@Test
	void testIsZero() {
		Monom o = new Monom("0");
		assertTrue(o.isZero());
	}


	@Test
	void testAdd() {
		Monom_Comperator test = new Monom_Comperator();
		System.out.println("add two monoms if possible otherwise throws exception");
		Monom m3 = new Monom(2.4,3);
		Monom m4 = new Monom(1.1,5);
		Monom o = new Monom(5.8,3);
		Monom tmp = new Monom(o);
		o.add(m3);
		assertEquals(new Monom(8.2,3).toString(),o.toString());
		System.out.println("   " + tmp.toString() + " + " + m3.toString() +   "  = " + o.toString() + " ? " + test.compare4(o, new Monom(8.2,3)) + "  (should return true)");
		o.add(m4);
		System.out.println("   error expected: can't add\n");
	}

	@Test
	void testMultipy() {
		Monom_Comperator test = new Monom_Comperator();
		System.out.println("multiply two monoms");
		Monom m5 = new Monom(2.5,12);
		Monom m6 = new Monom(1.1,9);
		Monom m7 = new Monom(2.75,21);
		Monom tmp2 = new Monom(m5);
		m5.multipy(m6);
		assertEquals(m7.toString(),m5.toString());
		System.out.println("   " + tmp2.toString() + " multiply  " + m6.toString() + " = " + m7.toString() + " ? " + test.compare4(m7, m5) + " (should return true)\n");
	}

	@Test
	void testcompare() {
		Monom_Comperator test = new Monom_Comperator();
		System.out.println("8) compares between two monoms");
		Monom m8 = new Monom ("9x^11");
		Monom m9 = new Monom(9.0,11);
		assertEquals(m8.toString(),m9.toString());
		System.out.println("   " + m8.toString() + " = " + m9.toString() + " ? " + test.compare4(m8, m9) + " (should return true)\n");
	}


}
