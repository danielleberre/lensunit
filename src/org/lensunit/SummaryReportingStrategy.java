package org.lensunit;

import java.util.EnumMap;
import java.util.Map;

/**
 * Only displays a summary of the tests results.
 * 
 * @author leberre
 *
 */
public class SummaryReportingStrategy implements ReportingStrategy {

    private Map<Outcome, Counter> stats = new EnumMap<>(Outcome.class);

    public SummaryReportingStrategy() {
        for (Outcome outcome : Outcome.values()) {
            stats.put(outcome, new Counter());
        }
    }

    @Override
    public void report(String testname, Outcome outcome, Throwable reason) {
        stats.get(outcome).inc();
    }

    public Map<Outcome, Counter> getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return stats.toString();
    }

    @Override
    public Outcome globalOutcome() {
        if (stats.get(Outcome.ERROR).getValue() > 0) {
            return Outcome.ERROR;
        }
        if (stats.get(Outcome.FAILURE).getValue() > 0) {
            return Outcome.FAILURE;
        }
        return Outcome.OK;
    }
}
