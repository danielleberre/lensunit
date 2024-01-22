package org.lensunit;

/**
 * A more verbose reporting strategy: each test name appears in the report, with
 * the outcome of that test.
 * 
 * @author leberre
 *
 */
public class VerboseReportingStrategy implements ReportingStrategy {

    private int nbtests = 0;

    private SummaryReportingStrategy summary = new SummaryReportingStrategy();

    @Override
    public void report(String testname, Outcome outcome, Throwable reason) {
        summary.report(testname, outcome, reason);
        nbtests++;
        System.out.printf("%s %s%n", testname, outcome);
        if (reason != null) {
            System.out.println(reason);
        }
    }

    @Override
    public String toString() {
        return String.format("%s%nTotal %d test(s) run, status is %s", summary.getStats(), nbtests, globalOutcome());
    }

    @Override
    public Outcome globalOutcome() {
        return summary.globalOutcome();
    }

}
