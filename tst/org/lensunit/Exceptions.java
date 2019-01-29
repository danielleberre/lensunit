package org.lensunit;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Exceptions extends TestCase {

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
