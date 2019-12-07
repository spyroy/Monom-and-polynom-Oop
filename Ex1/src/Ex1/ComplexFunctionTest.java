package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexFunctionTest {

	@Test
	void testComplexFunction() 
	{
		System.out.println("test constrctor ComplexFunction():");
		Monom M = new Monom(5,1);
		Monom m = new Monom(6,2);
		ComplexFunction cf = new ComplexFunction(M,Operation.Plus,m);
		System.out.println(cf);
		String s = "Plus(5.0x,6.0x^2)";
		assertEquals(s, cf.toString());
		cf.div(m);
		System.out.println(cf + "\n");
		s = "Divid(Plus(5.0x,6.0x^2),6.0x^2)";
		assertEquals(s, cf.toString());
	}

	@Test
	void testPlus() 
	{
		System.out.println("test plus operator:");
		Monom M = new Monom(6,1);
		Monom m = new Monom(7,2);
		ComplexFunction cf = new ComplexFunction(M,Operation.Plus,m);
		cf.plus(cf);
		System.out.println(cf.toString()+ "\n");
	}

	@Test
	void testMul() {
		System.out.println("test Times operator:");
		Monom M = new Monom(5.6,1);
		Monom m = new Monom(6.2,2);
		ComplexFunction cf = new ComplexFunction(M,Operation.Plus,m);
		System.out.println(cf);
		String s = "Plus(5.6x,6.2x^2)";
		assertEquals(s, cf.toString());
		cf.mul(m);
		System.out.println(cf+ "\n");
		s = "Times(Plus(5.6x,6.2x^2),6.2x^2)";
		assertEquals(s, cf.toString());
	}

	@Test
	void testDiv() {
		System.out.println("test divid operator:");
		Monom M = new Monom(12.8,1);
		Monom m = new Monom(16.8,2);
		ComplexFunction cf = new ComplexFunction(M,Operation.Divid,m);
		System.out.println(cf);
		String s = "Divid(12.8x,16.8x^2)";
		assertEquals(s, cf.toString());
		cf.div(m);
		System.out.println(cf+ "\n");
		s = "Divid(Divid(12.8x,16.8x^2),16.8x^2)";
		assertEquals(s, cf.toString());
	}

	@Test
	void testMax() {
		System.out.println("test max operator:");
		Monom M = new Monom(68451.2,1);
		Monom m = new Monom(165,2);
		ComplexFunction cf = new ComplexFunction(M,Operation.Max,m);
		System.out.println(cf);
		String s = "Max(68451.2x,165.0x^2)";
		assertEquals(s, cf.toString());
		cf.max(m);
		System.out.println(cf+ "\n");
		s = "Max(Max(68451.2x,165.0x^2),165.0x^2)";
		assertEquals(s, cf.toString());
	}

	@Test
	void testMin() {
		System.out.println("test min operator:");
		Monom M = new Monom(85,1);
		Monom m = new Monom(8450.2,2);
		ComplexFunction cf = new ComplexFunction(M,Operation.Min,m);
		System.out.println(cf);
		String s = "Min(85.0x,8450.2x^2)";
		assertEquals(s, cf.toString());
		cf.min(m);
		System.out.println(cf+ "\n");
		s = "Min(Min(85.0x,8450.2x^2),8450.2x^2)";
		assertEquals(s, cf.toString());
	}

	@Test
	void testComp() {
		System.out.println("test comp operator:");
		Monom M = new Monom(86,45);
		Monom m = new Monom(85.15,6);
		ComplexFunction cf = new ComplexFunction(M,Operation.Comp,m);
		System.out.println(cf);
		String s = "Comp(86.0x^45,85.15x^6)";
		assertEquals(s, cf.toString());
		cf.comp(m);
		System.out.println(cf+ "\n");
		s = "Comp(Comp(86.0x^45,85.15x^6),85.15x^6)";
		assertEquals(s, cf.toString());
	}

	@Test
	void testLeft() {
		System.out.println("test getting the left function in a CoplexFunction:");
		Monom M = new Monom(86,45);
		Monom m = new Monom(85.15,6);
		ComplexFunction cf = new ComplexFunction(M,Operation.Comp,m);
		System.out.println(cf);
		ComplexFunction cf1 = (ComplexFunction) cf.copy();
		System.out.println(cf1);
		cf.plus(m);
		System.out.println(cf);
		System.out.println(cf.left() +"\n");
		assertEquals(cf1.toString(), cf.left().toString());
	}

	@Test
	void testRight() {
		System.out.println("test getting the right function in a CoplexFunction:");
		Monom M = new Monom(86,45);
		Monom m = new Monom(85.15,6);
		ComplexFunction cf = new ComplexFunction(M,Operation.Comp,m);
		System.out.println(cf);
		Polynom cf1 = new Polynom("85.15x^6");
		System.out.println(cf1);
		cf.plus(m);
		System.out.println(cf);
		System.out.println(cf.right() +"\n");
		assertEquals(cf1.toString(), cf.right().toString());
	}

	@Test
	void testGetOp() {
		System.out.println("test getting the operation in a CoplexFunction:");
		Monom M = new Monom(86,45);
		Monom m = new Monom(85.15,6);
		ComplexFunction cf = new ComplexFunction(M,Operation.Comp,m);
		System.out.println(cf.getOp() +"\n");
		String s = "Comp";
		assertEquals(s, cf.getOp().toString());
	}

	@Test
	void testF() {
		System.out.println("test f function on ComplexFunction");
		Polynom p = new Polynom ("x^5");
		Polynom m = new Polynom ("x^7");
		ComplexFunction cf = new ComplexFunction(p,"Plus",m);
		System.out.println(cf.f(5));
		assertEquals(81250, cf.f(5));
		cf.plus(cf);
		System.out.println(cf.f(3));
		assertEquals(4860, cf.f(3));
	}

	@Test
	void testInitFromString() {
		System.out.println("test initfromstring function on ComplexFunction");
		ComplexFunction cf = new ComplexFunction();
		cf = (ComplexFunction)cf.initFromString("min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)"
				+ ",plus(divid(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0))"
				+ ",divid(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1))"
				+ ",-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
		System.out.println(cf);
		String s = "Min(Min(Min(Min(Plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),Plus(Divid(1.0x+1.0,Times(Times(1.0x+3.0,1.0x-2.0),1.0x-4.0)),2.0)),Divid(Plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4+2.4x^2+3.1),0.1x^5-1.2999999999999998x+5.0)";
		assertEquals(s, cf.toString());
	}

	@Test
	void testCopy() {
		System.out.println("test deep copy function on ComplexFunction");
		Polynom p = new Polynom ("x^5");
		Polynom m = new Polynom ("x^7");
		ComplexFunction cf = new ComplexFunction(p,"Plus",m);
		ComplexFunction cf1 = (ComplexFunction) cf.copy();
		System.out.println(cf);
		System.out.println(cf1);
		assertEquals(cf.toString(), cf1.toString());
		cf.plus(m);
		cf1.div(p);
		System.out.println(cf);
		System.out.println(cf1+"\n");
		assertNotEquals(cf.toString(), cf1.toString());
	}
	
	@Test
	void testequals() {
		Polynom p = new Polynom ("x^5");
		Polynom m = new Polynom ("x^7");
		ComplexFunction cf1 = new ComplexFunction(p,"Plus",m);
		ComplexFunction cf2 = new ComplexFunction(p,"Plus",m);
		boolean flag = cf1.equals(cf2);
		System.out.println(flag);
		assertEquals(cf1, cf2);
		ComplexFunction cf = new ComplexFunction(p,"Plus",m);
		cf.plus(cf2);
		assertNotEquals(cf1, cf);
	}

}
