/**
 * 
 */
package com.solstice.exception;

/**
 * Exception thrown when contact is not found on query
 * @author sarab
 *
 */
public class ContactNotFoundException extends ContactException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ContactNotFoundException() {
		super();
	}
	
	public ContactNotFoundException(final String errorMsg) {
		super(errorMsg);
	}
	
}
