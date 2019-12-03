package org.lensunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Logger logger = Logger.getLogger("org.lensunit");
        TestSuite allTests = new TestSuite();
        Class<?> clazz;
        for (String testclass : args) {
            clazz = Class.forName(testclass);

            // looking for a class with a suite() method
            try {
                Method m = clazz.getMethod("suite");
                Object o;
                if (Modifier.isStatic(m.getModifiers())) {
                    o = m.invoke(null);
                } else {
                    Object caller = clazz.newInstance();
                    o = m.invoke(caller);
                }
                if (o instanceof TestSuite) {
                    allTests.add((TestSuite) o);
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | InstantiationException e) {
                logger.log(Level.INFO, "cannot run tests " + testclass, e);
            }
        }

        TimedTest timedTests = new TimedTest(allTests);
        ReportingStrategy reporting = new VerboseReportingStrategy();
        timedTests.run(reporting);
        System.out.println(reporting);
        System.out.printf("Duration: %dms%n", timedTests.getDuration());
        if (reporting.globalOutcome() != Outcome.OK) {
            System.exit(10); // non zero exit code breaks a build
        }
    }

}
