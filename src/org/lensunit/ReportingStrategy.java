package org.lensunit;

import fr.univartois.migl.utils.DesignPattern;

/**
 * The simple {@link Runner} displays informations using various strategies.
 * 
 * The use of the strategy design pattern may be questionable here. The decision
 * to use it was mainly that it is one of the first design pattern seen by the
 * students.
 * 
 * @author leberre
 *
 */
@DesignPattern(name = "strategy", url = "https://en.wikipedia.org/wiki/Strategy_pattern")
public interface ReportingStrategy {

    void report(String testname, Outcome outcome, Throwable reason);

    default Outcome globalOutcome() {
        return Outcome.OK;
    }
}
