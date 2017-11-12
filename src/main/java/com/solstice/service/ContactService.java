/**
 * 
 */
package com.solstice.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solstice.exception.ContactException;
import com.solstice.exception.ContactNotFoundException;
import com.solstice.exception.DuplicateContactException;
import com.solstice.model.Contact;
import com.solstice.repository.ContactRepository;
import com.solstice.util.Validator;

/**
 * 
 * This is service for different operations where you want to set you
 * transaction boundaries ( if you are doing anything transactional ). You can
 * use Spring AOP for handling transactions
 * 
 * @author sarab
 *
 */
@Service
public class ContactService {

	private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

	/*
	 * search pattern for Contacts search. compiling here so that we dont have do it
	 * for each request coming from user. Attribute and value is separated by colon
	 * (:). sameples values (similar to below) are passed with REST url query. e.g
	 * search=city:chicago , search=state:OH
	 */
	private static final Pattern searchPatternForCityOrState = Pattern.compile("(\\w+?):(\\w+?),");
	
	private static final String CITY= "city";
	private static final String STATE= "state";
	
	private static final String ID= "id";
	private static final String EMAIL= "email";
	private static final String PERSONAL_PHONE_NUMBER= "personalPhoneNumber";
	

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private Validator<Boolean, String> emailValidator;

	@Autowired
	private Validator<Boolean, String> phoneValidator;

	@Autowired
	private Validator<List<String>, Contact> contactValidator;

	/*
	 * search for list of contacts based on search criteria. eg. you can search by
	 * either city or state and get list of contacts which live in same city or
	 * state. Currently it handles only seach for city and state but it can be made
	 * more generic (based on requirements) where you can any number of attrbtes
	 * together
	 * 
	 * @param search this parameter can be used to pass search attribute e.g
	 * search=city:chicago
	 * 
	 */
	public List<Contact> findAll(final String search) throws ContactException {

		List<Contact> contactList = null;

		// if no search was specified , just return list of all contacts in repo
		if (search == null) {
			
			logger.info("returning list of all contacts in repo");
			contactList = contactRepository.findAll();

		} else {
			
			logger.info("searching list of contacts in repo for search ", search);
			Matcher matcher = searchPatternForCityOrState.matcher(search + ",");
			logger.debug("search=", search);

			if (matcher.find()) {

				String searchAttribute = matcher.group(1);
				String searchAttributeValue = matcher.group(2);

				if (searchAttributeValue == null || "".equalsIgnoreCase(searchAttributeValue.trim())) {
					throw new ContactException(String.join(" ","Invalid search value specified for",searchAttribute));
				}

				if (CITY.equalsIgnoreCase(searchAttribute)) {
					
					logger.info("searching list of contacts in city ", searchAttributeValue);
					contactList = contactRepository.findByCity(searchAttributeValue);

				} else if (STATE.equalsIgnoreCase(searchAttribute)) {
					
					logger.info("searching list of contacts in state ", searchAttributeValue);
					contactList = contactRepository.findByState(searchAttributeValue);

				} else {
					throw new ContactException("Invalid search criteria specified");
				}

			}else {
				throw new ContactException("Invalid search criteria specified");
			}
		}

		if (contactList == null || contactList.isEmpty())
			throw new ContactNotFoundException("no contact(s) found");

		return contactList;
	}

	/*
	 * creates new contact in repository
	 * 
	 * @param contact it contains information for new contact to be created
	 */
	public Contact create(Contact contact) throws ContactException {
		
		//this will check if contact is duplicate and throw exception if true
		checkIfDuplicateContact(contact);
		
		List<String> errorList = contactValidator.validate(contact);

		if (errorList == null || errorList.isEmpty()) {
			logger.info("creating new contact with email ",contact.getEmail());
			logger.debug(contact.toString());
			return contactRepository.saveAndFlush(contact);

		} else {
			throw new ContactNotFoundException(String.join(" , ", errorList));
		}
	}
	
