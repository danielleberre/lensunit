package org.lensunit;

import java.lang.reflect.InvocationTargetException;

import fr.univartois.migl.utils.DesignPattern;

/**
 * JUnit 3 like test cases.
 * 
 * @author leberre
 *
 */
public abstract class TestCase implements Test {

	private String methodName;

	/**
	 * Constructor used to limit the execution of a single test.
	 * 
	 * @param methodName
	 */
	protected TestCase(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Default constructor used to avoid having to declare a constructor in the test cases.
	 */
	protected TestCase() {
		
	}
	
	/**
	 * Used in the {@link TestSuite} class to set the test to execute.
	 * 
	 * @param methodName
	 */
	void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
 	@Override
	@DesignPattern(name = "template", url = "https://en.wikipedia.org/wiki/Template_method_pattern")
	public void run(ReportingStrategy reporting) {
 		// Each test follows the same template
 		// 1. Call the beforeEach() method
 		// 2. Run the test
 		// 3. Call the afterEach() method
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
			// the tricky part here is that each exception thrown in the invoked method
			// is embedded in an InvocationTargetException. We need to recover the original
			// issue here.
			throw e.getCause();
		}
	}

	/**
	 * Hook method to be called before each test.
	 */
	public void beforeEach() {
	}

	/**
	 * Hook method to be called after each test.
	 */
	public void afterEach() {
	}

	public static final void assertTrue(boolean condition) {
		if (!condition) {
			throw new AssertionError();
		}
	}

	public static final void assertFalse(boolean condition) {
		if (condition) {
			throw new AssertionError();
		}
	}

	public static final void assertTrue(boolean condition, String message) {
		if (!condition) {
			throw new AssertionError(message);
		}
	}

	public static final void assertFalse(boolean condition, String message) {
		if (condition) {
			throw new AssertionError(message);
		}
	}

	public static final void assertNull(Object o) {
		if (o != null) {
			throw new AssertionError("This object should be null!");
		}
	}

	public static final void assertNotNull(Object o) {
		if (o == null) {
			throw new AssertionError("This object should NOT be null!");
		}
	}

	public static final void assertEquals(Object expected, Object o) {
		if (!expected.equals(o)) {
			throw new AssertionError(String.format("Expected %s but got %s", expected, o));
		}
	}
}
