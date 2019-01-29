package org.lensunit;

public class Basic extends TestCase {

	public void testTrivialOk() {
		// an empty test trivially passes
	}
	
	public void testEasyCases() {
		assertTrue(2<3);
		assertFalse(2>3);
		assertEquals(new Integer(2),new Integer(2));
	}
	
	public void testFailingTest() {
		assertTrue(4<3);
	}
	
	public void testFailingEquals() {
		assertEquals(2,4);
	}
	
	public void testError() {
		throw new NullPointerException();
	}
	
	public void testIntegerValueOf() {
		Integer a = Integer.valueOf(10);
		Integer b = Integer.valueOf(10);
		Integer c = new Integer(10);
		Integer d = new Integer(10);
		assertEquals(a,b);
		assertEquals(a,c);
		assertEquals(a,d);
		assertSame(a,b);
		assertNotSame(a,c);
		assertNotSame(a,d);
		assertNotSame(b,c);
		assertNotSame(b,d);
		assertNotSame(c,d);
	}
}
