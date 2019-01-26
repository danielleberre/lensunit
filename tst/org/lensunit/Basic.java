package org.lensunit;

public class Basic extends TestCase {

	public void testTrivialOk() {
		
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
}
