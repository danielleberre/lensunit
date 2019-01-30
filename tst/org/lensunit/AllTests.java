package org.lensunit;

/**
 * Any non-TestCase class with a suite() method
 * can be used to build a TestSuite.
 * 
 * @author leberre
 *
 */
public class AllTests {
	public TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.add(Basic.class);
		suite.add(Exceptions.class);
		return suite;
	}
}
