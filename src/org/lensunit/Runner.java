package org.lensunit;

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
		for (String testclass :args) {
			allTests.add((Class<? extends TestCase>)Class.forName(testclass));
		}
		TimedTest timedTests = new TimedTest(allTests);
		ReportingStrategy reporting = new VerboseReportingStrategy();
		timedTests.run(reporting);
		System.out.println(reporting);
		System.out.printf("Duration: %dms",timedTests.getDuration());
	}

}
