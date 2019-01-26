package org.lensunit;

/**
 * The outcome of running a test.
 * 
 * @author leberre
 *
 */
public enum Outcome {

	
	/**
	 * The test pass.
	 */
	 OK, 
	 /**
	  * The test fails, i.e. the expected value is not the one computed.
	  */
	FAILURE, 
	/**
	 * Something went wrong during the test, something unexpected.
	 */
	ERROR;
}
