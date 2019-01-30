package org.lensunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Very simple test runner.
 * 
 * @author leberre
 *
 */
public class Runner {

	/**
	 * Runs all the tests found in the classes given on the command line.
	 * 
	 * @param args fully qualified name of the
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		TestSuite allTests = new TestSuite();
		Class<?> clazz;
		for (String testclass : args) {
			clazz = Class.forName(testclass);
			
				// looking for a class with a suite() method
				try {
					Method m = clazz.getMethod("suite");
					Object caller = clazz.newInstance();
					Object o = m.invoke(caller);
					if (o instanceof TestSuite) {
						allTests.add((TestSuite)o);
					}
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		TimedTest timedTests = new TimedTest(allTests);
		ReportingStrategy reporting = new VerboseReportingStrategy();
		timedTests.run(reporting);
		System.out.println(reporting);
		System.out.printf("Duration: %dms", timedTests.getDuration());
	}

}
