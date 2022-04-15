import csv
from random import randint

airports = []
aFile = open('VS_School/341/airport.csv', 'r')
aReader = csv.reader(aFile)

planes = []
pFile = open('VS_School/341/plane.csv', 'r')
pReader = csv.reader(pFile)

customers = []
cFile = open('VS_School/341/customer.csv', 'r')
cReader = csv.reader(cFile)

seats = []
sFile = open('VS_School/341/seat.csv', 'r')
sReader = csv.reader(sFile)

schedule = []
schFile = open('VS_School/341/schedule.csv', 'r')
schReader = csv.reader(schFile)

tFile = open('VS_School/341/ticket.csv', 'w', newline='')
tWriter = csv.writer(tFile)

for row in aReader:
    airports.append(row)

for row in pReader:
    planes.append(row)

for row in cReader:
    customers.append(row)

for row in sReader:
    seats.append(row)

for row in schReader:
    schedule.append(row)

tickets = []
cnum = 0
cust = 1

#goes through each scheduled flight and adds 50 people to each flight with random seat assignments
for fnum in schedule:
    fTickets = []
    for x in range(1,51):
        row = []
        row.append(cust)
        row.append(cnum)
        row.append(fnum[0])
        if(int(fnum[4])<51):
            row.append(seats[randint(0,125)][1])
            for taken in fTickets:
                while taken[3] == row[3]:
                    row.remove(row[3])
                    row.append(seats[randint(0,125)][1])
        else:
            row.append(seats[randint(6300,6461)][1])
            for taken in fTickets:
                while taken[3] == row[3]:
                    row.remove(row[3])
                    row.append(seats[randint(6300,6461)][1])
        row.append(fnum[4])
        fTickets.append(row)
        tickets.append(row)
        cnum += 1
        cust = (cust % 520) + 1

tWriter.writerows(tickets)
