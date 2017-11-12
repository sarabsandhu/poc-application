# poc-application
POC application

How to run the application
---------------------------------------------------------------------------------------------------------

1. run on default port 8585

	java -jar solstice-poc-application-1.0.0.jar --DB_URL=jdbc:h2:~/solsticepoc --DB_USERNAME=sa --DB_PASSWORD

2. run on different port like 8686

	java -jar solstice-poc-application-1.0.0.jar  --DB_URL=jdbc:h2:~/solsticepoc --DB_USERNAME=sa --DB_PASSWORD --server.port=8686

3. run on different port like 8686 and specify log file name to something different like solstice.log.
	
	java -jar solstice-poc-application-1.0.0.jar  --DB_URL=jdbc:h2:~/solsticepoc --DB_USERNAME=sa --DB_PASSWORD --server.port=8686 --logging.file=solstice.log



Notes
---------------------------------------------------------------------------------------------------------

1. Application will be running on port 8585.
2. Application log will stored in same directory where application was started using terminal/command prompt.
3. Application log will be at INFO level by default.
4. Application log name will be solstice-poc-app.log.
5. Contact table will be created by default on application startup and populated with sample data (provided in end section)



Schema/Sample Data Assumptions
---------------------------------------------------------------------------------------------------------

1. Phone numbers format should be 10-digit like 111-222-3333.
2. Date of Birth will be stored in string format to simplify poc implementation.
3. Date of Birth will be stored in ISO date format like 2000-05-29 (yyyy-mm-dd).
3. Email/Personal phone number will be unique attributes for contact. 
4. update contact api call will not update email/personal phone number attributes. These will be simply ignored if 
   submitted with update operation. 
5. It will be assumed that profile photo/image will be stored on persistent storage like s3 so table will only
   store location of this profile photo (e.g. on s3). It will be a URI.
6. Assuming that I don't need to implement security of API as it was not mentioned in assignment. Again simplifying 
   it to reduce scope of implementation.




How to test the application 
---------------------------------------------------------------------------------------------------------

 You can use curl on unix terminal to make REST calls (GET,POST,PUT,DELETE) for below urls after application startup. You can also use any other rest client of your choice. I used Advanced REST client in chrome apps.
 
 1. search by city or state
 
	a) Valid requests
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contacts
		
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contacts?search=city:NYC
		
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contacts?search=state:NY
		
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contacts?search=state:OH
	
	b) Invalid requests, invalid values
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contacts?search=city:
		
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contacts?search=state:
	
	c) invalid parameter
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contacts?search=phonenumber:


 2. search by email/phone number using GET Requests

	a) valid requests
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/brian@noname.org
		
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/111-222-4444
	
	c) invalid requests like missing @ character from email
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/briannoname.org
	
	d) invalid phone number, missing - in first part of phone
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/111222-3333
	
3. delete a contact (using HTTP DELETE method)

	a) delete using id of contact which is retrieved using other GET queries
	
		DELETE http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/1

4. create a contact (using HTTP POST method)
	
	a) valid requests
	
	    POST http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact (with contact information in body(json format) like below)
		
		{  
		   "name":"Brian",
		   "email":"brian@noname.org",
		   "company":"companyC",
		   "profileImage":"~/profile/brian/image",
		   "birthDate":"2000-07-27",
		   "workPhoneNumber":"111-222-4446",
		   "personalPhoneNumber":"111-333-5557",
		   "addressLine1":"1234 clark st",
		   "addressLine2":"APT 116",
		   "city":"cleveland",
		   "state":"OH",
		   "country":"USA",
		   "zipcode":"53749"
		}
	
	b) invalid requests
	
	   try passing invalid email/personal phone number/date of birth in passed json
	
5. update a contact 

	a) valid request

	PUT http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/1 (with contact information in below json format)
	{  
	   "name":"Brian Maher",
	   "email":"brian@noname.org",
	   "company":"companyD",
	   "profileImage":"~/profile/brian/image",
	   "birthDate":"2000-07-28",
	   "workPhoneNumber":"111-222-4445",
	   "personalPhoneNumber":"111-333-5557",
	   "addressLine1":"1234 clark st",
	   "addressLine2":"APT 116",
	   "city":"cincinatti",
	   "state":"OH",
	   "country":"USA",
	   "zipcode":"78297"
	}


6. Sample Data initialized at startup

	{
		"name": "John",
		"email": "john@noname.org",
		"company": "companyA",
		"profileImage": "~/profile/john/image",
		"birthDate": "2000-07-29",
		"workPhoneNumber": "111-222-4444",
		"personalPhoneNumber": "111-333-5555",
		"addressLine1": "123 state st",
		"addressLine2": "APT 114",
		"city": "Chicago",
		"state": "IL",
		"country": "USA",
		"zipcode": "60607"
	},
	{
		"name": "Mike",
		"email": "mike@noname.org",
		"company": "companyB",
		"profileImage": "~/profile/mike/image",
		"birthDate": "2000-07-28",
		"workPhoneNumber": "111-222-4445",
		"personalPhoneNumber": "111-333-5556",
		"addressLine1": "123 main st",
		"addressLine2": "APT 115",
		"city": "NYC",
		"state": "NY",
		"country": "USA",
		"zipcode": "29483"
	},
	{
		"name": "Brian",
		"email": "brian@noname.org",
		"company": "companyC",
		"profileImage": "~/profile/brian/image",
		"birthDate": "2000-07-27",
		"workPhoneNumber": "111-222-4446",
		"personalPhoneNumber": "111-333-5557",
		"addressLine1": "1234 clark st",
		"addressLine2": "APT 116",
		"city": "cleveland",
		"state": "OH",
		"country": "USA",
		"zipcode": "53749"
	},
	{
		"name": "Patrick",
		"email": "patrick@noname.org",
		"company": "companyD",
		"profileImage": "~/profile/patrick/image",
		"birthDate": "2000-07-26",
		"workPhoneNumber": "111-222-4447",
		"personalPhoneNumber": "111-333-5558",
		"addressLine1": "189 sun st",
		"addressLine2": "APT 211",
		"city": "cleveland",
		"state": "OH",
		"country": "USA",
		"zipcode": "53749"
	},
	{

		"name": "Mark",
		"email": "mark@noname.org",
		"company": "companyE",
		"profileImage": "~/profile/mark/image",
		"birthDate": "2000-07-25",
		"workPhoneNumber": "111-222-4448",
		"personalPhoneNumber": "111-333-5559",
		"addressLine1": "18 dearborn st",
		"addressLine2": "APT 212",
		"city": "NYC",
		"state": "NY",
		"country": "USA",
		"zipcode": "29483"
	},
	{

		"name": "Harry",
		"email": "harry@noname.org",
		"company": "companyF",
		"profileImage": "~/profile/harry/image",
		"birthDate": "2000-07-24",
		"workPhoneNumber": "111-222-4449",
		"personalPhoneNumber": "111-333-5554",
		"addressLine1": "123 wine st",
		"addressLine2": "APT 213",
		"city": "Chicago",
		"state": "IL",
		"country": "USA",
		"zipcode": "60607"
	}
	
