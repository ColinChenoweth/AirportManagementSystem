LOAD DATA LOCAL INFILE 'C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\FakeData\\airport.csv'
INTO TABLE airport
FIELDS TERMINATED BY ','
ENCLOSED BY ''
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA LOCAL INFILE 'C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\FakeData\\schedule.csv'
INTO TABLE schedule
FIELDS TERMINATED BY ','
ENCLOSED BY ''
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA LOCAL INFILE 'C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\FakeData\\plane.csv'
INTO TABLE plane
FIELDS TERMINATED BY ','
ENCLOSED BY ''
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA LOCAL INFILE 'C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\FakeData\\customer.csv'
INTO TABLE customer
FIELDS TERMINATED BY ','
ENCLOSED BY ''
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA LOCAL INFILE 'C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\FakeData\\seat.csv'
INTO TABLE seat
FIELDS TERMINATED BY ','
ENCLOSED BY ''
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA LOCAL INFILE 'C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\FakeData\\ticket.csv'
INTO TABLE ticket
FIELDS TERMINATED BY ','
ENCLOSED BY ''
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA LOCAL INFILE 'C:\\Users\\chenb\\Documents\\GitHub\\AirportManagementSystem\\FakeData\\airline_company.csv'
INTO TABLE airline_company
FIELDS TERMINATED BY ','
ENCLOSED BY ''
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;