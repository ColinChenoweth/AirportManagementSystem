-- -----------------------------------------------------
-- Schema ams
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS ams ;
USE ams ;


DROP TABLE IF EXISTS ams.Ticket ;
DROP TABLE IF EXISTS ams.Seat ;
DROP TABLE IF EXISTS ams.Customer ;
DROP TABLE IF EXISTS ams.Schedule ;
DROP TABLE IF EXISTS ams.Plane ;
DROP TABLE IF EXISTS ams.Airline_Company ;
DROP TABLE IF EXISTS ams.Airport ;


-- -----------------------------------------------------
-- Table ams.Customer
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ams.Customer (
  PassportNum 	INT 		PRIMARY KEY,
  CustName 		VARCHAR(50) NOT NULL,
  DOB 			INT 		NOT NULL)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ams.Airport
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ams.Airport (
  Abbreviation 	VARCHAR(3)  PRIMARY KEY,
  AirportName  	VARCHAR(50) NOT NULL,
  City 			VARCHAR(50) NOT NULL,
  Size			INT			NOT NULL)
  ENGINE = InnoDB; 


-- -----------------------------------------------------
-- Table ams.Airline_Company
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ams.Airline_Company (
  AirlineName 	VARCHAR(50) PRIMARY KEY,
  CEO 			VARCHAR(50) NOT NULL,
  HomeAirport	VARCHAR(3) 	NULL,
  CONSTRAINT HomeAirport_fk FOREIGN KEY (HomeAirport)
	REFERENCES Airport(Abbreviation)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ams.Plane
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ams.Plane (
  AircraftID 	INT 		PRIMARY KEY,
  PlaneModel 	VARCHAR(50) NOT NULL,
  AirlineName	VARCHAR(50) NULL,
  CONSTRAINT AirlineName_fk FOREIGN KEY (AirlineName)
	REFERENCES Airline_Company(AirlineName)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ams.Seat
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ams.Seat (
  AircraftID 	 	INT		 	NOT NULL,
  SeatNum 		 	VARCHAR(3) 	NOT NULL,
  Class 		 	VARCHAR(50) NOT NULL,
  PRIMARY KEY (AircraftID, SeatNum),
  KEY SeatNum (SeatNum),
  CONSTRAINT AircraftID_Seat_fk FOREIGN KEY (AircraftID)
	REFERENCES Plane(AircraftID)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ams.Schedule
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ams.Schedule (
  FlightNum 	INT 		PRIMARY KEY,
  DepartTime 	INT 		NULL,
  DepartDate 	INT			NULL,
  FlightStatus 	VARCHAR(50) NOT NULL,
  AircraftID 	INT			NULL,
  DestAirport	VARCHAR(3) NOT NULL,
  OriginAirport	VARCHAR(3) NOT NULL,
  CONSTRAINT AircraftID_fk FOREIGN KEY (AircraftID)
	REFERENCES Plane(AircraftID)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT DestAirport_fk FOREIGN KEY (DestAirport)
	REFERENCES Airport(Abbreviation)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT OriginAirport_fk FOREIGN KEY (OriginAirport)
	REFERENCES Airport(Abbreviation)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;
  

-- -----------------------------------------------------
-- Table ams.Ticket
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ams.Ticket (
  PassportNum		INT 		NOT NULL,
  ConfirmationNum 	INT 		NOT NULL,
  FlightNum			INT 		NOT NULL,
  SeatNum			VARCHAR(3) 	NULL,
  AircraftID		INT 		NULL,
  PRIMARY KEY (PassportNum, ConfirmationNum),
  CONSTRAINT PassportNum_fk FOREIGN KEY (PassportNum)
	REFERENCES Customer(PassportNum)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FlightNum_fk FOREIGN KEY (FlightNum)
	REFERENCES Schedule(FlightNum)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT SeatNum_AircaftID_fk FOREIGN KEY (SeatNum, AircraftID)
	REFERENCES Seat(SeatNum, AircraftID)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;