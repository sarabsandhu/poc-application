/**
 * 
 */
package com.solstice.util.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.solstice.model.Contact;
import com.solstice.util.Validator;

/**
 * @author sarab
 *
 */
@Component
public class ContactValidator implements Validator<List<String>, Contact> {

	@Autowired
	private Validator<Boolean, String> emailValidator;

	@Autowired
	private Validator<Boolean, String> phoneValidator;

	@Autowired
	private Validator<Boolean, String> dateValidator;


	public ContactValidator() {
	}
	
	//this is for Junit testing only without context loading
	public ContactValidator(Validator<Boolean, String> emailValidator,
			Validator<Boolean, String> phoneValidator,
			Validator<Boolean, String> dateValidator) {
		this.emailValidator = emailValidator;
		this.phoneValidator = phoneValidator;
		this.dateValidator = dateValidator;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.solstice.util.Validator#validate(java.lang.String)
	 */
	@Override
	public List<String> validate(final Contact o) {
		List<String> errorMessages = new ArrayList<String>();

		Contact contact = (Contact) o;

		if (!emailValidator.validate(contact.getEmail())) {
			errorMessages.add(String.join(" ",contact.getEmail()," is invalid. correct format is someone@noname.aaa"));
		}

		if (!phoneValidator.validate(contact.getPersonalPhoneNumber())) {
			errorMessages.add(String.join(" ",contact.getPersonalPhoneNumber()," is invalid. correct format is ddd-ddd-dddd"));
		}

		if (!phoneValidator.validate(contact.getWorkPhoneNumber())) {
			errorMessages.add(String.join(" ",contact.getWorkPhoneNumber()," is invalid. correct format is ddd-ddd-dddd"));
		}

		if (!dateValidator.validate(contact.getBirthDate())) {
			errorMessages.add(String.join(" ",contact.getBirthDate()," is invalid. correct format is yyyy-mm-dd"));
		}

		// you can do other validations here to make sure that Contact data being
		// supplied is correct.
		
		return errorMessages;
	}

}
