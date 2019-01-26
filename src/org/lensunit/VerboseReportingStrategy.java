package org.lensunit;

public class VerboseReportingStrategy implements ReportingStrategy {

	private int nbtests = 0;
	private Outcome overall = Outcome.OK;
	
	@Override
	public void report(String testname, Outcome outcome, Throwable reason) {
		nbtests++;
		System.out.printf("%s %s%n",testname,outcome);
		if (reason !=null) {
			System.out.println(reason);
		}
		if (outcome == Outcome.FAILURE && overall == Outcome.OK) {
			overall = Outcome.FAILURE;
		} else if (outcome == Outcome.ERROR) {
			overall = Outcome.ERROR;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Total %d test(s) run, status is %s",nbtests,overall);
	}

}
