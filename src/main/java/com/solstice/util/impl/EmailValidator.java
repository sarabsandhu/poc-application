/**
 * 
 */
package com.solstice.util.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.solstice.util.Validator;

/**
 * @author sarab
 *
 */
@Component
public class EmailValidator implements Validator<Boolean,String> {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	/* (non-Javadoc)
	 * @see com.solstice.util.Validator#validate(java.lang.String)
	 */
	@Override
	public Boolean validate(final String email) {

			matcher = pattern.matcher(email);
			return matcher.matches();

	}
	

}

