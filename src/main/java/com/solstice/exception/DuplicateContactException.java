/**
 * 
 */
package com.solstice.exception;

/**
 * Exception thrown when contact already exists in repo on insertion
 * or (update when you trying to update email/personal phone number/
 * or work phone number)
 * @author sarab
 *
 */
public class DuplicateContactException extends ContactException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateContactException() {
		super();
	}
	
	public DuplicateContactException(final String errorMsg) {
		super(errorMsg);
	}
	
}
