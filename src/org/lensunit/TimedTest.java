package org.lensunit;

import fr.univartois.migl.utils.DesignPattern;

@DesignPattern(name="decorator",url="https://en.wikipedia.org/wiki/Decorator_pattern")
public class TimedTest implements Test {
	
	private Test decoratedTest;
	private long duration;
	
	public TimedTest(Test decoratedTest) {
		this.decoratedTest = decoratedTest;
	}

	@Override
	public void run(ReportingStrategy reporting) {
		long start = System.currentTimeMillis();
		decoratedTest.run(reporting);
		long stop = System.currentTimeMillis();
		duration = stop - start;
	}

	public long getDuration() {
		return duration;
	}
}
