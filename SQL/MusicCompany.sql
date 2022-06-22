CREATE TABLE Artist(

	Artist_ID VARCHAR(20) PRIMARY KEY,
	Artist_Name VARCHAR(20)

);

CREATE TABLE Address(

	Address VARCHAR(40) PRIMARY KEY,
	Artist_ID VARCHAR(20) REFERENCES Artist(Artist_ID) ON DELETE CASCADE
	
);

CREATE TABLE Album(
	
	Album_ID VARCHAR(20) PRIMARY KEY,
	Title VARCHAR(20),
	Format VARCHAR(10),
	Copyright BOOLEAN NOT NULL

);

CREATE TABLE Song(

	Song_ID VARCHAR(20) PRIMARY KEY,
	Song_Name VARCHAR(20),
	Genre VARCHAR(20),
	Album_ID VARCHAR(20) REFERENCES Album(Album_ID) on delete cascade

);

CREATE TABLE Creates(

	Song_ID VARCHAR(20) REFERENCES Song(Song_ID) on delete cascade,
	Album_ID VARCHAR(20) REFERENCES Album(Album_ID) on delete cascade,
	Artist_ID VARCHAR(20) REFERENCES Artist(Artist_ID) on delete cascade
	

);

CREATE TABLE Publishing_Company(

	License_no VARCHAR(10) PRIMARY KEY,
	Company_Name VARCHAR(20)
	
);

CREATE TABLE Contract(

	Contract_ID VARCHAR(20) PRIMARY KEY,
	Contract_Expiry DATE NOT NULL,
	Wages NUMERIC(8,2),
	License_no VARCHAR(10) REFERENCES Publishing_Company(License_no)

);

CREATE TABLE is_hired(

	Contract_ID VARCHAR(20) REFERENCES Contract(Contract_ID),
	License_no VARCHAR(10) REFERENCES Publishing_Company(License_no),
	Artist_ID VARCHAR(20) REFERENCES Artist(Artist_ID) ON DELETE CASCADE

);

CREATE TABLE users(
	uid VARCHAR(20),
	pwd VARCHAR(20),
	role VARCHAR(20),
	Artist_ID VARCHAR(20) REFERENCES Artist(Artist_ID) ON DELETE CASCADE
);

INSERT INTO Artist VALUES('PC101','Arjit Singh'),
('PC102','Dosanjh'),
('PC103','Shreya Ghoshal'),
('PC104','A.R Rahman');

INSERT INTO Address VALUES('Maharashtra','PC101'),
('Punjab','PC102'),
('Rajasthan','PC103'),
('Haryana','PC104');

INSERT INTO Album VALUES('A1','Aashiqui-2','mp3','true'),('A2','Sunoh','mp3','true'),('A3','Vande Maataram','mp3','true');
INSERT INTO Song VALUES( 'S100','Tum-Hi-Ho','Romantic','A1'),
('S101','Dil-Dodeya','Hip-Hop','A2'),
('S103','Sun-Raha-Hai','Romantic','A1'),('S104','Maa-Tujhe-Salaam','Patriotic','A3');

INSERT INTO Creates VALUES('S100','A1','PC101'),('S101','A2','PC102'),('S103','A1','PC103'),('S104','A3','PC104');

insert into users values('ABC','1234','ADMIN',null),('XYZ','4321','ARTIST','PC101');

INSERT INTO Publishing_Company VALUES('00-abcd', 'SunderLandInt'),('00-psrn', 'MilestoneMusic'), ('00-dqqi', 'KoverMinesInt'),('00-twrp','T-Series');
INSERT INTO Contract VALUES('z00-aml', '2021-05-21', 225000.00, '00-abcd'),('y00-llw', '2021-01-01', 355000.00, '00-psrn'),('x00-sxp', '2021-02-01', 500650.00, '00-dqqi'),('z00-xyz','2021-06-25',435555.00,'00-twrp');
INSERT INTO is_hired VALUES('z00-aml', '00-abcd','PC101'),('y00-llw', '00-psrn','PC102'),('x00-sxp', '00-dqqi','PC103'),('z00-xyz','00-twrp','PC104');


drop table Artist cascade;
drop table Song CASCADE;
drop table Album cascade;
drop table Address cascade;
drop table Contract cascade;
drop table Publishing_Company cascade;
drop table is_hired cascade;
drop table Creates cascade;
drop table users cascade;

SELECT * FROM Album;
SELECT * FROM Song;
SELECT * FROM Creates;
SELECT * FROM Artist;
SELECT * FROM Address;
SELECT * FROM Publishing_Company;
SELECT * FROM Contract;
SELECT * FROM is_hired;
SELECT * FROM users;

/*
1. Display the album name where the no of Artists is more than 1
*/

select Title, count(Artist_ID) from Artist natural join Album natural join Creates group by Album_ID having count(Artist_ID)>1;

/*
2. Display the Artist_ID and Name in alphabetical order of Artist_Name 
*/

select * from Artist order by Artist_Name ASC;

/*
3. Display the names of Artist/s in SunderLandInt having wages more than 3 lakh
*/

select * from Artist natural join is_hired natural join Contract where Wages > 300000;

/*
4. Find the Artist having the highest wage.
*/
select Artist_Name,Wages from Artist natural join is_hired natural join Contract where Wages = (select max(Wages) from Artist natural join is_hired natural join Contract);

/*
5. Display Song_Name,Genre,Title,Format of A.R Rahman songs which has copyright. 
*/
select Song_Name,Genre,Title,Format from Song natural join Album natural join Creates natural join Artist where Artist_Name = 'A.R Rahman' AND Copyright = 'true';

/*
6. Find the number of days remaining from the present date until A.R Rahman's contract expires.
*/

select Artist_ID,(Contract_Expiry - Current_Date) as Days_Left from Artist natural join is_hired natural join Contract where Artist_Name = 'A.R Rahman';

/*
7. Find companies having their names ending with 'Int'. 
*/

select Company_Name from Publishing_Company where Company_Name like '%Int';

/*
8. Display the Contract Expiry Month and Expiry Year of all the Artists.
*/

select Artist_Name,to_char(Contract_Expiry,'Month') as Expiry_Month,extract (YEAR from Contract_Expiry) as Expiry_Year from Contract natural join is_hired natural join Artist; 

/*
9. Display the Name & Wage of Artists having their Wages between 300000 & 600000. 
*/

select Artist_Name,Wages from Artist natural join is_hired natural join Contract where Wages between 300000 and 600000;

/*
10. Display ID of Artists who have been hired by a company.
*/
select Artist_ID from Artist intersect select Artist_ID from is_hired;