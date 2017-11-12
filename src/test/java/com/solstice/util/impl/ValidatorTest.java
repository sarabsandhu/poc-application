/**
 * 
 */
package com.solstice.util.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;

import com.solstice.model.Contact;
import com.solstice.util.Validator;

/**
 * @author sarab
 *
 */
public class ValidatorTest {

	private Validator<Boolean,String> emailValidator = new EmailValidator();
	private Validator<Boolean,String> phoneValidator = new PhoneValidator();
	private Validator<Boolean,String> dateValidator = new DateValidator();
	private Validator<List<String>,Contact> contactValidator = new ContactValidator(emailValidator,phoneValidator,dateValidator);
    
	/*
	 * basic 
	 */
	@Test
	public void testDateFormat() {
		String dob = "2000-12-03";
		assertThat(dateValidator.validate(dob), is(true));
		
	}

	@Test
	public void testEmailFormat() {
		String email = "sarab@ets.org";
		assertThat(emailValidator.validate(email), is(true));
		
	}
	
	@Test
	public void testPhoneFormat() {
		String phoneNumber = "111-222-8888";
		assertThat(phoneValidator.validate(phoneNumber), is(true));
		
	}
	
	/*
	 * check contact with all fields incorrect format
	 */
	@Test
	public void testContact() {
		Contact contact = new Contact(1L,"John","john@noname.org","companyA","profileImagePath","1999-07-29",
				"111-222-4444","111-333-5555","123 state st ","APT 114","chicago","IL","USA","60607");
		List<String> errorList = contactValidator.validate(contact);
		assertThat(errorList.size(), is(0));
		
	}
	
	/*
	 * check contact with email missing @ character
	 */
	@Test
	public void testContactWithWrongEmail() {
		Contact contact = new Contact(1L,"John","johnnoname.org","companyA","profileImagePath","1999-07-29",
				"111-222-4444","111-333-5555","123 state st ","APT 114","chicago","IL","USA","60607");
		List<String> errorList = contactValidator.validate(contact);
		assertThat(errorList.size(), is(1));
		
	}
	
	/*
	 * check contact with email missing @ character and wrong personal phone number as 111222-4444
	 */	
	@Test
	public void testContactWithWrongEmailAndPhoneNumber() {
		Contact contact = new Contact(1L,"John","johnnoname.org","companyA","profileImagePath","1999-07-29",
				"111222-4444","111-333-5555","123 state st ","APT 114","chicago","IL","USA","60607");
		List<String> errorList = contactValidator.validate(contact);
		assertThat(errorList.size(), is(2));
		
	}
	
	/*
	 * check contact with email missing @ character and wrong personal phone number as 111222-4444 and
	 * DOB as 199907-29
	 */	
	@Test
	public void testContactWithWrongEmailAndPhoneNumberAndDOB() {
		Contact contact = new Contact(1L,"John","johnnoname.org","companyA","profileImagePath","199907-29",
				"111222-4444","111-333-5555","123 state st ","APT 114","chicago","IL","USA","60607");
		List<String> errorList = contactValidator.validate(contact);
		assertThat(errorList.size(), is(3));
		
	}
}
