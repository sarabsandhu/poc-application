/**
 * 
 */
package com.solstice.exception;

/**
 * Root Exception for Contact REST API related exceptions
 * @author sarab
 */
public class ContactException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ContactException() {
		super();
	}

	public ContactException(final String errorMsg) {
		super(errorMsg);
	}

}
