package com.solstice.web.integration;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.solstice.model.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class EndpointIntegrationTest {
	
    @Autowired
    private TestRestTemplate restTemplate;
	
    /*
     * H2 DB will be initialized with 6 contacts. 
     * testing if we can retrieve those contacts
     */
	@Test
	public void testContactsNode() throws IOException{
		
		ResponseEntity<List<Contact>> response = this.restTemplate.exchange("/api/v1/contacts",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Contact>>() {});
        
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
		
		List<Contact> contactList = response.getBody();
		
		assertThat(contactList.size(), greaterThanOrEqualTo(0));
	}
	
    /*
     * H2 DB will be initialized with 6 contacts. 
     * Testing with specific email of contact of
     * which is part of this collection
     * 
     */
	@Test
	public void testContactGetRequest() throws IOException{
		
		Contact contact = this.restTemplate.getForObject("/api/v1/contact/john@noname.org", com.solstice.model.Contact.class);
		
		assertThat(contact.getEmail(),equalTo("john@noname.org"));
	}

}
