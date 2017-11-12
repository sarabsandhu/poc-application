/**
 * 
 */
package com.solstice.util;

/**
 * @author sarab
 *
 */
public interface Validator<T,R> {
	
	T validate(final R r);

}
