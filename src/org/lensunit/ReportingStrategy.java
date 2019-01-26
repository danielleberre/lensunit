package org.lensunit;

import fr.univartois.migl.utils.DesignPattern;

@DesignPattern(name="strategy",url="https://en.wikipedia.org/wiki/Strategy_pattern")
public interface ReportingStrategy {

	void report(String testname, Outcome outcome, Throwable reason);
}
