package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

class PolynomTestt {
	public static final double EPSILON = 0.0000001;
	private static final DecimalFormat df = new DecimalFormat("#.0000");

	@Test
	void testGoodPolynom() {
		String[] goodpolynom = { "-6x+1", "6x^1+6x^3+1", "X^4-x", "x", "x^5+6x+8+12x^101-x^7+x^1+x^0-7", "x^0+8x+2.2",
				"-x^0-4.9x+5x", "-x^1+x^122+81x^5+6" };
		System.out.println("tests good polynoms");
		for (int i = 0; i < goodpolynom.length; i++) {
			Polynom p = new Polynom(goodpolynom[i]);
			System.out.println(p + " is a good polynom");
		}
		System.out.println();
	}

	@Test
	void testbadPolynom() {
		String[] badpolynom = { "6x4", "xx", "-3.3x^-0.7+6x^1.44+3", "3x+6+3x+21x^+4", "-5x^-3+2x+4+8", "x^6+5x+5+2z^1",
				"4x^1.3+x^3+0x", "7xX", "8Xx^", "9x+--7", "0x--7", "", "z" };
		System.out.println("tests bad polynoms");
		for (int i = 0; i < badpolynom.length; i++) {
			Polynom p = new Polynom(badpolynom[i]);
		}
		System.out.println("13 errors expected\n");
	}

	@Test
	void testF() {
		System.out.println("tests f function on polynom");
		Polynom p = new Polynom("x^5+9.3x+10");
		assertEquals(567.76875,p.f(3.5));
		double a = p.f(3.5);
		boolean flag = 567.76875 == a;
		System.out.println("f(3.5) = 567.76875 ? " + flag + " (should return true)\n");
	}

	@Test
	void testAddPolynom_able() {
		System.out.println("tests adding polynom to another");
		Polynom p1 = new Polynom("x^4+7");
		Polynom p2 = new Polynom("x^4+6");
		Polynom p3 = new Polynom("2x^4+13");
		Polynom tmp = new Polynom(p1);
		p1.add(p2);
		assertEquals(true, p1.equals(p3));
		boolean flag = p1.equals(p3);
		System.out.println(tmp.toString() + " + " + p2.toString() + " = " + p3.toString() + "   " + flag
				+ " (should return true)\n");
	}

	@Test
	void testAddMonom() {
		System.out.println("tests adding monom to polynom");
		Polynom p1 = new Polynom("x^5+8");
		Monom[] monoms = { new Monom("8x^8"), new Monom("1x^5"), new Monom("6.2") };
		Polynom p2 = new Polynom("8x^8+2x^5+14.2");
		for (int i = 0; i < monoms.length; i++) {
			p1.add(monoms[i]);
		}
		assertEquals(true, p1.equals(p2));
		boolean a = p2.equals(p1);
		System.out.println("x^5+8 + 8.0x^8 + 1.0x^5 + 6.2 = " + p2 + " ?" + a + " (should return true)\n");
	}

	@Test
	void testSubstract() {
		System.out.println("tests subtracting polynom from another");
		Polynom p1 = new Polynom("x^7+7");
		Polynom p2 = new Polynom("x^7+4");
		Polynom p3 = new Polynom("3");
		Polynom tmp2 = new Polynom(p1);
		p1.substract(p2);
		assertEquals(p3.toString(),p1.toString());
		boolean a = p3.equals(p1);
		System.out.println(tmp2.toString() + " - " + p2.toString() + " = 3 ?" + "  " + a + " (should return true)\n");
	}

	@Test
	void testMultiplyPolynom_able() {
		System.out.println("tests multiplying polynom with another polynom");
		Polynom p1 = new Polynom("x^100+5");
		Polynom p2 = new Polynom("x^3+1");
		Polynom p3 = new Polynom("x^103+x^100+5x^3+5");
		Polynom tmp = new Polynom(p1);
		p1.multiply(p2);
		assertEquals(p3.toString(),p1.toString());
		boolean a = p1.equals(p3);
		System.out.println(tmp.toString() + " * " + p2.toString() + " = " + p3.toString() + " ?" + "  " + a
				+ " (should return true)\n");
	}

	@Test
	void testEqualsPolynom_able() {
		System.out.println("tests if polynom equals to another polynom");
		Polynom p1= new Polynom("15x^5+47.80x^9+3x+16.0+0+x^0");
		Polynom p2= new Polynom("15x^5+47.8x^9+17+3x");
		assertEquals(p1.toString(), p2.toString());
		boolean a = p1.equals(p2);
		System.out.println(p1.toString() + " = " + p2.toString() + " ? " + a + " (should return true)\n" );
	}

	@Test
	void testIsZero() {
		System.out.println("tests if polynom is equal to zero");
		Polynom p1 = new Polynom("");
		Polynom p2 = new Polynom("4x^2+5x+3");
		assertEquals(true, p1.isZero());
		assertEquals(false, p2.isZero());
		System.out.println("is " + p1.toString() + " = 0 ? " + p1.isZero() + " (should return true)");
		System.out.println("is " + p2.toString() + " = 0 ? " + p2.isZero() + " (should return false)\n");
	}

	@Test
	void testRoot() {
		System.out.println("tests root [f(x) = 0] of polynom in a given range");
		Polynom p1 = new Polynom("-2x^4-3x^2+3");
		double root = p1.root(0.5, -0.5, EPSILON);
		System.out.println("error expected");
		double root1 = p1.root(0.5, -1, EPSILON);
		assertEquals(-0.8283360302448273,root1);
		System.out.println(" root of " + p1.toString() + " = " + root1 + "\n");
	}

	@Test
	void testCopy() {
		System.out.println("tests copy function");
		Polynom p1 = new Polynom("-5x^8 - 3x^2 + 5");
		Polynom p2 =  (Polynom) p1.copy();
		assertEquals(true,p1.equals(p2));
		boolean a = p1.equals(p2);
		System.out.println(p1.toString() + " = " + p2.toString() + " ? " + a + " (should return true)\n");
	}

	@Test
	void testDerivative() {
		System.out.println("tests derivative of polynom");
		Polynom p1 = new Polynom("5x^3+8x^2+7x+0");
		Polynom p2 = new Polynom("15x^2+16x+7");
		assertEquals(p2.toString(),p1.derivative().toString());
		boolean a = p1.derivative().equals(p2);
		System.out.println("(" + p1.toString() + ")'" + " = " + p2.toString() + " ? " + a + " (should return true)\n");
	}

	@Test
	void testArea() {
		System.out.println("tests area below the polynom using rimans integral");
		Polynom p1 = new Polynom("8x^3+5x^2+12");
		double area = p1.area(1, 2, EPSILON);
		assertEquals(53.66666311666506,area);
		System.out.println("area below " + p1.toString() + " equals " + area);
		System.out.println();
	}

	@Test
	void testMultiplyMonom() {
		System.out.println("tests multiplying polynom with monom");
		Polynom p1 = new Polynom("12x^3+7x^2+x");
		Monom[] monoms2 = { new Monom("8x^8"), new Monom("1x^5"), new Monom("6.2") };
		Polynom p2 = new Polynom("595.2x^16+347.2x^15+49.6x^14");
		Polynom tmp = new Polynom (p1);
		for (int i = 0; i < monoms2.length; i++) {
			p1.multiply(monoms2[i]);
		}
		assertEquals(p1.toString(),p2.toString());
		boolean a = p1.equals(p2);
		System.out.println(tmp.toString() + " * " + " (8x^8)(x^5)(6.2) = " + p2.toString() + " ? " + a + " (should return true)\n");
	}

}
