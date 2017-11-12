# poc-application
POC application

Description/Notes
---------------------------------------------------------------------------------------------------------

1. There will be two types of urls for app functionality (shown below). First URI implements GET,POST,PUT,DELETE to implement
   single contact operations like retrieve, create, update and delete respectively. Second url will implement functionality
   where we will be dealing with multiple contacts. This API only implements GET operation for second URL to retrieve list 
   of contacts based on search criteria. Sample requests are provided in "How to test the application" section below.


    a) http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact
    

    b) http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contacts
    
    
2. Application will be running on port 8585.
3. Application log will stored in same directory where application was started using terminal/command prompt.
4. Application log will be at INFO level by default.
5. Application log name will be solstice-poc-app.log by default.
6. Contact table will be created by default on application startup using h2 database (http://www.h2database.com/html/main.html) and populated with sample data (provided at end).


Schema/Sample Data Assumptions
---------------------------------------------------------------------------------------------------------

1. Phone numbers format will be 10-digit like 111-222-3333.
2. Date of Birth will be stored in string format to simplify poc implementation.
3. Date of Birth will be stored in ISO date format like 2000-05-29 (yyyy-mm-dd).
3. Email/Personal phone number will be unique attributes for contact. You will be only able to search by email or personal phone number.
   You will not be able to search a contact by work phone number as it is being assumed that two contacts can have same work phone number.
   Functionality of API will need to be enhanced if you want to search by work phone number.
4. Update contact api call will not update email/personal phone number attributes. These will be simply ignored if 
   submitted with update operation. 
5. It will be assumed that profile photo/image will be stored on persistent storage like s3 so table will only
   store location of this profile photo (e.g. on s3). It will be a URI.
6. Assuming that I don't need to implement security of API as it was not mentioned in assignment. Again simplifying 
   it to reduce scope of implementation.
   

How to build the application
---------------------------------------------------------------------------------------------------------

Application is built Spring Boot/MVC and h2 database (http://www.h2database.com/html/main.html).
 
You will need JDK 1.8, Maven and git installed to build the application. Make sure JAVA_HOME and MAVEN_HOME environment variables are set properly. Make sure that these tools are available on your PATH environment variable.

1. Clone application repo locally

   git clone https://github.com/sarabsandhu/poc-application.git
   
2. Change your directory to poc-application folder and run below command. it will build application along side running your test cases.

	mvn clean package

3. solstice-poc-application-1.0.0.jar will be available under target folder on build success from previous step.


How to run the application
---------------------------------------------------------------------------------------------------------

Note: you need JDK/JRE 1.8 on your PATH environment variable for this step.

1. run on default port 8585

	java -jar solstice-poc-application-1.0.0.jar --DB_URL=jdbc:h2:~/solsticepoc --DB_USERNAME=sa --DB_PASSWORD

2. run on different port like 8686

	java -jar solstice-poc-application-1.0.0.jar  --DB_URL=jdbc:h2:~/solsticepoc --DB_USERNAME=sa --DB_PASSWORD --server.port=8686

3. run on different port like 8686 and specify log file name to something different like solstice.log.
	
	java -jar solstice-poc-application-1.0.0.jar  --DB_URL=jdbc:h2:~/solsticepoc --DB_USERNAME=sa --DB_PASSWORD --server.port=8686 --logging.file=solstice.log


How to test the application 
---------------------------------------------------------------------------------------------------------

 You can use curl on unix terminal to make REST calls (GET,POST,PUT,DELETE) for below urls after application startup. You can also use any other rest client of your choice. I used Advanced REST client in chrome apps.
 
 1. search by city or state ( FYI city and state search values are case-sensitive. OH will work but oh will not work. )
 
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


 2. search by email/phone number using GET Requests. Note. you can only search by personal phone number. DO NOT mix up work phone number with  personal phone number. it is not supported right now.

	a) valid requests
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/brian@noname.org
		
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/111-222-4444 
	
	c) invalid requests like missing @ character from email
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/briannoname.org
	
	d) invalid phone number, missing - in first part of phone
	
		GET http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/111222-3333
	
3. delete a contact (using HTTP DELETE method)

	a) delete using email/personal phone number of contact (which is retrieved using other GET queries)
	
		DELETE http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact/brian@noname.org

4. create a contact (using HTTP POST method)
	
	a) valid requests
	
	    POST http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact 
	    
	    (with contact information provided in body of POST request ( in json format))
		
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
	
	   try passing invalid email/personal-phone-number/date-of-birth in input json. You should get detailed error for each attribute.
	
5. update a contact 

	a) valid request (with contact information in body shown in below json format).

		PUT http://<Your_IP_ADDRESS/localhost>:8585/api/v1/contact
	
		(with contact information provided in body of POST request ( in json format))
		
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
	
