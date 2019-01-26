package org.lensunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import fr.univartois.migl.utils.DesignPattern;

/**
 * A set of tests.
 * 
 * @author leberre
 *
 */
@DesignPattern(name="composite",url="https://en.wikipedia.org/wiki/Composite_pattern")
public class TestSuite implements Test {

	private Collection<Test> tests = new ArrayList<>();

	/**
	 * Add a single test to the suite.
	 * 
	 * @param test a test
	 */
	public void add(Test test) {
		tests.add(test);
	}

	/**
	 * Add all the tests available in that class.
	 * 
	 * @param testcases a subclass of TestCase containing tests
	 */
	public void add(Class<? extends TestCase> testcases) {
		TestCase testCase;
		for (Method m : testcases.getMethods()) {
			if (m.getName().startsWith("test")) {
				try {
					testCase = testcases.getConstructor().newInstance();
					testCase.setMethodName(m.getName());
					add(testCase);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run(ReportingStrategy reporting) {
		for (Test test : tests) {
			test.run(reporting);
		}
	}

}
