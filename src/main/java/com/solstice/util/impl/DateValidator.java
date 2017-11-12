/**
 * 
 */
package com.solstice.util.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import com.solstice.util.Validator;

/**
 * @author sarab
 *
 */
@Component
public class DateValidator implements Validator<Boolean,String> {
	
    private DateTimeFormatter formatter;

	public DateValidator() {
		formatter = DateTimeFormatter.ISO_DATE;
	}
	
	/* (non-Javadoc)
	 * @see com.solstice.util.Validator#validate(java.lang.String)
	 */
	@Override
	public Boolean validate(final String birthDate) {

			try {
			    LocalDate.parse(birthDate, formatter);
			    return true;
			}
			catch (DateTimeParseException exc) {
				return false;
			}
		
	}

}

