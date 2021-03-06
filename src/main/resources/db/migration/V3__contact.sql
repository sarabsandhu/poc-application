create table contact(
	ID INT AUTO_INCREMENT,
	NAME VARCHAR(50),
	EMAIL VARCHAR(50),
	COMPANY VARCHAR(100),
	PROFILE_IMAGE VARCHAR(255),
	BIRTH_DATE VARCHAR(20),
	WORK_PHONE_NUMBER VARCHAR(20),
	PERSONAL_PHONE_NUMBER VARCHAR(20),
	ADDRESS_LINE1 VARCHAR(100),
	ADDRESS_LINE2 VARCHAR(100),
	CITY VARCHAR(50),
	STATE VARCHAR(20),
	COUNTRY VARCHAR(30),
	ZIPCODE VARCHAR(10)
);

insert into contact (NAME,EMAIL,COMPANY,PROFILE_IMAGE,BIRTH_DATE,WORK_PHONE_NUMBER,PERSONAL_PHONE_NUMBER,ADDRESS_LINE1,ADDRESS_LINE2,CITY,STATE,COUNTRY,ZIPCODE) values ('John','john@noname.org','companyA','~/profile/john/image','2000-07-29','111-222-4444','111-333-5555','123 state st','APT 114','Chicago','IL','USA','60607');
insert into contact (NAME,EMAIL,COMPANY,PROFILE_IMAGE,BIRTH_DATE,WORK_PHONE_NUMBER,PERSONAL_PHONE_NUMBER,ADDRESS_LINE1,ADDRESS_LINE2,CITY,STATE,COUNTRY,ZIPCODE) values ('Mike','mike@noname.org','companyB','~/profile/mike/image','2000-07-28','111-222-4445','111-333-5556','123 main st','APT 115','NYC','NY','USA','29483');
insert into contact (NAME,EMAIL,COMPANY,PROFILE_IMAGE,BIRTH_DATE,WORK_PHONE_NUMBER,PERSONAL_PHONE_NUMBER,ADDRESS_LINE1,ADDRESS_LINE2,CITY,STATE,COUNTRY,ZIPCODE) values ('Brian','brian@noname.org','companyC','~/profile/brian/image','2000-07-27','111-222-4446','111-333-5557','1234 clark st','APT 116','cleveland','OH','USA','53749');
insert into contact (NAME,EMAIL,COMPANY,PROFILE_IMAGE,BIRTH_DATE,WORK_PHONE_NUMBER,PERSONAL_PHONE_NUMBER,ADDRESS_LINE1,ADDRESS_LINE2,CITY,STATE,COUNTRY,ZIPCODE) values ('Patrick','patrick@noname.org','companyD','~/profile/patrick/image','2000-07-26','111-222-4447','111-333-5558','189 sun st','APT 211','cleveland','OH','USA','53749');
insert into contact (NAME,EMAIL,COMPANY,PROFILE_IMAGE,BIRTH_DATE,WORK_PHONE_NUMBER,PERSONAL_PHONE_NUMBER,ADDRESS_LINE1,ADDRESS_LINE2,CITY,STATE,COUNTRY,ZIPCODE) values ('Mark','mark@noname.org','companyE','~/profile/mark/image','2000-07-25','111-222-4448','111-333-5559','18 dearborn st','APT 212','NYC','NY','USA','29483');
insert into contact (NAME,EMAIL,COMPANY,PROFILE_IMAGE,BIRTH_DATE,WORK_PHONE_NUMBER,PERSONAL_PHONE_NUMBER,ADDRESS_LINE1,ADDRESS_LINE2,CITY,STATE,COUNTRY,ZIPCODE) values ('Harry','harry@noname.org','companyF','~/profile/harry/image','2000-07-24','111-222-4449','111-333-5554','123 wine st','APT 213','Chicago','IL','USA','60607');