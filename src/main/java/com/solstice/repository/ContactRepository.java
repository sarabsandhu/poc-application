/**
 * 
 */
package com.solstice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solstice.model.Contact;

/**
 * Contact Repository
 * 
 * @author sarab
 */
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	//returns all contacts by email
	List<Contact> findByEmail(String email);
	
	//returns all contacts by personal phone number of work phone number
	List<Contact> findByPersonalPhoneNumber(String personalPhoneNumber);
	List<Contact> findByWorkPhoneNumber(String workPhoneNumber);
	
	//return all contacts which live in same city
	List<Contact> findByCity(String city);
	
	//return all contacts in same state
	List<Contact> findByState(String state);

}
