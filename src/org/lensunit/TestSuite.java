package org.lensunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import fr.univartois.migl.utils.DesignPattern;

@DesignPattern(name="composite",url="https://en.wikipedia.org/wiki/Composite_pattern")
public class TestSuite implements Test {

	private Collection<Test> tests = new ArrayList<>();

	public void add(Test test) {
		tests.add(test);
	}

	public void add(Class<? extends TestCase> testcases) {
		for (Method m : testcases.getMethods()) {
			if (m.getName().startsWith("test")) {
				try {
					add(testcases.getConstructor(String.class).newInstance(m.getName()));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run(ReportingStrategy reporting) {
		for (Test test : tests) {
			test.run(reporting);
		}
	}

}
