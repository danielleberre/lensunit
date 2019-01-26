package org.lensunit;

import java.lang.reflect.InvocationTargetException;

import fr.univartois.migl.utils.DesignPattern;

public abstract class TestCase implements Test {

	private final String methodName;

	protected TestCase(String methodName) {
		this.methodName = methodName;
	}

	@Override
	@DesignPattern(name = "template", url = "https://en.wikipedia.org/wiki/Template_method_pattern")
	public void run(ReportingStrategy reporting) {
		beforeEach();
		try {
			runCurrent();
			reporting.report(methodName, Outcome.OK, null);
		} catch (AssertionError assertion) {
			reporting.report(methodName, Outcome.FAILURE, assertion);
		} catch (Throwable t) {
			reporting.report(methodName, Outcome.ERROR, t);
		} finally {
			afterEach();
		}
	}

	@DesignPattern(name="pluggable selector",url="http://junit.sourceforge.net/doc/cookstour/cookstour.htm")
	protected void runCurrent() throws Throwable {
		try {
			this.getClass().getMethod(methodName).invoke(this);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}

	public void beforeEach() {
	}

	public void afterEach() {
	}

	public void assertTrue(boolean condition) {
		if (!condition) {
			throw new AssertionError();
		}
	}

	public void assertFalse(boolean condition) {
		if (condition) {
			throw new AssertionError();
		}
	}

	public void assertTrue(boolean condition, String message) {
		if (!condition) {
			throw new AssertionError(message);
		}
	}

	public void assertFalse(boolean condition, String message) {
		if (condition) {
			throw new AssertionError(message);
		}
	}

	public void assertNull(Object o) {
		if (o != null) {
			throw new AssertionError("This object should be null!");
		}
	}

	public void assertNotNull(Object o) {
		if (o == null) {
			throw new AssertionError("This object should NOT be null!");
		}
	}

	public void assertEquals(Object expected, Object o) {
		if (!expected.equals(o)) {
			throw new AssertionError(String.format("Expected %s but got %s", expected, o));
		}
	}
}
