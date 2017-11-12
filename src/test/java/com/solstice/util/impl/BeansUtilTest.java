/**
 * 
 */
package com.solstice.util.impl;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.solstice.model.Contact;

/**
 * @author sarab
 *
 */
public class BeansUtilTest {
	
	@Test
	public void testCopyProperties() {
		Contact contact1 = new Contact(1L,"Mark","mark@noname.org","companyA","profileImagePath1","1999-07-29",
				"111-222-4443","111-333-5555","123 main st ","APT 114","chicago","IL","USA","60607");
		Contact contact2 = new Contact(2L,"John","johnnoname.org","companyB","profileImagePath2","1999-07-28",
				"111-222-4444","111-333-5556","123 state st ","APT 115","NYC","NY","USA","87782");
		BeanUtils.copyProperties(contact1, contact2,"id","email","personalPhoneNumber");
		
		
		System.out.println(contact2);
		
	}

}
