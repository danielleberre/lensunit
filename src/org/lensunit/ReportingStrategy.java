package org.lensunit;

import fr.univartois.migl.utils.DesignPattern;

/**
 * The simple {@link Runner} displays informations using various strategies.
 * 
 * @author leberre
 *
 */
@DesignPattern(name="strategy",url="https://en.wikipedia.org/wiki/Strategy_pattern")
public interface ReportingStrategy {

	void report(String testname, Outcome outcome, Throwable reason);
}
