/**
 * 
 */
package com.solstice.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.solstice.model.Contact;

/**
 * 
 * Stub class for unit testing
 * @author sarab
 *
 */
public class ContactStubRepository {
	private static Map<Long,Contact> contacts = new HashMap<Long,Contact>();
	
	
	static {
		
		contacts.put(1L,new Contact(1L,"John","john@noname.org","companyA","profileImagePath","1999-07-29",
				"111-222-4444","111-333-5555","123 state st ","APT 114","chicago","IL","USA","60607"));
		contacts.put(2L,new Contact(2L,"John2","john2@noname.org","companyB","profileImagePath1","7-28-1999",
				"111-222-4445","111-333-5556","124 state st ","APT 115","chicago","IL","USA","60607"));
		contacts.put(3L,new Contact(3L,"John3","john3@noname.org","companyC","profileImagePath2","7-27-1999",
				"111-222-4446","111-333-5557","125 state st ","APT 116","chicago","IL","USA","60607"));
		contacts.put(4L,new Contact(4L,"John4","john4@noname.org","companyD","profileImagePath3","7-26-1999",
				"111-222-4447","111-333-5558","126 state st ","APT 117","chicago","IL","USA","60607"));
		
	}
	
	public static List<Contact> list(){
		return new ArrayList<Contact>(contacts.values());
	}
	
	public static Contact create(Contact contact){
		return contacts.putIfAbsent(contact.getId(), contact);
	}
	
	public static Contact get(Long id){
			return contacts.get(id);
	}
	
	public static Contact update(Long id, Contact contact){
		return contacts.put(contact.getId(),contact);
	}
	
	public static Contact delete(Long id){
		return contacts.remove(id);
	}

}
