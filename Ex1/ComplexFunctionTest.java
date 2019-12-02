package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexFunctionTest {

	@Test
	void testComplexFunction() {
		Monom M = new Monom(5,1);
		Monom m = new Monom(6,2);
		ComplexFunction cf = new ComplexFunction(M,Operation.Plus,m);
		System.out.println(cf);
		
	}

	@Test
	void testPlus() {
		Monom M = new Monom(5,1);
		Monom m = new Monom(6,2);
		ComplexFunction cf = new ComplexFunction(M,Operation.Plus,m);
		cf.plus(cf);
		System.out.println(cf);
	}

	@Test
	void testMul() {
		fail("Not yet implemented");
	}

	@Test
	void testDiv() {
		fail("Not yet implemented");
	}

	@Test
	void testMax() {
		fail("Not yet implemented");
	}

	@Test
	void testMin() {
		fail("Not yet implemented");
	}

	@Test
	void testComp() {
		fail("Not yet implemented");
	}

	@Test
	void testLeft() {
		fail("Not yet implemented");
	}

	@Test
	void testRight() {
		fail("Not yet implemented");
	}

	@Test
	void testGetOp() {
		fail("Not yet implemented");
	}

	@Test
	void testF() {
		fail("Not yet implemented");
	}

	@Test
	void testInitFromString() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

}
