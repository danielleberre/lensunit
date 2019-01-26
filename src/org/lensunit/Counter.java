package org.lensunit;

public class Counter {
	private int value = 0;
	
	public void inc() {
		value++;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public String  toString() {
		return Integer.toString(value);
	}
}
