package org.lensunit;

import java.io.FileNotFoundException;
import java.io.IOException;

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
	
	public void launchException() {
		throw new IllegalArgumentException();
	}
	
	public void testExpectedException() {
		assertThrows(IllegalArgumentException.class,this::launchException);
	}
	
	public void testUnexpectedException() {
		assertThrows(IllegalStateException.class,this::launchException);
	}
	
	public void launchCheckedException() throws IOException {
		throw new FileNotFoundException();
	}
	
	public void testExpectedCheckedException() {
		assertThrows(IOException.class,this::launchCheckedException);
		assertThrows(FileNotFoundException.class,this::launchCheckedException);
	}
	
	public int launchExceptionNonVoidMethod() {
		throw new UnsupportedOperationException();
	}
	
	public void testExceptionOnNonVoidMethod() {
		assertThrows(UnsupportedOperationException.class,this::launchExceptionNonVoidMethod);
	}
	
	public int launchExceptionNonVoidMethodWithParameters(String s, int n) {
		throw new UnsupportedOperationException();
	}
	
	public void testExceptionOnNonVoidMethodWithParameters() {
		assertThrows(UnsupportedOperationException.class,() -> this.launchExceptionNonVoidMethodWithParameters("toto",5));
	}
}
