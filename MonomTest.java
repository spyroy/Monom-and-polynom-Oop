package myMath;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a simple (naive) tester for the Monom class, Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be
 * changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and
 * functionality of the Monom class. <br>
 * (iii) Expected output: <br>
 * ***** Test1: ***** <br>
 * 0) 2.0 isZero: false f(0) = 2.0 <br>
 * 1) -1.0x isZero: false f(1) = -1.0 <br>
 * 2) -3.2x^2 isZero: false f(2) = -12.8 <br>
 * 3) 0 isZero: true f(3) = 0.0 <br>
 ***** Test2: ***** <br>
 * 0) 0 isZero: true eq: true <br>
 * 1) -1.0 isZero: false eq: true <br>
 * 2) -1.3x isZero: false eq: true <br>
 * 3) -2.2x^2 isZero: false eq: true <br>
 * ***** Test3: ***** <br>
0) 0    	de: 0  	add: 0        mu: 0 <br>
1) -2.0    	de: 0  	add: -2.0        mu: 4.0 <br>
2) -2.6x    	de: -2.6  	add: -2.6x        mu: 6.760000000000001x^2 <br>
3) -4.4x^2    	de: -8.8x  	add: -4.4x^2        mu: 19.360000000000003x^4 <br>
 */
public class MonomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = { "2", "-x", "-3.2x^2", "0" };
		for (int i = 0; i < monoms.length; i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i + ") " + m + "    \tisZero: " + m.isZero() + "\t f(" + i + ") = " + fx);
		}
	}

	private static void test2() {
		Monom_Comperator test = new Monom_Comperator();
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0, 5));
		monoms.add(new Monom(-1, 0));
		monoms.add(new Monom(-1.3, 1));
		monoms.add(new Monom(-2.2, 2));

		for (int i = 0; i < monoms.size(); i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = test.compare3(m1, m);
			System.out.println(i + ") " + m + "    \tisZero: " + m.isZero() + "  \teq: " + e);
		}

	}

	private static void test3() {
		System.out.println("*****  Test3:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0, 5));
		monoms.add(new Monom(-1, 0));
		monoms.add(new Monom(-1.3, 1));
		monoms.add(new Monom(-2.2, 2));
		for (int i = 0; i < monoms.size(); i++) {
			Monom m = new Monom(monoms.get(i));
			m.add(m);
			System.out.print(i + ") " + m + "    \tde: " + m.derivative() + "  \tadd: " + m);
			m.multipy(m);
			System.out.println("        mu: " + m);
		}

	}
}

