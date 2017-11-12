/**
 * 
 */
package com.solstice.exception;

/**
 * Error object returned in case there is exception
 * from Contact REST API
 * @author sarab
 *
 */
public final class ContactErrorInfo {
    private final String url;
	private final String ex;
	
    public ContactErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }
    
    public String getUrl() {
		return url;
	}

	public String getEx() {
		return ex;
	}

}
