package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		//test1();
		//test2();
		mytest();
	}

	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = { "1", "x", "x^2", "0.5x^2" };
		for (int i = 0; i < monoms.length; i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
			double aa = p1.area(0, 1, 0.0001);
			p1.substract(p1);
			System.out.println(p1 + " " + aa);
		}
		System.out.println();
	}

	public static void test2() {
		Polynom p1 = new Polynom(), p2 = new Polynom();
		String[] monoms1 = { "2", "-x", "-3.2x^2", "4", "-1.5x^2" };
		String[] monoms2 = { "5", "1.7x", "3.2x^2", "-3", "-1.5x^2" };
		for (int i = 0; i < monoms1.length; i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for (int i = 0; i < monoms2.length; i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: " + p1);
		System.out.println("p2: " + p2);
		p1.add(p2);
		System.out.println("p1+p2: " + p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: " + p1);
		String s1 = p1.toString();
		Polynom_able pp1 = new Polynom();
		;
		System.out.println("from string: " + pp1);
		System.out.println();
	}
	

	private static void mytest() {
		String[] goodpolynom = { "-6x+1", "6x^1+6x^3+1", "X^4-x", "x", "x^5+6x+8+12x^101-x^7+x^1+x^0-7", "x^0+8x+2.2",
				"-x^0-4.9x+5x", "-x^1+x^122+81x^5+6" };
		String[] badpolynom = { "6x4", "xx", "-3.3x^-0.7+6x^1.44+3", "3x+6+3x+21x^+4", "-5x^-3+2x+4+8", "x^6+5x+5+2z^1",
				"4x^1.3+x^3+0x", "7xX", "8Xx^", "9x+--7", "0x--7", "", "z" };
		Monom_Comperator test = new Monom_Comperator();
		Polynom p;

		// tests good polynoms
		System.out.println("tests good polynoms");
		for (int i = 0; i < goodpolynom.length; i++) {
			p = new Polynom(goodpolynom[i]);
			System.out.println(p + " is a good polynom");
		}
		System.out.println();

		// tests bad polynoms
		System.out.println("tests bad polynoms");
		for (int i = 0; i < badpolynom.length; i++) {
			p = new Polynom(badpolynom[i]);
		}
		System.out.println("13 errors expected\n");

		// tests f function on polynom
		System.out.println("tests f function on polynom");
		p = new Polynom("x^5+9.3x+10");
		double a = p.f(3.5);
		boolean flag = 567.76875 == a;
		System.out.println("f(3.5) = 567.76875 ? " + flag + " (should return true)\n");

		// tests adding polynom to another
		System.out.println("tests adding polynom to another");
		Polynom p1 = new Polynom("x^4+7");
		Polynom p2 = new Polynom("x^4+6");
		Polynom p3 = new Polynom("2x^4+13");
		Polynom tmp = new Polynom(p1);
		p1.add(p2);
		boolean flag1 = p1.equals(p3);
		System.out.println(tmp.toString() + " + " + p2.toString() + " = " + p3.toString() + "   " + flag1
				+ " (should return true)\n");

		// tests adding monom to polynom
		System.out.println("tests adding monom to polynom");
		Polynom p4 = new Polynom("x^5+8");
		Monom[] monoms = { new Monom("8x^8"), new Monom("1x^5"), new Monom("6.2") };
		Polynom p5 = new Polynom("8x^8+2x^5+14.2");
		for (int i = 0; i < monoms.length; i++) {
			p4.add(monoms[i]);
		}
		boolean b = p5.equals(p4);
		System.out.println("x^5+8 + 8.0x^8 + 1.0x^5 + 6.2 = " + p5 + " ?" + b + " (should return true)\n");

		// tests subtracting polynom from another
		System.out.println("tests subtracting polynom from another");
		Polynom p6 = new Polynom("x^7+7");
		Polynom p7 = new Polynom("x^7+4");
		Polynom p8 = new Polynom("3");
		p6.substract(p7);
		// p6.substract(p6);//fix
		boolean b2 = p8.equals(p6);
		System.out.println(p6.toString() + " - " + p7.toString() + " = 3 ?" + "  " + b2 + " (should return true)\n");
		
		//tests multiplying polynom with another polynom
		System.out.println("tests multiplying polynom with another polynom");
		Polynom p9 = new Polynom("x^100+5");
		Polynom p10 = new Polynom("x^3+1");
		Polynom p11 = new Polynom("x^103+x^100+5x^3+5");
		Polynom tmp5 = new Polynom(p9);
		p9.multiply(p10);
		boolean b3 = p9.equals(p11);
		System.out.println(tmp5.toString() + " * " + p10.toString() + " = " + p11.toString() + " ?" + "  " + b3
				+ " (should return true)\n");
		
		//tests if polynom equals to another polynom
		System.out.println("tests if polynom equals to another polynom");
		Polynom p12= new Polynom("15x^5+47.80x^9+3x+16.0+0+x^0");
		Polynom p13= new Polynom("15x^5+47.8x^9+17+3x");
		boolean b4 = p12.equals(p13);
		System.out.println(p12.toString() + " = " + p13.toString() + " ? " + b4 + " (should return true)\n" );
		
		
		Polynom p14 = new Polynom("");
		Polynom p15 = new Polynom("4x^2+5x+3");
		System.out.println("is " + p14.toString() + " = 0 ? " + p14.isZero() + " (should return true)");
		System.out.println("is " + p15.toString() + " = 0 ? " + p15.isZero() + " (should return false)\n");
		
	}
}
