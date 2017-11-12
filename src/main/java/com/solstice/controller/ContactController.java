/**
 * 
 */
package com.solstice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solstice.exception.ContactErrorInfo;
import com.solstice.exception.ContactException;
import com.solstice.model.Contact;
import com.solstice.service.ContactService;


/**
 * Contacts Controller implements all contacts interface REST operations
 * 
 * Front Controller for handling all REST operations
 * @author sarab
 *
 */
@RestController
@RequestMapping("api/v1/")
public class ContactController {

	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	ContactService contactService;

	/*
	 * Search for a record by email or phone number ● Retrieve all records from the
	 * same state or city
	 */
	@RequestMapping(value = "contacts", method = RequestMethod.GET)
	@ResponseBody
	public List<Contact> findAll(@RequestParam(value = "search", required = false) String search)
			throws ContactException {
		logger.info("search request received with search string ", search);
		return contactService.findAll(search);
	}

	@RequestMapping(value = "contact", method = RequestMethod.POST)
	public Contact create(@RequestBody Contact contact) throws ContactException {
		logger.info("create request received with contact info ");
		logger.debug("contact : ", contact);
		return contactService.create(contact);
	}

	/*
	 * Get contact either by email or phone number(personal or work).
	 * 
	 * Note:In mapping, we specify path varible as {emailOrPhoneNumber:.+} instead of {emailOrPhoneNumber}
	 * because string after dot character gets truncated when we pass email address like sarab@ets.org.
	 * It will be received as sarab@ets only. .org will be removed from email address.
	 */
	@RequestMapping(value = "contact/{emailOrPhoneNumber:.+}", method = RequestMethod.GET)
	public Contact get(@PathVariable String emailOrPhoneNumber) throws ContactException {
		logger.info("retreival request received with contact email/phone-number ", emailOrPhoneNumber);
		Contact contact = null;

		contact = contactService.get(emailOrPhoneNumber);

		return contact;
	}

	@RequestMapping(value = "contact", method = RequestMethod.PUT)
	public Contact update(@RequestBody Contact contact) throws ContactException {
		logger.info("update contact request received with email", contact.getEmail());
		logger.debug("Updated contact Info : ", contact);
		return contactService.update(contact);
	}

	@RequestMapping(value = "contact/{emailOrPhoneNumber:.+}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String emailOrPhoneNumber) throws Exception{
		logger.info("delete contact request received with email/phone number ", emailOrPhoneNumber);
		contactService.delete(emailOrPhoneNumber);
	}

	/*
	 * This method is used to handle all exception thrown by this Controller. It will handle
	 * all exception of type Exception(or its child classes). It will send Http Status
	 * code of type BAD_REQUEST(400) back to client
	 * 
	 * @param req HttpServletRequest request received by controller.
	 * @param ex exception being thrown
	 * 
	 * @return ContactErrorInfo This return value will contain URL of request and exception/error message.
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	ContactErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {

		logger.error(ex.getMessage(), ex);
		return new ContactErrorInfo(req.getRequestURL().toString(), ex);

	}

}
