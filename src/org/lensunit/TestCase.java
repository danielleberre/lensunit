package org.lensunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	
	/**
	 * Return all the tests available in that class.
	 * 
	 * @return a suite of tests containing all the tests found in that class.
	 */
	public TestSuite suite() {
		TestSuite suite = new TestSuite();
		TestCase testCase;
		Class<? extends TestCase> testcases = this.getClass();
		for (Method m : testcases.getMethods()) {
			if (m.getName().startsWith("test")) {
				try {
					testCase = testcases.getConstructor().newInstance();
					testCase.setMethodName(m.getName());
					suite.add(testCase);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return suite;
	}

	public static final void fail() {
		throw new AssertionError();
	}
	
	public static final void fail(String message) {
		throw new AssertionError(message);
	}
	
	public static final void assertTrue(boolean condition) {
		if (!condition) {
			fail();
		}
	}

	public static final void assertFalse(boolean condition) {
		if (condition) {
			fail();
		}
	}

	public static final void assertTrue(boolean condition, String message) {
		if (!condition) {
			fail(message);
		}
	}

	public static final void assertFalse(boolean condition, String message) {
		if (condition) {
			fail(message);
		}
	}

	public static final void assertNull(Object o) {
		if (o != null) {
			fail("This object should be null!");
		}
	}

	public static final void assertNotNull(Object o) {
		if (o == null) {
			fail("This object should NOT be null!");
		}
	}

	public static final void assertEquals(Object expected, Object o) {
		if (!expected.equals(o)) {
			fail(String.format("Expected %s but got %s", expected, o));
		}
	}
	
	public static final void assertSame(Object expected, Object o) {
		if (expected != o) {
			fail(String.format("%s and %s are not identical", expected, o));
		}
	}
	
	public static final void assertNotSame(Object expected, Object o) {
		if (expected == o) {
			fail(String.format("The two objects are identical to %s ", expected));
		}
	}
	
	public static final <T extends Throwable> T assertThrows(Class<T> clazz, MethodHandle handle) {
		try {
			handle.doSomething();
			throw new AssertionError("No exception thrown");
		} catch (Throwable t) {
			if (clazz.isAssignableFrom(t.getClass())) {
				return (T)t;
			}
			throw new AssertionError(String.format("Expected exception %s but got exception %s", clazz.getName(),t.getClass().getName()));
		}
	}
}
