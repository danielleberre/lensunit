package org.lensunit;

import java.util.ArrayList;
import java.util.Collection;

import fr.univartois.migl.utils.DesignPattern;

/**
 * A set of tests.
 * 
 * @author leberre
 *
 */
@DesignPattern(name = "composite", url = "https://en.wikipedia.org/wiki/Composite_pattern")
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
        try {
            add(testcases.newInstance().suite());
        } catch (InstantiationException | IllegalAccessException e) {
            // do nothing
        }
    }

    @Override
    public void run(ReportingStrategy reporting) {
        for (Test test : tests) {
            test.run(reporting);
        }
    }

}
