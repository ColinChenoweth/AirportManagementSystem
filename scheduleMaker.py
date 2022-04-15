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

schFile = open('VS_School/341/schedule.csv', 'w', newline='')
schWriter = csv.writer(schFile)

for row in aReader:
    airports.append(row)

for row in pReader:
    planes.append(row)

for row in cReader:
    customers.append(row)

for row in sReader:
    seats.append(row)


schedule = []


#incomplete alternate which adds x flights per day each day of the year

# months = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
# m = 1
# fnum = 1
# p = 1

# for month in months:
#     for day in range(1, month+1):
#         for x in range (1, 6):
#             row = []
#             row.append(fnum)
#             row.append(randint(0,5)*400)
#             row.append(m*1000000 + day*10000 + 2023)
#             row.append("On Time")
#             row.append(p)
#             row.append(airports[randint(0,99)][0])
#             row.append(airports[randint(0,99)][0])
#             while row[5] == row[6]:
#                 row.remove(row[6])
#                 row.append(airports[randint(0,99)][0])
#             schedule.append(row)
#             fnum += 1
#             p = (p % 100) + 1
#     m += 1

#adds 1000 flights on random days at random times with random planes to random dest and orign
for fnum in range(1, 1001):
    row = []
    row.append(fnum)
    row.append(randint(0,5)*400)
    row.append(randint(1,12)*1000000 + randint(1,28)*10000 + 2023)
    row.append("On time")
    row.append(randint(1,100))
    row.append(airports[randint(0,99)][0])
    row.append(airports[randint(0,99)][0])
    while row[5] == row[6]:
        row.remove(row[6])
        row.append(airports[randint(0,99)][0])
    schedule.append(row)

schWriter.writerows(schedule)
