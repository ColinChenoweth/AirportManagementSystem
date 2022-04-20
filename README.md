# AirportManagementSystem
Database Systems project where we created a database for managing specific data related to airports/flights.

If you are wanting to test this database, make sure you check the comments in the amsInterface.java file for what lines to possibly edit to ensure it runs properly. Also check that the data file locations in dataInput.sql are correct for your setup.



This is the ER Diagram we attempted to create with our database:

![Alt text](relative/ER-Diagram.jpg?raw=true)



The official varriable names in our database (*note: that this list includes forieng key attributes that create the relationships*) are the following:

Customer
  - PassportNum (int, primary key)
  - CustName (char(50))
  - DOB (int)

Airport
  - Abbreviation (char(3), primary key)
  - AirportName (char(50))
  - City (char(50))
  - Size (int)

AirlineCompany
  - AirlineName (char(50), primary key)
  - CEO (char(50))
  - HomeAirport (char(3), foreign key from Airport)

Plane
  - AircraftID (int, primary key)
  - PlaneModel (char(50))
  - AirlineName (char(50), foreign key from AirlineCompany)

Seat
  - AircraftID (int, primary/foreign key from Plane)
  - SeatNum (char(3), primary key)
  - Class (char(50))

Schedule
  - FlightNum (int, primary key)
  - DepartTime (int)
  - DepartDate (int)
  - FlightStatus (char(50))
  - AircraftID (int, foreign key from Plane)
  - DestAirport (char(3), foreign key from Airport)
  - OriginAirport (char(3), foreign key from Airport)

Ticket
  - PassportNum (int, primary/foreign key from Customer
  - ConfirmationNum (int, primary key)
  - FlightNum (int, foreign key from Schedule)
  - SeatNum (char(3))
  - AircraftID (int, foreign key from Plane)

*Note: all dates are in the int from mmddyyyy and time is in military time from int 0000 to 2359.*
