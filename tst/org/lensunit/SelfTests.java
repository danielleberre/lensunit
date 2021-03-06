package org.lensunit;

public class SelfTests extends TestCase {
    private static final VerboseReportingStrategy verboseStrategy = new VerboseReportingStrategy();

    public void testOutcomeOK() throws InstantiationException, IllegalAccessException {
        outcomeTest(Basic.class, Outcome.OK,
                new String[] { "testTrivialOk", "testEasyCases", "testIntegerValueOf", "testDouble" });
        outcomeTest(Exceptions.class, Outcome.OK,
                new String[] { "testExpectedException", "testExpectedCheckedException", "testExceptionOnNonVoidMethod",
                        "testExceptionOnNonVoidMethodWithParameters", "testExceptionOnEqualsWithEqualBooleans",
                        "testExceptionOnEqualsWithDifferentBooleans", "testExceptionOnEqualsWithDouble",
                        "testExceptionOnEqualsWithFloat" });
    }

    public void testOutcomeFailure() throws InstantiationException, IllegalAccessException {
        outcomeTest(Basic.class, Outcome.FAILURE, new String[] { "testFailingTest", "testFailingEquals", "testFailingTimeout"});
        outcomeTest(Exceptions.class, Outcome.FAILURE, new String[] { "testUnexpectedException" });
    }

    public void testOutcomeError() throws InstantiationException, IllegalAccessException {
        outcomeTest(Basic.class, Outcome.ERROR, new String[] { "testError" });
    }

    private void outcomeTest(Class<? extends TestCase> clazz, Outcome expectedOutcome, String[] methodNames)
            throws InstantiationException, IllegalAccessException {
        ReportingStrategy strategy = (name, outcome, t) -> {
            verboseStrategy.report(name, outcome, t);
            assertEquals(expectedOutcome, outcome);
        };
        for (String methodName : methodNames) {
            TestCase testcase = clazz.newInstance();
            testcase.setMethodName(methodName);
            testcase.run(strategy);
        }
    }
}
