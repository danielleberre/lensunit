package org.lensunit;

import java.util.HashMap;
import java.util.Map;

public class SummaryReportingStrategy implements ReportingStrategy {

	private Map<Outcome,Counter> stats = new HashMap<>();
	
	public SummaryReportingStrategy() {
		for (Outcome outcome : Outcome.values()) {
			stats.put(outcome, new Counter());
		}
	}
	@Override
	public void report(String testname, Outcome outcome, Throwable reason) {
		stats.get(outcome).inc();
	}

	public Map<Outcome,Counter> getStats() {
		return stats;
	}
	
	@Override
	public String toString() {
		return stats.toString();
	}
}
