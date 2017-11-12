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
public class PhoneValidator implements Validator<Boolean,String> {

	private Pattern pattern;
	private Matcher matcher;
	
	//This is basic phone validation but regular expression can be
	//expaneded to include more complex cases.
	private static final String PHONE_PATTERN ="\\d{3}-\\d{3}-\\d{4}";
	
	public PhoneValidator() {
		pattern = Pattern.compile(PHONE_PATTERN);
	}
	
	/* (non-Javadoc)
	 * @see com.solstice.util.Validator#validate(java.lang.String)
	 */
	@Override
	public Boolean validate(final String phoneNumber) {

			matcher = pattern.matcher(phoneNumber);
			return matcher.matches();

	}

}
