package org.ds.practises.jdk7.features.klass;


/**
 *
 */
public class TestByteCode {

	public int id = 0;


	/**
	 * 
	 */
	public TestByteCode() {
		id++;
	}


	public int testMutil(int i) {
		return i * 32;
	}
}
