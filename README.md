# poc-application
POC application

How to run the app
---------------------------------------------------------------------------------------------------------

1. run on default port 8585
java -jar solstice-poc-application-0.0.1.jar

2. run on different port like 8686
java -jar target/solstice-poc-application-0.0.1.jar --server.port=8686

3. specify log file name to something different like solstice.log.
java -jar target/solstice-poc-application-0.0.1.jar --server.port=8686 --logging.file=solstice.log



Notes
---------------------------------------------------------------------------------------------------------

1. Application will be running on port 8585.
2. Application log will stored in same directory where application was started using terminal/command prompt.
3. Application log will at debug level by default.
4. Application log name will be solstice-poc-app.log.
5. Contact table will be created by default on application startup and populated with sample data (given at end)



Schema/Sample Data Assumptions
---------------------------------------------------------------------------------------------------------

1. Phone numbers should be in format 111-222-3333.
2. Date of Birth will be stored in string format to simplify poc implementation.
3. Date of Birth will be stored in ISO date format like 2000-05-29 (yyyy-mm-dd).
3. Email/Personal phone number will be unique attributes for contact. 
4. update contact api call will not update email/personal phone number attributes. These will be simply ignored if 
   submitted with update operation. 
5. It will be assumed that profile photo/image will be stored on persistent storage like s3 so table will only
   store location of this profile photo on s3. It can be URL.
6. Assuming that I don't need to implement security of API as it was not mentioned in assignment. Again simplifying 
   it to reduce scope of implementation.




How to test the application 
---------------------------------------------------------------------------------------------------------

 You can use curl on unix terminal to make REST calls for below urls. You can use any other rest client. 
 
 1. search by city or state
 
	a) Valid requests
	
		GET http://localhost:8585/api/v1/contacts
		
		GET http://localhost:8585/api/v1/contacts?search=city:NYC
		
		GET http://localhost:8585/api/v1/contacts?search=state:NY
		
		GET http://localhost:8585/api/v1/contacts?search=state:OH
	
	b) Invalid requests, invalid values
	
		GET http://localhost:8585/api/v1/contacts?search=city:
		
		GET http://localhost:8585/api/v1/contacts?search=state:
	
	c) invalid parameter
	
		GET http://localhost:8686/api/v1/contacts?search=phonenumber:


 2. search by email/phone number using GET Requests

	a) valid requests
	
		GET http://localhost:8585/api/v1/contact/brian@noname.org
		
		GET http://localhost:8585/api/v1/contact/111-222-4444
	
	c) invalid requests like missing @ character from email
	
		GET http://localhost:8686/api/v1/contact/briannoname.org
	
	d) invalid phone number, missing - in first part of phone
	
		GET http://localhost:8585/api/v1/contact/111222-3333
	
3. delete a contact

	a) delete using id of contact which is retrieved using other GET queries
	
		GET DELETE http://localhost:8585/api/v1/contact/1

4. create a contact
	
	a) valid requests
	
		GET POST http://localhost:8585/api/v1/contact (with contact information in below json format)
		
		{"name":"Brian","email":"brian@noname.org","company":"companyC","profileImage":"~/profile/brian/image","birthDate":"2000-07-27","workPhoneNumber":"111-222-4446","personalPhoneNumber":"111-333-5557","addressLine1":"1234 clark st","addressLine2":"APT 116","city":"cleveland","state":"OH","country":"USA","zipcode":"53749"}
	
	b) invalid requests
	
	   try passing invalid email/personal phone number/date of birth in passed json
	
5. update a contact 

	a) valid request

	PUT http://localhost:8585/api/v1/contact/1 (with contact information in below json format)

	{"name":"Brian Maher","email":"brian@noname.org","company":"companyD","profileImage":"~/profile/brian/image","birthDate":"2000-07-28","workPhoneNumber":"111-222-4445","personalPhoneNumber":"111-333-5557","addressLine1":"1234 clark st","addressLine2":"APT 116","city":"cincinatti","state":"OH","country":"USA","zipcode":"78297"}


6. Sample Data initialized at startup

(NAME     ,EMAIL               ,COMPANY    ,PROFILE_IMAGE            ,BIRTH_DATE   ,WORK_PHONE_NUMBER ,PERSONAL_PHONE_NUMBER ,ADDRESS_LINE1   ,ADDRESS_LINE2 ,CITY       ,STATE ,COUNTRY ,ZIPCODE)
(John   ,john@noname.org   ,companyA ,~/profile/john/image   ,2000-07-29 ,111-222-4444    ,111-333-5555        ,123 state st  ,APT 114     ,Chicago  ,IL  ,USA   ,60607)
(Mike   ,mike@noname.org   ,companyB ,~/profile/mike/image   ,2000-07-28 ,111-222-4445    ,111-333-5556        ,123 main st   ,APT 115     ,NYC      ,NY  ,USA   ,29483)
(Brian  ,brian@noname.org  ,companyC ,~/profile/brian/image  ,2000-07-27 ,111-222-4446    ,111-333-5557        ,1234 clark st ,APT 116     ,cleveland,OH  ,USA   ,53749)
(Patrick,patrick@noname.org,companyD ,~/profile/patrick/image,2000-07-26 ,111-222-4447    ,111-333-5558        ,189 sun st    ,APT 211     ,cleveland,OH  ,USA   ,53749)
(Mark   ,mark@noname.org   ,companyE ,~/profile/mark/image   ,2000-07-25 ,111-222-4448    ,111-333-5559        ,18 dearborn st,APT 212     ,NYC      ,NY  ,USA   ,29483)
(Harry  ,harry@noname.org  ,companyF ,~/profile/harry/image  ,2000-07-24 ,111-222-4449    ,111-333-5554        ,123 wine st   ,APT 213     ,Chicago  ,IL  ,USA   ,60607)
