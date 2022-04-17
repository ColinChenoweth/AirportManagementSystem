-- -----------------------------------------------------
-- Schema ams
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS ams ;
USE ams ;


-- -----------------------------------------------------
-- Table ams.Seat
-- -----------------------------------------------------
DROP TABLE IF EXISTS ams.Seat ;

CREATE TABLE IF NOT EXISTS ams.Seat (
  AircraftID 	 	INT		 	NOT NULL
	REFERENCES Plane(AircraftID)
    ON DELETE CASCADE,
  SeatNum 		 	VARCHAR(3) 	NOT NULL,
  Class 		 	VARCHAR(50) NOT NULL,
  primary key (AircraftID, SeatNum));


-- -----------------------------------------------------
-- Table ams.Ticket
-- -----------------------------------------------------
DROP TABLE IF EXISTS ams.Ticket ;

CREATE TABLE IF NOT EXISTS ams.Ticket (
  PassportNum		INT 		NOT NULL
	REFERENCES Customer(PassportNum)
    ON DELETE CASCADE,
  ConfirmationNum 	INT 		NOT NULL,
  FlightNum			INT 		NOT NULL
	REFERENCES Schedule(FlightNum),
  SeatNum			VARCHAR(3) 	NULL
  	REFERENCES Seat(SeatNum),
  AircraftID		INT 		NULL
	REFERENCES Seat(AircraftID),
  primary key (PassportNum, ConfirmationNum));


-- -----------------------------------------------------
-- Table ams.Customer
-- -----------------------------------------------------
DROP TABLE IF EXISTS ams.Customer ;

CREATE TABLE IF NOT EXISTS ams.Customer (
  PassportNum 	INT 		PRIMARY KEY,
  CustName 		VARCHAR(50) NOT NULL,
  DOB 			INT 		NOT NULL) ;


-- -----------------------------------------------------
-- Table ams.Schedule
-- -----------------------------------------------------
DROP TABLE IF EXISTS ams.Schedule ;

CREATE TABLE IF NOT EXISTS ams.Schedule (
  FlightNum 	INT 		PRIMARY KEY,
  DepartTime 	INT 		NULL,
  DepartDate 	int			NULL,
  FlightStatus 	VARCHAR(50) NOT NULL,
  AircraftID 	INT			NULL
	REFERENCES Plane(AircraftID),
  DestAirport	VARCHAR(3) NOT NULL
	REFERENCES Airport(Abbreviation),
  OriginAirport	VARCHAR(3) NOT NULL
	REFERENCES Airport(Abbreviation));


-- -----------------------------------------------------
-- Table ams.Plane
-- -----------------------------------------------------
DROP TABLE IF EXISTS ams.Plane ;

CREATE TABLE IF NOT EXISTS ams.Plane (
  AircraftID 	INT 		PRIMARY KEY,
  PlaneModel 	VARCHAR(50) NOT NULL,
  AirlineName	VARCHAR(50) NULL
	REFERENCES Airline_Company(AirlineName));


-- -----------------------------------------------------
-- Table ams.Airline_Company
-- -----------------------------------------------------
DROP TABLE IF EXISTS ams.Airline_Company ;

CREATE TABLE IF NOT EXISTS ams.Airline_Company (
  AirlineName 	VARCHAR(50) PRIMARY KEY,
  CEO 			VARCHAR(50) NOT NULL,
  HomeAirport	VARCHAR(3) 	NULL
	REFERENCES Airport(Abbreviation));


-- -----------------------------------------------------
-- Table ams.Airport
-- -----------------------------------------------------
DROP TABLE IF EXISTS ams.Airport ;

CREATE TABLE IF NOT EXISTS ams.Airport (
  Abbreviation 	VARCHAR(3)  PRIMARY KEY,
  AirportName  	VARCHAR(50) NOT NULL,
  City 			VARCHAR(50) NOT NULL,
  Size			INT			NOT NULL); 
  
-- -----------------------------------------------------
-- Table ams.Seat_Assignement
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS ams.Seat_Assignment ;

-- CREATE TABLE IF NOT EXISTS ams.Seat_Assignment (
--   SeatNum			INT
--   	REFERENCES Seat(SeatNum),
--   AircraftID		INT
-- 	REFERENCES Seat(AircraftID),
--   ConfirmationNum	INT
-- 	REFERENCES Ticket(ConfirmationNum),
--   PRIMARY KEY (SeatNum, AircraftID, ConfirmationNUM)) ;