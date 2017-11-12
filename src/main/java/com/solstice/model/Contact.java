/**
 * 
 */
package com.solstice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author sarab
 * The contact record should represent the following information:
 * name, company, profile image, email,birthdate, phone number (work, personal) and address.
 */
@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/*
	 * rest of fields will be mapped to databse table as they are named here
	 * so there is no explicit need for annotations here
	 */
	private String name;
	private String email;
	private String company;
	private String profileImage;
	private String birthDate;
	
	//contact information
	private String workPhoneNumber;
	private String personalPhoneNumber;

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	
	public Contact() {
		
	}

	public Contact(Long id, String name, String email, String company, String profileImage, String birthDate,
			String workPhoneNumber, String personalPhoneNumber,String addressLine1,String addressLine2,
			String city,String state, String country,String zipcode) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.company = company;
		this.profileImage = profileImage;
		this.birthDate = birthDate;
		this.workPhoneNumber = workPhoneNumber;
		this.personalPhoneNumber = personalPhoneNumber;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getCompany() {
		return company;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}
	public String getPersonalPhoneNumber() {
		return personalPhoneNumber;
	}
	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getZipcode() {
		return zipcode;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}
	public void setPersonalPhoneNumber(String personalPhoneNumber) {
		this.personalPhoneNumber = personalPhoneNumber;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((personalPhoneNumber == null) ? 0 : personalPhoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (personalPhoneNumber == null) {
			if (other.personalPhoneNumber != null)
				return false;
		} else if (!personalPhoneNumber.equals(other.personalPhoneNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", company=" + company + ", profileImage="
				+ profileImage + ", birthDate=" + birthDate + ", workPhoneNumber=" + workPhoneNumber
				+ ", personalPhoneNumber=" + personalPhoneNumber + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", city=" + city + ", state=" + state + ", country=" + country + ", zipcode=" + zipcode
				+ "]";
	}
	
}