	private void checkIfDuplicateContact(Contact contact) throws ContactException {
		
		List<Contact> contactList = contactRepository.findByEmail(contact.getEmail());
		if(!(contactList == null || contactList.isEmpty())) {
			throw new DuplicateContactException(String.join(" ","Contact with email",contact.getEmail(),"already exists"));
		}
		
		contactList = contactRepository.findByPersonalPhoneNumber(contact.getPersonalPhoneNumber());
		if(!(contactList == null || contactList.isEmpty())) {
			throw new DuplicateContactException(String.join(" ","Contact with personal phone number",contact.getPersonalPhoneNumber(),"already exists"));
		}
		
		
	}

	/*
	 * find contact by email/phone number.
	 * 
	 * @param emailOrPhoneNumber this contains either email address or phone number
	 * for searhing through repository
	 */
	public Contact get(String emailOrPhoneNumber) throws ContactException {
		
		logger.info("emailOrPhoneNumber=" + emailOrPhoneNumber);

		List<Contact> contactList = null;

		// check if supplied search parameter is valid email
		if (emailValidator.validate(emailOrPhoneNumber)) {

			logger.info("searching by Email");
			contactList = contactRepository.findByEmail(emailOrPhoneNumber);

			// check if supplied search parameter is valid Phone number
		} else if (phoneValidator.validate(emailOrPhoneNumber)) {

			logger.info("searching by Personal Phone Number");
			contactList = contactRepository.findByPersonalPhoneNumber(emailOrPhoneNumber);

		} else {
			throw new ContactNotFoundException("Invalid email/phone number provided for contact lookup");
		}

		if (contactList == null || contactList.isEmpty())
			throw new ContactNotFoundException("no contact(s) found with given email/phonenumber");
		else {
			if (contactList.size() == 1)
				return contactList.get(0);
			else {
				
				/*
				 * we are assuming that this will never happen when there are two(or more) contacts with either same
				 * email or same personal phone number. Further problem analysis/logic/handling will be required
				 * in this scenario. I am just simply throwing exception in this case. It is certainly possible that 
				 * two contacts may have same work phone number (main office line)
				 * 
				 */
				
				throw new ContactException(String.join(" ", "Multiple contcats found for give email/phone muber",
						emailOrPhoneNumber));
			}
		}

	}

	/*
	 * update the contact with given Contact Information. Assuming that complete contact information
	 * will be sent as part of update information (including attributes which are not
	 * changed). Most of time UI contains all information and sends all attributes
	 * (including changes). logic can be changed to receive only updated fields of
	 * Contact and update changed attributes only.
	 * 
	 * @param contact it contains update information.
	 */
	public Contact update(Contact contact) throws ContactException {

		List<String> errorList = contactValidator.validate(contact);

		if (!(errorList == null || errorList.isEmpty())) {
			logger.error("Problem with provided contact data for update");
			throw new ContactException(String.join(" , ", errorList));

		} else {
			
			// find the current Contact by provided email since we already validated contact information
			// we have validated email already.
			Contact currentContact = get(contact.getEmail());

			// if current contact is null, inform user about non-existent contact
			if (currentContact == null) {
				throw new ContactNotFoundException("no contact(s) found with given email/phonenumber");
			}

			/*
			 * Copy all properties from provided contact to current contact retrieved from repository
			 * except id, email and personal phone number which are unique attributes.
			 */

			BeanUtils.copyProperties(contact, currentContact,ID,EMAIL,PERSONAL_PHONE_NUMBER);
			
			logger.debug("Saving Updated contact info ",currentContact);
			// save and flush it back to repo after update
			return contactRepository.saveAndFlush(currentContact);
		}
	}

	/*
	 * delete contact for given id
	 */
	public void delete(String emailOrPhoneNumber) throws Exception {
		// find contact by email/phone number
		Contact currentContact = get(emailOrPhoneNumber);
		
		//no need to check if currentContact found or not.
		//get function takes care of throwing excption if contact not found
		// issue delete command retrieved contact
		contactRepository.delete(currentContact);
	}
}
