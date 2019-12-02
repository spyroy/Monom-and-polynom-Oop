package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Monom_Comperator;

class MonomTestJUNIT 
{
	@Test
	void test1() 
	{
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
	@Test
	void test2() 
	{
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
			boolean e = test.compare4(m1, m);
			System.out.println(i + ") " + m + "    \tisZero: " + m.isZero() + "  \teq: " + e);
		}

	}
	@Test
	private static void mytest() 
	{
		System.out.println();
		System.out.println("*****  mytest:  *****");
		Monom_Comperator test = new Monom_Comperator();
		int k = 1;
		
		String[] goodmonoms = { "x", "-x", "x^5", "5" , "2x^2", "-3x" , "5.0x" , "5.0", "66.66x^6" , "-7.2x^5", "2x^0", "-10x"};
		String[] badmonoms = { "++x", "-", "--x", "--5" , "2x^y", "", "1.1x^^" , "++11", "+" , "^" , "x^-3", "." };
		
		//checks if Monom has the right coefficient and power
		System.out.println("1) checks if Monom has the right coefficient and power: 5.3x^11");
		Monom o = new Monom(5.3,11);
		boolean flag = (5.3 == o.get_coefficient());
		boolean flag1 = (11 == o.get_power());
		System.out.println("   is the cofficient equals to 5.3?   " + flag + "  (should return true)" );
		System.out.println("   is the power equals to 11?   " + flag1 + "  (should return true)\n");
		
		//checks if initializing Monom using other Monom is equal to the Monom
		System.out.println("2) checks if initializing Monom using other Monom is equal to the Monom: 1.11x^12");
		o = new Monom(1.11, 12);
		Monom o2 = new Monom(o);
		flag = (test.compare4(o, o2));
		System.out.println("   is the monom are equal?     " + flag  + "  (should return true)\n");
		
		//printing all 12 good monoms
		System.out.println("3) printing all 12 good monoms:");
		for(String good_monoms : goodmonoms) {
				o = new Monom(good_monoms);
				System.out.println("   "  + "good monom " + k + ":  " + o.toString());	
				k++;
		}
		System.out.println("   12 monoms expected\n");
		
		//test bad monoms, 12 error exceptions for bad monoms
		System.out.println("4) test bad monoms, 12 error exceptions for bad monoms:");
		for(String bad_monoms : badmonoms) {
			o = new Monom(bad_monoms);	
		}
		System.out.println("   12 error expected\n");
		
		//prints true of the derivative is correct
		System.out.println("5) prints true of the derivative is correct:");
		Monom[] monom = {new Monom("7x^6"),new Monom("x^14"),new Monom("11.11x^0"),new Monom("1x^1"),new Monom("5.5"),new Monom("0")};
		Monom[] dv = {new Monom("42x^5"),new Monom("14x^13"),new Monom("0"),new Monom("1"),new Monom("0"),new Monom("0")};
		for(int i=0; i < monom.length;i++) {
			System.out.println("   " + monom[i].toString() + "  derivative: " + dv[i].toString() + "   " + test.compare4(monom[i].derivative(), dv[i]) + "  (should return true)");
		}
		System.out.println();
		
		//add two monoms if possible otherwise throws exception
		System.out.println("6) add two monoms if possible otherwise throws exception");
		Monom m3 = new Monom(2.4,3);
		Monom m4 = new Monom(1.1,5);
		o = new Monom(5.8,3);
		Monom tmp = new Monom(o);
		o.add(m3);
		System.out.println("   " + tmp.toString() + " + " + m3.toString() +   "  = " + o.toString() + " ? " + test.compare4(o, new Monom(8.2,3)) + "  (should return true)");
		o.add(m4);
		System.out.println("   error expected: can't add\n");
		
		//multiply two monoms
		System.out.println("7) multiply two monoms");
		Monom m5 = new Monom(2.5,12);
		Monom m6 = new Monom(1.1,9);
		Monom m7 = new Monom(2.75,21);
		Monom tmp2 = new Monom(m5);
		m5.multipy(m6);
		System.out.println("   " + tmp2.toString() + " multiply  " + m6.toString() + " = " + m7.toString() + " ? " + test.compare4(m7, m5) + " (should return true)\n");
		
		//compares between two monoms
		System.out.println("8) compares between two monoms");
		Monom m8 = new Monom ("9x^11");
		Monom m9 = new Monom(9.0,11);
		System.out.println("   " + m8.toString() + " = " + m9.toString() + " ? " + test.compare4(m8, m9) + " (should return true)\n");
		
		//prints true if f function working
		System.out.println("9) prints true if f function is working");
		double y = 3.2;
		o = new Monom("5.17x^2");
		double result = 5.17*Math.pow(y, 2);
		boolean fun = result == o.f(y);
		System.out.println("   f(3.2) = " +  result + " ? " + fun + " (should return true)\n");
	}

}
